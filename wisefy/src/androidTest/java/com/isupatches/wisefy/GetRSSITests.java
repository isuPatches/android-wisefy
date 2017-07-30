package com.isupatches.wisefy;


import android.net.wifi.ScanResult;
import com.isupatches.wisefy.callbacks.GetRSSICallbacks;
import com.isupatches.wisefy.constants.WiseFyCodes;
import org.junit.Test;
import static com.isupatches.wisefy.base.TestUtils.TEST_RSSI_LEVEL;
import static com.isupatches.wisefy.base.TestUtils.TEST_SSID;
import static com.isupatches.wisefy.base.TestUtils.TEST_TIMEOUT;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class GetRSSITests extends BaseAndroidJUnit4TestClass {

    @Test
    public void sync_failure_takeHighest_false_nullSSIDParam() {
        assertEquals(null, mWiseFy.getRSSI(null, false, TEST_TIMEOUT));
    }

    @Test
    public void sync_failure_takeHighest_true_nullSSIDParam() {
        assertEquals(null, mWiseFy.getRSSI(null, true, TEST_TIMEOUT));
    }

    @Test
    public void sync_failure_takeHighest_false_missingPrerequisite() {
        missingPrerequisite();
        assertEquals(null, mWiseFy.getRSSI(TEST_SSID, false, TEST_TIMEOUT));
    }

    @Test
    public void sync_failure_takeHighest_true_missingPrerequisite() {
        missingPrerequisite();
        assertEquals(null, mWiseFy.getRSSI(TEST_SSID, true, TEST_TIMEOUT));
    }

    @Test
    public void sync_failure_takeHighest_false_noNetworkFound() {
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findAccessPointByRegex(anyString(), anyInt(), anyBoolean())).thenReturn(null);

        assertEquals(null, mWiseFy.getRSSI(TEST_SSID, false, TEST_TIMEOUT));
    }

    @Test
    public void sync_failure_takeHighest_true_noNetworkFound() {
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findAccessPointByRegex(anyString(), anyInt(), anyBoolean())).thenReturn(null);

        assertEquals(null, mWiseFy.getRSSI(TEST_SSID, true, TEST_TIMEOUT));
    }

    @Test
    public void sync_success_takeHighest_false () {
        ScanResult accessPoint = mock(ScanResult.class);
        accessPoint.level = TEST_RSSI_LEVEL;
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findAccessPointByRegex(anyString(), anyInt(), anyBoolean())).thenReturn(accessPoint);

        assertEquals(TEST_RSSI_LEVEL, (int) mWiseFy.getRSSI(TEST_SSID, false, TEST_TIMEOUT));
    }

    @Test
    public void sync_success_takeHighest_true () {
        ScanResult accessPoint = mock(ScanResult.class);
        accessPoint.level = TEST_RSSI_LEVEL;
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findAccessPointByRegex(anyString(), anyInt(), anyBoolean())).thenReturn(accessPoint);

        assertEquals(TEST_RSSI_LEVEL, (int) mWiseFy.getRSSI(TEST_SSID, true, TEST_TIMEOUT));
    }

    @Test
    public void async_failure_takeHighest_false_nullSSIDParam() {
        GetRSSICallbacks mockCallbacks = mock(GetRSSICallbacks.class);
        mWiseFy.getRSSI(null, false, TEST_TIMEOUT, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).getRSSIWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
    }

    @Test
    public void async_failure_takeHighest_false_nullSSIDParam_nullCallback() {
        try {
            mWiseFy.getRSSI(null, false, TEST_TIMEOUT, null);
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void async_failure_takeHighest_true_nullSSIDParam() {
        GetRSSICallbacks mockCallbacks = mock(GetRSSICallbacks.class);
        mWiseFy.getRSSI(null, true, TEST_TIMEOUT, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).getRSSIWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
    }

    @Test
    public void async_failure_takeHighest_true_nullSSIDParam_nullCallback() {
        try {
            mWiseFy.getRSSI(null, true, TEST_TIMEOUT, null);
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void async_failure_takeHighest_false_missingPrerequisite() {
        missingPrerequisite();
        GetRSSICallbacks mockCallbacks = mock(GetRSSICallbacks.class);
        mWiseFy.getRSSI(TEST_SSID, false, TEST_TIMEOUT, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).getRSSIWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
    }

    @Test
    public void async_failure_takeHighest_false_nullSSIDParam_missingPrerequisite() {
        missingPrerequisite();
        try {
            mWiseFy.getRSSI(TEST_SSID, false, TEST_TIMEOUT, null);
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void async_failure_takeHighest_true_missingPrerequisite() {
        missingPrerequisite();
        GetRSSICallbacks mockCallbacks = mock(GetRSSICallbacks.class);
        mWiseFy.getRSSI(TEST_SSID, true, TEST_TIMEOUT, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).getRSSIWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
    }

    @Test
    public void async_failure_takeHighest_true_nullSSIDParam_missingPrerequisite() {
        missingPrerequisite();
        try {
            mWiseFy.getRSSI(TEST_SSID, true, TEST_TIMEOUT, null);
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void async_failure_takeHighest_false_noNetworkFound() {
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findAccessPointByRegex(anyString(), anyInt(), anyBoolean())).thenReturn(null);

        GetRSSICallbacks mockCallbacks = mock(GetRSSICallbacks.class);
        mWiseFy.getRSSI(TEST_SSID, false, TEST_TIMEOUT, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).networkNotFoundToRetrieveRSSI();
    }

    @Test
    public void async_failure_takeHighest_false_noNetworkFound_nullCallback() {
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findAccessPointByRegex(anyString(), anyInt(), anyBoolean())).thenReturn(null);

        try {
            mWiseFy.getRSSI(TEST_SSID, false, TEST_TIMEOUT, null);
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void async_failure_takeHighest_true_noNetworkFound() {
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findAccessPointByRegex(anyString(), anyInt(), anyBoolean())).thenReturn(null);

        GetRSSICallbacks mockCallbacks = mock(GetRSSICallbacks.class);
        mWiseFy.getRSSI(TEST_SSID, true, TEST_TIMEOUT, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).networkNotFoundToRetrieveRSSI();
    }

    @Test
    public void async_failure_takeHighest_true_noNetworkFound_nullCallback() {
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findAccessPointByRegex(anyString(), anyInt(), anyBoolean())).thenReturn(null);

        try {
            mWiseFy.getRSSI(TEST_SSID, true, TEST_TIMEOUT, null);
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void async_success_takeHighest_false() {
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        ScanResult accessPoint = mock(ScanResult.class);
        accessPoint.level = TEST_RSSI_LEVEL;
        when(mockWiseFySearch.findAccessPointByRegex(anyString(), anyInt(), anyBoolean())).thenReturn(accessPoint);


        GetRSSICallbacks mockCallbacks = mock(GetRSSICallbacks.class);
        mWiseFy.getRSSI(TEST_SSID, false, TEST_TIMEOUT, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).retrievedRSSI(TEST_RSSI_LEVEL);
    }

    @Test
    public void async_success_takeHighest_false_nullCallback() {
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        ScanResult accessPoint = mock(ScanResult.class);
        accessPoint.level = TEST_RSSI_LEVEL;
        when(mockWiseFySearch.findAccessPointByRegex(anyString(), anyInt(), anyBoolean())).thenReturn(accessPoint);

        try {
            mWiseFy.getRSSI(TEST_SSID, false, TEST_TIMEOUT, null);
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void async_success_takeHighest_true() {
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        ScanResult accessPoint = mock(ScanResult.class);
        accessPoint.level = TEST_RSSI_LEVEL;
        when(mockWiseFySearch.findAccessPointByRegex(anyString(), anyInt(), anyBoolean())).thenReturn(accessPoint);


        GetRSSICallbacks mockCallbacks = mock(GetRSSICallbacks.class);
        mWiseFy.getRSSI(TEST_SSID, true, TEST_TIMEOUT, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).retrievedRSSI(TEST_RSSI_LEVEL);
    }

    @Test
    public void async_success_takeHighest_true_nullCallback() {
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        ScanResult accessPoint = mock(ScanResult.class);
        accessPoint.level = TEST_RSSI_LEVEL;
        when(mockWiseFySearch.findAccessPointByRegex(anyString(), anyInt(), anyBoolean())).thenReturn(accessPoint);

        try {
            mWiseFy.getRSSI(TEST_SSID, true, TEST_TIMEOUT, null);
        } catch (NullPointerException npe) {
            fail();
        }
    }
}