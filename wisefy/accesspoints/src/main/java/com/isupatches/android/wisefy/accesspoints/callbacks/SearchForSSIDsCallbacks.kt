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

import com.isupatches.android.wisefy.accesspoints.entities.SSIDData
import com.isupatches.android.wisefy.core.base.BaseWisefyCallbacks

/**
 * A set of callbacks for retrieving multiple SSIDs.
 *
 * @see BaseWisefyCallbacks
 *
 * @author Patches Klinefelter
 * @since 08/2022, version 5.0.0
 */
interface SearchForSSIDsCallbacks : BaseWisefyCallbacks {

    /**
     * A callback triggered when there are matching SSIDs.
     *
     * @param ssids The list of matching SSIDs
     *
     * @see SSIDData
     *
     * @author Patches Klinefelter
     * @since 08/2022, version 5.0.0
     */
    fun onSSIDsFound(ssids: List<SSIDData>)

    /**
     * A callback triggered when there are no matching matching SSIDs.
     *
     * @author Patches Klinefelter
     * @since 08/2022, version 5.0.0
     */
    fun onNoSSIDsFound()
}
