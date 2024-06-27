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
package com.isupatches.android.wisefy.networkconnection.entities

/**
 * A set of classes and objects that are representations of a result when disconnecting from the current network.
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
sealed class DisconnectFromCurrentNetworkResult {

    /**
     * A set of classes objects that are representations of a success when disconnecting from the current network.
     *
     * @see DisconnectFromCurrentNetworkResult
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    sealed class Success : DisconnectFromCurrentNetworkResult() {

        /**
         * A data representation for when there is a success disconnecting from the current network.
         *
         * @see Success
         *
         * @author Patches Barrett
         * @since 12/2022, version 5.0.0
         */
        object True : Success()
    }

    /**
     * A set of classes and objects that are representations of a failure when disconnecting from the current network.
     *
     * @see DisconnectFromCurrentNetworkResult
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    sealed class Failure : DisconnectFromCurrentNetworkResult() {

        /**
         * A data representation for when there is a failure disconnecting from the current network.
         *
         * @see Failure
         *
         * @author Patches Barrett
         * @since 12/2022, version 5.0.0
         */
        object False : Failure()

        /**
         * A representation of a failure disconnecting from the current network due to hitting an unexpected path
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
