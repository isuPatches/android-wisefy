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
package com.isupatches.android.wisefy.savednetworks.entities

/**
 * A set of classes and objects that are used to represent a result when searching for saved networks.
 *
 * @author Patches Klinefelter
 * @since 07/2022, version 5.0.0
 */
sealed class SearchForSavedNetworksResult {

    /**
     * A set of classes and objects that are used to represent a success while searching for saved networks on the
     * device.
     *
     * @author Patches Klinefelter
     * @since 07/2022, version 5.0.0
     */
    sealed class Success : SearchForSavedNetworksResult() {

        /**
         * A data representation for when there are no matching saved networks.
         *
         * @see Success
         *
         * @author Patches Klinefelter
         * @since 07/2022, version 5.0.0
         */
        object Empty : Success()

        /**
         * A data representation for when there are matching saved networks.
         *
         * @property data The list of matching saved networks
         *
         * @see SavedNetworkData
         * @see Success
         *
         * @author Patches Klinefelter
         * @since 07/2022, version 5.0.0
         */
        data class SavedNetworks(val data: List<SavedNetworkData>) : Success()
    }

    /**
     * A set of classes and objects that are used to represent a failure while searching for saved networks on the
     * device.
     *
     * @author Patches Klinefelter
     * @since 07/2022, version 5.0.0
     */
    sealed class Failure : SearchForSavedNetworksResult() {

        /**
         * A data representation of a failure searching for saved networks due to hitting an unexpected path causing an
         * assertion.
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
