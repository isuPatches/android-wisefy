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
package com.isupatches.android.wisefy.signal.entities

import android.os.Build
import androidx.annotation.RequiresApi

/**
 * A set of classes and objects that are used to represent requests to calculate the number of signal strength bars
 * based on the RSSI level of a network.
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
sealed class CalculateSignalLevelRequest {

    /**
     * A data representation of a request on pre-Android 30 devices to calculate the number of signal bars based on the
     * RSSI level of a network and the desired amount of bars.
     *
     * @property rssi The RSSI level of the network
     * @property desiredNumberOfBars The desired number of signal strength bars
     *
     * @see CalculateSignalLevelRequest
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    data class BelowAndroid30(val rssi: Int, val desiredNumberOfBars: Int) : CalculateSignalLevelRequest()

    /**
     * A data representation of a request on Android 30 or higher devices to calculate the number of signal strength
     * bars based on the RSSI level of a network.
     *
     * @property rssi The RSSI level of the network
     *
     * @see CalculateSignalLevelRequest
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    @RequiresApi(Build.VERSION_CODES.R)
    data class Android30AndAbove(val rssi: Int) : CalculateSignalLevelRequest()
}
