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
package com.isupatches.android.wisefy.core

import android.net.wifi.WifiInfo
import com.isupatches.android.wisefy.core.constants.QUOTE

/**
 * A convenience property to expose the SSID of a [WifiInfo] taking into account any SDK level considerations and with
 * stripping unnecessary quotes.
 *
 * @return String - The value of the [WifiInfo]'s SSID with quotes removed
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
val WifiInfo.ssidWithoutQuotes: String
    get() = ssid.replace(QUOTE, "")

/**
 * A convenience property to expose the BSSID of a [WifiInfo] taking into account any SDK level considerations and
 * with stripping unnecessary quotes.
 *
 * @return String - The value of the [WifiInfo]'s BSSID with quotes removed
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
val WifiInfo.bssidWithoutQuotes: String
    get() = bssid.replace(QUOTE, "")
