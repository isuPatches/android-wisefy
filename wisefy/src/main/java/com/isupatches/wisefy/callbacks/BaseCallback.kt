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

import com.isupatches.wisefy.constants.WiseFyCode

/**
 * Callbacks that are applicable to all Async WiseFy operations.
 * All Async interfaces should extend this class.
 *
 * @author Patches
 * @since 3.0
 */
interface BaseCallback {

    /**
     * Called when [com.isupatches.wisefy.WiseFyPrechecks] determines that there
     * is an issue and an operation cannot be completed.
     *
     * Example: A null or empty ssid is passed in but is a required parameter.
     *
     * @see [com.isupatches.wisefy.WiseFyPrechecks]
     * @see [com.isupatches.wisefy.constants.WiseFyCode]
     *
     * @author Patches
     * @since 3.0
     */
    fun wisefyFailure(@WiseFyCode wisefyFailureCode: Int)
}
