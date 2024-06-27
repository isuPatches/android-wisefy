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
package com.isupatches.android.wisefy.removenetwork.entities

/**
 * A set of classes and objects that are used to represent a result while attempting to remove a network.
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
sealed class RemoveNetworkResult {

    /**
     * A set of classes that are used to denote a success while attempting to remove a network.
     *
     * @see RemoveNetworkResult
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    sealed class Success : RemoveNetworkResult() {

        /**
         * A data representation of a success removing a network based on Android OS level returns.
         *
         * *NOTE*
         * - Returns for this are the same as `removeNetworkSuggestion` found here:
         *  https://developer.android.com/reference/android/net/wifi/WifiManager#removeNetworkSuggestions(java.util.List%3Candroid.net.wifi.WifiNetworkSuggestion%3E,%20int)
         *
         * @property value The raw value of the result code from the Android OS
         *
         * @see Success
         *
         * @author Patches Barrett
         * @since 12/2022, version 5.0.0
         */
        data class ResultCode(val value: Int) : Success()

        /**
         * A data representation of a success removing a network on older Android OS levels.
         *
         * *NOTE*
         * - Returns for this are defined the same as `removeNetwork`:
         *  https://developer.android.com/reference/android/net/wifi/WifiManager#removeNetwork(int)
         *
         * @see Success
         *
         * @author Patches Barrett
         * @since 12/2022, version 5.0.0
         */
        object True : Success()
    }

    /**
     * A set of classes that are used to denote a failure while attempting to remove a network.
     *
     * @see RemoveNetworkResult
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    sealed class Failure : RemoveNetworkResult() {

        /**
         * A data representation of a failure removing a network on older Android OS levels.
         *
         * *NOTE*
         * - Returns for this are defined the same as `removeNetwork`:
         *  https://developer.android.com/reference/android/net/wifi/WifiManager#removeNetwork(int)
         *
         * @see Failure
         *
         * @author Patches Barrett
         * @since 12/2022, version 5.0.0
         */
        object False : Failure()

        /**
         * A data representation of a failure removing a network based on Android OS level returns.
         *
         * *NOTE*
         * - Returns for this are the same as `removeNetworkSuggestion` found here:
         *  https://developer.android.com/reference/android/net/wifi/WifiManager#removeNetworkSuggestions(java.util.List%3Candroid.net.wifi.WifiNetworkSuggestion%3E,%20int)
         *
         * @property value The raw value of the result code from the Android OS
         *
         * @see Failure
         *
         * @author Patches Barrett
         * @since 12/2022, version 5.0.0
         */
        data class ResultCode(val value: Int) : Failure()

        /**
         * A representation of a failure removing a network due to hitting an unexpected path causing an assertion.
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
