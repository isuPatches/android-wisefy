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
package com.isupatches.android.wisefy.networkconnection.callbacks

import com.isupatches.android.wisefy.core.base.BaseWisefyCallbacks

/**
 * A set of callbacks for disconnecting from the current network.
 *
 * @see BaseWisefyCallbacks
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
interface DisconnectFromCurrentNetworkCallbacks : BaseWisefyCallbacks {

    /**
     * A callback triggered when the device has successfully disconnected from the current network.
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    fun onDisconnectedFromCurrentNetwork()

    /**
     * A callback triggered when a request is placed to disconnect from the current network.
     *
     * *NOTES*
     *  - Android 30 specific
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    fun onDisconnectRequestPlaced()

    /**
     * A callback triggered when there is a failure disconnecting from the current network.
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    fun onFailureDisconnectingFromCurrentNetwork()

    /**
     * A callback triggered when there is a no current network found to disconnect from.
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    fun onNetworkNotFoundToDisconnectFrom()
}
