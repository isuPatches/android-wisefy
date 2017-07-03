package com.isupatches.wisefy.test;


import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.test.rule.ActivityTestRule;
import com.isupatches.wisefy.WiseFy;
import com.isupatches.wisefy.test.base.BaseTestClass;
import com.isupatches.wisefy.test.base.TestActivity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static com.isupatches.wisefy.test.base.TestUtils.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class WiseFyTest extends BaseTestClass<TestActivity> {

    @Rule
    public ActivityTestRule<TestActivity> mActivityTestRule = new ActivityTestRule<>(TestActivity.class);

    private ConnectivityManager mMockConnectivityManager;

    private WifiManager mMockWiFiManager;

    private WiseFy mWiseFy;

    public WiseFyTest() {
        super(TestActivity.class);
    }

    @Before
    public void setUp() {
        mMockWiFiManager = mock(WifiManager.class);
        mMockConnectivityManager = mock(ConnectivityManager.class);

        mActivityTestRule.launchActivity(new Intent());

        mWiseFy = new WiseFy.withContext(mActivityTestRule.getActivity()).logging(true).getSmarts();
    }

    @Test
    public void addOpenNetwork_failure() {
        setManagers();
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(WiseFy.WIFI_MANAGER_FAILURE);
        int result = mWiseFy.addOpenNetwork(OPEN_NETWORK_SSID);
        assertEquals(WiseFy.WIFI_MANAGER_FAILURE, result);
    }

    @Test
    public void addOpenNetwork_failure_nullSSID() {
        int result = mWiseFy.addOpenNetwork(null);
        assertEquals(WiseFy.WISEFY_FAILURE, result);
    }

    @Test
    public void addOpenNetwork_failure_nullWifiManager() {
        setManagersToNull();
        int result = mWiseFy.addOpenNetwork(OPEN_NETWORK_SSID);
        assertEquals(WiseFy.WISEFY_FAILURE, result);
    }

    @Test
    public void addOpenNetwork_success() {
        setManagers();
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(0);
        int result = mWiseFy.addOpenNetwork(OPEN_NETWORK_SSID);
        assertNotEquals(WiseFy.WIFI_MANAGER_FAILURE, result);
    }

    @Test
    public void addOpenNetwork_success_alreadyConfigured() {
        setManagers();

        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = OPEN_NETWORK_SSID;
        wifiList.add(mWiFiConfiguration);

        WifiInfo mockWiFiInfo = mock(WifiInfo.class);
        when(mockWiFiInfo.getSSID()).thenReturn(OPEN_NETWORK_SSID);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWiFiInfo);

        int result = mWiseFy.addOpenNetwork(OPEN_NETWORK_SSID);
        assertNotEquals(WiseFy.WIFI_MANAGER_FAILURE, result);
    }

    @Test
    public void addWEPNetwork_failure() {
        setManagers();
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(WiseFy.WIFI_MANAGER_FAILURE);
        int result = mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD);
        assertEquals(WiseFy.WIFI_MANAGER_FAILURE, result);
    }

    @Test
    public void addWEPNetwork_failure_nullPassword() {
        int result = mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, null);
        assertEquals(WiseFy.WISEFY_FAILURE, result);
    }

    @Test
    public void addWEPNetwork_failure_nullSSID() {
        int result = mWiseFy.addWEPNetwork(null, WEP_NETWORK_PASSWORD);
        assertEquals(WiseFy.WISEFY_FAILURE, result);
    }

    @Test
    public void addWEPNetwork_failure_nullWifiManager() {
        setManagersToNull();
        int result = mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD);
        assertEquals(WiseFy.WISEFY_FAILURE, result);
    }

    @Test
    public void addWEPNetwork_success() {
        setManagers();
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(0);
        int result = mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD);
        assertNotEquals(WiseFy.WIFI_MANAGER_FAILURE, result);
    }

    @Test
    public void addWEPNetwork_success_alreadyConfigured() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = WEP_NETWORK_SSID;
        wifiList.add(mWiFiConfiguration);

        WifiInfo mockWiFiInfo = mock(WifiInfo.class);
        when(mockWiFiInfo.getSSID()).thenReturn(WEP_NETWORK_SSID);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWiFiInfo);

        int result = mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD);
        assertNotEquals(WiseFy.WIFI_MANAGER_FAILURE, result);
    }

    @Test
    public void addWPA2Network_failure() {
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(WiseFy.WIFI_MANAGER_FAILURE);
        int result = mWiseFy.addWPA2Network(WPA2_NETWORK_SSID, WPA2_NETWORK_PASSWORD);
        assertEquals(WiseFy.WIFI_MANAGER_FAILURE, result);
    }

    @Test
    public void addWPA2Network_failure_nullPassword() {
        int result = mWiseFy.addWPA2Network(WPA2_NETWORK_SSID, null);
        assertEquals(WiseFy.WISEFY_FAILURE, result);
    }

    @Test
    public void addWPA2Network_failure_nullSSID() {
        int result = mWiseFy.addWPA2Network(null, WPA2_NETWORK_PASSWORD);
        assertEquals(WiseFy.WISEFY_FAILURE, result);
    }

    @Test
    public void addWPA2Network_failure_nullWifiManager() {
        setManagersToNull();
        int result = mWiseFy.addWPA2Network(WPA2_NETWORK_SSID, WPA2_NETWORK_PASSWORD);
        assertEquals(WiseFy.WISEFY_FAILURE, result);
    }

    @Test
    public void addWPA2Network_success() {
        setManagers();
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(0);
        int result = mWiseFy.addWPA2Network(WPA2_NETWORK_SSID, WPA2_NETWORK_PASSWORD);
        assertNotEquals(WiseFy.WIFI_MANAGER_FAILURE, result);
    }

    @Test
    public void addWPA2Network_success_alreadyConfigured() {
        setManagers();

        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = WPA2_NETWORK_SSID;
        wifiList.add(mWiFiConfiguration);

        WifiInfo mockWiFiInfo = mock(WifiInfo.class);
        when(mockWiFiInfo.getSSID()).thenReturn(WPA2_NETWORK_SSID);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWiFiInfo);

        int result = mWiseFy.addWPA2Network(WPA2_NETWORK_SSID, WPA2_NETWORK_PASSWORD);
        assertNotEquals(WiseFy.WIFI_MANAGER_FAILURE, result);
    }

    @Test
    public void builder_loggingFalse() {
        WiseFy wiseFy = new WiseFy.withContext(mActivityTestRule.getActivity()).logging(false).getSmarts();
        assertEquals(false, wiseFy.isLoggingEnabled());
    }

    @Test
    public void builder_loggingTrue() {
        WiseFy wiseFy = new WiseFy.withContext(mActivityTestRule.getActivity()).logging(true).getSmarts();
        assertEquals(true, wiseFy.isLoggingEnabled());
    }

    @Test
    public void calculateBars() {
        int result = mWiseFy.calculateBars(-35, 5);
        assertEquals(4, result);
    }

    @Test
    public void compareSignalLevel() {
        int result = mWiseFy.compareSignalLevel(-35, -70);
        assertEquals(35, result);
    }

    @Test
    public void connectToNetwork_failure_emptyList() {
        setManagers();
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(new ArrayList<WifiConfiguration>());
        boolean result = mWiseFy.connectToNetwork(TEST_SSID, 1);
        assertEquals(false, result);
    }

    @Test
    public void connectToNetwork_failure_notAvailable() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = TEST_SSID;
        wifiList.add(mWiFiConfiguration);

        WifiInfo mockWiFiInfo = mock(WifiInfo.class);
        when(mockWiFiInfo.getSSID()).thenReturn(TEST_SSID);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWiFiInfo);

        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(false);
        when(mockNetworkInfo.isConnected()).thenReturn(true);

        boolean result = mWiseFy.connectToNetwork(TEST_SSID, 1);
        assertEquals(false, result);
    }

    @Test
    public void connectToNetwork_failure_notConnected() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = TEST_SSID;
        wifiList.add(mWiFiConfiguration);

        WifiInfo mockWiFiInfo = mock(WifiInfo.class);
        when(mockWiFiInfo.getSSID()).thenReturn(TEST_SSID);

        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWiFiInfo);
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);

        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(true);
        when(mockNetworkInfo.isConnected()).thenReturn(false);

        boolean result = mWiseFy.connectToNetwork(TEST_SSID, 1);
        assertEquals(false, result);
    }

    @Test
    public void connectToNetwork_failure_nullList() {
        setManagers();
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(null);
        boolean result = mWiseFy.connectToNetwork(TEST_SSID, 1);
        assertEquals(false, result);
    }

    @Test
    public void connectToNetwork_failure_nullWifiManager() {
        setManagersToNull();
        boolean result = mWiseFy.connectToNetwork(TEST_SSID, 1);
        assertEquals(false, result);
    }

    @Test
    public void connectToNetwork_success() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = TEST_SSID;
        wifiList.add(mWiFiConfiguration);

        WifiInfo mockWiFiInfo = mock(WifiInfo.class);
        when(mockWiFiInfo.getSSID()).thenReturn(TEST_SSID);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWiFiInfo);

        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(true);
        when(mockNetworkInfo.isConnected()).thenReturn(true);

        boolean result = mWiseFy.connectToNetwork(TEST_SSID, 1);
        assertEquals(true, result);
    }

    @Test
    public void disableWifi_failure() {
        setManagers();
        when(mMockWiFiManager.setWifiEnabled(anyBoolean())).thenReturn(true);
        boolean result = mWiseFy.disableWifi();
        verify(mMockWiFiManager).setWifiEnabled(false);
        assertEquals(true, result);
    }

    @Test
    public void disableWifi_failure_nullWifiManager() {
        setManagersToNull();
        boolean result = mWiseFy.disableWifi();
        assertEquals(false, result);
    }

    @Test
    public void disableWifi_success() {
        setManagers();
        when(mMockWiFiManager.setWifiEnabled(anyBoolean())).thenReturn(true);
        boolean result = mWiseFy.disableWifi();
        verify(mMockWiFiManager).setWifiEnabled(false);
        assertEquals(true, result);
    }

    @Test
    public void disconnectFromCurrentNetwork_failure() {
        setManagers();
        when(mMockWiFiManager.disconnect()).thenReturn(false);
        boolean result = mWiseFy.disconnectFromCurrentNetwork();
        verify(mMockWiFiManager).disconnect();
        assertEquals(false, result);
    }

    @Test
    public void disconnectFromCurrentNetwork_failure_nullWifiManager() {
        setManagersToNull();
        boolean result = mWiseFy.disconnectFromCurrentNetwork();
        assertEquals(false, result);
    }

    @Test
    public void disconnectFromCurrentNetwork_success() {
        setManagers();
        when(mMockWiFiManager.disconnect()).thenReturn(true);
        boolean result = mWiseFy.disconnectFromCurrentNetwork();
        verify(mMockWiFiManager).disconnect();
        assertEquals(true, result);
    }

    @Test
    public void enableWiFi_failure() {
        setManagers();
        when(mMockWiFiManager.setWifiEnabled(anyBoolean())).thenReturn(false);
        boolean result = mWiseFy.enableWifi();
        verify(mMockWiFiManager).setWifiEnabled(true);
        assertEquals(false, result);
    }

    @Test
    public void enableWiFi_failure_nullWifiManager() {
        setManagersToNull();
        boolean result = mWiseFy.enableWifi();
        assertEquals(false, result);
    }

    @Test
    public void enableWiFi_success() {
        setManagers();
        when(mMockWiFiManager.setWifiEnabled(anyBoolean())).thenReturn(true);
        boolean result = mWiseFy.enableWifi();
        verify(mMockWiFiManager).setWifiEnabled(true);
        assertEquals(true, result);
    }

    @Test
    public void getCurrentNetwork_failure_nullWifiManager() {
        setManagersToNull();
        assertEquals(null, mWiseFy.getCurrentNetwork());
    }

    @Test
    public void getCurrentNetwork_success() {
        setManagers();
        WifiInfo mockWifiInfo = mock(WifiInfo.class);
        when(mockWifiInfo.getSSID()).thenReturn(TEST_SSID);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWifiInfo);

        assertEquals(TEST_SSID, mWiseFy.getCurrentNetwork().getSSID());
    }

    @Test
    public void getFrequency_currentNetwork_failure() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setManagers();
            when(mMockWiFiManager.getConnectionInfo()).thenReturn(null);

            assertEquals(WiseFy.WISEFY_FAILURE, mWiseFy.getFrequency());
        }
    }

    @Test
    public void getFrequency_currentNetwork_success() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setManagers();
            WifiInfo mockWifiInfo = mock(WifiInfo.class);
            when(mockWifiInfo.getFrequency()).thenReturn(TEST_NETWORK_FREQUENCY_24GHZ);
            when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWifiInfo);

            assertEquals(TEST_NETWORK_FREQUENCY_24GHZ, mWiseFy.getFrequency());
        }
    }

    @Test
    public void getFrequency_provideWifiInfo_failure() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            assertEquals(WiseFy.WISEFY_FAILURE, mWiseFy.getFrequency(null));
        }
    }

    @Test
    public void getFrequency_provideWifiInfo_success() {
      if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        setManagers();
        WifiInfo mockWifiInfo = mock(WifiInfo.class);
        when(mockWifiInfo.getFrequency()).thenReturn(TEST_NETWORK_FREQUENCY_24GHZ);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWifiInfo);

        assertEquals(TEST_NETWORK_FREQUENCY_24GHZ, mWiseFy.getFrequency(mockWifiInfo));
      }
    }

    @Test
    public void getNearbyAccessPoints_success_filterDuplicates_false() {
        setManagers();
        List<ScanResult> scanResults = new ArrayList<>();
        ScanResult scanResult1 = mock(ScanResult.class);
        scanResult1.SSID = TEST_SSID;
        scanResults.add(scanResult1);
        ScanResult scanResult2 = mock(ScanResult.class);
        scanResult2.SSID = TEST_SSID2;
        scanResults.add(scanResult2);

        when(mMockWiFiManager.getScanResults()).thenReturn(scanResults);

        List<ScanResult> results = mWiseFy.getNearbyAccessPoints(false);

        assertEquals(scanResults, results);
        assertEquals(2, results.size());
    }

    @Test
    public void getNearbyAccessPoints_success_filterDuplicates_true_differentSSIDs() {
        setManagers();
        List<ScanResult> scanResults = new ArrayList<>();
        ScanResult scanResult1 = mock(ScanResult.class);
        scanResult1.SSID = TEST_SSID;
        scanResults.add(scanResult1);
        ScanResult scanResult2 = mock(ScanResult.class);
        scanResult2.SSID = TEST_SSID2;
        scanResults.add(scanResult2);

        when(mMockWiFiManager.getScanResults()).thenReturn(scanResults);

        List<ScanResult> results = mWiseFy.getNearbyAccessPoints(true);

        assertEquals(scanResults, results);
        assertEquals(2, results.size());
    }

    @Test
    public void getNearbyAccessPoints_success_filterDuplicates_true_sameSSIDs_higherFrequency() {
        setManagers();
        List<ScanResult> scanResults = new ArrayList<>();
        ScanResult scanResult1 = mock(ScanResult.class);
        scanResult1.SSID = TEST_SSID;
        scanResult1.level = -35;
        scanResults.add(scanResult1);
        ScanResult scanResult2 = mock(ScanResult.class);
        scanResult2.SSID = TEST_SSID;
        scanResult2.level = -70;
        scanResults.add(scanResult2);

        List<ScanResult> expectedScanResults = new ArrayList<>();
        expectedScanResults.add(scanResult1);

        when(mMockWiFiManager.getScanResults()).thenReturn(scanResults);

        List<ScanResult> results = mWiseFy.getNearbyAccessPoints(true);

        assertEquals(expectedScanResults, results);
        assertEquals(1, results.size());
    }

    @Test
    public void getNearbyAccessPoints_filterDuplicates_true_sameSSIDs_lowerFrequency() {
        setManagers();
        List<ScanResult> scanResults = new ArrayList<>();
        ScanResult scanResult1 = mock(ScanResult.class);
        scanResult1.SSID = TEST_SSID;
        scanResult1.level = -70;
        scanResults.add(scanResult1);
        ScanResult scanResult2 = mock(ScanResult.class);
        scanResult2.SSID = TEST_SSID;
        scanResult2.level = -35;
        scanResults.add(scanResult2);

        List<ScanResult> expectedScanResults = new ArrayList<>();
        expectedScanResults.add(scanResult2);

        when(mMockWiFiManager.getScanResults()).thenReturn(scanResults);

        List<ScanResult> results = mWiseFy.getNearbyAccessPoints(true);

        assertEquals(expectedScanResults, results);
        assertEquals(1, results.size());
    }

    @Test
    public void getNearbyAccessPoints_failure_filterDuplicates_false_nullWifiManager() {
        setManagersToNull();
        List<ScanResult> results = mWiseFy.getNearbyAccessPoints(false);
        assertEquals(null, results);
    }

    @Test
    public void getNearbyAccessPoints_failure_filterDuplicates_true_nullWifiManager() {
        setManagersToNull();
        List<ScanResult> results = mWiseFy.getNearbyAccessPoints(true);
        assertEquals(null, results);
    }

    @Test
    public void getSavedNetworks_failure_nullWifiManager() {
        setManagersToNull();
        assertEquals(null, mWiseFy.getSavedNetworks());
    }

    @Test
    public void getSavedNetworks_success() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = TEST_SSID;
        wifiList.add(mWiFiConfiguration);
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);

        assertEquals(wifiList, mWiseFy.getSavedNetworks());
    }

    @Test
    public void isDeviceConnectedToMobileNetwork_failure_notAvailable() {
        setManagers();
        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);
        when(mockNetworkInfo.getTypeName()).thenReturn("MOBILE");

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(false);
        when(mockNetworkInfo.isConnected()).thenReturn(true);

        boolean result = mWiseFy.isDeviceConnectedToMobileNetwork();
        assertEquals(false, result);
    }

    @Test
    public void isDeviceConnectedToMobileNetwork_failure_notConnected() {
        setManagers();
        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);
        when(mockNetworkInfo.getTypeName()).thenReturn("MOBILE");

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(true);
        when(mockNetworkInfo.isConnected()).thenReturn(false);

        boolean result = mWiseFy.isDeviceConnectedToMobileNetwork();
        assertEquals(false, result);
    }

    @Test
    public void isDeviceConnectedToMobileNetwork_failure_nullConfigurationManager() {
        setManagers();
        setConnectivityManagerToNull();
        boolean result = mWiseFy.isDeviceConnectedToMobileNetwork();
        assertEquals(false, result);
    }

    @Test
    public void isDeviceConnectedToMobileNetwork_failure_nullActiveNetworkInfo() {
        setManagers();
        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(null);
        boolean result = mWiseFy.isDeviceConnectedToMobileNetwork();
        assertEquals(false, result);
    }

    @Test
    public void isDeviceConnectedToMobileNetwork_failure_wrongType() {
        setManagers();
        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);
        when(mockNetworkInfo.getTypeName()).thenReturn("WRONG TYPE");

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(true);
        when(mockNetworkInfo.isConnected()).thenReturn(true);

        boolean result = mWiseFy.isDeviceConnectedToMobileNetwork();
        assertEquals(false, result);
    }

    @Test
    public void isDeviceConnectedToMobileNetwork_success() {
        setManagers();
        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);
        when(mockNetworkInfo.getTypeName()).thenReturn("MOBILE");

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(true);
        when(mockNetworkInfo.isConnected()).thenReturn(true);

        boolean result = mWiseFy.isDeviceConnectedToMobileNetwork();
        assertEquals(true, result);
    }

    @Test
    public void isDeviceConnectedToMobileOrWifiNetwork_success_mobile() {
        setManagers();
        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);
        when(mockNetworkInfo.getTypeName()).thenReturn("MOBILE");

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(true);
        when(mockNetworkInfo.isConnected()).thenReturn(true);

        boolean result = mWiseFy.isDeviceConnectedToMobileOrWifiNetwork();
        assertEquals(true, result);
    }

    @Test
    public void isDeviceConnectedToMobileOrWifiNetwork_success_wifi() {
        setManagers();
        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);
        when(mockNetworkInfo.getTypeName()).thenReturn("WIFI");

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(true);
        when(mockNetworkInfo.isConnected()).thenReturn(true);

        boolean result = mWiseFy.isDeviceConnectedToMobileOrWifiNetwork();
        assertEquals(true, result);
    }

    @Test
    public void isDeviceConnectedToMobileOrWifiNetwork_failure_mobile_notAvailable() {
        setManagers();
        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);
        when(mockNetworkInfo.getTypeName()).thenReturn("MOBILE");

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(false);
        when(mockNetworkInfo.isConnected()).thenReturn(true);

        boolean result = mWiseFy.isDeviceConnectedToMobileOrWifiNetwork();
        assertEquals(false, result);
    }

    @Test
    public void isDeviceConnectedToMobileOrWifiNetwork_failure_wifi_notAvailable() {
        setManagers();
        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);
        when(mockNetworkInfo.getTypeName()).thenReturn("WIFI");

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(false);
        when(mockNetworkInfo.isConnected()).thenReturn(true);

        boolean result = mWiseFy.isDeviceConnectedToMobileOrWifiNetwork();
        assertEquals(false, result);
    }

    @Test
    public void isDeviceConnectedToMobileOrWifiNetwork_failure_mobile_notConnected() {
        setManagers();
        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);
        when(mockNetworkInfo.getTypeName()).thenReturn("MOBILE");

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(true);
        when(mockNetworkInfo.isConnected()).thenReturn(false);

        boolean result = mWiseFy.isDeviceConnectedToMobileOrWifiNetwork();
        assertEquals(false, result);
    }

    @Test
    public void isDeviceConnectedToMobileOrWifiNetwork_failure_wifi_notConnected() {
        setManagers();
        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);
        when(mockNetworkInfo.getTypeName()).thenReturn("WIFI");

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(true);
        when(mockNetworkInfo.isConnected()).thenReturn(false);

        boolean result = mWiseFy.isDeviceConnectedToMobileOrWifiNetwork();
        assertEquals(false, result);
    }

    @Test
    public void isDeviceConnectedToMobileOrWifiNetwork_failure_nullConfigurationManager() {
        setManagersToNull();
        boolean result = mWiseFy.isDeviceConnectedToMobileOrWifiNetwork();
        assertEquals(false, result);
    }

    @Test
    public void isDeviceConnectedToSSID_failure_differentSSID() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = TEST_SSID2;
        wifiList.add(mWiFiConfiguration);

        WifiInfo mockWiFiInfo = mock(WifiInfo.class);
        when(mockWiFiInfo.getSSID()).thenReturn(TEST_SSID2);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWiFiInfo);

        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(true);
        when(mockNetworkInfo.isConnected()).thenReturn(true);

        boolean result = mWiseFy.isDeviceConnectedToSSID(TEST_SSID);
        assertEquals(false, result);
    }

    @Test
    public void isDeviceConnectedToSSID_failure_notAvailable() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = TEST_SSID;
        wifiList.add(mWiFiConfiguration);

        WifiInfo mockWiFiInfo = mock(WifiInfo.class);
        when(mockWiFiInfo.getSSID()).thenReturn(TEST_SSID);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWiFiInfo);

        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(false);
        when(mockNetworkInfo.isConnected()).thenReturn(true);

        boolean result = mWiseFy.isDeviceConnectedToSSID(TEST_SSID);
        assertEquals(false, result);
    }

    @Test
    public void isDeviceConnectedToSSID_failure_notConnected() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = TEST_SSID;
        wifiList.add(mWiFiConfiguration);

        WifiInfo mockWiFiInfo = mock(WifiInfo.class);
        when(mockWiFiInfo.getSSID()).thenReturn(TEST_SSID);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWiFiInfo);

        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(true);
        when(mockNetworkInfo.isConnected()).thenReturn(false);

        boolean result = mWiseFy.isDeviceConnectedToSSID(TEST_SSID);
        assertEquals(false, result);
    }

    @Test
    public void isDeviceConnectedToSSID_failure_nullActiveNetworkInfo() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = TEST_SSID;
        wifiList.add(mWiFiConfiguration);

        WifiInfo mockWiFiInfo = mock(WifiInfo.class);
        when(mockWiFiInfo.getSSID()).thenReturn(TEST_SSID);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWiFiInfo);
        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(null);

        boolean result = mWiseFy.isDeviceConnectedToSSID(TEST_SSID);
        assertEquals(false, result);
    }

    @Test
    public void isDeviceConnectedToSSID_failure_nullConnectionInfo() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = TEST_SSID;
        wifiList.add(mWiFiConfiguration);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(null);

        boolean result = mWiseFy.isDeviceConnectedToSSID(TEST_SSID);
        assertEquals(false, result);
    }

    @Test
    public void isDeviceConnectedToSSID_failure_nullConnectionInfoSSID() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = TEST_SSID;
        wifiList.add(mWiFiConfiguration);

        WifiInfo mockWiFiInfo = mock(WifiInfo.class);
        when(mockWiFiInfo.getSSID()).thenReturn(null);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWiFiInfo);

        boolean result = mWiseFy.isDeviceConnectedToSSID(TEST_SSID);
        assertEquals(false, result);
    }

    @Test
    public void isDeviceConnectedToSSID_failure_nullConnectivityManagerManager() {
        setManagers();
        setConnectivityManagerToNull();

        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = TEST_SSID;
        wifiList.add(mWiFiConfiguration);

        WifiInfo mockWiFiInfo = mock(WifiInfo.class);
        when(mockWiFiInfo.getSSID()).thenReturn(TEST_SSID);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWiFiInfo);

        boolean result = mWiseFy.isDeviceConnectedToSSID(TEST_SSID);
        assertEquals(false, result);
    }

    @Test
    public void isDeviceConnectedToSSID_failure_nullWifiManager() {
        setManagersToNull();
        boolean result = mWiseFy.isDeviceConnectedToSSID(TEST_SSID);
        assertEquals(false, result);
    }

    @Test
    public void isDeviceConnectedToSSID_success() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = TEST_SSID;
        wifiList.add(mWiFiConfiguration);

        WifiInfo mockWiFiInfo = mock(WifiInfo.class);
        when(mockWiFiInfo.getSSID()).thenReturn(TEST_SSID);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWiFiInfo);

        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(true);
        when(mockNetworkInfo.isConnected()).thenReturn(true);

        boolean result = mWiseFy.isDeviceConnectedToSSID(TEST_SSID);
        assertEquals(true, result);
    }

    @Test
    public void isDeviceConnectedToWifiNetwork_Failure_notAvailable() {
        setManagers();
        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);
        when(mockNetworkInfo.getTypeName()).thenReturn("WIFI");

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(false);
        when(mockNetworkInfo.isConnected()).thenReturn(true);

        boolean result = mWiseFy.isDeviceConnectedToWifiNetwork();
        assertEquals(false, result);
    }

    @Test
    public void isDeviceConnectedToWifiNetwork_failure_notConnected() {
        setManagers();
        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);
        when(mockNetworkInfo.getTypeName()).thenReturn("WIFI");

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(true);
        when(mockNetworkInfo.isConnected()).thenReturn(false);

        boolean result = mWiseFy.isDeviceConnectedToWifiNetwork();
        assertEquals(false, result);
    }

    @Test
    public void isDeviceConnectedToWifiNetwork_failure_nullActiveNetworkInfo() {
        setManagers();
        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(null);
        boolean result = mWiseFy.isDeviceConnectedToWifiNetwork();
        assertEquals(false, result);
    }

    @Test
    public void isDeviceConnectedToWifiNetwork_failure_nullConfigurationManager() {
        setManagers();
        setConnectivityManagerToNull();
        boolean result = mWiseFy.isDeviceConnectedToWifiNetwork();
        assertEquals(false, result);
    }

    @Test
    public void isDeviceConnectedToWifiNetwork_failure_wrongType() {
        setManagers();
        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);
        when(mockNetworkInfo.getTypeName()).thenReturn("WRONG TYPE");

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(true);
        when(mockNetworkInfo.isConnected()).thenReturn(true);

        boolean result = mWiseFy.isDeviceConnectedToWifiNetwork();
        assertEquals(false, result);
    }

    @Test
    public void isDeviceConnectedToWifiNetwork_success() {
        setManagers();
        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);
        when(mockNetworkInfo.getTypeName()).thenReturn("WIFI");

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(true);
        when(mockNetworkInfo.isConnected()).thenReturn(true);

        boolean result = mWiseFy.isDeviceConnectedToWifiNetwork();
        assertEquals(true, result);
    }

    @Test
    public void isNetwork5gHz_currentNetwork_failure_above5ghz() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setManagers();
            WifiInfo mockWifiInfo = mock(WifiInfo.class);
            when(mockWifiInfo.getFrequency()).thenReturn(TEST_NETWORK_FREQUENCY_ABOVE_5GHZ);
            when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWifiInfo);

            assertEquals(false, mWiseFy.isNetwork5gHz());
        }
    }

    @Test
    public void isNetwork5gHz_currentNetwork_failure_below5ghz() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setManagers();
            WifiInfo mockWifiInfo = mock(WifiInfo.class);
            when(mockWifiInfo.getFrequency()).thenReturn(TEST_NETWORK_FREQUENCY_BELOW_5GHZ);
            when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWifiInfo);

            assertEquals(false, mWiseFy.isNetwork5gHz());
        }
    }

    @Test
    public void isNetwork5gHz_currentNetwork_success() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setManagers();
            WifiInfo mockWifiInfo = mock(WifiInfo.class);
            when(mockWifiInfo.getFrequency()).thenReturn(TEST_NETWORK_FREQUENCY_5GHZ);
            when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWifiInfo);

            assertEquals(true, mWiseFy.isNetwork5gHz());
        }
    }

    @Test
    public void isNetwork5gHz_provideWifiInfo_failure_above5ghz() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setManagers();
            WifiInfo mockWifiInfo = mock(WifiInfo.class);
            when(mockWifiInfo.getFrequency()).thenReturn(TEST_NETWORK_FREQUENCY_ABOVE_5GHZ);
            when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWifiInfo);

            assertEquals(false, mWiseFy.isNetwork5gHz(mockWifiInfo));
        }
    }

    @Test
    public void isNetwork5gHz_provideWifiInfo_failure_below5ghz() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setManagers();
            WifiInfo mockWifiInfo = mock(WifiInfo.class);
            when(mockWifiInfo.getFrequency()).thenReturn(TEST_NETWORK_FREQUENCY_BELOW_5GHZ);
            when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWifiInfo);

            assertEquals(false, mWiseFy.isNetwork5gHz(mockWifiInfo));
        }
    }

    @Test
    public void isNetwork5gHz_provideWifiInfo_success() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setManagers();
            WifiInfo mockWifiInfo = mock(WifiInfo.class);
            when(mockWifiInfo.getFrequency()).thenReturn(TEST_NETWORK_FREQUENCY_5GHZ);
            when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWifiInfo);

            assertEquals(true, mWiseFy.isNetwork5gHz(mockWifiInfo));
        }
    }

    @Test
    public void isNetworkInConfigurationList_failure() {
        setManagers();
        assertEquals(false, mWiseFy.isNetworkInConfigurationList(TEST_SSID));
    }

    @Test
    public void isNetworkInConfigurationList_failure_noNetworks() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        assertEquals(false, mWiseFy.isNetworkInConfigurationList(TEST_SSID));
    }

    @Test
    public void isNetworkInConfigurationList_failure_nullWifiManager() {
        setManagersToNull();
        assertEquals(false, mWiseFy.isNetworkInConfigurationList(TEST_SSID));
    }

    @Test
    public void isNetworkInConfigurationList_success() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = TEST_SSID;
        wifiList.add(mWiFiConfiguration);
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        assertEquals(true, mWiseFy.isNetworkInConfigurationList(TEST_SSID));
    }

    @Test
    public void isNetworkSecure_success_withWEP() {
        ScanResult scanResult = mock(ScanResult.class);
        scanResult.capabilities = "WEP";
        assertEquals(true, mWiseFy.isNetworkSecure(scanResult));
    }

    @Test
    public void isNetworkSecure_success_withPSK() {
        ScanResult scanResult = mock(ScanResult.class);
        scanResult.capabilities = "PSK";
        assertEquals(true, mWiseFy.isNetworkSecure(scanResult));
    }

    @Test
    public void isNetworkSecure_success_withEAP() {
        ScanResult scanResult = mock(ScanResult.class);
        scanResult.capabilities = "EAP";
        assertEquals(true, mWiseFy.isNetworkSecure(scanResult));
    }

    @Test
    public void isNetworkSecure_failure_emptyCapabilities() {
        ScanResult scanResult = mock(ScanResult.class);
        scanResult.capabilities = "";
        assertEquals(false, mWiseFy.isNetworkSecure(scanResult));
    }

    @Test
    public void isNetworkSecure_failure_nullCapabilities() {
        ScanResult scanResult = mock(ScanResult.class);
        assertEquals(false, mWiseFy.isNetworkSecure(scanResult));
    }

    @Test
    public void isNetworkSecure_failure_nullScanResult() {
        assertEquals(false, mWiseFy.isNetworkSecure(null));
    }

    @Test
    public void isWifiEnabled_failure() {
        setManagers();
        when(mMockWiFiManager.isWifiEnabled()).thenReturn(false);
        assertEquals(false, mWiseFy.isWifiEnabled());
    }

    @Test
    public void isWifiEnabled_success() {
        setManagers();
        when(mMockWiFiManager.isWifiEnabled()).thenReturn(true);
        assertEquals(true, mWiseFy.isWifiEnabled());
    }

    @Test
    public void isWifiEnabled_failure_nullWifiManager() {
        setManagersToNull();
        assertEquals(false, mWiseFy.isWifiEnabled());
    }

    @Test
    public void removeNetwork_failure() {
        setManagers();
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(getTestWifiConfiguration());
        when(mMockWiFiManager.removeNetwork(anyInt())).thenReturn(false);

        boolean result = mWiseFy.removeNetwork(TEST_SSID);
        assertEquals(false, result);
    }

    @Test
    public void removeNetwork_failure_notInList() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        when(mMockWiFiManager.removeNetwork(anyInt())).thenReturn(false);

        boolean result = mWiseFy.removeNetwork(TEST_SSID);
        assertEquals(false, result);
    }

    @Test
    public void removeNetwork_failure_nullWifiManager() {
        setManagersToNull();
        boolean result = mWiseFy.removeNetwork(TEST_SSID);
        assertEquals(false, result);
    }

    @Test
    public void removeNetwork_success() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = TEST_SSID;
        wifiList.add(mWiFiConfiguration);
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        when(mMockWiFiManager.removeNetwork(anyInt())).thenReturn(true);

        boolean result = mWiseFy.removeNetwork(TEST_SSID);
        assertEquals(true, result);
    }

    @Test
    public void searchSSID_failure() {
        setManagers();
        WifiInfo mockWifiInfo = mock(WifiInfo.class);
        when(mockWifiInfo.getSSID()).thenReturn(TEST_SSID);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWifiInfo);

        assertEquals(null, mWiseFy.searchForSSID(TEST_SSID, 1));
    }

    @Test
    public void searchSSID_failure_nullWifiManager() {
        setManagersToNull();
        assertEquals(null, mWiseFy.searchForSSID(TEST_SSID, 1));
    }

    @Test
    public void searchSSID_success() {
        setManagers();
        List<ScanResult> scanResults = new ArrayList<>();
        ScanResult scanResult = mock(ScanResult.class);
        scanResult.SSID = TEST_SSID;
        scanResults.add(scanResult);

        WifiInfo mockWifiInfo = mock(WifiInfo.class);
        when(mockWifiInfo.getSSID()).thenReturn(TEST_SSID);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWifiInfo);
        when(mMockWiFiManager.getScanResults()).thenReturn(scanResults);

        assertEquals(TEST_SSID, mWiseFy.searchForSSID(TEST_SSID, 1));
    }

    /**
     * HELPERS
     */

    private void setManagers() {
        mWiseFy.mWifiManager = mMockWiFiManager;
        mWiseFy.mConnectivityManager = mMockConnectivityManager;
    }

    private void setManagersToNull() {
        mWiseFy.mWifiManager = null;
        mWiseFy.mConnectivityManager = null;
    }

    private void setConnectivityManagerToNull() {
        mWiseFy.mConnectivityManager = null;
    }
}