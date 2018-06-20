package com.isupatches.wisefy.main;

import static org.junit.Assert.assertEquals;

import com.isupatches.wisefy.AbstractBaseAndroidJUnit4TestClass;
import com.isupatches.wisefy.WiseFy;

import org.junit.Test;

/**
 * Tests the ability to determine if wifi is enabled on a device.
 *
 * @see WiseFy#isWifiEnabled()
 *
 * @author Patches
 */
public class IsWifiEnabledTests extends AbstractBaseAndroidJUnit4TestClass {

  public IsWifiEnabledTests() {
    // No-op
  }

  @Test
  public void failure_prechecks() {
    getMockWiseFyPrechecksUtil().isWifiEnabled_failure();
    assertEquals(false, getWiseFy().isWifiEnabled());
  }

  @Test
  public void failure() {
    getMockNetworkUtil().isWifiEnabled(false);
    assertEquals(false, getWiseFy().isWifiEnabled());
  }

  @Test
  public void success() {
    getMockNetworkUtil().isWifiEnabled(true);
    assertEquals(true, getWiseFy().isWifiEnabled());
  }
}
