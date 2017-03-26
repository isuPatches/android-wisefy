package com.isupatches.wisefy;


import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.test.rule.ActivityTestRule;
import com.isupatches.wisefy.base.BaseTestClass;
import com.isupatches.wisefy.base.TestActivity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static com.isupatches.wisefy.base.TestUtils.*;
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
    public void testAddOpenNetworkSuccess() {
        setManagers();
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(0);
        int result = mWiseFy.addOpenNetwork(OPEN_NETWORK_SSID);
        assertNotEquals(WiseFy.WIFI_MANAGER_FAILURE, result);
    }

    @Test
    public void testAddOpenNetworkFailure() {
        setManagers();
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(WiseFy.WIFI_MANAGER_FAILURE);
        int result = mWiseFy.addOpenNetwork(OPEN_NETWORK_SSID);
        assertEquals(WiseFy.WIFI_MANAGER_FAILURE, result);
    }

    @Test
    public void testAddOpenNetworkNullSSID() {
        int result = mWiseFy.addOpenNetwork(null);
        assertEquals(WiseFy.WISEFY_FAILURE, result);
    }

    @Test
    public void testAddOpenNetworkNullWifiManager() {
        setManagersToNull();
        int result = mWiseFy.addOpenNetwork(OPEN_NETWORK_SSID);
        assertEquals(WiseFy.WISEFY_FAILURE, result);
    }

    @Test
    public void testAddWEPNetworkSuccess() {
        setManagers();
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(0);
        int result = mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD);
        assertNotEquals(WiseFy.WIFI_MANAGER_FAILURE, result);
    }

    @Test
    public void testAddWEPNetworkFailure() {
        setManagers();
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(WiseFy.WIFI_MANAGER_FAILURE);
        int result = mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD);
        assertEquals(WiseFy.WIFI_MANAGER_FAILURE, result);
    }

    @Test
    public void testAddWEPNetworkNullPassword() {
        int result = mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, null);
        assertEquals(WiseFy.WISEFY_FAILURE, result);
    }

    @Test
    public void testAddWEPNetworkNullSSID() {
        int result = mWiseFy.addWEPNetwork(null, WEP_NETWORK_PASSWORD);
        assertEquals(WiseFy.WISEFY_FAILURE, result);
    }

    @Test
    public void testAddWEPNetworkNullWifiManager() {
        setManagersToNull();
        int result = mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD);
        assertEquals(WiseFy.WISEFY_FAILURE, result);
    }

    @Test
    public void testAddWPA2NetworkSuccess() {
        setManagers();
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(0);
        int result = mWiseFy.addWPA2Network(WPA2_NETWORK_SSID, WPA2_NETWORK_PASSWORD);
        assertNotEquals(WiseFy.WIFI_MANAGER_FAILURE, result);
    }

    @Test
    public void testAddWPA2NetworkFailure() {
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(WiseFy.WIFI_MANAGER_FAILURE);
        int result = mWiseFy.addWPA2Network(WPA2_NETWORK_SSID, WPA2_NETWORK_PASSWORD);
        assertEquals(WiseFy.WIFI_MANAGER_FAILURE, result);
    }

    @Test
    public void testAddWPA2NetworkNullPassword() {
        int result = mWiseFy.addWPA2Network(WPA2_NETWORK_SSID, null);
        assertEquals(WiseFy.WISEFY_FAILURE, result);
    }

    @Test
    public void testAddWPA2NetworkNullSSID() {
        int result = mWiseFy.addWPA2Network(null, WPA2_NETWORK_PASSWORD);
        assertEquals(WiseFy.WISEFY_FAILURE, result);
    }

    @Test
    public void testAddWPA2NetworkNullWifiManager() {
        setManagersToNull();
        int result = mWiseFy.addWPA2Network(WPA2_NETWORK_SSID, WPA2_NETWORK_PASSWORD);
        assertEquals(WiseFy.WISEFY_FAILURE, result);
    }

    @Test
    public void testBuilderWithLoggingFalse() {
        WiseFy wiseFy = new WiseFy.withContext(mActivityTestRule.getActivity()).logging(false).getSmarts();
        assertEquals(false, wiseFy.isLoggingEnabled());
    }

    @Test
    public void testBuilderWithLoggingTrue() {
        WiseFy wiseFy = new WiseFy.withContext(mActivityTestRule.getActivity()).logging(true).getSmarts();
        assertEquals(true, wiseFy.isLoggingEnabled());
    }

    @Test
    public void testCalculateBar() {
        int result = mWiseFy.calculateBars(-35, 5);
        assertEquals(4, result);
    }

    @Test
    public void testCompareSignalLevel() {
        int result = mWiseFy.compareSignalLevel(-35, -70);
        assertEquals(35, result);
    }

    @Test
    public void testConnectToNetworkFailureNoList() {
        setManagers();
        boolean result = mWiseFy.connectToNetwork(TEST_SSID, 1);
        assertEquals(false, result);
    }

    @Test
    public void testConnectToNetworkFailureNotAvailable() {
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
    public void testConnectToNetworkFailureNotConnected() {
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
    public void testConnectToNetworkFailureNullWifiManager() {
        setManagersToNull();
        boolean result = mWiseFy.connectToNetwork(TEST_SSID, 1);
        assertEquals(false, result);
    }

    @Test
    public void testConnectToNetworkSuccess() {
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
    public void testDisableWifiFailure() {
        setManagers();
        when(mMockWiFiManager.setWifiEnabled(anyBoolean())).thenReturn(true);
        boolean result = mWiseFy.disableWifi();
        verify(mMockWiFiManager).setWifiEnabled(false);
        assertEquals(true, result);
    }

    @Test
    public void testDisableWifiNullWifiManager() {
        setManagersToNull();
        boolean result = mWiseFy.disableWifi();
        assertEquals(false, result);
    }

    @Test
    public void testDisableWifiSuccess() {
        setManagers();
        when(mMockWiFiManager.setWifiEnabled(anyBoolean())).thenReturn(true);
        boolean result = mWiseFy.disableWifi();
        verify(mMockWiFiManager).setWifiEnabled(false);
        assertEquals(true, result);
    }

    @Test
    public void testDisconnectFromCurrentNetworkFailure() {
        setManagers();
        when(mMockWiFiManager.disconnect()).thenReturn(false);
        boolean result = mWiseFy.disconnectFromCurrentNetwork();
        verify(mMockWiFiManager).disconnect();
        assertEquals(false, result);
    }

    @Test
    public void testDisconnectFromCurrentNetworkNullWifiManager() {
        setManagersToNull();
        boolean result = mWiseFy.disconnectFromCurrentNetwork();
        assertEquals(false, result);
    }

    @Test
    public void testDisconnectFromCurrentNetworkSuccess() {
        setManagers();
        when(mMockWiFiManager.disconnect()).thenReturn(true);
        boolean result = mWiseFy.disconnectFromCurrentNetwork();
        verify(mMockWiFiManager).disconnect();
        assertEquals(true, result);
    }

    @Test
    public void testEnableWiFiFailure() {
        setManagers();
        when(mMockWiFiManager.setWifiEnabled(anyBoolean())).thenReturn(false);
        boolean result = mWiseFy.enableWifi();
        verify(mMockWiFiManager).setWifiEnabled(true);
        assertEquals(false, result);
    }

    @Test
    public void testEnableWiFiNullWifiManager() {
        setManagersToNull();
        boolean result = mWiseFy.enableWifi();
        assertEquals(false, result);
    }

    @Test
    public void testEnableWiFiSuccess() {
        setManagers();
        when(mMockWiFiManager.setWifiEnabled(anyBoolean())).thenReturn(true);
        boolean result = mWiseFy.enableWifi();
        verify(mMockWiFiManager).setWifiEnabled(true);
        assertEquals(true, result);
    }

    @Test
    public void testGetCurrentNetwork() {
        setManagers();
        WifiInfo mockWifiInfo = mock(WifiInfo.class);
        when(mockWifiInfo.getSSID()).thenReturn(TEST_SSID);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWifiInfo);

        assertEquals(TEST_SSID, mWiseFy.getCurrentNetwork().getSSID());
    }

    @Test
    public void testGetCurrentNetworkNullWifiManager() {
        setManagersToNull();
        assertEquals(null, mWiseFy.getCurrentNetwork());
    }

    @Test
    public void testGetFrequency() {
      if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        setManagers();
        WifiInfo mockWifiInfo = mock(WifiInfo.class);
        when(mockWifiInfo.getFrequency()).thenReturn(TEST_NETWORK_FREQUENCY);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWifiInfo);

        assertEquals(TEST_NETWORK_FREQUENCY, mWiseFy.getFrequency(mockWifiInfo));
      }
    }

    @Test
    public void testGetFrequencyError() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            assertEquals(WiseFy.WISEFY_FAILURE, mWiseFy.getFrequency(null));
        }
    }

    @Test
    public void testGetFrequencyCurrentNetwork() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setManagers();
            WifiInfo mockWifiInfo = mock(WifiInfo.class);
            when(mockWifiInfo.getFrequency()).thenReturn(TEST_NETWORK_FREQUENCY);
            when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWifiInfo);

            assertEquals(TEST_NETWORK_FREQUENCY, mWiseFy.getFrequency());
        }
    }

    @Test
    public void testGetFrequencyCurrentNetworkError() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setManagers();
            when(mMockWiFiManager.getConnectionInfo()).thenReturn(null);

            assertEquals(WiseFy.WISEFY_FAILURE, mWiseFy.getFrequency());
        }
    }

    @Test
    public void testGetNearbyAccessPoints() {
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
    public void testGetNearbyAccessPointsFilterDuplicatesDifferent() {
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
    public void testGetNearbyAccessPointsFilterDuplicatesHigher() {
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
    public void testGetNearbyAccessPointsFilterDuplicatesLower() {
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
    public void testGetNearbyAccessPointsNullWifiManager() {
        setManagersToNull();
        List<ScanResult> results = mWiseFy.getNearbyAccessPoints(true);
        assertEquals(null, results);
    }

    @Test
    public void testGetSavedNetworks() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = TEST_SSID;
        wifiList.add(mWiFiConfiguration);
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);

        assertEquals(wifiList, mWiseFy.getSavedNetworks());
    }

    @Test
    public void testGetSavedNetworksNullWifiManager() {
        setManagersToNull();
        assertEquals(null, mWiseFy.getSavedNetworks());
    }

    @Test
    public void testIsDeviceConnectedToMobileNetworkFailureSuccess() {
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
    public void testIsDeviceConnectedToMobileNetworkFailureNotAvailable() {
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
    public void testIsDeviceConnectedToMobileNetworkFailureNotConnected() {
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
    public void testIsDeviceConnectedToMobileNetworkFailureWrongType() {
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
    public void testIsDeviceConnectedToMobileNetworkNullConfigurationManager() {
        setManagersToNull();
        boolean result = mWiseFy.isDeviceConnectedToMobileNetwork();
        assertEquals(false, result);
    }

    @Test
    public void testIsDeviceConnectedToMobileOrWifiNetworkFailureSuccessMobile() {
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
    public void testIsDeviceConnectedToMobileOrWifiNetworkFailureSuccessWifi() {
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
    public void testIsDeviceConnectedToMobileOrWifiNetworkFailureNotAvailableMobile() {
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
    public void testIsDeviceConnectedToMobileOrWifiNetworkFailureNotAvailableWifi() {
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
    public void testIsDeviceConnectedToMobileOrWifiNetworkFailureNotConnectedMobile() {
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
    public void testIsDeviceConnectedToMobileOrWifiNetworkFailureNotConnectedWifi() {
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
    public void testIsDeviceConnectedToMobileOrWifiNetworkNullConfigurationManager() {
        setManagersToNull();
        boolean result = mWiseFy.isDeviceConnectedToMobileOrWifiNetwork();
        assertEquals(false, result);
    }

    @Test
    public void testIsDeviceConnectedToSSID() {
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
    public void testIsDeviceConnectedToSSIDFailureOtherSSID() {
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
    public void testIsDeviceConnectedToSSIDFailureNotAvailable() {
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
    public void testIsDeviceConnectedToSSIDFailureNotConnected() {
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
    public void testIsDeviceConnectedToSSIDFailureNullWifiManager() {
        setManagersToNull();
        boolean result = mWiseFy.isDeviceConnectedToSSID(TEST_SSID);
        assertEquals(false, result);
    }

    @Test
    public void testIsDeviceConnectedToWifiNetworkFailureSuccess() {
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
    public void testIsDeviceConnectedToWifiNetworkFailureNotAvailable() {
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
    public void testIsDeviceConnectedToWifiNetworkFailureNotConnected() {
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
    public void testIsDeviceConnectedToWifiNetworkFailureWrongType() {
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
    public void testIsDeviceConnectedToWifiNetworkNullConfigurationManager() {
        setManagersToNull();
        boolean result = mWiseFy.isDeviceConnectedToWifiNetwork();
        assertEquals(false, result);
    }

    @Test
    public void testIsNetwork5gHzCurrentNetworkTrue() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setManagers();
            WifiInfo mockWifiInfo = mock(WifiInfo.class);
            when(mockWifiInfo.getFrequency()).thenReturn(TEST_NETWORK_FREQUENCY_5G);
            when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWifiInfo);

            assertEquals(true, mWiseFy.isNetwork5gHz());
        }
    }

    @Test
    public void testIsNetwork5gHzCurrentNetworkFalse() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setManagers();
            WifiInfo mockWifiInfo = mock(WifiInfo.class);
            when(mockWifiInfo.getFrequency()).thenReturn(TEST_NETWORK_FREQUENCY);
            when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWifiInfo);

            assertEquals(false, mWiseFy.isNetwork5gHz());
        }
    }

    @Test
    public void testIsNetwork5gHzTrue() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setManagers();
            WifiInfo mockWifiInfo = mock(WifiInfo.class);
            when(mockWifiInfo.getFrequency()).thenReturn(TEST_NETWORK_FREQUENCY_5G);
            when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWifiInfo);

            assertEquals(true, mWiseFy.isNetwork5gHz(mockWifiInfo));
        }
    }

    @Test
    public void testIsNetwork5gHzFalse() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setManagers();
            WifiInfo mockWifiInfo = mock(WifiInfo.class);
            when(mockWifiInfo.getFrequency()).thenReturn(TEST_NETWORK_FREQUENCY);
            when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWifiInfo);

            assertEquals(false, mWiseFy.isNetwork5gHz(mockWifiInfo));
        }
    }

    @Test
    public void testIsNetworkInConfigurationListFailure() {
        setManagers();
        assertEquals(false, mWiseFy.isNetworkInConfigurationList(TEST_SSID));
    }

    @Test
    public void testIsNetworkInConfigurationListFailureNoNetworks() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        assertEquals(false, mWiseFy.isNetworkInConfigurationList(TEST_SSID));
    }

    @Test
    public void testIsNetworkInConfigurationListSuccess() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = TEST_SSID;
        wifiList.add(mWiFiConfiguration);
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        assertEquals(true, mWiseFy.isNetworkInConfigurationList(TEST_SSID));
    }

    @Test
    public void testIsNetworkInConfigurationNullWifiManager() {
        setManagersToNull();
        assertEquals(false, mWiseFy.isNetworkInConfigurationList(TEST_SSID));
    }

    @Test
    public void testIsNetworkSecureWithWEP() {
        ScanResult scanResult = mock(ScanResult.class);
        scanResult.capabilities = "WEP";
        assertEquals(true, mWiseFy.isNetworkSecure(scanResult));
    }

    @Test
    public void testIsNetworkSecureWithPSK() {
        ScanResult scanResult = mock(ScanResult.class);
        scanResult.capabilities = "PSK";
        assertEquals(true, mWiseFy.isNetworkSecure(scanResult));
    }

    @Test
    public void testIsNetworkSecureWithEAP() {
        ScanResult scanResult = mock(ScanResult.class);
        scanResult.capabilities = "EAP";
        assertEquals(true, mWiseFy.isNetworkSecure(scanResult));
    }

    @Test
    public void testIsNetworkSecureEmptyCapabilities() {
        ScanResult scanResult = mock(ScanResult.class);
        scanResult.capabilities = "";
        assertEquals(false, mWiseFy.isNetworkSecure(scanResult));
    }

    @Test
    public void testIsNetworkSecureNullCapabilities() {
        ScanResult scanResult = mock(ScanResult.class);
        assertEquals(false, mWiseFy.isNetworkSecure(scanResult));
    }

    @Test
    public void testIsWifiEnabledFailure() {
        setManagers();
        when(mMockWiFiManager.isWifiEnabled()).thenReturn(false);
        assertEquals(false, mWiseFy.isWifiEnabled());
    }

    @Test
    public void testIsWifiEnabledSuccess() {
        setManagers();
        when(mMockWiFiManager.isWifiEnabled()).thenReturn(true);
        assertEquals(true, mWiseFy.isWifiEnabled());
    }

    @Test
    public void testIsWifiEnabledNullWifiManager() {
        setManagersToNull();
        assertEquals(false, mWiseFy.isWifiEnabled());
    }

    @Test
    public void testRemoveNetworkFailure() {
        setManagers();
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(getTestWifiConfiguration());
        when(mMockWiFiManager.removeNetwork(anyInt())).thenReturn(false);

        boolean result = mWiseFy.removeNetwork(TEST_SSID);
        assertEquals(false, result);
    }

    @Test
    public void testRemoveNetworkFailureNotInList() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        when(mMockWiFiManager.removeNetwork(anyInt())).thenReturn(false);

        boolean result = mWiseFy.removeNetwork(TEST_SSID);
        assertEquals(false, result);
    }

    @Test
    public void testRemoveNetworkNullWifiManager() {
        setManagersToNull();
        boolean result = mWiseFy.removeNetwork(TEST_SSID);
        assertEquals(false, result);
    }

    @Test
    public void testRemoveNetworkSuccess() {
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
    public void testSearchSSIDNFailure() {
        setManagers();
        WifiInfo mockWifiInfo = mock(WifiInfo.class);
        when(mockWifiInfo.getSSID()).thenReturn(TEST_SSID);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWifiInfo);

        assertEquals(null, mWiseFy.searchForSSID(TEST_SSID, 1));
    }

    @Test
    public void testSearchSSIDNullWifiManager() {
        setManagersToNull();
        assertEquals(null, mWiseFy.searchForSSID(TEST_SSID, 1));
    }

    @Test
    public void testSearchSSIDSuccess() {
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
}