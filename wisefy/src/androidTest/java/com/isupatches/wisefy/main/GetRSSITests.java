package com.isupatches.wisefy.main;

import static com.isupatches.wisefy.TestUtils.TEST_RSSI_LEVEL;
import static com.isupatches.wisefy.TestUtils.TEST_SSID;
import static com.isupatches.wisefy.TestUtils.TEST_TIMEOUT;
import static com.isupatches.wisefy.TestUtils.VERIFICATION_SUCCESS_TIMEOUT;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

import com.isupatches.wisefy.AbstractBaseAndroidJUnit4TestClass;
import com.isupatches.wisefy.WiseFy;
import com.isupatches.wisefy.callbacks.GetRSSICallbacks;
import com.isupatches.wisefy.constants.WiseFyCodeDefs;

import org.junit.Test;

/**
 * Tests the ability to retrieve the RSSI for a nearby access point.
 *
 * @see WiseFy#getRSSI(String, boolean, int)
 * @see WiseFy#getRSSI(String, boolean, int, GetRSSICallbacks)
 *
 * @author Patches
 */
public class GetRSSITests extends AbstractBaseAndroidJUnit4TestClass {

  public GetRSSITests() {
    // No-op
  }

  @Test
  public void sync_failure_prechecks_takeHighest_false() {
    getMockWiseFyPrechecksUtil().getRSSI_failure();
    assertEquals(null, getWiseFy().getRSSI(TEST_SSID, false, TEST_TIMEOUT));
  }

  @Test
  public void sync_failure_prechecks_takeHighest_true() {
    getMockWiseFyPrechecksUtil().getRSSI_failure();
    assertEquals(null, getWiseFy().getRSSI(TEST_SSID, true, TEST_TIMEOUT));
  }

  @Test
  public void sync_failure_noNetworkFound_takeHighest_false() {
    getMockWiseFySearchUtil().findAccessPointByRegex_null();
    assertEquals(null, getWiseFy().getRSSI(TEST_SSID, false, TEST_TIMEOUT));
  }

  @Test
  public void sync_failure_noNetworkFound_takeHighest_true() {
    getMockWiseFySearchUtil().findAccessPointByRegex_null();
    assertEquals(null, getWiseFy().getRSSI(TEST_SSID, true, TEST_TIMEOUT));
  }

  @Test
  public void sync_success_takeHighest_false() {
    getMockWiseFySearchUtil().findAccessPointByRegex_success();

    final Integer rssi = getWiseFy().getRSSI(TEST_SSID, false, TEST_TIMEOUT);
    if (rssi != null) {
      assertEquals(TEST_RSSI_LEVEL, (int) rssi);
    } else {
      fail();
    }
  }

  @Test
  public void sync_success_takeHighest_true() {
    getMockWiseFySearchUtil().findAccessPointByRegex_success();

    final Integer rssi = getWiseFy().getRSSI(TEST_SSID, true, TEST_TIMEOUT);
    if (rssi != null) {
      assertEquals(TEST_RSSI_LEVEL, (int) rssi);
    } else {
      fail();
    }
  }

  @Test
  public void async_failure_prechecks_takeHighest_false() {
    getMockWiseFyPrechecksUtil().getRSSI_failure();
    final GetRSSICallbacks mockCallbacks = mock(GetRSSICallbacks.class);
    getWiseFy().getRSSI(TEST_SSID, false, TEST_TIMEOUT, mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).getRSSIWiseFyFailure(WiseFyCodeDefs.MISSING_PREREQUISITE);
  }

  @Test
  public void async_failure_prechecks_takeHighest_false_nullCallback() {
    getMockWiseFyPrechecksUtil().getRSSI_failure();
    getNullCallbackUtil().callGetRSSI(false);
  }

  @Test
  public void async_failure_prechecks_takeHighest_true() {
    getMockWiseFyPrechecksUtil().getRSSI_failure();
    final GetRSSICallbacks mockCallbacks = mock(GetRSSICallbacks.class);
    getWiseFy().getRSSI(TEST_SSID, true, TEST_TIMEOUT, mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).getRSSIWiseFyFailure(WiseFyCodeDefs.MISSING_PREREQUISITE);
  }

  @Test
  public void async_failure_prechecks_takeHighest_true_nullCallback() {
    getMockWiseFyPrechecksUtil().getRSSI_failure();
    getNullCallbackUtil().callGetRSSI(true);
  }

  @Test
  public void async_failure_noNetworkFound_takeHighest_false() {
    getMockWiseFySearchUtil().findAccessPointByRegex_null();
    final GetRSSICallbacks mockCallbacks = mock(GetRSSICallbacks.class);
    getWiseFy().getRSSI(TEST_SSID, false, TEST_TIMEOUT, mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).networkNotFoundToRetrieveRSSI();
  }

  @Test
  public void async_failure_noNetworkFound_takeHighest_false_nullCallback() {
    getMockWiseFySearchUtil().findAccessPointByRegex_null();
    getNullCallbackUtil().callGetRSSI(false);
  }

  @Test
  public void async_failure_noNetworkFound_takeHighest_true() {
    getMockWiseFySearchUtil().findAccessPointByRegex_null();
    final GetRSSICallbacks mockCallbacks = mock(GetRSSICallbacks.class);
    getWiseFy().getRSSI(TEST_SSID, true, TEST_TIMEOUT, mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).networkNotFoundToRetrieveRSSI();
  }

  @Test
  public void async_failure_noNetworkFound_takeHighest_true_nullCallback() {
    getMockWiseFySearchUtil().findAccessPointByRegex_null();
    getNullCallbackUtil().callGetRSSI(true);
  }

  @Test
  public void async_success_takeHighest_false() {
    getMockWiseFySearchUtil().findAccessPointByRegex_success();
    final GetRSSICallbacks mockCallbacks = mock(GetRSSICallbacks.class);
    getWiseFy().getRSSI(TEST_SSID, false, TEST_TIMEOUT, mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).retrievedRSSI(TEST_RSSI_LEVEL);
  }

  @Test
  public void async_success_takeHighest_false_nullCallback() {
    getMockWiseFySearchUtil().findAccessPointByRegex_success();
    getNullCallbackUtil().callGetRSSI(false);
  }

  @Test
  public void async_success_takeHighest_true() {
    getMockWiseFySearchUtil().findAccessPointByRegex_success();
    final GetRSSICallbacks mockCallbacks = mock(GetRSSICallbacks.class);
    getWiseFy().getRSSI(TEST_SSID, true, TEST_TIMEOUT, mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).retrievedRSSI(TEST_RSSI_LEVEL);
  }

  @Test
  public void async_success_takeHighest_true_nullCallback() {
    getMockWiseFySearchUtil().findAccessPointByRegex_success();
    getNullCallbackUtil().callGetRSSI(true);
  }
}
