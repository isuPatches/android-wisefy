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
package com.isupatches.android.wisefy.savednetworks

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.ACCESS_WIFI_STATE
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.savednetworks.entities.GetSavedNetworksRequest
import com.isupatches.android.wisefy.savednetworks.entities.GetSavedNetworksResult
import com.isupatches.android.wisefy.savednetworks.entities.IsNetworkSavedRequest
import com.isupatches.android.wisefy.savednetworks.entities.IsNetworkSavedResult
import com.isupatches.android.wisefy.savednetworks.entities.SearchForSavedNetworkRequest
import com.isupatches.android.wisefy.savednetworks.entities.SearchForSavedNetworkResult

/**
 * A set of synchronous APIs for getting and searching for saved networks.
 *
 * @author Patches Klinefelter
 * @since 07/2022, version 5.0.0
 */
interface SavedNetworkApi {

    /**
     * A synchronous API to get all of the saved networks on the device.
     *
     * @param request The details of the request to get the saved networks on the device
     *
     * @see GetSavedNetworksRequest
     * @see GetSavedNetworksResult
     *
     * @return GetSavedNetworksResult - The result of getting the saved networks on the device
     *
     * @author Patches Klinefelter
     * @since 07/2022, version 5.0.0
     */
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    fun getSavedNetworks(request: GetSavedNetworksRequest = GetSavedNetworksRequest()): GetSavedNetworksResult

    /**
     * A synchronous API to check if a network is saved on the device.
     *
     * @param request The details of the request to check if a network is saved on the device
     *
     * @see IsNetworkSavedRequest
     * @see IsNetworkSavedResult
     *
     * @return IsNetworkSavedResult - The result of checking if a network is saved on the device
     *
     * @author Patches Klinefelter
     * @since 07/2022, version 5.0.0
     */
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    fun isNetworkSaved(request: IsNetworkSavedRequest): IsNetworkSavedResult

    /**
     * A synchronous API to search for saved networks.
     *
     * @param request The details of the request to search for saved networks
     *
     * @see SearchForSavedNetworkRequest
     * @see SearchForSavedNetworkResult
     *
     * @return SearchForSavedNetworkResult - The result of searching for saved networks
     *
     * @author Patches Klinefelter
     * @since 07/2022, version 5.0.0
     */
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    fun searchForSavedNetwork(request: SearchForSavedNetworkRequest): SearchForSavedNetworkResult
}
