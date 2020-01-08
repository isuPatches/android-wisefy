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
 * Callbacks for finding a list of nearby SSIDs on a device.
 *
 * @see [BaseCallback]
 * @see [com.isupatches.wisefy.WiseFy.searchForSSIDs]
 *
 * @author Patches
 * @since 3.0
 */
interface SearchForSSIDsCallbacks : BaseCallback {

    /**
     * Called when WiseFy has successfully found access points with a matching SSID.
     *
     * @param ssids The list of found SSIDs
     *
     * @author Patches
     * @since 3.0
     */
    fun retrievedSSIDs(ssids: List<String>)

    /**
     * Called when WiseFy times out trying to find access points with a matching SSID.
     *
     * @author Patches
     * @since 3.0
     */
    fun noSSIDsFound()
}
