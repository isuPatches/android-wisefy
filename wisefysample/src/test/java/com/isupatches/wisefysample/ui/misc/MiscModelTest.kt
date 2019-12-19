package com.isupatches.wisefysample.ui.misc

import android.net.NetworkInfo
import android.net.wifi.ScanResult
import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiInfo

import com.isupatches.wisefy.WiseFyPublicApi
import com.isupatches.wisefy.callbacks.DisableWifiCallbacks
import com.isupatches.wisefy.callbacks.EnableWifiCallbacks
import com.isupatches.wisefy.callbacks.GetCurrentNetworkCallbacks
import com.isupatches.wisefy.callbacks.GetCurrentNetworkInfoCallbacks
import com.isupatches.wisefy.callbacks.GetFrequencyCallbacks
import com.isupatches.wisefy.callbacks.GetIPCallbacks
import com.isupatches.wisefy.callbacks.GetNearbyAccessPointsCallbacks
import com.isupatches.wisefy.callbacks.GetSavedNetworksCallbacks

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify

import org.junit.Test

internal class MiscModelTest {

    private val wiseFy = mock<WiseFyPublicApi>()

    private val model = MiscModel(wiseFy)

    @Test fun disableWifi() {
        // When
        model.disableWifi(object : DisableWifiCallbacks {
            override fun failureDisablingWifi() {
            }

            override fun wifiDisabled() {
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
            }
        })

        // Then
        verify(wiseFy, times(1)).disableWifi(any())
    }

    @Test fun enableWifi() {
        // When
        model.enableWifi(object : EnableWifiCallbacks {
            override fun failureEnablingWifi() {
            }

            override fun wifiEnabled() {
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
            }
        })

        // Then
        verify(wiseFy, times(1)).enableWifi(any())
    }

    @Test fun getCurrentNetwork() {
        // When
        model.getCurrentNetwork(object : GetCurrentNetworkCallbacks {
            override fun noCurrentNetwork() {
            }

            override fun retrievedCurrentNetwork(currentNetwork: WifiInfo) {
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
            }
        })

        // Then
        verify(wiseFy, times(1)).getCurrentNetwork(any())
    }

    @Test fun getCurrentNetworkInfo() {
        // When
        model.getCurrentNetworkInfo(object : GetCurrentNetworkInfoCallbacks {
            override fun noCurrentNetworkInfo() {
            }

            override fun retrievedCurrentNetworkInfo(currentNetworkInfo: NetworkInfo) {
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
            }
        })

        // Then
        verify(wiseFy, times(1)).getCurrentNetworkInfo(any())
    }

    @Test fun getFrequency() {
        // When
        model.getFrequency(object : GetFrequencyCallbacks {
            override fun failureGettingFrequency() {
            }

            override fun retrievedFrequency(frequency: Int) {
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
            }
        })

        // Then
        verify(wiseFy, times(1)).getFrequency(any<GetFrequencyCallbacks>())
    }

    @Test fun getIp() {
        // When
        model.getIP(object : GetIPCallbacks {
            override fun failureRetrievingIP() {
            }

            override fun retrievedIP(ip: String) {
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
            }
        })

        // Then
        verify(wiseFy, times(1)).getIP(any())
    }

    @Test fun getNearbyAccessPoints() {
        // When
        model.getNearbyAccessPoints(object : GetNearbyAccessPointsCallbacks {
            override fun retrievedNearbyAccessPoints(nearbyAccessPoints: List<ScanResult>) {
            }

            override fun noAccessPointsFound() {
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
            }
        })

        // Then
        verify(wiseFy, times(1)).getNearbyAccessPoints(eq(true), any())
    }

    @Test fun getSavedNetworks() {
        // When
        model.getSavedNetworks(object : GetSavedNetworksCallbacks {
            override fun noSavedNetworksFound() {
            }

            override fun retrievedSavedNetworks(savedNetworks: List<WifiConfiguration>) {
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
            }
        })

        // Then
        verify(wiseFy, times(1)).getSavedNetworks(any())
    }
}