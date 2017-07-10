package com.isupatches.wisefy.test;


import android.net.wifi.WifiConfiguration;
import com.isupatches.wisefy.callbacks.GetSavedNetworksCallbacks;
import com.isupatches.wisefy.constants.WiseFyCodes;
import com.isupatches.wisefy.test.base.BaseTestClass;
import com.isupatches.wisefy.test.base.TestActivity;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static com.isupatches.wisefy.test.base.TestUtils.TEST_SSID;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class GetSavedNetworkTests extends BaseTestClass<TestActivity> {

    public GetSavedNetworkTests() {
        super(TestActivity.class);
    }

    @Test
    public void noCallbacks_failure_nullWifiManager() {
        setManagersToNull();
        assertEquals(null, mWiseFy.getSavedNetworks());
    }

    @Test
    public void noCallbacks_success() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = TEST_SSID;
        wifiList.add(mWiFiConfiguration);
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);

        assertEquals(wifiList, mWiseFy.getSavedNetworks());
    }

    @Test
    public void callbacks_failure_nullWifiManager() {
        setManagersToNull();
        GetSavedNetworksCallbacks getSavedNetworksCallbacks = mock(GetSavedNetworksCallbacks.class);
        mWiseFy.getSavedNetworks(getSavedNetworksCallbacks);
        verify(getSavedNetworksCallbacks, timeout(2000)).getSavedNetworksWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
    }

    @Test
    public void callbacks_failure_nullWifiManager_nullCallback() {
        setManagersToNull();
        GetSavedNetworksCallbacks getSavedNetworksCallbacks = mock(GetSavedNetworksCallbacks.class);
        mWiseFy.getSavedNetworks(null);
        verify(getSavedNetworksCallbacks, never()).getSavedNetworksWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
    }

    @Test
    public void callbacks_success() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = TEST_SSID;
        wifiList.add(mWiFiConfiguration);
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);

        GetSavedNetworksCallbacks getSavedNetworksCallbacks = mock(GetSavedNetworksCallbacks.class);
        mWiseFy.getSavedNetworks(getSavedNetworksCallbacks);
        verify(getSavedNetworksCallbacks, timeout(2000)).retrievedSavedNetworks(wifiList);
    }

    @Test
    public void callbacks_success_nullCallback() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = TEST_SSID;
        wifiList.add(mWiFiConfiguration);
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);

        GetSavedNetworksCallbacks getSavedNetworksCallbacks = mock(GetSavedNetworksCallbacks.class);
        mWiseFy.getSavedNetworks(null);
        verify(getSavedNetworksCallbacks, never()).retrievedSavedNetworks(wifiList);
    }
}
