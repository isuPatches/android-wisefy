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
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;

import com.isupatches.wisefy.annotations.Internal;
import com.isupatches.wisefy.constants.WiseFyCodes;
import com.isupatches.wisefy.constants.WiseFyCodes.WiseFyCode;
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
  private WiseFyPrechecks(@NonNull final WiseFyPrerequisites wisefyPrerequisites, @NonNull final WiseFySearch wisefySearch) {
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
  @NonNull
  static WiseFyPrechecks create(@NonNull final WiseFyPrerequisites wisefyPrerequisites, @NonNull final WiseFySearch wisefySearch) {
    return new WiseFyPrechecks(wisefyPrerequisites, wisefySearch);
  }

  /**
   * To check for necessary requirements to add a network.
   *
   * @param ssid The ssid of the network to add
   *
   * @return int - An error code or WiseFyCodes.DEFAULT_PRECHECK_RETURN for successfully passing all prechecks
   *
   * @see StringUtil#isEmpty(String)
   * @see WiseFyCodes
   * @see WiseFyPrerequisites#missingPrerequisites()
   * @see WiseFySearch#isNetworkASavedConfiguration(String)
   */
  @WiseFyCode
  @RequiresPermission(android.Manifest.permission.ACCESS_WIFI_STATE)
  int addNetworkPrechecks(@Nullable final String ssid) {
    int precheckResult = WiseFyCodes.DEFAULT_PRECHECK_RETURN;
    if (StringUtil.isEmpty(ssid)) {
      precheckResult = WiseFyCodes.MISSING_PARAMETER;
    }
    if (wisefyPrerequisites.missingPrerequisites()) {
      precheckResult = WiseFyCodes.MISSING_PREREQUISITE;
    }
    if (wisefySearch.isNetworkASavedConfiguration(ssid)) {
      precheckResult = WiseFyCodes.NETWORK_ALREADY_CONFIGURED;
    }
    return precheckResult;
  }

  /**
   * To check for necessary requirements to add a network.
   *
   * @param ssid The ssid of the network to add
   * @param password The password for the network to add
   *
   * @return int - An error code or WiseFyCodes.DEFAULT_PRECHECK_RETURN for successfully passing all prechecks
   *
   * @see StringUtil#isEmpty(String)
   * @see WiseFyCodes
   * @see WiseFyPrerequisites#missingPrerequisites()
   * @see WiseFySearch#isNetworkASavedConfiguration(String)
   */
  @WiseFyCode
  @RequiresPermission(android.Manifest.permission.ACCESS_WIFI_STATE)
  int addNetworkPrechecks(@Nullable final String ssid, @Nullable final String password) {
    int precheckResult = WiseFyCodes.DEFAULT_PRECHECK_RETURN;
    if (StringUtil.isEmpty(ssid) || StringUtil.isEmpty(password)) {
      precheckResult = WiseFyCodes.MISSING_PARAMETER;
    }
    if (wisefyPrerequisites.missingPrerequisites()) {
      precheckResult = WiseFyCodes.MISSING_PREREQUISITE;
    }
    if (wisefySearch.isNetworkASavedConfiguration(ssid)) {
      precheckResult = WiseFyCodes.NETWORK_ALREADY_CONFIGURED;
    }
    return precheckResult;
  }

  /**
   * A helper method to determine if the prechecks for an operation failed.
   *
   * @param result Whether the precheck failed
   *
   * @return boolean - True if checks failed
   *
   * @see WiseFyCodes#DEFAULT_PRECHECK_RETURN
   */
  static boolean checksFailed(@WiseFyCode final int result) {
    return result < WiseFyCodes.DEFAULT_PRECHECK_RETURN;
  }

  /**
   * A helper method to determine if the prechecks for an operation passed.
   *
   * @param result Whether the precheck passed
   *
   * @return boolean - True if checks passed
   *
   * @see WiseFyCodes#DEFAULT_PRECHECK_RETURN
   */
  static boolean checksPassed(@WiseFyCode final int result) {
    return result >= WiseFyCodes.DEFAULT_PRECHECK_RETURN;
  }

  /**
   * To check for necessary requirements to connect to a network.
   *
   * @param ssidToConnectTo The SSID of the network to connect to
   *
   * @return int - An error code or WiseFyCodes.DEFAULT_PRECHECK_RETURN for successfully passing all prechecks
   *
   * @see #checkForParamAndPrerequisites(String)
   */
  @WiseFyCode
  int connectToNetworkPrechecks(@Nullable final String ssidToConnectTo) {
    return checkForParamAndPrerequisites(ssidToConnectTo);
  }

  /**
   * To check for necessary requirements to disable wifi.
   *
   * @return int - An error code or WiseFyCodes.DEFAULT_PRECHECK_RETURN for successfully passing all prechecks
   *
   * @see #checkForPrerequisites()
   */
  @WiseFyCode
  int disableWifiChecks() {
    return checkForPrerequisites();
  }

  /**
   * To check for necessary requirements to disconnect from the current network.
   *
   * @return int - An error code or WiseFyCodes.DEFAULT_PRECHECK_RETURN for successfully passing all prechecks
   *
   * @see #checkForPrerequisites()
   */
  @WiseFyCode
  int disconnectFromCurrentNetworkChecks() {
    return checkForPrerequisites();
  }

  /**
   * To check for necessary requirements to enable wifi.
   *
   * @return int - An error code or WiseFyCodes.DEFAULT_PRECHECK_RETURN for successfully passing all prechecks
   *
   * @see #checkForPrerequisites()
   */
  @WiseFyCode
  int enableWifiChecks() {
    return checkForPrerequisites();
  }

  /**
   * To check for necessary requirements to get the device's current network.
   *
   * @return int - An error code or WiseFyCodes.DEFAULT_PRECHECK_RETURN for successfully passing all prechecks
   *
   * @see #checkForPrerequisites()
   */
  @WiseFyCode
  int getCurrentNetworkChecks() {
    return checkForPrerequisites();
  }

  /**
   * To check for necessary requirements to get the device's current network details.
   *
   * @return int - An error code or WiseFyCodes.DEFAULT_PRECHECK_RETURN for successfully passing all prechecks
   *
   * @see #checkForPrerequisites()
   */
  @WiseFyCode
  int getCurrentNetworkInfoChecks() {
    return checkForPrerequisites();
  }

  /**
   * To check for necessary requirements to get the device's IP.
   *
   * @return int - An error code or WiseFyCodes.DEFAULT_PRECHECK_RETURN for successfully passing all prechecks
   *
   * @see #checkForPrerequisites()
   */
  @WiseFyCode
  int getIPChecks() {
    return checkForPrerequisites();
  }

  /**
   * To check for necessary requirements to get nearby access points.
   *
   * @return int - An error code or WiseFyCodes.DEFAULT_PRECHECK_RETURN for successfully passing all prechecks
   *
   * @see #checkForPrerequisites()
   */
  @WiseFyCode
  int getNearbyAccessPointsChecks() {
    return checkForPrerequisites();
  }

  /**
   * To check for necessary requirements to get a network's RSSI.
   *
   * @param regexForSSID The regex param to check
   *
   * @return int - An error code or WiseFyCodes.DEFAULT_PRECHECK_RETURN for successfully passing all prechecks
   *
   * @see #checkForParamAndPrerequisites(String)
   */
  @WiseFyCode
  int getRSSIChecks(@Nullable final String regexForSSID) {
    return checkForParamAndPrerequisites(regexForSSID);
  }

  /**
   * To check for necessary requirements to get a saved network.
   *
   * @param regexForSSID The regex param to check
   *
   * @return int - An error code or WiseFyCodes.DEFAULT_PRECHECK_RETURN for successfully passing all prechecks
   *
   * @see #checkForParamAndPrerequisites(String)
   */
  @WiseFyCode
  int getSavedNetworkChecks(@Nullable final String regexForSSID) {
    return checkForParamAndPrerequisites(regexForSSID);
  }

  /**
   * To check for necessary requirements to get saved networks.
   *
   * @return int - An error code or WiseFyCodes.DEFAULT_PRECHECK_RETURN for successfully passing all prechecks
   *
   * @see #checkForPrerequisites()
   */
  @WiseFyCode
  int getSavedNetworksChecks() {
    return checkForPrerequisites();
  }

  /**
   * To check for necessary requirements to get saved networks.
   *
   * @param regexForSSID The ssid param to check
   *
   * @return int - An error code or WiseFyCodes.DEFAULT_PRECHECK_RETURN for successfully passing all prechecks
   *
   * @see #checkForParamAndPrerequisites(String)
   */
  @WiseFyCode
  int getSavedNetworksChecks(@Nullable final String regexForSSID) {
    return checkForParamAndPrerequisites(regexForSSID);
  }

  /**
   * To check for necessary requirements to see if a device is connected to a mobile network.
   *
   * @return int - An error code or WiseFyCodes.DEFAULT_PRECHECK_RETURN for successfully passing all prechecks
   *
   * @see #checkForPrerequisites()
   */
  @WiseFyCode
  int isDeviceConnectedToMobileNetworkChecks() {
    return checkForPrerequisites();
  }

  /**
   * To check for necessary requirements to see if a device is connected to a network.
   *
   * @return int - An error code or WiseFyCodes.DEFAULT_PRECHECK_RETURN for successfully passing all prechecks
   *
   * @see #checkForPrerequisites()
   */
  @WiseFyCode
  int isDeviceConnectedToMobileOrWifiNetworkChecks() {
    return checkForPrerequisites();
  }

  /**
   * To check for necessary requirements to see if a device is connected to a specific SSID.
   *
   * @param ssid The ssid param to check
   *
   * @return int - An error code or WiseFyCodes.DEFAULT_PRECHECK_RETURN for successfully passing all prechecks
   *
   * @see #checkForParamAndPrerequisites(String)
   */
  @WiseFyCode
  int isDeviceConnectedToSSIDChecks(@Nullable final String ssid) {
    return checkForParamAndPrerequisites(ssid);
  }

  /**
   * To check for necessary requirements to see if a device is connected to a wifi network.
   *
   * @return int - An error code or WiseFyCodes.DEFAULT_PRECHECK_RETURN for successfully passing all prechecks
   *
   * @see #checkForPrerequisites()
   */
  @WiseFyCode
  int isDeviceConnectedToWifiNetworkChecks() {
    return checkForPrerequisites();
  }

  /**
   * To check for necessary requirements to see if a device is roaming.
   *
   * @return int - An error code or WiseFyCodes.DEFAULT_PRECHECK_RETURN for successfully passing all prechecks
   *
   * @see #checkForPrerequisites()
   */
  @WiseFyCode
  int isDeviceRoamingChecks() {
    return checkForPrerequisites();
  }

  /**
   * To check for necessary requirements to see if a device has a saved network.
   *
   * @return int - An error code or WiseFyCodes.DEFAULT_PRECHECK_RETURN for successfully passing all prechecks
   *
   * @see #checkForPrerequisites()
   */
  @WiseFyCode
  int isNetworkSavedChecks() {
    return checkForPrerequisites();
  }

  /**
   * To check for necessary requirements to see if a device has wifi enabled.
   *
   * @return int - An error code or WiseFyCodes.DEFAULT_PRECHECK_RETURN for successfully passing all prechecks
   *
   * @see #checkForPrerequisites()
   */
  @WiseFyCode
  int isWifiEnabledChecks() {
    return checkForPrerequisites();
  }

  /**
   * To check for necessary requirements to remove a network.
   *
   * @param ssidToRemove The ssid param to check
   *
   * @return int - An error code or WiseFyCodes.DEFAULT_PRECHECK_RETURN for successfully passing all prechecks
   *
   * @see #checkForParamAndPrerequisites(String)
   */
  @WiseFyCode
  int removeNetworkCheck(@Nullable final String ssidToRemove) {
    return checkForParamAndPrerequisites(ssidToRemove);
  }

  /**
   * To check for necessary requirements to search for an access point.
   *
   * @param regexForSSID The regex param to check
   *
   * @return int - An error code or WiseFyCodes.DEFAULT_PRECHECK_RETURN for successfully passing all prechecks
   *
   * @see #checkForParamAndPrerequisites(String)
   */
  @WiseFyCode
  int searchForAccessPointChecks(@Nullable final String regexForSSID) {
    return checkForParamAndPrerequisites(regexForSSID);
  }

  /**
   * To check for necessary requirements to search for access points.
   *
   * @param regexForSSID The regex param to check
   *
   * @return int - An error code or WiseFyCodes.DEFAULT_PRECHECK_RETURN for successfully passing all prechecks
   *
   * @see #checkForParamAndPrerequisites(String)
   */
  @WiseFyCode
  int searchForAccessPointsChecks(@Nullable final String regexForSSID) {
    return checkForParamAndPrerequisites(regexForSSID);
  }

  /**
   * To check for necessary requirements to search for an SSID.
   *
   * @param regexForSSID The regex param to check
   *
   * @return int - An error code or WiseFyCodes.DEFAULT_PRECHECK_RETURN for successfully passing all prechecks
   *
   * @see #checkForParamAndPrerequisites(String)
   */
  @WiseFyCode
  int searchForSSIDChecks(@Nullable final String regexForSSID) {
    return checkForParamAndPrerequisites(regexForSSID);
  }

  /**
   * To check for necessary requirements to search for SSID's.
   *
   * @param regexForSSID The regex param to check
   *
   * @return int - An error code or WiseFyCodes.DEFAULT_PRECHECK_RETURN for successfully passing all prechecks
   *
   * @see #checkForParamAndPrerequisites(String)
   */
  @WiseFyCode
  int searchForSSIDsChecks(@Nullable final String regexForSSID) {
    return checkForParamAndPrerequisites(regexForSSID);
  }

  /*
   * HELPERS
   */

  /**
   * Internal helper to check for prerequisites.
   *
   * @return int - An error code or WiseFyCodes.DEFAULT_PRECHECK_RETURN for successfully having all prerequisites
   *
   * @see WiseFyCodes
   * @see WiseFyPrerequisites#missingPrerequisites()
   */
  @WiseFyCode
  private int checkForPrerequisites() {
    int precheckResult = WiseFyCodes.DEFAULT_PRECHECK_RETURN;
    if (wisefyPrerequisites.missingPrerequisites()) {
      precheckResult = WiseFyCodes.MISSING_PREREQUISITE;
    }
    return precheckResult;
  }

  /**
   * Internal helper to check for prerequisites and the presence of a parameter.
   *
   * @param param The param to check
   *
   * @return int - An error code or WiseFyCodes.DEFAULT_PRECHECK_RETURN for successfully passing all prerequisites and param
   *
   * @see StringUtil#isEmpty(String)
   * @see WiseFyCodes
   * @see WiseFyPrerequisites#missingPrerequisites()
   */
  @WiseFyCode
  private int checkForParamAndPrerequisites(@Nullable final String param) {
    int precheckResult = WiseFyCodes.DEFAULT_PRECHECK_RETURN;
    if (StringUtil.isEmpty(param)) {
      precheckResult = WiseFyCodes.MISSING_PARAMETER;
    }
    if (wisefyPrerequisites.missingPrerequisites()) {
      precheckResult = WiseFyCodes.MISSING_PREREQUISITE;
    }
    return precheckResult;
  }
}
