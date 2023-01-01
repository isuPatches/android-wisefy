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
package com.isupatches.android.wisefy.core.entities

/**
 * A set of supported authentication algorithms.
 *
 * @property stringValue The string value of the authentication algorithm
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
enum class AuthenticationAlgorithm(val stringValue: String) {

    /**
     * A representation of the WEP authentication algorithm.
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    WEP("WEP"),

    /**
     * A representation of the WPA authentication algorithm.
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    WPA("WPA"),

    /**
     * A representation of the WPA2 authentication algorithm.
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    WPA2("WPA2"),

    /**
     * A representation of the WPA-EAP enterprise authentication algorithm.
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    WPA_EAP("WPA-EAP"),

    /**
     * A representation of the IEEE8021X enterprise authentication algorithm.
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    IEEE8021X("IEEE8021X");

    companion object {
        /**
         * A list comprised of all of the representations of authentication algorithms.
         *
         * @see AuthenticationAlgorithm
         *
         * @author Patches Barrett
         * @since 12/2022, version 5.0.0
         */
        val ALL: List<AuthenticationAlgorithm> = values().asList()
    }
}
