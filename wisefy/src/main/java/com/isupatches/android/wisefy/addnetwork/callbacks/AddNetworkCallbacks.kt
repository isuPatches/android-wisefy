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
package com.isupatches.android.wisefy.addnetwork.callbacks

import com.isupatches.android.wisefy.addnetwork.entities.AddNetworkResult
import com.isupatches.android.wisefy.callbacks.BaseWisefyCallbacks

/**
 * A set of callbacks for adding a network.
 *
 * @see BaseWisefyCallbacks
 *
 * @author Patches Klinefelter
 * @since 02/2022
 */
interface AddNetworkCallbacks : BaseWisefyCallbacks {

    /**
     * A callback triggered when there is a failure adding a network.
     *
     * @param result The details from the failure while attempting to add the network
     *
     * @see AddNetworkResult.Failure
     *
     * @author Patches Klinefelter
     * @since 02/2022
     */
    fun onFailureAddingNetwork(result: AddNetworkResult.Failure)

    /**
     * A callback triggered when a result is returned for adding a network.
     *
     * @param result The details from the success while attempting to add the network
     *
     * @see AddNetworkResult.Success
     *
     * @author Patches Klinefelter
     * @since 02/2022
     */
    fun onNetworkAdded(result: AddNetworkResult.Success)
}
