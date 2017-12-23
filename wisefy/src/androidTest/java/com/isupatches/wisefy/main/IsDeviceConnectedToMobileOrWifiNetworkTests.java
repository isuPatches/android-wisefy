package com.isupatches.wisefy.main;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.isupatches.wisefy.AbstractBaseAndroidJUnit4TestClass;
import com.isupatches.wisefy.WiseFy;

import org.junit.Test;

/**
 * Tests the ability to determine if a device is connected to a mobile or wifi network.
 *
 * @see WiseFy#isDeviceConnectedToMobileOrWifiNetwork()
 */
public class IsDeviceConnectedToMobileOrWifiNetworkTests extends AbstractBaseAndroidJUnit4TestClass {

  public IsDeviceConnectedToMobileOrWifiNetworkTests() {
    // No-op
  }

  @Test
  public void failure_missingPrerequisite() {
    getMockWiseFyPrechecksUtil().isDeviceConnectedToMobileOrWifiNetwork_failure();
    assertFalse(getWiseFy().isDeviceConnectedToMobileOrWifiNetwork());
  }

  @Test
  public void failure() {
    getMockWiseFyConnectionUtil().isNetworkConnected(false);
    assertFalse(getWiseFy().isDeviceConnectedToMobileOrWifiNetwork());
  }

  @Test
  public void success() {
    getMockWiseFyPrerequisitesUtil().activeNetwork();
    getMockWiseFyConnectionUtil().isNetworkConnected(true);
    assertTrue(getWiseFy().isDeviceConnectedToMobileOrWifiNetwork());
  }
}
