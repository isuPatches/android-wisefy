package com.isupatches.wisefy;

import static org.mockito.Mockito.mock;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that has helpers to create mocks and objects for test classes.
 *
 * @author Patches
 */
public final class GeneratorUtil {

  /**
   * Private constructor.
   */
  private GeneratorUtil() {
    // No-op
  }

  /**
   * Creates a mock of a nearby access point for tests with specified security capabilities.
   *
   * @param capabilities The security attributes you want the access point to have
   *
   * @return ScanResult - A mock access point with the given capabilities
   *
   * @see ScanResult
   */
  @NonNull
  public static ScanResult createMockAccessPointWithCapabilities(@Nullable final String capabilities) {
    final ScanResult scanResult = mock(ScanResult.class);
    scanResult.capabilities = capabilities;
    return scanResult;
  }

  /**
   * Mocks two nearby access points given ssids and rssi levels.
   *
   * @param ssid1 - SSID of access points 1
   * @param rssi1 - RSSI level of access point 1
   * @param ssid2 - SSID of access point 2
   * @param rssi2 - RSSI level of access point 2
   *
   * @return List of ScanResults - The mocked access points
   *
   * @see ScanResult
   */
  @NonNull
  static List<ScanResult> createMockAccessPointList(@NonNull final String ssid1, final int rssi1,
                                                    @NonNull final String ssid2, final int rssi2) {
    final List<ScanResult> accessPoints = new ArrayList<>();
    accessPoints.add(createMockAccessPointWithSSIDAndRSSI(ssid1, rssi1));
    accessPoints.add(createMockAccessPointWithSSIDAndRSSI(ssid2, rssi2));
    return accessPoints;
  }

  /**
   * Creates a mock of a nearby access point for tests with a specified SSID.
   *
   * @param ssid Tne ssid for the access point
   *
   * @return ScanResult - A mock object representing a nearby access point
   *
   * @see ScanResult
   */
  @NonNull
  static ScanResult createMockAccessPointWithSSID(@Nullable final String ssid) {
    final ScanResult scanResult = mock(ScanResult.class);
    scanResult.SSID = ssid;
    return scanResult;
  }

  /**
   * Creates a mock of a nearby access point.
   *
   * @param ssid Tne ssid for the access point
   * @param rssi The rssi level for the access point
   *
   * @return ScanResult - A mock object representing a nearby access point
   *
   * @see ScanResult
   */
  @NonNull
  static ScanResult createMockAccessPointWithSSIDAndRSSI(@NonNull final String ssid, final int rssi) {
    final ScanResult scanResult = mock(ScanResult.class);
    scanResult.SSID = ssid;
    scanResult.level = rssi;
    return scanResult;
  }

  /**
   * Creates a saved network configuration.
   *
   * @param ssid The ssid for the saved network
   *
   * @return WifiConfiguration - Saved network
   *
   * @see WifiConfiguration
   */
  @NonNull
  static WifiConfiguration createSavedNetwork(@Nullable final String ssid) {
    final WifiConfiguration wiFiConfiguration = new WifiConfiguration();
    wiFiConfiguration.SSID = ssid;
    return wiFiConfiguration;
  }
}
