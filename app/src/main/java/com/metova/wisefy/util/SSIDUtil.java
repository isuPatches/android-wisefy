package com.metova.wisefy.util;


import android.os.Build;

public class SSIDUtil {

    private static final SSIDUtil SSID_UTIL = new SSIDUtil();

    /**
     * Private constructor with no setup
     */
    private SSIDUtil() {
    }

    /**
     * @return instance of SSIDUtil
     */
    public static SSIDUtil getInstance() {
        return SSID_UTIL;
    }

    public String convertSSIDForConfig(String ssid) {
        // On devices with version Kitkat and below, We need to send SSID name
        // with double quotes. On devices with version Lollipop, We need to send
        // SSID name without double quotes
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return ssid;
        } else {
            return "\"" + ssid + "\"";
        }
    }
}
