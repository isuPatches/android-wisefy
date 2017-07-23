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


import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.util.Log;
import com.isupatches.wisefy.annotations.Internal;
import com.isupatches.wisefy.annotations.WaitsForTimeout;
import com.isupatches.wisefy.util.LogUtil;
import com.isupatches.wisefy.util.SleepUtil;


/**
 * A class used internally to query and determine different parts of the connection state for a
 * device.
 *
 * @author Patches
 */
@Internal
class WiseFyConnection {

    private static final String TAG = WiseFyConnection.class.getSimpleName();

    private static final WiseFyConnection WISEFY_CONNECTION = new WiseFyConnection();

    private WiseFyConfiguration mWiseFyConfiguration;

    WiseFyPrerequisites mWiseFyPrerequisites;

    /**
     * Private constructor with no setup
     */
    private WiseFyConnection() {
        mWiseFyConfiguration = WiseFyConfiguration.getInstance();
        mWiseFyPrerequisites = WiseFyPrerequisites.getInstance();
    }

    /**
     * @return instance of WiseFyPrerequisites
     */
    static WiseFyConnection getInstance() {
        return WISEFY_CONNECTION;
    }

    /**
     * Used internally to see if the current network is connected to and matches a given ssid
     *
     * *NOTE* Case insensitive
     *
     * @param ssid The ssid to check if the device is connected to
     *
     * @see WiseFyConfiguration#isLoggingEnabled()
     * @see WiseFyPrerequisites#getConnectivityManager() ()
     * @see WiseFyPrerequisites#getWifiManager()
     *
     * @return boolean - True if the device is connected to a network
     */
    boolean isCurrentNetworkConnectedToSSID(String ssid) {
        if (ssid == null) {
            return false;
        }

        WifiInfo connectionInfo = mWiseFyPrerequisites.getWifiManager().getConnectionInfo();
        if (connectionInfo != null && connectionInfo.getSSID() != null) {
            String currentSSID = connectionInfo.getSSID().replaceAll("\"", "");
            if (LogUtil.isLoggable(TAG, Log.DEBUG, mWiseFyConfiguration.isLoggingEnabled())) {
                Log.d(TAG, String.format("Current SSID: %s, Desired SSID: %s", currentSSID, ssid));
            }
            if (currentSSID.equalsIgnoreCase(ssid)) {
                if (isNetworkConnected(mWiseFyPrerequisites.getConnectivityManager().getActiveNetworkInfo())) {
                    if (LogUtil.isLoggable(TAG, Log.DEBUG, mWiseFyConfiguration.isLoggingEnabled())) {
                        Log.d(TAG, "Network is connected");
                    }
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Used internally to check if a network is connected
     *
     * @param networkInfo The network to check
     *
     * @return boolean - True if the network is both available and connected
     */
    boolean isNetworkConnected(NetworkInfo networkInfo) {
        return networkInfo != null && networkInfo.isConnected() && networkInfo.isAvailable();
    }

    /**
     * Used internally to check if a given network matches a given type and is connected
     *
     * @param networkInfo The network to check
     * @param type The type of network (i.e. Mobile or Wifi)
     *
     * @see #doesNetworkMatchType(NetworkInfo, String)
     * @see #isNetworkConnected(NetworkInfo)
     *
     * @return boolean - True if the network is both connected and matches the given type of network
     */
    boolean isNetworkConnectedAndMatchesType(NetworkInfo networkInfo, String type) {
        return isNetworkConnected(networkInfo) && doesNetworkMatchType(networkInfo, type);
    }

    /**
     * Used internally to check if the device connects to a given SSID within a specified time
     *
     * @param ssid The ssid to wait for the device to connect to
     * @param timeoutInMillis The number of milliseconds to wait
     *
     * @see #isCurrentNetworkConnectedToSSID(String)}
     * @see WiseFyConfiguration#isLoggingEnabled()
     *
     * @return boolean - Ture if the device is connected to the ssid within the given time
     */
    @WaitsForTimeout
    boolean waitToConnectToSSID(String ssid, int timeoutInMillis) {
        long currentTime;
        long endTime = System.currentTimeMillis() + timeoutInMillis;
        do {
            boolean result = isCurrentNetworkConnectedToSSID(ssid);
            if (result) {
                return true;
            }
            SleepUtil.sleep(1000);
            currentTime = System.currentTimeMillis();
            if (LogUtil.isLoggable(TAG, Log.DEBUG, mWiseFyConfiguration.isLoggingEnabled())) {
                Log.d(TAG, String.format("Current time: %d / End time: %d (waitToConnectToSSID)", currentTime, endTime));
            }
        } while (currentTime < endTime);
        return false;
    }

    /**
     * Used internally to check to see if a given network matches a specified type (i.e. Mobile or Wifi)
     *
     * *NOTE* Case insensitive
     *
     * @param networkInfo The network to check
     * @param type The type of network
     *
     * @return boolean - True if the network matches the given type
     */
    private boolean doesNetworkMatchType(NetworkInfo networkInfo, String type) {
        return networkInfo != null && networkInfo.getTypeName() != null && networkInfo.getTypeName().equalsIgnoreCase(type);
    }
}
