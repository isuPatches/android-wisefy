package com.isupatches.android.wisefy.ui.search

import android.net.wifi.ScanResult
import android.net.wifi.WifiConfiguration
import com.isupatches.wisefy.WiseFyPublicApi
import com.isupatches.wisefy.callbacks.SearchForAccessPointCallbacks
import com.isupatches.wisefy.callbacks.SearchForAccessPointsCallbacks
import com.isupatches.wisefy.callbacks.SearchForSSIDCallbacks
import com.isupatches.wisefy.callbacks.SearchForSSIDsCallbacks
import com.isupatches.wisefy.callbacks.SearchForSavedNetworkCallbacks
import com.isupatches.wisefy.callbacks.SearchForSavedNetworksCallbacks
import com.isupatches.android.wisefy.TEST_SSID_1
import com.isupatches.android.wisefy.TEST_TIMEOUT
import com.isupatches.android.wisefy.sample.ui.search.SearchModel
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

internal class SearchModelTest {

    private val wiseFy = mock<WiseFyPublicApi>()

    private val model = SearchModel(wiseFy)

    @Test
    fun searchForAccessPoint() {
        // When
        model.searchForAccessPoint(TEST_SSID_1, TEST_TIMEOUT, true, object : SearchForAccessPointCallbacks {
            override fun accessPointFound(accessPoint: ScanResult) {
            }

            override fun accessPointNotFound() {
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
            }
        })

        // Then
        verify(wiseFy, times(1)).searchForAccessPoint(
            eq(TEST_SSID_1),
            eq(TEST_TIMEOUT),
            eq(true),
            any()
        )
    }

    @Test
    fun searchForAccessPoints() {
        // When
        model.searchForAccessPoints(TEST_SSID_1, true, object : SearchForAccessPointsCallbacks {
            override fun foundAccessPoints(accessPoints: List<ScanResult>) {
            }

            override fun noAccessPointsFound() {
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
            }
        })

        // Then
        verify(wiseFy, times(1)).searchForAccessPoints(
            eq(TEST_SSID_1),
            eq(true),
            any()
        )
    }

    @Test
    fun searchForSavedNetwork() {
        // When
        model.searchForSavedNetwork(TEST_SSID_1, object : SearchForSavedNetworkCallbacks {
            override fun retrievedSavedNetwork(savedNetwork: WifiConfiguration) {
            }

            override fun savedNetworkNotFound() {
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
            }
        })

        // Then
        verify(wiseFy, times(1)).searchForSavedNetwork(eq(TEST_SSID_1), any())
    }

    @Test
    fun searchForSavedNetworks() {
        // When
        model.searchForSavedNetworks(TEST_SSID_1, object : SearchForSavedNetworksCallbacks {
            override fun retrievedSavedNetworks(savedNetworks: List<WifiConfiguration>) {
            }

            override fun noSavedNetworksFound() {
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
            }
        })

        // Then
        verify(wiseFy, times(1)).searchForSavedNetworks(eq(TEST_SSID_1), any())
    }

    @Test
    fun searchForSSID() {
        // When
        model.searchForSSID(TEST_SSID_1, TEST_TIMEOUT, object : SearchForSSIDCallbacks {
            override fun ssidFound(ssid: String) {
            }

            override fun ssidNotFound() {
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
            }
        })

        // Then
        verify(wiseFy, times(1)).searchForSSID(
            eq(TEST_SSID_1),
            eq(TEST_TIMEOUT),
            any()
        )
    }

    @Test
    fun searchForSSIDs() {
        // When
        model.searchForSSIDs(TEST_SSID_1, object : SearchForSSIDsCallbacks {
            override fun retrievedSSIDs(ssids: List<String>) {
            }

            override fun noSSIDsFound() {
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
            }
        })

        // Then
        verify(wiseFy, times(1)).searchForSSIDs(
            eq(TEST_SSID_1),
            any()
        )
    }
}
