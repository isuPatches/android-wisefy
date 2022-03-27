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

import android.net.wifi.WifiInfo

/**
 * A set of classes that are used in requests to get a network's frequency.
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
sealed class GetFrequencyRequest {

    /**
     * A data representation of a request to get the current network's frequency.
     *
     * @see GetFrequencyRequest
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    object CurrentNetwork : GetFrequencyRequest()

    /**
     * A data representation of a request to get a given network's frequency.
     *
     * @property network The network to get the frequency of
     *
     * @see GetFrequencyRequest
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    data class ForNetwork(val network: WifiInfo) : GetFrequencyRequest()
}
