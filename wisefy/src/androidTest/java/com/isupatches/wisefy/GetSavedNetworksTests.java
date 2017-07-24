//package com.isupatches.wisefy;
//
//
//import android.net.wifi.WifiConfiguration;
//import com.isupatches.wisefy.base.TestActivity;
//import com.isupatches.wisefy.callbacks.GetSavedNetworksCallbacks;
//import com.isupatches.wisefy.constants.WiseFyCodes;
//import org.junit.Test;
//import java.util.ArrayList;
//import java.util.List;
//import static com.isupatches.wisefy.base.TestUtils.TEST_SSID;
//import static junit.framework.Assert.assertEquals;
//import static junit.framework.Assert.fail;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.never;
//import static org.mockito.Mockito.timeout;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//
//public class GetSavedNetworksTests extends BaseTestClass<TestActivity> {
//
//    public GetSavedNetworksTests() {
//        super(TestActivity.class);
//    }
//
//    @Test
//    public void noCallbacks_failure_missingPrerequisite() {
//        missingPrerequisite();
//        assertEquals(null, mWiseFy.getSavedNetworks());
//        verify(mMockWiFiManager, never()).getConfiguredNetworks();
//    }
//
//    @Test
//    public void noCallbacks_success() {
//        List<WifiConfiguration> wifiList = new ArrayList<>();
//        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
//        mWiFiConfiguration.SSID = TEST_SSID;
//        wifiList.add(mWiFiConfiguration);
//        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
//
//        assertEquals(wifiList, mWiseFy.getSavedNetworks());
//        verify(mMockWiFiManager, timeout(VERIFICATION_TIMEOUT)).getConfiguredNetworks();
//    }
//
//    @Test
//    public void callbacks_failure_missingPrerequisite() {
//        missingPrerequisite();
//        GetSavedNetworksCallbacks mockCallbacks = mock(GetSavedNetworksCallbacks.class);
//        mWiseFy.getSavedNetworks(mockCallbacks);
//        verify(mockCallbacks, timeout(VERIFICATION_TIMEOUT)).getSavedNetworksWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
//        verify(mMockWiFiManager, never()).getConfiguredNetworks();
//    }
//
//    @Test
//    public void callbacks_failure_missingPrerequisite_nullCallback() {
//        missingPrerequisite();
//        try {
//            mWiseFy.getSavedNetworks((GetSavedNetworksCallbacks) null);
//            verify(mMockWiFiManager, never()).getConfiguredNetworks();
//        } catch (NullPointerException npe) {
//            fail();
//        }
//    }
//
//    @Test
//    public void callbacks_failure_nullSavedNetworks() {
//        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(null);
//        GetSavedNetworksCallbacks mockCallbacks = mock(GetSavedNetworksCallbacks.class);
//        mWiseFy.getSavedNetworks(mockCallbacks);
//        verify(mockCallbacks, timeout(VERIFICATION_TIMEOUT)).noSavedNetworksFound();
//    }
//
//    @Test
//    public void callbacks_failure_nullSavedNetworks_nullCallback() {
//        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(null);
//        try {
//            mWiseFy.getSavedNetworks((GetSavedNetworksCallbacks) null);
//            verify(mMockWiFiManager, never()).getConfiguredNetworks();
//        } catch (NullPointerException npe) {
//            fail();
//        }
//    }
//
//    @Test
//    public void callbacks_failure_emptyConfiguredNetworks() {
//        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(new ArrayList<WifiConfiguration>());
//        GetSavedNetworksCallbacks mockCallbacks = mock(GetSavedNetworksCallbacks.class);
//        mWiseFy.getSavedNetworks(mockCallbacks);
//        verify(mockCallbacks, timeout(VERIFICATION_TIMEOUT)).noSavedNetworksFound();
//    }
//
//    @Test
//    public void callbacks_failure_emptyConfiguredNetworks_nullCallback() {
//        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(new ArrayList<WifiConfiguration>());
//        try {
//            mWiseFy.getSavedNetworks((GetSavedNetworksCallbacks) null);
//            verify(mMockWiFiManager, never()).getConfiguredNetworks();
//        } catch (NullPointerException npe) {
//            fail();
//        }
//    }
//
//    @Test
//    public void callbacks_success() {
//        List<WifiConfiguration> wifiList = new ArrayList<>();
//        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
//        mWiFiConfiguration.SSID = TEST_SSID;
//        wifiList.add(mWiFiConfiguration);
//        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
//
//        GetSavedNetworksCallbacks mockCallbacks = mock(GetSavedNetworksCallbacks.class);
//        mWiseFy.getSavedNetworks(mockCallbacks);
//        verify(mockCallbacks, timeout(VERIFICATION_TIMEOUT)).retrievedSavedNetworks(wifiList);
//        verify(mMockWiFiManager, timeout(VERIFICATION_TIMEOUT)).getConfiguredNetworks();
//    }
//
//    @Test
//    public void callbacks_success_nullCallback() {
//        List<WifiConfiguration> wifiList = new ArrayList<>();
//        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
//        mWiFiConfiguration.SSID = TEST_SSID;
//        wifiList.add(mWiFiConfiguration);
//        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
//
//        try {
//            mWiseFy.getSavedNetworks((GetSavedNetworksCallbacks) null);
//            verify(mMockWiFiManager, never()).getConfiguredNetworks();
//        } catch (NullPointerException npe) {
//            fail();
//        }
//    }
//
//    @Test
//    public void callbacks_failure_withRegex_missingPrerequisite() {
//        missingPrerequisite();
//
//        GetSavedNetworksCallbacks mockCallbacks = mock(GetSavedNetworksCallbacks.class);
//        mWiseFy.getSavedNetworks(TEST_SSID, mockCallbacks);
//        verify(mockCallbacks, timeout(VERIFICATION_TIMEOUT)).getSavedNetworksWiseFyFailure(WiseFyCodes.MISSING_PREREQUISITE);
//        verify(mMockWiFiManager, never()).getConfiguredNetworks();
//    }
//
//    @Test
//    public void noCallbacks_failure_withRegex_missingPrerequisite() {
//        missingPrerequisite();
//        assertEquals(null, mWiseFy.getSavedNetworks(TEST_SSID));
//        verify(mMockWiFiManager, never()).getConfiguredNetworks();
//    }
//
//    @Test
//    public void noCallbacks_success_withRegex() {
//        List<WifiConfiguration> wifiList = new ArrayList<>();
//        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
//        mWiFiConfiguration.SSID = TEST_SSID;
//        wifiList.add(mWiFiConfiguration);
//        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
//
//        assertEquals(wifiList, mWiseFy.getSavedNetworks(TEST_SSID));
//        verify(mMockWiFiManager, timeout(VERIFICATION_TIMEOUT)).getConfiguredNetworks();
//    }
//
//    @Test
//    public void callbacks_failure_withRegex_missingPrerequisite_nullCallback() {
//        missingPrerequisite();
//
//        try{
//            mWiseFy.getSavedNetworks(TEST_SSID, null);
//            verify(mMockWiFiManager, never()).getConfiguredNetworks();
//        } catch (NullPointerException npe) {
//            fail();
//        }
//    }
//
//    @Test
//    public void callbacks_failure_withRegex_nullSavedNetworks() {
//        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(null);
//        GetSavedNetworksCallbacks mockCallbacks = mock(GetSavedNetworksCallbacks.class);
//        mWiseFy.getSavedNetworks(TEST_SSID, mockCallbacks);
//        verify(mockCallbacks, timeout(VERIFICATION_TIMEOUT)).noSavedNetworksFound();
//    }
//
//    @Test
//    public void callbacks_failure_withRegex_nullSavedNetworks_nullCallback() {
//        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(null);
//        try {
//            mWiseFy.getSavedNetworks(TEST_SSID, null);
//            verify(mMockWiFiManager, never()).getConfiguredNetworks();
//        } catch (NullPointerException npe) {
//            fail();
//        }
//    }
//
//    @Test
//    public void callbacks_failure_withRegex_emptyConfiguredNetworks() {
//        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(new ArrayList<WifiConfiguration>());
//        GetSavedNetworksCallbacks mockCallbacks = mock(GetSavedNetworksCallbacks.class);
//        mWiseFy.getSavedNetworks(TEST_SSID, mockCallbacks);
//        verify(mockCallbacks, timeout(VERIFICATION_TIMEOUT)).noSavedNetworksFound();
//        verify(mMockWiFiManager, timeout(VERIFICATION_TIMEOUT)).getConfiguredNetworks();
//    }
//
//    @Test
//    public void callbacks_failure_withRegex_emptyConfiguredNetworks_nullCallback() {
//        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(new ArrayList<WifiConfiguration>());
//        try {
//            mWiseFy.getSavedNetworks(TEST_SSID, null);
//            verify(mMockWiFiManager, never()).getConfiguredNetworks();
//        } catch (NullPointerException npe) {
//            fail();
//        }
//    }
//
//    @Test
//    public void callbacks_success_withRegex() {
//        List<WifiConfiguration> wifiList = new ArrayList<>();
//        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
//        mWiFiConfiguration.SSID = TEST_SSID;
//        wifiList.add(mWiFiConfiguration);
//        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
//
//        GetSavedNetworksCallbacks mockCallbacks = mock(GetSavedNetworksCallbacks.class);
//        mWiseFy.getSavedNetworks(TEST_SSID, mockCallbacks);
//        verify(mockCallbacks, timeout(VERIFICATION_TIMEOUT)).retrievedSavedNetworks(wifiList);
//        verify(mMockWiFiManager, timeout(VERIFICATION_TIMEOUT)).getConfiguredNetworks();
//    }
//
//    @Test
//    public void callbacks_success_withRegex_nullCallbacks() {
//        List<WifiConfiguration> wifiList = new ArrayList<>();
//        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
//        mWiFiConfiguration.SSID = TEST_SSID;
//        wifiList.add(mWiFiConfiguration);
//        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
//
//        try {
//            mWiseFy.getSavedNetworks(TEST_SSID, null);
//            verify(mMockWiFiManager, never()).getConfiguredNetworks();
//        } catch (NullPointerException npe) {
//            fail();
//        }
//    }
//}
