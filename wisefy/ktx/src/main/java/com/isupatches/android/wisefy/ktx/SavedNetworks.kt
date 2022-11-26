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
package com.isupatches.android.wisefy.ktx

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.ACCESS_WIFI_STATE
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.WisefyApi
import com.isupatches.android.wisefy.core.exceptions.WisefyException
import com.isupatches.android.wisefy.savednetworks.callbacks.GetSavedNetworksCallbacks
import com.isupatches.android.wisefy.savednetworks.callbacks.IsNetworkSavedCallbacks
import com.isupatches.android.wisefy.savednetworks.entities.GetSavedNetworksQuery
import com.isupatches.android.wisefy.savednetworks.entities.GetSavedNetworksResult
import com.isupatches.android.wisefy.savednetworks.entities.IsNetworkSavedQuery
import com.isupatches.android.wisefy.savednetworks.entities.IsNetworkSavedResult
import com.isupatches.android.wisefy.savednetworks.entities.SavedNetworkData
import kotlin.coroutines.suspendCoroutine
import kotlin.jvm.Throws

/**
 * A coroutine extension for getting all of the saved networks on a device.
 *
 * *NOTES*
 *  - Internally locked by a mutex for all saved network related functionality (f.e. add, remove, get, search, etc.)
 *
 * @param query The details of the query to get all saved networks on the device
 *
 * @see GetSavedNetworksQuery
 * @see GetSavedNetworksResult
 *
 * @return GetSavedNetworksResult - The result of getting the saved networks on the device
 *
 * @throws WisefyException
 *
 * @author Patches Klinefelter
 * @since 07/2022, version 5.0.0
 */
@Throws(WisefyException::class)
@RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
suspend fun WisefyApi.getSavedNetworksAsync(
    query: GetSavedNetworksQuery = GetSavedNetworksQuery.All
): GetSavedNetworksResult =
    suspendCoroutine { continuation ->
        getSavedNetworks(
            query = query,
            callbacks = object : GetSavedNetworksCallbacks {
                override fun onNoSavedNetworksFound() {
                    continuation.resumeWith(Result.success(GetSavedNetworksResult.Empty))
                }

                override fun onSavedNetworksRetrieved(savedNetworks: List<SavedNetworkData>) {
                    continuation.resumeWith(Result.success(GetSavedNetworksResult.SavedNetworks(savedNetworks)))
                }

                override fun onWisefyAsyncFailure(exception: WisefyException) {
                    continuation.resumeWith(Result.failure(exception))
                }
            }
        )
    }

/**
 * A coroutine extension for checking if a network is saved on a device.
 *
 * *NOTES*
 *  - Internally locked by a mutex for all saved network related functionality (f.e. add, remove, get, search, etc.)
 *
 * @param request The details of the request to check if a network is saved on a device
 *
 * @see IsNetworkSavedQuery
 * @see IsNetworkSavedResult
 *
 * @return IsNetworkSavedResult - The result of checking if a network is saved on the device.
 *
 * @throws WisefyException
 *
 * @author Patches Klinefelter
 * @since 07/2022, version 5.0.0
 */
@Throws(WisefyException::class)
@RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
suspend fun WisefyApi.isNetworkSavedAsync(query: IsNetworkSavedQuery): IsNetworkSavedResult =
    suspendCoroutine { continuation ->
        isNetworkSaved(
            query = query,
            callbacks = object : IsNetworkSavedCallbacks {
                override fun onNetworkIsSaved() {
                    continuation.resumeWith(Result.success(IsNetworkSavedResult.True))
                }

                override fun onNetworkIsNotSaved() {
                    continuation.resumeWith(Result.success(IsNetworkSavedResult.False))
                }

                override fun onWisefyAsyncFailure(exception: WisefyException) {
                    continuation.resumeWith(Result.failure(exception))
                }
            }
        )
    }
