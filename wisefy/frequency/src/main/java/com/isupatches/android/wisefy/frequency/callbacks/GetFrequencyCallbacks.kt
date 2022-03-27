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
package com.isupatches.android.wisefy.frequency.callbacks

import com.isupatches.android.wisefy.frequency.entities.FrequencyData
import com.isupatches.android.wisefy.shared.base.BaseWisefyCallbacks

/**
 * A set of callbacks for getting the frequency of a network.
 *
 * @see BaseWisefyCallbacks
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
interface GetFrequencyCallbacks : BaseWisefyCallbacks {

    /**
     * A callback triggered when there is a failure getting the frequency of a network.
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    fun onFailureRetrievingFrequency()

    /**
     * A callback triggered when there is a success getting the frequency of a network.
     *
     * @param frequency The frequency data for the network
     *
     * @see FrequencyData
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    fun onFrequencyRetrieved(frequency: FrequencyData)
}
