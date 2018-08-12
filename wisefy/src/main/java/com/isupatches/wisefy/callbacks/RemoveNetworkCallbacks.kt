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
 * Callbacks for removing a saved network on a device.
 *
 * @see [com.isupatches.wisefy.WiseFy.removeNetwork]
 *
 * @author Patches
 * @since 3.0
 */
interface RemoveNetworkCallbacks : BaseCallback {

    /**
     * Called when there is an issue removing a saved network configuration with WifiManager.
     *
     * @author Patches
     * @since 3.0
     */
    fun failureRemovingNetwork()

    /**
     * Called when the network is not found in the save network configuration list.
     *
     * This is not necessarily an error as it may have never been stored,
     * removed manually, or various other scenarios.
     *
     * @author Patches
     * @since 3.0
     */
    fun networkNotFoundToRemove()

    /**
     * Called when WiseFy has successfully removed a network from the
     * list of saved network configurations.
     *
     * @author Patches
     * @since 3.0
     */
    fun networkRemoved()
}
