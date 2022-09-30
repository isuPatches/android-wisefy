/*
 * Copyright 2022 Patches Klinefelter
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
package com.isupatches.android.wisefy.savednetworks.os.adapters

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.ACCESS_WIFI_STATE
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.core.assertions.WisefyAssertions
import com.isupatches.android.wisefy.core.entities.AssertionMessages
import com.isupatches.android.wisefy.savednetworks.SavedNetworkApi
import com.isupatches.android.wisefy.savednetworks.entities.GetSavedNetworksRequest
import com.isupatches.android.wisefy.savednetworks.entities.GetSavedNetworksResult
import com.isupatches.android.wisefy.savednetworks.entities.IsNetworkSavedRequest
import com.isupatches.android.wisefy.savednetworks.entities.IsNetworkSavedResult
import com.isupatches.android.wisefy.savednetworks.entities.SearchForSavedNetworksRequest
import com.isupatches.android.wisefy.savednetworks.entities.SearchForSavedNetworksResult

/**
 * An Android 29 specific adapter for adding networks.
 *
 * @param assertions The [WisefyAssertions] instance to use
 *
 * @see SavedNetworkApi
 * @see WisefyAssertions
 *
 * @author Patches Klinefelter
 * @since 07/2022, version 5.0.0
 */
@RequiresApi(Build.VERSION_CODES.Q)
internal class Android29SavedNetworkAdapter(
    private val assertions: WisefyAssertions
) : SavedNetworkApi {

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun getSavedNetworks(request: GetSavedNetworksRequest): GetSavedNetworksResult {
        val message = AssertionMessages.SavedNetworks.USED_ANDROID_29
        assertions.fail(message = message)
        return GetSavedNetworksResult.Failure.Assertion(message = message)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun isNetworkSaved(request: IsNetworkSavedRequest): IsNetworkSavedResult {
        val message = AssertionMessages.SavedNetworks.USED_ANDROID_29
        assertions.fail(message = message)
        return IsNetworkSavedResult.Assertion(message = message)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForSavedNetwork(request: SearchForSavedNetworksRequest): SearchForSavedNetworksResult {
        val message = AssertionMessages.SavedNetworks.USED_ANDROID_29
        assertions.fail(message = message)
        return SearchForSavedNetworksResult.Failure.Assertion(message = message)
    }
}
