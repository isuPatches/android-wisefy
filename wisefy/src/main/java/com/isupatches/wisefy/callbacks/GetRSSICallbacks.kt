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
 * Callbacks for retrieving RSSI level of a nearby access point.
 *
 * @see [BaseCallback]
 * @see [com.isupatches.wisefy.WiseFy.getRSSI]
 *
 * @author Patches
 * @since 3.0
 */
interface GetRSSICallbacks : BaseCallback {

    /**
     * Called when WiseFy has successfully retrieved the RSSI level of a network.
     *
     * @param rssi The RSSI of the network (either the current one or one that is passed in)
     *
     * @author Patches
     * @since 3.0
     */
    fun retrievedRSSI(rssi: Int)

    /**
     * Called when the network to retrieve the RSSI level of is not found
     * in the list of nearby access points.
     *
     * @author Patches
     * @since 3.0
     */
    fun networkNotFoundToRetrieveRSSI()
}
