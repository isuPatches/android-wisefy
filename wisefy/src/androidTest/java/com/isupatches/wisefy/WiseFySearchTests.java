//package com.isupatches.wisefy;
//
//
//import android.net.wifi.ScanResult;
//import android.net.wifi.WifiConfiguration;
//import com.isupatches.wisefy.base.TestActivity;
//import org.junit.Test;
//import java.util.ArrayList;
//import java.util.List;
//import static com.isupatches.wisefy.base.TestUtils.TEST_SSID;
//import static com.isupatches.wisefy.base.TestUtils.TEST_SSID2;
//import static com.isupatches.wisefy.base.TestUtils.TEST_SSID3;
//import static junit.framework.Assert.assertEquals;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//
//public class WiseFySearchTests extends BaseTestClass<TestActivity> {
//
//    public WiseFySearchTests() {
//        super(TestActivity.class);
//    }
//
//    /*
//     * findAccessPointByRegex tests
//     */
//
//    @Test
//    public void findAccessPointByRegex_failure_nullAccessPoints_takeHighest_false() {
//        when(mMockWiFiManager.getScanResults()).thenReturn(null);
//        assertEquals(null, mWiseFy.mWiseFySearch.findAccessPointByRegex(TEST_SSID, 1000, false));
//    }
//
//    @Test
//    public void findAccessPointByRegex_failure_nullAccessPoints_takeHighest_true() {
//        when(mMockWiFiManager.getScanResults()).thenReturn(null);
//        assertEquals(null, mWiseFy.mWiseFySearch.findAccessPointByRegex(TEST_SSID, 1000, true));
//    }
//
//    @Test
//    public void findAccessPointByRegex_failure_emptyAccessPointList_takeHighest_false() {
//        when(mMockWiFiManager.getScanResults()).thenReturn(new ArrayList<ScanResult>());
//        assertEquals(null, mWiseFy.mWiseFySearch.findAccessPointByRegex(TEST_SSID, 1000, false));
//    }
//
//    @Test
//    public void findAccessPointByRegex_failure_emptyAccessPointList_takeHighest_true() {
//        when(mMockWiFiManager.getScanResults()).thenReturn(new ArrayList<ScanResult>());
//        assertEquals(null, mWiseFy.mWiseFySearch.findAccessPointByRegex(TEST_SSID, 1000, true));
//    }
//
//    @Test
//    public void findAccessPointByRegex_failure_nullAccessPoint_takeHighest_false() {
//        List<ScanResult> accessPoints = new ArrayList<>();
//        accessPoints.add(null);
//
//        when(mMockWiFiManager.getScanResults()).thenReturn(accessPoints);
//        assertEquals(null, mWiseFy.mWiseFySearch.findAccessPointByRegex(TEST_SSID, 1000, false));
//    }
//
//    @Test
//    public void findAccessPointByRegex_failure_nullAccessPoint_takeHighest_true() {
//        List<ScanResult> accessPoints = new ArrayList<>();
//        accessPoints.add(null);
//
//        when(mMockWiFiManager.getScanResults()).thenReturn(accessPoints);
//        assertEquals(null, mWiseFy.mWiseFySearch.findAccessPointByRegex(TEST_SSID, 1000, true));
//    }
//
//    @Test
//    public void findAccessPointByRegex_failure_nullSSID_takeHighest_false() {
//        List<ScanResult> accessPoints = new ArrayList<>();
//
//        ScanResult accessPoint= mock(ScanResult.class);
//        accessPoint.SSID = null;
//        accessPoints.add(accessPoint);
//
//        when(mMockWiFiManager.getScanResults()).thenReturn(accessPoints);
//        assertEquals(null, mWiseFy.mWiseFySearch.findAccessPointByRegex(TEST_SSID, 1000, false));
//    }
//
//    @Test
//    public void findAccessPointByRegex_failure_nullSSID_takeHighest_true() {
//        List<ScanResult> accessPoints = new ArrayList<>();
//
//        ScanResult accessPoint= mock(ScanResult.class);
//        accessPoint.SSID = null;
//        accessPoints.add(accessPoint);
//
//        when(mMockWiFiManager.getScanResults()).thenReturn(accessPoints);
//        assertEquals(null, mWiseFy.mWiseFySearch.findAccessPointByRegex(TEST_SSID, 1000, true));
//    }
//
//    @Test
//    public void findAccessPointByRegex_failure_nonMatchingSSID_takeHighest_false() {
//        List<ScanResult> accessPoints = new ArrayList<>();
//
//        ScanResult accessPoint= mock(ScanResult.class);
//        accessPoint.SSID = TEST_SSID2;
//        accessPoints.add(accessPoint);
//
//        when(mMockWiFiManager.getScanResults()).thenReturn(accessPoints);
//        assertEquals(null, mWiseFy.mWiseFySearch.findAccessPointByRegex(TEST_SSID, 1000, false));
//    }
//
//    @Test
//    public void findAccessPointByRegex_failure_nonMatchingSSID_takeHighest_true() {
//        List<ScanResult> accessPoints = new ArrayList<>();
//
//        ScanResult accessPoint= mock(ScanResult.class);
//        accessPoint.SSID = TEST_SSID2;
//        accessPoints.add(accessPoint);
//
//        when(mMockWiFiManager.getScanResults()).thenReturn(accessPoints);
//        assertEquals(null, mWiseFy.mWiseFySearch.findAccessPointByRegex(TEST_SSID, 1000, true));
//    }
//
//    @Test
//    public void findAccessPointByRegex_failure_multipleNonMatchingSSIDs_takeHighest_false() {
//        List<ScanResult> accessPoints = new ArrayList<>();
//
//        ScanResult accessPoint1 = mock(ScanResult.class);
//        accessPoint1.SSID = TEST_SSID2;
//        accessPoints.add(accessPoint1);
//
//        ScanResult accessPoint2 = mock(ScanResult.class);
//        accessPoint2.SSID = TEST_SSID3;
//        accessPoints.add(accessPoint2);
//
//        when(mMockWiFiManager.getScanResults()).thenReturn(accessPoints);
//        assertEquals(null, mWiseFy.mWiseFySearch.findAccessPointByRegex(TEST_SSID, 1000, false));
//    }
//
//    @Test
//    public void findAccessPointByRegex_failure_multipleNnonMatchingSSIDs_takeHighest_true() {
//        List<ScanResult> accessPoints = new ArrayList<>();
//
//        ScanResult accessPoint1= mock(ScanResult.class);
//        accessPoint1.SSID = TEST_SSID2;
//        accessPoints.add(accessPoint1);
//
//        ScanResult accessPoint2 = mock(ScanResult.class);
//        accessPoint2.SSID = TEST_SSID3;
//        accessPoints.add(accessPoint2);
//
//        when(mMockWiFiManager.getScanResults()).thenReturn(accessPoints);
//        assertEquals(null, mWiseFy.mWiseFySearch.findAccessPointByRegex(TEST_SSID, 1000, true));
//    }
//
//    @Test
//    public void findAccessPointByRegex_success_takeHighest_false() {
//        List<ScanResult> accessPoints = new ArrayList<>();
//
//        ScanResult accessPoint = mock(ScanResult.class);
//        accessPoint.SSID = TEST_SSID;
//        accessPoints.add(accessPoint);
//
//        when(mMockWiFiManager.getScanResults()).thenReturn(accessPoints);
//        assertEquals(accessPoint, mWiseFy.mWiseFySearch.findAccessPointByRegex(TEST_SSID, 1000, false));
//    }
//
//    @Test
//    public void findAccessPointByRegex_success_takeHighest_true() {
//        List<ScanResult> accessPoints = new ArrayList<>();
//
//        ScanResult accessPoint = mock(ScanResult.class);
//        accessPoint.SSID = TEST_SSID;
//        accessPoints.add(accessPoint);
//
//        when(mMockWiFiManager.getScanResults()).thenReturn(accessPoints);
//        assertEquals(accessPoint, mWiseFy.mWiseFySearch.findAccessPointByRegex(TEST_SSID, 1000, true));
//    }
//
//    @Test
//    public void findAccessPointByRegex_success_multipleSSIDs_takeHighest_false() {
//        List<ScanResult> accessPoints = new ArrayList<>();
//
//        ScanResult accessPoint1 = mock(ScanResult.class);
//        accessPoint1.SSID = TEST_SSID;
//        accessPoint1.level = -70;
//        accessPoints.add(accessPoint1);
//
//        ScanResult accessPoint2 = mock(ScanResult.class);
//        accessPoint2.SSID = TEST_SSID2;
//        accessPoint2.level = -50;
//        accessPoints.add(accessPoint2);
//
//        when(mMockWiFiManager.getScanResults()).thenReturn(accessPoints);
//        assertEquals(accessPoint1, mWiseFy.mWiseFySearch.findAccessPointByRegex(TEST_SSID, 1000, false));
//    }
//
//    @Test
//    public void findAccessPointByRegex_success_multipleSSIDs_takeHighest_true() {
//        List<ScanResult> accessPoints = new ArrayList<>();
//
//        ScanResult accessPoint1 = mock(ScanResult.class);
//        accessPoint1.SSID = TEST_SSID;
//        accessPoint1.level = -70;
//        accessPoints.add(accessPoint1);
//
//        ScanResult accessPoint2 = mock(ScanResult.class);
//        accessPoint2.SSID = TEST_SSID2;
//        accessPoint2.level = -50;
//        accessPoints.add(accessPoint2);
//
//        when(mMockWiFiManager.getScanResults()).thenReturn(accessPoints);
//        assertEquals(accessPoint1, mWiseFy.mWiseFySearch.findAccessPointByRegex(TEST_SSID, 1000, true));
//    }
//
//    @Test
//    public void findAccessPointByRegex_success_multipleSameSSIDs_takeHighest_false() {
//        List<ScanResult> accessPoints = new ArrayList<>();
//
//        ScanResult accessPoint1 = mock(ScanResult.class);
//        accessPoint1.SSID = TEST_SSID;
//        accessPoint1.level = -70;
//        accessPoints.add(accessPoint1);
//
//        ScanResult accessPoint2 = mock(ScanResult.class);
//        accessPoint2.SSID = TEST_SSID;
//        accessPoint2.level = -50;
//        accessPoints.add(accessPoint2);
//
//        when(mMockWiFiManager.getScanResults()).thenReturn(accessPoints);
//        assertEquals(accessPoint1, mWiseFy.mWiseFySearch.findAccessPointByRegex(TEST_SSID, 1000, false));
//    }
//
//    @Test
//    public void findAccessPointByRegex_success_multipleSameSSIDs_takeHighest_true() {
//        List<ScanResult> accessPoints = new ArrayList<>();
//
//        ScanResult accessPoint1 = mock(ScanResult.class);
//        accessPoint1.SSID = TEST_SSID;
//        accessPoint1.level = -70;
//        accessPoints.add(accessPoint1);
//
//        ScanResult accessPoint2 = mock(ScanResult.class);
//        accessPoint2.SSID = TEST_SSID;
//        accessPoint2.level = -50;
//        accessPoints.add(accessPoint2);
//
//        when(mMockWiFiManager.getScanResults()).thenReturn(accessPoints);
//        assertEquals(accessPoint2, mWiseFy.mWiseFySearch.findAccessPointByRegex(TEST_SSID, 1000, true));
//    }
//
//    /*
//     * findAccessPointsMatchingRegex tests
//     */
//
//    @Test
//    public void findAccessPointsMatchingRegex_failure_nullAccessPoints_takeHighest_false() {
//        when(mMockWiFiManager.getScanResults()).thenReturn(null);
//        assertEquals(null, mWiseFy.mWiseFySearch.findAccessPointsMatchingRegex(TEST_SSID, false));
//    }
//
//    @Test
//    public void findAccessPointsMatchingRegex_failure_nullAccessPoints_takeHighest_true() {
//        when(mMockWiFiManager.getScanResults()).thenReturn(null);
//        assertEquals(null, mWiseFy.mWiseFySearch.findAccessPointsMatchingRegex(TEST_SSID, true));
//    }
//
//    @Test
//    public void findAccessPointsMatchingRegex_failure_emptyAccessPointList_takeHighest_false() {
//        when(mMockWiFiManager.getScanResults()).thenReturn(new ArrayList<ScanResult>());
//        assertEquals(null, mWiseFy.mWiseFySearch.findAccessPointsMatchingRegex(TEST_SSID, false));
//    }
//
//    @Test
//    public void findAccessPointsMatchingRegex_failure_emptyAccessPointList_takeHighest_true() {
//        when(mMockWiFiManager.getScanResults()).thenReturn(new ArrayList<ScanResult>());
//        assertEquals(null, mWiseFy.mWiseFySearch.findAccessPointsMatchingRegex(TEST_SSID, true));
//    }
//
//    @Test
//    public void findAccessPointsMatchingRegex_failure_nullAccessPoint_takeHighest_false() {
//        List<ScanResult> accessPoints = new ArrayList<>();
//        accessPoints.add(null);
//
//        when(mMockWiFiManager.getScanResults()).thenReturn(accessPoints);
//        assertEquals(null, mWiseFy.mWiseFySearch.findAccessPointsMatchingRegex(TEST_SSID, false));
//    }
//
//    @Test
//    public void findAccessPointsMatchingRegex_failure_nullAccessPoint_takeHighest_true() {
//        List<ScanResult> accessPoints = new ArrayList<>();
//        accessPoints.add(null);
//
//        when(mMockWiFiManager.getScanResults()).thenReturn(accessPoints);
//        assertEquals(null, mWiseFy.mWiseFySearch.findAccessPointsMatchingRegex(TEST_SSID, true));
//    }
//
//    @Test
//    public void findAccessPointsMatchingRegex_failure_nullSSID_takeHighest_false() {
//        List<ScanResult> accessPoints = new ArrayList<>();
//
//        ScanResult accessPoint= mock(ScanResult.class);
//        accessPoint.SSID = null;
//        accessPoints.add(accessPoint);
//
//        when(mMockWiFiManager.getScanResults()).thenReturn(accessPoints);
//        assertEquals(null, mWiseFy.mWiseFySearch.findAccessPointsMatchingRegex(TEST_SSID, false));
//    }
//
//    @Test
//    public void findAccessPointsMatchingRegex_failure_nullSSID_takeHighest_true() {
//        List<ScanResult> accessPoints = new ArrayList<>();
//
//        ScanResult accessPoint= mock(ScanResult.class);
//        accessPoint.SSID = null;
//        accessPoints.add(accessPoint);
//
//        when(mMockWiFiManager.getScanResults()).thenReturn(accessPoints);
//        assertEquals(null, mWiseFy.mWiseFySearch.findAccessPointsMatchingRegex(TEST_SSID, true));
//    }
//
//    @Test
//    public void findAccessPointsMatchingRegex_failure_nonMatchingSSID_takeHighest_false() {
//        List<ScanResult> accessPoints = new ArrayList<>();
//
//        ScanResult accessPoint= mock(ScanResult.class);
//        accessPoint.SSID = TEST_SSID2;
//        accessPoints.add(accessPoint);
//
//        when(mMockWiFiManager.getScanResults()).thenReturn(accessPoints);
//        assertEquals(null, mWiseFy.mWiseFySearch.findAccessPointsMatchingRegex(TEST_SSID, false));
//    }
//
//    @Test
//    public void findAccessPointsMatchingRegex_failure_nonMatchingSSID_takeHighest_true() {
//        List<ScanResult> accessPoints = new ArrayList<>();
//
//        ScanResult accessPoint= mock(ScanResult.class);
//        accessPoint.SSID = TEST_SSID2;
//        accessPoints.add(accessPoint);
//
//        when(mMockWiFiManager.getScanResults()).thenReturn(accessPoints);
//        assertEquals(null, mWiseFy.mWiseFySearch.findAccessPointsMatchingRegex(TEST_SSID, true));
//    }
//
//    @Test
//    public void findAccessPointsMatchingRegex_failure_multipleNonMatchingSSIDs_takeHighest_false() {
//        List<ScanResult> accessPoints = new ArrayList<>();
//
//        ScanResult accessPoint1 = mock(ScanResult.class);
//        accessPoint1.SSID = TEST_SSID2;
//        accessPoints.add(accessPoint1);
//
//        ScanResult accessPoint2 = mock(ScanResult.class);
//        accessPoint2.SSID = TEST_SSID3;
//        accessPoints.add(accessPoint2);
//
//        when(mMockWiFiManager.getScanResults()).thenReturn(accessPoints);
//        assertEquals(null, mWiseFy.mWiseFySearch.findAccessPointsMatchingRegex(TEST_SSID, false));
//    }
//
//    @Test
//    public void findAccessPointsMatchingRegex_failure_multipleNnonMatchingSSIDs_takeHighest_true() {
//        List<ScanResult> accessPoints = new ArrayList<>();
//
//        ScanResult accessPoint1= mock(ScanResult.class);
//        accessPoint1.SSID = TEST_SSID2;
//        accessPoints.add(accessPoint1);
//
//        ScanResult accessPoint2 = mock(ScanResult.class);
//        accessPoint2.SSID = TEST_SSID3;
//        accessPoints.add(accessPoint2);
//
//        when(mMockWiFiManager.getScanResults()).thenReturn(accessPoints);
//        assertEquals(null, mWiseFy.mWiseFySearch.findAccessPointsMatchingRegex(TEST_SSID, true));
//    }
//
//    @Test
//    public void findAccessPointsMatchingRegex_success_takeHighest_false() {
//        List<ScanResult> accessPoints = new ArrayList<>();
//
//        ScanResult accessPoint = mock(ScanResult.class);
//        accessPoint.SSID = TEST_SSID;
//        accessPoints.add(accessPoint);
//
//        when(mMockWiFiManager.getScanResults()).thenReturn(accessPoints);
//        assertEquals(accessPoints, mWiseFy.mWiseFySearch.findAccessPointsMatchingRegex(TEST_SSID, false));
//    }
//
//    @Test
//    public void findAccessPointsMatchingRegex_success_takeHighest_true() {
//        List<ScanResult> accessPoints = new ArrayList<>();
//
//        ScanResult accessPoint = mock(ScanResult.class);
//        accessPoint.SSID = TEST_SSID;
//        accessPoints.add(accessPoint);
//
//        when(mMockWiFiManager.getScanResults()).thenReturn(accessPoints);
//        assertEquals(accessPoints, mWiseFy.mWiseFySearch.findAccessPointsMatchingRegex(TEST_SSID, true));
//    }
//
//    @Test
//    public void findAccessPointsMatchingRegex_success_multipleSSIDs_takeHighest_false() {
//        List<ScanResult> accessPoints = new ArrayList<>();
//        List<ScanResult> expectedResults = new ArrayList<>();
//
//        ScanResult accessPoint1 = mock(ScanResult.class);
//        accessPoint1.SSID = TEST_SSID;
//        accessPoint1.level = -70;
//        accessPoints.add(accessPoint1);
//        expectedResults.add(accessPoint1);
//
//        ScanResult accessPoint2 = mock(ScanResult.class);
//        accessPoint2.SSID = TEST_SSID2;
//        accessPoint2.level = -50;
//        accessPoints.add(accessPoint2);
//
//        when(mMockWiFiManager.getScanResults()).thenReturn(accessPoints);
//        assertEquals(expectedResults, mWiseFy.mWiseFySearch.findAccessPointsMatchingRegex(TEST_SSID, false));
//    }
//
//    @Test
//    public void findAccessPointsMatchingRegex_success_multipleSSIDs_takeHighest_true() {
//        List<ScanResult> accessPoints = new ArrayList<>();
//        List<ScanResult> expectedResults = new ArrayList<>();
//
//        ScanResult accessPoint1 = mock(ScanResult.class);
//        accessPoint1.SSID = TEST_SSID;
//        accessPoint1.level = -70;
//        accessPoints.add(accessPoint1);
//        expectedResults.add(accessPoint1);
//
//        ScanResult accessPoint2 = mock(ScanResult.class);
//        accessPoint2.SSID = TEST_SSID2;
//        accessPoint2.level = -50;
//        accessPoints.add(accessPoint2);
//
//        when(mMockWiFiManager.getScanResults()).thenReturn(accessPoints);
//        assertEquals(expectedResults, mWiseFy.mWiseFySearch.findAccessPointsMatchingRegex(TEST_SSID, true));
//    }
//
//    @Test
//    public void findAccessPointsMatchingRegex_success_multipleSameSSIDs_takeHighest_false() {
//        List<ScanResult> accessPoints = new ArrayList<>();
//        List<ScanResult> expectedResults = new ArrayList<>();
//
//        ScanResult accessPoint1 = mock(ScanResult.class);
//        accessPoint1.SSID = TEST_SSID;
//        accessPoint1.level = -70;
//        accessPoints.add(accessPoint1);
//        expectedResults.add(accessPoint1);
//
//        ScanResult accessPoint2 = mock(ScanResult.class);
//        accessPoint2.SSID = TEST_SSID;
//        accessPoint2.level = -50;
//        accessPoints.add(accessPoint2);
//        expectedResults.add(accessPoint2);
//
//        when(mMockWiFiManager.getScanResults()).thenReturn(accessPoints);
//        assertEquals(expectedResults, mWiseFy.mWiseFySearch.findAccessPointsMatchingRegex(TEST_SSID, false));
//    }
//
//    @Test
//    public void findAccessPointsMatchingRegex_success_multipleSameSSIDs_takeHighest_true() {
//        List<ScanResult> accessPoints = new ArrayList<>();
//        List<ScanResult> expectedResults = new ArrayList<>();
//
//        ScanResult accessPoint1 = mock(ScanResult.class);
//        accessPoint1.SSID = TEST_SSID;
//        accessPoint1.level = -70;
//        accessPoints.add(accessPoint1);
//
//        ScanResult accessPoint2 = mock(ScanResult.class);
//        accessPoint2.SSID = TEST_SSID;
//        accessPoint2.level = -50;
//        accessPoints.add(accessPoint2);
//        expectedResults.add(accessPoint2);
//
//        when(mMockWiFiManager.getScanResults()).thenReturn(accessPoints);
//        assertEquals(expectedResults, mWiseFy.mWiseFySearch.findAccessPointsMatchingRegex(TEST_SSID, true));
//    }
//
//    /*
//     * findSavedNetworkByRegex tests
//     */
//
//    @Test
//    public void findSavedNetworkByRegex_failure_nullSavedNetworkList() {
//        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(null);
//        assertEquals(null, mWiseFy.mWiseFySearch.findSavedNetworkByRegex(TEST_SSID));
//    }
//
//    @Test
//    public void findSavedNetworkByRegex_failure_emptySavedNetworkList() {
//        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(new ArrayList<WifiConfiguration>());
//        assertEquals(null, mWiseFy.mWiseFySearch.findSavedNetworkByRegex(TEST_SSID));
//    }
//
//    @Test
//    public void findSavedNetworkByRegex_failure_nullWifiConfiguration() {
//        List<WifiConfiguration> wifiList = new ArrayList<>();
//        wifiList.add(null);
//
//        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
//        assertEquals(null, mWiseFy.mWiseFySearch.findSavedNetworkByRegex(TEST_SSID));
//    }
//
//    @Test
//    public void findSavedNetworkByRegex_failure_nullSSID() {
//        List<WifiConfiguration> wifiList = new ArrayList<>();
//        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
//        mWiFiConfiguration.SSID = null;
//        wifiList.add(mWiFiConfiguration);
//
//        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
//        assertEquals(null, mWiseFy.mWiseFySearch.findSavedNetworkByRegex(TEST_SSID));
//    }
//
//    @Test
//    public void findSavedNetworkByRegex_failure_nonMatchingSSID() {
//        List<WifiConfiguration> wifiList = new ArrayList<>();
//
//        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
//        mWiFiConfiguration.SSID = TEST_SSID2;
//        wifiList.add(mWiFiConfiguration);
//
//        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
//        assertEquals(null, mWiseFy.mWiseFySearch.findSavedNetworkByRegex(TEST_SSID));
//    }
//
//    @Test
//    public void findSavedNetworkByRegex_failure_multipleNonMatchingSSIDs() {
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
//        assertEquals(null, mWiseFy.mWiseFySearch.findSavedNetworkByRegex(TEST_SSID));
//    }
//
//    @Test
//    public void findSavedNetworkByRegex_success() {
//        List<WifiConfiguration> wifiList = new ArrayList<>();
//
//        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
//        mWiFiConfiguration.SSID = TEST_SSID;
//        wifiList.add(mWiFiConfiguration);
//
//        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
//        assertEquals(mWiFiConfiguration, mWiseFy.mWiseFySearch.findSavedNetworkByRegex(TEST_SSID));
//    }
//
//    @Test
//    public void findSavedNetworkByRegex_success_multipleSSIDs() {
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
//        assertEquals(mWiFiConfiguration1, mWiseFy.mWiseFySearch.findSavedNetworkByRegex(TEST_SSID));
//    }
//
//    /*
//     * findSavedNetworksMatchingRegex tests
//     */
//
//    @Test
//    public void findSavedNetworksMatchingRegex_failure_nullSavedNetworkList() {
//        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(null);
//        assertEquals(null, mWiseFy.mWiseFySearch.findSavedNetworksMatchingRegex(TEST_SSID));
//    }
//
//    @Test
//    public void findSavedNetworksMatchingRegex_failure_emptySavedNetworkList() {
//        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(new ArrayList<WifiConfiguration>());
//        assertEquals(null, mWiseFy.mWiseFySearch.findSavedNetworksMatchingRegex(TEST_SSID));
//    }
//
//    @Test
//    public void findSavedNetworksMatchingRegex_failure_nullWifiConfiguration() {
//        List<WifiConfiguration> wifiList = new ArrayList<>();
//        wifiList.add(null);
//
//        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
//        assertEquals(null, mWiseFy.mWiseFySearch.findSavedNetworksMatchingRegex(TEST_SSID));
//    }
//
//    @Test
//    public void findSavedNetworksMatchingRegex_failure_nullSSID() {
//        List<WifiConfiguration> wifiList = new ArrayList<>();
//        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
//        mWiFiConfiguration.SSID = null;
//        wifiList.add(mWiFiConfiguration);
//
//        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
//        assertEquals(null, mWiseFy.mWiseFySearch.findSavedNetworksMatchingRegex(TEST_SSID));
//    }
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
//
//    /*
//     * findSSIDsMatchingRegex tests
//     */
//
//    @Test
//    public void findSSIDsMatchingRegex_failure_nullAccessPoints() {
//        when(mMockWiFiManager.getScanResults()).thenReturn(null);
//        assertEquals(null, mWiseFy.mWiseFySearch.findSSIDsMatchingRegex(TEST_SSID));
//    }
//
//    @Test
//    public void findSSIDsMatchingRegex_failure_emptySavedNetworkList() {
//        when(mMockWiFiManager.getScanResults()).thenReturn(new ArrayList<ScanResult>());
//        assertEquals(null, mWiseFy.mWiseFySearch.findSSIDsMatchingRegex(TEST_SSID));
//    }
//
//    @Test
//    public void findSSIDsMatchingRegex_failure_nullWifiConfiguration() {
//        List<ScanResult> accessPoints = new ArrayList<>();
//        accessPoints.add(null);
//
//        when(mMockWiFiManager.getScanResults()).thenReturn(accessPoints);
//        assertEquals(null, mWiseFy.mWiseFySearch.findSSIDsMatchingRegex(TEST_SSID));
//    }
//
//    @Test
//    public void findSSIDsMatchingRegex_failure_nullSSID() {
//        List<ScanResult> accessPoints = new ArrayList<>();
//
//        ScanResult accessPoint = mock(ScanResult.class);
//        accessPoint.SSID = null;
//        accessPoints.add(accessPoint);
//
//        when(mMockWiFiManager.getScanResults()).thenReturn(accessPoints);
//        assertEquals(null, mWiseFy.mWiseFySearch.findSSIDsMatchingRegex(TEST_SSID));
//    }
//
//    @Test
//    public void findSSIDsMatchingRegex_failure_nonMatchingSSID() {
//        List<ScanResult> accessPoints = new ArrayList<>();
//
//        ScanResult accessPoint = mock(ScanResult.class);
//        accessPoint.SSID = TEST_SSID2;
//        accessPoints.add(accessPoint);
//
//        when(mMockWiFiManager.getScanResults()).thenReturn(accessPoints);
//        assertEquals(null, mWiseFy.mWiseFySearch.findSSIDsMatchingRegex(TEST_SSID));
//    }
//
//    @Test
//    public void findSSIDsMatchingRegex_failure_multipleNonMatchingSSIDs() {
//        List<ScanResult> accessPoints = new ArrayList<>();
//
//        ScanResult accessPoint1 = mock(ScanResult.class);
//        accessPoint1.SSID = TEST_SSID2;
//        accessPoints.add(accessPoint1);
//
//        ScanResult accessPoint2 = mock(ScanResult.class);
//        accessPoint2.SSID = TEST_SSID3;
//        accessPoints.add(accessPoint2);
//
//        when(mMockWiFiManager.getScanResults()).thenReturn(accessPoints);
//        assertEquals(null, mWiseFy.mWiseFySearch.findSSIDsMatchingRegex(TEST_SSID));
//    }
//
//    @Test
//    public void findSSIDsMatchingRegex_success() {
//        List<ScanResult> accessPoints = new ArrayList<>();
//        List<String> expectedResults = new ArrayList<>();
//
//        ScanResult accessPoint = mock(ScanResult.class);
//        accessPoint.SSID = TEST_SSID;
//        accessPoints.add(accessPoint);
//        expectedResults.add(TEST_SSID);
//
//        when(mMockWiFiManager.getScanResults()).thenReturn(accessPoints);
//        assertEquals(expectedResults, mWiseFy.mWiseFySearch.findSSIDsMatchingRegex(TEST_SSID));
//    }
//
//    @Test
//    public void findSSIDsMatchingRegex_success_multipleSSIDs() {
//        List<ScanResult> accessPoints = new ArrayList<>();
//        List<String> expectedResults = new ArrayList<>();
//
//        ScanResult accessPoint1 = mock(ScanResult.class);
//        accessPoint1.SSID = TEST_SSID;
//        accessPoints.add(accessPoint1);
//        expectedResults.add(TEST_SSID);
//
//        ScanResult accessPoint2 = mock(ScanResult.class);
//        accessPoint2.SSID = TEST_SSID;
//        accessPoints.add(accessPoint2);
//        expectedResults.add(TEST_SSID);
//
//        when(mMockWiFiManager.getScanResults()).thenReturn(accessPoints);
//        assertEquals(expectedResults, mWiseFy.mWiseFySearch.findSSIDsMatchingRegex(".*Test.*"));
//    }
//
//    /*
//     *  isNetworkASavedConfiguration tests
//     */
//
//    @Test
//    public void isNetworkASavedConfiguration_failure() {
//        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(new ArrayList<WifiConfiguration>());
//        assertEquals(false, mWiseFy.mWiseFySearch.isNetworkASavedConfiguration(TEST_SSID));
//    }
//
//    @Test
//    public void isNetworkASavedConfiguration_success() {
//        List<WifiConfiguration> wifiList = new ArrayList<>();
//
//        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
//        mWiFiConfiguration.SSID = TEST_SSID;
//        wifiList.add(mWiFiConfiguration);
//
//        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
//        assertEquals(true, mWiseFy.mWiseFySearch.isNetworkASavedConfiguration(TEST_SSID));
//    }
//
//    /*
//     *  removeEntriesWithLowerSignalStrength tests
//     */
//
//    @Test
//    public void removeEntriesWithLowerSignalStrength_differentSSIDs() {
//        List<ScanResult> accessPoints = new ArrayList<>();
//        List<ScanResult> expectedResults = new ArrayList<>();
//
//        ScanResult accessPoint1 = mock(ScanResult.class);
//        accessPoint1.SSID = TEST_SSID;
//        accessPoint1.level = -70;
//        accessPoints.add(accessPoint1);
//        expectedResults.add(accessPoint1);
//
//        ScanResult accessPoint2 = mock(ScanResult.class);
//        accessPoint2.SSID = TEST_SSID2;
//        accessPoint2.level = -50;
//        accessPoints.add(accessPoint2);
//        expectedResults.add(accessPoint2);
//
//        assertEquals(expectedResults, mWiseFy.mWiseFySearch.removeEntriesWithLowerSignalStrength(accessPoints));
//    }
//
//    @Test
//    public void removeEntriesWithLowerSignalStrength_sameSignalLevels() {
//        List<ScanResult> accessPoints = new ArrayList<>();
//        List<ScanResult> expectedResults = new ArrayList<>();
//
//        ScanResult accessPoint1 = mock(ScanResult.class);
//        accessPoint1.SSID = TEST_SSID;
//        accessPoint1.level = -70;
//        accessPoints.add(accessPoint1);
//        expectedResults.add(accessPoint1);
//
//        ScanResult accessPoint2 = mock(ScanResult.class);
//        accessPoint2.SSID = TEST_SSID;
//        accessPoint2.level = -70;
//        accessPoints.add(accessPoint2);
//
//        assertEquals(expectedResults, mWiseFy.mWiseFySearch.removeEntriesWithLowerSignalStrength(accessPoints));
//    }
//
//    @Test
//    public void removeEntriesWithLowerSignalStrength_accessPoint1Higher() {
//        List<ScanResult> accessPoints = new ArrayList<>();
//        List<ScanResult> expectedResults = new ArrayList<>();
//
//        ScanResult accessPoint1 = mock(ScanResult.class);
//        accessPoint1.SSID = TEST_SSID;
//        accessPoint1.level = -50;
//        accessPoints.add(accessPoint1);
//        expectedResults.add(accessPoint1);
//
//        ScanResult accessPoint2 = mock(ScanResult.class);
//        accessPoint2.SSID = TEST_SSID;
//        accessPoint2.level = -70;
//        accessPoints.add(accessPoint2);
//
//        assertEquals(expectedResults , mWiseFy.mWiseFySearch.removeEntriesWithLowerSignalStrength(accessPoints));
//    }
//
//    @Test
//    public void removeEntriesWithLowerSignalStrength_accessPoint2Higher() {
//        List<ScanResult> accessPoints = new ArrayList<>();
//        List<ScanResult> expectedResults = new ArrayList<>();
//
//        ScanResult accessPoint1 = mock(ScanResult.class);
//        accessPoint1.SSID = TEST_SSID;
//        accessPoints.add(accessPoint1);
//        accessPoint1.level = -70;
//
//        ScanResult accessPoint2 = mock(ScanResult.class);
//        accessPoint2.SSID = TEST_SSID;
//        accessPoint2.level = -50;
//        accessPoints.add(accessPoint2);
//        expectedResults.add(accessPoint2);
//
//        assertEquals(expectedResults , mWiseFy.mWiseFySearch.removeEntriesWithLowerSignalStrength(accessPoints));
//    }
//}
