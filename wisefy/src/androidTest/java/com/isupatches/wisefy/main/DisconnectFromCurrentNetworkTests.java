package com.isupatches.wisefy.main;

import static com.isupatches.wisefy.TestUtils.VERIFICATION_SUCCESS_TIMEOUT;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

import com.isupatches.wisefy.AbstractBaseAndroidJUnit4TestClass;
import com.isupatches.wisefy.WiseFy;
import com.isupatches.wisefy.callbacks.DisconnectFromCurrentNetworkCallbacks;
import com.isupatches.wisefy.constants.WiseFyCodes;

import org.junit.Test;

/**
 * Tests the ability to disconnect a device from it's current network.
 *
 * @see WiseFy#disconnectFromCurrentNetwork()
 * @see WiseFy#disconnectFromCurrentNetwork(DisconnectFromCurrentNetworkCallbacks)
 *
 * @author Patches
 */
public class DisconnectFromCurrentNetworkTests extends AbstractBaseAndroidJUnit4TestClass {

  public DisconnectFromCurrentNetworkTests() {
    // No-op
  }

  @Test
  public void sync_failure_prechecks() {
    getMockWiseFyPrechecksUtil().disconnectFromCurrentNetwork_failure();
    assertEquals(false, getWiseFy().disconnectFromCurrentNetwork());
    getVerificationUtil().didNotTryToDisconnectFromCurrentNetwork();
  }

  @Test
  public void sync_failure() {
    getMockNetworkUtil().disconnectFromCurrentNetwork_failure();
    assertEquals(false, getWiseFy().disconnectFromCurrentNetwork());
    getVerificationUtil().triedToDisconnectFromCurrentNetwork();
  }

  @Test
  public void sync_success() {
    getMockNetworkUtil().disconnectFromCurrentNetwork_success();
    assertEquals(true, getWiseFy().disconnectFromCurrentNetwork());
    getVerificationUtil().triedToDisconnectFromCurrentNetwork();
  }

  @Test
  public void async_failure_prechecks() {
    getMockWiseFyPrechecksUtil().disconnectFromCurrentNetwork_failure();
    final DisconnectFromCurrentNetworkCallbacks mockCallbacks = mock(DisconnectFromCurrentNetworkCallbacks.class);
    getWiseFy().disconnectFromCurrentNetwork(mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).disconnectFromCurrentNetworkWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
    getVerificationUtil().didNotTryToDisconnectFromCurrentNetwork();
  }

  @Test
  public void async_failure_prechecks_nullCallback() {
    getMockWiseFyPrechecksUtil().disconnectFromCurrentNetwork_failure();
    getNullCallbackUtil().callDisconnectFromCurrentNetwork();
    getVerificationUtil().didNotTryToDisconnectFromCurrentNetwork();
  }

  @Test
  public void async_failure() {
    getMockNetworkUtil().disconnectFromCurrentNetwork_failure();
    final DisconnectFromCurrentNetworkCallbacks mockCallbacks = mock(DisconnectFromCurrentNetworkCallbacks.class);
    getWiseFy().disconnectFromCurrentNetwork(mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).failureDisconnectingFromCurrentNetwork();
    getVerificationUtil().triedToDisconnectFromCurrentNetwork();
  }

  @Test
  public void async_failure_nullCallback() {
    getMockNetworkUtil().disconnectFromCurrentNetwork_failure();
    getNullCallbackUtil().callDisconnectFromCurrentNetwork();
    getVerificationUtil().triedToDisconnectFromCurrentNetwork();
  }

  @Test
  public void async_success() {
    getMockNetworkUtil().disconnectFromCurrentNetwork_success();
    final DisconnectFromCurrentNetworkCallbacks mockCallbacks = mock(DisconnectFromCurrentNetworkCallbacks.class);
    getWiseFy().disconnectFromCurrentNetwork(mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).disconnectedFromCurrentNetwork();
    getVerificationUtil().triedToDisconnectFromCurrentNetwork();
  }

  @Test
  public void async_success_nullCallback() {
    getMockNetworkUtil().disconnectFromCurrentNetwork_success();
    getNullCallbackUtil().callDisconnectFromCurrentNetwork();
    getVerificationUtil().triedToDisconnectFromCurrentNetwork();
  }
}
