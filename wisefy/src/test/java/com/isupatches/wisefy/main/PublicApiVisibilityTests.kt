package com.isupatches.wisefy.main

import android.net.NetworkInfo
import android.net.wifi.ScanResult
import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiInfo
import com.isupatches.wisefy.OPEN_NETWORK_SSID
import com.isupatches.wisefy.TEST_DELAY
import com.isupatches.wisefy.TEST_NUMBER_OF_BARS
import com.isupatches.wisefy.TEST_RSSI_LEVEL
import com.isupatches.wisefy.TEST_RSSI_LEVEL_HIGH
import com.isupatches.wisefy.TEST_RSSI_LEVEL_LOW
import com.isupatches.wisefy.TEST_SSID
import com.isupatches.wisefy.WEP_NETWORK_PASSWORD
import com.isupatches.wisefy.WEP_NETWORK_SSID
import com.isupatches.wisefy.WPA2_NETWORK_PASSWORD
import com.isupatches.wisefy.WPA2_NETWORK_SSID
import com.isupatches.wisefy.WiseFy.Companion.WIFI_MANAGER_FAILURE
import com.isupatches.wisefy.WiseFyPublicApi
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
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyBoolean
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

/**
 * Used to test the visibility of the public API.
 *
 * @author Patches
 */
@Suppress("LargeClass")
internal class PublicApiVisibilityTests {

    private val wisefy = mock(WiseFyPublicApi::class.java)

    @Test
    fun addOpenNetwork_apis() {
        wisefy.addOpenNetwork(OPEN_NETWORK_SSID)
        verify<WiseFyPublicApi>(wisefy).addOpenNetwork(anyString())
        wisefy.addOpenNetwork(OPEN_NETWORK_SSID, object : AddNetworkCallbacks {
            override fun failureAddingNetwork(wifiManagerReturn: Int) {
            }

            override fun networkAdded(newNetworkId: Int, networkConfig: WifiConfiguration) {
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
            }
        })
        verify<WiseFyPublicApi>(wisefy).addOpenNetwork(anyString(), any(AddNetworkCallbacks::class.java))
    }

    @Suppress("deprecation")
    @Test
    fun addWEPNetwork_apis() {
        wisefy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD)
        verify<WiseFyPublicApi>(wisefy).addWEPNetwork(anyString(), anyString())
        wisefy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD, object : AddNetworkCallbacks {
            override fun failureAddingNetwork(wifiManagerReturn: Int) {
            }

            override fun networkAdded(newNetworkId: Int, networkConfig: WifiConfiguration) {
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
            }
        })
        verify<WiseFyPublicApi>(wisefy).addWEPNetwork(anyString(), anyString(), any(AddNetworkCallbacks::class.java))
    }

    @Test
    fun addWPA2Network_apis() {
        wisefy.addWPA2Network(WPA2_NETWORK_SSID, WPA2_NETWORK_PASSWORD)
        verify<WiseFyPublicApi>(wisefy).addWPA2Network(anyString(), anyString())
        wisefy.addWPA2Network(WPA2_NETWORK_SSID, WPA2_NETWORK_PASSWORD, object : AddNetworkCallbacks {
            override fun failureAddingNetwork(wifiManagerReturn: Int) {
            }

            override fun networkAdded(newNetworkId: Int, networkConfig: WifiConfiguration) {
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
            }
        })
        verify<WiseFyPublicApi>(wisefy).addWPA2Network(anyString(), anyString(), any(AddNetworkCallbacks::class.java))
    }

    @Test
    fun compareSignalLevel_api() {
        wisefy.compareSignalLevel(TEST_RSSI_LEVEL_LOW, TEST_RSSI_LEVEL_HIGH)
        verify<WiseFyPublicApi>(wisefy).compareSignalLevel(anyInt(), anyInt())
    }

    @Test
    fun calculateBars_api() {
        wisefy.calculateBars(TEST_RSSI_LEVEL, TEST_NUMBER_OF_BARS)
        verify<WiseFyPublicApi>(wisefy).calculateBars(anyInt(), anyInt())
    }

    @Test
    fun connectToNetwork_apis() {
        wisefy.connectToNetwork(TEST_SSID, TEST_DELAY)
        verify<WiseFyPublicApi>(wisefy).connectToNetwork(anyString(), anyInt())
        wisefy.connectToNetwork(TEST_SSID, TEST_DELAY, object : ConnectToNetworkCallbacks {
            override fun connectedToNetwork() {
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
            }

            override fun failureConnectingToNetwork() {
            }

            override fun networkNotFoundToConnectTo() {
            }
        })
        verify<WiseFyPublicApi>(wisefy).connectToNetwork(
            anyString(),
            anyInt(),
            any(ConnectToNetworkCallbacks::class.java)
        )
    }

    @Test
    fun disableWifi_apis() {
        wisefy.disableWifi()
        verify<WiseFyPublicApi>(wisefy).disableWifi()
        wisefy.disableWifi(object : DisableWifiCallbacks {
            override fun wisefyFailure(wisefyFailureCode: Int) {
            }

            override fun failureDisablingWifi() {
            }

            override fun wifiDisabled() {
            }
        })
        verify<WiseFyPublicApi>(wisefy).disableWifi(any(DisableWifiCallbacks::class.java))
    }

    @Test
    fun disconnectFromCurrentNetwork_apis() {
        wisefy.disconnectFromCurrentNetwork()
        verify<WiseFyPublicApi>(wisefy).disconnectFromCurrentNetwork()
        wisefy.disconnectFromCurrentNetwork(object : DisconnectFromCurrentNetworkCallbacks {
            override fun disconnectedFromCurrentNetwork() {
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
            }

            override fun failureDisconnectingFromCurrentNetwork() {
            }
        })
        verify<WiseFyPublicApi>(wisefy).disconnectFromCurrentNetwork(
            any(DisconnectFromCurrentNetworkCallbacks::class.java)
        )
    }

    @Test
    fun enableWifi_apis() {
        wisefy.enableWifi()
        verify<WiseFyPublicApi>(wisefy).enableWifi()
        wisefy.enableWifi(object : EnableWifiCallbacks {
            override fun wisefyFailure(wisefyFailureCode: Int) {
            }

            override fun failureEnablingWifi() {
            }

            override fun wifiEnabled() {
            }
        })
        verify<WiseFyPublicApi>(wisefy).enableWifi(any(EnableWifiCallbacks::class.java))
    }

    @Test
    fun getCurrentNetwork_apis() {
        wisefy.getCurrentNetwork()
        verify<WiseFyPublicApi>(wisefy).getCurrentNetwork()
        wisefy.getCurrentNetwork(object : GetCurrentNetworkCallbacks {
            override fun wisefyFailure(wisefyFailureCode: Int) {
            }

            override fun noCurrentNetwork() {
            }

            override fun retrievedCurrentNetwork(currentNetwork: WifiInfo) {
            }
        })
        verify<WiseFyPublicApi>(wisefy).getCurrentNetwork(any(GetCurrentNetworkCallbacks::class.java))
    }

    @Test
    fun getCurrentNetworkInfo_apis() {
        wisefy.getCurrentNetworkInfo()
        verify<WiseFyPublicApi>(wisefy).getCurrentNetworkInfo()
        wisefy.getCurrentNetworkInfo(object : GetCurrentNetworkInfoCallbacks {
            override fun noCurrentNetworkInfo() {
            }

            override fun retrievedCurrentNetworkInfo(currentNetworkInfo: NetworkInfo) {
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
            }
        })
        verify<WiseFyPublicApi>(wisefy).getCurrentNetworkInfo(any(GetCurrentNetworkInfoCallbacks::class.java))
    }

    @Test
    fun getFrequency_apis() {
        wisefy.getFrequency()
        verify<WiseFyPublicApi>(wisefy).getFrequency()
        wisefy.getFrequency(object : GetFrequencyCallbacks {
            override fun failureGettingFrequency() {
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
            }

            override fun retrievedFrequency(frequency: Int) {
            }
        })
        verify<WiseFyPublicApi>(wisefy).getFrequency(any(GetFrequencyCallbacks::class.java))

        wisefy.getFrequency(mock(WifiInfo::class.java))
        verify<WiseFyPublicApi>(wisefy).getFrequency(any(WifiInfo::class.java))
        wisefy.getFrequency(mock(WifiInfo::class.java), object : GetFrequencyCallbacks {
            override fun failureGettingFrequency() {
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
            }

            override fun retrievedFrequency(frequency: Int) {
            }
        })
        verify<WiseFyPublicApi>(wisefy).getFrequency(any(WifiInfo::class.java), any(GetFrequencyCallbacks::class.java))
    }

    @Test
    fun getIP_apis() {
        wisefy.getIP()
        verify<WiseFyPublicApi>(wisefy).getIP()
        wisefy.getIP(object : GetIPCallbacks {
            override fun failureRetrievingIP() {
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
            }

            override fun retrievedIP(ip: String) {
            }
        })
        verify<WiseFyPublicApi>(wisefy).getIP(any(GetIPCallbacks::class.java))
    }

    @Test
    fun getNearbyAccessPoints_apis() {
        wisefy.getNearbyAccessPoints(true)
        verify<WiseFyPublicApi>(wisefy).getNearbyAccessPoints(anyBoolean())
        wisefy.getNearbyAccessPoints(true, object : GetNearbyAccessPointsCallbacks {
            override fun retrievedNearbyAccessPoints(nearbyAccessPoints: List<ScanResult>) {
            }

            override fun noAccessPointsFound() {
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
            }
        })
        verify<WiseFyPublicApi>(wisefy).getNearbyAccessPoints(
            anyBoolean(),
            any(GetNearbyAccessPointsCallbacks::class.java)
        )
    }

    @Test
    fun getRSSI_apis() {
        wisefy.getRSSI(TEST_SSID, true, TEST_DELAY)
        verify<WiseFyPublicApi>(wisefy).getRSSI(anyString(), anyBoolean(), anyInt())
        wisefy.getRSSI(TEST_SSID, true, TEST_DELAY, object : GetRSSICallbacks {
            override fun retrievedRSSI(rssi: Int) {
            }

            override fun networkNotFoundToRetrieveRSSI() {
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
            }
        })
        verify<WiseFyPublicApi>(wisefy).getRSSI(anyString(), anyBoolean(), anyInt(), any(GetRSSICallbacks::class.java))
    }

    @Test
    fun getSavedNetworks_apis() {
        wisefy.getSavedNetworks()
        verify<WiseFyPublicApi>(wisefy).getSavedNetworks()
        wisefy.getSavedNetworks(object : GetSavedNetworksCallbacks {
            override fun noSavedNetworksFound() {
            }

            override fun retrievedSavedNetworks(savedNetworks: List<WifiConfiguration>) {
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
            }
        })
        verify<WiseFyPublicApi>(wisefy).getSavedNetworks(any(GetSavedNetworksCallbacks::class.java))
    }

    @Test
    fun getWiseFyLock_api() {
        wisefy.getWiseFyLock()
        verify<WiseFyPublicApi>(wisefy).getWiseFyLock()
    }

    @Test
    fun isDeviceConnectedToMobileNetwork_apis() {
        wisefy.isDeviceConnectedToMobileNetwork()
        verify<WiseFyPublicApi>(wisefy).isDeviceConnectedToMobileNetwork()
    }

    @Test
    fun isDeviceConnectedToMobileOrWifiNetwork_apis() {
        wisefy.isDeviceConnectedToMobileOrWifiNetwork()
        verify<WiseFyPublicApi>(wisefy).isDeviceConnectedToMobileOrWifiNetwork()
    }

    @Test
    fun isDeviceConnectedToSSID_apis() {
        wisefy.isDeviceConnectedToSSID(TEST_SSID)
        verify<WiseFyPublicApi>(wisefy).isDeviceConnectedToSSID(anyString())
    }

    @Test
    fun isDeviceConnectedToWifiNetwork_apis() {
        wisefy.isDeviceConnectedToWifiNetwork()
        verify<WiseFyPublicApi>(wisefy).isDeviceConnectedToWifiNetwork()
    }

    @Test
    fun isDeviceRoaming_apis() {
        wisefy.isDeviceRoaming()
        verify<WiseFyPublicApi>(wisefy).isDeviceRoaming()
    }

    @Test
    fun isNetwork5gHz_apis() {
        wisefy.isNetwork5gHz()
        verify<WiseFyPublicApi>(wisefy).isNetwork5gHz()

        wisefy.isNetwork5gHz(mock(WifiInfo::class.java))
        verify<WiseFyPublicApi>(wisefy).isNetwork5gHz(any(WifiInfo::class.java))
    }

    @Test
    fun isNetworkEAP_api() {
        wisefy.isNetworkEAP(mock(ScanResult::class.java))
        verify<WiseFyPublicApi>(wisefy).isNetworkEAP(any(ScanResult::class.java))
    }

    @Test
    fun isNetworkPSK_api() {
        wisefy.isNetworkPSK(mock(ScanResult::class.java))
        verify<WiseFyPublicApi>(wisefy).isNetworkPSK(any(ScanResult::class.java))
    }

    @Test
    fun isNetworkSaved_api() {
        wisefy.isNetworkSaved(TEST_SSID)
        verify<WiseFyPublicApi>(wisefy).isNetworkSaved(anyString())
    }

    @Test
    fun isNetworkSecure_api() {
        wisefy.isNetworkSecure(mock(ScanResult::class.java))
        verify<WiseFyPublicApi>(wisefy).isNetworkSecure(any(ScanResult::class.java))
    }

    @Test
    fun isNetworkWEP_api() {
        wisefy.isNetworkWEP(mock(ScanResult::class.java))
        verify<WiseFyPublicApi>(wisefy).isNetworkWEP(any(ScanResult::class.java))
    }

    @Test
    fun isNetworkWPA_api() {
        wisefy.isNetworkWPA(mock(ScanResult::class.java))
        verify<WiseFyPublicApi>(wisefy).isNetworkWPA(any(ScanResult::class.java))
    }

    @Test
    fun isNetworkWPA2_api() {
        wisefy.isNetworkWPA2(mock(ScanResult::class.java))
        verify<WiseFyPublicApi>(wisefy).isNetworkWPA2(any(ScanResult::class.java))
    }

    @Test
    fun isWifiEnabled_api() {
        wisefy.isWifiEnabled()
        verify<WiseFyPublicApi>(wisefy).isWifiEnabled()
    }

    @Test
    fun removeNetwork_api() {
        wisefy.removeNetwork(TEST_SSID)
        verify<WiseFyPublicApi>(wisefy).removeNetwork(anyString())

        wisefy.removeNetwork(TEST_SSID, object : RemoveNetworkCallbacks {
            override fun failureRemovingNetwork() {
            }

            override fun networkNotFoundToRemove() {
            }

            override fun networkRemoved() {
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
            }
        })
        verify<WiseFyPublicApi>(wisefy).removeNetwork(anyString(), any(RemoveNetworkCallbacks::class.java))
    }

    @Test
    fun searchForAccessPoint_api() {
        wisefy.searchForAccessPoint(TEST_SSID, TEST_DELAY, true)
        verify<WiseFyPublicApi>(wisefy).searchForAccessPoint(anyString(), anyInt(), anyBoolean())

        wisefy.searchForAccessPoint(TEST_SSID, TEST_DELAY, true, object : SearchForAccessPointCallbacks {
            override fun wisefyFailure(wisefyFailureCode: Int) {
            }

            override fun accessPointFound(accessPoint: ScanResult) {
            }

            override fun accessPointNotFound() {
            }
        })
        verify<WiseFyPublicApi>(wisefy).searchForAccessPoint(
            anyString(),
            anyInt(),
            anyBoolean(),
            any(SearchForAccessPointCallbacks::class.java)
        )
    }

    @Test
    fun searchForAccessPoints_api() {
        wisefy.searchForAccessPoints(TEST_SSID, true)
        verify<WiseFyPublicApi>(wisefy).searchForAccessPoints(anyString(), anyBoolean())

        wisefy.searchForAccessPoints(TEST_SSID, true, object : SearchForAccessPointsCallbacks {
            override fun foundAccessPoints(accessPoints: List<ScanResult>) {
            }

            override fun noAccessPointsFound() {
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
            }
        })
        verify<WiseFyPublicApi>(wisefy).searchForAccessPoints(
            anyString(),
            anyBoolean(),
            any(SearchForAccessPointsCallbacks::class.java)
        )
    }

    @Test
    fun searchForSavedNetwork_apis() {
        wisefy.searchForSavedNetwork(TEST_SSID)
        verify<WiseFyPublicApi>(wisefy).searchForSavedNetwork(anyString())
        wisefy.searchForSavedNetwork(TEST_SSID, object : SearchForSavedNetworkCallbacks {
            override fun wisefyFailure(wisefyFailureCode: Int) {
            }

            override fun savedNetworkNotFound() {
            }

            override fun retrievedSavedNetwork(savedNetwork: WifiConfiguration) {
            }
        })
        verify<WiseFyPublicApi>(wisefy).searchForSavedNetwork(
            anyString(),
            any(SearchForSavedNetworkCallbacks::class.java)
        )
    }

    @Test
    fun searchForSavedNetworks_apis() {
        wisefy.searchForSavedNetworks(TEST_SSID)
        verify<WiseFyPublicApi>(wisefy).searchForSavedNetworks(anyString())
        wisefy.searchForSavedNetworks(TEST_SSID, object : SearchForSavedNetworksCallbacks {
            override fun noSavedNetworksFound() {
            }

            override fun retrievedSavedNetworks(savedNetworks: List<WifiConfiguration>) {
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
            }
        })
        verify<WiseFyPublicApi>(wisefy).searchForSavedNetworks(
            anyString(),
            any(SearchForSavedNetworksCallbacks::class.java)
        )
    }

    @Test
    fun searchForSSID_api() {
        wisefy.searchForSSID(TEST_SSID, TEST_DELAY)
        verify<WiseFyPublicApi>(wisefy).searchForSSID(anyString(), anyInt())

        wisefy.searchForSSID(TEST_SSID, TEST_DELAY, object : SearchForSSIDCallbacks {
            override fun wisefyFailure(wisefyFailureCode: Int) {
            }

            override fun ssidFound(ssid: String) {
            }

            override fun ssidNotFound() {
            }
        })
        verify<WiseFyPublicApi>(wisefy).searchForSSID(anyString(), anyInt(), any(SearchForSSIDCallbacks::class.java))
    }

    @Test
    fun searchForSSIDs_api() {
        wisefy.searchForSSIDs(TEST_SSID)
        verify<WiseFyPublicApi>(wisefy).searchForSSIDs(anyString())

        wisefy.searchForSSIDs(TEST_SSID, object : SearchForSSIDsCallbacks {
            override fun wisefyFailure(wisefyFailureCode: Int) {
            }

            override fun retrievedSSIDs(ssids: List<String>) {
            }

            override fun noSSIDsFound() {
            }
        })
        verify<WiseFyPublicApi>(wisefy).searchForSSIDs(anyString(), any(SearchForSSIDsCallbacks::class.java))
    }

    @Test
    fun wifiManagerFailure_value() {
        assertEquals(WIFI_MANAGER_FAILURE.toLong(), -1)
    }
}
