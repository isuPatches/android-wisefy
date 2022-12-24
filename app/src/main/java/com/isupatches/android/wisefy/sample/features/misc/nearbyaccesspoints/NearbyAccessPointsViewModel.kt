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
package com.isupatches.android.wisefy.sample.features.misc.nearbyaccesspoints

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.ACCESS_WIFI_STATE
import androidx.annotation.RequiresPermission
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.isupatches.android.wisefy.WisefyApi
import com.isupatches.android.wisefy.accesspoints.entities.AccessPointData
import com.isupatches.android.wisefy.accesspoints.entities.GetAccessPointsQuery
import com.isupatches.android.wisefy.accesspoints.entities.GetAccessPointsResult
import com.isupatches.android.wisefy.accesspoints.entities.SecurityCapability
import com.isupatches.android.wisefy.core.exceptions.WisefyException
import com.isupatches.android.wisefy.ktx.isNetworkSavedAsync
import com.isupatches.android.wisefy.sample.scaffolding.BaseViewModel
import com.isupatches.android.wisefy.sample.scaffolding.BaseViewModelFactory
import com.isupatches.android.wisefy.savednetworks.entities.IsNetworkSavedQuery
import com.isupatches.android.wisefy.savednetworks.entities.IsNetworkSavedResult

internal abstract class NearbyAccessPointsViewModel : BaseViewModel() {
    abstract val uiState: State<NearbyAccessPointsUIState>

    abstract fun onDialogClosed()

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    abstract suspend fun getNearbyAccessPoints()

    abstract fun onGetNearbyAccessPointsPermissionError()
}

internal class DefaultNearbyAccessPointsViewModel(
    private val wisefy: WisefyApi
) : NearbyAccessPointsViewModel() {

    private val _uiState = mutableStateOf(
        NearbyAccessPointsUIState(
            loadingState = NearbyAccessPointsLoadingState(isLoading = false),
            dialogState = NearbyAccessPointsDialogState.None,
            accessPointUIData = emptyList()
        )
    )

    override val uiState: State<NearbyAccessPointsUIState>
        get() = _uiState

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override suspend fun getNearbyAccessPoints() {
        _uiState.value = _uiState.value.copy(
            loadingState = NearbyAccessPointsLoadingState(isLoading = true),
            dialogState = NearbyAccessPointsDialogState.None
        )
        try {
            when (val result = wisefy.getAccessPoints(GetAccessPointsQuery.All())) {
                is GetAccessPointsResult.Empty -> {
                    _uiState.value = NearbyAccessPointsUIState(
                        loadingState = NearbyAccessPointsLoadingState(isLoading = false),
                        dialogState = NearbyAccessPointsDialogState.None,
                        accessPointUIData = emptyList()
                    )
                }
                is GetAccessPointsResult.AccessPoints -> {
                    val accessPointUIData = result.value.map { accessPoint ->
                        val isSavedBySSIDResult = wisefy.isNetworkSavedAsync(
                            query = IsNetworkSavedQuery.SSID(accessPoint.ssid)
                        )
                        val isSavedBySSID = when (isSavedBySSIDResult) {
                            IsNetworkSavedResult.True -> true
                            IsNetworkSavedResult.False -> false
                        }
                        val isSavedByBSSIDResult = wisefy.isNetworkSavedAsync(
                            query = IsNetworkSavedQuery.BSSID(accessPoint.bssid)
                        )
                        val isSavedByBSSID = when (isSavedByBSSIDResult) {
                            IsNetworkSavedResult.True -> true
                            IsNetworkSavedResult.False -> false
                        }
                        val securityCapabilityMap = mutableMapOf<SecurityCapability, Boolean>()
                        SecurityCapability.ALL.forEach { securityCapability ->
                            securityCapabilityMap[securityCapability] =
                                accessPoint.containSecurityCapability(securityCapability)
                        }
                        AccessPointUIData(
                            accessPoint = accessPoint,
                            isSavedBySSID = isSavedBySSID,
                            isSavedByBSSID = isSavedByBSSID,
                            securityCapabilities = securityCapabilityMap
                        )
                    }
                    _uiState.value = NearbyAccessPointsUIState(
                        loadingState = NearbyAccessPointsLoadingState(isLoading = false),
                        dialogState = NearbyAccessPointsDialogState.None,
                        accessPointUIData = accessPointUIData
                    )
                }
            }
        } catch (ex: WisefyException) {
            _uiState.value = NearbyAccessPointsUIState(
                loadingState = NearbyAccessPointsLoadingState(isLoading = false),
                dialogState = NearbyAccessPointsDialogState.Failure.WisefyAsync(exception = ex),
                accessPointUIData = emptyList()
            )
        }
    }

    override fun onGetNearbyAccessPointsPermissionError() {
        _uiState.value = _uiState.value.copy(
            loadingState = NearbyAccessPointsLoadingState(isLoading = false),
            dialogState = NearbyAccessPointsDialogState.GetNearbyAccessPoints.PermissionsError
        )
    }

    override fun onDialogClosed() {
        _uiState.value = _uiState.value.copy(
            loadingState = NearbyAccessPointsLoadingState(isLoading = false),
            dialogState = NearbyAccessPointsDialogState.None
        )
    }
}

internal class NearbyAccessPointsViewModelFactory(
    private val wisefy: WisefyApi
) : BaseViewModelFactory<NearbyAccessPointsViewModel>(
    supportedClass = NearbyAccessPointsViewModel::class,
    vmProvider = { DefaultNearbyAccessPointsViewModel(wisefy) }
)

internal data class AccessPointUIData(
    val accessPoint: AccessPointData,
    val isSavedBySSID: Boolean,
    val isSavedByBSSID: Boolean,
    val securityCapabilities: Map<SecurityCapability, Boolean>
)
