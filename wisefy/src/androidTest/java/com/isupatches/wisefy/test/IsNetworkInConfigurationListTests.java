package com.isupatches.wisefy.test;


import android.net.wifi.WifiConfiguration;
import com.isupatches.wisefy.test.base.BaseTestClass;
import com.isupatches.wisefy.test.base.TestActivity;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static com.isupatches.wisefy.test.base.TestUtils.TEST_SSID;
import static com.isupatches.wisefy.test.base.TestUtils.TEST_SSID2;
import static com.isupatches.wisefy.test.base.TestUtils.TEST_SSID3;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


public class IsNetworkInConfigurationListTests extends BaseTestClass<TestActivity> {

    public IsNetworkInConfigurationListTests() {
        super(TestActivity.class);
    }

    @Test
    public void failure() {
        setManagers();
        assertEquals(false, mWiseFy.isNetworkInConfigurationList(TEST_SSID));
    }

    @Test
    public void failure_multipleNetworks() {
        setManagers();

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
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        assertEquals(false, mWiseFy.isNetworkInConfigurationList(TEST_SSID));
    }

    @Test
    public void failure_nullSSID() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        wifiConfiguration.SSID = null;
        wifiList.add(wifiConfiguration);
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        assertEquals(false, mWiseFy.isNetworkInConfigurationList(TEST_SSID));
    }

    @Test
    public void failure_nullWifiConfiguration() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        wifiList.add(0, null);
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        assertEquals(false, mWiseFy.isNetworkInConfigurationList(TEST_SSID));
    }

    @Test
    public void failure_nullWifiManager() {
        setManagersToNull();
        assertEquals(false, mWiseFy.isNetworkInConfigurationList(TEST_SSID));
    }

    @Test
    public void success() {
        setManagers();
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = TEST_SSID;
        wifiList.add(mWiFiConfiguration);
        when(mMockWiFiManager.getConfiguredNetworks()).thenReturn(wifiList);
        assertEquals(true, mWiseFy.isNetworkInConfigurationList(TEST_SSID));
    }

    @Test
    public void success_multipleNetworks() {
        setManagers();

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
