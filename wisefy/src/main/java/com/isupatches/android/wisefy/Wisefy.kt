/*
 * Copyright 2022 Patches Barrett
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
import android.Manifest.permission.CHANGE_WIFI_STATE
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkRequest
import android.net.wifi.WifiManager
import android.os.Build
import androidx.annotation.RequiresApi
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
import com.isupatches.android.wisefy.core.constants.DeprecationMessages
import com.isupatches.android.wisefy.core.coroutines.DefaultCoroutineDispatcherProvider
import com.isupatches.android.wisefy.core.logging.DefaultWisefyLogger
import com.isupatches.android.wisefy.core.logging.WisefyLogger
import com.isupatches.android.wisefy.core.util.SdkUtilImpl
import com.isupatches.android.wisefy.networkconnection.NetworkConnectionDelegate
import com.isupatches.android.wisefy.networkconnection.WisefyNetworkConnectionDelegate
import com.isupatches.android.wisefy.networkconnection.callbacks.ChangeNetworkCallbacks
import com.isupatches.android.wisefy.networkconnection.callbacks.ConnectToNetworkCallbacks
import com.isupatches.android.wisefy.networkconnection.callbacks.DisconnectFromCurrentNetworkCallbacks
import com.isupatches.android.wisefy.networkconnection.entities.ChangeNetworkRequest
import com.isupatches.android.wisefy.networkconnection.entities.ChangeNetworkResult
import com.isupatches.android.wisefy.networkconnection.entities.ConnectToNetworkRequest
import com.isupatches.android.wisefy.networkconnection.entities.ConnectToNetworkResult
import com.isupatches.android.wisefy.networkconnection.entities.DisconnectFromCurrentNetworkRequest
import com.isupatches.android.wisefy.networkconnection.entities.DisconnectFromCurrentNetworkResult
import com.isupatches.android.wisefy.networkconnectionstatus.WisefyNetworkCallbacks
import com.isupatches.android.wisefy.networkconnectionstatus.WisefyNetworkConnectionStatusManager
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

/**
 * The private constructor used by [Brains] to create a Wisefy instance.
 *
 * @property accessPointsDelegate The [AccessPointsDelegate] instance to use
 * @property addNetworkDelegate The [AddNetworkDelegate] instance to use
 * @property networkConnectionDelegate The [NetworkConnectionDelegate] instance to use
 * @property networkInfoDelegate The [NetworkInfoDelegate] instance to use
 * @property removeNetworkDelegate The [RemoveNetworkDelegate] instance to use
 * @property savedNetworkDelegate The [SavedNetworkDelegate] instance to use
 * @property signalDelegate The [SignalDelegate] instance to use
 * @property wifiDelegate The [WifiDelegate] instance to use
 * @property scope The [CoroutineScope] to use for async operations
 * @property connectivityManager The ConnectivityManager instance to use
 * @property networkConnectionMutex The mutex for network connection operations
 * @param logger The [WisefyLogger] instance to use
 *
 * @see AccessPointsDelegate
 * @see AddNetworkDelegate
 * @see NetworkConnectionDelegate
 * @see NetworkInfoDelegate
 * @see RemoveNetworkDelegate
 * @see SignalDelegate
 * @see WifiDelegate
 * @see WisefyApi
 * @see WisefyLogger
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
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
    private val scope: CoroutineScope,
    private val connectivityManager: ConnectivityManager,
    private val networkConnectionMutex: Mutex,
    logger: WisefyLogger
) : WisefyApi {

    /**
     * The public builder to create a Wisefy instance.
     *
     * @param context The application context. Used for creating a [ConnectivityManager] and [wifiManager] instance
     * to use within Wisefy
     * @param throwOnAssertions Whether assertions will throw an [IllegalStateException] when hit or be no-op
     * @property logger The [WisefyLogger] instance to use within Wisefy
     *
     * @see DefaultWisefyLogger
     * @see WisefyLogger
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
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

        private val networkConnectionMutex: Mutex

        init {
            connectivityManager = context.applicationContext.getSystemService(
                Context.CONNECTIVITY_SERVICE
            ) as ConnectivityManager
            wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

            val sdkUtil = SdkUtilImpl()
            val coroutineDispatcherProvider = DefaultCoroutineDispatcherProvider()
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
                logger,
                wifiManager,
                coroutineDispatcherProvider,
                wisefyScope
            )
            addNetworkDelegate = WisefyAddNetworkDelegate(
                assertions,
                logger,
                sdkUtil,
                wifiManager,
                coroutineDispatcherProvider,
                wisefyScope,
                savedNetworkMutex
            )
            networkConnectionDelegate = WisefyNetworkConnectionDelegate(
                assertions,
                connectivityManager,
                logger,
                sdkUtil,
                wifiManager,
                {
                    WisefyNetworkConnectionStatusManager.getInstance(networkConnectionMutex)
                        .getNetworkConnectionStatus()
                },
                coroutineDispatcherProvider,
                wisefyScope,
                networkConnectionMutex
            )
            networkInfoDelegate = WisefyNetworkInfoDelegate(
                connectivityManager,
                logger,
                sdkUtil,
                wifiManager,
                {
                    WisefyNetworkConnectionStatusManager.getInstance(networkConnectionMutex)
                        .getNetworkConnectionStatus()
                },
                coroutineDispatcherProvider,
                wisefyScope,
                networkConnectionMutex
            )
            removeNetworkDelegate = WisefyRemoveNetworkDelegate(
                assertions,
                logger,
                sdkUtil,
                wifiManager,
                coroutineDispatcherProvider,
                wisefyScope,
                savedNetworkMutex
            )
            savedNetworkDelegate = WisefySavedNetworkDelegate(
                assertions,
                logger,
                sdkUtil,
                wifiManager,
                coroutineDispatcherProvider,
                wisefyScope,
                savedNetworkMutex
            )
            signalDelegate = WisefySignalDelegate(
                assertions,
                logger,
                sdkUtil,
                wifiManager
            )
            wifiDelegate = WisefyWifiDelegate(
                assertions,
                logger,
                sdkUtil,
                wifiManager,
                coroutineDispatcherProvider,
                wisefyScope,
                wifiMutex
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
         * @author Patches Barrett
         * @since 12/2022, version 5.0.0
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
         * @author Patches Barrett
         * @since 12/2022, version 5.0.0
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
         * @author Patches Barrett
         * @since 12/2022, version 5.0.0
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
         * @author Patches Barrett
         * @since 12/2022, version 5.0.0
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
         * @author Patches Barrett
         * @since 12/2022, version 5.0.0
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
         * @author Patches Barrett
         * @since 12/2022, version 5.0.0
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
         * @author Patches Barrett
         * @since 12/2022, version 5.0.0
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
         * @author Patches Barrett
         * @since 12/2022, version 5.0.0
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
         * @author Patches Barrett
         * @since 12/2022, version 5.0.0
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
         * @author Patches Barrett
         * @since 12/2022, version 5.0.0
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
         * @author Patches Barrett
         * @since 12/2022, version 5.0.0
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
         * @author Patches Barrett
         * @since 12/2022, version 5.0.0
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
                scope = wisefyScope,
                connectivityManager = connectivityManager,
                networkConnectionMutex = networkConnectionMutex,
                logger = logger
            )
        }
    }

    private val wisefyNetworkCallbacks = WisefyNetworkCallbacks(
        logger = logger,
        onNetworkConnectionStatusUpdated = { status ->
            scope.launch {
                WisefyNetworkConnectionStatusManager.getInstance(networkConnectionMutex)
                    .setNetworkConnectionStatus(status)
            }
        }
    )

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun init() {
        val request = NetworkRequest.Builder().build()
        connectivityManager.registerNetworkCallback(request, wisefyNetworkCallbacks)
    }

    override fun dump() {
        scope.coroutineContext[Job]?.cancelChildren()
        connectivityManager.unregisterNetworkCallback(wisefyNetworkCallbacks)
        scope.launch {
            WisefyNetworkConnectionStatusManager.getInstance(networkConnectionMutex).clear()
        }
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addNetwork(request: AddNetworkRequest): AddNetworkResult {
        return addNetworkDelegate.addNetwork(request)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addNetwork(request: AddNetworkRequest, callbacks: AddNetworkCallbacks?) {
        addNetworkDelegate.addNetwork(request, callbacks)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun changeNetwork(request: ChangeNetworkRequest): ChangeNetworkResult {
        return networkConnectionDelegate.changeNetwork(request)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun changeNetwork(request: ChangeNetworkRequest, callbacks: ChangeNetworkCallbacks?) {
        networkConnectionDelegate.changeNetwork(request, callbacks)
    }

    override fun calculateSignalLevel(request: CalculateSignalLevelRequest): CalculateSignalLevelResult {
        return signalDelegate.calculateSignalLevel(request)
    }

    override fun compareSignalLevel(request: CompareSignalLevelRequest): CompareSignalLevelResult {
        return signalDelegate.compareSignalLevel(request)
    }

    @Deprecated(DeprecationMessages.NetworkConnection.CONNECT_TO_NETWORK)
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE, ACCESS_NETWORK_STATE])
    override fun connectToNetwork(request: ConnectToNetworkRequest): ConnectToNetworkResult {
        @Suppress("Deprecation")
        return networkConnectionDelegate.connectToNetwork(request)
    }

    @Deprecated(DeprecationMessages.NetworkConnection.CONNECT_TO_NETWORK)
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE, ACCESS_NETWORK_STATE])
    override fun connectToNetwork(request: ConnectToNetworkRequest, callbacks: ConnectToNetworkCallbacks?) {
        @Suppress("Deprecation")
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

    @Deprecated(DeprecationMessages.NetworkConnection.DISCONNECT_FROM_CURRENT_NETWORK)
    override fun disconnectFromCurrentNetwork(
        request: DisconnectFromCurrentNetworkRequest
    ): DisconnectFromCurrentNetworkResult {
        @Suppress("Deprecation")
        return networkConnectionDelegate.disconnectFromCurrentNetwork(request)
    }

    @Deprecated(DeprecationMessages.NetworkConnection.DISCONNECT_FROM_CURRENT_NETWORK)
    override fun disconnectFromCurrentNetwork(
        request: DisconnectFromCurrentNetworkRequest,
        callbacks: DisconnectFromCurrentNetworkCallbacks?
    ) {
        @Suppress("Deprecation")
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
    override fun getAccessPoints(query: GetAccessPointsQuery, callbacks: GetAccessPointsCallbacks?) {
        accessPointsDelegate.getAccessPoints(query, callbacks)
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun getNetworkConnectionStatus(query: GetNetworkConnectionStatusQuery): GetNetworkConnectionStatusResult {
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
}
