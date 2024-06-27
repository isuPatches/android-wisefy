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
package com.isupatches.android.wisefy.wifi.entities

import android.content.Context

/**
 * A set of classes and objects that represent requests to disable wifi.
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
sealed class DisableWifiRequest {

    /**
     * A representation of a request to disable wifi on a device before Android Q / SDK 29.
     *
     * @see DisableWifiRequest
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    object Default : DisableWifiRequest()

    /**
     * A representation of a request to disable wifi on an Android Q / SDK 29 or higher device.
     *
     * @property context The reference to the context to place the request to disable wifi on Android Q / SDK 29
     * or higher devices.
     *
     * @see DisableWifiRequest
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    data class Android29OrAbove(val context: Context) : DisableWifiRequest()
}
