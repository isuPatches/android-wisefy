package com.isupatches.wisefy;


import android.net.wifi.WifiConfiguration;
import com.isupatches.wisefy.base.TestActivity;
import com.isupatches.wisefy.callbacks.ConnectToNetworkCallbacks;
import com.isupatches.wisefy.constants.WiseFyCodes;
import org.junit.Test;
import static com.isupatches.wisefy.base.TestUtils.TEST_SSID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class ConnectToNetworkTests extends BaseTestClass<TestActivity> {

    public ConnectToNetworkTests() {
        super(TestActivity.class);
    }

    @Test
    public void noCallbacks_failure_nullSSIDParam() {
        assertEquals(false, mWiseFy.connectToNetwork(null, 1));
        verify(mMockWiFiManager, never()).enableNetwork(anyInt(), anyBoolean());
    }

    @Test
    public void noCallbacks_failure_missingPrerequisites() {
        missingPrerequisite();
        assertEquals(false, mWiseFy.connectToNetwork(TEST_SSID, 1));
        verify(mMockWiFiManager, never()).enableNetwork(anyInt(), anyBoolean());
    }

    @Test
    public void noCallbacks_failure_noSavedNetwork() {
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findSavedNetworkByRegex(anyString())).thenReturn(null);

        assertEquals(false, mWiseFy.connectToNetwork(TEST_SSID, 1));
        verify(mMockWiFiManager, never()).enableNetwork(anyInt(), anyBoolean());
    }

    @Test
    public void noCallbacks_failure() {
        WifiConfiguration wiFiConfiguration = new WifiConfiguration();
        wiFiConfiguration.SSID = TEST_SSID;
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findSavedNetworkByRegex(anyString())).thenReturn(wiFiConfiguration);

        WiseFyConnection mockWiseFyConnection = mock(WiseFyConnection.class);
        mWiseFy.mWiseFyConnection = mockWiseFyConnection;
        when(mockWiseFyConnection.waitToConnectToSSID(anyString(), anyInt())).thenReturn(false);

        assertEquals(false, mWiseFy.connectToNetwork(TEST_SSID, 1));
        verify(mMockWiFiManager, timeout(VERIFICATION_TIMEOUT)).enableNetwork(anyInt(), anyBoolean());
    }

    @Test
    public void noCallbacks_success() {
        WifiConfiguration wiFiConfiguration = new WifiConfiguration();
        wiFiConfiguration.SSID = TEST_SSID;
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findSavedNetworkByRegex(anyString())).thenReturn(wiFiConfiguration);

        WiseFyConnection mockWiseFyConnection = mock(WiseFyConnection.class);
        mWiseFy.mWiseFyConnection = mockWiseFyConnection;
        when(mockWiseFyConnection.waitToConnectToSSID(anyString(), anyInt())).thenReturn(true);

        assertEquals(true, mWiseFy.connectToNetwork(TEST_SSID, 1));
        verify(mMockWiFiManager, timeout(VERIFICATION_TIMEOUT)).enableNetwork(anyInt(), anyBoolean());
    }

    @Test
    public void callbacks_failure_nullSSIDParam() {
        ConnectToNetworkCallbacks mockCallbacks = mock(ConnectToNetworkCallbacks.class);
        mWiseFy.connectToNetwork(null, 1, mockCallbacks);
        verify(mockCallbacks, timeout(3000)).connectToNetworkWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
        verify(mMockWiFiManager, never()).enableNetwork(anyInt(), anyBoolean());
    }

    @Test
    public void callbacks_failure_nullSSIDParam_nullCallback() {
        try {
            mWiseFy.connectToNetwork(null, 1, null);
            verify(mMockWiFiManager, never()).enableNetwork(anyInt(), anyBoolean());
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void callbacks_failure_missingPrerequisites() {
        missingPrerequisite();
        ConnectToNetworkCallbacks mockCallbacks = mock(ConnectToNetworkCallbacks.class);
        mWiseFy.connectToNetwork(TEST_SSID, 1, mockCallbacks);
        verify(mockCallbacks, timeout(3000)).connectToNetworkWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
        verify(mMockWiFiManager, never()).enableNetwork(anyInt(), anyBoolean());
    }

    @Test
    public void callbacks_failure_missingPrerequisites_nullCallback() {
        missingPrerequisite();
        try {
            mWiseFy.connectToNetwork(TEST_SSID, 1, null);
            verify(mMockWiFiManager, never()).enableNetwork(anyInt(), anyBoolean());
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void callbacks_failure_noSavedNetwork() {
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findSavedNetworkByRegex(anyString())).thenReturn(null);

        ConnectToNetworkCallbacks mockCallbacks = mock(ConnectToNetworkCallbacks.class);
        mWiseFy.connectToNetwork(TEST_SSID, 1, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_TIMEOUT)).networkNotFoundToConnectTo();
        verify(mMockWiFiManager, never()).enableNetwork(anyInt(), anyBoolean());
    }

    @Test
    public void callbacks_failure_noSavedNetwork_nullCallback() {
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findSavedNetworkByRegex(anyString())).thenReturn(null);

        try {
            mWiseFy.connectToNetwork(TEST_SSID, 1, null);
            verify(mMockWiFiManager, never()).enableNetwork(anyInt(), anyBoolean());
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void callbacks_failure() {
        WifiConfiguration wiFiConfiguration = new WifiConfiguration();
        wiFiConfiguration.SSID = TEST_SSID;
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findSavedNetworkByRegex(anyString())).thenReturn(wiFiConfiguration);

        WiseFyConnection mockWiseFyConnection = mock(WiseFyConnection.class);
        mWiseFy.mWiseFyConnection = mockWiseFyConnection;
        when(mockWiseFyConnection.waitToConnectToSSID(anyString(), anyInt())).thenReturn(false);

        ConnectToNetworkCallbacks mockCallbacks = mock(ConnectToNetworkCallbacks.class);
        mWiseFy.connectToNetwork(TEST_SSID, 1, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_TIMEOUT)).failureConnectingToNetwork();
        verify(mMockWiFiManager, timeout(VERIFICATION_TIMEOUT)).enableNetwork(anyInt(), anyBoolean());
    }

    @Test
    public void callbacks_failure_nullCallback() {
        WifiConfiguration wiFiConfiguration = new WifiConfiguration();
        wiFiConfiguration.SSID = TEST_SSID;
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findSavedNetworkByRegex(anyString())).thenReturn(wiFiConfiguration);

        WiseFyConnection mockWiseFyConnection = mock(WiseFyConnection.class);
        mWiseFy.mWiseFyConnection = mockWiseFyConnection;
        when(mockWiseFyConnection.waitToConnectToSSID(anyString(), anyInt())).thenReturn(false);

        try {
            mWiseFy.connectToNetwork(TEST_SSID, 1, null);
            verify(mMockWiFiManager, never()).enableNetwork(anyInt(), anyBoolean());
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void callbacks_success() {
        WifiConfiguration wiFiConfiguration = new WifiConfiguration();
        wiFiConfiguration.SSID = TEST_SSID;
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findSavedNetworkByRegex(anyString())).thenReturn(wiFiConfiguration);

        WiseFyConnection mockWiseFyConnection = mock(WiseFyConnection.class);
        mWiseFy.mWiseFyConnection = mockWiseFyConnection;
        when(mockWiseFyConnection.waitToConnectToSSID(anyString(), anyInt())).thenReturn(true);

        ConnectToNetworkCallbacks mockCallbacks = mock(ConnectToNetworkCallbacks.class);
        mWiseFy.connectToNetwork(TEST_SSID, 1, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_TIMEOUT)).connectedToNetwork();
        verify(mMockWiFiManager, timeout(VERIFICATION_TIMEOUT)).enableNetwork(anyInt(), anyBoolean());
    }

    @Test
    public void callbacks_success_nullCallback() {
        WifiConfiguration wiFiConfiguration = new WifiConfiguration();
        wiFiConfiguration.SSID = TEST_SSID;
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findSavedNetworkByRegex(anyString())).thenReturn(wiFiConfiguration);

        WiseFyConnection mockWiseFyConnection = mock(WiseFyConnection.class);
        mWiseFy.mWiseFyConnection = mockWiseFyConnection;
        when(mockWiseFyConnection.waitToConnectToSSID(anyString(), anyInt())).thenReturn(true);

        try {
            mWiseFy.connectToNetwork(TEST_SSID, 1, null);
            verify(mMockWiFiManager, never()).enableNetwork(anyInt(), anyBoolean());
        } catch (NullPointerException npe) {
            fail();
        }
    }
}