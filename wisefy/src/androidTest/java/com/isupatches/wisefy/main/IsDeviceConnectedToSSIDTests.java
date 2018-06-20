package com.isupatches.wisefy.main;

import static com.isupatches.wisefy.TestUtils.TEST_SSID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.isupatches.wisefy.AbstractBaseAndroidJUnit4TestClass;
import com.isupatches.wisefy.WiseFy;

import org.junit.Test;

/**
 * Tests the ability to determine if a device is connected to an SSID.
 *
 * @see WiseFy#isDeviceConnectedToSSID(String)
 *
 * @author Patches
 */
public class IsDeviceConnectedToSSIDTests extends AbstractBaseAndroidJUnit4TestClass {

  public IsDeviceConnectedToSSIDTests() {
    // No-op
  }

  @Test
  public void failure_prechecks() {
    getMockWiseFyPrechecksUtil().isDeviceConnectedToSSIDChecks_failure();
    assertFalse(getWiseFy().isDeviceConnectedToSSID(TEST_SSID));
  }

  @Test
  public void failure() {
    getMockWiseFyConnectionUtil().isCurrentNetworkConnectedToSSID(false);
    assertFalse(getWiseFy().isDeviceConnectedToSSID(TEST_SSID));
  }

  @Test
  public void success() {
    getMockWiseFyConnectionUtil().isCurrentNetworkConnectedToSSID(true);
    assertTrue(getWiseFy().isDeviceConnectedToSSID(TEST_SSID));
  }
}
