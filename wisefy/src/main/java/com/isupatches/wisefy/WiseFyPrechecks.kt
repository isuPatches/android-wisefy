/*
 * Copyright 2018 Patches Klinefelter
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

import android.Manifest.permission.ACCESS_WIFI_STATE
import android.support.annotation.RequiresPermission

import com.isupatches.wisefy.constants.DEFAULT_PRECHECK_RETURN_CODE
import com.isupatches.wisefy.constants.MISSING_PARAMETER
import com.isupatches.wisefy.constants.NETWORK_ALREADY_CONFIGURED

import com.isupatches.wisefy.constants.WiseFyCode

/**
 * A helper class with methods to determine if the necessary requirements are met to preform operations.
 *
 * @see WiseFyPrechecks
 *
 * @author Patches
 * @since 3.0
 */
internal class WiseFyPrechecksImpl private constructor(
    private val wisefySearch: WiseFySearch
) : WiseFyPrechecks {

    /**
     *
     */
    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun addNetworkPrechecks(ssid: String?): PrecheckResult =
        checkAddNetworkPrerequisites(ssid)

    /**
     *
     */
    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun addNetworkPrechecks(ssid: String?, password: String?): PrecheckResult =
        checkAddNetworkPrerequisites(ssid, password)

    /**
     *
     */
    override fun connectToNetworkPrechecks(ssidToConnectTo: String?): PrecheckResult =
        checkForParam(ssidToConnectTo)

    /**
     *
     */
    override fun disableWifiChecks() = DEFAULT_PRECHECK_RESULT

    /**
     *
     */
    override fun disconnectFromCurrentNetworkChecks() = DEFAULT_PRECHECK_RESULT

    /**
     *
     */
    override fun enableWifiChecks() = DEFAULT_PRECHECK_RESULT

    /**
     *
     */
    override fun getCurrentNetworkChecks() = DEFAULT_PRECHECK_RESULT

    /**
     *
     */
    override fun getCurrentNetworkInfoChecks() = DEFAULT_PRECHECK_RESULT

    /**
     *
     */
    override fun getIPChecks() = DEFAULT_PRECHECK_RESULT

    /**
     *
     */
    override fun getNearbyAccessPointsChecks() = DEFAULT_PRECHECK_RESULT

    /**
     *
     */
    override fun getRSSIChecks(regexForSSID: String?): PrecheckResult =
        checkForParam(regexForSSID)

    /**
     *
     */
    override fun getSavedNetworkChecks(regexForSSID: String?): PrecheckResult =
        checkForParam(regexForSSID)

    /**
     *
     */
    override fun getSavedNetworksChecks() = DEFAULT_PRECHECK_RESULT

    /**
     *
     */
    override fun getSavedNetworksChecks(regexForSSID: String?): PrecheckResult =
        checkForParam(regexForSSID)

    /**
     *
     */
    override fun isDeviceConnectedToMobileNetworkChecks() = DEFAULT_PRECHECK_RESULT

    /**
     *
     */
    override fun isDeviceConnectedToMobileOrWifiNetworkChecks() = DEFAULT_PRECHECK_RESULT

    /**
     *
     */
    override fun isDeviceConnectedToSSIDChecks(ssid: String?): PrecheckResult =
        checkForParam(ssid)

    /**
     *
     */
    override fun isDeviceConnectedToWifiNetworkChecks() = DEFAULT_PRECHECK_RESULT

    /**
     *
     */
    override fun isDeviceRoamingChecks() = DEFAULT_PRECHECK_RESULT

    /**
     *
     */
    override fun isNetworkSavedChecks() = DEFAULT_PRECHECK_RESULT

    /**
     *
     */
    override fun isWifiEnabledChecks() = DEFAULT_PRECHECK_RESULT

    /**
     *
     */
    override fun removeNetworkCheck(ssidToRemove: String?): PrecheckResult =
        checkForParam(ssidToRemove)

    /**
     *
     */
    override fun searchForAccessPointChecks(regexForSSID: String?): PrecheckResult =
        checkForParam(regexForSSID)

    /**
     *
     */
    override fun searchForAccessPointsChecks(regexForSSID: String?): PrecheckResult =
        checkForParam(regexForSSID)

    /**
     *
     */
    override fun searchForSSIDChecks(regexForSSID: String?): PrecheckResult =
        checkForParam(regexForSSID)

    /**
     *
     */
    override fun searchForSSIDsChecks(regexForSSID: String?): PrecheckResult =
        checkForParam(regexForSSID)

    /*
     * HELPERS
     */

    /**
     *
     */
    private fun checkForParam(param: String?): PrecheckResult {
        return if (param.isNullOrEmpty()) {
            PrecheckResult(code = MISSING_PARAMETER)
        } else {
            DEFAULT_PRECHECK_RESULT
        }
    }

    /**
     *
     */
    private fun checkAddNetworkPrerequisites(ssid: String?): PrecheckResult =
        when {
            ssid == null -> PrecheckResult(code = MISSING_PARAMETER)
            ssid.isEmpty() -> PrecheckResult(code = MISSING_PARAMETER)
            wisefySearch.isNetworkASavedConfiguration(ssid) -> {
                PrecheckResult(code = NETWORK_ALREADY_CONFIGURED)
            }
            else -> DEFAULT_PRECHECK_RESULT
        }

    /**
     *
     */
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
 *
 */
interface WiseFyPrechecks {

    /**
     *
     */
    fun addNetworkPrechecks(ssid: String?): PrecheckResult

    /**
     *
     */
    fun addNetworkPrechecks(ssid: String?, password: String?): PrecheckResult

    /**
     *
     */
    fun connectToNetworkPrechecks(ssidToConnectTo: String?): PrecheckResult

    /**
     *
     */
    fun disableWifiChecks(): PrecheckResult

    /**
     *
     */
    fun disconnectFromCurrentNetworkChecks(): PrecheckResult

    /**
     *
     */
    fun enableWifiChecks(): PrecheckResult

    /**
     *
     */
    fun getCurrentNetworkChecks(): PrecheckResult

    /**
     *
     */
    fun getCurrentNetworkInfoChecks(): PrecheckResult

    /**
     *
     */
    fun getIPChecks(): PrecheckResult

    /**
     *
     */
    fun getNearbyAccessPointsChecks(): PrecheckResult

    /**
     *
     */
    fun getRSSIChecks(regexForSSID: String?): PrecheckResult

    /**
     *
     */
    fun getSavedNetworkChecks(regexForSSID: String?): PrecheckResult

    /**
     *
     */
    fun getSavedNetworksChecks(regexForSSID: String?): PrecheckResult

    /**
     *
     */
    fun getSavedNetworksChecks(): PrecheckResult

    /**
     *
     */
    fun isDeviceConnectedToMobileNetworkChecks(): PrecheckResult

    /**
     *
     */
    fun isDeviceConnectedToMobileOrWifiNetworkChecks(): PrecheckResult

    /**
     *
     */
    fun isDeviceConnectedToSSIDChecks(ssid: String?): PrecheckResult

    /**
     *
     */
    fun isDeviceConnectedToWifiNetworkChecks(): PrecheckResult

    /**
     *
     */
    fun isDeviceRoamingChecks(): PrecheckResult

    /**
     *
     */
    fun isNetworkSavedChecks(): PrecheckResult

    /**
     *
     */
    fun isWifiEnabledChecks(): PrecheckResult

    /**
     * Used internally to make sure prerequisites for removing a network are met
     *
     * @author Patches
     * @since 3.0
     */
    fun removeNetworkCheck(ssidToRemove: String?): PrecheckResult

    /**
     * Used internally to make sure prerequisites for searching for an individual access point are met
     *
     * @author Patches
     * @since 3.0
     */
    fun searchForAccessPointChecks(regexForSSID: String?): PrecheckResult

    /**
     * Used internally to make sure prerequisites for searching for access points are met
     *
     * @author Patches
     * @since 3.0
     */
    fun searchForAccessPointsChecks(regexForSSID: String?): PrecheckResult

    /**
     * Used internally to make sure prerequisites for searching for an individual SSID are met
     *
     * @author Patches
     * @since 3.0
     */
    fun searchForSSIDChecks(regexForSSID: String?): PrecheckResult

    /**
     * Used internally to make sure prerequisites for searching for SSIDs are met
     *
     * @author Patches
     * @since 3.0
     */
    fun searchForSSIDsChecks(regexForSSID: String?): PrecheckResult
}

/**
 * A return from WiseFyPrechecks that includes a relevant detail code.
 *
 * @param code The return code from prechecks (i.e. [DEFAULT_PRECHECK_RETURN_CODE] or
 * an error code such as [MISSING_PARAMETER].  This code will also be used by the extension
 * functions [passed] and [failed]
 *
 * @see [passed]
 * @see [failed]
 * @see [WiseFyCode]
 *
 * @author Patches
 * @since 3.0
 */
data class PrecheckResult(@WiseFyCode val code: Int)

/**
 *  An extension function to determine if prerequisites for an operation are NOT met
 *
 * @see [PrecheckResult]
 *
 * @author Patches
 * @since 3.0
 */
fun PrecheckResult.failed(): Boolean = code < DEFAULT_PRECHECK_RETURN_CODE

/**
 *  An extension function to determine if prerequisites for an operation ARE met
 *
 * @see [PrecheckResult]
 *
 * @author Patches
 * @since 3.0
 */
fun PrecheckResult.passed(): Boolean = code >= DEFAULT_PRECHECK_RETURN_CODE

/**
 * A default result to return that denotes a success and that prerequisites for an operation are met
 *
 * @see [PrecheckResult]
 *
 * @author Patches
 * @since 3.0
 */
internal val DEFAULT_PRECHECK_RESULT = PrecheckResult(code = DEFAULT_PRECHECK_RETURN_CODE)
