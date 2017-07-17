package com.isupatches.wisefy;


import android.net.wifi.WifiInfo;
import com.isupatches.wisefy.base.TestActivity;
import com.isupatches.wisefy.callbacks.GetCurrentNetworkCallbacks;
import com.isupatches.wisefy.constants.WiseFyCodes;
import org.junit.Test;
import static com.isupatches.wisefy.base.TestUtils.TEST_SSID;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
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
    public void noCallbacks_failure_missingPrerequisite() {
        missingPrerequisite();
        assertEquals(null, mWiseFy.getCurrentNetwork());
        verify(mMockWiFiManager, never()).getConnectionInfo();
    }

    @Test
    public void noCallbacks_success() {
        WifiInfo mockWifiInfo = mock(WifiInfo.class);
        when(mockWifiInfo.getSSID()).thenReturn(TEST_SSID);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWifiInfo);

        assertEquals(TEST_SSID, mWiseFy.getCurrentNetwork().getSSID());
        verify(mMockWiFiManager, timeout(VERIFICATION_TIMEOUT)).getConnectionInfo();
    }

    @Test
    public void callbacks_failure_missingPrerequisite() {
        missingPrerequisite();
        GetCurrentNetworkCallbacks mockCallbacks = mock(GetCurrentNetworkCallbacks.class);
        mWiseFy.getCurrentNetwork(mockCallbacks);
        verify(mockCallbacks, timeout(VERIFICATION_TIMEOUT)).getCurrentNetworkWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
        verify(mMockWiFiManager, never()).getConnectionInfo();
    }

    @Test
    public void callbacks_failure_missingPrerequisite_nullCallbacks() {
        missingPrerequisite();
        try {
            mWiseFy.getCurrentNetwork(null);
            verify(mMockWiFiManager, never()).getConnectionInfo();
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void callbacks_success() {
        WifiInfo mockWifiInfo = mock(WifiInfo.class);
        when(mockWifiInfo.getSSID()).thenReturn(TEST_SSID);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWifiInfo);

        GetCurrentNetworkCallbacks mockCallbacks = mock(GetCurrentNetworkCallbacks.class);
        mWiseFy.getCurrentNetwork(mockCallbacks);
        verify(mockCallbacks, timeout(2000)).retrievedCurrentNetwork(mockWifiInfo);
        verify(mMockWiFiManager, timeout(VERIFICATION_TIMEOUT)).getConnectionInfo();
    }

    @Test
    public void callbacks_success_nullCallback() {
        WifiInfo mockWifiInfo = mock(WifiInfo.class);
        when(mockWifiInfo.getSSID()).thenReturn(TEST_SSID);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWifiInfo);

        try {
            mWiseFy.getCurrentNetwork(null);
            verify(mMockWiFiManager, never()).getConnectionInfo();
        } catch (NullPointerException npe) {
            fail();
        }
    }
}
