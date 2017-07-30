package com.isupatches.wisefy;


import com.isupatches.wisefy.callbacks.EnableWifiCallbacks;
import com.isupatches.wisefy.constants.WiseFyCodes;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.after;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class EnableWifiTests extends BaseAndroidJUnit4TestClass {

    @Test
    public void sync_failure() {
        when(mMockWiFiManager.setWifiEnabled(anyBoolean())).thenReturn(false);
        assertEquals(false, mWiseFy.enableWifi());
        verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).setWifiEnabled(true);
    }

    @Test
    public void sync_failure_missingPrerequisite() {
        missingPrerequisite();
        assertEquals(false, mWiseFy.enableWifi());
        verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).setWifiEnabled(true);
    }

    @Test
    public void sync_success() {
        when(mMockWiFiManager.setWifiEnabled(anyBoolean())).thenReturn(true);
        assertEquals(true, mWiseFy.enableWifi());
        verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).setWifiEnabled(true);
    }

    @Test
    public void async_failure() {
        when(mMockWiFiManager.setWifiEnabled(anyBoolean())).thenReturn(false);
        EnableWifiCallbacks mockCallbacks = mock(EnableWifiCallbacks.class);
        mWiseFy.enableWifi(mockCallbacks);
        verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).setWifiEnabled(true);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).failureEnablingWifi();
    }

    @Test
    public void async_failure_nullCallback() {
        when(mMockWiFiManager.setWifiEnabled(anyBoolean())).thenReturn(false);
        try {
            mWiseFy.enableWifi(null);
            verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).setWifiEnabled(true);
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void async_failure_missingPrerequisite() {
        missingPrerequisite();
        EnableWifiCallbacks mockCallbacks = mock(EnableWifiCallbacks.class);
        mWiseFy.enableWifi(mockCallbacks);
        verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).setWifiEnabled(true);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).enableWifiWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
    }

    @Test
    public void async_failure_missingPrerequisite_nullCallback() {
        missingPrerequisite();
        try {
            mWiseFy.enableWifi(null);
            verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).setWifiEnabled(true);
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void async_success() {
        when(mMockWiFiManager.setWifiEnabled(anyBoolean())).thenReturn(true);
        EnableWifiCallbacks mockCallbacks = mock(EnableWifiCallbacks.class);
        mWiseFy.enableWifi(mockCallbacks);
        verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).setWifiEnabled(true);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).wifiEnabled();
    }

    @Test
    public void async_success_nullCallback() {
        when(mMockWiFiManager.setWifiEnabled(anyBoolean())).thenReturn(true);
        try {
            mWiseFy.enableWifi(null);
            verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).setWifiEnabled(true);
        } catch (NullPointerException npe) {
            fail();
        }
    }
}
