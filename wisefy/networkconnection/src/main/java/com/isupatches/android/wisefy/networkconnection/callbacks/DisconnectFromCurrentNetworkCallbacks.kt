/*
 * Copyright 2022 Patches Barrett
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
import com.isupatches.android.wisefy.networkconnection.entities.DisconnectFromCurrentNetworkResult

/**
 * A set of callbacks for disconnecting from the current network.
 *
 * @see BaseWisefyCallbacks
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
interface DisconnectFromCurrentNetworkCallbacks : BaseWisefyCallbacks {

    /**
     * A callback triggered when there is a failure disconnecting from the current network.
     *
     * @param result The details from the failure while attempting to disconnect from the current network
     *
     * @see DisconnectFromCurrentNetworkResult.Failure
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    fun onFailureDisconnectingFromCurrentNetwork(result: DisconnectFromCurrentNetworkResult.Failure)

    /**
     * A callback triggered when there is a success while disconnecting from the current network.
     *
     * @param result The details from the successful attempt disconnecting from the current network
     *
     * @see DisconnectFromCurrentNetworkResult.Success
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    fun onSuccessDisconnectingFromCurrentNetwork(result: DisconnectFromCurrentNetworkResult.Success)
}
