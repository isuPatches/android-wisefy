package com.isupatches.wisefy.test;


import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import com.isupatches.wisefy.WiseFy;
import com.isupatches.wisefy.callbacks.AddWEPNetworkCallbacks;
import com.isupatches.wisefy.constants.WiseFyCodes;
import com.isupatches.wisefy.test.base.BaseTestClass;
import com.isupatches.wisefy.test.base.TestActivity;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static com.isupatches.wisefy.test.base.TestUtils.WEP_NETWORK_PASSWORD;
import static com.isupatches.wisefy.test.base.TestUtils.WEP_NETWORK_SSID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
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
        setManagers();
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(WiseFy.WIFI_MANAGER_FAILURE);
        int result = mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD);
        assertEquals(WiseFy.WIFI_MANAGER_FAILURE, result);
    }

    @Test
    public void noCallbacks_failure_nullPassword() {
        int result = mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, null);
        assertEquals(WiseFyCodes.MISSING_PARAMETER, result);
    }

    @Test
    public void noCallbacks_failure_nullSSID() {
        int result = mWiseFy.addWEPNetwork(null, WEP_NETWORK_PASSWORD);
        assertEquals(WiseFyCodes.MISSING_PARAMETER, result);
    }

    @Test
    public void noCallbacks_failure_nullWifiManager() {
        setManagersToNull();
        int result = mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD);
        assertEquals(WiseFyCodes.NULL_MANAGER, result);
    }

    @Test
    public void noCallbacks_success() {
        setManagers();
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(0);
        int result = mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD);
        assertNotEquals(WiseFy.WIFI_MANAGER_FAILURE, result);
    }

    @Test
    public void noCallbacks_success_alreadyConfigured() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = WEP_NETWORK_SSID;
        wifiList.add(mWiFiConfiguration);

        WifiInfo mockWiFiInfo = mock(WifiInfo.class);
        when(mockWiFiInfo.getSSID()).thenReturn(WEP_NETWORK_SSID);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWiFiInfo);

        int result = mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD);
        assertEquals(WiseFyCodes.NETWORK_ALREADY_CONFIGURED, result);
    }

    @Test
    public void callbacks_failure() {
        setManagers();
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(WiseFy.WIFI_MANAGER_FAILURE);
        AddWEPNetworkCallbacks mockAddNetworkCallbacks = mock(AddWEPNetworkCallbacks.class);
        mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD, mockAddNetworkCallbacks);
        verify(mockAddNetworkCallbacks, timeout(3000)).failureAddingWEPNetwork(WiseFy.WIFI_MANAGER_FAILURE);
    }

    @Test
    public void callbacks_failure_nullCallback() {
        setManagers();
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(WiseFy.WIFI_MANAGER_FAILURE);
        AddWEPNetworkCallbacks mockAddNetworkCallbacks = mock(AddWEPNetworkCallbacks.class);
        mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD, null);
        verify(mockAddNetworkCallbacks, never()).failureAddingWEPNetwork(WiseFy.WIFI_MANAGER_FAILURE);
    }

    @Test
    public void callbacks_failure_nullPassword() {
        AddWEPNetworkCallbacks mockAddNetworkCallbacks = mock(AddWEPNetworkCallbacks.class);
        mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, null, mockAddNetworkCallbacks);
        verify(mockAddNetworkCallbacks, timeout(2000)).addWEPNetworkWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
    }

    @Test
    public void callbacks_failure_nullPassword_nullCallback() {
        AddWEPNetworkCallbacks mockAddNetworkCallbacks = mock(AddWEPNetworkCallbacks.class);
        mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, null, null);
        verify(mockAddNetworkCallbacks, never()).addWEPNetworkWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
    }

    @Test
    public void callbacks_failure_nullSSID() {
        AddWEPNetworkCallbacks mockAddNetworkCallbacks = mock(AddWEPNetworkCallbacks.class);
        mWiseFy.addWEPNetwork(null, WEP_NETWORK_PASSWORD, mockAddNetworkCallbacks);
        verify(mockAddNetworkCallbacks, timeout(2000)).addWEPNetworkWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
    }

    @Test
    public void callbacks_failure_nullSSID_nullCallback() {
        AddWEPNetworkCallbacks mockAddNetworkCallbacks = mock(AddWEPNetworkCallbacks.class);
        mWiseFy.addWEPNetwork(null, WEP_NETWORK_PASSWORD, null);
        verify(mockAddNetworkCallbacks, never()).addWEPNetworkWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
    }

    @Test
    public void callbacks_failure_nullWifiManager() {
        setManagersToNull();
        AddWEPNetworkCallbacks mockAddNetworkCallbacks = mock(AddWEPNetworkCallbacks.class);
        mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD, mockAddNetworkCallbacks);
        verify(mockAddNetworkCallbacks, timeout(2000)).addWEPNetworkWiseFyFailure(WiseFyCodes.NULL_MANAGER);
    }

    @Test
    public void callbacks_failure_nullWifiManager_nullCallback() {
        setManagersToNull();
        AddWEPNetworkCallbacks mockAddNetworkCallbacks = mock(AddWEPNetworkCallbacks.class);
        mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD, null);
        verify(mockAddNetworkCallbacks, never()).addWEPNetworkWiseFyFailure(WiseFyCodes.NULL_MANAGER);
    }

    @Test
    public void callbacks_success() {
        setManagers();
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(0);
        AddWEPNetworkCallbacks mockAddNetworkCallbacks = mock(AddWEPNetworkCallbacks.class);
        mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD, mockAddNetworkCallbacks);
        verify(mockAddNetworkCallbacks, timeout(2000)).wepNetworkAdded(any(WifiConfiguration.class));
    }

    @Test
    public void callbacks_success_nullCallback() {
        setManagers();
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(0);
        AddWEPNetworkCallbacks mockAddNetworkCallbacks = mock(AddWEPNetworkCallbacks.class);
        mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD, null);
        verify(mockAddNetworkCallbacks, never()).wepNetworkAdded(any(WifiConfiguration.class));
    }

    @Test
    public void callbacks_success_alreadyConfigured() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = WEP_NETWORK_SSID;
        wifiList.add(mWiFiConfiguration);

        WifiInfo mockWiFiInfo = mock(WifiInfo.class);
        when(mockWiFiInfo.getSSID()).thenReturn(WEP_NETWORK_SSID);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWiFiInfo);

        AddWEPNetworkCallbacks mockAddNetworkCallbacks = mock(AddWEPNetworkCallbacks.class);
        mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD, mockAddNetworkCallbacks);
        verify(mockAddNetworkCallbacks, timeout(2000)).addWEPNetworkWiseFyFailure(WiseFyCodes.NETWORK_ALREADY_CONFIGURED);
    }

    @Test
    public void callbacks_success_alreadyConfigured_nullCallback() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = WEP_NETWORK_SSID;
        wifiList.add(mWiFiConfiguration);

        WifiInfo mockWiFiInfo = mock(WifiInfo.class);
        when(mockWiFiInfo.getSSID()).thenReturn(WEP_NETWORK_SSID);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWiFiInfo);

        AddWEPNetworkCallbacks mockAddNetworkCallbacks = mock(AddWEPNetworkCallbacks.class);
        mWiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD, null);
        verify(mockAddNetworkCallbacks, never()).addWEPNetworkWiseFyFailure(WiseFyCodes.NETWORK_ALREADY_CONFIGURED);
    }
}
