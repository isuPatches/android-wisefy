package com.isupatches.wisefy;


import android.net.NetworkInfo;
import org.junit.Test;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class IsDeviceConnectedToWifiNetworkTests extends BaseAndroidJUnit4TestClass {

    @Test
    public void failure_missingPrerequisite() {
        missingPrerequisite();
        assertFalse(mWiseFy.isDeviceConnectedToWifiNetwork());
    }

    @Test
    public void failure() {
        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mock(NetworkInfo.class));

        WiseFyConnection mockWiseFyConnection = mock(WiseFyConnection.class);
        mWiseFy.mWiseFyConnection = mockWiseFyConnection;
        when(mockWiseFyConnection.isNetworkConnectedAndMatchesType(any(NetworkInfo.class), anyString())).thenReturn(false);
        assertFalse(mWiseFy.isDeviceConnectedToWifiNetwork());
    }

    @Test
    public void success() {
        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mock(NetworkInfo.class));

        WiseFyConnection mockWiseFyConnection = mock(WiseFyConnection.class);
        mWiseFy.mWiseFyConnection = mockWiseFyConnection;
        when(mockWiseFyConnection.isNetworkConnectedAndMatchesType(any(NetworkInfo.class), anyString())).thenReturn(true);
        assertTrue(mWiseFy.isDeviceConnectedToWifiNetwork());
    }
}
