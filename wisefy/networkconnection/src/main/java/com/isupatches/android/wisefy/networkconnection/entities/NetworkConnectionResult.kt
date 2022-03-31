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
 * A set of classes that are data representations of a result when connecting to or disconnecting from a network.
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
sealed class NetworkConnectionResult {

    /**
     * A set of classes that are data representations of a boolean result when connecting to or disconnecting
     * from a network.
     *
     * @see NetworkConnectionResult
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    sealed class Boolean : NetworkConnectionResult() {

        /**
         * A data representation for when there is a success connecting to or disconnecting from a network.
         *
         * @see NetworkConnectionResult.Boolean
         *
         * @author Patches Klinefelter
         * @since 03/2022
         */
        object True : NetworkConnectionResult.Boolean()

        /**
         * A data representation for when there is a failure connecting to or disconnecting from a network.
         *
         * @see NetworkConnectionResult.Boolean
         *
         * @author Patches Klinefelter
         * @since 03/2022
         */
        object False : NetworkConnectionResult.Boolean()
    }

    /**
     * A data representation for when there is a request sent to connecting to a network.
     *
     * @see NetworkConnectionResult
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    object ConnectionRequestSent : NetworkConnectionResult()

    /**
     * A data representation for when there is no network found to connect to or disconnect from.
     *
     * @see NetworkConnectionResult
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    object NetworkNotFound : NetworkConnectionResult()

    /**
     * A data representation for when there is a request sent to disconnect from a network.
     *
     * @see NetworkConnectionResult
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    object DisconnectRequestSent : NetworkConnectionResult()
}
