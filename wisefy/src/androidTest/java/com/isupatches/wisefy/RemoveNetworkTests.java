package com.isupatches.wisefy;


import android.net.wifi.WifiConfiguration;
import com.isupatches.wisefy.callbacks.RemoveNetworkCallbacks;
import com.isupatches.wisefy.constants.WiseFyCodes;
import org.junit.Test;
import static com.isupatches.wisefy.base.TestUtils.TEST_SSID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.after;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class RemoveNetworkTests extends BaseAndroidJUnit4TestClass {

    @Test
    public void sync_failure_nullSSIDParam() {
        assertEquals(false, mWiseFy.removeNetwork(null));
        verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).removeNetwork(anyInt());
    }

    @Test
    public void sync_failure_missingPrerequisites() {
        missingPrerequisite();
        assertEquals(false, mWiseFy.removeNetwork(TEST_SSID));
        verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).removeNetwork(anyInt());
    }

    @Test
    public void sync_failure_noSavedNetwork() {
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findSavedNetworkByRegex(anyString())).thenReturn(null);

        assertEquals(false, mWiseFy.removeNetwork(TEST_SSID));
        verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).removeNetwork(anyInt());
    }

    @Test
    public void sync_failure() {
        WifiConfiguration wiFiConfiguration = new WifiConfiguration();
        wiFiConfiguration.SSID = TEST_SSID;
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findSavedNetworkByRegex(anyString())).thenReturn(wiFiConfiguration);

        when(mMockWiFiManager.removeNetwork(anyInt())).thenReturn(false);

        assertEquals(false, mWiseFy.removeNetwork(TEST_SSID));
        verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).removeNetwork(anyInt());

    }

    @Test
    public void sync_success() {
        WifiConfiguration wiFiConfiguration = new WifiConfiguration();
        wiFiConfiguration.SSID = TEST_SSID;
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findSavedNetworkByRegex(anyString())).thenReturn(wiFiConfiguration);

        when(mMockWiFiManager.removeNetwork(anyInt())).thenReturn(true);

        assertEquals(true, mWiseFy.removeNetwork(TEST_SSID));
        verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).removeNetwork(anyInt());
    }

    @Test
    public void async_failure_nullSSIDParam() {
        RemoveNetworkCallbacks mockCallbacks = mock(RemoveNetworkCallbacks.class);
        mWiseFy.removeNetwork(null, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).removeNetworkWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
        verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).removeNetwork(anyInt());
    }

    @Test
    public void async_failure_nullSSIDParam_nullCallback() {
        try {
            mWiseFy.removeNetwork(null, null);
            verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).removeNetwork(anyInt());
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void async_failure_missingPrerequisites() {
        missingPrerequisite();
        RemoveNetworkCallbacks mockCallbacks = mock(RemoveNetworkCallbacks.class);
        mWiseFy.removeNetwork(TEST_SSID, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).removeNetworkWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
        verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).removeNetwork(anyInt());
    }

    @Test
    public void async_failure_missingPrerequisites_nullCallback() {
        missingPrerequisite();
        try {
            mWiseFy.removeNetwork(TEST_SSID, null);
            verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).removeNetwork(anyInt());
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void async_failure_noSavedNetwork() {
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findSavedNetworkByRegex(anyString())).thenReturn(null);

        RemoveNetworkCallbacks mockCallbacks = mock(RemoveNetworkCallbacks.class);
        mWiseFy.removeNetwork(TEST_SSID, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).networkNotFoundToRemove();
        verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).removeNetwork(anyInt());
    }

    @Test
    public void async_failure_noSavedNetwork_nullCallback() {
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findSavedNetworkByRegex(anyString())).thenReturn(null);

        try {
            mWiseFy.removeNetwork(TEST_SSID, null);
            verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).removeNetwork(anyInt());
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void async_failure() {
        WifiConfiguration wiFiConfiguration = new WifiConfiguration();
        wiFiConfiguration.SSID = TEST_SSID;
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findSavedNetworkByRegex(anyString())).thenReturn(wiFiConfiguration);

        when(mMockWiFiManager.removeNetwork(anyInt())).thenReturn(false);

        RemoveNetworkCallbacks mockCallbacks = mock(RemoveNetworkCallbacks.class);
        mWiseFy.removeNetwork(TEST_SSID, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).failureRemovingNetwork();
        verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).removeNetwork(anyInt());
    }

    @Test
    public void async_failure_nullCallback() {
        WifiConfiguration wiFiConfiguration = new WifiConfiguration();
        wiFiConfiguration.SSID = TEST_SSID;
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findSavedNetworkByRegex(anyString())).thenReturn(wiFiConfiguration);

        when(mMockWiFiManager.removeNetwork(anyInt())).thenReturn(false);

        try {
            mWiseFy.removeNetwork(TEST_SSID, null);
            verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).removeNetwork(anyInt());
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void async_success() {
        WifiConfiguration wiFiConfiguration = new WifiConfiguration();
        wiFiConfiguration.SSID = TEST_SSID;
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findSavedNetworkByRegex(anyString())).thenReturn(wiFiConfiguration);

        when(mMockWiFiManager.removeNetwork(anyInt())).thenReturn(true);

        RemoveNetworkCallbacks mockCallbacks = mock(RemoveNetworkCallbacks.class);
        mWiseFy.removeNetwork(TEST_SSID, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).networkRemoved();
        verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).removeNetwork(anyInt());
    }

    @Test
    public void async_success_nullCallback() {
        WifiConfiguration wiFiConfiguration = new WifiConfiguration();
        wiFiConfiguration.SSID = TEST_SSID;
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findSavedNetworkByRegex(anyString())).thenReturn(wiFiConfiguration);

        when(mMockWiFiManager.removeNetwork(anyInt())).thenReturn(true);

        try {
            mWiseFy.removeNetwork(TEST_SSID, null);
            verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).removeNetwork(anyInt());
        } catch (NullPointerException npe) {
            fail();
        }
    }
}
