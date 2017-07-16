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
        int result = mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD);
        assertEquals(WiseFy.WIFI_MANAGER_FAILURE, result);
    }

    @Test
    public void noCallbacks_failure_nullPasswordParam() {
        int result = mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, null);
        assertEquals(WiseFyCodes.MISSING_PARAMETER, result);
    }

    @Test
    public void noCallbacks_failure_nullSSIDParam() {
        int result = mWiseFy.addWEPNetwork(null, WEP_NETWORK_PASSWORD);
        assertEquals(WiseFyCodes.MISSING_PARAMETER, result);
    }

    @Test
    public void noCallbacks_failure_missingPrerequisite() {
        missingPrerequisite();
        int result = mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD);
        assertEquals(WiseFyCodes.MISSING_PREREQUISITE, result);
    }

    @Test
    public void noCallbacks_success() {
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(0);
        int result = mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD);
        assertNotEquals(WiseFy.WIFI_MANAGER_FAILURE, result);
    }

    @Test
    public void noCallbacks_success_alreadyConfigured() {
        networkAlreadyInConfigurationList();

        int result = mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD);
        assertEquals(WiseFyCodes.NETWORK_ALREADY_CONFIGURED, result);
    }

    @Test
    public void callbacks_failure() {
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(WiseFy.WIFI_MANAGER_FAILURE);
        AddWEPNetworkCallbacks mockCallbacks = mock(AddWEPNetworkCallbacks.class);
        mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD, mockCallbacks);
        verify(mockCallbacks, timeout(3000)).failureAddingWEPNetwork(WiseFy.WIFI_MANAGER_FAILURE);
    }

    @Test
    public void callbacks_failure_nullCallback() {
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(WiseFy.WIFI_MANAGER_FAILURE);

        try {
            mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD, null);
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void callbacks_failure_nullPasswordParam() {
        AddWEPNetworkCallbacks mockCallbacks = mock(AddWEPNetworkCallbacks.class);
        mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, null, mockCallbacks);
        verify(mockCallbacks, timeout(2000)).addWEPNetworkWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
    }

    @Test
    public void callbacks_failure_nullPasswordParam_nullCallback() {
        try {
            mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, null, null);
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void callbacks_failure_nullSSIDParam() {
        AddWEPNetworkCallbacks mockCallbacks = mock(AddWEPNetworkCallbacks.class);
        mWiseFy.addWEPNetwork(null, WEP_NETWORK_PASSWORD, mockCallbacks);
        verify(mockCallbacks, timeout(2000)).addWEPNetworkWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
    }

    @Test
    public void callbacks_failure_nullSSIDParam_nullCallback() {
        try {
            mWiseFy.addWEPNetwork(null, WEP_NETWORK_PASSWORD, null);
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void callbacks_failure_missingPrerequisite() {
        missingPrerequisite();
        AddWEPNetworkCallbacks mockCallbacks = mock(AddWEPNetworkCallbacks.class);
        mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD, mockCallbacks);
        verify(mockCallbacks, timeout(2000)).addWEPNetworkWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
    }

    @Test
    public void callbacks_failure_missingPrerequisite_nullCallback() {
        missingPrerequisite();

        try {
            mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD, null);
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void callbacks_success() {
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(0);
        AddWEPNetworkCallbacks mockCallbacks = mock(AddWEPNetworkCallbacks.class);
        mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD, mockCallbacks);
        verify(mockCallbacks, timeout(2000)).wepNetworkAdded(any(WifiConfiguration.class));
    }

    @Test
    public void callbacks_success_nullCallback() {
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(0);

        try {
            mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD, null);
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void callbacks_success_alreadyConfigured() {
        networkAlreadyInConfigurationList();

        AddWEPNetworkCallbacks mockCallbacks = mock(AddWEPNetworkCallbacks.class);
        mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD, mockCallbacks);
        verify(mockCallbacks, timeout(2000)).addWEPNetworkWiseFyFailure(WiseFyCodes.NETWORK_ALREADY_CONFIGURED);
    }

    @Test
    public void callbacks_success_alreadyConfigured_nullCallback() {
        networkAlreadyInConfigurationList();

        try {
            mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD, null);
        } catch (NullPointerException npe) {
            fail();
        }
    }
}
