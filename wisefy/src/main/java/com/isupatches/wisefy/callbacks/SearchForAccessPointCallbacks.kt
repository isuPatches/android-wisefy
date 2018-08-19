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

import android.net.wifi.ScanResult

/**
 * Callbacks for finding a nearby access point on a device.
 *
 * @see [BaseCallback]
 * @see [com.isupatches.wisefy.WiseFy.searchForAccessPoint]
 *
 * @author Patches
 * @since 3.0
 */
interface SearchForAccessPointCallbacks : BaseCallback {

    /**
     * Called when WiseFy has successfully found a matching access point.
     *
     * @see [ScanResult]
     *
     * @author Patches
     * @since 3.0
     */
    fun accessPointFound(accessPoint: ScanResult)

    /**
     * Called when WiseFy times out trying to find a matching access point.
     *
     * @author Patches
     * @since 3.0
     */
    fun accessPointNotFound()
}
