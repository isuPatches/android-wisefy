package com.isupatches.wisefy.test;


import android.net.wifi.WifiInfo;
import com.isupatches.wisefy.callbacks.GetCurrentNetworkCallbacks;
import com.isupatches.wisefy.constants.WiseFyCodes;
import com.isupatches.wisefy.test.base.BaseTestClass;
import com.isupatches.wisefy.test.base.TestActivity;
import org.junit.Test;
import static com.isupatches.wisefy.test.base.TestUtils.TEST_SSID;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class GetCurrentNetworkTests extends BaseTestClass<TestActivity> {

    public GetCurrentNetworkTests() {
        super(TestActivity.class);
    }

    @Test
    public void noCallbacks_failure_nullWifiManager() {
        setManagersToNull();
        assertEquals(null, mWiseFy.getCurrentNetwork());
    }

    @Test
    public void noCallbacks_success() {
        setManagers();
        WifiInfo mockWifiInfo = mock(WifiInfo.class);
        when(mockWifiInfo.getSSID()).thenReturn(TEST_SSID);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWifiInfo);

        assertEquals(TEST_SSID, mWiseFy.getCurrentNetwork().getSSID());
    }

    @Test
    public void callbacks_failure_nullWifiManager() {
        setManagersToNull();
        GetCurrentNetworkCallbacks getCurrentNetworkCallbacks = mock(GetCurrentNetworkCallbacks.class);
        mWiseFy.getCurrentNetwork(getCurrentNetworkCallbacks);
        verify(getCurrentNetworkCallbacks, timeout(2000)).getCurrentNetworkWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
    }

    @Test
    public void callbacks_failure_nullWifiManager_nullCallbacks() {
        setManagersToNull();
        GetCurrentNetworkCallbacks getCurrentNetworkCallbacks = mock(GetCurrentNetworkCallbacks.class);
        mWiseFy.getCurrentNetwork(null);
        verify(getCurrentNetworkCallbacks, never()).getCurrentNetworkWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
    }

    @Test
    public void callbacks_success() {
        setManagers();
        WifiInfo mockWifiInfo = mock(WifiInfo.class);
        when(mockWifiInfo.getSSID()).thenReturn(TEST_SSID);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWifiInfo);

        GetCurrentNetworkCallbacks getCurrentNetworkCallbacks = mock(GetCurrentNetworkCallbacks.class);
        mWiseFy.getCurrentNetwork(getCurrentNetworkCallbacks);
        verify(getCurrentNetworkCallbacks, timeout(2000)).retrievedCurrentNetwork(mockWifiInfo);
    }

    @Test
    public void callbacks_success_nullCallback() {
        setManagers();
        WifiInfo mockWifiInfo = mock(WifiInfo.class);
        when(mockWifiInfo.getSSID()).thenReturn(TEST_SSID);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWifiInfo);

        GetCurrentNetworkCallbacks getCurrentNetworkCallbacks = mock(GetCurrentNetworkCallbacks.class);
        mWiseFy.getCurrentNetwork(null);
        verify(getCurrentNetworkCallbacks, never()).retrievedCurrentNetwork(mockWifiInfo);
    }
}
