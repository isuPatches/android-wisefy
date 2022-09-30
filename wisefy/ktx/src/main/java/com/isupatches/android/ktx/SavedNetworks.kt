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
package com.isupatches.android.ktx

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.ACCESS_WIFI_STATE
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.WisefyApi
import com.isupatches.android.wisefy.core.exceptions.WisefyException
import com.isupatches.android.wisefy.savednetworks.callbacks.GetSavedNetworksCallbacks
import com.isupatches.android.wisefy.savednetworks.callbacks.IsNetworkSavedCallbacks
import com.isupatches.android.wisefy.savednetworks.callbacks.SearchForSavedNetworksCallbacks
import com.isupatches.android.wisefy.savednetworks.entities.GetSavedNetworksRequest
import com.isupatches.android.wisefy.savednetworks.entities.GetSavedNetworksResult
import com.isupatches.android.wisefy.savednetworks.entities.IsNetworkSavedRequest
import com.isupatches.android.wisefy.savednetworks.entities.IsNetworkSavedResult
import com.isupatches.android.wisefy.savednetworks.entities.SavedNetworkData
import com.isupatches.android.wisefy.savednetworks.entities.SearchForSavedNetworksRequest
import com.isupatches.android.wisefy.savednetworks.entities.SearchForSavedNetworksResult
import kotlin.coroutines.suspendCoroutine
import kotlin.jvm.Throws

/**
 * A coroutine extension for getting all of the saved networks on a device.
 *
 * *NOTES*
 *  - Internally locked by a mutex for all saved network related functionality (f.e. add, remove, get, search, etc.)
 *
 * @param request The details of the request to get all saved networks on the device
 *
 * @see GetSavedNetworksRequest
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
    request: GetSavedNetworksRequest = GetSavedNetworksRequest()
): GetSavedNetworksResult =
    suspendCoroutine { continuation ->
        getSavedNetworks(
            request = request,
            callbacks = object : GetSavedNetworksCallbacks {
                override fun onNoSavedNetworksFound() {
                    continuation.resumeWith(Result.success(GetSavedNetworksResult.Success.Empty))
                }

                override fun onSavedNetworksRetrieved(savedNetworks: List<SavedNetworkData>) {
                    continuation.resumeWith(Result.success(GetSavedNetworksResult.Success.SavedNetworks(savedNetworks)))
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
 * @see IsNetworkSavedRequest
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
suspend fun WisefyApi.isNetworkSavedAsync(request: IsNetworkSavedRequest): IsNetworkSavedResult =
    suspendCoroutine { continuation ->
        isNetworkSaved(
            request = request,
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

/**
 * A coroutine extension for searching for saved networks.
 *
 * *NOTES*
 *  - Internally locked by a mutex for all saved network related functionality (f.e. add, remove, get, search, etc.)
 *
 * @param request The details of the request to search for saved networks
 *
 * @see SearchForSavedNetworksRequest
 * @see SearchForSavedNetworksResult
 *
 * @return SearchForSavedNetworkResult - The result of searching for saved networks
 *
 * @throws WisefyException
 *
 * @author Patches Klinefelter
 * @since 07/2022, version 5.0.0
 */
@Throws(WisefyException::class)
@RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
suspend fun WisefyApi.searchForSavedNetworkAsync(request: SearchForSavedNetworksRequest): SearchForSavedNetworksResult =
    suspendCoroutine { continuation ->
        searchForSavedNetworks(
            request = request,
            callbacks = object : SearchForSavedNetworksCallbacks {
                override fun onNoSavedNetworksFound() {
                    continuation.resumeWith(Result.success(SearchForSavedNetworksResult.Success.Empty))
                }

                override fun onSavedNetworksRetrieved(savedNetworks: List<SavedNetworkData>) {
                    continuation.resumeWith(
                        Result.success(SearchForSavedNetworksResult.Success.SavedNetworks(savedNetworks))
                    )
                }

                override fun onWisefyAsyncFailure(exception: WisefyException) {
                    continuation.resumeWith(Result.failure(exception))
                }
            }
        )
    }
