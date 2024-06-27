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
 * A set of classes and objects that are used to represent a result while comparing the RSSI values of two networks.
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
sealed class CompareSignalLevelResult {

    /**
     * A set of classes and objects that are used to represent a success while comparing the RSSI values of two
     * networks.
     *
     * *Notes*
     * See https://developer.android.com/reference/android/net/wifi/WifiManager#compareSignalLevel(int,%20int)
     *
     * @property value The result of the comparison. This will be less than 0 if first RSSI value is weaker than the
     * second RSSI, 0 if the two have the same strength, and greater than zero if the first RSSI is stronger than the
     * second RSSI value.
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    sealed class Success(open val value: Int) : CompareSignalLevelResult() {

        /**
         * A representation of when the first network has an RSSI value that is weaker than the second network's RSSI.
         *
         * @property value The difference between the first and second networks RSSI value (expected to be negative)
         *
         * @see Success
         *
         * @author Patches Barrett
         * @since 12/2022, version 5.0.0
         */
        data class FirstRSSIValueIsWeaker(override val value: Int) : Success(value)

        /**
         * A representation of when the first network has an RSSI value that is equal to the second network's RSSI.
         *
         * @property value The difference between the first and second networks RSSI value (always 0)
         *
         * @see Success
         *
         * @author Patches Barrett
         * @since 12/2022, version 5.0.0
         */
        data class RSSIValuesAreEqual(override val value: Int) : Success(value)

        /**
         * A representation of when the first network has an RSSI value that is stronger than the second network's RSSI.
         *
         * @property value The difference between the first and second networks RSSI value (expected to be positive)
         *
         * @see Success
         *
         * @author Patches Barrett
         * @since 12/2022, version 5.0.0
         */
        data class FirstRSSIValueIsStronger(override val value: Int) : Success(value)
    }
}
