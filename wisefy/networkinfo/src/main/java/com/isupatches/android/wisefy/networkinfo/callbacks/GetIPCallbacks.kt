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
package com.isupatches.android.wisefy.networkinfo.callbacks

import com.isupatches.android.wisefy.core.base.BaseWisefyCallbacks
import com.isupatches.android.wisefy.networkinfo.entities.IPData

/**
 * A set of callbacks for retrieving the current device's IP.
 *
 * @see BaseWisefyCallbacks
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
interface GetIPCallbacks : BaseWisefyCallbacks {

    /**
     * A callback triggered when there is a success getting the device's IP.
     *
     * @param ip The IP data for the device
     *
     * @see IPData
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    fun onIPRetrieved(ip: IPData)

    /**
     * A callback triggered when there is failure retrieving the device's IP.
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    fun onFailureRetrievingIP()
}
