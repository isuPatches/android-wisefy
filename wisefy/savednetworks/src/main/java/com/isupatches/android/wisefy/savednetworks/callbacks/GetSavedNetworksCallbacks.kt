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
package com.isupatches.android.wisefy.savednetworks.callbacks

import com.isupatches.android.wisefy.core.base.BaseWisefyCallbacks
import com.isupatches.android.wisefy.savednetworks.entities.SavedNetworkData

/**
 * A set of callbacks for retrieving the saved networks on a device.
 *
 * @see BaseWisefyCallbacks
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
interface GetSavedNetworksCallbacks : BaseWisefyCallbacks {

    /**
     * A callback triggered when there are no saved networks on the device.
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    fun onNoSavedNetworksFound()

    /**
     * A callback triggered when there is a success retrieving saved networks on the device.
     *
     * @param savedNetworks The list of saved networks retrieved
     *
     * @see SavedNetworkData
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    fun onSavedNetworksRetrieved(savedNetworks: List<SavedNetworkData>)
}
