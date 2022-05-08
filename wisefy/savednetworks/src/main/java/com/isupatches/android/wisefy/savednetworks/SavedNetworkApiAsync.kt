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
import com.isupatches.android.wisefy.savednetworks.callbacks.GetSavedNetworksCallbacks
import com.isupatches.android.wisefy.savednetworks.callbacks.SearchForSavedNetworkCallbacks
import com.isupatches.android.wisefy.savednetworks.callbacks.SearchForSavedNetworksCallbacks
import com.isupatches.android.wisefy.savednetworks.entities.GetSavedNetworksRequest
import com.isupatches.android.wisefy.savednetworks.entities.SearchForSavedNetworkRequest
import com.isupatches.android.wisefy.savednetworks.entities.SearchForSavedNetworksRequest

/**
 * A set of asynchronous APIs for getting and searching for saved networks.
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
interface SavedNetworkApiAsync {

    /**
     * An asynchronous API to get the saved networks on the device.
     *
     * @param request The details of the request to get the saved networks on the device
     * @param callbacks The callbacks for when the result for getting saved networks on the device is returned
     *
     * @see GetSavedNetworksRequest
     * @see GetSavedNetworksCallbacks
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    fun getSavedNetworks(
        request: GetSavedNetworksRequest = GetSavedNetworksRequest(),
        callbacks: GetSavedNetworksCallbacks?
    )

    /**
     * An asynchronous API to search for a single saved network.
     *
     * @param request The details of the request to search for a single saved network
     * @param callbacks The callbacks for when the result of searching for a single saved network is returned
     *
     * @see SearchForSavedNetworkRequest
     * @see SearchForSavedNetworkCallbacks
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    fun searchForSavedNetwork(request: SearchForSavedNetworkRequest, callbacks: SearchForSavedNetworkCallbacks?)

    /**
     * An asynchronous API to search for a set of saved networks.
     *
     * @param request The details of the request to search for a set of saved networks
     * @param callbacks The callbacks for when the result of searching for a set of saved networks is returned
     *
     * @see SearchForSavedNetworksRequest
     * @see SearchForSavedNetworksCallbacks
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    fun searchForSavedNetworks(request: SearchForSavedNetworksRequest, callbacks: SearchForSavedNetworksCallbacks?)
}
