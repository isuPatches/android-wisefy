package com.isupatches.wisefy.test;


import android.net.wifi.ScanResult;
import com.isupatches.wisefy.callbacks.SearchForSSIDCallbacks;
import com.isupatches.wisefy.constants.WiseFyCodes;
import com.isupatches.wisefy.test.base.BaseTestClass;
import com.isupatches.wisefy.test.base.TestActivity;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static com.isupatches.wisefy.test.base.TestUtils.TEST_SSID;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class SearchForSSIDTests extends BaseTestClass<TestActivity> {

    public SearchForSSIDTests() {
        super(TestActivity.class);
    }

    @Test
    public void noCallbacks_failure() {
        setManagers();
        assertEquals(null, mWiseFy.searchForSSID(TEST_SSID, 1));
    }

    @Test
    public void noCallbacks_failure_nullSSID() {
        setManagers();

        List<ScanResult> scanResults = new ArrayList<>();
        ScanResult scanResult = mock(ScanResult.class);
        scanResult.SSID = null;
        scanResults.add(scanResult);

        when(mMockWiFiManager.getScanResults()).thenReturn(scanResults);

        assertEquals(null, mWiseFy.searchForSSID(TEST_SSID, 1));
    }

    @Test
    public void noCallbacks_failure_nullSSIDParam() {
        setManagers();

        List<ScanResult> scanResults = new ArrayList<>();
        ScanResult scanResult = mock(ScanResult.class);
        scanResult.SSID = null;
        scanResults.add(scanResult);

        when(mMockWiFiManager.getScanResults()).thenReturn(scanResults);

        assertEquals(null, mWiseFy.searchForSSID(null, 1));
    }

    @Test
    public void noCallbacks_failure_differentSSID() {
        setManagers();

        List<ScanResult> scanResults = new ArrayList<>();
        ScanResult scanResult = mock(ScanResult.class);
        scanResult.SSID = "Another SSID";
        scanResults.add(scanResult);

        when(mMockWiFiManager.getScanResults()).thenReturn(scanResults);

        assertEquals(null, mWiseFy.searchForSSID(TEST_SSID, 1));
    }

    @Test
    public void noCallbacks_failure_nullWifiManager() {
        setManagersToNull();
        assertEquals(null, mWiseFy.searchForSSID(TEST_SSID, 1));
    }

    @Test
    public void noCallbacks_success() {
        setManagers();

        List<ScanResult> scanResults = new ArrayList<>();
        ScanResult scanResult = mock(ScanResult.class);
        scanResult.SSID = TEST_SSID;
        scanResults.add(scanResult);

        when(mMockWiFiManager.getScanResults()).thenReturn(scanResults);

        assertEquals(TEST_SSID, mWiseFy.searchForSSID(TEST_SSID, 1));
    }

    @Test
    public void callbacks_failure() {
        setManagers();
        SearchForSSIDCallbacks mockSearchForSSIDCallbacks = mock(SearchForSSIDCallbacks.class);
        mWiseFy.searchForSSID(TEST_SSID, 1, mockSearchForSSIDCallbacks);
        verify(mockSearchForSSIDCallbacks, timeout(2000)).ssidNotFound();
    }

    @Test
    public void callbacks_failure_nullCallback() {
        setManagers();
        SearchForSSIDCallbacks mockSearchForSSIDCallbacks = mock(SearchForSSIDCallbacks.class);
        mWiseFy.searchForSSID(TEST_SSID, 1, null);
        verify(mockSearchForSSIDCallbacks, never()).ssidNotFound();
    }

    @Test
    public void callbacks_failure_nullSSID() {
        setManagers();

        List<ScanResult> scanResults = new ArrayList<>();
        ScanResult scanResult = mock(ScanResult.class);
        scanResult.SSID = null;
        scanResults.add(scanResult);

        when(mMockWiFiManager.getScanResults()).thenReturn(scanResults);

        SearchForSSIDCallbacks mockSearchForSSIDCallbacks = mock(SearchForSSIDCallbacks.class);
        mWiseFy.searchForSSID(TEST_SSID, 1, mockSearchForSSIDCallbacks);
        verify(mockSearchForSSIDCallbacks, timeout(2000)).ssidNotFound();
    }

    @Test
    public void callbacks_failure_nullSSID_nullCallback() {
        setManagers();

        List<ScanResult> scanResults = new ArrayList<>();
        ScanResult scanResult = mock(ScanResult.class);
        scanResult.SSID = null;
        scanResults.add(scanResult);

        when(mMockWiFiManager.getScanResults()).thenReturn(scanResults);

        SearchForSSIDCallbacks mockSearchForSSIDCallbacks = mock(SearchForSSIDCallbacks.class);
        mWiseFy.searchForSSID(TEST_SSID, 1, null);
        verify(mockSearchForSSIDCallbacks, never()).ssidNotFound();
    }

    @Test
    public void callbacks_failure_nullSSIDParam() {
        setManagers();

        List<ScanResult> scanResults = new ArrayList<>();
        ScanResult scanResult = mock(ScanResult.class);
        scanResult.SSID = null;
        scanResults.add(scanResult);

        when(mMockWiFiManager.getScanResults()).thenReturn(scanResults);

        SearchForSSIDCallbacks mockSearchForSSIDCallbacks = mock(SearchForSSIDCallbacks.class);
        mWiseFy.searchForSSID(null, 1, mockSearchForSSIDCallbacks);
        verify(mockSearchForSSIDCallbacks, timeout(2000)).searchForSSIDWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
    }

    @Test
    public void callbacks_failure_nullSSIDParam_nullCallback() {
        setManagers();

        List<ScanResult> scanResults = new ArrayList<>();
        ScanResult scanResult = mock(ScanResult.class);
        scanResult.SSID = null;
        scanResults.add(scanResult);

        when(mMockWiFiManager.getScanResults()).thenReturn(scanResults);

        SearchForSSIDCallbacks mockSearchForSSIDCallbacks = mock(SearchForSSIDCallbacks.class);
        mWiseFy.searchForSSID(null, 1, null);
        verify(mockSearchForSSIDCallbacks, never()).searchForSSIDWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
    }

    @Test
    public void callbacks_failure_differentSSID() {
        setManagers();

        List<ScanResult> scanResults = new ArrayList<>();
        ScanResult scanResult = mock(ScanResult.class);
        scanResult.SSID = "Another SSID";
        scanResults.add(scanResult);

        when(mMockWiFiManager.getScanResults()).thenReturn(scanResults);

        SearchForSSIDCallbacks mockSearchForSSIDCallbacks = mock(SearchForSSIDCallbacks.class);
        mWiseFy.searchForSSID(TEST_SSID, 1, mockSearchForSSIDCallbacks);
        verify(mockSearchForSSIDCallbacks, timeout(2000)).ssidNotFound();
    }

    @Test
    public void callbacks_failure_differentSSID_nullCallback() {
        setManagers();

        List<ScanResult> scanResults = new ArrayList<>();
        ScanResult scanResult = mock(ScanResult.class);
        scanResult.SSID = "Another SSID";
        scanResults.add(scanResult);

        when(mMockWiFiManager.getScanResults()).thenReturn(scanResults);

        SearchForSSIDCallbacks mockSearchForSSIDCallbacks = mock(SearchForSSIDCallbacks.class);
        mWiseFy.searchForSSID(TEST_SSID, 1, null);
        verify(mockSearchForSSIDCallbacks, never()).ssidNotFound();
    }

    @Test
    public void callbacks_failure_nullWifiManager() {
        setManagersToNull();
        SearchForSSIDCallbacks mockSearchForSSIDCallbacks = mock(SearchForSSIDCallbacks.class);
        mWiseFy.searchForSSID(TEST_SSID, 1, mockSearchForSSIDCallbacks);
        verify(mockSearchForSSIDCallbacks, timeout(2000)).searchForSSIDWiseFyFailure(WiseFyCodes.NULL_MANAGER);
    }

    @Test
    public void callbacks_failure_nullWifiManager_nullCallback() {
        setManagersToNull();
        SearchForSSIDCallbacks mockSearchForSSIDCallbacks = mock(SearchForSSIDCallbacks.class);
        mWiseFy.searchForSSID(TEST_SSID, 1, null);
        verify(mockSearchForSSIDCallbacks, never()).searchForSSIDWiseFyFailure(WiseFyCodes.NULL_MANAGER);
    }

    @Test
    public void callbacks_success() {
        setManagers();

        List<ScanResult> scanResults = new ArrayList<>();
        ScanResult scanResult = mock(ScanResult.class);
        scanResult.SSID = TEST_SSID;
        scanResults.add(scanResult);

        when(mMockWiFiManager.getScanResults()).thenReturn(scanResults);

        SearchForSSIDCallbacks mockSearchForSSIDCallbacks = mock(SearchForSSIDCallbacks.class);
        mWiseFy.searchForSSID(TEST_SSID, 1, mockSearchForSSIDCallbacks);
        verify(mockSearchForSSIDCallbacks, timeout(2000)).ssidFound(TEST_SSID);
    }

    @Test
    public void callbacks_success_nullCallback() {
        setManagers();
        List<ScanResult> scanResults = new ArrayList<>();
        ScanResult scanResult = mock(ScanResult.class);
        scanResult.SSID = TEST_SSID;
        scanResults.add(scanResult);

        when(mMockWiFiManager.getScanResults()).thenReturn(scanResults);

        SearchForSSIDCallbacks mockSearchForSSIDCallbacks = mock(SearchForSSIDCallbacks.class);
        mWiseFy.searchForSSID(TEST_SSID, 1, null);
        verify(mockSearchForSSIDCallbacks, never()).ssidFound(TEST_SSID);
    }
}
