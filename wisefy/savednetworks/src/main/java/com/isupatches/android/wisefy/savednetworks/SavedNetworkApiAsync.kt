/*
 * Copyright 2022 Patches Barrett
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
import com.isupatches.android.wisefy.savednetworks.callbacks.IsNetworkSavedCallbacks
import com.isupatches.android.wisefy.savednetworks.entities.GetSavedNetworksQuery
import com.isupatches.android.wisefy.savednetworks.entities.IsNetworkSavedQuery

/**
 * A set of asynchronous APIs for getting and searching for saved networks.
 *
 * @author Patches Barrett
 * @since 07/2022, version 5.0.0
 */
interface SavedNetworkApiAsync {

    /**
     * An asynchronous API to get the saved networks on the device.
     *
     * @param query The details of the query to get the saved networks on the device
     * @param callbacks The callbacks for when the result for getting saved networks on the device is returned
     *
     * @see GetSavedNetworksQuery
     * @see GetSavedNetworksCallbacks
     *
     * @author Patches Barrett
     * @since 07/2022, version 5.0.0
     */
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    fun getSavedNetworks(
        query: GetSavedNetworksQuery = GetSavedNetworksQuery.All,
        callbacks: GetSavedNetworksCallbacks?
    )

    /**
     * An asynchronous API to check if a network is saved on the device.
     *
     * @param query The details of the query to check if a network is saved on the device
     * @param callbacks The callbacks for the result of whether the network is saved on the device
     *
     * @see IsNetworkSavedQuery
     * @see IsNetworkSavedCallbacks
     *
     * @author Patches Barrett
     * @since 07/2022, version 5.0.0
     */
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    fun isNetworkSaved(query: IsNetworkSavedQuery, callbacks: IsNetworkSavedCallbacks?)
}
