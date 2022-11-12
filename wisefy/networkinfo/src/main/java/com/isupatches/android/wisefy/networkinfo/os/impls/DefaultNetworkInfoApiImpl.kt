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
package com.isupatches.android.wisefy.networkinfo.os.impls

import android.Manifest.permission.ACCESS_NETWORK_STATE
import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.core.logging.WisefyLogger
import com.isupatches.android.wisefy.networkinfo.os.apis.DefaultNetworkInfoApi

/**
 * A default implementation for getting information about a network, the device's current network, and the device's IP.
 *
 * @param connectivityManager The ConnectivityManager instance to use
 *
 * @see DefaultNetworkInfoApi
 * @see WisefyLogger
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
internal class DefaultNetworkInfoApiImpl(
    private val connectivityManager: ConnectivityManager
) : DefaultNetworkInfoApi {

    override fun getCurrentNetwork(): Network? {
        return connectivityManager.activeNetwork
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun getNetworkCapabilities(network: Network): NetworkCapabilities? {
        return connectivityManager.getNetworkCapabilities(network)
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun getLinkProperties(network: Network): LinkProperties? {
        return connectivityManager.getLinkProperties(network)
    }
}
