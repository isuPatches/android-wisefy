/**
 * Copyright 2016 by Patches Klinefelter
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


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import com.isupatches.wisefy.util.GetManagerUtil;
import com.isupatches.wisefy.util.LogUtil;
import com.isupatches.wisefy.util.SSIDUtil;
import java.util.ArrayList;
import java.util.List;


/**
 *  Main class to manipulate and query network settings on an Android device
 *
 *  Uses the builder pattern for creation - {@link withContext}
 */
public class WiseFy {

    private static final String TAG = WiseFy.class.getSimpleName();

    public static final int WISE_MANAGER_FAILURE = -1000;

    public static final int WIFI_MANAGER_FAILURE = -1;

    private LogUtil mLogUtil = LogUtil.getInstance();

    private SSIDUtil mSSIDUtil = SSIDUtil.getInstance();

    public ConnectivityManager mConnectivityManager;

    public WifiManager mWifiManager;

    private boolean mLoggingEnabled;

    /**
     * Private constructor that accepts builder input
     */
    private WiseFy(withContext withContext) {
        this.mLoggingEnabled = withContext.loggingEnabled;
        this.mConnectivityManager = GetManagerUtil.getInstance().getConnectivityManager(withContext.context);
        this.mWifiManager = GetManagerUtil.getInstance().getWiFiManager(withContext.context);
    }

    /**
     * Static class for builder pattern
     *
     * Implements builder interfaces #{@link Logging} #{@link GetSmarts}
     */
    public static class withContext implements Logging, GetSmarts {

        private Context context;

        private boolean loggingEnabled;

        /**
         * Mandatory - The public constructor for the builder that requires a context
         *
         * @param context - The activity or application context to get a WifiConfiguration and
         * ConnectivityManager instance
         *
         * {@link #WiseFy(withContext)}
         */
        public withContext(Context context) {
            this.context = context;
        }

        /**
         * Mandatory - To build and return a WiseFy instance
         *
         * @return WiseFy - The instance created by the builder
         */
        @Override
        public WiseFy getSmarts() {
            return new WiseFy(this);
        }

        /**
         * Optional - Builder method that enables/disables logging for a WiseWy instance
         *
         * @param loggingEnabled - If logging is enabled or disabled for an instance
         * {@link Logging}
         *
         * @return withContext - The builder with updated logging setting
         */
        @Override
        public withContext logging(boolean loggingEnabled) {
            this.loggingEnabled = loggingEnabled;
            return this;
        }
    }

    /**
     * An interface that enables/disables logging for a WiseFy instance
     */
    interface Logging {
        withContext logging(boolean loggingEnabled);
    }

    /**
     * An interface that builds a WiseFy instance
     */
    interface GetSmarts {
        WiseFy getSmarts();
    }

    /**
     * To add an open network to the user's configured network list
     *
     * @param ssid - The ssid of the open network you want to add
     *
     * @return int - The return code from WifiManager for network creation (-1 for failure)
     */
    public int addOpenNetwork(String ssid) {
        if (TextUtils.isEmpty(ssid)) {
            mLogUtil.e(TAG, "Breaking due to empty SSID", mLoggingEnabled);
            return WISE_MANAGER_FAILURE;
        }
        if (mWifiManager != null) {
            boolean ssidAlreadyConfigured = checkIfNetworkInConfigurationList(ssid);

            if (!ssidAlreadyConfigured) {
                mLogUtil.d(TAG, "Adding open network with SSID " + ssid, mLoggingEnabled);

                WifiConfiguration wifiConfig = new WifiConfiguration();

                wifiConfig.SSID = mSSIDUtil.convertSSIDForConfig(ssid);
                wifiConfig.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
                wifiConfig.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
                wifiConfig.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
                wifiConfig.allowedAuthAlgorithms.clear();
                wifiConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
                wifiConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
                wifiConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
                wifiConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
                wifiConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
                wifiConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);

                return addNetwork(wifiConfig);
            }
        } else {
            mLogUtil.e(TAG, "No mWifiManager to connect to add open network", mLoggingEnabled);
        }
        return WISE_MANAGER_FAILURE;
    }

    /**
     * To add a WEP network to the user's configured network list
     *
     * @param ssid - The ssid of the WEP network you want to add
     * @param password - The password for the WEP network being added
     *
     * @return int - The return code from WifiManager for network creation (-1 for failure)
     */
    public int addWEPNetwork(String ssid, String password) {
        if (TextUtils.isEmpty(ssid) || TextUtils.isEmpty(password)) {
            mLogUtil.w(TAG, "Breaking due to missing ssid or password. ssid: " + ssid + ", password: " + password, mLoggingEnabled);
            return WISE_MANAGER_FAILURE;
        }
        if (mWifiManager != null) {
            boolean ssidAlreadyConfigured = checkIfNetworkInConfigurationList(ssid);

            if (!ssidAlreadyConfigured) {
                mLogUtil.d(TAG, "Adding WEP network with SSID " + ssid, mLoggingEnabled);

                WifiConfiguration wifiConfig = new WifiConfiguration();
                wifiConfig.SSID = mSSIDUtil.convertSSIDForConfig(ssid);
                wifiConfig.wepKeys[0] = "\"" + password + "\"";
                wifiConfig.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
                wifiConfig.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
                wifiConfig.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
                wifiConfig.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
                wifiConfig.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.SHARED);
                wifiConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
                wifiConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
                wifiConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
                wifiConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);

                return addNetwork(wifiConfig);
            }
        } else {
            mLogUtil.e(TAG, "No mWifiManager to connect to add WEP network", mLoggingEnabled);
        }
        return WISE_MANAGER_FAILURE;
    }

    /**
     * To add a WPA2 network to the user's configured network list
     *
     * @param ssid - The ssid of the WPA2 network you want to add
     * @param password - The password for the WPA2 network being added
     *
     * @return int - The return code from WifiManager for network creation (-1 for failure)
     */
    public int addWPA2Network(String ssid, String password) {
        if (TextUtils.isEmpty(ssid) || TextUtils.isEmpty(password)) {
            mLogUtil.w(TAG, "Breaking due to missing ssid or password. ssid: " + ssid + ", password: " + password, mLoggingEnabled);
            return WISE_MANAGER_FAILURE;
        }
        if (mWifiManager != null) {
            boolean ssidAlreadyConfigured = checkIfNetworkInConfigurationList(ssid);

            if (!ssidAlreadyConfigured) {
                mLogUtil.d(TAG, "Adding WPA2 network with SSID " + ssid, mLoggingEnabled);

                WifiConfiguration wifiConfig = new WifiConfiguration();
                wifiConfig.SSID = mSSIDUtil.convertSSIDForConfig(ssid);
                wifiConfig.preSharedKey = "\"" + password + "\"";
                wifiConfig.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
                wifiConfig.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
                wifiConfig.status = WifiConfiguration.Status.ENABLED;
                wifiConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
                wifiConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
                wifiConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
                wifiConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
                wifiConfig.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
                wifiConfig.allowedProtocols.set(WifiConfiguration.Protocol.WPA);

                wifiConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
                wifiConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);

                return addNetwork(wifiConfig);
            }
        } else {
            mLogUtil.e(TAG, "No mWifiManager to connect to add WPA2 network", mLoggingEnabled);
        }
        return WISE_MANAGER_FAILURE;
    }

    /**
     * To convert an RSSI level for a network to a number of bars
     *
     * @param rssiLevel - The signal strength of the network
     * @param targetNumberOfBars - How many bars or levels there will be total
     *
     * @return int - The number of bars for the given RSSI value
     */
    public int calculateBars(int rssiLevel, int targetNumberOfBars) {
        return WifiManager.calculateSignalLevel(rssiLevel, targetNumberOfBars);
    }

    /**
     * To compare the signal strength of two networks
     *
     * @param rssi1 - The signal strength of network 1
     * @param rssi2 - The signal strength of network 2
     *
     * @return int - Returns negative value if the first signal is weaker than the second signal, 0 if the two
     * signals have the same strength, and a positive value if the first signal is stronger than the second signal.
     */
    public int compareSignalLevel(int rssi1, int rssi2) {
        return WifiManager.compareSignalLevel(rssi1, rssi2);
    }

    /**
     * Used to connect to a network
     *
     * Gets a list of saved networks, connects/reconnects to the given ssid, and then calls waitToConnectToSSID to verify connectivity
     *
     * @param ssidToConnectTo - The ssid to connect/reconnect to
     * @param timeoutInMillis - The number of milliseconds to continue waiting for the device to connect to the given SSID
     *
     * @return boolean - If the network was successfully reconnected
     */
    public boolean connectToNetwork(String ssidToConnectTo, int timeoutInMillis) {
        mLogUtil.d(TAG, "Connecting to network: " + ssidToConnectTo, mLoggingEnabled);
        if (mWifiManager != null) {
            List<WifiConfiguration> list = mWifiManager.getConfiguredNetworks();
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    WifiConfiguration wifiConfiguration = list.get(i);
                    if (wifiConfiguration != null && wifiConfiguration.SSID != null) {
                        String ssidInList = wifiConfiguration.SSID.replaceAll("\"", "");

                        mLogUtil.d(TAG, "Configured WiFi Network {index:" + i + ", ssidInList:" + ssidInList + "}", mLoggingEnabled);
                        if (ssidInList.equals(ssidToConnectTo)) {
                            mLogUtil.d(TAG, "ssidToReconnectTo: " + ssidToConnectTo + " matches ssidInList:" + ssidInList, mLoggingEnabled);
                            mWifiManager.disconnect();
                            mWifiManager.enableNetwork(wifiConfiguration.networkId, true);
                            mWifiManager.reconnect();
                            return waitToConnectToSSID(ssidToConnectTo, timeoutInMillis);
                        }
                    }
                }
            }
            mLogUtil.w(TAG, "ssidToReconnectTo: " + ssidToConnectTo + " was not found in list to connect to", mLoggingEnabled);
        } else {
            mLogUtil.e(TAG, "No mWifiManager to connect to network.  SSID: " + ssidToConnectTo, mLoggingEnabled);
        }
        return false;
    }

    /**
     * To disable Wifi on a user's device
     *
     * @return boolean - If the command succeeded in disabling wifi
     */
    public boolean disableWifi() {
        mLogUtil.d(TAG, "Disabling WiFi", mLoggingEnabled);
        if (mWifiManager != null) {
            return mWifiManager.setWifiEnabled(false);
        } else {
            mLogUtil.e(TAG, "No mWifiManager to disable Wifi", mLoggingEnabled);
        }
        return false;
    }

    /**
     * To disconnect the user from their current network
     *
     * @return boolean - If the command succeeded in disconnecting the device from the current network
     */
    public boolean disconnectFromCurrentNetwork() {
        mLogUtil.d(TAG, "Disconnecting from current network", mLoggingEnabled);
        if (mWifiManager != null) {
            return mWifiManager.disconnect();
        } else {
            mLogUtil.e(TAG, "No mWifiManager to disconnect from current network", mLoggingEnabled);
        }
        return false;
    }

    /**
     * To enable Wifi on a user's device
     *
     * @return boolean - If the command succeeded in enabling wifi
     */
    public boolean enableWifi() {
        mLogUtil.d(TAG, "Enabling WiFi", mLoggingEnabled);
        if (mWifiManager != null) {
            return mWifiManager.setWifiEnabled(true);
        } else {
            mLogUtil.e(TAG, "No mWifiManager to enable wifi", mLoggingEnabled);
        }
        return false;
    }

    /**
     * To retrieve the user's current network
     *
     * @return WifiInfo|null - The user's current network information
     */
    public WifiInfo getCurrentNetwork() {
        if (mWifiManager != null) {
            return mWifiManager.getConnectionInfo();
        } else {
            mLogUtil.e(TAG, "No mWifiManager to get current network", mLoggingEnabled);
        }
        return null;
    }

    /**
     * To retrieve a list of nearby access points
     *
     * *NOTE* Setting filterDuplicates to true will not return SSIDs with a weaker signal strength (will always take the highest)
     *
     * @param filterDuplicates - If you want to exclude SSIDs with that same name that have a weaker signal strength
     *
     * @return List of ScanResults|null - List of nearby access points
     */
    public List<ScanResult> getNearbyAccessPoints(boolean filterDuplicates) {
        if (mWifiManager != null) {
            mWifiManager.startScan();
            if (!filterDuplicates) {
                return mWifiManager.getScanResults();
            } else {
                List<ScanResult> scanResults = mWifiManager.getScanResults();
                List<ScanResult> scanResultsToReturn = new ArrayList<>();

                for (ScanResult newScanResult : scanResults) {
                    boolean found = false;
                    for (int i = 0; i < scanResultsToReturn.size(); i++) {
                        ScanResult scanResult = scanResultsToReturn.get(i);
                        mLogUtil.d(TAG, "SSID 1: " + newScanResult.SSID + ". SSID 2: " + scanResult.SSID, mLoggingEnabled);
                        if (newScanResult.SSID.equalsIgnoreCase(scanResult.SSID)) {
                            found = true;
                            mLogUtil.d(TAG, "SSID did match", mLoggingEnabled);

                            mLogUtil.d(TAG, "Current level: " + scanResult.level, mLoggingEnabled);
                            mLogUtil.d(TAG, "New level: " + newScanResult.level, mLoggingEnabled);
                            mLogUtil.d(TAG, "comparison result: " + WifiManager.compareSignalLevel(newScanResult.level, scanResult.level), mLoggingEnabled);

                            if (WifiManager.compareSignalLevel(newScanResult.level, scanResult.level) > 0) {
                                mLogUtil.d(TAG, "New result has a higher signal strength, swapping", mLoggingEnabled);
                                scanResultsToReturn.set(i, newScanResult);
                            }
                        } else {
                            mLogUtil.d(TAG, "SSID did not match", mLoggingEnabled);
                        }
                    }

                    if (!found) {
                        mLogUtil.d(TAG, "Found new wifi network", mLoggingEnabled);
                        scanResultsToReturn.add(newScanResult);
                    }
                }
                return scanResultsToReturn;
            }
        } else {
            mLogUtil.e(TAG, "No mWifiManager to get nearby access points", mLoggingEnabled);
        }
        return null;
    }

    /**
     * To retrieve a list of saved networks on a user's device
     *
     * @return List of WifiConfiguration|null - List of saved networks on a users device
     */
    public List<WifiConfiguration> getSavedNetworks() {
        if (mWifiManager != null) {
            return mWifiManager.getConfiguredNetworks();
        } else {
            mLogUtil.e(TAG, "No mWifiManager to get saved networks", mLoggingEnabled);
        }
        return null;
    }

    /**
     * To check if the device is connected to a mobile network
     *
     * @return bool - If the device is currently connected to a mobile network
     */
    public boolean isDeviceConnectedToMobileNetwork() {
        if (mConnectivityManager != null) {
            NetworkInfo networkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.getTypeName().equalsIgnoreCase("MOBILE")) {
                if (networkInfo.isConnected() && networkInfo.isAvailable()) {
                    return true;
                }
            }
        } else {
            mLogUtil.e(TAG, "No mConnectivityManager check if device is connected to mobile network", mLoggingEnabled);
        }
        return false;
    }

    /**
     * To check if the device is connected to a mobile or wifi network
     *
     * @return bool - If the device is currently connected to a mobile or wifi network
     */
    public boolean isDeviceConnectedToMobileOrWifiNetwork() {
        if (mConnectivityManager != null) {
            NetworkInfo networkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (networkInfo.isConnected() && networkInfo.isAvailable()) {
                return true;
            }
        } else {
            mLogUtil.e(TAG, "No mConnectivityManager check if device is connected to mobile or wifi network", mLoggingEnabled);
        }
        return false;
    }

    /**
     * To check if the device is connected to a given SSID
     *
     * Used by connectToNetwork
     *
     * @param ssid - The SSID to check if the device is attached to
     *
     * @return bool - If the device is currently attached to the given SSID
     */
    public boolean isDeviceConnectedToSSID(String ssid) {
        if (mWifiManager != null) {
            WifiInfo connectionInfo = mWifiManager.getConnectionInfo();
            if (connectionInfo != null && connectionInfo.getSSID() != null) {
                String currentSSID = connectionInfo.getSSID().replaceAll("\"", "");
                mLogUtil.d(TAG, "Current SSID: " + currentSSID, mLoggingEnabled);

                if (currentSSID.equals(ssid)) {
                    mLogUtil.d(TAG, "Correct SSID", mLoggingEnabled);
                    if (mConnectivityManager != null
                            && mConnectivityManager.getActiveNetworkInfo() != null
                            && mConnectivityManager.getActiveNetworkInfo().isAvailable()
                            && mConnectivityManager.getActiveNetworkInfo().isConnected()) {
                        mLogUtil.d(TAG, "Network is connected", mLoggingEnabled);
                        return true;
                    }
                }
            }
        } else {
            mLogUtil.e(TAG, "No mWifiManager check if device is connected to SSID: " + ssid, mLoggingEnabled);
        }
        return false;
    }

    /**
     * To check if the device is connected to a wifi network
     *
     * @return bool - If the device is currently connected to a wifi network
     */
    public boolean isDeviceConnectedToWifiNetwork() {
        if (mConnectivityManager != null) {
            NetworkInfo networkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.getTypeName().equalsIgnoreCase("WIFI")) {
                if (networkInfo.isConnected() && networkInfo.isAvailable()) {
                    return true;
                }
            }
        } else {
            mLogUtil.e(TAG, "No mConnectivityManager check if device is connected to wifi network", mLoggingEnabled);
        }
        return false;
    }

    /**
     * To check if an SSID is in the list of configured networks
     *
     * @param ssid - The SSID to check and see if it's in the list of configured networks
     *
     * @return boolean - If the SSID is in the list of configured networks
     */
    public boolean isNetworkInConfigurationList(String ssid) {
        if(mWifiManager != null) {
            return checkIfNetworkInConfigurationList(ssid);
        } else {
            mLogUtil.e(TAG, "No mWifiManager to check if network is in the configuration list", mLoggingEnabled);
        }
        return false;
    }

    /**
     * To check and return if a network is secure (WEP/PSK/EAP capabilities)
     *
     * @param scanResult - The network to see if it is secure
     *
     * @return boolean - Whether the network is secure
     */
    public boolean isNetworkSecure(ScanResult scanResult) {
        boolean isSecure = false;
        if (scanResult != null && scanResult.capabilities != null) {
            if (scanResult.capabilities.contains("WEP") || scanResult.capabilities.contains("PSK") || scanResult.capabilities.contains("EAP")) {
                isSecure = true;
            }
        }
        return isSecure;
    }

    /**
     * To check if Wifi is enabled on the device or not
     *
     * @return boolean - if Wifi is enabled on device
     */
    public boolean isWifiEnabled() {
        if (mWifiManager != null) {
            return mWifiManager.isWifiEnabled();
        } else {
            mLogUtil.e(TAG, "No mWifiManager to check if wifi is enabled", mLoggingEnabled);
        }
        return false;
    }

    /**
     * To remove a configured network
     *
     * @param ssidToRemove - The ssid of the network you want to remove from the configured network list
     *
     * @return boolean - If the command succeeded in removing the network
     */
    public boolean removeNetwork(String ssidToRemove) {
        if (mWifiManager != null) {
            List<WifiConfiguration> list = mWifiManager.getConfiguredNetworks();
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    WifiConfiguration wifiConfiguration = list.get(i);
                    if (wifiConfiguration != null && wifiConfiguration.SSID != null) {
                        String ssidInList = wifiConfiguration.SSID.replaceAll("\"", "");

                        mLogUtil.d(TAG, "Configured WiFi Network {index:" + i + ", ssidInList:" + ssidInList + "}", mLoggingEnabled);
                        if (ssidInList.equals(ssidToRemove)) {
                            mLogUtil.d(TAG, "Removing network: " + ssidToRemove, mLoggingEnabled);
                            mWifiManager.disconnect();
                            boolean result = mWifiManager.removeNetwork(wifiConfiguration.networkId);
                            if (result) {
                                mLogUtil.d(TAG, "Successfully removed network", mLoggingEnabled);
                                mWifiManager.saveConfiguration();
                            } else {
                                mLogUtil.d(TAG, "Failed to remove network", mLoggingEnabled);
                            }
                            mWifiManager.reconnect();
                            return result;
                        }
                    }
                }
            }
            mLogUtil.w(TAG, "SSID to remove: " + ssidToRemove + " was not found in list to remove network", mLoggingEnabled);
        } else {
            mLogUtil.e(TAG, "No mWifiManager to remove network. SSID: " + ssidToRemove, mLoggingEnabled);
        }
        return false;
    }

    /**
     * To search local networks and return the first one that contains a given ssid (non-case sensitive)
     *
     * @param ssidToSearchFor - The ssid to search for
     * @param timeoutInMillis - The number of milliseconds to keep searching for the SSID
     *
     * @return String|null - The first SSID that contains the search ssid (if any, else null)
     */
    public String searchForSSID(String ssidToSearchFor, int timeoutInMillis) {
        if (mWifiManager != null) {
            int scanPass = 1;
            long currentTime;
            long endTime = System.currentTimeMillis() + timeoutInMillis;
            LogUtil.getInstance().d(TAG, "End time (searchForSSID): " + endTime, mLoggingEnabled);
            do {
                mLogUtil.d(TAG, "Scanning SSIDs, pass " + scanPass, mLoggingEnabled);
                mWifiManager.startScan();
                List<ScanResult> networks = mWifiManager.getScanResults();
                for (ScanResult scanResult : networks) {
                    mLogUtil.d(TAG, "scanResult.SSID: " + scanResult.SSID, mLoggingEnabled);
                    if (scanResult.SSID != null && (scanResult.SSID.toUpperCase().contains(ssidToSearchFor.toUpperCase()))) {
                        mLogUtil.d(TAG, "Found match, SSID: " + scanResult.SSID, mLoggingEnabled);
                        return scanResult.SSID;
                    }
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ie) {
                    // Do nothing
                }
                currentTime = System.currentTimeMillis();
                LogUtil.getInstance().d(TAG, "Current time (searchForSSID): " + endTime, mLoggingEnabled);
                scanPass++;
            } while(currentTime < endTime);
        } else {
            mLogUtil.e(TAG, "No mWifiManager to search for network. SSID: " + ssidToSearchFor, mLoggingEnabled);
        }
        return null;
    }

    /**
     * To query if logging is enabled or disabled for a WiseFy instance
     *
     * @return boolean - If logging is enabled for the WiseFy instance
     */
    public boolean isLoggingEnabled() {
        return mLoggingEnabled;
    }

    /**
     * HELPERS
     */

    /**
     * Used internally by addOpenNetwork, addWEPNetwork, and addWPA2Network to add and save a new wifi configuration
     *
     * @param wifiConfig - The network to configuration to add
     *
     * {@link #addOpenNetwork(String)}
     * {@link #addWEPNetwork(String, String)}
     * {@link #addWPA2Network(String, String)}
     *
     * @return int - The return code from WifiManager for network creation (-1 for failure)
     */
    private int addNetwork(WifiConfiguration wifiConfig) {
        int result = mWifiManager.addNetwork(wifiConfig);
        if (result != WIFI_MANAGER_FAILURE) {
            mLogUtil.d(TAG, "Successfully added network", mLoggingEnabled);
            mWifiManager.saveConfiguration();
        } else {
            mLogUtil.e(TAG, "Failed to add network", mLoggingEnabled);
        }
        return result;
    }

    /**
     * Used internally by addOpenNetwork, addWEPNetwork, addWPA2Network, and
     * isNetworkInConfigurationList to see if an ssid is already saved (CASE SENSITIVE!)
     *
     * @param ssid - The ssid to check for in the configured network list
     *
     * {@link #addOpenNetwork(String)}
     * {@link #addWEPNetwork(String, String)}
     * {@link #addWPA2Network(String, String)}
     * {@link #isNetworkInConfigurationList(String)}
     *
     * @return boolean - If the ssid was found in the configuration list
     */
    private boolean checkIfNetworkInConfigurationList(String ssid) {
        List<WifiConfiguration> list = mWifiManager.getConfiguredNetworks();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                WifiConfiguration wifiConfiguration = list.get(i);
                if (wifiConfiguration != null && wifiConfiguration.SSID != null) {
                    String ssidInList = wifiConfiguration.SSID.replaceAll("\"", "");
                    mLogUtil.d(TAG, "SSID in list: " + ssidInList + ", SSID: " + ssid, mLoggingEnabled);
                    if (ssidInList.equals(ssid)) {
                        mLogUtil.d(TAG, "Found SSID in list", mLoggingEnabled);
                        return true;
                    }
                }
            }
        } else {
            mLogUtil.w(TAG, "Found 0 configured networks", mLoggingEnabled);
        }
        return false;
    }

    /**
     * Used internally by connectToNetwork to check if the device connects to
     * a given SSID within a specified time (timeout is in millis)
     *
     * @param ssid - The ssid to wait for the device to connect to
     * @param timeoutInMillis - The number of milliseconds to wait
     *
     * @return boolean - If the device is connected to the ssid in the given time
     */
    private boolean waitToConnectToSSID(String ssid, int timeoutInMillis) {
        long currentTime;
        long endTime = System.currentTimeMillis() + timeoutInMillis;
        LogUtil.getInstance().d(TAG, "End time (waitToConnectToSSID): " + endTime, mLoggingEnabled);
        do {
            boolean result = isDeviceConnectedToSSID(ssid);
            if (result) {
                return result;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                // Do nothing
            }
            currentTime = System.currentTimeMillis();
            LogUtil.getInstance().d(TAG, "Current time (waitToConnectToSSID): " + endTime, mLoggingEnabled);
        } while(currentTime < endTime);
        return false;
    }
}