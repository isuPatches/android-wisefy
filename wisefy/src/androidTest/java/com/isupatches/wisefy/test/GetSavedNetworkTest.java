package com.isupatches.wisefy.test;


import android.net.wifi.WifiConfiguration;
import com.isupatches.wisefy.callbacks.GetSavedNetworkCallbacks;
import com.isupatches.wisefy.constants.WiseFyCodes;
import com.isupatches.wisefy.test.base.BaseTestClass;
import com.isupatches.wisefy.test.base.TestActivity;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static com.isupatches.wisefy.test.base.TestUtils.TEST_SSID;
import static com.isupatches.wisefy.test.base.TestUtils.TEST_SSID2;
import static com.isupatches.wisefy.test.base.TestUtils.TEST_SSID3;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class GetSavedNetworkTest extends BaseTestClass<TestActivity> {

    public GetSavedNetworkTest() {
        super(TestActivity.class);
    }

    @Test
    public void noCallbacks_failure_nullWifiManager() {
        setManagersToNull();
        assertEquals(null, mWiseFy.getSavedNetwork(TEST_SSID));
    }

    @Test
    public void noCallbacks_failure_nullSSIDParam() {
        setManagers();
        assertEquals(null, mWiseFy.getSavedNetwork(null));
    }

    @Test
    public void noCallbacks_failure_differentSSID() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        wifiConfiguration.SSID = TEST_SSID2;
        wifiList.add(wifiConfiguration);
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);

        assertEquals(null, mWiseFy.getSavedNetwork(TEST_SSID));
    }

    @Test
    public void noCallbacks_failure_nullSSID() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        wifiConfiguration.SSID = null;
        wifiList.add(wifiConfiguration);
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);

        assertEquals(null, mWiseFy.getSavedNetwork(TEST_SSID));
    }

    @Test
    public void noCallbacks_failure_multipleNetworks() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();

        WifiConfiguration wifiConfiguration1 = new WifiConfiguration();
        wifiConfiguration1.SSID = TEST_SSID2;
        wifiList.add(wifiConfiguration1);

        WifiConfiguration wifiConfiguration2 = new WifiConfiguration();
        wifiConfiguration2.SSID = TEST_SSID3;
        wifiList.add(wifiConfiguration2);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);

        assertEquals(null, mWiseFy.getSavedNetwork(TEST_SSID));
    }

    @Test
    public void noCallbacks_success() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        wifiConfiguration.SSID = TEST_SSID;
        wifiList.add(wifiConfiguration);
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);

        assertEquals(wifiConfiguration, mWiseFy.getSavedNetwork(TEST_SSID));
    }

    @Test
    public void noCallbacks_success_multipleNetworks() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();

        WifiConfiguration wifiConfiguration1 = new WifiConfiguration();
        wifiConfiguration1.SSID = TEST_SSID;
        wifiList.add(wifiConfiguration1);

        WifiConfiguration wifiConfiguration2 = new WifiConfiguration();
        wifiConfiguration2.SSID = TEST_SSID2;
        wifiList.add(wifiConfiguration2);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);

        assertEquals(wifiConfiguration1, mWiseFy.getSavedNetwork(TEST_SSID));
    }

    @Test
    public void callbacks_failure_nullWifiManager() {
        setManagersToNull();
        GetSavedNetworkCallbacks getSavedNetworkCallbacks = mock(GetSavedNetworkCallbacks.class);
        mWiseFy.getSavedNetwork(TEST_SSID, getSavedNetworkCallbacks);
        verify(getSavedNetworkCallbacks, timeout(2000)).getSavedNetworkWiseFyFailure(WiseFyCodes.NULL_MANAGER);
    }

    @Test
    public void callbacks_failure_nullWifiManager_nullCallback() {
        setManagersToNull();
        GetSavedNetworkCallbacks getSavedNetworkCallbacks = mock(GetSavedNetworkCallbacks.class);
        mWiseFy.getSavedNetwork(TEST_SSID, null);
        verify(getSavedNetworkCallbacks, never()).getSavedNetworkWiseFyFailure(WiseFyCodes.NULL_MANAGER);
    }

    @Test
    public void callbacks_failure_nullSSIDParam() {
        setManagers();
        GetSavedNetworkCallbacks getSavedNetworkCallbacks = mock(GetSavedNetworkCallbacks.class);
        mWiseFy.getSavedNetwork(null, getSavedNetworkCallbacks);
        verify(getSavedNetworkCallbacks, timeout(2000)).getSavedNetworkWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
    }

    @Test
    public void callbacks_failure_nullSSIDParam_nullCallback() {
        setManagers();
        GetSavedNetworkCallbacks getSavedNetworkCallbacks = mock(GetSavedNetworkCallbacks.class);
        mWiseFy.getSavedNetwork(null, null);
        verify(getSavedNetworkCallbacks, never()).getSavedNetworkWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
    }

    @Test
    public void callbacks_failure_differentSSID() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        wifiConfiguration.SSID = TEST_SSID2;
        wifiList.add(wifiConfiguration);
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);

        GetSavedNetworkCallbacks getSavedNetworkCallbacks = mock(GetSavedNetworkCallbacks.class);
        mWiseFy.getSavedNetwork(TEST_SSID, getSavedNetworkCallbacks);
        verify(getSavedNetworkCallbacks, timeout(2000)).networkNotFound();
    }

    @Test
    public void callbacks_failure_differentSSID_nullCallback() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        wifiConfiguration.SSID = TEST_SSID2;
        wifiList.add(wifiConfiguration);
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);

        GetSavedNetworkCallbacks getSavedNetworkCallbacks = mock(GetSavedNetworkCallbacks.class);
        mWiseFy.getSavedNetwork(TEST_SSID, null);
        verify(getSavedNetworkCallbacks, never()).networkNotFound();
    }

    @Test
    public void callbacks_failure_nullSSID() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        wifiConfiguration.SSID = null;
        wifiList.add(wifiConfiguration);
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);

        GetSavedNetworkCallbacks getSavedNetworkCallbacks = mock(GetSavedNetworkCallbacks.class);
        mWiseFy.getSavedNetwork(TEST_SSID, getSavedNetworkCallbacks);
        verify(getSavedNetworkCallbacks, timeout(2000)).networkNotFound();
    }

    @Test
    public void callbacks_failure_nullSSID_nullCallback() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        wifiConfiguration.SSID = null;
        wifiList.add(wifiConfiguration);
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);

        GetSavedNetworkCallbacks getSavedNetworkCallbacks = mock(GetSavedNetworkCallbacks.class);
        mWiseFy.getSavedNetwork(TEST_SSID, null);
        verify(getSavedNetworkCallbacks, never()).networkNotFound();
    }

    @Test
    public void callbacks_failure_multipleNetworks() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();

        WifiConfiguration wifiConfiguration1 = new WifiConfiguration();
        wifiConfiguration1.SSID = TEST_SSID2;
        wifiList.add(wifiConfiguration1);

        WifiConfiguration wifiConfiguration2 = new WifiConfiguration();
        wifiConfiguration2.SSID = TEST_SSID3;
        wifiList.add(wifiConfiguration2);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);

        GetSavedNetworkCallbacks getSavedNetworkCallbacks = mock(GetSavedNetworkCallbacks.class);
        mWiseFy.getSavedNetwork(TEST_SSID, getSavedNetworkCallbacks);
        verify(getSavedNetworkCallbacks, timeout(2000)).networkNotFound();
    }

    @Test
    public void callbacks_failure_multipleNetworks_nullCallback() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();

        WifiConfiguration wifiConfiguration1 = new WifiConfiguration();
        wifiConfiguration1.SSID = TEST_SSID2;
        wifiList.add(wifiConfiguration1);

        WifiConfiguration wifiConfiguration2 = new WifiConfiguration();
        wifiConfiguration2.SSID = TEST_SSID3;
        wifiList.add(wifiConfiguration2);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);

        GetSavedNetworkCallbacks getSavedNetworkCallbacks = mock(GetSavedNetworkCallbacks.class);
        mWiseFy.getSavedNetwork(TEST_SSID, null);
        verify(getSavedNetworkCallbacks, never()).networkNotFound();
    }

    @Test
    public void callbacks_success() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        wifiConfiguration.SSID = TEST_SSID;
        wifiList.add(wifiConfiguration);
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);

        GetSavedNetworkCallbacks getSavedNetworkCallbacks = mock(GetSavedNetworkCallbacks.class);
        mWiseFy.getSavedNetwork(TEST_SSID, getSavedNetworkCallbacks);
        verify(getSavedNetworkCallbacks, timeout(2000)).retrievedSavedNetwork(wifiConfiguration);
    }

    @Test
    public void callbacks_success_nullCallback() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        wifiConfiguration.SSID = TEST_SSID;
        wifiList.add(wifiConfiguration);
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);

        GetSavedNetworkCallbacks getSavedNetworkCallbacks = mock(GetSavedNetworkCallbacks.class);
        mWiseFy.getSavedNetwork(TEST_SSID, null);
        verify(getSavedNetworkCallbacks, never()).retrievedSavedNetwork(wifiConfiguration);
    }

    @Test
    public void callbacks_success_multipleNetworks() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();

        WifiConfiguration wifiConfiguration1 = new WifiConfiguration();
        wifiConfiguration1.SSID = TEST_SSID;
        wifiList.add(wifiConfiguration1);

        WifiConfiguration wifiConfiguration2 = new WifiConfiguration();
        wifiConfiguration2.SSID = TEST_SSID2;
        wifiList.add(wifiConfiguration2);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);

        GetSavedNetworkCallbacks getSavedNetworkCallbacks = mock(GetSavedNetworkCallbacks.class);
        mWiseFy.getSavedNetwork(TEST_SSID, getSavedNetworkCallbacks);
        verify(getSavedNetworkCallbacks, timeout(2000)).retrievedSavedNetwork(wifiConfiguration1);    }

    @Test
    public void callbacks_success_multipleNetworks_nullCallback() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();

        WifiConfiguration wifiConfiguration1 = new WifiConfiguration();
        wifiConfiguration1.SSID = TEST_SSID;
        wifiList.add(wifiConfiguration1);

        WifiConfiguration wifiConfiguration2 = new WifiConfiguration();
        wifiConfiguration2.SSID = TEST_SSID2;
        wifiList.add(wifiConfiguration2);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);

        GetSavedNetworkCallbacks getSavedNetworkCallbacks = mock(GetSavedNetworkCallbacks.class);
        mWiseFy.getSavedNetwork(TEST_SSID, null);
        verify(getSavedNetworkCallbacks, never()).retrievedSavedNetwork(wifiConfiguration1);
    }
}
