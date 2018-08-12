/*
 * Copyright 2018 Patches Klinefelter
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
package com.isupatches.wisefy.utils

import com.isupatches.wisefy.constants.QUOTE

/**
 * Used internally to convert an SSID into an acceptable format to create a WifiConfiguration.
 *
 * @param ssid The SSID to format
 *
 * @return String - The formatted SSID value
 */
internal fun convertSSIDForConfig(ssid: String): String = String.format("%s%s%s", QUOTE, ssid, QUOTE)
