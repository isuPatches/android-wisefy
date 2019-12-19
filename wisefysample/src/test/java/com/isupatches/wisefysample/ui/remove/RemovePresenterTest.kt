package com.isupatches.wisefysample.ui.remove

import com.isupatches.wisefy.callbacks.RemoveNetworkCallbacks
import com.isupatches.wisefy.constants.MISSING_PARAMETER
import com.isupatches.wisefysample.TEST_SSID_1
import com.isupatches.wisefysample.TestRxSchedulersProvider
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.doAnswer

internal class RemovePresenterTest {

    private val view = mock<RemoveNetworkMvp.View>()
    private val model = mock<RemoveNetworkMvp.Model>()

    private val presenter = RemoveNetworkPresenter(model, TestRxSchedulersProvider())

    @Before fun setUp() {
        presenter.attachView(view)
    }

    @After fun tearDown() {
        presenter.detachView()
    }

    @Test fun removeNetwork_networkRemoved() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[1] as RemoveNetworkCallbacks
            callback.networkRemoved()
        }.whenever(model).removeNetwork(eq(TEST_SSID_1), any())

        // When
        removeNetwork()

        // Then
        verifyTriedToRemoveNetwork()
        verify(view, times(1)).displayNetworkRemoved()
    }

    @Test fun removeNetwork_failureRemovingNetwork() {
        // Then
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[1] as RemoveNetworkCallbacks
            callback.failureRemovingNetwork()
        }.whenever(model).removeNetwork(eq(TEST_SSID_1), any())

        // When
        removeNetwork()

        // Then
        verifyTriedToRemoveNetwork()
        verify(view, times(1)).displayFailureRemovingNetwork()
    }

    @Test fun removeNetwork_networkNotFoundToRemove() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[1] as RemoveNetworkCallbacks
            callback.networkNotFoundToRemove()
        }.whenever(model).removeNetwork(eq(TEST_SSID_1), any())

        // When
        removeNetwork()

        // Then
        verifyTriedToRemoveNetwork()
        verify(view, times(1)).displayNetworkNotFoundToRemove()
    }

    @Test fun removeNetwork_wisefyFailure() {
        // Given
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[1] as RemoveNetworkCallbacks
            callback.wisefyFailure(MISSING_PARAMETER)
        }.whenever(model).removeNetwork(eq(TEST_SSID_1), any())

        // When
        removeNetwork()

        // Then
        verifyTriedToRemoveNetwork()
        verify(view, times(1)).displayWiseFyFailure(MISSING_PARAMETER)
    }

    /*
     * Call Helpers
     */

    private fun removeNetwork() {
        presenter.removeNetwork(TEST_SSID_1)
    }

    /*
     * Verification Helpers
     */

    private fun verifyTriedToRemoveNetwork() {
        verify(model, times(1)).removeNetwork(eq(TEST_SSID_1), any())
    }
}
