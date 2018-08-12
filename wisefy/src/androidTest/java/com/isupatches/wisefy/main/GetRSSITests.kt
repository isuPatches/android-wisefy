package com.isupatches.wisefy.main

import org.junit.Assert.assertEquals
import org.junit.Assert.fail

import org.mockito.Mockito.mock
import org.mockito.Mockito.timeout
import org.mockito.Mockito.verify

import com.isupatches.wisefy.BaseAndroidJUnit4TestClass
import com.isupatches.wisefy.TEST_RSSI_LEVEL
import com.isupatches.wisefy.TEST_SSID
import com.isupatches.wisefy.TEST_TIMEOUT
import com.isupatches.wisefy.VERIFICATION_SUCCESS_TIMEOUT
import com.isupatches.wisefy.callbacks.GetRSSICallbacks
import com.isupatches.wisefy.constants.MISSING_PARAMETER

import org.junit.Test

/**
 * Tests the ability to retrieve the RSSI for a nearby access point.
 *
 * @author Patches
 */
internal class GetRSSITests : BaseAndroidJUnit4TestClass() {

    @Test fun sync_failure_prechecks_takeHighest_false() {
        mockWiseFyPrechecksUtil.getRSSI_failure()
        assertEquals(null, wiseFy.getRSSI(TEST_SSID, false, TEST_TIMEOUT))
    }

    @Test fun sync_failure_prechecks_takeHighest_true() {
        mockWiseFyPrechecksUtil.getRSSI_failure()
        assertEquals(null, wiseFy.getRSSI(TEST_SSID, true, TEST_TIMEOUT))
    }

    @Test fun sync_failure_noNetworkFound_takeHighest_false() {
        mockWiseFyPrechecksUtil.getRSSI_success()
        mockWiseFySearchUtil.findAccessPointByRegex_null()
        assertEquals(null, wiseFy.getRSSI(TEST_SSID, false, TEST_TIMEOUT))
    }

    @Test fun sync_failure_noNetworkFound_takeHighest_true() {
        mockWiseFyPrechecksUtil.getRSSI_success()
        mockWiseFySearchUtil.findAccessPointByRegex_null()
        assertEquals(null, wiseFy.getRSSI(TEST_SSID, true, TEST_TIMEOUT))
    }

    @Test fun sync_success_takeHighest_false() {
        mockWiseFyPrechecksUtil.getRSSI_success()
        mockWiseFySearchUtil.findAccessPointByRegex_success()

        val rssi = wiseFy.getRSSI(TEST_SSID, false, TEST_TIMEOUT)
        if (rssi != null) {
            assertEquals(TEST_RSSI_LEVEL.toLong(), rssi.toLong())
        } else {
            fail()
        }
    }

    @Test fun sync_success_takeHighest_true() {
        mockWiseFyPrechecksUtil.getRSSI_success()
        mockWiseFySearchUtil.findAccessPointByRegex_success()

        val rssi = wiseFy.getRSSI(TEST_SSID, true, TEST_TIMEOUT)
        if (rssi != null) {
            assertEquals(TEST_RSSI_LEVEL.toLong(), rssi.toLong())
        } else {
            fail()
        }
    }

    @Test fun async_failure_prechecks_takeHighest_false() {
        mockWiseFyPrechecksUtil.getRSSI_failure()
        val mockCallbacks = mock(GetRSSICallbacks::class.java)
        wiseFy.getRSSI(TEST_SSID, false, TEST_TIMEOUT, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).wisefyFailure(MISSING_PARAMETER)
    }

    @Test fun async_failure_prechecks_takeHighest_false_nullCallback() {
        mockWiseFyPrechecksUtil.getRSSI_failure()
        nullCallbackUtil.callGetRSSI(false)
    }

    @Test fun async_failure_prechecks_takeHighest_true() {
        mockWiseFyPrechecksUtil.getRSSI_failure()
        val mockCallbacks = mock(GetRSSICallbacks::class.java)
        wiseFy.getRSSI(TEST_SSID, true, TEST_TIMEOUT, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).wisefyFailure(MISSING_PARAMETER)
    }

    @Test fun async_failure_prechecks_takeHighest_true_nullCallback() {
        mockWiseFyPrechecksUtil.getRSSI_failure()
        nullCallbackUtil.callGetRSSI(true)
    }

    @Test fun async_failure_noNetworkFound_takeHighest_false() {
        mockWiseFyPrechecksUtil.getRSSI_success()
        mockWiseFySearchUtil.findAccessPointByRegex_null()
        val mockCallbacks = mock(GetRSSICallbacks::class.java)
        wiseFy.getRSSI(TEST_SSID, false, TEST_TIMEOUT, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).networkNotFoundToRetrieveRSSI()
    }

    @Test fun async_failure_noNetworkFound_takeHighest_false_nullCallback() {
        mockWiseFyPrechecksUtil.getRSSI_success()
        mockWiseFySearchUtil.findAccessPointByRegex_null()
        nullCallbackUtil.callGetRSSI(false)
    }

    @Test fun async_failure_noNetworkFound_takeHighest_true() {
        mockWiseFyPrechecksUtil.getRSSI_success()
        mockWiseFySearchUtil.findAccessPointByRegex_null()
        val mockCallbacks = mock(GetRSSICallbacks::class.java)
        wiseFy.getRSSI(TEST_SSID, true, TEST_TIMEOUT, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).networkNotFoundToRetrieveRSSI()
    }

    @Test fun async_failure_noNetworkFound_takeHighest_true_nullCallback() {
        mockWiseFyPrechecksUtil.getRSSI_success()
        mockWiseFySearchUtil.findAccessPointByRegex_null()
        nullCallbackUtil.callGetRSSI(true)
    }

    @Test fun async_success_takeHighest_false() {
        mockWiseFyPrechecksUtil.getRSSI_success()
        mockWiseFySearchUtil.findAccessPointByRegex_success()
        val mockCallbacks = mock(GetRSSICallbacks::class.java)
        wiseFy.getRSSI(TEST_SSID, false, TEST_TIMEOUT, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).retrievedRSSI(TEST_RSSI_LEVEL)
    }

    @Test fun async_success_takeHighest_false_nullCallback() {
        mockWiseFyPrechecksUtil.getRSSI_success()
        mockWiseFySearchUtil.findAccessPointByRegex_success()
        nullCallbackUtil.callGetRSSI(false)
    }

    @Test fun async_success_takeHighest_true() {
        mockWiseFyPrechecksUtil.getRSSI_success()
        mockWiseFySearchUtil.findAccessPointByRegex_success()
        val mockCallbacks = mock(GetRSSICallbacks::class.java)
        wiseFy.getRSSI(TEST_SSID, true, TEST_TIMEOUT, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).retrievedRSSI(TEST_RSSI_LEVEL)
    }

    @Test fun async_success_takeHighest_true_nullCallback() {
        mockWiseFyPrechecksUtil.getRSSI_success()
        mockWiseFySearchUtil.findAccessPointByRegex_success()
        nullCallbackUtil.callGetRSSI(true)
    }
}
