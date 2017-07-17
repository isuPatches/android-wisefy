package com.isupatches.wisefy;


import android.net.wifi.WifiConfiguration;
import com.isupatches.wisefy.base.TestActivity;
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


public class GetSavedNetworkTests extends BaseTestClass<TestActivity> {

    public GetSavedNetworkTests() {
        super(TestActivity.class);
    }

    @Test
    public void noCallbacks_failure_nullSSIDParam() {
        assertEquals(null, mWiseFy.getSavedNetwork(null));
    }

    @Test
    public void noCallbacks_failure_missingPrerequisite() {
        missingPrerequisite();
        assertEquals(null, mWiseFy.getSavedNetwork(TEST_SSID));
    }

    @Test
    public void noCallbacks_failure() {
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findSavedNetworkByRegex(anyString())).thenReturn(null);

        assertEquals(null, mWiseFy.getSavedNetwork(TEST_SSID));
    }

    @Test
    public void noCallbacks_success() {
        WifiConfiguration wiFiConfiguration = new WifiConfiguration();
        wiFiConfiguration.SSID = TEST_SSID;

        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findSavedNetworkByRegex(anyString())).thenReturn(wiFiConfiguration);

        assertEquals(wiFiConfiguration, mWiseFy.getSavedNetwork(TEST_SSID));
    }

    @Test
    public void callbacks_failure_nullSSIDParam() {
        GetSavedNetworkCallbacks mockCallbacks = mock(GetSavedNetworkCallbacks.class);
        mWiseFy.getSavedNetwork(null, mockCallbacks);
        verify(mockCallbacks, timeout(2000)).getSavedNetworkWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
    }

    @Test
    public void callbacks_failure_nullSSIDParam_nullCallback() {
        try {
            mWiseFy.getSavedNetwork(null, null);
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void callbacks_failure_missingPrerequisite() {
        missingPrerequisite();
        GetSavedNetworkCallbacks mockCallbacks = mock(GetSavedNetworkCallbacks.class);
        mWiseFy.getSavedNetwork(TEST_SSID, mockCallbacks);
        verify(mockCallbacks, timeout(2000)).getSavedNetworkWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
    }

    @Test
    public void callbacks_failure_missingPrerequisite_nullCallback() {
        missingPrerequisite();
        try {
            mWiseFy.getSavedNetwork(TEST_SSID, null);
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void callbacks_failure() {
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findSavedNetworkByRegex(anyString())).thenReturn(null);

        GetSavedNetworkCallbacks mockCallbacks = mock(GetSavedNetworkCallbacks.class);
        mWiseFy.getSavedNetwork(TEST_SSID, mockCallbacks);
        verify(mockCallbacks, timeout(2000)).savedNetworkNotFound();
    }

    @Test
    public void callbacks_failure_nullCallback() {
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
    public void callbacks_success() {
        WifiConfiguration wiFiConfiguration = new WifiConfiguration();
        wiFiConfiguration.SSID = TEST_SSID;

        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findSavedNetworkByRegex(anyString())).thenReturn(wiFiConfiguration);

        GetSavedNetworkCallbacks mockCallbacks = mock(GetSavedNetworkCallbacks.class);
        mWiseFy.getSavedNetwork(TEST_SSID, mockCallbacks);
        verify(mockCallbacks, timeout(2000)).retrievedSavedNetwork(wiFiConfiguration);
    }

    @Test
    public void callbacks_success_nullCallback() {
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
