package com.metova.wisefy;


import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;
import com.metova.wisefy.util.GetManagerUtil;
import com.metova.wisefy.util.LogUtil;
import java.util.ArrayList;
import java.util.List;


public class WiseFy {

    public static final String TAG = WiseFy.class.getSimpleName();

    @VisibleForTesting
    public GetManagerUtil mGetManagerUtil = GetManagerUtil.getInstance();

    private static final WiseFy WIFI_MANAGER = new WiseFy();

    /**
     * Private constructor with no setup
     */
    private WiseFy() {
    }

    /**
     * @return instance of WiFiManager
     */
    public static WiseFy getSmarts() {
        return WIFI_MANAGER;
    }

    /**
     * To add a WEP network to the user's configured network list
     *
     * @param activity-  The activity to use as context to retrieve a wifi manager via getSystemService
     * @param ssid - The ssid of the WEP network you want to add
     * @param password - The password for the WEP network being added
     */
    public void addWEPNetwork(Activity activity, String ssid, String password) {
        if (TextUtils.isEmpty(ssid)) {
            LogUtil.d(TAG, "Breaking due to empty ssid or password. ssid: " + ssid);
            return;
        }
        if (activity != null) {
            WifiManager wifiManager = mGetManagerUtil.getWiFiManager(activity);
            if (wifiManager != null) {
                boolean ssidAlreadyConfigured = false;
                List<WifiConfiguration> list = wifiManager.getConfiguredNetworks();
                if (list != null) {
                    for (int i = 0; i < list.size(); i++) {
                        WifiConfiguration wifiConfiguration = list.get(i);
                        if (wifiConfiguration != null && wifiConfiguration.SSID != null) {
                            String ssidInList = wifiConfiguration.SSID.replaceAll("\"", "");

                            if (ssidInList.equals(ssid)) {
                                LogUtil.d(TAG, "Found SSID in list");
                                wifiManager.disconnect();
                                wifiManager.enableNetwork(wifiConfiguration.networkId, true);
                                wifiManager.reconnect();
                                ssidAlreadyConfigured = true;
                                break;
                            }
                        }
                    }
                }

                if (!ssidAlreadyConfigured) {
                    LogUtil.d(TAG, "Adding WEP network with SSID " + ssid);

                    WifiConfiguration wifiConfig = new WifiConfiguration();
                    // On devices with version Kitkat and below, We need to send SSID name
                    // with double quotes. On devices with version Lollipop, We need to send
                    // SSID name without double quotes
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        wifiConfig.SSID = ssid;
                    } else {
                        wifiConfig.SSID = "\"" + ssid + "\"";
                    }

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

                    int result = wifiManager.addNetwork(wifiConfig);
                    if(result != -1) {
                        wifiManager.saveConfiguration();
                    } else {
                        LogUtil.d(TAG,  "Failed to add network");
                    }
                }
            }
        }
    }

    /**
     * To add a WPA2 network to the user's configured network list
     *
     * @param activity-  The activity to use as context to retrieve a wifi manager via getSystemService
     * @param ssid - The ssid of the WPA2 network you want to add
     * @param password - The password for the WPA2 network being added
     */
    public void addWPA2Network(Activity activity, String ssid, String password) {
        if (TextUtils.isEmpty(ssid) || TextUtils.isEmpty(password)) {
            LogUtil.d(TAG, "Breaking due to empty ssid or password. ssid: " + ssid + ", password: " + password);
            return;
        }
        if (activity != null) {
            WifiManager wifiManager = mGetManagerUtil.getWiFiManager(activity);
            if (wifiManager != null) {
                boolean ssidAlreadyConfigured = false;
                List<WifiConfiguration> list = wifiManager.getConfiguredNetworks();
                if (list != null) {
                    for (int i = 0; i < list.size(); i++) {
                        WifiConfiguration wifiConfiguration = list.get(i);
                        if (wifiConfiguration != null && wifiConfiguration.SSID != null) {
                            String ssidInList = wifiConfiguration.SSID.replaceAll("\"", "");

                            if (ssidInList.equals(ssid)) {
                                LogUtil.d(TAG, "Found SSID in list");
                                wifiManager.disconnect();
                                wifiManager.enableNetwork(wifiConfiguration.networkId, true);
                                wifiManager.reconnect();
                                ssidAlreadyConfigured = true;
                                break;
                            }
                        }
                    }
                }

                if (!ssidAlreadyConfigured) {
                    LogUtil.d(TAG, "Adding WPA2 network with SSID " + ssid);

                    WifiConfiguration wifiConfig = new WifiConfiguration();
                    // On devices with version Kitkat and below, We need to send SSID name
                    // with double quotes. On devices with version Lollipop, We need to send
                    // SSID name without double quotes
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        wifiConfig.SSID = ssid;
                    } else {
                        wifiConfig.SSID = "\"" + ssid + "\"";
                    }

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

                    int result = wifiManager.addNetwork(wifiConfig);
                    if(result != -1) {
                        wifiManager.saveConfiguration();
                    } else {
                        LogUtil.d(TAG,  "Failed to add network");
                    }
                }
            }
        }
    }

    /**
     * To add an open network to the user's configured network list
     *
     * @param activity-  The activity to use as context to retrieve a wifi manager via getSystemService
     * @param ssid - The ssid of the open network you want to add
     */
    public void addOpenNetwork(Activity activity, String ssid) {
        if (TextUtils.isEmpty(ssid)) {
            LogUtil.d(TAG, "Breaking due to empty ssid or password. ssid: " + ssid);
            return;
        }
        if (activity != null) {
            boolean ssidAlreadyConfigured = false;
            WifiManager wifiManager = mGetManagerUtil.getWiFiManager(activity);
            if (wifiManager != null) {
                List<WifiConfiguration> list = wifiManager.getConfiguredNetworks();
                if (list != null) {
                    for (int i = 0; i < list.size(); i++) {
                        WifiConfiguration wifiConfiguration = list.get(i);
                        if (wifiConfiguration != null && wifiConfiguration.SSID != null) {
                            String ssidInList = wifiConfiguration.SSID.replaceAll("\"", "");

                            if (ssidInList.equals(ssid)) {
                                LogUtil.d(TAG, "Found SSID in list");
                                wifiManager.disconnect();
                                wifiManager.enableNetwork(wifiConfiguration.networkId, true);
                                wifiManager.reconnect();
                                ssidAlreadyConfigured = true;
                                break;
                            }
                        }
                    }
                }

                if(!ssidAlreadyConfigured) {
                    LogUtil.d(TAG, "Adding open network with SSID " + ssid);

                    WifiConfiguration wifiConfig = new WifiConfiguration();
                    // On devices with version Kitkat and below, We need to send SSID name
                    // with double quotes. On devices with version Lollipop, We need to send
                    // SSID name without double quotes
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        wifiConfig.SSID = ssid;
                    } else {
                        wifiConfig.SSID = "\"" + ssid + "\"";
                    }

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

                    int result = wifiManager.addNetwork(wifiConfig);
                    if(result != -1) {
                        wifiManager.saveConfiguration();
                    } else {
                        LogUtil.d(TAG,  "Failed to add network");
                    }
                }
            }
        }
    }

    /**
     *  To convert RRSI level for a network to number of bars
     *
     * @param rssiLevel - The signal strength of the network
     * @param targetNumberOfBars - How many bars or levels there will be total
     * @return int - Number of bars
     */
    public int calculateBars(int rssiLevel, int targetNumberOfBars) {
        return WifiManager.calculateSignalLevel(rssiLevel, targetNumberOfBars);
    }

    /**
     * Used to check if a given SSID is in a connected state
     *
     * Used by reconnectToNetwork
     *
     * @param activity - The activity to use as context to retrieve a wifi manager and a connectivity manager via getSystemService
     * @param ssid -
     * @param timeout -
     * @return int - Number of bars
     */
    private boolean checkWifi(Activity activity, String ssid, int timeout) {
        for (int x = 0; x < timeout; x++) {
            if (activity == null || activity.isFinishing()) {
                LogUtil.d(TAG, "Breaking due to no activity");
                break;
            }
            WifiManager wifiManager = mGetManagerUtil.getWiFiManager(activity);
            if (wifiManager != null) {
                WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                if (connectionInfo != null && connectionInfo.getSSID() != null) {
                    String currentSSID = connectionInfo.getSSID().replaceAll("\"", "");
                    LogUtil.d(TAG, "Current SSID: " + currentSSID);

                    if (currentSSID.equals(ssid)) {
                        LogUtil.d(TAG, "Correct SSID");
                        ConnectivityManager connectivityManager = mGetManagerUtil.getConnectivityManager(activity);
                        if (connectivityManager != null
                                && connectivityManager.getActiveNetworkInfo() != null
                                && connectivityManager.getActiveNetworkInfo().isAvailable()
                                && connectivityManager.getActiveNetworkInfo().isConnected()) {
                            LogUtil.d(TAG, "Network is connected");
                            return true;
                        }
                    }
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // Do nothing
                }
            }
        }
        return false;
    }

    /**
     * To compare the signal strength of two networks
     *
     * @param rssi1 - The signal strength of network 1
     * @param rssi2 - The signal strength of network 2
     * @return int - Returns <0 if the first signal is weaker than the second signal, 0 if the two
     *          signals have the same strength, and >0 if the first signal is stronger than the second signal.
     */
    public int compareSignalLevel(int rssi1, int rssi2) {
        return WifiManager.compareSignalLevel(rssi1, rssi2);
    }

    /**
     * To disable WiFi on a user's device
     *
     * @param activity - The activity to use as context to retrieve a wifi manager via getSystemService
     */
    public void disableWiFi(Activity activity) {
        if(activity != null) {
            WifiManager wifiManager = mGetManagerUtil.getWiFiManager(activity);
            if(wifiManager != null) {
                wifiManager.setWifiEnabled(false);
            }
        } else {
            LogUtil.w(TAG, "No activity to disable Wifi");
        }
    }

    /**
     * To disconnect the user from their current network
     *
     * @param activity - The activity to use as context to retrieve a wifi manager via getSystemService
     */
    public void disconnectFromCurrentNetwork(Activity activity) {
        if (activity != null) {
            LogUtil.d(TAG, "Disconnecting from current network");
            WifiManager wifiManager = mGetManagerUtil.getWiFiManager(activity);
            if(wifiManager != null) {
                wifiManager.disconnect();
            }
        } else {
            LogUtil.w(TAG, "No activity to disconnect from current network");
        }
    }


    /**
     * To enable WiFi on a user's device
     *
     * @param activity - The activity to use as context to retrieve a wifi manager via getSystemService
     */
    public void enableWiFi(Activity activity) {
        if (activity != null) {
            LogUtil.d(TAG, "Enabling WiFi");
            WifiManager wifiManager = mGetManagerUtil.getWiFiManager(activity);
            if(wifiManager != null) {
                wifiManager.setWifiEnabled(true);
            }
        } else {
            LogUtil.w(TAG, "No activity to enable Wifi");
        }
    }

    /**
     * To retrieve the user's current network
     *
     * @param activity - The activity to use as context to retrieve a wifi manager via getSystemService
     * @return WifiInfo|null - The user's current network info
     */
    public WifiInfo getCurrentNetwork(Activity activity) {
        if(activity != null) {
            WifiManager wifiManager = mGetManagerUtil.getWiFiManager(activity);
            if(wifiManager != null) {
                return wifiManager.getConnectionInfo();
            } else {
                return null;
            }
        } else {
            LogUtil.w(TAG, "No activity to get current network");
            return null;
        }
    }

    /**
     * To retrieve a list of nearby access points
     *
     * Setting filterDuplicates to true will not return SSIDs with a weaker signal strength (will always take the highest)
     *
     * @param activity - The activity to use as context to retrieve a wifi manager via getSystemService
     * @param filterDuplicates -
     * @return List<ScanResult>|null - List of nearby access points
     */
    public List<ScanResult> getNearbyAccessPoints(Activity activity, boolean filterDuplicates) {
        if(activity != null) {
            WifiManager wifiManager = mGetManagerUtil.getWiFiManager(activity);
            if(wifiManager != null) {
                wifiManager.startScan();
                if(!filterDuplicates) {
                    return wifiManager.getScanResults();
                } else {
                    List<ScanResult> scanResults = wifiManager.getScanResults();
                    List<ScanResult> scanResultsToReturn = new ArrayList<>();

                    for(ScanResult newScanResult : scanResults) {
                        boolean found = false;
                        for (int i = 0; i < scanResultsToReturn.size(); i++) {
                            ScanResult scanResult = scanResultsToReturn.get(i);
                            if(newScanResult.SSID.equals(scanResult.SSID)) {
                                found = true;
                                LogUtil.d(TAG, "SSID did match");

                                LogUtil.d(TAG, "Current level: " + scanResult.level);
                                LogUtil.d(TAG, "New level: " + newScanResult.level);
                                LogUtil.d(TAG, "comparison result: " + WifiManager.compareSignalLevel(newScanResult.level, scanResult.level));

                                if (WifiManager.compareSignalLevel(newScanResult.level, scanResult.level) > 0) {
                                    LogUtil.d(TAG, "New result has a higher signal strength, swapping");
                                    scanResultsToReturn.set(i, newScanResult);
                                }
                            } else {
                                LogUtil.d(TAG, "SSID did not match");
                            }
                        }

                        if(!found) {
                            LogUtil.d(TAG, "Found new wifi network");
                            scanResultsToReturn.add(newScanResult);
                        }
                    }
                    return scanResultsToReturn;
                }
            } else {
                return null;
            }
        } else {
            LogUtil.w(TAG, "No activity to get nearby networks");
            return null;
        }
    }

    /**
     * To retrieve a list of saved networks on a user's device
     *
     * @param activity - The activity to use as context to retrieve a wifi manager via getSystemService
     * @return List<WifiConfiguration>|null - List of saved networks on a users device
     */
    public List<WifiConfiguration> getSavedNetworks(Activity activity) {
        if(activity != null) {
            WifiManager wifiManager = mGetManagerUtil.getWiFiManager(activity);
            if(wifiManager != null) {
                return wifiManager.getConfiguredNetworks();
            } else {
                return null;
            }
        } else {
            LogUtil.w(TAG, "No activity to get saved networks");
            return null;
        }
    }

    /**
     * To check and return if a network is secure (WEP/PSK/EAP capabilities)
     *
     * @param scanResult - The network to see if it is secure
     * @return boolean - Whether the network is secure
     */
    public boolean isSecure(ScanResult scanResult) {
        boolean isSecure = false;
        if(scanResult != null && scanResult.capabilities != null) {
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
     * @return boolean - if Wifi is enabled on device
     */
    public boolean isWifiEnabled(Activity activity) {
        boolean isWifiEnabled = false;
        if(activity != null) {
            WifiManager wifiManager = GetManagerUtil.getInstance().getWiFiManager(activity);
            isWifiEnabled = wifiManager.isWifiEnabled();
        } else {
            LogUtil.w(TAG, "No activity to get wifi config");
        }
        return isWifiEnabled;
    }

    /**
     * Used to reconnect to a network
     *
     * Gets a list of saved networks, reconnects to the given ssid, and then calls checkWifi to verify connectivity
     *
     * @param activity - The activity to use as context to retrieve a wifi manager via getSystemService
     * @param ssidToReconnectTo - The ssid to reconnect to
     * @param timeout - The approximate number of seconds to keep reconnecting for the SSID
     * @return boolean - If the network was successfully reconnected
     */
    public boolean reconnectToNetwork(Activity activity, String ssidToReconnectTo, int timeout) {
        if (activity != null) {
            LogUtil.d(TAG, "Reconnecting to network: " + ssidToReconnectTo);
            WifiManager wifiManager = mGetManagerUtil.getWiFiManager(activity);
            if (wifiManager != null) {
                List<WifiConfiguration> list = wifiManager.getConfiguredNetworks();
                if (list != null) {
                    for (int i = 0; i < list.size(); i++) {
                        WifiConfiguration wifiConfiguration = list.get(i);
                        if (wifiConfiguration != null && wifiConfiguration.SSID != null) {
                            String ssidInList = wifiConfiguration.SSID.replaceAll("\"", "");

                            LogUtil.d(TAG, "Configured WiFi Network {index:" + i + ", ssidInList:" + ssidInList + "}");
                            if (ssidInList.equals(ssidToReconnectTo)) {
                                LogUtil.d(TAG, "ssidToReconnectTo: " + ssidToReconnectTo + " matches ssidInList:" + ssidInList);
                                wifiManager.disconnect();
                                wifiManager.enableNetwork(wifiConfiguration.networkId, true);
                                wifiManager.reconnect();
                                return checkWifi(activity, ssidToReconnectTo, timeout);
                            }
                        }
                    }
                }

            }
            LogUtil.w(TAG, "ssidToReconnectTo: " + ssidToReconnectTo + " was not found in list to reconnect to");
        } else {
            LogUtil.w(TAG, "No activity to reconnect to network");
        }
        return false;
    }


    /**
     * To remove a configured network
     *
     * @param activity - The activity to use as context to retrieve a wifi manager via getSystemService
     * @param ssidToRemove - The ssid of the network you want to remove from the configured network list
     */
    public void removeNetwork(Activity activity, String ssidToRemove) {
        if (activity != null) {
            WifiManager wifiManager = mGetManagerUtil.getWiFiManager(activity);
            if (wifiManager != null) {
                List<WifiConfiguration> list = wifiManager.getConfiguredNetworks();
                if (list != null) {
                    for (int i = 0; i < list.size(); i++) {
                        WifiConfiguration wifiConfiguration = list.get(i);
                        if (wifiConfiguration != null && wifiConfiguration.SSID != null) {
                            String ssidInList = wifiConfiguration.SSID.replaceAll("\"", "");

                            LogUtil.d(TAG, "Configured WiFi Network {index:" + i + ", ssidInList:" + ssidInList + "}");
                            if (ssidInList.equals(ssidToRemove)) {
                                LogUtil.d(TAG, "Removing network: " + ssidToRemove);
                                wifiManager.disconnect();
                                wifiManager.removeNetwork(wifiConfiguration.networkId);
                                wifiManager.reconnect();
                            }
                        }
                    }
                }

            }
            LogUtil.w(TAG, "SSID to remove: " + ssidToRemove + " was not found in list to reconnect to");
        } else {
            LogUtil.w(TAG, "No activity to reconnect to network");
        }
    }

    /**
     * To search local networks and see if any of them match a given ssid
     *
     * @param activity - The activity to use as context to retrieve a wifi manager via getSystemService
     * @param ssidToSearchFor - The ssid to search for
     * @param timeout - The approximate number of seconds to keep searching for the SSID
     * @return String|null - The first SSID that contains the search ssid (if any, else null)
     */
    public String searchForSSID(Activity activity, String ssidToSearchFor, int timeout) {
        String ssid = null;
        if(activity != null) {
            WifiManager wifiManager = mGetManagerUtil.getWiFiManager(activity);
            if(wifiManager != null) {
                for (int x = 0; x < timeout; x++) {
                    LogUtil.d(TAG, "Scanning SSIDs, pass " + x);
                    wifiManager.startScan();
                    List<ScanResult> networks = wifiManager.getScanResults();
                    boolean ssidFound = false;
                    for (ScanResult scanResult : networks) {
                        LogUtil.d(TAG, "scanResult.SSID: " + scanResult.SSID);
                        if (scanResult.SSID != null
                                && (scanResult.SSID.toUpperCase().contains(ssidToSearchFor))) {
                            LogUtil.d(TAG, "Found match, SSID: " + scanResult.SSID);
                            ssid = scanResult.SSID;
                            ssidFound = true;
                            break;
                        }
                    }
                    if (ssidFound) {
                        break;
                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ie) {
                        // Do nothing
                    }
                }
            }
        } else {
            LogUtil.w(TAG, "No activity to search for SSID");
        }
        return ssid;
    }
}