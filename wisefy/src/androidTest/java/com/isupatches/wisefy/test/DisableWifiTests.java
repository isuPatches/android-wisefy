package com.isupatches.wisefy.test;


import com.isupatches.wisefy.callbacks.DisableWifiCallbacks;
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


public class DisableWifiTests extends BaseTestClass<TestActivity> {

    public DisableWifiTests() {
        super(TestActivity.class);
    }

    @Test
    public void noCallbacks_failure() {
        setManagers();
        when(mMockWiFiManager.setWifiEnabled(anyBoolean())).thenReturn(false);
        boolean result = mWiseFy.disableWifi();
        verify(mMockWiFiManager, timeout(2000)).setWifiEnabled(false);
        assertEquals(false, result);
    }

    @Test
    public void noCallbacks_failure_nullWifiManager() {
        setManagersToNull();
        boolean result = mWiseFy.disableWifi();
        assertEquals(false, result);
    }

    @Test
    public void noCallbacks_success() {
        setManagers();
        when(mMockWiFiManager.setWifiEnabled(anyBoolean())).thenReturn(true);
        boolean result = mWiseFy.disableWifi();
        verify(mMockWiFiManager, timeout(2000)).setWifiEnabled(false);
        assertEquals(true, result);
    }

    @Test
    public void callbacks_failure() {
        setManagers();
        when(mMockWiFiManager.setWifiEnabled(anyBoolean())).thenReturn(false);
        DisableWifiCallbacks mockDisableWifiCallbacks = mock(DisableWifiCallbacks.class);
        mWiseFy.disableWifi(mockDisableWifiCallbacks);
        verify(mMockWiFiManager, timeout(2000)).setWifiEnabled(false);
        verify(mockDisableWifiCallbacks, timeout(2000)).failureDisablingWifi();
    }

    @Test
    public void callbacks_failure_nullCallback() {
        setManagers();
        when(mMockWiFiManager.setWifiEnabled(anyBoolean())).thenReturn(false);
        DisableWifiCallbacks mockDisableWifiCallbacks = mock(DisableWifiCallbacks.class);
        mWiseFy.disableWifi(null);
        verify(mMockWiFiManager, timeout(2000)).setWifiEnabled(false);
        verify(mockDisableWifiCallbacks, never()).failureDisablingWifi();
    }

    @Test
    public void callbacks_failure_nullWifiManager() {
        setManagersToNull();
        DisableWifiCallbacks mockDisableWifiCallbacks = mock(DisableWifiCallbacks.class);
        mWiseFy.disableWifi(mockDisableWifiCallbacks);
        verify(mMockWiFiManager, never()).setWifiEnabled(false);
        verify(mockDisableWifiCallbacks, timeout(2000)).disableWifiWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
    }

    @Test
    public void callbacks_failure_nullWifiManager_nullCallback() {
        setManagersToNull();
        DisableWifiCallbacks mockDisableWifiCallbacks = mock(DisableWifiCallbacks.class);
        mWiseFy.disableWifi(null);
        verify(mMockWiFiManager, never()).setWifiEnabled(false);
        verify(mockDisableWifiCallbacks, never()).disableWifiWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
    }

    @Test
    public void callbacks_success() {
        setManagers();
        when(mMockWiFiManager.setWifiEnabled(anyBoolean())).thenReturn(true);
        DisableWifiCallbacks mockDisableWifiCallbacks = mock(DisableWifiCallbacks.class);
        mWiseFy.disableWifi(mockDisableWifiCallbacks);
        verify(mMockWiFiManager, timeout(2000)).setWifiEnabled(false);
        verify(mockDisableWifiCallbacks, timeout(2000)).wifiDisabled();
    }

    @Test
    public void callbacks_success_nullCallback() {
        setManagers();
        when(mMockWiFiManager.setWifiEnabled(anyBoolean())).thenReturn(true);
        DisableWifiCallbacks mockDisableWifiCallbacks = mock(DisableWifiCallbacks.class);
        mWiseFy.disableWifi(null);
        verify(mMockWiFiManager, timeout(2000)).setWifiEnabled(false);
        verify(mockDisableWifiCallbacks, never()).wifiDisabled();
    }
}
