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
 * The interface that is the public facing API for WiseFy
 *
 * @author Patches
 * @since 3.0
 */
interface WiseFyPublicApi : AccessPointApi, AddNetworkApi, ConnectionApi, CurrentNetworkApi, DeviceApi,
    FrequencyApi, RemoveNetworkApi, SavedNetworkApi, SecurityApi, SignalStrengthApi, WifiApi {

    /**
     * Stops the WiseFyHandlerThread and does necessary cleanup
     *
     * @see [com.isupatches.wisefy.threads.WiseFyHandlerThread]
     *
     * @author Patches
     * @since 3.0
     */
    fun dump()

    /**
     * Used to return the object that is used to synchronize logic running on the WiseFyHandlerThread
     *
     * @author Patches
     * @since 3.0
     */
    fun getWiseFyLock(): WiseFyLock

    /**
     * Returns if whether or not logging is enabled for the instance of WiseFy
     *
     * @author Patches
     * @since 3.0
     */
    fun isLoggingEnabled(): Boolean
}

/**
 *
 *
 * @author Patches
 * @since 3.0
 */
interface AccessPointApi {

    /**
     * To retrieve a list of nearby access points.
     *
     * @author Patches
     * @since 3.0
     */
    fun getNearbyAccessPoints(filterDuplicates: Boolean): List<ScanResult>?

    /**
     * To retrieve a list of nearby access points.
     *
     * @author Patches
     * @since 3.0
     */
    fun getNearbyAccessPoints(filterDuplicates: Boolean, callbacks: GetNearbyAccessPointsCallbacks?)

    /**
     * To retrieve the RSSI of the first network matching a given regex.
     *
     * @author Patches
     * @since 3.0
     */
    fun getRSSI(regexForSSID: String?, takeHighest: Boolean, timeoutInMillis: Int): Int?

    /**
     * To retrieve the RSSI of the first network matching a given regex.
     *
     * @author Patches
     * @since 3.0
     */
    fun getRSSI(
        regexForSSID: String?,
        takeHighest: Boolean,
        timeoutInMillis: Int,
        callbacks: GetRSSICallbacks?
    )

    /**
     *
     */
    fun searchForAccessPoint(
        regexForSSID: String?,
        timeoutInMillis: Int,
        filterDuplicates: Boolean
    ): ScanResult?

    /**
     *
     */
    fun searchForAccessPoint(
        regexForSSID: String?,
        timeoutInMillis: Int,
        filterDuplicates: Boolean,
        callbacks: SearchForAccessPointCallbacks?
    )

    /**
     *
     */
    fun searchForAccessPoints(regexForSSID: String?, filterDuplicates: Boolean): List<ScanResult>?

    /**
     *
     */
    fun searchForAccessPoints(
        regexForSSID: String?,
        filterDuplicates: Boolean,
        callbacks: SearchForAccessPointsCallbacks?
    )

    /**
     *
     */
    fun searchForSSID(regexForSSID: String?, timeoutInMillis: Int): String?

    /**
     *
     */
    fun searchForSSID(regexForSSID: String?, timeoutInMillis: Int, callbacks: SearchForSSIDCallbacks?)

    /**
     *
     */
    fun searchForSSIDs(regexForSSID: String?): List<String>?

    /**
     *
     */
    fun searchForSSIDs(regexForSSID: String?, callbacks: SearchForSSIDsCallbacks?)
}

/**
 *
 */
interface AddNetworkApi {

    /**
     *
     */
    fun addOpenNetwork(ssid: String?): Int

    /**
     *
     */
    fun addOpenNetwork(ssid: String?, callbacks: AddNetworkCallbacks?)

    /**
     *
     */
    fun addWEPNetwork(ssid: String?, password: String?): Int

    /**
     *
     */
    fun addWEPNetwork(ssid: String?, password: String?, callbacks: AddNetworkCallbacks?)

    /**
     *
     */
    fun addWPA2Network(ssid: String?, password: String?): Int

    /**
     *
     */
    fun addWPA2Network(ssid: String?, password: String?, callbacks: AddNetworkCallbacks?)
}

/**
 *
 */
interface ConnectionApi {
    /**
     *
     */
    fun connectToNetwork(ssidToConnectTo: String?, timeoutInMillis: Int): Boolean

    /**
     *
     */
    fun connectToNetwork(
        ssidToConnectTo: String?,
        timeoutInMillis: Int,
        callbacks: ConnectToNetworkCallbacks?
    )

    /**
     *
     */
    fun disconnectFromCurrentNetwork(): Boolean

    /**
     *
     */
    fun disconnectFromCurrentNetwork(callbacks: DisconnectFromCurrentNetworkCallbacks?)
}

/**
 *
 */
interface CurrentNetworkApi {
    /**
     *
     */
    fun getCurrentNetwork(): WifiInfo?

    /**
     *
     */
    fun getCurrentNetwork(callbacks: GetCurrentNetworkCallbacks?)

    /**
     *
     */
    fun getCurrentNetworkInfo(): NetworkInfo?

    /**
     *
     */
    fun getCurrentNetworkInfo(callbacks: GetCurrentNetworkInfoCallbacks?)

    /**
     *
     */
    fun getIP(): String?

    /**
     *
     */
    fun getIP(callbacks: GetIPCallbacks?)
}

/**
 *
 */
interface DeviceApi {

    /**
     *
     */
    fun isDeviceConnectedToMobileNetwork(): Boolean

    /**
     *
     */
    fun isDeviceConnectedToMobileOrWifiNetwork(): Boolean

    /**
     *
     */
    fun isDeviceConnectedToSSID(ssid: String?): Boolean

    /**
     *
     */
    fun isDeviceConnectedToWifiNetwork(): Boolean

    /**
     *
     */
    fun isDeviceRoaming(): Boolean
}

/**
 *
 */
interface FrequencyApi {

    /**
     *
     */
    fun getFrequency(): Int?

    /**
     *
     */
    fun getFrequency(callbacks: GetFrequencyCallbacks?)

    /**
     *
     */
    fun getFrequency(network: WifiInfo?): Int?

    /**
     *
     */
    fun getFrequency(network: WifiInfo?, callbacks: GetFrequencyCallbacks?)

    /**
     *
     */
    fun isNetwork5gHz(): Boolean

    /**
     *
     */
    fun isNetwork5gHz(network: WifiInfo?): Boolean
}

/**
 *
 */
interface RemoveNetworkApi {

    /**
     *
     */
    fun removeNetwork(ssidToRemove: String?): Boolean

    /**
     *
     */
    fun removeNetwork(ssidToRemove: String?, callbacks: RemoveNetworkCallbacks?)
}

/**
 *
 */
interface SavedNetworkApi {

    /**
     *
     */
    fun getSavedNetwork(regexForSSID: String?): WifiConfiguration?

    /**
     *
     */
    fun getSavedNetwork(regexForSSID: String?, callbacks: GetSavedNetworkCallbacks?)

    /**
     *
     */
    fun getSavedNetworks(): List<WifiConfiguration>?

    /**
     *
     */
    fun getSavedNetworks(callbacks: GetSavedNetworksCallbacks?)

    /**
     *
     */
    fun getSavedNetworks(regexForSSID: String?): List<WifiConfiguration>?

    /**
     *
     */
    fun getSavedNetworks(regexForSSID: String?, callbacks: GetSavedNetworksCallbacks?)

    /**
     *
     */
    fun isNetworkSaved(ssid: String?): Boolean
}

/**
 *
 */
interface SecurityApi {

    /**
     *
     */
    fun isNetworkEAP(scanResult: ScanResult?): Boolean

    /**
     *
     */
    fun isNetworkPSK(scanResult: ScanResult?): Boolean

    /**
     *
     */
    fun isNetworkSecure(scanResult: ScanResult?): Boolean

    /**
     *
     */
    fun isNetworkWEP(scanResult: ScanResult?): Boolean

    /**
     *
     */
    fun isNetworkWPA(scanResult: ScanResult?): Boolean

    /**
     *
     */
    fun isNetworkWPA2(scanResult: ScanResult?): Boolean
}

/**
 * An interface for functionality relating to signal strength.
 *
 * @author Patches
 * @since 3.0
 */
interface SignalStrengthApi {

    /**
     * To convert an RSSI level for a network to a number of bars.
     *
     * @author Patches
     * @since 3.0
     */
    fun calculateBars(rssiLevel: Int, targetNumberOfBars: Int): Int

    /**
     * To compare the signal strength of two networks.
     *
     * @author Patches
     * @since 3.0
     */
    fun compareSignalLevel(rssi1: Int, rssi2: Int): Int
}

/**
 * An interface for functionality relating to Wifi.
 *
 * @author Patches
 * @since 3.0
 */
interface WifiApi {

    /**
     * To disable Wifi on a user's device.
     *
     * @author Patches
     * @since 3.0
     */
    fun disableWifi(): Boolean

    /**
     * To disable Wifi on a user's device.
     *
     * @author Patches
     * @since 3.0
     */
    fun disableWifi(callbacks: DisableWifiCallbacks?)

    /**
     * To enable Wifi on a user's device.
     *
     * @author Patches
     * @since 3.0
     */
    fun enableWifi(): Boolean

    /**
     * To enable Wifi on a user's device.
     *
     * @author Patches
     * @since 3.0
     */
    fun enableWifi(callbacks: EnableWifiCallbacks?)

    /**
     * To check if Wifi is enabled on the device or not.
     *
     * @author Patches
     * @since 3.0
     */
    fun isWifiEnabled(): Boolean
}
