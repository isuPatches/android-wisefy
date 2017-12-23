package com.isupatches.wisefy.main;

import static com.isupatches.wisefy.TestUtils.VERIFICATION_SUCCESS_TIMEOUT;
import static com.isupatches.wisefy.TestUtils.WPA2_NETWORK_PASSWORD;
import static com.isupatches.wisefy.TestUtils.WPA2_NETWORK_SSID;

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
import com.isupatches.wisefy.callbacks.AddWPA2NetworkCallbacks;
import com.isupatches.wisefy.constants.WiseFyCodeDefs;

import org.junit.Test;

/**
 * Tests the ability to add WPA2 networks.
 *
 * @see WiseFy#addWPA2Network(String, String)
 * @see WiseFy#addWPA2Network(String, String, AddWPA2NetworkCallbacks)
 *
 * @author Patches
 */
public class AddWPA2NetworkTests extends AbstractBaseAndroidJUnit4TestClass {

  public AddWPA2NetworkTests() {
    // No-op
  }

  @Test
  public void sync_failure_prechecks() {
    getMockWiseFyPrechecksUtil().addNetwork_failure();
    assertEquals(WiseFyCodeDefs.MISSING_PREREQUISITE, getWiseFy().addWPA2Network(WPA2_NETWORK_SSID, WPA2_NETWORK_PASSWORD));
    getVerificationUtil().didNoTryToAddNetwork();
  }

  @Test
  public void sync_failure() {
    getMockNetworkUtil().addNetwork_failure();
    assertEquals(WiseFy.WIFI_MANAGER_FAILURE, getWiseFy().addWPA2Network(WPA2_NETWORK_SSID, WPA2_NETWORK_PASSWORD));
    getVerificationUtil().triedToAddNetwork();
  }

  @Test
  public void sync_success() {
    getMockNetworkUtil().addNetwork_success();
    assertNotEquals(WiseFy.WIFI_MANAGER_FAILURE, getWiseFy().addWPA2Network(WPA2_NETWORK_SSID, WPA2_NETWORK_PASSWORD));
    getVerificationUtil().triedToAddNetwork();
  }

  @Test
  public void async_failure_prechecks() {
    getMockWiseFyPrechecksUtil().addNetwork_failure();
    final AddWPA2NetworkCallbacks mockCallbacks = mock(AddWPA2NetworkCallbacks.class);
    getWiseFy().addWPA2Network(WPA2_NETWORK_SSID, WPA2_NETWORK_PASSWORD, mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).addWPA2NetworkWiseFyFailure(WiseFyCodeDefs.MISSING_PREREQUISITE);
    getVerificationUtil().didNoTryToAddNetwork();
  }

  @Test
  public void async_failure_prechecks_nullCallback() {
    getMockWiseFyPrechecksUtil().addNetwork_failure();
    getNullCallbackUtil().callAddWPA2Network(WPA2_NETWORK_SSID, WPA2_NETWORK_PASSWORD);
    getVerificationUtil().didNoTryToAddNetwork();
  }

  @Test
  public void async_failure() {
    getMockNetworkUtil().addNetwork_failure();
    final AddWPA2NetworkCallbacks mockCallbacks = mock(AddWPA2NetworkCallbacks.class);
    getWiseFy().addWPA2Network(WPA2_NETWORK_SSID, WPA2_NETWORK_PASSWORD, mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).failureAddingWPA2Network(WiseFy.WIFI_MANAGER_FAILURE);
    getVerificationUtil().triedToAddNetwork();
  }

  @Test
  public void async_failure_nullCallback() {
    getMockNetworkUtil().addNetwork_failure();
    getNullCallbackUtil().callAddWPA2Network(WPA2_NETWORK_SSID, WPA2_NETWORK_PASSWORD);
    getVerificationUtil().triedToAddNetwork();
  }

  @Test
  public void async_success() {
    getMockNetworkUtil().addNetwork_success();
    final AddWPA2NetworkCallbacks mockCallbacks = mock(AddWPA2NetworkCallbacks.class);
    getWiseFy().addWPA2Network(WPA2_NETWORK_SSID, WPA2_NETWORK_PASSWORD, mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).wpa2NetworkAdded(anyInt(), any(WifiConfiguration.class));
    getVerificationUtil().triedToAddNetwork();
  }

  @Test
  public void async_success_nullCallback() {
    getMockNetworkUtil().addNetwork_success();
    getNullCallbackUtil().callAddWPA2Network(WPA2_NETWORK_SSID, WPA2_NETWORK_PASSWORD);
    getVerificationUtil().triedToAddNetwork();
  }
}
