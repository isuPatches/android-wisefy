/*
 * Copyright 2018 Patches Klinefelter
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
package com.isupatches.wisefy.callbacks

/**
 * Callbacks for retrieving the frequency of a network.
 *
 * @see [com.isupatches.wisefy.WiseFy.getFrequency]
 *
 * @author Patches
 * @since 3.0
 */
interface GetFrequencyCallbacks : BaseCallback {

    /**
     * Called when there is an issue retrieving the frequency of a network.
     *
     * i.e. Unable to retrieve current network or no current network
     *
     * @author Patches
     * @since 3.0
     */
    fun failureGettingFrequency()

    /**
     * Called when WiseFy has successfully retrieved the frequency of a network.
     *
     * @author Patches
     * @since 3.0
     */
    fun retrievedFrequency(frequency: Int)
}
