package com.isupatches.wisefy.main;

import static org.junit.Assert.assertEquals;

import android.net.wifi.ScanResult;

import com.isupatches.wisefy.AbstractBaseAndroidJUnit4TestClass;
import com.isupatches.wisefy.GeneratorUtil;
import com.isupatches.wisefy.WiseFy;
import com.isupatches.wisefy.constants.Capabilities;

import org.junit.Test;

/**
 * Tests the ability to determine if a network is secure.
 *
 * @see WiseFy#isNetworkSecure(ScanResult)
 *
 * @author Patches
 */
public class IsNetworkSecureTests extends AbstractBaseAndroidJUnit4TestClass {

  public IsNetworkSecureTests() {
    // No-op
  }

  @Test
  public void failure_emptyCapabilities() {
    final ScanResult scanResult = GeneratorUtil.createMockAccessPointWithCapabilities("");
    assertEquals(false, getWiseFy().isNetworkSecure(scanResult));
  }

  @Test
  public void failure_nullCapabilities() {
    final ScanResult scanResult = GeneratorUtil.createMockAccessPointWithCapabilities(null);
    assertEquals(false, getWiseFy().isNetworkSecure(scanResult));
  }

  @Test
  public void failure_nullScanResult() {
    assertEquals(false, getWiseFy().isNetworkSecure(null));
  }

  @Test
  public void success_withEAP() {
    final ScanResult scanResult = GeneratorUtil.createMockAccessPointWithCapabilities(Capabilities.EAP);
    assertEquals(true, getWiseFy().isNetworkSecure(scanResult));
  }

  @Test
  public void success_withPSK() {
    final ScanResult scanResult = GeneratorUtil.createMockAccessPointWithCapabilities(Capabilities.PSK);
    assertEquals(true, getWiseFy().isNetworkSecure(scanResult));
  }

  @Test
  public void success_withWEP() {
    final ScanResult scanResult = GeneratorUtil.createMockAccessPointWithCapabilities(Capabilities.WEP);
    assertEquals(true, getWiseFy().isNetworkSecure(scanResult));
  }

  @Test
  public void success_withWPA() {
    final ScanResult scanResult = GeneratorUtil.createMockAccessPointWithCapabilities(Capabilities.WPA);
    assertEquals(true, getWiseFy().isNetworkSecure(scanResult));
  }

  @Test
  public void success_withWPA2() {
    final ScanResult scanResult = GeneratorUtil.createMockAccessPointWithCapabilities(Capabilities.WPA2);
    assertEquals(true, getWiseFy().isNetworkSecure(scanResult));
  }

  @Test
  public void failure_otherCapabilities() {
    final ScanResult scanResult = GeneratorUtil.createMockAccessPointWithCapabilities("Other");
    assertEquals(false, getWiseFy().isNetworkSecure(scanResult));
  }
}
