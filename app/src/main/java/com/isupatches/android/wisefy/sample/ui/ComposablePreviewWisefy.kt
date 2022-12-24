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
package com.isupatches.android.wisefy.sample.ui

import android.os.Build
import androidx.annotation.RequiresApi
import com.isupatches.android.wisefy.WisefyApi
import com.isupatches.android.wisefy.accesspoints.callbacks.GetAccessPointsCallbacks
import com.isupatches.android.wisefy.accesspoints.entities.GetAccessPointsQuery
import com.isupatches.android.wisefy.accesspoints.entities.GetAccessPointsResult
import com.isupatches.android.wisefy.addnetwork.callbacks.AddNetworkCallbacks
import com.isupatches.android.wisefy.addnetwork.entities.AddNetworkRequest
import com.isupatches.android.wisefy.addnetwork.entities.AddNetworkResult
import com.isupatches.android.wisefy.networkconnection.callbacks.ChangeNetworkCallbacks
import com.isupatches.android.wisefy.networkconnection.callbacks.ConnectToNetworkCallbacks
import com.isupatches.android.wisefy.networkconnection.callbacks.DisconnectFromCurrentNetworkCallbacks
import com.isupatches.android.wisefy.networkconnection.entities.ChangeNetworkRequest
import com.isupatches.android.wisefy.networkconnection.entities.ChangeNetworkResult
import com.isupatches.android.wisefy.networkconnection.entities.ConnectToNetworkRequest
import com.isupatches.android.wisefy.networkconnection.entities.ConnectToNetworkResult
import com.isupatches.android.wisefy.networkconnection.entities.DisconnectFromCurrentNetworkRequest
import com.isupatches.android.wisefy.networkconnection.entities.DisconnectFromCurrentNetworkResult
import com.isupatches.android.wisefy.networkinfo.callbacks.GetCurrentNetworkCallbacks
import com.isupatches.android.wisefy.networkinfo.callbacks.GetNetworkConnectionStatusCallbacks
import com.isupatches.android.wisefy.networkinfo.entities.GetCurrentNetworkQuery
import com.isupatches.android.wisefy.networkinfo.entities.GetCurrentNetworkResult
import com.isupatches.android.wisefy.networkinfo.entities.GetNetworkConnectionStatusQuery
import com.isupatches.android.wisefy.networkinfo.entities.GetNetworkConnectionStatusResult
import com.isupatches.android.wisefy.networkinfo.entities.NetworkConnectionStatusData
import com.isupatches.android.wisefy.networkinfo.entities.NetworkData
import com.isupatches.android.wisefy.removenetwork.callbacks.RemoveNetworkCallbacks
import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkRequest
import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkResult
import com.isupatches.android.wisefy.savednetworks.callbacks.GetSavedNetworksCallbacks
import com.isupatches.android.wisefy.savednetworks.callbacks.IsNetworkSavedCallbacks
import com.isupatches.android.wisefy.savednetworks.entities.GetSavedNetworksQuery
import com.isupatches.android.wisefy.savednetworks.entities.GetSavedNetworksResult
import com.isupatches.android.wisefy.savednetworks.entities.IsNetworkSavedQuery
import com.isupatches.android.wisefy.savednetworks.entities.IsNetworkSavedResult
import com.isupatches.android.wisefy.signal.entities.CalculateSignalLevelRequest
import com.isupatches.android.wisefy.signal.entities.CalculateSignalLevelResult
import com.isupatches.android.wisefy.signal.entities.CompareSignalLevelRequest
import com.isupatches.android.wisefy.signal.entities.CompareSignalLevelResult
import com.isupatches.android.wisefy.wifi.callbacks.DisableWifiCallbacks
import com.isupatches.android.wisefy.wifi.callbacks.EnableWifiCallbacks
import com.isupatches.android.wisefy.wifi.callbacks.IsWifiEnabledCallbacks
import com.isupatches.android.wisefy.wifi.entities.DisableWifiRequest
import com.isupatches.android.wisefy.wifi.entities.DisableWifiResult
import com.isupatches.android.wisefy.wifi.entities.EnableWifiRequest
import com.isupatches.android.wisefy.wifi.entities.EnableWifiResult
import com.isupatches.android.wisefy.wifi.entities.IsWifiEnabledQuery
import com.isupatches.android.wisefy.wifi.entities.IsWifiEnabledResult

internal class ComposablePreviewWisefy : WisefyApi {

    override fun init() {
        // No-op
    }

    override fun dump() {
        // No-op
    }

    override fun addNetwork(request: AddNetworkRequest): AddNetworkResult {
        return AddNetworkResult.Success.ResultCode(1)
    }

    override fun addNetwork(request: AddNetworkRequest, callbacks: AddNetworkCallbacks?) {
        callbacks?.onSuccessAddingNetwork(AddNetworkResult.Success.ResultCode(1))
    }

    override fun calculateSignalLevel(request: CalculateSignalLevelRequest): CalculateSignalLevelResult {
        return CalculateSignalLevelResult.Success(value = 0)
    }

    override fun compareSignalLevel(request: CompareSignalLevelRequest): CompareSignalLevelResult {
        return CompareSignalLevelResult.Success.RSSIValuesAreEqual(value = 0)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun changeNetwork(request: ChangeNetworkRequest): ChangeNetworkResult {
        return ChangeNetworkResult.Success.InternetConnectionPanelOpened
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun changeNetwork(request: ChangeNetworkRequest, callbacks: ChangeNetworkCallbacks?) {
        callbacks?.onSuccessChangingNetworks(result = ChangeNetworkResult.Success.InternetConnectionPanelOpened)
    }

    override fun connectToNetwork(request: ConnectToNetworkRequest): ConnectToNetworkResult {
        return ConnectToNetworkResult.Success.True
    }

    override fun connectToNetwork(request: ConnectToNetworkRequest, callbacks: ConnectToNetworkCallbacks?) {
        callbacks?.onSuccessConnectingToNetwork(result = ConnectToNetworkResult.Success.True)
    }

    override fun disableWifi(request: DisableWifiRequest): DisableWifiResult {
        return DisableWifiResult.Success.Disabled
    }

    override fun disableWifi(request: DisableWifiRequest, callbacks: DisableWifiCallbacks?) {
        callbacks?.onSuccessDisablingWifi(result = DisableWifiResult.Success.Disabled)
    }

    override fun disconnectFromCurrentNetwork(
        request: DisconnectFromCurrentNetworkRequest
    ): DisconnectFromCurrentNetworkResult {
        return DisconnectFromCurrentNetworkResult.Success.True
    }

    override fun disconnectFromCurrentNetwork(
        request: DisconnectFromCurrentNetworkRequest,
        callbacks: DisconnectFromCurrentNetworkCallbacks?
    ) {
        callbacks?.onSuccessDisconnectingFromCurrentNetwork(result = DisconnectFromCurrentNetworkResult.Success.True)
    }

    override fun enableWifi(request: EnableWifiRequest): EnableWifiResult {
        return EnableWifiResult.Success.Enabled
    }

    override fun enableWifi(request: EnableWifiRequest, callbacks: EnableWifiCallbacks?) {
        callbacks?.onSuccessEnablingWifi(result = EnableWifiResult.Success.Enabled)
    }

    override fun getAccessPoints(query: GetAccessPointsQuery): GetAccessPointsResult {
        return GetAccessPointsResult.Empty
    }

    override fun getAccessPoints(query: GetAccessPointsQuery, callbacks: GetAccessPointsCallbacks?) {
        callbacks?.onNoNearbyAccessPoints()
    }

    override fun getCurrentNetwork(query: GetCurrentNetworkQuery): GetCurrentNetworkResult {
        return GetCurrentNetworkResult(
            value = NetworkData(
                network = null,
                connectionInfo = null,
                capabilities = null,
                linkProperties = null
            )
        )
    }

    override fun getCurrentNetwork(query: GetCurrentNetworkQuery, callbacks: GetCurrentNetworkCallbacks?) {
        callbacks?.onCurrentNetworkRetrieved(
            NetworkData(
                network = null,
                connectionInfo = null,
                capabilities = null,
                linkProperties = null
            )
        )
    }

    override fun getNetworkConnectionStatus(query: GetNetworkConnectionStatusQuery): GetNetworkConnectionStatusResult {
        return GetNetworkConnectionStatusResult(
            value = NetworkConnectionStatusData(
                isConnected = false,
                isConnectedToMobileNetwork = false,
                isConnectedToWifiNetwork = false,
                isRoaming = false,
                ssidOfNetworkConnectedTo = null,
                bssidOfNetworkConnectedTo = null,
                ip = null
            )
        )
    }

    override fun getNetworkConnectionStatus(
        query: GetNetworkConnectionStatusQuery,
        callbacks: GetNetworkConnectionStatusCallbacks?
    ) {
        callbacks?.onDeviceNetworkConnectionStatusRetrieved(
            result = GetNetworkConnectionStatusResult(
                value = NetworkConnectionStatusData(
                    isConnected = false,
                    isConnectedToMobileNetwork = false,
                    isConnectedToWifiNetwork = false,
                    isRoaming = false,
                    ssidOfNetworkConnectedTo = null,
                    bssidOfNetworkConnectedTo = null,
                    ip = null
                )
            )
        )
    }

    override fun getSavedNetworks(query: GetSavedNetworksQuery): GetSavedNetworksResult {
        return GetSavedNetworksResult.Empty
    }

    override fun getSavedNetworks(query: GetSavedNetworksQuery, callbacks: GetSavedNetworksCallbacks?) {
        callbacks?.onNoSavedNetworksFound()
    }

    override fun isNetworkSaved(query: IsNetworkSavedQuery): IsNetworkSavedResult {
        return IsNetworkSavedResult.False
    }

    override fun isNetworkSaved(query: IsNetworkSavedQuery, callbacks: IsNetworkSavedCallbacks?) {
        callbacks?.onNetworkIsNotSaved()
    }

    override fun isWifiEnabled(query: IsWifiEnabledQuery): IsWifiEnabledResult {
        return IsWifiEnabledResult.True
    }

    override fun isWifiEnabled(query: IsWifiEnabledQuery, callbacks: IsWifiEnabledCallbacks?) {
        callbacks?.onWifiIsEnabled()
    }

    override fun removeNetwork(request: RemoveNetworkRequest): RemoveNetworkResult {
        return RemoveNetworkResult.Success.True
    }

    override fun removeNetwork(request: RemoveNetworkRequest, callbacks: RemoveNetworkCallbacks?) {
        callbacks?.onSuccessRemovingNetwork(RemoveNetworkResult.Success.True)
    }
}
