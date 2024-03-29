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
package com.isupatches.android.wisefy.removenetwork.os.adapters

import android.Manifest.permission.CHANGE_WIFI_STATE
import android.net.wifi.WifiManager
import android.net.wifi.WifiManager.STATUS_NETWORK_SUGGESTIONS_SUCCESS
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.core.logging.WisefyLogger
import com.isupatches.android.wisefy.removenetwork.RemoveNetworkApi
import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkRequest
import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkResult
import com.isupatches.android.wisefy.removenetwork.os.apis.Android30RemoveNetworkApi
import com.isupatches.android.wisefy.removenetwork.os.impls.Android30RemoveNetworkApiImpl

/**
 * An Android 30 or higher adapter for removing a network.
 *
 * @param logger The [WisefyLogger] instance to use
 * @property wifiManager The WifiManager instance to use
 * @property api The OS level API instance to use
 *
 * @see Android30RemoveNetworkApi
 * @see Android30RemoveNetworkApiImpl
 * @see RemoveNetworkApi
 * @see WisefyLogger
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
@RequiresApi(Build.VERSION_CODES.R)
internal class Android30RemoveNetworkAdapter(
    logger: WisefyLogger,
    private val wifiManager: WifiManager,
    private val api: Android30RemoveNetworkApi = Android30RemoveNetworkApiImpl(wifiManager, logger)
) : RemoveNetworkApi {

    @RequiresPermission(CHANGE_WIFI_STATE)
    override fun removeNetwork(request: RemoveNetworkRequest): RemoveNetworkResult {
        val result = when (request) {
            is RemoveNetworkRequest.SSID -> api.removeNetworkBySSID(request.ssid)
            is RemoveNetworkRequest.BSSID -> api.removeNetworkByBSSID(request.bssid)
        }
        return if (result == STATUS_NETWORK_SUGGESTIONS_SUCCESS) {
            RemoveNetworkResult.Success.ResultCode(result)
        } else {
            RemoveNetworkResult.Failure.ResultCode(result)
        }
    }
}
