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
import com.isupatches.android.wisefy.core.assertions.WisefyAssertions
import com.isupatches.android.wisefy.core.constants.AssertionMessages
import com.isupatches.android.wisefy.signal.SignalApi
import com.isupatches.android.wisefy.signal.entities.CalculateSignalLevelRequest
import com.isupatches.android.wisefy.signal.entities.CalculateSignalLevelResult
import com.isupatches.android.wisefy.signal.entities.CompareSignalLevelRequest
import com.isupatches.android.wisefy.signal.entities.CompareSignalLevelResult
import com.isupatches.android.wisefy.signal.os.apis.Android30SignalApi
import com.isupatches.android.wisefy.signal.os.converters.toCompareSignalLevelResult
import com.isupatches.android.wisefy.signal.os.impls.Android30SignalApiImpl

/**
 * An Android 30 specific adapter for functions related to the signal strength of networks.
 *
 * @param wifiManager The WifiManager instance to use
 * @param assertions The [WisefyAssertions] instance to use
 * @param api The OS level API instance to use
 *
 * @see Android30SignalApi
 * @see Android30SignalApiImpl
 * @see SignalApi
 * @see WisefyAssertions
 *
 * @author Patches Klinefelter
 * @since 07/2022, version 5.0.0
 */
@RequiresApi(Build.VERSION_CODES.R)
internal class Android30SignalAdapter(
    wifiManager: WifiManager,
    private val assertions: WisefyAssertions,
    private val api: Android30SignalApi = Android30SignalApiImpl(wifiManager)
) : SignalApi {

    override fun calculateSignalLevel(request: CalculateSignalLevelRequest): CalculateSignalLevelResult {
        return when (request) {
            is CalculateSignalLevelRequest.Android30AndAbove -> {
                val result = api.calculateBars(request.rssiLevel)
                CalculateSignalLevelResult.Success(value = result)
            }
            is CalculateSignalLevelRequest.BelowAndroid30 -> {
                val message = AssertionMessages.Signal.INCORRECT_CALCULATE_BARS_USED_ANDROID_30
                assertions.fail(message = message)
                CalculateSignalLevelResult.Failure.Assertion(message)
            }
        }
    }

    override fun compareSignalLevel(request: CompareSignalLevelRequest): CompareSignalLevelResult {
        val comparisonResult = api.compareSignalLevel(request.rssi1, request.rssi2)
        return comparisonResult.toCompareSignalLevelResult()
    }
}
