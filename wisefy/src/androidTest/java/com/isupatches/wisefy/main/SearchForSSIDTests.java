package com.isupatches.wisefy.main;

import static com.isupatches.wisefy.TestUtils.TEST_SSID;
import static com.isupatches.wisefy.TestUtils.TEST_TIMEOUT;
import static com.isupatches.wisefy.TestUtils.VERIFICATION_SUCCESS_TIMEOUT;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

import com.isupatches.wisefy.AbstractBaseAndroidJUnit4TestClass;
import com.isupatches.wisefy.WiseFy;
import com.isupatches.wisefy.callbacks.SearchForSSIDCallbacks;
import com.isupatches.wisefy.constants.WiseFyCodes;

import org.junit.Test;

/**
 * Used to test the functionality to search for SSIDs nearby.
 *
 * @see WiseFy#searchForSSID(String, int)
 * @see WiseFy#searchForSSID(String, int, SearchForSSIDCallbacks)
 *
 * @author Patches
 */
public class SearchForSSIDTests extends AbstractBaseAndroidJUnit4TestClass {

  public SearchForSSIDTests() {
    // No-op
  }

  @Test
  public void sync_failure_missingPrerequisite() {
    getMockWiseFyPrechecksUtil().searchForSSID_failure();
    assertEquals(null, getWiseFy().searchForSSID(TEST_SSID, TEST_TIMEOUT));
  }

  @Test
  public void sync_failure() {
    getMockWiseFySearchUtil().findAccessPointByRegex_null();
    assertEquals(null, getWiseFy().searchForSSID(TEST_SSID, TEST_TIMEOUT));
  }

  @Test
  public void sync_success() {
    getMockWiseFySearchUtil().findAccessPointByRegex_success();
    assertEquals(TEST_SSID, getWiseFy().searchForSSID(TEST_SSID, TEST_TIMEOUT));
  }

  @Test
  public void async_failure_missingPrerequisite() {
    getMockWiseFyPrechecksUtil().searchForSSID_failure();
    final SearchForSSIDCallbacks mockCallbacks = mock(SearchForSSIDCallbacks.class);
    getWiseFy().searchForSSID(TEST_SSID, TEST_TIMEOUT, mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).searchForSSIDWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
  }

  @Test
  public void async_failure_missingPrerequisite_nullCallback() {
    getMockWiseFyPrechecksUtil().searchForSSID_failure();
    getNullCallbackUtil().callSearchForSSID(TEST_SSID);
  }

  @Test
  public void async_failure() {
    getMockWiseFySearchUtil().findAccessPointByRegex_null();
    final SearchForSSIDCallbacks mockCallbacks = mock(SearchForSSIDCallbacks.class);
    getWiseFy().searchForSSID(TEST_SSID, TEST_TIMEOUT, mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).ssidNotFound();
  }


  @Test
  public void async_failure_nullCallback() {
    getMockWiseFySearchUtil().findAccessPointByRegex_null();
    getNullCallbackUtil().callSearchForSSID(TEST_SSID);
  }

  @Test
  public void async_success() {
    getMockWiseFySearchUtil().findAccessPointByRegex_success();
    final SearchForSSIDCallbacks mockCallbacks = mock(SearchForSSIDCallbacks.class);
    getWiseFy().searchForSSID(TEST_SSID, TEST_TIMEOUT, mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).ssidFound(TEST_SSID);
  }

  @Test
  public void async_success_nullCallback() {
    getMockWiseFySearchUtil().findAccessPointByRegex_success();
    getNullCallbackUtil().callSearchForSSID(TEST_SSID);
  }
}
