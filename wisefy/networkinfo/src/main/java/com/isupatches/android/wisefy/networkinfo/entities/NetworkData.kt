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

import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.net.wifi.WifiInfo

/**
 * A data representation of the current network from Android OS level returns.
 *
 * @property network The raw value of the current network from the Android OS
 * @property connectionInfo The raw value of the current connection info from the Android OS
 * @property capabilities The raw value of he network capabilities from the Android OS
 * @property linkProperties The raw value of the link properties from the Android OS
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
data class NetworkData(
    val network: Network?,
    val connectionInfo: WifiInfo?,
    val capabilities: NetworkCapabilities?,
    val linkProperties: LinkProperties?
)
