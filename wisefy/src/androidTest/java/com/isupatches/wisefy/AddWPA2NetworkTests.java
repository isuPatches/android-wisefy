package com.isupatches.wisefy;


import android.net.wifi.WifiConfiguration;
import com.isupatches.wisefy.base.TestUtils;
import com.isupatches.wisefy.callbacks.AddWPA2NetworkCallbacks;
import com.isupatches.wisefy.constants.WiseFyCodes;
import org.junit.Test;
import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class AddWPA2NetworkTests extends BaseAndroidJUnit4TestClass {

    @Test
    public void noCallbacks_failure() {
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(WiseFy.WIFI_MANAGER_FAILURE);
        assertEquals(WiseFy.WIFI_MANAGER_FAILURE, mWiseFy.addWPA2Network(TestUtils.WPA2_NETWORK_SSID, TestUtils.WPA2_NETWORK_PASSWORD));
        verify(mMockWiFiManager, timeout(VERIFICATION_TIMEOUT)).addNetwork(any(WifiConfiguration.class));
    }

    @Test
    public void noCallbacks_failure_nullPasswordParam() {
        assertEquals(WiseFyCodes.MISSING_PARAMETER, mWiseFy.addWPA2Network(TestUtils.WPA2_NETWORK_SSID, null));
        verify(mMockWiFiManager, never()).addNetwork(any(WifiConfiguration.class));
    }

    @Test
    public void anoCallbacks_failure_nullSSIDParam() {
        assertEquals(WiseFyCodes.MISSING_PARAMETER, mWiseFy.addWPA2Network(null, TestUtils.WPA2_NETWORK_PASSWORD));
        verify(mMockWiFiManager, never()).addNetwork(any(WifiConfiguration.class));
    }

    @Test
    public void noCallbacks_failure_missingPrerequisite() {
        missingPrerequisite();
        assertEquals(WiseFyCodes.MISSING_PREREQUISITE, mWiseFy.addWPA2Network(TestUtils.WPA2_NETWORK_SSID, TestUtils.WPA2_NETWORK_PASSWORD));
        verify(mMockWiFiManager, never()).addNetwork(any(WifiConfiguration.class));
    }

    @Test
    public void noCallbacks_success() {
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(0);
        assertNotEquals(WiseFy.WIFI_MANAGER_FAILURE, mWiseFy.addWPA2Network(TestUtils.WPA2_NETWORK_SSID, TestUtils.WPA2_NETWORK_PASSWORD));
        verify(mMockWiFiManager, timeout(VERIFICATION_TIMEOUT)).addNetwork(any(WifiConfiguration.class));
    }

    @Test
    public void noCallbacks_success_alreadyConfigured() {
        networkAlreadyInConfigurationList();
        assertEquals(WiseFyCodes.NETWORK_ALREADY_CONFIGURED, mWiseFy.addWPA2Network(TestUtils.WPA2_NETWORK_SSID, TestUtils.WPA2_NETWORK_PASSWORD));
        verify(mMockWiFiManager, never()).addNetwork(any(WifiConfiguration.class));
    }

    @Test
    public void callbacks_failure() {
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(WiseFy.WIFI_MANAGER_FAILURE);
        AddWPA2NetworkCallbacks mockCallbacks = mock(AddWPA2NetworkCallbacks.class);
        mWiseFy.addWPA2Network(TestUtils.WPA2_NETWORK_SSID, TestUtils.WPA2_NETWORK_PASSWORD, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_TIMEOUT)).failureAddingWPA2Network(WiseFy.WIFI_MANAGER_FAILURE);
        verify(mMockWiFiManager, timeout(VERIFICATION_TIMEOUT)).addNetwork(any(WifiConfiguration.class));
    }

    @Test
    public void callbacks_failure_nullCallback() {
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(WiseFy.WIFI_MANAGER_FAILURE);
        try {
            mWiseFy.addWPA2Network(TestUtils.WPA2_NETWORK_SSID, TestUtils.WPA2_NETWORK_PASSWORD, null);
            verify(mMockWiFiManager, never()).addNetwork(any(WifiConfiguration.class));
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void callbacks_failure_nullPasswordParam() {
        AddWPA2NetworkCallbacks mockCallbacks = mock(AddWPA2NetworkCallbacks.class);
        mWiseFy.addWPA2Network(TestUtils.WPA2_NETWORK_SSID, null, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_TIMEOUT)).addWPA2NetworkWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
        verify(mMockWiFiManager, never()).addNetwork(any(WifiConfiguration.class));
    }

    @Test
    public void callbacks_failure_nullPasswordParam_nullCallback() {
        try {
            mWiseFy.addWPA2Network(TestUtils.WPA2_NETWORK_SSID, null, null);
            verify(mMockWiFiManager, never()).addNetwork(any(WifiConfiguration.class));
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void callbacks_failure_nullSSIDParam() {
        AddWPA2NetworkCallbacks mockCallbacks = mock(AddWPA2NetworkCallbacks.class);
        mWiseFy.addWPA2Network(null, TestUtils.WPA2_NETWORK_PASSWORD, mockCallbacks);
        verify(mockCallbacks, never()).addWPA2NetworkWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
    }

    @Test
    public void callbacks_failure_nullSSIDParam_nullCallback() {
        try {
            mWiseFy.addWPA2Network(null, TestUtils.WPA2_NETWORK_PASSWORD, null);
            verify(mMockWiFiManager, never()).addNetwork(any(WifiConfiguration.class));
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void callbacks_failure_missingPrerequisite() {
        missingPrerequisite();

        AddWPA2NetworkCallbacks mockCallbacks = mock(AddWPA2NetworkCallbacks.class);
        mWiseFy.addWPA2Network(TestUtils.WPA2_NETWORK_SSID, TestUtils.WPA2_NETWORK_PASSWORD, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_TIMEOUT)).addWPA2NetworkWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
        verify(mMockWiFiManager, never()).addNetwork(any(WifiConfiguration.class));
    }

    @Test
    public void callbacks_failure_missingPrerequisite_nullCallback() {
        missingPrerequisite();

        try {
            mWiseFy.addWPA2Network(TestUtils.WPA2_NETWORK_SSID, TestUtils.WPA2_NETWORK_PASSWORD, null);
            verify(mMockWiFiManager, never()).addNetwork(any(WifiConfiguration.class));
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void callbacks_success() {
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(0);
        AddWPA2NetworkCallbacks mockCallbacks = mock(AddWPA2NetworkCallbacks.class);
        mWiseFy.addWPA2Network(TestUtils.WPA2_NETWORK_SSID, TestUtils.WPA2_NETWORK_PASSWORD, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_TIMEOUT)).wpa2NetworkAdded(any(WifiConfiguration.class));
        verify(mMockWiFiManager, timeout(VERIFICATION_TIMEOUT)).addNetwork(any(WifiConfiguration.class));
    }

    @Test
    public void callbacks_success_nullCallback() {
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(0);

        try {
            mWiseFy.addWPA2Network(TestUtils.WPA2_NETWORK_SSID, TestUtils.WPA2_NETWORK_PASSWORD, null);
            verify(mMockWiFiManager, never()).addNetwork(any(WifiConfiguration.class));
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void callbacks_success_alreadyConfigured() {
        networkAlreadyInConfigurationList();

        AddWPA2NetworkCallbacks mockCallbacks = mock(AddWPA2NetworkCallbacks.class);
        mWiseFy.addWPA2Network(TestUtils.WPA2_NETWORK_SSID, TestUtils.WPA2_NETWORK_PASSWORD, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_TIMEOUT)).addWPA2NetworkWiseFyFailure(WiseFyCodes.NETWORK_ALREADY_CONFIGURED);
        verify(mMockWiFiManager, never()).addNetwork(any(WifiConfiguration.class));
    }

    @Test
    public void callbacks_success_alreadyConfigured_nullCallback() {
        networkAlreadyInConfigurationList();

        try {
            mWiseFy.addWPA2Network(TestUtils.WPA2_NETWORK_SSID, TestUtils.WPA2_NETWORK_PASSWORD, null);
            verify(mMockWiFiManager, never()).addNetwork(any(WifiConfiguration.class));
        } catch (NullPointerException npe) {
            fail();
        }
    }
}
