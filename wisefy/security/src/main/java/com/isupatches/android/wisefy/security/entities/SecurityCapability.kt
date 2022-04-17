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
package com.isupatches.android.wisefy.security.entities

/**
 * A set of supported security capabilities.
 *
 * @property stringValue The string value of the security capability
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
enum class SecurityCapability(val stringValue: String) {
    /**
     * A representation of EAP security capability.
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    EAP("EAP"),

    /**
     * A representation of PSK security capability.
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    PSK("PSK"),

    /**
     * A representation of WEP security capability.
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    WEP("WEP"),

    /**
     * A representation of WPA security capability.
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    WPA("WPA"),

    /**
     * A representation of WPA2 security capability.
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    WPA2("WPA2"),

    /**
     * A representation of WPA3 security capability.
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    WPA3("WPA3");

    companion object {
        /**
         * A list of all representations of security capabilities.
         *
         * @author Patches Klinefelter
         * @since 03/2022
         */
        val ALL: List<SecurityCapability> = values().asList()
    }
}
