package com.isupatches.wisefy;


import android.net.wifi.WifiConfiguration;
import com.isupatches.wisefy.callbacks.AddWPA2NetworkCallbacks;
import com.isupatches.wisefy.constants.WiseFyCodes;
import org.junit.Test;
import static com.isupatches.wisefy.base.TestUtils.*;
import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.after;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class AddWPA2NetworkTests extends BaseAndroidJUnit4TestClass {

    @Test
    public void sync_failure() {
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(WiseFy.WIFI_MANAGER_FAILURE);
        assertEquals(WiseFy.WIFI_MANAGER_FAILURE, mWiseFy.addWPA2Network(WPA2_NETWORK_SSID, WPA2_NETWORK_PASSWORD));
        verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).addNetwork(any(WifiConfiguration.class));
    }

    @Test
    public void sync_failure_nullPasswordParam() {
        assertEquals(WiseFyCodes.MISSING_PARAMETER, mWiseFy.addWPA2Network(WPA2_NETWORK_SSID, null));
        verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).addNetwork(any(WifiConfiguration.class));
    }

    @Test
    public void sync_failure_nullSSIDParam() {
        assertEquals(WiseFyCodes.MISSING_PARAMETER, mWiseFy.addWPA2Network(null, WPA2_NETWORK_PASSWORD));
        verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).addNetwork(any(WifiConfiguration.class));
    }

    @Test
    public void sync_failure_missingPrerequisite() {
        missingPrerequisite();
        assertEquals(WiseFyCodes.MISSING_PREREQUISITE, mWiseFy.addWPA2Network(WPA2_NETWORK_SSID, WPA2_NETWORK_PASSWORD));
        verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).addNetwork(any(WifiConfiguration.class));
    }

    @Test
    public void sync_success() {
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(0);
        assertNotEquals(WiseFy.WIFI_MANAGER_FAILURE, mWiseFy.addWPA2Network(WPA2_NETWORK_SSID, WPA2_NETWORK_PASSWORD));
        verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).addNetwork(any(WifiConfiguration.class));
    }

    @Test
    public void sync_success_alreadyConfigured() {
        networkAlreadyInConfigurationList();
        assertEquals(WiseFyCodes.NETWORK_ALREADY_CONFIGURED, mWiseFy.addWPA2Network(WPA2_NETWORK_SSID, WPA2_NETWORK_PASSWORD));
        verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).addNetwork(any(WifiConfiguration.class));
    }

    @Test
    public void async_failure() {
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(WiseFy.WIFI_MANAGER_FAILURE);
        AddWPA2NetworkCallbacks mockCallbacks = mock(AddWPA2NetworkCallbacks.class);
        mWiseFy.addWPA2Network(WPA2_NETWORK_SSID, WPA2_NETWORK_PASSWORD, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).failureAddingWPA2Network(WiseFy.WIFI_MANAGER_FAILURE);
        verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).addNetwork(any(WifiConfiguration.class));
    }

    @Test
    public void async_failure_nullCallback() {
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(WiseFy.WIFI_MANAGER_FAILURE);
        try {
            mWiseFy.addWPA2Network(WPA2_NETWORK_SSID, WPA2_NETWORK_PASSWORD, null);
            verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).addNetwork(any(WifiConfiguration.class));
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void async_failure_nullPasswordParam() {
        AddWPA2NetworkCallbacks mockCallbacks = mock(AddWPA2NetworkCallbacks.class);
        mWiseFy.addWPA2Network(WPA2_NETWORK_SSID, null, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).addWPA2NetworkWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
        verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).addNetwork(any(WifiConfiguration.class));
    }

    @Test
    public void async_failure_nullPasswordParam_nullCallback() {
        try {
            mWiseFy.addWPA2Network(WPA2_NETWORK_SSID, null, null);
            verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).addNetwork(any(WifiConfiguration.class));
        } catch(NullPointerException npe){
            fail();
        }
    }

    @Test
    public void async_failure_nullSSIDParam() {
        AddWPA2NetworkCallbacks mockCallbacks = mock(AddWPA2NetworkCallbacks.class);
        mWiseFy.addWPA2Network(null, WPA2_NETWORK_PASSWORD, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).addWPA2NetworkWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
    }

    @Test
    public void async_failure_nullSSIDParam_nullCallback() {
        try {
            mWiseFy.addWPA2Network(null, WPA2_NETWORK_PASSWORD, null);
            verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).addNetwork(any(WifiConfiguration.class));
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void async_failure_missingPrerequisite() {
        missingPrerequisite();
        AddWPA2NetworkCallbacks mockCallbacks = mock(AddWPA2NetworkCallbacks.class);
        mWiseFy.addWPA2Network(WPA2_NETWORK_SSID, WPA2_NETWORK_PASSWORD, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).addWPA2NetworkWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
        verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).addNetwork(any(WifiConfiguration.class));
    }

    @Test
    public void async_failure_missingPrerequisite_nullCallback() {
        missingPrerequisite();
        try {
            mWiseFy.addWPA2Network(WPA2_NETWORK_SSID, WPA2_NETWORK_PASSWORD, null);
            verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).addNetwork(any(WifiConfiguration.class));
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void async_success() {
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(0);
        AddWPA2NetworkCallbacks mockCallbacks = mock(AddWPA2NetworkCallbacks.class);
        mWiseFy.addWPA2Network(WPA2_NETWORK_SSID, WPA2_NETWORK_PASSWORD, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).wpa2NetworkAdded(any(WifiConfiguration.class));
        verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).addNetwork(any(WifiConfiguration.class));
    }

    @Test
    public void async_success_nullCallback() {
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(0);
        try {
            mWiseFy.addWPA2Network(WPA2_NETWORK_SSID, WPA2_NETWORK_PASSWORD, null);
            verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).addNetwork(any(WifiConfiguration.class));
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void async_success_alreadyConfigured() {
        networkAlreadyInConfigurationList();
        AddWPA2NetworkCallbacks mockCallbacks = mock(AddWPA2NetworkCallbacks.class);
        mWiseFy.addWPA2Network(WPA2_NETWORK_SSID, WPA2_NETWORK_PASSWORD, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).addWPA2NetworkWiseFyFailure(WiseFyCodes.NETWORK_ALREADY_CONFIGURED);
        verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).addNetwork(any(WifiConfiguration.class));
    }

    @Test
    public void async_success_alreadyConfigured_nullCallback() {
        networkAlreadyInConfigurationList();
        try {
            mWiseFy.addWPA2Network(WPA2_NETWORK_SSID, WPA2_NETWORK_PASSWORD, null);
            verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).addNetwork(any(WifiConfiguration.class));
        } catch (NullPointerException npe) {
            fail();
        }
    }
}
