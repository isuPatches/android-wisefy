package com.isupatches.wisefy;


import android.net.wifi.WifiConfiguration;
import com.isupatches.wisefy.base.TestActivity;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static com.isupatches.wisefy.base.TestUtils.TEST_SSID;
import static com.isupatches.wisefy.base.TestUtils.TEST_SSID2;
import static com.isupatches.wisefy.base.TestUtils.TEST_SSID3;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


public class IsNetworkInConfigurationListTests extends BaseTestClass<TestActivity> {

    public IsNetworkInConfigurationListTests() {
        super(TestActivity.class);
    }

    @Test
    public void failure() {
        assertEquals(false, mWiseFy.isNetworkInConfigurationList(TEST_SSID));
    }

    @Test
    public void failure_multipleNetworks() {
        List<WifiConfiguration> wifiList = new ArrayList<>();

        WifiConfiguration wifiConfiguration1 = new WifiConfiguration();
        wifiConfiguration1.SSID = TEST_SSID2;
        wifiList.add(wifiConfiguration1);

        WifiConfiguration wiFiConfiguration2 = new WifiConfiguration();
        wiFiConfiguration2.SSID = TEST_SSID3;
        wifiList.add(wiFiConfiguration2);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        assertEquals(false, mWiseFy.isNetworkInConfigurationList(TEST_SSID));
    }

    @Test
    public void failure_noNetworks() {
        List<WifiConfiguration> wifiList = new ArrayList<>();
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        assertEquals(false, mWiseFy.isNetworkInConfigurationList(TEST_SSID));
    }

    @Test
    public void failure_nullSSID() {
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        wifiConfiguration.SSID = null;
        wifiList.add(wifiConfiguration);
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        assertEquals(false, mWiseFy.isNetworkInConfigurationList(TEST_SSID));
    }

    @Test
    public void failure_nullWifiConfiguration() {
        List<WifiConfiguration> wifiList = new ArrayList<>();
        wifiList.add(0, null);
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        assertEquals(false, mWiseFy.isNetworkInConfigurationList(TEST_SSID));
    }

    @Test
    public void failure_nmissingPrerequisite() {
        missingPrerequisite();
        assertEquals(false, mWiseFy.isNetworkInConfigurationList(TEST_SSID));
    }

    @Test
    public void success() {
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = TEST_SSID;
        wifiList.add(mWiFiConfiguration);
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        assertEquals(true, mWiseFy.isNetworkInConfigurationList(TEST_SSID));
    }

    @Test
    public void success_multipleNetworks() {
        List<WifiConfiguration> wifiList = new ArrayList<>();

        WifiConfiguration wifiConfiguration1 = new WifiConfiguration();
        wifiConfiguration1.SSID = TEST_SSID;
        wifiList.add(wifiConfiguration1);

        WifiConfiguration wiFiConfiguration2 = new WifiConfiguration();
        wiFiConfiguration2.SSID = TEST_SSID2;
        wifiList.add(wiFiConfiguration2);

        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        assertEquals(true, mWiseFy.isNetworkInConfigurationList(TEST_SSID));
    }
}
