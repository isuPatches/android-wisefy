package com.isupatches.wisefy;


import android.net.wifi.WifiConfiguration;
import com.isupatches.wisefy.callbacks.ConnectToNetworkCallbacks;
import com.isupatches.wisefy.constants.WiseFyCodes;
import org.junit.Test;
import static com.isupatches.wisefy.base.TestUtils.TEST_SSID;
import static com.isupatches.wisefy.base.TestUtils.TEST_TIMEOUT;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.after;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class ConnectToNetworkTests extends BaseAndroidJUnit4TestClass {

    @Test
    public void sync_failure_nullSSIDParam() {
        assertEquals(false, mWiseFy.connectToNetwork(null, TEST_TIMEOUT));
        verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).enableNetwork(anyInt(), anyBoolean());
    }

    @Test
    public void sync_failure_missingPrerequisites() {
        missingPrerequisite();
        assertEquals(false, mWiseFy.connectToNetwork(TEST_SSID, TEST_TIMEOUT));
        verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).enableNetwork(anyInt(), anyBoolean());
    }

    @Test
    public void sync_failure_noSavedNetwork() {
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findSavedNetworkByRegex(anyString())).thenReturn(null);

        assertEquals(false, mWiseFy.connectToNetwork(TEST_SSID, TEST_TIMEOUT));
        verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).enableNetwork(anyInt(), anyBoolean());
    }

    @Test
    public void sync_failure() {
        WifiConfiguration wiFiConfiguration = new WifiConfiguration();
        wiFiConfiguration.SSID = TEST_SSID;
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findSavedNetworkByRegex(anyString())).thenReturn(wiFiConfiguration);

        WiseFyConnection mockWiseFyConnection = mock(WiseFyConnection.class);
        mWiseFy.mWiseFyConnection = mockWiseFyConnection;
        when(mockWiseFyConnection.waitToConnectToSSID(anyString(), anyInt())).thenReturn(false);

        assertEquals(false, mWiseFy.connectToNetwork(TEST_SSID, TEST_TIMEOUT));
        verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).enableNetwork(anyInt(), anyBoolean());
    }

    @Test
    public void sync_success() {
        WifiConfiguration wiFiConfiguration = new WifiConfiguration();
        wiFiConfiguration.SSID = TEST_SSID;
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findSavedNetworkByRegex(anyString())).thenReturn(wiFiConfiguration);

        WiseFyConnection mockWiseFyConnection = mock(WiseFyConnection.class);
        mWiseFy.mWiseFyConnection = mockWiseFyConnection;
        when(mockWiseFyConnection.waitToConnectToSSID(anyString(), anyInt())).thenReturn(true);

        assertEquals(true, mWiseFy.connectToNetwork(TEST_SSID, TEST_TIMEOUT));
        verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).enableNetwork(anyInt(), anyBoolean());
    }

    @Test
    public void async_failure_nullSSIDParam() {
        ConnectToNetworkCallbacks mockCallbacks = mock(ConnectToNetworkCallbacks.class);
        mWiseFy.connectToNetwork(null, TEST_TIMEOUT, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).connectToNetworkWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
        verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).enableNetwork(anyInt(), anyBoolean());
    }

    @Test
    public void async_failure_nullSSIDParam_nullCallback() {
        try {
            mWiseFy.connectToNetwork(null, TEST_TIMEOUT, null);
            verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).enableNetwork(anyInt(), anyBoolean());
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void async_failure_missingPrerequisites() {
        missingPrerequisite();
        ConnectToNetworkCallbacks mockCallbacks = mock(ConnectToNetworkCallbacks.class);
        mWiseFy.connectToNetwork(TEST_SSID, TEST_TIMEOUT, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).connectToNetworkWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
        verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).enableNetwork(anyInt(), anyBoolean());
    }

    @Test
    public void async_failure_missingPrerequisites_nullCallback() {
        missingPrerequisite();
        try {
            mWiseFy.connectToNetwork(TEST_SSID, TEST_TIMEOUT, null);
            verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).enableNetwork(anyInt(), anyBoolean());
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void async_failure_noSavedNetwork() {
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findSavedNetworkByRegex(anyString())).thenReturn(null);

        ConnectToNetworkCallbacks mockCallbacks = mock(ConnectToNetworkCallbacks.class);
        mWiseFy.connectToNetwork(TEST_SSID, TEST_TIMEOUT, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).networkNotFoundToConnectTo();
        verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).enableNetwork(anyInt(), anyBoolean());
    }

    @Test
    public void async_failure_noSavedNetwork_nullCallback() {
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findSavedNetworkByRegex(anyString())).thenReturn(null);

        try {
            mWiseFy.connectToNetwork(TEST_SSID, TEST_TIMEOUT, null);
            verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).enableNetwork(anyInt(), anyBoolean());
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

        WiseFyConnection mockWiseFyConnection = mock(WiseFyConnection.class);
        mWiseFy.mWiseFyConnection = mockWiseFyConnection;
        when(mockWiseFyConnection.waitToConnectToSSID(anyString(), anyInt())).thenReturn(false);

        ConnectToNetworkCallbacks mockCallbacks = mock(ConnectToNetworkCallbacks.class);
        mWiseFy.connectToNetwork(TEST_SSID, TEST_TIMEOUT, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).failureConnectingToNetwork();
        verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).enableNetwork(anyInt(), anyBoolean());
    }

    @Test
    public void async_failure_nullCallback() {
        WifiConfiguration wiFiConfiguration = new WifiConfiguration();
        wiFiConfiguration.SSID = TEST_SSID;
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findSavedNetworkByRegex(anyString())).thenReturn(wiFiConfiguration);

        WiseFyConnection mockWiseFyConnection = mock(WiseFyConnection.class);
        mWiseFy.mWiseFyConnection = mockWiseFyConnection;
        when(mockWiseFyConnection.waitToConnectToSSID(anyString(), anyInt())).thenReturn(false);

        try {
            mWiseFy.connectToNetwork(TEST_SSID, TEST_TIMEOUT, null);
            verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).enableNetwork(anyInt(), anyBoolean());
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

        WiseFyConnection mockWiseFyConnection = mock(WiseFyConnection.class);
        mWiseFy.mWiseFyConnection = mockWiseFyConnection;
        when(mockWiseFyConnection.waitToConnectToSSID(anyString(), anyInt())).thenReturn(true);

        ConnectToNetworkCallbacks mockCallbacks = mock(ConnectToNetworkCallbacks.class);
        mWiseFy.connectToNetwork(TEST_SSID, TEST_TIMEOUT, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).connectedToNetwork();
        verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).enableNetwork(anyInt(), anyBoolean());
    }

    @Test
    public void async_success_nullCallback() {
        WifiConfiguration wiFiConfiguration = new WifiConfiguration();
        wiFiConfiguration.SSID = TEST_SSID;
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findSavedNetworkByRegex(anyString())).thenReturn(wiFiConfiguration);

        WiseFyConnection mockWiseFyConnection = mock(WiseFyConnection.class);
        mWiseFy.mWiseFyConnection = mockWiseFyConnection;
        when(mockWiseFyConnection.waitToConnectToSSID(anyString(), anyInt())).thenReturn(true);

        try {
            mWiseFy.connectToNetwork(TEST_SSID, TEST_TIMEOUT, null);
            verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).enableNetwork(anyInt(), anyBoolean());
        } catch (NullPointerException npe) {
            fail();
        }
    }
}