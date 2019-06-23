package com.isupatches.wisefy

import com.isupatches.wisefy.WiseFy.Companion.WIFI_MANAGER_FAILURE
import com.isupatches.wisefy.callbacks.AddNetworkCallbacks
import com.isupatches.wisefy.constants.MISSING_PARAMETER
import com.isupatches.wisefy.internal.base.BaseInstrumentationTest

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

import org.mockito.Mockito.mock
import org.mockito.Mockito.timeout
import org.mockito.Mockito.verify

/**
 * Tests the ability to add open networks.
 *
 * @author Patches
 * @since 3.0
 */
internal class AddOpenNetworkTests : BaseInstrumentationTest() {

    @Test fun sync_failure_prechecks() {
        mockWiseFyPrechecksUtil.addNetwork_failure()
        assertEquals(MISSING_PARAMETER, wisefy.addOpenNetwork(OPEN_NETWORK_SSID))
        verificationUtil.didNoTryToAddNetwork()
    }

    @Test fun sync_failure() {
        mockWiseFyPrechecksUtil.addNetwork_success()
        mockNetworkUtil.addNetwork_failure()
        assertEquals(WIFI_MANAGER_FAILURE, wisefy.addOpenNetwork(OPEN_NETWORK_SSID))
        verificationUtil.triedToAddNetwork()
    }

    @Test fun sync_success() {
        mockWiseFyPrechecksUtil.addNetwork_success()
        mockNetworkUtil.addNetwork_success()
        assertNotEquals(WIFI_MANAGER_FAILURE, wisefy.addOpenNetwork(OPEN_NETWORK_SSID))
        verificationUtil.triedToAddNetwork()
    }

    @Test fun async_failure_prechecks() {
        mockWiseFyPrechecksUtil.addNetwork_failure()
        val mockCallbacks = mock(AddNetworkCallbacks::class.java)
        wisefy.addOpenNetwork(OPEN_NETWORK_SSID, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).wisefyFailure(MISSING_PARAMETER)
        verificationUtil.didNoTryToAddNetwork()
    }

    @Test fun async_failure_prechecks_nullCallback() {
        mockWiseFyPrechecksUtil.addNetwork_failure()
        nullCallbackUtil.callAddOpenNetwork(OPEN_NETWORK_SSID)
        verificationUtil.didNoTryToAddNetwork()
    }

    @Test fun async_failure() {
        mockWiseFyPrechecksUtil.addNetwork_success()
        mockNetworkUtil.addNetwork_failure()
        val mockCallbacks = mock(AddNetworkCallbacks::class.java)
        wisefy.addOpenNetwork(OPEN_NETWORK_SSID, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).failureAddingNetwork(WIFI_MANAGER_FAILURE)
        verificationUtil.triedToAddNetwork()
    }

    @Test fun async_failure_nullCallback() {
        mockWiseFyPrechecksUtil.addNetwork_success()
        mockNetworkUtil.addNetwork_failure()
        nullCallbackUtil.callAddOpenNetwork(OPEN_NETWORK_SSID)
        verificationUtil.triedToAddNetwork()
    }

    @Test fun async_success() {
        mockWiseFyPrechecksUtil.addNetwork_success()
        mockNetworkUtil.addNetwork_success()
        val mockCallbacks = mock(AddNetworkCallbacks::class.java)
        wisefy.addOpenNetwork(OPEN_NETWORK_SSID, mockCallbacks)
        verifyNetworkAdded(mockCallbacks)
        verificationUtil.triedToAddNetwork()
    }

    @Test fun async_success_nullCallback() {
        mockWiseFyPrechecksUtil.addNetwork_success()
        mockNetworkUtil.addNetwork_success()
        nullCallbackUtil.callAddOpenNetwork(OPEN_NETWORK_SSID)
        verificationUtil.triedToAddNetwork()
    }
}
