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
package com.isupatches.android.wisefy.savednetworks.entities

/**
 * A set of classes and objects that are used to represent a result while seeing if a network is saved on the device.
 *
 * @author Patches Barrett
 * @since 07/2022, version 5.0.0
 */
sealed class IsNetworkSavedResult {

    /**
     * A data representation of the network being saved on the device.
     *
     * @see IsNetworkSavedResult
     *
     * @author Patches Barrett
     * @since 07/2022, version 5.0.0
     */
    object True : IsNetworkSavedResult()

    /**
     * A data representation of the network not being saved on the device.
     *
     * @see IsNetworkSavedResult
     *
     * @author Patches Barrett
     * @since 07/2022, version 5.0.0
     */
    object False : IsNetworkSavedResult()

//    /**
//     * A data representation of a failure checking if a network is saved on a device due to hitting an unexpected path
//     * causing an assertion.
//     *
//     * *NOTE* This is for developer specific feedback and should NEVER actually be hit unless there is a bug.
//     *
//     * @property message A text description describing the assertion error hit
//     *
//     * @see IsNetworkSavedResult
//     *
//     * @author Patches Barrett
//     * @since 07/2022, version 5.0.0
//     */
//    data class Assertion(val message: String) : IsNetworkSavedResult()
}
