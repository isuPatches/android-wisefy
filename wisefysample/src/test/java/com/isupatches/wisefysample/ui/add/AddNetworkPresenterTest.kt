package com.isupatches.wisefysample.ui.add

import android.net.wifi.WifiConfiguration
import com.isupatches.wisefy.callbacks.AddNetworkCallbacks
import com.isupatches.wisefy.constants.MISSING_PARAMETER
import com.isupatches.wisefysample.BAD_WIFI_MANAGER_RETURN
import com.isupatches.wisefysample.TEST_PASSWORD_1
import com.isupatches.wisefysample.TEST_SSID_1
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

internal class AddNetworkPresenterTest {

    private val view = mock<AddNetworkMvp.View>()
    private val model = mock<AddNetworkMvp.Model>()

    private val presenter = AddNetworkPresenter(model, TestRxSchedulersProvider())

    companion object {
        private const val NETWORK_ID = 1
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
    fun addOpenNetwork_networkAdded() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[1] as AddNetworkCallbacks
            callback.networkAdded(NETWORK_ID, SAVED_NETWORK)
        }.whenever(model).addOpenNetwork(eq(TEST_SSID_1), any())

        // When
        addOpenNetwork()

        // Then
        verifyTriedToAddOpenNetwork()
        verify(view, times(1)).displayNetworkAdded(NETWORK_ID, SAVED_NETWORK)
    }

    @Test
    fun addOpenNetwork_failureAddingNetwork() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[1] as AddNetworkCallbacks
            callback.failureAddingNetwork(BAD_WIFI_MANAGER_RETURN)
        }.whenever(model).addOpenNetwork(eq(TEST_SSID_1), any())

        // When
        addOpenNetwork()

        // Then
        verifyTriedToAddOpenNetwork()
        verify(view, times(1)).displayFailureAddingNetwork(BAD_WIFI_MANAGER_RETURN)
    }

    @Test
    fun addOpenNetwork_wisefyFailure() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[1] as AddNetworkCallbacks
            callback.wisefyFailure(MISSING_PARAMETER)
        }.whenever(model).addOpenNetwork(eq(TEST_SSID_1), any())

        // When
        addOpenNetwork()

        // Then
        verifyTriedToAddOpenNetwork()
        verify(view, times(1)).displayWiseFyFailure(MISSING_PARAMETER)
    }

    @Test
    fun addWEPNetwork_networkAdded() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[2] as AddNetworkCallbacks
            callback.networkAdded(NETWORK_ID, SAVED_NETWORK)
        }.whenever(model).addWEPNetwork(eq(TEST_SSID_1), eq(TEST_PASSWORD_1), any())

        // When
        addWEPNetwork()

        // Then
        verifyTriedToAddWEPNetwork()
        verify(view, times(1)).displayNetworkAdded(NETWORK_ID, SAVED_NETWORK)
    }

    @Test
    fun addWEPNetwork_failureAddingNetwork() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[2] as AddNetworkCallbacks
            callback.failureAddingNetwork(BAD_WIFI_MANAGER_RETURN)
        }.whenever(model).addWEPNetwork(eq(TEST_SSID_1), eq(TEST_PASSWORD_1), any())

        // When
        addWEPNetwork()

        // Then
        verifyTriedToAddWEPNetwork()
        verify(view, times(1)).displayFailureAddingNetwork(BAD_WIFI_MANAGER_RETURN)
    }

    @Test
    fun addWEPNetwork_wisefyFailure() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[2] as AddNetworkCallbacks
            callback.wisefyFailure(MISSING_PARAMETER)
        }.whenever(model).addWEPNetwork(eq(TEST_SSID_1), eq(TEST_PASSWORD_1), any())

        // When
        addWEPNetwork()

        // Then
        verifyTriedToAddWEPNetwork()
        verify(view, times(1)).displayWiseFyFailure(MISSING_PARAMETER)
    }

    @Test
    fun addWPA2Network_networkAdded() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[2] as AddNetworkCallbacks
            callback.networkAdded(NETWORK_ID, SAVED_NETWORK)
        }.whenever(model).addWPA2Network(eq(TEST_SSID_1), eq(TEST_PASSWORD_1), any())

        // When
        addWPA2Network()

        // Then
        verifyTriedToAddWPA2Network()
        verify(view, times(1)).displayNetworkAdded(NETWORK_ID, SAVED_NETWORK)
    }

    @Test
    fun addWPA2Network_failureAddingNetwork() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[2] as AddNetworkCallbacks
            callback.failureAddingNetwork(BAD_WIFI_MANAGER_RETURN)
        }.whenever(model).addWPA2Network(eq(TEST_SSID_1), eq(TEST_PASSWORD_1), any())

        // When
        addWPA2Network()

        // Then
        verifyTriedToAddWPA2Network()
        verify(view, times(1)).displayFailureAddingNetwork(BAD_WIFI_MANAGER_RETURN)
    }

    @Test
    fun addWPA2Network_wisefyFailure() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[2] as AddNetworkCallbacks
            callback.wisefyFailure(MISSING_PARAMETER)
        }.whenever(model).addWPA2Network(eq(TEST_SSID_1), eq(TEST_PASSWORD_1), any())

        // When
        addWPA2Network()

        // Then
        verifyTriedToAddWPA2Network()
        verify(view, times(1)).displayWiseFyFailure(MISSING_PARAMETER)
    }

    /*
     * Call Helpers
     */

    private fun addOpenNetwork() {
        presenter.addOpenNetwork(TEST_SSID_1)
    }

    private fun addWEPNetwork() {
        presenter.addWEPNetwork(TEST_SSID_1, TEST_PASSWORD_1)
    }

    private fun addWPA2Network() {
        presenter.addWPA2Network(TEST_SSID_1, TEST_PASSWORD_1)
    }

    /*
     * Verification Helpers
     */

    private fun verifyTriedToAddOpenNetwork() {
        verify(model, times(1)).addOpenNetwork(eq(TEST_SSID_1), any())
    }

    private fun verifyTriedToAddWEPNetwork() {
        verify(model, times(1)).addWEPNetwork(
            eq(TEST_SSID_1),
            eq(TEST_PASSWORD_1),
            any()
        )
    }

    private fun verifyTriedToAddWPA2Network() {
        verify(model, times(1)).addWPA2Network(
            eq(TEST_SSID_1),
            eq(TEST_PASSWORD_1),
            any()
        )
    }
}
