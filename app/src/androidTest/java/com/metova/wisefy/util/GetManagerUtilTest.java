package com.metova.wisefy.util;


import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import com.metova.wisefy.base.BaseInstrumentationTestCase;


public class GetManagerUtilTest extends BaseInstrumentationTestCase<TestActivity> {

    public GetManagerUtilTest() {
        super(TestActivity.class);
    }
    public void testGetWiFiManager() {
        WifiManager wifiManager = GetManagerUtil.getInstance().getWiFiManager(getActivity());
        assertNotNull(wifiManager);
    }

    public void testGetConnectivityManager() {
        ConnectivityManager connectivityManager = GetManagerUtil.getInstance().getConnectivityManager(getActivity());
        assertNotNull(connectivityManager);
    }
}