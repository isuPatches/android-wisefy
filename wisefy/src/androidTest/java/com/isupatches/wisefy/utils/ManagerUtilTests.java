package com.isupatches.wisefy.utils;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import android.support.test.InstrumentationRegistry;

import com.isupatches.wisefy.AbstractBaseAndroidJUnit4TestClass;

import org.junit.Test;

/**
 * Tests the behavior of the WifiManager and ConnectivityManager provider.
 *
 * @see ManagerUtil
 * @see ManagerUtil#getConnectivityManager()
 * @see ManagerUtil#getWiFiManager()
 *
 * @author Patches
 */
public class ManagerUtilTests extends AbstractBaseAndroidJUnit4TestClass {

  public ManagerUtilTests() {
    // No-op
  }

  @Test
  public void getConnectivityManager_returnsNotNull() {
    final ManagerUtil managerUtil = ManagerUtil.create(InstrumentationRegistry.getTargetContext());
    assertNotNull(managerUtil.getConnectivityManager());
  }

  @Test
  public void getConnectivityManager_returnsNull_noActivity() {
    final ManagerUtil managerUtil = ManagerUtil.create(null);
    assertNull(managerUtil.getConnectivityManager());
  }

  @Test
  public void getWiFiManager_returnsNotNull() {
    final ManagerUtil managerUtil = ManagerUtil.create(InstrumentationRegistry.getTargetContext());
    assertNotNull(managerUtil.getWiFiManager());
  }

  @Test
  public void getWiFiManager_returnsNull_noActivity() {
    final ManagerUtil managerUtil = ManagerUtil.create(null);
    assertNull(managerUtil.getWiFiManager());
  }
}
