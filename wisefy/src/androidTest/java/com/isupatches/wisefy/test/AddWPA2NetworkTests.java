package com.isupatches.wisefy.test;


import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import com.isupatches.wisefy.WiseFy;
import com.isupatches.wisefy.callbacks.AddWPA2NetworkCallbacks;
import com.isupatches.wisefy.constants.WiseFyCodes;
import com.isupatches.wisefy.test.base.BaseTestClass;
import com.isupatches.wisefy.test.base.TestActivity;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static com.isupatches.wisefy.test.base.TestUtils.WPA2_NETWORK_PASSWORD;
import static com.isupatches.wisefy.test.base.TestUtils.WPA2_NETWORK_SSID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class AddWPA2NetworkTests extends BaseTestClass<TestActivity> {

    public AddWPA2NetworkTests() {
        super(TestActivity.class);
    }

    @Test
    public void noCallbacks_failure() {
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(WiseFy.WIFI_MANAGER_FAILURE);
        int result = mWiseFy.addWPA2Network(WPA2_NETWORK_SSID, WPA2_NETWORK_PASSWORD);
        assertEquals(WiseFy.WIFI_MANAGER_FAILURE, result);
    }

    @Test
    public void noCallbacks_failure_nullPassword() {
        int result = mWiseFy.addWPA2Network(WPA2_NETWORK_SSID, null);
        assertEquals(WiseFyCodes.MISSING_PARAMETER, result);
    }

    @Test
    public void anoCallbacks_failure_nullSSID() {
        int result = mWiseFy.addWPA2Network(null, WPA2_NETWORK_PASSWORD);
        assertEquals(WiseFyCodes.MISSING_PARAMETER, result);
    }

    @Test
    public void noCallbacks_failure_nullWifiManager() {
        setManagersToNull();
        int result = mWiseFy.addWPA2Network(WPA2_NETWORK_SSID, WPA2_NETWORK_PASSWORD);
        assertEquals(WiseFyCodes.MISSING_PREREQUISITE, result);
    }

    @Test
    public void noCallbacks_success() {
        setManagers();
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(0);
        int result = mWiseFy.addWPA2Network(WPA2_NETWORK_SSID, WPA2_NETWORK_PASSWORD);
        assertNotEquals(WiseFy.WIFI_MANAGER_FAILURE, result);
    }

    @Test
    public void noCallbacks_success_alreadyConfigured() {
        setManagers();

        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = WPA2_NETWORK_SSID;
        wifiList.add(mWiFiConfiguration);

        WifiInfo mockWiFiInfo = mock(WifiInfo.class);
        when(mockWiFiInfo.getSSID()).thenReturn(WPA2_NETWORK_SSID);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWiFiInfo);

        int result = mWiseFy.addWPA2Network(WPA2_NETWORK_SSID, WPA2_NETWORK_PASSWORD);
        assertEquals(WiseFyCodes.NETWORK_ALREADY_CONFIGURED, result);
    }

    @Test
    public void callbacks_failure() {
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(WiseFy.WIFI_MANAGER_FAILURE);
        AddWPA2NetworkCallbacks mockAddNetworkCallbacks = mock(AddWPA2NetworkCallbacks.class);
        mWiseFy.addWPA2Network(WPA2_NETWORK_SSID, WPA2_NETWORK_PASSWORD, mockAddNetworkCallbacks);
        verify(mockAddNetworkCallbacks, timeout(2000)).failureAddingWPA2Network(WiseFy.WIFI_MANAGER_FAILURE);
    }

    @Test
    public void callbacks_failure_nullCallback() {
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(WiseFy.WIFI_MANAGER_FAILURE);
        AddWPA2NetworkCallbacks mockAddNetworkCallbacks = mock(AddWPA2NetworkCallbacks.class);
        mWiseFy.addWPA2Network(WPA2_NETWORK_SSID, WPA2_NETWORK_PASSWORD, null);
        verify(mockAddNetworkCallbacks, never()).failureAddingWPA2Network(WiseFy.WIFI_MANAGER_FAILURE);
    }

    @Test
    public void callbacks_failure_nullPassword() {
        AddWPA2NetworkCallbacks mockAddNetworkCallbacks = mock(AddWPA2NetworkCallbacks.class);
        mWiseFy.addWPA2Network(WPA2_NETWORK_SSID, null, mockAddNetworkCallbacks);
        verify(mockAddNetworkCallbacks, timeout(2000)).addWPA2NetworkWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
    }

    @Test
    public void callbacks_failure_nullPassword_nullCallback() {
        AddWPA2NetworkCallbacks mockAddNetworkCallbacks = mock(AddWPA2NetworkCallbacks.class);
        mWiseFy.addWPA2Network(WPA2_NETWORK_SSID, null, null);
        verify(mockAddNetworkCallbacks, never()).addWPA2NetworkWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
    }

    @Test
    public void callbacks_failure_nullSSID() {
        AddWPA2NetworkCallbacks mockAddNetworkCallbacks = mock(AddWPA2NetworkCallbacks.class);
        mWiseFy.addWPA2Network(null, WPA2_NETWORK_PASSWORD, mockAddNetworkCallbacks);
        verify(mockAddNetworkCallbacks, timeout(2000)).addWPA2NetworkWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
    }

    @Test
    public void callbacks_failure_nullSSID_nullCallback() {
        AddWPA2NetworkCallbacks mockAddNetworkCallbacks = mock(AddWPA2NetworkCallbacks.class);
        mWiseFy.addWPA2Network(null, WPA2_NETWORK_PASSWORD, null);
        verify(mockAddNetworkCallbacks, never()).addWPA2NetworkWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
    }

    @Test
    public void callbacks_failure_nullWifiManager() {
        setManagersToNull();
        AddWPA2NetworkCallbacks mockAddNetworkCallbacks = mock(AddWPA2NetworkCallbacks.class);
        mWiseFy.addWPA2Network(WPA2_NETWORK_SSID, WPA2_NETWORK_PASSWORD, mockAddNetworkCallbacks);
        verify(mockAddNetworkCallbacks, timeout(2000)).addWPA2NetworkWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
    }

    @Test
    public void callbacks_failure_nullWifiManager_nullCallback() {
        setManagersToNull();
        AddWPA2NetworkCallbacks mockAddNetworkCallbacks = mock(AddWPA2NetworkCallbacks.class);
        mWiseFy.addWPA2Network(WPA2_NETWORK_SSID, WPA2_NETWORK_PASSWORD, null);
        verify(mockAddNetworkCallbacks, never()).addWPA2NetworkWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
    }

    @Test
    public void callbacks_success() {
        setManagers();
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(0);
        AddWPA2NetworkCallbacks mockAddNetworkCallbacks = mock(AddWPA2NetworkCallbacks.class);
        mWiseFy.addWPA2Network(WPA2_NETWORK_SSID, WPA2_NETWORK_PASSWORD, mockAddNetworkCallbacks);
        verify(mockAddNetworkCallbacks, timeout(2000)).wpa2NetworkAdded(any(WifiConfiguration.class));
    }

    @Test
    public void callbacks_success_nullCallback() {
        setManagers();
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(0);
        AddWPA2NetworkCallbacks mockAddNetworkCallbacks = mock(AddWPA2NetworkCallbacks.class);
        mWiseFy.addWPA2Network(WPA2_NETWORK_SSID, WPA2_NETWORK_PASSWORD, null);
        verify(mockAddNetworkCallbacks, never()).wpa2NetworkAdded(any(WifiConfiguration.class));
    }

    @Test
    public void callbacks_success_alreadyConfigured() {
        setManagers();

        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = WPA2_NETWORK_SSID;
        wifiList.add(mWiFiConfiguration);

        WifiInfo mockWiFiInfo = mock(WifiInfo.class);
        when(mockWiFiInfo.getSSID()).thenReturn(WPA2_NETWORK_SSID);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWiFiInfo);

        AddWPA2NetworkCallbacks mockAddNetworkCallbacks = mock(AddWPA2NetworkCallbacks.class);
        mWiseFy.addWPA2Network(WPA2_NETWORK_SSID, WPA2_NETWORK_PASSWORD, mockAddNetworkCallbacks);
        verify(mockAddNetworkCallbacks, timeout(2000)).addWPA2NetworkWiseFyFailure(WiseFyCodes.NETWORK_ALREADY_CONFIGURED);
    }

    @Test
    public void callbacks_success_alreadyConfigured_nullCallback() {
        setManagers();

        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = WPA2_NETWORK_SSID;
        wifiList.add(mWiFiConfiguration);

        WifiInfo mockWiFiInfo = mock(WifiInfo.class);
        when(mockWiFiInfo.getSSID()).thenReturn(WPA2_NETWORK_SSID);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWiFiInfo);

        AddWPA2NetworkCallbacks mockAddNetworkCallbacks = mock(AddWPA2NetworkCallbacks.class);
        mWiseFy.addWPA2Network(WPA2_NETWORK_SSID, WPA2_NETWORK_PASSWORD, null);
        verify(mockAddNetworkCallbacks,never()).addWPA2NetworkWiseFyFailure(WiseFyCodes.NETWORK_ALREADY_CONFIGURED);
    }
}
