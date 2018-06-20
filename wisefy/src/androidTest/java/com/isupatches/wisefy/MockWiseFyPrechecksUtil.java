package com.isupatches.wisefy;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import android.support.annotation.NonNull;

import com.isupatches.wisefy.constants.WiseFyCodes;
import com.isupatches.wisefy.constants.WiseFyCodes.WiseFyCode;

/**
 * A class to mock returns from the WiseFyPrechecks class.
 *
 * @see WiseFyPrechecks
 *
 * @author Patches
 */
public final class MockWiseFyPrechecksUtil {

  private final WiseFyPrechecks mockWiseFyPrechecks;

  /**
   * Constructor.
   *
   * @param mockWiseFyPrechecks The WiseFyPrechecks instance to use.
   *
   * @see WiseFyPrechecks
   */
  MockWiseFyPrechecksUtil(@NonNull final WiseFyPrechecks mockWiseFyPrechecks) {
    this.mockWiseFyPrechecks = mockWiseFyPrechecks;
  }

  /**
   * Mocks a precheck failure adding a network.
   *
   * @see WiseFyPrechecks#addNetworkPrechecks(String)
   * @see WiseFyPrechecks#addNetworkPrechecks(String, String)
   * @see WiseFyCode#MISSING_PREREQUISITE
   */
  public void addNetwork_failure() {
    when(mockWiseFyPrechecks.addNetworkPrechecks(anyString())).thenReturn(WiseFyCodes.MISSING_PREREQUISITE);
    when(mockWiseFyPrechecks.addNetworkPrechecks(anyString(), anyString())).thenReturn(WiseFyCodes.MISSING_PREREQUISITE);
  }

  /**
   * Mocks a precheck failure connecting to a network.
   *
   * @see WiseFyPrechecks#connectToNetworkPrechecks(String)
   * @see WiseFyCode#MISSING_PREREQUISITE
   */
  public void connectToNetwork_failure() {
    when(mockWiseFyPrechecks.connectToNetworkPrechecks(anyString())).thenReturn(WiseFyCodes.MISSING_PREREQUISITE);
  }

  /**
   * Mocks a precheck failure disabling wifi.
   *
   * @see WiseFyPrechecks#disableWifiChecks()
   * @see WiseFyCode#MISSING_PREREQUISITE
   */
  public void disableWifi_failure() {
    when(mockWiseFyPrechecks.disableWifiChecks()).thenReturn(WiseFyCodes.MISSING_PREREQUISITE);
  }

  /**
   * Mocks a precheck failure disconnecting from the current network.
   *
   * @see WiseFyPrechecks#disconnectFromCurrentNetworkChecks()
   * @see WiseFyCode#MISSING_PREREQUISITE
   */
  public void disconnectFromCurrentNetwork_failure() {
    when(mockWiseFyPrechecks.disconnectFromCurrentNetworkChecks()).thenReturn(WiseFyCodes.MISSING_PREREQUISITE);
  }

  /**
   * Mocks a precheck failure enabling wifi.
   *
   * @see WiseFyPrechecks#enableWifiChecks()
   * @see WiseFyCode#MISSING_PREREQUISITE
   */
  public void enableWifi_failure() {
    when(mockWiseFyPrechecks.enableWifiChecks()).thenReturn(WiseFyCodes.MISSING_PREREQUISITE);
  }

  /**
   * Mocks a precheck failure getting the current network.
   *
   * @see WiseFyPrechecks#getCurrentNetworkChecks()
   * @see WiseFyCode#MISSING_PREREQUISITE
   */
  public void getCurrentNetwork_failure() {
    when(mockWiseFyPrechecks.getCurrentNetworkChecks()).thenReturn(WiseFyCodes.MISSING_PREREQUISITE);
  }

  /**
   * Mocks a precheck failure getting the ip of the device.
   *
   * @see WiseFyPrechecks#getIPChecks()
   * @see WiseFyCode#MISSING_PREREQUISITE
   */
  public void geIP_failure() {
    when(mockWiseFyPrechecks.getIPChecks()).thenReturn(WiseFyCodes.MISSING_PREREQUISITE);
  }

  /**
   * Mocks a precheck failure getting nearby access points.
   *
   * @see WiseFyPrechecks#getNearbyAccessPointsChecks()
   * @see WiseFyCode#MISSING_PREREQUISITE
   */
  public void getNearbyAccessPoints_failure() {
    when(mockWiseFyPrechecks.getNearbyAccessPointsChecks()).thenReturn(WiseFyCodes.MISSING_PREREQUISITE);
  }

  /**
   * Mocks a precheck failure getting the RSSI level of a network.
   *
   * @see WiseFyPrechecks#getRSSIChecks(String)
   * @see WiseFyCode#MISSING_PREREQUISITE
   */
  public void getRSSI_failure() {
    when(mockWiseFyPrechecks.getRSSIChecks(anyString())).thenReturn(WiseFyCodes.MISSING_PREREQUISITE);
  }

  /**
   * Mocks a precheck failure getting a saved network.
   *
   * @see WiseFyPrechecks#getSavedNetworkChecks(String)
   * @see WiseFyCode#MISSING_PREREQUISITE
   */
  public void getSavedNetwork_failure() {
    when(mockWiseFyPrechecks.getSavedNetworkChecks(anyString())).thenReturn(WiseFyCodes.MISSING_PREREQUISITE);
  }

  /**
   * Mocks a precheck failure getting saved networks.
   *
   * @see WiseFyPrechecks#getSavedNetworksChecks()
   * @see WiseFyPrechecks#getSavedNetworksChecks(String)
   * @see WiseFyCode#MISSING_PREREQUISITE
   */
  public void getSavedNetworks_failure() {
    when(mockWiseFyPrechecks.getSavedNetworksChecks()).thenReturn(WiseFyCodes.MISSING_PREREQUISITE);
    when(mockWiseFyPrechecks.getSavedNetworksChecks(anyString())).thenReturn(WiseFyCodes.MISSING_PREREQUISITE);
  }

  /**
   * Mocks a precheck failure checking if a device is connected to a mobile network.
   *
   * @see WiseFyPrechecks#isDeviceConnectedToMobileNetworkChecks()
   * @see WiseFyCode#MISSING_PREREQUISITE
   */
  public void isDeviceConnectedToMobileNetwork_failure() {
    when(mockWiseFyPrechecks.isDeviceConnectedToMobileNetworkChecks()).thenReturn(WiseFyCodes.MISSING_PREREQUISITE);
  }

  /**
   * Mocks a precheck failure checking if a device is connected to a mobile or wifi network.
   *
   * @see WiseFyPrechecks#isDeviceConnectedToMobileOrWifiNetworkChecks()
   * @see WiseFyCode#MISSING_PREREQUISITE
   */
  public void isDeviceConnectedToMobileOrWifiNetwork_failure() {
    when(mockWiseFyPrechecks.isDeviceConnectedToMobileOrWifiNetworkChecks()).thenReturn(WiseFyCodes.MISSING_PREREQUISITE);
  }

  /**
   * Mocks a precheck failure checking if a device is connected to a network with a specific SSID.
   *
   * @see WiseFyPrechecks#isDeviceConnectedToSSIDChecks(String)
   * @see WiseFyCode#MISSING_PREREQUISITE
   */
  public void isDeviceConnectedToSSIDChecks_failure() {
    when(mockWiseFyPrechecks.isDeviceConnectedToSSIDChecks(anyString())).thenReturn(WiseFyCodes.MISSING_PREREQUISITE);
  }

  /**
   * Mocks a precheck failure checking if a device is connected to a wifi network.
   *
   * @see WiseFyPrechecks#isDeviceConnectedToWifiNetworkChecks()
   * @see WiseFyCode#MISSING_PREREQUISITE
   */
  public void isDeviceConnectedToWifiNetwork_failure() {
    when(mockWiseFyPrechecks.isDeviceConnectedToWifiNetworkChecks()).thenReturn(WiseFyCodes.MISSING_PREREQUISITE);
  }

  /**
   * Mocks a precheck failure checking if a device is roaming.
   *
   * @see WiseFyPrechecks#isDeviceRoamingChecks()
   * @see WiseFyCode#MISSING_PREREQUISITE
   */
  public void isDeviceRoaming_failure() {
    when(mockWiseFyPrechecks.isDeviceRoamingChecks()).thenReturn(WiseFyCodes.MISSING_PREREQUISITE);
  }

  /**
   * Mocks a precheck failure checking if a network is saved on a device.
   *
   * @see WiseFyPrechecks#isNetworkSavedChecks()
   * @see WiseFyCode#MISSING_PREREQUISITE
   */
  public void isNetworkSaved_failure() {
    when(mockWiseFyPrechecks.isNetworkSavedChecks()).thenReturn(WiseFyCodes.MISSING_PREREQUISITE);
  }

  /**
   * Mocks a precheck failure checking if Wifi is enabled on a device.
   *
   * @see WiseFyPrechecks#isWifiEnabledChecks()
   * @see WiseFyCode#MISSING_PREREQUISITE
   */
  public void isWifiEnabled_failure() {
    when(mockWiseFyPrechecks.isWifiEnabledChecks()).thenReturn(WiseFyCodes.MISSING_PREREQUISITE);
  }

  /**
   * Mocks a precheck failure removing a network.
   *
   * @see WiseFyPrechecks#removeNetworkCheck(String)
   * @see WiseFyCode#MISSING_PREREQUISITE
   */
  public void removeNetwork_failure() {
    when(mockWiseFyPrechecks.removeNetworkCheck(anyString())).thenReturn(WiseFyCodes.MISSING_PREREQUISITE);
  }

  /**
   * Mocks a precheck failure searching for a nearby access point.
   *
   * @see WiseFyPrechecks#searchForAccessPointsChecks(String)
   * @see WiseFyCode#MISSING_PREREQUISITE
   */
  public void searchForAccessPoints_failure() {
    when(mockWiseFyPrechecks.searchForAccessPointsChecks(anyString())).thenReturn(WiseFyCodes.MISSING_PREREQUISITE);
  }

  /**
   * Mocks a precheck failure searching for nearby access points.
   *
   * @see WiseFyPrechecks#searchForAccessPointChecks(String)
   * @see WiseFyCode#MISSING_PREREQUISITE
   */
  public void searchForAccessPoint_failure() {
    when(mockWiseFyPrechecks.searchForAccessPointChecks(anyString())).thenReturn(WiseFyCodes.MISSING_PREREQUISITE);
  }

  /**
   * Mocks a precheck failure searching for an SSID.
   *
   * @see WiseFyPrechecks#searchForSSIDChecks(String)
   * @see WiseFyCode#MISSING_PREREQUISITE
   */
  public void searchForSSID_failure() {
    when(mockWiseFyPrechecks.searchForSSIDChecks(anyString())).thenReturn(WiseFyCodes.MISSING_PREREQUISITE);
  }

  /**
   * Mocks a precheck failure searching for SSIDs.
   *
   * @see WiseFyPrechecks#searchForSSIDsChecks
   * @see WiseFyCode#MISSING_PREREQUISITE
   */
  public void searchForSSIDs_failure() {
    when(mockWiseFyPrechecks.searchForSSIDsChecks(anyString())).thenReturn(WiseFyCodes.MISSING_PREREQUISITE);
  }
}
