package com.isupatches.wisefy;


import com.isupatches.wisefy.base.TestActivity;
import com.isupatches.wisefy.callbacks.DisconnectFromCurrentNetworkCallbacks;
import com.isupatches.wisefy.constants.WiseFyCodes;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
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
        when(mMockWiFiManager.disconnect()).thenReturn(false);
        boolean result = mWiseFy.disconnectFromCurrentNetwork();
        verify(mMockWiFiManager, timeout(2000)).disconnect();
        assertEquals(false, result);
    }

    @Test
    public void noCallbacks_failure_missingPrerequisite() {
        missingPrerequisite();
        boolean result = mWiseFy.disconnectFromCurrentNetwork();
        assertEquals(false, result);
    }

    @Test
    public void noCallbacks_success() {
        when(mMockWiFiManager.disconnect()).thenReturn(true);
        boolean result = mWiseFy.disconnectFromCurrentNetwork();
        verify(mMockWiFiManager, timeout(2000)).disconnect();
        assertEquals(true, result);
    }

    @Test
    public void callbacks_failure() {
        when(mMockWiFiManager.disconnect()).thenReturn(false);
        DisconnectFromCurrentNetworkCallbacks mockCallbacks = mock(DisconnectFromCurrentNetworkCallbacks.class);
        mWiseFy.disconnectFromCurrentNetwork(mockCallbacks);
        verify(mMockWiFiManager, timeout(2000)).disconnect();
        verify(mockCallbacks, timeout(2000)).failureDisconnectingFromCurrentNetwork();
    }

    @Test
    public void callbacks_failure_nullCallback() {
        when(mMockWiFiManager.disconnect()).thenReturn(false);
        try {
            mWiseFy.disconnectFromCurrentNetwork(null);
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void callbacks_failure_missingPrerequisite() {
        missingPrerequisite();
        DisconnectFromCurrentNetworkCallbacks mockCallbacks = mock(DisconnectFromCurrentNetworkCallbacks.class);
        mWiseFy.disconnectFromCurrentNetwork(mockCallbacks);
        verify(mMockWiFiManager, never()).disconnect();
        verify(mockCallbacks, timeout(2000)).disconnectFromCurrentNetworkWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
    }

    @Test
    public void callbacks_failure_missingPrerequisite_nullCallback() {
        missingPrerequisite();
        try {
            mWiseFy.disconnectFromCurrentNetwork(null);
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void callbacks_success() {
        when(mMockWiFiManager.disconnect()).thenReturn(true);
        DisconnectFromCurrentNetworkCallbacks mockCallbacks = mock(DisconnectFromCurrentNetworkCallbacks.class);
        mWiseFy.disconnectFromCurrentNetwork(mockCallbacks);
        verify(mMockWiFiManager, timeout(2000)).disconnect();
        verify(mockCallbacks, timeout(2000)).disconnectedFromCurrentNetwork();
    }

    @Test
    public void callbacks_success_nullCallback() {
        when(mMockWiFiManager.disconnect()).thenReturn(true);
        try {
            mWiseFy.disconnectFromCurrentNetwork(null);
        } catch (NullPointerException npe) {
            fail();
        }
    }
}
