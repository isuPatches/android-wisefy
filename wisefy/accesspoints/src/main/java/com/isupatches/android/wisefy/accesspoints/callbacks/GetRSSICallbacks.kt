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
package com.isupatches.android.wisefy.accesspoints.callbacks

import com.isupatches.android.wisefy.accesspoints.entities.RSSIData
import com.isupatches.android.wisefy.core.base.BaseWisefyCallbacks

/**
 * A set of callbacks for retrieving the RSSI level of a nearby access point.
 *
 * @see BaseWisefyCallbacks
 *
 * @author Patches Klinefelter
 * @since 08/2022, version 5.0.0
 */
interface GetRSSICallbacks : BaseWisefyCallbacks {

    /**
     * A callback triggered when there is a matching access point to retrieve RSSI data.
     *
     * @param rssi The RSSI data retrieved for the nearby access point
     *
     * @see RSSIData
     *
     * @author Patches Klinefelter
     * @since 08/2022, version 5.0.0
     */
    fun onRSSIRetrieved(rssi: RSSIData)

    /**
     * A callback triggered when there is no matching nearby access point to retrieve RSSI data.
     *
     * @author Patches Klinefelter
     * @since 08/2022, version 5.0.0
     */
    fun onNoNetworkToRetrieveRSSI()
}
