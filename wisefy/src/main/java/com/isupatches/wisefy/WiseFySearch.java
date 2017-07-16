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
import android.util.Log;
import com.isupatches.wisefy.util.LogUtil;
import com.isupatches.wisefy.util.SleepUtil;
import java.util.ArrayList;
import java.util.List;


class WiseFySearch {

    private static final String TAG = WiseFySearch.class.getSimpleName();

    private static final WiseFySearch WISEFY_SEARCH = new WiseFySearch();

    WiseFyPrerequisites mWiseFyPrerequisites;

    private WiseFyConfiguration mWiseFyConfiguration;

    /**
     * Private constructor with no setup
     */
    private WiseFySearch() {
        mWiseFyPrerequisites = WiseFyPrerequisites.getInstance();
        mWiseFyConfiguration = WiseFyConfiguration.getInstance();
    }

    /**
     * @return instance of WiseFySearch
     */
    static WiseFySearch getInstance() {
        return WISEFY_SEARCH;
    }

    /**
     * Used internally to see if an ssid is already saved
     *
     * *NOTE* CASE SENSITIVE!
     *
     * @param ssid The ssid to check for in the configured network list
     * @return boolean - True if the ssid was found in the configuration list
     * @see WiseFySearch#findSavedNetworkByRegex(String)
     */
    boolean checkIfNetworkInConfigurationList(String ssid) {
        WifiConfiguration wifiConfiguration = findSavedNetworkByRegex(ssid);
        return wifiConfiguration != null;
    }

    /**
     * Used internally to wait for a given time and return the first ScanResult whose SSID matches a given regex
     *
     * @param regexForSSID    The regex to check the SSID of the network against
     * @param timeoutInMillis The amount of time to wait for a match
     *
     * @return ScanResult|null - The first network whose SSID matches a given regex
     */
    ScanResult findAccessPointByRegex(String regexForSSID, Integer timeoutInMillis, boolean filterDuplicates) {
        int scanPass = 1;
        long currentTime;
        long endTime = System.currentTimeMillis() + timeoutInMillis;
        do {
            if (LogUtil.isLoggable(TAG, Log.DEBUG, mWiseFyConfiguration.isLoggingEnabled())) {
                Log.d(TAG, String.format("Scanning SSIDs, pass %d", scanPass));
            }
            mWiseFyPrerequisites.getWifiManager().startScan();
            List<ScanResult> accessPoints = mWiseFyPrerequisites.getWifiManager().getScanResults();
            if (accessPoints != null && accessPoints.size() > 0) {
                for (ScanResult accessPoint : accessPoints) {
                    if (LogUtil.isLoggable(TAG, Log.DEBUG, mWiseFyConfiguration.isLoggingEnabled())) {
                        Log.d(TAG, String.format("scanResult.SSID: %s, regex for SSID: %s", accessPoint.SSID, regexForSSID));
                    }
                    if (accessPoint.SSID != null && (accessPoint.SSID).matches(regexForSSID)) {
                        if (filterDuplicates) {
                            if (hasHighestSignalStrength(accessPoints, accessPoint)) {
                                return accessPoint;
                            }
                        } else {
                            return accessPoint;
                        }
                    }
                }
            }

            SleepUtil.sleep(1000);

            currentTime = System.currentTimeMillis();
            if (LogUtil.isLoggable(TAG, Log.DEBUG, mWiseFyConfiguration.isLoggingEnabled())) {
                Log.d(TAG, String.format("Current time: %d / End time: %d (findAccessPointByRegex)", currentTime, endTime));
            }
            scanPass++;
        } while (currentTime < endTime);
        return null;
    }

    /**
     * Used internally to return a list of networks whose SSID match the given regex
     *
     * @param regexForSSID The regex to check the SSID of the network against
     *
     * @return List<ScanResult>|null - The list of networks that have an SSID that matches the given regex
     */
    List<ScanResult> findAccessPointsMatchingRegex(String regexForSSID, boolean filterDuplicates) {
        mWiseFyPrerequisites.getWifiManager().startScan();
        List<ScanResult> matchingAccessPoints = new ArrayList<>();
        List<ScanResult> accessPoints = mWiseFyPrerequisites.getWifiManager().getScanResults();
        if (accessPoints != null && accessPoints.size() > 0) {
            for (ScanResult accessPoint : accessPoints) {
                if (LogUtil.isLoggable(TAG, Log.DEBUG, mWiseFyConfiguration.isLoggingEnabled())) {
                    Log.d(TAG, String.format("accessPoint.SSID: %s, regex for SSID: %s", accessPoint.SSID, regexForSSID));
                }
                if (accessPoint.SSID != null && accessPoint.SSID.matches(regexForSSID)) {
                    if (filterDuplicates) {
                        if (hasHighestSignalStrength(accessPoints, accessPoint)) {
                            matchingAccessPoints.add(accessPoint);
                        }
                    } else {
                        matchingAccessPoints.add(accessPoint);
                    }
                }
            }
        }
        if (matchingAccessPoints.size() > 0) {
            return matchingAccessPoints;
        } else {
            return null;
        }
    }

    /**
     * Used internally to return the first configuration of s saved networks matching a given regex
     *
     * @param regexForSSID The regex for the SSID to find in the configured network list
     *
     * @return WiFiConfiguration|null - The first saved configuration matching the given regex
     * or null if none meet the criteria
     */
    WifiConfiguration findSavedNetworkByRegex(String regexForSSID) {
        List<WifiConfiguration> savedNetworks = mWiseFyPrerequisites.getWifiManager().getConfiguredNetworks();
        if (savedNetworks != null && savedNetworks.size() > 0) {
            for (WifiConfiguration savedNetwork : savedNetworks) {
                if (savedNetwork != null && savedNetwork.SSID != null) {
                    String ssidInList = savedNetwork.SSID.replaceAll("\"", "");
                    if (LogUtil.isLoggable(TAG, Log.DEBUG, mWiseFyConfiguration.isLoggingEnabled())) {
                        Log.d(TAG, String.format("SSID in list: %s, SSID: %s", ssidInList, regexForSSID));
                    }
                    if (ssidInList.matches(regexForSSID)) {
                        return savedNetwork;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Used internally to return a list of saved networks matching a given regex
     *
     * @param regexForSSID The regex for the SSIDs to find in the configured network list
     *
     * @return List<WifiConfiguration>|null - The list of saved network configurations matching
     * the given regex or null if none meet the criteria
     */
    List<WifiConfiguration> findSavedNetworksMatchingRegex(String regexForSSID) {
        List<WifiConfiguration> savedNetworks = mWiseFyPrerequisites.getWifiManager().getConfiguredNetworks();
        List<WifiConfiguration> matchingSavedNetworks = new ArrayList<>();
        if (savedNetworks != null && savedNetworks.size() > 0) {
            for (WifiConfiguration savedNetwork : savedNetworks) {
                if (savedNetwork != null && savedNetwork.SSID != null) {
                    String ssidInList = savedNetwork.SSID.replaceAll("\"", "");
                    if (LogUtil.isLoggable(TAG, Log.DEBUG, mWiseFyConfiguration.isLoggingEnabled())) {
                        Log.d(TAG, String.format("SSID in list: %s, SSID: %s", ssidInList, regexForSSID));
                    }
                    if (ssidInList.matches(regexForSSID)) {
                        matchingSavedNetworks.add(savedNetwork);
                    }
                }
            }
        }
        if (matchingSavedNetworks.size() > 0) {
            return matchingSavedNetworks;
        } else {
            return null;
        }
    }

    /**
     * Used internally to return a list of SSIDs from saved networks matching a given regex
     *
     * @param regexForSSID The regex for the SSIDs to find in the configured network list
     *
     * @return List<String>|null - The list of SSIDs of saved network configurations matching
     * the given regex or null if none meet the criteria
     */
    List<String> findSSIDsMatchingRegex(String regexForSSID) {
        mWiseFyPrerequisites.getWifiManager().startScan();
        List<String> matchingSSIDs = new ArrayList<>();
        List<ScanResult> accessPoints = mWiseFyPrerequisites.getWifiManager().getScanResults();
        if (accessPoints != null && accessPoints.size() > 0) {
            for (ScanResult accessPoint : accessPoints) {
                if (accessPoint != null && accessPoint.SSID != null) {
                    if (LogUtil.isLoggable(TAG, Log.DEBUG, mWiseFyConfiguration.isLoggingEnabled())) {
                        Log.d(TAG, String.format("accessPoint.SSID: %s, regex for SSID: %s", accessPoint.SSID, regexForSSID));
                    }
                    if (accessPoint.SSID.matches(regexForSSID)) {
                        matchingSSIDs.add(accessPoint.SSID);
                    }
                }
            }
        }
        if (matchingSSIDs.size() > 0) {
            return matchingSSIDs;
        } else {
            return null;
        }
    }

    /**
     * Used internally to build a list of ScanResults (Removes duplicates by taking access point with higher RSSI)
     *
     * *NOTE* Case insensitive
     *
     * @return List<ScanResult> - The filtered list of networks
     */
    List<ScanResult> removeEntriesWithLowerSignalStrength(List<ScanResult> accessPoints) {
        List<ScanResult> accessPointsToReturn = new ArrayList<>();

        for (ScanResult accessPoint : accessPoints) {
            boolean found = false;
            for (int i = 0; i < accessPointsToReturn.size(); i++) {
                ScanResult scanResult = accessPointsToReturn.get(i);
                if (LogUtil.isLoggable(TAG, Log.DEBUG, mWiseFyConfiguration.isLoggingEnabled())) {
                    Log.d(TAG, String.format("SSID 1: %s, SSID 2: %s", accessPoint.SSID, scanResult.SSID));
                }
                if (accessPoint.SSID.equalsIgnoreCase(scanResult.SSID)) {
                    found = true;
                    if (LogUtil.isLoggable(TAG, Log.DEBUG, mWiseFyConfiguration.isLoggingEnabled())) {
                        Log.d(TAG, String.format("RSSI level of access point 1: %d", scanResult.level));
                        Log.d(TAG, String.format("RSSI level of access point 2: %d", accessPoint.level));
                        Log.d(TAG, String.format("comparison result: %d", WifiManager.compareSignalLevel(accessPoint.level, scanResult.level)));
                    }
                    if (WifiManager.compareSignalLevel(accessPoint.level, scanResult.level) > 0) {
                        if (LogUtil.isLoggable(TAG, Log.DEBUG, mWiseFyConfiguration.isLoggingEnabled())) {
                            Log.d(TAG, "New result has a higher signal strength, swapping");
                        }
                        accessPointsToReturn.set(i, accessPoint);
                    }
                }
            }

            if (!found) {
                if (LogUtil.isLoggable(TAG, Log.DEBUG, mWiseFyConfiguration.isLoggingEnabled())) {
                    Log.d(TAG, "Found new wifi network");
                }
                accessPointsToReturn.add(accessPoint);
            }
        }
        return accessPointsToReturn;
    }

    /*
     * Helpers
     */

    /**
     * @param accessPoints
     * @param currentAccessPoint
     * @return
     */
    private boolean hasHighestSignalStrength(List<ScanResult> accessPoints, ScanResult currentAccessPoint) {
        for (ScanResult accessPoint : accessPoints) {
            if (accessPoint.SSID.equalsIgnoreCase(currentAccessPoint.SSID)) {
                if (LogUtil.isLoggable(TAG, Log.DEBUG, mWiseFyConfiguration.isLoggingEnabled())) {
                    Log.d(TAG, String.format("RSSI level of current access point: %d", currentAccessPoint.level));
                    Log.d(TAG, String.format("RSSI level of access point in list: %d", accessPoint.level));
                    Log.d(TAG, String.format("comparison result: %d", WifiManager.compareSignalLevel(accessPoint.level, currentAccessPoint.level)));
                }
                if (WifiManager.compareSignalLevel(accessPoint.level, currentAccessPoint.level) < 0) {
                    if (LogUtil.isLoggable(TAG, Log.DEBUG, mWiseFyConfiguration.isLoggingEnabled())) {
                        Log.d(TAG, "Stronger signal strength found");
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
