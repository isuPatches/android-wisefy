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


import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import com.isupatches.wisefy.annotations.CallingThread;
import com.isupatches.wisefy.annotations.Async;
import com.isupatches.wisefy.annotations.Sync;
import com.isupatches.wisefy.annotations.WaitsForTimeout;
import com.isupatches.wisefy.annotations.WiseFyThread;
import com.isupatches.wisefy.callbacks.AddOpenNetworkCallbacks;
import com.isupatches.wisefy.callbacks.AddWEPNetworkCallbacks;
import com.isupatches.wisefy.callbacks.AddWPA2NetworkCallbacks;
import com.isupatches.wisefy.callbacks.ConnectToNetworkCallbacks;
import com.isupatches.wisefy.callbacks.DisableWifiCallbacks;
import com.isupatches.wisefy.callbacks.DisconnectFromCurrentNetworkCallbacks;
import com.isupatches.wisefy.callbacks.EnableWifiCallbacks;
import com.isupatches.wisefy.callbacks.GetCurrentNetworkCallbacks;
import com.isupatches.wisefy.callbacks.GetFrequencyCallbacks;
import com.isupatches.wisefy.callbacks.GetNearbyAccessPointsCallbacks;
import com.isupatches.wisefy.callbacks.GetSavedNetworkCallbacks;
import com.isupatches.wisefy.callbacks.GetSavedNetworksCallbacks;
import com.isupatches.wisefy.callbacks.RemoveNetworkCallbacks;
import com.isupatches.wisefy.callbacks.SearchForSSIDCallbacks;
import com.isupatches.wisefy.constants.Capabilities;
import com.isupatches.wisefy.constants.NetworkTypes;
import com.isupatches.wisefy.constants.WiseFyCodes;
import com.isupatches.wisefy.threads.WiseFyHandlerThread;
import com.isupatches.wisefy.util.ManagerUtil;
import com.isupatches.wisefy.util.LogUtil;
import com.isupatches.wisefy.util.WifiConfigurationUtil;
import java.util.ArrayList;
import java.util.List;


/**
 * Main class to manipulate and query network settings on an Android device
 *
 * Uses the builder pattern for creation - {@link withContext}
 */
public class WiseFy {

    private static final String TAG = WiseFy.class.getSimpleName();

    public static final int WIFI_MANAGER_FAILURE = -1;

    public static final int MIN_FREQUENCY_5GHZ = 4900;

    public static final int MAX_FREQUENCY_5GHZ = 5900;

    private WiseFyHandlerThread mWiseFyHandlerThread;

    public ConnectivityManager mConnectivityManager;

    public WifiManager mWifiManager;

    private boolean mLoggingEnabled;

    private Handler mWiseFyHandler;

    /**
     * Private constructor that accepts builder input
     */
    private WiseFy(withContext withContext) {
        this.mLoggingEnabled = withContext.loggingEnabled;
        this.mConnectivityManager = ManagerUtil.getInstance().getConnectivityManager(withContext.context);
        this.mWifiManager = ManagerUtil.getInstance().getWiFiManager(withContext.context);
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
         */
        public withContext(Context context) {
            this.context = context;
        }

        /**
         * Mandatory - To build and return a WiseFy instance
         *
         * Must be called after withContext
         * {@link withContext}
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
     * Uses
     *   {@link #addNetwork(WifiConfiguration)}
     *   {@link #checkIfNetworkInConfigurationList(String)}
     *   {@link WifiConfigurationUtil#generateOpenNetworkConfiguration(String)}
     *   {@link WiseFyCodes
     *
     * @return int - The return code from WifiManager for network creation (-1 for failure)
     */
    @Sync
    @CallingThread
    public int addOpenNetwork(String ssid) {
        if (TextUtils.isEmpty(ssid)) {
            return WiseFyCodes.MISSING_PARAMETER;
        }

        if (!hasPrerequisites()) {
            return WiseFyCodes.MISSING_PREREQUISITE;
        }

        if (checkIfNetworkInConfigurationList(ssid)) {
            return WiseFyCodes.NETWORK_ALREADY_CONFIGURED;
        }

        if (LogUtil.isLoggable(TAG, Log.DEBUG, mLoggingEnabled)) {
            Log.d(TAG, String.format("Adding open network with SSID %s", ssid));
        }
        WifiConfiguration wifiConfiguration = WifiConfigurationUtil.getInstance().generateOpenNetworkConfiguration(ssid);
        return addNetwork(wifiConfiguration);
    }

    /**
     * To add an open network to the user's configured network list
     *
     * @param ssid - The ssid of the open network you want to add
     * @param addOpenNetworkCallbacks -
     *
     * Uses
     *   {@link #addNetwork(WifiConfiguration)}
     *   {@link #checkIfNetworkInConfigurationList(String)}
     *   {@link WifiConfigurationUtil#generateOpenNetworkConfiguration(String)}
     *   {@link WiseFyCodes
     */
    @Async
    @WiseFyThread
    public void addOpenNetwork(final String ssid, final AddOpenNetworkCallbacks addOpenNetworkCallbacks) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (TextUtils.isEmpty(ssid)) {
                    if (addOpenNetworkCallbacks != null) {
                        addOpenNetworkCallbacks.addOpenNetworkWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
                    }
                    return;
                }

                if (!hasPrerequisites()) {
                    if (addOpenNetworkCallbacks != null) {
                        addOpenNetworkCallbacks.addOpenNetworkWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
                    }
                    return;
                }

                if (checkIfNetworkInConfigurationList(ssid)) {
                    if (addOpenNetworkCallbacks != null) {
                        addOpenNetworkCallbacks.addOpenNetworkWiseFyFailure(WiseFyCodes.NETWORK_ALREADY_CONFIGURED);
                    }
                    return;
                }

                if (LogUtil.isLoggable(TAG, Log.DEBUG, mLoggingEnabled)) {
                    Log.d(TAG, String.format("Adding open network with SSID %s", ssid));
                }
                WifiConfiguration wifiConfiguration = WifiConfigurationUtil.getInstance().generateOpenNetworkConfiguration(ssid);
                if (addOpenNetworkCallbacks != null) {
                    int result = addNetwork(wifiConfiguration);
                    if (result != WIFI_MANAGER_FAILURE) {
                        addOpenNetworkCallbacks.openNetworkAdded(wifiConfiguration);
                    } else {
                        addOpenNetworkCallbacks.failureAddingOpenNetwork(result);
                    }
                }
                mWiseFyHandlerThread.quitSafely();
            }
        };
        execute(runnable);
    }

    /**
     * To add a WEP network to the user's configured network list
     *
     * @param ssid - The ssid of the WEP network you want to add
     * @param password - The password for the WEP network being added
     *
     * Uses
     *   {@link #addNetwork(WifiConfiguration)}
     *   {@link #checkIfNetworkInConfigurationList(String)}
     *   {@link WifiConfigurationUtil#generateWEPNetworkConfiguration(String, String)}
     *   {@link WiseFyCodes
     *
     * @return int - The return code from WifiManager for network creation (-1 for failure)
     */
    @Sync
    @CallingThread
    public int addWEPNetwork(String ssid, String password) {
        if (TextUtils.isEmpty(ssid) || TextUtils.isEmpty(password)) {
            return WiseFyCodes.MISSING_PARAMETER;
        }

        if (!hasPrerequisites()) {
            return WiseFyCodes.MISSING_PREREQUISITE;
        }

        if (checkIfNetworkInConfigurationList(ssid)) {
            return WiseFyCodes.NETWORK_ALREADY_CONFIGURED;
        }

        if (LogUtil.isLoggable(TAG, Log.DEBUG, mLoggingEnabled)) {
            Log.d(TAG, String.format("Adding WEP network with SSID %s", ssid));
        }
        WifiConfiguration wifiConfiguration = WifiConfigurationUtil.getInstance().generateWEPNetworkConfiguration(ssid, password);
        return addNetwork(wifiConfiguration);
    }

    /**
     * To add a WEP network to the user's configured network list
     *
     * @param ssid - The ssid of the WEP network you want to add
     * @param password - The password for the WEP network being added
     * @param addWEPNetworkCallbacks -
     *
     * Uses
     *   {@link #addNetwork(WifiConfiguration)}
     *   {@link #checkIfNetworkInConfigurationList(String)}
     *   {@link WifiConfigurationUtil#generateWEPNetworkConfiguration(String, String)}
     *   {@link WiseFyCodes
     */
    @Async
    @WiseFyThread
    public void addWEPNetwork(final String ssid, final String password, final AddWEPNetworkCallbacks addWEPNetworkCallbacks) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (TextUtils.isEmpty(ssid) || TextUtils.isEmpty(password)) {
                    if (addWEPNetworkCallbacks != null) {
                        addWEPNetworkCallbacks.addWEPNetworkWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
                    }
                    return;
                }

                if (!hasPrerequisites()) {
                    if (addWEPNetworkCallbacks != null) {
                        addWEPNetworkCallbacks.addWEPNetworkWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
                    }
                    return;
                }

                if (checkIfNetworkInConfigurationList(ssid)) {
                    if (addWEPNetworkCallbacks != null) {
                        addWEPNetworkCallbacks.addWEPNetworkWiseFyFailure(WiseFyCodes.NETWORK_ALREADY_CONFIGURED);
                    }
                    return;
                }

                if (LogUtil.isLoggable(TAG, Log.DEBUG, mLoggingEnabled)) {
                    Log.d(TAG, String.format("Adding WEP network with SSID %s", ssid));
                }
                WifiConfiguration wifiConfiguration = WifiConfigurationUtil.getInstance().generateWEPNetworkConfiguration(ssid, password);
                if (addWEPNetworkCallbacks != null) {
                    int result = addNetwork(wifiConfiguration);
                    if (result != WIFI_MANAGER_FAILURE) {
                        addWEPNetworkCallbacks.wepNetworkAdded(wifiConfiguration);
                    } else {
                        addWEPNetworkCallbacks.failureAddingWEPNetwork(result);
                    }
                }
                mWiseFyHandlerThread.quitSafely();
            }
        };
        execute(runnable);
    }

    /**
     * To add a WPA2 network to the user's configured network list
     *
     * @param ssid - The ssid of the WPA2 network you want to add
     * @param password - The password for the WPA2 network being added
     *
     * Uses
     *   {@link #addNetwork(WifiConfiguration)}
     *   {@link #checkIfNetworkInConfigurationList(String)}
     *   {@link WifiConfigurationUtil#generateWPA2NetworkConfiguration(String, String)}
     *   {@link WiseFyCodes
     *
     * @return int - The return code from WifiManager for network creation (-1 for failure)
     */
    @Sync
    @CallingThread
    public int addWPA2Network(String ssid, String password) {
        if (TextUtils.isEmpty(ssid) || TextUtils.isEmpty(password)) {
            return WiseFyCodes.MISSING_PARAMETER;
        }

        if (!hasPrerequisites()) {
            return WiseFyCodes.MISSING_PREREQUISITE;
        }

        if (checkIfNetworkInConfigurationList(ssid)) {
            return WiseFyCodes.NETWORK_ALREADY_CONFIGURED;
        }

        if (LogUtil.isLoggable(TAG, Log.DEBUG, mLoggingEnabled)) {
            Log.d(TAG, String.format("Adding WPA2 network with SSID %s", ssid));
        }
        WifiConfiguration wifiConfiguration = WifiConfigurationUtil.getInstance().generateWPA2NetworkConfiguration(ssid, password);
        return addNetwork(wifiConfiguration);
    }

    /**
     * To add a WPA2 network to the user's configured network list
     *
     * @param ssid - The ssid of the WPA2 network you want to add
     * @param password - The password for the WPA2 network being added
     * @param addWPA2NetworkCallbacks -
     *
     * Uses
     *   {@link #addNetwork(WifiConfiguration)}
     *   {@link #checkIfNetworkInConfigurationList(String)}
     *   {@link WifiConfigurationUtil#generateWPA2NetworkConfiguration(String, String)}
     *   {@link WiseFyCodes
     */
    @Async
    @WiseFyThread
    public void addWPA2Network(final String ssid, final String password, final AddWPA2NetworkCallbacks addWPA2NetworkCallbacks) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (TextUtils.isEmpty(ssid) || TextUtils.isEmpty(password)) {
                    if (addWPA2NetworkCallbacks != null) {
                        addWPA2NetworkCallbacks.addWPA2NetworkWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
                    }
                    return;
                }

                if (!hasPrerequisites()) {
                    if (addWPA2NetworkCallbacks != null) {
                        addWPA2NetworkCallbacks.addWPA2NetworkWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
                    }
                    return;
                }

                if (checkIfNetworkInConfigurationList(ssid)) {
                    if (addWPA2NetworkCallbacks != null) {
                        addWPA2NetworkCallbacks.addWPA2NetworkWiseFyFailure(WiseFyCodes.NETWORK_ALREADY_CONFIGURED);
                    }
                    return;
                }

                if (LogUtil.isLoggable(TAG, Log.DEBUG, mLoggingEnabled)) {
                    Log.d(TAG, String.format("Adding WPA2 network with SSID %s", ssid));
                }
                WifiConfiguration wifiConfiguration = WifiConfigurationUtil.getInstance().generateWPA2NetworkConfiguration(ssid, password);
                if (addWPA2NetworkCallbacks != null) {
                    int result = addNetwork(wifiConfiguration);
                    if (result != WIFI_MANAGER_FAILURE) {
                        addWPA2NetworkCallbacks.wpa2NetworkAdded(wifiConfiguration);
                    } else {
                        addWPA2NetworkCallbacks.failureAddingWPA2Network(result);
                    }
                }
                mWiseFyHandlerThread.quitSafely();
            }
        };
        execute(runnable);
    }

    /**
     * To convert an RSSI level for a network to a number of bars
     *
     * @param rssiLevel - The signal strength of the network
     * @param targetNumberOfBars - How many bars or levels there will be total
     *
     * @return int - The number of bars for the given RSSI value
     */
    @Sync
    @CallingThread
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
    @Sync
    @CallingThread
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
     * Uses
     *   {@link #waitToConnectToSSID(String, int)}
     *
     * @return boolean - If the network was successfully reconnected
     */
    @Sync
    @CallingThread
    @WaitsForTimeout
    public boolean connectToNetwork(String ssidToConnectTo, int timeoutInMillis) {
        if (TextUtils.isEmpty(ssidToConnectTo) || !hasPrerequisites()) {
            return false;
        }

        List<WifiConfiguration> list = mWifiManager.getConfiguredNetworks();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                WifiConfiguration wifiConfiguration = list.get(i);
                if (wifiConfiguration != null && wifiConfiguration.SSID != null) {
                    String ssidInList = wifiConfiguration.SSID.replaceAll("\"", "");
                    if (LogUtil.isLoggable(TAG, Log.DEBUG, mLoggingEnabled)) {
                        Log.d(TAG, String.format("Configured WiFi Network { index: %d, ssidInList: %s }", i, ssidInList));
                    }
                    if (ssidInList.equals(ssidToConnectTo)) {
                        if (LogUtil.isLoggable(TAG, Log.DEBUG, mLoggingEnabled)) {
                            Log.d(TAG, String.format("ssidToConnectTo: %s matches ssidInList: %s", ssidToConnectTo, ssidInList));
                        }
                        mWifiManager.disconnect();
                        mWifiManager.enableNetwork(wifiConfiguration.networkId, true);
                        mWifiManager.reconnect();
                        return waitToConnectToSSID(ssidToConnectTo, timeoutInMillis);
                    }
                }
            }
        }
        if (LogUtil.isLoggable(TAG, Log.WARN, mLoggingEnabled)) {
            Log.w(TAG, String.format("ssidToConnectTo: %s was not found in list to connect to", ssidToConnectTo));
        }
        return false;
    }

    /**
     * Used to connect to a network
     *
     * Gets a list of saved networks, connects/reconnects to the given ssid, and then calls waitToConnectToSSID to verify connectivity
     *
     * @param ssidToConnectTo - The ssid to connect/reconnect to
     * @param timeoutInMillis - The number of milliseconds to continue waiting for the device to connect to the given SSID
     *
     * Uses
     *   {@link #waitToConnectToSSID(String, int)}
     *   {@link WiseFyCodes}
     */
    @Async
    @WiseFyThread
    @WaitsForTimeout
    public void connectToNetwork(final String ssidToConnectTo, final int timeoutInMillis, final ConnectToNetworkCallbacks connectToNetworkCallbacks) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (TextUtils.isEmpty(ssidToConnectTo)) {
                    if (connectToNetworkCallbacks != null) {
                        connectToNetworkCallbacks.connectToNetworkWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
                    }
                    return;
                }

                if (!hasPrerequisites()) {
                    if (connectToNetworkCallbacks != null) {
                        connectToNetworkCallbacks.connectToNetworkWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
                    }
                    return;
                }

                List<WifiConfiguration> list = mWifiManager.getConfiguredNetworks();
                if (list != null) {
                    for (int i = 0; i < list.size(); i++) {
                        WifiConfiguration wifiConfiguration = list.get(i);
                        if (wifiConfiguration != null && wifiConfiguration.SSID != null) {
                            String ssidInList = wifiConfiguration.SSID.replaceAll("\"", "");
                            if (LogUtil.isLoggable(TAG, Log.DEBUG, mLoggingEnabled)) {
                                Log.d(TAG, String.format("Configured WiFi Network { index: %d, ssidInList: %s }", i, ssidInList));
                            }
                            if (ssidInList.equals(ssidToConnectTo)) {
                                if (LogUtil.isLoggable(TAG, Log.DEBUG, mLoggingEnabled)) {
                                    Log.d(TAG, String.format("ssidToConnectTo: %s matches ssidInList: %s", ssidToConnectTo, ssidInList));
                                }
                                mWifiManager.disconnect();
                                mWifiManager.enableNetwork(wifiConfiguration.networkId, true);
                                mWifiManager.reconnect();
                                boolean connected = waitToConnectToSSID(ssidToConnectTo, timeoutInMillis);
                                if (connectToNetworkCallbacks != null) {
                                    if (connected) {
                                        connectToNetworkCallbacks.connectedToNetwork();
                                    } else {
                                        connectToNetworkCallbacks.failureConnectingToNetwork();
                                    }
                                }
                                return;
                            }
                        }
                    }
                }
                if (LogUtil.isLoggable(TAG, Log.WARN, mLoggingEnabled)) {
                    Log.w(TAG, String.format("ssidToConnectTo: %s was not found in list to connect to", ssidToConnectTo));
                }
                if (connectToNetworkCallbacks != null) {
                    connectToNetworkCallbacks.networkNotFoundToConnectTo();
                }
                mWiseFyHandlerThread.quitSafely();
            }
        };
        execute(runnable);
    }

    /**
     * To disable Wifi on a user's device
     *
     * @return boolean - If the command succeeded in disabling wifi
     */
    @Sync
    @CallingThread
    public boolean disableWifi() {
        return hasPrerequisites() && mWifiManager.setWifiEnabled(false);
    }

    /**
     * To disable Wifi on a user's device
     *
     * Uses
     *   {@link WiseFyCodes}
     */
    @Async
    @WiseFyThread
    public void disableWifi(final DisableWifiCallbacks disableWifiCallbacks) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (!hasPrerequisites()) {
                    if (disableWifiCallbacks != null) {
                        disableWifiCallbacks.disableWifiWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
                    }
                    return;
                }

                boolean result = mWifiManager.setWifiEnabled(false);
                if (disableWifiCallbacks != null) {
                    if (result) {
                        disableWifiCallbacks.wifiDisabled();
                    } else {
                        disableWifiCallbacks.failureDisablingWifi();
                    }
                }
                mWiseFyHandlerThread.quitSafely();
            }
        };
        execute(runnable);
    }

    /**
     * To disconnect the user from their current network
     *
     * @return boolean - If the command succeeded in disconnecting the device from the current network
     */
    @Sync
    @CallingThread
    public boolean disconnectFromCurrentNetwork() {
        return hasPrerequisites() && mWifiManager.disconnect();
    }

    /**
     * To disconnect the user from their current network
     *
     * Uses
     *   {@link WiseFyCodes}
     */
    @Async
    @WiseFyThread
    public void disconnectFromCurrentNetwork(final DisconnectFromCurrentNetworkCallbacks disconnectFromCurrentNetworkCallbacks) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                if (!hasPrerequisites()) {
                    if (disconnectFromCurrentNetworkCallbacks != null) {
                        disconnectFromCurrentNetworkCallbacks.disconnectFromCurrentNetworkWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
                    }
                    return;
                }

                boolean result = mWifiManager.disconnect();
                if (disconnectFromCurrentNetworkCallbacks != null) {
                    if (result) {
                        disconnectFromCurrentNetworkCallbacks.disconnectedFromCurrentNetwork();
                    } else {
                        disconnectFromCurrentNetworkCallbacks.failureDisconnectingFromCurrentNetwork();
                    }
                }
                mWiseFyHandlerThread.quitSafely();
            }
        };
        execute(runnable);
    }

    /**
     * To enable Wifi on a user's device
     *
     * @return boolean - If the command succeeded in enabling wifi
     */
    @Sync
    @CallingThread
    public boolean enableWifi() {
        return hasPrerequisites() && mWifiManager.setWifiEnabled(true);
    }

    /**
     * To enable Wifi on a user's device
     *
     * Uses
     *   {@link WiseFyCodes}
     */
    @Async
    @WiseFyThread
    public void enableWifi(final EnableWifiCallbacks enableWifiCallbacks) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (!hasPrerequisites()) {
                    if (enableWifiCallbacks != null) {
                        enableWifiCallbacks.enableWifiWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
                    }
                    return;
                }

                boolean result = mWifiManager.setWifiEnabled(true);
                if (enableWifiCallbacks != null) {
                    if (result) {
                        enableWifiCallbacks.wifiEnabled();
                    } else {
                        enableWifiCallbacks.failureEnablingWifi();
                    }
                }
                mWiseFyHandlerThread.quitSafely();
            }
        };
        execute(runnable);
    }

    /**
     * To retrieve the user's current network
     *
     * @return WifiInfo|null - The user's current network information
     */
    @Sync
    @CallingThread
    public WifiInfo getCurrentNetwork() {
        if (!hasPrerequisites()) {
            return null;
        }
        return mWifiManager.getConnectionInfo();
    }

    /**
     * To retrieve the user's current network
     *
     * Uses
     *   {@link WiseFyCodes}
     */
    @Async
    @WiseFyThread
    public void getCurrentNetwork(final GetCurrentNetworkCallbacks getCurrentNetworkCallbacks) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (!hasPrerequisites()) {
                    if (getCurrentNetworkCallbacks != null) {
                        getCurrentNetworkCallbacks.getCurrentNetworkWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
                    }
                    return;
                }

                if (getCurrentNetworkCallbacks != null) {
                    getCurrentNetworkCallbacks.retrievedCurrentNetwork(mWifiManager.getConnectionInfo());
                }
                mWiseFyHandlerThread.quitSafely();
            }
        };
        execute(runnable);
    }

    /**
     * To retrieve the frequency of the device's current network
     *
     * Used by
     *  {@link #isNetwork5gHz()}
     *
     * Uses
     *  {@link #getCurrentNetwork()}
     *
     * @return int|NULL - The frequency of the devices current network or null if no network
     */
    @Sync
    @CallingThread
    @TargetApi(21)
    public Integer getFrequency() {
        WifiInfo currentNetwork = getCurrentNetwork();
        if (currentNetwork != null) {
            return currentNetwork.getFrequency();
        }
        return null;
    }

    /**
     * To retrieve the frequency of the device's current network
     *
     * Used by
     *   {@link #isNetwork5gHz()}
     *
     * Uses
     *   {@link #getCurrentNetwork(GetCurrentNetworkCallbacks)} ()}
     *   {@link WiseFyCodes}
     */
    @Async
    @WiseFyThread
    @TargetApi(21)
    public void getFrequency(final GetFrequencyCallbacks getFrequencyCallbacks) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                WifiInfo currentNetwork = getCurrentNetwork();
                if (currentNetwork != null) {
                    if (getFrequencyCallbacks != null) {
                        getFrequencyCallbacks.retrievedFrequency(currentNetwork.getFrequency());
                    }
                } else {
                    if (getFrequencyCallbacks != null) {
                        getFrequencyCallbacks.failureGettingFrequency();
                    }
                }
                mWiseFyHandlerThread.quitSafely();
            }
        };
        execute(runnable);
    }

    /**
     * To retrieve the frequency of a network
     *
     * @param network - The network to return the frequency of
     *
     * Used by
     *   {@link #isNetwork5gHz(WifiInfo)}
     *
     * @return int|NULL - The frequency of the devices current network or null if no network
     */
    @Sync
    @CallingThread
    @TargetApi(21)
    public Integer getFrequency(WifiInfo network) {
        if (network != null) {
            return network.getFrequency();
        }
        return null;
    }

    /**
     * To retrieve the frequency of a network
     *
     * @param network - The network to return the frequency of
     *
     * Used by isNetwork5gHz
     *   {@link #isNetwork5gHz(WifiInfo)}
     *
     * Uses
     *   {@link WiseFyCodes}
     */
    @Async
    @WiseFyThread
    @TargetApi(21)
    public void getFrequency(final WifiInfo network, final GetFrequencyCallbacks getFrequencyCallbacks) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (network != null) {
                    if (getFrequencyCallbacks != null) {
                        getFrequencyCallbacks.retrievedFrequency(network.getFrequency());
                    }
                }
                if (getFrequencyCallbacks != null) {
                    getFrequencyCallbacks.getFrequencyWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
                }
                mWiseFyHandlerThread.quitSafely();
            }
        };
        execute(runnable);
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
    @Sync
    @CallingThread
    public List<ScanResult> getNearbyAccessPoints(boolean filterDuplicates) {
        if (!hasPrerequisites()) {
            return null;
        }

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
                    if (LogUtil.isLoggable(TAG, Log.DEBUG, mLoggingEnabled)) {
                        Log.d(TAG, String.format("SSID 1: %s, SSID 2: %s", newScanResult.SSID, scanResult.SSID));
                    }
                    if (newScanResult.SSID.equalsIgnoreCase(scanResult.SSID)) {
                        found = true;
                        if (LogUtil.isLoggable(TAG, Log.DEBUG, mLoggingEnabled)) {
                            Log.d(TAG, "SSID did match");
                            Log.d(TAG, String.format("Current level: %d", scanResult.level));
                            Log.d(TAG, String.format("New level: %d", newScanResult.level));
                            Log.d(TAG, String.format("comparison result: %d", WifiManager.compareSignalLevel(newScanResult.level, scanResult.level)));
                        }
                        if (WifiManager.compareSignalLevel(newScanResult.level, scanResult.level) > 0) {
                            if (LogUtil.isLoggable(TAG, Log.DEBUG, mLoggingEnabled)) {
                                Log.d(TAG, "New result has a higher signal strength, swapping");
                            }
                            scanResultsToReturn.set(i, newScanResult);
                        }
                    }
                }

                if (!found) {
                    if (LogUtil.isLoggable(TAG, Log.DEBUG, mLoggingEnabled)) {
                        Log.d(TAG, "Found new wifi network");
                    }
                    scanResultsToReturn.add(newScanResult);
                }
            }
            return scanResultsToReturn;
        }
    }

    /**
     * To retrieve a list of nearby access points
     *
     * *NOTE* Setting filterDuplicates to true will not return SSIDs with a weaker signal strength (will always take the highest)
     *
     * @param filterDuplicates - If you want to exclude SSIDs with that same name that have a weaker signal strength
     */
    @Async
    @WiseFyThread
    public void getNearbyAccessPoints(final boolean filterDuplicates, final GetNearbyAccessPointsCallbacks getNearbyAccessPointsCallbacks) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (!hasPrerequisites()) {
                    if (getNearbyAccessPointsCallbacks != null) {
                        getNearbyAccessPointsCallbacks.getNearbyAccessPointsWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
                    }
                    return;
                }

                mWifiManager.startScan();
                if (!filterDuplicates) {
                    if (getNearbyAccessPointsCallbacks != null) {
                        getNearbyAccessPointsCallbacks.retrievedNearbyAccessPoints(mWifiManager.getScanResults());
                    }
                } else {
                    List<ScanResult> scanResults = mWifiManager.getScanResults();
                    List<ScanResult> scanResultsToReturn = new ArrayList<>();

                    for (ScanResult newScanResult : scanResults) {
                        boolean found = false;
                        for (int i = 0; i < scanResultsToReturn.size(); i++) {
                            ScanResult scanResult = scanResultsToReturn.get(i);
                            if (LogUtil.isLoggable(TAG, Log.DEBUG, mLoggingEnabled)) {
                                Log.d(TAG, String.format("SSID 1: %s, SSID 2: %s", newScanResult.SSID, scanResult.SSID));
                            }
                            if (newScanResult.SSID.equalsIgnoreCase(scanResult.SSID)) {
                                found = true;
                                if (LogUtil.isLoggable(TAG, Log.DEBUG, mLoggingEnabled)) {
                                    Log.d(TAG, "SSID did match");
                                    Log.d(TAG, String.format("Current level: %d", scanResult.level));
                                    Log.d(TAG, String.format("New level: %d", newScanResult.level));
                                    Log.d(TAG, String.format("comparison result: %d", WifiManager.compareSignalLevel(newScanResult.level, scanResult.level)));
                                }
                                if (WifiManager.compareSignalLevel(newScanResult.level, scanResult.level) > 0) {
                                    if (LogUtil.isLoggable(TAG, Log.DEBUG, mLoggingEnabled)) {
                                        Log.d(TAG, "New result has a higher signal strength, swapping");
                                    }
                                    scanResultsToReturn.set(i, newScanResult);
                                }
                            }
                        }

                        if (!found) {
                            if (LogUtil.isLoggable(TAG, Log.DEBUG, mLoggingEnabled)) {
                                Log.d(TAG, "Found new wifi network");
                            }
                            scanResultsToReturn.add(newScanResult);
                        }
                    }
                    if (getNearbyAccessPointsCallbacks != null) {
                        getNearbyAccessPointsCallbacks.retrievedNearbyAccessPoints(scanResultsToReturn);
                    }
                }
                mWiseFyHandlerThread.quitSafely();
            }
        };
        execute(runnable);
    }

    /**
     * To search for and return a saved WiFiConfiguration given an SSID
     *
     * @param ssid - The ssid to use while searching for saved configuration
     *
     * Uses
     *   {@link #findNetworkInConfigurationList(String)}
     *
     * @return WifiConfiguration|null - Saved network that matches the ssid
     */
    @Sync
    @CallingThread
    public WifiConfiguration getSavedNetwork(String ssid) {
        if (!hasPrerequisites() || TextUtils.isEmpty(ssid)) {
            return null;
        }
        return findNetworkInConfigurationList(ssid);
    }

    /**
     * To search for and return a saved WiFiConfiguration given an SSID
     *
     * @param ssid - The ssid to use while searching for saved configuration
     * @param getSavedNetworkCallback - The listener to return results to
     *
     * Uses
     *   {@link #execute(Runnable)}
     *   {@link #findNetworkInConfigurationList(String)}
     *   {@link GetSavedNetworkCallbacks}
     *   {@link WiseFyHandlerThread}
     */
    @Async
    @WiseFyThread
    public void getSavedNetwork(final String ssid, final GetSavedNetworkCallbacks getSavedNetworkCallback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (TextUtils.isEmpty(ssid)) {
                    if (getSavedNetworkCallback != null) {
                        getSavedNetworkCallback.getSavedNetworkWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
                    }
                    return;
                }

                if (!hasPrerequisites()) {
                    if (getSavedNetworkCallback != null) {
                        getSavedNetworkCallback.getSavedNetworkWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
                    }
                    return;
                }

                WifiConfiguration savedNetwork = findNetworkInConfigurationList(ssid);
                if (getSavedNetworkCallback != null) {
                    if (savedNetwork != null) {
                        getSavedNetworkCallback.retrievedSavedNetwork(savedNetwork);
                    } else {
                        getSavedNetworkCallback.networkNotFound();
                    }
                }
                mWiseFyHandlerThread.quitSafely();
            }
        };
        execute(runnable);
    }

    /**
     * To retrieve a list of saved networks on a user's device
     *
     * @return List of WifiConfiguration|null - List of saved networks on a users device
     */
    @Sync
    @CallingThread
    public List<WifiConfiguration> getSavedNetworks() {
        if (!hasPrerequisites()) {
            return null;
        }
        return mWifiManager.getConfiguredNetworks();
    }

    /**
     * To retrieve a list of saved networks on a user's device
     *
     * @param getSavedNetworksCallback - The listener to return results to
     *
     * Uses
     *   {@link #execute(Runnable)}
     *   {@link GetSavedNetworksCallbacks}
     *   {@link WiseFyHandlerThread}
     */
    @Async
    @WiseFyThread
    public void getSavedNetworks(final GetSavedNetworksCallbacks getSavedNetworksCallback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (!hasPrerequisites()) {
                    if (getSavedNetworksCallback != null) {
                        getSavedNetworksCallback.getSavedNetworksWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
                    }
                    return;
                }

                if (getSavedNetworksCallback != null) {
                    getSavedNetworksCallback.retrievedSavedNetworks(mWifiManager.getConfiguredNetworks());
                }
                mWiseFyHandlerThread.quitSafely();
            }
        };
        execute(runnable);
    }

    /**
     * To check if the device is connected to a mobile network
     *
     * Uses
     *   {@link NetworkTypes}
     *
     * @return bool - If the device is currently connected to a mobile network
     */
    @Sync
    @CallingThread
    public boolean isDeviceConnectedToMobileNetwork() {
        if (!hasPrerequisites()) {
            return false;
        }

        NetworkInfo networkInfo = mConnectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.getTypeName() != null && networkInfo.getTypeName().equalsIgnoreCase(NetworkTypes.MOBILE)) {
            if (networkInfo.isConnected() && networkInfo.isAvailable()) {
                return true;
            }
        }
        return false;
    }

    /**
     * To check if the device is connected to a mobile or wifi network
     *
     * @return bool - If the device is currently connected to a mobile or wifi network
     */
    @Sync
    @CallingThread
    public boolean isDeviceConnectedToMobileOrWifiNetwork() {
        if (!hasPrerequisites()) {
            return false;
        }

        NetworkInfo networkInfo = mConnectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected() && networkInfo.isAvailable();
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
    @Sync
    @CallingThread
    public boolean isDeviceConnectedToSSID(String ssid) {
        if (TextUtils.isEmpty(ssid) || !hasPrerequisites()) {
            return false;
        }

        WifiInfo connectionInfo = mWifiManager.getConnectionInfo();
        if (connectionInfo != null && connectionInfo.getSSID() != null) {
            String currentSSID = connectionInfo.getSSID().replaceAll("\"", "");
            if (LogUtil.isLoggable(TAG, Log.DEBUG, mLoggingEnabled)) {
                Log.d(TAG, String.format("Current SSID: %s, Desired SSID: %s", currentSSID, ssid));
            }
            if (currentSSID.equals(ssid)) {
                if (mConnectivityManager.getActiveNetworkInfo() != null
                        && mConnectivityManager.getActiveNetworkInfo().isAvailable()
                        && mConnectivityManager.getActiveNetworkInfo().isConnected()) {
                    if (LogUtil.isLoggable(TAG, Log.DEBUG, mLoggingEnabled)) {
                        Log.d(TAG, "Network is connected");
                    }
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * To check if the device is connected to a wifi network
     *
     * Uses
     *   {@link NetworkTypes}
     *
     * @return bool - If the device is currently connected to a wifi network
     */
    @Sync
    @CallingThread
    public boolean isDeviceConnectedToWifiNetwork() {
        if (!hasPrerequisites()) {
            return false;
        }

        NetworkInfo networkInfo = mConnectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.getTypeName() != null && networkInfo.getTypeName().equalsIgnoreCase(NetworkTypes.WIFI)) {
            if (networkInfo.isConnected() && networkInfo.isAvailable()) {
                return true;
            }
        }
        return false;
    }

    /**
     * To query if logging is enabled or disabled for a WiseFy instance
     *
     * @return boolean - If logging is enabled for the WiseFy instance
     */
    @Sync
    @CallingThread
    public boolean isLoggingEnabled() {
        return mLoggingEnabled;
    }

    /**
     * To check if the device's current network is 5gHz
     *
     * Uses
     *   {@link #getFrequency()}
     *
     * @return boolean - If the network is 5gHz
     */
    @Sync
    @CallingThread
    public boolean isNetwork5gHz() {
        int frequency = getFrequency();
        return frequency > MIN_FREQUENCY_5GHZ && frequency < MAX_FREQUENCY_5GHZ;
    }

    /**
     * To check if a given network is 5gHz
     *
     * @param network - The network to check if it's 5gHz
     *
     * Uses
     *   {@link #getFrequency(WifiInfo)}
     *
     * @return boolean - If the network is 5gHz
     */
    @Sync
    @CallingThread
    public boolean isNetwork5gHz(WifiInfo network) {
        int frequency = getFrequency(network);
        return frequency > MIN_FREQUENCY_5GHZ && frequency < MAX_FREQUENCY_5GHZ;
    }

    /**
     * To check if an SSID is in the list of configured networks
     *
     * @param ssid - The SSID to check and see if it's in the list of configured networks
     *
     * Uses
     *   {@link #checkIfNetworkInConfigurationList(String)}
     *
     * @return boolean - If the SSID is in the list of configured networks
     */
    @Sync
    @CallingThread
    public boolean isNetworkInConfigurationList(String ssid) {
        return hasPrerequisites() && checkIfNetworkInConfigurationList(ssid);
    }

    /**
     * To check and return if a network is secure (WEP/PSK/EAP capabilities)
     *
     * @param scanResult - The network to see if it is secure
     *
     * Uses
     *   {@link Capabilities}
     *
     * @return boolean - Whether the network is secure
     */
    @Sync
    @CallingThread
    public boolean isNetworkSecure(ScanResult scanResult) {
        if (scanResult != null && scanResult.capabilities != null) {
            if (scanResult.capabilities.contains(Capabilities.WEP) || scanResult.capabilities.contains(Capabilities.PSK) || scanResult.capabilities.contains(Capabilities.EAP)) {
                return true;
            }
        }
        return false;
    }

    /**
     * To check if Wifi is enabled on the device or not
     *
     * @return boolean - if Wifi is enabled on device
     */
    @Sync
    @CallingThread
    public boolean isWifiEnabled() {
        return hasPrerequisites() && mWifiManager.isWifiEnabled();
    }

    /**
     * To remove a configured network
     *
     * @param ssidToRemove - The ssid of the network you want to remove from the configured network list
     *
     * Uses
     *   {@link #findNetworkInConfigurationList(String)}
     *
     * @return boolean - If the command succeeded in removing the network
     */
    @Sync
    @CallingThread
    public boolean removeNetwork(String ssidToRemove) {
        if (TextUtils.isEmpty(ssidToRemove) || !hasPrerequisites()) {
            return false;
        }

        WifiConfiguration wifiConfiguration = findNetworkInConfigurationList(ssidToRemove);
        if (wifiConfiguration != null) {
            mWifiManager.disconnect();
            boolean result = mWifiManager.removeNetwork(wifiConfiguration.networkId);
            if (LogUtil.isLoggable(TAG, Log.DEBUG, mLoggingEnabled)) {
                Log.d(TAG, String.format("Removing network: %s had result: %b", ssidToRemove, result));
            }
            mWifiManager.reconnect();
            return result;
        } else {
            if (LogUtil.isLoggable(TAG, Log.WARN, mLoggingEnabled)) {
                Log.w(TAG, String.format("SSID to remove: %s was not found in list to remove network", ssidToRemove));
            }
        }
        return false;
    }

    /**
     * To remove a configured network
     *
     * @param ssidToRemove - The ssid of the network you want to remove from the configured network list
     * @param removeNetworkCallback - The listener to return results to
     *
     * Uses
     *   {@link #execute(Runnable)}
     *   {@link #findNetworkInConfigurationList(String)}
     *   {@link RemoveNetworkCallbacks}
     *   {@link WiseFyHandlerThread}
     */
    @Async
    @WiseFyThread
    public void removeNetwork(final String ssidToRemove, final RemoveNetworkCallbacks removeNetworkCallback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (TextUtils.isEmpty(ssidToRemove)) {
                    if (removeNetworkCallback != null) {
                        removeNetworkCallback.removeNetworkWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
                    }
                    return;
                }

                if (!hasPrerequisites()) {
                    if (removeNetworkCallback != null) {
                        removeNetworkCallback.removeNetworkWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
                    }
                    return;
                }

                WifiConfiguration wifiConfiguration = findNetworkInConfigurationList(ssidToRemove);
                if (wifiConfiguration != null) {
                    mWifiManager.disconnect();
                    boolean result = mWifiManager.removeNetwork(wifiConfiguration.networkId);
                    if (LogUtil.isLoggable(TAG, Log.DEBUG, mLoggingEnabled)) {
                        Log.d(TAG, String.format("Removing network: %s had result: %b", ssidToRemove, result));
                    }
                    mWifiManager.reconnect();
                    if (result) {
                        if (removeNetworkCallback != null) {
                            removeNetworkCallback.networkRemoved();
                        }
                    } else {
                        if (removeNetworkCallback != null) {
                            removeNetworkCallback.failureRemovingNetwork();
                        }
                    }
                } else {
                    if (LogUtil.isLoggable(TAG, Log.WARN, mLoggingEnabled)) {
                        Log.w(TAG, String.format("SSID to remove: %s was not found in list to remove network", ssidToRemove));
                    }
                    if (removeNetworkCallback != null) {
                        removeNetworkCallback.networkNotFoundToRemove();
                    }
                }
                mWiseFyHandlerThread.quitSafely();
            }
        };
        execute(runnable);
    }

    /**
     * To search local networks and return the first one that contains a given ssid
     *
     * @param regexForSSID - The regex to be used to search for the ssid
     * @param timeoutInMillis - The number of milliseconds to keep searching for the SSID
     *
     * @return String|null - The first SSID that contains the search ssid (if any, else null)
     */
    @Sync
    @CallingThread
    @WaitsForTimeout
    public String searchForSSID(String regexForSSID, int timeoutInMillis) {
        if (TextUtils.isEmpty(regexForSSID) || !hasPrerequisites()) {
            return null;
        }

        int scanPass = 1;
        long currentTime;
        long endTime = System.currentTimeMillis() + timeoutInMillis;
        do {
            if (LogUtil.isLoggable(TAG, Log.DEBUG, mLoggingEnabled)) {
                Log.d(TAG, String.format("Scanning SSIDs, pass %d", scanPass));
            }
            mWifiManager.startScan();
            List<ScanResult> networks = mWifiManager.getScanResults();
            for (ScanResult scanResult : networks) {
                if (LogUtil.isLoggable(TAG, Log.DEBUG, mLoggingEnabled)) {
                    Log.d(TAG, String.format("scanResult.SSID: %s, regex for SSID: %s", scanResult.SSID, regexForSSID));
                }
                if (scanResult.SSID != null && (scanResult.SSID).matches(regexForSSID)) {
                    return scanResult.SSID;
                }
            }

            sleep(1000);

            currentTime = System.currentTimeMillis();
            if (LogUtil.isLoggable(TAG, Log.DEBUG, mLoggingEnabled)) {
                Log.d(TAG, String.format("Current time: %d / End time: %d (searchForSSID)", currentTime, endTime));
            }
            scanPass++;
        } while (currentTime < endTime);
        return null;
    }

    /**
     * To search local networks and return the first one that contains a given ssid
     *
     * @param regexForSSID - The regex to be used to search for the ssid
     * @param timeoutInMillis - The number of milliseconds to keep searching for the SSID
     * @param searchForSSIDCallback - The listener to return results to
     *
     * Uses
     *   {@link #execute(Runnable)}
     *   {@link SearchForSSIDCallbacks}
     *   {@link WiseFyHandlerThread}
     */
    @Async
    @WiseFyThread
    @WaitsForTimeout
    public void searchForSSID(final String regexForSSID, final int timeoutInMillis, final SearchForSSIDCallbacks searchForSSIDCallback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (TextUtils.isEmpty(regexForSSID)) {
                    if (searchForSSIDCallback != null) {
                        searchForSSIDCallback.searchForSSIDWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
                    }
                    return;
                }

                if (!hasPrerequisites()) {
                    if (searchForSSIDCallback != null) {
                        searchForSSIDCallback.searchForSSIDWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
                    }
                    return;
                }

                int scanPass = 1;
                long currentTime;
                long endTime = System.currentTimeMillis() + timeoutInMillis;
                do {
                    if (LogUtil.isLoggable(TAG, Log.DEBUG, mLoggingEnabled)) {
                        Log.d(TAG, String.format("Scanning SSIDs, pass %d", scanPass));
                    }
                    mWifiManager.startScan();
                    List<ScanResult> networks = mWifiManager.getScanResults();
                    for (ScanResult scanResult : networks) {
                        if (LogUtil.isLoggable(TAG, Log.DEBUG, mLoggingEnabled)) {
                            Log.d(TAG, String.format("scanResult.SSID: %s, regex for SSID: %s", scanResult.SSID, regexForSSID));
                        }
                        if (scanResult.SSID != null && (scanResult.SSID).matches(regexForSSID)) {
                            if (searchForSSIDCallback != null) {
                                searchForSSIDCallback.ssidFound(scanResult.SSID);
                            }
                            return;
                        }
                    }

                    sleep(1000);

                    currentTime = System.currentTimeMillis();
                    if (LogUtil.isLoggable(TAG, Log.DEBUG, mLoggingEnabled)) {
                        Log.d(TAG, String.format("Current time: %d / End time: %d (searchForSSID)", currentTime, endTime));
                    }
                    scanPass++;
                } while (currentTime < endTime);
                if (searchForSSIDCallback != null) {
                    searchForSSIDCallback.ssidNotFound();
                }
                mWiseFyHandlerThread.quitSafely();
            }
        };
        execute(runnable);
    }

    /*
     * HELPERS
     */

    /**
     * Used internally to add and save a new wifi configuration
     *
     * @param wifiConfiguration - The network to configuration to add
     *
     * Used by
     *   {@link #addOpenNetwork(String)}
     *   {@link #addOpenNetwork(String, AddOpenNetworkCallbacks)}
     *   {@link #addWEPNetwork(String, String)}
     *   {@link #addWEPNetwork(String, String, AddWEPNetworkCallbacks)}
     *   {@link #addWPA2Network(String, String)}
     *   {@link #addWPA2Network(String, String, AddWPA2NetworkCallbacks)}
     *
     * @return int - The return code from WifiManager for network creation (-1 for failure)
     */
    private int addNetwork(WifiConfiguration wifiConfiguration) {
        int result = mWifiManager.addNetwork(wifiConfiguration);
        if (result == WIFI_MANAGER_FAILURE) {
            if (LogUtil.isLoggable(TAG, Log.ERROR, mLoggingEnabled)) {
                Log.e(TAG, "Failed to add network");
            }
        }
        return result;
    }

    private boolean hasPrerequisites() {
        if (mWifiManager == null) {
            if (LogUtil.isLoggable(TAG, Log.ERROR, mLoggingEnabled)) {
                Log.e(TAG, "Missing mWifiManager");
            }
            return false;
        }
        if (mConnectivityManager == null) {
            if (LogUtil.isLoggable(TAG, Log.ERROR, mLoggingEnabled)) {
                Log.e(TAG, "Missing mConnectivityManager");
            }
            return false;
        }
        return true;
    }

    /**
     * Used internally to see if an ssid is already saved
     *
     * *NOTE* CASE SENSITIVE!
     *
     * @param ssid - The ssid to check for in the configured network list
     *
     * Used by
     *   {@link #addOpenNetwork(String)}
     *   {@link #addOpenNetwork(String, AddOpenNetworkCallbacks)}
     *   {@link #addWEPNetwork(String, String)}
     *   {@link #addWEPNetwork(String, String, AddWEPNetworkCallbacks)}
     *   {@link #addWPA2Network(String, String)}
     *   {@link #addWPA2Network(String, String, AddWPA2NetworkCallbacks)}
     *   {@link #isNetworkInConfigurationList(String)}
     *
     * Uses
     *   {@link #findNetworkInConfigurationList(String)}
     *
     * @return boolean - If the ssid was found in the configuration list
     */
    private boolean checkIfNetworkInConfigurationList(String ssid) {
        WifiConfiguration wifiConfiguration = findNetworkInConfigurationList(ssid);
        return wifiConfiguration != null;
    }

    /**
     * A method to execute logic on a background thread
     *
     * @param runnable - The block of code to execute in the background
     *
     * Used by
     *   {@link #addOpenNetwork(String, AddOpenNetworkCallbacks)}
     *   {@link #addWEPNetwork(String, String, AddWEPNetworkCallbacks)}
     *   {@link #addWPA2Network(String, String, AddWPA2NetworkCallbacks)}
     *   {@link #connectToNetwork(String, int, ConnectToNetworkCallbacks)}
     *   {@link #disableWifi(DisableWifiCallbacks)}
     *   {@link #disconnectFromCurrentNetwork(DisconnectFromCurrentNetworkCallbacks)}
     *   {@link #enableWifi(EnableWifiCallbacks)}
     *   {@link #getCurrentNetwork(GetCurrentNetworkCallbacks)}
     *   {@link #getFrequency(GetFrequencyCallbacks)}
     *   {@link #getFrequency(WifiInfo, GetFrequencyCallbacks)}
     *   {@link #getNearbyAccessPoints(boolean, GetNearbyAccessPointsCallbacks)}
     *   {@link #getSavedNetwork(String, GetSavedNetworkCallbacks)}
     *   {@link #getSavedNetworks(GetSavedNetworksCallbacks)}
     *   {@link #removeNetwork(String, RemoveNetworkCallbacks)}
     *   {@link #searchForSSID(String, int, SearchForSSIDCallbacks)}
     *
     * Uses
     *   {@link #setupWiseFyThread()}
     */
    private void execute(Runnable runnable) {
        if (mWiseFyHandler == null) {
            setupWiseFyThread();
        }
        mWiseFyHandler.post(runnable);
    }

    /**
     * Used internally to see if an ssid is already saved
     *
     * *NOTE* CASE SENSITIVE!
     *
     * @param ssid - The ssid to find in the configured network list
     *
     * Used by
     *   {@link #checkIfNetworkInConfigurationList(String)}
     *   {@link #getSavedNetwork(String)}
     *   {@link #getSavedNetwork(String, GetSavedNetworkCallbacks)}
     *   {@link #removeNetwork(String)}
     *   {@link #removeNetwork(String, RemoveNetworkCallbacks)}
     *
     * @return WiFiConfiguration|null - A matching configured network in the list
     * or null if no matching ones found
     */
    private WifiConfiguration findNetworkInConfigurationList(String ssid) {
        List<WifiConfiguration> list = mWifiManager.getConfiguredNetworks();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                WifiConfiguration wifiConfiguration = list.get(i);
                if (wifiConfiguration != null && wifiConfiguration.SSID != null) {
                    String ssidInList = wifiConfiguration.SSID.replaceAll("\"", "");
                    if (LogUtil.isLoggable(TAG, Log.DEBUG, mLoggingEnabled)) {
                        Log.d(TAG, String.format("SSID in list: %s, SSID: %s", ssidInList, ssid));
                    }
                    if (ssidInList.equals(ssid)) {
                        if (LogUtil.isLoggable(TAG, Log.DEBUG, mLoggingEnabled)) {
                            Log.d(TAG, "Found SSID in list");
                        }
                        return wifiConfiguration;
                    }
                }
            }
            if (LogUtil.isLoggable(TAG, Log.WARN, mLoggingEnabled)) {
                Log.w(TAG, String.format("Found 0 configured networks matching SSID %s", ssid));
            }
        } else {
            if (LogUtil.isLoggable(TAG, Log.WARN, mLoggingEnabled)) {
                Log.w(TAG, "Found 0 configured networks");
            }
        }
        return null;
    }

    /**
     *  Used internally to setup a WiseFyThread to run background operations
     *
     *  Used by
     *    {@link #execute(Runnable)}
     */
    private void setupWiseFyThread() {
        mWiseFyHandlerThread = new WiseFyHandlerThread(WiseFyHandlerThread.TAG, mLoggingEnabled);
        mWiseFyHandlerThread.start();
        Looper looper = mWiseFyHandlerThread.getLooper();
        mWiseFyHandler = new Handler(looper);
    }

    /**
     *
     */
    private void sleep(long timeToSleepInMillis) {
        try {
            Thread.sleep(timeToSleepInMillis);
        } catch (InterruptedException ie) {
            // Do nothing
        }
    }

    /**
     * Used internally to check if the device connects to a given SSID within a specified time
     *
     * *Notes*
     *   - Timeout is in millis
     *
     * @param ssid - The ssid to wait for the device to connect to
     * @param timeoutInMillis - The number of milliseconds to wait
     *
     * Used by
     *   {@link #connectToNetwork(String, int)}
     *   {@link #connectToNetwork(String, int, ConnectToNetworkCallbacks)}
     *
     * Uses
     *   {@link #isDeviceConnectedToSSID(String)}
     *
     * @return boolean - If the device is connected to the ssid in the given time
     */
    @WaitsForTimeout
    private boolean waitToConnectToSSID(String ssid, int timeoutInMillis) {
        long currentTime;
        long endTime = System.currentTimeMillis() + timeoutInMillis;
        do {
            boolean result = isDeviceConnectedToSSID(ssid);
            if (result) {
                return true;
            }
            sleep(1000);
            currentTime = System.currentTimeMillis();
            if (LogUtil.isLoggable(TAG, Log.DEBUG, mLoggingEnabled)) {
                Log.d(TAG, String.format("Current time: %d / End time: %d (waitToConnectToSSID)", currentTime, endTime));
            }
        } while (currentTime < endTime);
        return false;
    }
}