/*
 * Copyright 2018 Patches Klinefelter
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
package com.isupatches.wisefy.callbacks

import android.net.wifi.WifiConfiguration

/**
 * Callbacks for retrieving a list of saved networks on a device.
 *
 * @see com.isupatches.wisefy.WiseFy.getSavedNetworks
 *
 * @author Patches
 * @since 3.0
 */
interface GetSavedNetworksCallbacks : BaseCallback {

    /**
     * Called when there are no saved network configuration on the device.
     *
     * @author Patches
     * @since 3.0
     */
    fun noSavedNetworksFound()

    /**
     * Called when WiseFy has successfully retrieved a list of saved networks.
     *
     * @author Patches
     * @since 3.0
     */
    fun retrievedSavedNetworks(savedNetworks: List<WifiConfiguration>)
}
