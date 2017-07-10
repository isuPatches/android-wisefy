package com.isupatches.wisefy.test;


import com.isupatches.wisefy.callbacks.EnableWifiCallbacks;
import com.isupatches.wisefy.constants.WiseFyCodes;
import com.isupatches.wisefy.test.base.BaseTestClass;
import com.isupatches.wisefy.test.base.TestActivity;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
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
        setManagers();
        when(mMockWiFiManager.setWifiEnabled(anyBoolean())).thenReturn(false);
        boolean result = mWiseFy.enableWifi();
        verify(mMockWiFiManager, timeout(2000)).setWifiEnabled(true);
        assertEquals(false, result);
    }

    @Test
    public void noCallbacks_failure_nullWifiManager() {
        setManagersToNull();
        boolean result = mWiseFy.enableWifi();
        assertEquals(false, result);
    }

    @Test
    public void noCallbacks_success() {
        setManagers();
        when(mMockWiFiManager.setWifiEnabled(anyBoolean())).thenReturn(true);
        boolean result = mWiseFy.enableWifi();
        verify(mMockWiFiManager, timeout(2000)).setWifiEnabled(true);
        assertEquals(true, result);
    }

    @Test
    public void callbacks_failure() {
        setManagers();
        when(mMockWiFiManager.setWifiEnabled(anyBoolean())).thenReturn(false);
        EnableWifiCallbacks enableWifiCallbacks = mock(EnableWifiCallbacks.class);
        mWiseFy.enableWifi(enableWifiCallbacks);
        verify(mMockWiFiManager, timeout(2000)).setWifiEnabled(true);
        verify(enableWifiCallbacks, timeout(2000)).failureEnablingWifi();
    }

    @Test
    public void callbacks_failure_nullCallback() {
        setManagers();
        when(mMockWiFiManager.setWifiEnabled(anyBoolean())).thenReturn(false);
        EnableWifiCallbacks enableWifiCallbacks = mock(EnableWifiCallbacks.class);
        mWiseFy.enableWifi(null);
        verify(mMockWiFiManager, timeout(2000)).setWifiEnabled(true);
        verify(enableWifiCallbacks, never()).failureEnablingWifi();
    }

    @Test
    public void callbacks_failure_nullWifiManager() {
        setManagersToNull();
        EnableWifiCallbacks enableWifiCallbacks = mock(EnableWifiCallbacks.class);
        mWiseFy.enableWifi(enableWifiCallbacks);
        verify(mMockWiFiManager, never()).setWifiEnabled(true);
        verify(enableWifiCallbacks, timeout(2000)).enableWifiWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
    }

    @Test
    public void callbacks_failure_nullWifiManager_nullCallback() {
        setManagersToNull();
        EnableWifiCallbacks enableWifiCallbacks = mock(EnableWifiCallbacks.class);
        mWiseFy.enableWifi(null);
        verify(mMockWiFiManager, never()).setWifiEnabled(true);
        verify(enableWifiCallbacks, never()).enableWifiWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
    }

    @Test
    public void callbacks_success() {
        setManagers();
        when(mMockWiFiManager.setWifiEnabled(anyBoolean())).thenReturn(true);
        EnableWifiCallbacks enableWifiCallbacks = mock(EnableWifiCallbacks.class);
        mWiseFy.enableWifi(enableWifiCallbacks);
        verify(mMockWiFiManager, timeout(2000)).setWifiEnabled(true);
        verify(enableWifiCallbacks, timeout(2000)).wifiEnabled();
    }

    @Test
    public void callbacks_success_nullCallback() {
        setManagers();
        when(mMockWiFiManager.setWifiEnabled(anyBoolean())).thenReturn(true);
        EnableWifiCallbacks enableWifiCallbacks = mock(EnableWifiCallbacks.class);
        mWiseFy.enableWifi(null);
        verify(mMockWiFiManager, timeout(2000)).setWifiEnabled(true);
        verify(enableWifiCallbacks, never()).wifiEnabled();
    }
}
