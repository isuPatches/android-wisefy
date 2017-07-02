package com.isupatches.wisefy.util;


import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.support.test.rule.ActivityTestRule;
import com.isupatches.wisefy.base.BaseTestClass;
import com.isupatches.wisefy.base.TestActivity;
import org.junit.Rule;
import org.junit.Test;
import static junit.framework.Assert.*;


public class ManagerUtilTest extends BaseTestClass<TestActivity> {

    public ManagerUtilTest() {
        super(TestActivity.class);
    }

    @Rule
    public ActivityTestRule<TestActivity> mActivityTestRule = new ActivityTestRule<>(TestActivity.class);

    @Test
    public void testGetConnectivityManager() {
        ConnectivityManager connectivityManager = ManagerUtil.getInstance().getConnectivityManager(mActivityTestRule.getActivity());
        assertNotNull(connectivityManager);
    }

    @Test
    public void testGetConnectivityManagerNullActivity() {
        ConnectivityManager connectivityManager = ManagerUtil.getInstance().getConnectivityManager(null);
        assertNull(connectivityManager);
    }

    @Test
    public void testGetWiFiManager() {
        WifiManager wifiManager = ManagerUtil.getInstance().getWiFiManager(mActivityTestRule.getActivity());
        assertNotNull(wifiManager);
    }

    @Test
    public void testGetWiFiManagerNullActivity() {
        WifiManager wifiManager = ManagerUtil.getInstance().getWiFiManager(null);
        assertNull(wifiManager);
    }
}