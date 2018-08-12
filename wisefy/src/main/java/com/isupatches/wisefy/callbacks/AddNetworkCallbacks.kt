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
 * Callbacks for adding a network as a saved configuration on a device.
 *
 * @see [com.isupatches.wisefy.WiseFy.addOpenNetwork]
 * @see [com.isupatches.wisefy.WiseFy.addWEPNetwork]
 * @see [com.isupatches.wisefy.WiseFy.addWPA2Network]
 *
 * @author Patches
 * @since 3.0
 */
interface AddNetworkCallbacks : BaseCallback {

    /**
     * Called when [android.net.wifi.WifiManager] encounters an error adding a network.
     *
     * @see [android.net.wifi.WifiManager]
     * @see [com.isupatches.wisefy.WiseFy.WIFI_MANAGER_FAILURE]
     *
     * @author Patches
     * @since 3.0
     */
    fun failureAddingNetwork(wifiManagerReturn: Int)

    /**
     * Called upon successfully adding a network.
     *
     * @see [android.net.wifi.WifiConfiguration]
     *
     * @author Patches
     * @since 3.0
     */
    fun networkAdded(newNetworkId: Int, networkConfig: WifiConfiguration)
}
