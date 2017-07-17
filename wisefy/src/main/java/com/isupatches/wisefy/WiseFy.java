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
import com.isupatches.wisefy.callbacks.GetRSSICallbacks;
import com.isupatches.wisefy.callbacks.GetSavedNetworkCallbacks;
import com.isupatches.wisefy.callbacks.GetSavedNetworksCallbacks;
import com.isupatches.wisefy.callbacks.RemoveNetworkCallbacks;
import com.isupatches.wisefy.callbacks.SearchForAccessPointCallbacks;
import com.isupatches.wisefy.callbacks.SearchForAccessPointsCallbacks;
import com.isupatches.wisefy.callbacks.SearchForSSIDCallbacks;
import com.isupatches.wisefy.callbacks.SearchForSSIDsCallbacks;
import com.isupatches.wisefy.constants.Capabilities;
import com.isupatches.wisefy.constants.NetworkTypes;
import com.isupatches.wisefy.constants.WiseFyCodes;
import com.isupatches.wisefy.threads.WiseFyHandlerThread;
import com.isupatches.wisefy.util.LogUtil;
import com.isupatches.wisefy.util.ManagerUtil;
import com.isupatches.wisefy.util.WifiConfigurationUtil;
import java.util.List;


/**
 * Main class for WiseFy that provides a synchronous and asynchronous API to manipulate and query
 * for different parts of a device's wifi configuration and status.
 *
 * @author Patches
 * @version 2.0
 *
 * Uses the builder pattern for creation - {@link brains}
 */
public class WiseFy {

    private static final String TAG = WiseFy.class.getSimpleName();

    public static final int WIFI_MANAGER_FAILURE = -1;

    public static final int MIN_FREQUENCY_5GHZ = 4900;

    public static final int MAX_FREQUENCY_5GHZ = 5900;

    private WiseFyHandlerThread mWiseFyHandlerThread;

    private Handler mWiseFyHandler;

    WiseFyConnection mWiseFyConnection;

    WiseFyConfiguration mWiseFyConfiguration;

    WiseFyPrerequisites mWiseFyPrerequisites;

    WiseFySearch mWiseFySearch;

    /**
     * Private constructor that accepts builder input
     */
    private WiseFy(brains brains) {
        this.mWiseFyConfiguration = WiseFyConfiguration.getInstance();
        this.mWiseFyConnection = WiseFyConnection.getInstance();
        this.mWiseFyPrerequisites = WiseFyPrerequisites.getInstance();
        this.mWiseFySearch = WiseFySearch.getInstance();
        mWiseFyConfiguration.setLoggingEnabled(brains.loggingEnabled);
        mWiseFyPrerequisites.setConnectivityManager(ManagerUtil.getInstance().getConnectivityManager(brains.context));
        mWiseFyPrerequisites.setWifiManager(ManagerUtil.getInstance().getWiFiManager(brains.context));
    }

    /**
     * Static class for builder pattern
     *
     * Implements builder interfaces #{@link Logging} #{@link GetSmarts}
     */
    public static class brains implements Logging, GetSmarts {

        private Context context;

        private boolean loggingEnabled;

        /**
         * Mandatory - The public constructor for the builder that requires a context
         *
         * @param context The activity or application context to get a WifiConfiguration and
         * ConnectivityManager instance
         */
        public brains(Context context) {
            this.context = context;
        }

        /**
         * Mandatory - To build and return a WiseFy instance
         *
         * Must be called after brains
         * {@link brains}
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
         * @param loggingEnabled If logging is enabled or disabled for an instance
         * {@link Logging}
         *
         * @return brains - The builder with updated logging setting
         */
        @Override
        public brains logging(boolean loggingEnabled) {
            this.loggingEnabled = loggingEnabled;
            return this;
        }
    }

    /**
     * An interface that enables/disables logging for a WiseFy instance
     */
    interface Logging {
        brains logging(boolean loggingEnabled);
    }

    /**
     * An interface that builds a WiseFy instance
     */
    interface GetSmarts {
        WiseFy getSmarts();
    }

    /**
     * To add an open network to the user's configured network list (synchronously)
     *
     * @param ssid The ssid of the open network you want to add
     *
     * @see #addNetworkConfiguration(WifiConfiguration)
     * @see WifiConfigurationUtil#generateOpenNetworkConfiguration(String)
     * @see WiseFyCodes
     * @see WiseFyConfiguration#isLoggingEnabled()
     * @see WiseFyPrerequisites#hasPrerequisites()
     * @see WiseFySearch#isNetworkASavedConfiguration(String)
     *
     * @return int - The return code from WifiManager for network creation (-1 for failure)
     */
    @Sync
    @CallingThread
    public int addOpenNetwork(String ssid) {
        if (TextUtils.isEmpty(ssid)) {
            return WiseFyCodes.MISSING_PARAMETER;
        }

        if (!mWiseFyPrerequisites.hasPrerequisites()) {
            return WiseFyCodes.MISSING_PREREQUISITE;
        }

        if (mWiseFySearch.isNetworkASavedConfiguration(ssid)) {
            return WiseFyCodes.NETWORK_ALREADY_CONFIGURED;
        }

        if (LogUtil.isLoggable(TAG, Log.DEBUG, mWiseFyConfiguration.isLoggingEnabled())) {
            Log.d(TAG, String.format("Adding open network with SSID %s", ssid));
        }
        WifiConfiguration wifiConfiguration = WifiConfigurationUtil.getInstance().generateOpenNetworkConfiguration(ssid);
        return addNetworkConfiguration(wifiConfiguration);
    }

    /**
     * To add an open network to the user's configured network list (asynchronously)
     *
     * @param ssid The ssid of the open network you want to add
     * @param callbacks The listener to return results to
     *
     * @see #addNetworkConfiguration(WifiConfiguration)
     * @see #execute(Runnable)
     * @see AddOpenNetworkCallbacks
     * @see WifiConfigurationUtil#generateOpenNetworkConfiguration(String)
     * @see WiseFyCodes
     * @see WiseFyConfiguration#isLoggingEnabled()
     * @see WiseFyPrerequisites#hasPrerequisites()
     * @see WiseFySearch#isNetworkASavedConfiguration(String)
     */
    @Async
    @WiseFyThread
    public void addOpenNetwork(final String ssid, final AddOpenNetworkCallbacks callbacks) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (TextUtils.isEmpty(ssid)) {
                    if (callbacks != null) {
                        callbacks.addOpenNetworkWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
                    }
                    return;
                }

                if (!mWiseFyPrerequisites.hasPrerequisites()) {
                    if (callbacks != null) {
                        callbacks.addOpenNetworkWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
                    }
                    return;
                }

                if (mWiseFySearch.isNetworkASavedConfiguration(ssid)) {
                    if (callbacks != null) {
                        callbacks.addOpenNetworkWiseFyFailure(WiseFyCodes.NETWORK_ALREADY_CONFIGURED);
                    }
                    return;
                }

                if (LogUtil.isLoggable(TAG, Log.DEBUG, mWiseFyConfiguration.isLoggingEnabled())) {
                    Log.d(TAG, String.format("Adding open network with SSID %s", ssid));
                }
                WifiConfiguration wifiConfiguration = WifiConfigurationUtil.getInstance().generateOpenNetworkConfiguration(ssid);
                if (callbacks != null) {
                    int result = addNetworkConfiguration(wifiConfiguration);
                    if (result != WIFI_MANAGER_FAILURE) {
                        callbacks.openNetworkAdded(wifiConfiguration);
                    } else {
                        callbacks.failureAddingOpenNetwork(result);
                    }
                }
            }
        };
        execute(runnable);
    }

    /**
     * To add a WEP network to the user's configured network list (synchronously)
     *
     * @param ssid The ssid of the WEP network you want to add
     * @param password The password for the WEP network being added
     *
     * @see #addNetworkConfiguration(WifiConfiguration)
     * @see WifiConfigurationUtil#generateWEPNetworkConfiguration(String, String)
     * @see WiseFyCodes
     * @see WiseFyConfiguration#isLoggingEnabled()
     * @see WiseFyPrerequisites#hasPrerequisites()
     * @see WiseFySearch#isNetworkASavedConfiguration(String)
     *
     * @return int - The return code from WifiManager for network creation (-1 for failure)
     */
    @Sync
    @CallingThread
    public int addWEPNetwork(String ssid, String password) {
        if (TextUtils.isEmpty(ssid) || TextUtils.isEmpty(password)) {
            return WiseFyCodes.MISSING_PARAMETER;
        }

        if (!mWiseFyPrerequisites.hasPrerequisites()) {
            return WiseFyCodes.MISSING_PREREQUISITE;
        }

        if (mWiseFySearch.isNetworkASavedConfiguration(ssid)) {
            return WiseFyCodes.NETWORK_ALREADY_CONFIGURED;
        }

        if (LogUtil.isLoggable(TAG, Log.DEBUG, mWiseFyConfiguration.isLoggingEnabled())) {
            Log.d(TAG, String.format("Adding WEP network with SSID %s", ssid));
        }
        WifiConfiguration wifiConfiguration = WifiConfigurationUtil.getInstance().generateWEPNetworkConfiguration(ssid, password);
        return addNetworkConfiguration(wifiConfiguration);
    }

    /**
     * To add a WEP network to the user's configured network list (asynchronously)
     *
     * @param ssid The ssid of the WEP network you want to add
     * @param password The password for the WEP network being added
     * @param callbacks The listener to return results to
     *
     * @see #addNetworkConfiguration(WifiConfiguration)
     * @see #execute(Runnable)
     * @see AddWEPNetworkCallbacks
     * @see WifiConfigurationUtil#generateWEPNetworkConfiguration(String, String)
     * @see WiseFyCodes
     * @see WiseFyConfiguration#isLoggingEnabled()
     * @see WiseFyPrerequisites#hasPrerequisites()
     * @see WiseFySearch#isNetworkASavedConfiguration(String)
     */
    @Async
    @WiseFyThread
    public void addWEPNetwork(final String ssid, final String password, final AddWEPNetworkCallbacks callbacks) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (TextUtils.isEmpty(ssid) || TextUtils.isEmpty(password)) {
                    if (callbacks != null) {
                        callbacks.addWEPNetworkWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
                    }
                    return;
                }

                if (!mWiseFyPrerequisites.hasPrerequisites()) {
                    if (callbacks != null) {
                        callbacks.addWEPNetworkWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
                    }
                    return;
                }

                if (mWiseFySearch.isNetworkASavedConfiguration(ssid)) {
                    if (callbacks != null) {
                        callbacks.addWEPNetworkWiseFyFailure(WiseFyCodes.NETWORK_ALREADY_CONFIGURED);
                    }
                    return;
                }

                if (LogUtil.isLoggable(TAG, Log.DEBUG, mWiseFyConfiguration.isLoggingEnabled())) {
                    Log.d(TAG, String.format("Adding WEP network with SSID %s", ssid));
                }
                WifiConfiguration wifiConfiguration = WifiConfigurationUtil.getInstance().generateWEPNetworkConfiguration(ssid, password);
                if (callbacks != null) {
                    int result = addNetworkConfiguration(wifiConfiguration);
                    if (result != WIFI_MANAGER_FAILURE) {
                        callbacks.wepNetworkAdded(wifiConfiguration);
                    } else {
                        callbacks.failureAddingWEPNetwork(result);
                    }
                }
            }
        };
        execute(runnable);
    }

    /**
     * To add a WPA2 network to the user's configured network list (synchronously)
     *
     * @param ssid The ssid of the WPA2 network you want to add
     * @param password The password for the WPA2 network being added
     *
     * @see #addNetworkConfiguration(WifiConfiguration)
     * @see WifiConfigurationUtil#generateWPA2NetworkConfiguration(String, String)
     * @see WiseFyCodes
     * @see WiseFyConfiguration#isLoggingEnabled()
     * @see WiseFyPrerequisites#hasPrerequisites()
     * @see WiseFySearch#isNetworkASavedConfiguration(String)
     *
     * @return int - The return code from WifiManager for network creation (-1 for failure)
     */
    @Sync
    @CallingThread
    public int addWPA2Network(String ssid, String password) {
        if (TextUtils.isEmpty(ssid) || TextUtils.isEmpty(password)) {
            return WiseFyCodes.MISSING_PARAMETER;
        }

        if (!mWiseFyPrerequisites.hasPrerequisites()) {
            return WiseFyCodes.MISSING_PREREQUISITE;
        }

        if (mWiseFySearch.isNetworkASavedConfiguration(ssid)) {
            return WiseFyCodes.NETWORK_ALREADY_CONFIGURED;
        }

        if (LogUtil.isLoggable(TAG, Log.DEBUG, mWiseFyConfiguration.isLoggingEnabled())) {
            Log.d(TAG, String.format("Adding WPA2 network with SSID %s", ssid));
        }
        WifiConfiguration wifiConfiguration = WifiConfigurationUtil.getInstance().generateWPA2NetworkConfiguration(ssid, password);
        return addNetworkConfiguration(wifiConfiguration);
    }

    /**
     * To add a WPA2 network to the user's configured network list (asynchronously)
     *
     * @param ssid The ssid of the WPA2 network you want to add
     * @param password The password for the WPA2 network being added
     * @param callbacks The listener to return results to
     *
     * @see #addNetworkConfiguration(WifiConfiguration)
     * @see #execute(Runnable)
     * @see AddWPA2NetworkCallbacks
     * @see WifiConfigurationUtil#generateWPA2NetworkConfiguration(String, String)
     * @see WiseFyCodes
     * @see WiseFyConfiguration#isLoggingEnabled()
     * @see WiseFyPrerequisites#hasPrerequisites()
     * @see WiseFySearch#isNetworkASavedConfiguration(String)
     */
    @Async
    @WiseFyThread
    public void addWPA2Network(final String ssid, final String password, final AddWPA2NetworkCallbacks callbacks) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (TextUtils.isEmpty(ssid) || TextUtils.isEmpty(password)) {
                    if (callbacks != null) {
                        callbacks.addWPA2NetworkWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
                    }
                    return;
                }

                if (!mWiseFyPrerequisites.hasPrerequisites()) {
                    if (callbacks != null) {
                        callbacks.addWPA2NetworkWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
                    }
                    return;
                }

                if (mWiseFySearch.isNetworkASavedConfiguration(ssid)) {
                    if (callbacks != null) {
                        callbacks.addWPA2NetworkWiseFyFailure(WiseFyCodes.NETWORK_ALREADY_CONFIGURED);
                    }
                    return;
                }

                if (LogUtil.isLoggable(TAG, Log.DEBUG, mWiseFyConfiguration.isLoggingEnabled())) {
                    Log.d(TAG, String.format("Adding WPA2 network with SSID %s", ssid));
                }
                WifiConfiguration wifiConfiguration = WifiConfigurationUtil.getInstance().generateWPA2NetworkConfiguration(ssid, password);
                if (callbacks != null) {
                    int result = addNetworkConfiguration(wifiConfiguration);
                    if (result != WIFI_MANAGER_FAILURE) {
                        callbacks.wpa2NetworkAdded(wifiConfiguration);
                    } else {
                        callbacks.failureAddingWPA2Network(result);
                    }
                }
            }
        };
        execute(runnable);
    }

    /**
     * To convert an RSSI level for a network to a number of bars
     *
     * @param rssiLevel The signal strength of the network
     * @param targetNumberOfBars How many bars or levels there will be total
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
     * @param rssi1 The signal strength of network 1
     * @param rssi2 The signal strength of network 2
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
     * Used to connect to a network (synchronously)
     *
     * Gets a list of saved networks, connects to the given ssid if found, and verifies connectivity
     *
     * @param ssidToConnectTo The ssid to connect/reconnect to
     * @param timeoutInMillis The number of milliseconds to continue waiting for the device to connect to the given SSID
     *
     * @see WiseFyConnection#waitToConnectToSSID(String, int)
     * @see WiseFyPrerequisites#hasPrerequisites()
     * @see WiseFySearch#findSavedNetworkByRegex(String)
     *
     * @return boolean - If the network was successfully reconnected
     */
    @Sync
    @CallingThread
    @WaitsForTimeout
    public boolean connectToNetwork(String ssidToConnectTo, int timeoutInMillis) {
        if (TextUtils.isEmpty(ssidToConnectTo) || !mWiseFyPrerequisites.hasPrerequisites()) {
            return false;
        }

        if (LogUtil.isLoggable(TAG, Log.DEBUG, mWiseFyConfiguration.isLoggingEnabled())) {
            Log.d(TAG, String.format("Waiting %d to connect to network with ssid %s", timeoutInMillis, ssidToConnectTo));
        }

        WifiConfiguration wifiConfiguration = mWiseFySearch.findSavedNetworkByRegex(ssidToConnectTo);
        if (wifiConfiguration != null) {
            mWiseFyPrerequisites.getWifiManager().disconnect();
            mWiseFyPrerequisites.getWifiManager().enableNetwork(wifiConfiguration.networkId, true);
            mWiseFyPrerequisites.getWifiManager().reconnect();
            return mWiseFyConnection.waitToConnectToSSID(ssidToConnectTo, timeoutInMillis);
        }

        return false;
    }

    /**
     * Used to connect to a network (asynchronously)
     *
     * Gets a list of saved networks, connects to the given ssid if found, and verifies connectivity
     *
     * @param ssidToConnectTo The ssid to connect/reconnect to
     * @param timeoutInMillis The number of milliseconds to continue waiting for the device to connect to the given SSID
     * @param callbacks The listener to return results to
     *
     * @see #execute(Runnable)
     * @see ConnectToNetworkCallbacks
     * @see WiseFyCodes
     * @see WiseFyConnection#waitToConnectToSSID(String, int)
     * @see WiseFyPrerequisites#hasPrerequisites()
     * @see WiseFySearch#findSavedNetworkByRegex(String)
     */
    @Async
    @WiseFyThread
    @WaitsForTimeout
    public void connectToNetwork(final String ssidToConnectTo, final int timeoutInMillis, final ConnectToNetworkCallbacks callbacks) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (TextUtils.isEmpty(ssidToConnectTo)) {
                    if (callbacks != null) {
                        callbacks.connectToNetworkWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
                    }
                    return;
                }

                if (!mWiseFyPrerequisites.hasPrerequisites()) {
                    if (callbacks != null) {
                        callbacks.connectToNetworkWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
                    }
                    return;
                }

                if (LogUtil.isLoggable(TAG, Log.DEBUG, mWiseFyConfiguration.isLoggingEnabled())) {
                    Log.d(TAG, String.format("Waiting %d to connect to network with ssid %s", timeoutInMillis, ssidToConnectTo));
                }

                WifiConfiguration wifiConfiguration = mWiseFySearch.findSavedNetworkByRegex(ssidToConnectTo);
                if (wifiConfiguration != null) {
                    mWiseFyPrerequisites.getWifiManager().disconnect();
                    mWiseFyPrerequisites.getWifiManager().enableNetwork(wifiConfiguration.networkId, true);
                    mWiseFyPrerequisites.getWifiManager().reconnect();
                    boolean connected = mWiseFyConnection.waitToConnectToSSID(ssidToConnectTo, timeoutInMillis);
                    if (callbacks != null) {
                        if (connected) {
                            callbacks.connectedToNetwork();
                        } else {
                            callbacks.failureConnectingToNetwork();
                        }
                    }
                    return;
                }

                if (callbacks != null) {
                    callbacks.networkNotFoundToConnectTo();
                }
            }
        };
        execute(runnable);
    }

    /**
     * To disable Wifi on a user's device (synchronously)
     *
     * @see WiseFyPrerequisites#hasPrerequisites()
     *
     * @return boolean - True if the command succeeded in disabling wifi
     */
    @Sync
    @CallingThread
    public boolean disableWifi() {
        return mWiseFyPrerequisites.hasPrerequisites() && mWiseFyPrerequisites.getWifiManager().setWifiEnabled(false);
    }

    /**
     * To disable Wifi on a user's device (asynchronously)
     *
     * @param callbacks The listener to return results to
     *
     * @see #execute(Runnable)
     * @see DisableWifiCallbacks
     * @see WiseFyCodes
     * @see WiseFyPrerequisites#hasPrerequisites()
     */
    @Async
    @WiseFyThread
    public void disableWifi(final DisableWifiCallbacks callbacks) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (!mWiseFyPrerequisites.hasPrerequisites()) {
                    if (callbacks != null) {
                        callbacks.disableWifiWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
                    }
                    return;
                }

                boolean result = mWiseFyPrerequisites.getWifiManager().setWifiEnabled(false);
                if (callbacks != null) {
                    if (result) {
                        callbacks.wifiDisabled();
                    } else {
                        callbacks.failureDisablingWifi();
                    }
                }
            }
        };
        execute(runnable);
    }

    /**
     * To disconnect the user from their current network (synchronously)
     *
     * @see WiseFyPrerequisites#hasPrerequisites()
     *
     * @return boolean - If the command succeeded in disconnecting the device from the current network
     */
    @Sync
    @CallingThread
    public boolean disconnectFromCurrentNetwork() {
        return mWiseFyPrerequisites.hasPrerequisites() && mWiseFyPrerequisites.getWifiManager().disconnect();
    }

    /**
     * To disconnect the user from their current network (asynchronously)
     *
     * @param callbacks The listener to return results to
     *
     * @see #execute(Runnable)
     * @see DisconnectFromCurrentNetworkCallbacks
     * @see WiseFyCodes
     * @see WiseFyPrerequisites#hasPrerequisites()
     */
    @Async
    @WiseFyThread
    public void disconnectFromCurrentNetwork(final DisconnectFromCurrentNetworkCallbacks callbacks) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (!mWiseFyPrerequisites.hasPrerequisites()) {
                    if (callbacks != null) {
                        callbacks.disconnectFromCurrentNetworkWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
                    }
                    return;
                }

                boolean result = mWiseFyPrerequisites.getWifiManager().disconnect();
                if (callbacks != null) {
                    if (result) {
                        callbacks.disconnectedFromCurrentNetwork();
                    } else {
                        callbacks.failureDisconnectingFromCurrentNetwork();
                    }
                }
            }
        };
        execute(runnable);
    }

    /**
     * Used to cleanup the thread started by WiseFy
     */
    public void dump() {
        if (mWiseFyHandlerThread != null) {
            mWiseFyHandlerThread.interrupt();
            mWiseFyHandlerThread.quit();
            mWiseFyHandlerThread = null;
            if (LogUtil.isLoggable(TAG, Log.DEBUG, mWiseFyConfiguration.isLoggingEnabled())) {
                Log.d(TAG, String.format("Cleaning up WiseFy Thread. Thread value: %s, Handler value: %s", mWiseFyHandlerThread, mWiseFyHandler));
            }
        }
        mWiseFyHandler = null;
    }

    /**
     * To enable Wifi on a user's device
     *
     * @see WiseFyPrerequisites#hasPrerequisites()
     *
     * @return boolean - If the command succeeded in enabling wifi
     */
    @Sync
    @CallingThread
    public boolean enableWifi() {
        return mWiseFyPrerequisites.hasPrerequisites() && mWiseFyPrerequisites.getWifiManager().setWifiEnabled(true);
    }

    /**
     * To enable Wifi on a user's device
     *
     * @param callbacks The listener to return results to
     *
     * @see #execute(Runnable)
     * @see EnableWifiCallbacks
     * @see WiseFyCodes
     * @see WiseFyPrerequisites#hasPrerequisites()
     */
    @Async
    @WiseFyThread
    public void enableWifi(final EnableWifiCallbacks callbacks) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (!mWiseFyPrerequisites.hasPrerequisites()) {
                    if (callbacks != null) {
                        callbacks.enableWifiWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
                    }
                    return;
                }

                boolean result = mWiseFyPrerequisites.getWifiManager().setWifiEnabled(true);
                if (callbacks != null) {
                    if (result) {
                        callbacks.wifiEnabled();
                    } else {
                        callbacks.failureEnablingWifi();
                    }
                }
            }
        };
        execute(runnable);
    }

    /**
     * To retrieve the user's current network
     *
     * @see WiseFyPrerequisites#hasPrerequisites()
     *
     * @return WifiInfo|null - The user's current network information
     */
    @Sync
    @CallingThread
    public WifiInfo getCurrentNetwork() {
        if (!mWiseFyPrerequisites.hasPrerequisites()) {
            return null;
        }
        return mWiseFyPrerequisites.getWifiManager().getConnectionInfo();
    }

    /**
     * To retrieve the user's current network
     *
     * @param callbacks The listener to return results to
     *
     * @see #execute(Runnable)
     * @see GetCurrentNetworkCallbacks
     * @see WiseFyCodes
     * @see WiseFyPrerequisites#hasPrerequisites()
     */
    @Async
    @WiseFyThread
    public void getCurrentNetwork(final GetCurrentNetworkCallbacks callbacks) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (!mWiseFyPrerequisites.hasPrerequisites()) {
                    if (callbacks != null) {
                        callbacks.getCurrentNetworkWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
                    }
                    return;
                }

                if (callbacks != null) {
                    callbacks.retrievedCurrentNetwork(mWiseFyPrerequisites.getWifiManager().getConnectionInfo());
                }
            }
        };
        execute(runnable);
    }

    /**
     * To retrieve the frequency of the device's current network
     *
     * @see #getCurrentNetwork()
     *
     * @return Integer - The frequency of the devices current network or null if no network
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
     * @param callbacks The listener to return results to
     *
     * @see #execute(Runnable)
     * @see #getCurrentNetwork(GetCurrentNetworkCallbacks)
     * @see GetFrequencyCallbacks
     */
    @Async
    @WiseFyThread
    @TargetApi(21)
    public void getFrequency(final GetFrequencyCallbacks callbacks) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                WifiInfo currentNetwork = getCurrentNetwork();
                if (callbacks != null) {
                    if (currentNetwork != null) {
                        callbacks.retrievedFrequency(currentNetwork.getFrequency());
                    } else {
                        callbacks.failureGettingFrequency();
                    }
                }
            }
        };
        execute(runnable);
    }

    /**
     * To retrieve the frequency of a network
     *
     * @param network The network to return the frequency of
     *
     * @return Integer - The frequency of the devices current network or null if no network
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
     * @param network The network to return the frequency of
     * @param callbacks The listener to return results to
     *
     * @see #execute(Runnable)
     * @see GetFrequencyCallbacks
     * @see WiseFyCodes
     */
    @Async
    @WiseFyThread
    @TargetApi(21)
    public void getFrequency(final WifiInfo network, final GetFrequencyCallbacks callbacks) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (network != null) {
                    if (callbacks != null) {
                        callbacks.retrievedFrequency(network.getFrequency());
                    }
                }
                if (callbacks != null) {
                    callbacks.getFrequencyWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
                }
            }
        };
        execute(runnable);
    }

    /**
     * To retrieve a list of nearby access points
     *
     * *NOTE* Setting filterDuplicates to true will not return SSIDs with a weaker signal strength (will always take the highest)
     *
     * @param filterDuplicates If you want to exclude SSIDs with that same name that have a weaker signal strength
     *
     * @see WiseFyPrerequisites#hasPrerequisites()
     * @see WiseFySearch#removeEntriesWithLowerSignalStrength(List)
     *
     * @return List of ScanResults|null - List of nearby access points
     */
    @Sync
    @CallingThread
    public List<ScanResult> getNearbyAccessPoints(boolean filterDuplicates) {
        if (!mWiseFyPrerequisites.hasPrerequisites()) {
            return null;
        }

        mWiseFyPrerequisites.getWifiManager().startScan();
        if (filterDuplicates) {
            return mWiseFySearch.removeEntriesWithLowerSignalStrength(mWiseFyPrerequisites.getWifiManager().getScanResults());
        } else {
            return mWiseFyPrerequisites.getWifiManager().getScanResults();
        }
    }

    /**
     * To retrieve a list of nearby access points
     *
     * *NOTE* Setting filterDuplicates to true will not return SSIDs with a weaker signal strength (will always take the highest)
     *
     * @param filterDuplicates If you want to exclude SSIDs with that same name that have a weaker signal strength
     * @param callbacks The listener to return results to
     *
     * @see #execute(Runnable)
     * @see GetNearbyAccessPointsCallbacks
     * @see WiseFyCodes
     * @see WiseFyPrerequisites#hasPrerequisites()
     * @see WiseFySearch#removeEntriesWithLowerSignalStrength(List)
     */
    @Async
    @WiseFyThread
    public void getNearbyAccessPoints(final boolean filterDuplicates, final GetNearbyAccessPointsCallbacks callbacks) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (!mWiseFyPrerequisites.hasPrerequisites()) {
                    if (callbacks != null) {
                        callbacks.getNearbyAccessPointsWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
                    }
                    return;
                }

                mWiseFyPrerequisites.getWifiManager().startScan();
                if (callbacks != null) {
                    if (filterDuplicates) {
                        callbacks.retrievedNearbyAccessPoints(mWiseFySearch.removeEntriesWithLowerSignalStrength(mWiseFyPrerequisites.getWifiManager().getScanResults()));
                    } else {
                        callbacks.retrievedNearbyAccessPoints(mWiseFyPrerequisites.getWifiManager().getScanResults());
                    }
                }
            }
        };
        execute(runnable);
    }

    @Sync
    @CallingThread
    @WaitsForTimeout
    public Integer getRSSI(String regexForSSID, boolean takeHighest, int timeoutInMillis) {
        if (TextUtils.isEmpty(regexForSSID) || !mWiseFyPrerequisites.hasPrerequisites()) {
            return null;
        }

        ScanResult accessPoint = mWiseFySearch.findAccessPointByRegex(regexForSSID, timeoutInMillis, takeHighest);
        if (accessPoint != null) {
            return accessPoint.level;
        }
        return null;
    }

    @Async
    @WiseFyThread
    @WaitsForTimeout
    public void getRSSI(final String regexForSSID, final boolean takeHighest, final int timeoutInMillis, final GetRSSICallbacks callbacks) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (TextUtils.isEmpty(regexForSSID)) {
                    if (callbacks != null) {
                        callbacks.getRSSIWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
                    }
                    return;
                }

                if (!mWiseFyPrerequisites.hasPrerequisites()) {
                    if (callbacks != null) {
                        callbacks.getRSSIWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
                    }
                    return;
                }

                ScanResult accessPoint = mWiseFySearch.findAccessPointByRegex(regexForSSID, timeoutInMillis, takeHighest);
                if (callbacks != null) {
                    if (accessPoint != null) {
                        callbacks.retrievedRSSI(accessPoint.level);
                    } else {
                        callbacks.networkNotFoundToRetrieveRSSI();
                    }
                }
            }
        };
        execute(runnable);
    }

    /**
     * To search for and return a saved WiFiConfiguration given an SSID
     *
     * @param regexForSSID The ssid to use while searching for saved configuration
     *
     * @see WiseFyPrerequisites#hasPrerequisites()
     * @see WiseFySearch#findSavedNetworkByRegex(String)
     *
     * @return WifiConfiguration|null - Saved network that matches the ssid
     */
    @Sync
    @CallingThread
    public WifiConfiguration getSavedNetwork(String regexForSSID) {
        if (!mWiseFyPrerequisites.hasPrerequisites() || TextUtils.isEmpty(regexForSSID)) {
            return null;
        }
        return mWiseFySearch.findSavedNetworkByRegex(regexForSSID);
    }

    /**
     * To search for and return a saved WiFiConfiguration given an SSID
     *
     * @param regexForSSID The ssid to use while searching for saved configuration
     * @param callbacks The listener to return results to
     *
     * @see #execute(Runnable)
     * @see GetSavedNetworkCallbacks
     * @see WiseFyCodes
     * @see WiseFyPrerequisites#hasPrerequisites()
     * @see WiseFySearch#findSavedNetworkByRegex(String)
     */
    @Async
    @WiseFyThread
    public void getSavedNetwork(final String regexForSSID, final GetSavedNetworkCallbacks callbacks) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (TextUtils.isEmpty(regexForSSID)) {
                    if (callbacks != null) {
                        callbacks.getSavedNetworkWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
                    }
                    return;
                }

                if (!mWiseFyPrerequisites.hasPrerequisites()) {
                    if (callbacks != null) {
                        callbacks.getSavedNetworkWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
                    }
                    return;
                }

                WifiConfiguration savedNetwork = mWiseFySearch.findSavedNetworkByRegex(regexForSSID);
                if (callbacks != null) {
                    if (savedNetwork != null) {
                        callbacks.retrievedSavedNetwork(savedNetwork);
                    } else {
                        callbacks.savedNetworkNotFound();
                    }
                }
            }
        };
        execute(runnable);
    }

    /**
     * To retrieve a list of saved networks on a user's device
     *
     * @see WiseFyPrerequisites#hasPrerequisites()
     *
     * @return List of WifiConfiguration|null - List of saved networks on a users device
     */
    @Sync
    @CallingThread
    public List<WifiConfiguration> getSavedNetworks() {
        if (!mWiseFyPrerequisites.hasPrerequisites()) {
            return null;
        }
        return mWiseFyPrerequisites.getWifiManager().getConfiguredNetworks();
    }

    /**
     * To retrieve a list of saved networks on a user's device
     *
     * @param callbacks The listener to return results to
     *
     * @see #execute(Runnable)
     * @see GetSavedNetworksCallbacks
     * @see WiseFyCodes
     * @see WiseFyPrerequisites#hasPrerequisites()
     */
    @Async
    @WiseFyThread
    public void getSavedNetworks(final GetSavedNetworksCallbacks callbacks) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (!mWiseFyPrerequisites.hasPrerequisites()) {
                    if (callbacks != null) {
                        callbacks.getSavedNetworksWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
                    }
                    return;
                }

                if (callbacks != null) {
                    callbacks.retrievedSavedNetworks(mWiseFyPrerequisites.getWifiManager().getConfiguredNetworks());
                }
            }
        };
        execute(runnable);
    }

    @Sync
    @CallingThread
    public List<WifiConfiguration> getSavedNetworks(String regexForSSID) {
        if (!mWiseFyPrerequisites.hasPrerequisites()) {
            return null;
        }
        return mWiseFySearch.findSavedNetworksMatchingRegex(regexForSSID);
    }

    @Async
    @WiseFyThread
    public void getSavedNetworks(final String regexForSSID, final GetSavedNetworksCallbacks callbacks) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (!mWiseFyPrerequisites.hasPrerequisites()) {
                    if (callbacks != null) {
                        callbacks.getSavedNetworksWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
                    }
                    return;
                }

                if (callbacks != null) {
                    callbacks.retrievedSavedNetworks(mWiseFySearch.findSavedNetworksMatchingRegex(regexForSSID));
                }
            }
        };
        execute(runnable);
    }

    /**
     * To check if the device is connected to a mobile network
     *
     * @see NetworkTypes
     * @see WiseFyConnection#isNetworkConnectedAndMatchesType(NetworkInfo, String)
     * @see WiseFyPrerequisites#hasPrerequisites()
     *
     * @return bool - If the device is currently connected to a mobile network
     */
    @Sync
    @CallingThread
    public boolean isDeviceConnectedToMobileNetwork() {
        if (!mWiseFyPrerequisites.hasPrerequisites()) {
            return false;
        }

        NetworkInfo networkInfo = mWiseFyPrerequisites.getConnectivityManager().getActiveNetworkInfo();
        return mWiseFyConnection.isNetworkConnectedAndMatchesType(networkInfo, NetworkTypes.MOBILE);
    }

    /**
     * To check if the device is connected to a mobile or wifi network
     *
     * @see WiseFyConnection#isNetworkConnected(NetworkInfo)
     * @see WiseFyPrerequisites#hasPrerequisites()
     *
     * @return bool - If the device is currently connected to a mobile or wifi network
     */
    @Sync
    @CallingThread
    public boolean isDeviceConnectedToMobileOrWifiNetwork() {
        if (!mWiseFyPrerequisites.hasPrerequisites()) {
            return false;
        }

        NetworkInfo networkInfo = mWiseFyPrerequisites.getConnectivityManager().getActiveNetworkInfo();
        return mWiseFyConnection.isNetworkConnected(networkInfo);
    }

    /**
     * To check if the device is connected to a given SSID
     *
     * Used by connectToNetwork
     *
     * @param ssid The SSID to check if the device is attached to
     *
     * @see WiseFyConnection#isCurrentNetworkConnectedToSSID(String)
     *
     * @return bool - If the device is currently attached to the given SSID
     */
    @Sync
    @CallingThread
    public boolean isDeviceConnectedToSSID(String ssid) {
        return !(TextUtils.isEmpty(ssid) || !mWiseFyPrerequisites.hasPrerequisites()) && mWiseFyConnection.isCurrentNetworkConnectedToSSID(ssid);
    }

    /**
     * To check if the device is connected to a wifi network
     *
     * @see NetworkTypes
     * @see WiseFyPrerequisites#hasPrerequisites()
     * @see WiseFyConnection#isNetworkConnectedAndMatchesType(NetworkInfo, String)
     *
     * @return bool - If the device is currently connected to a wifi network
     */
    @Sync
    @CallingThread
    public boolean isDeviceConnectedToWifiNetwork() {
        if (!mWiseFyPrerequisites.hasPrerequisites()) {
            return false;
        }

        NetworkInfo networkInfo = mWiseFyPrerequisites.getConnectivityManager().getActiveNetworkInfo();
        return mWiseFyConnection.isNetworkConnectedAndMatchesType(networkInfo, NetworkTypes.WIFI);
    }

    /**
     * To query if logging is enabled or disabled for a WiseFy instance
     *
     * @return boolean - If logging is enabled for the WiseFy instance
     */
    @Sync
    @CallingThread
    public boolean isLoggingEnabled() {
        return mWiseFyConfiguration.isLoggingEnabled();
    }

    /**
     * To check if the device's current network is 5gHz
     *
     * @see #getFrequency()
     * @see #MIN_FREQUENCY_5GHZ
     * @see #MAX_FREQUENCY_5GHZ
     *
     * @return boolean - If the network is 5gHz
     */
    @Sync
    @CallingThread
    public boolean isNetwork5gHz() {
        Integer frequency = getFrequency();
        return frequency != null && frequency > MIN_FREQUENCY_5GHZ && frequency < MAX_FREQUENCY_5GHZ;
    }

    /**
     * To check if a given network is 5gHz
     *
     * @param network The network to check if it's 5gHz
     *
     * @see #getFrequency(WifiInfo)
     * @see #MIN_FREQUENCY_5GHZ
     * @see #MAX_FREQUENCY_5GHZ
     *
     * @return boolean - If the network is 5gHz
     */
    @Sync
    @CallingThread
    public boolean isNetwork5gHz(WifiInfo network) {
        Integer frequency = getFrequency(network);
        return frequency != null && frequency > MIN_FREQUENCY_5GHZ && frequency < MAX_FREQUENCY_5GHZ;
    }

    /**
     * To check if an SSID is in the list of configured networks
     *
     * @param ssid The SSID to check and see if it's in the list of configured networks
     *
     * @see WiseFyPrerequisites#hasPrerequisites()
     * @see WiseFySearch#isNetworkASavedConfiguration(String)
     *
     * @return boolean - If the SSID is in the list of configured networks
     */
    @Sync
    @CallingThread
    public boolean isNetworkInConfigurationList(String ssid) {
        return mWiseFyPrerequisites.hasPrerequisites() && mWiseFySearch.isNetworkASavedConfiguration(ssid);
    }

    /**
     * To check and return if a network is a EAP network
     *
     * @param scanResult The network to check
     *
     * @see Capabilities
     *
     * @return boolean - Whether the network has EAP capabilities listed
     */
    @Sync
    @CallingThread
    public boolean isNetworkEAP(ScanResult scanResult) {
        return containsCapability(scanResult, Capabilities.EAP);
    }

    /**
     * To check and return if a network is a PSK network
     *
     * @param scanResult The network to check
     *
     * @see Capabilities
     *
     * @return boolean - Whether the network has PSK capabilities listed
     */
    @Sync
    @CallingThread
    public boolean isNetworkPSK(ScanResult scanResult) {
        return containsCapability(scanResult, Capabilities.PSK);
    }

    /**
     * To check and return if a network is secure (WEP/WPA/WPA2 capabilities)
     *
     * @param scanResult The network to see if it is secure
     *
     * @see Capabilities
     *
     * @return boolean - Whether the network is secure
     */
    @Sync
    @CallingThread
    public boolean isNetworkSecure(ScanResult scanResult) {
        if (scanResult != null && scanResult.capabilities != null) {
            String networkCapabilities = scanResult.capabilities;
            String[] securityModes = { Capabilities.EAP, Capabilities.PSK, Capabilities.WEP, Capabilities.WPA, Capabilities.WPA2 };
            for (int i = securityModes.length - 1; i >= 0; i--) {
                if (networkCapabilities.contains(securityModes[i])) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * To check and return if a network is a WEP network
     *
     * @param scanResult The network to check
     *
     * @see Capabilities
     *
     * @return boolean - Whether the network has WEP capabilities listed
     */
    @Sync
    @CallingThread
    public boolean isNetworkWEP(ScanResult scanResult) {
        return containsCapability(scanResult, Capabilities.WEP);
    }

    /**
     * To check and return if a network is a WPA network
     *
     * @param scanResult The network to check
     *
     * @see Capabilities
     *
     * @return boolean - Whether the network has WPA capabilities listed
     */
    @Sync
    @CallingThread
    public boolean isNetworkWPA(ScanResult scanResult) {
        return containsCapability(scanResult, Capabilities.WPA);
    }

    /**
     * To check and return if a network is a WPA2 network
     *
     * @param scanResult The network to check
     *
     * @see Capabilities
     *
     * @return boolean - Whether the network has WPA2 capabilities listed
     */
    @Sync
    @CallingThread
    public boolean isNetworkWPA2(ScanResult scanResult) {
        return containsCapability(scanResult, Capabilities.WPA2);
    }

    /**
     * To check if Wifi is enabled on the device or not
     *
     * @see WiseFyPrerequisites#hasPrerequisites()
     *
     * @return boolean - if Wifi is enabled on device
     */
    @Sync
    @CallingThread
    public boolean isWifiEnabled() {
        return mWiseFyPrerequisites.hasPrerequisites() && mWiseFyPrerequisites.getWifiManager().isWifiEnabled();
    }

    /**
     * To remove a configured network
     *
     * @param ssidToRemove The ssid of the network you want to remove from the configured network list
     *
     * @see WiseFyPrerequisites#hasPrerequisites()
     * @see WiseFySearch#findSavedNetworkByRegex(String)
     *
     * @return boolean - If the command succeeded in removing the network
     */
    @Sync
    @CallingThread
    public boolean removeNetwork(String ssidToRemove) {
        if (TextUtils.isEmpty(ssidToRemove) || !mWiseFyPrerequisites.hasPrerequisites()) {
            return false;
        }

        WifiConfiguration wifiConfiguration = mWiseFySearch.findSavedNetworkByRegex(ssidToRemove);
        if (wifiConfiguration != null) {
            mWiseFyPrerequisites.getWifiManager().disconnect();
            boolean result = mWiseFyPrerequisites.getWifiManager().removeNetwork(wifiConfiguration.networkId);
            if (LogUtil.isLoggable(TAG, Log.DEBUG, mWiseFyConfiguration.isLoggingEnabled())) {
                Log.d(TAG, String.format("Removing network: %s had result: %b", ssidToRemove, result));
            }
            mWiseFyPrerequisites.getWifiManager().reconnect();
            return result;
        } else {
            if (LogUtil.isLoggable(TAG, Log.WARN, mWiseFyConfiguration.isLoggingEnabled())) {
                Log.w(TAG, String.format("SSID to remove: %s was not found in list to remove network", ssidToRemove));
            }
        }
        return false;
    }

    /**
     * To remove a configured network
     *
     * @param ssidToRemove The ssid of the network you want to remove from the configured network list
     * @param callbacks The listener to return results to
     *
     * @see #execute(Runnable)
     * @see RemoveNetworkCallbacks
     * @see WiseFyCodes
     * @see WiseFyPrerequisites#hasPrerequisites()
     * @see WiseFySearch#findSavedNetworkByRegex(String)
     */
    @Async
    @WiseFyThread
    public void removeNetwork(final String ssidToRemove, final RemoveNetworkCallbacks callbacks) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (TextUtils.isEmpty(ssidToRemove)) {
                    if (callbacks != null) {
                        callbacks.removeNetworkWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
                    }
                    return;
                }

                if (!mWiseFyPrerequisites.hasPrerequisites()) {
                    if (callbacks != null) {
                        callbacks.removeNetworkWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
                    }
                    return;
                }

                WifiConfiguration wifiConfiguration = mWiseFySearch.findSavedNetworkByRegex(ssidToRemove);
                if (wifiConfiguration != null) {
                    mWiseFyPrerequisites.getWifiManager().disconnect();
                    boolean result = mWiseFyPrerequisites.getWifiManager().removeNetwork(wifiConfiguration.networkId);
                    if (LogUtil.isLoggable(TAG, Log.DEBUG, mWiseFyConfiguration.isLoggingEnabled())) {
                        Log.d(TAG, String.format("Removing network: %s had result: %b", ssidToRemove, result));
                    }
                    mWiseFyPrerequisites.getWifiManager().reconnect();
                    if (callbacks != null) {
                        if (result) {
                            callbacks.networkRemoved();
                        } else {
                            callbacks.failureRemovingNetwork();
                        }
                    }
                } else {
                    if (LogUtil.isLoggable(TAG, Log.WARN, mWiseFyConfiguration.isLoggingEnabled())) {
                        Log.w(TAG, String.format("SSID to remove: %s was not found in list to remove network", ssidToRemove));
                    }
                    if (callbacks != null) {
                        callbacks.networkNotFoundToRemove();
                    }
                }
            }
        };
        execute(runnable);
    }

    public ScanResult searchForAccessPoint(String regexForSSID, int timeoutInMillis, boolean filterDuplicates) {
        if (TextUtils.isEmpty(regexForSSID) || !mWiseFyPrerequisites.hasPrerequisites()) {
            return null;
        }

        ScanResult scanResult = mWiseFySearch.findAccessPointByRegex(regexForSSID, timeoutInMillis, filterDuplicates);
        if (scanResult != null) {
            return scanResult;
        }
        return null;
    }

    public void searchForAccessPoint(final String regexForSSID, final int timeoutInMillis, final boolean filterDuplicates, final SearchForAccessPointCallbacks callbacks) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (TextUtils.isEmpty(regexForSSID)) {
                    if (callbacks != null) {
                        callbacks.searchForAccessPointWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
                    }
                    return;
                }

                if (!mWiseFyPrerequisites.hasPrerequisites()) {
                    if (callbacks != null) {
                        callbacks.searchForAccessPointWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
                    }
                    return;
                }

                ScanResult scanResult = mWiseFySearch.findAccessPointByRegex(regexForSSID, timeoutInMillis, filterDuplicates);
                if (callbacks != null) {
                    if (scanResult != null) {
                        callbacks.accessPointFound(scanResult);
                    } else {
                        callbacks.accessPointNotFound();
                    }
                }
            }
        };
        execute(runnable);
    }

    public List<ScanResult> searchForAccessPoints(String regexForSSID, boolean filterDuplicates) {
        if (TextUtils.isEmpty(regexForSSID) || !mWiseFyPrerequisites.hasPrerequisites()) {
            return null;
        }

        return mWiseFySearch.findAccessPointsMatchingRegex(regexForSSID, filterDuplicates);
    }

    public void searchForAccessPoints(final String regexForSSID, final boolean filterDuplicates, final SearchForAccessPointsCallbacks callbacks) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (TextUtils.isEmpty(regexForSSID)) {
                    if (callbacks != null) {
                        callbacks.searchForAccessPointsWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
                    }
                    return;
                }

                if (!mWiseFyPrerequisites.hasPrerequisites()) {
                    if (callbacks != null) {
                        callbacks.searchForAccessPointsWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
                    }
                    return;
                }

                List<ScanResult> networks = mWiseFySearch.findAccessPointsMatchingRegex(regexForSSID, filterDuplicates);
                if (callbacks != null) {
                    if (networks != null && networks.size() > 0) {
                        callbacks.foundNetworks(networks);
                    } else {
                        callbacks.noNetworksFound();
                    }
                }
            }
        };
        execute(runnable);
    }

    /**
     * To search local networks and return the first one that contains a given ssid
     *
     * @param regexForSSID The regex to be used to search for the ssid
     * @param timeoutInMillis The number of milliseconds to keep searching for the SSID
     *
     * @return String|null - The first SSID that contains the search ssid (if any, else null)
     */
    @Sync
    @CallingThread
    @WaitsForTimeout
    public String searchForSSID(String regexForSSID, int timeoutInMillis) {
        if (TextUtils.isEmpty(regexForSSID) || !mWiseFyPrerequisites.hasPrerequisites()) {
            return null;
        }

        ScanResult scanResult = mWiseFySearch.findAccessPointByRegex(regexForSSID, timeoutInMillis, false);
        if (scanResult != null) {
            return scanResult.SSID;
        }
        return null;
    }

    /**
     * To search local networks and return the first one that contains a given ssid
     *
     * @param regexForSSID The regex to be used to search for the ssid
     * @param timeoutInMillis The number of milliseconds to keep searching for the SSID
     * @param callbacks The listener to return results to
     *
     * @see #execute(Runnable)
     * @see SearchForSSIDCallbacks
     * @see WiseFyCodes
     * @see WiseFySearch#findAccessPointByRegex(String, Integer, boolean)
     */
    @Async
    @WiseFyThread
    @WaitsForTimeout
    public void searchForSSID(final String regexForSSID, final int timeoutInMillis, final SearchForSSIDCallbacks callbacks) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (TextUtils.isEmpty(regexForSSID)) {
                    if (callbacks != null) {
                        callbacks.searchForSSIDWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
                    }
                    return;
                }

                if (!mWiseFyPrerequisites.hasPrerequisites()) {
                    if (callbacks != null) {
                        callbacks.searchForSSIDWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
                    }
                    return;
                }

                ScanResult scanResult = mWiseFySearch.findAccessPointByRegex(regexForSSID, timeoutInMillis, false);
                if (callbacks != null) {
                    if (scanResult != null) {
                        callbacks.ssidFound(scanResult.SSID);
                    } else {
                        callbacks.ssidNotFound();
                    }
                }
            }
        };
        execute(runnable);
    }

    /**
     * To search local networks and return the first one that contains a given ssid
     *
     * @param regexForSSID The regex to be used to search for the ssid
     *
     * @return String|null - The first SSID that contains the search ssid (if any, else null)
     */
    @Sync
    @CallingThread
    @WaitsForTimeout
    public List<String> searchForSSIDs(String regexForSSID) {
        if (TextUtils.isEmpty(regexForSSID) || !mWiseFyPrerequisites.hasPrerequisites()) {
            return null;
        }

        return mWiseFySearch.findSSIDsMatchingRegex(regexForSSID);
    }

    /**
     * To search local networks and return the first one that contains a given ssid
     *
     * @param regexForSSID The regex to be used to search for the ssid
     * @param callbacks The listener to return results to
     *
     * @see #execute(Runnable)
     * @see SearchForSSIDCallbacks
     * @see WiseFyCodes
     * @see WiseFySearch#findSSIDsMatchingRegex(String)
     */
    @Async
    @WiseFyThread
    @WaitsForTimeout
    public void searchForSSIDs(final String regexForSSID, final SearchForSSIDsCallbacks callbacks) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (TextUtils.isEmpty(regexForSSID)) {
                    if (callbacks != null) {
                        callbacks.searchForSSIDsWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
                    }
                    return;
                }

                if (!mWiseFyPrerequisites.hasPrerequisites()) {
                    if (callbacks != null) {
                        callbacks.searchForSSIDsWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
                    }
                    return;
                }

                List<String> ssids = mWiseFySearch.findSSIDsMatchingRegex(regexForSSID);
                if (callbacks != null) {
                    if (ssids != null) {
                        callbacks.retrievedSSIDs(ssids);
                    } else {
                        callbacks.noSSIDsFound();
                    }
                }
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
     * @param wifiConfiguration The network configuration to add
     *
     * @return int - The return code from WifiManager for network creation (-1 for failure)
     */
    private int addNetworkConfiguration(WifiConfiguration wifiConfiguration) {
        int result = mWiseFyPrerequisites.getWifiManager().addNetwork(wifiConfiguration);
        if (result == WIFI_MANAGER_FAILURE) {
            if (LogUtil.isLoggable(TAG, Log.ERROR, mWiseFyConfiguration.isLoggingEnabled())) {
                Log.e(TAG, "Failed to add network");
            }
        }
        return result;
    }

    /**
     * Used internally to check if a network has a given capability
     *
     * @param scanResult The network to check
     * @param capability The capability to check for
     *
     * @return boolean - True if the network contains the capability
     */
    private boolean containsCapability(ScanResult scanResult, String capability) {
        return scanResult != null && scanResult.capabilities != null && scanResult.capabilities.contains(capability);
    }

    /**
     * A method to execute logic on a background thread
     *
     * @param runnable The block of code to execute in the background
     *
     * @see #setupWiseFyThread()
     */
    private void execute(Runnable runnable) {
        if (mWiseFyHandler == null) {
            setupWiseFyThread();
        }
        mWiseFyHandler.post(runnable);
    }

    /**
     * Used internally to setup a WiseFyThread to run background operations
     *
     * @see #execute(Runnable)
     * @see WiseFyHandlerThread
     */
    private void setupWiseFyThread() {
        mWiseFyHandlerThread = new WiseFyHandlerThread(WiseFyHandlerThread.TAG, mWiseFyConfiguration.isLoggingEnabled());
        mWiseFyHandlerThread.start();
        Looper looper = mWiseFyHandlerThread.getLooper();
        mWiseFyHandler = new Handler(looper);
    }
}