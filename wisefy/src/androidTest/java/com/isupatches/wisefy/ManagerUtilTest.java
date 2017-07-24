//package com.isupatches.wisefy;
//
//
//import android.net.ConnectivityManager;
//import android.net.wifi.WifiManager;
//import android.support.test.rule.ActivityTestRule;
//import com.isupatches.wisefy.base.TestActivity;
//import com.isupatches.wisefy.util.ManagerUtil;
//import org.junit.Rule;
//import org.junit.Test;
//import static junit.framework.Assert.*;
//
//
//public class ManagerUtilTest extends BaseTestClass<TestActivity> {
//
//    public ManagerUtilTest() {
//        super(TestActivity.class);
//    }
//
//    @Rule
//    public ActivityTestRule<TestActivity> mActivityTestRule = new ActivityTestRule<>(TestActivity.class);
//
//    @Test
//    public void getConnectivityManager_returnsNotNull() {
//        ConnectivityManager connectivityManager = ManagerUtil.getInstance().getConnectivityManager(mActivityTestRule.getActivity());
//        assertNotNull(connectivityManager);
//    }
//
//    @Test
//    public void getConnectivityManager_returnsNull_noActivity() {
//        ConnectivityManager connectivityManager = ManagerUtil.getInstance().getConnectivityManager(null);
//        assertNull(connectivityManager);
//    }
//
//    @Test
//    public void getWiFiManager_returnsNotNull() {
//        WifiManager wifiManager = ManagerUtil.getInstance().getWiFiManager(mActivityTestRule.getActivity());
//        assertNotNull(wifiManager);
//    }
//
//    @Test
//    public void getWiFiManager_returnsNull_noActivity() {
//        WifiManager wifiManager = ManagerUtil.getInstance().getWiFiManager(null);
//        assertNull(wifiManager);
//    }
//}