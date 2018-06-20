package com.isupatches.wisefy;

import static com.isupatches.wisefy.TestUtils.TEST_SSID;
import static com.isupatches.wisefy.TestUtils.TEST_TIMEOUT;

import static org.junit.Assert.fail;

import android.net.wifi.WifiInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.isupatches.wisefy.callbacks.AddOpenNetworkCallbacks;
import com.isupatches.wisefy.callbacks.AddWEPNetworkCallbacks;
import com.isupatches.wisefy.callbacks.AddWPA2NetworkCallbacks;
import com.isupatches.wisefy.callbacks.ConnectToNetworkCallbacks;
import com.isupatches.wisefy.callbacks.DisableWifiCallbacks;
import com.isupatches.wisefy.callbacks.DisconnectFromCurrentNetworkCallbacks;
import com.isupatches.wisefy.callbacks.EnableWifiCallbacks;
import com.isupatches.wisefy.callbacks.GetCurrentNetworkCallbacks;
import com.isupatches.wisefy.callbacks.GetFrequencyCallbacks;
import com.isupatches.wisefy.callbacks.GetRSSICallbacks;
import com.isupatches.wisefy.callbacks.GetSavedNetworkCallbacks;
import com.isupatches.wisefy.callbacks.GetSavedNetworksCallbacks;
import com.isupatches.wisefy.callbacks.RemoveNetworkCallbacks;
import com.isupatches.wisefy.callbacks.SearchForAccessPointCallbacks;
import com.isupatches.wisefy.callbacks.SearchForAccessPointsCallbacks;
import com.isupatches.wisefy.callbacks.SearchForSSIDCallbacks;
import com.isupatches.wisefy.callbacks.SearchForSSIDsCallbacks;

/**
 * A helper class to call methods in WiseFy with no callbacks.
 *
 * @author Patches
 */
public final class NullCallbackUtil {

  private final WiseFy wisefy;

  /**
   * Constructor.
   *
   * @param wisefy The WiseFy instance to use.
   */
  NullCallbackUtil(@NonNull final WiseFy wisefy) {
    this.wisefy = wisefy;
  }

  /**
   * To try to adding an open network with null callbacks.
   *
   * @param ssid The ssid to use when adding
   *
   * @see WiseFy#addOpenNetwork(String, AddOpenNetworkCallbacks)
   */
  public void callAddOpenNetwork(@Nullable final String ssid) {
    try {
      wisefy.addOpenNetwork(ssid, null);
    } catch (NullPointerException npe) {
      fail();
    }
  }

  /**
   * To try adding a WEP network with null callbacks.
   *
   * @param ssid The ssid to use when adding
   * @param password The password to use when adding
   *
   * @see WiseFy#addWEPNetwork(String, String, AddWEPNetworkCallbacks)
   */
  public void callAddWEPNetwork(@Nullable final String ssid, @Nullable final String password) {
    try {
      wisefy.addWEPNetwork(ssid, password, null);
    } catch (NullPointerException npe) {
      fail();
    }
  }

  /**
   * To try adding a WPA2 network with null callbacks.
   *
   * @param ssid The ssid to use when adding
   * @param password The password to use when adding
   *
   * @see WiseFy#addWPA2Network(String, String, AddWPA2NetworkCallbacks)
   */
  public void callAddWPA2Network(@Nullable final String ssid, @Nullable final String password) {
    try {
      wisefy.addWPA2Network(ssid, password, null);
    } catch (NullPointerException npe) {
      fail();
    }
  }

  /**
   * To try to connect to a network with null callbacks.
   *
   * @param ssid The ssid to use when connecting
   *
   * @see WiseFy#connectToNetwork(String, int, ConnectToNetworkCallbacks)
   */
  public void callConnectToNetwork(@Nullable final String ssid) {
    try {
      wisefy.connectToNetwork(ssid, TEST_TIMEOUT, null);
    } catch (NullPointerException npe) {
      fail();
    }
  }

  /**
   * To try disable wifi with null callbacks.
   *
   * @see WiseFy#disableWifi(DisableWifiCallbacks)
   */
  public void callDisableWifi() {
    try {
      wisefy.disableWifi(null);
    } catch (NullPointerException npe) {
      fail();
    }
  }

  /**
   * To try to disconnect from the current network with null callbacks.
   *
   * @see WiseFy#disconnectFromCurrentNetwork(DisconnectFromCurrentNetworkCallbacks)
   */
  public void callDisconnectFromCurrentNetwork() {
    try {
      wisefy.disconnectFromCurrentNetwork(null);
    } catch (NullPointerException npe) {
      fail();
    }
  }

  /**
   * To try to enable wifi with null callbacks.
   *
   * @see WiseFy#enableWifi(EnableWifiCallbacks)
   */
  public void callEnableWifi() {
    try {
      wisefy.enableWifi(null);
    } catch (NullPointerException npe) {
      fail();
    }
  }

  /**
   * To try to get the current network with null callbacks.
   *
   * @see WiseFy#getCurrentNetwork(GetCurrentNetworkCallbacks)
   */
  public void callGetCurrentNetwork() {
    try {
      wisefy.getCurrentNetwork(null);
    } catch (NullPointerException npe) {
      fail();
    }
  }

  /**
   * To try and get the frequency of a network with null callbacks.
   *
   * @see WiseFy#getFrequency(GetFrequencyCallbacks)
   */
  public void callGetFrequency() {
    try {
      wisefy.getFrequency((GetFrequencyCallbacks) null);
    } catch (NullPointerException npe) {
      fail();
    }
  }

  /**
   * To try and get the frequency of a network with null callbacks.
   *
   * @param network The network to use when trying
   *
   * @see WiseFy#getFrequency(WifiInfo, GetFrequencyCallbacks)
   */
  public void callGetFrequency_networkProvided(@Nullable final WifiInfo network) {
    try {
      wisefy.getFrequency(network, null);
    } catch (NullPointerException npe) {
      fail();
    }
  }

  /**
   * To try to get nearby access points with null callbacks.
   *
   * @param filterDuplicates The filterDuplicates param to use when trying.
   *
   * @see WiseFy#getCurrentNetwork(GetCurrentNetworkCallbacks)
   */
  public void callGetNearbyAccessPoints(final boolean filterDuplicates) {
    try {
      wisefy.getNearbyAccessPoints(filterDuplicates, null);
    } catch (NullPointerException npe) {
      fail();
    }
  }

  /**
   * To try to get the RSSI level of a nearby access point with null callbacks.
   *
   * @param takeHighest The takeHighest param to use when trying
   *
   * @see WiseFy#getRSSI(String, boolean, int, GetRSSICallbacks)
   */
  public void callGetRSSI(final boolean takeHighest) {
    try {
      wisefy.getRSSI(TEST_SSID, takeHighest, TEST_TIMEOUT, null);
    } catch (NullPointerException npe) {
      fail();
    }
  }

  /**
   * To try to retrieve a saved network with a given regex.
   *
   * @param regexForSSID The regex to use while trying
   *
   * @see WiseFy#getSavedNetwork(String, GetSavedNetworkCallbacks)
   */
  public void callGetSavedNetwork(@Nullable final String regexForSSID) {
    try {
      wisefy.getSavedNetwork(regexForSSID, null);
    } catch (NullPointerException npe) {
      fail();
    }
  }

  /**
   * To try getting all nearby access points with a null callback.
   *
   * @see WiseFy#getSavedNetworks(GetSavedNetworksCallbacks)
   */
  public void callGetSavedNetworks() {
    try {
      wisefy.getSavedNetworks((GetSavedNetworksCallbacks) null);
    } catch (NullPointerException npe) {
      fail();
    }
  }

  /**
   * To try to get all nearby access points matching a given regex with null callbacks.
   *
   * @param regexForSSID The ssid to use while trying
   *
   * @see WiseFy#getSavedNetwork(String, GetSavedNetworkCallbacks)
   */
  public void callGetSavedNetworks_withRegex(@Nullable final String regexForSSID) {
    try {
      wisefy.getSavedNetworks(regexForSSID, null);
    } catch (NullPointerException npe) {
      fail();
    }
  }

  /**
   * To try to remove a network with null callbacks.
   *
   * @param ssid The ssid to use while trying.
   *
   * @see WiseFy#removeNetwork(String, RemoveNetworkCallbacks)
   */
  public void callRemoveNetwork(@Nullable final String ssid) {
    try {
      wisefy.removeNetwork(ssid, null);
    } catch (NullPointerException npe) {
      fail();
    }
  }

  /**
   * To try and search for a nearby access point with null callbacks.
   *
   * @param ssid The ssid to use when trying
   * @param filterDuplicates The filter duplicate param to use when trying
   *
   * @see WiseFy#searchForAccessPoint(String, int, boolean, SearchForAccessPointCallbacks)
   */
  public void callSearchForAccessPoint(@Nullable final String ssid, final boolean filterDuplicates) {
    try {
      wisefy.searchForAccessPoint(ssid, TEST_TIMEOUT, filterDuplicates, null);
    } catch (NullPointerException npe) {
      fail();
    }
  }

  /**
   * To try and search for nearby access points with null callbacks.
   *
   * @param regexForSSID The regex to use when trying
   * @param filterDuplicates The filter duplicates param to use when trying
   *
   * @see WiseFy#searchForAccessPoints(String, boolean, SearchForAccessPointsCallbacks)
   */
  public void callSearchForAccessPoints(@NonNull final String regexForSSID, final boolean filterDuplicates) {
    try {
      wisefy.searchForAccessPoints(regexForSSID, filterDuplicates, null);
    } catch (NullPointerException npe) {
      fail();
    }
  }

  /**
   * To try and search for a nearby SSID given a regex with null callbacks.
   *
   * @param regexForSSID The regex to use when trying
   *
   * @see WiseFy#searchForSSID(String, int, SearchForSSIDCallbacks)
   */
  public void callSearchForSSID(@Nullable final String regexForSSID) {
    try {
      wisefy.searchForSSID(regexForSSID, TEST_TIMEOUT, null);
    } catch (NullPointerException npe) {
      fail();
    }
  }

  /**
   * To try and search for nearby SSIDs that match a given regex with null callbacks.
   *
   * @param regexForSSID The regex to use when trying
   *
   * @see WiseFy#searchForSSIDs(String, SearchForSSIDsCallbacks)
   */
  public void callSearchForSSIDs(@Nullable final String regexForSSID) {
    try {
      wisefy.searchForSSIDs(regexForSSID, null);
    } catch (NullPointerException npe) {
      fail();
    }
  }
}
