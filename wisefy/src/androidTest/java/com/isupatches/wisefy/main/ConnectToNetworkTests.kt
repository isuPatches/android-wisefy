package com.isupatches.wisefy.main

import com.isupatches.wisefy.BaseAndroidJUnit4TestClass
import com.isupatches.wisefy.TEST_SSID
import com.isupatches.wisefy.TEST_TIMEOUT
import com.isupatches.wisefy.VERIFICATION_SUCCESS_TIMEOUT
import com.isupatches.wisefy.callbacks.ConnectToNetworkCallbacks
import com.isupatches.wisefy.constants.MISSING_PARAMETER

import org.junit.Assert.assertEquals
import org.junit.Test

import org.mockito.Mockito.mock
import org.mockito.Mockito.timeout
import org.mockito.Mockito.verify

/**
 * Tests the ability to connect to a network.
 *
 * @author Patches
 */
internal class ConnectToNetworkTests : BaseAndroidJUnit4TestClass() {

    @Test fun sync_failure_prechecks() {
        mockWiseFyPrechecksUtil.connectToNetwork_failure()
        assertEquals(false, wiseFy.connectToNetwork(TEST_SSID, TEST_TIMEOUT))
        verificationUtil.didNotTryToConnectToNetwork()
    }

    @Test fun sync_failure_noSavedNetwork() {
        mockWiseFyPrechecksUtil.connectToNetwork_success()
        mockWiseFySearchUtil.findSavedNetworkByRegex_null()
        assertEquals(false, wiseFy.connectToNetwork(TEST_SSID, TEST_TIMEOUT))
        verificationUtil.didNotTryToConnectToNetwork()
    }

    @Test fun sync_failure() {
        mockWiseFyPrechecksUtil.connectToNetwork_success()
        mockWiseFySearchUtil.findSavedNetworkByRegex_success()
        mockWiseFyConnectionUtil.waitToConnectToSSID(false)
        assertEquals(false, wiseFy.connectToNetwork(TEST_SSID, TEST_TIMEOUT))
        verificationUtil.triedToConnectToNetwork()
    }

    @Test fun sync_success() {
        mockWiseFyPrechecksUtil.connectToNetwork_success()
        mockWiseFySearchUtil.findSavedNetworkByRegex_success()
        mockWiseFyConnectionUtil.waitToConnectToSSID(true)
        assertEquals(true, wiseFy.connectToNetwork(TEST_SSID, TEST_TIMEOUT))
        verificationUtil.triedToConnectToNetwork()
    }

    @Test fun async_failure_prechecks() {
        mockWiseFyPrechecksUtil.connectToNetwork_failure()
        val mockCallbacks = mock(ConnectToNetworkCallbacks::class.java)
        wiseFy.connectToNetwork(TEST_SSID, TEST_TIMEOUT, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).wisefyFailure(MISSING_PARAMETER)
        verificationUtil.didNotTryToConnectToNetwork()
    }

    @Test fun async_failure_prechecks_nullCallback() {
        mockWiseFyPrechecksUtil.connectToNetwork_success()
        mockWiseFyPrechecksUtil.connectToNetwork_failure()
        nullCallbackUtil.callConnectToNetwork(TEST_SSID)
        verificationUtil.didNotTryToConnectToNetwork()
    }

    @Test fun async_failure_noSavedNetwork() {
        mockWiseFyPrechecksUtil.connectToNetwork_success()
        mockWiseFySearchUtil.findSavedNetworkByRegex_null()
        val mockCallbacks = mock(ConnectToNetworkCallbacks::class.java)
        wiseFy.connectToNetwork(TEST_SSID, TEST_TIMEOUT, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).networkNotFoundToConnectTo()
        verificationUtil.didNotTryToConnectToNetwork()
    }

    @Test fun async_failure_noSavedNetwork_nullCallback() {
        mockWiseFyPrechecksUtil.connectToNetwork_success()
        mockWiseFySearchUtil.findSavedNetworkByRegex_null()
        nullCallbackUtil.callConnectToNetwork(TEST_SSID)
        verificationUtil.didNotTryToConnectToNetwork()
    }

    @Test fun async_failure() {
        mockWiseFyPrechecksUtil.connectToNetwork_success()
        mockWiseFySearchUtil.findSavedNetworkByRegex_success()
        mockWiseFyConnectionUtil.waitToConnectToSSID(false)
        val mockCallbacks = mock(ConnectToNetworkCallbacks::class.java)
        wiseFy.connectToNetwork(TEST_SSID, TEST_TIMEOUT, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).failureConnectingToNetwork()
        verificationUtil.triedToConnectToNetwork()
    }

    @Test fun async_failure_nullCallback() {
        mockWiseFyPrechecksUtil.connectToNetwork_success()
        mockWiseFySearchUtil.findSavedNetworkByRegex_success()
        mockWiseFyConnectionUtil.waitToConnectToSSID(false)
        nullCallbackUtil.callConnectToNetwork(TEST_SSID)
        verificationUtil.triedToConnectToNetwork()
    }

    @Test fun async_success() {
        mockWiseFyPrechecksUtil.connectToNetwork_success()
        mockWiseFySearchUtil.findSavedNetworkByRegex_success()
        mockWiseFyConnectionUtil.waitToConnectToSSID(true)
        val mockCallbacks = mock(ConnectToNetworkCallbacks::class.java)
        wiseFy.connectToNetwork(TEST_SSID, TEST_TIMEOUT, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).connectedToNetwork()
        verificationUtil.triedToConnectToNetwork()
    }

    @Test fun async_success_nullCallback() {
        mockWiseFyPrechecksUtil.connectToNetwork_success()
        mockWiseFySearchUtil.findSavedNetworkByRegex_success()
        mockWiseFyConnectionUtil.waitToConnectToSSID(true)
        nullCallbackUtil.callConnectToNetwork(TEST_SSID)
        verificationUtil.triedToConnectToNetwork()
    }
}
