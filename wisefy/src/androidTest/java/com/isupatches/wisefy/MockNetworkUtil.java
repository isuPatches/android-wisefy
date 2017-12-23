package com.isupatches.wisefy;

import static com.isupatches.wisefy.TestUtils.TEST_RSSI_LEVEL;
import static com.isupatches.wisefy.TestUtils.TEST_RSSI_LEVEL_HIGH;
import static com.isupatches.wisefy.TestUtils.TEST_RSSI_LEVEL_LOW;
import static com.isupatches.wisefy.TestUtils.TEST_SSID;
import static com.isupatches.wisefy.TestUtils.TEST_SSID2;
import static com.isupatches.wisefy.TestUtils.TEST_SSID3;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that mocks lower level returns from ConnectivityManager and WifiManager.
 *
 * @see ConnectivityManager
 * @see WifiManager
 *
 * @author Patches
 */
public final class MockNetworkUtil {

  private final ConnectivityManager mockConnectivityManager;
  private final WifiManager mockWifiManager;

  private ScanResult expectedNearbyAccessPoint;
  private List<ScanResult> expectedNearbyAccessPoints;

  private WifiConfiguration expectedSavedNetwork;
  private List<WifiConfiguration> expectedSavedNetworks;

  private List<String> expectedSSIDs;

  /**
   * Constructor.
   *
   * @param mockConnectivityManager The mock ConnectivityManager instance to use
   * @param mockWifiManager The mock WifiManager instance to use
   */
  MockNetworkUtil(final ConnectivityManager mockConnectivityManager, final WifiManager mockWifiManager) {
    this.mockConnectivityManager = mockConnectivityManager;
    this.mockWifiManager = mockWifiManager;
  }

  /**
   * Mocks a failure to add a network from WifiManager.
   */
  public void addNetwork_failure() {
    when(mockWifiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(WiseFy.WIFI_MANAGER_FAILURE);
  }

  /**
   * Mocks successfully adding a network with WifiManager.
   */
  public void addNetwork_success() {
    when(mockWifiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(0);
  }

  /**
   * Mocks having a current network with a given SSID.
   *
   * @param ssid The ssid for the mocked network to have
   *
   * @return WifiInfo - The mocked current network
   */
  public WifiInfo currentNetwork(final String ssid) {
    final WifiInfo mockCurrentNetwork = mock(WifiInfo.class);
    when(mockCurrentNetwork.getSSID()).thenReturn(ssid);
    when(mockWifiManager.getConnectionInfo()).thenReturn(mockCurrentNetwork);
    return mockCurrentNetwork;
  }

  /**
   * Mocks the current connection status to a network.
   *
   * @param isAvailable If the network is available
   * @param isConnected If the network is connected
   * @param type The type of network
   *
   * @return NetworkInfo - The mocked network the device is connected to
   */
  NetworkInfo currentNetworkConnectionStatus(final boolean isAvailable, final boolean isConnected, @Nullable final String type) {
    final NetworkInfo networkInfo = mock(NetworkInfo.class);
    when(networkInfo.isAvailable()).thenReturn(isAvailable);
    when(networkInfo.isConnected()).thenReturn(isConnected);
    if (type != null) {
      when(networkInfo.getTypeName()).thenReturn(type);
    }
    when(mockConnectivityManager.getActiveNetworkInfo()).thenReturn(networkInfo);
    return networkInfo;
  }

  /**
   * Mocks an empty/null current network.
   */
  public void currentNetwork_null() {
    when(mockWifiManager.getConnectionInfo()).thenReturn(null);
  }

  /**
   * Mocks a failure disabling wifi.
   */
  public void disableWifi_failure() {
    when(mockWifiManager.setWifiEnabled(false)).thenReturn(false);
  }

  /**
   * Mocks a success disabling wifi.
   */
  public void disableWifi_success() {
    when(mockWifiManager.setWifiEnabled(false)).thenReturn(true);
  }

  /**
   * Mocks a failure disconnecting from the current network.
   */
  public void disconnectFromCurrentNetwork_failure() {
    when(mockWifiManager.disconnect()).thenReturn(false);
  }

  /**
   * Mocks a success disconnecting from the current network.
   */
  public void disconnectFromCurrentNetwork_success() {
    when(mockWifiManager.disconnect()).thenReturn(true);
  }

  /**
   * Mocks a failure enabling wifi.
   */
  public void enableWifi_failure() {
    when(mockWifiManager.setWifiEnabled(true)).thenReturn(false);
  }

  /**
   * Mocks a success enabling wifi.
   */
  public void enableWifi_success() {
    when(mockWifiManager.setWifiEnabled(true)).thenReturn(true);
  }

  /**
   * Returns an expected nearby access point.
   *
   * @return ScanResult - The expected nearby access point.
   */
  ScanResult getExpectedNearbyAccessPoint() {
    return expectedNearbyAccessPoint;
  }

  /**
   * Returns an expected set of nearby access points.
   *
   * @return List of type ScanResult - The expected set of nearby access points.
   */
  List<ScanResult> getExpectedNearbyAccessPoints() {
    return expectedNearbyAccessPoints;
  }

  /**
   * Returns an expected saved network.
   *
   * @return WifiConfiguration - The expected saved network.
   */
  WifiConfiguration getExpectedSavedNetwork() {
    return expectedSavedNetwork;
  }

  /**
   * Returns a list of expected saved networks.
   *
   * @return List of type WifiConfiguration - The list of expected saved networks.
   */
  List<WifiConfiguration> getExpectedSavedNetworks() {
    return expectedSavedNetworks;
  }

  /**
   * Returns a list of expected SSIDs.
   *
   * @return List of Strings - The list of expected SSIDs.
   */
  List<String> getExpectedSSIDs() {
    return expectedSSIDs;
  }

  /**
   * Mocks if the device is roaming.
   *
   * @param roaming Whether the device is roaming or not
   */
  public void isDeviceRoaming(final boolean roaming) {
    final NetworkInfo networkInfo = mock(NetworkInfo.class);
    when(networkInfo.isRoaming()).thenReturn(roaming);
    when(mockConnectivityManager.getActiveNetworkInfo()).thenReturn(networkInfo);
  }

  /**
   * Mocks if the device has wifi enabled.
   *
   * @param wifiEnabled Whether wifi is enabled on the device or not.
   */
  public void isWifiEnabled(final boolean wifiEnabled) {
    when(mockWifiManager.isWifiEnabled()).thenReturn(wifiEnabled);
  }

  /**
   * Mocks two nearby access points.
   *
   * @return List of type ScanResult - The mocked nearby access points
   */
  @NonNull
  public List<ScanResult> nearbyAccessPoints() {
    final List<ScanResult> accessPoints = GeneratorUtil.createMockAccessPointList(TEST_SSID, TEST_RSSI_LEVEL_HIGH, TEST_SSID2, TEST_RSSI_LEVEL_LOW);
    when(mockWifiManager.getScanResults()).thenReturn(accessPoints);
    return accessPoints;
  }

  /**
   * Mocks a network with a given frequency.
   *
   * @param frequency The frequency for the network
   *
   * @return WifiInfo - A network with the given frequency
   */
  public WifiInfo networkWithFrequency(final int frequency) {
    final WifiInfo mockWifiInfo = mock(WifiInfo.class);
    when(mockWifiInfo.getFrequency()).thenReturn(frequency);
    when(mockWifiManager.getConnectionInfo()).thenReturn(mockWifiInfo);
    return mockWifiInfo;
  }

  /**
   * Mocks if removing a network is successful or not.
   *
   * @param removed Whether the network was removed successfully or not
   */
  public void removeNetwork(final boolean removed) {
    when(mockWifiManager.removeNetwork(anyInt())).thenReturn(removed);
  }

  /**
   * Mocks a saved network list.
   *
   * @return List of type WifiConfiguration - The mocked saved network list
   */
  @NonNull
  public List<WifiConfiguration> savedNetworks() {
    final List<WifiConfiguration> savedNetworks = new ArrayList<>();
    final WifiConfiguration wiFiConfiguration = GeneratorUtil.createSavedNetwork(TEST_SSID);
    savedNetworks.add(wiFiConfiguration);

    when(mockWifiManager.getConfiguredNetworks()).thenReturn(savedNetworks);
    return savedNetworks;
  }

  /**
   * Mocks a null list of saved networks.
   */
  public void getConfiguredNetworks_null() {
    when(mockWifiManager.getConfiguredNetworks()).thenReturn(null);
  }

  /**
   * Mocks an empty list of saved networks.
   */
  public void getConfiguredNetworks_emptyList() {
    when(mockWifiManager.getConfiguredNetworks()).thenReturn(new ArrayList<>());
  }

  /**
   * Mocks a failure getting the ip of a device.
   */
  public void ip_failure() {
    final WifiInfo wifiInfo = mock(WifiInfo.class);
    when(wifiInfo.getIpAddress()).thenReturn(0);
    when(mockWifiManager.getConnectionInfo()).thenReturn(wifiInfo);
  }

  /**
   * Mocks a success getting the ip of a device.
   */
  public void ip_success() {
    final WifiInfo wifiInfo = mock(WifiInfo.class);
    when(wifiInfo.getIpAddress()).thenReturn(TestUtils.TEST_IP_ADDRESS_INT);
    when(mockWifiManager.getConnectionInfo()).thenReturn(wifiInfo);
  }

  /**
   * Mocks a nearby access point with a matching SSID.
   */
  void nearbyAccessPoints_matchingSSID() {
    final ScanResult accessPoint = GeneratorUtil.createMockAccessPointWithSSID(TEST_SSID);

    final List<ScanResult> accessPoints = new ArrayList<>();
    accessPoints.add(accessPoint);

    addToExpectedNearbyAccessPoints(accessPoint);
    addToExpectedSSIDs(accessPoint);

    when(mockWifiManager.getScanResults()).thenReturn(accessPoints);
  }

  /**
   * Mocks two nearby access points, both with matching SSIDs and access point 1 having the higher RSSI.
   *
   * @param takeHighest If only the access point with the highest RSSI should be returned (manipulates expected results)
   *
   * @return List of type ScanResult - The mocked nearby access points
   */
  @NonNull
  List<ScanResult> nearbyAccessPoints_multipleMatchingSSIDs_accessPoint1HasHigherRSSI(final boolean takeHighest) {
    final ScanResult accessPoint1 = GeneratorUtil.createMockAccessPointWithSSIDAndRSSI(TEST_SSID, TEST_RSSI_LEVEL_HIGH);
    final ScanResult accessPoint2 = GeneratorUtil.createMockAccessPointWithSSIDAndRSSI(TEST_SSID, TEST_RSSI_LEVEL_LOW);

    final List<ScanResult> accessPoints = new ArrayList<>();
    accessPoints.add(accessPoint1);
    accessPoints.add(accessPoint2);

    if (takeHighest) {
      addToExpectedNearbyAccessPoints(accessPoint1);
    } else {
      addToExpectedNearbyAccessPoints(accessPoint1, accessPoint2);
    }
    addToExpectedSSIDs(accessPoint1);

    when(mockWifiManager.getScanResults()).thenReturn(accessPoints);

    return accessPoints;
  }

  /**
   * Mocks two nearby access points, both with matching SSIDs and access point 2 having the higher RSSI.
   *
   * @param takeHighest If only the access point with the highest RSSI should be returned (manipulates expected results)
   *
   * @return List of type ScanResult - The mocked nearby access points
   */
  @NonNull
  List<ScanResult> nearbyAccessPoints_multipleMatchingSSIDs_accessPoint2HasHigherRSSI(final boolean takeHighest) {
    final ScanResult accessPoint1 = GeneratorUtil.createMockAccessPointWithSSIDAndRSSI(TEST_SSID, TEST_RSSI_LEVEL_LOW);
    final ScanResult accessPoint2 = GeneratorUtil.createMockAccessPointWithSSIDAndRSSI(TEST_SSID, TEST_RSSI_LEVEL_HIGH);

    final List<ScanResult> accessPoints = new ArrayList<>();
    accessPoints.add(accessPoint1);
    accessPoints.add(accessPoint2);

    if (takeHighest) {
      addToExpectedNearbyAccessPoints(accessPoint2);
    } else {
      addToExpectedNearbyAccessPoints(accessPoint1, accessPoint2);
    }
    addToExpectedSSIDs(accessPoint1);

    when(mockWifiManager.getScanResults()).thenReturn(accessPoints);

    return accessPoints;
  }

  /**
   * Mocks two nearby access points, one with a matching and one with a non-matching SSID.
   *
   * @param addSecondNetwork If the second access point should be added to the expected results
   *
   * @return List of type ScanResult - The mocked nearby access points
   */
  @NonNull
  List<ScanResult> nearbyAccessPoints_multipleSSIDs_sameRSSI(final boolean addSecondNetwork) {
    final ScanResult accessPoint1 = GeneratorUtil.createMockAccessPointWithSSIDAndRSSI(TEST_SSID, TEST_RSSI_LEVEL);
    final ScanResult accessPoint2 = GeneratorUtil.createMockAccessPointWithSSIDAndRSSI(TEST_SSID2, TEST_RSSI_LEVEL);

    final List<ScanResult> accessPoints = new ArrayList<>();
    accessPoints.add(accessPoint1);
    accessPoints.add(accessPoint2);

    if (addSecondNetwork) {
      addToExpectedNearbyAccessPoints(accessPoint1, accessPoint2);
    } else {
      addToExpectedNearbyAccessPoints(accessPoint1);
    }

    if (addSecondNetwork) {
      addToExpectedSSIDs(accessPoint1, accessPoint2);
    } else {
      addToExpectedSSIDs(accessPoint1);
    }

    when(mockWifiManager.getScanResults()).thenReturn(accessPoints);

    return accessPoints;
  }

  /**
   * Mocks two nearby access points, both with matching SSIDs and the same RSSI level.
   *
   * @param addSecondNetwork If the second access point should be added to the expected results
   *
   * @return List of type ScanResult - The mocked nearby access points
   */
  @NonNull
  List<ScanResult> nearbyAccessPoints_multipleMatchingSSIDs_sameRSSI(final boolean addSecondNetwork) {
    final ScanResult accessPoint1 = GeneratorUtil.createMockAccessPointWithSSIDAndRSSI(TEST_SSID, TEST_RSSI_LEVEL);
    final ScanResult accessPoint2 = GeneratorUtil.createMockAccessPointWithSSIDAndRSSI(TEST_SSID, TEST_RSSI_LEVEL);

    final List<ScanResult> accessPoints = new ArrayList<>();
    accessPoints.add(accessPoint1);
    accessPoints.add(accessPoint2);

    if (addSecondNetwork) {
      addToExpectedNearbyAccessPoints(accessPoint1, accessPoint2);
    } else {
      addToExpectedNearbyAccessPoints(accessPoint1);
    }
    addToExpectedSSIDs(accessPoint1);

    when(mockWifiManager.getScanResults()).thenReturn(accessPoints);

    return accessPoints;
  }

  /**
   * Mocks two nearby access points, both with non-matching SSIDs.
   */
  void nearbyAccessPoints_multipleNonMatchingSSIDs() {
    final ScanResult accessPoint1 = GeneratorUtil.createMockAccessPointWithSSID(TEST_SSID2);
    final ScanResult accessPoint2 = GeneratorUtil.createMockAccessPointWithSSID(TEST_SSID3);

    final List<ScanResult> accessPoints = new ArrayList<>();
    accessPoints.add(accessPoint1);
    accessPoints.add(accessPoint2);

    when(mockWifiManager.getScanResults()).thenReturn(accessPoints);
  }

  /**
   * Mocks a nearby access point list with a non-matching SSID.
   */
  void nearbyAccessPoints_nonMatchingSSID() {
    final ScanResult accessPoint = GeneratorUtil.createMockAccessPointWithSSID(TEST_SSID2);

    final List<ScanResult> accessPoints = new ArrayList<>();
    accessPoints.add(accessPoint);

    when(mockWifiManager.getScanResults()).thenReturn(accessPoints);
  }

  /**
   * Mocks a nearby access point list with a null entry.
   */
  void nearbyAccessPoints_nullAccessPoint() {
    final List<ScanResult> accessPoints = new ArrayList<>();
    accessPoints.add(null);

    when(mockWifiManager.getScanResults()).thenReturn(accessPoints);
  }

  /**
   * Mocks a null nearby access point list.
   */
  void nearbyAccessPoints_nullList() {
    when(mockWifiManager.getScanResults()).thenReturn(null);
  }

  /**
   * Mocks a null nearby access point list.
   */
  void nearbyAccessPoints_emptyList() {
    when(mockWifiManager.getScanResults()).thenReturn(new ArrayList<>());
  }

  /**
   * Mocks a nearby access point with a null SSID.
   */
  void nearbyAccessPoints_nullSSID() {
    final ScanResult accessPoint = GeneratorUtil.createMockAccessPointWithSSID(null);

    final List<ScanResult> accessPoints = new ArrayList<>();
    accessPoints.add(accessPoint);

    when(mockWifiManager.getScanResults()).thenReturn(accessPoints);
  }

  /**
   * Mocks an empty saved network list.
   */
  void savedNetworks_emptyList() {
    when(mockWifiManager.getConfiguredNetworks()).thenReturn(new ArrayList<>());
  }

  /**
   * Mocks a saved network with a matching SSID.
   */
  void savedNetworks_matchingSSID() {
    final WifiConfiguration savedNetwork = GeneratorUtil.createSavedNetwork(TEST_SSID);

    final List<WifiConfiguration> savedNetworks = new ArrayList<>();
    savedNetworks.add(savedNetwork);

    addToExpectedSavedNetworks(savedNetwork);

    when(mockWifiManager.getConfiguredNetworks()).thenReturn(savedNetworks);
  }

  /**
   * Mocks two saved networks, both with a matching SSID.
   */
  void savedNetworks_multipleMatchingSSIDs() {
    final WifiConfiguration savedNetwork1 = GeneratorUtil.createSavedNetwork(TEST_SSID);
    final WifiConfiguration savedNetwork2 = GeneratorUtil.createSavedNetwork(TEST_SSID);

    final List<WifiConfiguration> savedNetworks = new ArrayList<>();
    savedNetworks.add(savedNetwork1);
    savedNetworks.add(savedNetwork2);

    addToExpectedSavedNetworks(savedNetwork1, savedNetwork2);

    when(mockWifiManager.getConfiguredNetworks()).thenReturn(savedNetworks);
  }

  /**
   * Mocks two saved networks, both with non-matching SSIDs.
   */
  void savedNetworks_multipleNonMatchingSSIDs() {
    final WifiConfiguration savedNetwork1 = GeneratorUtil.createSavedNetwork(TEST_SSID2);
    final WifiConfiguration savedNetwork2 = GeneratorUtil.createSavedNetwork(TEST_SSID3);

    final List<WifiConfiguration> savedNetworks = new ArrayList<>();
    savedNetworks.add(savedNetwork1);
    savedNetworks.add(savedNetwork2);

    when(mockWifiManager.getConfiguredNetworks()).thenReturn(savedNetworks);
  }

  /**
   * Mocks two saved networks, one with a matching and one with a non-matching SSID.
   *
   * @param addSecondNetwork If the second saved ne network should be added to the expected results
   */
  void savedNetworks_multipleSSIDs(final boolean addSecondNetwork) {
    final WifiConfiguration savedNetwork1 = GeneratorUtil.createSavedNetwork(TEST_SSID);
    final WifiConfiguration savedNetwork2 = GeneratorUtil.createSavedNetwork(TEST_SSID2);

    final List<WifiConfiguration> savedNetworks = new ArrayList<>();
    savedNetworks.add(savedNetwork1);
    savedNetworks.add(savedNetwork2);

    if (addSecondNetwork) {
      addToExpectedSavedNetworks(savedNetwork1, savedNetwork2);
    } else {
      addToExpectedSavedNetworks(savedNetwork1);
    }

    when(mockWifiManager.getConfiguredNetworks()).thenReturn(savedNetworks);
  }

  /**
   * Mocks a saved network with a non matching SSID.
   */
  void savedNetworks_nonMatchingSSID() {
    final WifiConfiguration savedNetwork = GeneratorUtil.createSavedNetwork(TEST_SSID2);

    final List<WifiConfiguration> savedNetworks = new ArrayList<>();
    savedNetworks.add(savedNetwork);

    when(mockWifiManager.getConfiguredNetworks()).thenReturn(savedNetworks);
  }

  /**
   * Mocks a null saved network list.
   */
  void savedNetworks_nullList() {
    when(mockWifiManager.getConfiguredNetworks()).thenReturn(null);
  }

  /**
   * Mocks a saved network list with a null entry.
   */
  void savedNetworks_nullSavedNetwork() {
    final List<WifiConfiguration> wifiList = new ArrayList<>();
    wifiList.add(null);

    when(mockWifiManager.getConfiguredNetworks()).thenReturn(wifiList);
  }

  /**
   * Mocks a saved network with a null SSID.
   */
  void savedNetworks_nullSSID() {
    final WifiConfiguration savedNetwork = GeneratorUtil.createSavedNetwork(null);

    final List<WifiConfiguration> savedNetworks = new ArrayList<>();
    savedNetworks.add(savedNetwork);

    when(mockWifiManager.getConfiguredNetworks()).thenReturn(savedNetworks);
  }

  /*
   * HELPERS
   */

  /**
   * Adds a nearby access point to expected results.
   *
   * @param accessPoint The nearby access point to add
   */
  private void addToExpectedNearbyAccessPoints(final ScanResult accessPoint) {
    expectedNearbyAccessPoint = accessPoint;

    expectedNearbyAccessPoints = new ArrayList<>();
    expectedNearbyAccessPoints.add(accessPoint);
  }

  /**
   * Adds two nearby access points to expected results.
   *
   * @param accessPoint1 The first nearby access point to add
   * @param accessPoint2 The second nearby access point to add
   */
  private void addToExpectedNearbyAccessPoints(final ScanResult accessPoint1, final ScanResult accessPoint2) {
    expectedNearbyAccessPoint = accessPoint1;

    expectedNearbyAccessPoints = new ArrayList<>();
    expectedNearbyAccessPoints.add(accessPoint1);
    expectedNearbyAccessPoints.add(accessPoint2);
  }

  /**
   * Adds a saved network to expected results.
   *
   * @param savedNetwork The saved network to add
   */
  private void addToExpectedSavedNetworks(final WifiConfiguration savedNetwork) {
    expectedSavedNetwork = savedNetwork;

    expectedSavedNetworks = new ArrayList<>();
    expectedSavedNetworks.add(savedNetwork);
  }

  /**
   * Adds two saved networks to expected results.
   *
   * @param savedNetwork1 The first saved network to add
   * @param savedNetwork2 The second saved network to add
   */
  private void addToExpectedSavedNetworks(final WifiConfiguration savedNetwork1, final WifiConfiguration savedNetwork2) {
    expectedSavedNetwork = savedNetwork1;

    expectedSavedNetworks = new ArrayList<>();
    expectedSavedNetworks.add(savedNetwork1);
    expectedSavedNetworks.add(savedNetwork2);
  }

  /**
   * Adds an SSID of an access point to expected results.
   *
   * @param accessPoint The access point to add SSID of
   */
  private void addToExpectedSSIDs(@NonNull final ScanResult accessPoint) {
    expectedSSIDs = new ArrayList<>();

    expectedSSIDs.add(accessPoint.SSID);
  }

  /**
   * Adds the SSIDs of two access points to expected results.
   *
   * @param accessPoint1 The first access point to add SSID of
   * @param accessPoint2 The second access point to add SSID of
   */
  private void addToExpectedSSIDs(@NonNull final ScanResult accessPoint1, @NonNull final ScanResult accessPoint2) {
    expectedSSIDs = new ArrayList<>();
    expectedSSIDs.add(accessPoint1.SSID);
    expectedSSIDs.add(accessPoint2.SSID);
  }
}
