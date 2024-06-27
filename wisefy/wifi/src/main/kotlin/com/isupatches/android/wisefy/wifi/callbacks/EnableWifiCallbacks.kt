/*
 * Copyright 2022 Patches Barrett
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
package com.isupatches.android.wisefy.wifi.callbacks

import com.isupatches.android.wisefy.core.base.BaseWisefyCallbacks
import com.isupatches.android.wisefy.wifi.entities.EnableWifiResult

/**
 * A set of callbacks for enabling wifi.
 *
 * @see BaseWisefyCallbacks
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
interface EnableWifiCallbacks : BaseWisefyCallbacks {

    /**
     * A callback triggered when there is a failure enabling wifi.
     *
     * @param result The details from the failure while attempting to enabling wifi
     *
     * @see EnableWifiResult.Failure
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    fun onFailureEnablingWifi(result: EnableWifiResult.Failure)

    /**
     * A callback triggered when wifi is successfully enabled.
     *
     * @param result The details from the successful attempt enabling wifi
     *
     * @see EnableWifiResult.Success
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    fun onSuccessEnablingWifi(result: EnableWifiResult.Success)
}
