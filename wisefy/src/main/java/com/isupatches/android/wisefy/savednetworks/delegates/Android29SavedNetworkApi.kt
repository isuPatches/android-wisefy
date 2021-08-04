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

import com.isupatches.android.wisefy.logging.WisefyLogger
import com.isupatches.android.wisefy.savednetworks.entities.SavedNetworkData

internal interface Android29SavedNetworkApi {
    fun getSavedNetworks(): List<SavedNetworkData>

    fun isNetworkSaved(ssid: String): Boolean

    fun searchForSavedNetwork(regexForSSID: String): SavedNetworkData?

    fun searchForSavedNetworks(regexForSSID: String): List<SavedNetworkData>
}

private const val LOG_TAG = "Android29SavedNetworkApiImpl"

internal class Android29SavedNetworkApiImpl(
    private val logger: WisefyLogger?
) : Android29SavedNetworkApi {

    override fun getSavedNetworks(): List<SavedNetworkData> {
        logger?.w(LOG_TAG, "There is no known way to see saved networks with Android Q")
        return emptyList()
    }

    override fun isNetworkSaved(ssid: String): Boolean {
        logger?.w(LOG_TAG, "There is no known way to see saved networks with Android Q")
        return false
    }

    override fun searchForSavedNetwork(regexForSSID: String): SavedNetworkData? {
        logger?.w(LOG_TAG, "There is no known way to see saved networks with Android Q")
        return null
    }

    override fun searchForSavedNetworks(regexForSSID: String): List<SavedNetworkData> {
        logger?.w(LOG_TAG, "There is no known way to see saved networks with Android Q")
        return emptyList()
    }
}
