package com.metova.wisefy;


import android.app.Activity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.test.rule.ActivityTestRule;
import com.metova.wisefy.base.BaseTestClass;
import com.metova.wisefy.util.GetManagerUtil;
import com.metova.wisefy.util.TestActivity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static com.metova.wisefy.util.TestUtils.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class WiseFyTest extends BaseTestClass<TestActivity> {

    @Rule
    public ActivityTestRule<TestActivity> mActivityTestRule = new ActivityTestRule<>(TestActivity.class);

    private ConnectivityManager mMockConnectivityManager;

    private GetManagerUtil mMockGetManagerUtil;

    private WifiManager mMockWiFiManager;

    public WiseFyTest() {
        super(TestActivity.class);
    }

    @Before
    public void setUp() {
        mMockWiFiManager = mock(WifiManager.class);
        mMockConnectivityManager = mock(ConnectivityManager.class);
        mMockGetManagerUtil = mock(GetManagerUtil.class);

        when(mMockGetManagerUtil.getWiFiManager(any(Activity.class))).thenReturn(mMockWiFiManager);
        when(mMockGetManagerUtil.getConnectivityManager(any(Activity.class))).thenReturn(mMockConnectivityManager);

        WiseFy.getSmarts().mGetManagerUtil = mMockGetManagerUtil;

        mActivityTestRule.launchActivity(new Intent());
    }

    @Test
    public void testAddOpenNetwork() {
        int result = WiseFy.getSmarts().addOpenNetwork(mActivityTestRule.getActivity(), OPEN_NETWORK_SSID);
        assertNotEquals(WiseFy.WIFI_MANAGER_FAILURE, result);
    }

    @Test
    public void testAddOpenNetworkFailureToAdd() {
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(WiseFy.WIFI_MANAGER_FAILURE);
        int result = WiseFy.getSmarts().addOpenNetwork(mActivityTestRule.getActivity(), OPEN_NETWORK_SSID);
        assertEquals(WiseFy.WIFI_MANAGER_FAILURE, result);
    }

    @Test
    public void testAddOpenNetworkNullActivity() {
        int result = WiseFy.getSmarts().addOpenNetwork(null, OPEN_NETWORK_SSID);
        assertEquals(WiseFy.WISE_MANAGER_FAILURE, result);
    }

    @Test
    public void testAddOpenNetworkNullPassword() {
        int result = WiseFy.getSmarts().addOpenNetwork(mActivityTestRule.getActivity(), null);
        assertEquals(WiseFy.WISE_MANAGER_FAILURE, result);
    }

    @Test
    public void testAddOpenNetworkNullWifiManager() {
        when(mMockGetManagerUtil.getWiFiManager(any(Activity.class))).thenReturn(null);
        int result = WiseFy.getSmarts().addOpenNetwork(mActivityTestRule.getActivity(), OPEN_NETWORK_SSID);
        assertEquals(WiseFy.WISE_MANAGER_FAILURE, result);
    }

    @Test
    public void testAddWEPNetwork() {
        int result = WiseFy.getSmarts().addWEPNetwork(mActivityTestRule.getActivity(), WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD);
        assertNotEquals(WiseFy.WIFI_MANAGER_FAILURE, result);
    }

    @Test
    public void testAddWEPNetworkFailureToAdd() {
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(WiseFy.WIFI_MANAGER_FAILURE);
        int result = WiseFy.getSmarts().addWEPNetwork(mActivityTestRule.getActivity(), WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD);
        assertEquals(WiseFy.WIFI_MANAGER_FAILURE, result);
    }

    @Test
    public void testAddWEPNetworkNullActivity() {
        int result = WiseFy.getSmarts().addWEPNetwork(null, WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD);
        assertEquals(WiseFy.WISE_MANAGER_FAILURE, result);
    }

    @Test
    public void testAddWEPNetworkNullSSID() {
        int result = WiseFy.getSmarts().addWEPNetwork(mActivityTestRule.getActivity(), null, WEP_NETWORK_PASSWORD);
        assertEquals(WiseFy.WISE_MANAGER_FAILURE, result);
    }

    @Test
    public void testAddWEPNetworkNullPassword() {
        int result = WiseFy.getSmarts().addWEPNetwork(mActivityTestRule.getActivity(), WEP_NETWORK_SSID, null);
        assertEquals(WiseFy.WISE_MANAGER_FAILURE, result);
    }

    @Test
    public void testAddWEPNetworkNullWifiManager() {
        when(mMockGetManagerUtil.getWiFiManager(any(Activity.class))).thenReturn(null);
        int result = WiseFy.getSmarts().addWEPNetwork(mActivityTestRule.getActivity(), WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD);
        assertEquals(WiseFy.WISE_MANAGER_FAILURE, result);
    }

    @Test
    public void testAddWPA2Network() {
        int result = WiseFy.getSmarts().addWPA2Network(mActivityTestRule.getActivity(), WPA2_NETWORK_SSID, WPA2_NETWORK_PASSWORD);
        assertNotEquals(WiseFy.WIFI_MANAGER_FAILURE, result);
    }

    @Test
    public void testAddWPA2NetworkFailureToAdd() {
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(WiseFy.WIFI_MANAGER_FAILURE);
        int result = WiseFy.getSmarts().addWPA2Network(mActivityTestRule.getActivity(), WPA2_NETWORK_SSID, WPA2_NETWORK_PASSWORD);
        assertEquals(WiseFy.WIFI_MANAGER_FAILURE, result);
    }

    @Test
    public void testAddWPA2NetworkNullActivity() {
        int result = WiseFy.getSmarts().addWPA2Network(null, WPA2_NETWORK_SSID, WPA2_NETWORK_PASSWORD);
        assertEquals(WiseFy.WISE_MANAGER_FAILURE, result);
    }

    @Test
    public void testAddWPA2NetworkNullSSID() {
        int result = WiseFy.getSmarts().addWPA2Network(mActivityTestRule.getActivity(), null, WPA2_NETWORK_PASSWORD);
        assertEquals(WiseFy.WISE_MANAGER_FAILURE, result);
    }

    @Test
    public void testAddWPA2NetworkNullPassword() {
        int result = WiseFy.getSmarts().addWPA2Network(mActivityTestRule.getActivity(), WPA2_NETWORK_SSID, null);
        assertEquals(WiseFy.WISE_MANAGER_FAILURE, result);
    }

    @Test
    public void testAddWPA2NetworkNullWifiManager() {
        when(mMockGetManagerUtil.getWiFiManager(any(Activity.class))).thenReturn(null);
        int result = WiseFy.getSmarts().addWPA2Network(mActivityTestRule.getActivity(), WPA2_NETWORK_SSID, WPA2_NETWORK_PASSWORD);
        assertEquals(WiseFy.WISE_MANAGER_FAILURE, result);
    }

    @Test
    public void testCalculateBar() {
        int result = WiseFy.getSmarts().calculateBars(-35, 5);
        assertEquals(4, result);
    }

    @Test
    public void testCompareSignalLevel() {
        int result = WiseFy.getSmarts().compareSignalLevel(-35, -70);
        assertEquals(35, result);
    }

    @Test
    public void testConnectToNetworkFailureNoList() {
        boolean result = WiseFy.getSmarts().connectToNetwork(mActivityTestRule.getActivity(), TEST_SSID, 1);
        assertEquals(false, result);
    }

    @Test
    public void testConnectToNetworkFailureNotAvailable() {
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

        boolean result = WiseFy.getSmarts().connectToNetwork(mActivityTestRule.getActivity(), TEST_SSID, 1);
        assertEquals(false, result);
    }

    @Test
    public void testConnectToNetworkFailureNotConnected() {
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

        boolean result = WiseFy.getSmarts().connectToNetwork(mActivityTestRule.getActivity(), TEST_SSID, 1);
        assertEquals(false, result);
    }

    @Test
    public void testConnectToNetworkFailureNullActivity() {
        boolean result = WiseFy.getSmarts().connectToNetwork(null, TEST_SSID, 1);
        assertEquals(false, result);
    }

    @Test
    public void testConnectToNetworkFailureNullWifiManager() {
        when(mMockGetManagerUtil.getWiFiManager(any(Activity.class))).thenReturn(null);
        boolean result = WiseFy.getSmarts().connectToNetwork(null, TEST_SSID, 1);
        assertEquals(false, result);
    }

    @Test
    public void testConnectToNetworkSuccess() {
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

        boolean result = WiseFy.getSmarts().connectToNetwork(mActivityTestRule.getActivity(), TEST_SSID, 1);
        assertEquals(true, result);
    }

    @Test
    public void testDisableWifiFailure() {
        when(mMockWiFiManager.setWifiEnabled(anyBoolean())).thenReturn(true);
        boolean result = WiseFy.getSmarts().disableWifi(mActivityTestRule.getActivity());
        verify(mMockWiFiManager).setWifiEnabled(false);
        assertEquals(true, result);
    }

    @Test
    public void testDisableWifiNullActivity() {
        boolean result = WiseFy.getSmarts().disableWifi(null);
        assertEquals(false, result);
    }

    @Test
    public void testDisableWifiSuccess() {
        when(mMockWiFiManager.setWifiEnabled(anyBoolean())).thenReturn(true);
        boolean result = WiseFy.getSmarts().disableWifi(mActivityTestRule.getActivity());
        verify(mMockWiFiManager).setWifiEnabled(false);
        assertEquals(true, result);
    }

    @Test
    public void testDisconnectFromCurrentNetworkFailure() {
        when(mMockWiFiManager.disconnect()).thenReturn(false);
        boolean result = WiseFy.getSmarts().disconnectFromCurrentNetwork(mActivityTestRule.getActivity());
        verify(mMockWiFiManager).disconnect();
        assertEquals(false, result);
    }

    @Test
    public void testDisconnectFromCurrentNetworkNullActivity() {
        boolean result = WiseFy.getSmarts().disconnectFromCurrentNetwork(null);
        assertEquals(false, result);
    }

    @Test
    public void testDisconnectFromCurrentNetworkSuccess() {
        when(mMockWiFiManager.disconnect()).thenReturn(true);
        boolean result = WiseFy.getSmarts().disconnectFromCurrentNetwork(mActivityTestRule.getActivity());
        verify(mMockWiFiManager).disconnect();
        assertEquals(true, result);
    }

    @Test
    public void testEnableWiFiFailure() {
        when(mMockWiFiManager.setWifiEnabled(anyBoolean())).thenReturn(false);
        boolean result = WiseFy.getSmarts().enableWifi(mActivityTestRule.getActivity());
        verify(mMockWiFiManager).setWifiEnabled(true);
        assertEquals(false, result);
    }

    @Test
    public void testEnableWiFiNullActivity() {
        boolean result = WiseFy.getSmarts().enableWifi(null);
        assertEquals(false, result);
    }

    @Test
    public void testEnableWiFiSuccess() {
        when(mMockWiFiManager.setWifiEnabled(anyBoolean())).thenReturn(true);
        boolean result = WiseFy.getSmarts().enableWifi(mActivityTestRule.getActivity());
        verify(mMockWiFiManager).setWifiEnabled(true);
        assertEquals(true, result);
    }

    @Test
    public void testGetCurrentNetwork() {
        WifiInfo mockWifiInfo = mock(WifiInfo.class);
        when(mockWifiInfo.getSSID()).thenReturn(TEST_SSID);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWifiInfo);

        assertEquals(TEST_SSID, WiseFy.getSmarts().getCurrentNetwork(mActivityTestRule.getActivity()).getSSID());
    }

    @Test
    public void testGetCurrentNetworkNullActivity() {
        assertEquals(null, WiseFy.getSmarts().getCurrentNetwork(null));
    }

    @Test
    public void testGetCurrentNetworkNullWifiManager() {
        when(mMockGetManagerUtil.getWiFiManager(any(Activity.class))).thenReturn(null);
        assertEquals(null, WiseFy.getSmarts().getCurrentNetwork(mActivityTestRule.getActivity()));
    }

    @Test
    public void testGetNearbyAccessPoints() {
        List<ScanResult> scanResults = new ArrayList<>();
        ScanResult scanResult1 = mock(ScanResult.class);
        scanResult1.SSID = TEST_SSID;
        scanResults.add(scanResult1);
        ScanResult scanResult2 = mock(ScanResult.class);
        scanResult2.SSID = TEST_SSID2;
        scanResults.add(scanResult2);

        when(mMockWiFiManager.getScanResults()).thenReturn(scanResults);

        List<ScanResult> results = WiseFy.getSmarts().getNearbyAccessPoints(mActivityTestRule.getActivity(), false);

        assertEquals(scanResults, results);
        assertEquals(2, results.size());
    }

    @Test
    public void testGetNearbyAccessPointsFilterDuplicatesDifferent() {
        List<ScanResult> scanResults = new ArrayList<>();
        ScanResult scanResult1 = mock(ScanResult.class);
        scanResult1.SSID = TEST_SSID;
        scanResults.add(scanResult1);
        ScanResult scanResult2 = mock(ScanResult.class);
        scanResult2.SSID = TEST_SSID2;
        scanResults.add(scanResult2);

        when(mMockWiFiManager.getScanResults()).thenReturn(scanResults);

        List<ScanResult> results = WiseFy.getSmarts().getNearbyAccessPoints(mActivityTestRule.getActivity(), true);

        assertEquals(scanResults, results);
        assertEquals(2, results.size());
    }

    @Test
    public void testGetNearbyAccessPointsFilterDuplicatesHigher() {
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

        List<ScanResult> results = WiseFy.getSmarts().getNearbyAccessPoints(mActivityTestRule.getActivity(), true);

        assertEquals(expectedScanResults, results);
        assertEquals(1, results.size());
    }

    @Test
    public void testGetNearbyAccessPointsFilterDuplicatesLower() {
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

        List<ScanResult> results = WiseFy.getSmarts().getNearbyAccessPoints(mActivityTestRule.getActivity(), true);

        assertEquals(expectedScanResults, results);
        assertEquals(1, results.size());
    }

    @Test
    public void testGetNearbyAccessPointsNullActivity() {
        List<ScanResult> results = WiseFy.getSmarts().getNearbyAccessPoints(null, true);
        assertEquals(null, results);
    }

    @Test
    public void testGetNearbyAccessPointsNullWifiManager() {
        when(mMockGetManagerUtil.getWiFiManager(any(Activity.class))).thenReturn(null);
        List<ScanResult> results = WiseFy.getSmarts().getNearbyAccessPoints(mActivityTestRule.getActivity(), true);
        assertEquals(null, results);
    }

    @Test
    public void testGetSavedNetworks() {
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = TEST_SSID;
        wifiList.add(mWiFiConfiguration);
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);

        assertEquals(wifiList, WiseFy.getSmarts().getSavedNetworks(mActivityTestRule.getActivity()));
    }

    @Test
    public void testGetSavedNetworksNullActivity() {
        assertEquals(null, WiseFy.getSmarts().getSavedNetworks(null));
    }

    @Test
    public void testGetSavedNetworksNullWifiManager() {
        when(mMockGetManagerUtil.getWiFiManager(any(Activity.class))).thenReturn(null);
        assertEquals(null, WiseFy.getSmarts().getSavedNetworks(mActivityTestRule.getActivity()));
    }

    @Test
    public void testIsDeviceConnectedToMobileNetworkFailureSuccess() {
        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);
        when(mockNetworkInfo.getTypeName()).thenReturn("MOBILE");

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(true);
        when(mockNetworkInfo.isConnected()).thenReturn(true);

        boolean result = WiseFy.getSmarts().isDeviceConnectedToMobileNetwork(mActivityTestRule.getActivity());
        assertEquals(true, result);
    }

    @Test
    public void testIsDeviceConnectedToMobileNetworkFailureNotAvailable() {
        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);
        when(mockNetworkInfo.getTypeName()).thenReturn("MOBILE");

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(false);
        when(mockNetworkInfo.isConnected()).thenReturn(true);

        boolean result = WiseFy.getSmarts().isDeviceConnectedToMobileNetwork(mActivityTestRule.getActivity());
        assertEquals(false, result);
    }

    @Test
    public void testIsDeviceConnectedToMobileNetworkFailureNotConnected() {
        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);
        when(mockNetworkInfo.getTypeName()).thenReturn("MOBILE");

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(true);
        when(mockNetworkInfo.isConnected()).thenReturn(false);

        boolean result = WiseFy.getSmarts().isDeviceConnectedToMobileNetwork(mActivityTestRule.getActivity());
        assertEquals(false, result);
    }

    @Test
    public void testIsDeviceConnectedToMobileNetworkFailureWrongType() {
        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);
        when(mockNetworkInfo.getTypeName()).thenReturn("WRONG TYPE");

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(true);
        when(mockNetworkInfo.isConnected()).thenReturn(true);

        boolean result = WiseFy.getSmarts().isDeviceConnectedToMobileNetwork(null);
        assertEquals(false, result);
    }

    @Test
    public void testIsDeviceConnectedToMobileNetworkNullActivity() {
        boolean result = WiseFy.getSmarts().isDeviceConnectedToMobileNetwork(null);
        assertEquals(false, result);
    }

    @Test
    public void testIsDeviceConnectedToMobileNetworkNullConfigurationManager() {
        when(mMockGetManagerUtil.getConnectivityManager(any(Activity.class))).thenReturn(null);
        boolean result = WiseFy.getSmarts().isDeviceConnectedToMobileNetwork(mActivityTestRule.getActivity());
        assertEquals(false, result);
    }

    @Test
    public void testIsDeviceConnectedToMobileOrWifiNetworkFailureSuccessMobile() {
        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);
        when(mockNetworkInfo.getTypeName()).thenReturn("MOBILE");

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(true);
        when(mockNetworkInfo.isConnected()).thenReturn(true);

        boolean result = WiseFy.getSmarts().isDeviceConnectedToMobileOrWifiNetwork(mActivityTestRule.getActivity());
        assertEquals(true, result);
    }

    @Test
    public void testIsDeviceConnectedToMobileOrWifiNetworkFailureSuccessWifi() {
        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);
        when(mockNetworkInfo.getTypeName()).thenReturn("WIFI");

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(true);
        when(mockNetworkInfo.isConnected()).thenReturn(true);

        boolean result = WiseFy.getSmarts().isDeviceConnectedToMobileOrWifiNetwork(mActivityTestRule.getActivity());
        assertEquals(true, result);
    }

    @Test
    public void testIsDeviceConnectedToMobileOrWifiNetworkFailureNotAvailableMobile() {
        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);
        when(mockNetworkInfo.getTypeName()).thenReturn("MOBILE");

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(false);
        when(mockNetworkInfo.isConnected()).thenReturn(true);

        boolean result = WiseFy.getSmarts().isDeviceConnectedToMobileOrWifiNetwork(mActivityTestRule.getActivity());
        assertEquals(false, result);
    }

    @Test
    public void testIsDeviceConnectedToMobileOrWifiNetworkFailureNotAvailableWifi() {
        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);
        when(mockNetworkInfo.getTypeName()).thenReturn("WIFI");

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(false);
        when(mockNetworkInfo.isConnected()).thenReturn(true);

        boolean result = WiseFy.getSmarts().isDeviceConnectedToMobileOrWifiNetwork(mActivityTestRule.getActivity());
        assertEquals(false, result);
    }

    @Test
    public void testIsDeviceConnectedToMobileOrWifiNetworkFailureNotConnectedMobile() {
        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);
        when(mockNetworkInfo.getTypeName()).thenReturn("MOBILE");

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(true);
        when(mockNetworkInfo.isConnected()).thenReturn(false);

        boolean result = WiseFy.getSmarts().isDeviceConnectedToMobileOrWifiNetwork(mActivityTestRule.getActivity());
        assertEquals(false, result);
    }

    @Test
    public void testIsDeviceConnectedToMobileOrWifiNetworkFailureNotConnectedWifi() {
        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);
        when(mockNetworkInfo.getTypeName()).thenReturn("WIFI");

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(true);
        when(mockNetworkInfo.isConnected()).thenReturn(false);

        boolean result = WiseFy.getSmarts().isDeviceConnectedToMobileOrWifiNetwork(mActivityTestRule.getActivity());
        assertEquals(false, result);
    }

    @Test
    public void testIsDeviceConnectedToMobileOrWifiNetworkFailureWrongType() {
        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);
        when(mockNetworkInfo.getTypeName()).thenReturn("WRONG TYPE");

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(true);
        when(mockNetworkInfo.isConnected()).thenReturn(true);

        boolean result = WiseFy.getSmarts().isDeviceConnectedToMobileOrWifiNetwork(null);
        assertEquals(false, result);
    }

    @Test
    public void testIsDeviceConnectedToMobileWifiNetworkNullActivity() {
        boolean result = WiseFy.getSmarts().isDeviceConnectedToMobileOrWifiNetwork(null);
        assertEquals(false, result);
    }

    @Test
    public void testIsDeviceConnectedToMobileOrWifiNetworkNullConfigurationManager() {
        when(mMockGetManagerUtil.getConnectivityManager(any(Activity.class))).thenReturn(null);
        boolean result = WiseFy.getSmarts().isDeviceConnectedToMobileOrWifiNetwork(mActivityTestRule.getActivity());
        assertEquals(false, result);
    }

    @Test
    public void testIsDeviceConnectedToSSID() {
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

        boolean result = WiseFy.getSmarts().isDeviceConnectedToSSID(mActivityTestRule.getActivity(), TEST_SSID);
        assertEquals(true, result);
    }

    @Test
    public void testIsDeviceConnectedToSSIDFailureOtherSSID() {
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

        boolean result = WiseFy.getSmarts().isDeviceConnectedToSSID(mActivityTestRule.getActivity(), TEST_SSID);
        assertEquals(false, result);
    }

    @Test
    public void testIsDeviceConnectedToSSIDFailureNotAvailable() {
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

        boolean result = WiseFy.getSmarts().isDeviceConnectedToSSID(mActivityTestRule.getActivity(), TEST_SSID);
        assertEquals(false, result);
    }

    @Test
    public void testIsDeviceConnectedToSSIDFailureNotConnected() {
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

        boolean result = WiseFy.getSmarts().isDeviceConnectedToSSID(mActivityTestRule.getActivity(), TEST_SSID);
        assertEquals(false, result);
    }

    @Test
    public void testIsDeviceConnectedToSSIDFailureNullActivity() {
        boolean result = WiseFy.getSmarts().isDeviceConnectedToSSID(null, TEST_SSID);
        assertEquals(false, result);
    }

    @Test
    public void testIsDeviceConnectedToSSIDFailureNullWifiManager() {
        when(mMockGetManagerUtil.getWiFiManager(any(Activity.class))).thenReturn(null);
        boolean result = WiseFy.getSmarts().isDeviceConnectedToSSID(mActivityTestRule.getActivity(), TEST_SSID);
        assertEquals(false, result);
    }

    @Test
    public void testIsDeviceConnectedToWifiNetworkFailureSuccess() {
        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);
        when(mockNetworkInfo.getTypeName()).thenReturn("WIFI");

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(true);
        when(mockNetworkInfo.isConnected()).thenReturn(true);

        boolean result = WiseFy.getSmarts().isDeviceConnectedToWifiNetwork(mActivityTestRule.getActivity());
        assertEquals(true, result);
    }

    @Test
    public void testIsDeviceConnectedToWifiNetworkFailureNotAvailable() {
        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);
        when(mockNetworkInfo.getTypeName()).thenReturn("WIFI");

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(false);
        when(mockNetworkInfo.isConnected()).thenReturn(true);

        boolean result = WiseFy.getSmarts().isDeviceConnectedToWifiNetwork(mActivityTestRule.getActivity());
        assertEquals(false, result);
    }

    @Test
    public void testIsDeviceConnectedToWifiNetworkFailureNotConnected() {
        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);
        when(mockNetworkInfo.getTypeName()).thenReturn("WIFI");

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(true);
        when(mockNetworkInfo.isConnected()).thenReturn(false);

        boolean result = WiseFy.getSmarts().isDeviceConnectedToWifiNetwork(mActivityTestRule.getActivity());
        assertEquals(false, result);
    }

    @Test
    public void testIsDeviceConnectedToWifiNetworkFailureWrongType() {
        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);
        when(mockNetworkInfo.getTypeName()).thenReturn("WRONG TYPE");

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(true);
        when(mockNetworkInfo.isConnected()).thenReturn(true);

        boolean result = WiseFy.getSmarts().isDeviceConnectedToWifiNetwork(null);
        assertEquals(false, result);
    }

    @Test
    public void testIsDeviceConnectedToWifiNetworkNullActivity() {
        boolean result = WiseFy.getSmarts().isDeviceConnectedToWifiNetwork(null);
        assertEquals(false, result);
    }

    @Test
    public void testIsDeviceConnectedToWifiNetworkNullConfigurationManager() {
        when(mMockGetManagerUtil.getConnectivityManager(any(Activity.class))).thenReturn(null);
        boolean result = WiseFy.getSmarts().isDeviceConnectedToWifiNetwork(mActivityTestRule.getActivity());
        assertEquals(false, result);
    }

    @Test
    public void testIsNetworkInConfigurationListFailure() {
        assertEquals(false, WiseFy.getSmarts().isNetworkInConfigurationList(mActivityTestRule.getActivity(), TEST_SSID));
    }

    @Test
    public void testIsNetworkInConfigurationListFailureNoNetworks() {
        List<WifiConfiguration> wifiList = new ArrayList<>();
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        assertEquals(false, WiseFy.getSmarts().isNetworkInConfigurationList(mActivityTestRule.getActivity(), TEST_SSID));
    }

    @Test
    public void testIsNetworkInConfigurationListSuccess() {
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = TEST_SSID;
        wifiList.add(mWiFiConfiguration);
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        assertEquals(true, WiseFy.getSmarts().isNetworkInConfigurationList(mActivityTestRule.getActivity(), TEST_SSID));
    }

    @Test
    public void testIsNetworkInConfigurationNullActivity() {
        assertEquals(false, WiseFy.getSmarts().isNetworkInConfigurationList(null, TEST_SSID));
    }

    @Test
    public void testIsNetworkInConfigurationNullWifiManager() {
        when(mMockGetManagerUtil.getWiFiManager(any(Activity.class))).thenReturn(null);
        assertEquals(false, WiseFy.getSmarts().isNetworkInConfigurationList(mActivityTestRule.getActivity(), TEST_SSID));
    }

    @Test
    public void testIsNetworkSecureWithWEP() {
        ScanResult scanResult = mock(ScanResult.class);
        scanResult.capabilities = "WEP";
        assertEquals(true, WiseFy.getSmarts().isNetworkSecure(scanResult));
    }

    @Test
    public void testIsNetworkSecureWithPSK() {
        ScanResult scanResult = mock(ScanResult.class);
        scanResult.capabilities = "PSK";
        assertEquals(true, WiseFy.getSmarts().isNetworkSecure(scanResult));
    }

    @Test
    public void testIsNetworkSecureWithEAP() {
        ScanResult scanResult = mock(ScanResult.class);
        scanResult.capabilities = "EAP";
        assertEquals(true, WiseFy.getSmarts().isNetworkSecure(scanResult));
    }

    @Test
    public void testIsNetworkSecureEmptyCapabilities() {
        ScanResult scanResult = mock(ScanResult.class);
        scanResult.capabilities = "";
        assertEquals(false, WiseFy.getSmarts().isNetworkSecure(scanResult));
    }

    @Test
    public void testIsNetworkSecureNullCapabilities() {
        ScanResult scanResult = mock(ScanResult.class);
        assertEquals(false, WiseFy.getSmarts().isNetworkSecure(scanResult));
    }

    @Test
    public void testIsWifiEnabledFailure() {
        when(mMockWiFiManager.isWifiEnabled()).thenReturn(false);
        assertEquals(false, WiseFy.getSmarts().isWifiEnabled(mActivityTestRule.getActivity()));
    }

    @Test
    public void testIsWifiEnabledSuccess() {
        when(mMockWiFiManager.isWifiEnabled()).thenReturn(true);
        assertEquals(true, WiseFy.getSmarts().isWifiEnabled(mActivityTestRule.getActivity()));
    }

    @Test
    public void testIsWifiEnabledNullActivity() {
        assertEquals(false, WiseFy.getSmarts().isWifiEnabled(null));
    }

    @Test
    public void testIsWifiEnabledNullWifiManager() {
        when(mMockGetManagerUtil.getWiFiManager(any(Activity.class))).thenReturn(null);
        assertEquals(false, WiseFy.getSmarts().isWifiEnabled(mActivityTestRule.getActivity()));
    }

    @Test
    public void testRemoveNetworkFailure() {
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(getTestWifiConfiguration());
        when(mMockWiFiManager.removeNetwork(anyInt())).thenReturn(false);

        boolean result = WiseFy.getSmarts().removeNetwork(mActivityTestRule.getActivity(), TEST_SSID);
        assertEquals(false, result);
    }

    @Test
    public void testRemoveNetworkFailureNotInList() {
        List<WifiConfiguration> wifiList = new ArrayList<>();
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        when(mMockWiFiManager.removeNetwork(anyInt())).thenReturn(false);

        boolean result = WiseFy.getSmarts().removeNetwork(mActivityTestRule.getActivity(), TEST_SSID);
        assertEquals(false, result);
    }

    @Test
    public void testRemoveNetworkNullActivity() {
        boolean result = WiseFy.getSmarts().removeNetwork(null, TEST_SSID);
        assertEquals(false, result);
    }

    @Test
    public void testRemoveNetworkNullWifiManager() {
        when(mMockGetManagerUtil.getWiFiManager(any(Activity.class))).thenReturn(null);
        boolean result = WiseFy.getSmarts().removeNetwork(null, TEST_SSID);
        assertEquals(false, result);
    }

    @Test
    public void testRemoveNetworkSuccess() {
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = TEST_SSID;
        wifiList.add(mWiFiConfiguration);
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        when(mMockWiFiManager.removeNetwork(anyInt())).thenReturn(true);

        boolean result = WiseFy.getSmarts().removeNetwork(mActivityTestRule.getActivity(), TEST_SSID);
        assertEquals(true, result);
    }

    @Test
    public void testSearchSSIDNFailure() {
        WifiInfo mockWifiInfo = mock(WifiInfo.class);
        when(mockWifiInfo.getSSID()).thenReturn(TEST_SSID);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWifiInfo);

        assertEquals(null, WiseFy.getSmarts().searchForSSID(mActivityTestRule.getActivity(), TEST_SSID, 1));
    }

    @Test
    public void testSearchSSIDNullActivity() {
        assertEquals(null, WiseFy.getSmarts().searchForSSID(null, TEST_SSID, 1));
    }

    @Test
    public void testSearchSSIDNullWifiManager() {
        when(mMockGetManagerUtil.getWiFiManager(any(Activity.class))).thenReturn(null);
        assertEquals(null, WiseFy.getSmarts().searchForSSID(null, TEST_SSID, 1));
    }

    @Test
    public void testSearchSSIDSuccess() {
        List<ScanResult> scanResults = new ArrayList<>();
        ScanResult scanResult = mock(ScanResult.class);
        scanResult.SSID = TEST_SSID;
        scanResults.add(scanResult);

        WifiInfo mockWifiInfo = mock(WifiInfo.class);
        when(mockWifiInfo.getSSID()).thenReturn(TEST_SSID);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWifiInfo);
        when(mMockWiFiManager.getScanResults()).thenReturn(scanResults);

        assertEquals(TEST_SSID, WiseFy.getSmarts().searchForSSID(mActivityTestRule.getActivity(), TEST_SSID, 1));
    }

    @Test
    public void testSearchSSIDSuccessWithoutLogs() {
        List<ScanResult> scanResults = new ArrayList<>();
        ScanResult scanResult = mock(ScanResult.class);
        scanResult.SSID = TEST_SSID;
        scanResults.add(scanResult);

        WifiInfo mockWifiInfo = mock(WifiInfo.class);
        when(mockWifiInfo.getSSID()).thenReturn(TEST_SSID);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWifiInfo);
        when(mMockWiFiManager.getScanResults()).thenReturn(scanResults);

        assertEquals(TEST_SSID, WiseFy.getSmarts(false).searchForSSID(mActivityTestRule.getActivity(), TEST_SSID, 1));
    }
}