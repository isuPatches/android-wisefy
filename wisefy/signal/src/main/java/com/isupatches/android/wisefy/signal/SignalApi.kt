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
package com.isupatches.android.wisefy.signal

import com.isupatches.android.wisefy.signal.entities.CalculateBarsRequest
import com.isupatches.android.wisefy.signal.entities.CalculateBarsResult
import com.isupatches.android.wisefy.signal.entities.CompareSignalLevelRequest
import com.isupatches.android.wisefy.signal.entities.CompareSignalLevelResult

/**
 * A set of synchronous APIs for signal strength functionality.
 *
 * @author Patches Klinefelter
 * @since 07/2022, version 5.0.0
 */
interface SignalApi {

    /**
     * A synchronous API to calculate the number of signal strength bars for a network.
     *
     * @param request The details of the request to calculate the number of signal strength bars for a network
     *
     * @see CalculateBarsRequest
     * @see CalculateBarsResult
     *
     * @return CalculateBarsResult - The result of calculating the signal strength bars for a network
     *
     * @author Patches Klinefelter
     * @since 07/2022, version 5.0.0
     */
    fun calculateBars(request: CalculateBarsRequest): CalculateBarsResult

    /**
     * A synchronous API to compare the signal strength of two networks.
     *
     * @param request The details of the request to compare the signal strength of two networks
     *
     * @see CompareSignalLevelRequest
     * @see CompareSignalLevelResult
     *
     * @return CompareSignalLevelResult - The result of comparing the RSSI levels of two networks
     *
     * @author Patches Klinefelter
     * @since 07/2022, version 5.0.0
     */
    fun compareSignalLevel(request: CompareSignalLevelRequest): CompareSignalLevelResult
}
