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
package com.isupatches.android.wisefy.networkinfo.entities

/**
 * A set of classes and objects that are used to represent results for getting the device's IP address.
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
sealed class GetIPResult {

    /**
     * A data representation for when there is no current IP address for the device.
     *
     * @see GetIPResult
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    object Empty : GetIPResult()

    /**
     * A data representation for when there is a success retrieving the device's current IP address.
     *
     * @property data The IP value for the device
     *
     * @see GetIPResult
     * @see IPData
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    data class IPAddress(val data: IPData) : GetIPResult()
}
