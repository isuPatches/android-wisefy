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
package com.isupatches.android.wisefy.sample.ui.search

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.ACCESS_WIFI_STATE
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.WisefyApi
import com.isupatches.android.wisefy.accesspoints.callbacks.SearchForAccessPointCallbacks
import com.isupatches.android.wisefy.accesspoints.callbacks.SearchForAccessPointsCallbacks
import com.isupatches.android.wisefy.accesspoints.callbacks.SearchForSSIDCallbacks
import com.isupatches.android.wisefy.accesspoints.callbacks.SearchForSSIDsCallbacks
import com.isupatches.android.wisefy.accesspoints.entities.SearchForMultipleAccessPointsRequest
import com.isupatches.android.wisefy.accesspoints.entities.SearchForMultipleSSIDsRequest
import com.isupatches.android.wisefy.accesspoints.entities.SearchForSingleAccessPointRequest
import com.isupatches.android.wisefy.accesspoints.entities.SearchForSingleSSIDRequest
import com.isupatches.android.wisefy.savednetworks.callbacks.SearchForSavedNetworkCallbacks
import com.isupatches.android.wisefy.savednetworks.callbacks.SearchForSavedNetworksCallbacks
import com.isupatches.android.wisefy.sample.internal.scaffolding.BaseModel
import com.isupatches.android.wisefy.savednetworks.entities.SearchForSavedNetworkRequest
import javax.inject.Inject

internal interface SearchModel {

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForAccessPoint(
        regexForSSID: String,
        timeoutInMillis: Int,
        filterDuplicates: Boolean,
        callbacks: SearchForAccessPointCallbacks?
    )

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForAccessPoints(
        regexForSSID: String,
        filterDuplicates: Boolean,
        callbacks: SearchForAccessPointsCallbacks?
    )

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    fun searchForSavedNetwork(
        regexForSSID: String,
        callbacks: SearchForSavedNetworkCallbacks?
    )

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    fun searchForSavedNetworks(
        regexForSSID: String,
        callbacks: SearchForSavedNetworksCallbacks?
    )

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForSSID(
        regexForSSID: String,
        timeoutInMillis: Int,
        callbacks: SearchForSSIDCallbacks?
    )

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForSSIDs(
        regexForSSID: String,
        callbacks: SearchForSSIDsCallbacks?
    )
}

@SearchScope
internal class DefaultSearchModel @Inject constructor(
    private val wisefy: WisefyApi
) : BaseModel(), SearchModel {

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForAccessPoint(
        regexForSSID: String,
        timeoutInMillis: Int,
        filterDuplicates: Boolean,
        callbacks: SearchForAccessPointCallbacks?
    ) {
        wisefy.searchForAccessPoint(
            request = SearchForSingleAccessPointRequest.SSID(
                regexForSSID = regexForSSID,
                timeoutInMillis = timeoutInMillis,
                filterDuplicates = filterDuplicates
            ),
            callbacks = callbacks
        )
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForAccessPoints(
        regexForSSID: String,
        filterDuplicates: Boolean,
        callbacks: SearchForAccessPointsCallbacks?
    ) {
        wisefy.searchForAccessPoints(
            request = SearchForMultipleAccessPointsRequest.SSID(
                regexForSSID = regexForSSID,
                filterDuplicates = filterDuplicates
            ),
            callbacks = callbacks
        )
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForSavedNetwork(
        regexForSSID: String,
        callbacks: SearchForSavedNetworkCallbacks?
    ) {
        wisefy.searchForSavedNetwork(
            request = SearchForSavedNetworkRequest.SSID(regex = regexForSSID),
            callbacks = callbacks
        )
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForSavedNetworks(
        regexForSSID: String,
        callbacks: SearchForSavedNetworksCallbacks?
    ) {
        wisefy.searchForSavedNetworks(
            request = SearchForSavedNetworkRequest.SSID(regex = regexForSSID),
            callbacks = callbacks
        )
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForSSID(
        regexForSSID: String,
        timeoutInMillis: Int,
        callbacks: SearchForSSIDCallbacks?
    ) {
        wisefy.searchForSSID(
            request = SearchForSingleSSIDRequest.SSID(
                regexForSSID = regexForSSID,
                timeoutInMillis = timeoutInMillis
            ),
            callbacks = callbacks
        )
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForSSIDs(
        regexForSSID: String,
        callbacks: SearchForSSIDsCallbacks?
    ) {
        wisefy.searchForSSIDs(
            request = SearchForMultipleSSIDsRequest.SSID(regexForSSID = regexForSSID),
            callbacks = callbacks
        )
    }
}
