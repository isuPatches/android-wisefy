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
package com.isupatches.android.wisefy.signal

import android.net.wifi.WifiManager
import com.isupatches.android.wisefy.core.assertions.WisefyAssertions
import com.isupatches.android.wisefy.core.logging.WisefyLogger
import com.isupatches.android.wisefy.core.util.SdkUtil
import com.isupatches.android.wisefy.signal.entities.CalculateSignalLevelRequest
import com.isupatches.android.wisefy.signal.entities.CalculateSignalLevelResult
import com.isupatches.android.wisefy.signal.entities.CompareSignalLevelRequest
import com.isupatches.android.wisefy.signal.entities.CompareSignalLevelResult
import com.isupatches.android.wisefy.signal.os.adapters.Android30SignalAdapter
import com.isupatches.android.wisefy.signal.os.adapters.DefaultSignalAdapter

/**
 * An internal Wisefy delegate for signal strength functionality.
 *
 * @param assertions The [WisefyAssertions] instance to use
 * @param logger The [WisefyLogger] instance to use
 * @param sdkUtil The [SdkUtil] instance to use
 * @param wifiManager The WifiManager instance to use
 *
 * @see SignalDelegate
 * @see SdkUtil
 * @see WisefyAssertions
 * @see WisefyLogger
 *
 * @author Patches Barrett
 * @since 07/2022, version 5.0.0
 */
class WisefySignalDelegate(
    assertions: WisefyAssertions,
    logger: WisefyLogger,
    sdkUtil: SdkUtil,
    wifiManager: WifiManager,
    private val adapter: SignalApi = when {
        sdkUtil.isAtLeastR() -> Android30SignalAdapter(wifiManager, logger, assertions)
        else -> DefaultSignalAdapter(logger, assertions)
    }
) : SignalDelegate {

    companion object {
        private const val LOG_TAG = "WisefySignalDelegate"
    }

    init {
        logger.d(LOG_TAG, "WisefySignalDelegate adapter is: ${adapter::class.java.simpleName}")
    }

    override fun calculateSignalLevel(request: CalculateSignalLevelRequest): CalculateSignalLevelResult {
        return adapter.calculateSignalLevel(request)
    }

    override fun compareSignalLevel(request: CompareSignalLevelRequest): CompareSignalLevelResult {
        return adapter.compareSignalLevel(request)
    }
}
