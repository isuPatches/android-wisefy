package com.metova.wisefy.util;


import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import com.metova.wisefy.WiseFy;


public class GetManagerUtil {

    private static final GetManagerUtil GET_MANAGER_UTIL = new GetManagerUtil();

    /**
     * Private constructor with no setup
     */
    private GetManagerUtil() {
    }

    /**
     * @return instance of GetManagerUtil
     */
    public static GetManagerUtil getInstance() {
        return GET_MANAGER_UTIL;
    }

    /**
     * To get a Connectivity manger instance from an activity's context.
     *
     * @see ConnectivityManager
     * @see WiseFy
     *
     * @param activity - The activity to use as context to retrieve a connectivity manager via getSystemService
     * @return ConnectivityManager|null
     */
    public ConnectivityManager getConnectivityManager(Activity activity) {
        if(activity != null) {
            return (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        } else {
            return null;
        }
    }

    /**
     * To get a WiFi manger instance from an activity's context.
     *
     * @see WifiManager
     * @see WiseFy
     *
     * @param activity - The activity to use as context to retrieve a wifi manager via getSystemService
     * @return WifiManager|null
     */
    public WifiManager getWiFiManager(Activity activity) {
        if(activity != null) {
            return (WifiManager) activity.getSystemService(Context.WIFI_SERVICE);
        } else {
            return null;
        }
    }
}