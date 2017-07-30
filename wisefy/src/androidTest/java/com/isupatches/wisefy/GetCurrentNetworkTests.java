package com.isupatches.wisefy;


import android.net.wifi.WifiInfo;
import com.isupatches.wisefy.callbacks.GetCurrentNetworkCallbacks;
import com.isupatches.wisefy.constants.WiseFyCodes;
import org.junit.Test;
import static com.isupatches.wisefy.base.TestUtils.TEST_SSID;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import static org.mockito.Mockito.after;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class GetCurrentNetworkTests extends BaseAndroidJUnit4TestClass {

    @Test
    public void sync_failure_missingPrerequisite() {
        missingPrerequisite();
        assertEquals(null, mWiseFy.getCurrentNetwork());
        verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).getConnectionInfo();
    }

    @Test
    public void sync_success() {
        WifiInfo mockWifiInfo = mock(WifiInfo.class);
        when(mockWifiInfo.getSSID()).thenReturn(TEST_SSID);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWifiInfo);

        assertEquals(TEST_SSID, mWiseFy.getCurrentNetwork().getSSID());
        verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).getConnectionInfo();
    }

    @Test
    public void async_failure_missingPrerequisite() {
        missingPrerequisite();
        GetCurrentNetworkCallbacks mockCallbacks = mock(GetCurrentNetworkCallbacks.class);
        mWiseFy.getCurrentNetwork(mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).getCurrentNetworkWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
        verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).getConnectionInfo();
    }

    @Test
    public void async_failure_missingPrerequisite_nullCallbacks() {
        missingPrerequisite();
        try {
            mWiseFy.getCurrentNetwork(null);
            verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).getConnectionInfo();
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void async_success() {
        WifiInfo mockWifiInfo = mock(WifiInfo.class);
        when(mockWifiInfo.getSSID()).thenReturn(TEST_SSID);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWifiInfo);

        GetCurrentNetworkCallbacks mockCallbacks = mock(GetCurrentNetworkCallbacks.class);
        mWiseFy.getCurrentNetwork(mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).retrievedCurrentNetwork(mockWifiInfo);
        verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).getConnectionInfo();
    }

    @Test
    public void async_success_nullCallback() {
        WifiInfo mockWifiInfo = mock(WifiInfo.class);
        when(mockWifiInfo.getSSID()).thenReturn(TEST_SSID);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWifiInfo);

        try {
            mWiseFy.getCurrentNetwork(null);
            verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).getConnectionInfo();
        } catch (NullPointerException npe) {
            fail();
        }
    }
}
