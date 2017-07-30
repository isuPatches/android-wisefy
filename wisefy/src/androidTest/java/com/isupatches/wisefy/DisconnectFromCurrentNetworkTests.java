package com.isupatches.wisefy;


import com.isupatches.wisefy.callbacks.DisconnectFromCurrentNetworkCallbacks;
import com.isupatches.wisefy.constants.WiseFyCodes;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.after;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class DisconnectFromCurrentNetworkTests extends BaseAndroidJUnit4TestClass {

    @Test
    public void sync_failure() {
        when(mMockWiFiManager.disconnect()).thenReturn(false);
        assertEquals(false, mWiseFy.disconnectFromCurrentNetwork());
        verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).disconnect();
    }

    @Test
    public void sync_failure_missingPrerequisite() {
        missingPrerequisite();
        assertEquals(false, mWiseFy.disconnectFromCurrentNetwork());
        verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).disconnect();
    }

    @Test
    public void sync_success() {
        when(mMockWiFiManager.disconnect()).thenReturn(true);
        assertEquals(true, mWiseFy.disconnectFromCurrentNetwork());
        verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).disconnect();
    }

    @Test
    public void async_failure() {
        when(mMockWiFiManager.disconnect()).thenReturn(false);
        DisconnectFromCurrentNetworkCallbacks mockCallbacks = mock(DisconnectFromCurrentNetworkCallbacks.class);
        mWiseFy.disconnectFromCurrentNetwork(mockCallbacks);
        verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).disconnect();
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).failureDisconnectingFromCurrentNetwork();
    }

    @Test
    public void async_failure_nullCallback() {
        when(mMockWiFiManager.disconnect()).thenReturn(false);
        try {
            mWiseFy.disconnectFromCurrentNetwork(null);
            verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).disconnect();
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void async_failure_missingPrerequisite() {
        missingPrerequisite();
        DisconnectFromCurrentNetworkCallbacks mockCallbacks = mock(DisconnectFromCurrentNetworkCallbacks.class);
        mWiseFy.disconnectFromCurrentNetwork(mockCallbacks);
        verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).disconnect();
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).disconnectFromCurrentNetworkWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
    }

    @Test
    public void async_failure_missingPrerequisite_nullCallback() {
        missingPrerequisite();
        try {
            mWiseFy.disconnectFromCurrentNetwork(null);
            verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).disconnect();
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void async_success() {
        when(mMockWiFiManager.disconnect()).thenReturn(true);
        DisconnectFromCurrentNetworkCallbacks mockCallbacks = mock(DisconnectFromCurrentNetworkCallbacks.class);
        mWiseFy.disconnectFromCurrentNetwork(mockCallbacks);
        verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).disconnect();
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).disconnectedFromCurrentNetwork();
    }

    @Test
    public void async_success_nullCallback() {
        when(mMockWiFiManager.disconnect()).thenReturn(true);
        try {
            mWiseFy.disconnectFromCurrentNetwork(null);
            verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).disconnect();
        } catch (NullPointerException npe) {
            fail();
        }
    }
}
