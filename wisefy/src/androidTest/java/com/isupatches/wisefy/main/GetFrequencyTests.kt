package com.isupatches.wisefy.main

import android.net.wifi.WifiInfo
import android.os.Build

import com.isupatches.wisefy.BaseAndroidJUnit4TestClass
import com.isupatches.wisefy.TEST_NETWORK_FREQUENCY_24GHZ
import com.isupatches.wisefy.VERIFICATION_FAILURE_TIMEOUT
import com.isupatches.wisefy.VERIFICATION_SUCCESS_TIMEOUT
import com.isupatches.wisefy.callbacks.GetFrequencyCallbacks
import com.isupatches.wisefy.constants.MISSING_PARAMETER

import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Assume.assumeTrue
import org.junit.Before
import org.junit.Test

import org.mockito.Mockito.after
import org.mockito.Mockito.mock
import org.mockito.Mockito.timeout
import org.mockito.Mockito.verify

/**
 * Tests the ability to retrieve a network's frequency.
 *
 * @author Patches
 */
internal class GetFrequencyTests : BaseAndroidJUnit4TestClass() {

    @Before fun setup() {
        assumeTrue(
        "Can only run on API Level 21 or newer",
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
        )
    }

    @Test fun sync_getFrequency_currentNetwork_failure() {
        mockWiseFyPrechecksUtil.getCurrentNetwork_success()
        mockNetworkUtil.currentNetwork_null()
        assertEquals(null, wisefy.getFrequency())
        verificationUtil.triedToGetCurrentNetwork()
    }

    @Test fun sync_getFrequency_currentNetwork_success() {
        mockWiseFyPrechecksUtil.getCurrentNetwork_success()
        val wifiInfo = mockNetworkUtil.networkWithFrequency(TEST_NETWORK_FREQUENCY_24GHZ)
        val frequency = wisefy.getFrequency()
        if (frequency != null) {
            assertEquals(TEST_NETWORK_FREQUENCY_24GHZ.toLong(), frequency.toLong())
            verificationUtil.triedToGetCurrentNetwork()
            verify(wifiInfo, timeout(VERIFICATION_SUCCESS_TIMEOUT)).frequency
        } else {
            fail()
        }
    }

    @Test fun async_getFrequency_currentNetwork_failure() {
        mockWiseFyPrechecksUtil.getCurrentNetwork_success()
        mockNetworkUtil.currentNetwork_null()
        val mockCallbacks = mock(GetFrequencyCallbacks::class.java)
        wisefy.getFrequency(mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).failureGettingFrequency()
        verificationUtil.triedToGetCurrentNetwork()
    }

    @Test fun async_getFrequency_currentNetwork_failure_nullCallback() {
        mockWiseFyPrechecksUtil.getCurrentNetwork_success()
        mockNetworkUtil.currentNetwork_null()
        nullCallbackUtil.callGetFrequency()
        verificationUtil.triedToGetCurrentNetwork()
    }

    @Test fun async_getFrequency_currentNetwork_success() {
        mockWiseFyPrechecksUtil.getCurrentNetwork_success()
        val mockWifiInfo = mockNetworkUtil.networkWithFrequency(TEST_NETWORK_FREQUENCY_24GHZ)
        val mockCallbacks = mock(GetFrequencyCallbacks::class.java)
        wisefy.getFrequency(mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).retrievedFrequency(TEST_NETWORK_FREQUENCY_24GHZ)
        verificationUtil.triedToGetCurrentNetwork()
        verify(mockWifiInfo, timeout(VERIFICATION_SUCCESS_TIMEOUT)).frequency
    }

    @Test fun async_getFrequency_currentNetwork_success_nullCallback() {
        mockWiseFyPrechecksUtil.getCurrentNetwork_success()
        nullCallbackUtil.callGetFrequency()
        verificationUtil.triedToGetCurrentNetwork()
    }

    @Test fun sync_getFrequency_networkProvided_failure() {
        mockNetworkUtil.currentNetwork_null()
        assertEquals(null, wisefy.getFrequency(null as? WifiInfo?))
    }

    @Test fun sync_getFrequency_networkProvided_success() {
        val mockWifiInfo = mockNetworkUtil.networkWithFrequency(TEST_NETWORK_FREQUENCY_24GHZ)
        val frequency = wisefy.getFrequency(mockWifiInfo)
        if (frequency != null) {
            assertEquals(TEST_NETWORK_FREQUENCY_24GHZ.toLong(), frequency.toLong())
        } else {
            fail()
        }
    }

    @Test fun async_getFrequency_networkProvided_failure() {
        mockNetworkUtil.currentNetwork_null()
        val mockCallbacks = mock(GetFrequencyCallbacks::class.java)
        wisefy.getFrequency(null, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).wisefyFailure(MISSING_PARAMETER)
    }

    @Test fun async_getFrequency_networkProvided_failure_nullCallback() {
        mockNetworkUtil.currentNetwork_null()
        nullCallbackUtil.callGetFrequency_networkProvided(null)
    }

    @Test fun async_getFrequency_networkProvided_success() {
        val mockWifiInfo = mockNetworkUtil.networkWithFrequency(TEST_NETWORK_FREQUENCY_24GHZ)
        val mockCallbacks = mock(GetFrequencyCallbacks::class.java)
        wisefy.getFrequency(mockWifiInfo, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).retrievedFrequency(TEST_NETWORK_FREQUENCY_24GHZ)
        verify(mockWifiInfo, timeout(VERIFICATION_SUCCESS_TIMEOUT)).frequency
    }

    @Test fun async_getFrequency_networkProvided_success_nullCallback() {
        val mockWifiInfo = mockNetworkUtil.networkWithFrequency(TEST_NETWORK_FREQUENCY_24GHZ)
        nullCallbackUtil.callGetFrequency_networkProvided(mockWifiInfo)
        verify(mockWifiInfo, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).frequency
    }
}
