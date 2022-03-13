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
package com.isupatches.android.wisefy.signal

import android.net.wifi.WifiManager
import com.isupatches.android.wisefy.shared.logging.WisefyLogger
import com.isupatches.android.wisefy.shared.util.SdkUtil
import com.isupatches.android.wisefy.signal.entities.CalculateBarsRequest
import com.isupatches.android.wisefy.signal.entities.CalculateBarsResult
import com.isupatches.android.wisefy.signal.entities.CompareSignalLevelRequest
import com.isupatches.android.wisefy.signal.entities.CompareSignalLevelResult
import com.isupatches.android.wisefy.signal.os.adapters.Android30SignalAdapter
import com.isupatches.android.wisefy.signal.os.adapters.DefaultSignalAdapter

class WisefySignalDelegate(
    logger: WisefyLogger?,
    sdkUtil: SdkUtil,
    wifiManager: WifiManager
) : SignalDelegate {

    companion object {
        private const val LOG_TAG = "WisefySignalDelegate"
    }

    private val adapter = when {
        sdkUtil.isAtLeastR() -> Android30SignalAdapter(wifiManager)
        else -> DefaultSignalAdapter()
    }

    init {
        logger?.d(LOG_TAG, "WisefySignalDelegate adapter is: ${adapter::class.java.simpleName}")
    }

    override fun calculateBars(request: CalculateBarsRequest): CalculateBarsResult {
        return adapter.calculateBars(request)
    }

    override fun compareSignalLevel(request: CompareSignalLevelRequest): CompareSignalLevelResult {
        return adapter.compareSignalLevel(request)
    }
}
