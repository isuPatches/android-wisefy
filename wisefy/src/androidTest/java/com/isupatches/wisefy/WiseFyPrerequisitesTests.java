package com.isupatches.wisefy;


import org.junit.Test;
import static junit.framework.Assert.*;


public class WiseFyPrerequisitesTests extends BaseAndroidJUnit4TestClass {

    @Test
    public void hasPrerequisites_nullWifiManager() {
        WiseFyPrerequisites.getInstance().setConnectivityManager(mMockConnectivityManager);
        WiseFyPrerequisites.getInstance().setWifiManager(null);
        assertFalse(WiseFyPrerequisites.getInstance().hasPrerequisites());
    }

    @Test
    public void hasPrerequisites_nullConnectivityManager() {
        WiseFyPrerequisites.getInstance().setConnectivityManager(null);
        WiseFyPrerequisites.getInstance().setWifiManager(mMockWiFiManager);
        assertFalse(WiseFyPrerequisites.getInstance().hasPrerequisites());
    }

    @Test
    public void hasPrerequisites_noManagers() {
        WiseFyPrerequisites.getInstance().setConnectivityManager(null);
        WiseFyPrerequisites.getInstance().setWifiManager(null);
        assertFalse(WiseFyPrerequisites.getInstance().hasPrerequisites());
    }

    @Test
    public void hasPrerequisites_bothManagers() {
        WiseFyPrerequisites.getInstance().setConnectivityManager(mMockConnectivityManager);
        WiseFyPrerequisites.getInstance().setWifiManager(mMockWiFiManager);
        assertTrue(WiseFyPrerequisites.getInstance().hasPrerequisites());
    }

    @Test
    public void connectivityManager_getterAndSetter() {
        WiseFyPrerequisites.getInstance().setConnectivityManager(mMockConnectivityManager);
        assertEquals(mMockConnectivityManager, WiseFyPrerequisites.getInstance().getConnectivityManager());
    }

    @Test
    public void wifiManager_getterAndSetter() {
        WiseFyPrerequisites.getInstance().setWifiManager(mMockWiFiManager);
        assertEquals(mMockWiFiManager, WiseFyPrerequisites.getInstance().getWifiManager());
    }
}
