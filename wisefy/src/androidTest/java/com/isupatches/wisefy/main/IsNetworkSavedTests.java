package com.isupatches.wisefy.main;

import static com.isupatches.wisefy.TestUtils.TEST_SSID;

import static org.junit.Assert.assertEquals;

import com.isupatches.wisefy.AbstractBaseAndroidJUnit4TestClass;
import com.isupatches.wisefy.WiseFy;

import org.junit.Test;

/**
 * Tests the ability to determine if a network is in a device's list of saved networks.
 *
 * @see WiseFy#isNetworkSaved(String)
 *
 * @author Patches
 */
public class IsNetworkSavedTests extends AbstractBaseAndroidJUnit4TestClass {

  public IsNetworkSavedTests() {
    // No-op
  }

  @Test
  public void failure_missingPrerequisite() {
    getMockWiseFyPrechecksUtil().isNetworkSaved_failure();
    assertEquals(false, getWiseFy().isNetworkSaved(TEST_SSID));
  }

  @Test
  public void failure() {
    getMockWiseFySearchUtil().isNetworkASavedConfiguration(false);
    assertEquals(false, getWiseFy().isNetworkSaved(TEST_SSID));
  }

  @Test
  public void success() {
    getMockWiseFySearchUtil().isNetworkASavedConfiguration(true);
    assertEquals(true, getWiseFy().isNetworkSaved(TEST_SSID));
  }
}
