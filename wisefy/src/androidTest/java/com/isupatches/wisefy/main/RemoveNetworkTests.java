package com.isupatches.wisefy.main;

import static com.isupatches.wisefy.TestUtils.TEST_SSID;
import static com.isupatches.wisefy.TestUtils.VERIFICATION_SUCCESS_TIMEOUT;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

import com.isupatches.wisefy.AbstractBaseAndroidJUnit4TestClass;
import com.isupatches.wisefy.WiseFy;
import com.isupatches.wisefy.callbacks.RemoveNetworkCallbacks;
import com.isupatches.wisefy.constants.WiseFyCodeDefs;

import org.junit.Test;

/**
 * Tests the ability to remove a network from a device's list of saved networks.
 *
 * @see WiseFy#removeNetwork(String)
 * @see WiseFy#removeNetwork(String, RemoveNetworkCallbacks)
 */
public class RemoveNetworkTests extends AbstractBaseAndroidJUnit4TestClass {

  public RemoveNetworkTests() {
    // No-op
  }

  @Test
  public void sync_failure_prechecks() {
    getMockWiseFyPrechecksUtil().removeNetwork_failure();
    assertEquals(false, getWiseFy().removeNetwork(TEST_SSID));
    getVerificationUtil().didNotTryToRemoveNetwork();
  }

  @Test
  public void sync_failure_noSavedNetwork() {
    getMockWiseFySearchUtil().findSavedNetworkByRegex_null();
    assertEquals(false, getWiseFy().removeNetwork(TEST_SSID));
    getVerificationUtil().didNotTryToRemoveNetwork();
  }

  @Test
  public void sync_failure() {
    getMockWiseFySearchUtil().findSavedNetworkByRegex_success();
    getMockNetworkUtil().removeNetwork(false);
    assertEquals(false, getWiseFy().removeNetwork(TEST_SSID));
    getVerificationUtil().triedToRemoveNetwork();
  }

  @Test
  public void sync_success() {
    getMockWiseFySearchUtil().findSavedNetworkByRegex_success();
    getMockNetworkUtil().removeNetwork(true);
    assertEquals(true, getWiseFy().removeNetwork(TEST_SSID));
    getVerificationUtil().triedToRemoveNetwork();
  }

  @Test
  public void async_failure_prechecks() {
    getMockWiseFyPrechecksUtil().removeNetwork_failure();
    final RemoveNetworkCallbacks mockCallbacks = mock(RemoveNetworkCallbacks.class);
    getWiseFy().removeNetwork(TEST_SSID, mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).removeNetworkWiseFyFailure(WiseFyCodeDefs.MISSING_PREREQUISITE);
    getVerificationUtil().didNotTryToRemoveNetwork();
  }

  @Test
  public void async_failure_prechecks_nullCallback() {
    getMockWiseFyPrechecksUtil().removeNetwork_failure();
    getNullCallbackUtil().callRemoveNetwork(TEST_SSID);
    getVerificationUtil().didNotTryToRemoveNetwork();
  }

  @Test
  public void async_failure_noSavedNetwork() {
    getMockWiseFySearchUtil().findSavedNetworkByRegex_null();
    final RemoveNetworkCallbacks mockCallbacks = mock(RemoveNetworkCallbacks.class);
    getWiseFy().removeNetwork(TEST_SSID, mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).networkNotFoundToRemove();
    getVerificationUtil().didNotTryToRemoveNetwork();
  }

  @Test
  public void async_failure_noSavedNetwork_nullCallback() {
    getMockWiseFySearchUtil().findSavedNetworkByRegex_null();
    getNullCallbackUtil().callRemoveNetwork(TEST_SSID);
    getVerificationUtil().didNotTryToRemoveNetwork();
  }

  @Test
  public void async_failure() {
    getMockWiseFySearchUtil().findSavedNetworkByRegex_success();
    getMockNetworkUtil().removeNetwork(false);
    final RemoveNetworkCallbacks mockCallbacks = mock(RemoveNetworkCallbacks.class);
    getWiseFy().removeNetwork(TEST_SSID, mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).failureRemovingNetwork();
    getVerificationUtil().triedToRemoveNetwork();
  }

  @Test
  public void async_failure_nullCallback() {
    getMockWiseFySearchUtil().findSavedNetworkByRegex_success();
    getMockNetworkUtil().removeNetwork(false);
    getNullCallbackUtil().callRemoveNetwork(TEST_SSID);
    getVerificationUtil().triedToRemoveNetwork();
  }

  @Test
  public void async_success() {
    getMockWiseFySearchUtil().findSavedNetworkByRegex_success();
    getMockNetworkUtil().removeNetwork(true);
    final RemoveNetworkCallbacks mockCallbacks = mock(RemoveNetworkCallbacks.class);
    getWiseFy().removeNetwork(TEST_SSID, mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).networkRemoved();
    getVerificationUtil().triedToRemoveNetwork();
  }

  @Test
  public void async_success_nullCallback() {
    getMockWiseFySearchUtil().findSavedNetworkByRegex_success();
    getMockNetworkUtil().removeNetwork(true);
    getNullCallbackUtil().callRemoveNetwork(TEST_SSID);
    getVerificationUtil().triedToRemoveNetwork();
  }
}
