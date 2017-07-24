package com.isupatches.wisefy;


import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.support.test.InstrumentationRegistry;
import com.isupatches.wisefy.util.ManagerUtil;
import org.junit.Test;
import static junit.framework.Assert.*;


public class ManagerUtilTest extends BaseAndroidJUnit4TestClass {

    @Test
    public void getConnectivityManager_returnsNotNull() {
        ConnectivityManager connectivityManager = ManagerUtil.getInstance().getConnectivityManager(InstrumentationRegistry.getContext());
        assertNotNull(connectivityManager);
    }

    @Test
    public void getConnectivityManager_returnsNull_noActivity() {
        ConnectivityManager connectivityManager = ManagerUtil.getInstance().getConnectivityManager(null);
        assertNull(connectivityManager);
    }

    @Test
    public void getWiFiManager_returnsNotNull() {
        WifiManager wifiManager = ManagerUtil.getInstance().getWiFiManager(InstrumentationRegistry.getContext());
        assertNotNull(wifiManager);
    }

    @Test
    public void getWiFiManager_returnsNull_noActivity() {
        WifiManager wifiManager = ManagerUtil.getInstance().getWiFiManager(null);
        assertNull(wifiManager);
    }
}