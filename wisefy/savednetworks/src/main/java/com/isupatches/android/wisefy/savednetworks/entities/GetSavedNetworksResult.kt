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
 * A set of classes and objects that are used to represent a result while getting all of the saved networks on the
 * device.
 *
 * @author Patches Klinefelter
 * @since 07/2022, version 5.0.0
 */
sealed class GetSavedNetworksResult {

    /**
     * A data representation of no networks being saved on the device.
     *
     * @see GetSavedNetworksResult
     *
     * @author Patches Klinefelter
     * @since 07/2022, version 5.0.0
     */
    object Empty : GetSavedNetworksResult()

    /**
     * A data representation of a success retrieving saved networks on the device.
     *
     * @property value The saved networks retrieved
     *
     * @see GetSavedNetworksResult
     * @see SavedNetworkData
     *
     * @author Patches Klinefelter
     * @since 07/2022, version 5.0.0
     */
    data class SavedNetworks(val value: List<SavedNetworkData>) : GetSavedNetworksResult()
}
