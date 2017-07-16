package com.isupatches.wisefy;


import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import com.isupatches.wisefy.base.TestActivity;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static com.isupatches.wisefy.base.TestUtils.TEST_SSID;
import static com.isupatches.wisefy.base.TestUtils.TEST_SSID2;
import static com.isupatches.wisefy.base.TestUtils.TEST_SSID3;
import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class WiseFySearchTests extends BaseTestClass<TestActivity> {

    public WiseFySearchTests() {
        super(TestActivity.class);
    }

    /*
     * findSavedNetworkByRegex tests
     */

    @Test
    public void findSavedNetworkByRegex_failure_nullSavedNetworkList() {
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(null);
        assertEquals(null, mWiseFy.mWiseFySearch.findSavedNetworkByRegex(TEST_SSID));
    }

    @Test
    public void findSavedNetworkByRegex_failure_emptySavedNetworkList() {
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(new ArrayList<WifiConfiguration>());
        assertEquals(null, mWiseFy.mWiseFySearch.findSavedNetworkByRegex(TEST_SSID));
    }

    @Test
    public void findSavedNetworkByRegex_failure_nullWifiConfiguration() {
        List<WifiConfiguration> wifiList = new ArrayList<>();
        wifiList.add(null);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        assertEquals(null, mWiseFy.mWiseFySearch.findSavedNetworkByRegex(TEST_SSID));
    }

    @Test
    public void findSavedNetworkByRegex_failure_nullSSID() {
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = null;
        wifiList.add(mWiFiConfiguration);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        assertEquals(null, mWiseFy.mWiseFySearch.findSavedNetworkByRegex(TEST_SSID));
    }

    @Test
    public void findSavedNetworkByRegex_failure_nonMatchingSSID() {
        List<WifiConfiguration> wifiList = new ArrayList<>();

        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = TEST_SSID2;
        wifiList.add(mWiFiConfiguration);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        assertEquals(null, mWiseFy.mWiseFySearch.findSavedNetworkByRegex(TEST_SSID));
    }

    @Test
    public void findSavedNetworkByRegex_failure_multipleNonMatchingSSIDs() {
        List<WifiConfiguration> wifiList = new ArrayList<>();

        WifiConfiguration mWiFiConfiguration1 = new WifiConfiguration();
        mWiFiConfiguration1.SSID = TEST_SSID2;
        wifiList.add(mWiFiConfiguration1);

        WifiConfiguration mWiFiConfiguration2 = new WifiConfiguration();
        mWiFiConfiguration2.SSID = TEST_SSID3;
        wifiList.add(mWiFiConfiguration2);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        assertEquals(null, mWiseFy.mWiseFySearch.findSavedNetworkByRegex(TEST_SSID));
    }

    @Test
    public void findSavedNetworkByRegex_success() {
        List<WifiConfiguration> wifiList = new ArrayList<>();

        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = TEST_SSID;
        wifiList.add(mWiFiConfiguration);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        assertEquals(mWiFiConfiguration, mWiseFy.mWiseFySearch.findSavedNetworkByRegex(TEST_SSID));
    }

    @Test
    public void findSavedNetworkByRegex_success_multipleSSIDs() {
        List<WifiConfiguration> wifiList = new ArrayList<>();

        WifiConfiguration mWiFiConfiguration1 = new WifiConfiguration();
        mWiFiConfiguration1.SSID = TEST_SSID;
        wifiList.add(mWiFiConfiguration1);

        WifiConfiguration mWiFiConfiguration2 = new WifiConfiguration();
        mWiFiConfiguration2.SSID = TEST_SSID2;
        wifiList.add(mWiFiConfiguration2);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        assertEquals(mWiFiConfiguration1, mWiseFy.mWiseFySearch.findSavedNetworkByRegex(TEST_SSID));
    }

    /*
     * findSavedNetworksMatchingRegex tests
     */

    @Test
    public void findSavedNetworksMatchingRegex_failure_nullSavedNetworkList() {
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(null);
        assertEquals(null, mWiseFy.mWiseFySearch.findSavedNetworksMatchingRegex(TEST_SSID));
    }

    @Test
    public void findSavedNetworksMatchingRegex_failure_emptySavedNetworkList() {
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(new ArrayList<WifiConfiguration>());
        assertEquals(null, mWiseFy.mWiseFySearch.findSavedNetworksMatchingRegex(TEST_SSID));
    }

    @Test
    public void findSavedNetworksMatchingRegex_failure_nullWifiConfiguration() {
        List<WifiConfiguration> wifiList = new ArrayList<>();
        wifiList.add(null);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        assertEquals(null, mWiseFy.mWiseFySearch.findSavedNetworksMatchingRegex(TEST_SSID));
    }

    @Test
    public void findSavedNetworksMatchingRegex_failure_nullSSID() {
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = null;
        wifiList.add(mWiFiConfiguration);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        assertEquals(null, mWiseFy.mWiseFySearch.findSavedNetworksMatchingRegex(TEST_SSID));
    }

    @Test
    public void findSavedNetworksMatchingRegex_failure_nonMatchingSSID() {
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = TEST_SSID2;
        wifiList.add(mWiFiConfiguration);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        assertEquals(null, mWiseFy.mWiseFySearch.findSavedNetworksMatchingRegex(TEST_SSID));
    }

    @Test
    public void findSavedNetworksMatchingRegex_failure_multipleNonMatchingSSIDs() {
        List<WifiConfiguration> wifiList = new ArrayList<>();

        WifiConfiguration mWiFiConfiguration1 = new WifiConfiguration();
        mWiFiConfiguration1.SSID = TEST_SSID2;
        wifiList.add(mWiFiConfiguration1);

        WifiConfiguration mWiFiConfiguration2 = new WifiConfiguration();
        mWiFiConfiguration2.SSID = TEST_SSID3;
        wifiList.add(mWiFiConfiguration2);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        assertEquals(null, mWiseFy.mWiseFySearch.findSavedNetworksMatchingRegex(TEST_SSID));
    }

    @Test
    public void findSavedNetworksMatchingRegex_success() {
        List<WifiConfiguration> wifiList = new ArrayList<>();

        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = TEST_SSID;
        wifiList.add(mWiFiConfiguration);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        assertEquals(wifiList, mWiseFy.mWiseFySearch.findSavedNetworksMatchingRegex(TEST_SSID));
    }

    @Test
    public void findSavedNetworksMatchingRegex_success_multipleSSIDs() {
        List<WifiConfiguration> wifiList = new ArrayList<>();

        WifiConfiguration mWiFiConfiguration1 = new WifiConfiguration();
        mWiFiConfiguration1.SSID = TEST_SSID;
        wifiList.add(mWiFiConfiguration1);

        WifiConfiguration mWiFiConfiguration2 = new WifiConfiguration();
        mWiFiConfiguration2.SSID = TEST_SSID2;
        wifiList.add(mWiFiConfiguration2);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        assertEquals(wifiList, mWiseFy.mWiseFySearch.findSavedNetworksMatchingRegex(".*Test.*"));
    }

    /*
     * findSSIDsMatchingRegex
     */


    @Test
    public void findSSIDsMatchingRegex_failure_nullAccessPoints() {
        when(mMockWiFiManager.getScanResults()).thenReturn(null);
        assertEquals(null, mWiseFy.mWiseFySearch.findSSIDsMatchingRegex(TEST_SSID));
    }

    @Test
    public void findSSIDsMatchingRegex_failure_emptySavedNetworkList() {
        when(mMockWiFiManager.getScanResults()).thenReturn(new ArrayList<ScanResult>());
        assertEquals(null, mWiseFy.mWiseFySearch.findSSIDsMatchingRegex(TEST_SSID));
    }

    @Test
    public void findSSIDsMatchingRegex_failure_nullWifiConfiguration() {
        List<ScanResult> accessPoints = new ArrayList<>();
        accessPoints.add(null);

        when(mMockWiFiManager.getScanResults()).thenReturn(accessPoints);
        assertEquals(null, mWiseFy.mWiseFySearch.findSSIDsMatchingRegex(TEST_SSID));
    }

    @Test
    public void findSSIDsMatchingRegex_failure_nullSSID() {
        List<ScanResult> accessPoints = new ArrayList<>();

        ScanResult accessPoint = mock(ScanResult.class);
        accessPoint.SSID = null;
        accessPoints.add(accessPoint);

        when(mMockWiFiManager.getScanResults()).thenReturn(accessPoints);
        assertEquals(null, mWiseFy.mWiseFySearch.findSSIDsMatchingRegex(TEST_SSID));
    }
//
//    @Test
//    public void findSavedNetworksMatchingRegex_failure_nonMatchingSSID() {
//        List<WifiConfiguration> wifiList = new ArrayList<>();
//        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
//        mWiFiConfiguration.SSID = TEST_SSID2;
//        wifiList.add(mWiFiConfiguration);
//
//        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
//        assertEquals(null, mWiseFy.mWiseFySearch.findSavedNetworksMatchingRegex(TEST_SSID));
//    }
//
//    @Test
//    public void findSavedNetworksMatchingRegex_failure_multipleNonMatchingSSIDs() {
//        List<WifiConfiguration> wifiList = new ArrayList<>();
//
//        WifiConfiguration mWiFiConfiguration1 = new WifiConfiguration();
//        mWiFiConfiguration1.SSID = TEST_SSID2;
//        wifiList.add(mWiFiConfiguration1);
//
//        WifiConfiguration mWiFiConfiguration2 = new WifiConfiguration();
//        mWiFiConfiguration2.SSID = TEST_SSID3;
//        wifiList.add(mWiFiConfiguration2);
//
//        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
//        assertEquals(null, mWiseFy.mWiseFySearch.findSavedNetworksMatchingRegex(TEST_SSID));
//    }
//
//    @Test
//    public void findSavedNetworksMatchingRegex_success() {
//        List<WifiConfiguration> wifiList = new ArrayList<>();
//
//        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
//        mWiFiConfiguration.SSID = TEST_SSID;
//        wifiList.add(mWiFiConfiguration);
//
//        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
//        assertEquals(wifiList, mWiseFy.mWiseFySearch.findSavedNetworksMatchingRegex(TEST_SSID));
//    }
//
//    @Test
//    public void findSavedNetworksMatchingRegex_success_multipleSSIDs() {
//        List<WifiConfiguration> wifiList = new ArrayList<>();
//
//        WifiConfiguration mWiFiConfiguration1 = new WifiConfiguration();
//        mWiFiConfiguration1.SSID = TEST_SSID;
//        wifiList.add(mWiFiConfiguration1);
//
//        WifiConfiguration mWiFiConfiguration2 = new WifiConfiguration();
//        mWiFiConfiguration2.SSID = TEST_SSID2;
//        wifiList.add(mWiFiConfiguration2);
//
//        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
//        assertEquals(wifiList, mWiseFy.mWiseFySearch.findSavedNetworksMatchingRegex(".*Test.*"));
//    }
}
