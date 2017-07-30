package com.isupatches.wisefy;


import android.net.wifi.WifiConfiguration;
import com.isupatches.wisefy.callbacks.AddOpenNetworkCallbacks;
import com.isupatches.wisefy.constants.WiseFyCodes;
import org.junit.Test;
import static com.isupatches.wisefy.base.TestUtils.OPEN_NETWORK_SSID;
import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.after;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class AddOpenNetworkTests extends BaseAndroidJUnit4TestClass {

    @Test
    public void sync_failure() {
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(WiseFy.WIFI_MANAGER_FAILURE);
        assertEquals(WiseFy.WIFI_MANAGER_FAILURE,  mWiseFy.addOpenNetwork(OPEN_NETWORK_SSID));
        verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).addNetwork(any(WifiConfiguration.class));
    }

    @Test
    public void sync_failure_nullSSIDParam() {
        assertEquals(WiseFyCodes.MISSING_PARAMETER, mWiseFy.addOpenNetwork(null));
        verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).addNetwork(any(WifiConfiguration.class));
    }

    @Test
    public void sync_failure_missingPrerequisite() {
        missingPrerequisite();
        assertEquals(WiseFyCodes.MISSING_PREREQUISITE, mWiseFy.addOpenNetwork(OPEN_NETWORK_SSID));
        verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).addNetwork(any(WifiConfiguration.class));
    }

    @Test
    public void sync_success() {
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(0);
        assertNotEquals(WiseFy.WIFI_MANAGER_FAILURE, mWiseFy.addOpenNetwork(OPEN_NETWORK_SSID));
        verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).addNetwork(any(WifiConfiguration.class));
    }

    @Test
    public void sync_success_alreadyConfigured() {
        networkAlreadyInConfigurationList();
        assertEquals(WiseFyCodes.NETWORK_ALREADY_CONFIGURED, mWiseFy.addOpenNetwork(OPEN_NETWORK_SSID));
        verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).addNetwork(any(WifiConfiguration.class));
    }

    @Test
    public void async_failure() {
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(WiseFy.WIFI_MANAGER_FAILURE);
        AddOpenNetworkCallbacks mockCallbacks = mock(AddOpenNetworkCallbacks.class);
        mWiseFy.addOpenNetwork(OPEN_NETWORK_SSID, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).failureAddingOpenNetwork(WiseFy.WIFI_MANAGER_FAILURE);
        verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).addNetwork(any(WifiConfiguration.class));
    }

    @Test
    public void async_failure_nullCallback() {
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(WiseFy.WIFI_MANAGER_FAILURE);
        try {
            mWiseFy.addOpenNetwork(OPEN_NETWORK_SSID, null);
            verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).addNetwork(any(WifiConfiguration.class));
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void async_failure_nullSSIDParam() {
        AddOpenNetworkCallbacks mockCallbacks = mock(AddOpenNetworkCallbacks.class);
        mWiseFy.addOpenNetwork(null, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).addOpenNetworkWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
        verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).addNetwork(any(WifiConfiguration.class));
    }

    @Test
    public void async_failure_nullSSIDParam_nullCallback() {
        try {
            mWiseFy.addOpenNetwork(null, null);
            verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).addNetwork(any(WifiConfiguration.class));
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void async_failure_missingPrerequisite() {
        missingPrerequisite();
        AddOpenNetworkCallbacks mockCallbacks = mock(AddOpenNetworkCallbacks.class);
        mWiseFy.addOpenNetwork(OPEN_NETWORK_SSID, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).addOpenNetworkWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
        verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).addNetwork(any(WifiConfiguration.class));
    }

    @Test
    public void async_failure_missingPrerequisite_nullCallback() {
        missingPrerequisite();
        try {
            mWiseFy.addOpenNetwork(OPEN_NETWORK_SSID, null);
            verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).addNetwork(any(WifiConfiguration.class));
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void async_success() {
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(0);
        AddOpenNetworkCallbacks mockCallbacks = mock(AddOpenNetworkCallbacks.class);
        mWiseFy.addOpenNetwork(OPEN_NETWORK_SSID, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).openNetworkAdded(any(WifiConfiguration.class));
        verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).addNetwork(any(WifiConfiguration.class));
    }

    @Test
    public void async_success_nullCallback() {
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(0);
        try {
            mWiseFy.addOpenNetwork(OPEN_NETWORK_SSID, null);
            verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).addNetwork(any(WifiConfiguration.class));
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void async_success_alreadyConfigured() {
        networkAlreadyInConfigurationList();
        AddOpenNetworkCallbacks mockCallbacks = mock(AddOpenNetworkCallbacks.class);
        mWiseFy.addOpenNetwork(OPEN_NETWORK_SSID, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).addOpenNetworkWiseFyFailure(WiseFyCodes.NETWORK_ALREADY_CONFIGURED);
        verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).addNetwork(any(WifiConfiguration.class));
    }

    @Test
    public void async_success_alreadyConfigured_nullCallback() {
        networkAlreadyInConfigurationList();
        try {
            mWiseFy.addOpenNetwork(OPEN_NETWORK_SSID, null);
            verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).addNetwork(any(WifiConfiguration.class));
        } catch (NullPointerException npe) {
            fail();
        }
    }
}