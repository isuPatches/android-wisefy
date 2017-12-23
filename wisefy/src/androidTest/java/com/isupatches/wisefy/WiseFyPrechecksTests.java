package com.isupatches.wisefy;

import static com.isupatches.wisefy.TestUtils.TEST_SSID;
import static com.isupatches.wisefy.TestUtils.WEP_NETWORK_PASSWORD;
import static com.isupatches.wisefy.TestUtils.WEP_NETWORK_SSID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.isupatches.wisefy.constants.CommonValues;
import com.isupatches.wisefy.constants.WiseFyCodeDefs;

import org.junit.Test;

/**
 * Used to test the WiseFyPrechecks class and functionality pertaining to being able to continue
 * with processing logic.
 *
 * @see WiseFyPrechecks
 *
 * @author Patches
 */
public class WiseFyPrechecksTests extends AbstractBaseAndroidJUnit4TestClass {

  private final WiseFyPrechecks wisefyPrechecks;

  public WiseFyPrechecksTests() {
    wisefyPrechecks = WiseFyPrechecks.create(getMockWiseFyPrerequisites(), getMockWiseFySearch());
  }

  @Test
  public void addNetworkPrechecks_failure_nullSSIDParam() {
    assertEquals(WiseFyCodeDefs.MISSING_PARAMETER, wisefyPrechecks.addNetworkPrechecks(null));
  }

  @Test
  public void addNetworkPrechecks_failure_missingPrerequisites() {
    getMockWiseFyPrerequisitesUtil().missingPrerequisites();
    assertEquals(WiseFyCodeDefs.MISSING_PREREQUISITE, wisefyPrechecks.addNetworkPrechecks(TEST_SSID));
  }

  @Test
  public void addNetworkPrechecks_failure_networkAlreadySaved() {
    getMockWiseFyPrerequisitesUtil().hasPrerequisites();
    getMockWiseFySearchUtil().isNetworkASavedConfiguration(true);
    assertEquals(WiseFyCodeDefs.NETWORK_ALREADY_CONFIGURED, wisefyPrechecks.addNetworkPrechecks(TEST_SSID));
  }

  @Test
  public void addNetworkPrechecks_success() {
    getMockWiseFyPrerequisitesUtil().hasPrerequisites();
    getMockWiseFySearchUtil().isNetworkASavedConfiguration(false);
    assertEquals(CommonValues.DEFAULT_PRECHECK_RETURN, wisefyPrechecks.addNetworkPrechecks(TEST_SSID));
  }

  @Test
  public void addNetworkPrechecks_withPassword_failure_nullSSIDParam() {
    assertEquals(WiseFyCodeDefs.MISSING_PARAMETER, wisefyPrechecks.addNetworkPrechecks(null, WEP_NETWORK_PASSWORD));
  }

  @Test
  public void addNetworkPrechecks_withPassword_failure_nullPasswordParam() {
    assertEquals(WiseFyCodeDefs.MISSING_PARAMETER, wisefyPrechecks.addNetworkPrechecks(WEP_NETWORK_SSID, null));
  }

  @Test
  public void addNetworkPrechecks_withPassword_failure_missingPrerequisites() {
    getMockWiseFyPrerequisitesUtil().missingPrerequisites();
    assertEquals(WiseFyCodeDefs.MISSING_PREREQUISITE, wisefyPrechecks.addNetworkPrechecks(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD));
  }

  @Test
  public void addNetworkPrechecks_withPassword_failure_networkAlreadySaved() {
    getMockWiseFyPrerequisitesUtil().hasPrerequisites();
    getMockWiseFySearchUtil().isNetworkASavedConfiguration(true);
    assertEquals(WiseFyCodeDefs.NETWORK_ALREADY_CONFIGURED, wisefyPrechecks.addNetworkPrechecks(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD));
  }

  @Test
  public void addNetworkPrechecks_withPassword_success() {
    getMockWiseFyPrerequisitesUtil().hasPrerequisites();
    getMockWiseFySearchUtil().isNetworkASavedConfiguration(false);
    assertEquals(CommonValues.DEFAULT_PRECHECK_RETURN, wisefyPrechecks.addNetworkPrechecks(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD));
  }

  @Test
  public void checksFailed_false() {
    assertFalse(WiseFyPrechecks.checksFailed(CommonValues.DEFAULT_PRECHECK_RETURN));
  }

  @Test
  public void checksFailed_true() {
    assertTrue(WiseFyPrechecks.checksFailed(WiseFyCodeDefs.MISSING_PARAMETER));
  }

  @Test
  public void checksPassed_false() {
    assertFalse(WiseFyPrechecks.checksPassed(WiseFyCodeDefs.MISSING_PARAMETER));
  }

  @Test
  public void checksPassed_true() {
    assertTrue(WiseFyPrechecks.checksPassed(CommonValues.DEFAULT_PRECHECK_RETURN));
  }

  @Test
  public void connectToNetworkPrechecks_failure_nullParam() {
    assertEquals(WiseFyCodeDefs.MISSING_PARAMETER, wisefyPrechecks.connectToNetworkPrechecks(null));
  }

  @Test
  public void connectToNetworkPrechecks_failure_missingPrerequisites() {
    getMockWiseFyPrerequisitesUtil().missingPrerequisites();
    assertEquals(WiseFyCodeDefs.MISSING_PREREQUISITE, wisefyPrechecks.connectToNetworkPrechecks(TEST_SSID));
  }

  @Test
  public void connectToNetworkPrechecks_success() {
    getMockWiseFyPrerequisitesUtil().hasPrerequisites();
    assertEquals(CommonValues.DEFAULT_PRECHECK_RETURN, wisefyPrechecks.connectToNetworkPrechecks(TEST_SSID));
  }

  @Test
  public void disableWifiChecks_failure() {
    getMockWiseFyPrerequisitesUtil().missingPrerequisites();
    assertEquals(WiseFyCodeDefs.MISSING_PREREQUISITE, wisefyPrechecks.disableWifiChecks());
  }

  @Test
  public void disableWifiChecks_success() {
    getMockWiseFyPrerequisitesUtil().hasPrerequisites();
    assertEquals(CommonValues.DEFAULT_PRECHECK_RETURN, wisefyPrechecks.disableWifiChecks());
  }

  @Test
  public void disconnectFromCurrentNetworkChecks_failure() {
    getMockWiseFyPrerequisitesUtil().missingPrerequisites();
    assertEquals(WiseFyCodeDefs.MISSING_PREREQUISITE, wisefyPrechecks.disconnectFromCurrentNetworkChecks());
  }

  @Test
  public void disconnectFromCurrentNetworkChecks_success() {
    getMockWiseFyPrerequisitesUtil().hasPrerequisites();
    assertEquals(CommonValues.DEFAULT_PRECHECK_RETURN, wisefyPrechecks.disconnectFromCurrentNetworkChecks());
  }

  @Test
  public void enableWifiChecks_failure() {
    getMockWiseFyPrerequisitesUtil().missingPrerequisites();
    assertEquals(WiseFyCodeDefs.MISSING_PREREQUISITE, wisefyPrechecks.enableWifiChecks());
  }

  @Test
  public void enableWifiChecks_success() {
    getMockWiseFyPrerequisitesUtil().hasPrerequisites();
    assertEquals(CommonValues.DEFAULT_PRECHECK_RETURN, wisefyPrechecks.enableWifiChecks());
  }

  @Test
  public void getCurrentNetworkChecks_failure() {
    getMockWiseFyPrerequisitesUtil().missingPrerequisites();
    assertEquals(WiseFyCodeDefs.MISSING_PREREQUISITE, wisefyPrechecks.getCurrentNetworkChecks());
  }

  @Test
  public void getCurrentNetworkChecks_success() {
    getMockWiseFyPrerequisitesUtil().hasPrerequisites();
    assertEquals(CommonValues.DEFAULT_PRECHECK_RETURN, wisefyPrechecks.getCurrentNetworkChecks());
  }

  @Test
  public void getIPChecks_failure() {
    getMockWiseFyPrerequisitesUtil().missingPrerequisites();
    assertEquals(WiseFyCodeDefs.MISSING_PREREQUISITE, wisefyPrechecks.getIPChecks());
  }

  @Test
  public void getIPChecks_success() {
    getMockWiseFyPrerequisitesUtil().hasPrerequisites();
    assertEquals(CommonValues.DEFAULT_PRECHECK_RETURN, wisefyPrechecks.getIPChecks());
  }

  @Test
  public void getNearbyAccessPointsChecks_failure() {
    getMockWiseFyPrerequisitesUtil().missingPrerequisites();
    assertEquals(WiseFyCodeDefs.MISSING_PREREQUISITE, wisefyPrechecks.getNearbyAccessPointsChecks());
  }

  @Test
  public void getNearbyAccessPointsChecks_success() {
    getMockWiseFyPrerequisitesUtil().hasPrerequisites();
    assertEquals(CommonValues.DEFAULT_PRECHECK_RETURN, wisefyPrechecks.getNearbyAccessPointsChecks());
  }

  @Test
  public void getRSSIChecks_failure_nullRegexForSSIDParam() {
    assertEquals(WiseFyCodeDefs.MISSING_PARAMETER, wisefyPrechecks.getRSSIChecks(null));
  }

  @Test
  public void getRSSIChecks_failure_missingPrerequisites() {
    getMockWiseFyPrerequisitesUtil().missingPrerequisites();
    assertEquals(WiseFyCodeDefs.MISSING_PREREQUISITE, wisefyPrechecks.getRSSIChecks(TEST_SSID));
  }

  @Test
  public void getRSSIChecks_success() {
    getMockWiseFyPrerequisitesUtil().hasPrerequisites();
    assertEquals(CommonValues.DEFAULT_PRECHECK_RETURN, wisefyPrechecks.getRSSIChecks(TEST_SSID));
  }

  @Test
  public void getSavedNetworkChecks_failure_nullRegexForSSIDParam() {
    assertEquals(WiseFyCodeDefs.MISSING_PARAMETER, wisefyPrechecks.getSavedNetworkChecks(null));
  }

  @Test
  public void getSavedNetworkChecks_failure_missingPrerequisites() {
    getMockWiseFyPrerequisitesUtil().missingPrerequisites();
    assertEquals(WiseFyCodeDefs.MISSING_PREREQUISITE, wisefyPrechecks.getSavedNetworkChecks(TEST_SSID));
  }

  @Test
  public void getSavedNetworkChecks_success() {
    getMockWiseFyPrerequisitesUtil().hasPrerequisites();
    assertEquals(CommonValues.DEFAULT_PRECHECK_RETURN, wisefyPrechecks.getSavedNetworkChecks(TEST_SSID));
  }

  @Test
  public void getSavedNetworksChecks_failure() {
    getMockWiseFyPrerequisitesUtil().missingPrerequisites();
    assertEquals(WiseFyCodeDefs.MISSING_PREREQUISITE, wisefyPrechecks.getSavedNetworksChecks());
  }

  @Test
  public void getSavedNetworksChecks_success() {
    getMockWiseFyPrerequisitesUtil().hasPrerequisites();
    assertEquals(CommonValues.DEFAULT_PRECHECK_RETURN, wisefyPrechecks.getSavedNetworksChecks());
  }

  @Test
  public void getSavedNetworksChecks_withRegex_failure_nullRegexForSSIDParam() {
    assertEquals(WiseFyCodeDefs.MISSING_PARAMETER, wisefyPrechecks.getSavedNetworksChecks(null));
  }

  @Test
  public void getSavedNetworksChecks_withRegex_failure_missingPrerequisites() {
    getMockWiseFyPrerequisitesUtil().missingPrerequisites();
    assertEquals(WiseFyCodeDefs.MISSING_PREREQUISITE, wisefyPrechecks.getSavedNetworksChecks(TEST_SSID));
  }

  @Test
  public void getSavedNetworksChecks_with_regex_success() {
    getMockWiseFyPrerequisitesUtil().hasPrerequisites();
    assertEquals(CommonValues.DEFAULT_PRECHECK_RETURN, wisefyPrechecks.getSavedNetworksChecks(TEST_SSID));
  }

  @Test
  public void isDeviceConnectedToMobileNetworkChecks_failure() {
    getMockWiseFyPrerequisitesUtil().missingPrerequisites();
    assertEquals(WiseFyCodeDefs.MISSING_PREREQUISITE, wisefyPrechecks.isDeviceConnectedToMobileNetworkChecks());
  }

  @Test
  public void isDeviceConnectedToMobileNetworkChecks_success() {
    getMockWiseFyPrerequisitesUtil().hasPrerequisites();
    assertEquals(CommonValues.DEFAULT_PRECHECK_RETURN, wisefyPrechecks.isDeviceConnectedToMobileNetworkChecks());
  }

  @Test
  public void isDeviceConnectedToMobileOrWifiNetworkChecks_failure() {
    getMockWiseFyPrerequisitesUtil().missingPrerequisites();
    assertEquals(WiseFyCodeDefs.MISSING_PREREQUISITE, wisefyPrechecks.isDeviceConnectedToMobileOrWifiNetworkChecks());
  }

  @Test
  public void isDeviceConnectedToMobileOrWifiNetworkChecks_success() {
    getMockWiseFyPrerequisitesUtil().hasPrerequisites();
    assertEquals(CommonValues.DEFAULT_PRECHECK_RETURN, wisefyPrechecks.isDeviceConnectedToMobileOrWifiNetworkChecks());
  }

  @Test
  public void isDeviceConnectedToSSIDChecks_failure_nullSSIDParam() {
    assertEquals(WiseFyCodeDefs.MISSING_PARAMETER, wisefyPrechecks.isDeviceConnectedToSSIDChecks(null));
  }

  @Test
  public void isDeviceConnectedToSSIDChecks_failure_missingPrerequisites() {
    getMockWiseFyPrerequisitesUtil().missingPrerequisites();
    assertEquals(WiseFyCodeDefs.MISSING_PREREQUISITE, wisefyPrechecks.isDeviceConnectedToSSIDChecks(TEST_SSID));
  }

  @Test
  public void isDeviceConnectedToSSIDChecks_success() {
    getMockWiseFyPrerequisitesUtil().hasPrerequisites();
    assertEquals(CommonValues.DEFAULT_PRECHECK_RETURN, wisefyPrechecks.isDeviceConnectedToSSIDChecks(TEST_SSID));
  }

  @Test
  public void isDeviceConnectedToWifiNetworkChecks_failure() {
    getMockWiseFyPrerequisitesUtil().missingPrerequisites();
    assertEquals(WiseFyCodeDefs.MISSING_PREREQUISITE, wisefyPrechecks.isDeviceConnectedToWifiNetworkChecks());
  }

  @Test
  public void isDeviceConnectedToWifiNetworkChecks_success() {
    getMockWiseFyPrerequisitesUtil().hasPrerequisites();
    assertEquals(CommonValues.DEFAULT_PRECHECK_RETURN, wisefyPrechecks.isDeviceConnectedToWifiNetworkChecks());
  }

  @Test
  public void isDeviceRoamingChecks_failure() {
    getMockWiseFyPrerequisitesUtil().missingPrerequisites();
    assertEquals(WiseFyCodeDefs.MISSING_PREREQUISITE, wisefyPrechecks.isDeviceRoamingChecks());
  }

  @Test
  public void isDeviceRoamingChecks_success() {
    getMockWiseFyPrerequisitesUtil().hasPrerequisites();
    assertEquals(CommonValues.DEFAULT_PRECHECK_RETURN, wisefyPrechecks.isDeviceRoamingChecks());
  }

  @Test
  public void isNetworkSavedChecks_failure() {
    getMockWiseFyPrerequisitesUtil().missingPrerequisites();
    assertEquals(WiseFyCodeDefs.MISSING_PREREQUISITE, wisefyPrechecks.isNetworkSavedChecks());
  }

  @Test
  public void isNetworkSavedChecks_success() {
    getMockWiseFyPrerequisitesUtil().hasPrerequisites();
    assertEquals(CommonValues.DEFAULT_PRECHECK_RETURN, wisefyPrechecks.isNetworkSavedChecks());
  }

  @Test
  public void isWifiEnabledChecks_failure() {
    getMockWiseFyPrerequisitesUtil().missingPrerequisites();
    assertEquals(WiseFyCodeDefs.MISSING_PREREQUISITE, wisefyPrechecks.isWifiEnabledChecks());
  }

  @Test
  public void isWifiEnabledChecks_success() {
    getMockWiseFyPrerequisitesUtil().hasPrerequisites();
    assertEquals(CommonValues.DEFAULT_PRECHECK_RETURN, wisefyPrechecks.isWifiEnabledChecks());
  }

  @Test
  public void removeNetworkCheck_failure_nullSSIDToRemoveParam() {
    assertEquals(WiseFyCodeDefs.MISSING_PARAMETER, wisefyPrechecks.removeNetworkCheck(null));
  }

  @Test
  public void removeNetworkCheck_failure_missingPrerequisites() {
    getMockWiseFyPrerequisitesUtil().missingPrerequisites();
    assertEquals(WiseFyCodeDefs.MISSING_PREREQUISITE, wisefyPrechecks.removeNetworkCheck(TEST_SSID));
  }

  @Test
  public void removeNetworkCheck_success() {
    getMockWiseFyPrerequisitesUtil().hasPrerequisites();
    assertEquals(CommonValues.DEFAULT_PRECHECK_RETURN, wisefyPrechecks.removeNetworkCheck(TEST_SSID));
  }

  @Test
  public void searchForAccessPointChecks_failure_nullRegexForSSIDParam() {
    assertEquals(WiseFyCodeDefs.MISSING_PARAMETER, wisefyPrechecks.searchForAccessPointChecks(null));
  }

  @Test
  public void searchForAccessPointChecks_failure_missingPrerequisites() {
    getMockWiseFyPrerequisitesUtil().missingPrerequisites();
    assertEquals(WiseFyCodeDefs.MISSING_PREREQUISITE, wisefyPrechecks.searchForAccessPointChecks(TEST_SSID));
  }

  @Test
  public void searchForAccessPointChecks_success() {
    getMockWiseFyPrerequisitesUtil().hasPrerequisites();
    assertEquals(CommonValues.DEFAULT_PRECHECK_RETURN, wisefyPrechecks.searchForAccessPointChecks(TEST_SSID));
  }

  @Test
  public void searchForAccessPointsChecks_failure_nullRegexForSSIDParam() {
    assertEquals(WiseFyCodeDefs.MISSING_PARAMETER, wisefyPrechecks.searchForAccessPointsChecks(null));
  }

  @Test
  public void searchForAccessPointsChecks_failure_missingPrerequisites() {
    getMockWiseFyPrerequisitesUtil().missingPrerequisites();
    assertEquals(WiseFyCodeDefs.MISSING_PREREQUISITE, wisefyPrechecks.searchForAccessPointsChecks(TEST_SSID));
  }

  @Test
  public void searchForAccessPointsChecks_success() {
    getMockWiseFyPrerequisitesUtil().hasPrerequisites();
    assertEquals(CommonValues.DEFAULT_PRECHECK_RETURN, wisefyPrechecks.searchForAccessPointsChecks(TEST_SSID));
  }

  @Test
  public void searchForSSIDChecks_failure_nullRegexForSSIDParam() {
    assertEquals(WiseFyCodeDefs.MISSING_PARAMETER, wisefyPrechecks.searchForSSIDChecks(null));
  }

  @Test
  public void searchForSSIDChecks_failure_missingPrerequisites() {
    getMockWiseFyPrerequisitesUtil().missingPrerequisites();
    assertEquals(WiseFyCodeDefs.MISSING_PREREQUISITE, wisefyPrechecks.searchForSSIDChecks(TEST_SSID));
  }

  @Test
  public void searchForSSIDChecks_success() {
    getMockWiseFyPrerequisitesUtil().hasPrerequisites();
    assertEquals(CommonValues.DEFAULT_PRECHECK_RETURN, wisefyPrechecks.searchForSSIDChecks(TEST_SSID));
  }

  @Test
  public void searchForSSIDsChecks_failure_nullRegexForSSIDParam() {
    assertEquals(WiseFyCodeDefs.MISSING_PARAMETER, wisefyPrechecks.searchForSSIDsChecks(null));
  }

  @Test
  public void searchForSSIDsChecks_failure_missingPrerequisites() {
    getMockWiseFyPrerequisitesUtil().missingPrerequisites();
    assertEquals(WiseFyCodeDefs.MISSING_PREREQUISITE, wisefyPrechecks.searchForSSIDsChecks(TEST_SSID));
  }

  @Test
  public void searchForSSIDsChecks_success() {
    getMockWiseFyPrerequisitesUtil().hasPrerequisites();
    assertEquals(CommonValues.DEFAULT_PRECHECK_RETURN, wisefyPrechecks.searchForSSIDsChecks(TEST_SSID));
  }
}
