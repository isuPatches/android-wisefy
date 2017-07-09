package com.isupatches.wisefy.test;


import android.net.NetworkInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import com.isupatches.wisefy.callbacks.ConnectToNetworkCallbacks;
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


public class ConnectToNetworkTests extends BaseTestClass<TestActivity> {

    public ConnectToNetworkTests() {
        super(TestActivity.class);
    }

    @Test
    public void noCallbacks_failure_emptyList() {
        setManagers();
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(new ArrayList<WifiConfiguration>());
        boolean result = mWiseFy.connectToNetwork(TEST_SSID, 1);
        assertEquals(false, result);
    }

    @Test
    public void noCallbacks_failure_multipleNetworks() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();

        WifiConfiguration mWiFiConfiguration1 = new WifiConfiguration();
        mWiFiConfiguration1.SSID = TEST_SSID2;
        wifiList.add(mWiFiConfiguration1);

        WifiConfiguration mWiFiConfiguration2 = new WifiConfiguration();
        mWiFiConfiguration2.SSID = TEST_SSID3;
        wifiList.add(mWiFiConfiguration2);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);

        boolean result = mWiseFy.connectToNetwork(TEST_SSID, 1);
        assertEquals(false, result);
    }

    @Test
    public void noCallbacks_failure_notAvailable() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = TEST_SSID;
        wifiList.add(mWiFiConfiguration);

        WifiInfo mockWiFiInfo = mock(WifiInfo.class);
        when(mockWiFiInfo.getSSID()).thenReturn(TEST_SSID);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWiFiInfo);

        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(false);
        when(mockNetworkInfo.isConnected()).thenReturn(true);

        boolean result = mWiseFy.connectToNetwork(TEST_SSID, 1);
        assertEquals(false, result);
    }

    @Test
    public void noCallbacks_failure_notConnected() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = TEST_SSID;
        wifiList.add(mWiFiConfiguration);

        WifiInfo mockWiFiInfo = mock(WifiInfo.class);
        when(mockWiFiInfo.getSSID()).thenReturn(TEST_SSID);

        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWiFiInfo);
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);

        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(true);
        when(mockNetworkInfo.isConnected()).thenReturn(false);

        boolean result = mWiseFy.connectToNetwork(TEST_SSID, 1);
        assertEquals(false, result);
    }

    @Test
    public void noCallbacks_failure_nullList() {
        setManagers();
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(null);
        boolean result = mWiseFy.connectToNetwork(TEST_SSID, 1);
        assertEquals(false, result);
    }

    @Test
    public void noCallbacks_failure_nullSSIDParam() {
        setManagers();
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(new ArrayList<WifiConfiguration>());
        boolean result = mWiseFy.connectToNetwork(null, 1);
        assertEquals(false, result);
    }

    @Test
    public void noCallbacks_failure_nullSSID() {
        setManagers();

        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = null;
        wifiList.add(mWiFiConfiguration);

        WifiInfo mockWiFiInfo = mock(WifiInfo.class);
        when(mockWiFiInfo.getSSID()).thenReturn(TEST_SSID);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWiFiInfo);

        boolean result = mWiseFy.connectToNetwork(TEST_SSID, 1);
        assertEquals(false, result);
    }

    @Test
    public void noCallbacks_failure_nullWifiConfiguration() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        wifiList.add(0, null);
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        boolean result = mWiseFy.connectToNetwork(TEST_SSID, 1);
        assertEquals(false, result);
    }

    @Test
    public void noCallbacks_failure_nullWifiManager() {
        setManagersToNull();
        boolean result = mWiseFy.connectToNetwork(TEST_SSID, 1);
        assertEquals(false, result);
    }

    @Test
    public void noCallbacks_success() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = TEST_SSID;
        wifiList.add(mWiFiConfiguration);

        WifiInfo mockWiFiInfo = mock(WifiInfo.class);
        when(mockWiFiInfo.getSSID()).thenReturn(TEST_SSID);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWiFiInfo);

        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(true);
        when(mockNetworkInfo.isConnected()).thenReturn(true);

        boolean result = mWiseFy.connectToNetwork(TEST_SSID, 1);
        assertEquals(true, result);
    }

    @Test
    public void noCallbacks_success_multipleNetworks() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();

        WifiConfiguration mWiFiConfiguration1 = new WifiConfiguration();
        mWiFiConfiguration1.SSID = TEST_SSID;
        wifiList.add(mWiFiConfiguration1);

        WifiConfiguration mWiFiConfiguration2 = new WifiConfiguration();
        mWiFiConfiguration2.SSID = TEST_SSID2;
        wifiList.add(mWiFiConfiguration2);

        WifiInfo mockWiFiInfo = mock(WifiInfo.class);
        when(mockWiFiInfo.getSSID()).thenReturn(TEST_SSID);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWiFiInfo);

        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(true);
        when(mockNetworkInfo.isConnected()).thenReturn(true);

        boolean result = mWiseFy.connectToNetwork(TEST_SSID, 1);
        assertEquals(true, result);
    }

    @Test
    public void callbacks_failure_emptyList() {
        setManagers();
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(new ArrayList<WifiConfiguration>());
        ConnectToNetworkCallbacks connectToNetworkCallbacks = mock(ConnectToNetworkCallbacks.class);
        mWiseFy.connectToNetwork(TEST_SSID, 1, connectToNetworkCallbacks);
        verify(connectToNetworkCallbacks, timeout(2000)).networkNotFoundToConnectTo();
    }

    @Test
    public void callbacks_failure_emptyList_nullCallback() {
        setManagers();
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(new ArrayList<WifiConfiguration>());
        ConnectToNetworkCallbacks connectToNetworkCallbacks = mock(ConnectToNetworkCallbacks.class);
        mWiseFy.connectToNetwork(TEST_SSID, 1, null);
        verify(connectToNetworkCallbacks, never()).networkNotFoundToConnectTo();
    }

    @Test
    public void callbacks_failure_multipleNetworks() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();

        WifiConfiguration mWiFiConfiguration1 = new WifiConfiguration();
        mWiFiConfiguration1.SSID = TEST_SSID2;
        wifiList.add(mWiFiConfiguration1);

        WifiConfiguration mWiFiConfiguration2 = new WifiConfiguration();
        mWiFiConfiguration2.SSID = TEST_SSID3;
        wifiList.add(mWiFiConfiguration2);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);

        ConnectToNetworkCallbacks connectToNetworkCallbacks = mock(ConnectToNetworkCallbacks.class);
        mWiseFy.connectToNetwork(TEST_SSID, 1, connectToNetworkCallbacks);
        verify(connectToNetworkCallbacks, timeout(2000)).networkNotFoundToConnectTo();
    }

    @Test
    public void callbacks_failure_multipleNetworks_nullCallback() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();

        WifiConfiguration mWiFiConfiguration1 = new WifiConfiguration();
        mWiFiConfiguration1.SSID = TEST_SSID2;
        wifiList.add(mWiFiConfiguration1);

        WifiConfiguration mWiFiConfiguration2 = new WifiConfiguration();
        mWiFiConfiguration2.SSID = TEST_SSID3;
        wifiList.add(mWiFiConfiguration2);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);

        ConnectToNetworkCallbacks connectToNetworkCallbacks = mock(ConnectToNetworkCallbacks.class);
        mWiseFy.connectToNetwork(TEST_SSID, 1, null);
        verify(connectToNetworkCallbacks, never()).networkNotFoundToConnectTo();
    }

    @Test
    public void callbacks_failure_notAvailable() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = TEST_SSID;
        wifiList.add(mWiFiConfiguration);

        WifiInfo mockWiFiInfo = mock(WifiInfo.class);
        when(mockWiFiInfo.getSSID()).thenReturn(TEST_SSID);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWiFiInfo);

        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(false);
        when(mockNetworkInfo.isConnected()).thenReturn(true);

        ConnectToNetworkCallbacks connectToNetworkCallbacks = mock(ConnectToNetworkCallbacks.class);
        mWiseFy.connectToNetwork(TEST_SSID, 1, connectToNetworkCallbacks);
        verify(connectToNetworkCallbacks, timeout(2000)).failureConnectingToNetwork();
    }

    @Test
    public void callbacks_failure_notAvailable_nullCallback() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = TEST_SSID;
        wifiList.add(mWiFiConfiguration);

        WifiInfo mockWiFiInfo = mock(WifiInfo.class);
        when(mockWiFiInfo.getSSID()).thenReturn(TEST_SSID);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWiFiInfo);

        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(false);
        when(mockNetworkInfo.isConnected()).thenReturn(true);

        ConnectToNetworkCallbacks connectToNetworkCallbacks = mock(ConnectToNetworkCallbacks.class);
        mWiseFy.connectToNetwork(TEST_SSID, 1, null);
        verify(connectToNetworkCallbacks, never()).failureConnectingToNetwork();
    }

    @Test
    public void callbacks_failure_notConnected() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = TEST_SSID;
        wifiList.add(mWiFiConfiguration);

        WifiInfo mockWiFiInfo = mock(WifiInfo.class);
        when(mockWiFiInfo.getSSID()).thenReturn(TEST_SSID);

        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWiFiInfo);
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);

        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(true);
        when(mockNetworkInfo.isConnected()).thenReturn(false);

        ConnectToNetworkCallbacks connectToNetworkCallbacks = mock(ConnectToNetworkCallbacks.class);
        mWiseFy.connectToNetwork(TEST_SSID, 1, connectToNetworkCallbacks);
        verify(connectToNetworkCallbacks, timeout(2000)).failureConnectingToNetwork();
    }

    @Test
    public void callbacks_failure_notConnected_nullCallback() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = TEST_SSID;
        wifiList.add(mWiFiConfiguration);

        WifiInfo mockWiFiInfo = mock(WifiInfo.class);
        when(mockWiFiInfo.getSSID()).thenReturn(TEST_SSID);

        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWiFiInfo);
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);

        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(true);
        when(mockNetworkInfo.isConnected()).thenReturn(false);

        ConnectToNetworkCallbacks connectToNetworkCallbacks = mock(ConnectToNetworkCallbacks.class);
        mWiseFy.connectToNetwork(TEST_SSID, 1, null);
        verify(connectToNetworkCallbacks, never()).failureConnectingToNetwork();
    }

    @Test
    public void callbacks_failure_nullList() {
        setManagers();
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(null);
        ConnectToNetworkCallbacks connectToNetworkCallbacks = mock(ConnectToNetworkCallbacks.class);
        mWiseFy.connectToNetwork(TEST_SSID, 1, connectToNetworkCallbacks);
        verify(connectToNetworkCallbacks, timeout(2000)).networkNotFoundToConnectTo();
    }

    @Test
    public void callbacks_failure_nullList_nullCallback() {
        setManagers();
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(null);
        ConnectToNetworkCallbacks connectToNetworkCallbacks = mock(ConnectToNetworkCallbacks.class);
        mWiseFy.connectToNetwork(TEST_SSID, 1, null);
        verify(connectToNetworkCallbacks, never()).networkNotFoundToConnectTo();
    }

    @Test
    public void callbacks_failure_nullSSIDParam() {
        setManagers();
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(new ArrayList<WifiConfiguration>());
        ConnectToNetworkCallbacks connectToNetworkCallbacks = mock(ConnectToNetworkCallbacks.class);
        mWiseFy.connectToNetwork(null, 1, connectToNetworkCallbacks);
        verify(connectToNetworkCallbacks, timeout(2000)).connectToNetworkWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
    }

    @Test
    public void callbacks_failure_nullSSIDParam_nullCallback() {
        setManagers();
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(new ArrayList<WifiConfiguration>());
        ConnectToNetworkCallbacks connectToNetworkCallbacks = mock(ConnectToNetworkCallbacks.class);
        mWiseFy.connectToNetwork(null, 1, null);
        verify(connectToNetworkCallbacks, never()).connectToNetworkWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
    }

    @Test
    public void callbacks_failure_nullSSID() {
        setManagers();

        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = null;
        wifiList.add(mWiFiConfiguration);

        WifiInfo mockWiFiInfo = mock(WifiInfo.class);
        when(mockWiFiInfo.getSSID()).thenReturn(TEST_SSID);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWiFiInfo);

        ConnectToNetworkCallbacks connectToNetworkCallbacks = mock(ConnectToNetworkCallbacks.class);
        mWiseFy.connectToNetwork(TEST_SSID, 1, connectToNetworkCallbacks);
        verify(connectToNetworkCallbacks, timeout(2000)).networkNotFoundToConnectTo();
    }

    @Test
    public void callbacks_failure_nullSSID_nullCallback() {
        setManagers();

        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = null;
        wifiList.add(mWiFiConfiguration);

        WifiInfo mockWiFiInfo = mock(WifiInfo.class);
        when(mockWiFiInfo.getSSID()).thenReturn(TEST_SSID);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWiFiInfo);

        ConnectToNetworkCallbacks connectToNetworkCallbacks = mock(ConnectToNetworkCallbacks.class);
        mWiseFy.connectToNetwork(TEST_SSID, 1, null);
        verify(connectToNetworkCallbacks,never()).networkNotFoundToConnectTo();
    }

    @Test
    public void callbacks_failure_nullWifiConfiguration() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        wifiList.add(0, null);
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        ConnectToNetworkCallbacks connectToNetworkCallbacks = mock(ConnectToNetworkCallbacks.class);
        mWiseFy.connectToNetwork(TEST_SSID, 1, connectToNetworkCallbacks);
        verify(connectToNetworkCallbacks,timeout(2000)).networkNotFoundToConnectTo();
    }

    @Test
    public void callbacks_failure_nullWifiConfiguration_nullCallback() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        wifiList.add(0, null);
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        ConnectToNetworkCallbacks connectToNetworkCallbacks = mock(ConnectToNetworkCallbacks.class);
        mWiseFy.connectToNetwork(TEST_SSID, 1, null);
        verify(connectToNetworkCallbacks,never()).networkNotFoundToConnectTo();
    }


    @Test
    public void callbacks_failure_nullWifiManager() {
        setManagersToNull();
        ConnectToNetworkCallbacks connectToNetworkCallbacks = mock(ConnectToNetworkCallbacks.class);
        mWiseFy.connectToNetwork(TEST_SSID, 1, connectToNetworkCallbacks);
        verify(connectToNetworkCallbacks, timeout(2000)).connectToNetworkWiseFyFailure(WiseFyCodes.NULL_MANAGER);
    }

    @Test
    public void callbacks_failure_nullWifiManager_nullCallback() {
        setManagersToNull();
        ConnectToNetworkCallbacks connectToNetworkCallbacks = mock(ConnectToNetworkCallbacks.class);
        mWiseFy.connectToNetwork(TEST_SSID, 1, null);
        verify(connectToNetworkCallbacks, never()).connectToNetworkWiseFyFailure(WiseFyCodes.NULL_MANAGER);
    }

    @Test
    public void callbacks_success() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = TEST_SSID;
        wifiList.add(mWiFiConfiguration);

        WifiInfo mockWiFiInfo = mock(WifiInfo.class);
        when(mockWiFiInfo.getSSID()).thenReturn(TEST_SSID);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWiFiInfo);

        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(true);
        when(mockNetworkInfo.isConnected()).thenReturn(true);

        ConnectToNetworkCallbacks connectToNetworkCallbacks = mock(ConnectToNetworkCallbacks.class);
        mWiseFy.connectToNetwork(TEST_SSID, 1, connectToNetworkCallbacks);
        verify(connectToNetworkCallbacks, timeout(2000)).connectedToNetwork();
    }

    @Test
    public void callbacks_success_nullCallback() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = TEST_SSID;
        wifiList.add(mWiFiConfiguration);

        WifiInfo mockWiFiInfo = mock(WifiInfo.class);
        when(mockWiFiInfo.getSSID()).thenReturn(TEST_SSID);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWiFiInfo);

        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(true);
        when(mockNetworkInfo.isConnected()).thenReturn(true);

        ConnectToNetworkCallbacks connectToNetworkCallbacks = mock(ConnectToNetworkCallbacks.class);
        mWiseFy.connectToNetwork(TEST_SSID, 1, null);
        verify(connectToNetworkCallbacks, never()).connectedToNetwork();
    }

    @Test
    public void callbacks_success_multipleNetworks() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();

        WifiConfiguration mWiFiConfiguration1 = new WifiConfiguration();
        mWiFiConfiguration1.SSID = TEST_SSID;
        wifiList.add(mWiFiConfiguration1);

        WifiConfiguration mWiFiConfiguration2 = new WifiConfiguration();
        mWiFiConfiguration2.SSID = TEST_SSID2;
        wifiList.add(mWiFiConfiguration2);

        WifiInfo mockWiFiInfo = mock(WifiInfo.class);
        when(mockWiFiInfo.getSSID()).thenReturn(TEST_SSID);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWiFiInfo);

        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(true);
        when(mockNetworkInfo.isConnected()).thenReturn(true);

        ConnectToNetworkCallbacks connectToNetworkCallbacks = mock(ConnectToNetworkCallbacks.class);
        mWiseFy.connectToNetwork(TEST_SSID, 1, connectToNetworkCallbacks);
        verify(connectToNetworkCallbacks, timeout(2000)).connectedToNetwork();
    }

    @Test
    public void callbacks_success_multipleNetworks_nullCallback() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();

        WifiConfiguration mWiFiConfiguration1 = new WifiConfiguration();
        mWiFiConfiguration1.SSID = TEST_SSID;
        wifiList.add(mWiFiConfiguration1);

        WifiConfiguration mWiFiConfiguration2 = new WifiConfiguration();
        mWiFiConfiguration2.SSID = TEST_SSID2;
        wifiList.add(mWiFiConfiguration2);

        WifiInfo mockWiFiInfo = mock(WifiInfo.class);
        when(mockWiFiInfo.getSSID()).thenReturn(TEST_SSID);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWiFiInfo);

        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(true);
        when(mockNetworkInfo.isConnected()).thenReturn(true);

        ConnectToNetworkCallbacks connectToNetworkCallbacks = mock(ConnectToNetworkCallbacks.class);
        mWiseFy.connectToNetwork(TEST_SSID, 1, null);
        verify(connectToNetworkCallbacks, never()).connectedToNetwork();
    }
}