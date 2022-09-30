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
package com.isupatches.android.wisefy.networkconnection.entities

/**
 * A set of classes that are data representations of a result when disconnecting from the current network.
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
sealed class DisconnectFromCurrentNetworkResult {

    /**
     * A set of classes that are data representations of a success when disconnecting from the current network.
     *
     * @see DisconnectFromCurrentNetworkResult
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    sealed class Success : DisconnectFromCurrentNetworkResult() {

        /**
         * A data representation for when there is a success disconnecting from the current network.
         *
         * @see Success
         *
         * @author Patches Klinefelter
         * @since 03/2022
         */
        object True : Success()

        /**
         * A data representation for when there is a request sent to disconnect from the current network.
         *
         * @see Success
         *
         * @author Patches Klinefelter
         * @since 03/2022
         */
        object DisconnectRequestSent : Success()
    }

    /**
     * A set of classes that are data representations of a failure when disconnecting from the current network.
     *
     * @see DisconnectFromCurrentNetworkResult
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    sealed class Failure : DisconnectFromCurrentNetworkResult() {

        /**
         * A data representation for when there is a failure disconnecting from the current network.
         *
         * @see Failure
         *
         * @author Patches Klinefelter
         * @since 03/2022
         */
        object False : Failure()

        /**
         * A data representation for when there is no current network found to disconnect from.
         *
         * @see Failure
         *
         * @author Patches Klinefelter
         * @since 03/2022
         */
        object NetworkNotFound : Failure()

        data class Assertion(val message: String) : Failure()
    }
}
