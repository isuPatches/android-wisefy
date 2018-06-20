package com.isupatches.wisefy.main;

import static com.isupatches.wisefy.TestUtils.OPEN_NETWORK_SSID;
import static com.isupatches.wisefy.TestUtils.TEST_DELAY;
import static com.isupatches.wisefy.TestUtils.TEST_NUMBER_OF_BARS;
import static com.isupatches.wisefy.TestUtils.TEST_RSSI_LEVEL;
import static com.isupatches.wisefy.TestUtils.TEST_RSSI_LEVEL_HIGH;
import static com.isupatches.wisefy.TestUtils.TEST_RSSI_LEVEL_LOW;
import static com.isupatches.wisefy.TestUtils.TEST_SSID;
import static com.isupatches.wisefy.TestUtils.VERIFICATION_SUCCESS_TIMEOUT;
import static com.isupatches.wisefy.TestUtils.WEP_NETWORK_PASSWORD;
import static com.isupatches.wisefy.TestUtils.WEP_NETWORK_SSID;
import static com.isupatches.wisefy.TestUtils.WPA2_NETWORK_PASSWORD;
import static com.isupatches.wisefy.TestUtils.WPA2_NETWORK_SSID;

import static org.junit.Assert.assertEquals;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;

import com.isupatches.wisefy.WiseFy;
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

import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * Used to test the visibility of the public API.
 *
 * @author Patches
 */
public class PublicApiVisibilityTests {

  private WiseFy wisefy;

  private final GetSavedNetworksCallbacks savedNetworksCallbacks = new MyGetSavedNetworksCallbacks();

  public PublicApiVisibilityTests() {
    // No-op
  }

  @Before
  public void setUp() {
    wisefy = mock(WiseFy.class);
  }

  @Test
  public void addOpenNetwork_apis() {
    wisefy.addOpenNetwork(OPEN_NETWORK_SSID);
    verify(wisefy).addOpenNetwork(anyString());
    wisefy.addOpenNetwork(OPEN_NETWORK_SSID, new AddOpenNetworkCallbacks() {
      @Override
      public void addOpenNetworkWiseFyFailure(final int wisefyReturnCode) {

      }

      @Override
      public void failureAddingOpenNetwork(final int wifiManagerReturn) {

      }

      @Override
      public void openNetworkAdded(final int newNetworkId, final WifiConfiguration openNetworkConfig) {

      }
    });
    verify(wisefy).addOpenNetwork(anyString(), any(AddOpenNetworkCallbacks.class));
  }

  @Test
  public void addWEPNetwork_apis() {
    wisefy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD);
    verify(wisefy).addWEPNetwork(anyString(), anyString());
    wisefy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD, new AddWEPNetworkCallbacks() {
      @Override
      public void addWEPNetworkWiseFyFailure(final int wisefyReturnCode) {

      }

      @Override
      public void failureAddingWEPNetwork(final int wifiManagerReturn) {

      }

      @Override
      public void wepNetworkAdded(final int newNetworkId, final WifiConfiguration wepNetworkConfig) {

      }
    });
    verify(wisefy).addWEPNetwork(anyString(), anyString(), any(AddWEPNetworkCallbacks.class));
  }

  @Test
  public void addWPA2Network_apis() {
    wisefy.addWPA2Network(WPA2_NETWORK_SSID, WPA2_NETWORK_PASSWORD);
    verify(wisefy).addWPA2Network(anyString(), anyString());
    wisefy.addWPA2Network(WPA2_NETWORK_SSID, WPA2_NETWORK_PASSWORD, new AddWPA2NetworkCallbacks() {
      @Override
      public void addWPA2NetworkWiseFyFailure(final int wisefyReturnCode) {

      }

      @Override
      public void failureAddingWPA2Network(final int wifiManagerReturn) {

      }

      @Override
      public void wpa2NetworkAdded(final int newNetworkId, final WifiConfiguration wpa2Network) {

      }
    });
    verify(wisefy).addWPA2Network(anyString(), anyString(), any(AddWPA2NetworkCallbacks.class));
  }

  @Test
  public void compareSignalLevel_api() {
    wisefy.compareSignalLevel(TEST_RSSI_LEVEL_LOW, TEST_RSSI_LEVEL_HIGH);
    verify(wisefy).compareSignalLevel(anyInt(), anyInt());
  }

  @Test
  public void calculateBars_api() {
    wisefy.calculateBars(TEST_RSSI_LEVEL, TEST_NUMBER_OF_BARS);
    verify(wisefy).calculateBars(anyInt(), anyInt());
  }

  @Test
  public void connectToNetwork_apis() {
    wisefy.connectToNetwork(TEST_SSID, VERIFICATION_SUCCESS_TIMEOUT);
    verify(wisefy).connectToNetwork(anyString(), anyInt());
    wisefy.connectToNetwork(TEST_SSID, VERIFICATION_SUCCESS_TIMEOUT, new ConnectToNetworkCallbacks() {
      @Override
      public void connectedToNetwork() {

      }

      @Override
      public void connectToNetworkWiseFyFailure(final int wisefyReturnCode) {

      }

      @Override
      public void failureConnectingToNetwork() {

      }

      @Override
      public void networkNotFoundToConnectTo() {

      }
    });
    verify(wisefy).connectToNetwork(anyString(), anyInt(), any(ConnectToNetworkCallbacks.class));
  }

  @Test
  public void disableWifi_apis() {
    wisefy.disableWifi();
    verify(wisefy).disableWifi();
    wisefy.disableWifi(new DisableWifiCallbacks() {
      @Override
      public void disableWifiWiseFyFailure(final int wisefyReturnCode) {

      }

      @Override
      public void failureDisablingWifi() {

      }

      @Override
      public void wifiDisabled() {

      }
    });
    verify(wisefy).disableWifi(any(DisableWifiCallbacks.class));
  }

  @Test
  public void disconnectFromCurrentNetwork_apis() {
    wisefy.disconnectFromCurrentNetwork();
    verify(wisefy).disconnectFromCurrentNetwork();
    wisefy.disconnectFromCurrentNetwork(new DisconnectFromCurrentNetworkCallbacks() {
      @Override
      public void disconnectedFromCurrentNetwork() {

      }

      @Override
      public void disconnectFromCurrentNetworkWiseFyFailure(final int wisefyReturnCode) {

      }

      @Override
      public void failureDisconnectingFromCurrentNetwork() {

      }
    });
    verify(wisefy).disconnectFromCurrentNetwork(any(DisconnectFromCurrentNetworkCallbacks.class));
  }

  @Test
  public void enableWifi_apis() {
    wisefy.enableWifi();
    verify(wisefy).enableWifi();
    wisefy.enableWifi(new EnableWifiCallbacks() {
      @Override
      public void enableWifiWiseFyFailure(final int wisefyReturnCode) {

      }

      @Override
      public void failureEnablingWifi() {

      }

      @Override
      public void wifiEnabled() {

      }
    });
    verify(wisefy).enableWifi(any(EnableWifiCallbacks.class));
  }

  @Test
  public void getCurrentNetwork_apis() {
    wisefy.getCurrentNetwork();
    verify(wisefy).getCurrentNetwork();
    wisefy.getCurrentNetwork(new GetCurrentNetworkCallbacks() {
      @Override
      public void getCurrentNetworkWiseFyFailure(final int wisefyReturnCode) {

      }

      @Override
      public void retrievedCurrentNetwork(final WifiInfo currentNetwork) {

      }
    });
    verify(wisefy).getCurrentNetwork(any(GetCurrentNetworkCallbacks.class));
  }

  @Test
  public void getFrequency_apis() {
    wisefy.getFrequency();
    verify(wisefy).getFrequency();
    wisefy.getFrequency(new GetFrequencyCallbacks() {
      @Override
      public void failureGettingFrequency() {

      }

      @Override
      public void getFrequencyWiseFyFailure(final int wisefyReturnCode) {

      }

      @Override
      public void retrievedFrequency(final int frequency) {

      }
    });
    verify(wisefy).getFrequency(any(GetFrequencyCallbacks.class));

    wisefy.getFrequency(mock(WifiInfo.class));
    verify(wisefy).getFrequency(any(WifiInfo.class));
    wisefy.getFrequency(mock(WifiInfo.class), new GetFrequencyCallbacks() {
      @Override
      public void failureGettingFrequency() {

      }

      @Override
      public void getFrequencyWiseFyFailure(final int wisefyReturnCode) {

      }

      @Override
      public void retrievedFrequency(final int frequency) {

      }
    });
    verify(wisefy).getFrequency(any(WifiInfo.class), any(GetFrequencyCallbacks.class));
  }

  @Test
  public void getIP_apis() {
    wisefy.getIP();
    verify(wisefy).getIP();
    wisefy.getIP(new GetIPCallbacks() {
      @Override
      public void failureRetrievingIP() {

      }

      @Override
      public void getIPWiseFyFailure(final int wisefyReturnCode) {

      }

      @Override
      public void retrievedIP(final String ip) {

      }
    });
    verify(wisefy).getIP(any(GetIPCallbacks.class));
  }


  @Test
  public void getNearbyAccessPoints_apis() {
    wisefy.getNearbyAccessPoints(true);
    verify(wisefy).getNearbyAccessPoints(anyBoolean());
    wisefy.getNearbyAccessPoints(true, new GetNearbyAccessPointsCallbacks() {
      @Override
      public void getNearbyAccessPointsWiseFyFailure(final int wisefyReturnCode) {

      }

      @Override
      public void retrievedNearbyAccessPoints(final List<ScanResult> nearbyAccessPoints) {

      }
    });
    verify(wisefy).getNearbyAccessPoints(anyBoolean(), any(GetNearbyAccessPointsCallbacks.class));
  }

  @Test
  public void getRSSI_apis() {
    wisefy.getRSSI(TEST_SSID, true, TEST_DELAY);
    verify(wisefy).getRSSI(anyString(), anyBoolean(), anyInt());
    wisefy.getRSSI(TEST_SSID, true, TEST_DELAY, new GetRSSICallbacks() {
      @Override
      public void retrievedRSSI(final int rssi) {

      }

      @Override
      public void networkNotFoundToRetrieveRSSI() {

      }

      @Override
      public void getRSSIWiseFyFailure(final int wisefyReturnCode) {

      }
    });
    verify(wisefy).getRSSI(anyString(), anyBoolean(), anyInt(), any(GetRSSICallbacks.class));
  }

  @Test
  public void getSavedNetwork_apis() {
    wisefy.getSavedNetwork(TEST_SSID);
    verify(wisefy).getSavedNetwork(anyString());
    wisefy.getSavedNetwork(TEST_SSID, new GetSavedNetworkCallbacks() {
      @Override
      public void getSavedNetworkWiseFyFailure(final int wisefyReturnCode) {

      }

      @Override
      public void savedNetworkNotFound() {

      }

      @Override
      public void retrievedSavedNetwork(final WifiConfiguration savedNetwork) {

      }
    });
    verify(wisefy).getSavedNetwork(anyString(), any(GetSavedNetworkCallbacks.class));
  }

  @Test
  public void getSavedNetworks_apis() {
    wisefy.getSavedNetworks();
    verify(wisefy).getSavedNetworks();
    wisefy.getSavedNetworks(savedNetworksCallbacks);
    verify(wisefy).getSavedNetworks(any(GetSavedNetworksCallbacks.class));

    wisefy.getSavedNetworks(TEST_SSID);
    verify(wisefy).getSavedNetworks(anyString());
    wisefy.getSavedNetworks(TEST_SSID, savedNetworksCallbacks);
    verify(wisefy).getSavedNetworks(anyString(), any(GetSavedNetworksCallbacks.class));
  }

  @SuppressWarnings("ResultOfMethodCallIgnored")
  @Test
  public void getWiseFyLock_api() {
    wisefy.getWiseFyLock();
    verify(wisefy).getWiseFyLock();
  }

  @Test
  public void isDeviceConnectedToMobileNetwork_apis() {
    wisefy.isDeviceConnectedToMobileNetwork();
    verify(wisefy).isDeviceConnectedToMobileNetwork();
  }

  @Test
  public void isDeviceConnectedToMobileOrWifiNetwork_apis() {
    wisefy.isDeviceConnectedToMobileOrWifiNetwork();
    verify(wisefy).isDeviceConnectedToMobileOrWifiNetwork();
  }

  @Test
  public void isDeviceConnectedToSSID_apis() {
    wisefy.isDeviceConnectedToSSID(TEST_SSID);
    verify(wisefy).isDeviceConnectedToSSID(anyString());
  }

  @Test
  public void isDeviceConnectedToWifiNetwork_apis() {
    wisefy.isDeviceConnectedToWifiNetwork();
    verify(wisefy).isDeviceConnectedToWifiNetwork();
  }

  @Test
  public void isDeviceRoaming_apis() {
    wisefy.isDeviceRoaming();
    verify(wisefy).isDeviceRoaming();
  }

  @Test
  public void isLoggingEnabled_api() {
    wisefy.isLoggingEnabled();
    verify(wisefy).isLoggingEnabled();
  }

  @Test
  public void isNetwork5gHz_apis() {
    wisefy.isNetwork5gHz();
    verify(wisefy).isNetwork5gHz();

    wisefy.isNetwork5gHz(mock(WifiInfo.class));
    verify(wisefy).isNetwork5gHz(any(WifiInfo.class));
  }

  @Test
  public void isNetworkEAP_api() {
    wisefy.isNetworkEAP(mock(ScanResult.class));
    verify(wisefy).isNetworkEAP(any(ScanResult.class));
  }

  @Test
  public void isNetworkPSK_api() {
    wisefy.isNetworkPSK(mock(ScanResult.class));
    verify(wisefy).isNetworkPSK(any(ScanResult.class));
  }

  @Test
  public void isNetworkSaved_api() {
    wisefy.isNetworkSaved(TEST_SSID);
    verify(wisefy).isNetworkSaved(anyString());
  }

  @Test
  public void isNetworkSecure_api() {
    wisefy.isNetworkSecure(mock(ScanResult.class));
    verify(wisefy).isNetworkSecure(any(ScanResult.class));
  }

  @Test
  public void isNetworkWEP_api() {
    wisefy.isNetworkWEP(mock(ScanResult.class));
    verify(wisefy).isNetworkWEP(any(ScanResult.class));
  }

  @Test
  public void isNetworkWPA_api() {
    wisefy.isNetworkWPA(mock(ScanResult.class));
    verify(wisefy).isNetworkWPA(any(ScanResult.class));
  }

  @Test
  public void isNetworkWPA2_api() {
    wisefy.isNetworkWPA2(mock(ScanResult.class));
    verify(wisefy).isNetworkWPA2(any(ScanResult.class));
  }

  @Test
  public void isWifiEnabled_api() {
    wisefy.isWifiEnabled();
    verify(wisefy).isWifiEnabled();
  }

  @Test
  public void removeNetwork_api() {
    wisefy.removeNetwork(TEST_SSID);
    verify(wisefy).removeNetwork(anyString());

    wisefy.removeNetwork(TEST_SSID, new RemoveNetworkCallbacks() {
      @Override
      public void failureRemovingNetwork() {

      }

      @Override
      public void networkNotFoundToRemove() {

      }

      @Override
      public void networkRemoved() {

      }

      @Override
      public void removeNetworkWiseFyFailure(final int wisefyReturnCode) {

      }
    });
    verify(wisefy).removeNetwork(anyString(), any(RemoveNetworkCallbacks.class));
  }


  @Test
  public void searchForAccessPoint_api() {
    wisefy.searchForAccessPoint(TEST_SSID, TEST_DELAY, true);
    verify(wisefy).searchForAccessPoint(anyString(), anyInt(), anyBoolean());

    wisefy.searchForAccessPoint(TEST_SSID, TEST_DELAY, true, new SearchForAccessPointCallbacks() {
      @Override
      public void searchForAccessPointWiseFyFailure(final int wisefyReturnCode) {

      }

      @Override
      public void accessPointFound(final ScanResult accessPoint) {

      }

      @Override
      public void accessPointNotFound() {

      }
    });
    verify(wisefy).searchForAccessPoint(anyString(), anyInt(), anyBoolean(), any(SearchForAccessPointCallbacks.class));
  }

  @Test
  public void searchForAccessPoints_api() {
    wisefy.searchForAccessPoints(TEST_SSID, true);
    verify(wisefy).searchForAccessPoints(anyString(), anyBoolean());

    wisefy.searchForAccessPoints(TEST_SSID, true, new SearchForAccessPointsCallbacks() {
      @Override
      public void searchForAccessPointsWiseFyFailure(final int wisefyReturnCode) {

      }

      @Override
      public void foundAccessPoints(final List<ScanResult> accessPoints) {

      }

      @Override
      public void noAccessPointsFound() {

      }
    });
    verify(wisefy).searchForAccessPoints(anyString(), anyBoolean(), any(SearchForAccessPointsCallbacks.class));
  }

  @Test
  public void searchForSSID_api() {
    wisefy.searchForSSID(TEST_SSID, TEST_DELAY);
    verify(wisefy).searchForSSID(anyString(), anyInt());

    wisefy.searchForSSID(TEST_SSID, TEST_DELAY, new SearchForSSIDCallbacks() {
      @Override
      public void searchForSSIDWiseFyFailure(final int wisefyReturnCode) {

      }

      @Override
      public void ssidFound(final String ssid) {

      }

      @Override
      public void ssidNotFound() {

      }
    });
    verify(wisefy).searchForSSID(anyString(), anyInt(), any(SearchForSSIDCallbacks.class));
  }

  @Test
  public void searchForSSIDs_api() {
    wisefy.searchForSSIDs(TEST_SSID);
    verify(wisefy).searchForSSIDs(anyString());

    wisefy.searchForSSIDs(TEST_SSID, new SearchForSSIDsCallbacks() {
      @Override
      public void searchForSSIDsWiseFyFailure(final int wisefyReturnCode) {

      }

      @Override
      public void retrievedSSIDs(final List<String> ssids) {

      }

      @Override
      public void noSSIDsFound() {

      }
    });
    verify(wisefy).searchForSSIDs(anyString(), any(SearchForSSIDsCallbacks.class));
  }

  @Test
  public void wifiManagerFailure_value() {
    assertEquals(WiseFy.WIFI_MANAGER_FAILURE, -1);
  }

  private static class MyGetSavedNetworksCallbacks implements GetSavedNetworksCallbacks {
    @Override
    public void getSavedNetworksWiseFyFailure(final int wisefyReturnCode) {

    }

    @Override
    public void noSavedNetworksFound() {

    }

    @Override
    public void retrievedSavedNetworks(final List<WifiConfiguration> savedNetworks) {

    }
  }
}
