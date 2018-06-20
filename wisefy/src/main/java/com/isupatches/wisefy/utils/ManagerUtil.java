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
package com.isupatches.wisefy.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.isupatches.wisefy.annotations.Internal;

/**
 * A helper class to create ConnectivityManager and WifiManager instances.
 *
 * @author Patches
 */
@Internal
public final class ManagerUtil {

  private final ConnectivityManager connectivityManager;
  private final WifiManager wifiManager;

  /**
   * Private constructor for instance creation.
   *
   * @param context The context to retrieve a connectivity manager and wifi manager.
   */
  private ManagerUtil(@Nullable final Context context) {
    if (context != null) {
      this.connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
      this.wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
    } else {
      this.connectivityManager = null;
      this.wifiManager = null;
    }
  }

  /**
   * Used to construct a ManagerUtil instance.
   *
   * @param context - The activity context used to create a ConnectivityManager and WifiManager
   *
   * @return ManagerUtil - A constructed ManagerUtil for use
   *
   * @see #ManagerUtil(Context)
   */
  @NonNull
  public static ManagerUtil create(@Nullable final Context context) {
    return new ManagerUtil(context);
  }

  /**
   * To get the ConnectivityManager instance for use.
   *
   * @return ConnectivityManager|null - The ConnectivityManager instance
   *
   * @see ConnectivityManager
   */
  @Nullable
  public ConnectivityManager getConnectivityManager() {
    return connectivityManager;
  }

  /**
   * To get the WifiManager instance for use.
   *
   * @return WifiManager|null - the WifiManager instance
   *
   * @see WifiManager
   */
  @Nullable
  public WifiManager getWiFiManager() {
    return wifiManager;
  }
}
