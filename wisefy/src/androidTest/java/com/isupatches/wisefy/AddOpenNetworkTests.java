//package com.isupatches.wisefy;
//
//
//import android.net.wifi.WifiConfiguration;
//import com.isupatches.wisefy.callbacks.AddOpenNetworkCallbacks;
//import com.isupatches.wisefy.constants.WiseFyCodes;
//import com.isupatches.wisefy.base.TestActivity;
//import org.junit.Test;
//import static com.isupatches.wisefy.base.TestUtils.OPEN_NETWORK_SSID;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotEquals;
//import static org.junit.Assert.fail;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.never;
//import static org.mockito.Mockito.timeout;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//
//public class AddOpenNetworkTests extends BaseTestClass<TestActivity> {
//
//    public AddOpenNetworkTests() {
//        super(TestActivity.class);
//    }
//
//    @Test
//    public void noCallbacks_failure() {
//        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(WiseFy.WIFI_MANAGER_FAILURE);
//        assertEquals(WiseFy.WIFI_MANAGER_FAILURE,  mWiseFy.addOpenNetwork(OPEN_NETWORK_SSID));
//        verify(mMockWiFiManager, timeout(VERIFICATION_TIMEOUT)).addNetwork(any(WifiConfiguration.class));
//    }
//
//    @Test
//    public void noCallbacks_failure_nullSSIDParam() {
//        assertEquals(WiseFyCodes.MISSING_PARAMETER, mWiseFy.addOpenNetwork(null));
//        verify(mMockWiFiManager, never()).addNetwork(any(WifiConfiguration.class));
//    }
//
//    @Test
//    public void noCallbacks_failure_missingPrerequisite() {
//        missingPrerequisite();
//        assertEquals(WiseFyCodes.MISSING_PREREQUISITE, mWiseFy.addOpenNetwork(OPEN_NETWORK_SSID));
//        verify(mMockWiFiManager, never()).addNetwork(any(WifiConfiguration.class));
//    }
//
//    @Test
//    public void noCallbacks_success() {
//        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(0);
//        assertNotEquals(WiseFy.WIFI_MANAGER_FAILURE, mWiseFy.addOpenNetwork(OPEN_NETWORK_SSID));
//        verify(mMockWiFiManager, timeout(VERIFICATION_TIMEOUT)).addNetwork(any(WifiConfiguration.class));
//    }
//
//    @Test
//    public void noCallbacks_success_alreadyConfigured() {
//        networkAlreadyInConfigurationList();
//        assertEquals(WiseFyCodes.NETWORK_ALREADY_CONFIGURED, mWiseFy.addOpenNetwork(OPEN_NETWORK_SSID));
//        verify(mMockWiFiManager, never()).addNetwork(any(WifiConfiguration.class));
//    }
//
//    @Test
//    public void callbacks_failure() {
//        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(WiseFy.WIFI_MANAGER_FAILURE);
//        AddOpenNetworkCallbacks mockCallbacks = mock(AddOpenNetworkCallbacks.class);
//        mWiseFy.addOpenNetwork(OPEN_NETWORK_SSID, mockCallbacks);
//        verify(mockCallbacks, timeout(VERIFICATION_TIMEOUT)).failureAddingOpenNetwork(WiseFy.WIFI_MANAGER_FAILURE);
//        verify(mMockWiFiManager, timeout(VERIFICATION_TIMEOUT)).addNetwork(any(WifiConfiguration.class));
//    }
//
//    @Test
//    public void callbacks_failure_nullCallback() {
//        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(WiseFy.WIFI_MANAGER_FAILURE);
//        try {
//            mWiseFy.addOpenNetwork(OPEN_NETWORK_SSID, null);
//            verify(mMockWiFiManager, never()).addNetwork(any(WifiConfiguration.class));
//        } catch (NullPointerException npe) {
//            fail();
//        }
//    }
//
//    @Test
//    public void callbacks_failure_nullSSIDParam() {
//        AddOpenNetworkCallbacks mockCallbacks = mock(AddOpenNetworkCallbacks.class);
//        mWiseFy.addOpenNetwork(null, mockCallbacks);
//        verify(mockCallbacks, timeout(VERIFICATION_TIMEOUT)).addOpenNetworkWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
//        verify(mMockWiFiManager, never()).addNetwork(any(WifiConfiguration.class));
//    }
//
//    @Test
//    public void callbacks_failure_nullSSIDParam_nullCallback() {
//        try {
//            mWiseFy.addOpenNetwork(null, null);
//            verify(mMockWiFiManager, never()).addNetwork(any(WifiConfiguration.class));
//        } catch (NullPointerException npe) {
//            fail();
//        }
//    }
//
//    @Test
//    public void callbacks_failure_missingPrerequisite() {
//        missingPrerequisite();
//        AddOpenNetworkCallbacks mockCallbacks = mock(AddOpenNetworkCallbacks.class);
//        mWiseFy.addOpenNetwork(OPEN_NETWORK_SSID, mockCallbacks);
//        verify(mockCallbacks, timeout(VERIFICATION_TIMEOUT)).addOpenNetworkWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
//        verify(mMockWiFiManager, never()).addNetwork(any(WifiConfiguration.class));
//    }
//
//    @Test
//    public void callbacks_failure_missingPrerequisite_nullCallback() {
//        missingPrerequisite();
//        try {
//            mWiseFy.addOpenNetwork(OPEN_NETWORK_SSID, null);
//            verify(mMockWiFiManager, never()).addNetwork(any(WifiConfiguration.class));
//        } catch (NullPointerException npe) {
//            fail();
//        }
//    }
//
//    @Test
//    public void callbacks_success() {
//        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(0);
//        AddOpenNetworkCallbacks mockCallbacks = mock(AddOpenNetworkCallbacks.class);
//        mWiseFy.addOpenNetwork(OPEN_NETWORK_SSID, mockCallbacks);
//        verify(mockCallbacks, timeout(VERIFICATION_TIMEOUT)).openNetworkAdded(any(WifiConfiguration.class));
//        verify(mMockWiFiManager, timeout(VERIFICATION_TIMEOUT)).addNetwork(any(WifiConfiguration.class));
//    }
//
//    @Test
//    public void callbacks_success_nullCallback() {
//        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(0);
//        try {
//            mWiseFy.addOpenNetwork(OPEN_NETWORK_SSID, null);
//            verify(mMockWiFiManager, never()).addNetwork(any(WifiConfiguration.class));
//        } catch (NullPointerException npe) {
//            fail();
//        }
//    }
//
//    @Test
//    public void callbacks_success_alreadyConfigured() {
//        networkAlreadyInConfigurationList();
//
//        AddOpenNetworkCallbacks mockCallbacks = mock(AddOpenNetworkCallbacks.class);
//        mWiseFy.addOpenNetwork(OPEN_NETWORK_SSID, mockCallbacks);
//        verify(mockCallbacks, timeout(VERIFICATION_TIMEOUT)).addOpenNetworkWiseFyFailure(WiseFyCodes.NETWORK_ALREADY_CONFIGURED);
//        verify(mMockWiFiManager, never()).addNetwork(any(WifiConfiguration.class));
//    }
//
//    @Test
//    public void callbacks_success_alreadyConfigured_nullCallback() {
//        networkAlreadyInConfigurationList();
//
//        try {
//            mWiseFy.addOpenNetwork(OPEN_NETWORK_SSID, null);
//            verify(mMockWiFiManager, never()).addNetwork(any(WifiConfiguration.class));
//        } catch (NullPointerException npe) {
//            fail();
//        }
//    }
//}