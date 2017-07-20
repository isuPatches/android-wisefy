package com.isupatches.wisefy;


import com.isupatches.wisefy.base.TestActivity;
import com.isupatches.wisefy.callbacks.DisableWifiCallbacks;
import com.isupatches.wisefy.constants.WiseFyCodes;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class DisableWifiTests extends BaseTestClass<TestActivity> {

    public DisableWifiTests() {
        super(TestActivity.class);
    }

    @Test
    public void noCallbacks_failure() {
        when(mMockWiFiManager.setWifiEnabled(anyBoolean())).thenReturn(false);
        assertEquals(false, mWiseFy.disableWifi());
        verify(mMockWiFiManager, timeout(VERIFICATION_TIMEOUT)).setWifiEnabled(false);
    }

    @Test
    public void noCallbacks_failure_missingPrerequisite() {
        missingPrerequisite();
        assertEquals(false, mWiseFy.disableWifi());
        verify(mMockWiFiManager, never()).setWifiEnabled(false);
    }

    @Test
    public void noCallbacks_success() {
        when(mMockWiFiManager.setWifiEnabled(anyBoolean())).thenReturn(true);
        assertEquals(true, mWiseFy.disableWifi());
        verify(mMockWiFiManager, timeout(VERIFICATION_TIMEOUT)).setWifiEnabled(false);
    }

    @Test
    public void callbacks_failure() {
        when(mMockWiFiManager.setWifiEnabled(anyBoolean())).thenReturn(false);
        DisableWifiCallbacks mockCallbacks = mock(DisableWifiCallbacks.class);
        mWiseFy.disableWifi(mockCallbacks);
        verify(mMockWiFiManager, timeout(VERIFICATION_TIMEOUT)).setWifiEnabled(false);
        verify(mockCallbacks, timeout(VERIFICATION_TIMEOUT)).failureDisablingWifi();
    }

    @Test
    public void callbacks_failure_nullCallback() {
        when(mMockWiFiManager.setWifiEnabled(anyBoolean())).thenReturn(false);
        try {
            mWiseFy.disableWifi(null);
            verify(mMockWiFiManager, never()).setWifiEnabled(false);
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void callbacks_failure_missingPrerequisite() {
        missingPrerequisite();
        DisableWifiCallbacks mockCallbacks = mock(DisableWifiCallbacks.class);
        mWiseFy.disableWifi(mockCallbacks);
        verify(mMockWiFiManager, never()).setWifiEnabled(false);
        verify(mockCallbacks, timeout(VERIFICATION_TIMEOUT)).disableWifiWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
    }

    @Test
    public void callbacks_failure_missingPrerequisite_nullCallback() {
        missingPrerequisite();
        try {
            mWiseFy.disableWifi(null);
            verify(mMockWiFiManager, never()).setWifiEnabled(false);
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void callbacks_success() {
        when(mMockWiFiManager.setWifiEnabled(anyBoolean())).thenReturn(true);
        DisableWifiCallbacks mockCallbacks = mock(DisableWifiCallbacks.class);
        mWiseFy.disableWifi(mockCallbacks);
        verify(mMockWiFiManager, timeout(VERIFICATION_TIMEOUT)).setWifiEnabled(false);
        verify(mockCallbacks, timeout(VERIFICATION_TIMEOUT)).wifiDisabled();
    }

    @Test
    public void callbacks_success_nullCallback() {
        when(mMockWiFiManager.setWifiEnabled(anyBoolean())).thenReturn(true);
        try {
            mWiseFy.disableWifi(null);
            verify(mMockWiFiManager, never()).setWifiEnabled(false);
        } catch (NullPointerException npe) {
            fail();
        }
    }
}
