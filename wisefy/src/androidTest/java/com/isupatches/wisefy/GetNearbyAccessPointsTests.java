package com.isupatches.wisefy;


import android.net.wifi.ScanResult;
import com.isupatches.wisefy.callbacks.GetNearbyAccessPointsCallbacks;
import com.isupatches.wisefy.constants.WiseFyCodes;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import java.util.ArrayList;
import java.util.List;
import static com.isupatches.wisefy.base.TestUtils.TEST_SSID;
import static com.isupatches.wisefy.base.TestUtils.TEST_SSID2;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import static org.mockito.Mockito.after;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class GetNearbyAccessPointsTests extends BaseAndroidJUnit4TestClass {

    @Test
    public void sync_failure_missingPrerequisite_filterDuplicates_false() {
        missingPrerequisite();
        assertEquals(null, mWiseFy.getNearbyAccessPoints(false));
        verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).getScanResults();
    }

    @Test
    public void sync_failure_missingPrerequisite_filterDuplicates_true() {
        missingPrerequisite();
        assertEquals(null, mWiseFy.getNearbyAccessPoints(true));
        verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).getScanResults();
    }

    @Test
    public void sync_success_filterDuplicates_false() {
        List<ScanResult> scanResults = new ArrayList<>();
        ScanResult scanResult1 = mock(ScanResult.class);
        scanResult1.SSID = TEST_SSID;
        scanResult1.level = -35;
        scanResults.add(scanResult1);
        ScanResult scanResult2 = mock(ScanResult.class);
        scanResult2.SSID = TEST_SSID2;
        scanResult2.level = -70;
        scanResults.add(scanResult2);

        when(mMockWiFiManager.getScanResults()).thenReturn(scanResults);

        List<ScanResult> accessPoints = mWiseFy.getNearbyAccessPoints(false);
        assertEquals(accessPoints, scanResults);
        verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).getScanResults();
    }

    @Test
    public void sync_success_filterDuplicates_true() {
        List<ScanResult> scanResults = new ArrayList<>();
        ScanResult scanResult1 = mock(ScanResult.class);
        scanResult1.SSID = TEST_SSID;
        scanResult1.level = -35;
        scanResults.add(scanResult1);
        ScanResult scanResult2 = mock(ScanResult.class);
        scanResult2.SSID = TEST_SSID2;
        scanResult2.level = -70;
        scanResults.add(scanResult2);

        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.removeEntriesWithLowerSignalStrength(ArgumentMatchers.<ScanResult>anyList())).thenReturn(scanResults);

        List<ScanResult> accessPoints = mWiseFy.getNearbyAccessPoints(true);
        assertEquals(accessPoints, scanResults);
        verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).getScanResults();
    }

    @Test
    public void async_failure_missingPrerequisite_filterDuplicates_false() {
        missingPrerequisite();
        GetNearbyAccessPointsCallbacks mockCallbacks = mock(GetNearbyAccessPointsCallbacks.class);
        mWiseFy.getNearbyAccessPoints(false, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).getNearbyAccessPointsWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
        verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).getScanResults();
    }

    @Test
    public void async_failure_missingPrerequisite_filterDuplicates_false_nullCallback() {
        missingPrerequisite();
        try {
            mWiseFy.getNearbyAccessPoints(false, null);
            verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).getScanResults();
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void async_failure_missingPrerequisite_filterDuplicates_true() {
        missingPrerequisite();
        GetNearbyAccessPointsCallbacks mockCallbacks = mock(GetNearbyAccessPointsCallbacks.class);
        mWiseFy.getNearbyAccessPoints(true, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).getNearbyAccessPointsWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
        verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).getScanResults();
    }

    @Test
    public void async_failure_missingPrerequisite_filterDuplicates_true_nullCallback() {
        missingPrerequisite();
        try {
            mWiseFy.getNearbyAccessPoints(true, null);
            verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).getScanResults();
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void async_success_filterDuplicates_false() {
        List<ScanResult> scanResults = new ArrayList<>();
        ScanResult scanResult1 = mock(ScanResult.class);
        scanResult1.SSID = TEST_SSID;
        scanResult1.level = -35;
        scanResults.add(scanResult1);
        ScanResult scanResult2 = mock(ScanResult.class);
        scanResult2.SSID = TEST_SSID2;
        scanResult2.level = -70;
        scanResults.add(scanResult2);

        when(mMockWiFiManager.getScanResults()).thenReturn(scanResults);

        GetNearbyAccessPointsCallbacks mockCallbacks = mock(GetNearbyAccessPointsCallbacks.class);
        mWiseFy.getNearbyAccessPoints(false, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).retrievedNearbyAccessPoints(scanResults);
        verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).getScanResults();
    }

    @Test
    public void async_success_filterDuplicates_false_nullCallback() {
        List<ScanResult> scanResults = new ArrayList<>();
        ScanResult scanResult1 = mock(ScanResult.class);
        scanResult1.SSID = TEST_SSID;
        scanResult1.level = -35;
        scanResults.add(scanResult1);
        ScanResult scanResult2 = mock(ScanResult.class);
        scanResult2.SSID = TEST_SSID2;
        scanResult2.level = -70;
        scanResults.add(scanResult2);

        when(mMockWiFiManager.getScanResults()).thenReturn(scanResults);

        try {
            mWiseFy.getNearbyAccessPoints(false, null);
            verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).getScanResults();
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void async_success_filterDuplicates_true() {
        List<ScanResult> scanResults = new ArrayList<>();
        ScanResult scanResult1 = mock(ScanResult.class);
        scanResult1.SSID = TEST_SSID;
        scanResult1.level = -35;
        scanResults.add(scanResult1);
        ScanResult scanResult2 = mock(ScanResult.class);
        scanResult2.SSID = TEST_SSID2;
        scanResult2.level = -70;
        scanResults.add(scanResult2);

        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.removeEntriesWithLowerSignalStrength(ArgumentMatchers.<ScanResult>anyList())).thenReturn(scanResults);

        GetNearbyAccessPointsCallbacks mockCallbacks = mock(GetNearbyAccessPointsCallbacks.class);
        mWiseFy.getNearbyAccessPoints(true, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).retrievedNearbyAccessPoints(scanResults);
        verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).getScanResults();
    }

    @Test
    public void async_success_filterDuplicates_true_nullCallback() {
        List<ScanResult> scanResults = new ArrayList<>();
        ScanResult scanResult1 = mock(ScanResult.class);
        scanResult1.SSID = TEST_SSID;
        scanResult1.level = -35;
        scanResults.add(scanResult1);
        ScanResult scanResult2 = mock(ScanResult.class);
        scanResult2.SSID = TEST_SSID2;
        scanResult2.level = -70;
        scanResults.add(scanResult2);

        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.removeEntriesWithLowerSignalStrength(ArgumentMatchers.<ScanResult>anyList())).thenReturn(scanResults);

        try {
            mWiseFy.getNearbyAccessPoints(true, null);
            verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).getScanResults();
        } catch (NullPointerException npe) {
            fail();
        }
    }
}