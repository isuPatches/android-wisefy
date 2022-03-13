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

import android.net.NetworkRequest
import android.os.Build
import androidx.annotation.RequiresApi

internal interface Android29NetworkConnectionApi {
    @RequiresApi(Build.VERSION_CODES.Q)
    fun connectToNetwork(request: NetworkRequest, timeoutInMillis: Int)

    fun disconnectFromCurrentNetwork()
}
