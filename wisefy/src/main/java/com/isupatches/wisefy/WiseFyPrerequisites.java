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

import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.isupatches.wisefy.annotations.Internal;

/**
 * A class used internally to hold references and methods related to the prerequisites required
 * throughout the library.
 *
 * @author Patches
 */
@Internal
class WiseFyPrerequisites {

  private static final String TAG = WiseFyPrerequisites.class.getSimpleName();

  private final ConnectivityManager connectivityManager;
  private final WifiManager wifiManager;

  /**
   * Private constructor.
   *
   * @param connectivityManager The connectivity manager instance needed
   * @param wifiManager The wifi manager instance needed
   *
   * @see ConnectivityManager
   * @see WifiManager
   */
  private WiseFyPrerequisites(@Nullable final ConnectivityManager connectivityManager,
                              @Nullable final WifiManager wifiManager) {
    this.connectivityManager = connectivityManager;
    this.wifiManager = wifiManager;
  }

  /**
   * Factory pattern creation method to get an instance of WiseFyPrerequisites.
   *
   * @param connectivityManager The connectivity manager instance needed
   * @param wifiManager The wifi manager instance needed
   *
   * @return - An instance of WiseFyPrerequisites
   *
   * @see ConnectivityManager
   * @see WifiManager
   */
  @NonNull
  static WiseFyPrerequisites create(@Nullable final ConnectivityManager connectivityManager,
                                    @Nullable final WifiManager wifiManager) {
    return new WiseFyPrerequisites(connectivityManager, wifiManager);
  }

  /**
   * Used internally to verify if the WiseFy instances has the necessary criteria to work.
   *
   * @return boolean - True if all prerequisites are met
   */
  boolean hasPrerequisites() {
    boolean hasPrerequisites = true;
    if (wifiManager == null) {
      WiseFyLogger.error(TAG, "Missing WifiManager");
      hasPrerequisites = false;
    }
    if (connectivityManager == null) {
      WiseFyLogger.error(TAG, "Missing ConnectivityManager");
      hasPrerequisites = false;
    }
    return hasPrerequisites;
  }

  /**
   * Used internally to return a shared ConnectivityManager instance.
   *
   * @return ConnectivityManager|null - The shared ConnectivityManager instance if any or null
   *
   * @see ConnectivityManager
   */
  ConnectivityManager getConnectivityManager() {
    return connectivityManager;
  }

  /**
   * Used internally to return a shared WifiManager instance.
   *
   * @return WifiManager|null - The shared WifiManager instance if any or null
   *
   * @see WifiManager
   */
  WifiManager getWifiManager() {
    return wifiManager;
  }

  /**
   * Used internal to check if a prerequisite is missing.
   *
   * @return boolean - True if ConnectivityManager or WifiManager are null
   *
   * @see #hasPrerequisites()
   */
  boolean missingPrerequisites() {
    return !hasPrerequisites();
  }
}
