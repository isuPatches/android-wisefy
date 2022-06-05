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
package com.isupatches.android.wisefy.wifi.entities

/**
 * A set of classes that are data representations of a result when disabling Wifi.
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
sealed class DisableWifiResult {

    /**
     * A data representation for when there is a success disabling Wifi.
     *
     * @see DisableWifiResult
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    object True : DisableWifiResult()

    /**
     * A data representation for when there is a failure disabling Wifi.
     *
     * @see DisableWifiResult
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    object False : DisableWifiResult()
}
