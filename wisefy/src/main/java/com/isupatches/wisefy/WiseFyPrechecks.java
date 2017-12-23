/*
 * Copyright 2017 Patches Klinefelter
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.isupatches.wisefy;

import android.support.annotation.NonNull;

import com.isupatches.wisefy.annotations.Internal;
import com.isupatches.wisefy.constants.CommonValues;
import com.isupatches.wisefy.constants.WiseFyCodeDefs;
import com.isupatches.wisefy.constants.WiseFyCodeDefs.WiseFyCodes;
import com.isupatches.wisefy.utils.StringUtil;

/**
 * A helper class with methods to determine if the necessary requirements are met to preform operations.
 *
 * @author Patches
 */
@Internal
class WiseFyPrechecks {

  private final WiseFyPrerequisites wisefyPrerequisites;
  private final WiseFySearch wisefySearch;

  /**
   * Private constructor.
   *
   * @param wisefyPrerequisites The prerequisites instance needed
   * @param wisefySearch The search instance needed
   *
   * @see WiseFyPrerequisites
   * @see WiseFySearch
   */
  private WiseFyPrechecks(final WiseFyPrerequisites wisefyPrerequisites, final WiseFySearch wisefySearch) {
    this.wisefyPrerequisites = wisefyPrerequisites;
    this.wisefySearch = wisefySearch;
  }

  /**
   * Factory pattern creation method to get an instance of WiseFyPrechecks.
   *
   * @param wisefyPrerequisites The prerequisites instance needed
   * @param wisefySearch The search instance needed
   *
   * @return WiseFyPrechecks - An instance of WiseFyPrechecks
   *
   * @see WiseFyPrerequisites
   * @see WiseFySearch
   */
  static WiseFyPrechecks create(final WiseFyPrerequisites wisefyPrerequisites, final WiseFySearch wisefySearch) {
    return new WiseFyPrechecks(wisefyPrerequisites, wisefySearch);
  }

  /**
   * To check for necessary requirements to add a network.
   *
   * @param ssid The ssid of the network to add
   *
   * @return 0 or int - An error code or 0 for successfully passing all prechecks
   *
   * @see CommonValues#DEFAULT_PRECHECK_RETURN
   * @see StringUtil#isEmpty(String)
   * @see WiseFyPrerequisites#missingPrerequisites()
   * @see WiseFySearch#isNetworkASavedConfiguration(String)
   */
  @WiseFyCodes
  int addNetworkPrechecks(@NonNull final String ssid) {
    int precheckResult = CommonValues.DEFAULT_PRECHECK_RETURN;
    if (StringUtil.isEmpty(ssid)) {
      precheckResult = WiseFyCodeDefs.MISSING_PARAMETER;
    }
    if (wisefyPrerequisites.missingPrerequisites()) {
      precheckResult = WiseFyCodeDefs.MISSING_PREREQUISITE;
    }
    if (wisefySearch.isNetworkASavedConfiguration(ssid)) {
      precheckResult = WiseFyCodeDefs.NETWORK_ALREADY_CONFIGURED;
    }
    return precheckResult;
  }

  /**
   * To check for necessary requirements to add a network.
   *
   * @param ssid The ssid of the network to add
   * @param password The password for the network to add
   *
   * @return 0 or int - An error code or 0 for successfully passing all prechecks
   *
   * @see CommonValues#DEFAULT_PRECHECK_RETURN
   * @see StringUtil#isEmpty(String)
   * @see WiseFyPrerequisites#missingPrerequisites()
   * @see WiseFySearch#isNetworkASavedConfiguration(String)
   */
  @WiseFyCodes
  int addNetworkPrechecks(@NonNull final String ssid, final String password) {
    int precheckResult = CommonValues.DEFAULT_PRECHECK_RETURN;
    if (StringUtil.isEmpty(ssid) || StringUtil.isEmpty(password)) {
      precheckResult = WiseFyCodeDefs.MISSING_PARAMETER;
    }
    if (wisefyPrerequisites.missingPrerequisites()) {
      precheckResult = WiseFyCodeDefs.MISSING_PREREQUISITE;
    }
    if (wisefySearch.isNetworkASavedConfiguration(ssid)) {
      precheckResult = WiseFyCodeDefs.NETWORK_ALREADY_CONFIGURED;
    }
    return precheckResult;
  }

  /**
   * A helper method to determine if the prechecks for an operation failed.
   *
   * @param result Whether the precheck failed
   *
   * @return bool - true if checks failed
   *
   * @see CommonValues#DEFAULT_PRECHECK_RETURN
   */
  static boolean checksFailed(final int result) {
    return result < CommonValues.DEFAULT_PRECHECK_RETURN;
  }

  /**
   * A helper method to determine if the prechecks for an operation passed.
   *
   * @param result Whether the precheck passed
   *
   * @return bool - true if checks passed
   *
   * @see CommonValues#DEFAULT_PRECHECK_RETURN
   */
  static boolean checksPassed(final int result) {
    return result >= CommonValues.DEFAULT_PRECHECK_RETURN;
  }

  /**
   * To check for necessary requirements to connect to a network.
   *
   * @param ssidToConnectTo The network to connect to
   *
   * @return int - 0 or negative error code
   *
   * @see #checkForParamAndPrerequisites(String)
   */
  @WiseFyCodes
  int connectToNetworkPrechecks(final String ssidToConnectTo) {
    return checkForParamAndPrerequisites(ssidToConnectTo);
  }

  /**
   * To check for necessary requirements to disable wifi.
   *
   * @return int - 0 or negative error code
   *
   * @see #checkForPrerequisites()
   */
  @WiseFyCodes
  int disableWifiChecks() {
    return checkForPrerequisites();
  }

  /**
   * To check for necessary requirements to disconnect from the current network.
   *
   * @return int - 0 or negative error code
   *
   * @see #checkForPrerequisites()
   */
  @WiseFyCodes
  int disconnectFromCurrentNetworkChecks() {
    return checkForPrerequisites();
  }

  /**
   * To check for necessary requirements to enable wifi.
   *
   * @return int - 0 or negative error code
   *
   * @see #checkForPrerequisites()
   */
  @WiseFyCodes
  int enableWifiChecks() {
    return checkForPrerequisites();
  }

  /**
   * To check for necessary requirements to get the device's current network.
   *
   * @return int - 0 or negative error code
   *
   * @see #checkForPrerequisites()
   */
  @WiseFyCodes
  int getCurrentNetworkChecks() {
    return checkForPrerequisites();
  }

  /**
   * To check for necessary requirements to get the device's IP.
   *
   * @return int - 0 or negative error code
   *
   * @see #checkForPrerequisites()
   */
  @WiseFyCodes
  int getIPChecks() {
    return checkForPrerequisites();
  }

  /**
   * To check for necessary requirements to get nearby access points.
   *
   * @return int - 0 or negative error code
   *
   * @see #checkForPrerequisites()
   */
  @WiseFyCodes
  int getNearbyAccessPointsChecks() {
    return checkForPrerequisites();
  }

  /**
   * To check for necessary requirements to get a network's RSSI.
   *
   * @param regexForSSID The regex param to check
   *
   * @return int - 0 or negative error code
   *
   * @see #checkForParamAndPrerequisites(String)
   */
  @WiseFyCodes
  int getRSSIChecks(final String regexForSSID) {
    return checkForParamAndPrerequisites(regexForSSID);
  }

  /**
   * To check for necessary requirements to get a saved network.
   *
   * @param regexForSSID The regex param to check
   *
   * @return int - 0 or negative error code
   *
   * @see #checkForParamAndPrerequisites(String)
   */
  @WiseFyCodes
  int getSavedNetworkChecks(final String regexForSSID) {
    return checkForParamAndPrerequisites(regexForSSID);
  }

  /**
   * To check for necessary requirements to get saved networks.
   *
   * @return int - 0 or negative error code
   *
   * @see #checkForPrerequisites()
   */
  @WiseFyCodes
  int getSavedNetworksChecks() {
    return checkForPrerequisites();
  }

  /**
   * To check for necessary requirements to get saved networks.
   *
   * @param regexForSSID The ssid param to check
   *
   * @return int - 0 or negative error code
   *
   * @see #checkForParamAndPrerequisites(String)
   */
  @WiseFyCodes
  int getSavedNetworksChecks(final String regexForSSID) {
    return checkForParamAndPrerequisites(regexForSSID);
  }

  /**
   * To check for necessary requirements to see if a device is connected to a mobile network.
   *
   * @return int - 0 or negative error code
   *
   * @see #checkForPrerequisites()
   */
  @WiseFyCodes
  int isDeviceConnectedToMobileNetworkChecks() {
    return checkForPrerequisites();
  }

  /**
   * To check for necessary requirements to see if a device is connected to a network.
   *
   * @return int - 0 or negative error code
   *
   * @see #checkForPrerequisites()
   */
  @WiseFyCodes
  int isDeviceConnectedToMobileOrWifiNetworkChecks() {
    return checkForPrerequisites();
  }

  /**
   * To check for necessary requirements to see if a device is connected to a specific SSID.
   *
   * @param ssid The ssid param to check
   *
   * @return int - 0 or negative error code
   *
   * @see #checkForParamAndPrerequisites(String)
   */
  @WiseFyCodes
  int isDeviceConnectedToSSIDChecks(final String ssid) {
    return checkForParamAndPrerequisites(ssid);
  }

  /**
   * To check for necessary requirements to see if a device is connected to a wifi network.
   *
   * @return int - 0 or negative error code
   *
   * @see #checkForPrerequisites()
   */
  @WiseFyCodes
  int isDeviceConnectedToWifiNetworkChecks() {
    return checkForPrerequisites();
  }

  /**
   * To check for necessary requirements to see if a device is roaming.
   *
   * @return int - 0 or negative error code
   *
   * @see #checkForPrerequisites()
   */
  @WiseFyCodes
  int isDeviceRoamingChecks() {
    return checkForPrerequisites();
  }

  /**
   * To check for necessary requirements to see if a device has a saved network.
   *
   * @return int - 0 or negative error code
   *
   * @see #checkForPrerequisites()
   */
  @WiseFyCodes
  int isNetworkSavedChecks() {
    return checkForPrerequisites();
  }

  /**
   * To check for necessary requirements to see if a device has wifi enabled.
   *
   * @return int - 0 or negative error code
   *
   * @see #checkForPrerequisites()
   */
  @WiseFyCodes
  int isWifiEnabledChecks() {
    return checkForPrerequisites();
  }

  /**
   * To check for necessary requirements to remove a network.
   *
   * @param ssidToRemove The ssid param to check
   *
   * @return int - 0 or negative error code
   *
   * @see #checkForParamAndPrerequisites(String)
   */
  @WiseFyCodes
  int removeNetworkCheck(final String ssidToRemove) {
    return checkForParamAndPrerequisites(ssidToRemove);
  }

  /**
   * To check for necessary requirements to search for an access point.
   *
   * @param regexForSSID The regex param to check
   *
   * @return int - 0 or negative error code
   *
   * @see #checkForParamAndPrerequisites(String)
   */
  @WiseFyCodes
  int searchForAccessPointChecks(final String regexForSSID) {
    return checkForParamAndPrerequisites(regexForSSID);
  }

  /**
   * To check for necessary requirements to search for access points.
   *
   * @param regexForSSID The regex param to check
   *
   * @return int - 0 or negative error code
   *
   * @see #checkForParamAndPrerequisites(String)
   */
  @WiseFyCodes
  int searchForAccessPointsChecks(final String regexForSSID) {
    return checkForParamAndPrerequisites(regexForSSID);
  }

  /**
   * To check for necessary requirements to search for an SSID.
   *
   * @param regexForSSID The regex param to check
   *
   * @return int - 0 or negative error code
   *
   * @see #checkForParamAndPrerequisites(String)
   */
  @WiseFyCodes
  int searchForSSIDChecks(final String regexForSSID) {
    return checkForParamAndPrerequisites(regexForSSID);
  }

  /**
   * To check for necessary requirements to search for SSID's.
   *
   * @param regexForSSID The regex param to check
   *
   * @return int - 0 or negative error code
   *
   * @see #checkForParamAndPrerequisites(String)
   */
  @WiseFyCodes
  int searchForSSIDsChecks(final String regexForSSID) {
    return checkForParamAndPrerequisites(regexForSSID);
  }

  /*
   * HELPERS
   */

  /**
   * Internal helper to check for prerequisites.
   *
   * @return int - 0 or negative error code
   *
   * @see CommonValues#DEFAULT_PRECHECK_RETURN
   * @see WiseFyPrerequisites#missingPrerequisites()
   */
  @WiseFyCodes
  private int checkForPrerequisites() {
    int precheckResult = CommonValues.DEFAULT_PRECHECK_RETURN;
    if (wisefyPrerequisites.missingPrerequisites()) {
      precheckResult = WiseFyCodeDefs.MISSING_PREREQUISITE;
    }
    return precheckResult;
  }

  /**
   * Internal helper to check for prerequisites and the presence of a parameter.
   *
   * @param param The param to check
   *
   * @return int - 0 or negative error code
   *
   * @see CommonValues#DEFAULT_PRECHECK_RETURN
   * @see StringUtil#isEmpty(String)
   * @see WiseFyPrerequisites#missingPrerequisites()
   */
  @WiseFyCodes
  private int checkForParamAndPrerequisites(final String param) {
    int precheckResult = CommonValues.DEFAULT_PRECHECK_RETURN;
    if (StringUtil.isEmpty(param)) {
      precheckResult = WiseFyCodeDefs.MISSING_PARAMETER;
    }
    if (wisefyPrerequisites.missingPrerequisites()) {
      precheckResult = WiseFyCodeDefs.MISSING_PREREQUISITE;
    }
    return precheckResult;
  }
}
