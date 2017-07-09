package com.isupatches.wisefy.test;


import android.net.wifi.WifiConfiguration;
import com.isupatches.wisefy.callbacks.RemoveNetworkCallbacks;
import com.isupatches.wisefy.constants.WiseFyCodes;
import com.isupatches.wisefy.test.base.BaseTestClass;
import com.isupatches.wisefy.test.base.TestActivity;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static com.isupatches.wisefy.test.base.TestUtils.TEST_SSID;
import static com.isupatches.wisefy.test.base.TestUtils.getTestWifiConfiguration;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class RemoveNetworkTests extends BaseTestClass<TestActivity> {

    public RemoveNetworkTests() {
        super(TestActivity.class);
    }

    @Test
    public void noCallbacks_failure() {
        setManagers();
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(getTestWifiConfiguration());
        when(mMockWiFiManager.removeNetwork(anyInt())).thenReturn(false);

        boolean result = mWiseFy.removeNetwork(TEST_SSID);
        assertEquals(false, result);
    }

    @Test
    public void noCallbacks_failure_notInList() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        when(mMockWiFiManager.removeNetwork(anyInt())).thenReturn(false);

        boolean result = mWiseFy.removeNetwork(TEST_SSID);
        assertEquals(false, result);
    }

    @Test
    public void noCallbacks_failure_nullSSIDParam() {
        setManagersToNull();
        boolean result = mWiseFy.removeNetwork(null);
        assertEquals(false, result);
    }

    @Test
    public void noCallbacks_failure_nullWifiManager() {
        setManagersToNull();
        boolean result = mWiseFy.removeNetwork(TEST_SSID);
        assertEquals(false, result);
    }

    @Test
    public void noCallbacks_success() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = TEST_SSID;
        wifiList.add(mWiFiConfiguration);
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        when(mMockWiFiManager.removeNetwork(anyInt())).thenReturn(true);

        boolean result = mWiseFy.removeNetwork(TEST_SSID);
        assertEquals(true, result);
    }

    @Test
    public void callbacks_failure() {
        setManagers();
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(getTestWifiConfiguration());
        when(mMockWiFiManager.removeNetwork(anyInt())).thenReturn(false);

        RemoveNetworkCallbacks removeNetworkCallbacks = mock(RemoveNetworkCallbacks.class);
        mWiseFy.removeNetwork(TEST_SSID, removeNetworkCallbacks);
        verify(removeNetworkCallbacks, timeout(2000)).failureRemovingNetwork();
    }

    @Test
    public void callbacks_failure_nullCallback() {
        setManagers();
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(getTestWifiConfiguration());
        when(mMockWiFiManager.removeNetwork(anyInt())).thenReturn(false);

        RemoveNetworkCallbacks removeNetworkCallbacks = mock(RemoveNetworkCallbacks.class);
        mWiseFy.removeNetwork(TEST_SSID, null);
        verify(removeNetworkCallbacks, never()).failureRemovingNetwork();
    }

    @Test
    public void callbacks_failure_notInList() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        when(mMockWiFiManager.removeNetwork(anyInt())).thenReturn(false);

        RemoveNetworkCallbacks removeNetworkCallbacks = mock(RemoveNetworkCallbacks.class);
        mWiseFy.removeNetwork(TEST_SSID, removeNetworkCallbacks);
        verify(removeNetworkCallbacks, timeout(2000)).networkNotFoundToRemove();
    }

    @Test
    public void callbacks_failure_notInList_nullCallback() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        when(mMockWiFiManager.removeNetwork(anyInt())).thenReturn(false);

        RemoveNetworkCallbacks removeNetworkCallbacks = mock(RemoveNetworkCallbacks.class);
        mWiseFy.removeNetwork(TEST_SSID, null);
        verify(removeNetworkCallbacks, never()).networkNotFoundToRemove();
    }

    @Test
    public void callbacks_failure_nullSSIDParam() {
        setManagers();
        RemoveNetworkCallbacks removeNetworkCallbacks = mock(RemoveNetworkCallbacks.class);
        mWiseFy.removeNetwork(null, removeNetworkCallbacks);
        verify(removeNetworkCallbacks, timeout(2000)).removeNetworkWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
    }

    @Test
    public void callbacks_failure_nullSSIDParam_nullCallback() {
        setManagers();
        RemoveNetworkCallbacks removeNetworkCallbacks = mock(RemoveNetworkCallbacks.class);
        mWiseFy.removeNetwork(null, null);
        verify(removeNetworkCallbacks, never()).removeNetworkWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
    }

    @Test
    public void callbacks_failure_nullWifiManager() {
        setManagersToNull();
        RemoveNetworkCallbacks removeNetworkCallbacks = mock(RemoveNetworkCallbacks.class);
        mWiseFy.removeNetwork(TEST_SSID, removeNetworkCallbacks);
        verify(removeNetworkCallbacks, timeout(2000)).removeNetworkWiseFyFailure(WiseFyCodes.NULL_MANAGER);
    }

    @Test
    public void callbacks_failure_nullWifiManager_nullCallback() {
        setManagersToNull();
        RemoveNetworkCallbacks removeNetworkCallbacks = mock(RemoveNetworkCallbacks.class);
        mWiseFy.removeNetwork(TEST_SSID, null);
        verify(removeNetworkCallbacks, never()).removeNetworkWiseFyFailure(WiseFyCodes.NULL_MANAGER);
    }

    @Test
    public void callbacks_success() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = TEST_SSID;
        wifiList.add(mWiFiConfiguration);
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        when(mMockWiFiManager.removeNetwork(anyInt())).thenReturn(true);

        RemoveNetworkCallbacks removeNetworkCallbacks = mock(RemoveNetworkCallbacks.class);
        mWiseFy.removeNetwork(TEST_SSID, removeNetworkCallbacks);
        verify(removeNetworkCallbacks, timeout(2000)).networkRemoved();
    }

    @Test
    public void callbacks_success_nullCallback() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = TEST_SSID;
        wifiList.add(mWiFiConfiguration);
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        when(mMockWiFiManager.removeNetwork(anyInt())).thenReturn(true);

        RemoveNetworkCallbacks removeNetworkCallbacks = mock(RemoveNetworkCallbacks.class);
        mWiseFy.removeNetwork(TEST_SSID, null);
        verify(removeNetworkCallbacks, never()).networkRemoved();
    }
}
