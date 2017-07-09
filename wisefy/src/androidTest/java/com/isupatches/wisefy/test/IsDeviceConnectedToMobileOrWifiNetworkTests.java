package com.isupatches.wisefy.test;


import android.net.NetworkInfo;
import com.isupatches.wisefy.constants.NetworkTypes;
import com.isupatches.wisefy.test.base.BaseTestClass;
import com.isupatches.wisefy.test.base.TestActivity;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class IsDeviceConnectedToMobileOrWifiNetworkTests extends BaseTestClass<TestActivity> {

    public IsDeviceConnectedToMobileOrWifiNetworkTests() {
        super(TestActivity.class);
    }

    @Test
    public void failure_mobile_notAvailable() {
        setManagers();
        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);
        when(mockNetworkInfo.getTypeName()).thenReturn(NetworkTypes.MOBILE);

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(false);
        when(mockNetworkInfo.isConnected()).thenReturn(true);

        boolean result = mWiseFy.isDeviceConnectedToMobileOrWifiNetwork();
        assertEquals(false, result);
    }

    @Test
    public void failure_mobile_notConnected() {
        setManagers();
        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);
        when(mockNetworkInfo.getTypeName()).thenReturn(NetworkTypes.MOBILE);

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(true);
        when(mockNetworkInfo.isConnected()).thenReturn(false);

        boolean result = mWiseFy.isDeviceConnectedToMobileOrWifiNetwork();
        assertEquals(false, result);
    }

    @Test
    public void failure_nullActiveNetworkInfo() {
        setManagers();
        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(null);
        boolean result = mWiseFy.isDeviceConnectedToMobileOrWifiNetwork();
        assertEquals(false, result);
    }

    @Test
    public void failure_nullConfigurationManager() {
        setManagersToNull();
        boolean result = mWiseFy.isDeviceConnectedToMobileOrWifiNetwork();
        assertEquals(false, result);
    }

    @Test
    public void failure_wifi_notAvailable() {
        setManagers();
        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);
        when(mockNetworkInfo.getTypeName()).thenReturn(NetworkTypes.WIFI);

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(false);
        when(mockNetworkInfo.isConnected()).thenReturn(true);

        boolean result = mWiseFy.isDeviceConnectedToMobileOrWifiNetwork();
        assertEquals(false, result);
    }

    @Test
    public void failure_wifi_notConnected() {
        setManagers();
        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);
        when(mockNetworkInfo.getTypeName()).thenReturn(NetworkTypes.WIFI);

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(true);
        when(mockNetworkInfo.isConnected()).thenReturn(false);

        boolean result = mWiseFy.isDeviceConnectedToMobileOrWifiNetwork();
        assertEquals(false, result);
    }

    @Test
    public void success_mobile() {
        setManagers();
        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);
        when(mockNetworkInfo.getTypeName()).thenReturn(NetworkTypes.MOBILE);

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(true);
        when(mockNetworkInfo.isConnected()).thenReturn(true);

        boolean result = mWiseFy.isDeviceConnectedToMobileOrWifiNetwork();
        assertEquals(true, result);
    }

    @Test
    public void success_wifi() {
        setManagers();
        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);
        when(mockNetworkInfo.getTypeName()).thenReturn(NetworkTypes.WIFI);

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(true);
        when(mockNetworkInfo.isConnected()).thenReturn(true);

        boolean result = mWiseFy.isDeviceConnectedToMobileOrWifiNetwork();
        assertEquals(true, result);
    }
}
