package com.isupatches.wisefysample.ui.search

import android.net.wifi.ScanResult
import android.net.wifi.WifiConfiguration
import com.isupatches.wisefy.callbacks.SearchForAccessPointCallbacks
import com.isupatches.wisefy.callbacks.SearchForAccessPointsCallbacks
import com.isupatches.wisefy.callbacks.SearchForSSIDCallbacks
import com.isupatches.wisefy.callbacks.SearchForSSIDsCallbacks
import com.isupatches.wisefy.callbacks.SearchForSavedNetworkCallbacks
import com.isupatches.wisefy.callbacks.SearchForSavedNetworksCallbacks
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

    @Before
    fun setUp() {
        presenter.attachView(view)
    }

    @After
    fun tearDown() {
        presenter.detachView()
    }

    @Test
    fun searchForAccessPoint_accessPointFound() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[3] as SearchForAccessPointCallbacks
            callback.accessPointFound(ACCESS_POINT)
        }.whenever(model).searchForAccessPoint(eq(TEST_SSID_1), eq(TEST_TIMEOUT), eq(true), any())

        // When
        searchForAccessPoint()

        // Then
        verifySearchedForAccessPoint()
        verify(view, times(1)).displayAccessPoint(ACCESS_POINT)
    }

    @Test
    fun searchForAccessPoint_accessPointNotFound() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[3] as SearchForAccessPointCallbacks
            callback.accessPointNotFound()
        }.whenever(model).searchForAccessPoint(eq(TEST_SSID_1), eq(TEST_TIMEOUT), eq(true), any())

        // When
        searchForAccessPoint()

        // Then
        verifySearchedForAccessPoint()
        verify(view, times(1)).displayAccessPointNotFound()
    }

    @Test
    fun searchForAccessPoint_wisefyFailure() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[3] as SearchForAccessPointCallbacks
            callback.wisefyFailure(MISSING_PARAMETER)
        }.whenever(model).searchForAccessPoint(eq(TEST_SSID_1), eq(TEST_TIMEOUT), eq(true), any())

        // When
        searchForAccessPoint()

        // Then
        verifySearchedForAccessPoint()
        verify(view, times(1)).displayWiseFyFailure(MISSING_PARAMETER)
    }

    @Test
    fun searchForAccessPoints_foundAccessPoints() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[2] as SearchForAccessPointsCallbacks
            callback.foundAccessPoints(listOf(ACCESS_POINT))
        }.whenever(model).searchForAccessPoints(eq(TEST_SSID_1), eq(true), any())

        // When
        searchForAccessPoints()

        // Then
        verifySearchedForAccessPoints()
        verify(view, times(1)).displayAccessPoints(listOf(ACCESS_POINT))
    }

    @Test
    fun searchForAccessPoints_noAccessPointsFound() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[2] as SearchForAccessPointsCallbacks
            callback.noAccessPointsFound()
        }.whenever(model).searchForAccessPoints(eq(TEST_SSID_1), eq(true), any())

        // When
        searchForAccessPoints()

        // Then
        verifySearchedForAccessPoints()
        verify(view, times(1)).displayNoAccessPointsFound()
    }

    @Test
    fun searchForAccessPoints_wisefyFailure() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[2] as SearchForAccessPointsCallbacks
            callback.wisefyFailure(MISSING_PARAMETER)
        }.whenever(model).searchForAccessPoints(eq(TEST_SSID_1), eq(true), any())

        // When
        searchForAccessPoints()

        // Then
        verifySearchedForAccessPoints()
        verify(view, times(1)).displayWiseFyFailure(MISSING_PARAMETER)
    }

    @Test
    fun searchForSavedNetwork_retrievedSavedNetwork() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[1] as SearchForSavedNetworkCallbacks
            callback.retrievedSavedNetwork(SAVED_NETWORK)
        }.whenever(model).searchForSavedNetwork(eq(TEST_SSID_1), any())

        // When
        searchForSavedNetwork()

        // Then
        verifySearchedForSavedNetwork()
        verify(view, times(1)).displaySavedNetwork(SAVED_NETWORK)
    }

    @Test
    fun searchForSavedNetwork_savedNetworkNotFound() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[1] as SearchForSavedNetworkCallbacks
            callback.savedNetworkNotFound()
        }.whenever(model).searchForSavedNetwork(eq(TEST_SSID_1), any())

        // When
        searchForSavedNetwork()

        // Then
        verifySearchedForSavedNetwork()
        verify(view, times(1)).displaySavedNetworkNotFound()
    }

    @Test
    fun searchForSavedNetwork_wisefyFailure() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[1] as SearchForSavedNetworkCallbacks
            callback.wisefyFailure(MISSING_PARAMETER)
        }.whenever(model).searchForSavedNetwork(eq(TEST_SSID_1), any())

        // When
        searchForSavedNetwork()

        // Then
        verifySearchedForSavedNetwork()
        verify(view, times(1)).displayWiseFyFailure(MISSING_PARAMETER)
    }

    @Test
    fun searchForSavedNetworks_retrievedSavedNetworks() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[1] as SearchForSavedNetworksCallbacks
            callback.retrievedSavedNetworks(listOf(SAVED_NETWORK))
        }.whenever(model).searchForSavedNetworks(eq(TEST_SSID_1), any())

        // When
        searchForSavedNetworks()

        // Then
        verifySearchedForSavedNetworks()
        verify(view, times(1)).displaySavedNetworks(listOf(SAVED_NETWORK))
    }

    @Test
    fun searchForSavedNetworks_noSavedNetworksFound() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[1] as SearchForSavedNetworksCallbacks
            callback.noSavedNetworksFound()
        }.whenever(model).searchForSavedNetworks(eq(TEST_SSID_1), any())

        // When
        searchForSavedNetworks()

        // Then
        verifySearchedForSavedNetworks()
        verify(view, times(1)).displayNoSavedNetworksFound()
    }

    @Test
    fun searchForSavedNetworks_wisefyFailure() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[1] as SearchForSavedNetworksCallbacks
            callback.wisefyFailure(MISSING_PARAMETER)
        }.whenever(model).searchForSavedNetworks(eq(TEST_SSID_1), any())

        // When
        searchForSavedNetworks()

        // Then
        verifySearchedForSavedNetworks()
        verify(view, times(1)).displayWiseFyFailure(MISSING_PARAMETER)
    }

    @Test
    fun searchForSSID_ssidFound() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[2] as SearchForSSIDCallbacks
            callback.ssidFound(TEST_SSID_1)
        }.whenever(model).searchForSSID(eq(TEST_SSID_1), eq(TEST_TIMEOUT), any())

        // When
        searchForSSID()

        // Then
        verifySearchedForSSID()
        verify(view, times(1)).displaySSID(TEST_SSID_1)
    }

    @Test
    fun searchForSSID_ssidNotFound() {
        // Then
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[2] as SearchForSSIDCallbacks
            callback.ssidNotFound()
        }.whenever(model).searchForSSID(eq(TEST_SSID_1), eq(TEST_TIMEOUT), any())

        // When
        searchForSSID()

        // Then
        verifySearchedForSSID()
        verify(view, times(1)).displaySSIDNotFound()
    }

    @Test
    fun searchForSSID_wisefyFailure() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[2] as SearchForSSIDCallbacks
            callback.wisefyFailure(MISSING_PARAMETER)
        }.whenever(model).searchForSSID(eq(TEST_SSID_1), eq(TEST_TIMEOUT), any())

        // When
        searchForSSID()

        // Then
        verifySearchedForSSID()
        verify(view, times(1)).displayWiseFyFailure(MISSING_PARAMETER)
    }

    @Test
    fun searchForSSIDs_retrievedSSIDs() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[1] as SearchForSSIDsCallbacks
            callback.retrievedSSIDs(listOf(TEST_SSID_1))
        }.whenever(model).searchForSSIDs(eq(TEST_SSID_1), any())

        // When
        searchForSSIDs()

        // Then
        verifySearchedForSSIDs()
        verify(view, times(1)).displaySSIDs(listOf(TEST_SSID_1))
    }

    @Test
    fun searchForSSIDs_noSSIDsFound() {
        // Then
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[1] as SearchForSSIDsCallbacks
            callback.noSSIDsFound()
        }.whenever(model).searchForSSIDs(eq(TEST_SSID_1), any())

        // When
        searchForSSIDs()

        // Then
        verifySearchedForSSIDs()
        verify(view, times(1)).displayNoSSIDsFound()
    }

    @Test
    fun searchForSSIDs_wisefyFailure() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[1] as SearchForSSIDsCallbacks
            callback.wisefyFailure(MISSING_PARAMETER)
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

    private fun searchForAccessPoint() {
        presenter.searchForAccessPoint(TEST_SSID_1, TEST_TIMEOUT, true)
    }

    private fun searchForAccessPoints() {
        presenter.searchForAccessPoints(TEST_SSID_1, true)
    }

    private fun searchForSavedNetwork() {
        presenter.searchForSavedNetwork(TEST_SSID_1)
    }

    private fun searchForSavedNetworks() {
        presenter.searchForSavedNetworks(TEST_SSID_1)
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

    private fun verifySearchedForSavedNetwork() {
        verify(model, times(1)).searchForSavedNetwork(eq(TEST_SSID_1), any())
    }

    private fun verifySearchedForSavedNetworks() {
        verify(model, times(1)).searchForSavedNetworks(eq(TEST_SSID_1), any())
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
