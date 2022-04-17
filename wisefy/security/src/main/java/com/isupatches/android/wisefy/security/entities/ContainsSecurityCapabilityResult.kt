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
 * A set of classes and objects that are used to represent a result while checking
 * if a network contains a given security capability.
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
sealed class ContainsSecurityCapabilityResult {

    /**
     * A data representation of when a network contains a given security capability.
     *
     * @see ContainsSecurityCapabilityResult
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    object True : ContainsSecurityCapabilityResult()

    /**
     * A data representation of when a network does NOT contain a given security capability.
     *
     * @see ContainsSecurityCapabilityResult
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    object False : ContainsSecurityCapabilityResult()
}
