package com.isupatches.wisefy.test;


import android.net.wifi.WifiInfo;
import android.os.Build;
import com.isupatches.wisefy.callbacks.GetFrequencyCallbacks;
import com.isupatches.wisefy.constants.WiseFyCodes;
import com.isupatches.wisefy.test.base.BaseTestClass;
import com.isupatches.wisefy.test.base.TestActivity;
import org.junit.Test;
import static com.isupatches.wisefy.test.base.TestUtils.TEST_NETWORK_FREQUENCY_24GHZ;
import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class GetFrequencyTests extends BaseTestClass<TestActivity> {

    public GetFrequencyTests() {
        super(TestActivity.class);
    }

    @Test
    public void noCallbacks_getFrequency_currentNetwork_failure() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setManagers();
            when(mMockWiFiManager.getConnectionInfo()).thenReturn(null);
            assertEquals(null, mWiseFy.getFrequency());
        }
    }

    @Test
    public void noCallbacks_getFrequency_currentNetwork_success() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setManagers();
            WifiInfo mockWifiInfo = mock(WifiInfo.class);
            when(mockWifiInfo.getFrequency()).thenReturn(TEST_NETWORK_FREQUENCY_24GHZ);
            when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWifiInfo);
            assertEquals(TEST_NETWORK_FREQUENCY_24GHZ, (int) mWiseFy.getFrequency());
        }
    }

    @Test
    public void callbacks_getFrequency_currentNetwork_failure() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setManagers();
            when(mMockWiFiManager.getConnectionInfo()).thenReturn(null);
            GetFrequencyCallbacks mockGetFrequencyCallbacks = mock(GetFrequencyCallbacks.class);
            mWiseFy.getFrequency(mockGetFrequencyCallbacks);
            verify(mockGetFrequencyCallbacks, timeout(2000)).failureGettingFrequency();
        }
    }

    @Test
    public void callbacks_getFrequency_currentNetwork_failure_nullCallback() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setManagers();
            when(mMockWiFiManager.getConnectionInfo()).thenReturn(null);
            GetFrequencyCallbacks mockGetFrequencyCallbacks = mock(GetFrequencyCallbacks.class);
            mWiseFy.getFrequency((GetFrequencyCallbacks) null);
            verify(mockGetFrequencyCallbacks, never()).failureGettingFrequency();
        }
    }

    @Test
    public void callbacks_getFrequency_currentNetwork_success() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setManagers();
            WifiInfo mockWifiInfo = mock(WifiInfo.class);
            when(mockWifiInfo.getFrequency()).thenReturn(TEST_NETWORK_FREQUENCY_24GHZ);
            when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWifiInfo);
            GetFrequencyCallbacks mockGetFrequencyCallbacks = mock(GetFrequencyCallbacks.class);
            mWiseFy.getFrequency(mockGetFrequencyCallbacks);
            verify(mockGetFrequencyCallbacks, timeout(2000)).retrievedFrequency(TEST_NETWORK_FREQUENCY_24GHZ);
        }
    }

    @Test
    public void callbacks_getFrequency_currentNetwork_success_nullCallback() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setManagers();
            WifiInfo mockWifiInfo = mock(WifiInfo.class);
            when(mockWifiInfo.getFrequency()).thenReturn(TEST_NETWORK_FREQUENCY_24GHZ);
            when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWifiInfo);
            GetFrequencyCallbacks mockGetFrequencyCallbacks = mock(GetFrequencyCallbacks.class);
            mWiseFy.getFrequency((GetFrequencyCallbacks) null);
            verify(mockGetFrequencyCallbacks, never()).retrievedFrequency(TEST_NETWORK_FREQUENCY_24GHZ);
        }
    }

    @Test
    public void noCallbacks_getFrequency_provideWifiInfo_failure() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setManagers();
            when(mMockWiFiManager.getConnectionInfo()).thenReturn(null);
            assertEquals(null, mWiseFy.getFrequency((WifiInfo) null));
        }
    }

    @Test
    public void noCallbacks_getFrequency_provideWifiInfo_success() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setManagers();
            WifiInfo mockWifiInfo = mock(WifiInfo.class);
            when(mockWifiInfo.getFrequency()).thenReturn(TEST_NETWORK_FREQUENCY_24GHZ);
            when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWifiInfo);
            assertEquals(TEST_NETWORK_FREQUENCY_24GHZ, (int) mWiseFy.getFrequency(mockWifiInfo));
        }
    }

    @Test
    public void callbacks_getFrequency_provideWifiInfo_failure() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setManagers();
            when(mMockWiFiManager.getConnectionInfo()).thenReturn(null);
            GetFrequencyCallbacks mockGetFrequencyCallbacks = mock(GetFrequencyCallbacks.class);
            mWiseFy.getFrequency(null, mockGetFrequencyCallbacks);
            verify(mockGetFrequencyCallbacks, timeout(2000)).getFrequencyWiseFyFailure(WiseFyCodes.MISSING_PARAMETER);
        }
    }

    @Test
    public void callbacks_getFrequency_provideWifiInfo_failure_nullCallback() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setManagers();
            when(mMockWiFiManager.getConnectionInfo()).thenReturn(null);
            GetFrequencyCallbacks mockGetFrequencyCallbacks = mock(GetFrequencyCallbacks.class);
            mWiseFy.getFrequency(null, null);
            verify(mockGetFrequencyCallbacks, never()).failureGettingFrequency();
        }
    }

    @Test
    public void callbacks_getFrequency_provideWifiInfo_success() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setManagers();
            WifiInfo mockWifiInfo = mock(WifiInfo.class);
            when(mockWifiInfo.getFrequency()).thenReturn(TEST_NETWORK_FREQUENCY_24GHZ);
            when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWifiInfo);
            GetFrequencyCallbacks mockGetFrequencyCallbacks = mock(GetFrequencyCallbacks.class);
            mWiseFy.getFrequency(mockWifiInfo, mockGetFrequencyCallbacks);
            verify(mockGetFrequencyCallbacks, timeout(2000)).retrievedFrequency(TEST_NETWORK_FREQUENCY_24GHZ);
        }
    }

    @Test
    public void callbacks_getFrequency_provideWifiInfo_success_nullCallback() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setManagers();
            WifiInfo mockWifiInfo = mock(WifiInfo.class);
            when(mockWifiInfo.getFrequency()).thenReturn(TEST_NETWORK_FREQUENCY_24GHZ);
            when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWifiInfo);
            GetFrequencyCallbacks mockGetFrequencyCallbacks = mock(GetFrequencyCallbacks.class);
            mWiseFy.getFrequency(mockWifiInfo, null);
            verify(mockGetFrequencyCallbacks, never()).retrievedFrequency(TEST_NETWORK_FREQUENCY_24GHZ);
        }
    }
}
