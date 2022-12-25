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
package com.isupatches.android.wisefy.savednetworks.entities

import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiNetworkSuggestion
import android.os.Build
import androidx.annotation.RequiresApi

/**
 * A set of classes and objects that are used to represent a saved network on the device.
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
sealed class SavedNetworkData {

    /**
     * A data representation of a saved network configuration prior to Android Q.
     *
     * @property rawValue The raw value of the saved network as a configuration
     *
     * @see SavedNetworkData
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    data class Configuration(val rawValue: WifiConfiguration) : SavedNetworkData()

    /**
     * A data representation of a saved network suggestion starting at Android Q.
     *
     * @property rawValue The raw value of the saved network as a suggestion
     *
     * @see SavedNetworkData
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    @RequiresApi(Build.VERSION_CODES.Q)
    data class Suggestion(val rawValue: WifiNetworkSuggestion) : SavedNetworkData()
}
