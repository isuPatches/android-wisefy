package com.isupatches.wisefysample.ui.search

import android.net.wifi.ScanResult
import android.net.wifi.WifiConfiguration

import com.isupatches.wisefy.callbacks.GetSavedNetworkCallbacks
import com.isupatches.wisefy.callbacks.GetSavedNetworksCallbacks
import com.isupatches.wisefy.callbacks.SearchForAccessPointCallbacks
import com.isupatches.wisefy.callbacks.SearchForAccessPointsCallbacks
import com.isupatches.wisefy.callbacks.SearchForSSIDCallbacks
import com.isupatches.wisefy.callbacks.SearchForSSIDsCallbacks
import com.isupatches.wisefy.constants.MISSING_PARAMETER
import com.isupatches.wisefysample.TEST_SSID_1
import com.isupatches.wisefysample.TEST_TIMEOUT
import com.isupatches.wisefysample.TestRxSchedulersProvider

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever

import org.junit.After
import org.junit.Before
import org.junit.Test

internal class SearchPresenterTest {

    private val view = mock<SearchMvp.View>()
    private val model = mock<SearchMvp.Model>()

    private val presenter = SearchPresenter(model, TestRxSchedulersProvider())

    companion object {
        private val SAVED_NETWORK = mock<WifiConfiguration>()
        private val ACCESS_POINT = mock<ScanResult>()
    }

    @Before fun setUp() {
        presenter.attachView(view)
    }

    @After fun tearDown() {
        presenter.detachView()
    }

    @Test fun getSavedNetwork_retrievedSavedNetwork() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[1] as GetSavedNetworkCallbacks
            callback.retrievedSavedNetwork(SAVED_NETWORK)
            null
        }.whenever(model).getSavedNetwork(eq(TEST_SSID_1), any())

        // When
        getSavedNetwork()

        // Then
        verifyRetrievedSavedNetwork()
        verify(view, times(1)).displaySavedNetwork(SAVED_NETWORK)
    }

    @Test fun getSavedNetwork_savedNetworkNotFound() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[1] as GetSavedNetworkCallbacks
            callback.savedNetworkNotFound()
            null
        }.whenever(model).getSavedNetwork(eq(TEST_SSID_1), any())

        // When
        getSavedNetwork()

        // Then
        verifyRetrievedSavedNetwork()
        verify(view, times(1)).displaySavedNetworkNotFound()
    }

    @Test fun getSavedNetwork_wisefyFailure() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[1] as GetSavedNetworkCallbacks
            callback.wisefyFailure(MISSING_PARAMETER)
            null
        }.whenever(model).getSavedNetwork(eq(TEST_SSID_1), any())

        // When
        getSavedNetwork()

        // Then
        verifyRetrievedSavedNetwork()
        verify(view, times(1)).displayWiseFyFailure(MISSING_PARAMETER)
    }

    @Test fun getSavedNetworks_retrievedSavedNetworks() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[1] as GetSavedNetworksCallbacks
            callback.retrievedSavedNetworks(listOf(SAVED_NETWORK))
            null
        }.whenever(model).getSavedNetworks(eq(TEST_SSID_1), any())

        // When
        getSavedNetworks()

        // Then
        verifyRetrievedSavedNetworks()
        verify(view, times(1)).displaySavedNetworks(listOf(SAVED_NETWORK))
    }

    @Test fun getSavedNetworks_noSavedNetworksFound() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[1] as GetSavedNetworksCallbacks
            callback.noSavedNetworksFound()
            null
        }.whenever(model).getSavedNetworks(eq(TEST_SSID_1), any())

        // When
        getSavedNetworks()

        // Then
        verifyRetrievedSavedNetworks()
        verify(view, times(1)).displayNoSavedNetworksFound()
    }

    @Test fun getSavedNetworks_wisefyFailure() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[1] as GetSavedNetworksCallbacks
            callback.wisefyFailure(MISSING_PARAMETER)
            null
        }.whenever(model).getSavedNetworks(eq(TEST_SSID_1), any())

        // When
        getSavedNetworks()

        // Then
        verifyRetrievedSavedNetworks()
        verify(view, times(1)).displayWiseFyFailure(MISSING_PARAMETER)
    }

    @Test fun searchForAccessPoint_accessPointFound() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[3] as SearchForAccessPointCallbacks
            callback.accessPointFound(ACCESS_POINT)
            null
        }.whenever(model).searchForAccessPoint(eq(TEST_SSID_1), eq(TEST_TIMEOUT), eq(true), any())

        // When
        searchForAccessPoint()

        // Then
        verifySearchedForAccessPoint()
        verify(view, times(1)).displayAccessPoint(ACCESS_POINT)
    }

    @Test fun searchForAccessPoint_accessPointNotFound() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[3] as SearchForAccessPointCallbacks
            callback.accessPointNotFound()
            null
        }.whenever(model).searchForAccessPoint(eq(TEST_SSID_1), eq(TEST_TIMEOUT), eq(true), any())

        // When
        searchForAccessPoint()

        // Then
        verifySearchedForAccessPoint()
        verify(view, times(1)).displayAccessPointNotFound()
    }

    @Test fun searchForAccessPoint_wisefyFailure() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[3] as SearchForAccessPointCallbacks
            callback.wisefyFailure(MISSING_PARAMETER)
            null
        }.whenever(model).searchForAccessPoint(eq(TEST_SSID_1), eq(TEST_TIMEOUT), eq(true), any())

        // When
        searchForAccessPoint()

        // Then
        verifySearchedForAccessPoint()
        verify(view, times(1)).displayWiseFyFailure(MISSING_PARAMETER)
    }

    @Test fun searchForAccessPoints_foundAccessPoints() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[2] as SearchForAccessPointsCallbacks
            callback.foundAccessPoints(listOf(ACCESS_POINT))
            null
        }.whenever(model).searchForAccessPoints(eq(TEST_SSID_1), eq(true), any())

        // When
        searchForAccessPoints()

        // Then
        verifySearchedForAccessPoints()
        verify(view, times(1)).displayAccessPoints(listOf(ACCESS_POINT))
    }

    @Test fun searchForAccessPoints_noAccessPointsFound() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[2] as SearchForAccessPointsCallbacks
            callback.noAccessPointsFound()
            null
        }.whenever(model).searchForAccessPoints(eq(TEST_SSID_1), eq(true), any())

        // When
        searchForAccessPoints()

        // Then
        verifySearchedForAccessPoints()
        verify(view, times(1)).displayNoAccessPointsFound()
    }

    @Test fun searchForAccessPoints_wisefyFailure() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[2] as SearchForAccessPointsCallbacks
            callback.wisefyFailure(MISSING_PARAMETER)
            null
        }.whenever(model).searchForAccessPoints(eq(TEST_SSID_1), eq(true), any())

        // When
        searchForAccessPoints()

        // Then
        verifySearchedForAccessPoints()
        verify(view, times(1)).displayWiseFyFailure(MISSING_PARAMETER)
    }

    @Test fun searchForSSID_accessPointFound() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[2] as SearchForSSIDCallbacks
            callback.ssidFound(TEST_SSID_1)
            null
        }.whenever(model).searchForSSID(eq(TEST_SSID_1), eq(TEST_TIMEOUT), any())

        // When
        searchForSSID()

        // Then
        verifySearchedForSSID()
        verify(view, times(1)).displaySSID(TEST_SSID_1)
    }

    @Test fun searchForSSID_ssidNotFound() {
        // Then
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[2] as SearchForSSIDCallbacks
            callback.ssidNotFound()
            null
        }.whenever(model).searchForSSID(eq(TEST_SSID_1), eq(TEST_TIMEOUT), any())

        // When
        searchForSSID()

        // Then
        verifySearchedForSSID()
        verify(view, times(1)).displaySSIDNotFound()
    }

    @Test fun searchForSSID_wisefyFailure() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[2] as SearchForSSIDCallbacks
            callback.wisefyFailure(MISSING_PARAMETER)
            null
        }.whenever(model).searchForSSID(eq(TEST_SSID_1), eq(TEST_TIMEOUT), any())

        // When
        searchForSSID()

        // Then
        verifySearchedForSSID()
        verify(view, times(1)).displayWiseFyFailure(MISSING_PARAMETER)
    }

    @Test fun searchForSSIDs_retrievedSSIDs() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[1] as SearchForSSIDsCallbacks
            callback.retrievedSSIDs(listOf(TEST_SSID_1))
            null
        }.whenever(model).searchForSSIDs(eq(TEST_SSID_1), any())

        // When
        searchForSSIDs()

        // Then
        verifySearchedForSSIDs()
        verify(view, times(1)).displaySSIDs(listOf(TEST_SSID_1))
    }

    @Test fun searchForSSIDs_noSSIDsFound() {
        // Then
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[1] as SearchForSSIDsCallbacks
            callback.noSSIDsFound()
            null
        }.whenever(model).searchForSSIDs(eq(TEST_SSID_1), any())

        // When
        searchForSSIDs()

        // Then
        verifySearchedForSSIDs()
        verify(view, times(1)).displayNoSSIDsFound()
    }

    @Test fun searchForSSIDs_wisefyFailure() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[1] as SearchForSSIDsCallbacks
            callback.wisefyFailure(MISSING_PARAMETER)
            null
        }.whenever(model).searchForSSIDs(eq(TEST_SSID_1), any())

        // When
        searchForSSIDs()

        // Then
        verifySearchedForSSIDs()
        verify(view, times(1)).displayWiseFyFailure(MISSING_PARAMETER)
    }

    /*
     * Call Helpers
     */

    private fun getSavedNetwork() {
        presenter.getSavedNetwork(TEST_SSID_1)
    }

    private fun getSavedNetworks() {
        presenter.getSavedNetworks(TEST_SSID_1)
    }

    private fun searchForAccessPoint() {
        presenter.searchForAccessPoint(TEST_SSID_1, TEST_TIMEOUT, true)
    }

    private fun searchForAccessPoints() {
        presenter.searchForAccessPoints(TEST_SSID_1, true)
    }

    private fun searchForSSID() {
        presenter.searchForSSID(TEST_SSID_1, TEST_TIMEOUT)
    }

    private fun searchForSSIDs() {
        presenter.searchForSSIDs(TEST_SSID_1)
    }

    /*
     * Verification Helpers
     */

    private fun verifyRetrievedSavedNetwork() {
        verify(model, times(1)).getSavedNetwork(eq(TEST_SSID_1), any())
    }

    private fun verifyRetrievedSavedNetworks() {
        verify(model, times(1)).getSavedNetworks(eq(TEST_SSID_1), any())
    }

    private fun verifySearchedForAccessPoint() {
        verify(model, times(1)).searchForAccessPoint(
            eq(TEST_SSID_1),
            eq(TEST_TIMEOUT),
            eq(true),
            any()
        )
    }

    private fun verifySearchedForAccessPoints() {
        verify(model, times(1)).searchForAccessPoints(
            eq(TEST_SSID_1),
            eq(true),
            any()
        )
    }

    private fun verifySearchedForSSID() {
        verify(model, times(1)).searchForSSID(
            eq(TEST_SSID_1),
            eq(TEST_TIMEOUT),
            any()
        )
    }

    private fun verifySearchedForSSIDs() {
        verify(model, times(1)).searchForSSIDs(
            eq(TEST_SSID_1),
            any()
        )
    }
}
