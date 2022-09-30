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
package com.isupatches.android.ktx

import com.isupatches.android.wisefy.WisefyApi
import com.isupatches.android.wisefy.core.entities.DeprecationMessages
import com.isupatches.android.wisefy.core.exceptions.WisefyException
import com.isupatches.android.wisefy.wifi.callbacks.DisableWifiCallbacks
import com.isupatches.android.wisefy.wifi.callbacks.EnableWifiCallbacks
import com.isupatches.android.wisefy.wifi.callbacks.IsWifiEnabledCallbacks
import com.isupatches.android.wisefy.wifi.entities.DisableWifiRequest
import com.isupatches.android.wisefy.wifi.entities.DisableWifiResult
import com.isupatches.android.wisefy.wifi.entities.EnableWifiRequest
import com.isupatches.android.wisefy.wifi.entities.EnableWifiResult
import com.isupatches.android.wisefy.wifi.entities.IsWifiEnabledRequest
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
@Deprecated(DeprecationMessages.Wifi.DISABLE)
suspend fun WisefyApi.disableWifiAsync(request: DisableWifiRequest = DisableWifiRequest()): DisableWifiResult =
    suspendCoroutine { continuation ->
        disableWifi(
            request = request,
            callbacks = object : DisableWifiCallbacks {
                override fun onWifiDisabled() {
                    continuation.resumeWith(Result.success(DisableWifiResult.Success))
                }

                override fun onFailureDisablingWifi() {
                    continuation.resumeWith(Result.success(DisableWifiResult.Failure.UnableToDisable))
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
@Deprecated(DeprecationMessages.Wifi.ENABLE)
suspend fun WisefyApi.enableWifiAsync(request: EnableWifiRequest = EnableWifiRequest()): EnableWifiResult =
    suspendCoroutine { continuation ->
        enableWifi(
            request = request,
            callbacks = object : EnableWifiCallbacks {
                override fun onWifiEnabled() {
                    continuation.resumeWith(Result.success(EnableWifiResult.Success))
                }

                override fun onFailureEnablingWifi() {
                    continuation.resumeWith(Result.success(EnableWifiResult.Failure.UnableToEnable))
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
 * @param request The details of the request to check the current state of wifi.
 *
 * @see IsWifiEnabledRequest
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
suspend fun WisefyApi.isWifiEnabledAsync(request: IsWifiEnabledRequest = IsWifiEnabledRequest()): IsWifiEnabledResult =
    suspendCoroutine { continuation ->
        isWifiEnabled(
            request = request,
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
