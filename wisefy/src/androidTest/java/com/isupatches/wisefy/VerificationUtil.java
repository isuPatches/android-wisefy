package com.isupatches.wisefy;

import static com.isupatches.wisefy.TestUtils.VERIFICATION_FAILURE_TIMEOUT;
import static com.isupatches.wisefy.TestUtils.VERIFICATION_SUCCESS_TIMEOUT;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.after;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;

/**
 * A helper class with common logic to verify operations with WifiManager.
 *
 * @author Patches
 */
public final class VerificationUtil {

  private final WifiManager mockWifiManager;

  /**
   * Constructor.
   *
   * @param mockWifiManager The WifiManager instance to use.
   */
  VerificationUtil(final WifiManager mockWifiManager) {
    this.mockWifiManager = mockWifiManager;
  }

  /*
   * Anti-methods (checking for things TO NOT happen)
   */

  /**
   * To verify no attempt to add a network was made.
   */
  public void didNoTryToAddNetwork() {
    verify(mockWifiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).addNetwork(any(WifiConfiguration.class));
  }

  /**
   * To verify no attempt attempt to connect to a network was made.
   */
  public void didNotTryToConnectToNetwork() {
    verify(mockWifiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).enableNetwork(anyInt(), anyBoolean());
    verify(mockWifiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).reconnect();
  }

  /**
   * To verify no attempt to disable wifi was made.
   */
  public void didNotTryToDisableWifi() {
    verify(mockWifiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).setWifiEnabled(false);
  }

  /**
   * To verify no attempt to disconnect the device from it's current network was made.
   */
  public void didNotTryToDisconnectFromCurrentNetwork() {
    verify(mockWifiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).disconnect();
  }

  /**
   * To verify no attempt to enable wifi was made.
   */
  public void didNotTryToEnableWifi() {
    verify(mockWifiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).setWifiEnabled(true);
  }

  /**
   * To verify no attempt to get the device's current network was made.
   */
  public void didNotTryToGetCurrentNetwork() {
    verify(mockWifiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).getConnectionInfo();
  }

  /**
   * To verify no attempt to get nearby access points was made.
   */
  public void didNotTryToGetNearbyAccessPoints() {
    verify(mockWifiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).getScanResults();
  }

  /**
   * To verify no attempt to get saved networks was made.
   */
  public void didNotTryToGetSavedNetworks() {
    verify(mockWifiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).getConfiguredNetworks();
  }

  /**
   * To verify no attempt to remove a network was made.
   */
  public void didNotTryToRemoveNetwork() {
    verify(mockWifiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).removeNetwork(anyInt());
  }

  /*
   * Positive-methods (checking for things TO happen)
   */

  /**
   * To verify no attempt to add a network was made.
   */
  public void triedToAddNetwork() {
    verify(mockWifiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).addNetwork(any(WifiConfiguration.class));
  }

  /**
   * To verify an attempt to connect to a network was made.
   */
  public void triedToConnectToNetwork() {
    verify(mockWifiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).enableNetwork(anyInt(), anyBoolean());
    verify(mockWifiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).reconnect();
  }

  /**
   * To verify an attempt to disable wifi was made.
   */
  public void triedToDisableWifi() {
    verify(mockWifiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).setWifiEnabled(false);
  }

  /**
   * To verify an attempt to disconnect the device from it's current network was made.
   */
  public void triedToDisconnectFromCurrentNetwork() {
    verify(mockWifiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).disconnect();
  }

  /**
   * To verify an attempt to enable wifi was made.
   */
  public void triedToEnableWifi() {
    verify(mockWifiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).setWifiEnabled(true);
  }

  /**
   * To verify an attempt to get the device's current network was made.
   */
  public void triedToGetCurrentNetwork() {
    verify(mockWifiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).getConnectionInfo();
  }

  /**
   * To verify an attempt to get nearby access points was made.
   */
  public void triedToGetNearbyAccessPoints() {
    verify(mockWifiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).getScanResults();
  }

  /**
   * To verify an attempt to get saved networks was made.
   */
  public void triedToGetSavedNetworks() {
    verify(mockWifiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).getConfiguredNetworks();
  }

  /**
   * To verify an attempt to remove a network was made.
   */
  public void triedToRemoveNetwork() {
    verify(mockWifiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).removeNetwork(anyInt());
  }
}
