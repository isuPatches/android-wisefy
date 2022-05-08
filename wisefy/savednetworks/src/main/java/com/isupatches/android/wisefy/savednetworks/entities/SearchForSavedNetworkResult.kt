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
package com.isupatches.android.wisefy.savednetworks.entities

/**
 * A set of classes and objects that are used to represent a result when searching for a saved network.
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
sealed class SearchForSavedNetworkResult {

    /**
     * A data representation for when there is no matching saved network.
     *
     * @see SearchForSavedNetworkResult
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    object Empty : SearchForSavedNetworkResult()

    /**
     * A data representation for when there is a matching saved network.
     *
     * @property data The matching saved network
     *
     * @see SavedNetworkData
     * @see SearchForSavedNetworkResult
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    data class SavedNetwork(val data: SavedNetworkData) : SearchForSavedNetworkResult()
}
