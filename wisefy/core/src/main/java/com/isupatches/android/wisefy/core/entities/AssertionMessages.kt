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
package com.isupatches.android.wisefy.core.entities

/**
 * A singleton that houses all of the assertion messages present throughout the app.
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
object AssertionMessages {

    /**
     * A singleton that houses the assertion messages present for the add network features.
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    object AddNetwork {

        /**
         * A singleton that houses the assertion messages present for functions using ActivityResultLauncher.
         *
         * @author Patches Klinefelter
         * @since 03/2022
         */
        object ActivityResultLauncher {

            /**
             * A string value for the assertion message for using ActivityResultLauncher prior to Android 30.
             *
             * @author Patches Klinefelter
             * @since 03/2022
             */
            const val USED_PRE_ANDROID_30: String =
                "Adding a network using ActivityResultLauncher is not available until Android R"

            /**
             * A string value for the assertion message for not using ActivityResultLauncher on Android 30+.
             *
             * @author Patches Klinefelter
             * @since 03/2022
             */
            const val NOT_USED_ANDROID_30: String =
                "Adding a network should be done with an ActivityResultLauncher on Android R+"
        }

        /**
         * A singleton that houses the assertion messages present for functions adding a WPA3 network.
         *
         * @author Patches Klinefelter
         * @since 03/2022
         */
        object WPA3Network {

            /**
             * A string value for the assertion message for trying to add a WPA3 network before Android 29.
             *
             * @author Patches Klinefelter
             * @since 03/2022
             */
            const val PRE_ANDROID_29: String = "WPA3 networks are not supported until Android Q"
        }
    }

    /**
     * A singleton that houses the assertion messages present for the saved network features.
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    object SavedNetworks {

        /**
         * A string value for the assertion message for retrieving saved networks on Android Q.
         *
         * @author Patches Klinefelter
         * @since 03/2022
         */
        const val USED_ANDROID_29: String = "There is no known way to see or save a network on Android Q similar to " +
            "pre-Q or R+ behavior"
    }

    /**
     * A singleton that houses the assertion messages present for the signal features.
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    object Signal {

        /**
         * A string value for the assertion message for using calculateBars(rssiLevel: Int) before Android 30.
         *
         * @author Patches Klinefelter
         * @since 03/2022
         */
        const val CALCULATE_BARS_ANDROID_30: String = "calculateBars(rssiLevel: Int): Int should be used for Android R"

        /**
         * A string value for the assertion message for using calculateBars(rssiLevel: Int, targetNumberOfBars: Int)
         * on Android 30+.
         *
         * @author Patches Klinefelter
         * @since 03/2022
         */
        const val CALCULATE_BARS_LEGACY: String = "calculateBars(rssiLevel: Int): Int is not available until Android R"
    }

    /**
     * A singleton that houses the assertion messages present for the Wifi features.
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    object Wifi {

        /**
         * A string value for the assertion message for using disableWifi on Android Q+
         *
         * @author Patches Klinefelter
         * @since 03/2022
         */
        const val DISABLE_DEPRECATED_WITH_ANDROID_Q = DeprecationMessages.Wifi.DISABLE

        /**
         * A string value for the assertion message for using enableWifi on Android Q+
         *
         * @author Patches Klinefelter
         * @since 03/2022
         */
        const val ENABLE_DEPRECATED_WITH_ANDROID_Q = DeprecationMessages.Wifi.ENABLE
    }
}
