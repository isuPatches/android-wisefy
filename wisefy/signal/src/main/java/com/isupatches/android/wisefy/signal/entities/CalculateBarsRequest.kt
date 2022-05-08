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
package com.isupatches.android.wisefy.signal.entities

import android.os.Build
import androidx.annotation.RequiresApi

/**
 * A set of classes and objects that are used to represent requests to calculate the number of signal bars
 * based on an RSSI level.
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
sealed class CalculateBarsRequest {

    /**
     * A data representation of a request on devices below Android 30 to calculate the number of signal bars
     * based on an RSSI level and a given number for the desired amount of bars.
     *
     * @property rssiLevel The RSSI level of the network
     * @property targetNumberOfBars The desired number of bars
     *
     * @see CalculateBarsRequest
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    data class BelowAndroid30(val rssiLevel: Int, val targetNumberOfBars: Int) : CalculateBarsRequest()

    /**
     * A data representation of a request on devices above Android 30 to calculate the number of signal bars
     * based on an RSSI level.
     *
     * @property rssiLevel The RSSI level of the network
     *
     * @see CalculateBarsRequest
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresApi(Build.VERSION_CODES.R)
    data class Android30AndAbove(val rssiLevel: Int) : CalculateBarsRequest()
}
