/*
 * Copyright 2019 Patches Klinefelter
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
 * Callbacks for retrieving a saved network on a device.
 *
 * @see [BaseCallback]
 * @see [com.isupatches.wisefy.WiseFy.searchForSavedNetwork]
 *
 * @author Patches
 * @see 4.0
 */
interface SearchForSavedNetworkCallbacks : BaseCallback {

    /**
     * Called when there are no saved network configurations matching search criteria.
     *
     * @author Patches
     * @since 4.0
     */
    fun savedNetworkNotFound()

    /**
     * Called when WiseFy has successfully retrieved a matching saved network configuration.
     *
     * @see [WifiConfiguration]
     *
     * @author Patches
     * @since 4.0
     */
    fun retrievedSavedNetwork(savedNetwork: WifiConfiguration)
}
