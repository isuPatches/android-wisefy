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
package com.isupatches.android.wisefy.signal.entities

/**
 * A set of classes and objects that are used to represent a result while calculating the number of signal bars
 * based on an RSSI level.
 *
 * @author Patches Klinefelter
 * @since 07/2022, version 5.0.0
 */
sealed class CalculateBarsResult {

    /**
     * A data representation of the number of bars that represents the RSSI level of a network.
     *
     * @property value The return value for the number of bars representing the RSSI level of the network
     *
     * @see CalculateBarsResult
     *
     * @author Patches Klinefelter
     * @since 07/2022, version 5.0.0
     */
    data class Success(val value: Int) : CalculateBarsResult()

    /**
     * A set of classes and objects that are used a representation of a failure while calculating the number of bars to
     * represent the RSSI level of a network.
     *
     * @see CalculateBarsResult
     *
     * @author Patches Klinefelter
     * @since 07/2022, version 5.0.0
     */
    sealed class Failure : CalculateBarsResult() {

        /**
         * A data representation of a failure while calculating the number of bars to represent the RSSI level of
         * a network due to hitting an unexpected path causing an assertion.
         *
         * *NOTE* This is for developer specific feedback and should NEVER actually be hit unless there is a bug.
         *
         * @property message A text description describing the assertion error hit
         *
         * @see Failure
         *
         * @author Patches Klinefelter
         * @since 07/2022, version 5.0.0
         */
        data class Assertion(val message: String) : Failure()
    }
}
