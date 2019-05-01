package com.isupatches.wisefy.search

import android.Manifest
import android.Manifest.permission.ACCESS_WIFI_STATE
import android.net.wifi.ScanResult
import android.net.wifi.WifiManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission

@RequiresApi(Build.VERSION_CODES.M)
internal class WiseFySearchSDK23 private constructor(
    private val wifiManager: WifiManager
) : AbstractWiseFySearch(wifiManager) {

    internal companion object {
        fun create(wifiManager: WifiManager): WiseFySearch = WiseFySearchSDK23(wifiManager)
    }

    @RequiresPermission(allOf = [Manifest.permission.ACCESS_COARSE_LOCATION, ACCESS_WIFI_STATE])
    override fun findAccessPointByRegex(
        regexForSSID: String,
        timeoutInMillis: Int,
        takeHighest: Boolean
    ): ScanResult? = findAccessPointByRegex(
        accessPoints = wifiManager.scanResults,
        regexForSSID = regexForSSID,
        timeoutInMillis = timeoutInMillis,
        takeHighest = takeHighest
    )

    @RequiresPermission(allOf = [Manifest.permission.ACCESS_COARSE_LOCATION, ACCESS_WIFI_STATE])
    override fun findAccessPointsMatchingRegex(
        regexForSSID: String,
        takeHighest: Boolean
    ): List<ScanResult>? = findAccessPointsMatchingRegex(
        accessPoints = wifiManager.scanResults,
        regexForSSID = regexForSSID,
        takeHighest = takeHighest
    )

    @RequiresPermission(allOf = [Manifest.permission.ACCESS_COARSE_LOCATION, ACCESS_WIFI_STATE])
    override fun findSSIDsMatchingRegex(
        regexForSSID: String
    ): List<String>? = findSSIDsMatchingRegex(
        accessPoints = wifiManager.scanResults,
        regexForSSID = regexForSSID
    )

    @RequiresPermission(allOf = [Manifest.permission.ACCESS_COARSE_LOCATION, ACCESS_WIFI_STATE])
    override fun getNearbyAccessPoints(
        filterDuplicates: Boolean
    ): List<ScanResult> = getNearbyAccessPoints(
        accessPoints = wifiManager.scanResults,
        filterDuplicates = filterDuplicates
    )
}
