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
package com.isupatches.android.wisefy.networkconnection.os.apis

import android.Manifest.permission.CHANGE_NETWORK_STATE
import android.net.NetworkRequest
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission

/**
 * An Android 29 specific internal API for connecting to and disconnecting from a network through the Android OS.
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
@RequiresApi(Build.VERSION_CODES.Q)
internal interface Android29NetworkConnectionApi {

    /**
     * An Android 29 internal API to connect to a network through the Android OS.
     *
     * @param request The OS level network request to connect to a network
     * @param timeoutInMillis How long the request should wait to connect to the network
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresPermission(CHANGE_NETWORK_STATE)
    fun connectToNetwork(request: NetworkRequest, timeoutInMillis: Int)
}
