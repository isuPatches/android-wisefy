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
import android.os.Build
import androidx.annotation.RequiresApi

/**
 * A class used internally to query and determine different parts of the connection state for a
 * device when WiseFy is on a device with at least SDK23 and is not configured to use the legacy
 * connection class.
 *
 * @see [AbstractWiseFySearch]
 * @see [WifiManager]
 *
 * @author Patches
 * @since 4.0
 */
@RequiresApi(Build.VERSION_CODES.M)
internal class WiseFySearchSDK23 private constructor(
    private val wifiManager: WifiManager
) : AbstractWiseFySearch(wifiManager) {

    internal companion object {
        fun create(wifiManager: WifiManager): WiseFySearch = WiseFySearchSDK23(wifiManager)
    }

    // For SDK 23 and above, devices will be limited on ability to trigger scans and it's been
    // indicated by Android Google docs that eventually apps will no longer be able to trigger a
    // scan to prevent abusive apps, therefore for WiseFy we're going to just use the last
    // set of scan results...the downside is this may take some time to be updated.
    override val scanResultsProvider by lazy { { wifiManager.scanResults } }
}
