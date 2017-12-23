package com.isupatches.wisefy;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.isupatches.wisefy.constants.WiseFyCodeDefs;

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
   */
  MockWiseFyPrechecksUtil(final WiseFyPrechecks mockWiseFyPrechecks) {
    this.mockWiseFyPrechecks = mockWiseFyPrechecks;
  }

  /**
   * Mocks a precheck failure adding a network.
   */
  public void addNetwork_failure() {
    when(mockWiseFyPrechecks.addNetworkPrechecks(anyString())).thenReturn(WiseFyCodeDefs.MISSING_PREREQUISITE);
    when(mockWiseFyPrechecks.addNetworkPrechecks(anyString(), anyString())).thenReturn(WiseFyCodeDefs.MISSING_PREREQUISITE);
  }

  /**
   * Mocks a precheck failure connecting to a network.
   */
  public void connectToNetwork_failure() {
    when(mockWiseFyPrechecks.connectToNetworkPrechecks(anyString())).thenReturn(WiseFyCodeDefs.MISSING_PREREQUISITE);
  }

  /**
   * Mocks a precheck failure disabling wifi.
   */
  public void disableWifi_failure() {
    when(mockWiseFyPrechecks.disableWifiChecks()).thenReturn(WiseFyCodeDefs.MISSING_PREREQUISITE);
  }

  /**
   * Mocks a precheck failure disconnecting from the current network.
   */
  public void disconnectFromCurrentNetwork_failure() {
    when(mockWiseFyPrechecks.disconnectFromCurrentNetworkChecks()).thenReturn(WiseFyCodeDefs.MISSING_PREREQUISITE);
  }

  /**
   * Mocks a precheck failure enabling wifi.
   */
  public void enableWifi_failure() {
    when(mockWiseFyPrechecks.enableWifiChecks()).thenReturn(WiseFyCodeDefs.MISSING_PREREQUISITE);
  }

  /**
   * Mocks a precheck failure getting the current network.
   */
  public void getCurrentNetwork_failure() {
    when(mockWiseFyPrechecks.getCurrentNetworkChecks()).thenReturn(WiseFyCodeDefs.MISSING_PREREQUISITE);
  }

  /**
   * Mocks a precheck failure getting the ip of the device.
   */
  public void geIP_failure() {
    when(mockWiseFyPrechecks.getIPChecks()).thenReturn(WiseFyCodeDefs.MISSING_PREREQUISITE);
  }

  /**
   * Mocks a precheck failure getting nearby access points.
   */
  public void getNearbyAccessPoints_failure() {
    when(mockWiseFyPrechecks.getNearbyAccessPointsChecks()).thenReturn(WiseFyCodeDefs.MISSING_PREREQUISITE);
  }

  /**
   * Mocks a precheck failure getting the RSSI level of a network.
   */
  public void getRSSI_failure() {
    when(mockWiseFyPrechecks.getRSSIChecks(anyString())).thenReturn(WiseFyCodeDefs.MISSING_PREREQUISITE);
  }

  /**
   * Mocks a precheck failure getting a saved network.
   */
  public void getSavedNetwork_failure() {
    when(mockWiseFyPrechecks.getSavedNetworkChecks(anyString())).thenReturn(WiseFyCodeDefs.MISSING_PREREQUISITE);
  }

  /**
   * Mocks a precheck failure getting saved networks.
   */
  public void getSavedNetworks_failure() {
    when(mockWiseFyPrechecks.getSavedNetworksChecks()).thenReturn(WiseFyCodeDefs.MISSING_PREREQUISITE);
    when(mockWiseFyPrechecks.getSavedNetworksChecks(anyString())).thenReturn(WiseFyCodeDefs.MISSING_PREREQUISITE);
  }

  /**
   * Mocks a precheck failure checking if a device is connected to a mobile network.
   */
  public void isDeviceConnectedToMobileNetwork_failure() {
    when(mockWiseFyPrechecks.isDeviceConnectedToMobileNetworkChecks()).thenReturn(WiseFyCodeDefs.MISSING_PREREQUISITE);
  }

  /**
   * Mocks a precheck failure checking if a device is connected to a mobile or wifi network.
   */
  public void isDeviceConnectedToMobileOrWifiNetwork_failure() {
    when(mockWiseFyPrechecks.isDeviceConnectedToMobileOrWifiNetworkChecks()).thenReturn(WiseFyCodeDefs.MISSING_PREREQUISITE);
  }

  /**
   * Mocks a precheck failure checking if a device is connected to a network with a specific SSID.
   */
  public void isDeviceConnectedToSSIDChecks_failure() {
    when(mockWiseFyPrechecks.isDeviceConnectedToSSIDChecks(anyString())).thenReturn(WiseFyCodeDefs.MISSING_PREREQUISITE);
  }

  /**
   * Mocks a precheck failure checking if a device is connected to a wifi network.
   */
  public void isDeviceConnectedToWifiNetwork_failure() {
    when(mockWiseFyPrechecks.isDeviceConnectedToWifiNetworkChecks()).thenReturn(WiseFyCodeDefs.MISSING_PREREQUISITE);
  }

  /**
   * Mocks a precheck failure checking if a device is roaming.
   */
  public void isDeviceRoaming_failure() {
    when(mockWiseFyPrechecks.isDeviceRoamingChecks()).thenReturn(WiseFyCodeDefs.MISSING_PREREQUISITE);
  }

  /**
   * Mocks a precheck failure checking if a network is saved on a device.
   */
  public void isNetworkSaved_failure() {
    when(mockWiseFyPrechecks.isNetworkSavedChecks()).thenReturn(WiseFyCodeDefs.MISSING_PREREQUISITE);
  }

  /**
   * Mocks a precheck failure checking if Wifi is enabled on a device.
   */
  public void isWifiEnabled_failure() {
    when(mockWiseFyPrechecks.isWifiEnabledChecks()).thenReturn(WiseFyCodeDefs.MISSING_PREREQUISITE);
  }

  /**
   * Mocks a precheck failure removing a network.
   */
  public void removeNetwork_failure() {
    when(mockWiseFyPrechecks.removeNetworkCheck(anyString())).thenReturn(WiseFyCodeDefs.MISSING_PREREQUISITE);
  }

  /**
   * Mocks a precheck failure searching for a nearby access point.
   */
  public void searchForAccessPoints_failure() {
    when(mockWiseFyPrechecks.searchForAccessPointsChecks(anyString())).thenReturn(WiseFyCodeDefs.MISSING_PREREQUISITE);
  }

  /**
   * Mocks a precheck failure searching for nearby access points.
   */
  public void searchForAccessPoint_failure() {
    when(mockWiseFyPrechecks.searchForAccessPointChecks(anyString())).thenReturn(WiseFyCodeDefs.MISSING_PREREQUISITE);
  }

  /**
   * Mocks a precheck failure searching for an SSID.
   */
  public void searchForSSID_failure() {
    when(mockWiseFyPrechecks.searchForSSIDChecks(anyString())).thenReturn(WiseFyCodeDefs.MISSING_PREREQUISITE);
  }

  /**
   * Mocks a precheck failure searching for SSIDs.
   */
  public void searchForSSIDs_failure() {
    when(mockWiseFyPrechecks.searchForSSIDsChecks(anyString())).thenReturn(WiseFyCodeDefs.MISSING_PREREQUISITE);
  }
}
