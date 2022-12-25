/*
 * Copyright 2022 Patches Barrett
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

import com.isupatches.android.wisefy.core.assertions.WisefyAssertions
import com.isupatches.android.wisefy.core.constants.AssertionMessages
import com.isupatches.android.wisefy.core.logging.WisefyLogger
import com.isupatches.android.wisefy.signal.SignalApi
import com.isupatches.android.wisefy.signal.entities.CalculateSignalLevelRequest
import com.isupatches.android.wisefy.signal.entities.CalculateSignalLevelResult
import com.isupatches.android.wisefy.signal.entities.CompareSignalLevelRequest
import com.isupatches.android.wisefy.signal.entities.CompareSignalLevelResult
import com.isupatches.android.wisefy.signal.os.apis.DefaultSignalApi
import com.isupatches.android.wisefy.signal.os.converters.toCompareSignalLevelResult
import com.isupatches.android.wisefy.signal.os.impls.DefaultSignalApiImpl

/**
 * A default adapter for functions related to the signal strength of networks.
 *
 * @param logger The [WisefyLogger] instance to use
 * @property assertions The [WisefyAssertions] instance to use
 * @property api The OS level API instance to use
 *
 * @see DefaultSignalApi
 * @see DefaultSignalApiImpl
 * @see SignalApi
 * @see WisefyAssertions
 * @see WisefyLogger
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
internal class DefaultSignalAdapter(
    logger: WisefyLogger,
    private val assertions: WisefyAssertions,
    private val api: DefaultSignalApi = DefaultSignalApiImpl(logger)
) : SignalApi {

    override fun calculateSignalLevel(request: CalculateSignalLevelRequest): CalculateSignalLevelResult {
        return when (request) {
            is CalculateSignalLevelRequest.BelowAndroid30 -> {
                @Suppress("Deprecation")
                val result = api.calculateSignalLevel(request.rssiLevel, request.numLevels)
                CalculateSignalLevelResult.Success(value = result)
            }
            is CalculateSignalLevelRequest.Android30AndAbove -> {
                val message = AssertionMessages.Signal.INCORRECT_CALCULATE_BARS_USED_PRE_ANDROID_R
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
