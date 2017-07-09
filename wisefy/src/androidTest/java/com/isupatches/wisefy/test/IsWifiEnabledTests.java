package com.isupatches.wisefy.test;


import com.isupatches.wisefy.test.base.BaseTestClass;
import com.isupatches.wisefy.test.base.TestActivity;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


public class IsWifiEnabledTests extends BaseTestClass<TestActivity> {

    public IsWifiEnabledTests() {
        super(TestActivity.class);
    }

    @Test
    public void failure() {
        setManagers();
        when(mMockWiFiManager.isWifiEnabled()).thenReturn(false);
        assertEquals(false, mWiseFy.isWifiEnabled());
    }

    @Test
    public void failure_nullWifiManager() {
        setManagersToNull();
        assertEquals(false, mWiseFy.isWifiEnabled());
    }

    @Test
    public void success() {
        setManagers();
        when(mMockWiFiManager.isWifiEnabled()).thenReturn(true);
        assertEquals(true, mWiseFy.isWifiEnabled());
    }
}