package com.isupatches.wisefy;

import static com.isupatches.wisefy.TestUtils.TEST_REGEX;
import static com.isupatches.wisefy.TestUtils.TEST_SSID;
import static com.isupatches.wisefy.TestUtils.TEST_TIMEOUT;

import static org.junit.Assert.assertEquals;

import android.net.wifi.ScanResult;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * Used to test the WiseFySearch class and search capabilities.
 *
 * @see WiseFySearch
 *
 * @author Patches
 */
public class WiseFySearchTests extends AbstractBaseAndroidJUnit4TestClass {

  private WiseFySearch wisefySearch;

  public WiseFySearchTests() {
    // No-op
  }

  @Before
  public void setUp() {
    wisefySearch = WiseFySearch.create(getMockWiseFyPrerequisites());
  }

  /*
   * findAccessPointByRegex tests
   */

  @Test
  public void findAccessPointByRegex_failure_nullAccessPoints_takeHighest_false() {
    getMockNetworkUtil().nearbyAccessPoints_nullList();
    assertEquals(null, wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, false));
  }

  @Test
  public void findAccessPointByRegex_failure_nullAccessPoints_takeHighest_true() {
    getMockNetworkUtil().nearbyAccessPoints_nullList();
    assertEquals(null, wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, true));
  }

  @Test
  public void findAccessPointByRegex_failure_emptyAccessPointList_takeHighest_false() {
    getMockNetworkUtil().nearbyAccessPoints_emptyList();
    assertEquals(null, wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, false));
  }

  @Test
  public void findAccessPointByRegex_failure_emptyAccessPointList_takeHighest_true() {
    getMockNetworkUtil().nearbyAccessPoints_emptyList();
    assertEquals(null, wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, true));
  }

  @Test
  public void findAccessPointByRegex_failure_nullAccessPoint_takeHighest_false() {
    getMockNetworkUtil().nearbyAccessPoints_nullAccessPoint();
    assertEquals(null, wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, false));
  }

  @Test
  public void findAccessPointByRegex_failure_nullAccessPoint_takeHighest_true() {
    getMockNetworkUtil().nearbyAccessPoints_nullAccessPoint();
    assertEquals(null, wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, true));
  }

  @Test
  public void findAccessPointByRegex_failure_nullSSID_takeHighest_false() {
    getMockNetworkUtil().nearbyAccessPoints_nullSSID();
    assertEquals(null, wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, false));
  }

  @Test
  public void findAccessPointByRegex_failure_nullSSID_takeHighest_true() {
    getMockNetworkUtil().nearbyAccessPoints_nullSSID();
    assertEquals(null, wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, true));
  }

  @Test
  public void findAccessPointByRegex_failure_nonMatchingSSID_takeHighest_false() {
    getMockNetworkUtil().nearbyAccessPoints_nonMatchingSSID();
    assertEquals(null, wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, false));
  }

  @Test
  public void findAccessPointByRegex_failure_nonMatchingSSID_takeHighest_true() {
    getMockNetworkUtil().nearbyAccessPoints_nonMatchingSSID();
    assertEquals(null, wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, true));
  }

  @Test
  public void findAccessPointByRegex_failure_multipleNonMatchingSSIDs_takeHighest_false() {
    getMockNetworkUtil().nearbyAccessPoints_multipleNonMatchingSSIDs();
    assertEquals(null, wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, false));
  }

  @Test
  public void findAccessPointByRegex_failure_multipleNonMatchingSSIDs_takeHighest_true() {
    getMockNetworkUtil().nearbyAccessPoints_multipleNonMatchingSSIDs();
    assertEquals(null, wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, true));
  }

  @Test
  public void findAccessPointByRegex_success_takeHighest_false() {
    getMockNetworkUtil().nearbyAccessPoints_matchingSSID();
    assertEquals(getMockNetworkUtil().getExpectedNearbyAccessPoint(), wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, false));
  }

  @Test
  public void findAccessPointByRegex_success_takeHighest_true() {
    getMockNetworkUtil().nearbyAccessPoints_matchingSSID();
    assertEquals(getMockNetworkUtil().getExpectedNearbyAccessPoint(), wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, true));
  }

  @Test
  public void findAccessPointByRegex_success_multipleSSIDs_sameRSSI_nonRegex_takeHighest_false() {
    getMockNetworkUtil().nearbyAccessPoints_multipleSSIDs_sameRSSI(false);
    assertEquals(getMockNetworkUtil().getExpectedNearbyAccessPoint(), wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, false));
  }

  @Test
  public void findAccessPointByRegex_success_multipleSSIDs_sameRSSI_nonRegex_takeHighest_true() {
    getMockNetworkUtil().nearbyAccessPoints_multipleSSIDs_sameRSSI(false);
    assertEquals(getMockNetworkUtil().getExpectedNearbyAccessPoint(), wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, true));
  }

  @Test
  public void findAccessPointByRegex_success_multipleSSIDs_sameRSSI_regex_takeHighest_false() {
    getMockNetworkUtil().nearbyAccessPoints_multipleSSIDs_sameRSSI(true);
    assertEquals(getMockNetworkUtil().getExpectedNearbyAccessPoint(), wisefySearch.findAccessPointByRegex(TEST_REGEX, TEST_TIMEOUT, false));
  }

  @Test
  public void findAccessPointByRegex_success_multipleSSIDs_sameRSSI_regex_takeHighest_true() {
    getMockNetworkUtil().nearbyAccessPoints_multipleSSIDs_sameRSSI(true);
    assertEquals(getMockNetworkUtil().getExpectedNearbyAccessPoint(), wisefySearch.findAccessPointByRegex(TEST_REGEX, TEST_TIMEOUT, true));
  }

  @Test
  public void findAccessPointByRegex_success_multipleMatchingSSIDs_accessPoint1HasHigherRSSI_takeHighest_false() {
    getMockNetworkUtil().nearbyAccessPoints_multipleMatchingSSIDs_accessPoint1HasHigherRSSI(false);
    assertEquals(getMockNetworkUtil().getExpectedNearbyAccessPoint(), wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, false));
  }

  @Test
  public void findAccessPointByRegex_success_multipleMatchesSSIDs_accessPoint1HasHigherRSSI_takeHighest_true() {
    getMockNetworkUtil().nearbyAccessPoints_multipleMatchingSSIDs_accessPoint1HasHigherRSSI(true);
    assertEquals(getMockNetworkUtil().getExpectedNearbyAccessPoint(), wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, true));
  }

  @Test
  public void findAccessPointByRegex_success_multipleMatchingSSIDs_accessPoint2HasHigherRSSI_takeHighest_false() {
    final boolean takeHighest = false;
    getMockNetworkUtil().nearbyAccessPoints_multipleMatchingSSIDs_accessPoint2HasHigherRSSI(takeHighest);
    assertEquals(getMockNetworkUtil().getExpectedNearbyAccessPoint(), wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, takeHighest));
  }

  @Test
  public void findAccessPointByRegex_success_multipleMatchesSSIDs_accessPoint2HasHigherRSSI_takeHighest_true() {
    final boolean takeHighest = true;
    getMockNetworkUtil().nearbyAccessPoints_multipleMatchingSSIDs_accessPoint2HasHigherRSSI(takeHighest);
    assertEquals(getMockNetworkUtil().getExpectedNearbyAccessPoint(), wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, takeHighest));
  }

  @Test
  public void findAccessPointByRegex_success_multipleMatchingSSIDs_sameRSSI_takeHighest_false() {
    getMockNetworkUtil().nearbyAccessPoints_multipleMatchingSSIDs_sameRSSI(false);
    assertEquals(getMockNetworkUtil().getExpectedNearbyAccessPoint(), wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, false));
  }

  @Test
  public void findAccessPointByRegex_success_multipleMatchesSSIDs_sameRSSI_takeHighest_true() {
    getMockNetworkUtil().nearbyAccessPoints_multipleMatchingSSIDs_sameRSSI(false);
    assertEquals(getMockNetworkUtil().getExpectedNearbyAccessPoint(), wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, true));
  }

  /*
   * findAccessPointsMatchingRegex tests
   */

  @Test
  public void findAccessPointsMatchingRegex_failure_nullAccessPoints_takeHighest_false() {
    getMockNetworkUtil().nearbyAccessPoints_nullList();
    assertEquals(null, wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, false));
  }

  @Test
  public void findAccessPointsMatchingRegex_failure_nullAccessPoints_takeHighest_true() {
    getMockNetworkUtil().nearbyAccessPoints_nullList();
    assertEquals(null, wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, true));
  }

  @Test
  public void findAccessPointsMatchingRegex_failure_emptyAccessPointList_takeHighest_false() {
    getMockNetworkUtil().nearbyAccessPoints_emptyList();
    assertEquals(null, wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, false));
  }

  @Test
  public void findAccessPointsMatchingRegex_failure_emptyAccessPointList_takeHighest_true() {
    getMockNetworkUtil().nearbyAccessPoints_emptyList();
    assertEquals(null, wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, true));
  }

  @Test
  public void findAccessPointsMatchingRegex_failure_nullAccessPoint_takeHighest_false() {
    getMockNetworkUtil().nearbyAccessPoints_nullAccessPoint();
    assertEquals(null, wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, false));
  }

  @Test
  public void findAccessPointsMatchingRegex_failure_nullAccessPoint_takeHighest_true() {
    getMockNetworkUtil().nearbyAccessPoints_nullAccessPoint();
    assertEquals(null, wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, true));
  }

  @Test
  public void findAccessPointsMatchingRegex_failure_nullSSID_takeHighest_false() {
    getMockNetworkUtil().nearbyAccessPoints_nullSSID();
    assertEquals(null, wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, false));
  }

  @Test
  public void findAccessPointsMatchingRegex_failure_nullSSID_takeHighest_true() {
    getMockNetworkUtil().nearbyAccessPoints_nullSSID();
    assertEquals(null, wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, true));
  }

  @Test
  public void findAccessPointsMatchingRegex_failure_nonMatchingSSID_takeHighest_false() {
    getMockNetworkUtil().nearbyAccessPoints_nonMatchingSSID();
    assertEquals(null, wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, false));
  }

  @Test
  public void findAccessPointsMatchingRegex_failure_nonMatchingSSID_takeHighest_true() {
    getMockNetworkUtil().nearbyAccessPoints_nonMatchingSSID();
    assertEquals(null, wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, true));
  }

  @Test
  public void findAccessPointsMatchingRegex_failure_multipleNonMatchingSSIDs_takeHighest_false() {
    getMockNetworkUtil().nearbyAccessPoints_multipleNonMatchingSSIDs();
    assertEquals(null, wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, false));
  }

  @Test
  public void findAccessPointsMatchingRegex_failure_multipleNonMatchingSSIDs_takeHighest_true() {
    getMockNetworkUtil().nearbyAccessPoints_multipleNonMatchingSSIDs();
    assertEquals(null, wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, true));
  }

  @Test
  public void findAccessPointsMatchingRegex_success_takeHighest_false() {
    getMockNetworkUtil().nearbyAccessPoints_matchingSSID();
    assertEquals(getMockNetworkUtil().getExpectedNearbyAccessPoints(), wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, false));
  }

  @Test
  public void findAccessPointsMatchingRegex_success_takeHighest_true() {
    getMockNetworkUtil().nearbyAccessPoints_matchingSSID();
    assertEquals(getMockNetworkUtil().getExpectedNearbyAccessPoints(), wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, true));
  }

  @Test
  public void findAccessPointsMatchingRegex_success_multipleSSIDs_nonRegex_takeHighest_false() {
    getMockNetworkUtil().nearbyAccessPoints_multipleSSIDs_sameRSSI(false);
    assertEquals(getMockNetworkUtil().getExpectedNearbyAccessPoints(), wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, false));
  }

  @Test
  public void findAccessPointsMatchingRegex_success_multipleSSIDs_nonRegex_takeHighest_true() {
    getMockNetworkUtil().nearbyAccessPoints_multipleSSIDs_sameRSSI(false);
    assertEquals(getMockNetworkUtil().getExpectedNearbyAccessPoints(), wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, true));
  }

  @Test
  public void findAccessPointsMatchingRegex_success_multipleSSIDs_regex_takeHighest_false() {
    getMockNetworkUtil().nearbyAccessPoints_multipleSSIDs_sameRSSI(true);
    assertEquals(getMockNetworkUtil().getExpectedNearbyAccessPoints(), wisefySearch.findAccessPointsMatchingRegex(TEST_REGEX, false));
  }

  @Test
  public void findAccessPointsMatchingRegex_success_multipleSSIDs_regex_takeHighest_true() {
    getMockNetworkUtil().nearbyAccessPoints_multipleSSIDs_sameRSSI(true);
    assertEquals(getMockNetworkUtil().getExpectedNearbyAccessPoints(), wisefySearch.findAccessPointsMatchingRegex(TEST_REGEX, true));
  }

  @Test
  public void findAccessPointsMatchingRegex_success_multipleMatchingSSIDs_accessPoint1HasHigherRSSI_takeHighest_false() {
    final boolean takeHighest = false;
    getMockNetworkUtil().nearbyAccessPoints_multipleMatchingSSIDs_accessPoint1HasHigherRSSI(takeHighest);
    assertEquals(getMockNetworkUtil().getExpectedNearbyAccessPoints(), wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, takeHighest));
  }

  @Test
  public void findAccessPointsMatchingRegex_success_multipleMatchingSSIDs_accessPoint1HasHigherRSSI_takeHighest_true() {
    final boolean takeHighest = true;
    getMockNetworkUtil().nearbyAccessPoints_multipleMatchingSSIDs_accessPoint1HasHigherRSSI(takeHighest);
    assertEquals(getMockNetworkUtil().getExpectedNearbyAccessPoints(), wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, takeHighest));
  }

  @Test
  public void findAccessPointsMatchingRegex_success_multipleMatchingSSIDs_accessPoint2HasHigherRSSI_takeHighest_false() {
    getMockNetworkUtil().nearbyAccessPoints_multipleMatchingSSIDs_accessPoint2HasHigherRSSI(false);
    assertEquals(getMockNetworkUtil().getExpectedNearbyAccessPoints(), wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, false));
  }

  @Test
  public void findAccessPointsMatchingRegex_success_multipleMatchingSSIDs_accessPoint2HasHigherRSSI_takeHighest_true() {
    getMockNetworkUtil().nearbyAccessPoints_multipleMatchingSSIDs_accessPoint2HasHigherRSSI(true);
    assertEquals(getMockNetworkUtil().getExpectedNearbyAccessPoints(), wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, true));
  }

  @Test
  public void findAccessPointsMatchingRegex_success_multipleMatchingSSIDs_sameRSSI_takeHighest_false() {
    getMockNetworkUtil().nearbyAccessPoints_multipleMatchingSSIDs_sameRSSI(true);
    assertEquals(getMockNetworkUtil().getExpectedNearbyAccessPoints(), wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, false));
  }

  @Test
  public void findAccessPointsMatchingRegex_success_multipleMatchingSSIDs_sameRSSI_takeHighest_true() {
    getMockNetworkUtil().nearbyAccessPoints_multipleMatchingSSIDs_sameRSSI(true);
    assertEquals(getMockNetworkUtil().getExpectedNearbyAccessPoints(), wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, true));
  }

  /*
   * findSavedNetworkByRegex tests
   */

  @Test
  public void findSavedNetworkByRegex_failure_nullSavedNetworkList() {
    getMockNetworkUtil().savedNetworks_nullList();
    assertEquals(null, wisefySearch.findSavedNetworkByRegex(TEST_SSID));
  }

  @Test
  public void findSavedNetworkByRegex_failure_emptySavedNetworkList() {
    getMockNetworkUtil().savedNetworks_emptyList();
    assertEquals(null, wisefySearch.findSavedNetworkByRegex(TEST_SSID));
  }

  @Test
  public void findSavedNetworkByRegex_failure_nullWifiConfiguration() {
    getMockNetworkUtil().savedNetworks_nullSavedNetwork();
    assertEquals(null, wisefySearch.findSavedNetworkByRegex(TEST_SSID));
  }

  @Test
  public void findSavedNetworkByRegex_failure_nullSSID() {
    getMockNetworkUtil().savedNetworks_nullSSID();
    assertEquals(null, wisefySearch.findSavedNetworkByRegex(TEST_SSID));
  }

  @Test
  public void findSavedNetworkByRegex_failure_nonMatchingSSID() {
    getMockNetworkUtil().savedNetworks_nonMatchingSSID();
    assertEquals(null, wisefySearch.findSavedNetworkByRegex(TEST_SSID));
  }

  @Test
  public void findSavedNetworkByRegex_failure_multipleNonMatchingSSIDs() {
    getMockNetworkUtil().savedNetworks_multipleNonMatchingSSIDs();
    assertEquals(null, wisefySearch.findSavedNetworkByRegex(TEST_SSID));
  }

  @Test
  public void findSavedNetworkByRegex_success() {
    getMockNetworkUtil().savedNetworks_matchingSSID();
    assertEquals(getMockNetworkUtil().getExpectedSavedNetwork(), wisefySearch.findSavedNetworkByRegex(TEST_SSID));
  }

  @Test
  public void findSavedNetworkByRegex_success_multipleMatchingSSIDs() {
    getMockNetworkUtil().savedNetworks_multipleMatchingSSIDs();
    assertEquals(getMockNetworkUtil().getExpectedSavedNetwork(), wisefySearch.findSavedNetworkByRegex(TEST_SSID));
  }

  @Test
  public void findSavedNetworkByRegex_success_multipleSSIDs_nonRegex() {
    getMockNetworkUtil().savedNetworks_multipleSSIDs(false);
    assertEquals(getMockNetworkUtil().getExpectedSavedNetwork(), wisefySearch.findSavedNetworkByRegex(TEST_SSID));
  }

  @Test
  public void findSavedNetworkByRegex_success_multipleSSIDs_regex() {
    getMockNetworkUtil().savedNetworks_multipleSSIDs(true);
    assertEquals(getMockNetworkUtil().getExpectedSavedNetwork(), wisefySearch.findSavedNetworkByRegex(TEST_REGEX));
  }

  /*
   * findSavedNetworksMatchingRegex tests
   */

  @Test
  public void findSavedNetworksMatchingRegex_failure_nullSavedNetworkList() {
    getMockNetworkUtil().savedNetworks_nullList();
    assertEquals(null, wisefySearch.findSavedNetworksMatchingRegex(TEST_SSID));
  }

  @Test
  public void findSavedNetworksMatchingRegex_failure_emptySavedNetworkList() {
    getMockNetworkUtil().savedNetworks_emptyList();
    assertEquals(null, wisefySearch.findSavedNetworksMatchingRegex(TEST_SSID));
  }

  @Test
  public void findSavedNetworksMatchingRegex_failure_nullWifiConfiguration() {
    getMockNetworkUtil().savedNetworks_nullSavedNetwork();
    assertEquals(null, wisefySearch.findSavedNetworksMatchingRegex(TEST_SSID));
  }

  @Test
  public void findSavedNetworksMatchingRegex_failure_nullSSID() {
    getMockNetworkUtil().savedNetworks_nullSSID();
    assertEquals(null, wisefySearch.findSavedNetworksMatchingRegex(TEST_SSID));
  }

  @Test
  public void findSavedNetworksMatchingRegex_failure_nonMatchingSSID() {
    getMockNetworkUtil().savedNetworks_nonMatchingSSID();
    assertEquals(null, wisefySearch.findSavedNetworksMatchingRegex(TEST_SSID));
  }

  @Test
  public void findSavedNetworksMatchingRegex_failure_multipleNonMatchingSSIDs() {
    getMockNetworkUtil().savedNetworks_multipleNonMatchingSSIDs();
    assertEquals(null, wisefySearch.findSavedNetworksMatchingRegex(TEST_SSID));
  }

  @Test
  public void findSavedNetworksMatchingRegex_success() {
    getMockNetworkUtil().savedNetworks_matchingSSID();
    assertEquals(getMockNetworkUtil().getExpectedSavedNetworks(), wisefySearch.findSavedNetworksMatchingRegex(TEST_SSID));
  }

  @Test
  public void findSavedNetworksMatchingRegex_success_multipleMatchingSSIDs() {
    getMockNetworkUtil().savedNetworks_multipleMatchingSSIDs();
    assertEquals(getMockNetworkUtil().getExpectedSavedNetworks(), wisefySearch.findSavedNetworksMatchingRegex(TEST_SSID));
  }

  @Test
  public void findSavedNetworksMatchingRegex_success_multipleSSIDs_nonRegex() {
    getMockNetworkUtil().savedNetworks_multipleSSIDs(false);
    assertEquals(getMockNetworkUtil().getExpectedSavedNetworks(), wisefySearch.findSavedNetworksMatchingRegex(TEST_SSID));
  }

  @Test
  public void findSavedNetworksMatchingRegex_success_multipleSSIDs_regex() {
    getMockNetworkUtil().savedNetworks_multipleSSIDs(true);
    assertEquals(getMockNetworkUtil().getExpectedSavedNetworks(), wisefySearch.findSavedNetworksMatchingRegex(TEST_REGEX));
  }

  /*
   * findSSIDsMatchingRegex tests
   */

  @Test
  public void findSSIDsMatchingRegex_failure_nullAccessPoints() {
    getMockNetworkUtil().nearbyAccessPoints_nullList();
    assertEquals(null, wisefySearch.findSSIDsMatchingRegex(TEST_SSID));
  }

  @Test
  public void findSSIDsMatchingRegex_failure_emptySavedNetworkList() {
    getMockNetworkUtil().nearbyAccessPoints_emptyList();
    assertEquals(null, wisefySearch.findSSIDsMatchingRegex(TEST_SSID));
  }

  @Test
  public void findSSIDsMatchingRegex_failure_nullAccessPoint() {
    getMockNetworkUtil().nearbyAccessPoints_nullAccessPoint();
    assertEquals(null, wisefySearch.findSSIDsMatchingRegex(TEST_SSID));
  }

  @Test
  public void findSSIDsMatchingRegex_failure_nullSSID() {
    getMockNetworkUtil().nearbyAccessPoints_nullSSID();
    assertEquals(null, wisefySearch.findSSIDsMatchingRegex(TEST_SSID));
  }

  @Test
  public void findSSIDsMatchingRegex_failure_nonMatchingSSID() {
    getMockNetworkUtil().nearbyAccessPoints_nonMatchingSSID();
    assertEquals(null, wisefySearch.findSSIDsMatchingRegex(TEST_SSID));
  }

  @Test
  public void findSSIDsMatchingRegex_failure_multipleNonMatchingSSIDs() {
    getMockNetworkUtil().nearbyAccessPoints_multipleNonMatchingSSIDs();
    assertEquals(null, wisefySearch.findSSIDsMatchingRegex(TEST_SSID));
  }

  @Test
  public void findSSIDsMatchingRegex_success() {
    getMockNetworkUtil().nearbyAccessPoints_matchingSSID();
    assertEquals(getMockNetworkUtil().getExpectedSSIDs(), wisefySearch.findSSIDsMatchingRegex(TEST_SSID));
  }

  @Test
  public void findSSIDsMatchingRegex_success_multipleMatchingSSIDs_sameRSSI() {
    getMockNetworkUtil().nearbyAccessPoints_multipleMatchingSSIDs_sameRSSI(true);
    assertEquals(getMockNetworkUtil().getExpectedSSIDs(), wisefySearch.findSSIDsMatchingRegex(TEST_SSID));
  }

  @Test
  public void findSSIDsMatchingRegex_success_multipleMatchingSSIDs_accessPoint1HasHigherRSSI() {
    getMockNetworkUtil().nearbyAccessPoints_multipleMatchingSSIDs_accessPoint1HasHigherRSSI(true);
    assertEquals(getMockNetworkUtil().getExpectedSSIDs(), wisefySearch.findSSIDsMatchingRegex(TEST_SSID));
  }

  @Test
  public void findSSIDsMatchingRegex_success_multipleMatchingSSIDs_accessPoint2HasHigherRSSI() {
    getMockNetworkUtil().nearbyAccessPoints_multipleMatchingSSIDs_accessPoint2HasHigherRSSI(true);
    assertEquals(getMockNetworkUtil().getExpectedSSIDs(), wisefySearch.findSSIDsMatchingRegex(TEST_SSID));
  }

  @Test
  public void findSSIDsMatchingRegex_success_multipleSSIDs_nonRegex() {
    getMockNetworkUtil().nearbyAccessPoints_multipleSSIDs_sameRSSI(false);
    assertEquals(getMockNetworkUtil().getExpectedSSIDs(), wisefySearch.findSSIDsMatchingRegex(TEST_SSID));
  }

  @Test
  public void findSSIDsMatchingRegex_success_multipleSSIDs_regex() {
    getMockNetworkUtil().nearbyAccessPoints_multipleSSIDs_sameRSSI(true);
    assertEquals(getMockNetworkUtil().getExpectedSSIDs(), wisefySearch.findSSIDsMatchingRegex(TEST_REGEX));
  }

  /*
   *  isNetworkASavedConfiguration tests
   */

  @Test
  public void isNetworkASavedConfiguration_failure_nullSSIDParam() {
    getMockNetworkUtil().savedNetworks_nullList();
    assertEquals(false, wisefySearch.isNetworkASavedConfiguration(null));
  }

  @Test
  public void isNetworkASavedConfiguration_failure_nullSavedNetworkList() {
    getMockNetworkUtil().savedNetworks_nullList();
    assertEquals(false, wisefySearch.isNetworkASavedConfiguration(TEST_SSID));
  }

  @Test
  public void isNetworkASavedConfiguration_failure_emptySavedNetworkList() {
    getMockNetworkUtil().savedNetworks_emptyList();
    assertEquals(false, wisefySearch.isNetworkASavedConfiguration(TEST_SSID));
  }

  @Test
  public void isNetworkASavedConfiguration_failure_nullWifiConfiguration() {
    getMockNetworkUtil().savedNetworks_nullSavedNetwork();
    assertEquals(false, wisefySearch.isNetworkASavedConfiguration(TEST_SSID));
  }

  @Test
  public void isNetworkASavedConfiguration_failure_nullSSID() {
    getMockNetworkUtil().savedNetworks_nullSSID();
    assertEquals(false, wisefySearch.isNetworkASavedConfiguration(TEST_SSID));
  }

  @Test
  public void isNetworkASavedConfiguration_failure_nonMatchingSSID() {
    getMockNetworkUtil().savedNetworks_nonMatchingSSID();
    assertEquals(false, wisefySearch.isNetworkASavedConfiguration(TEST_SSID));
  }

  @Test
  public void isNetworkASavedConfiguration_failure_multipleNonMatchingSSIDs() {
    getMockNetworkUtil().savedNetworks_multipleNonMatchingSSIDs();
    assertEquals(false, wisefySearch.isNetworkASavedConfiguration(TEST_SSID));
  }

  @Test
  public void isNetworkASavedConfiguration_success() {
    getMockNetworkUtil().savedNetworks_matchingSSID();
    assertEquals(true, wisefySearch.isNetworkASavedConfiguration(TEST_SSID));
  }

  @Test
  public void isNetworkASavedConfiguration_success_multipleMatchingSSIDs() {
    getMockNetworkUtil().savedNetworks_multipleMatchingSSIDs();
    assertEquals(true, wisefySearch.isNetworkASavedConfiguration(TEST_SSID));
  }

  @Test
  public void isNetworkASavedConfiguration_success_multipleSSIDs_nonRegex() {
    getMockNetworkUtil().savedNetworks_multipleSSIDs(false);
    assertEquals(true, wisefySearch.isNetworkASavedConfiguration(TEST_SSID));
  }

  @Test
  public void isNetworkASavedConfiguration_success_multipleSSIDs_regex() {
    getMockNetworkUtil().savedNetworks_multipleSSIDs(true);
    assertEquals(true, wisefySearch.isNetworkASavedConfiguration(TEST_REGEX));
  }

  /*
   *  removeEntriesWithLowerSignalStrength tests
   */

  @Test
  public void removeEntriesWithLowerSignalStrength_differentSSIDs() {
    final List<ScanResult> accessPoints = getMockNetworkUtil().nearbyAccessPoints_multipleSSIDs_sameRSSI(true);
    assertEquals(getMockNetworkUtil().getExpectedNearbyAccessPoints(), wisefySearch.removeEntriesWithLowerSignalStrength(accessPoints));
  }

  @Test
  public void removeEntriesWithLowerSignalStrength_sameSignalLevels() {
    final List<ScanResult> accessPoints = getMockNetworkUtil().nearbyAccessPoints_multipleMatchingSSIDs_sameRSSI(false);
    assertEquals(getMockNetworkUtil().getExpectedNearbyAccessPoints(), wisefySearch.removeEntriesWithLowerSignalStrength(accessPoints));
  }

  @Test
  public void removeEntriesWithLowerSignalStrength_accessPoint1Higher() {
    final List<ScanResult> accessPoints = getMockNetworkUtil().nearbyAccessPoints_multipleMatchingSSIDs_accessPoint1HasHigherRSSI(true);
    assertEquals(getMockNetworkUtil().getExpectedNearbyAccessPoints(), wisefySearch.removeEntriesWithLowerSignalStrength(accessPoints));
  }

  @Test
  public void removeEntriesWithLowerSignalStrength_accessPoint2Higher() {
    final List<ScanResult> accessPoints = getMockNetworkUtil().nearbyAccessPoints_multipleMatchingSSIDs_accessPoint2HasHigherRSSI(true);
    assertEquals(getMockNetworkUtil().getExpectedNearbyAccessPoints(), wisefySearch.removeEntriesWithLowerSignalStrength(accessPoints));
  }
}
