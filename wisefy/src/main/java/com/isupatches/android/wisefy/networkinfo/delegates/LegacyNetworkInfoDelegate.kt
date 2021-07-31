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
package com.isupatches.android.wisefy.networkinfo.delegates

import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.net.wifi.WifiInfo
import com.isupatches.android.wisefy.networkinfo.NetworkInfoApi

internal class LegacyNetworkInfoDelegate : NetworkInfoApi {

    override fun getCurrentNetwork(): WifiInfo? {
        TODO("Not yet implemented")
    }

    override fun getCurrentNetworkInfo(): NetworkInfo? {
        TODO("Not yet implemented")
    }

    override fun getIP(): String? {
        TODO("Not yet implemented")
    }

    override fun getNetworkCapabilities(network: Network): NetworkCapabilities? {
        TODO("Not yet implemented")
    }

    override fun getNetworkLinkProperties(network: Network): LinkProperties? {
        TODO("Not yet implemented")
    }
}
