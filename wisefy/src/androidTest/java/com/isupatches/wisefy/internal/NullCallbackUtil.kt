package com.isupatches.wisefy.internal

import android.net.wifi.WifiInfo
import com.isupatches.wisefy.TEST_SSID
import com.isupatches.wisefy.TEST_TIMEOUT
import com.isupatches.wisefy.WiseFy
import com.isupatches.wisefy.callbacks.GetFrequencyCallbacks
import com.isupatches.wisefy.callbacks.GetSavedNetworksCallbacks
import org.junit.Assert.fail

/**
 * A helper class to call methods in WiseFy with no callbacks.
 *
 * @author Patches
 * @since 3.0
 */
@Suppress("TooGenericExceptionCaught")
internal class NullCallbackUtil internal constructor(private val wisefy: WiseFy) {

    /**
     * To try to adding an open network with null callbacks.
     *
     * @param ssid The ssid to use when adding
     *
     * @author Patches
     * @since 3.0
     */
    fun callAddOpenNetwork(ssid: String?) {
        try {
            wisefy.addOpenNetwork(ssid, null)
        } catch (npe: NullPointerException) {
            fail()
        }
    }

    /**
     * To try adding a WEP network with null callbacks.
     *
     * @param ssid The ssid to use when adding
     * @param password The password to use when adding
     *
     * @author Patches
     * @since 3.0
     */
    @Suppress("DEPRECATION")
    fun callAddWEPNetwork(ssid: String?, password: String?) {
        try {
            wisefy.addWEPNetwork(ssid, password, null)
        } catch (npe: NullPointerException) {
            fail()
        }
    }

    /**
     * To try adding a WPA2 network with null callbacks.
     *
     * @param ssid The ssid to use when adding
     * @param password The password to use when adding
     *
     * @author Patches
     * @since 3.0
     */
    fun callAddWPA2Network(ssid: String?, password: String?) {
        try {
            wisefy.addWPA2Network(ssid, password, null)
        } catch (npe: NullPointerException) {
            fail()
        }
    }

    /**
     * To try to connect to a network with null callbacks.
     *
     * @param ssid The ssid to use when connecting
     *
     * @see WiseFy.connectToNetwork
     *
     * @author Patches
     * @since 3.0
     */
    fun callConnectToNetwork(ssid: String?) {
        try {
            wisefy.connectToNetwork(ssid, TEST_TIMEOUT, null)
        } catch (npe: NullPointerException) {
            fail()
        }
    }

    /**
     * To try disable wifi with null callbacks.
     *
     * @see WiseFy.disableWifi
     *
     * @author Patches
     * @since 3.0
     */
    fun callDisableWifi() {
        try {
            wisefy.disableWifi(null)
        } catch (npe: NullPointerException) {
            fail()
        }
    }

    /**
     * To try to disconnect from the current network with null callbacks.
     *
     * @see WiseFy.disconnectFromCurrentNetwork
     *
     * @author Patches
     * @since 3.0
     */
    fun callDisconnectFromCurrentNetwork() {
        try {
            wisefy.disconnectFromCurrentNetwork(null)
        } catch (npe: NullPointerException) {
            fail()
        }
    }

    /**
     * To try to enable wifi with null callbacks.
     *
     * @see WiseFy.enableWifi
     *
     * @author Patches
     * @since 3.0
     */
    fun callEnableWifi() {
        try {
            wisefy.enableWifi(null)
        } catch (npe: NullPointerException) {
            fail()
        }
    }

    /**
     * To try to get the current network with null callbacks.
     *
     * @see WiseFy.getCurrentNetwork
     *
     * @author Patches
     * @since 3.0
     */
    fun callGetCurrentNetwork() {
        try {
            wisefy.getCurrentNetwork(null)
        } catch (npe: NullPointerException) {
            fail()
        }
    }

    /**
     * To try to get the current network info with null callbacks.
     *
     * @see WiseFy.getCurrentNetworkInfo
     *
     * @author Patches
     * @since 3.0
     */
    fun callGetCurrentNetworkInfo() {
        try {
            wisefy.getCurrentNetworkInfo(null)
        } catch (npe: NullPointerException) {
            fail()
        }
    }

    /**
     * To try and get the frequency of a network with null callbacks.
     *
     * @see WiseFy.getFrequency
     *
     * @author Patches
     * @since 3.0
     */
    fun callGetFrequency() {
        try {
            wisefy.getFrequency(null as GetFrequencyCallbacks?)
        } catch (npe: NullPointerException) {
            fail()
        }
    }

    /**
     * To try and get the frequency of a network with null callbacks.
     *
     * @param network The network to use when trying
     *
     * @see WiseFy.getFrequency
     *
     * @author Patches
     * @since 3.0
     */
    fun callGetFrequency_networkProvided(network: WifiInfo?) {
        try {
            wisefy.getFrequency(network, null)
        } catch (npe: NullPointerException) {
            fail()
        }
    }

    /**
     * To try and get the IP of a device with null callbacks.
     *
     * @see WiseFy.getIP
     *
     * @author Patches
     * @since 3.0
     */
    fun callGetIP() {
        try {
            wisefy.getIP(null)
        } catch (npe: NullPointerException) {
            fail()
        }
    }

    /**
     * To try to get nearby access points with null callbacks.
     *
     * @param filterDuplicates The filterDuplicates param to use when trying.
     *
     * @see WiseFy.getCurrentNetwork
     *
     * @author Patches
     * @since 3.0
     */
    fun callGetNearbyAccessPoints(filterDuplicates: Boolean) {
        try {
            wisefy.getNearbyAccessPoints(filterDuplicates, null)
        } catch (npe: NullPointerException) {
            fail()
        }
    }

    /**
     * To try to get the RSSI level of a nearby access point with null callbacks.
     *
     * @param takeHighest The takeHighest param to use when trying
     *
     * @see WiseFy.getRSSI
     *
     * @author Patches
     * @since 3.0
     */
    fun callGetRSSI(takeHighest: Boolean) {
        try {
            wisefy.getRSSI(TEST_SSID, takeHighest, TEST_TIMEOUT, null)
        } catch (npe: NullPointerException) {
            fail()
        }
    }

    /**
     * To try getting all nearby access points with a null callback.
     *
     * @see WiseFy.getSavedNetworks
     *
     * @author Patches
     * @since 3.0
     */
    fun callGetSavedNetworks() {
        try {
            wisefy.getSavedNetworks(null as GetSavedNetworksCallbacks?)
        } catch (npe: NullPointerException) {
            fail()
        }
    }

    /**
     * To try to remove a network with null callbacks.
     *
     * @param ssid The ssid to use while trying.
     *
     * @see WiseFy.removeNetwork
     *
     * @author Patches
     * @since 3.0
     */
    fun callRemoveNetwork(ssid: String?) {
        try {
            wisefy.removeNetwork(ssid, null)
        } catch (npe: NullPointerException) {
            fail()
        }
    }

    /**
     * To try and search for a nearby access point with null callbacks.
     *
     * @param ssid The ssid to use when trying
     * @param filterDuplicates The filter duplicate param to use when trying
     *
     * @see WiseFy.searchForAccessPoint
     *
     * @author Patches
     * @since 3.0
     */
    fun callSearchForAccessPoint(ssid: String?, filterDuplicates: Boolean) {
        try {
            wisefy.searchForAccessPoint(ssid, TEST_TIMEOUT, filterDuplicates, null)
        } catch (npe: NullPointerException) {
            fail()
        }
    }

    /**
     * To try and search for nearby access points with null callbacks.
     *
     * @param regexForSSID The regex to use when trying
     * @param filterDuplicates The filter duplicates param to use when trying
     *
     * @see WiseFy.searchForAccessPoints
     *
     * @author Patches
     * @since 3.0
     */
    fun callSearchForAccessPoints(regexForSSID: String, filterDuplicates: Boolean) {
        try {
            wisefy.searchForAccessPoints(regexForSSID, filterDuplicates, null)
        } catch (npe: NullPointerException) {
            fail()
        }
    }

    /**
     * To try to retrieve a saved network with a given regex.
     *
     * @param regexForSSID The regex to use while trying
     *
     * @see WiseFy.searchForSavedNetwork
     *
     * @author Patches
     * @since 4.0
     */
    fun callSearchForSavedNetwork(regexForSSID: String?) {
        try {
            wisefy.searchForSavedNetwork(regexForSSID, null)
        } catch (npe: NullPointerException) {
            fail()
        }
    }

    /**
     * To try to get all nearby access points matching a given regex with null callbacks.
     *
     * @param regexForSSID The ssid to use while trying
     *
     * @see WiseFy.searchForSavedNetworks
     *
     * @author Patches
     * @since 4.0
     */
    fun callSearchForSavedNetworks(regexForSSID: String?) {
        try {
            wisefy.searchForSavedNetworks(regexForSSID, null)
        } catch (npe: NullPointerException) {
            fail()
        }
    }

    /**
     * To try and search for a nearby SSID given a regex with null callbacks.
     *
     * @param regexForSSID The regex to use when trying
     *
     * @see WiseFy.searchForSSID
     *
     * @author Patches
     * @since 3.0
     */
    fun callSearchForSSID(regexForSSID: String?) {
        try {
            wisefy.searchForSSID(regexForSSID, TEST_TIMEOUT, null)
        } catch (npe: NullPointerException) {
            fail()
        }
    }

    /**
     * To try and search for nearby SSIDs that match a given regex with null callbacks.
     *
     * @param regexForSSID The regex to use when trying
     *
     * @see WiseFy.searchForSSIDs
     *
     * @author Patches
     * @since 3.0
     */
    fun callSearchForSSIDs(regexForSSID: String?) {
        try {
            wisefy.searchForSSIDs(regexForSSID, null)
        } catch (npe: NullPointerException) {
            fail()
        }
    }
}
