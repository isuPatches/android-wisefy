/*
 * Copyright 2019 Patches Klinefelter
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

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.net.NetworkInfo
import android.net.wifi.ScanResult
import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiInfo
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
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
import com.isupatches.wisefy.callbacks.GetSavedNetworksCallbacks
import com.isupatches.wisefy.callbacks.RemoveNetworkCallbacks
import com.isupatches.wisefy.callbacks.SearchForAccessPointCallbacks
import com.isupatches.wisefy.callbacks.SearchForAccessPointsCallbacks
import com.isupatches.wisefy.callbacks.SearchForSSIDCallbacks
import com.isupatches.wisefy.callbacks.SearchForSSIDsCallbacks
import com.isupatches.wisefy.callbacks.SearchForSavedNetworkCallbacks
import com.isupatches.wisefy.callbacks.SearchForSavedNetworksCallbacks

/**
 * The interface that is the public facing API for WiseFy.  It is composed of various other sub-apis for separation
 * of functionality.
 *
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
 * Updates
 * - 01/07/2020: Removed isLoggingEnabled
 *
 * @author Patches
 * @since 3.0
 */
interface WiseFyPublicApi : AccessPointApi, AddNetworkApi, ConnectionApi, DeviceApi, FrequencyApi,
    NetworkInfoApi, RemoveNetworkApi, SavedNetworkApi, SecurityApi, SignalStrengthApi, WifiApi {

    /**
     * Used to cleanup the thread started by WiseFy.
     *
     * @see [WiseFy.dump]
     *
     * @author Patches
     * @since 3.0
     */
    fun dump()

    /**
     * To retrieve the lock in use by WiseFy for synchronization.
     *
     * @return WiseFyLock - The instance of the lock in-use by WiseFy
     *
     * @see [WiseFy.getWiseFyLock]
     * @see [WiseFyLock]
     *
     * @author Patches
     * @since 3.0
     */
    fun getWiseFyLock(): WiseFyLock
}

/**
 *  An API for querying for nearby access points and related information.
 *
 * @author Patches
 * @since 3.0
 */
interface AccessPointApi {

    /**
     * To retrieve a list of nearby access points.
     *
     * *NOTE* Setting filterDuplicates to true will exclude access points for an SSID that have a weaker RSSI.
     *  It will always take the highest signal strength.
     *
     * @param filterDuplicates If you want to exclude SSIDs with that same name that have a weaker signal strength
     *
     * @return List of ScanResults|null - List of nearby access points
     *
     * @see [WiseFy.getNearbyAccessPoints]
     * @see [ScanResult]
     *
     * Updates
     * - 01/04/2020: Refined permissions
     *
     * @author Patches
     * @since 3.0
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun getNearbyAccessPoints(filterDuplicates: Boolean): List<ScanResult>?

    /**
     * To retrieve a list of nearby access points.
     *
     * *NOTE* Setting filterDuplicates to true will not return SSIDs with a weaker signal strength.
     *  It will always take the highest.
     *
     * @param filterDuplicates If you want to exclude SSIDs with that same name that have a weaker signal strength
     * @param callbacks The listener to return results to
     *
     * @see [WiseFy.getNearbyAccessPoints]
     * @see [GetNearbyAccessPointsCallbacks]
     *
     * Updates
     * - 01/04/2020: Refined permissions
     *
     * @author Patches
     * @since 3.0
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun getNearbyAccessPoints(filterDuplicates: Boolean, callbacks: GetNearbyAccessPointsCallbacks?)

    /**
     * To retrieve the RSSI of the first network matching a given regex.
     *
     * *NOTE* Setting takeHighest to true will return the access point with the highest RSSI for the given SSID.
     *
     * @param regexForSSID The regex to be used to search for the ssid
     * @param takeHighest Whether to return the access point with the highest RSSI for the given SSID
     * @param timeoutInMillis The amount of time to search for a matching SSID
     *
     * @return Integer - The RSSI value for the found SSID or null if no matching network found
     *
     * @see [WiseFy.getRSSI]
     *
     * Updates
     * - 01/04/2020: Refined permissions
     *
     * @author Patches
     * @since 3.0
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun getRSSI(regexForSSID: String?, takeHighest: Boolean, timeoutInMillis: Int): Int?

    /**
     * To retrieve the RSSI of the first network matching a given regex.
     *
     * *NOTE* Setting takeHighest to true will return the access point with the highest RSSI for the given SSID.
     *
     * @param regexForSSID The regex to be used to search for the ssid
     * @param takeHighest Whether to return the access point with the highest RSSI for the given SSID
     * @param timeoutInMillis The amount of time to search for a matching SSID
     * @param callbacks The listener to return results to
     *
     * @see [WiseFy.getRSSI]
     * @see [GetRSSICallbacks]
     *
     * Updates
     * - 01/04/2020: Refined permissions
     *
     * @author Patches
     * @since 3.0
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun getRSSI(
        regexForSSID: String?,
        takeHighest: Boolean,
        timeoutInMillis: Int,
        callbacks: GetRSSICallbacks?
    )

    /**
     * To return the first access point that matches a given regex.
     *
     * *NOTE* Setting filterDuplicates to true will not return an access point with a weaker signal strength.
     *  It will always take the highest.
     *
     * @param regexForSSID The regex to use when iterating through nearby access points
     * @param timeoutInMillis The amount of time (in milliseconds) to wait for a matching access point
     * @param filterDuplicates If you want to exclude access points with the same name that have a weaker signal
     *  strength
     *
     * @return ScanResult|null - The first access point or access point with the highest signal strength matching the
     *  regex
     *
     * @see [WiseFy.searchForAccessPoint]
     * @see [ScanResult]
     *
     * Updates
     * - 01/04/2020: Refined permissions
     *
     * @author Patches
     * @since 3.0
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForAccessPoint(
        regexForSSID: String?,
        timeoutInMillis: Int,
        filterDuplicates: Boolean
    ): ScanResult?

    /**
     * To return the first access point that matches a given regex.
     *
     * *NOTE* Setting filterDuplicates to true will not return an access point with a weaker signal strength.
     *  It will always take the highest.
     *
     * @param regexForSSID The regex to use when iterating through nearby access points
     * @param timeoutInMillis The amount of time (in milliseconds) to wait for a matching access point
     * @param filterDuplicates If you want to exclude access points with the same name that have a weaker signal
     *  strength
     * @param callbacks The listener to return results to
     *
     * @see [WiseFy.searchForAccessPoint]
     * @see [SearchForAccessPointCallbacks]
     *
     * Updates
     * - 01/04/2020: Refined permissions
     *
     * @author Patches
     * @since 3.0
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForAccessPoint(
        regexForSSID: String?,
        timeoutInMillis: Int,
        filterDuplicates: Boolean,
        callbacks: SearchForAccessPointCallbacks?
    )

    /**
     * To return nearby access points that match a given regex.
     *
     * *NOTE* Setting filterDuplicates to true will not return access points with a weaker signal strength.
     *  It will always take the highest.
     *
     * @param regexForSSID The regex to use when iterating through nearby access points
     * @param filterDuplicates If you want to exclude access points with the same name that have a weaker signal
     *  strength
     *
     * @return List of ScanResult|null - The list of matching access points or null if none match the given regex
     *
     * @see [WiseFy.searchForAccessPoints]
     * @see [ScanResult]
     *
     * Updates
     * - 01/04/2020: Refined permissions
     *
     * @author Patches
     * @since 3.0
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForAccessPoints(regexForSSID: String?, filterDuplicates: Boolean): List<ScanResult>?

    /**
     * To return nearby access points that match a given regex.
     *
     * *NOTE* Setting filterDuplicates to true will not return access points with a weaker signal strength.
     *  It will always take the highest.
     *
     * @param regexForSSID The regex to use when iterating through nearby access points
     * @param filterDuplicates If you want to exclude access points with the same name that have a weaker signal
     *  strength
     * @param callbacks The listener to return results to
     *
     * @see [WiseFy.searchForAccessPoints]
     * @see [SearchForAccessPointsCallbacks]
     *
     * Updates
     * - 01/04/2020: Refined permissions
     *
     * @author Patches
     * @since 3.0
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForAccessPoints(
        regexForSSID: String?,
        filterDuplicates: Boolean,
        callbacks: SearchForAccessPointsCallbacks?
    )

    /**
     * To search local networks and return the first one that contains a given ssid.
     *
     * @param regexForSSID The regex to be used to search for the ssid
     * @param timeoutInMillis The number of milliseconds to keep searching for the SSID
     *
     * @return String|null - The first SSID that contains the search ssid (if any, else null)
     *
     * @see [WiseFy.searchForSSID]
     *
     * Updates
     * - 01/04/2020: Refined permissions
     *
     * @author Patches
     * @since 3.0
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForSSID(regexForSSID: String?, timeoutInMillis: Int): String?

    /**
     * To search local networks and return the first one that contains a given ssid.
     *
     * @param regexForSSID The regex to be used to search for the ssid
     * @param timeoutInMillis The number of milliseconds to keep searching for the SSID
     * @param callbacks The listener to return results to
     *
     * @see [WiseFy.searchForSSID]
     * @see [SearchForSSIDCallbacks]
     *
     * Updates
     * - 01/04/2020: Refined permissions
     *
     * @author Patches
     * @since 3.0
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForSSID(regexForSSID: String?, timeoutInMillis: Int, callbacks: SearchForSSIDCallbacks?)

    /**
     * To search local networks and return the first one that contains a given ssid.
     *
     * @param regexForSSID The regex to be used to search for the ssid
     *
     * @return String|null - The first SSID that contains the search ssid (if any, else null)
     *
     * @see [WiseFy.searchForSSIDs]
     *
     * Updates
     * - 01/04/2020: Refined permissions
     *
     * @author Patches
     * @since 3.0
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForSSIDs(regexForSSID: String?): List<String>?

    /**
     * To search local networks and return the first one that contains a given ssid.
     *
     * @param regexForSSID The regex to be used to search for the ssid
     * @param callbacks The listener to return results to
     *
     * @see [WiseFy.searchForSSIDs]
     * @see [SearchForSSIDsCallbacks]
     *
     * Updates
     * - 01/04/2020: Refined permissions
     *
     * @author Patches
     * @since 3.0
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForSSIDs(regexForSSID: String?, callbacks: SearchForSSIDsCallbacks?)
}

/**
 *  An API for adding networks as saved configurations on a device.
 *
 * @author Patches
 * @since 3.0
 */
interface AddNetworkApi {

    /**
     * To add an open network to the user's configured network list.
     *
     * @param ssid The ssid of the open network you want to add
     *
     * @return int - The return code from WifiManager for network creation (-1 for failure)
     *
     * @see [WiseFy.addOpenNetwork]
     *
     * Updates
     * - 01/04/2020: Refined permissions
     *
     * @author Patches
     * @since 3.0
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun addOpenNetwork(ssid: String?): Int

    /**
     * To add an open network to the user's configured network list.
     *
     * @param ssid The ssid of the open network you want to add
     * @param callbacks The listener to return results to
     *
     * @see [WiseFy.addOpenNetwork]
     * @see [AddNetworkCallbacks]
     *
     * Updates
     * - 01/04/2020: Refined permissions
     *
     * @author Patches
     * @since 3.0
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun addOpenNetwork(ssid: String?, callbacks: AddNetworkCallbacks?)

    /**
     * To add a WEP network to the user's configured network list.
     *
     * @param ssid The ssid of the WEP network you want to add
     * @param password The password for the WEP network being added
     *
     * @return int - The return code from WifiManager for network creation (-1 for failure)
     *
     * @see [WiseFy.addWEPNetwork]
     *
     * Updates
     * - 01/04/2020: Refined permissions
     *
     * @author Patches
     * @since 3.0
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    @Deprecated("Due to security and performance limitations, WEP networks are discouraged")
    fun addWEPNetwork(ssid: String?, password: String?): Int

    /**
     * To add a WEP network to the user's configured network list.
     *
     * @param ssid The ssid of the WEP network you want to add
     * @param password The password for the WEP network being added
     * @param callbacks The listener to return results to
     *
     * @see [WiseFy.addWEPNetwork]
     * @see [AddNetworkCallbacks]
     *
     * Updates
     * - 01/04/2020: Refined permissions
     *
     * @author Patches
     * @since 3.0
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    @Deprecated("Due to security and performance limitations, WEP networks are discouraged")
    fun addWEPNetwork(ssid: String?, password: String?, callbacks: AddNetworkCallbacks?)

    /**
     * To add a WPA2 network to the user's configured network list.
     *
     * @param ssid The ssid of the WPA2 network you want to add
     * @param password The password for the WPA2 network being added
     *
     * @return int - The return code from WifiManager for network creation (-1 for failure)
     *
     * @see [WiseFy.addWPA2Network]
     *
     * Updates
     * - 01/04/2020: Refined permissions
     *
     * @author Patches
     * @since 3.0
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun addWPA2Network(ssid: String?, password: String?): Int

    /**
     * To add a WPA2 network to the user's configured network list.
     *
     * @param ssid The ssid of the WPA2 network you want to add
     * @param password The password for the WPA2 network being added
     * @param callbacks The listener to return results to
     *
     * @see [WiseFy.addWPA2Network]
     * @see [AddNetworkCallbacks]
     *
     * Updates
     * - 01/04/2020: Refined permissions
     *
     * @author Patches
     * @since 3.0
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun addWPA2Network(ssid: String?, password: String?, callbacks: AddNetworkCallbacks?)
}

/**
 *  An API for managing the network connection of a device.
 *
 * @author Patches
 * @since 3.0
 */
interface ConnectionApi {

    /**
     * Used to connect to a network.
     *
     * @param ssidToConnectTo The ssid to connect/reconnect to
     * @param timeoutInMillis The number of milliseconds to continue waiting for the device to connect to the given SSID
     *
     * @return boolean - If the network was successfully reconnected
     *
     * @see [WiseFy.connectToNetwork]
     *
     * Updates
     * - 01/04/2020: Refined permissions
     *
     * @author Patches
     * @since 3.0
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun connectToNetwork(ssidToConnectTo: String?, timeoutInMillis: Int): Boolean

    /**
     * Used to connect to a network.
     *
     * @param ssidToConnectTo The ssid to connect/reconnect to
     * @param timeoutInMillis The number of milliseconds to continue waiting for the device to connect to the given SSID
     * @param callbacks The listener to return results to
     *
     * @see [WiseFy.connectToNetwork]
     * @see [ConnectToNetworkCallbacks]
     *
     * Updates
     * - 01/04/2020: Refined permissions
     *
     * @author Patches
     * @since 3.0
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun connectToNetwork(
        ssidToConnectTo: String?,
        timeoutInMillis: Int,
        callbacks: ConnectToNetworkCallbacks?
    )

    /**
     * To disconnect the user from their current network.
     *
     * @return boolean - If the command succeeded in disconnecting the device from the current network
     *
     * @see [WiseFy.disconnectFromCurrentNetwork]
     *
     * @author Patches
     * @since 3.0
     */
    fun disconnectFromCurrentNetwork(): Boolean

    /**
     * To disconnect the user from their current network.
     *
     * @param callbacks The listener to return results to
     *
     * @see [WiseFy.disconnectFromCurrentNetwork]
     * @see [DisconnectFromCurrentNetworkCallbacks]
     *
     * @author Patches
     * @since 3.0
     */
    fun disconnectFromCurrentNetwork(callbacks: DisconnectFromCurrentNetworkCallbacks?)
}

/**
 *  An API for querying about the different network statuses of a device.
 *
 * @author Patches
 * @since 3.0
 */
interface DeviceApi {

    /**
     * To check if the device is connected to a mobile network.
     *
     * @return bool - If the device is currently connected to a mobile network
     *
     * @see [WiseFy.isDeviceConnectedToMobileNetwork]
     *
     * @author Patches
     * @since 3.0
     */
    fun isDeviceConnectedToMobileNetwork(): Boolean

    /**
     * To check if the device is connected to a mobile or wifi network.
     *
     * @return bool - If the device is currently connected to a mobile or wifi network
     *
     * @see [WiseFy.isDeviceConnectedToMobileOrWifiNetwork]
     *
     * @author Patches
     * @since 3.0
     */
    fun isDeviceConnectedToMobileOrWifiNetwork(): Boolean

    /**
     * To check if the device is connected to a given SSID.
     *
     * @param ssid The SSID to check if the device is attached to
     *
     * @return bool - If the device is currently attached to the given SSID
     *
     * @see [WiseFy.isDeviceConnectedToSSID]
     *
     * @author Patches
     * @since 3.0
     */
    fun isDeviceConnectedToSSID(ssid: String?): Boolean

    /**
     * To check if the device is connected to a wifi network.
     *
     * @return bool - If the device is currently connected to a wifi network
     *
     * @see [WiseFy.isDeviceConnectedToWifiNetwork]
     *
     * @author Patches
     * @since 3.0
     */
    fun isDeviceConnectedToWifiNetwork(): Boolean

    /**
     * To query if the device is roaming.
     *
     * @return boolean - If the current network is roaming
     *
     * @see [WiseFy.isDeviceRoaming]
     *
     * @author Patches
     * @since 3.0
     */
    fun isDeviceRoaming(): Boolean
}

/**
 *  An API for querying about a network's frequency.
 *
 * @author Patches
 * @since 3.0
 */
interface FrequencyApi {

    /**
     * To retrieve the frequency of the device's current network.
     *
     * @return Integer - The frequency of the devices current network or null if no network
     *
     * @see [WiseFy.getFrequency]
     *
     * Updates
     * - 01/04/2020: Added API requirement
     *
     * @author Patches
     * @since 3.0
     */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun getFrequency(): Int?

    /**
     * To retrieve the frequency of the device's current network.
     *
     * @param callbacks The listener to return results to
     *
     * @see [WiseFy.getFrequency]
     * @see [GetFrequencyCallbacks]
     *
     * Updates
     * - 01/04/2020: Refined permissions and added API requirement
     *
     * @author Patches
     * @since 3.0
     */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun getFrequency(callbacks: GetFrequencyCallbacks?)

    /**
     * To retrieve the frequency of a network.
     *
     * @param network The network to return the frequency of
     *
     * @return Integer - The frequency of the devices current network or null if no network
     *
     * @see [WiseFy.getFrequency]
     * @see [WifiInfo]
     *
     * Updates
     * - 01/04/2020: Added API requirement
     *
     * @author Patches
     * @since 3.0
     */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun getFrequency(network: WifiInfo?): Int?

    /**
     * To retrieve the frequency of a network.
     *
     * @param network The network to return the frequency of
     * @param callbacks The listener to return results to
     *
     * @see [WiseFy.getFrequency]
     * @see [GetFrequencyCallbacks]
     * @see [WifiInfo]
     *
     * Updates
     * - 01/04/2020: Added API requirement
     *
     * @author Patches
     * @since 3.0
     */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun getFrequency(network: WifiInfo?, callbacks: GetFrequencyCallbacks?)

    /**
     * To check if the device's current network is 5gHz.
     *
     * @return boolean - If the network is 5gHz
     *
     * @see [WiseFy.isNetwork5gHz]
     * @see [getFrequency]
     *
     * Updates
     * - 01/04/2020: Refined permissions and added API requirement
     *
     * @author Patches
     * @since 3.0
     */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun isNetwork5gHz(): Boolean

    /**
     * To check if a given network is 5gHz.
     *
     * @param network The network to check if it's 5gHz
     *
     * @return boolean - If the network is 5gHz
     *
     * @see [WiseFy.isNetwork5gHz]
     * @see [WifiInfo]
     *
     * Updates
     * - 01/04/2020: Added API requirement
     *
     * @author Patches
     * @since 3.0
     */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun isNetwork5gHz(network: WifiInfo?): Boolean
}

/**
 *  An API for querying about a device's current network information.
 *
 * @author Patches
 * @since 3.0
 */
interface NetworkInfoApi {

    /**
     * To retrieve the user's current network.
     *
     * @return WifiInfo|null - The user's current network information
     *
     * @see [WiseFy.getCurrentNetwork]
     * @see [WifiInfo]
     *
     * @author Patches
     * @since 3.0
     */
    fun getCurrentNetwork(): WifiInfo?

    /**
     * To retrieve the user's current network.
     *
     * @param callbacks The listener to return results to
     *
     * @see [WiseFy.getCurrentNetwork]
     * @see [GetCurrentNetworkCallbacks]
     *
     * @author Patches
     * @since 3.0
     */
    fun getCurrentNetwork(callbacks: GetCurrentNetworkCallbacks?)

    /**
     * To retrieve the details of the phones active network.
     *
     * @return NetworkInfo
     *
     * @see [WiseFy.getCurrentNetworkInfo]
     * @see [NetworkInfo]
     *
     * @author Patches
     * @since 3.0
     */
    fun getCurrentNetworkInfo(): NetworkInfo?

    /**
     * To retrieve the details of the phones active network.
     *
     * @param callbacks The listener to return results to
     *
     * @see [WiseFy.getCurrentNetworkInfo]
     * @see [GetCurrentNetworkInfoCallbacks]
     *
     * @author Patches
     * @since 3.0
     */
    fun getCurrentNetworkInfo(callbacks: GetCurrentNetworkInfoCallbacks?)

    /**
     * To retrieve the IPv4 or IPv6 of a device.
     *
     * @return String - The IPv4 or IPv6 address
     *
     * @see [WiseFy.getIP]
     *
     * @author Patches
     * @since 3.0
     */
    fun getIP(): String?

    /**
     * To retrieve the IPv4 or IPv6 of a device.
     *
     * @param callbacks The listener to return results to
     *
     * @see [WiseFy.getIP]
     * @see [GetIPCallbacks]
     *
     * Updates
     * - 01/04/2020: Refined permissions
     *
     * @author Patches
     * @since 3.0
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun getIP(callbacks: GetIPCallbacks?)
}

/**
 *  An API for removing a network as a saved configuration.
 *
 * @author Patches
 * @since 3.0
 */
interface RemoveNetworkApi {

    /**
     * To remove a configured network.
     *
     * @param ssidToRemove The ssid of the network you want to remove from the configured network list
     *
     * @return boolean - If the command succeeded in removing the network
     *
     * @see [WiseFy.removeNetwork]
     *
     * Updates
     * - 01/04/2020: Refined permissions
     *
     * @author Patches
     * @since 3.0
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun removeNetwork(ssidToRemove: String?): Boolean

    /**
     * To remove a configured network.
     *
     * @param ssidToRemove The ssid of the network you want to remove from the configured network list
     * @param callbacks The listener to return results to
     *
     * @see [WiseFy.removeNetwork]
     * @see [RemoveNetworkCallbacks]
     *
     * Updates
     * - 01/04/2020: Refined permissions
     *
     * @author Patches
     * @since 3.0
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun removeNetwork(ssidToRemove: String?, callbacks: RemoveNetworkCallbacks?)
}

/**
 *  An API for querying saved network information on a device.
 *
 * @author Patches
 * @since 3.0
 */
interface SavedNetworkApi {

    /**
     * To retrieve a list of saved networks on a user's device.
     *
     * @return List of WifiConfiguration|null - List of saved networks on a users device
     *
     * @see [WiseFy.getSavedNetworks]
     * @see [WifiConfiguration]
     *
     * Updates
     * - 01/04/2020: Refined permissions
     *
     * @author Patches
     * @since 3.0
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun getSavedNetworks(): List<WifiConfiguration>?

    /**
     * To retrieve a list of saved networks on a user's device.
     *
     * @param callbacks The listener to return results to
     *
     * @see [WiseFy.getSavedNetworks]
     * @see [GetSavedNetworksCallbacks]
     *
     * Updates
     * - 01/04/2020: Refined permissions
     *
     * @author Patches
     * @since 3.0
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun getSavedNetworks(callbacks: GetSavedNetworksCallbacks?)

    /**
     * To check if an SSID is in the list of configured networks.
     *
     * @param ssid The SSID to check and see if it's in the list of configured networks
     *
     * @return boolean - If the SSID is in the list of configured networks
     *
     * @see [WiseFy.isNetworkSaved]
     *
     * Updates
     * - 01/04/2020: Refined permissions
     *
     * @author Patches
     * @since 3.0
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun isNetworkSaved(ssid: String?): Boolean

    /**
     * To search for and return a saved WiFiConfiguration given an SSID.
     *
     * @param regexForSSID The ssid to use while searching for saved configuration
     *
     * @return WifiConfiguration|null - Saved network that matches the ssid
     *
     * @see [WiseFy.searchForSavedNetwork]
     * @see [WifiConfiguration]
     *
     * Updates
     * - 01/04/2020: Refined permissions
     *
     * @author Patches
     * @since 3.0
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForSavedNetwork(regexForSSID: String?): WifiConfiguration?

    /**
     * To search for and return a saved WiFiConfiguration given an SSID.
     *
     * @param regexForSSID The ssid to use while searching for saved configuration
     * @param callbacks The listener to return results to
     *
     * @see [WiseFy.searchForSavedNetwork]
     * @see [SearchForSavedNetworkCallbacks]
     *
     * Updates
     * - 01/04/2020: Refined permissions
     *
     * @author Patches
     * @since 3.0
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForSavedNetwork(regexForSSID: String?, callbacks: SearchForSavedNetworkCallbacks?)

    /**
     * To retrieve a list of saved networks on a user's device that match a given regex.
     *
     * @param regexForSSID The ssid to use while searching for saved configurations
     *
     * @return List of WifiConfigurations|null - The list of saved network configurations that match the given regex
     *
     * @see [WiseFy.searchForSavedNetworks]
     * @see [WifiConfiguration]
     *
     * Updates
     * - 01/04/2020: Refined permissions
     *
     * @author Patches
     * @since 3.0
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForSavedNetworks(regexForSSID: String?): List<WifiConfiguration>?

    /**
     * To retrieve a list of saved networks on a user's device that match a given regex.
     *
     * @param regexForSSID The ssid to use while searching for saved configurations
     * @param callbacks The listener to return results to
     *
     * @see [WiseFy.searchForSavedNetworks]
     * @see [SearchForSavedNetworksCallbacks]
     *
     * Updates
     * - 01/04/2020: Refined permissions
     *
     * @author Patches
     * @since 3.0
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForSavedNetworks(regexForSSID: String?, callbacks: SearchForSavedNetworksCallbacks?)
}

/**
 *  An API for querying about a network's security details.
 *
 * @author Patches
 * @since 3.0
 */
interface SecurityApi {

    /**
     * To check and return if a network is a EAP network.
     *
     * @param scanResult The network to check
     *
     * @return boolean - Whether the network has EAP capabilities listed
     *
     * @see [WiseFy.isNetworkEAP]
     * @see [ScanResult]
     *
     * @author Patches
     * @since 3.0
     */
    fun isNetworkEAP(scanResult: ScanResult?): Boolean

    /**
     * To check and return if a network is a PSK network.
     *
     * @param scanResult The network to check
     *
     * @return boolean - Whether the network has PSK capabilities listed
     *
     * @see [WiseFy.isNetworkPSK]
     * @see [ScanResult]
     *
     * @author Patches
     * @since 3.0
     */
    fun isNetworkPSK(scanResult: ScanResult?): Boolean

    /**
     * To check and return if a network is secure (contains EAP/PSK/WEP/WPA/WPA2 capabilities).
     *
     * @param scanResult The network to see if it is secure
     *
     * @return boolean - Whether the network is secure
     *
     * @see [WiseFy.isNetworkSecure]
     * @see [ScanResult]
     *
     * @author Patches
     * @since 3.0
     */
    fun isNetworkSecure(scanResult: ScanResult?): Boolean

    /**
     * To check and return if a network is a WEP network.
     *
     * @param scanResult The network to check
     *
     * @return boolean - Whether the network has WEP capabilities listed
     *
     * @see [WiseFy.isNetworkWEP]
     * @see [ScanResult]
     *
     * @author Patches
     * @since 3.0
     */
    fun isNetworkWEP(scanResult: ScanResult?): Boolean

    /**
     * To check and return if a network is a WPA network.
     *
     * @param scanResult The network to check
     *
     * @return boolean - Whether the network has WPA capabilities listed
     *
     * @see [WiseFy.isNetworkWPA]
     * @see [ScanResult]
     *
     * @author Patches
     * @since 3.0
     */
    fun isNetworkWPA(scanResult: ScanResult?): Boolean

    /**
     * To check and return if a network is a WPA2 network.
     *
     * @param scanResult The network to check
     *
     * @return boolean - Whether the network has WPA2 capabilities listed
     *
     * @see [WiseFy.isNetworkWPA2]
     * @see [ScanResult]]
     *
     * @author Patches
     * @since 3.0
     */
    fun isNetworkWPA2(scanResult: ScanResult?): Boolean
}

/**
 * An API for functionality relating to signal strength.
 *
 * @author Patches
 * @since 3.0
 */
interface SignalStrengthApi {

    /**
     * To convert an RSSI level for a network to a number of bars.
     *
     * @param rssiLevel The signal strength of the network
     * @param targetNumberOfBars How many bars or levels there will be total
     *
     * @return int - The number of bars for the given RSSI value
     *
     * @see [WiseFy.calculateBars]
     *
     * @author Patches
     * @since 3.0
     */
    fun calculateBars(rssiLevel: Int, targetNumberOfBars: Int): Int

    /**
     * To compare the signal strength of two networks.
     *
     * This method will return:
     * - Negative value if the first signal is weaker than the second signal
     * - 0 if the two signals have the same strength
     * - Positive value if the first signal is stronger than the second signal
     *
     * @param rssi1 The signal strength of network 1
     * @param rssi2 The signal strength of network 2
     *
     * @return int - The result of the comparison
     *
     * @see [WiseFy.compareSignalLevel]
     *
     * @author Patches
     * @since 3.0
     */
    fun compareSignalLevel(rssi1: Int, rssi2: Int): Int
}

/**
 * An API for functionality relating to Wifi.
 *
 * @author Patches
 * @since 3.0
 */
interface WifiApi {

    /**
     * To disable Wifi on a user's device.
     *
     * @return boolean - True if the command succeeded in disabling wifi
     *
     * @see [WiseFy.disableWifi]
     *
     * @author Patches
     * @since 3.0
     */
    fun disableWifi(): Boolean

    /**
     * To disable Wifi on a user's device.
     *
     * @see [WiseFy.disableWifi]
     * @see [DisableWifiCallbacks]
     *
     * @author Patches
     * @since 3.0
     */
    fun disableWifi(callbacks: DisableWifiCallbacks?)

    /**
     * To enable Wifi on a user's device.
     *
     * @return boolean - If the command succeeded in enabling wifi
     *
     * @see [WiseFy.enableWifi]
     *
     * @author Patches
     * @since 3.0
     */
    fun enableWifi(): Boolean

    /**
     * To enable Wifi on a user's device.
     *
     * @param callbacks The listener to return results to
     *
     * @see [WiseFy.enableWifi]
     * @see [EnableWifiCallbacks]
     *
     * @author Patches
     * @since 3.0
     */
    fun enableWifi(callbacks: EnableWifiCallbacks?)

    /**
     * To check if Wifi is enabled on the device or not.
     *
     * @return boolean - if Wifi is enabled on device
     *
     * @see [WiseFy.isWifiEnabled]
     *
     * @author Patches
     * @since 3.0
     */
    fun isWifiEnabled(): Boolean
}
