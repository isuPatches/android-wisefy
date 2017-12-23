package com.isupatches.wisefy.main;

import static com.isupatches.wisefy.TestUtils.VERIFICATION_SUCCESS_TIMEOUT;
import static com.isupatches.wisefy.TestUtils.WEP_NETWORK_PASSWORD;
import static com.isupatches.wisefy.TestUtils.WEP_NETWORK_SSID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

import android.net.wifi.WifiConfiguration;

import com.isupatches.wisefy.AbstractBaseAndroidJUnit4TestClass;
import com.isupatches.wisefy.WiseFy;
import com.isupatches.wisefy.callbacks.AddWEPNetworkCallbacks;
import com.isupatches.wisefy.constants.WiseFyCodeDefs;

import org.junit.Test;

/**
 * Tests the ability to add WEP networks.
 *
 * @see WiseFy#addWEPNetwork(String, String)
 * @see WiseFy#addWEPNetwork(String, String, AddWEPNetworkCallbacks)
 *
 * @author Patches
 */
public class AddWEPNetworkTests extends AbstractBaseAndroidJUnit4TestClass {

  public AddWEPNetworkTests() {
    // No-op
  }

  @Test
  public void sync_failure_prechecks() {
    getMockWiseFyPrechecksUtil().addNetwork_failure();
    assertEquals(WiseFyCodeDefs.MISSING_PREREQUISITE, getWiseFy().addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD));
    getVerificationUtil().didNoTryToAddNetwork();
  }

  @Test
  public void sync_failure() {
    getMockNetworkUtil().addNetwork_failure();
    assertEquals(WiseFy.WIFI_MANAGER_FAILURE, getWiseFy().addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD));
    getVerificationUtil().triedToAddNetwork();
  }

  @Test
  public void sync_success() {
    getMockNetworkUtil().addNetwork_success();
    assertNotEquals(WiseFy.WIFI_MANAGER_FAILURE, getWiseFy().addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD));
    getVerificationUtil().triedToAddNetwork();
  }

  @Test
  public void async_failure_prechecks() {
    getMockWiseFyPrechecksUtil().addNetwork_failure();
    final AddWEPNetworkCallbacks mockCallbacks = mock(AddWEPNetworkCallbacks.class);
    getWiseFy().addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD, mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).addWEPNetworkWiseFyFailure(WiseFyCodeDefs.MISSING_PREREQUISITE);
    getVerificationUtil().didNoTryToAddNetwork();
  }

  @Test
  public void async_failure_prechecks_nullCallback() {
    getMockWiseFyPrechecksUtil().addNetwork_failure();
    getNullCallbackUtil().callAddWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD);
    getVerificationUtil().didNoTryToAddNetwork();
  }

  @Test
  public void async_failure() {
    getMockNetworkUtil().addNetwork_failure();
    final AddWEPNetworkCallbacks mockCallbacks = mock(AddWEPNetworkCallbacks.class);
    getWiseFy().addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD, mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).failureAddingWEPNetwork(WiseFy.WIFI_MANAGER_FAILURE);
    getVerificationUtil().triedToAddNetwork();
  }

  @Test
  public void async_failure_nullCallback() {
    getMockNetworkUtil().addNetwork_failure();
    getNullCallbackUtil().callAddWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD);
    getVerificationUtil().triedToAddNetwork();
  }

  @Test
  public void async_success() {
    getMockNetworkUtil().addNetwork_success();
    final AddWEPNetworkCallbacks mockCallbacks = mock(AddWEPNetworkCallbacks.class);
    getWiseFy().addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD, mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).wepNetworkAdded(anyInt(), any(WifiConfiguration.class));
    getVerificationUtil().triedToAddNetwork();
  }

  @Test
  public void async_success_nullCallback() {
    getMockNetworkUtil().addNetwork_success();
    getNullCallbackUtil().callAddWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD);
    getVerificationUtil().triedToAddNetwork();
  }
}
