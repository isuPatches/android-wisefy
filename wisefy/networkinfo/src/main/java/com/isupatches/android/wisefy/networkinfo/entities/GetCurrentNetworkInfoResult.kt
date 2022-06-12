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
 * A set of classes and objects that are used to represent results for getting information for a network.
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
sealed class GetCurrentNetworkInfoResult {

    /**
     * A data representation for when there is no information for a network.
     *
     * @see GetCurrentNetworkInfoResult
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    object Empty : GetCurrentNetworkInfoResult()

    /**
     * A data representation for when there is a success retrieving the device's current IP address.
     *
     * @property data The information value for the network
     *
     * @see GetCurrentNetworkInfoResult
     * @see NetworkInfoData
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    data class NetworkInfo(val data: NetworkInfoData) : GetCurrentNetworkInfoResult()
}
