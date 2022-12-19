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
package com.isupatches.android.wisefy.removenetwork.os.adapters

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.ACCESS_WIFI_STATE
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.core.logging.WisefyLogger
import com.isupatches.android.wisefy.removenetwork.RemoveNetworkApi
import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkRequest
import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkResult
import com.isupatches.android.wisefy.removenetwork.os.apis.DefaultRemoveNetworkApi
import com.isupatches.android.wisefy.removenetwork.os.impls.DefaultRemoveNetworkApiImpl

/**
 * A default adapter for removing a network.
 *
 * @param wifiManager The WifiManager instance to use
 * @param api The OS level API instance to use
 *
 * @see DefaultRemoveNetworkApi
 * @see DefaultRemoveNetworkApiImpl
 * @see RemoveNetworkApi
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
internal class DefaultRemoveNetworkAdapter(
    logger: WisefyLogger,
    wifiManager: WifiManager,
    private val api: DefaultRemoveNetworkApi = DefaultRemoveNetworkApiImpl(wifiManager, logger)
) : RemoveNetworkApi {

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun removeNetwork(request: RemoveNetworkRequest): RemoveNetworkResult {
        val result = when (request) {
            is RemoveNetworkRequest.SSID -> api.removeNetworkBySSID(request.regex)
            is RemoveNetworkRequest.BSSID -> api.removeNetworkByBSSID(request.regex)
        }
        return if (result) {
            RemoveNetworkResult.Success.True
        } else {
            RemoveNetworkResult.Failure.False
        }
    }
}
