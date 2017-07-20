package com.isupatches.wisefy;


import android.net.wifi.WifiConfiguration;
import com.isupatches.wisefy.base.TestActivity;
import com.isupatches.wisefy.callbacks.AddWEPNetworkCallbacks;
import com.isupatches.wisefy.constants.WiseFyCodes;
import org.junit.Test;
import static com.isupatches.wisefy.base.TestUtils.WEP_NETWORK_PASSWORD;
import static com.isupatches.wisefy.base.TestUtils.WEP_NETWORK_SSID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class AddWEPNetworkTests extends BaseTestClass<TestActivity> {

    public AddWEPNetworkTests() {
        super(TestActivity.class);
    }

    @Test
    public void noCallbacks_failure() {
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(WiseFy.WIFI_MANAGER_FAILURE);
        assertEquals(WiseFy.WIFI_MANAGER_FAILURE, mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD));
        verify(mMockWiFiManager, timeout(VERIFICATION_TIMEOUT)).addNetwork(any(WifiConfiguration.class));
    }

    @Test
    public void noCallbacks_failure_nullPasswordParam() {
        assertEquals(WiseFyCodes.MISSING_PARAMETER, mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, null));
        verify(mMockWiFiManager, never()).addNetwork(any(WifiConfiguration.class));
    }

    @Test
    public void noCallbacks_failure_nullSSIDParam() {
        assertEquals(WiseFyCodes.MISSING_PARAMETER, mWiseFy.addWEPNetwork(null, WEP_NETWORK_PASSWORD));
        verify(mMockWiFiManager, never()).addNetwork(any(WifiConfiguration.class));
    }

    @Test
    public void noCallbacks_failure_missingPrerequisite() {
        missingPrerequisite();
        assertEquals(WiseFyCodes.MISSING_PREREQUISITE, mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD));
        verify(mMockWiFiManager, never()).addNetwork(any(WifiConfiguration.class));
    }

    @Test
    public void noCallbacks_success() {
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(0);
        assertNotEquals(WiseFy.WIFI_MANAGER_FAILURE, mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD));
        verify(mMockWiFiManager, timeout(VERIFICATION_TIMEOUT)).addNetwork(any(WifiConfiguration.class));
    }

    @Test
    public void noCallbacks_success_alreadyConfigured() {
        networkAlreadyInConfigurationList();
        assertEquals(WiseFyCodes.NETWORK_ALREADY_CONFIGURED, mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD));
        verify(mMockWiFiManager, never()).addNetwork(any(WifiConfiguration.class));
    }

    @Test
    public void callbacks_failure() {
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(WiseFy.WIFI_MANAGER_FAILURE);
        AddWEPNetworkCallbacks mockCallbacks = mock(AddWEPNetworkCallbacks.class);
        mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_TIMEOUT)).failureAddingWEPNetwork(WiseFy.WIFI_MANAGER_FAILURE);
        verify(mMockWiFiManager, timeout(VERIFICATION_TIMEOUT)).addNetwork(any(WifiConfiguration.class));
    }

    @Test
    public void callbacks_failure_nullCallback() {
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(WiseFy.WIFI_MANAGER_FAILURE);

        try {
            mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD, null);
            verify(mMockWiFiManager, never()).addNetwork(any(WifiConfiguration.class));
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void callbacks_failure_nullPasswordParam() {
        AddWEPNetworkCallbacks mockCallbacks = mock(AddWEPNetworkCallbacks.class);
        mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, null, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_TIMEOUT)).addWEPNetworkWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
        verify(mMockWiFiManager, never()).addNetwork(any(WifiConfiguration.class));
    }

    @Test
    public void callbacks_failure_nullPasswordParam_nullCallback() {
        try {
            mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, null, null);
            verify(mMockWiFiManager, never()).addNetwork(any(WifiConfiguration.class));
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void callbacks_failure_nullSSIDParam() {
        AddWEPNetworkCallbacks mockCallbacks = mock(AddWEPNetworkCallbacks.class);
        mWiseFy.addWEPNetwork(null, WEP_NETWORK_PASSWORD, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_TIMEOUT)).addWEPNetworkWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
        verify(mMockWiFiManager, never()).addNetwork(any(WifiConfiguration.class));
    }

    @Test
    public void callbacks_failure_nullSSIDParam_nullCallback() {
        try {
            mWiseFy.addWEPNetwork(null, WEP_NETWORK_PASSWORD, null);
            verify(mMockWiFiManager, never()).addNetwork(any(WifiConfiguration.class));
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void callbacks_failure_missingPrerequisite() {
        missingPrerequisite();
        AddWEPNetworkCallbacks mockCallbacks = mock(AddWEPNetworkCallbacks.class);
        mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_TIMEOUT)).addWEPNetworkWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
        verify(mMockWiFiManager, never()).addNetwork(any(WifiConfiguration.class));
    }

    @Test
    public void callbacks_failure_missingPrerequisite_nullCallback() {
        missingPrerequisite();

        try {
            mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD, null);
            verify(mMockWiFiManager, never()).addNetwork(any(WifiConfiguration.class));
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void callbacks_success() {
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(0);
        AddWEPNetworkCallbacks mockCallbacks = mock(AddWEPNetworkCallbacks.class);
        mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_TIMEOUT)).wepNetworkAdded(any(WifiConfiguration.class));
        verify(mMockWiFiManager, timeout(VERIFICATION_TIMEOUT)).addNetwork(any(WifiConfiguration.class));
    }

    @Test
    public void callbacks_success_nullCallback() {
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(0);

        try {
            mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD, null);
            verify(mMockWiFiManager, never()).addNetwork(any(WifiConfiguration.class));
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void callbacks_success_alreadyConfigured() {
        networkAlreadyInConfigurationList();

        AddWEPNetworkCallbacks mockCallbacks = mock(AddWEPNetworkCallbacks.class);
        mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD, mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_TIMEOUT)).addWEPNetworkWiseFyFailure(WiseFyCodes.NETWORK_ALREADY_CONFIGURED);
        verify(mMockWiFiManager, never()).addNetwork(any(WifiConfiguration.class));
    }

    @Test
    public void callbacks_success_alreadyConfigured_nullCallback() {
        networkAlreadyInConfigurationList();

        try {
            mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD, null);
            verify(mMockWiFiManager, never()).addNetwork(any(WifiConfiguration.class));
        } catch (NullPointerException npe) {
            fail();
        }
    }
}
