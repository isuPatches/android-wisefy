//package com.isupatches.wisefy.test;
//
//
//import android.net.wifi.ScanResult;
//import com.isupatches.wisefy.callbacks.SearchForAccessPointCallbacks;
//import com.isupatches.wisefy.constants.WiseFyCodes;
//import com.isupatches.wisefy.test.base.BaseTestClass;
//import com.isupatches.wisefy.test.base.TestActivity;
//import org.junit.Test;
//import java.util.ArrayList;
//import java.util.List;
//import static com.isupatches.wisefy.test.base.TestUtils.TEST_SSID;
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.never;
//import static org.mockito.Mockito.timeout;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//
//public class SearchForAccessPointTests extends BaseTestClass<TestActivity> {
//
//    public SearchForAccessPointTests() {
//        super(TestActivity.class);
//    }
//
//    @Test
//    public void noCallbacks_failure() {
//        setManagers();
//        assertEquals(null, mWiseFy.searchForAccessPoint(TEST_SSID, 1));
//    }
//
//    @Test
//    public void noCallbacks_failure_nullSSID() {
//        setManagers();
//
//        List<ScanResult> scanResults = new ArrayList<>();
//        ScanResult scanResult = mock(ScanResult.class);
//        scanResult.SSID = null;
//        scanResults.add(scanResult);
//
//        when(mMockWiFiManager.getScanResults()).thenReturn(scanResults);
//
//        assertEquals(null, mWiseFy.searchForAccessPoint(TEST_SSID, 1));
//    }
//
//    @Test
//    public void noCallbacks_failure_nullSSIDParam() {
//        setManagers();
//
//        List<ScanResult> scanResults = new ArrayList<>();
//        ScanResult scanResult = mock(ScanResult.class);
//        scanResult.SSID = null;
//        scanResults.add(scanResult);
//
//        when(mMockWiFiManager.getScanResults()).thenReturn(scanResults);
//
//        assertEquals(null, mWiseFy.searchForAccessPoint(null, 1));
//    }
//
//    @Test
//    public void noCallbacks_failure_differentSSID() {
//        setManagers();
//
//        List<ScanResult> scanResults = new ArrayList<>();
//        ScanResult scanResult = mock(ScanResult.class);
//        scanResult.SSID = "Another SSID";
//        scanResults.add(scanResult);
//
//        when(mMockWiFiManager.getScanResults()).thenReturn(scanResults);
//
//        assertEquals(null, mWiseFy.searchForAccessPoint(TEST_SSID, 1));
//    }
//
//    @Test
//    public void noCallbacks_failure_nullWifiManager() {
//        setManagersToNull();
//        assertEquals(null, mWiseFy.searchForAccessPoint(TEST_SSID, 1));
//    }
//
//    @Test
//    public void noCallbacks_success() {
//        setManagers();
//
//        List<ScanResult> scanResults = new ArrayList<>();
//        ScanResult scanResult = mock(ScanResult.class);
//        scanResult.SSID = TEST_SSID;
//        scanResults.add(scanResult);
//
//        when(mMockWiFiManager.getScanResults()).thenReturn(scanResults);
//
//        assertEquals(scanResult, mWiseFy.searchForAccessPoint(TEST_SSID, 1));
//    }
//
//    @Test
//    public void callbacks_failure() {
//        setManagers();
//        SearchForAccessPointCallbacks mockSearchForNetworkCallbacks = mock(SearchForAccessPointCallbacks.class);
//        mWiseFy.searchForAccessPoint(TEST_SSID, 1, mockSearchForNetworkCallbacks);
//        verify(mockSearchForNetworkCallbacks, timeout(2000)).accessPointNotFound();
//    }
//
//    @Test
//    public void callbacks_failure_nullCallback() {
//        setManagers();
//        SearchForAccessPointCallbacks mockSearchForNetworkCallbacks = mock(SearchForAccessPointCallbacks.class);
//        mWiseFy.searchForAccessPoint(TEST_SSID, 1, null);
//        verify(mockSearchForNetworkCallbacks, never()).accessPointNotFound();
//    }
//
//    @Test
//    public void callbacks_failure_nullSSID() {
//        setManagers();
//
//        List<ScanResult> scanResults = new ArrayList<>();
//        ScanResult scanResult = mock(ScanResult.class);
//        scanResult.SSID = null;
//        scanResults.add(scanResult);
//
//        when(mMockWiFiManager.getScanResults()).thenReturn(scanResults);
//
//        SearchForAccessPointCallbacks mockSearchForNetworkCallbacks = mock(SearchForAccessPointCallbacks.class);
//        mWiseFy.searchForAccessPoint(TEST_SSID, 1, mockSearchForNetworkCallbacks);
//        verify(mockSearchForNetworkCallbacks, timeout(2000)).accessPointFound();
//    }
//
//    @Test
//    public void callbacks_failure_nullSSID_nullCallback() {
//        setManagers();
//
//        List<ScanResult> scanResults = new ArrayList<>();
//        ScanResult scanResult = mock(ScanResult.class);
//        scanResult.SSID = null;
//        scanResults.add(scanResult);
//
//        when(mMockWiFiManager.getScanResults()).thenReturn(scanResults);
//
//        SearchForAccessPointCallbacks mockSearchForNetworkCallbacks = mock(SearchForAccessPointCallbacks.class);
//        mWiseFy.searchForAccessPoint(TEST_SSID, 1, null);
//        verify(mockSearchForNetworkCallbacks, never()).accessPointNotFound();
//    }
//
//    @Test
//    public void callbacks_failure_nullSSIDParam() {
//        setManagers();
//
//        List<ScanResult> scanResults = new ArrayList<>();
//        ScanResult scanResult = mock(ScanResult.class);
//        scanResult.SSID = null;
//        scanResults.add(scanResult);
//
//        when(mMockWiFiManager.getScanResults()).thenReturn(scanResults);
//
//        SearchForAccessPointCallbacks mockSearchForNetworkCallbacks = mock(SearchForAccessPointCallbacks.class);
//        mWiseFy.searchForAccessPoint(null, 1, mockSearchForNetworkCallbacks);
//        verify(mockSearchForNetworkCallbacks, timeout(2000)).searchForNetworkWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
//    }
//
//    @Test
//    public void callbacks_failure_nullSSIDParam_nullCallback() {
//        setManagers();
//
//        List<ScanResult> scanResults = new ArrayList<>();
//        ScanResult scanResult = mock(ScanResult.class);
//        scanResult.SSID = null;
//        scanResults.add(scanResult);
//
//        when(mMockWiFiManager.getScanResults()).thenReturn(scanResults);
//
//        SearchForAccessPointCallbacks mockSearchForNetworkCallbacks = mock(SearchForAccessPointCallbacks.class);
//        mWiseFy.searchForAccessPoint(null, 1, null);
//        verify(mockSearchForNetworkCallbacks, never()).searchForNetworkWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
//    }
//
//    @Test
//    public void callbacks_failure_differentSSID() {
//        setManagers();
//
//        List<ScanResult> scanResults = new ArrayList<>();
//        ScanResult scanResult = mock(ScanResult.class);
//        scanResult.SSID = "Another SSID";
//        scanResults.add(scanResult);
//
//        when(mMockWiFiManager.getScanResults()).thenReturn(scanResults);
//
//        SearchForAccessPointCallbacks mockSearchForNetworkCallbacks = mock(SearchForAccessPointCallbacks.class);
//        mWiseFy.searchForAccessPoint(TEST_SSID, 1, mockSearchForNetworkCallbacks);
//        verify(mockSearchForNetworkCallbacks, timeout(2000)).accessPointFound();
//    }
//
//    @Test
//    public void callbacks_failure_differentSSID_nullCallback() {
//        setManagers();
//
//        List<ScanResult> scanResults = new ArrayList<>();
//        ScanResult scanResult = mock(ScanResult.class);
//        scanResult.SSID = "Another SSID";
//        scanResults.add(scanResult);
//
//        when(mMockWiFiManager.getScanResults()).thenReturn(scanResults);
//
//        SearchForAccessPointCallbacks mockSearchForNetworkCallbacks = mock(SearchForAccessPointCallbacks.class);
//        mWiseFy.searchForAccessPoint(TEST_SSID, 1, null);
//        verify(mockSearchForNetworkCallbacks, never()).accessPointNotFound();
//    }
//
//    @Test
//    public void callbacks_failure_nullWifiManager() {
//        setManagersToNull();
//        SearchForAccessPointCallbacks mockSearchForNetworkCallbacks = mock(SearchForAccessPointCallbacks.class);
//        mWiseFy.searchForAccessPoint(TEST_SSID, 1, mockSearchForNetworkCallbacks);
//        verify(mockSearchForNetworkCallbacks, timeout(2000)).searchForNetworkWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
//    }
//
//    @Test
//    public void callbacks_failure_nullWifiManager_nullCallback() {
//        setManagersToNull();
//        SearchForAccessPointCallbacks mockSearchForNetworkCallbacks = mock(SearchForAccessPointCallbacks.class);
//        mWiseFy.searchForAccessPoint(TEST_SSID, 1, null);
//        verify(mockSearchForNetworkCallbacks, never()).searchForNetworkWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
//    }
//
//    @Test
//    public void callbacks_success() {
//        setManagers();
//
//        List<ScanResult> scanResults = new ArrayList<>();
//        ScanResult scanResult = mock(ScanResult.class);
//        scanResult.SSID = TEST_SSID;
//        scanResults.add(scanResult);
//
//        when(mMockWiFiManager.getScanResults()).thenReturn(scanResults);
//
//        SearchForAccessPointCallbacks mockSearchForNetworkCallbacks = mock(SearchForAccessPointCallbacks.class);
//        mWiseFy.searchForAccessPoint(TEST_SSID, 1, mockSearchForNetworkCallbacks);
//        verify(mockSearchForNetworkCallbacks, timeout(2000)).accessPointFound(scanResult);
//    }
//
//    @Test
//    public void callbacks_success_nullCallback() {
//        setManagers();
//        List<ScanResult> scanResults = new ArrayList<>();
//        ScanResult scanResult = mock(ScanResult.class);
//        scanResult.SSID = TEST_SSID;
//        scanResults.add(scanResult);
//
//        when(mMockWiFiManager.getScanResults()).thenReturn(scanResults);
//
//        SearchForAccessPointCallbacks mockSearchForNetworkCallbacks = mock(SearchForAccessPointCallbacks.class);
//        mWiseFy.searchForAccessPoint(TEST_SSID, 1, null);
//        verify(mockSearchForNetworkCallbacks, never()).accessPointFound(scanResult);
//    }
//}
