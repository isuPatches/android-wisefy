/*
 * Copyright 2018 Patches Klinefelter
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.isupatches.wisefy.search

import android.net.wifi.WifiManager

/**
 * A class used internally for the purposes of shared query logic. This handles saved networks and
 * nearby access points. There is also filtering by regex functionality and some RSSI logic that
 * are tied into these queries.
 *
 * @see [WifiManager]
 * @see [WiseFySearch]
 *
 * @author Patches
 * @since 3.0
 */
@Suppress("deprecation")
internal class WiseFySearchLegacy private constructor(
    wifiManager: WifiManager
) : AbstractWiseFySearch(wifiManager) {

    internal companion object {
        fun create(wifiManager: WifiManager): WiseFySearch = WiseFySearchLegacy(wifiManager)
    }

    override val scanResultsProvider by lazy { {
        wifiManager.startScan()
        wifiManager.scanResults
    } }
}
