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
package com.isupatches.android.wisefy.networkconnection.os.adapters

import android.net.wifi.WifiManager
import com.isupatches.android.wisefy.shared.logging.WisefyLogger
import com.isupatches.android.wisefy.networkconnection.NetworkConnectionApi
import com.isupatches.android.wisefy.networkconnection.entities.NetworkConnectionRequest
import com.isupatches.android.wisefy.networkconnection.entities.NetworkConnectionResult
import com.isupatches.android.wisefy.networkconnection.os.apis.DefaultNetworkConnectionApi
import com.isupatches.android.wisefy.networkconnection.os.impls.DefaultNetworkConnectionApiImpl
import com.isupatches.android.wisefy.networkconnectionstatus.NetworkConnectionStatusDelegate
import com.isupatches.android.wisefy.savednetworks.SavedNetworkDelegate

internal class DefaultNetworkConnectionAdapter(
    wifiManager: WifiManager,
    networkConnectionStatusUtil: NetworkConnectionStatusDelegate,
    savedNetworkUtil: SavedNetworkDelegate,
    logger: WisefyLogger?,
    private val impl: DefaultNetworkConnectionApi = DefaultNetworkConnectionApiImpl(
        wifiManager,
        networkConnectionStatusUtil,
        savedNetworkUtil,
        logger
    )
) : NetworkConnectionApi {

    override fun connectToNetwork(request: NetworkConnectionRequest): NetworkConnectionResult {
        return impl.connectToNetwork(request)
    }

    override fun disconnectFromCurrentNetwork(): NetworkConnectionResult {
        return impl.disconnectFromCurrentNetwork()
    }
}
