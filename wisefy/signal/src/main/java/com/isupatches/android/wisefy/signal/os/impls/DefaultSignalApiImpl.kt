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
package com.isupatches.android.wisefy.signal.os.impls

import android.net.wifi.WifiManager
import com.isupatches.android.wisefy.core.constants.DeprecationMessages
import com.isupatches.android.wisefy.core.logging.WisefyLogger
import com.isupatches.android.wisefy.signal.os.apis.DefaultSignalApi

/**
 * A default implementation for signal strength functionality through the Android OS.
 *
 * @property logger The [WisefyLogger] instance to use
 *
 * @see DefaultSignalApi
 * @see WisefyLogger
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
internal class DefaultSignalApiImpl(
    private val logger: WisefyLogger
) : DefaultSignalApi {

    @Deprecated(
        message = DeprecationMessages.Signal.CALCULATE_BARS,
        replaceWith = ReplaceWith("this.calculateBars(rssiLevel)")
    )
    override fun calculateSignalLevel(rssi: Int, desiredNumberOfBars: Int): Int {
        @Suppress("Deprecation")
        val result = WifiManager.calculateSignalLevel(rssi, desiredNumberOfBars)
        logger.d(LOG_TAG, "Result from calculateSignalLevel: $result")
        return result
    }

    override fun compareSignalLevel(rssi1: Int, rssi2: Int): Int {
        val result = WifiManager.compareSignalLevel(rssi1, rssi2)
        logger.d(LOG_TAG, "Result from compareSignalLevel: $result")
        return result
    }

    companion object {
        private const val LOG_TAG = "DefaultSignalApiImpl"
    }
}
