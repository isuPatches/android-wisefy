/*
 * Copyright 2021 Patches Klinefelter
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
package com.isupatches.android.wisefy.networkinfo

import android.Manifest
import android.Manifest.permission.ACCESS_NETWORK_STATE
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.net.wifi.WifiInfo
import androidx.annotation.RequiresPermission

interface NetworkInfoApi {

    fun getCurrentNetwork(): WifiInfo?

    @Deprecated("")
    fun getCurrentNetworkInfo(): NetworkInfo?

    fun getIP(): String?

    @RequiresPermission(ACCESS_NETWORK_STATE)
    fun getNetworkCapabilities(network: Network? = null): NetworkCapabilities?

    @RequiresPermission(ACCESS_NETWORK_STATE)
    fun getNetworkLinkProperties(network: Network? = null): LinkProperties?
}
