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
package com.isupatches.android.wisefy.accesspoints.entities

/**
 * A set of supported security capabilities.
 *
 * @property stringValue The string value of the security capability
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
enum class SecurityCapability(val stringValue: String) {
    /**
     * A representation of the EAP security capability.
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    EAP("EAP"),

    /**
     * A representation of the PSK security capability.
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    PSK("PSK"),

    /**
     * A representation of the WEP security capability.
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    WEP("WEP"),

    /**
     * A representation of the WPA security capability.
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    WPA("WPA"),

    /**
     * A representation of the WPA2 security capability.
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    WPA2("WPA2"),

    /**
     * A representation of the WPA3 security capability.
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    WPA3("WPA3");

    companion object {
        /**
         * A list comprised of all of the representations of security capabilities.
         *
         * @see SecurityCapability
         *
         * @author Patches Barrett
         * @since 12/2022, version 5.0.0
         */
        val ALL: List<SecurityCapability> = values().asList()
    }
}
