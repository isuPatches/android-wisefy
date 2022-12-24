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
package com.isupatches.android.wisefy.sample.features.misc.nearbyaccesspoints

import com.isupatches.android.wisefy.core.exceptions.WisefyException

internal data class NearbyAccessPointsUIState(
    val loadingState: NearbyAccessPointsLoadingState,
    val dialogState: NearbyAccessPointsDialogState,
    val accessPointUIData: List<AccessPointUIData>
)

internal data class NearbyAccessPointsLoadingState(
    val isLoading: Boolean = false
)

internal sealed class NearbyAccessPointsDialogState {
    object None : NearbyAccessPointsDialogState()

    sealed class Failure : NearbyAccessPointsDialogState() {
        data class WisefyAsync(val exception: WisefyException) : Failure()
    }

    sealed class GetNearbyAccessPoints : NearbyAccessPointsDialogState() {
        object PermissionsError : GetNearbyAccessPoints()
    }
}
