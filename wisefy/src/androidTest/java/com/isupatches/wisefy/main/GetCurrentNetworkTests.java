package com.isupatches.wisefy.main;

import static com.isupatches.wisefy.TestUtils.TEST_SSID;
import static com.isupatches.wisefy.TestUtils.VERIFICATION_SUCCESS_TIMEOUT;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

import android.net.wifi.WifiInfo;

import com.isupatches.wisefy.AbstractBaseAndroidJUnit4TestClass;
import com.isupatches.wisefy.WiseFy;
import com.isupatches.wisefy.callbacks.GetCurrentNetworkCallbacks;
import com.isupatches.wisefy.constants.WiseFyCodes;

import org.junit.Test;

/**
 * Tests the ability to retrieve a device's current network.
 *
 * @see WiseFy#getCurrentNetwork()
 * @see WiseFy#getCurrentNetwork(GetCurrentNetworkCallbacks)
 *
 * @author Patches
 */
public class GetCurrentNetworkTests extends AbstractBaseAndroidJUnit4TestClass {

  public GetCurrentNetworkTests() {
    // No-op
  }

  @Test
  public void sync_failure_prechecks() {
    getMockWiseFyPrechecksUtil().getCurrentNetwork_failure();
    assertEquals(null, getWiseFy().getCurrentNetwork());
    getVerificationUtil().didNotTryToGetCurrentNetwork();
  }

  @Test
  public void sync_success() {
    getMockNetworkUtil().currentNetwork(TEST_SSID);
    final WifiInfo currentNetwork = getWiseFy().getCurrentNetwork();
    if (currentNetwork != null) {
      assertEquals(TEST_SSID, currentNetwork.getSSID());
      getVerificationUtil().triedToGetCurrentNetwork();
    } else {
      fail();
    }
  }

  @Test
  public void async_failure_prechecks() {
    getMockWiseFyPrechecksUtil().getCurrentNetwork_failure();
    final GetCurrentNetworkCallbacks mockCallbacks = mock(GetCurrentNetworkCallbacks.class);
    getWiseFy().getCurrentNetwork(mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).getCurrentNetworkWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
    getVerificationUtil().didNotTryToGetCurrentNetwork();
  }

  @Test
  public void async_failure_prechecks_nullCallbacks() {
    getMockWiseFyPrechecksUtil().getCurrentNetwork_failure();
    getNullCallbackUtil().callGetCurrentNetwork();
    getVerificationUtil().didNotTryToGetCurrentNetwork();
  }

  @Test
  public void async_success() {
    final WifiInfo currentNetwork = getMockNetworkUtil().currentNetwork(TEST_SSID);
    final GetCurrentNetworkCallbacks mockCallbacks = mock(GetCurrentNetworkCallbacks.class);
    getWiseFy().getCurrentNetwork(mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).retrievedCurrentNetwork(currentNetwork);
    getVerificationUtil().triedToGetCurrentNetwork();
  }

  @Test
  public void async_success_nullCallback() {
    getMockNetworkUtil().currentNetwork(TEST_SSID);
    getNullCallbackUtil().callGetCurrentNetwork();
    getVerificationUtil().didNotTryToGetCurrentNetwork();
  }
}
