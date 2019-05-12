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

import android.net.wifi.WifiInfo

/**
 * Callbacks for retrieving a device's current network.
 *
 * @see [BaseCallback]
 * @see [com.isupatches.wisefy.WiseFy.getCurrentNetwork]
 *
 * Updates
 * - 05/12/2019: Added noCurrentNetwork callback
 *
 * @author Patches
 * @since 3.0
 */
interface GetCurrentNetworkCallbacks : BaseCallback {

    /**
     * Called when the Android OS returns no current network.
     *
     * @author Patches
     * @since 4.0
     */
    fun noCurrentNetwork()

    /**
     * Called when WiseFy has successfully retrieved the device's current network.
     *
     * @see [WifiInfo]
     *
     * @author Patches
     * @since 3.0
     */
    fun retrievedCurrentNetwork(currentNetwork: WifiInfo)
}
