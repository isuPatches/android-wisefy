/*
 * Copyright 2021 Patches Klinefelter
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
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Build.VERSION_CODES.LOLLIPOP
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import androidx.annotation.VisibleForTesting
import com.isupatches.android.wisefy.accesspoints.AccessPointsDelegate
import com.isupatches.android.wisefy.accesspoints.WisefyAccessPointsDelegate
import com.isupatches.android.wisefy.accesspoints.callbacks.GetNearbyAccessPointCallbacks
import com.isupatches.android.wisefy.accesspoints.callbacks.GetRSSICallbacks
import com.isupatches.android.wisefy.accesspoints.callbacks.SearchForAccessPointCallbacks
import com.isupatches.android.wisefy.accesspoints.callbacks.SearchForAccessPointsCallbacks
import com.isupatches.android.wisefy.accesspoints.callbacks.SearchForSSIDCallbacks
import com.isupatches.android.wisefy.accesspoints.callbacks.SearchForSSIDsCallbacks
import com.isupatches.android.wisefy.accesspoints.entities.AccessPointData
import com.isupatches.android.wisefy.accesspoints.entities.GetNearbyAccessPointsRequest
import com.isupatches.android.wisefy.accesspoints.entities.GetRSSIRequest
import com.isupatches.android.wisefy.accesspoints.entities.RSSIData
import com.isupatches.android.wisefy.accesspoints.entities.SSIDData
import com.isupatches.android.wisefy.accesspoints.entities.SearchForMultipleAccessPointsRequest
import com.isupatches.android.wisefy.accesspoints.entities.SearchForMultipleSSIDsRequest
import com.isupatches.android.wisefy.accesspoints.entities.SearchForSingleAccessPointRequest
import com.isupatches.android.wisefy.accesspoints.entities.SearchForSingleSSIDRequest
import com.isupatches.android.wisefy.addnetwork.callbacks.AddNetworkCallbacks
import com.isupatches.android.wisefy.addnetwork.AddNetworkDelegate
import com.isupatches.android.wisefy.addnetwork.WisefyAddNetworkDelegate
import com.isupatches.android.wisefy.addnetwork.entities.AddNetworkResult
import com.isupatches.android.wisefy.addnetwork.entities.AddOpenNetworkRequest
import com.isupatches.android.wisefy.addnetwork.entities.AddWPA2NetworkRequest
import com.isupatches.android.wisefy.addnetwork.entities.AddWPA3NetworkRequest
import com.isupatches.android.wisefy.callbacks.ConnectToNetworkCallbacks
import com.isupatches.android.wisefy.callbacks.DisconnectFromCurrentNetworkCallbacks
import com.isupatches.android.wisefy.callbacks.GetCurrentNetworkCallbacks
import com.isupatches.android.wisefy.callbacks.GetCurrentNetworkInfoCallbacks
import com.isupatches.android.wisefy.callbacks.GetSavedNetworksCallbacks
import com.isupatches.android.wisefy.callbacks.SearchForSavedNetworkCallbacks
import com.isupatches.android.wisefy.callbacks.SearchForSavedNetworksCallbacks
import com.isupatches.android.wisefy.constants.DeprecationMessages
import com.isupatches.android.wisefy.frequency.FrequencyDelegate
import com.isupatches.android.wisefy.frequency.GetFrequencyCallbacks
import com.isupatches.android.wisefy.frequency.WisefyFrequencyDelegate
import com.isupatches.android.wisefy.frequency.entities.FrequencyData
import com.isupatches.android.wisefy.logging.WisefyLogger
import com.isupatches.android.wisefy.networkconnection.NetworkConnectionDelegate
import com.isupatches.android.wisefy.networkconnection.WisefyNetworkConnectionDelegate
import com.isupatches.android.wisefy.networkconnection.entities.NetworkConnectionRequest
import com.isupatches.android.wisefy.networkconnection.entities.NetworkConnectionResult
import com.isupatches.android.wisefy.networkconnectionstatus.NetworkConnectionStatusDelegate
import com.isupatches.android.wisefy.networkconnectionstatus.WisefyNetworkConnectionStatusUtil
import com.isupatches.android.wisefy.networkconnectionstatus.entities.IsNetworkConnectedToSSIDRequest
import com.isupatches.android.wisefy.networkinfo.GetIPCallbacks
import com.isupatches.android.wisefy.networkinfo.NetworkInfoDelegate
import com.isupatches.android.wisefy.networkinfo.WisefyNetworkInfoUtil
import com.isupatches.android.wisefy.networkinfo.entities.CurrentNetworkData
import com.isupatches.android.wisefy.networkinfo.entities.CurrentNetworkInfoData
import com.isupatches.android.wisefy.networkinfo.entities.GetCurrentNetworkInfoRequest
import com.isupatches.android.wisefy.networkinfo.entities.IPData
import com.isupatches.android.wisefy.removenetwork.RemoveNetworkCallbacks
import com.isupatches.android.wisefy.removenetwork.RemoveNetworkDelegate
import com.isupatches.android.wisefy.removenetwork.WisefyRemoveNetworkUtil
import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkRequest
import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkResult
import com.isupatches.android.wisefy.savednetworks.SavedNetworkDelegate
import com.isupatches.android.wisefy.savednetworks.WisefySavedNetworkUtil
import com.isupatches.android.wisefy.savednetworks.entities.SavedNetworkData
import com.isupatches.android.wisefy.savednetworks.entities.SearchForSavedNetworkRequest
import com.isupatches.android.wisefy.security.SecurityDelegate
import com.isupatches.android.wisefy.security.WisefySecurityUtil
import com.isupatches.android.wisefy.signal.SignalDelegate
import com.isupatches.android.wisefy.signal.WisefySignalUtil
import com.isupatches.android.wisefy.util.SdkUtilImpl
import com.isupatches.android.wisefy.util.coroutines.CoroutineDispatcherProvider
import com.isupatches.android.wisefy.wifi.WifiDelegate
import com.isupatches.android.wisefy.wifi.WisefyWifiDelegate
import com.isupatches.android.wisefy.wifi.callbacks.DisableWifiCallbacks
import com.isupatches.android.wisefy.wifi.callbacks.EnableWifiCallbacks
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

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

    class Brains @JvmOverloads constructor(
        context: Context,
        logger: WisefyLogger? = null
    ) {

        private var logger: WisefyLogger? = null
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

            // Used by other utils
            savedNetworkDelegate = WisefySavedNetworkUtil(
                coroutineDispatcherProvider = coroutineDispatcherProvider,
                logger = logger,
                sdkUtil = sdkUtil,
                wifiManager = wifiManager
            )
            networkConnectionStatusDelegate = WisefyNetworkConnectionStatusUtil(
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
                wifiManager = wifiManager
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
                connectivityManager = connectivityManager,
                logger = logger,
                networkConnectionStatusUtil = networkConnectionStatusDelegate,
                savedNetworkUtil = savedNetworkDelegate,
                sdkUtil = sdkUtil,
                wifiManager = wifiManager
            )
            networkInfoDelegate = WisefyNetworkInfoUtil(
                coroutineDispatcherProvider = coroutineDispatcherProvider,
                connectivityManager = connectivityManager,
                logger = logger,
                wifiManager = wifiManager
            )
            removeNetworkDelegate = WisefyRemoveNetworkUtil(
                coroutineDispatcherProvider = coroutineDispatcherProvider,
                logger = logger,
                savedNetworkDelegate = savedNetworkDelegate,
                sdkUtil = sdkUtil,
                wifiManager = wifiManager
            )
            securityDelegate = WisefySecurityUtil(
                logger = logger
            )
            signalDelegate = WisefySignalUtil(
                logger = logger,
                sdkUtil = sdkUtil,
                wifiManager = wifiManager
            )
            wifiDelegate = WisefyWifiDelegate(
                coroutineDispatcherProvider = coroutineDispatcherProvider,
                scope = wisefyScope,
                logger = logger,
                sdkUtil = sdkUtil,
                wifiManager = wifiManager
            )
        }

        internal fun logger(logger: WisefyLogger): Brains = apply {
            this.logger = logger
        }

        @VisibleForTesting
        internal fun customConnectivityManager(connectivityManager: ConnectivityManager): Brains = apply {
            this.connectivityManager = connectivityManager
        }

        @VisibleForTesting
        internal fun customWifiManager(wifiManager: WifiManager): Brains = apply {
            this.wifiManager = wifiManager
        }

        @VisibleForTesting
        internal fun customAccessPointsUtil(accessPointsDelegate: AccessPointsDelegate): Brains = apply {
            this.accessPointsDelegate = accessPointsDelegate
        }

        @VisibleForTesting
        internal fun customAddNetworkUtil(addNetworkDelegate: AddNetworkDelegate): Brains = apply {
            this.addNetworkDelegate = addNetworkDelegate
        }

        @VisibleForTesting
        internal fun customFrequencyUtil(frequencyDelegate: FrequencyDelegate): Brains = apply {
            this.frequencyDelegate = frequencyDelegate
        }

        @VisibleForTesting
        internal fun customNetworkConnectionUtil(networkConnectionDelegate: NetworkConnectionDelegate): Brains = apply {
            this.networkConnectionDelegate = networkConnectionDelegate
        }

        @VisibleForTesting
        internal fun customNetworkConnectionStatusUtil(
            networkConnectionStatusDelegate: NetworkConnectionStatusDelegate
        ): Brains = apply {
            this.networkConnectionStatusDelegate = networkConnectionStatusDelegate
        }

        @VisibleForTesting
        internal fun customNetworkInfoUtil(networkInfoDelegate: NetworkInfoDelegate): Brains = apply {
            this.networkInfoDelegate = networkInfoDelegate
        }

        @VisibleForTesting
        internal fun customRemoveNetworkUtil(removeNetworkDelegate: RemoveNetworkDelegate): Brains = apply {
            this.removeNetworkDelegate = removeNetworkDelegate
        }

        @VisibleForTesting
        internal fun customSavedNetworkUtil(savedNetworkDelegate: SavedNetworkDelegate): Brains = apply {
            this.savedNetworkDelegate = savedNetworkDelegate
        }

        @VisibleForTesting
        internal fun customSecurityDelegate(securityDelegate: SecurityDelegate): Brains = apply {
            this.securityDelegate = securityDelegate
        }

        @VisibleForTesting
        internal fun customSignalDelegate(signalDelegate: SignalDelegate): Brains = apply {
            this.signalDelegate = signalDelegate
        }

        @VisibleForTesting
        internal fun customWifiDelegate(wifiDelegate: WifiDelegate): Brains = apply {
            this.wifiDelegate = wifiDelegate
        }

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

    override fun attachNetworkWatcher() {
        networkConnectionStatusDelegate.attachNetworkWatcher()
    }

    override fun detachNetworkWatcher() {
        networkConnectionStatusDelegate.detachNetworkWatcher()
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addOpenNetwork(request: AddOpenNetworkRequest): AddNetworkResult {
        return addNetworkDelegate.addOpenNetwork(request)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addOpenNetwork(request: AddOpenNetworkRequest, callbacks: AddNetworkCallbacks?) {
        addNetworkDelegate.addOpenNetwork(request, callbacks)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addWPA2Network(request: AddWPA2NetworkRequest): AddNetworkResult {
        return addNetworkDelegate.addWPA2Network(request)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addWPA2Network(request: AddWPA2NetworkRequest, callbacks: AddNetworkCallbacks?) {
        addNetworkDelegate.addWPA2Network(request, callbacks)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addWPA3Network(request: AddWPA3NetworkRequest): AddNetworkResult {
        return addNetworkDelegate.addWPA3Network(request)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addWPA3Network(request: AddWPA3NetworkRequest, callbacks: AddNetworkCallbacks?) {
        addNetworkDelegate.addWPA3Network(request, callbacks)
    }

    override fun calculateBars(rssiLevel: Int, targetNumberOfBars: Int): Int {
        return signalDelegate.calculateBars(rssiLevel, targetNumberOfBars)
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun calculateBars(rssiLevel: Int): Int {
        return signalDelegate.calculateBars(rssiLevel)
    }

    override fun compareSignalLevel(rssi1: Int, rssi2: Int): Int {
        return signalDelegate.compareSignalLevel(rssi1, rssi2)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun connectToNetwork(request: NetworkConnectionRequest): NetworkConnectionResult {
        return networkConnectionDelegate.connectToNetwork(request)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun connectToNetwork(request: NetworkConnectionRequest, callbacks: ConnectToNetworkCallbacks?) {
        networkConnectionDelegate.connectToNetwork(request, callbacks)
    }

    @Deprecated(DeprecationMessages.DISABLE_WIFI)
    override fun disableWifi(): Boolean {
        return wifiDelegate.disableWifi()
    }

    @Deprecated(DeprecationMessages.DISABLE_WIFI)
    override fun disableWifi(callbacks: DisableWifiCallbacks?) {
        wifiDelegate.disableWifi(callbacks)
    }

    override fun disconnectFromCurrentNetwork(): NetworkConnectionResult {
        return networkConnectionDelegate.disconnectFromCurrentNetwork()
    }

    override fun disconnectFromCurrentNetwork(callbacks: DisconnectFromCurrentNetworkCallbacks?) {
        networkConnectionDelegate.disconnectFromCurrentNetwork(callbacks)
    }

    @Deprecated(DeprecationMessages.ENABLE_WIFI)
    override fun enableWifi(): Boolean {
        return wifiDelegate.enableWifi()
    }

    @Deprecated(DeprecationMessages.ENABLE_WIFI)
    override fun enableWifi(callbacks: EnableWifiCallbacks?) {
        wifiDelegate.enableWifi(callbacks)
    }

    override fun getCurrentNetwork(): CurrentNetworkData? {
        return networkInfoDelegate.getCurrentNetwork()
    }

    override fun getCurrentNetwork(callbacks: GetCurrentNetworkCallbacks?) {
        networkInfoDelegate.getCurrentNetwork(callbacks)
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun getCurrentNetworkInfo(request: GetCurrentNetworkInfoRequest): CurrentNetworkInfoData? {
        return networkInfoDelegate.getCurrentNetworkInfo(request)
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun getCurrentNetworkInfo(
        callbacks: GetCurrentNetworkInfoCallbacks?,
        request: GetCurrentNetworkInfoRequest
    ) {
        networkInfoDelegate.getCurrentNetworkInfo(callbacks, request)
    }

    @RequiresApi(LOLLIPOP)
    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getFrequency(): FrequencyData? {
        return frequencyDelegate.getFrequency()
    }

    @RequiresApi(LOLLIPOP)
    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getFrequency(callbacks: GetFrequencyCallbacks?) {
        frequencyDelegate.getFrequency(callbacks)
    }

    @RequiresApi(LOLLIPOP)
    override fun getFrequency(network: WifiInfo): FrequencyData {
        return frequencyDelegate.getFrequency(network)
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun getIP(): IPData? {
        return networkInfoDelegate.getIP()
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun getIP(callbacks: GetIPCallbacks?) {
        networkInfoDelegate.getIP(callbacks)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getNearbyAccessPoints(request: GetNearbyAccessPointsRequest): List<AccessPointData> {
        return accessPointsDelegate.getNearbyAccessPoints(request)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getNearbyAccessPoints(
        request: GetNearbyAccessPointsRequest,
        callbacks: GetNearbyAccessPointCallbacks?
    ) {
        accessPointsDelegate.getNearbyAccessPoints(request, callbacks)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getRSSI(request: GetRSSIRequest): RSSIData? {
        return accessPointsDelegate.getRSSI(request)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getRSSI(request: GetRSSIRequest, callbacks: GetRSSICallbacks?) {
        accessPointsDelegate.getRSSI(request, callbacks)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun getSavedNetworks(): List<SavedNetworkData> {
        return savedNetworkDelegate.getSavedNetworks()
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun getSavedNetworks(callbacks: GetSavedNetworksCallbacks?) {
        savedNetworkDelegate.getSavedNetworks(callbacks)
    }

    override fun isDeviceConnectedToMobileNetwork(): Boolean {
        return networkConnectionStatusDelegate.isDeviceConnectedToMobileNetwork()
    }

    override fun isDeviceConnectedToMobileOrWifiNetwork(): Boolean {
        return networkConnectionStatusDelegate.isDeviceConnectedToMobileOrWifiNetwork()
    }

    override fun isDeviceConnectedToSSID(request: IsNetworkConnectedToSSIDRequest): Boolean {
        return networkConnectionStatusDelegate.isDeviceConnectedToSSID(request)
    }

    override fun isDeviceConnectedToWifiNetwork(): Boolean {
        return networkConnectionStatusDelegate.isDeviceConnectedToWifiNetwork()
    }

    override fun isDeviceRoaming(): Boolean {
        return networkConnectionStatusDelegate.isDeviceRoaming()
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun isNetwork5gHz(): Boolean {
        return frequencyDelegate.isNetwork5gHz()
    }

    override fun isNetwork5gHz(network: WifiInfo): Boolean {
        return frequencyDelegate.isNetwork5gHz(network)
    }

    override fun isNetworkEAP(network: AccessPointData): Boolean {
        return securityDelegate.isNetworkEAP(network)
    }

    override fun isNetworkPSK(network: AccessPointData): Boolean {
        return securityDelegate.isNetworkPSK(network)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun isNetworkSaved(request: SearchForSavedNetworkRequest): Boolean {
        return savedNetworkDelegate.isNetworkSaved(request)
    }

    override fun isNetworkSecure(network: AccessPointData): Boolean {
        return securityDelegate.isNetworkSecure(network)
    }

    override fun isNetworkWEP(network: AccessPointData): Boolean {
        return securityDelegate.isNetworkWEP(network)
    }

    override fun isNetworkWPA(network: AccessPointData): Boolean {
        return securityDelegate.isNetworkWPA(network)
    }

    override fun isNetworkWPA2(network: AccessPointData): Boolean {
        return securityDelegate.isNetworkWPA2(network)
    }

    override fun isNetworkWPA3(network: AccessPointData): Boolean {
        return securityDelegate.isNetworkWPA3(network)
    }

    override fun isWifiEnabled(): Boolean {
        return wifiDelegate.isWifiEnabled()
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
    override fun searchForAccessPoint(request: SearchForSingleAccessPointRequest): AccessPointData? {
        return accessPointsDelegate.searchForAccessPoint(request)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForAccessPoint(
        request: SearchForSingleAccessPointRequest,
        callbacks: SearchForAccessPointCallbacks?
    ) {
        accessPointsDelegate.searchForAccessPoint(request, callbacks)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForAccessPoints(request: SearchForMultipleAccessPointsRequest): List<AccessPointData> {
        return accessPointsDelegate.searchForAccessPoints(request)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForAccessPoints(
        request: SearchForMultipleAccessPointsRequest,
        callbacks: SearchForAccessPointsCallbacks?
    ) {
        accessPointsDelegate.searchForAccessPoints(request, callbacks)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForSavedNetwork(request: SearchForSavedNetworkRequest): SavedNetworkData? {
        return savedNetworkDelegate.searchForSavedNetwork(request)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForSavedNetwork(
        request: SearchForSavedNetworkRequest,
        callbacks: SearchForSavedNetworkCallbacks?
    ) {
        savedNetworkDelegate.searchForSavedNetwork(request, callbacks)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForSavedNetworks(request: SearchForSavedNetworkRequest): List<SavedNetworkData> {
        return savedNetworkDelegate.searchForSavedNetworks(request)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForSavedNetworks(
        request: SearchForSavedNetworkRequest,
        callbacks: SearchForSavedNetworksCallbacks?
    ) {
        savedNetworkDelegate.searchForSavedNetworks(request, callbacks)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForSSID(request: SearchForSingleSSIDRequest): SSIDData? {
        return accessPointsDelegate.searchForSSID(request)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForSSID(request: SearchForSingleSSIDRequest, callbacks: SearchForSSIDCallbacks?) {
        accessPointsDelegate.searchForSSID(request, callbacks)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForSSIDs(request: SearchForMultipleSSIDsRequest): List<SSIDData> {
        return accessPointsDelegate.searchForSSIDs(request)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForSSIDs(request: SearchForMultipleSSIDsRequest, callbacks: SearchForSSIDsCallbacks?) {
        accessPointsDelegate.searchForSSIDs(request, callbacks)
    }
}
