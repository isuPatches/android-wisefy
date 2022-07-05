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

import com.isupatches.android.wisefy.accesspoints.entities.AccessPointData
import com.isupatches.android.wisefy.core.base.BaseWisefyCallbacks

/**
 * A set of callbacks for retrieving nearby access points.
 *
 * @see BaseWisefyCallbacks
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
interface GetNearbyAccessPointCallbacks : BaseWisefyCallbacks {

    /**
     * A callback triggered when there are nearby access points.
     *
     * @param accessPoints The list of nearby access points
     *
     * @see AccessPointData
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    fun onNearbyAccessPointsRetrieved(accessPoints: List<AccessPointData>)

    /**
     * A callback triggered when there are no nearby access points.
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    fun onNoNearbyAccessPoints()
}
