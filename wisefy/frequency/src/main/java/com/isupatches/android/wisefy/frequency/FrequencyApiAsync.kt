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
import android.Manifest.permission.ACCESS_NETWORK_STATE
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.frequency.callbacks.GetFrequencyCallbacks
import com.isupatches.android.wisefy.frequency.entities.GetFrequencyRequest

/**
 * A set of asynchronous APIs for getting the frequency of a network.
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
interface FrequencyApiAsync {

    /**
     * An asynchronous API to get the frequency of the current network.
     *
     * @param request The details of the request to get the frequency of a network
     * @param callbacks The callbacks for when the frequency is returned
     *
     * @see GetFrequencyRequest
     * @see GetFrequencyCallbacks
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_NETWORK_STATE])
    fun getFrequency(
        request: GetFrequencyRequest = GetFrequencyRequest.CurrentNetwork,
        callbacks: GetFrequencyCallbacks?
    )
}
