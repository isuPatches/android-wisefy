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

import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.isupatches.wisefy.annotations.Internal;
import com.isupatches.wisefy.annotations.WaitsForTimeout;
import com.isupatches.wisefy.constants.CommonValues;
import com.isupatches.wisefy.constants.Symbols;
import com.isupatches.wisefy.utils.SleepUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * A class used internally for the purposes of shared query logic. This handles saved networks and
 * nearby access points. There is also filtering by regex functionality and some RSSI logic that
 * are tied into these queries.
 *
 * @author Patches
 */
@Internal
class WiseFySearch {

  private static final String TAG = WiseFySearch.class.getSimpleName();

  private final WiseFyPrerequisites wisefyPrerequisites;

  /**
   * Private constructor.
   *
   * @param wisefyPrerequisites The prerequisites instance needed
   *
   * @see WiseFyPrerequisites
   */
  private WiseFySearch(final WiseFyPrerequisites wisefyPrerequisites) {
    this.wisefyPrerequisites = wisefyPrerequisites;
  }

  /**
   * Factory pattern creation method to get an instance of WiseFySearch.
   *
   * @param wisefyPrerequisites The prerequisites instance needed
   *
   * @return WiseFySearch - An instance of WiseFySearch
   *
   * @see WiseFyPrerequisites
   */
  static WiseFySearch create(final WiseFyPrerequisites wisefyPrerequisites) {
    return new WiseFySearch(wisefyPrerequisites);
  }

  /**
   * Used internally to wait for a given time and return the first ScanResult whose SSID matches a given regex.
   *
   * <p>
   *    Returns either:
   *      - The first network whose SSID matches a given regex
   *      - A network matching the given regex and has the highest RSSI
   * </p>
   *
   * @param regexForSSID The regex to check the SSID of the network against
   * @param timeoutInMillis The amount of time to wait for a match
   * @param takeHighest If the method should iterate through and return only the access point with the highest RSSI
   *
   * @return ScanResult|null - Matching network or null if none found
   *
   * @see #accessPointMatchesRegex(ScanResult, String)
   * @see #hasHighestSignalStrength(List, ScanResult)
   * @see ScanResult
   * @see SleepUtil#sleep(long)
   * @see WiseFyPrerequisites#getWifiManager()
   */
  @Nullable
  @WaitsForTimeout
  ScanResult findAccessPointByRegex(@NonNull final String regexForSSID, final Integer timeoutInMillis, final boolean takeHighest) {
    int scanPass = 1;
    long currentTime;
    final long endTime = System.currentTimeMillis() + timeoutInMillis;
    ScanResult accessPointToReturn = null;
    do {
      currentTime = System.currentTimeMillis();

      WiseFyLogger.log().debug(TAG, "Scanning SSIDs, pass %debug", scanPass);
      wisefyPrerequisites.getWifiManager().startScan();
      final List<ScanResult> accessPoints = wisefyPrerequisites.getWifiManager().getScanResults();
      if (accessPoints == null || accessPoints.isEmpty()) {
        continue;
      }

      for (ScanResult accessPoint : accessPoints) {
        if (accessPointMatchesRegex(accessPoint, regexForSSID)) {
          if (takeHighest) {
            if (hasHighestSignalStrength(accessPoints, accessPoint)) {
              accessPointToReturn = accessPoint;
              break;
            }
          } else {
            accessPointToReturn = accessPoint;
            break;
          }
        }
      }

      SleepUtil.getInstance().sleep(CommonValues.DELAY);

      WiseFyLogger.log().debug(TAG, "Current time: %debug, end time: %debug (findAccessPointByRegex)", currentTime, endTime);
      scanPass++;
    } while (currentTime < endTime);
    return accessPointToReturn;
  }

  /**
   * Used internally to return a list of networks whose SSID match the given regex.
   *
   * @param regexForSSID The regex to check the SSID of the network against
   * @param takeHighest If the method should iterate through and return only the access point with the highest RSSI
   *
   * @return List of ScanResults|null - The list of networks that have an SSID that matches the given regex
   *
   * @see #accessPointMatchesRegex(ScanResult, String)
   * @see #hasHighestSignalStrength(List, ScanResult)
   * @see ScanResult
   * @see WiseFyPrerequisites#getWifiManager()
   */
  @Nullable
  List<ScanResult> findAccessPointsMatchingRegex(@NonNull final String regexForSSID, final boolean takeHighest) {
    wisefyPrerequisites.getWifiManager().startScan();
    final List<ScanResult> matchingAccessPoints = new ArrayList<>();
    final List<ScanResult> accessPoints = wisefyPrerequisites.getWifiManager().getScanResults();

    if (accessPoints == null || accessPoints.isEmpty()) {
      return null;
    }

    for (ScanResult accessPoint : accessPoints) {
      if (accessPointMatchesRegex(accessPoint, regexForSSID)) {
        if (takeHighest) {
          if (hasHighestSignalStrength(accessPoints, accessPoint)) {
            matchingAccessPoints.add(accessPoint);
          }
        } else {
          matchingAccessPoints.add(accessPoint);
        }
      }
    }

    return !matchingAccessPoints.isEmpty() ? matchingAccessPoints : null;
  }

  /**
   * Used internally to return the first configuration of s saved networks matching a given regex.
   *
   * @param regexForSSID The regex for the SSID to find in the configured network list
   *
   * @return WiFiConfiguration|null - The first saved configuration matching the given regex or null if none found
   *
   * @see #savedNetworkMatchesRegex(WifiConfiguration, String)
   * @see WifiConfiguration
   * @see WiseFyPrerequisites#getWifiManager()
   */
  @Nullable
  WifiConfiguration findSavedNetworkByRegex(@NonNull final String regexForSSID) {
    final List<WifiConfiguration> savedNetworks = wisefyPrerequisites.getWifiManager().getConfiguredNetworks();

    if (savedNetworks == null || savedNetworks.isEmpty()) {
      return null;
    }

    WifiConfiguration savedNetworkToReturn = null;
    for (WifiConfiguration savedNetwork : savedNetworks) {
      if (savedNetworkMatchesRegex(savedNetwork, regexForSSID)) {
        savedNetworkToReturn = savedNetwork;
        break;
      }
    }
    return savedNetworkToReturn;
  }

  /**
   * Used internally to return a list of saved networks matching a given regex.
   *
   * @param regexForSSID The regex for the SSIDs to find in the configured network list
   *
   * @return List of WifiConfigurations|null - Saved network configurations matching the given regex or null if none found
   *
   * @see #savedNetworkMatchesRegex(WifiConfiguration, String)
   * @see WifiConfiguration
   * @see WiseFyPrerequisites#getWifiManager()
   */
  @Nullable
  List<WifiConfiguration> findSavedNetworksMatchingRegex(@NonNull final String regexForSSID) {
    final List<WifiConfiguration> savedNetworks = wisefyPrerequisites.getWifiManager().getConfiguredNetworks();
    final List<WifiConfiguration> matchingSavedNetworks = new ArrayList<>();

    if (savedNetworks == null || savedNetworks.isEmpty()) {
      return null;
    }

    for (WifiConfiguration savedNetwork : savedNetworks) {
      if (savedNetworkMatchesRegex(savedNetwork, regexForSSID)) {
        matchingSavedNetworks.add(savedNetwork);
      }
    }

    return !matchingSavedNetworks.isEmpty() ? matchingSavedNetworks : null;
  }

  /**
   * Used internally to return a list of SSIDs from saved networks matching a given regex.
   *
   * @param regexForSSID The regex for the SSIDs to find in the configured network list
   *
   * @return List of Strings|null - SSIDs of saved network configurations matching the given regex or null if none found
   *
   * @see #accessPointMatchesRegex(ScanResult, String)
   * @see WiseFyPrerequisites#getWifiManager()
   */
  @Nullable
  List<String> findSSIDsMatchingRegex(@NonNull final String regexForSSID) {
    wisefyPrerequisites.getWifiManager().startScan();
    final List<String> matchingSSIDs = new ArrayList<>();
    final List<ScanResult> accessPoints = wisefyPrerequisites.getWifiManager().getScanResults();
    if (accessPoints != null && !accessPoints.isEmpty()) {
      for (ScanResult accessPoint : accessPoints) {
        if (accessPointMatchesRegex(accessPoint, regexForSSID) && !matchingSSIDs.contains(accessPoint.SSID)) {
          matchingSSIDs.add(accessPoint.SSID);
        }
      }
    }

    return !matchingSSIDs.isEmpty() ? matchingSSIDs : null;
  }

  /**
   * Used internally to determine if a network exists as a saved network configuration.
   *
   * @param ssid The ssid to check for in the configured network list
   *
   * @return boolean - True if the ssid was found in the configuration list
   *
   * @see #findSavedNetworkByRegex(String)
   */
  boolean isNetworkASavedConfiguration(@NonNull final String ssid) {
    return findSavedNetworkByRegex(ssid) != null;
  }

  /**
   * Used internally to build a list of ScanResults (removes duplicates by taking access point with higher RSSI).
   *
   * <p>*NOTE* Case insensitive</p>
   *
   * @param accessPoints The list of access points to remove entries with lower signal strength from
   *
   * @return List of ScanResults - The filtered list of networks
   *
   * @see ScanResult
   */
  @NonNull
  List<ScanResult> removeEntriesWithLowerSignalStrength(@NonNull final List<ScanResult> accessPoints) {
    final List<ScanResult> accessPointsToReturn = new ArrayList<>();

    for (ScanResult accessPoint : accessPoints) {
      boolean found = false;
      for (int i = 0; i < accessPointsToReturn.size(); i++) {
        final ScanResult scanResult = accessPointsToReturn.get(i);
        WiseFyLogger.log().debug(TAG, "SSID 1: %s, SSID 2: %s", accessPoint.SSID, scanResult.SSID);
        if (accessPoint.SSID.equalsIgnoreCase(scanResult.SSID)) {
          found = true;
          WiseFyLogger.log().debug(TAG, "RSSI level of access point 1: %debug", scanResult.level);
          WiseFyLogger.log().debug(TAG, "RSSI level of access point 2: %debug", accessPoint.level);
          WiseFyLogger.log().debug(TAG, "comparison result: %debug (removeEntriesWithLowerSignalStrength)",
                                    WifiManager.compareSignalLevel(accessPoint.level,
                                    scanResult.level));
          if (WifiManager.compareSignalLevel(accessPoint.level, scanResult.level) > 0) {
            WiseFyLogger.log().debug(TAG, "New result has a higher or same signal strength, swapping");
            accessPointsToReturn.set(i, accessPoint);
          }
        }
      }

      if (!found) {
        WiseFyLogger.log().debug(TAG, "Found new wifi network");
        accessPointsToReturn.add(accessPoint);
      }
    }
    return accessPointsToReturn;
  }

  /*
   * Helpers
   */

  /**
   * Used internally to check if a an access point has an SSID that matches a given regex.
   *
   * @param accessPoint The access point for comparison
   * @param regexForSSID The regex for comparison
   *
   * @return bool - true if the access point's SSID matches the given regex
   */
  private boolean accessPointMatchesRegex(@Nullable final ScanResult accessPoint, @NonNull final String regexForSSID) {
    if (accessPoint != null) {
      WiseFyLogger.log().debug(TAG, String.format("accessPoint. SSID: %s, regex for SSID: %s", accessPoint.SSID, regexForSSID));
      return accessPoint.SSID != null && (accessPoint.SSID).matches(regexForSSID);
    } else {
      return false;
    }
  }

  /**
   * Used internally to determine if the current access point has the highest signal strength
   * compared to others that have the same SSID.
   *
   * <p>*NOTE* Case insensitive</p>
   *
   * @param accessPoints A list of access points to compare the current access point to
   * @param currentAccessPoint The access point to see if it has the highest signal strength
   *
   * @return boolean - True if the current access point has the highest signal strength
   */
  private boolean hasHighestSignalStrength(@NonNull final List<ScanResult> accessPoints, @NonNull final ScanResult currentAccessPoint) {
    for (ScanResult accessPoint : accessPoints) {
      if (accessPoint.SSID.equalsIgnoreCase(currentAccessPoint.SSID)) {
        WiseFyLogger.log().debug(TAG, "RSSI level of current access point: %debug", currentAccessPoint.level);
        WiseFyLogger.log().debug(TAG, "RSSI level of access point in list: %debug", accessPoint.level);
        WiseFyLogger.log().debug(TAG, "comparison result: %debug (hasHighestSignalStrength)",
                                  WifiManager.compareSignalLevel(accessPoint.level,
                                  currentAccessPoint.level));
        if (WifiManager.compareSignalLevel(accessPoint.level, currentAccessPoint.level) > 0) {
          WiseFyLogger.log().debug(TAG, "Stronger signal strength found");
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Used internally to check if a saved network has an SSID that matches a given regex.
   *
   * @param savedNetwork The saved network for comparison
   * @param regexForSSID The regex for comparison
   *
   * @return bool - true if the saved network's SSID matches the given regex
   */
  private boolean savedNetworkMatchesRegex(@Nullable final WifiConfiguration savedNetwork, @NonNull final String regexForSSID) {
    if (savedNetwork != null && savedNetwork.SSID != null) {
      final String ssidInList = savedNetwork.SSID.replaceAll(Symbols.QUOTE, "");
      return ssidInList.matches(regexForSSID);
    }
    return false;
  }
}
