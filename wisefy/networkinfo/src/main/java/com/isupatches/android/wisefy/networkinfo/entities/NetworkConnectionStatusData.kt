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
package com.isupatches.android.wisefy.networkinfo.entities

/**
 * A data representation of the current network connection status from Android OS level returns.
 *
 * @property isConnected If the device is connected to a mobile or wifi network
 * @property isConnectedToMobileNetwork If the device is connected to a mobile network
 * @property isConnectedToWifiNetwork If the device is connected to a wifi network
 * @property isRoaming If the device is roaming
 * @property ssidOfNetworkConnectedTo The current SSID that the device is connected to, or null
 * @property bssidOfNetworkConnectedTo The current BSSID that the device is connected to, or null
 * @property ip The IP address of the device from it's current network, or null
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
data class NetworkConnectionStatusData(
    val isConnected: Boolean,
    val isConnectedToMobileNetwork: Boolean,
    val isConnectedToWifiNetwork: Boolean,
    val isRoaming: Boolean,
    val ssidOfNetworkConnectedTo: String?,
    val bssidOfNetworkConnectedTo: String?,
    val ip: String?
)
