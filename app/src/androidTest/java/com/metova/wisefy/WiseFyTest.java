package com.metova.wisefy;


import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import com.metova.wisefy.base.BaseInstrumentationTestCase;
import com.metova.wisefy.util.GetManagerUtil;
import com.metova.wisefy.util.TestActivity;
import com.robotium.solo.Condition;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;


public class WiseFyTest extends BaseInstrumentationTestCase<TestActivity> {

    private WifiManager mMockWiFiManager;

    private ConnectivityManager mMockConnectivityManager;

    private GetManagerUtil mMockGetManagerUtil;

    public WiseFyTest() {
        super(TestActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mMockWiFiManager = mock(WifiManager.class);
        mMockConnectivityManager = mock(ConnectivityManager.class);
        mMockGetManagerUtil = mock(GetManagerUtil.class);

        when(mMockGetManagerUtil.getWiFiManager(any(Activity.class))).thenReturn(mMockWiFiManager);
        when(mMockGetManagerUtil.getConnectivityManager(any(Activity.class))).thenReturn(mMockConnectivityManager);
    }

    public void testAddOpenNetwork() {
        WiseFy.getSmarts().addOpenNetwork(getActivity(), "HAHA");
        WifiManager wifiManager = getLiveWiFiManager(getActivity());
        boolean foundWifiConfiguration = false;
        for(WifiConfiguration wifiConfiguration : wifiManager.getConfiguredNetworks()) {
            if(wifiConfiguration.SSID.replaceAll("\"", "").equals("HAHA")) {
                foundWifiConfiguration = true;
                break;
            }
        }

        assertEquals(true, foundWifiConfiguration);

        WiseFy.getSmarts().removeNetwork(getActivity(), "HAHA");
    }

    public void testAddWEPNetwork() {
        WiseFy.getSmarts().addWEPNetwork(getActivity(), "HAHA", "123456");
        WifiManager wifiManager = getLiveWiFiManager(getActivity());
        boolean foundWifiConfiguration = false;
        for(WifiConfiguration wifiConfiguration : wifiManager.getConfiguredNetworks()) {
            if(wifiConfiguration.SSID.replaceAll("\"", "").equals("HAHA")) {
                foundWifiConfiguration = true;
                break;
            }
        }

        assertEquals(true, foundWifiConfiguration);

        WiseFy.getSmarts().removeNetwork(getActivity(), "HAHA");
    }

    public void testAddWPA2Network() {
        WiseFy.getSmarts().addWPA2Network(getActivity(), "HAHA", "12345678");
        WifiManager wifiManager = getLiveWiFiManager(getActivity());
        boolean foundWifiConfiguration = false;
        for(WifiConfiguration wifiConfiguration : wifiManager.getConfiguredNetworks()) {
            if(wifiConfiguration.SSID.replaceAll("\"", "").equals("HAHA")) {
                foundWifiConfiguration = true;
                break;
            }
        }

        assertEquals(true, foundWifiConfiguration);

        WiseFy.getSmarts().removeNetwork(getActivity(), "HAHA");
    }

    public void testCalculateBar() {
        int result = WiseFy.getSmarts().calculateBars(-35, 5);
        assertEquals(4, result);
    }

    public void testCompareSignalLevel() {
        int result = WiseFy.getSmarts().compareSignalLevel(-35, -70);
        assertEquals(35, result);
    }

    public void testDisableWiFi() {
        WiseFy.getSmarts().mGetManagerUtil = GetManagerUtil.getInstance();
        final WifiManager wifiManager = getLiveWiFiManager(getActivity());
        if(!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }
        assertTrue(getSolo().waitForCondition(new Condition() {
            @Override
            public boolean isSatisfied() {
                return wifiManager.isWifiEnabled();
            }
        }, 3000));
        WiseFy.getSmarts().disableWiFi(getActivity());
        assertTrue(getSolo().waitForCondition(new Condition() {
            @Override
            public boolean isSatisfied() {
                return !wifiManager.isWifiEnabled();
            }
        }, 3000));
        wifiManager.setWifiEnabled(true);
        assertTrue(getSolo().waitForCondition(new Condition() {
            @Override
            public boolean isSatisfied() {
                return wifiManager.isWifiEnabled();
            }
        }, 3000));
    }

    public void testDisconnectFromCurrentNetwork() {
        WiseFy.getSmarts().mGetManagerUtil = GetManagerUtil.getInstance();
        final String originalSSID = WiseFy.getSmarts().getCurrentNetwork(getActivity()).getSSID().replaceAll("\"", "");
        WiseFy.getSmarts().disconnectFromCurrentNetwork(getActivity());
        assertTrue(getSolo().waitForCondition(new Condition() {
            @Override
            public boolean isSatisfied() {
                return WifiInfo.getDetailedStateOf(WiseFy.getSmarts().getCurrentNetwork(getActivity()).getSupplicantState()).equals(NetworkInfo.DetailedState.DISCONNECTED);
            }
        }, 10000));
        assertTrue(getSolo().waitForCondition(new Condition() {
            @Override
            public boolean isSatisfied() {
                return WiseFy.getSmarts().reconnectToNetwork(getActivity(), originalSSID, 30);
            }
        }, 30000));
    }

    public void testEnableWiFi() {
        WiseFy.getSmarts().mGetManagerUtil = GetManagerUtil.getInstance();
        final WifiManager wifiManager = getLiveWiFiManager(getActivity());
        if(wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(false);
        }
        assertTrue(getSolo().waitForCondition(new Condition() {
            @Override
            public boolean isSatisfied() {
                return !wifiManager.isWifiEnabled();
            }
        }, 3000));
        WiseFy.getSmarts().enableWiFi(getActivity());
        assertTrue(getSolo().waitForCondition(new Condition() {
            @Override
            public boolean isSatisfied() {
                return wifiManager.isWifiEnabled();
            }
        }, 3000));
    }

    public void testGetCurrentNetwork() {
        WiseFy.getSmarts().mGetManagerUtil = mMockGetManagerUtil;
        WifiInfo mockWifiInfo = mock(WifiInfo.class);
        when(mockWifiInfo.getSSID()).thenReturn("TEST SSID");
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWifiInfo);

        assertEquals("TEST SSID", WiseFy.getSmarts().getCurrentNetwork(getActivity()).getSSID());
    }

    public void testGetNearbyAccessPoints() {
        WiseFy.getSmarts().mGetManagerUtil = mMockGetManagerUtil;
        List<ScanResult> scanResults = new ArrayList<>();
        ScanResult scanResult1 = mock(ScanResult.class);
        scanResult1.SSID = "Test SSID";
        scanResults.add(scanResult1);
        ScanResult scanResult2 = mock(ScanResult.class);
        scanResult2.SSID = "Test SSID";
        scanResults.add(scanResult2);

        when(mMockWiFiManager.getScanResults()).thenReturn(scanResults);

        List<ScanResult> results = WiseFy.getSmarts().getNearbyAccessPoints(getActivity(), false);

        assertEquals(scanResults, results);
        assertEquals(2, results.size());
    }

    public void testGetNearbyAccessPointsFilterDuplicates() {
        WiseFy.getSmarts().mGetManagerUtil = mMockGetManagerUtil;
        List<ScanResult> scanResults = new ArrayList<>();
        ScanResult scanResult1 = mock(ScanResult.class);
        scanResult1.SSID = "Test SSID";
        scanResult1.level = -35;
        scanResults.add(scanResult1);
        ScanResult scanResult2 = mock(ScanResult.class);
        scanResult2.SSID = "Test SSID";
        scanResult2.level = -70;
        scanResults.add(scanResult2);

        List<ScanResult> expectedScanResults = new ArrayList<>();
        expectedScanResults.add(scanResult1);

        when(mMockWiFiManager.getScanResults()).thenReturn(scanResults);

        List<ScanResult> results = WiseFy.getSmarts().getNearbyAccessPoints(getActivity(), true);

        assertEquals(expectedScanResults, results);
        assertEquals(1, results.size());
    }

    public void testGetSavedNetworks() {
        WiseFy.getSmarts().mGetManagerUtil = mMockGetManagerUtil;
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = "TEST SSID";
        wifiList.add(mWiFiConfiguration);
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);

        assertEquals(wifiList, WiseFy.getSmarts().getSavedNetworks(getActivity()));
    }

    public void testReconnectToNetworkSuccess() {
        WiseFy.getSmarts().mGetManagerUtil = mMockGetManagerUtil;
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = "TEST SSID";
        wifiList.add(mWiFiConfiguration);

        WifiInfo mockWiFiInfo = mock(WifiInfo.class);
        when(mockWiFiInfo.getSSID()).thenReturn("TEST SSID");

        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWiFiInfo);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWiFiInfo);

        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(true);
        when(mockNetworkInfo.isConnected()).thenReturn(true);

        boolean result = WiseFy.getSmarts().reconnectToNetwork(getActivity(), "TEST SSID", 1);
        assertEquals(true, result);
    }

    public void testReconnectToNetworkFailureNotAvailable() {
        WiseFy.getSmarts().mGetManagerUtil = mMockGetManagerUtil;
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = "TEST SSID";
        wifiList.add(mWiFiConfiguration);

        WifiInfo mockWiFiInfo = mock(WifiInfo.class);
        when(mockWiFiInfo.getSSID()).thenReturn("TEST SSID");

        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWiFiInfo);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWiFiInfo);

        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(false);
        when(mockNetworkInfo.isConnected()).thenReturn(true);

        boolean result = WiseFy.getSmarts().reconnectToNetwork(getActivity(), "TEST SSID", 1);
        assertEquals(false, result);
    }

    public void testReconnectToNetworkFailureNotConnected() {
        WiseFy.getSmarts().mGetManagerUtil = mMockGetManagerUtil;
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = "TEST SSID";
        wifiList.add(mWiFiConfiguration);

        WifiInfo mockWiFiInfo = mock(WifiInfo.class);
        when(mockWiFiInfo.getSSID()).thenReturn("TEST SSID");

        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWiFiInfo);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWiFiInfo);

        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(true);
        when(mockNetworkInfo.isConnected()).thenReturn(false);

        boolean result = WiseFy.getSmarts().reconnectToNetwork(getActivity(), "TEST SSID", 1);
        assertEquals(false, result);
    }

    public void testReconnectToNetworkFailureNoList() {
        WiseFy.getSmarts().mGetManagerUtil = mMockGetManagerUtil;
        boolean result = WiseFy.getSmarts().reconnectToNetwork(getActivity(), "TEST SSID", 1);
        assertEquals(false, result);
    }

    public void testSearchSSIDNotFound() {
        WiseFy.getSmarts().mGetManagerUtil = mMockGetManagerUtil;
        WifiInfo mockWifiInfo = mock(WifiInfo.class);
        when(mockWifiInfo.getSSID()).thenReturn("TEST SSID");
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWifiInfo);

        assertEquals(null, WiseFy.getSmarts().searchForSSID(getActivity(), "TEST", 1));
    }

    public void testSearchSSIDFound() {
        WiseFy.getSmarts().mGetManagerUtil = mMockGetManagerUtil;
        List<ScanResult> scanResults = new ArrayList<>();
        ScanResult scanResult = mock(ScanResult.class);
        scanResult.SSID = "Test SSID";
        scanResults.add(scanResult);

        WifiInfo mockWifiInfo = mock(WifiInfo.class);
        when(mockWifiInfo.getSSID()).thenReturn("TEST SSID");
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWifiInfo);
        when(mMockWiFiManager.getScanResults()).thenReturn(scanResults);

        assertEquals("Test SSID", WiseFy.getSmarts().searchForSSID(getActivity(), "TEST", 1));
    }

    /**
     * HELPERS
     */

    private WifiManager getLiveWiFiManager(Activity activity) {
        return (WifiManager) activity.getSystemService(Context.WIFI_SERVICE);
    }
}