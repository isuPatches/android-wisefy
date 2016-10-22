package com.metova.wisefy;


import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;
import com.metova.wisefy.util.GetManagerUtil;
import com.metova.wisefy.util.LogUtil;
import com.metova.wisefy.util.SSIDUtil;
import java.util.ArrayList;
import java.util.List;


public class WiseFy {

    private static final String TAG = WiseFy.class.getSimpleName();

    @VisibleForTesting
    public GetManagerUtil mGetManagerUtil = GetManagerUtil.getInstance();

    public static final int WISE_MANAGER_FAILURE = -1000;

    public static final int WIFI_MANAGER_FAILURE = -1;

    private LogUtil mLogUtil = LogUtil.getInstance();

    private SSIDUtil mSSIDUtil = SSIDUtil.getInstance();

    private boolean loggingEnabled;

    /**
     * Private constructor that accepts builder input
     */
    private WiseFy(getSmarts getSmarts) {
        this.loggingEnabled = getSmarts.loggingEnabled;
    }

    /**
     * Internal static class for builder pattern
     */
    public static class getSmarts {
        private boolean loggingEnabled;

        public getSmarts() {
        }

        /**
         * To enable/disable logging for the WiseFy instance
         *
         * @param loggingEnabled - If logging is enabled or disabled
         *
         * @return getSmarts - The builder with updated logging setting
         */
        public getSmarts withLogging(boolean loggingEnabled) {
            this.loggingEnabled = loggingEnabled;
            return this;
        }

        /**
         * To build and return a WiseFy instance
         *
         * @return WiseFy - The instance created by the builder
         */
        public WiseFy initiateHamsters() {
            return new WiseFy(this);
        }
    }

    /**
     * To add an open network to the user's configured network list
     *
     * @param activity - The activity to use as context to retrieve a wifi manager via getSystemService
     * @param ssid - The ssid of the open network you want to add
     *
     * @return int - The return code from WifiManager for network creation (-1 for failure)
     */
    public int addOpenNetwork(Activity activity, String ssid) {
        if (TextUtils.isEmpty(ssid) || activity == null || activity.isFinishing()) {
            mLogUtil.w(TAG, "Breaking due to missing ssid or activity. ssid: " + ssid, loggingEnabled);
            return WISE_MANAGER_FAILURE;
        }
        WifiManager wifiManager = mGetManagerUtil.getWiFiManager(activity);
        if (wifiManager != null) {
            boolean ssidAlreadyConfigured = isNetworkInConfigurationList(wifiManager, ssid);

            if (!ssidAlreadyConfigured) {
                mLogUtil.d(TAG, "Adding open network with SSID " + ssid, loggingEnabled);

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

                return addNetwork(wifiManager, wifiConfig);
            }
        }
        return WISE_MANAGER_FAILURE;
    }

    /**
     * To add a WEP network to the user's configured network list
     *
     * @param activity - The activity to use as context to retrieve a wifi manager via getSystemService
     * @param ssid - The ssid of the WEP network you want to add
     * @param password - The password for the WEP network being added
     *
     * @return int - The return code from WifiManager for network creation (-1 for failure)
     */
    public int addWEPNetwork(Activity activity, String ssid, String password) {
        if (TextUtils.isEmpty(ssid) || TextUtils.isEmpty(password) || activity == null || activity.isFinishing()) {
            mLogUtil.w(TAG, "Breaking due to missing ssid, password, or activity. ssid: " + ssid + ", password: " + password, loggingEnabled);
            return WISE_MANAGER_FAILURE;
        }
        WifiManager wifiManager = mGetManagerUtil.getWiFiManager(activity);
        if (wifiManager != null) {
            boolean ssidAlreadyConfigured = isNetworkInConfigurationList(wifiManager, ssid);

            if (!ssidAlreadyConfigured) {
                mLogUtil.d(TAG, "Adding WEP network with SSID " + ssid, loggingEnabled);

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

                return addNetwork(wifiManager, wifiConfig);
            }
        }
        return WISE_MANAGER_FAILURE;
    }

    /**
     * To add a WPA2 network to the user's configured network list
     *
     * @param activity - The activity to use as context to retrieve a wifi manager via getSystemService
     * @param ssid - The ssid of the WPA2 network you want to add
     * @param password - The password for the WPA2 network being added
     *
     * @return int - The return code from WifiManager for network creation (-1 for failure)
     */
    public int addWPA2Network(Activity activity, String ssid, String password) {
        if (TextUtils.isEmpty(ssid) || TextUtils.isEmpty(password) || activity == null || activity.isFinishing()) {
            mLogUtil.w(TAG, "Breaking due to missing ssid, password, or activity. ssid: " + ssid + ", password: " + password, loggingEnabled);
            return WISE_MANAGER_FAILURE;
        }
        WifiManager wifiManager = mGetManagerUtil.getWiFiManager(activity);
        if (wifiManager != null) {
            boolean ssidAlreadyConfigured = isNetworkInConfigurationList(wifiManager, ssid);

            if (!ssidAlreadyConfigured) {
                mLogUtil.d(TAG, "Adding WPA2 network with SSID " + ssid, loggingEnabled);

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

                return addNetwork(wifiManager, wifiConfig);
            }
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
     * @return int - Returns <0 if the first signal is weaker than the second signal, 0 if the two
     * signals have the same strength, and >0 if the first signal is stronger than the second signal.
     */
    public int compareSignalLevel(int rssi1, int rssi2) {
        return WifiManager.compareSignalLevel(rssi1, rssi2);
    }

    /**
     * Used to connect to a network
     *
     * Gets a list of saved networks, connects/reconnects to the given ssid, and then calls waitToConnectToSSID to verify connectivity
     *
     * @param activity - The activity to use as context to retrieve a wifi manager via getSystemService
     * @param ssidToConnectTo - The ssid to connect/reconnect to
     * @param timeoutInMillis - The number of milliseconds to continue waiting for the device to connect to the given SSID
     *
     * @return boolean - If the network was successfully reconnected
     */
    public boolean connectToNetwork(Activity activity, String ssidToConnectTo, int timeoutInMillis) {
        if (activity != null && !activity.isFinishing()) {
            mLogUtil.d(TAG, "Connecting to network: " + ssidToConnectTo, loggingEnabled);
            WifiManager wifiManager = mGetManagerUtil.getWiFiManager(activity);
            if (wifiManager != null) {
                List<WifiConfiguration> list = wifiManager.getConfiguredNetworks();
                if (list != null) {
                    for (int i = 0; i < list.size(); i++) {
                        WifiConfiguration wifiConfiguration = list.get(i);
                        if (wifiConfiguration != null && wifiConfiguration.SSID != null) {
                            String ssidInList = wifiConfiguration.SSID.replaceAll("\"", "");

                            mLogUtil.d(TAG, "Configured WiFi Network {index:" + i + ", ssidInList:" + ssidInList + "}", loggingEnabled);
                            if (ssidInList.equals(ssidToConnectTo)) {
                                mLogUtil.d(TAG, "ssidToReconnectTo: " + ssidToConnectTo + " matches ssidInList:" + ssidInList, loggingEnabled);
                                wifiManager.disconnect();
                                wifiManager.enableNetwork(wifiConfiguration.networkId, true);
                                wifiManager.reconnect();
                                return waitToConnectToSSID(activity, ssidToConnectTo, timeoutInMillis);
                            }
                        }
                    }
                }

            }
            mLogUtil.w(TAG, "ssidToReconnectTo: " + ssidToConnectTo + " was not found in list to reconnect to", loggingEnabled);
        } else {
            mLogUtil.w(TAG, "No activity to reconnect to network", loggingEnabled);
        }
        return false;
    }

    /**
     * To disable Wifi on a user's device
     *
     * @param activity - The activity to use as context to retrieve a wifi manager via getSystemService
     *
     * @return boolean - If the command succeeded in disabling wifi
     */
    public boolean disableWifi(Activity activity) {
        if (activity != null && !activity.isFinishing()) {
            WifiManager wifiManager = mGetManagerUtil.getWiFiManager(activity);
            if (wifiManager != null) {
                return wifiManager.setWifiEnabled(false);
            }
        } else {
            mLogUtil.w(TAG, "No activity to disable Wifi", loggingEnabled);
        }
        return false;
    }

    /**
     * To disconnect the user from their current network
     *
     * @param activity - The activity to use as context to retrieve a wifi manager via getSystemService
     *
     * @return boolean - If the command succeeded in disconnecting the device from the current network
     */
    public boolean disconnectFromCurrentNetwork(Activity activity) {
        if (activity != null && !activity.isFinishing()) {
            mLogUtil.d(TAG, "Disconnecting from current network", loggingEnabled);
            WifiManager wifiManager = mGetManagerUtil.getWiFiManager(activity);
            if (wifiManager != null) {
                return wifiManager.disconnect();
            }
        } else {
            mLogUtil.w(TAG, "No activity to disconnect from current network", loggingEnabled);
        }
        return false;
    }

    /**
     * To enable Wifi on a user's device
     *
     * @param activity - The activity to use as context to retrieve a wifi manager via getSystemService
     *
     * @return boolean - If the command succeeded in enabling wifi
     */
    public boolean enableWifi(Activity activity) {
        if (activity != null && !activity.isFinishing()) {
            mLogUtil.d(TAG, "Enabling WiFi", loggingEnabled);
            WifiManager wifiManager = mGetManagerUtil.getWiFiManager(activity);
            if (wifiManager != null) {
                return wifiManager.setWifiEnabled(true);
            }
        } else {
            mLogUtil.w(TAG, "No activity to enable Wifi", loggingEnabled);
        }
        return false;
    }

    /**
     * To retrieve the user's current network
     *
     * @param activity - The activity to use as context to retrieve a wifi manager via getSystemService
     *
     * @return WifiInfo|null - The user's current network information
     */
    public WifiInfo getCurrentNetwork(Activity activity) {
        if (activity != null && !activity.isFinishing()) {
            WifiManager wifiManager = mGetManagerUtil.getWiFiManager(activity);
            if (wifiManager != null) {
                return wifiManager.getConnectionInfo();
            }
        } else {
            mLogUtil.w(TAG, "No activity to get current network", loggingEnabled);
        }
        return null;
    }

    /**
     * To retrieve a list of nearby access points
     *
     * *NOTE* Setting filterDuplicates to true will not return SSIDs with a weaker signal strength (will always take the highest)
     *
     * @param activity - The activity to use as context to retrieve a wifi manager via getSystemService
     * @param filterDuplicates - If you want to exclude SSIDs with that same name that have a weaker signal strength
     *
     * @return List<ScanResult>|null - List of nearby access points
     */
    public List<ScanResult> getNearbyAccessPoints(Activity activity, boolean filterDuplicates) {
        if (activity != null && !activity.isFinishing()) {
            WifiManager wifiManager = mGetManagerUtil.getWiFiManager(activity);
            if (wifiManager != null) {
                wifiManager.startScan();
                if (!filterDuplicates) {
                    return wifiManager.getScanResults();
                } else {
                    List<ScanResult> scanResults = wifiManager.getScanResults();
                    List<ScanResult> scanResultsToReturn = new ArrayList<>();

                    for (ScanResult newScanResult : scanResults) {
                        boolean found = false;
                        for (int i = 0; i < scanResultsToReturn.size(); i++) {
                            ScanResult scanResult = scanResultsToReturn.get(i);
                            mLogUtil.d(TAG, "SSID 1: " + newScanResult.SSID + ". SSID 2: " + scanResult.SSID, loggingEnabled);
                            if (newScanResult.SSID.equalsIgnoreCase(scanResult.SSID)) {
                                found = true;
                                mLogUtil.d(TAG, "SSID did match", loggingEnabled);

                                mLogUtil.d(TAG, "Current level: " + scanResult.level, loggingEnabled);
                                mLogUtil.d(TAG, "New level: " + newScanResult.level, loggingEnabled);
                                mLogUtil.d(TAG, "comparison result: " + WifiManager.compareSignalLevel(newScanResult.level, scanResult.level), loggingEnabled);

                                if (WifiManager.compareSignalLevel(newScanResult.level, scanResult.level) > 0) {
                                    mLogUtil.d(TAG, "New result has a higher signal strength, swapping", loggingEnabled);
                                    scanResultsToReturn.set(i, newScanResult);
                                }
                            } else {
                                mLogUtil.d(TAG, "SSID did not match", loggingEnabled);
                            }
                        }

                        if (!found) {
                            mLogUtil.d(TAG, "Found new wifi network", loggingEnabled);
                            scanResultsToReturn.add(newScanResult);
                        }
                    }
                    return scanResultsToReturn;
                }
            }
        } else {
            mLogUtil.w(TAG, "No activity to get nearby access points", loggingEnabled);

        }
        return null;
    }

    /**
     * To retrieve a list of saved networks on a user's device
     *
     * @param activity - The activity to use as context to retrieve a wifi manager via getSystemService
     *
     * @return List<WifiConfiguration>|null - List of saved networks on a users device
     */
    public List<WifiConfiguration> getSavedNetworks(Activity activity) {
        if (activity != null && !activity.isFinishing()) {
            WifiManager wifiManager = mGetManagerUtil.getWiFiManager(activity);
            if (wifiManager != null) {
                return wifiManager.getConfiguredNetworks();
            }
        } else {
            mLogUtil.w(TAG, "No activity to get saved networks", loggingEnabled);
        }
        return null;
    }

    /**
     * To check if the device is connected to a mobile network
     *
     * @param activity - The activity to use as context to retrieve a wifi manager and a connectivity manager via getSystemService
     *
     * @return bool - If the device is currently connected to a mobile network
     */
    public boolean isDeviceConnectedToMobileNetwork(Activity activity) {
        if (activity != null && !activity.isFinishing()) {
            ConnectivityManager connectivityManager = mGetManagerUtil.getConnectivityManager(activity);
            if (connectivityManager != null) {
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.getTypeName().equalsIgnoreCase("MOBILE")) {
                    if (networkInfo.isConnected() && networkInfo.isAvailable()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * To check if the device is connected to a mobile or wifi network
     *
     * @param activity - The activity to use as context to retrieve a wifi manager and a connectivity manager via getSystemService
     *
     * @return bool - If the device is currently connected to a mobile or wifi network
     */
    public boolean isDeviceConnectedToMobileOrWifiNetwork(Activity activity) {
        if (activity != null && !activity.isFinishing()) {
            ConnectivityManager connectivityManager = mGetManagerUtil.getConnectivityManager(activity);
            if (connectivityManager != null) {
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                if (networkInfo.isConnected() && networkInfo.isAvailable()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * To check if the device is connected to a given SSID
     *
     * Used by connectToNetwork
     *
     * @param activity - The activity to use as context to retrieve a wifi manager and a connectivity manager via getSystemService
     * @param ssid - The SSID to check if the device is attached to
     *
     * @return bool - If the device is currently attached to the given SSID
     */
    public boolean isDeviceConnectedToSSID(Activity activity, String ssid) {
        if (activity == null || activity.isFinishing()) {
            mLogUtil.w(TAG, "Breaking due to no activity", loggingEnabled);
            return false;
        }
        WifiManager wifiManager = mGetManagerUtil.getWiFiManager(activity);
        if (wifiManager != null) {
            WifiInfo connectionInfo = wifiManager.getConnectionInfo();
            if (connectionInfo != null && connectionInfo.getSSID() != null) {
                String currentSSID = connectionInfo.getSSID().replaceAll("\"", "");
                mLogUtil.d(TAG, "Current SSID: " + currentSSID, loggingEnabled);

                if (currentSSID.equals(ssid)) {
                    mLogUtil.d(TAG, "Correct SSID", loggingEnabled);
                    ConnectivityManager connectivityManager = mGetManagerUtil.getConnectivityManager(activity);
                    if (connectivityManager != null
                            && connectivityManager.getActiveNetworkInfo() != null
                            && connectivityManager.getActiveNetworkInfo().isAvailable()
                            && connectivityManager.getActiveNetworkInfo().isConnected()) {
                        mLogUtil.d(TAG, "Network is connected", loggingEnabled);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * To check if the device is connected to a wifi network
     *
     * @param activity - The activity to use as context to retrieve a wifi manager and a connectivity manager via getSystemService
     *
     * @return bool - If the device is currently connected to a wifi network
     */
    public boolean isDeviceConnectedToWifiNetwork(Activity activity) {
        if (activity != null && !activity.isFinishing()) {
            ConnectivityManager connectivityManager = mGetManagerUtil.getConnectivityManager(activity);
            if (connectivityManager != null) {
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.getTypeName().equalsIgnoreCase("WIFI")) {
                    if (networkInfo.isConnected() && networkInfo.isAvailable()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * To check if an SSID is in the list of configured networks
     *
     * @param activity - The activity to use as context to retrieve a wifi manager via getSystemService
     *
     * @return boolean - If the SSID is in the list of configured networks
     */
    public boolean isNetworkInConfigurationList(Activity activity, String ssid) {
        if (activity != null && !activity.isFinishing()) {
            WifiManager wifiManager = mGetManagerUtil.getWiFiManager(activity);
            if(wifiManager != null) {
                return isNetworkInConfigurationList(wifiManager, ssid);
            }
        } else {
            mLogUtil.w(TAG, "No activity to check if network is in configuration list", loggingEnabled);
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
     * @param activity - The activity to use as context to retrieve a wifi manager via getSystemService
     *
     * @return boolean - if Wifi is enabled on device
     */
    public boolean isWifiEnabled(Activity activity) {
        if (activity != null && !activity.isFinishing()) {
            WifiManager wifiManager = mGetManagerUtil.getWiFiManager(activity);
            if (wifiManager != null) {
                return wifiManager.isWifiEnabled();
            }
        } else {
            mLogUtil.w(TAG, "No activity to check if wifi is enabled", loggingEnabled);
        }
        return false;
    }

    /**
     * To remove a configured network
     *
     * @param activity - The activity to use as context to retrieve a wifi manager via getSystemService
     * @param ssidToRemove - The ssid of the network you want to remove from the configured network list
     *
     * @return boolean - If the command succeeded in removing the network
     */
    public boolean removeNetwork(Activity activity, String ssidToRemove) {
        if (activity != null && !activity.isFinishing()) {
            WifiManager wifiManager = mGetManagerUtil.getWiFiManager(activity);
            if (wifiManager != null) {
                List<WifiConfiguration> list = wifiManager.getConfiguredNetworks();
                if (list != null) {
                    for (int i = 0; i < list.size(); i++) {
                        WifiConfiguration wifiConfiguration = list.get(i);
                        if (wifiConfiguration != null && wifiConfiguration.SSID != null) {
                            String ssidInList = wifiConfiguration.SSID.replaceAll("\"", "");

                            mLogUtil.d(TAG, "Configured WiFi Network {index:" + i + ", ssidInList:" + ssidInList + "}", loggingEnabled);
                            if (ssidInList.equals(ssidToRemove)) {
                                mLogUtil.d(TAG, "Removing network: " + ssidToRemove, loggingEnabled);
                                wifiManager.disconnect();
                                boolean result = wifiManager.removeNetwork(wifiConfiguration.networkId);
                                if (result) {
                                    mLogUtil.d(TAG, "Successfully removed network", loggingEnabled);
                                    wifiManager.saveConfiguration();
                                } else {
                                    mLogUtil.d(TAG, "Failed to remove network", loggingEnabled);
                                }
                                wifiManager.reconnect();
                                return result;
                            }
                        }
                    }
                }

            }
            mLogUtil.w(TAG, "SSID to remove: " + ssidToRemove + " was not found in list to remove network", loggingEnabled);
        } else {
            mLogUtil.w(TAG, "No activity to remove network", loggingEnabled);
        }
        return false;
    }

    /**
     * To search local networks and return the first one that contains a given ssid (non-case sensitive)
     *
     * @param activity - The activity to use as context to retrieve a wifi manager via getSystemService
     * @param ssidToSearchFor - The ssid to search for
     * @param timeoutInMillis - The number of milliseconds to keep searching for the SSID
     *
     * @return String|null - The first SSID that contains the search ssid (if any, else null)
     */
    public String searchForSSID(Activity activity, String ssidToSearchFor, int timeoutInMillis) {
        if (activity != null && !activity.isFinishing()) {
            WifiManager wifiManager = mGetManagerUtil.getWiFiManager(activity);
            if (wifiManager != null) {
                int scanPass = 1;
                long currentTime;
                long endTime = System.currentTimeMillis() + timeoutInMillis;
                LogUtil.getInstance().d(TAG, "End time (searchForSSID): " + endTime, loggingEnabled);
                do {
                    mLogUtil.d(TAG, "Scanning SSIDs, pass " + scanPass, loggingEnabled);
                    wifiManager.startScan();
                    List<ScanResult> networks = wifiManager.getScanResults();
                    for (ScanResult scanResult : networks) {
                        mLogUtil.d(TAG, "scanResult.SSID: " + scanResult.SSID, loggingEnabled);
                        if (scanResult.SSID != null && (scanResult.SSID.toUpperCase().contains(ssidToSearchFor.toUpperCase()))) {
                            mLogUtil.d(TAG, "Found match, SSID: " + scanResult.SSID, loggingEnabled);
                            return scanResult.SSID;
                        }
                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ie) {
                        // Do nothing
                    }
                    currentTime = System.currentTimeMillis();
                    LogUtil.getInstance().d(TAG, "Current time (searchForSSID): " + endTime, loggingEnabled);
                    scanPass++;
                } while(currentTime < endTime);
            }
        } else {
            mLogUtil.w(TAG, "No activity to search for SSID", loggingEnabled);
        }
        return null;
    }

    /**
     * To query if logging is enabled or disabled for a WiseFy instance
     *
     * @return boolean - If logging is enabled for the WiseFy instance
     */
    @VisibleForTesting
    public boolean isLoggingEnabled() {
        return loggingEnabled;
    }

    /**
     * HELPERS
     */

    /**
     * Used internally by addOpenNetwork, addWEPNetwork, and addWPA2Network to add and save a new wifi configuration
     *
     * @param wifiManager - A wifi manager instance to add the network
     * @param wifiConfig - The network to configuration to add
     *
     * @return int - The return code from WifiManager for network creation (-1 for failure)
     */
    private int addNetwork(WifiManager wifiManager, WifiConfiguration wifiConfig) {
        int result = wifiManager.addNetwork(wifiConfig);
        if (result != WIFI_MANAGER_FAILURE) {
            mLogUtil.d(TAG, "Successfully added network", loggingEnabled);
            wifiManager.saveConfiguration();
        } else {
            mLogUtil.e(TAG, "Failed to add network", loggingEnabled);
        }
        return result;
    }

    /**
     * Used internally by addOpenNetwork, addWEPNetwork, addWPA2Network, and
     * isNetworkInConfigurationList to see if an ssid is already saved (CASE SENSITIVE!)
     *
     * @param wifiManager - A wifi manager instance to search for the ssid
     * @param ssid - The ssid to check for in the configured network list
     *
     * @return boolean - If the ssid was found in the configuration list
     */
    private boolean isNetworkInConfigurationList(WifiManager wifiManager, String ssid) {
        List<WifiConfiguration> list = wifiManager.getConfiguredNetworks();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                WifiConfiguration wifiConfiguration = list.get(i);
                if (wifiConfiguration != null && wifiConfiguration.SSID != null) {
                    String ssidInList = wifiConfiguration.SSID.replaceAll("\"", "");
                    mLogUtil.d(TAG, "SSID in list: " + ssidInList + ", SSID: " + ssid, loggingEnabled);
                    if (ssidInList.equals(ssid)) {
                        mLogUtil.d(TAG, "Found SSID in list", loggingEnabled);
                        return true;
                    }
                }
            }
        } else {
            mLogUtil.w(TAG, "Found 0 configured networks", loggingEnabled);
        }
        return false;
    }

    /**
     * Used internally by connectToNetwork to check if the device connects to
     * a given SSID within a specified time (timeout is in millis)
     *
     * @param activity - The activity to use as context to retrieve a wifi manager via getSystemService
     * @param ssid - The ssid to wait for the device to connect to
     * @param timeoutInMillis - The number of milliseconds to wait
     *
     * @return boolean - If the device is connected to the ssid in the given time
     */
    private boolean waitToConnectToSSID(Activity activity, String ssid, int timeoutInMillis) {
        long currentTime;
        long endTime = System.currentTimeMillis() + timeoutInMillis;
        LogUtil.getInstance().d(TAG, "End time (waitToConnectToSSID): " + endTime, loggingEnabled);
        do {
            boolean result = isDeviceConnectedToSSID(activity, ssid);
            if (result) {
                return result;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                // Do nothing
            }
            currentTime = System.currentTimeMillis();
            LogUtil.getInstance().d(TAG, "Current time (waitToConnectToSSID): " + endTime, loggingEnabled);
        } while(currentTime < endTime);
        return false;
    }
}