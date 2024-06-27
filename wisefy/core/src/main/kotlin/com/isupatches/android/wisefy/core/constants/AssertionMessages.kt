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
package com.isupatches.android.wisefy.core.constants

/**
 * A singleton that houses all of the assertion messages present throughout the app.
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
object AssertionMessages {

    /**
     * A singleton that houses the assertion messages present for the add network features.
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    object AddNetwork {

        /**
         * A singleton that houses the assertion messages present for functions adding a WPA3 network.
         *
         * @author Patches Barrett
         * @since 12/2022, version 5.0.0
         */
        object WPA3Network {

            /**
             * A string value for the assertion message for trying to add a WPA3 network before Android Q / SDK 29.
             *
             * @author Patches Barrett
             * @since 12/2022, version 5.0.0
             */
            const val USED_PRE_ANDROID_29: String = "WPA3 networks are not supported until Android Q / SDK 29"
        }
    }

    /**
     * A singleton that houses the assertion messages present for Android Q.
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    object AndroidQ {

        /**
         * A string value for the assertion message for trying to work with saved networks on Android Q / SDK 29.
         *
         * @author Patches Barrett
         * @since 12/2022, version 5.0.0
         */
        const val SAVED_NETWORK_FUNCTIONALITY_UNAVAILABLE_ANDROID_Q: String =
            "Saved network functionality is not supported on Android Q / SDK 29"
    }

    /**
     * A singleton that houses the assertion messages present for the network connection features.
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    object NetworkConnection {

        /**
         * A singleton that houses the assertion messages present for the changeNetwork API.
         *
         * @author Patches Barrett
         * @since 12/2022, version 5.0.0
         */
        object ChangeNetwork {

            /**
             * A string value for the assertion message for calling the changeNetwork API before Android Q.
             *
             * @author Patches Barrett
             * @since 12/2022, version 5.0.0
             */
            const val USED_BEFORE_ANDROID_Q: String = "Change network API used before Android Q / SDK 29"
        }

        /**
         * A singleton that houses the assertion messages present for the connectToNetwork API.
         *
         * @author Patches Barrett
         * @since 12/2022, version 5.0.0
         */
        object ConnectToNetwork {

            /**
             * A string value for the assertion message for calling the connectToNetwork API on Android Q / SDK 29
             * or higher.
             *
             * @author Patches Barrett
             * @since 12/2022, version 5.0.0
             */
            const val USED_ANDROID_Q_OR_HIGHER: String = "Connect to network API used on Android Q / SDK 29 or higher"
        }

        /**
         * A singleton that houses the assertion messages present for the disconnectFromCurrentNetwork API.
         *
         * @author Patches Barrett
         * @since 12/2022, version 5.0.0
         */
        object DisconnectFromCurrentNetwork {

            /**
             * A string value for the assertion message for calling the disconnectFromCurrentNetwork API on Android Q /
             * SDK 29 or higher.
             *
             * @author Patches Barrett
             * @since 12/2022, version 5.0.0
             */
            const val USED_ANDROID_Q_OR_HIGHER: String = "Connect to network API used on Android Q / SDK 29 or higher"
        }
    }

    /**
     * A singleton that houses the assertion messages present for the signal features.
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    object Signal {

        /**
         * A string value for the assertion message for using calculateBars(rssiLevel: Int) before Android 30.
         *
         * @author Patches Barrett
         * @since 12/2022, version 5.0.0
         */
        const val INCORRECT_CALCULATE_BARS_USED_ANDROID_R_OR_HIGHER: String =
            "calculateBars(rssiLevel: Int): Int should be used for Android R / SDK 30 or higher"

        /**
         * A string value for the assertion message for using calculateBars(rssiLevel: Int, targetNumberOfBars: Int)
         * on Android 30+.
         *
         * @author Patches Barrett
         * @since 12/2022, version 5.0.0
         */
        const val INCORRECT_CALCULATE_BARS_USED_PRE_ANDROID_R: String =
            "calculateBars(rssiLevel: Int): Int is not available until Android R / SDK 30"
    }

    /**
     * A singleton that houses the assertion messages present for the wifi features.
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    object Wifi {

        /**
         * A string value for trying to open the wifi settings screen to enable or disable wifi on pre-Android Q /
         * SDK 29 devices.
         *
         * @author Patches Barrett
         * @since 12/2022, version 5.0.0
         */
        const val ANDROID_29_REQUEST_USED_ON_PRE_ANDROID_29: String = "Before Android Q / SDK 29, apps can enable and" +
            "disable wifi without having to redirect to the wifi settings screen"

        /**
         * A string value for trying to enable or disable wifi without opening the wifi setting screen on Android Q /
         * SDK 29 devices.
         *
         * @author Patches Barrett
         * @since 12/2022, version 5.0.0
         */
        const val DEFAULT_REQUEST_USED_ANDROID_29_OR_HIGHER: String = "Starting with Android Q / SDK 29, app cannot" +
            "enable or disable wifi so the user must be directed the wifi settings screen"
    }
}
