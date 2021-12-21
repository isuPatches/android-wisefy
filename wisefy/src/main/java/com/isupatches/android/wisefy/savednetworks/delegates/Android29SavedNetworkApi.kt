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

import com.isupatches.android.wisefy.savednetworks.entities.SavedNetworkData
import com.isupatches.android.wisefy.savednetworks.entities.SearchForSavedNetworkRequest
import com.isupatches.android.wisefy.util.fail

internal interface Android29SavedNetworkApi {
    fun getSavedNetworks(): List<SavedNetworkData>

    fun isNetworkSaved(request: SearchForSavedNetworkRequest): Boolean

    fun searchForSavedNetwork(request: SearchForSavedNetworkRequest): SavedNetworkData?

    fun searchForSavedNetworks(request: SearchForSavedNetworkRequest): List<SavedNetworkData>
}

private const val ANDROID_Q_SAVED_NETWORK_WARNING =
    "There is no known way to see saved networks with Android Q"

internal class Android29SavedNetworkApiImpl : Android29SavedNetworkApi {

    override fun getSavedNetworks(): List<SavedNetworkData> {
        fail(ANDROID_Q_SAVED_NETWORK_WARNING)
        return emptyList()
    }

    override fun isNetworkSaved(request: SearchForSavedNetworkRequest): Boolean {
        fail(ANDROID_Q_SAVED_NETWORK_WARNING)
        return false
    }

    override fun searchForSavedNetwork(request: SearchForSavedNetworkRequest): SavedNetworkData? {
        fail(ANDROID_Q_SAVED_NETWORK_WARNING)
        return null
    }

    override fun searchForSavedNetworks(request: SearchForSavedNetworkRequest): List<SavedNetworkData> {
        fail(ANDROID_Q_SAVED_NETWORK_WARNING)
        return emptyList()
    }
}
