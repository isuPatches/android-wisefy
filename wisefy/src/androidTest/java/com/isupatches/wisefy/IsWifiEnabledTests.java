package com.isupatches.wisefy;


import com.isupatches.wisefy.base.TestActivity;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


public class IsWifiEnabledTests extends BaseTestClass<TestActivity> {

    public IsWifiEnabledTests() {
        super(TestActivity.class);
    }

    @Test
    public void failure() {
        when(mMockWiFiManager.isWifiEnabled()).thenReturn(false);
        assertEquals(false, mWiseFy.isWifiEnabled());
    }

    @Test
    public void failure_missingPrerequisite() {
        missingPrerequisite();
        assertEquals(false, mWiseFy.isWifiEnabled());
    }

    @Test
    public void success() {
        when(mMockWiFiManager.isWifiEnabled()).thenReturn(true);
        assertEquals(true, mWiseFy.isWifiEnabled());
    }
}