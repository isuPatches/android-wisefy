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
package com.isupatches.android.wisefy.savednetworks.entities

import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiNetworkSuggestion

/**
 * A set of classes and objects that are used to represent a saved network on the device.
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
sealed class SavedNetworkData {

    /**
     * A data representation of a network configuration from Android OS level returns.
     *
     * @property value The raw value of the saved network as a configuration from the Android OS
     *
     * @see SavedNetworkData
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    data class Configuration(val value: WifiConfiguration) : SavedNetworkData()

    /**
     * A data representation of a network suggestion from Android OS level returns.
     *
     * @property value The raw value of the saved network as a suggestion from the Android OS
     *
     * @see SavedNetworkData
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    data class Suggestion(val value: WifiNetworkSuggestion) : SavedNetworkData()
}
