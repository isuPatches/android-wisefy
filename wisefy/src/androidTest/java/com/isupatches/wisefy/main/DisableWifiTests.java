package com.isupatches.wisefy.main;

import static com.isupatches.wisefy.TestUtils.VERIFICATION_SUCCESS_TIMEOUT;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

import com.isupatches.wisefy.AbstractBaseAndroidJUnit4TestClass;
import com.isupatches.wisefy.WiseFy;
import com.isupatches.wisefy.callbacks.DisableWifiCallbacks;
import com.isupatches.wisefy.constants.WiseFyCodeDefs;

import org.junit.Test;

/**
 * Tests the ability to disable a device's wifi.
 *
 * @see WiseFy#disableWifi()
 * @see WiseFy#disableWifi(DisableWifiCallbacks)
 *
 * @author Patches
 */
public class DisableWifiTests extends AbstractBaseAndroidJUnit4TestClass {

  public DisableWifiTests() {
    // No-op
  }

  @Test
  public void sync_failure_prechecks() {
    getMockWiseFyPrechecksUtil().disableWifi_failure();
    assertEquals(false, getWiseFy().disableWifi());
    getVerificationUtil().didNotTryToDisableWifi();
  }

  @Test
  public void sync_failure() {
    getMockNetworkUtil().disableWifi_failure();
    assertEquals(false, getWiseFy().disableWifi());
    getVerificationUtil().triedToDisableWifi();
  }

  @Test
  public void sync_success() {
    getMockNetworkUtil().disableWifi_success();
    assertEquals(true, getWiseFy().disableWifi());
    getVerificationUtil().triedToDisableWifi();
  }

  @Test
  public void async_failure_prechecks() {
    getMockWiseFyPrechecksUtil().disableWifi_failure();
    final DisableWifiCallbacks mockCallbacks = mock(DisableWifiCallbacks.class);
    getWiseFy().disableWifi(mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).disableWifiWiseFyFailure(WiseFyCodeDefs.MISSING_PREREQUISITE);
    getVerificationUtil().didNotTryToDisableWifi();
  }

  @Test
  public void async_failure_prechecks_nullCallback() {
    getMockWiseFyPrechecksUtil().disableWifi_failure();
    getNullCallbackUtil().callDisableWifi();
    getVerificationUtil().didNotTryToDisableWifi();
  }

  @Test
  public void async_failure() {
    getMockNetworkUtil().disableWifi_failure();
    final DisableWifiCallbacks mockCallbacks = mock(DisableWifiCallbacks.class);
    getWiseFy().disableWifi(mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).failureDisablingWifi();
    getVerificationUtil().triedToDisableWifi();
  }

  @Test
  public void async_failure_nullCallback() {
    getMockNetworkUtil().disableWifi_failure();
    getNullCallbackUtil().callDisableWifi();
    getVerificationUtil().triedToDisableWifi();
  }

  @Test
  public void async_success() {
    getMockNetworkUtil().disableWifi_success();
    final DisableWifiCallbacks mockCallbacks = mock(DisableWifiCallbacks.class);
    getWiseFy().disableWifi(mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).wifiDisabled();
    getVerificationUtil().triedToDisableWifi();
  }

  @Test
  public void async_success_nullCallback() {
    getMockNetworkUtil().disableWifi_success();
    getNullCallbackUtil().callDisableWifi();
    getVerificationUtil().triedToDisableWifi();
  }
}
