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
package com.isupatches.android.wisefy.signal.os.adapters

import android.net.wifi.WifiManager
import android.os.Build
import androidx.annotation.RequiresApi
import com.isupatches.android.wisefy.core.entities.ErrorMessages
import com.isupatches.android.wisefy.signal.SignalApi
import com.isupatches.android.wisefy.signal.entities.CalculateBarsRequest
import com.isupatches.android.wisefy.signal.entities.CalculateBarsResult
import com.isupatches.android.wisefy.signal.entities.CompareSignalLevelRequest
import com.isupatches.android.wisefy.signal.entities.CompareSignalLevelResult
import com.isupatches.android.wisefy.signal.os.apis.Android30SignalApi
import com.isupatches.android.wisefy.signal.os.impls.Android30SignalApiImpl

@RequiresApi(Build.VERSION_CODES.R)
internal class Android30SignalAdapter(
    wifiManager: WifiManager,
    private val api: Android30SignalApi = Android30SignalApiImpl(wifiManager)
) : SignalApi {

    override fun calculateBars(request: CalculateBarsRequest): CalculateBarsResult {
        return when (request) {
            is CalculateBarsRequest.Android30AndAbove -> {
                val result = api.calculateBars(request.rssiLevel)
                CalculateBarsResult.Success(value = result)
            }
            is CalculateBarsRequest.BelowAndroid30 -> {
                CalculateBarsResult.WrongSDKLevel(message = ErrorMessages.Signal.CALCULATE_BARS_ANDROID_30)
            }
        }
    }

    override fun compareSignalLevel(request: CompareSignalLevelRequest): CompareSignalLevelResult {
        val result = api.compareSignalLevel(request.rssi1, request.rssi2)
        return CompareSignalLevelResult(value = result)
    }
}
