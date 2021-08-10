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
package com.isupatches.android.wisefy.networkconnection

import com.isupatches.android.wisefy.callbacks.ConnectToNetworkCallbacks
import com.isupatches.android.wisefy.callbacks.DisconnectFromCurrentNetworkCallbacks
import com.isupatches.android.wisefy.networkconnection.entities.NetworkConnectionResult

interface NetworkConnectionApi {
    fun connectToNetwork(ssidToConnectTo: String, timeoutInMillis: Int): NetworkConnectionResult
    fun disconnectFromCurrentNetwork(): NetworkConnectionResult
}

interface NetworkConnectionApiAsync {
    fun connectToNetwork(ssidToConnectTo: String, timeoutInMillis: Int, callbacks: ConnectToNetworkCallbacks?)
    fun disconnectFromCurrentNetwork(callbacks: DisconnectFromCurrentNetworkCallbacks?)
}
