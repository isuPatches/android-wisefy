package com.isupatches.wisefy.main;

import static com.isupatches.wisefy.TestUtils.VERIFICATION_SUCCESS_TIMEOUT;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

import android.net.wifi.ScanResult;

import com.isupatches.wisefy.AbstractBaseAndroidJUnit4TestClass;
import com.isupatches.wisefy.WiseFy;
import com.isupatches.wisefy.callbacks.GetNearbyAccessPointsCallbacks;
import com.isupatches.wisefy.constants.WiseFyCodes;

import java.util.List;

import org.junit.Test;

/**
 * Tests the ability to retrieve a list of nearby access points for a device.
 *
 * @see WiseFy#getNearbyAccessPoints(boolean)
 * @see WiseFy#getNearbyAccessPoints(boolean, GetNearbyAccessPointsCallbacks)
 *
 * @author Patches
 */
public class GetNearbyAccessPointsTests extends AbstractBaseAndroidJUnit4TestClass {

  public GetNearbyAccessPointsTests() {
    // No-op
  }

  @Test
  public void sync_failure_prechecks_filterDuplicates_false() {
    getMockWiseFyPrechecksUtil().getNearbyAccessPoints_failure();
    assertEquals(null, getWiseFy().getNearbyAccessPoints(false));
    getVerificationUtil().didNotTryToGetNearbyAccessPoints();
  }

  @Test
  public void sync_failure_prechecks_filterDuplicates_true() {
    getMockWiseFyPrechecksUtil().getNearbyAccessPoints_failure();
    assertEquals(null, getWiseFy().getNearbyAccessPoints(true));
    getVerificationUtil().didNotTryToGetNearbyAccessPoints();
  }

  @Test
  public void sync_success_filterDuplicates_false() {
    final List<ScanResult> accessPoints = getMockNetworkUtil().nearbyAccessPoints();
    final List<ScanResult> nearbyAccessPoints = getWiseFy().getNearbyAccessPoints(false);
    assertEquals(accessPoints, nearbyAccessPoints);
    getVerificationUtil().triedToGetNearbyAccessPoints();
  }

  @Test
  public void sync_success_filterDuplicates_true() {
    final List<ScanResult> accessPoints = getMockWiseFySearchUtil().removeEntriesWithLowerSignalStrength();
    final List<ScanResult> nearbyAccessPoints = getWiseFy().getNearbyAccessPoints(true);
    assertEquals(accessPoints, nearbyAccessPoints);
    getVerificationUtil().triedToGetNearbyAccessPoints();
  }

  @Test
  public void async_failure_prechecks_filterDuplicates_false() {
    getMockWiseFyPrechecksUtil().getNearbyAccessPoints_failure();
    final GetNearbyAccessPointsCallbacks mockCallbacks = mock(GetNearbyAccessPointsCallbacks.class);
    getWiseFy().getNearbyAccessPoints(false, mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).getNearbyAccessPointsWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
    getVerificationUtil().didNotTryToGetNearbyAccessPoints();
  }

  @Test
  public void async_failure_prechecks_filterDuplicates_false_nullCallback() {
    getMockWiseFyPrechecksUtil().getNearbyAccessPoints_failure();
    getNullCallbackUtil().callGetNearbyAccessPoints(false);
    getVerificationUtil().didNotTryToGetNearbyAccessPoints();
  }

  @Test
  public void async_failure_prechecks_filterDuplicates_true() {
    getMockWiseFyPrechecksUtil().getNearbyAccessPoints_failure();
    final GetNearbyAccessPointsCallbacks mockCallbacks = mock(GetNearbyAccessPointsCallbacks.class);
    getWiseFy().getNearbyAccessPoints(true, mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).getNearbyAccessPointsWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
    getVerificationUtil().didNotTryToGetNearbyAccessPoints();
  }

  @Test
  public void async_failure_prechecks_filterDuplicates_true_nullCallback() {
    getMockWiseFyPrechecksUtil().getNearbyAccessPoints_failure();
    getNullCallbackUtil().callGetNearbyAccessPoints(true);
    getVerificationUtil().didNotTryToGetNearbyAccessPoints();
  }

  @Test
  public void async_success_filterDuplicates_false() {
    final List<ScanResult> accessPoints = getMockNetworkUtil().nearbyAccessPoints();
    final GetNearbyAccessPointsCallbacks mockCallbacks = mock(GetNearbyAccessPointsCallbacks.class);
    getWiseFy().getNearbyAccessPoints(false, mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).retrievedNearbyAccessPoints(accessPoints);
    getVerificationUtil().triedToGetNearbyAccessPoints();
  }

  @Test
  public void async_success_filterDuplicates_false_nullCallback() {
    getMockNetworkUtil().nearbyAccessPoints();
    getNullCallbackUtil().callGetNearbyAccessPoints(false);
    getVerificationUtil().didNotTryToGetNearbyAccessPoints();
  }

  @Test
  public void async_success_filterDuplicates_true() {
    final List<ScanResult> accessPoints = getMockWiseFySearchUtil().removeEntriesWithLowerSignalStrength();
    final GetNearbyAccessPointsCallbacks mockCallbacks = mock(GetNearbyAccessPointsCallbacks.class);
    getWiseFy().getNearbyAccessPoints(true, mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).retrievedNearbyAccessPoints(accessPoints);
    getVerificationUtil().triedToGetNearbyAccessPoints();
  }

  @Test
  public void async_success_filterDuplicates_true_nullCallback() {
    getMockWiseFySearchUtil().removeEntriesWithLowerSignalStrength();
    getNullCallbackUtil().callGetNearbyAccessPoints(true);
    getVerificationUtil().didNotTryToGetNearbyAccessPoints();
  }
}
