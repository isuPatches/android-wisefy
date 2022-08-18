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
package com.isupatches.android.wisefy.wifi.entities

/**
 * A set of classes and objects that are data representations of a result when enabling Wifi.
 *
 * @author Patches Klinefelter
 * @since 07/2022, version 5.0.0
 */
sealed class EnableWifiResult {

    /**
     * A data representation for when there is a success enabling Wifi.
     *
     * @see EnableWifiResult
     *
     * @author Patches Klinefelter
     * @since 07/2022, version 5.0.0
     */
    object Success : EnableWifiResult()

    /**
     * A set of classes and objects that are data representations of a failure when enabling Wifi.
     *
     * @see EnableWifiResult
     *
     * @author Patches Klinefelter
     * @since 07/2022, version 5.0.0
     */
    sealed class Failure : EnableWifiResult() {

        /**
         * A data representation for when there is a failure enabling Wifi.
         *
         * @see Failure
         *
         * @author Patches Klinefelter
         * @since 07/2022, version 5.0.0
         */
        object UnableToEnable : Failure()

        /**
         * A data representation of a failure to enable Wifi due to hitting an unexpected path causing an assertion.
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
