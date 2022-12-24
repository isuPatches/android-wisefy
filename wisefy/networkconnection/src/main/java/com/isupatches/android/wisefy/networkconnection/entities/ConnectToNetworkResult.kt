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
 * A set of classes that are data representations of a result when connecting to or disconnecting from a network.
 *
 * @author Patches Barrett
 * @since 03/2022
 */
@Deprecated("")
sealed class ConnectToNetworkResult {

    /**
     * A set of classes that are data representations of a success when connecting to a network.
     *
     * @see ConnectToNetworkResult
     *
     * @author Patches Barrett
     * @since 03/2022
     */
    @Deprecated("")
    sealed class Success : ConnectToNetworkResult() {

        /**
         * A data representation for when there is a success connecting to or disconnecting from a network.
         *
         * @see Success
         *
         * @author Patches Barrett
         * @since 03/2022
         */
        @Deprecated("")
        object True : Success()

        /**
         * A data representation for when there is a request sent to connecting to a network.
         *
         * @see Success
         *
         * @author Patches Barrett
         * @since 03/2022
         */
        @Deprecated("")
        object ConnectionRequestSent : Success()
    }

    /**
     * A set of classes that are data representations of a failure when connecting to a network.
     *
     * @see ConnectToNetworkResult
     *
     * @author Patches Barrett
     * @since 03/2022
     */
    @Deprecated("")
    sealed class Failure : ConnectToNetworkResult() {

        /**
         * A data representation for when there is a failure connecting to or disconnecting from a network.
         *
         * @see Failure
         *
         * @author Patches Barrett
         * @since 03/2022
         */
        @Deprecated("")
        object False : Failure()

        /**
         * A data representation for when there is no network found to connect to or disconnect from.
         *
         * @see Failure
         *
         * @author Patches Barrett
         * @since 03/2022
         */
        @Deprecated("")
        object NetworkNotFound : Failure()

        @Deprecated("")
        data class Assertion(val message: String) : Failure()
    }
}
