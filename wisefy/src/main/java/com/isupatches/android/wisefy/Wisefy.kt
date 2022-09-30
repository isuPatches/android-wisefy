/*
 * Copyright 2022 Patches Klinefelter
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
@file:JvmName("WiseFy")
package com.isupatches.android.wisefy

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.ACCESS_NETWORK_STATE
import android.Manifest.permission.ACCESS_WIFI_STATE
import android.Manifest.permission.CHANGE_WIFI_STATE
import android.content.Context
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import androidx.annotation.VisibleForTesting
import com.isupatches.android.wisefy.accesspoints.AccessPointsDelegate
import com.isupatches.android.wisefy.accesspoints.WisefyAccessPointsDelegate
import com.isupatches.android.wisefy.accesspoints.callbacks.GetNearbyAccessPointCallbacks
import com.isupatches.android.wisefy.accesspoints.callbacks.GetRSSICallbacks
import com.isupatches.android.wisefy.accesspoints.callbacks.SearchForAccessPointsCallbacks
import com.isupatches.android.wisefy.accesspoints.entities.GetNearbyAccessPointsRequest
import com.isupatches.android.wisefy.accesspoints.entities.GetNearbyAccessPointsResult
import com.isupatches.android.wisefy.accesspoints.entities.GetRSSIRequest
import com.isupatches.android.wisefy.accesspoints.entities.GetRSSIResult
import com.isupatches.android.wisefy.accesspoints.entities.SearchForAccessPointsResult
import com.isupatches.android.wisefy.accesspoints.entities.SearchForAccessPointsRequest
import com.isupatches.android.wisefy.addnetwork.AddNetworkDelegate
import com.isupatches.android.wisefy.addnetwork.WisefyAddNetworkDelegate
import com.isupatches.android.wisefy.addnetwork.callbacks.AddNetworkCallbacks
import com.isupatches.android.wisefy.addnetwork.entities.AddNetworkRequest
import com.isupatches.android.wisefy.addnetwork.entities.AddNetworkResult
import com.isupatches.android.wisefy.core.assertions.WisefyAssertions
import com.isupatches.android.wisefy.core.coroutines.CoroutineDispatcherProvider
import com.isupatches.android.wisefy.core.entities.DeprecationMessages
import com.isupatches.android.wisefy.core.logging.DefaultWisefyLogger
import com.isupatches.android.wisefy.core.logging.WisefyLogger
import com.isupatches.android.wisefy.core.util.SdkUtilImpl
import com.isupatches.android.wisefy.frequency.FrequencyDelegate
import com.isupatches.android.wisefy.frequency.WisefyFrequencyDelegate
import com.isupatches.android.wisefy.frequency.callbacks.GetFrequencyCallbacks
import com.isupatches.android.wisefy.frequency.callbacks.IsNetwork5gHzCallbacks
import com.isupatches.android.wisefy.frequency.entities.GetFrequencyRequest
import com.isupatches.android.wisefy.frequency.entities.GetFrequencyResult
import com.isupatches.android.wisefy.frequency.entities.IsNetwork5gHzRequest
import com.isupatches.android.wisefy.frequency.entities.IsNetwork5gHzResult
import com.isupatches.android.wisefy.networkconnection.NetworkConnectionDelegate
import com.isupatches.android.wisefy.networkconnection.WisefyNetworkConnectionDelegate
import com.isupatches.android.wisefy.networkconnection.callbacks.ConnectToNetworkCallbacks
import com.isupatches.android.wisefy.networkconnection.callbacks.DisconnectFromCurrentNetworkCallbacks
import com.isupatches.android.wisefy.networkconnection.entities.ConnectToNetworkRequest
import com.isupatches.android.wisefy.networkconnection.entities.ConnectToNetworkResult
import com.isupatches.android.wisefy.networkconnection.entities.DisconnectFromCurrentNetworkRequest
import com.isupatches.android.wisefy.networkconnection.entities.DisconnectFromCurrentNetworkResult
import com.isupatches.android.wisefy.networkconnectionstatus.NetworkConnectionStatusDelegate
import com.isupatches.android.wisefy.networkconnectionstatus.WisefyNetworkConnectionStatusDelegate
import com.isupatches.android.wisefy.networkconnectionstatus.callbacks.GetNetworkConnectionStatusCallbacks
import com.isupatches.android.wisefy.networkconnectionstatus.entities.GetNetworkConnectionStatusRequest
import com.isupatches.android.wisefy.networkconnectionstatus.entities.GetNetworkConnectionStatusResult
import com.isupatches.android.wisefy.networkinfo.NetworkInfoDelegate
import com.isupatches.android.wisefy.networkinfo.WisefyNetworkInfoDelegate
import com.isupatches.android.wisefy.networkinfo.callbacks.GetCurrentNetworkCallbacks
import com.isupatches.android.wisefy.networkinfo.callbacks.GetCurrentNetworkInfoCallbacks
import com.isupatches.android.wisefy.networkinfo.callbacks.GetIPCallbacks
import com.isupatches.android.wisefy.networkinfo.entities.GetCurrentNetworkInfoRequest
import com.isupatches.android.wisefy.networkinfo.entities.GetCurrentNetworkInfoResult
import com.isupatches.android.wisefy.networkinfo.entities.GetCurrentNetworkRequest
import com.isupatches.android.wisefy.networkinfo.entities.GetCurrentNetworkResult
import com.isupatches.android.wisefy.networkinfo.entities.GetIPRequest
import com.isupatches.android.wisefy.networkinfo.entities.GetIPResult
import com.isupatches.android.wisefy.removenetwork.RemoveNetworkDelegate
import com.isupatches.android.wisefy.removenetwork.WisefyRemoveNetworkDelegate
import com.isupatches.android.wisefy.removenetwork.callbacks.RemoveNetworkCallbacks
import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkRequest
import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkResult
import com.isupatches.android.wisefy.savednetworks.SavedNetworkDelegate
import com.isupatches.android.wisefy.savednetworks.WisefySavedNetworkDelegate
import com.isupatches.android.wisefy.savednetworks.callbacks.GetSavedNetworksCallbacks
import com.isupatches.android.wisefy.savednetworks.callbacks.IsNetworkSavedCallbacks
import com.isupatches.android.wisefy.savednetworks.callbacks.SearchForSavedNetworksCallbacks
import com.isupatches.android.wisefy.savednetworks.entities.GetSavedNetworksRequest
import com.isupatches.android.wisefy.savednetworks.entities.GetSavedNetworksResult
import com.isupatches.android.wisefy.savednetworks.entities.IsNetworkSavedRequest
import com.isupatches.android.wisefy.savednetworks.entities.IsNetworkSavedResult
import com.isupatches.android.wisefy.savednetworks.entities.SearchForSavedNetworksRequest
import com.isupatches.android.wisefy.savednetworks.entities.SearchForSavedNetworksResult
import com.isupatches.android.wisefy.security.SecurityDelegate
import com.isupatches.android.wisefy.security.WisefySecurityDelegate
import com.isupatches.android.wisefy.security.entities.ContainsSecurityCapabilityRequest
import com.isupatches.android.wisefy.security.entities.ContainsSecurityCapabilityResult
import com.isupatches.android.wisefy.security.entities.IsNetworkSecureRequest
import com.isupatches.android.wisefy.security.entities.IsNetworkSecureResult
import com.isupatches.android.wisefy.signal.SignalDelegate
import com.isupatches.android.wisefy.signal.WisefySignalDelegate
import com.isupatches.android.wisefy.signal.entities.CalculateBarsRequest
import com.isupatches.android.wisefy.signal.entities.CalculateBarsResult
import com.isupatches.android.wisefy.signal.entities.CompareSignalLevelRequest
import com.isupatches.android.wisefy.signal.entities.CompareSignalLevelResult
import com.isupatches.android.wisefy.wifi.WifiDelegate
import com.isupatches.android.wisefy.wifi.WisefyWifiDelegate
import com.isupatches.android.wisefy.wifi.callbacks.DisableWifiCallbacks
import com.isupatches.android.wisefy.wifi.callbacks.EnableWifiCallbacks
import com.isupatches.android.wisefy.wifi.callbacks.IsWifiEnabledCallbacks
import com.isupatches.android.wisefy.wifi.entities.DisableWifiRequest
import com.isupatches.android.wisefy.wifi.entities.DisableWifiResult
import com.isupatches.android.wisefy.wifi.entities.EnableWifiRequest
import com.isupatches.android.wisefy.wifi.entities.EnableWifiResult
import com.isupatches.android.wisefy.wifi.entities.IsWifiEnabledRequest
import com.isupatches.android.wisefy.wifi.entities.IsWifiEnabledResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.sync.Mutex

/**
 * The private constructor used by [Brains] to create a Wisefy instance.
 *
 * @param accessPointsDelegate The [AccessPointsDelegate] instance to use
 * @param addNetworkDelegate The [AddNetworkDelegate] instance to use
 * @param frequencyDelegate The [FrequencyDelegate] instance to use
 * @param networkConnectionDelegate The [NetworkConnectionDelegate] instance to use
 * @param networkConnectionStatusDelegate The [NetworkConnectionStatusDelegate] instance to use
 * @param networkInfoDelegate The [NetworkInfoDelegate] instance to use
 * @param removeNetworkDelegate The [RemoveNetworkDelegate] instance to use
 * @param savedNetworkDelegate The [SavedNetworkDelegate] instance to use
 * @param securityDelegate The [SecurityDelegate] instance to use
 * @param signalDelegate The [SignalDelegate] instance to use
 * @param wifiDelegate The [WifiDelegate] instance to use
 * @param scope The [CoroutineScope] to use for async operations
 *
 * @author Patches Klinefelter
 * @since 07/2022, version 5.0.0
 */
@Suppress("SyntheticAccessor")
class Wisefy private constructor(
    private val accessPointsDelegate: AccessPointsDelegate,
    private val addNetworkDelegate: AddNetworkDelegate,
    private val frequencyDelegate: FrequencyDelegate,
    private val networkConnectionDelegate: NetworkConnectionDelegate,
    private val networkConnectionStatusDelegate: NetworkConnectionStatusDelegate,
    private val networkInfoDelegate: NetworkInfoDelegate,
    private val removeNetworkDelegate: RemoveNetworkDelegate,
    private val savedNetworkDelegate: SavedNetworkDelegate,
    private val securityDelegate: SecurityDelegate,
    private val signalDelegate: SignalDelegate,
    private val wifiDelegate: WifiDelegate,
    private val scope: CoroutineScope
) : WisefyApi {

    /**
     * The public builder to create a Wisefy instance.
     *
     * @param context The application context. Used for creating a [ConnectivityManager] and [wifiManager] instance
     * to use within Wisefy
     * @param logger The [WisefyLogger] instance to use within Wisefy
     *
     * @author Patches Klinefelter
     * @since 07/2022, version 5.0.0
     */
    class Brains @JvmOverloads constructor(
        context: Context,
        private val throwOnAssertions: Boolean = false,
        private var logger: WisefyLogger = DefaultWisefyLogger()
    ) {

        private var connectivityManager: ConnectivityManager
        private var wifiManager: WifiManager

        private var accessPointsDelegate: AccessPointsDelegate
        private var addNetworkDelegate: AddNetworkDelegate
        private var frequencyDelegate: FrequencyDelegate
        private var networkConnectionDelegate: NetworkConnectionDelegate
        private var networkConnectionStatusDelegate: NetworkConnectionStatusDelegate
        private var networkInfoDelegate: NetworkInfoDelegate
        private var removeNetworkDelegate: RemoveNetworkDelegate
        private var savedNetworkDelegate: SavedNetworkDelegate
        private var securityDelegate: SecurityDelegate
        private var signalDelegate: SignalDelegate
        private var wifiDelegate: WifiDelegate
        private var wisefyScope: CoroutineScope

        init {
            connectivityManager = context.applicationContext.getSystemService(
                Context.CONNECTIVITY_SERVICE
            ) as ConnectivityManager
            wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

            val sdkUtil = SdkUtilImpl()
            val coroutineDispatcherProvider = CoroutineDispatcherProvider()
            wisefyScope = CoroutineScope(Job() + coroutineDispatcherProvider.io)
            val assertions = WisefyAssertions(throwOnAssertions)
            val savedNetworkMutex = Mutex()

            // Used by other utils
            savedNetworkDelegate = WisefySavedNetworkDelegate(
                coroutineDispatcherProvider = coroutineDispatcherProvider,
                scope = wisefyScope,
                savedNetworkMutex = savedNetworkMutex,
                logger = logger,
                sdkUtil = sdkUtil,
                wifiManager = wifiManager,
                assertions = assertions
            )
            networkConnectionStatusDelegate = WisefyNetworkConnectionStatusDelegate(
                coroutineDispatcherProvider = coroutineDispatcherProvider,
                scope = wisefyScope,
                connectivityManager = connectivityManager,
                logger = logger,
                sdkUtil = sdkUtil,
                wifiManager = wifiManager
            )

            // Not used by other utils
            accessPointsDelegate = WisefyAccessPointsDelegate(
                coroutineDispatcherProvider = coroutineDispatcherProvider,
                scope = wisefyScope,
                logger = logger,
                wifiManager = wifiManager
            )
            addNetworkDelegate = WisefyAddNetworkDelegate(
                coroutineDispatcherProvider = coroutineDispatcherProvider,
                logger = logger,
                scope = wisefyScope,
                sdkUtil = sdkUtil,
                wifiManager = wifiManager,
                assertions = assertions
            )
            frequencyDelegate = WisefyFrequencyDelegate(
                coroutineDispatcherProvider = coroutineDispatcherProvider,
                scope = wisefyScope,
                logger = logger,
                wifiManager = wifiManager,
                connectivityManager = connectivityManager
            )
            networkConnectionDelegate = WisefyNetworkConnectionDelegate(
                coroutineDispatcherProvider = coroutineDispatcherProvider,
                scope = wisefyScope,
                connectivityManager = connectivityManager,
                logger = logger,
                networkConnectionStatusDelegate = networkConnectionStatusDelegate,
                savedNetworkDelegate = savedNetworkDelegate,
                sdkUtil = sdkUtil,
                wifiManager = wifiManager,
                assertions = assertions
            )
            networkInfoDelegate = WisefyNetworkInfoDelegate(
                coroutineDispatcherProvider = coroutineDispatcherProvider,
                scope = wisefyScope,
                connectivityManager = connectivityManager,
                logger = logger,
                wifiManager = wifiManager
            )
            removeNetworkDelegate = WisefyRemoveNetworkDelegate(
                coroutineDispatcherProvider = coroutineDispatcherProvider,
                scope = wisefyScope,
                logger = logger,
                savedNetworkDelegate = savedNetworkDelegate,
                sdkUtil = sdkUtil,
                wifiManager = wifiManager,
                assertions = assertions
            )
            securityDelegate = WisefySecurityDelegate(
                logger = logger
            )
            signalDelegate = WisefySignalDelegate(
                logger = logger,
                sdkUtil = sdkUtil,
                wifiManager = wifiManager,
                assertions = assertions
            )
            wifiDelegate = WisefyWifiDelegate(
                coroutineDispatcherProvider = coroutineDispatcherProvider,
                scope = wisefyScope,
                logger = logger,
                sdkUtil = sdkUtil,
                wifiManager = wifiManager,
                assertions = assertions
            )
        }

        /**
         * A function to override the logger that Wisefy uses.
         *
         * @param logger The custom [WisefyLogger] instance to use
         *
         * @see WisefyLogger
         *
         * @return [Brains] - The builder instance
         *
         * @author Patches Klinefelter
         * @since 07/2022, version 5.0.0
         */
        internal fun logger(logger: WisefyLogger): Brains = apply {
            this.logger = logger
        }

        /**
         * A function to override the [ConnectivityManager] that Wisefy uses.
         *
         * @param connectivityManager The custom [ConnectivityManager] instance to use
         *
         * @return [Brains] - The builder instance
         *
         * @author Patches Klinefelter
         * @since 07/2022, version 5.0.0
         */
        @VisibleForTesting
        internal fun customConnectivityManager(connectivityManager: ConnectivityManager): Brains = apply {
            this.connectivityManager = connectivityManager
        }

        /**
         * A function to override the [WifiManager] that Wisefy uses.
         *
         * @param wifiManager The custom [WifiManager] instance to use
         *
         * @return [Brains] - The builder instance
         *
         * @author Patches Klinefelter
         * @since 07/2022, version 5.0.0
         */
        @VisibleForTesting
        internal fun customWifiManager(wifiManager: WifiManager): Brains = apply {
            this.wifiManager = wifiManager
        }

        /**
         * A function to override the [AccessPointsDelegate] that Wisefy uses.
         *
         * @param accessPointsDelegate The custom [AccessPointsDelegate] instance to use
         *
         * @see AccessPointsDelegate
         *
         * @return [Brains] - The builder instance
         *
         * @author Patches Klinefelter
         * @since 07/2022, version 5.0.0
         */
        @VisibleForTesting
        internal fun customAccessPointsDelegate(accessPointsDelegate: AccessPointsDelegate): Brains = apply {
            this.accessPointsDelegate = accessPointsDelegate
        }

        /**
         * A function to override the [AddNetworkDelegate] that Wisefy uses.
         *
         * @param addNetworkDelegate The custom [AddNetworkDelegate] instance to use
         *
         * @see AddNetworkDelegate
         *
         * @return [Brains] - The builder instance
         *
         * @author Patches Klinefelter
         * @since 07/2022, version 5.0.0
         */
        @VisibleForTesting
        internal fun customAddNetworkDelegate(addNetworkDelegate: AddNetworkDelegate): Brains = apply {
            this.addNetworkDelegate = addNetworkDelegate
        }

        /**
         * A function to override the [FrequencyDelegate] that Wisefy uses.
         *
         * @param frequencyDelegate The custom [FrequencyDelegate] instance to use
         *
         * @see FrequencyDelegate
         *
         * @return [Brains] - The builder instance
         *
         * @author Patches Klinefelter
         * @since 07/2022, version 5.0.0
         */
        @VisibleForTesting
        internal fun customFrequencyDelegate(frequencyDelegate: FrequencyDelegate): Brains = apply {
            this.frequencyDelegate = frequencyDelegate
        }

        /**
         * A function to override the [NetworkConnectionDelegate] that Wisefy uses.
         *
         * @param networkConnectionDelegate The custom [NetworkConnectionDelegate] instance to use
         *
         * @see NetworkConnectionDelegate
         *
         * @return [Brains] - The builder instance
         *
         * @author Patches Klinefelter
         * @since 07/2022, version 5.0.0
         */
        @VisibleForTesting
        internal fun customNetworkConnectionDelegate(
            networkConnectionDelegate: NetworkConnectionDelegate
        ): Brains = apply {
            this.networkConnectionDelegate = networkConnectionDelegate
        }

        /**
         * A function to override the [NetworkConnectionStatusDelegate] that Wisefy uses.
         *
         * @param networkConnectionStatusDelegate The custom [NetworkConnectionStatusDelegate] instance to use
         *
         * @see NetworkConnectionStatusDelegate
         *
         * @return [Brains] - The builder instance
         *
         * @author Patches Klinefelter
         * @since 07/2022, version 5.0.0
         */
        @VisibleForTesting
        internal fun customNetworkConnectionStatusDelegate(
            networkConnectionStatusDelegate: NetworkConnectionStatusDelegate
        ): Brains = apply {
            this.networkConnectionStatusDelegate = networkConnectionStatusDelegate
        }

        /**
         * A function to override the [NetworkInfoDelegate] that Wisefy uses.
         *
         * @param networkInfoDelegate The custom [NetworkInfoDelegate] instance to use
         *
         * @see NetworkInfoDelegate
         *
         * @return [Brains] - The builder instance
         *
         * @author Patches Klinefelter
         * @since 07/2022, version 5.0.0
         */
        @VisibleForTesting
        internal fun customNetworkInfoDelegate(networkInfoDelegate: NetworkInfoDelegate): Brains = apply {
            this.networkInfoDelegate = networkInfoDelegate
        }

        /**
         * A function to override the [RemoveNetworkDelegate] that Wisefy uses.
         *
         * @param removeNetworkDelegate The custom [RemoveNetworkDelegate] instance to use
         *
         * @see RemoveNetworkDelegate
         *
         * @return [Brains] - The builder instance
         *
         * @author Patches Klinefelter
         * @since 07/2022, version 5.0.0
         */
        @VisibleForTesting
        internal fun customRemoveNetworkDelegate(removeNetworkDelegate: RemoveNetworkDelegate): Brains = apply {
            this.removeNetworkDelegate = removeNetworkDelegate
        }

        /**
         * A function to override the [SavedNetworkDelegate] that Wisefy uses.
         *
         * @param savedNetworkDelegate The custom [SavedNetworkDelegate] instance to use
         *
         * @see SavedNetworkDelegate
         *
         * @return [Brains] - The builder instance
         *
         * @author Patches Klinefelter
         * @since 07/2022, version 5.0.0
         */
        @VisibleForTesting
        internal fun customSavedNetworkDelegate(savedNetworkDelegate: SavedNetworkDelegate): Brains = apply {
            this.savedNetworkDelegate = savedNetworkDelegate
        }

        /**
         * A function to override the [SecurityDelegate] that Wisefy uses.
         *
         * @param securityDelegate The custom [SecurityDelegate] instance to use
         *
         * @see SecurityDelegate
         *
         * @return [Brains] - The builder instance
         *
         * @author Patches Klinefelter
         * @since 07/2022, version 5.0.0
         */
        @VisibleForTesting
        internal fun customSecurityDelegate(securityDelegate: SecurityDelegate): Brains = apply {
            this.securityDelegate = securityDelegate
        }

        /**
         * A function to override the [SignalDelegate] that Wisefy uses.
         *
         * @param signalDelegate The custom [SignalDelegate] instance to use
         *
         * @see SignalDelegate
         *
         * @return [Brains] - The builder instance
         *
         * @author Patches Klinefelter
         * @since 07/2022, version 5.0.0
         */
        @VisibleForTesting
        internal fun customSignalDelegate(signalDelegate: SignalDelegate): Brains = apply {
            this.signalDelegate = signalDelegate
        }

        /**
         * A function to override the [WifiDelegate] that Wisefy uses.
         *
         * @param wifiDelegate The custom [WifiDelegate] instance to use
         *
         * @see WifiDelegate
         *
         * @return [Brains] - The builder instance
         *
         * @author Patches Klinefelter
         * @since 07/2022, version 5.0.0
         */
        @VisibleForTesting
        internal fun customWifiDelegate(wifiDelegate: WifiDelegate): Brains = apply {
            this.wifiDelegate = wifiDelegate
        }

        /**
         * A function on the [Brains] builder class that returns a Wisefy instance ([WisefyApi]) and the equivalent to a
         * builder.build() call.
         *
         * @return WisefyApi - The Wisefy instance created by the [Wisefy.Brains] builder
         *
         * @author Patches Klinefelter
         * @since 07/2022, version 5.0.0
         */
        fun getSmarts(): WisefyApi {
            return Wisefy(
                accessPointsDelegate = accessPointsDelegate,
                addNetworkDelegate = addNetworkDelegate,
                frequencyDelegate = frequencyDelegate,
                networkConnectionDelegate = networkConnectionDelegate,
                networkConnectionStatusDelegate = networkConnectionStatusDelegate,
                networkInfoDelegate = networkInfoDelegate,
                removeNetworkDelegate = removeNetworkDelegate,
                savedNetworkDelegate = savedNetworkDelegate,
                securityDelegate = securityDelegate,
                signalDelegate = signalDelegate,
                wifiDelegate = wifiDelegate,
                scope = wisefyScope
            )
        }
    }

    override fun init() {
        networkConnectionStatusDelegate.attachNetworkWatcher()
    }

    override fun dump() {
        scope.coroutineContext[Job]?.cancelChildren()
        networkConnectionStatusDelegate.detachNetworkWatcher()
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addNetwork(request: AddNetworkRequest): AddNetworkResult {
        return addNetworkDelegate.addNetwork(request)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addNetwork(request: AddNetworkRequest, callbacks: AddNetworkCallbacks?) {
        addNetworkDelegate.addNetwork(request, callbacks)
    }

    override fun calculateBars(request: CalculateBarsRequest): CalculateBarsResult {
        return signalDelegate.calculateBars(request)
    }

    override fun compareSignalLevel(request: CompareSignalLevelRequest): CompareSignalLevelResult {
        return signalDelegate.compareSignalLevel(request)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun connectToNetwork(request: ConnectToNetworkRequest): ConnectToNetworkResult {
        return networkConnectionDelegate.connectToNetwork(request)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun connectToNetwork(request: ConnectToNetworkRequest, callbacks: ConnectToNetworkCallbacks?) {
        networkConnectionDelegate.connectToNetwork(request, callbacks)
    }

    @Deprecated(DeprecationMessages.Wifi.DISABLE)
    override fun disableWifi(request: DisableWifiRequest): DisableWifiResult {
        return wifiDelegate.disableWifi(request)
    }

    @Deprecated(DeprecationMessages.Wifi.DISABLE)
    override fun disableWifi(request: DisableWifiRequest, callbacks: DisableWifiCallbacks?) {
        wifiDelegate.disableWifi(request, callbacks)
    }

    override fun doesNetworkContainSecurityCapability(
        request: ContainsSecurityCapabilityRequest
    ): ContainsSecurityCapabilityResult {
        return securityDelegate.doesNetworkContainSecurityCapability(request)
    }

    @Deprecated(DeprecationMessages.NetworkConnection.DisconnectFromCurrentNetwork)
    override fun disconnectFromCurrentNetwork(
        request: DisconnectFromCurrentNetworkRequest
    ): DisconnectFromCurrentNetworkResult {
        return networkConnectionDelegate.disconnectFromCurrentNetwork(request)
    }

    @Deprecated(DeprecationMessages.NetworkConnection.DisconnectFromCurrentNetwork)
    override fun disconnectFromCurrentNetwork(
        request: DisconnectFromCurrentNetworkRequest,
        callbacks: DisconnectFromCurrentNetworkCallbacks?
    ) {
        networkConnectionDelegate.disconnectFromCurrentNetwork(request, callbacks)
    }

    @Deprecated(DeprecationMessages.Wifi.ENABLE)
    override fun enableWifi(request: EnableWifiRequest): EnableWifiResult {
        return wifiDelegate.enableWifi(request)
    }

    @Deprecated(DeprecationMessages.Wifi.ENABLE)
    override fun enableWifi(request: EnableWifiRequest, callbacks: EnableWifiCallbacks?) {
        wifiDelegate.enableWifi(request, callbacks)
    }

    override fun getCurrentNetwork(request: GetCurrentNetworkRequest): GetCurrentNetworkResult {
        return networkInfoDelegate.getCurrentNetwork()
    }

    override fun getCurrentNetwork(request: GetCurrentNetworkRequest, callbacks: GetCurrentNetworkCallbacks?) {
        networkInfoDelegate.getCurrentNetwork(request, callbacks)
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun getCurrentNetworkInfo(request: GetCurrentNetworkInfoRequest): GetCurrentNetworkInfoResult {
        return networkInfoDelegate.getCurrentNetworkInfo(request)
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun getCurrentNetworkInfo(
        request: GetCurrentNetworkInfoRequest,
        callbacks: GetCurrentNetworkInfoCallbacks?
    ) {
        networkInfoDelegate.getCurrentNetworkInfo(request, callbacks)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_NETWORK_STATE])
    override fun getFrequency(request: GetFrequencyRequest): GetFrequencyResult {
        return frequencyDelegate.getFrequency()
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_NETWORK_STATE])
    override fun getFrequency(request: GetFrequencyRequest, callbacks: GetFrequencyCallbacks?) {
        frequencyDelegate.getFrequency(request, callbacks)
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun getIP(request: GetIPRequest): GetIPResult {
        return networkInfoDelegate.getIP()
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun getIP(request: GetIPRequest, callbacks: GetIPCallbacks?) {
        networkInfoDelegate.getIP(request, callbacks)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getNearbyAccessPoints(request: GetNearbyAccessPointsRequest): GetNearbyAccessPointsResult {
        return accessPointsDelegate.getNearbyAccessPoints(request)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getNearbyAccessPoints(
        request: GetNearbyAccessPointsRequest,
        callbacks: GetNearbyAccessPointCallbacks?
    ) {
        accessPointsDelegate.getNearbyAccessPoints(request, callbacks)
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun getNetworkConnectionStatus(request: GetNetworkConnectionStatusRequest): GetNetworkConnectionStatusResult {
        return networkConnectionStatusDelegate.getNetworkConnectionStatus(request)
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun getNetworkConnectionStatus(
        request: GetNetworkConnectionStatusRequest,
        callbacks: GetNetworkConnectionStatusCallbacks?
    ) {
        networkConnectionStatusDelegate.getNetworkConnectionStatus(request, callbacks)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getRSSI(request: GetRSSIRequest): GetRSSIResult {
        return accessPointsDelegate.getRSSI(request)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getRSSI(request: GetRSSIRequest, callbacks: GetRSSICallbacks?) {
        accessPointsDelegate.getRSSI(request, callbacks)
    }

    @RequiresPermission(allOf = [ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun getSavedNetworks(request: GetSavedNetworksRequest): GetSavedNetworksResult {
        return savedNetworkDelegate.getSavedNetworks(request)
    }

    @RequiresPermission(allOf = [ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun getSavedNetworks(request: GetSavedNetworksRequest, callbacks: GetSavedNetworksCallbacks?) {
        savedNetworkDelegate.getSavedNetworks(request, callbacks)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_NETWORK_STATE])
    override fun isNetwork5gHz(request: IsNetwork5gHzRequest): IsNetwork5gHzResult {
        return frequencyDelegate.isNetwork5gHz()
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_NETWORK_STATE])
    override fun isNetwork5gHz(request: IsNetwork5gHzRequest, callbacks: IsNetwork5gHzCallbacks?) {
        frequencyDelegate.isNetwork5gHz(request, callbacks)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun isNetworkSaved(request: IsNetworkSavedRequest): IsNetworkSavedResult {
        return savedNetworkDelegate.isNetworkSaved(request)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun isNetworkSaved(request: IsNetworkSavedRequest, callbacks: IsNetworkSavedCallbacks?) {
        return savedNetworkDelegate.isNetworkSaved(request, callbacks)
    }

    override fun isNetworkSecure(request: IsNetworkSecureRequest): IsNetworkSecureResult {
        return securityDelegate.isNetworkSecure(request)
    }

    override fun isWifiEnabled(request: IsWifiEnabledRequest): IsWifiEnabledResult {
        return wifiDelegate.isWifiEnabled()
    }

    override fun isWifiEnabled(request: IsWifiEnabledRequest, callbacks: IsWifiEnabledCallbacks?) {
        wifiDelegate.isWifiEnabled(request, callbacks)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun removeNetwork(request: RemoveNetworkRequest): RemoveNetworkResult {
        return removeNetworkDelegate.removeNetwork(request)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun removeNetwork(request: RemoveNetworkRequest, callbacks: RemoveNetworkCallbacks?) {
        removeNetworkDelegate.removeNetwork(request, callbacks)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForAccessPoints(request: SearchForAccessPointsRequest): SearchForAccessPointsResult {
        return accessPointsDelegate.searchForAccessPoints(request)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForAccessPoints(
        request: SearchForAccessPointsRequest,
        callbacks: SearchForAccessPointsCallbacks?
    ) {
        accessPointsDelegate.searchForAccessPoints(request, callbacks)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForSavedNetwork(request: SearchForSavedNetworksRequest): SearchForSavedNetworksResult {
        return savedNetworkDelegate.searchForSavedNetwork(request)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForSavedNetworks(
        request: SearchForSavedNetworksRequest,
        callbacks: SearchForSavedNetworksCallbacks?
    ) {
        savedNetworkDelegate.searchForSavedNetworks(request, callbacks)
    }
}
