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
package com.isupatches.android.wisefy.frequency.entities

/**
 * A set of classes that are data representations of a result when getting the frequency of a network.
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
sealed class GetFrequencyResult {

    /**
     * A data representation for when there is no network to retrieve the frequency.
     *
     * @see GetFrequencyResult
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    object Empty : GetFrequencyResult()

    /**
     * A data representation for when there is a network to retrieve the frequency.
     *
     * @property data The frequency value for the network
     *
     * @see GetFrequencyResult
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    data class WithFrequency(val data: FrequencyData) : GetFrequencyResult()
}
