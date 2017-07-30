package com.isupatches.wisefy;


import android.net.wifi.WifiConfiguration;
import com.isupatches.wisefy.callbacks.AddWEPNetworkCallbacks;
import com.isupatches.wisefy.constants.WiseFyCodes;
import org.junit.Test;
import static com.isupatches.wisefy.base.TestUtils.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.after;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class AddWEPNetworkTests extends BaseAndroidJUnit4TestClass {

    @Test
    public void sync_failure() {
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(WiseFy.WIFI_MANAGER_FAILURE);
        assertEquals(WiseFy.WIFI_MANAGER_FAILURE, mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD));
        verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).addNetwork(any(WifiConfiguration.class));
    }

    @Test
    public void sync_failure_nullPasswordParam() {
        assertEquals(WiseFyCodes.MISSING_PARAMETER, mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, null));
        verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).addNetwork(any(WifiConfiguration.class));
    }

    @Test
    public void sync_failure_nullSSIDParam() {
        assertEquals(WiseFyCodes.MISSING_PARAMETER, mWiseFy.addWEPNetwork(null, WEP_NETWORK_PASSWORD));
        verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).addNetwork(any(WifiConfiguration.class));
    }

    @Test
    public void sync_failure_missingPrerequisite() {
        missingPrerequisite();
        assertEquals(WiseFyCodes.MISSING_PREREQUISITE, mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD));
        verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).addNetwork(any(WifiConfiguration.class));
    }

    @Test
    public void sync_success() {
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(0);
        assertNotEquals(WiseFy.WIFI_MANAGER_FAILURE, mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD));
        verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).addNetwork(any(WifiConfiguration.class));
    }

    @Test
    public void sync_success_alreadyConfigured() {
        networkAlreadyInConfigurationList();
        assertEquals(WiseFyCodes.NETWORK_ALREADY_CONFIGURED, mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD));
        verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).addNetwork(any(WifiConfiguration.class));
    }

    @Test
    public void async_failure() {
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(WiseFy.WIFI_MANAGER_FAILURE);
        AddWEPNetworkCallbacks mockCallbacks = mock(AddWEPNetworkCallbacks.class);
        mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).failureAddingWEPNetwork(WiseFy.WIFI_MANAGER_FAILURE);
        verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).addNetwork(any(WifiConfiguration.class));
    }

    @Test
    public void async_failure_nullCallback() {
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(WiseFy.WIFI_MANAGER_FAILURE);
        try {
            mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD, null);
            verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).addNetwork(any(WifiConfiguration.class));
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void async_failure_nullPasswordParam() {
        AddWEPNetworkCallbacks mockCallbacks = mock(AddWEPNetworkCallbacks.class);
        mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, null, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).addWEPNetworkWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
        verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).addNetwork(any(WifiConfiguration.class));
    }

    @Test
    public void async_failure_nullPasswordParam_nullCallback() {
        try {
            mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, null, null);
            verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).addNetwork(any(WifiConfiguration.class));
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void async_failure_nullSSIDParam() {
        AddWEPNetworkCallbacks mockCallbacks = mock(AddWEPNetworkCallbacks.class);
        mWiseFy.addWEPNetwork(null, WEP_NETWORK_PASSWORD, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).addWEPNetworkWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
        verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).addNetwork(any(WifiConfiguration.class));
    }

    @Test
    public void async_failure_nullSSIDParam_nullCallback() {
        try {
            mWiseFy.addWEPNetwork(null, WEP_NETWORK_PASSWORD, null);
            verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).addNetwork(any(WifiConfiguration.class));
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void async_failure_missingPrerequisite() {
        missingPrerequisite();
        AddWEPNetworkCallbacks mockCallbacks = mock(AddWEPNetworkCallbacks.class);
        mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).addWEPNetworkWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
        verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).addNetwork(any(WifiConfiguration.class));
    }

    @Test
    public void async_failure_missingPrerequisite_nullCallback() {
        missingPrerequisite();
        try {
            mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD, null);
            verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).addNetwork(any(WifiConfiguration.class));
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void async_success() {
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(0);
        AddWEPNetworkCallbacks mockCallbacks = mock(AddWEPNetworkCallbacks.class);
        mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).wepNetworkAdded(any(WifiConfiguration.class));
        verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).addNetwork(any(WifiConfiguration.class));
    }

    @Test
    public void async_success_nullCallback() {
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(0);
        try {
            mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD, null);
            verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).addNetwork(any(WifiConfiguration.class));
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void async_success_alreadyConfigured() {
        networkAlreadyInConfigurationList();
        AddWEPNetworkCallbacks mockCallbacks = mock(AddWEPNetworkCallbacks.class);
        mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).addWEPNetworkWiseFyFailure(WiseFyCodes.NETWORK_ALREADY_CONFIGURED);
        verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).addNetwork(any(WifiConfiguration.class));
    }

    @Test
    public void async_success_alreadyConfigured_nullCallback() {
        networkAlreadyInConfigurationList();
        try {
            mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD, null);
            verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).addNetwork(any(WifiConfiguration.class));
        } catch (NullPointerException npe) {
            fail();
        }
    }
}
