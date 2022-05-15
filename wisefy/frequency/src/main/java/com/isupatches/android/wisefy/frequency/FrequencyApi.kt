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
package com.isupatches.android.wisefy.frequency

import android.Manifest.permission.ACCESS_FINE_LOCATION
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.frequency.entities.GetFrequencyRequest
import com.isupatches.android.wisefy.frequency.entities.GetFrequencyResult
import com.isupatches.android.wisefy.frequency.entities.IsNetwork5gHzRequest
import com.isupatches.android.wisefy.frequency.entities.IsNetwork5gHzResult

/**
 * A constant for the minimum frequency for a 5G network.
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
const val MIN_FREQUENCY_5GHZ: Int = 4900

/**
 * A constant for the maximum frequency for a 5G network.
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
const val MAX_FREQUENCY_5GHZ: Int = 5900

/**
 * A set of synchronous APIs for getting the frequency of a network.
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
interface FrequencyApi {

    /**
     * A synchronous API to get the frequency of the current network.
     *
     * @param request The details of the request to get the frequency of a network
     *
     * @see GetFrequencyRequest
     * @see GetFrequencyResult
     *
     * @return GetFrequencyResult - The result of getting the frequency of a network
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun getFrequency(request: GetFrequencyRequest = GetFrequencyRequest.CurrentNetwork): GetFrequencyResult

    /**
     * A synchronous API to check if the frequency of the current network is 5G.
     *
     * @param request The details of the request to check if a network is 5G
     *
     * @see IsNetwork5gHzRequest
     * @see IsNetwork5gHzResult
     *
     * @return IsNetwork5gHzResult - The result of whether the network is 5G or not
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun isNetwork5gHz(request: IsNetwork5gHzRequest = IsNetwork5gHzRequest.CurrentNetwork): IsNetwork5gHzResult
}
