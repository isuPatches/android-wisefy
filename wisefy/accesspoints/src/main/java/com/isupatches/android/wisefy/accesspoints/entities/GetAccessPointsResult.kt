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
package com.isupatches.android.wisefy.accesspoints.entities

/**
 * A set of classes and objects that represent a result when getting nearby access points.
 *
 * @author Patches Klinefelter
 * @since 11/2022, version 5.0.0
 */
sealed class GetAccessPointsResult {

    /**
     * A representation for when there are no nearby access points.
     *
     * @see GetAccessPointsResult
     *
     * @author Patches Klinefelter
     * @since 11/2022, version 5.0.0
     */
    object Empty : GetAccessPointsResult()

    /**
     * A representation for when there is one or more nearby access points.
     *
     * @property value This list of nearby access points
     *
     * @see AccessPointData
     * @see GetAccessPointsResult
     *
     * @author Patches Klinefelter
     * @since 11/2022, version 5.0.0
     */
    data class AccessPoints(val value: List<AccessPointData>) : GetAccessPointsResult()
}