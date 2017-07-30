package com.isupatches.wisefy;


import android.net.wifi.WifiConfiguration;
import com.isupatches.wisefy.callbacks.GetSavedNetworksCallbacks;
import com.isupatches.wisefy.constants.WiseFyCodes;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static com.isupatches.wisefy.base.TestUtils.TEST_SSID;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.after;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class GetSavedNetworksTests extends BaseAndroidJUnit4TestClass {

    @Test
    public void sync_failure_missingPrerequisite() {
        missingPrerequisite();
        assertEquals(null, mWiseFy.getSavedNetworks());
        verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).getConfiguredNetworks();
    }

    @Test
    public void sync_success() {
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = TEST_SSID;
        wifiList.add(mWiFiConfiguration);
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);

        assertEquals(wifiList, mWiseFy.getSavedNetworks());
        verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).getConfiguredNetworks();
    }

    @Test
    public void async_failure_missingPrerequisite() {
        missingPrerequisite();
        GetSavedNetworksCallbacks mockCallbacks = mock(GetSavedNetworksCallbacks.class);
        mWiseFy.getSavedNetworks(mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).getSavedNetworksWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
        verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).getConfiguredNetworks();
    }

    @Test
    public void async_failure_missingPrerequisite_nullCallback() {
        missingPrerequisite();
        try {
            mWiseFy.getSavedNetworks((GetSavedNetworksCallbacks) null);
            verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).getConfiguredNetworks();
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void async_failure_nullSavedNetworks() {
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(null);
        GetSavedNetworksCallbacks mockCallbacks = mock(GetSavedNetworksCallbacks.class);
        mWiseFy.getSavedNetworks(mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).noSavedNetworksFound();
    }

    @Test
    public void async_failure_nullSavedNetworks_nullCallback() {
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(null);
        try {
            mWiseFy.getSavedNetworks((GetSavedNetworksCallbacks) null);
            verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).getConfiguredNetworks();
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void async_failure_emptyConfiguredNetworks() {
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(new ArrayList<WifiConfiguration>());
        GetSavedNetworksCallbacks mockCallbacks = mock(GetSavedNetworksCallbacks.class);
        mWiseFy.getSavedNetworks(mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).noSavedNetworksFound();
    }

    @Test
    public void async_failure_emptyConfiguredNetworks_nullCallback() {
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(new ArrayList<WifiConfiguration>());
        try {
            mWiseFy.getSavedNetworks((GetSavedNetworksCallbacks) null);
            verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).getConfiguredNetworks();
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void async_success() {
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = TEST_SSID;
        wifiList.add(mWiFiConfiguration);
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);

        GetSavedNetworksCallbacks mockCallbacks = mock(GetSavedNetworksCallbacks.class);
        mWiseFy.getSavedNetworks(mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).retrievedSavedNetworks(wifiList);
        verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).getConfiguredNetworks();
    }

    @Test
    public void async_success_nullCallback() {
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = TEST_SSID;
        wifiList.add(mWiFiConfiguration);
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);

        try {
            mWiseFy.getSavedNetworks((GetSavedNetworksCallbacks) null);
            verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).getConfiguredNetworks();
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void sync_failure_withRegex_missingPrerequisite() {
        missingPrerequisite();
        assertEquals(null, mWiseFy.getSavedNetworks(TEST_SSID));
        verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).getConfiguredNetworks();
    }

    @Test
    public void sync_success_withRegex() {
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = TEST_SSID;
        wifiList.add(mWiFiConfiguration);
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);

        assertEquals(wifiList, mWiseFy.getSavedNetworks(TEST_SSID));
        verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).getConfiguredNetworks();
    }

    @Test
    public void async_failure_withRegex_missingPrerequisite() {
        missingPrerequisite();

        GetSavedNetworksCallbacks mockCallbacks = mock(GetSavedNetworksCallbacks.class);
        mWiseFy.getSavedNetworks(TEST_SSID, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).getSavedNetworksWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
        verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).getConfiguredNetworks();
    }

    @Test
    public void async_failure_withRegex_missingPrerequisite_nullCallback() {
        missingPrerequisite();

        try{
            mWiseFy.getSavedNetworks(TEST_SSID, null);
            verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).getConfiguredNetworks();
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void async_failure_withRegex_nullSavedNetworks() {
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findSavedNetworksMatchingRegex(anyString())).thenReturn(null);
        GetSavedNetworksCallbacks mockCallbacks = mock(GetSavedNetworksCallbacks.class);
        mWiseFy.getSavedNetworks(TEST_SSID, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).noSavedNetworksFound();
    }

    @Test
    public void async_failure_withRegex_nullSavedNetworks_nullCallback() {
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findSavedNetworksMatchingRegex(anyString())).thenReturn(null);
        try {
            mWiseFy.getSavedNetworks(TEST_SSID, null);
            verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).getConfiguredNetworks();
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void async_failure_withRegex_emptyConfiguredNetworks() {
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findSavedNetworksMatchingRegex(anyString())).thenReturn(new ArrayList<WifiConfiguration>());
        GetSavedNetworksCallbacks mockCallbacks = mock(GetSavedNetworksCallbacks.class);
        mWiseFy.getSavedNetworks(TEST_SSID, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).noSavedNetworksFound();
    }

    @Test
    public void async_failure_withRegex_emptyConfiguredNetworks_nullCallback() {
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findSavedNetworksMatchingRegex(anyString())).thenReturn(new ArrayList<WifiConfiguration>());
        try {
            mWiseFy.getSavedNetworks(TEST_SSID, null);
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void async_success_withRegex() {
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = TEST_SSID;
        wifiList.add(mWiFiConfiguration);

        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findSavedNetworksMatchingRegex(anyString())).thenReturn(wifiList);

        GetSavedNetworksCallbacks mockCallbacks = mock(GetSavedNetworksCallbacks.class);
        mWiseFy.getSavedNetworks(TEST_SSID, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).retrievedSavedNetworks(wifiList);
    }

    @Test
    public void async_success_withRegex_nullCallbacks() {
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = TEST_SSID;
        wifiList.add(mWiFiConfiguration);

        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.findSavedNetworksMatchingRegex(anyString())).thenReturn(wifiList);

        try {
            mWiseFy.getSavedNetworks(TEST_SSID, null);
            verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).getConfiguredNetworks();
        } catch (NullPointerException npe) {
            fail();
        }
    }
}
