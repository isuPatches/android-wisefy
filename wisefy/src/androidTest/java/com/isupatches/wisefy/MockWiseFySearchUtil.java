package com.isupatches.wisefy;

import static com.isupatches.wisefy.TestUtils.TEST_RSSI_LEVEL;
import static com.isupatches.wisefy.TestUtils.TEST_RSSI_LEVEL_HIGH;
import static com.isupatches.wisefy.TestUtils.TEST_RSSI_LEVEL_LOW;
import static com.isupatches.wisefy.TestUtils.TEST_SSID;
import static com.isupatches.wisefy.TestUtils.TEST_SSID2;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import org.mockito.ArgumentMatchers;

/**
 * A class to mock returns from the WiseFySearch class.
 *
 * @see WiseFySearch
 *
 * @author Patches
 */
public final class MockWiseFySearchUtil {

  private final WiseFySearch mockWiseFySearch;

  /**
   * Constructor.
   *
   * @param mockWiseFySearch The
   */
  MockWiseFySearchUtil(final WiseFySearch mockWiseFySearch) {
    this.mockWiseFySearch = mockWiseFySearch;
  }

  /**
   * Mocks no nearby access point.
   */
  public void findAccessPointByRegex_null() {
    when(mockWiseFySearch.findAccessPointByRegex(anyString(), anyInt(), anyBoolean())).thenReturn(null);
  }

  /**
   * Mocks a nearby access point.
   *
   * @return ScanResult - The mocked nearby access point.
   */
  public ScanResult findAccessPointByRegex_success() {
    final ScanResult accessPoint = GeneratorUtil.createMockAccessPointWithSSIDAndRSSI(TEST_SSID, TEST_RSSI_LEVEL);
    when(mockWiseFySearch.findAccessPointByRegex(anyString(), anyInt(), anyBoolean())).thenReturn(accessPoint);
    return accessPoint;
  }

  /**
   * Mocks no nearby access points.
   */
  public void findAccessPointsMatchingRegex_null() {
    when(mockWiseFySearch.findAccessPointsMatchingRegex(anyString(), anyBoolean())).thenReturn(null);
  }

  /**
   * Mocks nearby access point.
   *
   * @return List of type ScanResult - The mocked access points.
   */
  @NonNull
  public List<ScanResult> findAccessPointsMatchingRegex_success() {
    final List<ScanResult> accessPoints = new ArrayList<>();
    final ScanResult accessPoint = GeneratorUtil.createMockAccessPointWithSSIDAndRSSI(TEST_SSID, TEST_RSSI_LEVEL);
    accessPoints.add(accessPoint);
    when(mockWiseFySearch.findAccessPointsMatchingRegex(anyString(), anyBoolean())).thenReturn(accessPoints);
    return accessPoints;
  }

  /**
   * Mocks no saved network.
   */
  public void findSavedNetworkByRegex_null() {
    when(mockWiseFySearch.findSavedNetworkByRegex(anyString())).thenReturn(null);
  }

  /**
   * Mocks a saved network.
   *
   * @return WifiConfiguration - The mocked saved network
   */
  @NonNull
  public WifiConfiguration findSavedNetworkByRegex_success() {
    final WifiConfiguration savedNetwork = GeneratorUtil.createSavedNetwork(TEST_SSID);
    when(mockWiseFySearch.findSavedNetworkByRegex(anyString())).thenReturn(savedNetwork);
    return savedNetwork;
  }

  /**
   * Mocks an empty saved network list.
   */
  public void findSavedNetworksByRegex_emptyList() {
    when(mockWiseFySearch.findSavedNetworksMatchingRegex(anyString())).thenReturn(new ArrayList<>());
  }

  /**
   * Mocks no saved networks.
   */
  public void findSavedNetworksByRegex_null() {
    when(mockWiseFySearch.findSavedNetworksMatchingRegex(anyString())).thenReturn(null);
  }

  /**
   * Mocks a list of saved networks.
   *
   * @return List of type WifiConfiguration - The mocked saved network list
   */
  @NonNull
  public List<WifiConfiguration> findSavedNetworksMatchingRegex_success() {
    final List<WifiConfiguration> savedNetworks = new ArrayList<>();
    final WifiConfiguration wiFiConfiguration = GeneratorUtil.createSavedNetwork(TEST_SSID);
    savedNetworks.add(wiFiConfiguration);

    when(mockWiseFySearch.findSavedNetworksMatchingRegex(anyString())).thenReturn(savedNetworks);
    return savedNetworks;
  }

  /**
   * Mocks a list of nearby SSIDs.
   *
   * @return List of type String - The mocked list of SSIDs.
   */
  @NonNull
  public List<String> findSSIDsMatchingRegex_success() {
    final List<String> ssids = new ArrayList<>();
    ssids.add(TEST_SSID);
    when(mockWiseFySearch.findSSIDsMatchingRegex(anyString())).thenReturn(ssids);
    return ssids;
  }

  /**
   * Mocks no matching SSIDs.
   */
  public void findSSIDsMatchingRegex_null() {
    when(mockWiseFySearch.findSSIDsMatchingRegex(anyString())).thenReturn(null);
  }

  /**
   * Mocks if a network is already saved.
   *
   * @param saved If the network is already saved
   *
   * @see WiseFySearch#isNetworkASavedConfiguration(String)
   */
  public void isNetworkASavedConfiguration(final boolean saved) {
    when(mockWiseFySearch.isNetworkASavedConfiguration(anyString())).thenReturn(saved);
  }

  /**
   * Mocks a basic return of two networks from WiseFySearch#removeEntriesWithLowerSignalStrength.
   *
   * @see WiseFySearch#removeEntriesWithLowerSignalStrength(List)
   *
   * @return List of type ScanResult - The mocked networks that will be returned
   */
  @NonNull
  public List<ScanResult> removeEntriesWithLowerSignalStrength() {
    final List<ScanResult> accessPoints = GeneratorUtil.createMockAccessPointList(TEST_SSID, TEST_RSSI_LEVEL_HIGH, TEST_SSID2, TEST_RSSI_LEVEL_LOW);
    when(mockWiseFySearch.removeEntriesWithLowerSignalStrength(ArgumentMatchers.anyList())).thenReturn(accessPoints);
    return accessPoints;
  }
}
