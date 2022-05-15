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

import com.isupatches.android.wisefy.core.entities.ErrorMessages
import com.isupatches.android.wisefy.signal.SignalApi
import com.isupatches.android.wisefy.signal.entities.CalculateBarsRequest
import com.isupatches.android.wisefy.signal.entities.CalculateBarsResult
import com.isupatches.android.wisefy.signal.entities.CompareSignalLevelRequest
import com.isupatches.android.wisefy.signal.entities.CompareSignalLevelResult
import com.isupatches.android.wisefy.signal.os.apis.DefaultSignalApi
import com.isupatches.android.wisefy.signal.os.impls.DefaultSignalApiImpl

/**
 * A default adapter for functions related to the signal strength of networks.
 *
 * @param api The OS level API instance to use
 *
 * @see DefaultSignalApi
 * @see DefaultSignalApiImpl
 * @see SignalApi
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
internal class DefaultSignalAdapter(
    private val api: DefaultSignalApi = DefaultSignalApiImpl()
) : SignalApi {

    override fun calculateBars(request: CalculateBarsRequest): CalculateBarsResult {
        return when (request) {
            is CalculateBarsRequest.BelowAndroid30 -> {
                val result = api.calculateBars(request.rssiLevel, request.targetNumberOfBars)
                CalculateBarsResult.Success(value = result)
            }
            is CalculateBarsRequest.Android30AndAbove -> {
                CalculateBarsResult.WrongSDKLevel(message = ErrorMessages.Signal.CALCULATE_BARS_LEGACY)
            }
        }
    }

    override fun compareSignalLevel(request: CompareSignalLevelRequest): CompareSignalLevelResult {
        val result = api.compareSignalLevel(request.rssi1, request.rssi2)
        return CompareSignalLevelResult(value = result)
    }
}
