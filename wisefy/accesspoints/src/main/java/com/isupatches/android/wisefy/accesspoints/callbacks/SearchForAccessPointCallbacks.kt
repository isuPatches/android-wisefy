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
 * A set of callbacks for retrieving a single nearby access point.
 *
 * @see BaseWisefyCallbacks
 *
 * @author Patches Klinefelter
 * @since 08/2022, version 5.0.0
 */
interface SearchForAccessPointCallbacks : BaseWisefyCallbacks {

    /**
     * A callback triggered when there is a matching nearby access point.
     *
     * @param accessPoint The data representing the matching nearby access point
     *
     * @see AccessPointData
     *
     * @author Patches Klinefelter
     * @since 08/2022, version 5.0.0
     */
    fun onAccessPointFound(accessPoint: AccessPointData)

    /**
     * A callback triggered when there is no matching nearby access point.
     *
     * @author Patches Klinefelter
     * @since 08/2022, version 5.0.0
     */
    fun onNoAccessPointFound()
}
