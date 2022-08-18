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

import com.isupatches.android.wisefy.accesspoints.entities.AccessPointData

/**
 * A data class that is used to represent a request to check if a network contains a given security capability.
 *
 * @property network The network to check the capabilities of
 * @property securityCapability The security capability to check for
 *
 * @see AccessPointData
 * @see SecurityCapability
 *
 * @author Patches Klinefelter
 * @since 07/2022, version 5.0.0
 */
data class ContainsSecurityCapabilityRequest(
    val network: AccessPointData,
    val securityCapability: SecurityCapability
)
