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
 * A singleton that houses all of the deprecation messages present throughout the app.
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
object DeprecationMessages {

    /**
     * A singleton that houses the deprecation messages present for the signal features.
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    object Signal {

        /**
         * A string value for the deprecation message for calculateBars(rssiLevel: Int, targetNumberOfBars: Int).
         *
         * @author Patches Barrett
         * @since 12/2022, version 5.0.0
         */
        const val CALCULATE_BARS: String = "Please replace calculateBars(rssiLevel: Int, targetNumberOfBars: Int) " +
            "with calculateBars(rssiLevel: Int)"
    }

    /**
     * A singleton that houses the deprecation messages present for the network connection features.
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    object NetworkConnection {
        const val DISCONNECT_FROM_CURRENT_NETWORK = "As of Android Q, applications canot "

        const val CONNECT_TO_NETWORK = ""
    }
}
