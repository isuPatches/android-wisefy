package com.isupatches.wisefy;


import com.isupatches.wisefy.base.TestActivity;
import com.isupatches.wisefy.callbacks.EnableWifiCallbacks;
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


public class EnableWifiTests extends BaseTestClass<TestActivity> {

    public EnableWifiTests() {
        super(TestActivity.class);
    }

    @Test
    public void noCallbacks_failure() {
        when(mMockWiFiManager.setWifiEnabled(anyBoolean())).thenReturn(false);
        assertEquals(false, mWiseFy.enableWifi());
        verify(mMockWiFiManager, timeout(VERIFICATION_TIMEOUT)).setWifiEnabled(true);
    }

    @Test
    public void noCallbacks_failure_missingPrerequisite() {
        missingPrerequisite();
        assertEquals(false, mWiseFy.enableWifi());
        verify(mMockWiFiManager, never()).setWifiEnabled(true);
    }

    @Test
    public void noCallbacks_success() {
        when(mMockWiFiManager.setWifiEnabled(anyBoolean())).thenReturn(true);
        assertEquals(true, mWiseFy.enableWifi());
        verify(mMockWiFiManager, timeout(VERIFICATION_TIMEOUT)).setWifiEnabled(true);
    }

    @Test
    public void callbacks_failure() {
        when(mMockWiFiManager.setWifiEnabled(anyBoolean())).thenReturn(false);
        EnableWifiCallbacks mockCallbacks = mock(EnableWifiCallbacks.class);
        mWiseFy.enableWifi(mockCallbacks);
        verify(mMockWiFiManager, timeout(VERIFICATION_TIMEOUT)).setWifiEnabled(true);
        verify(mockCallbacks, timeout(VERIFICATION_TIMEOUT)).failureEnablingWifi();
    }

    @Test
    public void callbacks_failure_nullCallback() {
        when(mMockWiFiManager.setWifiEnabled(anyBoolean())).thenReturn(false);
        try {
            mWiseFy.enableWifi(null);
            verify(mMockWiFiManager, never()).setWifiEnabled(true);
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void callbacks_failure_missingPrerequisite() {
        missingPrerequisite();
        EnableWifiCallbacks mockCallbacks = mock(EnableWifiCallbacks.class);
        mWiseFy.enableWifi(mockCallbacks);
        verify(mMockWiFiManager, never()).setWifiEnabled(true);
        verify(mockCallbacks, timeout(VERIFICATION_TIMEOUT)).enableWifiWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
    }

    @Test
    public void callbacks_failure_missingPrerequisite_nullCallback() {
        missingPrerequisite();
        try {
            mWiseFy.enableWifi(null);
            verify(mMockWiFiManager, never()).setWifiEnabled(true);
        } catch (NullPointerException npe) {
            fail();
        }
    }

    @Test
    public void callbacks_success() {
        when(mMockWiFiManager.setWifiEnabled(anyBoolean())).thenReturn(true);
        EnableWifiCallbacks mockCallbacks = mock(EnableWifiCallbacks.class);
        mWiseFy.enableWifi(mockCallbacks);
        verify(mMockWiFiManager, timeout(VERIFICATION_TIMEOUT)).setWifiEnabled(true);
        verify(mockCallbacks, timeout(VERIFICATION_TIMEOUT)).wifiEnabled();
    }

    @Test
    public void callbacks_success_nullCallback() {
        when(mMockWiFiManager.setWifiEnabled(anyBoolean())).thenReturn(true);
        try {
            mWiseFy.enableWifi(null);
            verify(mMockWiFiManager, never()).setWifiEnabled(true);
        } catch (NullPointerException npe) {
            fail();
        }
    }
}
