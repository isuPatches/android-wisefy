package com.isupatches.wisefy.test;


import android.net.wifi.ScanResult;
import com.isupatches.wisefy.callbacks.GetNearbyAccessPointsCallbacks;
import com.isupatches.wisefy.constants.WiseFyCodes;
import com.isupatches.wisefy.test.base.BaseTestClass;
import com.isupatches.wisefy.test.base.TestActivity;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static com.isupatches.wisefy.test.base.TestUtils.TEST_SSID;
import static com.isupatches.wisefy.test.base.TestUtils.TEST_SSID2;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class GetNearbyAccessPointsTests extends BaseTestClass<TestActivity> {

    public GetNearbyAccessPointsTests() {
        super(TestActivity.class);
    }

    @Test
    public void noCallbacks_success_filterDuplicates_false() {
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
    public void noCallbacks_success_filterDuplicates_true_differentSSIDs() {
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
    public void noCallbacks_success_filterDuplicates_true_sameSSIDs_higherFrequency() {
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
    public void noCallbacks_filterDuplicates_true_sameSSIDs_lowerFrequency() {
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
    public void noCallbacks_failure_filterDuplicates_false_nullWifiManager() {
        setManagersToNull();
        List<ScanResult> results = mWiseFy.getNearbyAccessPoints(false);
        assertEquals(null, results);
    }

    @Test
    public void noCallbacks_failure_filterDuplicates_true_nullWifiManager() {
        setManagersToNull();
        List<ScanResult> results = mWiseFy.getNearbyAccessPoints(true);
        assertEquals(null, results);
    }

    @Test
    public void callbacks_success_filterDuplicates_false() {
        setManagers();
        List<ScanResult> scanResults = new ArrayList<>();
        ScanResult scanResult1 = mock(ScanResult.class);
        scanResult1.SSID = TEST_SSID;
        scanResults.add(scanResult1);
        ScanResult scanResult2 = mock(ScanResult.class);
        scanResult2.SSID = TEST_SSID2;
        scanResults.add(scanResult2);

        when(mMockWiFiManager.getScanResults()).thenReturn(scanResults);

        GetNearbyAccessPointsCallbacks getNearbyAccessPointsCallbacks = mock(GetNearbyAccessPointsCallbacks.class);
        mWiseFy.getNearbyAccessPoints(false, getNearbyAccessPointsCallbacks);
        verify(getNearbyAccessPointsCallbacks, timeout(2000)).retrievedNearbyAccessPoints(scanResults);
    }

    @Test
    public void callbacks_success_filterDuplicates_false_nullCallback() {
        setManagers();
        List<ScanResult> scanResults = new ArrayList<>();
        ScanResult scanResult1 = mock(ScanResult.class);
        scanResult1.SSID = TEST_SSID;
        scanResults.add(scanResult1);
        ScanResult scanResult2 = mock(ScanResult.class);
        scanResult2.SSID = TEST_SSID2;
        scanResults.add(scanResult2);

        when(mMockWiFiManager.getScanResults()).thenReturn(scanResults);

        GetNearbyAccessPointsCallbacks getNearbyAccessPointsCallbacks = mock(GetNearbyAccessPointsCallbacks.class);
        mWiseFy.getNearbyAccessPoints(false, null);
        verify(getNearbyAccessPointsCallbacks, never()).retrievedNearbyAccessPoints(scanResults);
    }

    @Test
    public void callbacks_success_filterDuplicates_true_differentSSIDs() {
        setManagers();
        List<ScanResult> scanResults = new ArrayList<>();
        ScanResult scanResult1 = mock(ScanResult.class);
        scanResult1.SSID = TEST_SSID;
        scanResults.add(scanResult1);
        ScanResult scanResult2 = mock(ScanResult.class);
        scanResult2.SSID = TEST_SSID2;
        scanResults.add(scanResult2);

        when(mMockWiFiManager.getScanResults()).thenReturn(scanResults);

        GetNearbyAccessPointsCallbacks getNearbyAccessPointsCallbacks = mock(GetNearbyAccessPointsCallbacks.class);
        mWiseFy.getNearbyAccessPoints(true, getNearbyAccessPointsCallbacks);
        verify(getNearbyAccessPointsCallbacks, timeout(2000)).retrievedNearbyAccessPoints(scanResults);
    }

    @Test
    public void callbacks_success_filterDuplicates_true_differentSSIDs_nullCallback() {
        setManagers();
        List<ScanResult> scanResults = new ArrayList<>();
        ScanResult scanResult1 = mock(ScanResult.class);
        scanResult1.SSID = TEST_SSID;
        scanResults.add(scanResult1);
        ScanResult scanResult2 = mock(ScanResult.class);
        scanResult2.SSID = TEST_SSID2;
        scanResults.add(scanResult2);

        when(mMockWiFiManager.getScanResults()).thenReturn(scanResults);

        GetNearbyAccessPointsCallbacks getNearbyAccessPointsCallbacks = mock(GetNearbyAccessPointsCallbacks.class);
        mWiseFy.getNearbyAccessPoints(true, null);
        verify(getNearbyAccessPointsCallbacks, never()).retrievedNearbyAccessPoints(scanResults);
    }

    @Test
    public void callbacks_success_filterDuplicates_true_sameSSIDs_higherFrequency() {
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

        GetNearbyAccessPointsCallbacks getNearbyAccessPointsCallbacks = mock(GetNearbyAccessPointsCallbacks.class);
        mWiseFy.getNearbyAccessPoints(true, getNearbyAccessPointsCallbacks);
        verify(getNearbyAccessPointsCallbacks, timeout(2000)).retrievedNearbyAccessPoints(expectedScanResults);
    }

    @Test
    public void callbacks_success_filterDuplicates_true_sameSSIDs_higherFrequency_nullCallback() {
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

        GetNearbyAccessPointsCallbacks getNearbyAccessPointsCallbacks = mock(GetNearbyAccessPointsCallbacks.class);
        mWiseFy.getNearbyAccessPoints(true, null);
        verify(getNearbyAccessPointsCallbacks, never()).retrievedNearbyAccessPoints(expectedScanResults);
    }

    @Test
    public void callbacks_filterDuplicates_true_sameSSIDs_lowerFrequency() {
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

        GetNearbyAccessPointsCallbacks getNearbyAccessPointsCallbacks = mock(GetNearbyAccessPointsCallbacks.class);
        mWiseFy.getNearbyAccessPoints(true, getNearbyAccessPointsCallbacks);
        verify(getNearbyAccessPointsCallbacks, timeout(2000)).retrievedNearbyAccessPoints(expectedScanResults);
    }

    @Test
    public void callbacks_filterDuplicates_true_sameSSIDs_lowerFrequency_nullCallback() {
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

        GetNearbyAccessPointsCallbacks getNearbyAccessPointsCallbacks = mock(GetNearbyAccessPointsCallbacks.class);
        mWiseFy.getNearbyAccessPoints(true, null);
        verify(getNearbyAccessPointsCallbacks, never()).retrievedNearbyAccessPoints(expectedScanResults);
    }

    @Test
    public void callbacks_failure_filterDuplicates_false_nullWifiManager() {
        setManagersToNull();
        GetNearbyAccessPointsCallbacks getNearbyAccessPointsCallbacks = mock(GetNearbyAccessPointsCallbacks.class);
        mWiseFy.getNearbyAccessPoints(false, getNearbyAccessPointsCallbacks);
        verify(getNearbyAccessPointsCallbacks, timeout(2000)).getNearbyAccessPointsWiseFyFailure(WiseFyCodes.NULL_MANAGER);
    }

    @Test
    public void callbacks_failure_filterDuplicates_false_nullWifiManager_nullCallback() {
        setManagersToNull();
        GetNearbyAccessPointsCallbacks getNearbyAccessPointsCallbacks = mock(GetNearbyAccessPointsCallbacks.class);
        mWiseFy.getNearbyAccessPoints(false, null);
        verify(getNearbyAccessPointsCallbacks, never()).getNearbyAccessPointsWiseFyFailure(WiseFyCodes.NULL_MANAGER);
    }

    @Test
    public void callbacks_failure_filterDuplicates_true_nullWifiManager() {
        setManagersToNull();
        GetNearbyAccessPointsCallbacks getNearbyAccessPointsCallbacks = mock(GetNearbyAccessPointsCallbacks.class);
        mWiseFy.getNearbyAccessPoints(true, getNearbyAccessPointsCallbacks);
        verify(getNearbyAccessPointsCallbacks, timeout(2000)).getNearbyAccessPointsWiseFyFailure(WiseFyCodes.NULL_MANAGER);
    }

    @Test
    public void callbacks_failure_filterDuplicates_true_nullWifiManager_nullCallback() {
        setManagersToNull();
        GetNearbyAccessPointsCallbacks getNearbyAccessPointsCallbacks = mock(GetNearbyAccessPointsCallbacks.class);
        mWiseFy.getNearbyAccessPoints(true, null);
        verify(getNearbyAccessPointsCallbacks, never()).getNearbyAccessPointsWiseFyFailure(WiseFyCodes.NULL_MANAGER);
    }
}
