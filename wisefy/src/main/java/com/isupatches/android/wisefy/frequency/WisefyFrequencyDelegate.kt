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
package com.isupatches.android.wisefy.frequency

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.net.ConnectivityManager
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.frequency.delegates.DefaultFrequencyDelegate
import com.isupatches.android.wisefy.frequency.entities.FrequencyData
import com.isupatches.android.wisefy.logging.WisefyLogger
import com.isupatches.android.wisefy.util.coroutines.CoroutineDispatcherProvider
import com.isupatches.android.wisefy.util.coroutines.createBaseCoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal interface FrequencyDelegate : FrequencyApi {

    @RequiresPermission(ACCESS_FINE_LOCATION)
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun getFrequency(callbacks: GetFrequencyCallbacks?)
}

private const val LOG_TAG = "WisefyFrequencyUtil"

internal class WisefyFrequencyDelegate(
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider,
    private val scope: CoroutineScope,
    logger: WisefyLogger?,
    connectivityManager: ConnectivityManager,
    wifiManager: WifiManager
) : FrequencyDelegate {

    private val delegate = DefaultFrequencyDelegate(wifiManager, connectivityManager)

    init {
        logger?.d(LOG_TAG, "WisefyFrequencyUtil delegate is: ${delegate::class.java.simpleName}")
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun getFrequency(): FrequencyData? {
        return delegate.getFrequency()
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun getFrequency(callbacks: GetFrequencyCallbacks?) {
        scope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            val frequency = delegate.getFrequency()
            withContext(coroutineDispatcherProvider.main) {
                if (frequency != null) {
                    callbacks?.onFrequencyRetrieved(frequency)
                } else {
                    callbacks?.onFailureRetrievingFrequency()
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun getFrequency(network: WifiInfo): FrequencyData {
        return delegate.getFrequency(network)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun isNetwork5gHz(): Boolean {
        return delegate.isNetwork5gHz()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun isNetwork5gHz(network: WifiInfo): Boolean {
        return delegate.isNetwork5gHz(network)
    }
}
