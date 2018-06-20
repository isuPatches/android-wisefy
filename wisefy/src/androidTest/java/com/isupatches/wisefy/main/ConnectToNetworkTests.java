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
import com.isupatches.wisefy.callbacks.ConnectToNetworkCallbacks;
import com.isupatches.wisefy.constants.WiseFyCodes;

import org.junit.Test;

/**
 * Tests the ability to connect to a network.
 *
 * @see WiseFy#connectToNetwork(String, int)
 * @see WiseFy#connectToNetwork(String, int, ConnectToNetworkCallbacks)
 *
 * @author Patches
 */
public class ConnectToNetworkTests extends AbstractBaseAndroidJUnit4TestClass {

  public ConnectToNetworkTests() {
    // No-op
  }

  @Test
  public void sync_failure_prechecks() {
    getMockWiseFyPrechecksUtil().connectToNetwork_failure();
    assertEquals(false, getWiseFy().connectToNetwork(TEST_SSID, TEST_TIMEOUT));
    getVerificationUtil().didNotTryToConnectToNetwork();
  }

  @Test
  public void sync_failure_noSavedNetwork() {
    getMockWiseFySearchUtil().findSavedNetworkByRegex_null();
    assertEquals(false, getWiseFy().connectToNetwork(TEST_SSID, TEST_TIMEOUT));
    getVerificationUtil().didNotTryToConnectToNetwork();
  }

  @Test
  public void sync_failure() {
    getMockWiseFySearchUtil().findSavedNetworkByRegex_success();
    getMockWiseFyConnectionUtil().waitToConnectToSSID(false);
    assertEquals(false, getWiseFy().connectToNetwork(TEST_SSID, TEST_TIMEOUT));
    getVerificationUtil().triedToConnectToNetwork();
  }

  @Test
  public void sync_success() {
    getMockWiseFySearchUtil().findSavedNetworkByRegex_success();
    getMockWiseFyConnectionUtil().waitToConnectToSSID(true);
    assertEquals(true, getWiseFy().connectToNetwork(TEST_SSID, TEST_TIMEOUT));
    getVerificationUtil().triedToConnectToNetwork();
  }

  @Test
  public void async_failure_prechecks() {
    getMockWiseFyPrechecksUtil().connectToNetwork_failure();
    final ConnectToNetworkCallbacks mockCallbacks = mock(ConnectToNetworkCallbacks.class);
    getWiseFy().connectToNetwork(TEST_SSID, TEST_TIMEOUT, mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).connectToNetworkWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
    getVerificationUtil().didNotTryToConnectToNetwork();
  }

  @Test
  public void async_failure_prechecks_nullCallback() {
    getMockWiseFyPrechecksUtil().connectToNetwork_failure();
    getNullCallbackUtil().callConnectToNetwork(TEST_SSID);
    getVerificationUtil().didNotTryToConnectToNetwork();
  }

  @Test
  public void async_failure_noSavedNetwork() {
    getMockWiseFySearchUtil().findSavedNetworkByRegex_null();
    final ConnectToNetworkCallbacks mockCallbacks = mock(ConnectToNetworkCallbacks.class);
    getWiseFy().connectToNetwork(TEST_SSID, TEST_TIMEOUT, mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).networkNotFoundToConnectTo();
    getVerificationUtil().didNotTryToConnectToNetwork();
  }

  @Test
  public void async_failure_noSavedNetwork_nullCallback() {
    getMockWiseFySearchUtil().findSavedNetworkByRegex_null();
    getNullCallbackUtil().callConnectToNetwork(TEST_SSID);
    getVerificationUtil().didNotTryToConnectToNetwork();
  }

  @Test
  public void async_failure() {
    getMockWiseFySearchUtil().findSavedNetworkByRegex_success();
    getMockWiseFyConnectionUtil().waitToConnectToSSID(false);
    final ConnectToNetworkCallbacks mockCallbacks = mock(ConnectToNetworkCallbacks.class);
    getWiseFy().connectToNetwork(TEST_SSID, TEST_TIMEOUT, mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).failureConnectingToNetwork();
    getVerificationUtil().triedToConnectToNetwork();
  }

  @Test
  public void async_failure_nullCallback() {
    getMockWiseFySearchUtil().findSavedNetworkByRegex_success();
    getMockWiseFyConnectionUtil().waitToConnectToSSID(false);
    getNullCallbackUtil().callConnectToNetwork(TEST_SSID);
    getVerificationUtil().triedToConnectToNetwork();
  }

  @Test
  public void async_success() {
    getMockWiseFySearchUtil().findSavedNetworkByRegex_success();
    getMockWiseFyConnectionUtil().waitToConnectToSSID(true);
    final ConnectToNetworkCallbacks mockCallbacks = mock(ConnectToNetworkCallbacks.class);
    getWiseFy().connectToNetwork(TEST_SSID, TEST_TIMEOUT, mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).connectedToNetwork();
    getVerificationUtil().triedToConnectToNetwork();
  }

  @Test
  public void async_success_nullCallback() {
    getMockWiseFySearchUtil().findSavedNetworkByRegex_success();
    getMockWiseFyConnectionUtil().waitToConnectToSSID(true);
    getNullCallbackUtil().callConnectToNetwork(TEST_SSID);
    getVerificationUtil().triedToConnectToNetwork();
  }
}
