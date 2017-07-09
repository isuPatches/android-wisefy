package com.isupatches.wisefy.test;


import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import com.isupatches.wisefy.WiseFy;
import com.isupatches.wisefy.callbacks.AddOpenNetworkCallbacks;
import com.isupatches.wisefy.constants.WiseFyCodes;
import com.isupatches.wisefy.test.base.BaseTestClass;
import com.isupatches.wisefy.test.base.TestActivity;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static com.isupatches.wisefy.test.base.TestUtils.OPEN_NETWORK_SSID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class AddOpenNetworkTests extends BaseTestClass<TestActivity> {

    public AddOpenNetworkTests() {
        super(TestActivity.class);
    }

    @Test
    public void noCallbacks_failure() {
        setManagers();
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(WiseFy.WIFI_MANAGER_FAILURE);
        int result = mWiseFy.addOpenNetwork(OPEN_NETWORK_SSID);
        assertEquals(WiseFy.WIFI_MANAGER_FAILURE, result);
    }

    @Test
    public void noCallbacks_failure_nullSSID() {
        int result = mWiseFy.addOpenNetwork(null);
        assertEquals(WiseFyCodes.MISSING_PARAMETER, result);
    }

    @Test
    public void noCallbacks_failure_nullWifiManager() {
        setManagersToNull();
        int result = mWiseFy.addOpenNetwork(OPEN_NETWORK_SSID);
        assertEquals(WiseFyCodes.NULL_MANAGER, result);
    }

    @Test
    public void noCallbacks_success() {
        setManagers();
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(0);
        int result = mWiseFy.addOpenNetwork(OPEN_NETWORK_SSID);
        assertNotEquals(WiseFy.WIFI_MANAGER_FAILURE, result);
    }

    @Test
    public void noCallbacks_success_alreadyConfigured() {
        setManagers();

        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = OPEN_NETWORK_SSID;
        wifiList.add(mWiFiConfiguration);

        WifiInfo mockWiFiInfo = mock(WifiInfo.class);
        when(mockWiFiInfo.getSSID()).thenReturn(OPEN_NETWORK_SSID);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWiFiInfo);

        int result = mWiseFy.addOpenNetwork(OPEN_NETWORK_SSID);
        assertEquals(WiseFyCodes.NETWORK_ALREADY_CONFIGURED, result);
    }

    @Test
    public void callbacks_failure() {
        setManagers();
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(WiseFy.WIFI_MANAGER_FAILURE);
        AddOpenNetworkCallbacks mockAddNetworkCallbacks = mock(AddOpenNetworkCallbacks.class);
        mWiseFy.addOpenNetwork(OPEN_NETWORK_SSID, mockAddNetworkCallbacks);
        verify(mockAddNetworkCallbacks, timeout(3000)).failureAddingOpenNetwork(WiseFy.WIFI_MANAGER_FAILURE);
    }

    @Test
    public void callbacks_failure_nullCallback() {
        setManagers();
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(WiseFy.WIFI_MANAGER_FAILURE);
        AddOpenNetworkCallbacks mockAddNetworkCallbacks = mock(AddOpenNetworkCallbacks.class);
        mWiseFy.addOpenNetwork(OPEN_NETWORK_SSID, null);
        verify(mockAddNetworkCallbacks, never()).failureAddingOpenNetwork(WiseFy.WIFI_MANAGER_FAILURE);
    }

    @Test
    public void callbacks_failure_nullSSID() {
        setManagers();
        AddOpenNetworkCallbacks mockAddNetworkCallbacks = mock(AddOpenNetworkCallbacks.class);
        mWiseFy.addOpenNetwork(null, mockAddNetworkCallbacks);
        verify(mockAddNetworkCallbacks, timeout(3000)).addOpenNetworkWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
    }

    @Test
    public void callbacks_failure_nullSSID_nullCallback() {
        setManagers();
        AddOpenNetworkCallbacks mockAddNetworkCallbacks = mock(AddOpenNetworkCallbacks.class);
        mWiseFy.addOpenNetwork(null, null);
        verify(mockAddNetworkCallbacks, never()).addOpenNetworkWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
    }

    @Test
    public void callbacks_failure_nullWifiManager() {
        setManagersToNull();
        AddOpenNetworkCallbacks mockAddNetworkCallbacks = mock(AddOpenNetworkCallbacks.class);
        mWiseFy.addOpenNetwork(OPEN_NETWORK_SSID, mockAddNetworkCallbacks);
        verify(mockAddNetworkCallbacks, timeout(3000)).addOpenNetworkWiseFyFailure(WiseFyCodes.NULL_MANAGER);
    }

    @Test
    public void callbacks_failure_nullWifiManager_nullCallback() {
        setManagersToNull();
        AddOpenNetworkCallbacks mockAddNetworkCallbacks = mock(AddOpenNetworkCallbacks.class);
        mWiseFy.addOpenNetwork(OPEN_NETWORK_SSID, null);
        verify(mockAddNetworkCallbacks, never()).addOpenNetworkWiseFyFailure(WiseFyCodes.NULL_MANAGER);
    }

    @Test
    public void callbacks_success() {
        setManagers();
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(0);
        AddOpenNetworkCallbacks mockAddNetworkCallbacks = mock(AddOpenNetworkCallbacks.class);
        mWiseFy.addOpenNetwork(OPEN_NETWORK_SSID, mockAddNetworkCallbacks);
        verify(mockAddNetworkCallbacks, timeout(3000)).openNetworkAdded(any(WifiConfiguration.class));
    }

    @Test
    public void callbacks_success_nullCallback() {
        setManagers();
        when(mMockWiFiManager.addNetwork(any(WifiConfiguration.class))).thenReturn(0);
        AddOpenNetworkCallbacks mockAddNetworkCallbacks = mock(AddOpenNetworkCallbacks.class);
        mWiseFy.addOpenNetwork(OPEN_NETWORK_SSID, null);
        verify(mockAddNetworkCallbacks, never()).openNetworkAdded(any(WifiConfiguration.class));
    }

    @Test
    public void callbacks_success_alreadyConfigured() {
        setManagers();

        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = OPEN_NETWORK_SSID;
        wifiList.add(mWiFiConfiguration);

        WifiInfo mockWiFiInfo = mock(WifiInfo.class);
        when(mockWiFiInfo.getSSID()).thenReturn(OPEN_NETWORK_SSID);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWiFiInfo);

        AddOpenNetworkCallbacks mockAddNetworkCallbacks = mock(AddOpenNetworkCallbacks.class);
        mWiseFy.addOpenNetwork(OPEN_NETWORK_SSID, mockAddNetworkCallbacks);
        verify(mockAddNetworkCallbacks, timeout(3000)).addOpenNetworkWiseFyFailure(WiseFyCodes.NETWORK_ALREADY_CONFIGURED);
    }

    @Test
    public void callbacks_success_alreadyConfigured_nullCallback() {
        setManagers();

        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = OPEN_NETWORK_SSID;
        wifiList.add(mWiFiConfiguration);

        WifiInfo mockWiFiInfo = mock(WifiInfo.class);
        when(mockWiFiInfo.getSSID()).thenReturn(OPEN_NETWORK_SSID);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWiFiInfo);

        AddOpenNetworkCallbacks mockAddNetworkCallbacks = mock(AddOpenNetworkCallbacks.class);
        mWiseFy.addOpenNetwork(OPEN_NETWORK_SSID, null);
        verify(mockAddNetworkCallbacks, never()).addOpenNetworkWiseFyFailure(WiseFyCodes.NETWORK_ALREADY_CONFIGURED);
    }
}