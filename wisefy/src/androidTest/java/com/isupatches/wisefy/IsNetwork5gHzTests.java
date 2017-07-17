package com.isupatches.wisefy;


import android.net.wifi.WifiInfo;
import android.os.Build;
import com.isupatches.wisefy.base.TestActivity;
import org.junit.Test;
import static com.isupatches.wisefy.base.TestUtils.TEST_NETWORK_FREQUENCY_5GHZ;
import static com.isupatches.wisefy.base.TestUtils.TEST_NETWORK_FREQUENCY_ABOVE_5GHZ;
import static com.isupatches.wisefy.base.TestUtils.TEST_NETWORK_FREQUENCY_BELOW_5GHZ;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class IsNetwork5gHzTests extends BaseTestClass<TestActivity> {

    public IsNetwork5gHzTests() {
        super(TestActivity.class);
    }

    @Test
    public void currentNetwork_failure_above5ghz() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            WifiInfo mockWifiInfo = mock(WifiInfo.class);
            when(mockWifiInfo.getFrequency()).thenReturn(TEST_NETWORK_FREQUENCY_ABOVE_5GHZ);
            when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWifiInfo);

            assertEquals(false, mWiseFy.isNetwork5gHz());
        }
    }

    @Test
    public void currentNetwork_failure_below5ghz() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            WifiInfo mockWifiInfo = mock(WifiInfo.class);
            when(mockWifiInfo.getFrequency()).thenReturn(TEST_NETWORK_FREQUENCY_BELOW_5GHZ);
            when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWifiInfo);

            assertEquals(false, mWiseFy.isNetwork5gHz());
        }
    }

    @Test
    public void currentNetwork_failure_null() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            when(mMockWiFiManager.getConnectionInfo()).thenReturn(null);
            assertEquals(false, mWiseFy.isNetwork5gHz());
        }
    }

    @Test
    public void currentNetwork_success() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            WifiInfo mockWifiInfo = mock(WifiInfo.class);
            when(mockWifiInfo.getFrequency()).thenReturn(TEST_NETWORK_FREQUENCY_5GHZ);
            when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWifiInfo);

            assertEquals(true, mWiseFy.isNetwork5gHz());
        }
    }

    @Test
    public void provideWifiInfo_failure_above5ghz() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            WifiInfo mockWifiInfo = mock(WifiInfo.class);
            when(mockWifiInfo.getFrequency()).thenReturn(TEST_NETWORK_FREQUENCY_ABOVE_5GHZ);
            when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWifiInfo);

            assertEquals(false, mWiseFy.isNetwork5gHz(mockWifiInfo));
        }
    }

    @Test
    public void provideWifiInfo_failure_below5ghz() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            WifiInfo mockWifiInfo = mock(WifiInfo.class);
            when(mockWifiInfo.getFrequency()).thenReturn(TEST_NETWORK_FREQUENCY_BELOW_5GHZ);
            when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWifiInfo);

            assertEquals(false, mWiseFy.isNetwork5gHz(mockWifiInfo));
        }
    }

    @Test
    public void provideWifiInfo_failure_null() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            assertEquals(false, mWiseFy.isNetwork5gHz(null));
        }
    }

    @Test
    public void provideWifiInfo_success() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            WifiInfo mockWifiInfo = mock(WifiInfo.class);
            when(mockWifiInfo.getFrequency()).thenReturn(TEST_NETWORK_FREQUENCY_5GHZ);
            when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWifiInfo);

            assertEquals(true, mWiseFy.isNetwork5gHz(mockWifiInfo));
        }
    }
}
