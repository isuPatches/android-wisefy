//package com.isupatches.wisefy;
//
//
//import com.isupatches.wisefy.base.TestActivity;
//import com.isupatches.wisefy.callbacks.SearchForSSIDsCallbacks;
//import com.isupatches.wisefy.constants.WiseFyCodes;
//import org.junit.Test;
//import java.util.ArrayList;
//import java.util.List;
//import static com.isupatches.wisefy.base.TestUtils.TEST_SSID;
//import static junit.framework.Assert.fail;
//import static org.junit.Assert.assertEquals;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.timeout;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//
//public class SearchForSSIDsTests extends BaseTestClass<TestActivity> {
//
//    public SearchForSSIDsTests() {
//        super(TestActivity.class);
//    }
//
//    @Test
//    public void noCallbacks_failure_nullSSIDParam() {
//        assertEquals(null, mWiseFy.searchForSSIDs(null));
//    }
//
//    @Test
//    public void noCallbacks_failure_missingPrerequisite() {
//        missingPrerequisite();
//        assertEquals(null, mWiseFy.searchForSSIDs(TEST_SSID));
//    }
//
//    @Test
//    public void noCallbacks_failure() {
//        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
//        mWiseFy.mWiseFySearch = mockWiseFySearch;
//        when(mockWiseFySearch.findSSIDsMatchingRegex(anyString())).thenReturn(null);
//
//        assertEquals(null, mWiseFy.searchForSSIDs(TEST_SSID));
//    }
//
//    @Test
//    public void noCallbacks_success() {
//        List<String> ssids = new ArrayList<>();
//        ssids.add(TEST_SSID);
//
//        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
//        mWiseFy.mWiseFySearch = mockWiseFySearch;
//        when(mockWiseFySearch.findSSIDsMatchingRegex(anyString())).thenReturn(ssids);
//
//        assertEquals(ssids, mWiseFy.searchForSSIDs(TEST_SSID));
//    }
//
//    @Test
//    public void callbacks_failure_nullSSIDParam() {
//        SearchForSSIDsCallbacks mockCallbacks = mock(SearchForSSIDsCallbacks.class);
//        mWiseFy.searchForSSIDs(null, mockCallbacks);
//        verify(mockCallbacks, timeout(VERIFICATION_TIMEOUT)).searchForSSIDsWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
//    }
//
//    @Test
//    public void callbacks_failure_nullSSIDParam_nullCallback() {
//        try {
//            mWiseFy.searchForSSIDs(null, null);
//        } catch (NullPointerException npe) {
//            fail();
//        }
//    }
//
//    @Test
//    public void callbacks_failure_missingPrerequisite() {
//        missingPrerequisite();
//        SearchForSSIDsCallbacks mockCallbacks = mock(SearchForSSIDsCallbacks.class);
//        mWiseFy.searchForSSIDs(TEST_SSID, mockCallbacks);
//        verify(mockCallbacks, timeout(VERIFICATION_TIMEOUT)).searchForSSIDsWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
//    }
//
//    @Test
//    public void callbacks_failure_missingPrerequisite_nullCallback() {
//        missingPrerequisite();
//        try {
//            mWiseFy.searchForSSIDs(TEST_SSID, null);
//        } catch (NullPointerException npe) {
//            fail();
//        }
//    }
//
//    @Test
//    public void callbacks_failure() {
//        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
//        mWiseFy.mWiseFySearch = mockWiseFySearch;
//        when(mockWiseFySearch.findSSIDsMatchingRegex(anyString())).thenReturn(null);
//
//        SearchForSSIDsCallbacks mockCallbacks = mock(SearchForSSIDsCallbacks.class);
//        mWiseFy.searchForSSIDs(TEST_SSID, mockCallbacks);
//        verify(mockCallbacks, timeout(VERIFICATION_TIMEOUT)).noSSIDsFound();
//    }
//
//
//    @Test
//    public void callbacks_failure_nullCallback() {
//        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
//        mWiseFy.mWiseFySearch = mockWiseFySearch;
//        when(mockWiseFySearch.findSSIDsMatchingRegex(anyString())).thenReturn(null);
//
//        try {
//            mWiseFy.searchForSSIDs(TEST_SSID, null);
//        } catch (NullPointerException npe) {
//            fail();
//        }
//    }
//
//    @Test
//    public void callbacks_success() {
//        List<String> ssids = new ArrayList<>();
//        ssids.add(TEST_SSID);
//
//        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
//        mWiseFy.mWiseFySearch = mockWiseFySearch;
//        when(mockWiseFySearch.findSSIDsMatchingRegex(anyString())).thenReturn(ssids);
//
//        SearchForSSIDsCallbacks mockCallbacks = mock(SearchForSSIDsCallbacks.class);
//        mWiseFy.searchForSSIDs(TEST_SSID, mockCallbacks);
//        verify(mockCallbacks, timeout(VERIFICATION_TIMEOUT)).retrievedSSIDs(ssids);
//    }
//
//    @Test
//    public void callbacks_success_nullCallback() {
//        List<String> ssids = new ArrayList<>();
//        ssids.add(TEST_SSID);
//
//        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
//        mWiseFy.mWiseFySearch = mockWiseFySearch;
//        when(mockWiseFySearch.findSSIDsMatchingRegex(anyString())).thenReturn(ssids);
//
//        try {
//            mWiseFy.searchForSSIDs(TEST_SSID, null);
//        } catch (NullPointerException npe) {
//            fail();
//        }
//    }
//}
