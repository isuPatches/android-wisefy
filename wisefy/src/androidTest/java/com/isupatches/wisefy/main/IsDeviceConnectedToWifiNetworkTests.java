package com.isupatches.wisefy.main;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.isupatches.wisefy.AbstractBaseAndroidJUnit4TestClass;
import com.isupatches.wisefy.WiseFy;

import org.junit.Test;

/**
 * Tests the ability to determine if a device is connected to a wifi network.
 *
 * @see WiseFy#isDeviceConnectedToWifiNetwork()
 *
 * @author Patches
 */
public class IsDeviceConnectedToWifiNetworkTests extends AbstractBaseAndroidJUnit4TestClass {

  public IsDeviceConnectedToWifiNetworkTests() {
    // No-op
  }

  @Test
  public void failure_missingPrerequisite() {
    getMockWiseFyPrechecksUtil().isDeviceConnectedToWifiNetwork_failure();
    assertFalse(getWiseFy().isDeviceConnectedToWifiNetwork());
  }

  @Test
  public void failure() {
    getMockWiseFyConnectionUtil().isNetworkConnectedAndMatchesType(false);
    assertFalse(getWiseFy().isDeviceConnectedToWifiNetwork());
  }

  @Test
  public void success() {
    getMockWiseFyPrerequisitesUtil().activeNetwork();
    getMockWiseFyConnectionUtil().isNetworkConnectedAndMatchesType(true);
    assertTrue(getWiseFy().isDeviceConnectedToWifiNetwork());
  }
}
