/*
 * Copyright 2021 Patches Klinefelter
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
package com.isupatches.android.wisefy.networkinfo.delegates

import android.Manifest.permission.ACCESS_NETWORK_STATE
import android.net.ConnectivityManager
import android.net.Network
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.logging.WisefyLogger
import com.isupatches.android.wisefy.networkinfo.entities.CurrentNetworkData
import com.isupatches.android.wisefy.networkinfo.entities.CurrentNetworkInfoData
import java.math.BigInteger
import java.net.InetAddress
import java.net.UnknownHostException

internal interface LegacyNetworkInfoApi {
    fun getCurrentNetwork(): CurrentNetworkData?

    fun getCurrentNetworkInfo(network: Network?): CurrentNetworkInfoData?

    fun getIP(): String?
}

private const val LOG_TAG = "LegacyNetworkInfoApiImpl"

internal class LegacyNetworkInfoApiImpl(
    private val wifiManager: WifiManager,
    private val connectivityManager: ConnectivityManager,
    private val logger: WisefyLogger?
) : LegacyNetworkInfoApi {

    override fun getCurrentNetwork(): CurrentNetworkData? {
        val currentNetwork = wifiManager.connectionInfo
        return if (currentNetwork != null) {
            CurrentNetworkData(currentNetwork)
        } else {
            null
        }
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun getCurrentNetworkInfo(network: Network?): CurrentNetworkInfoData? {
        val networkForInfo = network ?: connectivityManager.activeNetwork
        return if (networkForInfo != null) {
            CurrentNetworkInfoData(
                capabilities = connectivityManager.getNetworkCapabilities(networkForInfo),
                linkProperties = connectivityManager.getLinkProperties(networkForInfo)
            )
        } else {
            null
        }
    }

    override fun getIP(): String? {
        val ipAddress = BigInteger.valueOf(wifiManager.connectionInfo.ipAddress.toLong()).toByteArray()
        return try {
            InetAddress.getByAddress(ipAddress).hostAddress
        } catch (uhe: UnknownHostException) {
            logger?.e(LOG_TAG, uhe, "UnknownHostException while gathering IP (sync)")
            null
        }
    }
}
