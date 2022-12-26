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
package com.isupatches.android.wisefy.signal.os.converters

import com.isupatches.android.wisefy.signal.entities.CompareSignalLevelResult

/**
 * A function that converts a raw Int result from WifiManager.compareSignalLevel into a [CompareSignalLevelResult].
 *
 * @receiver Int - The raw result from WifiManager.compareSignalLevel
 *
 * @see CompareSignalLevelResult
 *
 * @return CompareSignalLevelResult - A more readable variant of the result as any [CompareSignalLevelResult] type
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
internal fun Int.toCompareSignalLevelResult(): CompareSignalLevelResult {
    return when {
        this > 0 -> CompareSignalLevelResult.Success.FirstRSSIValueIsStronger(this)
        this < 0 -> CompareSignalLevelResult.Success.FirstRSSIValueIsWeaker(this)
        else -> CompareSignalLevelResult.Success.RSSIValuesAreEqual(0)
    }
}
