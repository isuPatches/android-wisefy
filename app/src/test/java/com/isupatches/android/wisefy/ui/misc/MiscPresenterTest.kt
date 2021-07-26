package com.isupatches.android.wisefy.ui.misc

import android.net.NetworkInfo
import android.net.wifi.ScanResult
import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiInfo
import com.isupatches.wisefy.WiseFy.Companion.MIN_FREQUENCY_5GHZ
import com.isupatches.wisefy.callbacks.DisableWifiCallbacks
import com.isupatches.wisefy.callbacks.EnableWifiCallbacks
import com.isupatches.wisefy.callbacks.GetCurrentNetworkCallbacks
import com.isupatches.wisefy.callbacks.GetCurrentNetworkInfoCallbacks
import com.isupatches.wisefy.callbacks.GetFrequencyCallbacks
import com.isupatches.wisefy.callbacks.GetIPCallbacks
import com.isupatches.wisefy.callbacks.GetNearbyAccessPointsCallbacks
import com.isupatches.wisefy.callbacks.GetSavedNetworksCallbacks
import com.isupatches.wisefy.constants.MISSING_PARAMETER
import com.isupatches.android.wisefy.TEST_IP
import com.isupatches.android.wisefy.TestRxSchedulersProvider
import com.isupatches.android.wisefy.sample.ui.misc.MiscPresenter
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.After
import org.junit.Before
import org.junit.Test

internal class MiscPresenterTest {

    private val view = mock<MiscMvp.View>()
    private val model = mock<MiscMvp.Model>()

    private val presenter = MiscPresenter(model, TestRxSchedulersProvider())

    companion object {
        private val CURRENT_NETWORK = mock<WifiInfo>()
        private val CURRENT_NETWORK_INFO = mock<NetworkInfo>()

        private val ACCESS_POINT = mock<ScanResult>()
        private val SAVED_NETWORK = mock<WifiConfiguration>()
    }

    @Before
    fun setUp() {
        presenter.attachView(view)
    }

    @After
    fun tearDown() {
        presenter.detachView()
    }

    @Test
    fun disableWifi_wifiDisabled() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[0] as DisableWifiCallbacks
            callback.wifiDisabled()
        }.whenever(model).disableWifi(any())

        // When
        disableWifi()

        // Then
        verifyTriedToDisableWifi()
        verify(view, times(1)).displayWifiDisabled()
    }

    @Test
    fun disableWifi_failureDisablingWifi() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[0] as DisableWifiCallbacks
            callback.failureDisablingWifi()
        }.whenever(model).disableWifi(any())

        // When
        disableWifi()

        // Then
        verifyTriedToDisableWifi()
        verify(view, times(1)).displayFailureDisablingWifi()
    }

    @Test
    fun disableWifi_wisefyFailure() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[0] as DisableWifiCallbacks
            callback.wisefyFailure(MISSING_PARAMETER)
        }.whenever(model).disableWifi(any())

        // When
        disableWifi()

        // Then
        verifyTriedToDisableWifi()
        verify(view, times(1)).displayWiseFyFailure(MISSING_PARAMETER)
    }

    @Test
    fun enableWifi_wifiEnabled() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[0] as EnableWifiCallbacks
            callback.wifiEnabled()
        }.whenever(model).enableWifi(any())

        // When
        enableWifi()

        // Then
        verifyTriedToEnableWifi()
        verify(view, times(1)).displayWifiEnabled()
    }

    @Test
    fun enableWifi_failureEnablingWifi() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[0] as EnableWifiCallbacks
            callback.failureEnablingWifi()
        }.whenever(model).enableWifi(any())

        // When
        enableWifi()

        // Then
        verifyTriedToEnableWifi()
        verify(view, times(1)).displayFailureEnablingWifi()
    }

    @Test
    fun enableWifi_wisefyFailure() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[0] as EnableWifiCallbacks
            callback.wisefyFailure(MISSING_PARAMETER)
        }.whenever(model).enableWifi(any())

        // When
        enableWifi()

        // Then
        verifyTriedToEnableWifi()
        verify(view, times(1)).displayWiseFyFailure(MISSING_PARAMETER)
    }

    @Test
    fun getCurrentNetwork_retrievedCurrentNetwork() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[0] as GetCurrentNetworkCallbacks
            callback.retrievedCurrentNetwork(CURRENT_NETWORK)
        }.whenever(model).getCurrentNetwork(any())

        // When
        getCurrentNetwork()

        // Then
        verifyTriedToGetCurrentNetwork()
        verify(view, times(1)).displayCurrentNetwork(CURRENT_NETWORK)
    }

    @Test
    fun getCurrentNetwork_noCurrentNetwork() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[0] as GetCurrentNetworkCallbacks
            callback.noCurrentNetwork()
        }.whenever(model).getCurrentNetwork(any())

        // When
        getCurrentNetwork()

        // Then
        verifyTriedToGetCurrentNetwork()
        verify(view, times(1)).displayNoCurrentNetwork()
    }

    @Test
    fun getCurrentNetwork_wisefyFailure() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[0] as GetCurrentNetworkCallbacks
            callback.wisefyFailure(MISSING_PARAMETER)
        }.whenever(model).getCurrentNetwork(any())

        // When
        getCurrentNetwork()

        // Then
        verifyTriedToGetCurrentNetwork()
        verify(view, times(1)).displayWiseFyFailure(MISSING_PARAMETER)
    }

    @Test
    fun getCurrentNetworkInfo_retrievedCurrentNetworkInfo() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[0] as GetCurrentNetworkInfoCallbacks
            callback.retrievedCurrentNetworkInfo(CURRENT_NETWORK_INFO)
        }.whenever(model).getCurrentNetworkInfo(any())

        // When
        getCurrentNetworkInfo()

        // Then
        verifyTriedToGetCurrentNetworkInfo()
        verify(view, times(1)).displayCurrentNetworkInfo(CURRENT_NETWORK_INFO)
    }

    @Test
    fun getCurrentNetworkInfo_noCurrentNetworkInfo() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[0] as GetCurrentNetworkInfoCallbacks
            callback.noCurrentNetworkInfo()
        }.whenever(model).getCurrentNetworkInfo(any())

        // When
        getCurrentNetworkInfo()

        // Then
        verifyTriedToGetCurrentNetworkInfo()
        verify(view, times(1)).displayNoCurrentNetworkInfo()
    }

    @Test
    fun getCurrentNetworkInfo_wisefyFailure() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[0] as GetCurrentNetworkInfoCallbacks
            callback.wisefyFailure(MISSING_PARAMETER)
        }.whenever(model).getCurrentNetworkInfo(any())

        // When
        getCurrentNetworkInfo()

        // Then
        verifyTriedToGetCurrentNetworkInfo()
        verify(view, times(1)).displayWiseFyFailure(MISSING_PARAMETER)
    }

    @Test
    fun getFrequency_retrievedFrequency() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[0] as GetFrequencyCallbacks
            callback.retrievedFrequency(MIN_FREQUENCY_5GHZ)
        }.whenever(model).getFrequency(any())

        // When
        getFrequency()

        // Then
        verifyTriedToGetFrequency()
        verify(view, times(1)).displayFrequency(MIN_FREQUENCY_5GHZ)
    }

    @Test
    fun getFrequency_failureGettingFrequency() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[0] as GetFrequencyCallbacks
            callback.failureGettingFrequency()
        }.whenever(model).getFrequency(any())

        // When
        getFrequency()

        // Then
        verifyTriedToGetFrequency()
        verify(view, times(1)).displayFailureRetrievingFrequency()
    }

    @Test
    fun getFrequency_wisefyFailure() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[0] as GetFrequencyCallbacks
            callback.wisefyFailure(MISSING_PARAMETER)
        }.whenever(model).getFrequency(any())

        // When
        getFrequency()

        // Then
        verifyTriedToGetFrequency()
        verify(view, times(1)).displayWiseFyFailure(MISSING_PARAMETER)
    }

    @Test
    fun getIP_retrievedIP() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[0] as GetIPCallbacks
            callback.retrievedIP(TEST_IP)
        }.whenever(model).getIP(any())

        // When
        getIP()

        // Then
        verifyTriedToGetIP()
        verify(view, times(1)).displayIP(TEST_IP)
    }

    @Test
    fun getIP_failureRetrievingIP() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[0] as GetIPCallbacks
            callback.failureRetrievingIP()
        }.whenever(model).getIP(any())

        // When
        getIP()

        // Then
        verifyTriedToGetIP()
        verify(view, times(1)).displayFailureRetrievingIP()
    }

    @Test
    fun getIP_wisefyFailure() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[0] as GetIPCallbacks
            callback.wisefyFailure(MISSING_PARAMETER)
        }.whenever(model).getIP(any())

        // When
        getIP()

        // Then
        verifyTriedToGetIP()
        verify(view, times(1)).displayWiseFyFailure(MISSING_PARAMETER)
    }

    @Test
    fun getNearbyAccessPoints_retrievedNearbyAccessPoints() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[0] as GetNearbyAccessPointsCallbacks
            callback.retrievedNearbyAccessPoints(listOf(ACCESS_POINT))
        }.whenever(model).getNearbyAccessPoints(any())

        // When
        getNearbyAccessPoints()

        // Then
        verifyTriedToGetNearbyAccessPoints()
        verify(view, times(1)).displayNearbyAccessPoints(listOf(ACCESS_POINT))
    }

    @Test
    fun getNearbyAccessPoints_noAccessPointsFound() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[0] as GetNearbyAccessPointsCallbacks
            callback.noAccessPointsFound()
        }.whenever(model).getNearbyAccessPoints(any())

        // When
        getNearbyAccessPoints()

        // Then
        verifyTriedToGetNearbyAccessPoints()
        verify(view, times(1)).displayNoAccessPointsFound()
    }

    @Test
    fun getNearbyAccessPoints_wisefyFailure() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[0] as GetNearbyAccessPointsCallbacks
            callback.wisefyFailure(MISSING_PARAMETER)
        }.whenever(model).getNearbyAccessPoints(any())

        // When
        getNearbyAccessPoints()

        // Then
        verifyTriedToGetNearbyAccessPoints()
        verify(view, times(1)).displayWiseFyFailure(MISSING_PARAMETER)
    }

    @Test
    fun getSavedNetworks_retrievedSavedNetworks() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[0] as GetSavedNetworksCallbacks
            callback.retrievedSavedNetworks(listOf(SAVED_NETWORK))
        }.whenever(model).getSavedNetworks(any())

        // When
        getSavedNetworks()

        // Then
        verifyTriedToGetSavedNetworks()
        verify(view, times(1)).displaySavedNetworks(listOf(SAVED_NETWORK))
    }

    @Test
    fun getSavedNetworks_noSavedNetworksFound() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[0] as GetSavedNetworksCallbacks
            callback.noSavedNetworksFound()
        }.whenever(model).getSavedNetworks(any())

        // When
        getSavedNetworks()

        // Then
        verifyTriedToGetSavedNetworks()
        verify(view, times(1)).displayNoSavedNetworksFound()
    }

    @Test
    fun getSavedNetworks_wisefyFailure() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[0] as GetSavedNetworksCallbacks
            callback.wisefyFailure(MISSING_PARAMETER)
        }.whenever(model).getSavedNetworks(any())

        // When
        getSavedNetworks()

        // Then
        verifyTriedToGetSavedNetworks()
        verify(view, times(1)).displayWiseFyFailure(MISSING_PARAMETER)
    }

    /*
     * Call Helpers
     */

    private fun disableWifi() {
        presenter.disableWifi()
    }

    private fun enableWifi() {
        presenter.enableWifi()
    }

    private fun getCurrentNetwork() {
        presenter.getCurrentNetwork()
    }

    private fun getCurrentNetworkInfo() {
        presenter.getCurrentNetworkInfo()
    }

    private fun getFrequency() {
        presenter.getFrequency()
    }

    private fun getIP() {
        presenter.getIP()
    }

    private fun getNearbyAccessPoints() {
        presenter.getNearbyAccessPoints()
    }

    private fun getSavedNetworks() {
        presenter.getSavedNetworks()
    }

    /*
     * Verification Helpers
     */

    private fun verifyTriedToDisableWifi() {
        verify(model, times(1)).disableWifi(any())
    }

    private fun verifyTriedToEnableWifi() {
        verify(model, times(1)).enableWifi(any())
    }

    private fun verifyTriedToGetCurrentNetwork() {
        verify(model, times(1)).getCurrentNetwork(any())
    }

    private fun verifyTriedToGetCurrentNetworkInfo() {
        verify(model, times(1)).getCurrentNetworkInfo(any())
    }

    private fun verifyTriedToGetFrequency() {
        verify(model, times(1)).getFrequency(any())
    }

    private fun verifyTriedToGetIP() {
        verify(model, times(1)).getIP(any())
    }

    private fun verifyTriedToGetNearbyAccessPoints() {
        verify(model, times(1)).getNearbyAccessPoints(any())
    }

    private fun verifyTriedToGetSavedNetworks() {
        verify(model, times(1)).getSavedNetworks(any())
    }
}
