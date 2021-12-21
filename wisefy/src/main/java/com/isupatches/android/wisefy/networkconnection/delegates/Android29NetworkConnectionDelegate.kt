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
package com.isupatches.android.wisefy.networkconnection.delegates

import android.net.ConnectivityManager
import android.os.Build
import androidx.annotation.RequiresApi
import com.isupatches.android.wisefy.logging.WisefyLogger
import com.isupatches.android.wisefy.networkconnection.NetworkConnectionApi
import com.isupatches.android.wisefy.networkconnection.entities.NetworkConnectionRequest
import com.isupatches.android.wisefy.networkconnection.entities.NetworkConnectionResult

internal class Android29NetworkConnectionDelegate(
    connectivityManager: ConnectivityManager,
    logger: WisefyLogger?,
    private val impl: Android29NetworkConnectionApi = Android29NetworkConnectionApiImpl(connectivityManager, logger)
) : NetworkConnectionApi {

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun connectToNetwork(request: NetworkConnectionRequest): NetworkConnectionResult {
        return impl.connectToNetwork(request)
    }

    override fun disconnectFromCurrentNetwork(): NetworkConnectionResult {
        return impl.disconnectFromCurrentNetwork()
    }
}
