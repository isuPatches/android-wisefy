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

import android.Manifest.permission.ACCESS_NETWORK_STATE
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.shared.logging.WisefyLogger
import com.isupatches.android.wisefy.networkinfo.NetworkInfoApi
import com.isupatches.android.wisefy.networkinfo.entities.CurrentNetworkData
import com.isupatches.android.wisefy.networkinfo.entities.CurrentNetworkInfoData
import com.isupatches.android.wisefy.networkinfo.entities.GetCurrentNetworkInfoRequest
import com.isupatches.android.wisefy.networkinfo.entities.IPData

internal class LegacyNetworkInfoDelegate(
    wifiManager: WifiManager,
    connectivityManager: ConnectivityManager,
    logger: WisefyLogger?,
    private val impl: LegacyNetworkInfoApi = LegacyNetworkInfoApiImpl(
        wifiManager,
        connectivityManager,
        logger
    )
) : NetworkInfoApi {

    override fun getCurrentNetwork(): CurrentNetworkData? {
        return impl.getCurrentNetwork()
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun getCurrentNetworkInfo(request: GetCurrentNetworkInfoRequest): CurrentNetworkInfoData? {
        return impl.getCurrentNetworkInfo(request)
    }

    override fun getIP(): IPData? {
        return impl.getIP()
    }
}
