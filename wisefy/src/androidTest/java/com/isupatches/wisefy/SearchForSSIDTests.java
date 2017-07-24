//package com.isupatches.wisefy;
//
//
//import android.net.wifi.ScanResult;
//import com.isupatches.wisefy.base.TestActivity;
//import com.isupatches.wisefy.callbacks.SearchForSSIDCallbacks;
//import com.isupatches.wisefy.constants.WiseFyCodes;
//import org.junit.Test;
//import static com.isupatches.wisefy.base.TestUtils.TEST_SSID;
//import static junit.framework.Assert.fail;
//import static org.junit.Assert.assertEquals;
//import static org.mockito.ArgumentMatchers.anyBoolean;
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.timeout;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//
//public class SearchForSSIDTests extends BaseTestClass<TestActivity> {
//
//    public SearchForSSIDTests() {
//        super(TestActivity.class);
//    }
//
//    @Test
//    public void noCallbacks_failure_nullSSIDParam() {
//        assertEquals(null, mWiseFy.searchForSSID(null, 1000));
//    }
//
//    @Test
//    public void noCallbacks_failure_missingPrerequisite() {
//        missingPrerequisite();
//        assertEquals(null, mWiseFy.searchForSSID(TEST_SSID, 1000));
//    }
//
//    @Test
//    public void noCallbacks_failure() {
//        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
//        mWiseFy.mWiseFySearch = mockWiseFySearch;
//        when(mockWiseFySearch.findAccessPointByRegex(anyString(), anyInt(), anyBoolean())).thenReturn(null);
//
//        assertEquals(null, mWiseFy.searchForSSID(TEST_SSID, 1000));
//    }
//
//    @Test
//    public void noCallbacks_success() {
//        ScanResult accessPoint = mock(ScanResult.class);
//        accessPoint.SSID = TEST_SSID;
//        accessPoint.level = -35;
//
//        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
//        mWiseFy.mWiseFySearch = mockWiseFySearch;
//        when(mockWiseFySearch.findAccessPointByRegex(anyString(), anyInt(), anyBoolean())).thenReturn(accessPoint);
//
//        assertEquals(TEST_SSID, mWiseFy.searchForSSID(TEST_SSID, 1000));
//    }
//
//    @Test
//    public void callbacks_failure_nullSSIDParam() {
//        SearchForSSIDCallbacks mockCallbacks = mock(SearchForSSIDCallbacks.class);
//        mWiseFy.searchForSSID(null, 1000, mockCallbacks);
//        verify(mockCallbacks, timeout(VERIFICATION_TIMEOUT)).searchForSSIDWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
//    }
//
//    @Test
//    public void callbacks_failure_nullSSIDParam_nullCallback() {
//        try {
//            mWiseFy.searchForSSID(null, 1000, null);
//        } catch (NullPointerException npe) {
//            fail();
//        }
//    }
//
//    @Test
//    public void callbacks_failure_missingPrerequisite() {
//        missingPrerequisite();
//        SearchForSSIDCallbacks mockCallbacks = mock(SearchForSSIDCallbacks.class);
//        mWiseFy.searchForSSID(TEST_SSID, 1000, mockCallbacks);
//        verify(mockCallbacks, timeout(VERIFICATION_TIMEOUT)).searchForSSIDWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
//    }
//
//    @Test
//    public void callbacks_failure_missingPrerequisite_nullCallback() {
//        missingPrerequisite();
//        try {
//            mWiseFy.searchForSSID(TEST_SSID, 1000, null);
//        } catch (NullPointerException npe) {
//            fail();
//        }
//    }
//
//    @Test
//    public void callbacks_failure() {
//        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
//        mWiseFy.mWiseFySearch = mockWiseFySearch;
//        when(mockWiseFySearch.findAccessPointByRegex(anyString(), anyInt(), anyBoolean())).thenReturn(null);
//
//        SearchForSSIDCallbacks mockCallbacks = mock(SearchForSSIDCallbacks.class);
//        mWiseFy.searchForSSID(TEST_SSID, 1000, mockCallbacks);
//        verify(mockCallbacks, timeout(VERIFICATION_TIMEOUT)).ssidNotFound();
//    }
//
//
//    @Test
//    public void callbacks_failure_nullCallback() {
//        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
//        mWiseFy.mWiseFySearch = mockWiseFySearch;
//        when(mockWiseFySearch.findAccessPointByRegex(anyString(), anyInt(), anyBoolean())).thenReturn(null);
//
//        try {
//            mWiseFy.searchForSSID(TEST_SSID, 1000, null);
//        } catch (NullPointerException npe) {
//            fail();
//        }
//    }
//
//    @Test
//    public void callbacks_success() {
//        ScanResult accessPoint = mock(ScanResult.class);
//        accessPoint.SSID = TEST_SSID;
//        accessPoint.level = -35;
//
//        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
//        mWiseFy.mWiseFySearch = mockWiseFySearch;
//        when(mockWiseFySearch.findAccessPointByRegex(anyString(), anyInt(), anyBoolean())).thenReturn(accessPoint);
//
//        SearchForSSIDCallbacks mockCallbacks = mock(SearchForSSIDCallbacks.class);
//        mWiseFy.searchForSSID(TEST_SSID, 1000, mockCallbacks);
//        verify(mockCallbacks, timeout(VERIFICATION_TIMEOUT)).ssidFound(TEST_SSID);
//    }
//
//    @Test
//    public void callbacks_success_nullCallback() {
//        ScanResult accessPoint = mock(ScanResult.class);
//        accessPoint.SSID = TEST_SSID;
//
//        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
//        mWiseFy.mWiseFySearch = mockWiseFySearch;
//        when(mockWiseFySearch.findAccessPointByRegex(anyString(), anyInt(), anyBoolean())).thenReturn(accessPoint);
//
//        try {
//            mWiseFy.searchForSSID(TEST_SSID, 1000, null);
//        } catch (NullPointerException npe) {
//            fail();
//        }
//    }
//}
