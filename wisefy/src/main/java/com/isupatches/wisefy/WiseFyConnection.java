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
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;

import com.isupatches.wisefy.annotations.Internal;
import com.isupatches.wisefy.annotations.WaitsForTimeout;
import com.isupatches.wisefy.constants.NetworkTypes.NetworkType;
import com.isupatches.wisefy.constants.Symbols;
import com.isupatches.wisefy.utils.SleepUtil;

/**
 * A class used internally to query and determine different parts of the connection state for a
 * device.
 *
 * @author Patches
 */
@Internal
class WiseFyConnection {

  private static final String TAG = WiseFyConnection.class.getSimpleName();

  private final WiseFyPrerequisites wiseFyPrerequisites;

  /**
   * Private constructor.
   *
   * @param wisefyPrerequisites The prerequisites instance needed
   */
  private WiseFyConnection(@NonNull final WiseFyPrerequisites wisefyPrerequisites) {
    this.wiseFyPrerequisites = wisefyPrerequisites;
  }

  /**
   * Factory pattern creation method to get an instance of WiseFyConnection.
   *
   * @param wisefyPrerequisites The prerequisites instance needed
   *
   * @return WiseFyConnection - An instance of WiseFyConnection
   *
   * @see WiseFyPrerequisites
   */
  @NonNull
  static WiseFyConnection create(@NonNull final WiseFyPrerequisites wisefyPrerequisites) {
    return new WiseFyConnection(wisefyPrerequisites);
  }

  /**
   * Used internally to see if the current network is connected to and matches a given ssid.
   *
   * <p>*NOTE* Case insensitive</p>
   *
   * @param ssid The ssid to check if the device is connected to
   *
   * @return boolean - True if the device is connected to a network
   *
   * @see WiseFyPrerequisites#getConnectivityManager() ()
   * @see WiseFyPrerequisites#getWifiManager()
   */
  @RequiresPermission(allOf = { android.Manifest.permission.ACCESS_NETWORK_STATE, android.Manifest.permission.ACCESS_WIFI_STATE })
  boolean isCurrentNetworkConnectedToSSID(@Nullable final String ssid) {
    if (ssid == null) {
      return false;
    }

    final WifiInfo connectionInfo = wiseFyPrerequisites.getWifiManager().getConnectionInfo();
    boolean isConnected = false;
    if (connectionInfo != null && connectionInfo.getSSID() != null) {
      final String currentSSID = connectionInfo.getSSID().replaceAll(Symbols.QUOTE, "");
      WiseFyLogger.debug(TAG, "Current SSID: %s, Desired SSID: %s", currentSSID, ssid);
      if (currentSSID.equalsIgnoreCase(ssid) && isNetworkConnected(wiseFyPrerequisites.getConnectivityManager().getActiveNetworkInfo())) {
        WiseFyLogger.debug(TAG, "Network is connected");
        isConnected = true;
      }
    }
    return isConnected;
  }

  /**
   * Used internally to check if a network is connected.
   *
   * @param networkInfo The network to check
   *
   * @return boolean - True if the network is both available and connected
   *
   * @see NetworkInfo
   */
  boolean isNetworkConnected(@Nullable final NetworkInfo networkInfo) {
    WiseFyLogger.debug(TAG, "networkInfo: %s", networkInfo);
    return networkInfo != null && networkInfo.isConnected() && networkInfo.isAvailable();
  }

  /**
   * Used internally to check if a given network matches a given type and is connected.
   *
   * @param networkInfo The network to check
   * @param type The type of network (i.error. Mobile or Wifi)
   *
   * @return boolean - True if the network is both connected and matches the given type of network
   *
   * @see #doesNetworkMatchType(NetworkInfo, String)
   * @see #isNetworkConnected(NetworkInfo)
   */
  boolean isNetworkConnectedAndMatchesType(@Nullable final NetworkInfo networkInfo, @NetworkType final String type) {
    return isNetworkConnected(networkInfo) && doesNetworkMatchType(networkInfo, type);
  }

  /**
   * Used internally to check if the device connects to a given SSID within a specified time.
   *
   * @param ssid The ssid to wait for the device to connect to
   * @param timeoutInMillis The number of milliseconds to wait
   *
   * @return boolean - True if the device is connected to the ssid within the given time
   *
   * @see #isCurrentNetworkConnectedToSSID(String)
   */
  @WaitsForTimeout
  @RequiresPermission(allOf = { android.Manifest.permission.ACCESS_NETWORK_STATE, android.Manifest.permission.ACCESS_WIFI_STATE })
  boolean waitToConnectToSSID(@Nullable final String ssid, final int timeoutInMillis) {
    WiseFyLogger.debug(TAG, "Waiting %d milliseconds to connect to network with ssid %s", timeoutInMillis, ssid);
    long currentTime;
    final long endTime = System.currentTimeMillis() + timeoutInMillis;
    do {
      if (isCurrentNetworkConnectedToSSID(ssid)) {
        return true;
      }
      SleepUtil.getInstance().rest();
      currentTime = System.currentTimeMillis();
      WiseFyLogger.debug(TAG, "Current time: %d, End time: %d (waitToConnectToSSID)", currentTime, endTime);
    } while (currentTime < endTime);
    return false;
  }

  /**
   * Used internally to check to see if a given network matches a specified type (i.error. Mobile or Wifi)
   *
   * <p>*NOTE* Case insensitive</p>
   *
   * @param networkInfo The network to check
   * @param type The type of network
   *
   * @return boolean - True if the network matches the given type
   *
   * @see NetworkInfo
   */
  private boolean doesNetworkMatchType(@Nullable final NetworkInfo networkInfo, @NonNull @NetworkType final String type) {
    return networkInfo != null && networkInfo.getTypeName() != null && networkInfo.getTypeName().equalsIgnoreCase(type);
  }
}
