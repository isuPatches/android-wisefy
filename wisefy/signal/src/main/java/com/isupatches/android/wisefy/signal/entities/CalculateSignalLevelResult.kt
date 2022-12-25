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
package com.isupatches.android.wisefy.signal.entities

/**
 * A set of classes and objects that are used to represent a result while calculating the number of signal bars
 * based on an RSSI level.
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
sealed class CalculateSignalLevelResult {

    /**
     * A representation of a success while attempting to calculate the sign level of a network.
     *
     * @property value The number of signal bars for the network based on its RSSI level
     *
     * @see CalculateSignalLevelResult
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    data class Success(val value: Int) : CalculateSignalLevelResult()

    /**
     * A set of classes that denote a failure while attempting to calculate the sign level of a network.
     *
     * @see CalculateSignalLevelResult
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    sealed class Failure : CalculateSignalLevelResult() {

        /**
         * A representation of a failure to calculate the sign level of a network due to hitting an unexpected path
         * causing an assertion.
         *
         * *NOTE* This is for developer specific feedback and should NEVER actually be hit unless there is a bug.
         *
         * @property message A text description describing the assertion error hit
         *
         * @see Failure
         *
         * @author Patches Barrett
         * @since 12/2022, version 5.0.0
         */
        data class Assertion(val message: String) : Failure()
    }
}
