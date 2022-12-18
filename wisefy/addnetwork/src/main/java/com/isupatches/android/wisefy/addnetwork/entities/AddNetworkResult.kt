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
package com.isupatches.android.wisefy.addnetwork.entities

/**
 * A set of classes and objects that represent a result while attempting to add a network.
 *
 * @author Patches Klinefelter
 * @since 11/2022, version 5.0.0
 */
sealed class AddNetworkResult {

    /**
     * A set of classes and objects that denote a success while attempting to add a network.
     *
     * @see AddNetworkResult
     *
     * @author Patches Klinefelter
     * @since 11/2022, version 5.0.0
     */
    sealed class Success : AddNetworkResult() {

        /**
         * A representation of a success while attempting to add a network based on Android OS level returns.
         *
         * *NOTES*
         * This could be instances such as:
         *  - Returning the id of the new network for the case of legacy wifiManager.addNetwork()
         *  https://developer.android.com/reference/android/net/wifi/WifiManager#addNetwork(android.net.wifi.WifiConfiguration))
         *  - STATUS_NETWORK_SUGGESTIONS_SUCCESS for SDK 29
         *  https://developer.android.com/reference/android/net/wifi/WifiManager#STATUS_NETWORK_SUGGESTIONS_SUCCESS
         *
         * @property value The value of the result code from the Android OS
         *
         * @see Success
         *
         * @author Patches Klinefelter
         * @since 11/2022, version 5.0.0
         */
        data class ResultCode(val value: Int) : Success()
    }

    /**
     * A set of classes that denote a failure while attempting to add a network.
     *
     * @see AddNetworkResult
     *
     * @author Patches Klinefelter
     * @since 11/2022, version 5.0.0
     */
    sealed class Failure : AddNetworkResult() {

        object AndroidQ : Failure()

        /**
         * A representation of a failure to add a network based on Android OS level returns.
         *
         * *NOTE*
         * This could be instances such as:
         *  - Returning -1 for the case of legacy wifiManager.addNetwork()
         *  https://developer.android.com/reference/android/net/wifi/WifiManager#addNetwork(android.net.wifi.WifiConfiguration))
         *  - Any of the failure codes for wifiManager.addNetworkSuggestions() for SDK 29
         *  https://developer.android.com/reference/android/net/wifi/WifiManager#addNetworkSuggestions(java.util.List%3Candroid.net.wifi.WifiNetworkSuggestion%3E)
         *  - As of Android 11, in-place modifications are allowed so there will be no
         *  STATUS_NETWORK_SUGGESTIONS_ERROR_ADD_DUPLICATE return
         *
         * @property value The raw value of the result code from the Android OS
         *
         * @see Failure
         *
         * @author Patches Klinefelter
         * @since 11/2022, version 5.0.0
         */
        data class ResultCode(val value: Int) : Failure()

        /**
         * A representation of a failure to add a network due to hitting an unexpected path causing an assertion.
         *
         * *NOTE* This is for developer specific feedback and should NEVER actually be hit unless there is a bug.
         *
         * @property message A text description describing the assertion error hit
         *
         * @see Failure
         *
         * @author Patches Klinefelter
         * @since 11/2022, version 5.0.0
         */
        data class Assertion(val message: String) : Failure()
    }
}
