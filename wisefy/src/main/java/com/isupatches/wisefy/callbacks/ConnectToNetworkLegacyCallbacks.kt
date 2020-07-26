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

/**
 * Callbacks for attempting to connect to a network.
 *
 * @see [BaseCallback]
 * @see [com.isupatches.wisefy.WiseFy.connectToNetwork]
 *
 * @author Patches
 * @since 3.0
 */
interface ConnectToNetworkLegacyCallbacks : BaseCallback {

    /**
     * Called when WiseFy has successfully connected to a network.
     *
     * @author Patches
     * @since 3.0
     */
    fun connectedToNetwork()

    /**
     * Called when the network was found, but there was an issue attempting to connect to
     * it with [android.net.wifi.WifiManager].
     *
     * @see [android.net.wifi.WifiManager]
     *
     * @author Patches
     * @since 3.0
     */
    fun failureConnectingToNetwork()

    /**
     * Called when the SSID of the network to connect to cannot be found by [android.net.wifi.WifiManager]
     * and WiseFy.
     *
     * @see [android.net.wifi.WifiManager]
     *
     * @author Patches
     * @since 3.0
     */
    fun networkNotFoundToConnectTo()
}
