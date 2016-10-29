package com.isupatches.wisefy.base;


import android.net.wifi.WifiConfiguration;
import java.util.ArrayList;
import java.util.List;


public class TestUtils {

    public static final String OPEN_NETWORK_SSID = "Open Network";

    public static final String WEP_NETWORK_SSID = "WEP Network";

    public static final String WEP_NETWORK_PASSWORD = "12345678";

    public static final String WPA2_NETWORK_SSID = "WPA2 Network";

    public static final String WPA2_NETWORK_PASSWORD = "123456";

    public static final String TEST_SSID = "Test Network";

    public static final String TEST_SSID2 = "Test Network 2";

    public static List<WifiConfiguration> getTestWifiConfiguration() {
        List<WifiConfiguration> wifiList = new ArrayList<>();
        WifiConfiguration mWiFiConfiguration = new WifiConfiguration();
        mWiFiConfiguration.SSID = TEST_SSID;
        wifiList.add(mWiFiConfiguration);
        return wifiList;
    }
}