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
package com.isupatches.android.wisefy.networkconnectionstatus.os.impls

import android.Manifest.permission.ACCESS_NETWORK_STATE
import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.core.constants.QUOTE
import com.isupatches.android.wisefy.core.logging.WisefyLogger
import com.isupatches.android.wisefy.core.util.SdkUtil
import com.isupatches.android.wisefy.networkinfo.entities.NetworkConnectionStatus
import com.isupatches.android.wisefy.networkconnectionstatus.os.apis.DefaultNetworkConnectionStatusApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.math.BigInteger
import java.net.InetAddress
import java.net.UnknownHostException

/**
 * A default implementation for checking the device's connection status and if it meets certain criteria.
 *
 * @param wifiManager The WifiManager instance to use
 * @param connectivityManager The ConnectivityManager instance to use
 * @param sdkUtil The SdkUtil instance to use
 * @param logger The WisefyLogger instance to use
 *
 * @see DefaultNetworkConnectionStatusApi
 * @see SdkUtil
 * @see WisefyLogger
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
internal class DefaultNetworkConnectionStatusApiImpl(
    private val wifiManager: WifiManager,
    private val connectivityManager: ConnectivityManager,
    private val sdkUtil: SdkUtil,
    private val logger: WisefyLogger,
    private val scope: CoroutineScope,
    private val networkConnectionMutex: Mutex
) : DefaultNetworkConnectionStatusApi {




}
