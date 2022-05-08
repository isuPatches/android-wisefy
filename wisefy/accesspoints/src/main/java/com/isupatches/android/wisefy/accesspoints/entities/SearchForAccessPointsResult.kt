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
 * A set of classes and objects that are used to represent a result when searching for nearby access points.
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
sealed class SearchForAccessPointsResult {

    /**
     * A data representation for when there are no matching nearby access points.
     *
     * @see SearchForAccessPointsResult
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    object Empty : SearchForAccessPointsResult()

    /**
     * A data representation for when there are matching nearby access points.
     *
     * @property data This list of matching nearby access points
     *
     * @see AccessPointData
     * @see SearchForAccessPointResult
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    data class AccessPoints(val data: List<AccessPointData>) : SearchForAccessPointsResult()
}
