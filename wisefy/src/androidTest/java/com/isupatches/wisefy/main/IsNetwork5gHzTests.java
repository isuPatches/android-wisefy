package com.isupatches.wisefy.main;

import static com.isupatches.wisefy.TestUtils.TEST_NETWORK_FREQUENCY_5GHZ;
import static com.isupatches.wisefy.TestUtils.TEST_NETWORK_FREQUENCY_ABOVE_5GHZ;
import static com.isupatches.wisefy.TestUtils.TEST_NETWORK_FREQUENCY_BELOW_5GHZ;

import static org.junit.Assert.assertEquals;

import android.net.wifi.WifiInfo;

import com.isupatches.wisefy.AbstractBaseAndroidJUnit4TestClass;
import com.isupatches.wisefy.WiseFy;

import org.junit.Test;

/**
 * Tests the ability to determine if a network is 5 gHz.
 *
 * @see WiseFy#isNetwork5gHz()
 * @see WiseFy#isNetwork5gHz(WifiInfo)
 *
 * @author Patches
 */
public class IsNetwork5gHzTests extends AbstractBaseAndroidJUnit4TestClass {

  public IsNetwork5gHzTests() {
    // No-op
  }

  @Test
  public void currentNetwork_failure_above5ghz() {
    if (preLollipop()) {
      return;
    }

    getMockNetworkUtil().networkWithFrequency(TEST_NETWORK_FREQUENCY_ABOVE_5GHZ);
    assertEquals(false, getWiseFy().isNetwork5gHz());
  }

  @Test
  public void currentNetwork_failure_below5ghz() {
    if (preLollipop()) {
      return;
    }

    getMockNetworkUtil().networkWithFrequency(TEST_NETWORK_FREQUENCY_BELOW_5GHZ);
    assertEquals(false, getWiseFy().isNetwork5gHz());
  }

  @Test
  public void currentNetwork_failure_null() {
    if (preLollipop()) {
      return;
    }

    getMockNetworkUtil().currentNetwork_null();
    assertEquals(false, getWiseFy().isNetwork5gHz());
  }

  @Test
  public void currentNetwork_success() {
    if (preLollipop()) {
      return;
    }

    getMockNetworkUtil().networkWithFrequency(TEST_NETWORK_FREQUENCY_5GHZ);
    assertEquals(true, getWiseFy().isNetwork5gHz());
  }

  @Test
  public void provideWifiInfo_failure_above5ghz() {
    if (preLollipop()) {
      return;
    }

    final WifiInfo network = getMockNetworkUtil().networkWithFrequency(TEST_NETWORK_FREQUENCY_ABOVE_5GHZ);
    assertEquals(false, getWiseFy().isNetwork5gHz(network));
  }

  @Test
  public void provideWifiInfo_failure_below5ghz() {
    if (preLollipop()) {
      return;
    }

    final WifiInfo network = getMockNetworkUtil().networkWithFrequency(TEST_NETWORK_FREQUENCY_BELOW_5GHZ);
    assertEquals(false, getWiseFy().isNetwork5gHz(network));
  }

  @Test
  public void provideWifiInfo_failure_null() {
    if (preLollipop()) {
      return;
    }

    assertEquals(false, getWiseFy().isNetwork5gHz(null));
  }

  @Test
  public void provideWifiInfo_success() {
    if (preLollipop()) {
      return;
    }

    final WifiInfo mockWifiInfo = getMockNetworkUtil().networkWithFrequency(TEST_NETWORK_FREQUENCY_5GHZ);
    assertEquals(true, getWiseFy().isNetwork5gHz(mockWifiInfo));
  }
}
