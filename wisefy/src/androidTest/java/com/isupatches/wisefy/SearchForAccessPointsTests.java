package com.isupatches.wisefy;


import android.net.wifi.ScanResult;
import com.isupatches.wisefy.callbacks.SearchForAccessPointsCallbacks;
import com.isupatches.wisefy.constants.WiseFyCodes;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static com.isupatches.wisefy.base.TestUtils.TEST_SSID;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class SearchForAccessPointsTests extends BaseAndroidJUnit4TestClass {

    @Test
    public void sync_failure_filterDuplicates_false_nullSSIDParam() {
        assertEquals(null, mWiseFy.searchForAccessPoints(null, false));
    }

    @Test
    public void sync_failure_filterDuplicates_true_nullSSIDParam() {
        assertEquals(null, mWiseFy.searchForAccessPoints(null, true));
    }

    @Test
    public void sync_failure_filterDuplicates_false_missingPrerequisite() {
        missingPrerequisite();
        assertEquals(null, mWiseFy.searchForAccessPoints(TEST_SSID, false));
    }

    @Test
    public void sync_failure_filterDuplicates_true_missingPrerequisite() {
        missingPrerequisite();
        assertEquals(null, mWiseFy.searchForAccessPoints(TEST_SSID, true));
    }

    @Test
    public void sync_failure_filterDuplicates_false() {
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findAccessPointsMatchingRegex(anyString(), anyBoolean())).thenReturn(null);

        assertEquals(null, mWiseFy.searchForAccessPoints(TEST_SSID, false));
    }

    @Test
    public void sync_failure_filterDuplicates_true() {
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findAccessPointsMatchingRegex(anyString(), anyBoolean())).thenReturn(null);

        assertEquals(null, mWiseFy.searchForAccessPoints(TEST_SSID, true));
    }

    @Test
    public void sync_success_filterDuplicates_false() {
        List<ScanResult> accessPoints = new ArrayList<>();
        ScanResult accessPoint = mock(ScanResult.class);
        accessPoint.SSID = TEST_SSID;
        accessPoint.level = -35;
        accessPoints.add(accessPoint);

        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findAccessPointsMatchingRegex(anyString(), anyBoolean())).thenReturn(accessPoints);

        assertEquals(accessPoints, mWiseFy.searchForAccessPoints(TEST_SSID, false));
    }

    @Test
    public void sync_success_filterDuplicates_true() {
        List<ScanResult> accessPoints = new ArrayList<>();
        ScanResult accessPoint = mock(ScanResult.class);
        accessPoint.SSID = TEST_SSID;
        accessPoint.level = -35;
        accessPoints.add(accessPoint);

        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findAccessPointsMatchingRegex(anyString(), anyBoolean())).thenReturn(accessPoints);

        assertEquals(accessPoints, mWiseFy.searchForAccessPoints(TEST_SSID, true));
    }

    @Test
    public void async_failure_filterDuplicates_false_nullSSIDParam() {
        SearchForAccessPointsCallbacks mockCallbacks = mock(SearchForAccessPointsCallbacks.class);
        mWiseFy.searchForAccessPoints(null, false, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).searchForAccessPointsWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
    }

    @Test
    public void async_failure_filterDuplicates_false_nullSSIDParam_nullCallback() {
        try {
            mWiseFy.searchForAccessPoints(null, false, null);
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void async_failure_filterDuplicates_true_nullSSIDParam() {
        SearchForAccessPointsCallbacks mockCallbacks = mock(SearchForAccessPointsCallbacks.class);
        mWiseFy.searchForAccessPoints(null, true, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).searchForAccessPointsWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
    }

    @Test
    public void async_failure_filterDuplicates_true_nullSSIDParam_nullCallback() {
        try {
            mWiseFy.searchForAccessPoints(null, true, null);
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void async_failure_filterDuplicates_false_missingPrerequisite() {
        missingPrerequisite();
        SearchForAccessPointsCallbacks mockCallbacks = mock(SearchForAccessPointsCallbacks.class);
        mWiseFy.searchForAccessPoints(TEST_SSID, false, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).searchForAccessPointsWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
    }

    @Test
    public void async_failure_filterDuplicates_false_missingPrerequisite_nullCallback() {
        missingPrerequisite();
        try {
            mWiseFy.searchForAccessPoints(TEST_SSID, false, null);
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void async_failure_filterDuplicates_true_missingPrerequisite() {
        missingPrerequisite();
        SearchForAccessPointsCallbacks mockCallbacks = mock(SearchForAccessPointsCallbacks.class);
        mWiseFy.searchForAccessPoints(TEST_SSID, true, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).searchForAccessPointsWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
    }

    @Test
    public void async_failure_filterDuplicates_true_missingPrerequisite_nullCallback() {
        missingPrerequisite();
        try {
            mWiseFy.searchForAccessPoints(TEST_SSID, true, null);
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void async_failure_filterDuplicates_false() {
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findAccessPointsMatchingRegex(anyString(), anyBoolean())).thenReturn(null);

        SearchForAccessPointsCallbacks mockCallbacks = mock(SearchForAccessPointsCallbacks.class);
        mWiseFy.searchForAccessPoints(TEST_SSID, false, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).noAccessPointsFound();
    }

    @Test
    public void async_failure_filterDuplicates_false_nullCallback() {
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findAccessPointsMatchingRegex(anyString(), anyBoolean())).thenReturn(null);

        try {
            mWiseFy.searchForAccessPoints(TEST_SSID, false, null);
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void async_failure_filterDuplicates_true() {
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findAccessPointsMatchingRegex(anyString(), anyBoolean())).thenReturn(null);

        SearchForAccessPointsCallbacks mockCallbacks = mock(SearchForAccessPointsCallbacks.class);
        mWiseFy.searchForAccessPoints(TEST_SSID, true, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).noAccessPointsFound();
    }


    @Test
    public void async_failure_filterDuplicates_true_nullCallback() {
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findAccessPointsMatchingRegex(anyString(), anyBoolean())).thenReturn(null);

        try {
            mWiseFy.searchForAccessPoints(TEST_SSID, true, null);
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void async_success_filterDuplicates_false() {
        List<ScanResult> accessPoints = new ArrayList<>();
        ScanResult accessPoint = mock(ScanResult.class);
        accessPoint.SSID = TEST_SSID;
        accessPoint.level = -35;
        accessPoints.add(accessPoint);

        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findAccessPointsMatchingRegex(anyString(), anyBoolean())).thenReturn(accessPoints);

        SearchForAccessPointsCallbacks mockCallbacks = mock(SearchForAccessPointsCallbacks.class);
        mWiseFy.searchForAccessPoints(TEST_SSID, true, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).foundAccessPoints(accessPoints);
    }

    @Test
    public void async_success_filterDuplicates_false_nullCallback() {
        List<ScanResult> accessPoints = new ArrayList<>();
        ScanResult accessPoint = mock(ScanResult.class);
        accessPoint.SSID = TEST_SSID;
        accessPoint.level = -35;
        accessPoints.add(accessPoint);

        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findAccessPointsMatchingRegex(anyString(), anyBoolean())).thenReturn(accessPoints);

        try {
            mWiseFy.searchForAccessPoints(TEST_SSID, true, null);
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void async_success_filterDuplicates_true() {
        List<ScanResult> accessPoints = new ArrayList<>();
        ScanResult accessPoint = mock(ScanResult.class);
        accessPoint.SSID = TEST_SSID;
        accessPoint.level = -35;
        accessPoints.add(accessPoint);

        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findAccessPointsMatchingRegex(anyString(), anyBoolean())).thenReturn(accessPoints);

        SearchForAccessPointsCallbacks mockCallbacks = mock(SearchForAccessPointsCallbacks.class);
        mWiseFy.searchForAccessPoints(TEST_SSID, true, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).foundAccessPoints(accessPoints);
    }

    @Test
    public void async_success_filterDuplicates_true_nullCallback() {
        List<ScanResult> accessPoints = new ArrayList<>();
        ScanResult accessPoint = mock(ScanResult.class);
        accessPoint.SSID = TEST_SSID;
        accessPoint.level = -35;
        accessPoints.add(accessPoint);

        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findAccessPointsMatchingRegex(anyString(), anyBoolean())).thenReturn(accessPoints);

        try {
            mWiseFy.searchForAccessPoints(TEST_SSID, true, null);
        } catch (NullPointerException npe) {
            fail();
        }
    }
}
