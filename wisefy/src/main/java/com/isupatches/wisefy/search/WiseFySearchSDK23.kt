package com.isupatches.wisefy.search

import android.net.wifi.ScanResult
import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiManager

internal class WiseFySearchSDK23 private constructor(
    private val wifiManager: WifiManager
) : WiseFySearch {

    internal companion object {
        private val TAG = WiseFySearchSDK23::class.java.simpleName

        fun create(wifiManager: WifiManager): WiseFySearch = WiseFySearchSDK23(wifiManager)
    }

    override fun init() {
    }

    override fun destroy() {
    }

    override fun findAccessPointByRegex(regexForSSID: String, timeoutInMillis: Int, takeHighest: Boolean): ScanResult? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun findAccessPointsMatchingRegex(regexForSSID: String, takeHighest: Boolean): List<ScanResult>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun findSavedNetworkByRegex(regexForSSID: String): WifiConfiguration? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun findSavedNetworksMatchingRegex(regexForSSID: String): List<WifiConfiguration>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun findSSIDsMatchingRegex(regexForSSID: String): List<String>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isNetworkASavedConfiguration(ssid: String?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeEntriesWithLowerSignalStrength(accessPoints: List<ScanResult>): List<ScanResult> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
