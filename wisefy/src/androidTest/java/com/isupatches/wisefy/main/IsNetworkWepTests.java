package com.isupatches.wisefy.main;

import static org.junit.Assert.assertEquals;

import android.net.wifi.ScanResult;

import com.isupatches.wisefy.AbstractBaseAndroidJUnit4TestClass;
import com.isupatches.wisefy.GeneratorUtil;
import com.isupatches.wisefy.WiseFy;
import com.isupatches.wisefy.constants.Capabilities;

import org.junit.Test;

/**
 * Tests the ability to determine if a network has WEP security.
 *
 * @see WiseFy#isNetworkWEP(ScanResult)
 *
 * @author Patches
 */
public class IsNetworkWepTests extends AbstractBaseAndroidJUnit4TestClass {

  public IsNetworkWepTests() {
    // No-op
  }

  @Test
  public void failure_differentCapability() {
    final ScanResult scanResult = GeneratorUtil.createMockAccessPointWithCapabilities("Other");
    assertEquals(false, getWiseFy().isNetworkWEP(scanResult));
  }

  @Test
  public void failure_emptyCapabilities() {
    final ScanResult scanResult = GeneratorUtil.createMockAccessPointWithCapabilities("");
    assertEquals(false, getWiseFy().isNetworkWEP(scanResult));
  }

  @Test
  public void failure_nullCapabilities() {
    final ScanResult scanResult = GeneratorUtil.createMockAccessPointWithCapabilities(null);
    assertEquals(false, getWiseFy().isNetworkWEP(scanResult));
  }

  @Test
  public void failure_nullScanResult() {
    assertEquals(false, getWiseFy().isNetworkWEP(null));
  }

  @Test
  public void success() {
    final ScanResult scanResult = GeneratorUtil.createMockAccessPointWithCapabilities(Capabilities.WEP);
    assertEquals(true, getWiseFy().isNetworkWEP(scanResult));
  }
}
