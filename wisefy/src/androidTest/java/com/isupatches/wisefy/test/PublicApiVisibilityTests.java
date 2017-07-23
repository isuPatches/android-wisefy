package com.isupatches.wisefy.test;


import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.support.test.rule.ActivityTestRule;
import com.isupatches.wisefy.WiseFy;
import com.isupatches.wisefy.base.TestActivity;
import com.isupatches.wisefy.callbacks.AddOpenNetworkCallbacks;
import com.isupatches.wisefy.callbacks.AddWEPNetworkCallbacks;
import com.isupatches.wisefy.callbacks.AddWPA2NetworkCallbacks;
import com.isupatches.wisefy.callbacks.ConnectToNetworkCallbacks;
import com.isupatches.wisefy.callbacks.DisableWifiCallbacks;
import com.isupatches.wisefy.callbacks.DisconnectFromCurrentNetworkCallbacks;
import com.isupatches.wisefy.callbacks.EnableWifiCallbacks;
import com.isupatches.wisefy.callbacks.GetCurrentNetworkCallbacks;
import com.isupatches.wisefy.callbacks.GetFrequencyCallbacks;
import com.isupatches.wisefy.callbacks.GetNearbyAccessPointsCallbacks;
import com.isupatches.wisefy.callbacks.GetRSSICallbacks;
import com.isupatches.wisefy.callbacks.GetSavedNetworkCallbacks;
import com.isupatches.wisefy.callbacks.GetSavedNetworksCallbacks;
import com.isupatches.wisefy.callbacks.RemoveNetworkCallbacks;
import com.isupatches.wisefy.callbacks.SearchForAccessPointCallbacks;
import com.isupatches.wisefy.callbacks.SearchForAccessPointsCallbacks;
import com.isupatches.wisefy.callbacks.SearchForSSIDCallbacks;
import com.isupatches.wisefy.callbacks.SearchForSSIDsCallbacks;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


public class PublicApiVisibilityTests extends ActivityTestRule<TestActivity> {

    @Rule
    public ActivityTestRule<TestActivity> mActivityTestRule = new ActivityTestRule<>(TestActivity.class);

    private WiseFy mWiseFy;

    public PublicApiVisibilityTests() {
        super(TestActivity.class);
    }

    @Before
    public void setUp() {
        mActivityTestRule.launchActivity(new Intent());

        mWiseFy = mock(WiseFy.class);
    }

    @Test
    public void addOpenNetwork_apis() {
        mWiseFy.addOpenNetwork("TEST");
        verify(mWiseFy).addOpenNetwork(anyString());
        mWiseFy.addOpenNetwork("TEST", new AddOpenNetworkCallbacks() {
            @Override
            public void addOpenNetworkWiseFyFailure(Integer wisefyReturnCode) {

            }

            @Override
            public void failureAddingOpenNetwork(Integer wifiManagerReturnCode) {

            }

            @Override
            public void openNetworkAdded(WifiConfiguration openNetwork) {

            }
        });
        verify(mWiseFy).addOpenNetwork(anyString(), any(AddOpenNetworkCallbacks.class));
    }

    @Test
    public void addWEPNetwork_apis() {
        mWiseFy.addWEPNetwork("WEP Network", "123456");
        verify(mWiseFy).addWEPNetwork(anyString(), anyString());
        mWiseFy.addWEPNetwork("WEP Network", "123456", new AddWEPNetworkCallbacks() {
            @Override
            public void addWEPNetworkWiseFyFailure(Integer wisefyReturnCode) {

            }

            @Override
            public void failureAddingWEPNetwork(Integer wifiManagerReturnCode) {

            }

            @Override
            public void wepNetworkAdded(WifiConfiguration wepNetwork) {

            }
        });
        verify(mWiseFy).addWEPNetwork(anyString(), anyString(), any(AddWEPNetworkCallbacks.class));
    }

    @Test
    public void addWPA2Network_apis() {
        mWiseFy.addWPA2Network("WPA2 Network", "12345678");
        verify(mWiseFy).addWPA2Network(anyString(), anyString());
        mWiseFy.addWPA2Network("WPA2 Network", "12345678", new AddWPA2NetworkCallbacks() {
            @Override
            public void addWPA2NetworkWiseFyFailure(Integer wisefyReturnCode) {

            }

            @Override
            public void failureAddingWPA2Network(Integer wifiManagerReturnCode) {

            }

            @Override
            public void wpa2NetworkAdded(WifiConfiguration wpa2Network) {

            }
        });
        verify(mWiseFy).addWPA2Network(anyString(), anyString(), any(AddWPA2NetworkCallbacks.class));
    }

    @Test
    public void compareSignalLevel_api() {
        mWiseFy.compareSignalLevel(-50, -65);
        verify(mWiseFy).compareSignalLevel(anyInt(), anyInt());
    }

    @Test
    public void calculateBars_api() {
        mWiseFy.calculateBars(-50, 5);
        verify(mWiseFy).calculateBars(anyInt(), anyInt());
    }

    @Test
    public void connectToNetwork_apis() {
        mWiseFy.connectToNetwork("SSID to connect to", 3000);
        verify(mWiseFy).connectToNetwork(anyString(), anyInt());
        mWiseFy.connectToNetwork("SSID to connect", 3000, new ConnectToNetworkCallbacks() {
            @Override
            public void connectedToNetwork() {

            }

            @Override
            public void connectToNetworkWiseFyFailure(Integer wisefyReturnCode) {

            }

            @Override
            public void failureConnectingToNetwork() {

            }

            @Override
            public void networkNotFoundToConnectTo() {

            }
        });
        verify(mWiseFy).connectToNetwork(anyString(), anyInt(), any(ConnectToNetworkCallbacks.class));
    }

    @Test
    public void disableWifi_apis() {
        mWiseFy.disableWifi();
        verify(mWiseFy).disableWifi();
        mWiseFy.disableWifi(new DisableWifiCallbacks() {
            @Override
            public void disableWifiWiseFyFailure(Integer wisefyReturnCode) {

            }

            @Override
            public void failureDisablingWifi() {

            }

            @Override
            public void wifiDisabled() {

            }
        });
        verify(mWiseFy).disableWifi(any(DisableWifiCallbacks.class));
    }

    @Test
    public void disconnectFromCurrentNetwork_apis() {
        mWiseFy.disconnectFromCurrentNetwork();
        verify(mWiseFy).disconnectFromCurrentNetwork();
        mWiseFy.disconnectFromCurrentNetwork(new DisconnectFromCurrentNetworkCallbacks() {
            @Override
            public void disconnectedFromCurrentNetwork() {

            }

            @Override
            public void disconnectFromCurrentNetworkWiseFyFailure(Integer wisefyReturnCode) {

            }

            @Override
            public void failureDisconnectingFromCurrentNetwork() {

            }
        });
        verify(mWiseFy).disconnectFromCurrentNetwork(any(DisconnectFromCurrentNetworkCallbacks.class));
    }

    @Test
    public void enableWifi_apis() {
        mWiseFy.enableWifi();
        verify(mWiseFy).enableWifi();
        mWiseFy.enableWifi(new EnableWifiCallbacks() {
            @Override
            public void enableWifiWiseFyFailure(Integer wisefyReturnCode) {

            }

            @Override
            public void failureEnablingWifi() {

            }

            @Override
            public void wifiEnabled() {

            }
        });
        verify(mWiseFy).enableWifi(any(EnableWifiCallbacks.class));
    }

    @Test
    public void getCurrentNetwork_apis() {
        mWiseFy.getCurrentNetwork();
        verify(mWiseFy).getCurrentNetwork();
        mWiseFy.getCurrentNetwork(new GetCurrentNetworkCallbacks() {
            @Override
            public void getCurrentNetworkWiseFyFailure(Integer wisefyReturnCode) {

            }

            @Override
            public void retrievedCurrentNetwork(WifiInfo currentNetwork) {

            }
        });
        verify(mWiseFy).getCurrentNetwork(any(GetCurrentNetworkCallbacks.class));
    }

    @Test
    public void getFrequency_apis() {
        mWiseFy.getFrequency();
        verify(mWiseFy).getFrequency();
        mWiseFy.getFrequency(new GetFrequencyCallbacks() {
            @Override
            public void failureGettingFrequency() {

            }

            @Override
            public void getFrequencyWiseFyFailure(Integer wisefyReturnCode) {

            }

            @Override
            public void retrievedFrequency(int frequency) {

            }
        });
        verify(mWiseFy).getFrequency(any(GetFrequencyCallbacks.class));

        mWiseFy.getFrequency(mock(WifiInfo.class));
        verify(mWiseFy).getFrequency(any(WifiInfo.class));
        mWiseFy.getFrequency(mock(WifiInfo.class), new GetFrequencyCallbacks() {
            @Override
            public void failureGettingFrequency() {

            }

            @Override
            public void getFrequencyWiseFyFailure(Integer wisefyReturnCode) {

            }

            @Override
            public void retrievedFrequency(int frequency) {

            }
        });
        verify(mWiseFy).getFrequency(any(WifiInfo.class), any(GetFrequencyCallbacks.class));
    }

    @Test
    public void getNearbyAccessPoints_apis() {
        mWiseFy.getNearbyAccessPoints(true);
        verify(mWiseFy).getNearbyAccessPoints(anyBoolean());
        mWiseFy.getNearbyAccessPoints(true, new GetNearbyAccessPointsCallbacks() {
            @Override
            public void getNearbyAccessPointsWiseFyFailure(Integer wisefyReturnCode) {

            }

            @Override
            public void retrievedNearbyAccessPoints(List<ScanResult> nearbyAccessPoints) {

            }
        });
        verify(mWiseFy).getNearbyAccessPoints(anyBoolean(), any(GetNearbyAccessPointsCallbacks.class));
    }

    @Test
    public void getRSSI_apis() {
        mWiseFy.getRSSI("SSID", true, 1000);
        verify(mWiseFy).getRSSI(anyString(), anyBoolean(), anyInt());
        mWiseFy.getRSSI("SSID", true, 1000, new GetRSSICallbacks() {
            @Override
            public void retrievedRSSI(Integer rssi) {

            }

            @Override
            public void networkNotFoundToRetrieveRSSI() {

            }

            @Override
            public void getRSSIWiseFyFailure(Integer wisefyReturnCode) {

            }
        });
        verify(mWiseFy).getRSSI(anyString(), anyBoolean(), anyInt(), any(GetRSSICallbacks.class));
    }

    @Test
    public void getSavedNetwork_apis() {
        mWiseFy.getSavedNetwork("SSID");
        verify(mWiseFy).getSavedNetwork(anyString());
        mWiseFy.getSavedNetwork("SSID", new GetSavedNetworkCallbacks() {
            @Override
            public void getSavedNetworkWiseFyFailure(Integer wisefyReturnCode) {

            }

            @Override
            public void savedNetworkNotFound() {

            }

            @Override
            public void retrievedSavedNetwork(WifiConfiguration savedNetwork) {

            }
        });
        verify(mWiseFy).getSavedNetwork(anyString(), any(GetSavedNetworkCallbacks.class));
    }

    @Test
    public void getSavedNetworks_apis() {
        mWiseFy.getSavedNetworks();
        verify(mWiseFy).getSavedNetworks();
        mWiseFy.getSavedNetworks(new GetSavedNetworksCallbacks() {
            @Override
            public void getSavedNetworksWiseFyFailure(Integer wisefyReturnCode) {

            }

            @Override
            public void noSavedNetworksFound() {

            }

            @Override
            public void retrievedSavedNetworks(List<WifiConfiguration> savedNetworks) {

            }
        });
        verify(mWiseFy).getSavedNetworks(any(GetSavedNetworksCallbacks.class));

        mWiseFy.getSavedNetworks("SSID");
        verify(mWiseFy).getSavedNetworks(anyString());
        mWiseFy.getSavedNetworks("SSID", new GetSavedNetworksCallbacks() {
            @Override
            public void getSavedNetworksWiseFyFailure(Integer wisefyReturnCode) {

            }

            @Override
            public void noSavedNetworksFound() {

            }

            @Override
            public void retrievedSavedNetworks(List<WifiConfiguration> savedNetworks) {

            }
        });
        verify(mWiseFy).getSavedNetworks(anyString(), any(GetSavedNetworksCallbacks.class));
    }

    @Test
    public void isDeviceConnectedToMobileNetwork_apis() {
        mWiseFy.isDeviceConnectedToMobileNetwork();
        verify(mWiseFy).isDeviceConnectedToMobileNetwork();
    }

    @Test
    public void isDeviceConnectedToMobileOrWifiNetwork_apis() {
        mWiseFy.isDeviceConnectedToMobileOrWifiNetwork();
        verify(mWiseFy).isDeviceConnectedToMobileOrWifiNetwork();
    }

    @Test
    public void isDeviceConnectedToSSID_apis() {
        mWiseFy.isDeviceConnectedToSSID("SSID");
        verify(mWiseFy).isDeviceConnectedToSSID(anyString());
    }

    @Test
    public void isDeviceConnectedToWifiNetwork_apis() {
        mWiseFy.isDeviceConnectedToWifiNetwork();
        verify(mWiseFy).isDeviceConnectedToWifiNetwork();
    }

    @Test
    public void isDeviceRoaming_apis() {
        mWiseFy.isDeviceRoaming();
        verify(mWiseFy).isDeviceRoaming();
    }

    @Test
    public void isNetwork5gHz_apis() {
        mWiseFy.isNetwork5gHz();
        verify(mWiseFy).isNetwork5gHz();

        mWiseFy.isNetwork5gHz(mock(WifiInfo.class));
        verify(mWiseFy).isNetwork5gHz(any(WifiInfo.class));
    }

    @Test
    public void isNetworkInConfigurationList_api() {
        mWiseFy.isNetworkInConfigurationList("SSID");
        verify(mWiseFy).isNetworkInConfigurationList(anyString());
    }

    @Test
    public void isNetworkEAP_api() {
        mWiseFy.isNetworkEAP(mock(ScanResult.class));
        verify(mWiseFy).isNetworkEAP(any(ScanResult.class));
    }

    @Test
    public void isNetworkPSK_api() {
        mWiseFy.isNetworkPSK(mock(ScanResult.class));
        verify(mWiseFy).isNetworkPSK(any(ScanResult.class));
    }

    @Test
    public void iisNetworkSecure_api() {
        mWiseFy.isNetworkSecure(mock(ScanResult.class));
        verify(mWiseFy).isNetworkSecure(any(ScanResult.class));
    }

    @Test
    public void isNetworkWEP_api() {
        mWiseFy.isNetworkWEP(mock(ScanResult.class));
        verify(mWiseFy).isNetworkWEP(any(ScanResult.class));
    }

    @Test
    public void isNetworkWPA_api() {
        mWiseFy.isNetworkWPA(mock(ScanResult.class));
        verify(mWiseFy).isNetworkWPA(any(ScanResult.class));
    }

    @Test
    public void isNetworkWPA2_api() {
        mWiseFy.isNetworkWPA2(mock(ScanResult.class));
        verify(mWiseFy).isNetworkWPA2(any(ScanResult.class));
    }

    @Test
    public void isWifiEnabled_api() {
        mWiseFy.isWifiEnabled();
        verify(mWiseFy).isWifiEnabled();
    }

    @Test
    public void removeNetwork_api() {
        mWiseFy.removeNetwork("SSID");
        verify(mWiseFy).removeNetwork(anyString());

        mWiseFy.removeNetwork("SSID", new RemoveNetworkCallbacks() {
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
            public void removeNetworkWiseFyFailure(Integer wisefyReturnCode) {

            }
        });
        verify(mWiseFy).removeNetwork(anyString(), any(RemoveNetworkCallbacks.class));
    }


    @Test
    public void searchForAccessPoint_api() {
        mWiseFy.searchForAccessPoint("SSID", 1000, true);
        verify(mWiseFy).searchForAccessPoint(anyString(), anyInt(), anyBoolean());

        mWiseFy.searchForAccessPoint("SSID", 1000, true, new SearchForAccessPointCallbacks() {
            @Override
            public void searchForAccessPointWiseFyFailure(Integer wisefyReturnCode) {

            }

            @Override
            public void accessPointFound(ScanResult accessPoint) {

            }

            @Override
            public void accessPointNotFound() {

            }
        });
        verify(mWiseFy).searchForAccessPoint(anyString(), anyInt(), anyBoolean(), any(SearchForAccessPointCallbacks.class));
    }

    @Test
    public void searchForAccessPoints_api() {
        mWiseFy.searchForAccessPoints("SSID", true);
        verify(mWiseFy).searchForAccessPoints(anyString(), anyBoolean());

        mWiseFy.searchForAccessPoints("SSID", true, new SearchForAccessPointsCallbacks() {
            @Override
            public void searchForAccessPointsWiseFyFailure(Integer wisefyReturnCode) {

            }

            @Override
            public void foundAccessPoints(List<ScanResult> accessPoints) {

            }

            @Override
            public void noAccessPointsFound() {

            }
        });
        verify(mWiseFy).searchForAccessPoints(anyString(), anyBoolean(), any(SearchForAccessPointsCallbacks.class));
    }

    @Test
    public void searchForSSID_api() {
        mWiseFy.searchForSSID("SSID", 1000);
        verify(mWiseFy).searchForSSID(anyString(), anyInt());

        mWiseFy.searchForSSID("SSID", 1000, new SearchForSSIDCallbacks() {
            @Override
            public void searchForSSIDWiseFyFailure(Integer wisefyReturnCode) {

            }

            @Override
            public void ssidFound(String ssid) {

            }

            @Override
            public void ssidNotFound() {

            }
        });
        verify(mWiseFy).searchForSSID(anyString(), anyInt(), any(SearchForSSIDCallbacks.class));
    }

    @Test
    public void searchForSSIDs_api() {
        mWiseFy.searchForSSIDs("SSID");
        verify(mWiseFy).searchForSSIDs(anyString());

        mWiseFy.searchForSSIDs("SSID", new SearchForSSIDsCallbacks() {
            @Override
            public void searchForSSIDsWiseFyFailure(Integer wisefyReturnCode) {

            }

            @Override
            public void retrievedSSIDs(List<String> ssids) {

            }

            @Override
            public void noSSIDsFound() {

            }
        });
        verify(mWiseFy).searchForSSIDs(anyString(), any(SearchForSSIDsCallbacks.class));
    }

    @Test
    public void wifiManagerFailure_value() {
        assertEquals(WiseFy.WIFI_MANAGER_FAILURE, -1);
    }
}
