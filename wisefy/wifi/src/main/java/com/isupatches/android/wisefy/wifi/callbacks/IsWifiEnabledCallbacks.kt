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

/**
 * A set of callbacks for checking the current state of Wifi.
 *
 * @see BaseWisefyCallbacks
 *
 * @author Patches Barrett
 * @since 07/2022, version 5.0.0
 */
interface IsWifiEnabledCallbacks : BaseWisefyCallbacks {

    /**
     * A callback triggered when Wifi is enabled.
     *
     * @author Patches Barrett
     * @since 07/2022, version 5.0.0
     */
    fun onWifiIsEnabled()

    /**
     * A callback triggered when Wifi is disabled.
     *
     * @author Patches Barrett
     * @since 07/2022, version 5.0.0
     */
    fun onWifiIsDisabled()
}
