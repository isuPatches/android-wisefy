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
package com.isupatches.android.wisefy.shared.entities

object DeprecationMessages {

    object Signal {
        const val CALCULATE_BARS = "Please replace calculateBars(rssiLevel: Int, targetNumberOfBars: Int) to " +
            "calculateBars(rssiLevel: Int)"
    }

    object Wifi {
        const val ENABLE = "Starting with Build.VERSION_CODES#Q, applications are not allowed to enable Wi-Fi."
        const val DISABLE = "Starting with Build.VERSION_CODES#Q, applications are not allowed to disable Wi-Fi."
    }
}
