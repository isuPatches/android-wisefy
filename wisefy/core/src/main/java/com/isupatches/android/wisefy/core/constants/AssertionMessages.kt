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
package com.isupatches.android.wisefy.core.constants

/**
 * A singleton that houses all of the assertion messages present throughout the app.
 *
 * @author Patches Klinefelter
 * @since 11/2022, version 5.0.0
 */
object AssertionMessages {

    /**
     * A singleton that houses the assertion messages present for the add network features.
     *
     * @author Patches Klinefelter
     * @since 11/2022, version 5.0.0
     */
    object AddNetwork {

        /**
         * A singleton that houses the assertion messages present for functions adding a WPA3 network.
         *
         * @author Patches Klinefelter
         * @since 11/2022, version 5.0.0
         */
        object WPA3Network {

            /**
             * A string value for the assertion message for trying to add a WPA3 network before Android 29.
             *
             * @author Patches Klinefelter
             * @since 11/2022, version 5.0.0
             */
            const val USED_PRE_ANDROID_29: String = "WPA3 networks are not supported until Android Q"
        }
    }

    object AndroidQ {

        const val SAVED_NETWORK_FUNCTIONALITY_UNAVAILABLE_ANDROID_29: String =
            "Saved network functionality is not supported on Android Q"
    }

    /**
     * A singleton that houses the assertion messages present for the network connection features.
     *
     * @author Patches Klinefelter
     * @since 11/2022, version 5.0.0
     */
    object NetworkConnection {

        object ChangeNetwork {
            const val USED_BEFORE_ANDROID_Q = "Change network API used before Android 29"
        }

        object ConnectToNetwork {
            const val USED_ANDROID_Q_OR_HIGHER = ""
        }

        object DisconnectFromCurrentNetwork {
            const val USED_ANDROID_Q_OR_HIGHER = ""
        }
    }

    /**
     * A singleton that houses the assertion messages present for the signal features.
     *
     * @author Patches Klinefelter
     * @since 11/2022, version 5.0.0
     */
    object Signal {

        /**
         * A string value for the assertion message for using calculateBars(rssiLevel: Int) before Android 30.
         *
         * @author Patches Klinefelter
         * @since 11/2022, version 5.0.0
         */
        const val INCORRECT_CALCULATE_BARS_USED_ANDROID_30: String =
            "calculateBars(rssiLevel: Int): Int should be used for Android R"

        /**
         * A string value for the assertion message for using calculateBars(rssiLevel: Int, targetNumberOfBars: Int)
         * on Android 30+.
         *
         * @author Patches Klinefelter
         * @since 11/2022, version 5.0.0
         */
        const val INCORRECT_CALCULATE_BARS_USED_PRE_ANDROID_30: String =
            "calculateBars(rssiLevel: Int): Int is not available until Android R"
    }

    object Wifi {
        const val ANDROID_29_REQUEST_USED_ON_PRE_ANDROID_29 = ""
        const val DEFAULT_REQUEST_USED_ANDROID_29_OR_HIGHER = ""
    }
}
