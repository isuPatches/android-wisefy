package com.isupatches.wisefy.main;

import static com.isupatches.wisefy.TestUtils.VERIFICATION_SUCCESS_TIMEOUT;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

import com.isupatches.wisefy.AbstractBaseAndroidJUnit4TestClass;
import com.isupatches.wisefy.WiseFy;
import com.isupatches.wisefy.callbacks.EnableWifiCallbacks;
import com.isupatches.wisefy.constants.WiseFyCodeDefs;

import org.junit.Test;

/**
 * Tests the ability to enable a device's wifi.
 *
 * @see WiseFy#enableWifi()
 * @see WiseFy#enableWifi(EnableWifiCallbacks)
 *
 * @author Patches
 */
public class EnableWifiTests extends AbstractBaseAndroidJUnit4TestClass {

  public EnableWifiTests() {
    // No-op
  }

  @Test
  public void sync_failure_prechecks() {
    getMockWiseFyPrechecksUtil().enableWifi_failure();
    assertEquals(false, getWiseFy().enableWifi());
    getVerificationUtil().didNotTryToEnableWifi();
  }

  @Test
  public void sync_failure() {
    getMockNetworkUtil().enableWifi_failure();
    assertEquals(false, getWiseFy().enableWifi());
    getVerificationUtil().triedToEnableWifi();
  }

  @Test
  public void sync_success() {
    getMockNetworkUtil().enableWifi_success();
    assertEquals(true, getWiseFy().enableWifi());
    getVerificationUtil().triedToEnableWifi();
  }

  @Test
  public void async_failure_prechecks() {
    getMockWiseFyPrechecksUtil().enableWifi_failure();
    final EnableWifiCallbacks mockCallbacks = mock(EnableWifiCallbacks.class);
    getWiseFy().enableWifi(mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).enableWifiWiseFyFailure(WiseFyCodeDefs.MISSING_PREREQUISITE);
    getVerificationUtil().didNotTryToEnableWifi();
  }

  @Test
  public void async_failure_prechecks_nullCallback() {
    getMockWiseFyPrechecksUtil().enableWifi_failure();
    getNullCallbackUtil().callEnableWifi();
    getVerificationUtil().didNotTryToEnableWifi();
  }

  @Test
  public void async_failure() {
    getMockNetworkUtil().enableWifi_failure();
    final EnableWifiCallbacks mockCallbacks = mock(EnableWifiCallbacks.class);
    getWiseFy().enableWifi(mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).failureEnablingWifi();
    getVerificationUtil().triedToEnableWifi();
  }

  @Test
  public void async_failure_nullCallback() {
    getMockNetworkUtil().enableWifi_failure();
    getNullCallbackUtil().callEnableWifi();
    getVerificationUtil().triedToEnableWifi();
  }

  @Test
  public void async_success() {
    getMockNetworkUtil().enableWifi_success();
    final EnableWifiCallbacks mockCallbacks = mock(EnableWifiCallbacks.class);
    getWiseFy().enableWifi(mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).wifiEnabled();
    getVerificationUtil().triedToEnableWifi();
  }

  @Test
  public void async_success_nullCallback() {
    getMockNetworkUtil().enableWifi_success();
    getNullCallbackUtil().callEnableWifi();
    getVerificationUtil().triedToEnableWifi();
  }
}
