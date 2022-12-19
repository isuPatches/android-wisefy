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

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.ACCESS_NETWORK_STATE
import android.Manifest.permission.ACCESS_WIFI_STATE
import android.Manifest.permission.CHANGE_NETWORK_STATE
import android.Manifest.permission.CHANGE_WIFI_STATE
import android.content.Context
import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import androidx.annotation.VisibleForTesting
import com.isupatches.android.wisefy.Wisefy.Brains
import com.isupatches.android.wisefy.accesspoints.AccessPointsDelegate
import com.isupatches.android.wisefy.accesspoints.WisefyAccessPointsDelegate
import com.isupatches.android.wisefy.accesspoints.callbacks.GetAccessPointsCallbacks
import com.isupatches.android.wisefy.accesspoints.entities.GetAccessPointsQuery
import com.isupatches.android.wisefy.accesspoints.entities.GetAccessPointsResult
import com.isupatches.android.wisefy.addnetwork.AddNetworkDelegate
import com.isupatches.android.wisefy.addnetwork.WisefyAddNetworkDelegate
import com.isupatches.android.wisefy.addnetwork.callbacks.AddNetworkCallbacks
import com.isupatches.android.wisefy.addnetwork.entities.AddNetworkRequest
import com.isupatches.android.wisefy.addnetwork.entities.AddNetworkResult
import com.isupatches.android.wisefy.core.assertions.WisefyAssertions
import com.isupatches.android.wisefy.core.coroutines.CoroutineDispatcherProvider
import com.isupatches.android.wisefy.core.entities.NetworkConnectionStatus
import com.isupatches.android.wisefy.core.logging.DefaultWisefyLogger
import com.isupatches.android.wisefy.core.logging.WisefyLogger
import com.isupatches.android.wisefy.core.util.SdkUtilImpl
import com.isupatches.android.wisefy.networkconnection.NetworkConnectionDelegate
import com.isupatches.android.wisefy.networkconnection.WisefyNetworkConnectionDelegate
import com.isupatches.android.wisefy.networkconnection.callbacks.ConnectToNetworkCallbacks
import com.isupatches.android.wisefy.networkconnection.callbacks.DisconnectFromCurrentNetworkCallbacks
import com.isupatches.android.wisefy.networkconnection.entities.ConnectToNetworkRequest
import com.isupatches.android.wisefy.networkconnection.entities.ConnectToNetworkResult
import com.isupatches.android.wisefy.networkconnection.entities.DisconnectFromCurrentNetworkRequest
import com.isupatches.android.wisefy.networkconnection.entities.DisconnectFromCurrentNetworkResult
import com.isupatches.android.wisefy.networkinfo.NetworkInfoDelegate
import com.isupatches.android.wisefy.networkinfo.WisefyNetworkInfoDelegate
import com.isupatches.android.wisefy.networkinfo.callbacks.GetCurrentNetworkCallbacks
import com.isupatches.android.wisefy.networkinfo.callbacks.GetNetworkConnectionStatusCallbacks
import com.isupatches.android.wisefy.networkinfo.entities.GetCurrentNetworkQuery
import com.isupatches.android.wisefy.networkinfo.entities.GetCurrentNetworkResult
import com.isupatches.android.wisefy.networkinfo.entities.GetNetworkConnectionStatusQuery
import com.isupatches.android.wisefy.networkinfo.entities.GetNetworkConnectionStatusResult
import com.isupatches.android.wisefy.removenetwork.RemoveNetworkDelegate
import com.isupatches.android.wisefy.removenetwork.WisefyRemoveNetworkDelegate
import com.isupatches.android.wisefy.removenetwork.callbacks.RemoveNetworkCallbacks
import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkRequest
import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkResult
import com.isupatches.android.wisefy.savednetworks.SavedNetworkDelegate
import com.isupatches.android.wisefy.savednetworks.WisefySavedNetworkDelegate
import com.isupatches.android.wisefy.savednetworks.callbacks.GetSavedNetworksCallbacks
import com.isupatches.android.wisefy.savednetworks.callbacks.IsNetworkSavedCallbacks
import com.isupatches.android.wisefy.savednetworks.entities.GetSavedNetworksQuery
import com.isupatches.android.wisefy.savednetworks.entities.GetSavedNetworksResult
import com.isupatches.android.wisefy.savednetworks.entities.IsNetworkSavedQuery
import com.isupatches.android.wisefy.savednetworks.entities.IsNetworkSavedResult
import com.isupatches.android.wisefy.signal.SignalDelegate
import com.isupatches.android.wisefy.signal.WisefySignalDelegate
import com.isupatches.android.wisefy.signal.entities.CalculateSignalLevelRequest
import com.isupatches.android.wisefy.signal.entities.CalculateSignalLevelResult
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
import com.isupatches.android.wisefy.wifi.entities.IsWifiEnabledQuery
import com.isupatches.android.wisefy.wifi.entities.IsWifiEnabledResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/**
 * The private constructor used by [Brains] to create a Wisefy instance.
 *
 * @param accessPointsDelegate The [AccessPointsDelegate] instance to use
 * @param addNetworkDelegate The [AddNetworkDelegate] instance to use
 * @param networkConnectionDelegate The [NetworkConnectionDelegate] instance to use
 * @param networkInfoDelegate The [NetworkInfoDelegate] instance to use
 * @param removeNetworkDelegate The [RemoveNetworkDelegate] instance to use
 * @param savedNetworkDelegate The [SavedNetworkDelegate] instance to use
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
    private val networkConnectionDelegate: NetworkConnectionDelegate,
    private val networkInfoDelegate: NetworkInfoDelegate,
    private val removeNetworkDelegate: RemoveNetworkDelegate,
    private val savedNetworkDelegate: SavedNetworkDelegate,
    private val signalDelegate: SignalDelegate,
    private val wifiDelegate: WifiDelegate,
    private val logger: WisefyLogger,
    private val scope: CoroutineScope,
    private val connectivityManager: ConnectivityManager,
    private val networkConnectionMutex: Mutex
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
        throwOnAssertions: Boolean = false,
        private var logger: WisefyLogger = DefaultWisefyLogger()
    ) {

        private var connectivityManager: ConnectivityManager
        private var wifiManager: WifiManager

        private var accessPointsDelegate: AccessPointsDelegate
        private var addNetworkDelegate: AddNetworkDelegate
        private var networkConnectionDelegate: NetworkConnectionDelegate
        private var networkInfoDelegate: NetworkInfoDelegate
        private var removeNetworkDelegate: RemoveNetworkDelegate
        private var savedNetworkDelegate: SavedNetworkDelegate
        private var signalDelegate: SignalDelegate
        private var wifiDelegate: WifiDelegate
        private var wisefyScope: CoroutineScope

        private var networkConnectionMutex: Mutex

        init {
            connectivityManager = context.applicationContext.getSystemService(
                Context.CONNECTIVITY_SERVICE
            ) as ConnectivityManager
            wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

            val sdkUtil = SdkUtilImpl()
            val coroutineDispatcherProvider = CoroutineDispatcherProvider()
            wisefyScope = CoroutineScope(SupervisorJob() + coroutineDispatcherProvider.io)
            val assertions = WisefyAssertions(throwOnAssertions)

            /*
             * Used to ensure async conflicts don't happen with these features:
             * - Connecting to a network
             * - Disconnecting from a network
             * - Getting the current network connection info
             * - Getting the current network connection status
             */
            networkConnectionMutex = Mutex()

            /*
             * Used to ensure async conflicts don't happen with these features:
             * - Adding a network
             * - Removing a network
             * - Checking if a network is saved
             * - Searching for a saved network
             */
            val savedNetworkMutex = Mutex()

            /*
             * Used to ensure async conflicts don't happen with these features:
             * - Enabling Wifi
             * - Disabling Wifi
             * - Checking if Wifi is enabled
             */
            val wifiMutex = Mutex()

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
                savedNetworkMutex = savedNetworkMutex,
                sdkUtil = sdkUtil,
                wifiManager = wifiManager,
                assertions = assertions
            )
            networkConnectionDelegate = WisefyNetworkConnectionDelegate(
                coroutineDispatcherProvider = coroutineDispatcherProvider,
                scope = wisefyScope,
                networkConnectionMutex = networkConnectionMutex,
                connectivityManager = connectivityManager,
                logger = logger,
                sdkUtil = sdkUtil,
                wifiManager = wifiManager,
                assertions = assertions,
                networkConnectionStatusProvider = {
                    NetworkConnectionStatus.AVAILABLE
                }
            )
            networkInfoDelegate = WisefyNetworkInfoDelegate(
                coroutineDispatcherProvider = coroutineDispatcherProvider,
                scope = wisefyScope,
                networkConnectionMutex = networkConnectionMutex,
                connectivityManager = connectivityManager,
                logger = logger,
                sdkUtil = sdkUtil,
                wifiManager = wifiManager,
                networkConnectionStatusProvider = {
                    NetworkConnectionStatus.AVAILABLE
                }
            )
            removeNetworkDelegate = WisefyRemoveNetworkDelegate(
                coroutineDispatcherProvider = coroutineDispatcherProvider,
                scope = wisefyScope,
                savedNetworkMutex = savedNetworkMutex,
                logger = logger,
                sdkUtil = sdkUtil,
                wifiManager = wifiManager,
                assertions = assertions
            )
            savedNetworkDelegate = WisefySavedNetworkDelegate(
                coroutineDispatcherProvider = coroutineDispatcherProvider,
                scope = wisefyScope,
                savedNetworkMutex = savedNetworkMutex,
                logger = logger,
                sdkUtil = sdkUtil,
                wifiManager = wifiManager,
                assertions = assertions
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
                wifiMutex = wifiMutex,
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
                networkConnectionDelegate = networkConnectionDelegate,
                networkInfoDelegate = networkInfoDelegate,
                removeNetworkDelegate = removeNetworkDelegate,
                savedNetworkDelegate = savedNetworkDelegate,
                signalDelegate = signalDelegate,
                wifiDelegate = wifiDelegate,
                logger = logger,
                networkConnectionMutex = networkConnectionMutex,
                scope = wisefyScope,
                connectivityManager = connectivityManager
            )
        }
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun init() {
        startListeningForNetworkChanges()
    }

    override fun dump() {
        scope.coroutineContext[Job]?.cancelChildren()
        stopListeningForNetworkChanges()
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addNetwork(request: AddNetworkRequest): AddNetworkResult {
        return addNetworkDelegate.addNetwork(request)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addNetwork(request: AddNetworkRequest, callbacks: AddNetworkCallbacks?) {
        addNetworkDelegate.addNetwork(request, callbacks)
    }

    override fun calculateSignalLevel(request: CalculateSignalLevelRequest): CalculateSignalLevelResult {
        return signalDelegate.calculateSignalLevel(request)
    }

    override fun compareSignalLevel(request: CompareSignalLevelRequest): CompareSignalLevelResult {
        return signalDelegate.compareSignalLevel(request)
    }

    @RequiresPermission(allOf = [CHANGE_NETWORK_STATE, ACCESS_FINE_LOCATION])
    override fun connectToNetwork(request: ConnectToNetworkRequest): ConnectToNetworkResult {
        return networkConnectionDelegate.connectToNetwork(request)
    }

    @RequiresPermission(allOf = [CHANGE_NETWORK_STATE, ACCESS_FINE_LOCATION])
    override fun connectToNetwork(request: ConnectToNetworkRequest, callbacks: ConnectToNetworkCallbacks?) {
        networkConnectionDelegate.connectToNetwork(request, callbacks)
    }

    @RequiresPermission(CHANGE_WIFI_STATE)
    override fun disableWifi(request: DisableWifiRequest): DisableWifiResult {
        return wifiDelegate.disableWifi(request)
    }

    @RequiresPermission(CHANGE_WIFI_STATE)
    override fun disableWifi(request: DisableWifiRequest, callbacks: DisableWifiCallbacks?) {
        wifiDelegate.disableWifi(request, callbacks)
    }

    override fun disconnectFromCurrentNetwork(
        request: DisconnectFromCurrentNetworkRequest
    ): DisconnectFromCurrentNetworkResult {
        return networkConnectionDelegate.disconnectFromCurrentNetwork(request)
    }

    override fun disconnectFromCurrentNetwork(
        request: DisconnectFromCurrentNetworkRequest,
        callbacks: DisconnectFromCurrentNetworkCallbacks?
    ) {
        networkConnectionDelegate.disconnectFromCurrentNetwork(request, callbacks)
    }

    @RequiresPermission(CHANGE_WIFI_STATE)
    override fun enableWifi(request: EnableWifiRequest): EnableWifiResult {
        return wifiDelegate.enableWifi(request)
    }

    @RequiresPermission(CHANGE_WIFI_STATE)
    override fun enableWifi(request: EnableWifiRequest, callbacks: EnableWifiCallbacks?) {
        wifiDelegate.enableWifi(request, callbacks)
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun getCurrentNetwork(query: GetCurrentNetworkQuery): GetCurrentNetworkResult {
        return networkInfoDelegate.getCurrentNetwork(query)
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun getCurrentNetwork(query: GetCurrentNetworkQuery, callbacks: GetCurrentNetworkCallbacks?) {
        networkInfoDelegate.getCurrentNetwork(query, callbacks)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getAccessPoints(query: GetAccessPointsQuery): GetAccessPointsResult {
        return accessPointsDelegate.getAccessPoints(query)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getAccessPoints(
        query: GetAccessPointsQuery,
        callbacks: GetAccessPointsCallbacks?
    ) {
        accessPointsDelegate.getAccessPoints(query, callbacks)
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun getNetworkConnectionStatus(
        query: GetNetworkConnectionStatusQuery
    ): GetNetworkConnectionStatusResult {
        return networkInfoDelegate.getNetworkConnectionStatus(query)
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun getNetworkConnectionStatus(
        query: GetNetworkConnectionStatusQuery,
        callbacks: GetNetworkConnectionStatusCallbacks?
    ) {
        networkInfoDelegate.getNetworkConnectionStatus(query, callbacks)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun getSavedNetworks(query: GetSavedNetworksQuery): GetSavedNetworksResult {
        return savedNetworkDelegate.getSavedNetworks(query)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun getSavedNetworks(query: GetSavedNetworksQuery, callbacks: GetSavedNetworksCallbacks?) {
        savedNetworkDelegate.getSavedNetworks(query, callbacks)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun isNetworkSaved(query: IsNetworkSavedQuery): IsNetworkSavedResult {
        return savedNetworkDelegate.isNetworkSaved(query)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun isNetworkSaved(query: IsNetworkSavedQuery, callbacks: IsNetworkSavedCallbacks?) {
        return savedNetworkDelegate.isNetworkSaved(query, callbacks)
    }

    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun isWifiEnabled(query: IsWifiEnabledQuery): IsWifiEnabledResult {
        return wifiDelegate.isWifiEnabled(query)
    }

    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun isWifiEnabled(query: IsWifiEnabledQuery, callbacks: IsWifiEnabledCallbacks?) {
        wifiDelegate.isWifiEnabled(query, callbacks)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE, CHANGE_WIFI_STATE])
    override fun removeNetwork(request: RemoveNetworkRequest): RemoveNetworkResult {
        return removeNetworkDelegate.removeNetwork(request)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE, CHANGE_WIFI_STATE])
    override fun removeNetwork(request: RemoveNetworkRequest, callbacks: RemoveNetworkCallbacks?) {
        removeNetworkDelegate.removeNetwork(request, callbacks)
    }

    private val wisefyNetworkCallbacks = WisefyNetworkCallbacks(
        logger = logger,
        onNetworkConnectionStatusUpdated = { status ->
            scope.launch {
                networkConnectionMutex.withLock {
                    connectionStatus = status
                }
            }
        }
    )

    @RequiresPermission(ACCESS_NETWORK_STATE)
    private fun startListeningForNetworkChanges() {
        val request = NetworkRequest.Builder().build()
        connectivityManager.registerNetworkCallback(request, wisefyNetworkCallbacks)
    }

    private fun stopListeningForNetworkChanges() {
        connectivityManager.unregisterNetworkCallback(wisefyNetworkCallbacks)
        connectionStatus = null
    }

    private var connectionStatus: NetworkConnectionStatus? = null

    private class WisefyNetworkCallbacks(
        private val logger: WisefyLogger,
        private val onNetworkConnectionStatusUpdated: (NetworkConnectionStatus) -> Unit
    ) : ConnectivityManager.NetworkCallback() {

        companion object {
            private const val LOG_TAG = "WisefyNetworkCallback"
        }

        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            logger.d(LOG_TAG, "onAvailable, $network")
            onNetworkConnectionStatusUpdated(NetworkConnectionStatus.AVAILABLE)
        }

        override fun onCapabilitiesChanged(network: Network, networkCapabilities: NetworkCapabilities) {
            super.onCapabilitiesChanged(network, networkCapabilities)
            logger.d(
                LOG_TAG,
                "onCapabilitiesChanged, network: $network, networkCapabilities: $networkCapabilities"
            )
        }

        override fun onLinkPropertiesChanged(network: Network, linkProperties: LinkProperties) {
            super.onLinkPropertiesChanged(network, linkProperties)
            logger.d(LOG_TAG, "onLinkPropertiesChanged, network: $network, linkProperties: $linkProperties")
        }

        override fun onLosing(network: Network, maxMsToLive: Int) {
            super.onLosing(network, maxMsToLive)
            logger.d(LOG_TAG, "onLosing, network: $network, maxMsToLive: $maxMsToLive")
            onNetworkConnectionStatusUpdated(NetworkConnectionStatus.LOSING)
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            logger.d(LOG_TAG, "onLost, network: $network")
            onNetworkConnectionStatusUpdated(NetworkConnectionStatus.LOST)
        }
    }
}
