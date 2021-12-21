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
import com.isupatches.android.wisefy.accesspoints.AccessPointsUtil
import com.isupatches.android.wisefy.accesspoints.WisefyAccessPointsUtil
import com.isupatches.android.wisefy.accesspoints.entities.AccessPointData
import com.isupatches.android.wisefy.accesspoints.entities.GetNearbyAccessPointsRequest
import com.isupatches.android.wisefy.accesspoints.entities.GetRSSIRequest
import com.isupatches.android.wisefy.accesspoints.entities.RSSIData
import com.isupatches.android.wisefy.accesspoints.entities.SSIDData
import com.isupatches.android.wisefy.accesspoints.entities.SearchForMultipleAccessPointsRequest
import com.isupatches.android.wisefy.accesspoints.entities.SearchForMultipleSSIDsRequest
import com.isupatches.android.wisefy.accesspoints.entities.SearchForSingleAccessPointRequest
import com.isupatches.android.wisefy.accesspoints.entities.SearchForSingleSSIDRequest
import com.isupatches.android.wisefy.addnetwork.AddNetworkUtil
import com.isupatches.android.wisefy.addnetwork.WisefyAddNetworkUtil
import com.isupatches.android.wisefy.addnetwork.entities.AddNetworkResult
import com.isupatches.android.wisefy.addnetwork.entities.AddOpenNetworkRequest
import com.isupatches.android.wisefy.addnetwork.entities.AddWPA2NetworkRequest
import com.isupatches.android.wisefy.addnetwork.entities.AddWPA3NetworkRequest
import com.isupatches.android.wisefy.callbacks.AddNetworkCallbacks
import com.isupatches.android.wisefy.callbacks.ConnectToNetworkCallbacks
import com.isupatches.android.wisefy.callbacks.DisableWifiCallbacks
import com.isupatches.android.wisefy.callbacks.DisconnectFromCurrentNetworkCallbacks
import com.isupatches.android.wisefy.callbacks.EnableWifiCallbacks
import com.isupatches.android.wisefy.callbacks.GetCurrentNetworkCallbacks
import com.isupatches.android.wisefy.callbacks.GetCurrentNetworkInfoCallbacks
import com.isupatches.android.wisefy.callbacks.GetFrequencyCallbacks
import com.isupatches.android.wisefy.callbacks.GetIPCallbacks
import com.isupatches.android.wisefy.callbacks.GetNearbyAccessPointCallbacks
import com.isupatches.android.wisefy.callbacks.GetRSSICallbacks
import com.isupatches.android.wisefy.callbacks.GetSavedNetworksCallbacks
import com.isupatches.android.wisefy.callbacks.RemoveNetworkCallbacks
import com.isupatches.android.wisefy.callbacks.SearchForAccessPointCallbacks
import com.isupatches.android.wisefy.callbacks.SearchForAccessPointsCallbacks
import com.isupatches.android.wisefy.callbacks.SearchForSSIDCallbacks
import com.isupatches.android.wisefy.callbacks.SearchForSSIDsCallbacks
import com.isupatches.android.wisefy.callbacks.SearchForSavedNetworkCallbacks
import com.isupatches.android.wisefy.callbacks.SearchForSavedNetworksCallbacks
import com.isupatches.android.wisefy.constants.DeprecationMessages
import com.isupatches.android.wisefy.frequency.FrequencyUtil
import com.isupatches.android.wisefy.frequency.WisefyFrequencyUtil
import com.isupatches.android.wisefy.frequency.entities.FrequencyData
import com.isupatches.android.wisefy.logging.WisefyLogger
import com.isupatches.android.wisefy.networkconnection.NetworkConnectionUtil
import com.isupatches.android.wisefy.networkconnection.WisefyNetworkConnectionUtil
import com.isupatches.android.wisefy.networkconnection.entities.NetworkConnectionRequest
import com.isupatches.android.wisefy.networkconnection.entities.NetworkConnectionResult
import com.isupatches.android.wisefy.networkconnectionstatus.NetworkConnectionStatusUtil
import com.isupatches.android.wisefy.networkconnectionstatus.WisefyNetworkConnectionStatusUtil
import com.isupatches.android.wisefy.networkconnectionstatus.entities.IsNetworkConnectedToSSIDRequest
import com.isupatches.android.wisefy.networkinfo.NetworkInfoUtil
import com.isupatches.android.wisefy.networkinfo.WisefyNetworkInfoUtil
import com.isupatches.android.wisefy.networkinfo.entities.CurrentNetworkData
import com.isupatches.android.wisefy.networkinfo.entities.CurrentNetworkInfoData
import com.isupatches.android.wisefy.networkinfo.entities.GetCurrentNetworkInfoRequest
import com.isupatches.android.wisefy.networkinfo.entities.IPData
import com.isupatches.android.wisefy.removenetwork.RemoveNetworkUtil
import com.isupatches.android.wisefy.removenetwork.WisefyRemoveNetworkUtil
import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkRequest
import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkResult
import com.isupatches.android.wisefy.savednetworks.SavedNetworkUtil
import com.isupatches.android.wisefy.savednetworks.WisefySavedNetworkUtil
import com.isupatches.android.wisefy.savednetworks.entities.SavedNetworkData
import com.isupatches.android.wisefy.savednetworks.entities.SearchForSavedNetworkRequest
import com.isupatches.android.wisefy.security.SecurityUtil
import com.isupatches.android.wisefy.security.WisefySecurityUtil
import com.isupatches.android.wisefy.signal.SignalUtil
import com.isupatches.android.wisefy.signal.WisefySignalUtil
import com.isupatches.android.wisefy.util.SdkUtilImpl
import com.isupatches.android.wisefy.util.coroutines.CoroutineDispatcherProvider
import com.isupatches.android.wisefy.wifi.WifiUtil
import com.isupatches.android.wisefy.wifi.WisefyWifiUtil

@Suppress("SyntheticAccessor")
class Wisefy private constructor(
    private val accessPointsUtil: AccessPointsUtil,
    private val addNetworkUtil: AddNetworkUtil,
    private val frequencyUtil: FrequencyUtil,
    private val networkConnectionUtil: NetworkConnectionUtil,
    private val networkConnectionStatusUtil: NetworkConnectionStatusUtil,
    private val networkInfoUtil: NetworkInfoUtil,
    private val removeNetworkUtil: RemoveNetworkUtil,
    private val savedNetworkUtil: SavedNetworkUtil,
    private val securityUtil: SecurityUtil,
    private val signalUtil: SignalUtil,
    private val wifiUtil: WifiUtil
) : WisefyApi {

    class Brains @JvmOverloads constructor(
        context: Context,
        logger: WisefyLogger? = null
    ) {

        private var logger: WisefyLogger? = null
        private var connectivityManager: ConnectivityManager
        private var wifiManager: WifiManager

        private var accessPointsUtil: AccessPointsUtil
        private var addNetworkUtil: AddNetworkUtil
        private var frequencyUtil: FrequencyUtil
        private var networkConnectionUtil: NetworkConnectionUtil
        private var networkConnectionStatusUtil: NetworkConnectionStatusUtil
        private var networkInfoUtil: NetworkInfoUtil
        private var removeNetworkUtil: RemoveNetworkUtil
        private var savedNetworkUtil: SavedNetworkUtil
        private var securityUtil: SecurityUtil
        private var signalUtil: SignalUtil
        private var wifiUtil: WifiUtil

        init {
            connectivityManager = context.applicationContext.getSystemService(
                Context.CONNECTIVITY_SERVICE
            ) as ConnectivityManager
            wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

            val sdkUtil = SdkUtilImpl()
            val coroutineDispatcherProvider = CoroutineDispatcherProvider()

            // Used by other utils
            savedNetworkUtil = WisefySavedNetworkUtil(
                coroutineDispatcherProvider = coroutineDispatcherProvider,
                logger = logger,
                sdkUtil = sdkUtil,
                wifiManager = wifiManager
            )
            networkConnectionStatusUtil = WisefyNetworkConnectionStatusUtil(
                connectivityManager = connectivityManager,
                logger = logger,
                sdkUtil = sdkUtil,
                wifiManager = wifiManager
            )

            // Not used by other utils
            accessPointsUtil = WisefyAccessPointsUtil(
                coroutineDispatcherProvider = coroutineDispatcherProvider,
                logger = logger,
                wifiManager = wifiManager
            )
            addNetworkUtil = WisefyAddNetworkUtil(
                coroutineDispatcherProvider = coroutineDispatcherProvider,
                logger = logger,
                sdkUtil = sdkUtil,
                wifiManager = wifiManager
            )
            frequencyUtil = WisefyFrequencyUtil(
                coroutineDispatcherProvider = coroutineDispatcherProvider,
                logger = logger,
                wifiManager = wifiManager,
                connectivityManager = connectivityManager
            )
            networkConnectionUtil = WisefyNetworkConnectionUtil(
                coroutineDispatcherProvider = coroutineDispatcherProvider,
                connectivityManager = connectivityManager,
                logger = logger,
                networkConnectionStatusUtil = networkConnectionStatusUtil,
                savedNetworkUtil = savedNetworkUtil,
                sdkUtil = sdkUtil,
                wifiManager = wifiManager
            )
            networkInfoUtil = WisefyNetworkInfoUtil(
                coroutineDispatcherProvider = coroutineDispatcherProvider,
                connectivityManager = connectivityManager,
                logger = logger,
                wifiManager = wifiManager
            )
            removeNetworkUtil = WisefyRemoveNetworkUtil(
                coroutineDispatcherProvider = coroutineDispatcherProvider,
                logger = logger,
                savedNetworkUtil = savedNetworkUtil,
                sdkUtil = sdkUtil,
                wifiManager = wifiManager
            )
            securityUtil = WisefySecurityUtil(
                logger = logger
            )
            signalUtil = WisefySignalUtil(
                logger = logger,
                sdkUtil = sdkUtil,
                wifiManager = wifiManager
            )
            wifiUtil = WisefyWifiUtil(
                coroutineDispatcherProvider = coroutineDispatcherProvider,
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
        internal fun customAccessPointsUtil(accessPointsUtil: AccessPointsUtil): Brains = apply {
            this.accessPointsUtil = accessPointsUtil
        }

        @VisibleForTesting
        internal fun customAddNetworkUtil(addNetworkUtil: AddNetworkUtil): Brains = apply {
            this.addNetworkUtil = addNetworkUtil
        }

        @VisibleForTesting
        internal fun customFrequencyUtil(frequencyUtil: FrequencyUtil): Brains = apply {
            this.frequencyUtil = frequencyUtil
        }

        @VisibleForTesting
        internal fun customNetworkConnectionUtil(networkConnectionUtil: NetworkConnectionUtil): Brains = apply {
            this.networkConnectionUtil = networkConnectionUtil
        }

        @VisibleForTesting
        internal fun customNetworkConnectionStatusUtil(
            networkConnectionStatusUtil: NetworkConnectionStatusUtil
        ): Brains = apply {
            this.networkConnectionStatusUtil = networkConnectionStatusUtil
        }

        @VisibleForTesting
        internal fun customNetworkInfoUtil(networkInfoUtil: NetworkInfoUtil): Brains = apply {
            this.networkInfoUtil = networkInfoUtil
        }

        @VisibleForTesting
        internal fun customRemoveNetworkUtil(removeNetworkUtil: RemoveNetworkUtil): Brains = apply {
            this.removeNetworkUtil = removeNetworkUtil
        }

        @VisibleForTesting
        internal fun customSavedNetworkUtil(savedNetworkUtil: SavedNetworkUtil): Brains = apply {
            this.savedNetworkUtil = savedNetworkUtil
        }

        @VisibleForTesting
        internal fun customSecurityUtil(securityUtil: SecurityUtil): Brains = apply {
            this.securityUtil = securityUtil
        }

        @VisibleForTesting
        internal fun customSignalUtil(signalUtil: SignalUtil): Brains = apply {
            this.signalUtil = signalUtil
        }

        @VisibleForTesting
        internal fun customWifiUtil(wifiUtil: WifiUtil): Brains = apply {
            this.wifiUtil = wifiUtil
        }

        fun getSmarts(): WisefyApi {
            return Wisefy(
                accessPointsUtil = accessPointsUtil,
                addNetworkUtil = addNetworkUtil,
                frequencyUtil = frequencyUtil,
                networkConnectionUtil = networkConnectionUtil,
                networkConnectionStatusUtil = networkConnectionStatusUtil,
                networkInfoUtil = networkInfoUtil,
                removeNetworkUtil = removeNetworkUtil,
                savedNetworkUtil = savedNetworkUtil,
                securityUtil = securityUtil,
                signalUtil = signalUtil,
                wifiUtil = wifiUtil
            )
        }
    }

    override fun attachNetworkWatcher() {
        networkConnectionStatusUtil.attachNetworkWatcher()
    }

    override fun detachNetworkWatcher() {
        networkConnectionStatusUtil.detachNetworkWatcher()
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addOpenNetwork(request: AddOpenNetworkRequest): AddNetworkResult {
        return addNetworkUtil.addOpenNetwork(request)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addOpenNetwork(request: AddOpenNetworkRequest, callbacks: AddNetworkCallbacks?) {
        addNetworkUtil.addOpenNetwork(request, callbacks)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addWPA2Network(request: AddWPA2NetworkRequest): AddNetworkResult {
        return addNetworkUtil.addWPA2Network(request)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addWPA2Network(request: AddWPA2NetworkRequest, callbacks: AddNetworkCallbacks?) {
        addNetworkUtil.addWPA2Network(request, callbacks)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addWPA3Network(request: AddWPA3NetworkRequest): AddNetworkResult {
        return addNetworkUtil.addWPA3Network(request)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addWPA3Network(request: AddWPA3NetworkRequest, callbacks: AddNetworkCallbacks?) {
        addNetworkUtil.addWPA3Network(request, callbacks)
    }

    override fun calculateBars(rssiLevel: Int, targetNumberOfBars: Int): Int {
        return signalUtil.calculateBars(rssiLevel, targetNumberOfBars)
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun calculateBars(rssiLevel: Int): Int {
        return signalUtil.calculateBars(rssiLevel)
    }

    override fun compareSignalLevel(rssi1: Int, rssi2: Int): Int {
        return signalUtil.compareSignalLevel(rssi1, rssi2)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun connectToNetwork(request: NetworkConnectionRequest): NetworkConnectionResult {
        return networkConnectionUtil.connectToNetwork(request)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun connectToNetwork(request: NetworkConnectionRequest, callbacks: ConnectToNetworkCallbacks?) {
        networkConnectionUtil.connectToNetwork(request, callbacks)
    }

    @Deprecated(DeprecationMessages.DISABLE_WIFI)
    override fun disableWifi(): Boolean {
        return wifiUtil.disableWifi()
    }

    @Deprecated(DeprecationMessages.DISABLE_WIFI)
    override fun disableWifi(callbacks: DisableWifiCallbacks?) {
        wifiUtil.disableWifi(callbacks)
    }

    override fun disconnectFromCurrentNetwork(): NetworkConnectionResult {
        return networkConnectionUtil.disconnectFromCurrentNetwork()
    }

    override fun disconnectFromCurrentNetwork(callbacks: DisconnectFromCurrentNetworkCallbacks?) {
        networkConnectionUtil.disconnectFromCurrentNetwork(callbacks)
    }

    @Deprecated(DeprecationMessages.ENABLE_WIFI)
    override fun enableWifi(): Boolean {
        return wifiUtil.enableWifi()
    }

    @Deprecated(DeprecationMessages.ENABLE_WIFI)
    override fun enableWifi(callbacks: EnableWifiCallbacks?) {
        wifiUtil.enableWifi(callbacks)
    }

    override fun getCurrentNetwork(): CurrentNetworkData? {
        return networkInfoUtil.getCurrentNetwork()
    }

    override fun getCurrentNetwork(callbacks: GetCurrentNetworkCallbacks?) {
        networkInfoUtil.getCurrentNetwork(callbacks)
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun getCurrentNetworkInfo(request: GetCurrentNetworkInfoRequest): CurrentNetworkInfoData? {
        return networkInfoUtil.getCurrentNetworkInfo(request)
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun getCurrentNetworkInfo(
        callbacks: GetCurrentNetworkInfoCallbacks?,
        request: GetCurrentNetworkInfoRequest
    ) {
        networkInfoUtil.getCurrentNetworkInfo(callbacks, request)
    }

    @RequiresApi(LOLLIPOP)
    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getFrequency(): FrequencyData? {
        return frequencyUtil.getFrequency()
    }

    @RequiresApi(LOLLIPOP)
    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getFrequency(callbacks: GetFrequencyCallbacks?) {
        frequencyUtil.getFrequency(callbacks)
    }

    @RequiresApi(LOLLIPOP)
    override fun getFrequency(network: WifiInfo): FrequencyData {
        return frequencyUtil.getFrequency(network)
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun getIP(): IPData? {
        return networkInfoUtil.getIP()
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun getIP(callbacks: GetIPCallbacks?) {
        networkInfoUtil.getIP(callbacks)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getNearbyAccessPoints(request: GetNearbyAccessPointsRequest): List<AccessPointData> {
        return accessPointsUtil.getNearbyAccessPoints(request)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getNearbyAccessPoints(
        request: GetNearbyAccessPointsRequest,
        callbacks: GetNearbyAccessPointCallbacks?
    ) {
        accessPointsUtil.getNearbyAccessPoints(request, callbacks)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getRSSI(request: GetRSSIRequest): RSSIData? {
        return accessPointsUtil.getRSSI(request)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getRSSI(request: GetRSSIRequest, callbacks: GetRSSICallbacks?) {
        accessPointsUtil.getRSSI(request, callbacks)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun getSavedNetworks(): List<SavedNetworkData> {
        return savedNetworkUtil.getSavedNetworks()
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun getSavedNetworks(callbacks: GetSavedNetworksCallbacks?) {
        savedNetworkUtil.getSavedNetworks(callbacks)
    }

    override fun isDeviceConnectedToMobileNetwork(): Boolean {
        return networkConnectionStatusUtil.isDeviceConnectedToMobileNetwork()
    }

    override fun isDeviceConnectedToMobileOrWifiNetwork(): Boolean {
        return networkConnectionStatusUtil.isDeviceConnectedToMobileOrWifiNetwork()
    }

    override fun isDeviceConnectedToSSID(request: IsNetworkConnectedToSSIDRequest): Boolean {
        return networkConnectionStatusUtil.isDeviceConnectedToSSID(request)
    }

    override fun isDeviceConnectedToWifiNetwork(): Boolean {
        return networkConnectionStatusUtil.isDeviceConnectedToWifiNetwork()
    }

    override fun isDeviceRoaming(): Boolean {
        return networkConnectionStatusUtil.isDeviceRoaming()
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun isNetwork5gHz(): Boolean {
        return frequencyUtil.isNetwork5gHz()
    }

    override fun isNetwork5gHz(network: WifiInfo): Boolean {
        return frequencyUtil.isNetwork5gHz(network)
    }

    override fun isNetworkEAP(network: AccessPointData): Boolean {
        return securityUtil.isNetworkEAP(network)
    }

    override fun isNetworkPSK(network: AccessPointData): Boolean {
        return securityUtil.isNetworkPSK(network)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun isNetworkSaved(request: SearchForSavedNetworkRequest): Boolean {
        return savedNetworkUtil.isNetworkSaved(request)
    }

    override fun isNetworkSecure(network: AccessPointData): Boolean {
        return securityUtil.isNetworkSecure(network)
    }

    override fun isNetworkWEP(network: AccessPointData): Boolean {
        return securityUtil.isNetworkWEP(network)
    }

    override fun isNetworkWPA(network: AccessPointData): Boolean {
        return securityUtil.isNetworkWPA(network)
    }

    override fun isNetworkWPA2(network: AccessPointData): Boolean {
        return securityUtil.isNetworkWPA2(network)
    }

    override fun isNetworkWPA3(network: AccessPointData): Boolean {
        return securityUtil.isNetworkWPA3(network)
    }

    override fun isWifiEnabled(): Boolean {
        return wifiUtil.isWifiEnabled()
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun removeNetwork(request: RemoveNetworkRequest): RemoveNetworkResult {
        return removeNetworkUtil.removeNetwork(request)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun removeNetwork(request: RemoveNetworkRequest, callbacks: RemoveNetworkCallbacks?) {
        removeNetworkUtil.removeNetwork(request, callbacks)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForAccessPoint(request: SearchForSingleAccessPointRequest): AccessPointData? {
        return accessPointsUtil.searchForAccessPoint(request)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForAccessPoint(
        request: SearchForSingleAccessPointRequest,
        callbacks: SearchForAccessPointCallbacks?
    ) {
        accessPointsUtil.searchForAccessPoint(request, callbacks)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForAccessPoints(request: SearchForMultipleAccessPointsRequest): List<AccessPointData> {
        return accessPointsUtil.searchForAccessPoints(request)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForAccessPoints(
        request: SearchForMultipleAccessPointsRequest,
        callbacks: SearchForAccessPointsCallbacks?
    ) {
        accessPointsUtil.searchForAccessPoints(request, callbacks)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForSavedNetwork(request: SearchForSavedNetworkRequest): SavedNetworkData? {
        return savedNetworkUtil.searchForSavedNetwork(request)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForSavedNetwork(
        request: SearchForSavedNetworkRequest,
        callbacks: SearchForSavedNetworkCallbacks?
    ) {
        savedNetworkUtil.searchForSavedNetwork(request, callbacks)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForSavedNetworks(request: SearchForSavedNetworkRequest): List<SavedNetworkData> {
        return savedNetworkUtil.searchForSavedNetworks(request)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForSavedNetworks(
        request: SearchForSavedNetworkRequest,
        callbacks: SearchForSavedNetworksCallbacks?
    ) {
        savedNetworkUtil.searchForSavedNetworks(request, callbacks)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForSSID(request: SearchForSingleSSIDRequest): SSIDData? {
        return accessPointsUtil.searchForSSID(request)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForSSID(request: SearchForSingleSSIDRequest, callbacks: SearchForSSIDCallbacks?) {
        accessPointsUtil.searchForSSID(request, callbacks)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForSSIDs(request: SearchForMultipleSSIDsRequest): List<SSIDData> {
        return accessPointsUtil.searchForSSIDs(request)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForSSIDs(request: SearchForMultipleSSIDsRequest, callbacks: SearchForSSIDsCallbacks?) {
        accessPointsUtil.searchForSSIDs(request, callbacks)
    }
}
