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
package com.isupatches.android.wisefy.removenetwork.entities

/**
 * A set of classes and objects that are used to represent requests for removing a network.
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
sealed class RemoveNetworkRequest {

    /**
     * A data representation to remove a network by SSID.
     *
     * *NOTE*
     * - The request will only remove the first network whose SSID matches
     *
     * @property ssid The SSID value of the network to remove
     *
     * @see RemoveNetworkRequest
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    data class SSID(val ssid: String) : RemoveNetworkRequest()

    /**
     * A data representation to remove a network by BSSID.
     *
     * *NOTE*
     * - The request will only remove the first network whose SSID matches
     *
     * @property bssid The BSSID value of the network to remove
     *
     * @see RemoveNetworkRequest
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    data class BSSID(val bssid: String) : RemoveNetworkRequest()
}
