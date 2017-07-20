package com.isupatches.wisefy;


import android.net.NetworkInfo;
import com.isupatches.wisefy.base.TestActivity;
import org.junit.Test;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class IsDeviceConnectedToMobileNetworkTests extends BaseTestClass<TestActivity> {

    public IsDeviceConnectedToMobileNetworkTests() {
        super(TestActivity.class);
    }

    @Test
    public void failure_missingPrerequisite() {
        missingPrerequisite();
        assertFalse(mWiseFy.isDeviceConnectedToMobileNetwork());
    }

    @Test
    public void failure() {
        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mock(NetworkInfo.class));

        WiseFyConnection mockWiseFyConnection = mock(WiseFyConnection.class);
        mWiseFy.mWiseFyConnection = mockWiseFyConnection;
        when(mockWiseFyConnection.isNetworkConnectedAndMatchesType(any(NetworkInfo.class), anyString())).thenReturn(false);
        assertFalse(mWiseFy.isDeviceConnectedToMobileNetwork());
    }

    @Test
    public void success() {
        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mock(NetworkInfo.class));

        WiseFyConnection mockWiseFyConnection = mock(WiseFyConnection.class);
        mWiseFy.mWiseFyConnection = mockWiseFyConnection;
        when(mockWiseFyConnection.isNetworkConnectedAndMatchesType(any(NetworkInfo.class), anyString())).thenReturn(true);
        assertTrue(mWiseFy.isDeviceConnectedToMobileNetwork());
    }
}
