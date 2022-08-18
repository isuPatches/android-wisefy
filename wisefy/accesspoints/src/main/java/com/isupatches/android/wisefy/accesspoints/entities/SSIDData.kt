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
package com.isupatches.android.wisefy.accesspoints.entities

/**
 * A set of data representations for a network's SSID value.
 *
 * @author Patches Klinefelter
 * @since 08/2022, version 5.0.0
 */
sealed class SSIDData {

    /**
     * A data representations for a network's SSID value.
     *
     * @property value The string value of the SSID for a network
     *
     * @see SSIDData
     *
     * @author Patches Klinefelter
     * @since 08/2022, version 5.0.0
     */
    data class SSID(val value: String) : SSIDData()

    /**
     * A data representations for a network's BSSID value.
     *
     * @property value The string value of the BSSID for a network
     *
     * @see SSIDData
     *
     * @author Patches Klinefelter
     * @since 08/2022, version 5.0.0
     */
    data class BSSID(val value: String) : SSIDData()
}
