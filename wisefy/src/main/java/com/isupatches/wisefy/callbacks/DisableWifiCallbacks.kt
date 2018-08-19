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
 * Callbacks for disabling a device's wifi.
 *
 * @see [BaseCallback]
 * @see [com.isupatches.wisefy.WiseFy.disableWifi]
 *
 * @author Patches
 * @since 3.0
 */
interface DisableWifiCallbacks : BaseCallback {

    /**
     * Called when there is an issue disabling Wifi on the device.
     *
     * @author Patches
     * @since 3.0
     */
    fun failureDisablingWifi()

    /**
     * Called when WiseFy has successfully disabled Wifi on the device.
     *
     * @author Patches
     * @since 3.0
     */
    fun wifiDisabled()
}
