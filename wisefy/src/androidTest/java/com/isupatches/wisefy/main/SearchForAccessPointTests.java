package com.isupatches.wisefy.main;

import static com.isupatches.wisefy.TestUtils.TEST_SSID;
import static com.isupatches.wisefy.TestUtils.TEST_TIMEOUT;
import static com.isupatches.wisefy.TestUtils.VERIFICATION_SUCCESS_TIMEOUT;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

import android.net.wifi.ScanResult;

import com.isupatches.wisefy.AbstractBaseAndroidJUnit4TestClass;
import com.isupatches.wisefy.WiseFy;
import com.isupatches.wisefy.callbacks.SearchForAccessPointCallbacks;
import com.isupatches.wisefy.constants.WiseFyCodes;

import org.junit.Test;

/**
 * Used to test the functionality to search for a set of nearby access points.
 *
 * @see WiseFy#searchForAccessPoint(String, int, boolean)
 * @see WiseFy#searchForAccessPoint(String, int, boolean, SearchForAccessPointCallbacks)
 *
 * @author Patches
 */
public class SearchForAccessPointTests extends AbstractBaseAndroidJUnit4TestClass {

  public SearchForAccessPointTests() {
    // No-op
  }

  @Test
  public void sync_failure_prechecks_filterDuplicates_false() {
    getMockWiseFyPrechecksUtil().searchForAccessPoint_failure();
    assertEquals(null, getWiseFy().searchForAccessPoint(TEST_SSID, TEST_TIMEOUT, false));
  }

  @Test
  public void sync_failure_prechecks_filterDuplicates_true() {
    getMockWiseFyPrechecksUtil().searchForAccessPoint_failure();
    assertEquals(null, getWiseFy().searchForAccessPoint(TEST_SSID, TEST_TIMEOUT, true));
  }

  @Test
  public void sync_failure_filterDuplicates_false() {
    getMockWiseFySearchUtil().findAccessPointByRegex_null();
    assertEquals(null, getWiseFy().searchForAccessPoint(TEST_SSID, TEST_TIMEOUT, false));
  }

  @Test
  public void sync_failure_filterDuplicates_true() {
    getMockWiseFySearchUtil().findAccessPointByRegex_null();
    assertEquals(null, getWiseFy().searchForAccessPoint(TEST_SSID, TEST_TIMEOUT, true));
  }

  @Test
  public void sync_success_filterDuplicates_false() {
    final ScanResult accessPoint = getMockWiseFySearchUtil().findAccessPointByRegex_success();
    assertEquals(accessPoint, getWiseFy().searchForAccessPoint(TEST_SSID, TEST_TIMEOUT, false));
  }

  @Test
  public void sync_success_filterDuplicates_true() {
    final ScanResult accessPoint = getMockWiseFySearchUtil().findAccessPointByRegex_success();
    assertEquals(accessPoint, getWiseFy().searchForAccessPoint(TEST_SSID, TEST_TIMEOUT, true));
  }

  @Test
  public void async_failure_prechecks_filterDuplicates_false() {
    getMockWiseFyPrechecksUtil().searchForAccessPoint_failure();
    final SearchForAccessPointCallbacks mockCallbacks = mock(SearchForAccessPointCallbacks.class);
    getWiseFy().searchForAccessPoint(TEST_SSID, TEST_TIMEOUT, false, mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).searchForAccessPointWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
  }

  @Test
  public void async_failure_prechecks_filterDuplicates_false_nullCallback() {
    getMockWiseFyPrechecksUtil().searchForAccessPoint_failure();
    getNullCallbackUtil().callSearchForAccessPoint(TEST_SSID, false);
  }

  @Test
  public void async_failure_prechecks_filterDuplicates_true() {
    getMockWiseFyPrechecksUtil().searchForAccessPoint_failure();
    final SearchForAccessPointCallbacks mockCallbacks = mock(SearchForAccessPointCallbacks.class);
    getWiseFy().searchForAccessPoint(TEST_SSID, TEST_TIMEOUT, true, mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).searchForAccessPointWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
  }

  @Test
  public void async_failure_prechecks_filterDuplicates_true_nullCallback() {
    getMockWiseFyPrechecksUtil().searchForAccessPoint_failure();
    getNullCallbackUtil().callSearchForAccessPoint(TEST_SSID, true);
  }

  @Test
  public void async_failure_filterDuplicates_false() {
    getMockWiseFySearchUtil().findAccessPointByRegex_null();
    final SearchForAccessPointCallbacks mockCallbacks = mock(SearchForAccessPointCallbacks.class);
    getWiseFy().searchForAccessPoint(TEST_SSID, TEST_TIMEOUT, false, mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).accessPointNotFound();
  }

  @Test
  public void async_failure_filterDuplicates_false_nullCallback() {
    getMockWiseFySearchUtil().findAccessPointByRegex_null();
    getNullCallbackUtil().callSearchForAccessPoint(TEST_SSID, false);
  }

  @Test
  public void async_failure_filterDuplicates_true() {
    getMockWiseFySearchUtil().findAccessPointByRegex_null();
    final SearchForAccessPointCallbacks mockCallbacks = mock(SearchForAccessPointCallbacks.class);
    getWiseFy().searchForAccessPoint(TEST_SSID, TEST_TIMEOUT, true, mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).accessPointNotFound();
  }


  @Test
  public void async_failure_filterDuplicates_true_nullCallback() {
    getMockWiseFySearchUtil().findAccessPointByRegex_null();
    getNullCallbackUtil().callSearchForAccessPoint(TEST_SSID, true);
  }

  @Test
  public void async_success_filterDuplicates_false() {
    final ScanResult accessPoint = getMockWiseFySearchUtil().findAccessPointByRegex_success();
    final SearchForAccessPointCallbacks mockCallbacks = mock(SearchForAccessPointCallbacks.class);
    getWiseFy().searchForAccessPoint(TEST_SSID, TEST_TIMEOUT, false, mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).accessPointFound(accessPoint);
  }

  @Test
  public void async_success_filterDuplicates_false_nullCallback() {
    getMockWiseFySearchUtil().findAccessPointByRegex_success();
    getNullCallbackUtil().callSearchForAccessPoint(TEST_SSID, false);
  }

  @Test
  public void async_success_filterDuplicates_true() {
    final ScanResult accessPoint = getMockWiseFySearchUtil().findAccessPointByRegex_success();
    final SearchForAccessPointCallbacks mockCallbacks = mock(SearchForAccessPointCallbacks.class);
    getWiseFy().searchForAccessPoint(TEST_SSID, TEST_TIMEOUT, true, mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).accessPointFound(accessPoint);
  }

  @Test
  public void async_success_filterDuplicates_true_nullCallback() {
    getMockWiseFySearchUtil().findAccessPointByRegex_success();
    getNullCallbackUtil().callSearchForAccessPoint(TEST_SSID, true);
  }
}
