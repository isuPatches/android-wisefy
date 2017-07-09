package com.isupatches.wisefy.test;


import com.isupatches.wisefy.callbacks.DisconnectFromCurrentNetworkCallbacks;
import com.isupatches.wisefy.constants.WiseFyCodes;
import com.isupatches.wisefy.test.base.BaseTestClass;
import com.isupatches.wisefy.test.base.TestActivity;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class DisconnectFromCurrentNetworkTests extends BaseTestClass<TestActivity> {

    public DisconnectFromCurrentNetworkTests() {
        super(TestActivity.class);
    }

    @Test
    public void noCallbacks_failure() {
        setManagers();
        when(mMockWiFiManager.disconnect()).thenReturn(false);
        boolean result = mWiseFy.disconnectFromCurrentNetwork();
        verify(mMockWiFiManager, timeout(2000)).disconnect();
        assertEquals(false, result);
    }

    @Test
    public void noCallbacks_failure_nullWifiManager() {
        setManagersToNull();
        boolean result = mWiseFy.disconnectFromCurrentNetwork();
        assertEquals(false, result);
    }

    @Test
    public void noCallbacks_success() {
        setManagers();
        when(mMockWiFiManager.disconnect()).thenReturn(true);
        boolean result = mWiseFy.disconnectFromCurrentNetwork();
        verify(mMockWiFiManager, timeout(2000)).disconnect();
        assertEquals(true, result);
    }

    @Test
    public void callbacks_failure() {
        setManagers();
        when(mMockWiFiManager.disconnect()).thenReturn(false);
        DisconnectFromCurrentNetworkCallbacks disconnectFromCurrentNetworkCallbacks = mock(DisconnectFromCurrentNetworkCallbacks.class);
        mWiseFy.disconnectFromCurrentNetwork(disconnectFromCurrentNetworkCallbacks);
        verify(mMockWiFiManager, timeout(2000)).disconnect();
        verify(disconnectFromCurrentNetworkCallbacks, timeout(2000)).failureDisconnectingFromCurrentNetwork();
    }

    @Test
    public void callbacks_failure_nullCallback() {
        setManagers();
        when(mMockWiFiManager.disconnect()).thenReturn(false);
        DisconnectFromCurrentNetworkCallbacks disconnectFromCurrentNetworkCallbacks = mock(DisconnectFromCurrentNetworkCallbacks.class);
        mWiseFy.disconnectFromCurrentNetwork(null);
        verify(mMockWiFiManager, timeout(2000)).disconnect();
        verify(disconnectFromCurrentNetworkCallbacks, never()).failureDisconnectingFromCurrentNetwork();
    }

    @Test
    public void callbacks_failure_nullWifiManager() {
        setManagersToNull();
        DisconnectFromCurrentNetworkCallbacks disconnectFromCurrentNetworkCallbacks = mock(DisconnectFromCurrentNetworkCallbacks.class);
        mWiseFy.disconnectFromCurrentNetwork(disconnectFromCurrentNetworkCallbacks);
        verify(mMockWiFiManager, never()).disconnect();
        verify(disconnectFromCurrentNetworkCallbacks, timeout(2000)).disconnectFromCurrentNetworkWiseFyFailure(WiseFyCodes.NULL_MANAGER);
    }

    @Test
    public void callbacks_failure_nullWifiManager_nullCallback() {
        setManagersToNull();
        DisconnectFromCurrentNetworkCallbacks disconnectFromCurrentNetworkCallbacks = mock(DisconnectFromCurrentNetworkCallbacks.class);
        mWiseFy.disconnectFromCurrentNetwork(null);
        verify(mMockWiFiManager, never()).disconnect();
        verify(disconnectFromCurrentNetworkCallbacks, never()).disconnectFromCurrentNetworkWiseFyFailure(WiseFyCodes.NULL_MANAGER);
    }

    @Test
    public void callbacks_success() {
        setManagers();
        when(mMockWiFiManager.disconnect()).thenReturn(true);
        DisconnectFromCurrentNetworkCallbacks disconnectFromCurrentNetworkCallbacks = mock(DisconnectFromCurrentNetworkCallbacks.class);
        mWiseFy.disconnectFromCurrentNetwork(disconnectFromCurrentNetworkCallbacks);
        verify(mMockWiFiManager, timeout(2000)).disconnect();
        verify(disconnectFromCurrentNetworkCallbacks, timeout(2000)).disconnectedFromCurrentNetwork();
    }

    @Test
    public void callbacks_success_nullCallback() {
        setManagers();
        when(mMockWiFiManager.disconnect()).thenReturn(true);
        DisconnectFromCurrentNetworkCallbacks disconnectFromCurrentNetworkCallbacks = mock(DisconnectFromCurrentNetworkCallbacks.class);
        mWiseFy.disconnectFromCurrentNetwork(null);
        verify(mMockWiFiManager, timeout(2000)).disconnect();
        verify(disconnectFromCurrentNetworkCallbacks, never()).disconnectedFromCurrentNetwork();
    }
}
