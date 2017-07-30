package com.isupatches.wisefy;


import android.net.wifi.WifiConfiguration;
import com.isupatches.wisefy.callbacks.GetSavedNetworkCallbacks;
import com.isupatches.wisefy.constants.WiseFyCodes;
import org.junit.Test;
import static com.isupatches.wisefy.base.TestUtils.TEST_SSID;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class GetSavedNetworkTests extends BaseAndroidJUnit4TestClass {

    @Test
    public void sync_failure_nullSSIDParam() {
        assertEquals(null, mWiseFy.getSavedNetwork(null));
    }

    @Test
    public void sync_failure_missingPrerequisite() {
        missingPrerequisite();
        assertEquals(null, mWiseFy.getSavedNetwork(TEST_SSID));
    }

    @Test
    public void sync_failure() {
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findSavedNetworkByRegex(anyString())).thenReturn(null);

        assertEquals(null, mWiseFy.getSavedNetwork(TEST_SSID));
    }

    @Test
    public void sync_success() {
        WifiConfiguration wiFiConfiguration = new WifiConfiguration();
        wiFiConfiguration.SSID = TEST_SSID;

        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findSavedNetworkByRegex(anyString())).thenReturn(wiFiConfiguration);

        assertEquals(wiFiConfiguration, mWiseFy.getSavedNetwork(TEST_SSID));
    }

    @Test
    public void async_failure_nullSSIDParam() {
        GetSavedNetworkCallbacks mockCallbacks = mock(GetSavedNetworkCallbacks.class);
        mWiseFy.getSavedNetwork(null, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).getSavedNetworkWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
    }

    @Test
    public void async_failure_nullSSIDParam_nullCallback() {
        try {
            mWiseFy.getSavedNetwork(null, null);
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void async_failure_missingPrerequisite() {
        missingPrerequisite();
        GetSavedNetworkCallbacks mockCallbacks = mock(GetSavedNetworkCallbacks.class);
        mWiseFy.getSavedNetwork(TEST_SSID, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).getSavedNetworkWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
    }

    @Test
    public void async_failure_missingPrerequisite_nullCallback() {
        missingPrerequisite();
        try {
            mWiseFy.getSavedNetwork(TEST_SSID, null);
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void async_failure() {
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findSavedNetworkByRegex(anyString())).thenReturn(null);

        GetSavedNetworkCallbacks mockCallbacks = mock(GetSavedNetworkCallbacks.class);
        mWiseFy.getSavedNetwork(TEST_SSID, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).savedNetworkNotFound();
    }

    @Test
    public void async_failure_nullCallback() {
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findSavedNetworkByRegex(anyString())).thenReturn(null);

        try {
            mWiseFy.getSavedNetwork(TEST_SSID, null);
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

        GetSavedNetworkCallbacks mockCallbacks = mock(GetSavedNetworkCallbacks.class);
        mWiseFy.getSavedNetwork(TEST_SSID, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).retrievedSavedNetwork(wiFiConfiguration);
    }

    @Test
    public void async_success_nullCallback() {
        WifiConfiguration wiFiConfiguration = new WifiConfiguration();
        wiFiConfiguration.SSID = TEST_SSID;

        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findSavedNetworkByRegex(anyString())).thenReturn(wiFiConfiguration);

        try {
            mWiseFy.getSavedNetwork(TEST_SSID, null);
        } catch (NullPointerException npe) {
            fail();
        }
    }
}
