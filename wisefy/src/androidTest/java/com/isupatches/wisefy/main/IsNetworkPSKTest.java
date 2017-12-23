package com.isupatches.wisefy.main;

import static org.junit.Assert.assertEquals;

import android.net.wifi.ScanResult;

import com.isupatches.wisefy.AbstractBaseAndroidJUnit4TestClass;
import com.isupatches.wisefy.GeneratorUtil;
import com.isupatches.wisefy.WiseFy;
import com.isupatches.wisefy.constants.CapabilityDefs;

import org.junit.Test;

/**
 * Tests the ability to determine if a network has PSK security.
 *
 * @see WiseFy#isNetworkPSK(ScanResult)
 */
public class IsNetworkPSKTest extends AbstractBaseAndroidJUnit4TestClass {

  public IsNetworkPSKTest() {
    // No-op
  }

  @Test
  public void failure_differentCapability() {
    final ScanResult scanResult = GeneratorUtil.createMockAccessPointWithCapabilities("Other");
    assertEquals(false, getWiseFy().isNetworkPSK(scanResult));
  }

  @Test
  public void failure_emptyCapabilities() {
    final ScanResult scanResult = GeneratorUtil.createMockAccessPointWithCapabilities("");
    assertEquals(false, getWiseFy().isNetworkPSK(scanResult));
  }

  @Test
  public void failure_nullCapabilities() {
    final ScanResult scanResult = GeneratorUtil.createMockAccessPointWithCapabilities(null);
    assertEquals(false, getWiseFy().isNetworkPSK(scanResult));
  }

  @Test
  public void failure_nullScanResult() {
    assertEquals(false, getWiseFy().isNetworkPSK(null));
  }

  @Test
  public void success() {
    final ScanResult scanResult = GeneratorUtil.createMockAccessPointWithCapabilities(CapabilityDefs.PSK);
    assertEquals(true, getWiseFy().isNetworkPSK(scanResult));
  }
}
