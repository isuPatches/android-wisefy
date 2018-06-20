package com.isupatches.wisefy.main;

import static com.isupatches.wisefy.TestUtils.TEST_NETWORK_FREQUENCY_24GHZ;
import static com.isupatches.wisefy.TestUtils.VERIFICATION_FAILURE_TIMEOUT;
import static com.isupatches.wisefy.TestUtils.VERIFICATION_SUCCESS_TIMEOUT;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import static org.mockito.Mockito.after;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

import android.net.wifi.WifiInfo;

import com.isupatches.wisefy.AbstractBaseAndroidJUnit4TestClass;
import com.isupatches.wisefy.WiseFy;
import com.isupatches.wisefy.callbacks.GetFrequencyCallbacks;
import com.isupatches.wisefy.constants.WiseFyCodes;

import org.junit.Test;

/**
 * Tests the ability to retrieve a network's frequency.
 *
 * @see WiseFy#getFrequency()
 * @see WiseFy#getFrequency(GetFrequencyCallbacks)
 * @see WiseFy#getFrequency(WifiInfo)
 * @see WiseFy#getFrequency(WifiInfo, GetFrequencyCallbacks)
 *
 * @author Patches
 */
public class GetFrequencyTests extends AbstractBaseAndroidJUnit4TestClass {

  public GetFrequencyTests() {
    // No-op
  }

  @Test
  public void sync_getFrequency_currentNetwork_failure() {
    if (preLollipop()) {
      return;
    }

    getMockNetworkUtil().currentNetwork_null();
    assertEquals(null, getWiseFy().getFrequency());
    getVerificationUtil().triedToGetCurrentNetwork();
  }

  @Test
  public void sync_getFrequency_currentNetwork_success() {
    if (preLollipop()) {
      return;
    }

    final WifiInfo wifiInfo = getMockNetworkUtil().networkWithFrequency(TEST_NETWORK_FREQUENCY_24GHZ);
    final Integer frequency = getWiseFy().getFrequency();
    if (frequency != null) {
      assertEquals(TEST_NETWORK_FREQUENCY_24GHZ, (int) frequency);
      getVerificationUtil().triedToGetCurrentNetwork();
      verify(wifiInfo, timeout(VERIFICATION_SUCCESS_TIMEOUT)).getFrequency();
    } else {
      fail();
    }
  }

  @Test
  public void async_getFrequency_currentNetwork_failure() {
    if (preLollipop()) {
      return;
    }

    getMockNetworkUtil().currentNetwork_null();
    final GetFrequencyCallbacks mockCallbacks = mock(GetFrequencyCallbacks.class);
    getWiseFy().getFrequency(mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).failureGettingFrequency();
    getVerificationUtil().triedToGetCurrentNetwork();
  }

  @Test
  public void async_getFrequency_currentNetwork_failure_nullCallback() {
    if (preLollipop()) {
      return;
    }

    getMockNetworkUtil().currentNetwork_null();
    getNullCallbackUtil().callGetFrequency();
    getVerificationUtil().triedToGetCurrentNetwork();
  }

  @Test
  public void async_getFrequency_currentNetwork_success() {
    if (preLollipop()) {
      return;
    }

    final WifiInfo mockWifiInfo = getMockNetworkUtil().networkWithFrequency(TEST_NETWORK_FREQUENCY_24GHZ);
    final GetFrequencyCallbacks mockCallbacks = mock(GetFrequencyCallbacks.class);
    getWiseFy().getFrequency(mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).retrievedFrequency(TEST_NETWORK_FREQUENCY_24GHZ);
    getVerificationUtil().triedToGetCurrentNetwork();
    verify(mockWifiInfo, timeout(VERIFICATION_SUCCESS_TIMEOUT)).getFrequency();
  }

  @Test
  public void async_getFrequency_currentNetwork_success_nullCallback() {
    if (preLollipop()) {
      return;
    }

    getNullCallbackUtil().callGetFrequency();
    getVerificationUtil().triedToGetCurrentNetwork();
  }

  @Test
  public void sync_getFrequency_networkProvided_failure() {
    if (preLollipop()) {
      return;
    }

    getMockNetworkUtil().currentNetwork_null();
    assertEquals(null, getWiseFy().getFrequency((WifiInfo) null));
  }

  @Test
  public void sync_getFrequency_networkProvided_success() {
    if (preLollipop()) {
      return;
    }

    final WifiInfo mockWifiInfo = getMockNetworkUtil().networkWithFrequency(TEST_NETWORK_FREQUENCY_24GHZ);
    final Integer frequency = getWiseFy().getFrequency(mockWifiInfo);
    if (frequency != null) {
      assertEquals(TEST_NETWORK_FREQUENCY_24GHZ, (int) frequency);
    } else {
      fail();
    }
  }

  @Test
  public void async_getFrequency_networkProvided_failure() {
    if (preLollipop()) {
      return;
    }

    getMockNetworkUtil().currentNetwork_null();
    final GetFrequencyCallbacks mockCallbacks = mock(GetFrequencyCallbacks.class);
    getWiseFy().getFrequency(null, mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).getFrequencyWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
  }

  @Test
  public void async_getFrequency_networkProvided_failure_nullCallback() {
    if (preLollipop()) {
      return;
    }

    getMockNetworkUtil().currentNetwork_null();
    getNullCallbackUtil().callGetFrequency_networkProvided(null);
  }

  @Test
  public void async_getFrequency_networkProvided_success() {
    if (preLollipop()) {
      return;
    }

    final WifiInfo mockWifiInfo = getMockNetworkUtil().networkWithFrequency(TEST_NETWORK_FREQUENCY_24GHZ);
    final GetFrequencyCallbacks mockCallbacks = mock(GetFrequencyCallbacks.class);
    getWiseFy().getFrequency(mockWifiInfo, mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).retrievedFrequency(TEST_NETWORK_FREQUENCY_24GHZ);
    verify(mockWifiInfo, timeout(VERIFICATION_SUCCESS_TIMEOUT)).getFrequency();
  }

  @Test
  public void async_getFrequency_networkProvided_success_nullCallback() {
    if (preLollipop()) {
      return;
    }

    final WifiInfo mockWifiInfo = getMockNetworkUtil().networkWithFrequency(TEST_NETWORK_FREQUENCY_24GHZ);
    getNullCallbackUtil().callGetFrequency_networkProvided(mockWifiInfo);
    verify(mockWifiInfo, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).getFrequency();
  }
}
