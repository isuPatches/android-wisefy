package com.isupatches.wisefy;


import com.isupatches.wisefy.callbacks.DisableWifiCallbacks;
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


public class DisableWifiTests extends BaseAndroidJUnit4TestClass {

    @Test
    public void sync_failure() {
        when(mMockWiFiManager.setWifiEnabled(anyBoolean())).thenReturn(false);
        assertEquals(false, mWiseFy.disableWifi());
        verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).setWifiEnabled(false);
    }

    @Test
    public void sync_failure_missingPrerequisite() {
        missingPrerequisite();
        assertEquals(false, mWiseFy.disableWifi());
        verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).setWifiEnabled(false);
    }

    @Test
    public void sync_success() {
        when(mMockWiFiManager.setWifiEnabled(anyBoolean())).thenReturn(true);
        assertEquals(true, mWiseFy.disableWifi());
        verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).setWifiEnabled(false);
    }

    @Test
    public void async_failure() {
        when(mMockWiFiManager.setWifiEnabled(anyBoolean())).thenReturn(false);
        DisableWifiCallbacks mockCallbacks = mock(DisableWifiCallbacks.class);
        mWiseFy.disableWifi(mockCallbacks);
        verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).setWifiEnabled(false);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).failureDisablingWifi();
    }

    @Test
    public void async_failure_nullCallback() {
        when(mMockWiFiManager.setWifiEnabled(anyBoolean())).thenReturn(false);
        try {
            mWiseFy.disableWifi(null);
            verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).setWifiEnabled(false);
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void async_failure_missingPrerequisite() {
        missingPrerequisite();
        DisableWifiCallbacks mockCallbacks = mock(DisableWifiCallbacks.class);
        mWiseFy.disableWifi(mockCallbacks);
        verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).setWifiEnabled(false);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).disableWifiWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
    }

    @Test
    public void async_failure_missingPrerequisite_nullCallback() {
        missingPrerequisite();
        try {
            mWiseFy.disableWifi(null);
            verify(mMockWiFiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).setWifiEnabled(false);
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void async_success() {
        when(mMockWiFiManager.setWifiEnabled(anyBoolean())).thenReturn(true);
        DisableWifiCallbacks mockCallbacks = mock(DisableWifiCallbacks.class);
        mWiseFy.disableWifi(mockCallbacks);
        verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).setWifiEnabled(false);
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).wifiDisabled();
    }

    @Test
    public void async_success_nullCallback() {
        when(mMockWiFiManager.setWifiEnabled(anyBoolean())).thenReturn(true);
        try {
            mWiseFy.disableWifi(null);
            verify(mMockWiFiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).setWifiEnabled(false);
        } catch (NullPointerException npe) {
            fail();
        }
    }
}
