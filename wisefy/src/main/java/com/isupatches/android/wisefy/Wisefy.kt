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
import android.Manifest.permission.CHANGE_WIFI_STATE
import android.content.Context
import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.net.wifi.ScanResult
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Build.VERSION_CODES.LOLLIPOP
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import androidx.annotation.VisibleForTesting
import com.isupatches.android.wisefy.accesspoints.AccessPointsUtil
import com.isupatches.android.wisefy.accesspoints.WiseFyAccessPointsUtil
import com.isupatches.android.wisefy.accesspoints.entities.AccessPointData
import com.isupatches.android.wisefy.addnetwork.AddNetworkUtil
import com.isupatches.android.wisefy.addnetwork.WisefyAddNetworkUtil
import com.isupatches.android.wisefy.addnetwork.entities.AddNetworkResult
import com.isupatches.android.wisefy.addnetwork.entities.OpenNetworkData
import com.isupatches.android.wisefy.addnetwork.entities.WPA2NetworkData
import com.isupatches.android.wisefy.addnetwork.entities.WPA3NetworkData
import com.isupatches.android.wisefy.constants.DeprecationMessages
import com.isupatches.android.wisefy.frequency.FrequencyUtil
import com.isupatches.android.wisefy.frequency.WisefyFrequencyUtil
import com.isupatches.android.wisefy.logging.WisefyLogger
import com.isupatches.android.wisefy.networkconnection.NetworkConnectionUtil
import com.isupatches.android.wisefy.networkconnection.WisefyNetworkConnectionUtil
import com.isupatches.android.wisefy.networkinfo.NetworkInfoUtil
import com.isupatches.android.wisefy.networkinfo.WisefyNetworkInfoUtil
import com.isupatches.android.wisefy.removenetwork.RemoveNetworkUtil
import com.isupatches.android.wisefy.removenetwork.WisefyRemoveNetworkUtil
import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkResult
import com.isupatches.android.wisefy.savednetworks.SavedNetworkUtil
import com.isupatches.android.wisefy.savednetworks.WisefySavedNetworkUtil
import com.isupatches.android.wisefy.savednetworks.entities.SavedNetworkData
import com.isupatches.android.wisefy.security.SecurityUtil
import com.isupatches.android.wisefy.security.WisefySecurityUtil
import com.isupatches.android.wisefy.signal.SignalUtil
import com.isupatches.android.wisefy.signal.WisefySignalUtil
import com.isupatches.android.wisefy.wifi.WifiUtil
import com.isupatches.android.wisefy.wifi.WisefyWifiUtil

@Suppress("SyntheticAccessor")
class Wisefy private constructor(
    private val accessPointsUtil: AccessPointsUtil,
    private val addNetworkUtil: AddNetworkUtil,
    private val frequencyUtil: FrequencyUtil,
    private val networkConnectionUtil: NetworkConnectionUtil,
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

            addNetworkUtil = WisefyAddNetworkUtil(wifiManager)
            savedNetworkUtil = WisefySavedNetworkUtil(wifiManager)
            removeNetworkUtil = WisefyRemoveNetworkUtil(wifiManager, savedNetworkUtil)
            frequencyUtil = WisefyFrequencyUtil(wifiManager)
            securityUtil = WisefySecurityUtil()
            signalUtil = WisefySignalUtil(wifiManager)
            networkConnectionUtil = WisefyNetworkConnectionUtil(connectivityManager, wifiManager, logger)
            accessPointsUtil = WiseFyAccessPointsUtil(wifiManager, logger)
            networkInfoUtil = WisefyNetworkInfoUtil(wifiManager, logger)
            wifiUtil = WisefyWifiUtil(wifiManager)
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
        networkConnectionUtil.attachNetworkWatcher()
    }

    override fun detachNetworkWatcher() {
        networkConnectionUtil.detachNetworkWatcher()
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addOpenNetwork(data: OpenNetworkData): AddNetworkResult {
        return addNetworkUtil.addOpenNetwork(data)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addWPA2Network(data: WPA2NetworkData): AddNetworkResult {
        return addNetworkUtil.addWPA2Network(data)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addWPA3Network(data: WPA3NetworkData): AddNetworkResult {
        return addNetworkUtil.addWPA3Network(data)
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
    override fun connectToNetwork(ssidToConnectTo: String, timeoutInMillis: Int): Boolean {
        return networkConnectionUtil.connectToNetwork(ssidToConnectTo, timeoutInMillis)
    }

    @Deprecated(DeprecationMessages.DISABLE_WIFI)
    override fun disableWifi(): Boolean {
        return wifiUtil.disableWifi()
    }

    override fun disconnectFromCurrentNetwork(): Boolean {
        return networkConnectionUtil.disconnectFromCurrentNetwork()
    }

    @Deprecated(DeprecationMessages.ENABLE_WIFI)
    override fun enableWifi(): Boolean {
        return wifiUtil.enableWifi()
    }

    override fun getCurrentNetwork(): WifiInfo? {
        return networkInfoUtil.getCurrentNetwork()
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun getCurrentNetworkInfo(): NetworkInfo? {
        return networkInfoUtil.getCurrentNetworkInfo()
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun getNetworkCapabilities(network: Network): NetworkCapabilities? {
        return networkInfoUtil.getNetworkCapabilities(network)
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun getNetworkLinkProperties(network: Network): LinkProperties? {
        return networkInfoUtil.getNetworkLinkProperties(network)
    }

    @RequiresApi(LOLLIPOP)
    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getFrequency(): Int? {
        return frequencyUtil.getFrequency()
    }

    @RequiresApi(LOLLIPOP)
    override fun getFrequency(network: WifiInfo): Int {
        return frequencyUtil.getFrequency(network)
    }

    override fun getIP(): String? {
        return networkInfoUtil.getIP()
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getNearbyAccessPoints(filterDuplicates: Boolean): List<AccessPointData> {
        return accessPointsUtil.getNearbyAccessPoints(filterDuplicates)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getRSSI(regexForSSID: String, takeHighest: Boolean, timeoutInMillis: Int): Int? {
        return accessPointsUtil.getRSSI(regexForSSID, takeHighest, timeoutInMillis)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getSavedNetworks(): List<SavedNetworkData> {
        return savedNetworkUtil.getSavedNetworks()
    }

    override fun isDeviceConnectedToMobileNetwork(): Boolean {
        return networkConnectionUtil.isDeviceConnectedToMobileNetwork()
    }

    override fun isDeviceConnectedToMobileOrWifiNetwork(): Boolean {
        return networkConnectionUtil.isDeviceConnectedToMobileOrWifiNetwork()
    }

    override fun isDeviceConnectedToSSID(ssid: String): Boolean {
        return networkConnectionUtil.isDeviceConnectedToSSID(ssid)
    }

    override fun isDeviceConnectedToWifiNetwork(): Boolean {
        return networkConnectionUtil.isDeviceConnectedToWifiNetwork()
    }

    override fun isDeviceRoaming(): Boolean {
        return networkConnectionUtil.isDeviceRoaming()
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun isNetwork5gHz(): Boolean {
        return frequencyUtil.isNetwork5gHz()
    }

    override fun isNetwork5gHz(network: WifiInfo): Boolean {
        return frequencyUtil.isNetwork5gHz(network)
    }

    override fun isNetworkEAP(scanResult: ScanResult): Boolean {
        return securityUtil.isNetworkEAP(scanResult)
    }

    override fun isNetworkPSK(scanResult: ScanResult): Boolean {
        return securityUtil.isNetworkPSK(scanResult)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun isNetworkSaved(ssid: String): Boolean {
        return savedNetworkUtil.isNetworkSaved(ssid)
    }

    override fun isNetworkSecure(scanResult: ScanResult): Boolean {
        return securityUtil.isNetworkSecure(scanResult)
    }

    override fun isNetworkWEP(scanResult: ScanResult): Boolean {
        return securityUtil.isNetworkWEP(scanResult)
    }

    override fun isNetworkWPA(scanResult: ScanResult): Boolean {
        return securityUtil.isNetworkWPA(scanResult)
    }

    override fun isNetworkWPA2(scanResult: ScanResult): Boolean {
        return securityUtil.isNetworkWPA2(scanResult)
    }

    override fun isNetworkWPA3(scanResult: ScanResult): Boolean {
        return securityUtil.isNetworkWPA3(scanResult)
    }

    override fun isWifiEnabled(): Boolean {
        return wifiUtil.isWifiEnabled()
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun removeNetwork(ssidToRemove: String): RemoveNetworkResult {
        return removeNetworkUtil.removeNetwork(ssidToRemove)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForAccessPoint(
        regexForSSID: String,
        timeoutInMillis: Int,
        filterDuplicates: Boolean
    ): AccessPointData {
        return accessPointsUtil.searchForAccessPoint(regexForSSID, timeoutInMillis, filterDuplicates)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForAccessPoints(regexForSSID: String, filterDuplicates: Boolean): List<AccessPointData> {
        return accessPointsUtil.searchForAccessPoints(regexForSSID, filterDuplicates)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForSavedNetwork(regexForSSID: String): SavedNetworkData? {
        return savedNetworkUtil.searchForSavedNetwork(regexForSSID)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForSavedNetworks(regexForSSID: String): List<SavedNetworkData> {
        return savedNetworkUtil.searchForSavedNetworks(regexForSSID)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForSSID(regexForSSID: String, timeoutInMillis: Int): String? {
        return accessPointsUtil.searchForSSID(regexForSSID, timeoutInMillis)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForSSIDs(regexForSSID: String): List<String> {
        return accessPointsUtil.searchForSSIDs(regexForSSID)
    }
}
