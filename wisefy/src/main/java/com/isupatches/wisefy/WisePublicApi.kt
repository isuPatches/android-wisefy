/*
 * Copyright 2018 Patches Klinefelter
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
package com.isupatches.wisefy

import android.net.NetworkInfo
import android.net.wifi.ScanResult
import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiInfo

import com.isupatches.wisefy.callbacks.AddNetworkCallbacks
import com.isupatches.wisefy.callbacks.ConnectToNetworkCallbacks
import com.isupatches.wisefy.callbacks.DisableWifiCallbacks
import com.isupatches.wisefy.callbacks.DisconnectFromCurrentNetworkCallbacks
import com.isupatches.wisefy.callbacks.EnableWifiCallbacks
import com.isupatches.wisefy.callbacks.GetCurrentNetworkCallbacks
import com.isupatches.wisefy.callbacks.GetCurrentNetworkInfoCallbacks
import com.isupatches.wisefy.callbacks.GetFrequencyCallbacks
import com.isupatches.wisefy.callbacks.GetIPCallbacks
import com.isupatches.wisefy.callbacks.GetNearbyAccessPointsCallbacks
import com.isupatches.wisefy.callbacks.GetRSSICallbacks
import com.isupatches.wisefy.callbacks.GetSavedNetworkCallbacks
import com.isupatches.wisefy.callbacks.GetSavedNetworksCallbacks
import com.isupatches.wisefy.callbacks.RemoveNetworkCallbacks
import com.isupatches.wisefy.callbacks.SearchForAccessPointCallbacks
import com.isupatches.wisefy.callbacks.SearchForAccessPointsCallbacks
import com.isupatches.wisefy.callbacks.SearchForSSIDCallbacks
import com.isupatches.wisefy.callbacks.SearchForSSIDsCallbacks

/**
 * The interface that is the public facing API for WiseFy.  It is composed of various other sub-apis for separation
 * of functionality.
 *
 * @see [WiseFy]
 * @see [AccessPointApi]
 * @see [AddNetworkApi]
 * @see [ConnectionApi]
 * @see [DeviceApi]
 * @see [FrequencyApi]
 * @see [NetworkInfoApi]
 * @see [RemoveNetworkApi]
 * @see [SavedNetworkApi]
 * @see [SecurityApi]
 * @see [SignalStrengthApi]
 * @see [WiseFy]
 *
 * @author Patches
 * @since 3.0
 */
internal interface WiseFyPublicApi : AccessPointApi, AddNetworkApi, ConnectionApi, DeviceApi, FrequencyApi,
    NetworkInfoApi, RemoveNetworkApi, SavedNetworkApi, SecurityApi, SignalStrengthApi, WifiApi {

    fun dump()

    fun getWiseFyLock(): WiseFyLock

    fun isLoggingEnabled(): Boolean
}

/**
 *  An API for querying for nearby access points and related information.
 *
 * @author Patches
 * @since 3.0
 */
internal interface AccessPointApi {

    fun getNearbyAccessPoints(filterDuplicates: Boolean): List<ScanResult>?

    fun getNearbyAccessPoints(filterDuplicates: Boolean, callbacks: GetNearbyAccessPointsCallbacks?)

    fun getRSSI(regexForSSID: String?, takeHighest: Boolean, timeoutInMillis: Int): Int?

    fun getRSSI(
        regexForSSID: String?,
        takeHighest: Boolean,
        timeoutInMillis: Int,
        callbacks: GetRSSICallbacks?
    )

    fun searchForAccessPoint(
        regexForSSID: String?,
        timeoutInMillis: Int,
        filterDuplicates: Boolean
    ): ScanResult?

    fun searchForAccessPoint(
        regexForSSID: String?,
        timeoutInMillis: Int,
        filterDuplicates: Boolean,
        callbacks: SearchForAccessPointCallbacks?
    )

    fun searchForAccessPoints(regexForSSID: String?, filterDuplicates: Boolean): List<ScanResult>?

    fun searchForAccessPoints(
        regexForSSID: String?,
        filterDuplicates: Boolean,
        callbacks: SearchForAccessPointsCallbacks?
    )

    fun searchForSSID(regexForSSID: String?, timeoutInMillis: Int): String?

    fun searchForSSID(regexForSSID: String?, timeoutInMillis: Int, callbacks: SearchForSSIDCallbacks?)

    fun searchForSSIDs(regexForSSID: String?): List<String>?

    fun searchForSSIDs(regexForSSID: String?, callbacks: SearchForSSIDsCallbacks?)
}

/**
 *  An API for adding networks as saved configurations on a device.
 *
 * @author Patches
 * @since 3.0
 */
internal interface AddNetworkApi {

    fun addOpenNetwork(ssid: String?): Int

    fun addOpenNetwork(ssid: String?, callbacks: AddNetworkCallbacks?)

    fun addWEPNetwork(ssid: String?, password: String?): Int

    fun addWEPNetwork(ssid: String?, password: String?, callbacks: AddNetworkCallbacks?)

    fun addWPA2Network(ssid: String?, password: String?): Int

    fun addWPA2Network(ssid: String?, password: String?, callbacks: AddNetworkCallbacks?)
}

/**
 *  An API for managing the network connection of a device.
 *
 * @author Patches
 * @since 3.0
 */
internal interface ConnectionApi {

    fun connectToNetwork(ssidToConnectTo: String?, timeoutInMillis: Int): Boolean

    fun connectToNetwork(
        ssidToConnectTo: String?,
        timeoutInMillis: Int,
        callbacks: ConnectToNetworkCallbacks?
    )

    fun disconnectFromCurrentNetwork(): Boolean

    fun disconnectFromCurrentNetwork(callbacks: DisconnectFromCurrentNetworkCallbacks?)
}

/**
 *  An API for querying about the different network statuses of a device.
 *
 * @author Patches
 * @since 3.0
 */
internal interface DeviceApi {

    fun isDeviceConnectedToMobileNetwork(): Boolean

    fun isDeviceConnectedToMobileOrWifiNetwork(): Boolean

    fun isDeviceConnectedToSSID(ssid: String?): Boolean

    fun isDeviceConnectedToWifiNetwork(): Boolean

    fun isDeviceRoaming(): Boolean
}

/**
 *  An API for querying about a network's frequency.
 *
 * @author Patches
 * @since 3.0
 */
internal interface FrequencyApi {

    fun getFrequency(): Int?

    fun getFrequency(callbacks: GetFrequencyCallbacks?)

    fun getFrequency(network: WifiInfo?): Int?

    fun getFrequency(network: WifiInfo?, callbacks: GetFrequencyCallbacks?)

    fun isNetwork5gHz(): Boolean

    fun isNetwork5gHz(network: WifiInfo?): Boolean
}

/**
 *  An API for querying about a device's current network information.
 *
 * @author Patches
 * @since 3.0
 */
internal interface NetworkInfoApi {

    fun getCurrentNetwork(): WifiInfo?

    fun getCurrentNetwork(callbacks: GetCurrentNetworkCallbacks?)

    fun getCurrentNetworkInfo(): NetworkInfo?

    fun getCurrentNetworkInfo(callbacks: GetCurrentNetworkInfoCallbacks?)

    fun getIP(): String?

    fun getIP(callbacks: GetIPCallbacks?)
}

/**
 *  An API for removing a network as a saved configuration.
 *
 * @author Patches
 * @since 3.0
 */
internal interface RemoveNetworkApi {

    fun removeNetwork(ssidToRemove: String?): Boolean

    fun removeNetwork(ssidToRemove: String?, callbacks: RemoveNetworkCallbacks?)
}

/**
 *  An API for querying saved network information on a device.
 *
 * @author Patches
 * @since 3.0
 */
internal interface SavedNetworkApi {

    fun getSavedNetwork(regexForSSID: String?): WifiConfiguration?

    fun getSavedNetwork(regexForSSID: String?, callbacks: GetSavedNetworkCallbacks?)

    fun getSavedNetworks(): List<WifiConfiguration>?

    fun getSavedNetworks(callbacks: GetSavedNetworksCallbacks?)

    fun getSavedNetworks(regexForSSID: String?): List<WifiConfiguration>?

    fun getSavedNetworks(regexForSSID: String?, callbacks: GetSavedNetworksCallbacks?)

    fun isNetworkSaved(ssid: String?): Boolean
}

/**
 *  An API for querying about a network's security details.
 *
 * @author Patches
 * @since 3.0
 */
internal interface SecurityApi {

    fun isNetworkEAP(scanResult: ScanResult?): Boolean

    fun isNetworkPSK(scanResult: ScanResult?): Boolean

    fun isNetworkSecure(scanResult: ScanResult?): Boolean

    fun isNetworkWEP(scanResult: ScanResult?): Boolean

    fun isNetworkWPA(scanResult: ScanResult?): Boolean

    fun isNetworkWPA2(scanResult: ScanResult?): Boolean
}

/**
 * An API for functionality relating to signal strength.
 *
 * @author Patches
 * @since 3.0
 */
internal interface SignalStrengthApi {

    fun calculateBars(rssiLevel: Int, targetNumberOfBars: Int): Int

    fun compareSignalLevel(rssi1: Int, rssi2: Int): Int
}

/**
 * An API for functionality relating to Wifi.
 *
 * @author Patches
 * @since 3.0
 */
internal interface WifiApi {

    fun disableWifi(): Boolean

    fun disableWifi(callbacks: DisableWifiCallbacks?)

    fun enableWifi(): Boolean

    fun enableWifi(callbacks: EnableWifiCallbacks?)

    fun isWifiEnabled(): Boolean
}
