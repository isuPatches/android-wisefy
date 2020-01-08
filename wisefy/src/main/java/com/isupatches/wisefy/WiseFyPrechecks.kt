/*
 * Copyright 2019 Patches Klinefelter
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
package com.isupatches.wisefy

import android.Manifest.permission.ACCESS_FINE_LOCATION
import androidx.annotation.RequiresPermission
import com.isupatches.wisefy.constants.DEFAULT_PRECHECK_RETURN_CODE
import com.isupatches.wisefy.constants.MISSING_PARAMETER
import com.isupatches.wisefy.constants.NETWORK_ALREADY_CONFIGURED
import com.isupatches.wisefy.constants.WiseFyCode
import com.isupatches.wisefy.search.WiseFySearch

/**
 * A helper class with methods to determine if the necessary requirements are met to preform operations.
 *
 * @see [WiseFyPrechecks]
 * @see [WiseFySearch]
 *
 * @author Patches
 * @since 3.0
 */
internal class WiseFyPrechecksImpl private constructor(
    private val wisefySearch: WiseFySearch
) : WiseFyPrechecks {

    /**
     * Used internally to make sure perquisites for adding a network as a saved configuration are met.
     *
     * @param ssid The ssid param from [com.isupatches.wisefy.WiseFy.addOpenNetwork]
     *
     * @return PrecheckResult - [DEFAULT_PRECHECK_RESULT] or a [PrecheckResult] with an error code.
     *
     * @see [checkAddNetworkPrerequisites]
     * @see [com.isupatches.wisefy.WiseFy.addOpenNetwork]
     *
     * @author Patches
     * @since 3.0
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun addNetworkPrechecks(ssid: String?): PrecheckResult =
        checkAddNetworkPrerequisites(ssid)

    /**
     * Used internally to make sure perquisites for adding a network as a saved configuration are met.
     *
     * @param ssid The ssid param from [com.isupatches.wisefy.WiseFy.addWEPNetwork] or
     *  [com.isupatches.wisefy.WiseFy.addWPA2Network]
     * @param password The password from [com.isupatches.wisefy.WiseFy.addWEPNetwork] or
     *  [com.isupatches.wisefy.WiseFy.addWPA2Network]
     *
     * @return PrecheckResult - [DEFAULT_PRECHECK_RESULT] or a [PrecheckResult] with an error code.
     *
     * @see [checkAddNetworkPrerequisites]
     * @see [com.isupatches.wisefy.WiseFy.addWEPNetwork]
     * @see [com.isupatches.wisefy.WiseFy.addWPA2Network]
     *
     * @author Patches
     * @since 3.0
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun addNetworkPrechecks(ssid: String?, password: String?): PrecheckResult =
        checkAddNetworkPrerequisites(ssid, password)

    /**
     * Used internally to make sure perquisites for connecting to a network are met.
     *
     * @param ssidToConnectTo The ssidToConnectTo param from [com.isupatches.wisefy.WiseFy.connectToNetwork]
     *
     * @return PrecheckResult - [DEFAULT_PRECHECK_RESULT] or a [PrecheckResult] with an error code.
     *
     * @see [checkForParam]
     * @see [com.isupatches.wisefy.WiseFy.connectToNetwork]
     *
     * @author Patches
     * @since 3.0
     */
    override fun connectToNetworkPrechecks(ssidToConnectTo: String?): PrecheckResult =
        checkForParam(ssidToConnectTo)

    /**
     * Used internally to make sure perquisites for disabling wifi on the device are met.
     *
     * @return PrecheckResult - [DEFAULT_PRECHECK_RESULT]
     *
     * @see [com.isupatches.wisefy.WiseFy.disableWifi]
     * @see [DEFAULT_PRECHECK_RESULT]
     *
     * @author Patches
     * @since 3.0
     */
    override fun disableWifiChecks() = DEFAULT_PRECHECK_RESULT

    /**
     * Used internally to make sure perquisites for disconnecting a device from it's current network are met.
     *
     * @return PrecheckResult - [DEFAULT_PRECHECK_RESULT]
     *
     * @see [com.isupatches.wisefy.WiseFy.disconnectFromCurrentNetwork]
     * @see [DEFAULT_PRECHECK_RESULT]
     *
     * @author Patches
     * @since 3.0
     */
    override fun disconnectFromCurrentNetworkChecks() = DEFAULT_PRECHECK_RESULT

    /**
     * Used internally to make sure perquisites for enabling wifi on the device are met.
     *
     * @return PrecheckResult - [DEFAULT_PRECHECK_RESULT]
     *
     * @see [com.isupatches.wisefy.WiseFy.enableWifi]
     * @see [DEFAULT_PRECHECK_RESULT]
     *
     * @author Patches
     * @since 3.0
     */
    override fun enableWifiChecks() = DEFAULT_PRECHECK_RESULT

    /**
     * Used internally to make sure perquisites for getting the device's current network are met.
     *
     * @return PrecheckResult - [DEFAULT_PRECHECK_RESULT]
     *
     * @see [com.isupatches.wisefy.WiseFy.getCurrentNetworkInfo]
     * @see [DEFAULT_PRECHECK_RESULT]
     *
     * @author Patches
     * @since 3.0
     */
    override fun getCurrentNetworkChecks() = DEFAULT_PRECHECK_RESULT

    /**
     * Used internally to make sure perquisites for getting the device's current network info are met.
     *
     * @return PrecheckResult - [DEFAULT_PRECHECK_RESULT]
     *
     * @see [com.isupatches.wisefy.WiseFy.getCurrentNetworkInfo]
     * @see [DEFAULT_PRECHECK_RESULT]
     *
     * @author Patches
     * @since 3.0
     */
    override fun getCurrentNetworkInfoChecks() = DEFAULT_PRECHECK_RESULT

    /**
     * Used internally to make sure perquisites for getting the frequency of the current network are met.
     *
     * @return PrecheckResult - [DEFAULT_PRECHECK_RESULT]
     *
     * @see [com.isupatches.wisefy.WiseFy.getFrequency]
     * @see [DEFAULT_PRECHECK_RESULT]
     *
     * @author Patches
     * @since 4.1
     */
    override fun getFrequencyChecks() = DEFAULT_PRECHECK_RESULT

    /**
     * Used internally to make sure perquisites for getting the ip of the device are met.
     *
     * @return PrecheckResult - [DEFAULT_PRECHECK_RESULT]
     *
     * @see [com.isupatches.wisefy.WiseFy.getIP]
     * @see [DEFAULT_PRECHECK_RESULT]
     *
     * @author Patches
     * @since 3.0
     */
    override fun getIPChecks() = DEFAULT_PRECHECK_RESULT

    /**
     * Used internally to make sure perquisites for getting nearby access points are met.
     *
     * @return PrecheckResult - [DEFAULT_PRECHECK_RESULT]
     *
     * @see [com.isupatches.wisefy.WiseFy.getNearbyAccessPoints]
     * @see [DEFAULT_PRECHECK_RESULT]
     *
     * @author Patches
     * @since 3.0
     */
    override fun getNearbyAccessPointsChecks() = DEFAULT_PRECHECK_RESULT

    /**
     * Used internally to make sure perquisites for getting the RSSI of a network matching a given regex are met.
     *
     * @param regexForSSID The regexForSSID param from [com.isupatches.wisefy.WiseFy.getRSSI]
     *
     * @return PrecheckResult - [DEFAULT_PRECHECK_RESULT] or a [PrecheckResult] with an error code.
     *
     * @see [checkForParam]
     * @see [com.isupatches.wisefy.WiseFy.getRSSI]
     *
     * @author Patches
     * @since 3.0
     */
    override fun getRSSIChecks(regexForSSID: String?): PrecheckResult =
        checkForParam(regexForSSID)

    /**
     * Used internally to make sure perquisites for searching for a saved network on a device are met.
     *
     * @param regexForSSID The regexForSSID param from [com.isupatches.wisefy.WiseFy.searchForSavedNetwork]
     *
     * @return PrecheckResult - [DEFAULT_PRECHECK_RESULT] or a [PrecheckResult] with an error code.
     *
     * @see [checkForParam]
     * @see [com.isupatches.wisefy.WiseFy.searchForSavedNetwork]
     *
     * @author Patches
     * @since 3.0
     */
    override fun getSavedNetworkChecks(regexForSSID: String?): PrecheckResult =
        checkForParam(regexForSSID)

    /**
     * Used internally to make sure perquisites for getting a saved network on a device are met.
     *
     * @return PrecheckResult - [DEFAULT_PRECHECK_RESULT]
     *
     * @see [com.isupatches.wisefy.WiseFy.getSavedNetworks]
     * @see [DEFAULT_PRECHECK_RESULT]
     *
     * @author Patches
     * @since 3.0
     */
    override fun getSavedNetworksChecks() = DEFAULT_PRECHECK_RESULT

    /**
     * Used internally to make sure perquisites for getting the saved networks on a device are met.
     *
     * @param regexForSSID The regexForSSID param from [com.isupatches.wisefy.WiseFy.getSavedNetworks]
     *
     * @return PrecheckResult - [DEFAULT_PRECHECK_RESULT] or a [PrecheckResult] with an error code.
     *
     * @see [checkForParam]
     * @see [com.isupatches.wisefy.WiseFy.getSavedNetworks]
     *
     * @author Patches
     * @since 3.0
     */
    override fun getSavedNetworksChecks(regexForSSID: String?): PrecheckResult =
        checkForParam(regexForSSID)

    /**
     * Used internally to make sure perquisites for checking if the device is connected to
     * a mobile network are met.
     *
     * @return PrecheckResult - [DEFAULT_PRECHECK_RESULT]
     *
     * @see [com.isupatches.wisefy.WiseFy.isDeviceConnectedToMobileNetwork]
     * @see [DEFAULT_PRECHECK_RESULT]
     *
     * @author Patches
     * @since 3.0
     */
    override fun isDeviceConnectedToMobileNetworkChecks() = DEFAULT_PRECHECK_RESULT

    /**
     * Used internally to make sure perquisites for checking if the device is connected to
     * a mobile or wifi network are met.
     *
     * @return PrecheckResult - [DEFAULT_PRECHECK_RESULT]
     *
     * @see [com.isupatches.wisefy.WiseFy.isDeviceConnectedToMobileOrWifiNetwork]
     * @see [DEFAULT_PRECHECK_RESULT]
     *
     * @author Patches
     * @since 3.0
     */
    override fun isDeviceConnectedToMobileOrWifiNetworkChecks() = DEFAULT_PRECHECK_RESULT

    /**
     * Used internally to make sure perquisites for checking if the device is connected to
     * a specific SSID are met.
     *
     * @param ssid The ssid param from [com.isupatches.wisefy.WiseFy.isDeviceConnectedToSSID]
     *
     * @return PrecheckResult - [DEFAULT_PRECHECK_RESULT] or a [PrecheckResult] with an error code.
     *
     * @see [checkForParam]
     * @see [com.isupatches.wisefy.WiseFy.isDeviceConnectedToSSID]
     *
     * @author Patches
     * @since 3.0
     */
    override fun isDeviceConnectedToSSIDChecks(ssid: String?): PrecheckResult =
        checkForParam(ssid)

    /**
     * Used internally to make sure perquisites for checking if the device is connected to
     * a wifi network are met.
     *
     * @return PrecheckResult - [DEFAULT_PRECHECK_RESULT]
     *
     * @see [com.isupatches.wisefy.WiseFy.isDeviceConnectedToWifiNetwork]
     * @see [DEFAULT_PRECHECK_RESULT]
     *
     * @author Patches
     * @since 3.0
     */
    override fun isDeviceConnectedToWifiNetworkChecks() = DEFAULT_PRECHECK_RESULT

    /**
     * Used internally to make sure prerequisites for seeing if a device is roaming are met.
     *
     * @return PrecheckResult - [DEFAULT_PRECHECK_RESULT]
     *
     * @see [com.isupatches.wisefy.WiseFy.isDeviceRoaming]
     * @see [DEFAULT_PRECHECK_RESULT]
     *
     * @author Patches
     * @since 3.0
     */
    override fun isDeviceRoamingChecks() = DEFAULT_PRECHECK_RESULT

    /**
     * Used internally to make sure prerequisites for seeing if a network is a saved configuration
     * are met.
     *
     * @return PrecheckResult - [DEFAULT_PRECHECK_RESULT]
     *
     * @see [com.isupatches.wisefy.WiseFy.isNetworkSaved]
     * @see [DEFAULT_PRECHECK_RESULT]
     *
     * @author Patches
     * @since 3.0
     */
    override fun isNetworkSavedChecks() = DEFAULT_PRECHECK_RESULT

    /**
     * Used internally to make sure prerequisites for enabling wifi are met.
     *
     * @return PrecheckResult - [DEFAULT_PRECHECK_RESULT]
     *
     * @see [com.isupatches.wisefy.WiseFy.isWifiEnabled]
     * @see [DEFAULT_PRECHECK_RESULT]
     *
     * @author Patches
     * @since 3.0
     */
    override fun isWifiEnabledChecks() = DEFAULT_PRECHECK_RESULT

    /**
     * Used internally to make sure prerequisites for removing a network are met.
     *
     * @param ssidToRemove The regexForSsid param from [com.isupatches.wisefy.WiseFy.removeNetwork]
     *
     * @return PrecheckResult - [DEFAULT_PRECHECK_RESULT] or a [PrecheckResult] with an error code.
     *
     * @see [checkForParam]
     * @see [com.isupatches.wisefy.WiseFy.removeNetwork]
     *
     * @author Patches
     * @since 3.0
     */
    override fun removeNetworkCheck(ssidToRemove: String?): PrecheckResult =
        checkForParam(ssidToRemove)

    /**
     * Used internally to make sure prerequisites for searching for an individual access point
     * are met.
     *
     * @param regexForSSID The regexForSsid param from [com.isupatches.wisefy.WiseFy.searchForAccessPoint]
     *
     * @return PrecheckResult - [DEFAULT_PRECHECK_RESULT] or a [PrecheckResult] with an error code.
     *
     * @see [checkForParam]
     * @see [com.isupatches.wisefy.WiseFy.searchForAccessPoint]
     *
     * @author Patches
     * @since 3.0
     */
    override fun searchForAccessPointChecks(regexForSSID: String?): PrecheckResult =
        checkForParam(regexForSSID)

    /**
     * Used internally to make sure prerequisites for searching for access points are met.
     *
     * @param regexForSSID The regexForSsid param from [com.isupatches.wisefy.WiseFy.searchForAccessPoints]
     *
     * @return PrecheckResult - [DEFAULT_PRECHECK_RESULT] or a [PrecheckResult] with an error code.
     *
     * @see [checkForParam]
     * @see [com.isupatches.wisefy.WiseFy.searchForAccessPoints]
     *
     * @author Patches
     * @since 3.0
     */
    override fun searchForAccessPointsChecks(regexForSSID: String?): PrecheckResult =
        checkForParam(regexForSSID)

    /**
     * Used internally to make sure prerequisites for searching for an individual saved network
     * are met.
     *
     * @param regexForSSID The regexForSsid param from [com.isupatches.wisefy.WiseFy.searchForSavedNetwork]
     *
     * @return PrecheckResult - [DEFAULT_PRECHECK_RESULT] or a [PrecheckResult] with an error code.
     *
     * @see [checkForParam]
     * @see [com.isupatches.wisefy.WiseFy.searchForSavedNetwork]
     *
     * @author Patches
     * @since 4.0
     */
    override fun searchForSavedNetworkChecks(regexForSSID: String?): PrecheckResult =
        checkForParam(regexForSSID)

    /**
     * Used internally to make sure prerequisites for searching for saved networks are met.
     *
     * @param regexForSSID The regexForSsid param from [com.isupatches.wisefy.WiseFy.searchForSavedNetworks]
     *
     * @return PrecheckResult - [DEFAULT_PRECHECK_RESULT] or a [PrecheckResult] with an error code.
     *
     * @see [checkForParam]
     * @see [com.isupatches.wisefy.WiseFy.searchForSavedNetworks]
     *
     * @author Patches
     * @since 4.0
     */
    override fun searchForSavedNetworksChecks(regexForSSID: String?): PrecheckResult =
        checkForParam(regexForSSID)

    /**
     * Used internally to make sure prerequisites for searching for an individual SSID are met.
     *
     * @param regexForSSID The regexForSsid param from [com.isupatches.wisefy.WiseFy.searchForSSID]
     *
     * @return PrecheckResult - [DEFAULT_PRECHECK_RESULT] or a [PrecheckResult] with an error code.
     *
     * @see [checkForParam]
     * @see [com.isupatches.wisefy.WiseFy.searchForSSID]
     *
     * @author Patches
     * @since 3.0
     */
    override fun searchForSSIDChecks(regexForSSID: String?): PrecheckResult =
        checkForParam(regexForSSID)

    /**
     * Used internally to make sure prerequisites for searching for SSIDs are met.
     *
     * @param regexForSSID The regexForSsid param from [com.isupatches.wisefy.WiseFy.searchForSSIDs]
     *
     * @return PrecheckResult - [DEFAULT_PRECHECK_RESULT] or a [PrecheckResult] with an error code.
     *
     * @see [checkForParam]
     * @see [com.isupatches.wisefy.WiseFy.searchForSSIDs]
     *
     * @author Patches
     * @since 3.0
     */
    override fun searchForSSIDsChecks(regexForSSID: String?): PrecheckResult =
        checkForParam(regexForSSID)

    /*
     * HELPERS
     */

    /**
     * Used internally as an abstracted layer that check if a necessary param is not empty.
     *
     * @param param The param to check if it's empty
     *
     * @return PrecheckResult - [DEFAULT_PRECHECK_RESULT] or a [PrecheckResult] with an error code.
     *
     * @see [DEFAULT_PRECHECK_RESULT]
     * @see [MISSING_PARAMETER]
     * @see [PrecheckResult]
     *
     * @author Patches
     * @since 3.0
     */
    private fun checkForParam(param: String?): PrecheckResult =
        if (param.isNullOrEmpty()) {
            PrecheckResult(code = MISSING_PARAMETER)
        } else {
            DEFAULT_PRECHECK_RESULT
        }

    /**
     * Used internally as an abstracted layer that checks if all of the prerequisites for adding
     * a network are met. f.e. ssid is not null or empty, network is not already saved, etc.
     *
     * @param ssid The ssid of the network to add
     *
     * @return PrecheckResult - [DEFAULT_PRECHECK_RESULT] or a [PrecheckResult] with an error code.
     *
     * @see [DEFAULT_PRECHECK_RESULT]
     * @see [MISSING_PARAMETER]
     * @see [NETWORK_ALREADY_CONFIGURED]
     * @see [PrecheckResult]
     *
     * @author Patches
     * @since 3.0
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    private fun checkAddNetworkPrerequisites(ssid: String?): PrecheckResult =
        when {
            ssid.isNullOrEmpty() -> PrecheckResult(code = MISSING_PARAMETER)
            wisefySearch.isNetworkASavedConfiguration(ssid) -> {
                PrecheckResult(code = NETWORK_ALREADY_CONFIGURED)
            }
            else -> DEFAULT_PRECHECK_RESULT
        }

    /**
     * Used internally as an abstracted layer that checks if all of the prerequisites for adding
     * a network are met. f.e. ssid and password are not null or empty, network is not already saved, etc.
     *
     * @param ssid The ssid of the network to add
     * @param password The password of the network to add
     *
     * @return PrecheckResult - [DEFAULT_PRECHECK_RESULT] or a [PrecheckResult] with an error code.
     *
     * @see [DEFAULT_PRECHECK_RESULT]
     * @see [MISSING_PARAMETER]
     * @see [NETWORK_ALREADY_CONFIGURED]
     * @see [PrecheckResult]
     *
     * @author Patches
     * @since 3.0
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    private fun checkAddNetworkPrerequisites(ssid: String?, password: String?): PrecheckResult =
        when {
            ssid.isNullOrEmpty() || password.isNullOrEmpty() -> {
                PrecheckResult(code = MISSING_PARAMETER)
            }
            wisefySearch.isNetworkASavedConfiguration(ssid) -> {
                PrecheckResult(code = NETWORK_ALREADY_CONFIGURED)
            }
            else -> DEFAULT_PRECHECK_RESULT
        }

    internal companion object {
        fun create(wisefySearch: WiseFySearch): WiseFyPrechecks = WiseFyPrechecksImpl(wisefySearch)
    }
}

/**
 * An interface with methods that relate to checking that prerequisites are met before
 * continuing with a WiseFy operation.
 *
 * @see [WiseFyPrechecksImpl]
 *
 * Updates
 * - 01/04/2020: Refined permissions
 *
 * @author Patches
 * @since 3.0
 */
internal interface WiseFyPrechecks {

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun addNetworkPrechecks(ssid: String?): PrecheckResult

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun addNetworkPrechecks(ssid: String?, password: String?): PrecheckResult

    fun connectToNetworkPrechecks(ssidToConnectTo: String?): PrecheckResult

    fun disableWifiChecks(): PrecheckResult

    fun disconnectFromCurrentNetworkChecks(): PrecheckResult

    fun enableWifiChecks(): PrecheckResult

    fun getCurrentNetworkChecks(): PrecheckResult

    fun getCurrentNetworkInfoChecks(): PrecheckResult

    fun getFrequencyChecks(): PrecheckResult

    fun getIPChecks(): PrecheckResult

    fun getNearbyAccessPointsChecks(): PrecheckResult

    fun getRSSIChecks(regexForSSID: String?): PrecheckResult

    fun getSavedNetworkChecks(regexForSSID: String?): PrecheckResult

    fun getSavedNetworksChecks(regexForSSID: String?): PrecheckResult

    fun getSavedNetworksChecks(): PrecheckResult

    fun isDeviceConnectedToMobileNetworkChecks(): PrecheckResult

    fun isDeviceConnectedToMobileOrWifiNetworkChecks(): PrecheckResult

    fun isDeviceConnectedToSSIDChecks(ssid: String?): PrecheckResult

    fun isDeviceConnectedToWifiNetworkChecks(): PrecheckResult

    fun isDeviceRoamingChecks(): PrecheckResult

    fun isNetworkSavedChecks(): PrecheckResult

    fun isWifiEnabledChecks(): PrecheckResult

    fun removeNetworkCheck(ssidToRemove: String?): PrecheckResult

    fun searchForAccessPointChecks(regexForSSID: String?): PrecheckResult

    fun searchForAccessPointsChecks(regexForSSID: String?): PrecheckResult

    fun searchForSavedNetworkChecks(regexForSSID: String?): PrecheckResult

    fun searchForSavedNetworksChecks(regexForSSID: String?): PrecheckResult

    fun searchForSSIDChecks(regexForSSID: String?): PrecheckResult

    fun searchForSSIDsChecks(regexForSSID: String?): PrecheckResult
}

/**
 * A return from WiseFyPrechecks that includes a relevant detail code.
 *
 * @param code The return code from prechecks (f.e. [DEFAULT_PRECHECK_RETURN_CODE] or
 * an error code such as [MISSING_PARAMETER].  This code will also be used by the extension
 * functions [passed] and [failed].
 *
 * @see [failed]
 * @see [passed]
 * @see [WiseFyCode]
 *
 * @author Patches
 * @since 3.0
 */
internal data class PrecheckResult(@WiseFyCode val code: Int)

/**
 *  An extension function to determine if prerequisites for an operation are NOT met.
 *
 * @see [PrecheckResult]
 *
 * @author Patches
 * @since 3.0
 */
internal fun PrecheckResult.failed(): Boolean = code < DEFAULT_PRECHECK_RETURN_CODE

/**
 *  An extension function to determine if prerequisites for an operation ARE met.
 *
 * @see [PrecheckResult]
 *
 * @author Patches
 * @since 3.0
 */
internal fun PrecheckResult.passed(): Boolean = code >= DEFAULT_PRECHECK_RETURN_CODE

/**
 * A default result to return that denotes a success and that prerequisites for an operation are met.
 *
 * @see [PrecheckResult]
 *
 * @author Patches
 * @since 3.0
 */
internal val DEFAULT_PRECHECK_RESULT = PrecheckResult(code = DEFAULT_PRECHECK_RETURN_CODE)
