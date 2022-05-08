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
package com.isupatches.android.wisefy.core.util

import android.Manifest.permission.ACCESS_NETWORK_STATE
import android.net.ConnectivityManager
import android.net.wifi.WifiInfo
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission

/**
 * A function that will retrieve a network from ConnectivityManager.
 *
 * @return WifiInfo or null - The network from ConnectivityManager or null if cannot be retrieved
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
@RequiresApi(Build.VERSION_CODES.Q)
@RequiresPermission(ACCESS_NETWORK_STATE)
fun ConnectivityManager.getNetwork(): WifiInfo? {
    return getNetworkCapabilities(activeNetwork)?.transportInfo as? WifiInfo
}
