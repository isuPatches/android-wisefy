package com.isupatches.wisefy.main;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.isupatches.wisefy.AbstractBaseAndroidJUnit4TestClass;
import com.isupatches.wisefy.WiseFy;

import org.junit.Test;

/**
 * Tests the ability to determine if a device is connected to a mobile network.
 *
 * @see WiseFy#isDeviceConnectedToMobileNetwork()
 */
public class IsDeviceConnectedToMobileNetworkTests extends AbstractBaseAndroidJUnit4TestClass {

  public IsDeviceConnectedToMobileNetworkTests() {
    // No-op
  }

  @Test
  public void failure_prechecks() {
    getMockWiseFyPrechecksUtil().isDeviceConnectedToMobileNetwork_failure();
    assertFalse(getWiseFy().isDeviceConnectedToMobileNetwork());
  }

  @Test
  public void failure() {
    getMockWiseFyConnectionUtil().isNetworkConnectedAndMatchesType(false);
    assertFalse(getWiseFy().isDeviceConnectedToMobileNetwork());
  }

  @Test
  public void success() {
    getMockWiseFyPrerequisitesUtil().activeNetwork();
    getMockWiseFyConnectionUtil().isNetworkConnectedAndMatchesType(true);
    assertTrue(getWiseFy().isDeviceConnectedToMobileNetwork());
  }
}
