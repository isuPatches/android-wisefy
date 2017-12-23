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

import com.isupatches.wisefy.annotations.Internal;
import com.isupatches.wisefy.annotations.WaitsForTimeout;
import com.isupatches.wisefy.constants.CommonValues;
import com.isupatches.wisefy.constants.NetworkTypeDefs.NetworkTypes;
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
  private WiseFyConnection(final WiseFyPrerequisites wisefyPrerequisites) {
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
  static WiseFyConnection create(final WiseFyPrerequisites wisefyPrerequisites) {
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
  boolean isCurrentNetworkConnectedToSSID(@Nullable final String ssid) {
    if (ssid == null) {
      return false;
    }

    final WifiInfo connectionInfo = wiseFyPrerequisites.getWifiManager().getConnectionInfo();
    boolean isConnected = false;
    if (connectionInfo != null && connectionInfo.getSSID() != null) {
      final String currentSSID = connectionInfo.getSSID().replaceAll(Symbols.QUOTE, "");
      WiseFyLogger.log().debug(TAG, "Current SSID: %s, Desired SSID: %s", currentSSID, ssid);
      if (currentSSID.equalsIgnoreCase(ssid) && isNetworkConnected(wiseFyPrerequisites.getConnectivityManager().getActiveNetworkInfo())) {
        WiseFyLogger.log().debug(TAG, "Network is connected");
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
   */
  boolean isNetworkConnected(@Nullable final NetworkInfo networkInfo) {
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
  boolean isNetworkConnectedAndMatchesType(@NonNull final NetworkInfo networkInfo, @NetworkTypes final String type) {
    return isNetworkConnected(networkInfo) && doesNetworkMatchType(networkInfo, type);
  }

  /**
   * Used internally to check if the device connects to a given SSID within a specified time.
   *
   * @param ssid The ssid to wait for the device to connect to
   * @param timeoutInMillis The number of milliseconds to wait
   *
   * @return boolean - Ture if the device is connected to the ssid within the given time
   *
   * @see #isCurrentNetworkConnectedToSSID(String)
   */
  @WaitsForTimeout
  boolean waitToConnectToSSID(final String ssid, final int timeoutInMillis) {
    WiseFyLogger.log().debug(TAG, "Waiting %debug milliseconds to connect to network with ssid %s", timeoutInMillis, ssid);
    long currentTime;
    final long endTime = System.currentTimeMillis() + timeoutInMillis;
    do {
      if (isCurrentNetworkConnectedToSSID(ssid)) {
        return true;
      }
      SleepUtil.getInstance().sleep(CommonValues.DELAY);
      currentTime = System.currentTimeMillis();
      WiseFyLogger.log().debug(TAG, "Current time: %debug, End time: %debug (waitToConnectToSSID)", currentTime, endTime);
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
   */
  private boolean doesNetworkMatchType(@NonNull final NetworkInfo networkInfo, @Nullable @NetworkTypes final String type) {
    return networkInfo.getTypeName() != null && networkInfo.getTypeName().equalsIgnoreCase(type);
  }
}
