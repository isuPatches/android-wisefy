package com.isupatches.wisefy;


import android.net.wifi.WifiInfo;
import android.os.Build;
import com.isupatches.wisefy.callbacks.GetFrequencyCallbacks;
import com.isupatches.wisefy.constants.WiseFyCodes;
import org.junit.Test;
import static com.isupatches.wisefy.base.TestUtils.TEST_NETWORK_FREQUENCY_24GHZ;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class GetFrequencyTests extends BaseAndroidJUnit4TestClass {

    @Test
    public void sync_getFrequency_currentNetwork_failure() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            when(mMockWiFiManager.getConnectionInfo()).thenReturn(null);
            assertEquals(null, mWiseFy.getFrequency());
        }
    }

    @Test
    public void sync_getFrequency_currentNetwork_success() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            WifiInfo mockWifiInfo = mock(WifiInfo.class);
            when(mockWifiInfo.getFrequency()).thenReturn(TEST_NETWORK_FREQUENCY_24GHZ);
            when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWifiInfo);
            assertEquals(TEST_NETWORK_FREQUENCY_24GHZ, (int) mWiseFy.getFrequency());
            verify(mockWifiInfo, timeout(VERIFICATION_SUCCESS_TIMEOUT)).getFrequency();
        }
    }

    @Test
    public void async_getFrequency_currentNetwork_failure() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            when(mMockWiFiManager.getConnectionInfo()).thenReturn(null);
            GetFrequencyCallbacks mockCallbacks = mock(GetFrequencyCallbacks.class);
            mWiseFy.getFrequency(mockCallbacks);
            verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).failureGettingFrequency();
        }
    }

    @Test
    public void async_getFrequency_currentNetwork_failure_nullCallback() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            when(mMockWiFiManager.getConnectionInfo()).thenReturn(null);
            try {
                mWiseFy.getFrequency((GetFrequencyCallbacks) null);
            } catch (NullPointerException npe) {
                fail();
            }
        }
    }

    @Test
    public void async_getFrequency_currentNetwork_success() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            WifiInfo mockWifiInfo = mock(WifiInfo.class);
            when(mockWifiInfo.getFrequency()).thenReturn(TEST_NETWORK_FREQUENCY_24GHZ);
            when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWifiInfo);
            GetFrequencyCallbacks mockCallbacks = mock(GetFrequencyCallbacks.class);
            mWiseFy.getFrequency(mockCallbacks);
            verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).retrievedFrequency(TEST_NETWORK_FREQUENCY_24GHZ);
            verify(mockWifiInfo, timeout(VERIFICATION_SUCCESS_TIMEOUT)).getFrequency();
        }
    }

    @Test
    public void async_getFrequency_currentNetwork_success_nullCallback() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            WifiInfo mockWifiInfo = mock(WifiInfo.class);
            when(mockWifiInfo.getFrequency()).thenReturn(TEST_NETWORK_FREQUENCY_24GHZ);
            when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWifiInfo);
            try {
                mWiseFy.getFrequency((GetFrequencyCallbacks) null);
            } catch (NullPointerException npe) {
                fail();
            }
        }
    }

    @Test
    public void sync_getFrequency_provideWifiInfo_failure() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            when(mMockWiFiManager.getConnectionInfo()).thenReturn(null);
            assertEquals(null, mWiseFy.getFrequency((WifiInfo) null));
        }
    }

    @Test
    public void sync_getFrequency_provideWifiInfo_success() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            WifiInfo mockWifiInfo = mock(WifiInfo.class);
            when(mockWifiInfo.getFrequency()).thenReturn(TEST_NETWORK_FREQUENCY_24GHZ);
            when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWifiInfo);
            assertEquals(TEST_NETWORK_FREQUENCY_24GHZ, (int) mWiseFy.getFrequency(mockWifiInfo));
        }
    }

    @Test
    public void async_getFrequency_provideWifiInfo_failure() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            when(mMockWiFiManager.getConnectionInfo()).thenReturn(null);
            GetFrequencyCallbacks mockCallbacks = mock(GetFrequencyCallbacks.class);
            mWiseFy.getFrequency(null, mockCallbacks);
            verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).getFrequencyWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
        }
    }

    @Test
    public void async_getFrequency_provideWifiInfo_failure_nullCallback() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            when(mMockWiFiManager.getConnectionInfo()).thenReturn(null);
            try {
                mWiseFy.getFrequency(null, null);
            } catch (NullPointerException npe) {
                fail();
            }
        }
    }

    @Test
    public void async_getFrequency_provideWifiInfo_success() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            WifiInfo mockWifiInfo = mock(WifiInfo.class);
            when(mockWifiInfo.getFrequency()).thenReturn(TEST_NETWORK_FREQUENCY_24GHZ);
            when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWifiInfo);
            GetFrequencyCallbacks mockCallbacks = mock(GetFrequencyCallbacks.class);
            mWiseFy.getFrequency(mockWifiInfo, mockCallbacks);
            verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).retrievedFrequency(TEST_NETWORK_FREQUENCY_24GHZ);
        }
    }

    @Test
    public void async_getFrequency_provideWifiInfo_success_nullCallback() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            WifiInfo mockWifiInfo = mock(WifiInfo.class);
            when(mockWifiInfo.getFrequency()).thenReturn(TEST_NETWORK_FREQUENCY_24GHZ);
            when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWifiInfo);
            try {
                mWiseFy.getFrequency(mockWifiInfo, null);
            } catch (NullPointerException npe) {
                fail();
            }
        }
    }
}
