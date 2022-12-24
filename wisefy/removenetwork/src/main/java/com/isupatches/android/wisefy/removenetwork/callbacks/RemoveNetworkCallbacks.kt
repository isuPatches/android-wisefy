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
package com.isupatches.android.wisefy.removenetwork.callbacks

import com.isupatches.android.wisefy.core.base.BaseWisefyCallbacks
import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkResult

/**
 * A set of callbacks for removing a network.
 *
 * @see BaseWisefyCallbacks
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
interface RemoveNetworkCallbacks : BaseWisefyCallbacks {

    /**
     * A callback triggered when there is a failure removing a network.
     *
     * @param result The result of removing the network
     *
     * @see RemoveNetworkResult
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    fun onFailureRemovingNetwork(result: RemoveNetworkResult.Failure)

    /**
     * A callback triggered when there is a success removing a network.
     *
     * @param result The result of removing the network
     *
     * @see RemoveNetworkResult
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    fun onSuccessRemovingNetwork(result: RemoveNetworkResult.Success)
}
