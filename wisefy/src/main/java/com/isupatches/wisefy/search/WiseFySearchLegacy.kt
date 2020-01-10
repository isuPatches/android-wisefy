/*
 * Copyright 2019 Patches Klinefelter
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
import com.isupatches.wisefy.logging.WiseFyLogger

/**
 * A class used internally for the purposes of shared query logic. This handles saved networks and
 * nearby access points. There is also filtering by regex functionality and some RSSI logic that
 * are tied into these queries.
 *
 * @see [WifiManager]
 * @see [AbstractWiseFySearch]
 *
 * Updates
 * - 01/04/2020: Formatting update
 * - 01/07/2020: Added WiseFyLogger
 *
 * @author Patches
 * @since 3.0
 */
@Suppress("deprecation")
internal class WiseFySearchLegacy private constructor(
    wifiManager: WifiManager,
    logger: WiseFyLogger?
) : AbstractWiseFySearch(wifiManager, logger) {

    internal companion object {
        fun create(
            wifiManager: WifiManager,
            logger: WiseFyLogger? = null
        ): WiseFySearch = WiseFySearchLegacy(wifiManager, logger)
    }

    // For SDK 23 and below, devices are still allowed to trigger a scan for nearby access points,
    // so we'll continue to do that to preserve previous behavior.
    override val scanResultsProvider by lazy {
        {
            wifiManager.startScan()
            wifiManager.scanResults
        }
    }
}
