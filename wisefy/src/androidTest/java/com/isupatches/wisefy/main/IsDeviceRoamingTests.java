package com.isupatches.wisefy.main;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.isupatches.wisefy.AbstractBaseAndroidJUnit4TestClass;
import com.isupatches.wisefy.WiseFy;

import org.junit.Test;

/**
 * Tests the ability to determine if a device is roaming.
 *
 * @see WiseFy#isDeviceRoaming()
 */
public class IsDeviceRoamingTests extends AbstractBaseAndroidJUnit4TestClass {

  public IsDeviceRoamingTests() {
    // No-op
  }

  @Test
  public void failure_missingPrerequisite() {
    getMockWiseFyPrechecksUtil().isDeviceRoaming_failure();
    assertFalse(getWiseFy().isDeviceRoaming());
  }

  @Test
  public void failure_nullActiveNetworkInfo() {
    getMockNetworkUtil().currentNetwork_null();
    assertFalse(getWiseFy().isDeviceRoaming());
  }

  @Test
  public void failure() {
    getMockNetworkUtil().isDeviceRoaming(false);
    assertFalse(getWiseFy().isDeviceRoaming());
  }

  @Test
  public void success() {
    getMockNetworkUtil().isDeviceRoaming(true);
    assertTrue(getWiseFy().isDeviceRoaming());
  }
}
