/*
 * Copyright 2021 Patches Klinefelter
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
package com.isupatches.android.wisefy.removenetwork

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.CHANGE_WIFI_STATE
import android.net.wifi.WifiManager
import android.os.Build
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.logging.WisefyLogger
import com.isupatches.android.wisefy.removenetwork.delegates.Android29RemoveNetworkDelegate
import com.isupatches.android.wisefy.removenetwork.delegates.LegacyRemoveNetworkDelegate
import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkResult
import com.isupatches.android.wisefy.savednetworks.SavedNetworkUtil

internal interface RemoveNetworkUtil : RemoveNetworkApi

private const val LOG_TAG = "WisefyRemoveNetworkUtil"

internal class WisefyRemoveNetworkUtil(
    wifiManager: WifiManager,
    logger: WisefyLogger?,
    savedNetworkUtil: SavedNetworkUtil
) : RemoveNetworkUtil {

    private val delegate: RemoveNetworkApi = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> Android29RemoveNetworkDelegate(wifiManager)
        else -> LegacyRemoveNetworkDelegate(wifiManager, savedNetworkUtil)
    }

    init {
        logger?.d(LOG_TAG, "WisefyRemoveNetworkUtil delegate is: ${delegate::class.java.simpleName}")
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun removeNetwork(ssidToRemove: String): RemoveNetworkResult {
        return delegate.removeNetwork(ssidToRemove)
    }
}
