package com.isupatches.wisefy.main;

import static com.isupatches.wisefy.TestUtils.TEST_SSID;
import static com.isupatches.wisefy.TestUtils.VERIFICATION_SUCCESS_TIMEOUT;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

import android.net.wifi.ScanResult;

import com.isupatches.wisefy.AbstractBaseAndroidJUnit4TestClass;
import com.isupatches.wisefy.WiseFy;
import com.isupatches.wisefy.callbacks.SearchForAccessPointsCallbacks;
import com.isupatches.wisefy.constants.WiseFyCodeDefs;

import java.util.List;

import org.junit.Test;

/**
 * Used to test the functionality to search for a nearby access point.
 *
 * @see WiseFy#searchForAccessPoints(String, boolean)
 * @see WiseFy#searchForAccessPoints(String, boolean, SearchForAccessPointsCallbacks)
 */
public class SearchForAccessPointsTests extends AbstractBaseAndroidJUnit4TestClass {

  public SearchForAccessPointsTests() {
    // No-op
  }

  @Test
  public void sync_failure_prechecks_filterDuplicates_false() {
    getMockWiseFyPrechecksUtil().searchForAccessPoints_failure();
    assertEquals(null, getWiseFy().searchForAccessPoints(TEST_SSID, false));
  }

  @Test
  public void sync_failure_prechecks_filterDuplicates_true() {
    getMockWiseFyPrechecksUtil().searchForAccessPoints_failure();
    assertEquals(null, getWiseFy().searchForAccessPoints(TEST_SSID, true));
  }

  @Test
  public void sync_failure_filterDuplicates_false() {
    getMockWiseFySearchUtil().findAccessPointsMatchingRegex_null();
    assertEquals(null, getWiseFy().searchForAccessPoints(TEST_SSID, false));
  }

  @Test
  public void sync_failure_filterDuplicates_true() {
    getMockWiseFySearchUtil().findAccessPointsMatchingRegex_null();
    assertEquals(null, getWiseFy().searchForAccessPoints(TEST_SSID, true));
  }

  @Test
  public void sync_success_filterDuplicates_false() {
    final List<ScanResult> accessPoints = getMockWiseFySearchUtil().findAccessPointsMatchingRegex_success();
    assertEquals(accessPoints, getWiseFy().searchForAccessPoints(TEST_SSID, false));
  }

  @Test
  public void sync_success_filterDuplicates_true() {
    final List<ScanResult> accessPoints = getMockWiseFySearchUtil().findAccessPointsMatchingRegex_success();
    assertEquals(accessPoints, getWiseFy().searchForAccessPoints(TEST_SSID, true));
  }

  @Test
  public void async_failure_prechecks_filterDuplicates_false() {
    getMockWiseFyPrechecksUtil().searchForAccessPoints_failure();
    final SearchForAccessPointsCallbacks mockCallbacks = mock(SearchForAccessPointsCallbacks.class);
    getWiseFy().searchForAccessPoints(TEST_SSID, false, mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).searchForAccessPointsWiseFyFailure(WiseFyCodeDefs.MISSING_PREREQUISITE);
  }

  @Test
  public void async_failure_prechecks_filterDuplicates_false_nullCallback() {
    getMockWiseFyPrechecksUtil().searchForAccessPoints_failure();
    getNullCallbackUtil().callSearchForAccessPoints(TEST_SSID, false);
  }

  @Test
  public void async_failure_prechecks_filterDuplicates_true() {
    getMockWiseFyPrechecksUtil().searchForAccessPoints_failure();
    final SearchForAccessPointsCallbacks mockCallbacks = mock(SearchForAccessPointsCallbacks.class);
    getWiseFy().searchForAccessPoints(TEST_SSID, true, mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).searchForAccessPointsWiseFyFailure(WiseFyCodeDefs.MISSING_PREREQUISITE);
  }

  @Test
  public void async_failure_prechecks_filterDuplicates_true_nullCallback() {
    getMockWiseFyPrechecksUtil().searchForAccessPoints_failure();
    getNullCallbackUtil().callSearchForAccessPoints(TEST_SSID, true);
  }

  @Test
  public void async_failure_filterDuplicates_false() {
    getMockWiseFySearchUtil().findAccessPointsMatchingRegex_null();
    final SearchForAccessPointsCallbacks mockCallbacks = mock(SearchForAccessPointsCallbacks.class);
    getWiseFy().searchForAccessPoints(TEST_SSID, false, mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).noAccessPointsFound();
  }

  @Test
  public void async_failure_filterDuplicates_false_nullCallback() {
    getMockWiseFySearchUtil().findAccessPointsMatchingRegex_null();
    getNullCallbackUtil().callSearchForAccessPoints(TEST_SSID, false);
  }

  @Test
  public void async_failure_filterDuplicates_true() {
    getMockWiseFySearchUtil().findAccessPointsMatchingRegex_null();
    final SearchForAccessPointsCallbacks mockCallbacks = mock(SearchForAccessPointsCallbacks.class);
    getWiseFy().searchForAccessPoints(TEST_SSID, true, mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).noAccessPointsFound();
  }


  @Test
  public void async_failure_filterDuplicates_true_nullCallback() {
    getMockWiseFySearchUtil().findAccessPointsMatchingRegex_null();
    getNullCallbackUtil().callSearchForAccessPoints(TEST_SSID, true);
  }

  @Test
  public void async_success_filterDuplicates_false() {
    final List<ScanResult> accessPoints = getMockWiseFySearchUtil().findAccessPointsMatchingRegex_success();
    final SearchForAccessPointsCallbacks mockCallbacks = mock(SearchForAccessPointsCallbacks.class);
    getWiseFy().searchForAccessPoints(TEST_SSID, false, mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).foundAccessPoints(accessPoints);
  }

  @Test
  public void async_success_filterDuplicates_false_nullCallback() {
    getMockWiseFySearchUtil().findAccessPointsMatchingRegex_success();
    getNullCallbackUtil().callSearchForAccessPoints(TEST_SSID, false);
  }

  @Test
  public void async_success_filterDuplicates_true() {
    final List<ScanResult> accessPoints = getMockWiseFySearchUtil().findAccessPointsMatchingRegex_success();
    final SearchForAccessPointsCallbacks mockCallbacks = mock(SearchForAccessPointsCallbacks.class);
    getWiseFy().searchForAccessPoints(TEST_SSID, true, mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).foundAccessPoints(accessPoints);
  }

  @Test
  public void async_success_filterDuplicates_true_nullCallback() {
    getMockWiseFySearchUtil().findAccessPointsMatchingRegex_success();
    getNullCallbackUtil().callSearchForAccessPoints(TEST_SSID, true);
  }
}
