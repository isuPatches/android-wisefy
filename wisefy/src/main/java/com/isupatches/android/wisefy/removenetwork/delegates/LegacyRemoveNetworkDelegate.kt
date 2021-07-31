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
package com.isupatches.android.wisefy.removenetwork.delegates

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.removenetwork.RemoveNetworkApi
import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkResult
import com.isupatches.android.wisefy.savednetworks.SavedNetworkUtil

internal class LegacyRemoveNetworkDelegate(
    private val wifiManager: WifiManager,
    private val savedNetworkUtil: SavedNetworkUtil,
    private val impl: LegacyRemoveNetworkApi = LegacyRemoveNetworkApiImpl(
        wifiManager,
        savedNetworkUtil
    )
) : RemoveNetworkApi {

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun removeNetwork(ssidToRemove: String): RemoveNetworkResult {
        return impl.removeNetwork(ssidToRemove)
    }
}
