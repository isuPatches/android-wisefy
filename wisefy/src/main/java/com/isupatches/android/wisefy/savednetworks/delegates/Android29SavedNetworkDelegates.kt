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
package com.isupatches.android.wisefy.savednetworks.delegates

import android.Manifest.permission.ACCESS_FINE_LOCATION
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.logging.WisefyLogger
import com.isupatches.android.wisefy.savednetworks.SavedNetworkApi
import com.isupatches.android.wisefy.savednetworks.entities.SavedNetworkData

internal class Android29SavedNetworkDelegate(
    logger: WisefyLogger?,
    private val impl: Android29SavedNetworkApiImpl = Android29SavedNetworkApiImpl(logger)
) : SavedNetworkApi {

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getSavedNetworks(): List<SavedNetworkData> {
        return impl.getSavedNetworks()
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun isNetworkSaved(ssid: String): Boolean {
        return impl.isNetworkSaved(ssid)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForSavedNetwork(regexForSSID: String): SavedNetworkData? {
        return impl.searchForSavedNetwork(regexForSSID)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForSavedNetworks(regexForSSID: String): List<SavedNetworkData> {
        return impl.searchForSavedNetworks(regexForSSID)
    }
}
