package com.isupatches.wisefy.main;

import static com.isupatches.wisefy.TestUtils.TEST_SSID;
import static com.isupatches.wisefy.TestUtils.VERIFICATION_SUCCESS_TIMEOUT;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

import com.isupatches.wisefy.AbstractBaseAndroidJUnit4TestClass;
import com.isupatches.wisefy.WiseFy;
import com.isupatches.wisefy.callbacks.SearchForSSIDsCallbacks;
import com.isupatches.wisefy.constants.WiseFyCodes;

import java.util.List;

import org.junit.Test;

/**
 * Used to test the functionality to search for SSIDs nearby.
 *
 * @see WiseFy#searchForSSIDs(String)
 * @see WiseFy#searchForSSIDs(String, SearchForSSIDsCallbacks)
 *
 * @author Patches
 */
public class SearchForSSIDsTests extends AbstractBaseAndroidJUnit4TestClass {

  public SearchForSSIDsTests() {
    // No-op
  }

  @Test
  public void sync_failure_precheck() {
    getMockWiseFyPrechecksUtil().searchForSSIDs_failure();
    assertEquals(null, getWiseFy().searchForSSIDs(TEST_SSID));
  }

  @Test
  public void sync_failure() {
    getMockWiseFySearchUtil().findSSIDsMatchingRegex_null();
    assertEquals(null, getWiseFy().searchForSSIDs(TEST_SSID));
  }

  @Test
  public void sync_success() {
    final List<String> ssids = getMockWiseFySearchUtil().findSSIDsMatchingRegex_success();
    assertEquals(ssids, getWiseFy().searchForSSIDs(TEST_SSID));
  }

  @Test
  public void async_failure_prechecks() {
    getMockWiseFyPrechecksUtil().searchForSSIDs_failure();
    final SearchForSSIDsCallbacks mockCallbacks = mock(SearchForSSIDsCallbacks.class);
    getWiseFy().searchForSSIDs(TEST_SSID, mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).searchForSSIDsWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
  }

  @Test
  public void async_failure_prechecks_nullCallback() {
    getMockWiseFyPrechecksUtil().searchForSSIDs_failure();
    getNullCallbackUtil().callSearchForSSIDs(TEST_SSID);
  }

  @Test
  public void async_failure() {
    getMockWiseFySearchUtil().findSSIDsMatchingRegex_null();
    final SearchForSSIDsCallbacks mockCallbacks = mock(SearchForSSIDsCallbacks.class);
    getWiseFy().searchForSSIDs(TEST_SSID, mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).noSSIDsFound();
  }


  @Test
  public void async_failure_nullCallback() {
    getMockWiseFySearchUtil().findSSIDsMatchingRegex_null();
    getNullCallbackUtil().callSearchForSSIDs(TEST_SSID);
  }

  @Test
  public void async_success() {
    final List<String> ssids = getMockWiseFySearchUtil().findSSIDsMatchingRegex_success();
    final SearchForSSIDsCallbacks mockCallbacks = mock(SearchForSSIDsCallbacks.class);
    getWiseFy().searchForSSIDs(TEST_SSID, mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).retrievedSSIDs(ssids);
  }

  @Test
  public void async_success_nullCallback() {
    getMockWiseFySearchUtil().findSSIDsMatchingRegex_success();
    getNullCallbackUtil().callSearchForSSIDs(TEST_SSID);
  }
}
