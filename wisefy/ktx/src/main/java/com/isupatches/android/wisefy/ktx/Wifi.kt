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
package com.isupatches.android.wisefy.ktx

import android.Manifest.permission.ACCESS_WIFI_STATE
import android.Manifest.permission.CHANGE_WIFI_STATE
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.WisefyApi
import com.isupatches.android.wisefy.core.exceptions.WisefyException
import com.isupatches.android.wisefy.wifi.callbacks.DisableWifiCallbacks
import com.isupatches.android.wisefy.wifi.callbacks.EnableWifiCallbacks
import com.isupatches.android.wisefy.wifi.callbacks.IsWifiEnabledCallbacks
import com.isupatches.android.wisefy.wifi.entities.DisableWifiRequest
import com.isupatches.android.wisefy.wifi.entities.DisableWifiResult
import com.isupatches.android.wisefy.wifi.entities.EnableWifiRequest
import com.isupatches.android.wisefy.wifi.entities.EnableWifiResult
import com.isupatches.android.wisefy.wifi.entities.IsWifiEnabledQuery
import com.isupatches.android.wisefy.wifi.entities.IsWifiEnabledResult
import kotlin.coroutines.suspendCoroutine

/**
 * A coroutine extension for disabling Wifi.
 *
 * *NOTES*
 *  - Locked by a mutex along with the async APIs to enable Wifi and check the device's current Wifi state
 *
 * @param request The details of the request to disable wifi.
 *
 * @see DisableWifiRequest
 * @see DisableWifiResult
 *
 * @return DisableWifiResult - The result of disabling Wifi
 *
 * @throws WisefyException
 *
 * @author Patches Klinefelter
 * @since 07/2022, version 5.0.0
 */
@Throws(WisefyException::class)
@RequiresPermission(CHANGE_WIFI_STATE)
suspend fun WisefyApi.disableWifiAsync(request: DisableWifiRequest): DisableWifiResult =
    suspendCoroutine { continuation ->
        disableWifi(
            request = request,
            callbacks = object : DisableWifiCallbacks {
                override fun onWifiDisabled(result: DisableWifiResult.Success) {
                    continuation.resumeWith(Result.success(result))
                }

                override fun onFailureDisablingWifi(result: DisableWifiResult.Failure) {
                    continuation.resumeWith(Result.success(result))
                }

                override fun onWisefyAsyncFailure(exception: WisefyException) {
                    continuation.resumeWith(Result.failure(exception))
                }
            }
        )
    }

/**
 * A coroutine extension for enabling Wifi.
 *
 * *NOTES*
 *  - Locked by a mutex along with the async APIs to disable Wifi and check the device's current Wifi state
 *
 * @param request The details of the request to enable wifi.
 *
 * @see EnableWifiRequest
 * @see EnableWifiResult
 *
 * @return EnableWifiResult - The result of enabling Wifi
 *
 * @throws WisefyException
 *
 * @author Patches Klinefelter
 * @since 07/2022, version 5.0.0
 */
@Throws(WisefyException::class)
@RequiresPermission(CHANGE_WIFI_STATE)
suspend fun WisefyApi.enableWifiAsync(request: EnableWifiRequest): EnableWifiResult =
    suspendCoroutine { continuation ->
        enableWifi(
            request = request,
            callbacks = object : EnableWifiCallbacks {
                override fun onWifiEnabled(result: EnableWifiResult.Success) {
                    continuation.resumeWith(Result.success(result))
                }

                override fun onFailureEnablingWifi(result: EnableWifiResult.Failure) {
                    continuation.resumeWith(Result.success(result))
                }

                override fun onWisefyAsyncFailure(exception: WisefyException) {
                    continuation.resumeWith(Result.failure(exception))
                }
            }
        )
    }

/**
 * A coroutine extension for checking the current state of Wifi.
 *
 * *NOTES*
 *  - Locked by a mutex along with the async APIs to enable and disable Wifi
 *
 * @param query The details of the query to check the current state of wifi.
 *
 * @see IsWifiEnabledQuery
 * @see IsWifiEnabledResult
 *
 * @return IsWifiEnabledResult - The result of checking if Wifi is enabled
 *
 * @throws WisefyException
 *
 * @author Patches Klinefelter
 * @since 07/2022, version 5.0.0
 */
@Throws(WisefyException::class)
@RequiresPermission(ACCESS_WIFI_STATE)
suspend fun WisefyApi.isWifiEnabledAsync(query: IsWifiEnabledQuery = IsWifiEnabledQuery()): IsWifiEnabledResult =
    suspendCoroutine { continuation ->
        isWifiEnabled(
            query = query,
            callbacks = object : IsWifiEnabledCallbacks {
                override fun onWifiIsEnabled() {
                    continuation.resumeWith(Result.success(IsWifiEnabledResult.True))
                }

                override fun onWifiIsDisabled() {
                    continuation.resumeWith(Result.success(IsWifiEnabledResult.False))
                }

                override fun onWisefyAsyncFailure(exception: WisefyException) {
                    continuation.resumeWith(Result.failure(exception))
                }
            }
        )
    }
