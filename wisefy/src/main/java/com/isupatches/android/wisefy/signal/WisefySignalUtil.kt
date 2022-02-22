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
package com.isupatches.android.wisefy.signal

import android.net.wifi.WifiManager
import android.os.Build
import androidx.annotation.RequiresApi
import com.isupatches.android.wisefy.constants.DeprecationMessages
import com.isupatches.android.wisefy.logging.WisefyLogger
import com.isupatches.android.wisefy.signal.delegates.Android30SignalDelegate
import com.isupatches.android.wisefy.signal.delegates.LegacySignalDelegate
import com.isupatches.android.wisefy.util.SdkUtil

internal interface SignalDelegate : SignalApi

private const val LOG_TAG = "WisefySignalUtil"

internal class WisefySignalUtil(
    logger: WisefyLogger?,
    sdkUtil: SdkUtil,
    wifiManager: WifiManager
) : SignalDelegate {

    private val delegate = when {
        sdkUtil.isAtLeastR() -> Android30SignalDelegate(wifiManager)
        else -> LegacySignalDelegate()
    }

    init {
        logger?.d(LOG_TAG, "WisefySignalUtil delegate is: ${delegate::class.java.simpleName}")
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun calculateBars(rssiLevel: Int): Int {
        return delegate.calculateBars(rssiLevel)
    }

    @Deprecated(DeprecationMessages.CALCULATE_BARS)
    override fun calculateBars(rssiLevel: Int, targetNumberOfBars: Int): Int {
        return delegate.calculateBars(rssiLevel, targetNumberOfBars)
    }

    override fun compareSignalLevel(rssi1: Int, rssi2: Int): Int {
        return delegate.compareSignalLevel(rssi1, rssi2)
    }
}
