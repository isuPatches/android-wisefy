package com.isupatches.wisefy.test;


import android.net.NetworkInfo;
import com.isupatches.wisefy.constants.NetworkTypes;
import com.isupatches.wisefy.test.base.BaseTestClass;
import com.isupatches.wisefy.test.base.TestActivity;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class IsDeviceConnectedToWifiNetworkTests extends BaseTestClass<TestActivity> {

    public IsDeviceConnectedToWifiNetworkTests() {
        super(TestActivity.class);
    }

    @Test
    public void failure_notAvailable() {
        setManagers();
        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);
        when(mockNetworkInfo.getTypeName()).thenReturn(NetworkTypes.WIFI);

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(false);
        when(mockNetworkInfo.isConnected()).thenReturn(true);

        boolean result = mWiseFy.isDeviceConnectedToWifiNetwork();
        assertEquals(false, result);
    }

    @Test
    public void failure_notConnected() {
        setManagers();
        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);
        when(mockNetworkInfo.getTypeName()).thenReturn(NetworkTypes.WIFI);

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(true);
        when(mockNetworkInfo.isConnected()).thenReturn(false);

        boolean result = mWiseFy.isDeviceConnectedToWifiNetwork();
        assertEquals(false, result);
    }

    @Test
    public void failure_nullActiveNetworkInfo() {
        setManagers();
        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(null);
        boolean result = mWiseFy.isDeviceConnectedToWifiNetwork();
        assertEquals(false, result);
    }

    @Test
    public void failure_nullConfigurationManager() {
        setManagers();
        setConnectivityManagerToNull();
        boolean result = mWiseFy.isDeviceConnectedToWifiNetwork();
        assertEquals(false, result);
    }

    @Test
    public void failure_nullTypeName() {
        setManagers();
        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);
        when(mockNetworkInfo.getTypeName()).thenReturn(null);
        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        boolean result = mWiseFy.isDeviceConnectedToWifiNetwork();
        assertEquals(false, result);
    }

    @Test
    public void failure_wrongType() {
        setManagers();
        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);
        when(mockNetworkInfo.getTypeName()).thenReturn("WRONG TYPE");

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(true);
        when(mockNetworkInfo.isConnected()).thenReturn(true);

        boolean result = mWiseFy.isDeviceConnectedToWifiNetwork();
        assertEquals(false, result);
    }

    @Test
    public void success() {
        setManagers();
        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);
        when(mockNetworkInfo.getTypeName()).thenReturn(NetworkTypes.WIFI);

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(true);
        when(mockNetworkInfo.isConnected()).thenReturn(true);

        boolean result = mWiseFy.isDeviceConnectedToWifiNetwork();
        assertEquals(true, result);
    }
}
