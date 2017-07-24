package com.isupatches.wisefy;


import android.net.NetworkInfo;
import com.isupatches.wisefy.base.TestActivity;
import org.junit.Test;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class IsDeviceConnectedToMobileOrWifiNetworkTests extends BaseAndroidJUnit4TestClass {

    @Test
    public void failure_missingPrerequisite() {
        missingPrerequisite();
        assertFalse(mWiseFy.isDeviceConnectedToMobileOrWifiNetwork());
    }

    @Test
    public void failure() {
        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mock(NetworkInfo.class));

        WiseFyConnection mockWiseFyConnection = mock(WiseFyConnection.class);
        mWiseFy.mWiseFyConnection = mockWiseFyConnection;
        when(mockWiseFyConnection.isNetworkConnected(any(NetworkInfo.class))).thenReturn(false);
        assertFalse(mWiseFy.isDeviceConnectedToMobileOrWifiNetwork());
    }

    @Test
    public void success() {
        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mock(NetworkInfo.class));

        WiseFyConnection mockWiseFyConnection = mock(WiseFyConnection.class);
        mWiseFy.mWiseFyConnection = mockWiseFyConnection;
        when(mockWiseFyConnection.isNetworkConnected(any(NetworkInfo.class))).thenReturn(true);
        assertTrue(mWiseFy.isDeviceConnectedToMobileOrWifiNetwork());
    }
}
