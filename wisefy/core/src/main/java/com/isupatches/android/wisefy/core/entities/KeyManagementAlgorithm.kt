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
 * A set of supported key management algorithms.
 *
 * @property stringValue The string value of the key management algorithm
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
enum class KeyManagementAlgorithm(val stringValue: String) {

    /**
     * A representation of the EAP key management algorithm.
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    PSK("PSK"),

    /**
     * A representation of the EAP key management algorithm.
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    EAP("EAP");

    companion object {
        /**
         * A list comprised of all of the representations of authentication algorithms.
         *
         * @see KeyManagementAlgorithm
         *
         * @author Patches Barrett
         * @since 12/2022, version 5.0.0
         */
        val ALL: List<KeyManagementAlgorithm> = values().asList()
    }
}
