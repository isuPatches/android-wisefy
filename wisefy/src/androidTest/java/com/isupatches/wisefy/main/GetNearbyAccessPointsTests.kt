package com.isupatches.wisefy.main

import com.isupatches.wisefy.BaseAndroidJUnit4TestClass
import com.isupatches.wisefy.VERIFICATION_SUCCESS_TIMEOUT
import com.isupatches.wisefy.callbacks.GetNearbyAccessPointsCallbacks
import com.isupatches.wisefy.constants.MISSING_PARAMETER
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.timeout
import org.mockito.Mockito.verify

/**
 * Tests the ability to retrieve a list of nearby access points for a device.
 *
 * @author Patches
 */
internal class GetNearbyAccessPointsTests : BaseAndroidJUnit4TestClass() {

    @Test fun sync_failure_prechecks_filterDuplicates_false() {
        mockWiseFyPrechecksUtil.getNearbyAccessPoints_failure()
        assertEquals(null, wiseFy.getNearbyAccessPoints(false))
        verificationUtil.didNotTryToGetNearbyAccessPoints()
    }

    @Test fun sync_failure_prechecks_filterDuplicates_true() {
        mockWiseFyPrechecksUtil.getNearbyAccessPoints_failure()
        assertEquals(null, wiseFy.getNearbyAccessPoints(true))
        verificationUtil.didNotTryToGetNearbyAccessPoints()
    }

    @Test fun sync_success_filterDuplicates_false() {
        mockWiseFyPrechecksUtil.getNearbyAccessPoints_success()
        val accessPoints = mockNetworkUtil.nearbyAccessPoints()
        val nearbyAccessPoints = wiseFy.getNearbyAccessPoints(false)
        assertEquals(accessPoints, nearbyAccessPoints)
        verificationUtil.triedToGetNearbyAccessPoints()
    }

    @Test fun sync_success_filterDuplicates_true() {
        mockWiseFyPrechecksUtil.getNearbyAccessPoints_success()
        val accessPoints = mockWiseFySearchUtil.removeEntriesWithLowerSignalStrength()
        val nearbyAccessPoints = wiseFy.getNearbyAccessPoints(true)
        assertEquals(accessPoints, nearbyAccessPoints)
        verificationUtil.triedToGetNearbyAccessPoints()
    }

    @Test fun async_failure_prechecks_filterDuplicates_false() {
        mockWiseFyPrechecksUtil.getNearbyAccessPoints_failure()
        val mockCallbacks = mock(GetNearbyAccessPointsCallbacks::class.java)
        wiseFy.getNearbyAccessPoints(false, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).wisefyFailure(MISSING_PARAMETER)
        verificationUtil.didNotTryToGetNearbyAccessPoints()
    }

    @Test fun async_failure_prechecks_filterDuplicates_false_nullCallback() {
        mockWiseFyPrechecksUtil.getNearbyAccessPoints_failure()
        nullCallbackUtil.callGetNearbyAccessPoints(false)
        verificationUtil.didNotTryToGetNearbyAccessPoints()
    }

    @Test fun async_failure_prechecks_filterDuplicates_true() {
        mockWiseFyPrechecksUtil.getNearbyAccessPoints_failure()
        val mockCallbacks = mock(GetNearbyAccessPointsCallbacks::class.java)
        wiseFy.getNearbyAccessPoints(true, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).wisefyFailure(MISSING_PARAMETER)
        verificationUtil.didNotTryToGetNearbyAccessPoints()
    }

    @Test fun async_failure_prechecks_filterDuplicates_true_nullCallback() {
        mockWiseFyPrechecksUtil.getNearbyAccessPoints_failure()
        nullCallbackUtil.callGetNearbyAccessPoints(true)
        verificationUtil.didNotTryToGetNearbyAccessPoints()
    }

    @Test fun async_success_filterDuplicates_false() {
        mockWiseFyPrechecksUtil.getNearbyAccessPoints_success()
        val accessPoints = mockNetworkUtil.nearbyAccessPoints()
        val mockCallbacks = mock(GetNearbyAccessPointsCallbacks::class.java)
        wiseFy.getNearbyAccessPoints(false, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).retrievedNearbyAccessPoints(accessPoints)
        verificationUtil.triedToGetNearbyAccessPoints()
    }

    @Test fun async_success_filterDuplicates_false_nullCallback() {
        mockWiseFyPrechecksUtil.getNearbyAccessPoints_success()
        mockNetworkUtil.nearbyAccessPoints()
        nullCallbackUtil.callGetNearbyAccessPoints(false)
        verificationUtil.didNotTryToGetNearbyAccessPoints()
    }

    @Test fun async_success_filterDuplicates_true() {
        mockWiseFyPrechecksUtil.getNearbyAccessPoints_success()
        val accessPoints = mockWiseFySearchUtil.removeEntriesWithLowerSignalStrength()
        val mockCallbacks = mock(GetNearbyAccessPointsCallbacks::class.java)
        wiseFy.getNearbyAccessPoints(true, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).retrievedNearbyAccessPoints(accessPoints)
        verificationUtil.triedToGetNearbyAccessPoints()
    }

    @Test fun async_success_filterDuplicates_true_nullCallback() {
        mockWiseFyPrechecksUtil.getNearbyAccessPoints_success()
        mockWiseFySearchUtil.removeEntriesWithLowerSignalStrength()
        nullCallbackUtil.callGetNearbyAccessPoints(true)
        verificationUtil.didNotTryToGetNearbyAccessPoints()
    }
}
