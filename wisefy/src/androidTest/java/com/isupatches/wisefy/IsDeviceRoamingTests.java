package com.isupatches.wisefy;


import android.net.NetworkInfo;
import com.isupatches.wisefy.base.TestActivity;
import org.junit.Test;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class IsDeviceRoamingTests extends BaseTestClass<TestActivity> {

    public IsDeviceRoamingTests() {
        super(TestActivity.class);
    }

    @Test
    public void failure_missingPrerequisite() {
        missingPrerequisite();
        assertFalse(mWiseFy.isDeviceRoaming());
    }

    @Test
    public void failure() {
        NetworkInfo networkInfo = mock(NetworkInfo.class);
        when(networkInfo.isRoaming()).thenReturn(false);
        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(networkInfo);

        assertFalse(mWiseFy.isDeviceRoaming());
    }

    @Test
    public void success() {
        NetworkInfo networkInfo = mock(NetworkInfo.class);
        when(networkInfo.isRoaming()).thenReturn(true);
        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(networkInfo);

        assertTrue(mWiseFy.isDeviceRoaming());
    }
}
