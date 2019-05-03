package com.isupatches.wisefy.search

import android.net.wifi.WifiManager
import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.M)
internal class WiseFySearchSDK23 private constructor(
    private val wifiManager: WifiManager
) : AbstractWiseFySearch(wifiManager) {

    internal companion object {
        fun create(wifiManager: WifiManager): WiseFySearch = WiseFySearchSDK23(wifiManager)
    }

    override val scanResultsProvider by lazy { { wifiManager.scanResults } }
}
