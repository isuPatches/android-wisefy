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
 * A set of classes and objects that are representations of a result while changing the device's current network.
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
sealed class ChangeNetworkResult {

    /**
     * A set of classes and objects that are representations of a success while changing the device's current network.
     *
     * @see ChangeNetworkResult
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    sealed class Success : ChangeNetworkResult() {

        /**
         * A representation of a success changing the device's current network by opening the internet connectivity
         * panel.
         *
         * @see Success
         *
         * @author Patches Barrett
         * @since 12/2022, version 5.0.0
         */
        object InternetConnectivityPanelOpened : Success()
    }

    /**
     * A set of classes and objects that are representations of a failure while changing the device's current network.
     *
     * @see ChangeNetworkResult
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    sealed class Failure : ChangeNetworkResult() {

        /**
         * A representation of a failure changing the device's current network due to hitting an unexpected path
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
