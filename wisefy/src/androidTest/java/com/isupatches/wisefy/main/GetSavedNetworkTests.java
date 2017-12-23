package com.isupatches.wisefy.main;

import static com.isupatches.wisefy.TestUtils.TEST_SSID;
import static com.isupatches.wisefy.TestUtils.VERIFICATION_SUCCESS_TIMEOUT;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

import android.net.wifi.WifiConfiguration;

import com.isupatches.wisefy.AbstractBaseAndroidJUnit4TestClass;
import com.isupatches.wisefy.WiseFy;
import com.isupatches.wisefy.callbacks.GetSavedNetworkCallbacks;
import com.isupatches.wisefy.constants.WiseFyCodeDefs;

import org.junit.Test;

/**
 * Tests the ability to retrieve a saved on a device.
 *
 * @see WiseFy#getSavedNetwork(String)
 * @see WiseFy#getSavedNetwork(String, GetSavedNetworkCallbacks)
 */
public class GetSavedNetworkTests extends AbstractBaseAndroidJUnit4TestClass {

  public GetSavedNetworkTests() {
    // No-op
  }

  @Test
  public void sync_failure_prechecks() {
    getMockWiseFyPrechecksUtil().getSavedNetwork_failure();
    assertEquals(null, getWiseFy().getSavedNetwork(TEST_SSID));
  }

  @Test
  public void sync_failure() {
    getMockWiseFySearchUtil().findSavedNetworkByRegex_null();
    assertEquals(null, getWiseFy().getSavedNetwork(TEST_SSID));
  }

  @Test
  public void sync_success() {
    final WifiConfiguration savedNetwork = getMockWiseFySearchUtil().findSavedNetworkByRegex_success();
    assertEquals(savedNetwork, getWiseFy().getSavedNetwork(TEST_SSID));
  }

  @Test
  public void async_failure_prechecks() {
    getMockWiseFyPrechecksUtil().getSavedNetwork_failure();
    final GetSavedNetworkCallbacks mockCallbacks = mock(GetSavedNetworkCallbacks.class);
    getWiseFy().getSavedNetwork(TEST_SSID, mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).getSavedNetworkWiseFyFailure(WiseFyCodeDefs.MISSING_PREREQUISITE);
  }

  @Test
  public void async_failure_prechecks_nullCallback() {
    getMockWiseFyPrechecksUtil().getSavedNetwork_failure();
    getNullCallbackUtil().callGetSavedNetwork(TEST_SSID);
  }

  @Test
  public void async_failure() {
    getMockWiseFySearchUtil().findSavedNetworkByRegex_null();
    final GetSavedNetworkCallbacks mockCallbacks = mock(GetSavedNetworkCallbacks.class);
    getWiseFy().getSavedNetwork(TEST_SSID, mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).savedNetworkNotFound();
  }

  @Test
  public void async_failure_nullCallback() {
    getMockWiseFySearchUtil().findSavedNetworkByRegex_null();
    getNullCallbackUtil().callGetSavedNetwork(TEST_SSID);
  }

  @Test
  public void async_success() {
    final WifiConfiguration savedNetwork = getMockWiseFySearchUtil().findSavedNetworkByRegex_success();
    final GetSavedNetworkCallbacks mockCallbacks = mock(GetSavedNetworkCallbacks.class);
    getWiseFy().getSavedNetwork(TEST_SSID, mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).retrievedSavedNetwork(savedNetwork);
  }

  @Test
  public void async_success_nullCallback() {
    getMockWiseFySearchUtil().findSavedNetworkByRegex_success();
    getNullCallbackUtil().callGetSavedNetwork(TEST_SSID);
  }
}
