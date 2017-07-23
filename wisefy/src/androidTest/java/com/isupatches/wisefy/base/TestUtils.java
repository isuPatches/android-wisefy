package com.isupatches.wisefy.base;


import com.isupatches.wisefy.WiseFy;


public class TestUtils {

    public static final String OPEN_NETWORK_SSID = "Open Network";

    public static final String WEP_NETWORK_SSID = "WEP Network";

    public static final String WEP_NETWORK_PASSWORD = "12345678";

    public static final String WPA2_NETWORK_SSID = "WPA2 Network";

    public static final String WPA2_NETWORK_PASSWORD = "123456";

    public static final String TEST_SSID = "Test Network";

    public static final String TEST_SSID2 = "Test Network 2";

    public static final String TEST_SSID3 = "Test Network 3";

    public static final int TEST_NETWORK_FREQUENCY_BELOW_5GHZ = WiseFy.MIN_FREQUENCY_5GHZ - 1;

    public static final int TEST_NETWORK_FREQUENCY_ABOVE_5GHZ = WiseFy.MAX_FREQUENCY_5GHZ + 1;

    public static final int TEST_NETWORK_FREQUENCY_24GHZ = 2400;

    public static final int TEST_NETWORK_FREQUENCY_5GHZ = 5000;

    public static final int TEST_RSSI_LEVEL = -60;

    public static final String TEST_TYPE1 = "TYPE 1";

    public static final String TEST_TYPE2 = "TYPE 2";
}