package com.isupatches.wisefy;


import android.net.wifi.WifiConfiguration;
import com.isupatches.wisefy.base.TestActivity;
import com.isupatches.wisefy.callbacks.RemoveNetworkCallbacks;
import com.isupatches.wisefy.constants.WiseFyCodes;
import org.junit.Test;
import static com.isupatches.wisefy.base.TestUtils.TEST_SSID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class RemoveNetworkTests extends BaseTestClass<TestActivity> {

    public RemoveNetworkTests() {
        super(TestActivity.class);
    }

    @Test
    public void noCallbacks_failure_nullSSIDParam() {
        boolean result = mWiseFy.removeNetwork(null);
        assertEquals(false, result);
    }

    @Test
    public void noCallbacks_failure_missingPrerequisites() {
        missingPrerequisite();
        boolean result = mWiseFy.removeNetwork(TEST_SSID);
        assertEquals(false, result);
    }

    @Test
    public void noCallbacks_failure_noSavedNetwork() {
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findSavedNetworkByRegex(anyString())).thenReturn(null);

        boolean result = mWiseFy.removeNetwork(TEST_SSID);
        assertEquals(false, result);
    }

    @Test
    public void noCallbacks_failure() {
        WifiConfiguration wiFiConfiguration = new WifiConfiguration();
        wiFiConfiguration.SSID = TEST_SSID;
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findSavedNetworkByRegex(anyString())).thenReturn(wiFiConfiguration);

        when(mMockWiFiManager.removeNetwork(anyInt())).thenReturn(false);

        boolean result = mWiseFy.removeNetwork(TEST_SSID);
        assertEquals(false, result);
    }

    @Test
    public void noCallbacks_success() {
        WifiConfiguration wiFiConfiguration = new WifiConfiguration();
        wiFiConfiguration.SSID = TEST_SSID;
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findSavedNetworkByRegex(anyString())).thenReturn(wiFiConfiguration);

        when(mMockWiFiManager.removeNetwork(anyInt())).thenReturn(true);

        boolean result = mWiseFy.removeNetwork(TEST_SSID);
        assertEquals(true, result);
    }

    @Test
    public void callbacks_failure_nullSSIDParam() {
        RemoveNetworkCallbacks mockCallbacks = mock(RemoveNetworkCallbacks.class);
        mWiseFy.removeNetwork(null, mockCallbacks);
        verify(mockCallbacks, timeout(3000)).removeNetworkWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
    }

    @Test
    public void callbacks_failure_nullSSIDParam_nullCallback() {
        try {
            mWiseFy.removeNetwork(null, null);
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void callbacks_failure_missingPrerequisites() {
        missingPrerequisite();
        RemoveNetworkCallbacks mockCallbacks = mock(RemoveNetworkCallbacks.class);
        mWiseFy.removeNetwork(TEST_SSID, mockCallbacks);
        verify(mockCallbacks, timeout(3000)).removeNetworkWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
    }

    @Test
    public void callbacks_failure_missingPrerequisites_nullCallback() {
        missingPrerequisite();
        try {
            mWiseFy.removeNetwork(TEST_SSID, null);
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void callbacks_failure_noSavedNetwork() {
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findSavedNetworkByRegex(anyString())).thenReturn(null);

        RemoveNetworkCallbacks mockCallbacks = mock(RemoveNetworkCallbacks.class);
        mWiseFy.removeNetwork(TEST_SSID, mockCallbacks);
        verify(mockCallbacks, timeout(3000)).networkNotFoundToRemove();
    }

    @Test
    public void callbacks_failure_noSavedNetwork_nullCallback() {
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findSavedNetworkByRegex(anyString())).thenReturn(null);

        try {
            mWiseFy.removeNetwork(TEST_SSID, null);
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

        when(mMockWiFiManager.removeNetwork(anyInt())).thenReturn(false);

        RemoveNetworkCallbacks mockCallbacks = mock(RemoveNetworkCallbacks.class);
        mWiseFy.removeNetwork(TEST_SSID, mockCallbacks);
        verify(mockCallbacks, timeout(3000)).failureRemovingNetwork();
    }

    @Test
    public void callbacks_failure_nullCallback() {
        WifiConfiguration wiFiConfiguration = new WifiConfiguration();
        wiFiConfiguration.SSID = TEST_SSID;
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findSavedNetworkByRegex(anyString())).thenReturn(wiFiConfiguration);

        when(mMockWiFiManager.removeNetwork(anyInt())).thenReturn(false);

        try {
            mWiseFy.removeNetwork(TEST_SSID, null);
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

        when(mMockWiFiManager.removeNetwork(anyInt())).thenReturn(true);

        RemoveNetworkCallbacks mockCallbacks = mock(RemoveNetworkCallbacks.class);
        mWiseFy.removeNetwork(TEST_SSID, mockCallbacks);
        verify(mockCallbacks, timeout(3000)).networkRemoved();
    }

    @Test
    public void callbacks_success_nullCallback() {
        WifiConfiguration wiFiConfiguration = new WifiConfiguration();
        wiFiConfiguration.SSID = TEST_SSID;
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findSavedNetworkByRegex(anyString())).thenReturn(wiFiConfiguration);

        when(mMockWiFiManager.removeNetwork(anyInt())).thenReturn(true);

        try {
            mWiseFy.removeNetwork(TEST_SSID, null);
        } catch (NullPointerException npe) {
            fail();
        }
    }
}
