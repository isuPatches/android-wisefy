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

import android.content.Context;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

import com.isupatches.wisefy.annotations.Async;
import com.isupatches.wisefy.annotations.CallingThread;
import com.isupatches.wisefy.annotations.PublicAPI;
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
import com.isupatches.wisefy.callbacks.GetIPCallbacks;
import com.isupatches.wisefy.callbacks.GetNearbyAccessPointsCallbacks;
import com.isupatches.wisefy.callbacks.GetRSSICallbacks;
import com.isupatches.wisefy.callbacks.GetSavedNetworkCallbacks;
import com.isupatches.wisefy.callbacks.GetSavedNetworksCallbacks;
import com.isupatches.wisefy.callbacks.RemoveNetworkCallbacks;
import com.isupatches.wisefy.callbacks.SearchForAccessPointCallbacks;
import com.isupatches.wisefy.callbacks.SearchForAccessPointsCallbacks;
import com.isupatches.wisefy.callbacks.SearchForSSIDCallbacks;
import com.isupatches.wisefy.callbacks.SearchForSSIDsCallbacks;
import com.isupatches.wisefy.constants.CapabilityDefs;
import com.isupatches.wisefy.constants.CapabilityDefs.Capabilities;
import com.isupatches.wisefy.constants.CommonValues;
import com.isupatches.wisefy.constants.NetworkTypeDefs;
import com.isupatches.wisefy.constants.WiseFyCodeDefs;
import com.isupatches.wisefy.threads.WiseFyHandlerThread;
import com.isupatches.wisefy.utils.ManagerUtil;
import com.isupatches.wisefy.utils.WifiConfigurationUtil;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

/**
 * Main class for WiseFy that provides a synchronous and asynchronous API to manipulate and query
 * for different parts of a device's wifi configuration and status.
 *
 * <p>Uses the builder pattern for creation - {@link brains}</p>
 *
 * @author Patches
 * @version 2.1
 */
@PublicAPI
public class WiseFy {

  public static final int WIFI_MANAGER_FAILURE = -1;

  static final int MIN_FREQUENCY_5GHZ = 4900;
  static final int MAX_FREQUENCY_5GHZ = 5900;

  private static final String TAG = WiseFy.class.getSimpleName();

  private final WiseFyLock wisefyLock = new WiseFyLock();

  private final WiseFyConnection wisefyConnection;
  private final WiseFyPrechecks wiseFyPrechecks;
  private final WiseFyPrerequisites wisefyPrerequisites;
  private final WiseFySearch wisefySearch;

  private WiseFyHandlerThread wisefyHandlerThread;
  private Handler wisefyHandler;

  /**
   * Private constructor that accepts builder input.
   *
   * @param brains The builder to use when creating the instance
   */
  private WiseFy(@NonNull final brains brains) {
    final ManagerUtil managerUtil = ManagerUtil.create(brains.context);

    if (brains.wisefyPrerequisites != null) {
      this.wisefyPrerequisites = brains.wisefyPrerequisites;
    } else {
      this.wisefyPrerequisites = WiseFyPrerequisites.create(
              managerUtil.getConnectivityManager(),
              managerUtil.getWiFiManager());
    }

    if (brains.wisefyConnection != null) {
      this.wisefyConnection = brains.wisefyConnection;
    } else {
      this.wisefyConnection = WiseFyConnection.create(wisefyPrerequisites);
    }

    if (brains.wisefySearch != null) {
      this.wisefySearch = brains.wisefySearch;
    } else {
      this.wisefySearch = WiseFySearch.create(wisefyPrerequisites);
    }

    if (brains.wiseFyPrechecks != null) {
      this.wiseFyPrechecks = brains.wiseFyPrechecks;
    } else {
      this.wiseFyPrechecks = WiseFyPrechecks.create(wisefyPrerequisites, wisefySearch);
    }

    WiseFyLogger.log().setLoggingEnabled(brains.loggingEnabled);
  }

  /**
   * To add an open network to the user's configured network list.
   *
   * @param ssid The ssid of the open network you want to add
   *
   * @return int - The return code from WifiManager for network creation (-1 for failure)
   *
   * @see #addNetworkConfiguration(WifiConfiguration)
   * @see WifiConfigurationUtil#generateOpenNetworkConfiguration(String)
   * @see WiseFyCodeDefs
   * @see WiseFyPrerequisites#hasPrerequisites()
   * @see WiseFySearch#isNetworkASavedConfiguration(String)
   */
  @Sync
  @CallingThread
  public int addOpenNetwork(final String ssid) {
    final int precheckResult = wiseFyPrechecks.addNetworkPrechecks(ssid);
    if (WiseFyPrechecks.checksFailed(precheckResult)) {
      return precheckResult;
    }

    return addNetworkConfiguration(WifiConfigurationUtil.getInstance().generateOpenNetworkConfiguration(ssid));
  }

  /**
   * To add an open network to the user's configured network list.
   *
   * @param ssid The ssid of the open network you want to add
   * @param callbacks The listener to return results to
   *
   * @see #addNetworkConfiguration(WifiConfiguration)
   * @see #runOnWiseFyThread(Runnable)
   * @see AddOpenNetworkCallbacks
   * @see WifiConfigurationUtil#generateOpenNetworkConfiguration(String)
   * @see WiseFyCodeDefs
   * @see WiseFyPrerequisites#hasPrerequisites()
   * @see WiseFySearch#isNetworkASavedConfiguration(String)
   */
  @Async
  @WiseFyThread
  public void addOpenNetwork(final String ssid, @Nullable final AddOpenNetworkCallbacks callbacks) {
    runOnWiseFyThread(() -> {
      synchronized (wisefyLock) {
        final int precheckResult = wiseFyPrechecks.addNetworkPrechecks(ssid);
        if (WiseFyPrechecks.checksFailed(precheckResult)) {
          if (callbacks != null) {
            callbacks.addOpenNetworkWiseFyFailure(precheckResult);
          }
          return;
        }

        final WifiConfiguration openNetworkConfiguration = WifiConfigurationUtil.getInstance().generateOpenNetworkConfiguration(ssid);
        final int result = WiseFy.this.addNetworkConfiguration(openNetworkConfiguration);
        if (callbacks != null) {
          if (result != WIFI_MANAGER_FAILURE) {
            callbacks.openNetworkAdded(result, openNetworkConfiguration);
          } else {
            callbacks.failureAddingOpenNetwork(result);
          }
        }
      }
    });
  }

  /**
   * To add a WEP network to the user's configured network list.
   *
   * @param ssid The ssid of the WEP network you want to add
   * @param password The password for the WEP network being added
   *
   * @return int - The return code from WifiManager for network creation (-1 for failure)
   *
   * @see #addNetworkConfiguration(WifiConfiguration)
   * @see WifiConfigurationUtil#generateWEPNetworkConfiguration(String, String)
   * @see WiseFyCodeDefs
   * @see WiseFyPrechecks#addNetworkPrechecks(String, String)
   * @see WiseFyPrerequisites#hasPrerequisites()
   * @see WiseFySearch#isNetworkASavedConfiguration(String)
   */
  @Sync
  @CallingThread
  public int addWEPNetwork(final String ssid, final String password) {
    final int precheckResult = wiseFyPrechecks.addNetworkPrechecks(ssid, password);
    if (WiseFyPrechecks.checksFailed(precheckResult)) {
      return precheckResult;
    }

    return addNetworkConfiguration(WifiConfigurationUtil.getInstance().generateWEPNetworkConfiguration(ssid, password));
  }

  /**
   * To add a WEP network to the user's configured network list.
   *
   * @param ssid The ssid of the WEP network you want to add
   * @param password The password for the WEP network being added
   * @param callbacks The listener to return results to
   *
   * @see #addNetworkConfiguration(WifiConfiguration)
   * @see #runOnWiseFyThread(Runnable)
   * @see AddWEPNetworkCallbacks
   * @see WifiConfigurationUtil#generateWEPNetworkConfiguration(String, String)
   * @see WiseFyCodeDefs
   * @see WiseFyPrechecks#addNetworkPrechecks(String, String)
   * @see WiseFyPrerequisites#hasPrerequisites()
   * @see WiseFySearch#isNetworkASavedConfiguration(String)
   */
  @Async
  @WiseFyThread
  public void addWEPNetwork(final String ssid, final String password, @Nullable final AddWEPNetworkCallbacks callbacks) {
    runOnWiseFyThread(() -> {
      synchronized (wisefyLock) {
        final int precheckResult = wiseFyPrechecks.addNetworkPrechecks(ssid, password);
        if (WiseFyPrechecks.checksFailed(precheckResult)) {
          if (callbacks != null) {
            callbacks.addWEPNetworkWiseFyFailure(precheckResult);
          }
          return;
        }

        final WifiConfiguration wepNetworkConfiguration = WifiConfigurationUtil.getInstance().generateWEPNetworkConfiguration(ssid, password);
        final int result = WiseFy.this.addNetworkConfiguration(wepNetworkConfiguration);
        if (callbacks != null) {
          if (result != WIFI_MANAGER_FAILURE) {
            callbacks.wepNetworkAdded(result, wepNetworkConfiguration);
          } else {
            callbacks.failureAddingWEPNetwork(result);
          }
        }
      }
    });
  }

  /**
   * To add a WPA2 network to the user's configured network list.
   *
   * @param ssid The ssid of the WPA2 network you want to add
   * @param password The password for the WPA2 network being added
   *
   * @return int - The return code from WifiManager for network creation (-1 for failure)
   *
   * @see #addNetworkConfiguration(WifiConfiguration)
   * @see WiseFyPrechecks#addNetworkPrechecks(String, String)
   * @see CommonValues
   */
  @Sync
  @CallingThread
  public int addWPA2Network(final String ssid, final String password) {
    final int precheckResult = wiseFyPrechecks.addNetworkPrechecks(ssid, password);
    if (WiseFyPrechecks.checksFailed(precheckResult)) {
      return precheckResult;
    }

    return addNetworkConfiguration(WifiConfigurationUtil.getInstance().generateWPA2NetworkConfiguration(ssid, password));
  }

  /**
   * To add a WPA2 network to the user's configured network list.
   *
   * @param ssid The ssid of the WPA2 network you want to add
   * @param password The password for the WPA2 network being added
   * @param callbacks The listener to return results to
   *
   * @see #addNetworkConfiguration(WifiConfiguration)
   * @see #runOnWiseFyThread(Runnable)
   * @see AddWPA2NetworkCallbacks
   * @see WifiConfigurationUtil#generateWPA2NetworkConfiguration(String, String)
   * @see WiseFyCodeDefs
   * @see WiseFyPrerequisites#hasPrerequisites()
   * @see WiseFySearch#isNetworkASavedConfiguration(String)
   */
  @Async
  @WiseFyThread
  public void addWPA2Network(final String ssid, final String password, @Nullable final AddWPA2NetworkCallbacks callbacks) {
    runOnWiseFyThread(() -> {
      synchronized (wisefyLock) {
        final int precheckResult = wiseFyPrechecks.addNetworkPrechecks(ssid, password);
        if (WiseFyPrechecks.checksFailed(precheckResult)) {
          if (callbacks != null) {
            callbacks.addWPA2NetworkWiseFyFailure(precheckResult);
          }
          return;
        }

        final WifiConfiguration wpa2NetworkConfiguration = WifiConfigurationUtil.getInstance().generateWPA2NetworkConfiguration(ssid, password);
        final int result = WiseFy.this.addNetworkConfiguration(wpa2NetworkConfiguration);
        if (callbacks != null) {
          if (result != WIFI_MANAGER_FAILURE) {
            callbacks.wpa2NetworkAdded(result, wpa2NetworkConfiguration);
          } else {
            callbacks.failureAddingWPA2Network(result);
          }
        }
      }
    });
  }

  /**
   * To convert an RSSI level for a network to a number of bars.
   *
   * @param rssiLevel The signal strength of the network
   * @param targetNumberOfBars How many bars or levels there will be total
   *
   * @return int - The number of bars for the given RSSI value
   */
  @Sync
  @CallingThread
  public int calculateBars(final int rssiLevel, final int targetNumberOfBars) {
    return WifiManager.calculateSignalLevel(rssiLevel, targetNumberOfBars);
  }

  /**
   * To compare the signal strength of two networks.
   *
   * <p>
   * This method will return:
   * - Negative value if the first signal is weaker than the second signal
   * - 0 if the two signals have the same strength
   * - Positive value if the first signal is stronger than the second signal
   * </p>
   *
   * @param rssi1 The signal strength of network 1
   * @param rssi2 The signal strength of network 2
   *
   * @return int - The result of the comparison
   */
  @Sync
  @CallingThread
  public int compareSignalLevel(final int rssi1, final int rssi2) {
    return WifiManager.compareSignalLevel(rssi1, rssi2);
  }

  /**
   * Used to connect to a network.
   *
   * <p>*NOTE* Gets a list of saved networks, connects to the given ssid if found, and verifies connectivity.</p>
   *
   * @param ssidToConnectTo The ssid to connect/reconnect to
   * @param timeoutInMillis The number of milliseconds to continue waiting for the device to connect to the given SSID
   *
   * @return boolean - If the network was successfully reconnected
   *
   * @see WiseFyConnection#waitToConnectToSSID(String, int)
   * @see WiseFyPrerequisites#getWifiManager()
   * @see WiseFyPrerequisites#hasPrerequisites()
   * @see WiseFySearch#findSavedNetworkByRegex(String)
   */
  @Sync
  @CallingThread
  @WaitsForTimeout
  public boolean connectToNetwork(final String ssidToConnectTo, final int timeoutInMillis) {
    if (WiseFyPrechecks.checksFailed(wiseFyPrechecks.connectToNetworkPrechecks(ssidToConnectTo))) {
      return false;
    }

    final WifiConfiguration wifiConfiguration = wisefySearch.findSavedNetworkByRegex(ssidToConnectTo);
    boolean result = false;
    if (wifiConfiguration != null) {
      connectToNetworkWithId(wifiConfiguration.networkId);
      result = wisefyConnection.waitToConnectToSSID(ssidToConnectTo, timeoutInMillis);
    }

    return result;
  }

  /**
   * Used to connect to a network.
   *
   * <p>Gets a list of saved networks, connects to the given ssid if found, and verifies connectivity.</p>
   *
   * @param ssidToConnectTo The ssid to connect/reconnect to
   * @param timeoutInMillis The number of milliseconds to continue waiting for the device to connect to the given SSID
   * @param callbacks The listener to return results to
   *
   * @see #runOnWiseFyThread(Runnable)
   * @see ConnectToNetworkCallbacks
   * @see WiseFyCodeDefs
   * @see WiseFyConnection#waitToConnectToSSID(String, int)
   * @see WiseFyPrerequisites#getWifiManager()
   * @see WiseFyPrerequisites#hasPrerequisites()
   * @see WiseFySearch#findSavedNetworkByRegex(String)
   */
  @Async
  @WiseFyThread
  @WaitsForTimeout
  public void connectToNetwork(final String ssidToConnectTo, final int timeoutInMillis, @Nullable final ConnectToNetworkCallbacks callbacks) {
    runOnWiseFyThread(() -> {
      synchronized (wisefyLock) {
        final int precheckResult = wiseFyPrechecks.connectToNetworkPrechecks(ssidToConnectTo);
        if (WiseFyPrechecks.checksFailed(precheckResult)) {
          if (callbacks != null) {
            callbacks.connectToNetworkWiseFyFailure(precheckResult);
          }
          return;
        }

        final WifiConfiguration wifiConfiguration = wisefySearch.findSavedNetworkByRegex(ssidToConnectTo);
        if (wifiConfiguration != null) {
          WiseFy.this.connectToNetworkWithId(wifiConfiguration.networkId);
          final boolean connected = wisefyConnection.waitToConnectToSSID(ssidToConnectTo, timeoutInMillis);
          if (callbacks != null) {
            if (connected) {
              callbacks.connectedToNetwork();
            } else {
              callbacks.failureConnectingToNetwork();
            }
          }
        } else {
          if (callbacks != null) {
            callbacks.networkNotFoundToConnectTo();
          }
        }
      }
    });
  }

  /**
   * To disable Wifi on a user's device.
   *
   * @return boolean - True if the command succeeded in disabling wifi
   *
   * @see WiseFyPrerequisites#getWifiManager()
   * @see WiseFyPrerequisites#hasPrerequisites()
   */
  @Sync
  @CallingThread
  public boolean disableWifi() {
    return WiseFyPrechecks.checksPassed(wiseFyPrechecks.disableWifiChecks()) && wisefyPrerequisites.getWifiManager().setWifiEnabled(false);
  }

  /**
   * To disable Wifi on a user's device.
   *
   * @param callbacks The listener to return results to
   *
   * @see #runOnWiseFyThread(Runnable)
   * @see DisableWifiCallbacks
   * @see WiseFyCodeDefs
   * @see WiseFyPrerequisites#getWifiManager()
   * @see WiseFyPrerequisites#hasPrerequisites()
   */
  @Async
  @WiseFyThread
  public void disableWifi(@Nullable final DisableWifiCallbacks callbacks) {
    runOnWiseFyThread(() -> {
      synchronized (wisefyLock) {
        final int precheckResult = wiseFyPrechecks.disableWifiChecks();
        if (WiseFyPrechecks.checksFailed(precheckResult)) {
          if (callbacks != null) {
            callbacks.disableWifiWiseFyFailure(precheckResult);
          }
          return;
        }

        if (wisefyPrerequisites.getWifiManager().setWifiEnabled(false)) {
          if (callbacks != null) {
            callbacks.wifiDisabled();
          }
        } else {
          if (callbacks != null) {
            callbacks.failureDisablingWifi();
          }
        }
      }
    });
  }

  /**
   * To disconnect the user from their current network.
   *
   * @return boolean - If the command succeeded in disconnecting the device from the current network
   *
   * @see WiseFyPrerequisites#getWifiManager()
   * @see WiseFyPrerequisites#hasPrerequisites()
   */
  @Sync
  @CallingThread
  public boolean disconnectFromCurrentNetwork() {
    return WiseFyPrechecks.checksPassed(wiseFyPrechecks.disconnectFromCurrentNetworkChecks()) && wisefyPrerequisites.getWifiManager().disconnect();
  }

  /**
   * To disconnect the user from their current network.
   *
   * @param callbacks The listener to return results to
   *
   * @see #runOnWiseFyThread(Runnable)
   * @see DisconnectFromCurrentNetworkCallbacks
   * @see WiseFyCodeDefs
   * @see WiseFyPrerequisites#getWifiManager()
   * @see WiseFyPrerequisites#hasPrerequisites()
   */
  @Async
  @WiseFyThread
  public void disconnectFromCurrentNetwork(@Nullable final DisconnectFromCurrentNetworkCallbacks callbacks) {
    runOnWiseFyThread(() -> {
      synchronized (wisefyLock) {
        final int precheckResult = wiseFyPrechecks.disconnectFromCurrentNetworkChecks();
        if (WiseFyPrechecks.checksFailed(precheckResult)) {
          if (callbacks != null) {
            callbacks.disconnectFromCurrentNetworkWiseFyFailure(precheckResult);
          }
          return;
        }

        if (wisefyPrerequisites.getWifiManager().disconnect()) {
          if (callbacks != null) {
            callbacks.disconnectedFromCurrentNetwork();
          }
        } else {
          if (callbacks != null) {
            callbacks.failureDisconnectingFromCurrentNetwork();
          }
        }
      }
    });
  }

  /**
   * Used to cleanup the thread started by WiseFy.
   **/
  public void dump() {
    if (wisefyHandlerThread != null) {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
        wisefyHandlerThread.quitSafely();
      } else {
        wisefyHandlerThread.quit();
      }
      if (wisefyHandlerThread.isAlive()) {
        WiseFyLogger.log().warn(TAG, "WiseFy Thread is still alive.  Current status: isAlive(): %b, getState(): %s",
                wisefyHandlerThread.isAlive(),
                wisefyHandlerThread.getState());
        wisefyHandlerThread.interrupt();
      }
      WiseFyLogger.log().debug(TAG, "WiseFy Thread isAlive(): %b, getState(): %s",
             wisefyHandlerThread.isAlive(),
             wisefyHandlerThread.getState());
      wisefyHandlerThread = null;
    }
    wisefyHandler = null;
    WiseFyLogger.log().debug(TAG, "Cleaned up WiseFy Thread");
  }

  /**
   * To enable Wifi on a user's device.
   *
   * @return boolean - If the command succeeded in enabling wifi
   *
   * @see WiseFyPrerequisites#getWifiManager()
   * @see WiseFyPrerequisites#hasPrerequisites()
   */
  @Sync
  @CallingThread
  public boolean enableWifi() {
    return WiseFyPrechecks.checksPassed(wiseFyPrechecks.enableWifiChecks()) && wisefyPrerequisites.getWifiManager().setWifiEnabled(true);
  }

  /**
   * To enable Wifi on a user's device.
   *
   * @param callbacks The listener to return results to
   *
   * @see #runOnWiseFyThread(Runnable)
   * @see EnableWifiCallbacks
   * @see WiseFyCodeDefs
   * @see WiseFyPrerequisites#getWifiManager()
   * @see WiseFyPrerequisites#hasPrerequisites()
   */
  @Async
  @WiseFyThread
  public void enableWifi(@Nullable final EnableWifiCallbacks callbacks) {
    runOnWiseFyThread(() -> {
      synchronized (wisefyLock) {
        final int precheckResult = wiseFyPrechecks.enableWifiChecks();
        if (WiseFyPrechecks.checksFailed(precheckResult)) {
          if (callbacks != null) {
            callbacks.enableWifiWiseFyFailure(precheckResult);
          }
          return;
        }

        if (wisefyPrerequisites.getWifiManager().setWifiEnabled(true)) {
          if (callbacks != null) {
            callbacks.wifiEnabled();
          }
        } else {
          if (callbacks != null) {
            callbacks.failureEnablingWifi();
          }
        }
      }
    });
  }

  /**
   * To retrieve the user's current network.
   *
   * @return WifiInfo|null - The user's current network information
   *
   * @see WiseFyPrerequisites#getWifiManager()
   * @see WiseFyPrerequisites#hasPrerequisites()
   */
  @Nullable
  @Sync
  @CallingThread
  public WifiInfo getCurrentNetwork() {
    if (WiseFyPrechecks.checksFailed(wiseFyPrechecks.getCurrentNetworkChecks())) {
      return null;
    }
    return wisefyPrerequisites.getWifiManager().getConnectionInfo();
  }

  /**
   * To retrieve the user's current network.
   *
   * @param callbacks The listener to return results to
   *
   * @see #runOnWiseFyThread(Runnable)
   * @see GetCurrentNetworkCallbacks
   * @see WiseFyCodeDefs
   * @see WiseFyPrerequisites#getWifiManager()
   * @see WiseFyPrerequisites#hasPrerequisites()
   */
  @Async
  @WiseFyThread
  public void getCurrentNetwork(@Nullable final GetCurrentNetworkCallbacks callbacks) {
    runOnWiseFyThread(() -> {
      synchronized (wisefyLock) {
        final int precheckResult = wiseFyPrechecks.getCurrentNetworkChecks();
        if (WiseFyPrechecks.checksFailed(precheckResult)) {
          if (callbacks != null) {
            callbacks.getCurrentNetworkWiseFyFailure(precheckResult);
          }
          return;
        }

        if (callbacks != null) {
          callbacks.retrievedCurrentNetwork(wisefyPrerequisites.getWifiManager().getConnectionInfo());
        }
      }
    });
  }

  /**
   * To retrieve the frequency of the device's current network.
   *
   * @return Integer - The frequency of the devices current network or null if no network
   *
   * @see #getCurrentNetwork()
   */
  @Nullable
  @Sync
  @CallingThread
  @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
  public Integer getFrequency() {
    final WifiInfo currentNetwork = getCurrentNetwork();
    if (currentNetwork != null) {
      return currentNetwork.getFrequency();
    }
    return null;
  }

  /**
   * To retrieve the frequency of the device's current network.
   *
   * @param callbacks The listener to return results to
   *
   * @see #runOnWiseFyThread(Runnable)
   * @see #getCurrentNetwork()
   * @see GetFrequencyCallbacks
   */
  @Async
  @WiseFyThread
  @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
  public void getFrequency(@Nullable final GetFrequencyCallbacks callbacks) {
    runOnWiseFyThread(() -> {
      synchronized (wisefyLock) {
        final WifiInfo currentNetwork = WiseFy.this.getCurrentNetwork();
        if (callbacks != null) {
          if (currentNetwork != null) {
            callbacks.retrievedFrequency(currentNetwork.getFrequency());
          } else {
            callbacks.failureGettingFrequency();
          }
        }
      }
    });
  }

  /**
   * To retrieve the frequency of a network.
   *
   * @param network The network to return the frequency of
   *
   * @return Integer - The frequency of the devices current network or null if no network
   */
  @Nullable
  @Sync
  @CallingThread
  @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
  public Integer getFrequency(@Nullable final WifiInfo network) {
    if (network != null) {
      return network.getFrequency();
    }
    return null;
  }

  /**
   * To retrieve the frequency of a network.
   *
   * @param network The network to return the frequency of
   * @param callbacks The listener to return results to
   *
   * @see #runOnWiseFyThread(Runnable)
   * @see GetFrequencyCallbacks
   * @see WiseFyCodeDefs
   */
  @Async
  @WiseFyThread
  @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
  public void getFrequency(@Nullable final WifiInfo network, @Nullable final GetFrequencyCallbacks callbacks) {
    runOnWiseFyThread(() -> {
      synchronized (wisefyLock) {
        if (network != null && callbacks != null) {
          callbacks.retrievedFrequency(network.getFrequency());
        }
        if (callbacks != null) {
          callbacks.getFrequencyWiseFyFailure(WiseFyCodeDefs.MISSING_PARAMETER);
        }
      }
    });
  }

  /**
   * To retrieve the IPv4 or IPv6 of a device.
   *
   * @return String - The IPv4 or IPv6 address
   *
   * @see InetAddress
   * @see WiseFyPrerequisites#hasPrerequisites()
   * @see WiseFyPrerequisites#getWifiManager()
   */
  @Nullable
  public String getIP() {
    if (WiseFyPrechecks.checksFailed(wiseFyPrechecks.getIPChecks())) {
      return null;
    }

    final byte[] ipAddress = BigInteger.valueOf(wisefyPrerequisites.getWifiManager().getConnectionInfo().getIpAddress()).toByteArray();
    String ipAddressToReturn = null;
    try {
      ipAddressToReturn = InetAddress.getByAddress(ipAddress).getHostAddress();
    } catch (UnknownHostException uhe) {
      WiseFyLogger.log().error(TAG, uhe, "UnknownHostException while gathering IP (sync)");
    }
    return ipAddressToReturn;
  }

  /**
   * To retrieve the IPv4 or IPv6 of a device.
   *
   * @param callbacks The listener to return results to
   *
   * @see GetIPCallbacks
   * @see InetAddress
   * @see WiseFyPrerequisites#hasPrerequisites()
   * @see WiseFyPrerequisites#getWifiManager()
   */
  public void getIP(@Nullable final GetIPCallbacks callbacks) {
    runOnWiseFyThread(() -> {
      synchronized (wisefyLock) {
        final int precheckResult = wiseFyPrechecks.getIPChecks();
        if (WiseFyPrechecks.checksFailed(precheckResult)) {
          if (callbacks != null) {
            callbacks.getIPWiseFyFailure(WiseFyCodeDefs.MISSING_PREREQUISITE);
          }
          return;
        }

        final byte[] ipAddress = BigInteger.valueOf(wisefyPrerequisites.getWifiManager().getConnectionInfo().getIpAddress()).toByteArray();
        if (callbacks != null) {
          try {
            callbacks.retrievedIP(InetAddress.getByAddress(ipAddress).getHostAddress());
          } catch (UnknownHostException uhe) {
            WiseFyLogger.log().error(TAG, uhe, "UnknownHostException while gathering IP (async)");
            callbacks.failureRetrievingIP();
          }
        }
      }
    });
  }

  /**
   * To retrieve a list of nearby access points.
   *
   * <p>*NOTE* Setting filterDuplicates to true will exclude access points for an SSID that have a weaker RSSI
   * (will always take the highest signal strength).</p>
   *
   * @param filterDuplicates If you want to exclude SSIDs with that same name that have a weaker signal strength
   *
   * @return List of ScanResults|null - List of nearby access points
   *
   * @see WiseFyPrerequisites#getWifiManager()
   * @see WiseFyPrerequisites#hasPrerequisites()
   * @see WiseFySearch#removeEntriesWithLowerSignalStrength(List)
   */
  @Nullable
  @Sync
  @CallingThread
  public List<ScanResult> getNearbyAccessPoints(final boolean filterDuplicates) {
    if (WiseFyPrechecks.checksFailed(wiseFyPrechecks.getNearbyAccessPointsChecks())) {
      return null;
    }

    wisefyPrerequisites.getWifiManager().startScan();
    final List<ScanResult> accessPoints;
    if (filterDuplicates) {
      accessPoints = wisefySearch.removeEntriesWithLowerSignalStrength(wisefyPrerequisites.getWifiManager().getScanResults());
    } else {
      accessPoints = wisefyPrerequisites.getWifiManager().getScanResults();
    }
    return accessPoints;
  }

  /**
   * To retrieve a list of nearby access points.
   *
   * <p>*NOTE* Setting filterDuplicates to true will not return SSIDs with a weaker signal strength (will always take the highest).</p>
   *
   * @param filterDuplicates If you want to exclude SSIDs with that same name that have a weaker signal strength
   * @param callbacks The listener to return results to
   *
   * @see #runOnWiseFyThread(Runnable)
   * @see GetNearbyAccessPointsCallbacks
   * @see WiseFyCodeDefs
   * @see WiseFyPrerequisites#getWifiManager()
   * @see WiseFyPrerequisites#hasPrerequisites()
   * @see WiseFySearch#removeEntriesWithLowerSignalStrength(List)
   */
  @Async
  @WiseFyThread
  public void getNearbyAccessPoints(final boolean filterDuplicates, @Nullable final GetNearbyAccessPointsCallbacks callbacks) {
    runOnWiseFyThread(() -> {
      synchronized (wisefyLock) {
        final int precheckResult = wiseFyPrechecks.getNearbyAccessPointsChecks();
        if (WiseFyPrechecks.checksFailed(precheckResult)) {
          if (callbacks != null) {
            callbacks.getNearbyAccessPointsWiseFyFailure(precheckResult);
          }
          return;
        }

        wisefyPrerequisites.getWifiManager().startScan();
        if (callbacks != null) {
          if (filterDuplicates) {
            callbacks.retrievedNearbyAccessPoints(
                    wisefySearch.removeEntriesWithLowerSignalStrength(wisefyPrerequisites.getWifiManager().getScanResults())
            );
          } else {
            callbacks.retrievedNearbyAccessPoints(wisefyPrerequisites.getWifiManager().getScanResults());
          }
        }
      }
    });
  }

  /**
   * To retrieve the RSSI of the first network matching a given regex.
   *
   * <p>*NOTE* Setting takeHighest to true will return the access point with the highest RSSI for the given SSID.</p>
   *
   * @param regexForSSID The regex to be used to search for the ssid
   * @param takeHighest Whether to return the access point with the highest RSSI for the given SSID
   * @param timeoutInMillis The amount of time to search for a matching SSID
   *
   * @return Integer - The RSSI value for the found SSID or null if no matching network found
   *
   * @see WiseFyPrerequisites#hasPrerequisites()
   * @see WiseFySearch#findAccessPointByRegex(String, Integer, boolean)
   */
  @Nullable
  @Sync
  @CallingThread
  @WaitsForTimeout
  public Integer getRSSI(final String regexForSSID, final boolean takeHighest, final int timeoutInMillis) {
    if (WiseFyPrechecks.checksFailed(wiseFyPrechecks.getRSSIChecks(regexForSSID))) {
      return null;
    }

    final ScanResult accessPoint = wisefySearch.findAccessPointByRegex(regexForSSID, timeoutInMillis, takeHighest);
    return accessPoint != null ? accessPoint.level : null;
  }

  /**
   * To retrieve the RSSI of the first network matching a given regex.
   *
   * <p>*NOTE* Setting takeHighest to true will return the access point with the highest RSSI for the given SSID.</p>
   *
   * @param regexForSSID The regex to be used to search for the ssid
   * @param takeHighest Whether to return the access point with the highest RSSI for the given SSID
   * @param timeoutInMillis The amount of time to search for a matching SSID
   * @param callbacks The listener to return results to
   *
   * @see #runOnWiseFyThread(Runnable)
   * @see GetRSSICallbacks
   * @see WiseFyCodeDefs
   * @see WiseFyPrerequisites#hasPrerequisites()
   * @see WiseFySearch#findAccessPointByRegex(String, Integer, boolean)
   */
  @Async
  @WiseFyThread
  @WaitsForTimeout
  public void getRSSI(final String regexForSSID, final boolean takeHighest, final int timeoutInMillis, @Nullable final GetRSSICallbacks callbacks) {
    runOnWiseFyThread(() -> {
      synchronized (wisefyLock) {
        final int precheckResult = wiseFyPrechecks.getRSSIChecks(regexForSSID);
        if (WiseFyPrechecks.checksFailed(precheckResult)) {
          if (callbacks != null) {
            callbacks.getRSSIWiseFyFailure(precheckResult);
          }
          return;
        }

        final ScanResult accessPoint = wisefySearch.findAccessPointByRegex(regexForSSID, timeoutInMillis, takeHighest);
        if (callbacks != null) {
          if (accessPoint != null) {
            callbacks.retrievedRSSI(accessPoint.level);
          } else {
            callbacks.networkNotFoundToRetrieveRSSI();
          }
        }
      }
    });
  }

  /**
   * To search for and return a saved WiFiConfiguration given an SSID.
   *
   * @param regexForSSID The ssid to use while searching for saved configuration
   *
   * @return WifiConfiguration|null - Saved network that matches the ssid
   *
   * @see WiseFyPrerequisites#hasPrerequisites()
   * @see WiseFySearch#findSavedNetworkByRegex(String)
   */
  @Nullable
  @Sync
  @CallingThread
  public WifiConfiguration getSavedNetwork(final String regexForSSID) {
    if (WiseFyPrechecks.checksFailed(wiseFyPrechecks.getSavedNetworkChecks(regexForSSID))) {
      return null;
    }
    return wisefySearch.findSavedNetworkByRegex(regexForSSID);
  }

  /**
   * To search for and return a saved WiFiConfiguration given an SSID.
   *
   * @param regexForSSID The ssid to use while searching for saved configuration
   * @param callbacks The listener to return results to
   *
   * @see #runOnWiseFyThread(Runnable)
   * @see GetSavedNetworkCallbacks
   * @see WiseFyCodeDefs
   * @see WiseFyPrerequisites#hasPrerequisites()
   * @see WiseFySearch#findSavedNetworkByRegex(String)
   */
  @Async
  @WiseFyThread
  public void getSavedNetwork(final String regexForSSID, @Nullable final GetSavedNetworkCallbacks callbacks) {
    runOnWiseFyThread(() -> {
      synchronized (wisefyLock) {
        final int precheckResult = wiseFyPrechecks.getSavedNetworkChecks(regexForSSID);
        if (WiseFyPrechecks.checksFailed(precheckResult)) {
          if (callbacks != null) {
            callbacks.getSavedNetworkWiseFyFailure(precheckResult);
          }
          return;
        }

        final WifiConfiguration savedNetwork = wisefySearch.findSavedNetworkByRegex(regexForSSID);
        if (callbacks != null) {
          if (savedNetwork != null) {
            callbacks.retrievedSavedNetwork(savedNetwork);
          } else {
            callbacks.savedNetworkNotFound();
          }
        }
      }
    });
  }

  /**
   * To retrieve a list of saved networks on a user's device.
   *
   * @return List of WifiConfiguration|null - List of saved networks on a users device
   *
   * @see WiseFyPrerequisites#getWifiManager()
   * @see WiseFyPrerequisites#hasPrerequisites()
   */
  @Nullable
  @Sync
  @CallingThread
  public List<WifiConfiguration> getSavedNetworks() {
    if (WiseFyPrechecks.checksFailed(wiseFyPrechecks.getSavedNetworksChecks())) {
      return null;
    }
    return wisefyPrerequisites.getWifiManager().getConfiguredNetworks();
  }

  /**
   * To retrieve a list of saved networks on a user's device.
   *
   * @param callbacks The listener to return results to
   *
   * @see #runOnWiseFyThread(Runnable)
   * @see GetSavedNetworksCallbacks
   * @see WiseFyCodeDefs
   * @see WiseFyPrerequisites#getWifiManager()
   * @see WiseFyPrerequisites#hasPrerequisites()
   */
  @Async
  @WiseFyThread
  public void getSavedNetworks(@Nullable final GetSavedNetworksCallbacks callbacks) {
    runOnWiseFyThread(() -> {
      synchronized (wisefyLock) {
        final int precheckResult = wiseFyPrechecks.getSavedNetworksChecks();
        if (WiseFyPrechecks.checksFailed(precheckResult)) {
          if (callbacks != null) {
            callbacks.getSavedNetworksWiseFyFailure(precheckResult);
          }
          return;
        }

        final List<WifiConfiguration> savedNetworks = wisefyPrerequisites.getWifiManager().getConfiguredNetworks();
        if (callbacks != null) {
          if (savedNetworks != null && !savedNetworks.isEmpty()) {
            callbacks.retrievedSavedNetworks(savedNetworks);
          } else {
            callbacks.noSavedNetworksFound();
          }
        }
      }
    });
  }

  /**
   * To retrieve a list of saved networks on a user's device that match a given regex.
   *
   * @param regexForSSID The ssid to use while searching for saved configurations
   *
   * @return List of WifiConfigurations|null - The list of saved network configurations that match the given regex
   *
   * @see WiseFyPrerequisites#hasPrerequisites()
   * @see WiseFySearch#findSavedNetworkByRegex(String)
   */
  @Nullable
  @Sync
  @CallingThread
  public List<WifiConfiguration> getSavedNetworks(final String regexForSSID) {
    if (WiseFyPrechecks.checksFailed(wiseFyPrechecks.getSavedNetworksChecks(regexForSSID))) {
      return null;
    }
    return wisefySearch.findSavedNetworksMatchingRegex(regexForSSID);
  }

  /**
   * To retrieve a list of saved networks on a user's device that match a given regex.
   *
   * @param regexForSSID The ssid to use while searching for saved configurations
   * @param callbacks The listener to return results to
   *
   * @see #runOnWiseFyThread(Runnable)
   * @see WiseFyPrerequisites#hasPrerequisites()
   * @see WiseFySearch#findSavedNetworkByRegex(String)
   */
  @Async
  @WiseFyThread
  public void getSavedNetworks(final String regexForSSID, @Nullable final GetSavedNetworksCallbacks callbacks) {
    runOnWiseFyThread(() -> {
      synchronized (wisefyLock) {
        final int precheckResult = wiseFyPrechecks.getSavedNetworksChecks(regexForSSID);
        if (WiseFyPrechecks.checksFailed(precheckResult)) {
          if (callbacks != null) {
            callbacks.getSavedNetworksWiseFyFailure(precheckResult);
          }
          return;
        }

        if (callbacks != null) {
          final List<WifiConfiguration> savedNetworks = wisefySearch.findSavedNetworksMatchingRegex(regexForSSID);
          if (savedNetworks != null && !savedNetworks.isEmpty()) {
            callbacks.retrievedSavedNetworks(savedNetworks);
          } else {
            callbacks.noSavedNetworksFound();
          }
        }
      }
    });
  }

  /**
   * To retrieve the lock in use by WiseFy for synchronization.
   *
   * @return WiseFyLock - The instance of the lock in use by WiseFy
   *
   * @see WiseFyLock
   */
  @NonNull
  public WiseFyLock getWiseFyLock() {
    return wisefyLock;
  }

  /**
   * To check if the device is connected to a mobile network.
   *
   * @return bool - If the device is currently connected to a mobile network
   *
   * @see NetworkTypeDefs
   * @see WiseFyConnection#isNetworkConnectedAndMatchesType(NetworkInfo, String)
   * @see WiseFyPrerequisites#getConnectivityManager()
   * @see WiseFyPrerequisites#hasPrerequisites()
   */
  @Sync
  @CallingThread
  public boolean isDeviceConnectedToMobileNetwork() {
    return WiseFyPrechecks.checksPassed(wiseFyPrechecks.isDeviceConnectedToMobileNetworkChecks())
            && wisefyConnection.isNetworkConnectedAndMatchesType(wisefyPrerequisites.getConnectivityManager().getActiveNetworkInfo(),
            NetworkTypeDefs.MOBILE);
  }

  /**
   * To check if the device is connected to a mobile or wifi network.
   *
   * @return bool - If the device is currently connected to a mobile or wifi network
   *
   * @see WiseFyConnection#isNetworkConnected(NetworkInfo)
   * @see WiseFyPrerequisites#getConnectivityManager()
   * @see WiseFyPrerequisites#hasPrerequisites()
   */
  @Sync
  @CallingThread
  public boolean isDeviceConnectedToMobileOrWifiNetwork() {
    return WiseFyPrechecks.checksPassed(wiseFyPrechecks.isDeviceConnectedToMobileOrWifiNetworkChecks())
            && wisefyConnection.isNetworkConnected(wisefyPrerequisites.getConnectivityManager().getActiveNetworkInfo());
  }

  /**
   * To check if the device is connected to a given SSID.
   *
   * @param ssid The SSID to check if the device is attached to
   *
   * @return bool - If the device is currently attached to the given SSID
   *
   * @see WiseFyConnection#isCurrentNetworkConnectedToSSID(String)
   * @see WiseFyPrerequisites#hasPrerequisites()
   */
  @Sync
  @CallingThread
  public boolean isDeviceConnectedToSSID(final String ssid) {
    return WiseFyPrechecks.checksPassed(wiseFyPrechecks.isDeviceConnectedToSSIDChecks(ssid))
            && wisefyConnection.isCurrentNetworkConnectedToSSID(ssid);
  }

  /**
   * To check if the device is connected to a wifi network.
   *
   * @return bool - If the device is currently connected to a wifi network
   *
   * @see NetworkTypeDefs
   * @see WiseFyConnection#isNetworkConnectedAndMatchesType(NetworkInfo, String)
   * @see WiseFyPrerequisites#getConnectivityManager()
   * @see WiseFyPrerequisites#hasPrerequisites()
   */
  @Sync
  @CallingThread
  public boolean isDeviceConnectedToWifiNetwork() {
    return WiseFyPrechecks.checksPassed(wiseFyPrechecks.isDeviceConnectedToWifiNetworkChecks())
            && wisefyConnection.isNetworkConnectedAndMatchesType(wisefyPrerequisites.getConnectivityManager().getActiveNetworkInfo(),
            NetworkTypeDefs.WIFI);
  }

  /**
   * To query if the device is roaming.
   *
   * @return boolean - If the current network is roaming
   *
   * @see WiseFyPrerequisites#getConnectivityManager()
   * @see WiseFyPrerequisites#hasPrerequisites()
   */
  @Sync
  @CallingThread
  public boolean isDeviceRoaming() {
    if (WiseFyPrechecks.checksFailed(wiseFyPrechecks.isDeviceRoamingChecks())) {
      return false;
    }

    final NetworkInfo networkInfo = wisefyPrerequisites.getConnectivityManager().getActiveNetworkInfo();
    return networkInfo != null && networkInfo.isRoaming();
  }

  /**
   * To query if logging is enabled or disabled for a WiseFy instance.
   *
   * @return boolean - If logging is enabled for the WiseFy instance
   */
  @Sync
  @CallingThread
  public boolean isLoggingEnabled() {
    return WiseFyLogger.log().isLoggingEnabled();
  }

  /**
   * To check if the device's current network is 5gHz.
   *
   * @return boolean - If the network is 5gHz
   *
   * @see #getFrequency()
   * @see #MIN_FREQUENCY_5GHZ
   * @see #MAX_FREQUENCY_5GHZ
   */
  @Sync
  @CallingThread
  @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
  public boolean isNetwork5gHz() {
    final Integer frequency = getFrequency();
    return frequency != null && frequency > MIN_FREQUENCY_5GHZ && frequency < MAX_FREQUENCY_5GHZ;
  }

  /**
   * To check if a given network is 5gHz.
   *
   * @param network The network to check if it's 5gHz
   *
   * @return boolean - If the network is 5gHz
   *
   * @see #getFrequency(WifiInfo)
   * @see #MIN_FREQUENCY_5GHZ
   * @see #MAX_FREQUENCY_5GHZ
   */
  @Sync
  @CallingThread
  @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
  public boolean isNetwork5gHz(final WifiInfo network) {
    final Integer frequency = getFrequency(network);
    return frequency != null && frequency > MIN_FREQUENCY_5GHZ && frequency < MAX_FREQUENCY_5GHZ;
  }

  /**
   * To check and return if a network is a EAP network.
   *
   * @param scanResult The network to check
   *
   * @return boolean - Whether the network has EAP capabilities listed
   *
   * @see CapabilityDefs
   * @see #containsCapability(ScanResult, String)
   */
  @Sync
  @CallingThread
  public boolean isNetworkEAP(final ScanResult scanResult) {
    return containsCapability(scanResult, CapabilityDefs.EAP);
  }

  /**
   * To check and return if a network is a PSK network.
   *
   * @param scanResult The network to check
   *
   * @return boolean - Whether the network has PSK capabilities listed
   *
   * @see CapabilityDefs
   * @see #containsCapability(ScanResult, String)
   */
  @Sync
  @CallingThread
  public boolean isNetworkPSK(final ScanResult scanResult) {
    return containsCapability(scanResult, CapabilityDefs.PSK);
  }

  /**
   * To check if an SSID is in the list of configured networks.
   *
   * @param ssid The SSID to check and see if it's in the list of configured networks
   *
   * @return boolean - If the SSID is in the list of configured networks
   *
   * @see WiseFyPrerequisites#hasPrerequisites()
   * @see WiseFySearch#isNetworkASavedConfiguration(String)
   */
  @Sync
  @CallingThread
  public boolean isNetworkSaved(final String ssid) {
    return WiseFyPrechecks.checksPassed(wiseFyPrechecks.isNetworkSavedChecks()) && wisefySearch.isNetworkASavedConfiguration(ssid);
  }

  /**
   * To check and return if a network is secure (WEP/WPA/WPA2 capabilities).
   *
   * @param scanResult The network to see if it is secure
   *
   * @return boolean - Whether the network is secure
   *
   * @see CapabilityDefs
   */
  @Sync
  @CallingThread
  public boolean isNetworkSecure(@Nullable final ScanResult scanResult) {
    if (scanResult != null && scanResult.capabilities != null) {
      final String networkCapabilities = scanResult.capabilities;
      final String[] securityModes = {CapabilityDefs.EAP, CapabilityDefs.PSK, CapabilityDefs.WEP, CapabilityDefs.WPA, CapabilityDefs.WPA2};
      for (int i = securityModes.length - 1; i >= 0; i--) {
        if (networkCapabilities.contains(securityModes[i])) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * To check and return if a network is a WEP network.
   *
   * @param scanResult The network to check
   *
   * @return boolean - Whether the network has WEP capabilities listed
   *
   * @see CapabilityDefs
   * @see #containsCapability(ScanResult, String)
   */
  @Sync
  @CallingThread
  public boolean isNetworkWEP(final ScanResult scanResult) {
    return containsCapability(scanResult, CapabilityDefs.WEP);
  }

  /**
   * To check and return if a network is a WPA network.
   *
   * @param scanResult The network to check
   *
   * @return boolean - Whether the network has WPA capabilities listed
   *
   * @see CapabilityDefs
   * @see #containsCapability(ScanResult, String)
   */
  @Sync
  @CallingThread
  public boolean isNetworkWPA(final ScanResult scanResult) {
    return containsCapability(scanResult, CapabilityDefs.WPA);
  }

  /**
   * To check and return if a network is a WPA2 network.
   *
   * @param scanResult The network to check
   *
   * @return boolean - Whether the network has WPA2 capabilities listed
   *
   * @see CapabilityDefs
   * @see #containsCapability(ScanResult, String)
   */
  @Sync
  @CallingThread
  public boolean isNetworkWPA2(final ScanResult scanResult) {
    return containsCapability(scanResult, CapabilityDefs.WPA2);
  }

  /**
   * To check if Wifi is enabled on the device or not.
   *
   * @return boolean - if Wifi is enabled on device
   *
   * @see WiseFyPrerequisites#getWifiManager()
   * @see WiseFyPrerequisites#hasPrerequisites()
   */
  @Sync
  @CallingThread
  public boolean isWifiEnabled() {
    return WiseFyPrechecks.checksPassed(wiseFyPrechecks.isWifiEnabledChecks()) && wisefyPrerequisites.getWifiManager().isWifiEnabled();
  }

  /**
   * To remove a configured network.
   *
   * @param ssidToRemove The ssid of the network you want to remove from the configured network list
   *
   * @return boolean - If the command succeeded in removing the network
   *
   * @see WiseFyPrerequisites#hasPrerequisites()
   * @see WiseFySearch#findSavedNetworkByRegex(String)
   */
  @Sync
  @CallingThread
  public boolean removeNetwork(final String ssidToRemove) {
    if (WiseFyPrechecks.checksFailed(wiseFyPrechecks.removeNetworkCheck(ssidToRemove))) {
      return false;
    }

    final WifiConfiguration wifiConfiguration = wisefySearch.findSavedNetworkByRegex(ssidToRemove);
    boolean networkRemoved = false;
    if (wifiConfiguration != null) {
      networkRemoved = removeNetworkConfiguration(wifiConfiguration);
    } else {
      WiseFyLogger.log().warn(TAG, "SSID to remove: %s was not found in list to remove network", ssidToRemove);
    }
    return networkRemoved;
  }

  /**
   * To remove a configured network.
   *
   * @param ssidToRemove The ssid of the network you want to remove from the configured network list
   * @param callbacks The listener to return results to
   *
   * @see #runOnWiseFyThread(Runnable)
   * @see RemoveNetworkCallbacks
   * @see WiseFyCodeDefs
   * @see WiseFyPrerequisites#hasPrerequisites()
   * @see WiseFyPrerequisites#getWifiManager()
   * @see WiseFySearch#findSavedNetworkByRegex(String)
   */
  @Async
  @WiseFyThread
  public void removeNetwork(final String ssidToRemove, @Nullable final RemoveNetworkCallbacks callbacks) {
    runOnWiseFyThread(() -> {
      synchronized (wisefyLock) {
        final int precheckResult = wiseFyPrechecks.removeNetworkCheck(ssidToRemove);
        if (WiseFyPrechecks.checksFailed(precheckResult)) {
          if (callbacks != null) {
            callbacks.removeNetworkWiseFyFailure(precheckResult);
          }
          return;
        }

        final WifiConfiguration wifiConfiguration = wisefySearch.findSavedNetworkByRegex(ssidToRemove);
        if (wifiConfiguration != null) {
          if (WiseFy.this.removeNetworkConfiguration(wifiConfiguration)) {
            if (callbacks != null) {
              callbacks.networkRemoved();
            }
          } else {
            if (callbacks != null) {
              callbacks.failureRemovingNetwork();
            }
          }
        } else {
          if (callbacks != null) {
            callbacks.networkNotFoundToRemove();
          }
        }
      }
    });
  }

  /**
   * To return the first access point that matches a given regex.
   *
   * <p>*NOTE* Setting filterDuplicates to true will not return an access point with a weaker signal strength (will always take the highest).</p>
   *
   * @param regexForSSID The regex to use when iterating through nearby access points
   * @param timeoutInMillis The amount of time (in milliseconds) to wait for a matching access point
   * @param filterDuplicates If you want to exclude access points with the same name that have a weaker signal strength
   *
   * @return ScanResult|null - The first access point or access point with the highest signal strength matching the regex
   *
   * @see WiseFyPrerequisites#hasPrerequisites()
   * @see WiseFySearch#findAccessPointByRegex(String, Integer, boolean)
   */
  @Nullable
  @Sync
  @CallingThread
  public ScanResult searchForAccessPoint(final String regexForSSID, final int timeoutInMillis, final boolean filterDuplicates) {
    if (WiseFyPrechecks.checksFailed(wiseFyPrechecks.searchForAccessPointChecks(regexForSSID))) {
      return null;
    }

    return wisefySearch.findAccessPointByRegex(regexForSSID, timeoutInMillis, filterDuplicates);
  }

  /**
   * To return the first access point that matches a given regex.
   *
   * <p>*NOTE* Setting filterDuplicates to true will not return an access point with a weaker signal strength (will always take the highest).</p>
   *
   * @param regexForSSID The regex to use when iterating through nearby access points
   * @param timeoutInMillis The amount of time (in milliseconds) to wait for a matching access point
   * @param filterDuplicates If you want to exclude access points with the same name that have a weaker signal strength
   * @param callbacks The listener to return results to
   *
   * @see #runOnWiseFyThread(Runnable)
   * @see SearchForAccessPointCallbacks
   * @see WiseFyPrerequisites#hasPrerequisites()
   * @see WiseFySearch#findAccessPointByRegex(String, Integer, boolean)
   */
  @Async
  @WiseFyThread
  public void searchForAccessPoint(final String regexForSSID, final int timeoutInMillis, final boolean filterDuplicates,
                                   @Nullable final SearchForAccessPointCallbacks callbacks) {
    runOnWiseFyThread(() -> {
      synchronized (wisefyLock) {
        final int precheckResult = wiseFyPrechecks.searchForAccessPointChecks(regexForSSID);
        if (WiseFyPrechecks.checksFailed(precheckResult)) {
          if (callbacks != null) {
            callbacks.searchForAccessPointWiseFyFailure(precheckResult);
          }
          return;
        }

        final ScanResult scanResult = wisefySearch.findAccessPointByRegex(regexForSSID, timeoutInMillis, filterDuplicates);
        if (callbacks != null) {
          if (scanResult != null) {
            callbacks.accessPointFound(scanResult);
          } else {
            callbacks.accessPointNotFound();
          }
        }
      }
    });
  }

  /**
   * To return nearby access points that match a given regex.
   *
   * <p>*NOTE* Setting filterDuplicates to true will not return access points with a weaker signal strength (will always take the highest).</p>
   *
   * @param regexForSSID The regex to use when iterating through nearby access points
   * @param filterDuplicates If you want to exclude access points with the same name that have a weaker signal strength
   *
   * @return List of ScanResult|null - The list of matching access points or null if none match the given regex
   *
   * @see WiseFyPrerequisites#hasPrerequisites()
   * @see WiseFySearch#findAccessPointsMatchingRegex(String, boolean)
   */
  @Nullable
  @Sync
  @CallingThread
  public List<ScanResult> searchForAccessPoints(final String regexForSSID, final boolean filterDuplicates) {
    if (WiseFyPrechecks.checksFailed(wiseFyPrechecks.searchForAccessPointsChecks(regexForSSID))) {
      return null;
    }

    return wisefySearch.findAccessPointsMatchingRegex(regexForSSID, filterDuplicates);
  }

  /**
   * To return nearby access points that match a given regex.
   *
   * <p>*NOTE* Setting filterDuplicates to true will not return access points with a weaker signal strength (will always take the highest).</p>
   *
   * @param regexForSSID The regex to use when iterating through nearby access points
   * @param filterDuplicates If you want to exclude access points with the same name that have a weaker signal strength
   * @param callbacks The listener to return results to
   *
   * @see #runOnWiseFyThread(Runnable)
   * @see SearchForAccessPointsCallbacks
   * @see WiseFyCodeDefs
   * @see WiseFyPrerequisites#hasPrerequisites()
   * @see WiseFySearch#findAccessPointsMatchingRegex(String, boolean)
   */
  @Async
  @WiseFyThread
  public void searchForAccessPoints(final String regexForSSID, final boolean filterDuplicates,
                                    @Nullable final SearchForAccessPointsCallbacks callbacks) {
    runOnWiseFyThread(() -> {
      synchronized (wisefyLock) {
        final int precheckResult = wiseFyPrechecks.searchForAccessPointsChecks(regexForSSID);
        if (WiseFyPrechecks.checksFailed(precheckResult)) {
          if (callbacks != null) {
            callbacks.searchForAccessPointsWiseFyFailure(precheckResult);
          }
          return;
        }

        final List<ScanResult> networks = wisefySearch.findAccessPointsMatchingRegex(regexForSSID, filterDuplicates);
        if (callbacks != null) {
          if (networks != null) {
            callbacks.foundAccessPoints(networks);
          } else {
            callbacks.noAccessPointsFound();
          }
        }
      }
    });
  }

  /**
   * To search local networks and return the first one that contains a given ssid.
   *
   * @param regexForSSID The regex to be used to search for the ssid
   * @param timeoutInMillis The number of milliseconds to keep searching for the SSID
   *
   * @return String|null - The first SSID that contains the search ssid (if any, else null)
   *
   * @see WiseFyPrerequisites#hasPrerequisites()
   * @see WiseFySearch#findAccessPointByRegex(String, Integer, boolean)
   */
  @Nullable
  @Sync
  @CallingThread
  @WaitsForTimeout
  public String searchForSSID(final String regexForSSID, final int timeoutInMillis) {
    if (WiseFyPrechecks.checksFailed(wiseFyPrechecks.searchForSSIDChecks(regexForSSID))) {
      return null;
    }

    final ScanResult scanResult = wisefySearch.findAccessPointByRegex(regexForSSID, timeoutInMillis, false);
    String ssid = null;
    if (scanResult != null) {
      ssid = scanResult.SSID;
    }
    return ssid;
  }

  /**
   * To search local networks and return the first one that contains a given ssid.
   *
   * @param regexForSSID The regex to be used to search for the ssid
   * @param timeoutInMillis The number of milliseconds to keep searching for the SSID
   * @param callbacks The listener to return results to
   *
   * @see #runOnWiseFyThread(Runnable)
   * @see SearchForSSIDCallbacks
   * @see WiseFyCodeDefs
   * @see WiseFySearch#findAccessPointByRegex(String, Integer, boolean)
   */
  @Async
  @WiseFyThread
  @WaitsForTimeout
  public void searchForSSID(final String regexForSSID, final int timeoutInMillis, @Nullable final SearchForSSIDCallbacks callbacks) {
    runOnWiseFyThread(() -> {
      synchronized (wisefyLock) {
        final int precheckResult = wiseFyPrechecks.searchForSSIDChecks(regexForSSID);
        if (WiseFyPrechecks.checksFailed(precheckResult)) {
          if (callbacks != null) {
            callbacks.searchForSSIDWiseFyFailure(precheckResult);
          }
          return;
        }

        final ScanResult scanResult = wisefySearch.findAccessPointByRegex(regexForSSID, timeoutInMillis, false);
        if (callbacks != null) {
          if (scanResult != null) {
            callbacks.ssidFound(scanResult.SSID);
          } else {
            callbacks.ssidNotFound();
          }
        }
      }
    });
  }

  /**
   * To search local networks and return the first one that contains a given ssid.
   *
   * @param regexForSSID The regex to be used to search for the ssid
   *
   * @return String|null - The first SSID that contains the search ssid (if any, else null)
   *
   * @see WiseFyPrerequisites#hasPrerequisites()
   * @see WiseFySearch#findSSIDsMatchingRegex(String)
   */
  @Nullable
  @Sync
  @CallingThread
  public List<String> searchForSSIDs(final String regexForSSID) {
    if (WiseFyPrechecks.checksFailed(wiseFyPrechecks.searchForSSIDsChecks(regexForSSID))) {
      return null;
    }

    return wisefySearch.findSSIDsMatchingRegex(regexForSSID);
  }

  /**
   * To search local networks and return the first one that contains a given ssid.
   *
   * @param regexForSSID The regex to be used to search for the ssid
   * @param callbacks The listener to return results to
   *
   * @see #runOnWiseFyThread(Runnable)
   * @see SearchForSSIDCallbacks
   * @see WiseFyCodeDefs
   * @see WiseFyPrerequisites#hasPrerequisites()
   * @see WiseFySearch#findSSIDsMatchingRegex(String)
   */
  @Async
  @WiseFyThread
  public void searchForSSIDs(final String regexForSSID, @Nullable final SearchForSSIDsCallbacks callbacks) {
    runOnWiseFyThread(() -> {
      synchronized (wisefyLock) {
        final int precheckResult = wiseFyPrechecks.searchForSSIDsChecks(regexForSSID);
        if (WiseFyPrechecks.checksFailed(precheckResult)) {
          if (callbacks != null) {
            callbacks.searchForSSIDsWiseFyFailure(precheckResult);
          }
          return;
        }

        final List<String> ssids = wisefySearch.findSSIDsMatchingRegex(regexForSSID);
        if (callbacks != null) {
          if (ssids != null) {
            callbacks.retrievedSSIDs(ssids);
          } else {
            callbacks.noSSIDsFound();
          }
        }
      }
    });
  }

  /*
   * HELPERS
   */

  /**
   * Used internally to add and save a new wifi configuration.
   *
   * @param wifiConfiguration The network configuration to add
   *
   * @return boolean - If the network was successfully added
   */
  private int addNetworkConfiguration(@NonNull final WifiConfiguration wifiConfiguration) {
    WiseFyLogger.log().debug(TAG, "Adding network with SSID %s", wifiConfiguration.SSID);
    final int result = wisefyPrerequisites.getWifiManager().addNetwork(wifiConfiguration);
    WiseFyLogger.log().debug(TAG, "Adding network: %s had result: %debug", wifiConfiguration.SSID, result);
    return result;
  }

  /**
   * Used internally to check if a network has a given capability.
   *
   * @param scanResult The network to check
   * @param capability The capability to check for
   *
   * @return boolean - True if the network contains the capability
   */
  private boolean containsCapability(@Nullable final ScanResult scanResult, @NonNull @Capabilities final String capability) {
    return scanResult != null && scanResult.capabilities != null && scanResult.capabilities.contains(capability);
  }

  /**
   * Used internally to connect to a network given it's id.
   *
   * @param networkId The network id to enable
   */
  private void connectToNetworkWithId(final int networkId) {
    wisefyPrerequisites.getWifiManager().disconnect();
    wisefyPrerequisites.getWifiManager().enableNetwork(networkId, true);
    wisefyPrerequisites.getWifiManager().reconnect();
  }

  /**
   * A method to execute logic on a background thread.
   *
   * @param runnable The block of code to execute in the background
   *
   * @see #setupWiseFyThread()
   */
  private void runOnWiseFyThread(final Runnable runnable) {
    if (wisefyHandler == null) {
      setupWiseFyThread();
    }
    wisefyHandler.post(runnable);
  }

  /**
   * Used internally to remove a network.
   *
   * @param wifiConfiguration The network to remove
   *
   * @return bool - true if the network was successfully removed
   */
  private boolean removeNetworkConfiguration(@NonNull final WifiConfiguration wifiConfiguration) {
    wisefyPrerequisites.getWifiManager().disconnect();
    final boolean result = wisefyPrerequisites.getWifiManager().removeNetwork(wifiConfiguration.networkId);
    WiseFyLogger.log().debug(TAG, "Removing network: %s had result: %b", wifiConfiguration.SSID, result);
    wisefyPrerequisites.getWifiManager().reconnect();
    return result;
  }

  /**
   * Used internally to setup a WiseFyThread to run background operations.
   *
   * @see #runOnWiseFyThread(Runnable)
   * @see WiseFyHandlerThread
   */
  private void setupWiseFyThread() {
    wisefyHandlerThread = new WiseFyHandlerThread(WiseFyHandlerThread.TAG);
    wisefyHandlerThread.start();
    final Looper looper = wisefyHandlerThread.getLooper();
    wisefyHandler = new Handler(looper);
  }

  /**
   * Static class for builder pattern.
   *
   * <p>Implements builder interfaces #{@link Logging} #{@link GetSmarts}</p>
   */
  public static class brains implements
          GetSmarts,
          Logging,
          SetCustomWiseFyConnection,
          SetCustomWiseFyPrechecks,
          SetCustomWiseFyPrerequisites,
          SetCustomWiseFySearch {

    private Context context;

    private boolean loggingEnabled;

    private WiseFyConnection wisefyConnection;
    private WiseFyPrechecks wiseFyPrechecks;
    private WiseFyPrerequisites wisefyPrerequisites;
    private WiseFySearch wisefySearch;

    /**
     * Mandatory - The public constructor for the builder that requires a context.
     *
     * @param context The activity or application context to get a WifiConfiguration and a ConnectivityManager instance
     */
    brains(final Context context) {
      this.context = context;
    }

    /**
     * Mandatory - To build and return a WiseFy instance.
     *
     * <p>*NOTE* Must be called after brains</p>
     *
     * @return WiseFy - The instance created by the builder
     *
     * @see brains
     */
    @NonNull
    @Override
    public WiseFy getSmarts() {
      return new WiseFy(this);
    }

    /**
     * Optional - Builder method that enables/disables logging for a WiseWy instance.
     *
     * @param enableLogging If logging will be enabled or disabled for an instance
     *
     * @return brains - The builder with the updated logging setting
     *
     * @see Logging
     */
    @NonNull
    @Override
    public brains logging(final boolean enableLogging) {
      this.loggingEnabled = enableLogging;
      return this;
    }

    /**
     * Optional - Builder method that allows a user to set a custom WiseFyConnection for a WiseFy instance.
     *
     * @param customWisefyConnection The custom WiseFyConnection class (i.error. a mock in tests)
     *
     * @return brains - The builder with the updated WiseFyConnection
     *
     * @see WiseFyConnection
     */
    @NonNull
    @Override
    public brains setCustomWiseFyConnection(final WiseFyConnection customWisefyConnection) {
      this.wisefyConnection = customWisefyConnection;
      return this;
    }


    /**
     * Optional - Builder method that allows a user to set a custom WiseFyPrechecks for a WiseFy instance.
     *
     * @param customWiseFyPrechecks The custom WiseFyPrechecks class (i.e. a mock in tests)
     *
     * @return brains - The builder with the updated WiseFyPrerequisites
     *
     * @see WiseFyPrechecks
     */
    @NonNull
    @Override
    public brains setCustomWiseFyPrechecks(final WiseFyPrechecks customWiseFyPrechecks) {
      this.wiseFyPrechecks = customWiseFyPrechecks;
      return this;
    }

    /**
     * Optional - Builder method that allows a user to set a custom WiseFyPrerequisites for a WiseFy instance.
     *
     * @param customWiseFyPrerequisites The custom WiseFyPrerequisites class (i.e. a mock in tests)
     *
     * @return brains - The builder with the updated WiseFyPrerequisites
     *
     * @see WiseFyConnection
     */
    @NonNull
    @Override
    public brains setCustomWiseFyPrerequisites(final WiseFyPrerequisites customWiseFyPrerequisites) {
      this.wisefyPrerequisites = customWiseFyPrerequisites;
      return this;
    }

    /**
     * Optional - Builder method that allows a user to set a custom WiseFySearch for a WiseFy instance.
     *
     * @param customWiseFySearch The custom WiseFySearch class (i.e. a mock in tests)
     *
     * @return brains - The builder with the updated WiseFySearch
     *
     * @see WiseFyConnection
     */
    @NonNull
    @Override
    public brains setCustomWiseFySearch(final WiseFySearch customWiseFySearch) {
      this.wisefySearch = customWiseFySearch;
      return this;
    }
  }

  /**
   * An interface that enables/disables logging for a WiseFy instance.
   */
  interface Logging {
    @NonNull
    brains logging(boolean enableLogging);
  }

  /**
   * An interface used for testing to provide a mock WiseFyConnection.
   *
   * @see WiseFyPrerequisites
   */
  interface SetCustomWiseFyConnection {
    @NonNull
    brains setCustomWiseFyConnection(WiseFyConnection customWisefyConnection);
  }

  /**
   * An interface used for testing to provide a mock WiseFyPrechecks.
   *
   * @see WiseFyPrechecks
   */
  interface SetCustomWiseFyPrechecks {
    @NonNull
    brains setCustomWiseFyPrechecks(WiseFyPrechecks wiseFyPrechecks);
  }

  /**
   * An interface used for testing to provide a mock WiseFyPrerequisites.
   *
   * @see WiseFyPrerequisites
   */
  interface SetCustomWiseFyPrerequisites {
    @NonNull
    brains setCustomWiseFyPrerequisites(WiseFyPrerequisites customWisefyPrerequisites);
  }

  /**
   * An interface used for testing to provide a mock WiseFySearch.
   *
   * @see WiseFySearch
   */
  interface SetCustomWiseFySearch {
    @NonNull
    brains setCustomWiseFySearch(WiseFySearch customWisefySearch);
  }

  /**
   * An interface that builds a WiseFy instance.
   */
  interface GetSmarts {
    @NonNull
    WiseFy getSmarts();
  }
}
