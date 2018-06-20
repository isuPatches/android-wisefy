package com.isupatches.wisefy.main;

import static com.isupatches.wisefy.TestUtils.OPEN_NETWORK_SSID;
import static com.isupatches.wisefy.TestUtils.VERIFICATION_SUCCESS_TIMEOUT;

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
import com.isupatches.wisefy.callbacks.AddOpenNetworkCallbacks;
import com.isupatches.wisefy.constants.WiseFyCodes;

import org.junit.Test;

/**
 * Tests the ability to add open networks.
 *
 * @see WiseFy#addOpenNetwork(String)
 * @see WiseFy#addOpenNetwork(String, AddOpenNetworkCallbacks)
 *
 * @author Patches
 */
public class AddOpenNetworkTests extends AbstractBaseAndroidJUnit4TestClass {

  public AddOpenNetworkTests() {
    // No-op
  }

  @Test
  public void sync_failure_prechecks() {
    getMockWiseFyPrechecksUtil().addNetwork_failure();
    assertEquals(WiseFyCodes.MISSING_PREREQUISITE, getWiseFy().addOpenNetwork(OPEN_NETWORK_SSID));
    getVerificationUtil().didNoTryToAddNetwork();
  }

  @Test
  public void sync_failure() {
    getMockNetworkUtil().addNetwork_failure();
    assertEquals(WiseFy.WIFI_MANAGER_FAILURE, getWiseFy().addOpenNetwork(OPEN_NETWORK_SSID));
    getVerificationUtil().triedToAddNetwork();
  }

  @Test
  public void sync_success() {
    getMockNetworkUtil().addNetwork_success();
    assertNotEquals(WiseFy.WIFI_MANAGER_FAILURE, getWiseFy().addOpenNetwork(OPEN_NETWORK_SSID));
    getVerificationUtil().triedToAddNetwork();
  }

  @Test
  public void async_failure_prechecks() {
    getMockWiseFyPrechecksUtil().addNetwork_failure();
    final AddOpenNetworkCallbacks mockCallbacks = mock(AddOpenNetworkCallbacks.class);
    getWiseFy().addOpenNetwork(OPEN_NETWORK_SSID, mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).addOpenNetworkWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
    getVerificationUtil().didNoTryToAddNetwork();
  }

  @Test
  public void async_failure_prechecks_nullCallback() {
    getMockWiseFyPrechecksUtil().addNetwork_failure();
    getNullCallbackUtil().callAddOpenNetwork(OPEN_NETWORK_SSID);
    getVerificationUtil().didNoTryToAddNetwork();
  }

  @Test
  public void async_failure() {
    getMockNetworkUtil().addNetwork_failure();
    final AddOpenNetworkCallbacks mockCallbacks = mock(AddOpenNetworkCallbacks.class);
    getWiseFy().addOpenNetwork(OPEN_NETWORK_SSID, mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).failureAddingOpenNetwork(WiseFy.WIFI_MANAGER_FAILURE);
    getVerificationUtil().triedToAddNetwork();
  }

  @Test
  public void async_failure_nullCallback() {
    getMockNetworkUtil().addNetwork_failure();
    getNullCallbackUtil().callAddOpenNetwork(OPEN_NETWORK_SSID);
    getVerificationUtil().triedToAddNetwork();
  }

  @Test
  public void async_success() {
    getMockNetworkUtil().addNetwork_success();
    final AddOpenNetworkCallbacks mockCallbacks = mock(AddOpenNetworkCallbacks.class);
    getWiseFy().addOpenNetwork(OPEN_NETWORK_SSID, mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).openNetworkAdded(anyInt(), any(WifiConfiguration.class));
    getVerificationUtil().triedToAddNetwork();
  }

  @Test
  public void async_success_nullCallback() {
    getMockNetworkUtil().addNetwork_success();
    getNullCallbackUtil().callAddOpenNetwork(OPEN_NETWORK_SSID);
    getVerificationUtil().triedToAddNetwork();
  }
}
