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
 * A set of classes that are used to denote a failure or success while attempting to add a network.
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
sealed class AddNetworkResult {

    /**
     * A set of classes that are used to denote a success while attempting to add a network.
     *
     * @see AddNetworkResult
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    sealed class Success : AddNetworkResult() {

        /**
         * A data representation of a success while attempting to add a network based on Android OS level returns.
         *
         * *NOTE* This could be instances such as:
         *  - Returning the id of the new network for the case of legacy wifiManager.addNetwork()
         *  https://developer.android.com/reference/android/net/wifi/WifiManager#addNetwork(android.net.wifi.WifiConfiguration))
         *  - STATUS_NETWORK_SUGGESTIONS_SUCCESS for SDK 30
         *  https://developer.android.com/reference/android/net/wifi/WifiManager#STATUS_NETWORK_SUGGESTIONS_SUCCESS
         *
         * @property value The raw value of the result code from the Android OS
         *
         * @see AddNetworkResult.Success
         *
         * @author Patches Klinefelter
         * @since 03/2022
         */
        data class ResultCode(
            val value: Int
        ) : AddNetworkResult.Success()

        /**
         * A data representation of a success while launching a network suggestion intent in Android 30.
         *
         * *NOTE* Only applicable to Android 30 and higher
         *
         * @see AddNetworkResult.Success
         *
         * @author Patches Klinefelter
         * @since 03/2022
         */
        object IntentLaunched : AddNetworkResult.Success()
    }

    /**
     * A set of classes that are used to denote a failure while attempting to add a network.
     *
     * @see AddNetworkResult
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    sealed class Failure : AddNetworkResult() {

        /**
         * A data representation of a failure to add a network based on Android OS level returns.
         *
         * *NOTE* This could be instances such as:
         *  - Returning -1 for the case of legacy wifiManager.addNetwork()
         *  https://developer.android.com/reference/android/net/wifi/WifiManager#addNetwork(android.net.wifi.WifiConfiguration))
         *  - Any of the failure codes for wifiManager.addNetworkSuggestions() for SDK 29
         *  https://developer.android.com/reference/android/net/wifi/WifiManager#addNetworkSuggestions(java.util.List%3Candroid.net.wifi.WifiNetworkSuggestion%3E)
         *
         * @property value The raw value of the result code from the Android OS
         *
         * @see AddNetworkResult.Failure
         *
         * @author Patches Klinefelter
         * @since 03/2022
         */
        data class ResultCode(
            val value: Int
        ) : AddNetworkResult.Failure()

        /**
         * A data representation of a failure to add a network due to a function being called for the wrong SDK level.
         *
         * *NOTE* This is a developer specific use case to catch instances within Wisefy that proxy to the wrong
         *  SDK level.  This should NEVER actually be hit and if it is, it is a bug.
         *
         * @property message A text description describing the SDK level and error hit
         *
         * @see AddNetworkResult.Failure
         *
         * @author Patches Klinefelter
         * @since 03/2022
         */
        data class WrongSDKLevel(
            val message: String
        ) : AddNetworkResult.Failure()
    }
}
