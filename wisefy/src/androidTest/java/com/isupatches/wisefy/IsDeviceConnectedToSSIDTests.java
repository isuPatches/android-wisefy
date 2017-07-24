package com.isupatches.wisefy;


import org.junit.Test;
import static com.isupatches.wisefy.base.TestUtils.TEST_SSID;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class IsDeviceConnectedToSSIDTests extends BaseAndroidJUnit4TestClass {

    @Test
    public void noCallbacks_failure_nullSSIDParams() {
        assertFalse(mWiseFy.isDeviceConnectedToSSID(null));
    }

    @Test
    public void noCallbacks_failure_missingPrerequisite() {
        missingPrerequisite();
        assertFalse(mWiseFy.isDeviceConnectedToSSID(TEST_SSID));
    }

    @Test
    public void noCallbacks_failure() {
        WiseFyConnection mockWiseFyConnection = mock(WiseFyConnection.class);
        mWiseFy.mWiseFyConnection = mockWiseFyConnection;
        when(mockWiseFyConnection.isCurrentNetworkConnectedToSSID(anyString())).thenReturn(false);

        assertFalse(mWiseFy.isDeviceConnectedToSSID(TEST_SSID));
    }

    @Test
    public void noCallbacks_success() {
        WiseFyConnection mockWiseFyConnection = mock(WiseFyConnection.class);
        mWiseFy.mWiseFyConnection = mockWiseFyConnection;
        when(mockWiseFyConnection.isCurrentNetworkConnectedToSSID(anyString())).thenReturn(true);

        assertTrue(mWiseFy.isDeviceConnectedToSSID(TEST_SSID));
    }
}
