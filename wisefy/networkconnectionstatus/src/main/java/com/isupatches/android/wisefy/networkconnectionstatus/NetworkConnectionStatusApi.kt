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
package com.isupatches.android.wisefy.networkconnectionstatus

import com.isupatches.android.wisefy.networkconnectionstatus.entities.IsDeviceConnectedResult
import com.isupatches.android.wisefy.networkconnectionstatus.entities.IsDeviceRoamingResult
import com.isupatches.android.wisefy.networkconnectionstatus.entities.IsNetworkConnectedToSSIDRequest

interface NetworkConnectionStatusApi {
    fun attachNetworkWatcher()
    fun detachNetworkWatcher()

    fun isDeviceConnectedToMobileNetwork(): IsDeviceConnectedResult

    fun isDeviceConnectedToMobileOrWifiNetwork(): IsDeviceConnectedResult

    fun isDeviceConnectedToSSID(request: IsNetworkConnectedToSSIDRequest): IsDeviceConnectedResult

    fun isDeviceConnectedToWifiNetwork(): IsDeviceConnectedResult

    fun isDeviceRoaming(): IsDeviceRoamingResult
}
