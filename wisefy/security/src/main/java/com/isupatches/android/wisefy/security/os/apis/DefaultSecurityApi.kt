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
package com.isupatches.android.wisefy.security.os.apis

import android.net.wifi.ScanResult
import com.isupatches.android.wisefy.security.entities.SecurityCapability

/**
 * A default internal API for checking the security details of a network through the Android OS.
 *
 * @author Patches Klinefelter
 * @since 07/2022, version 5.0.0
 */
internal interface DefaultSecurityApi {

    /**
     * A default API to check if a network has a given security capability.
     *
     * @param network The network to check if it has a given security capability
     *
     * @return Boolean - Whether or not the network has the given security capability.  True if secure, otherwise false.
     *
     * @author Patches Klinefelter
     * @since 07/2022, version 5.0.0
     */
    fun doesNetworkContainSecurityCapability(
        network: ScanResult,
        securityCapability: SecurityCapability
    ): Boolean

    /**
     * A default API to check if a network is secure.
     *
     * @param network The network to check if it is secure
     *
     * @return Boolean - Whether or not the network is secure.  True if secure, otherwise false.
     *
     * @author Patches Klinefelter
     * @since 07/2022, version 5.0.0
     */
    fun isNetworkSecure(network: ScanResult): Boolean
}
