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

object ErrorMessages {

    object AddNetwork {
        object ActivityResultLauncher {
            const val USED_PRE_ANDROID_30 =
                "Adding a network using ActivityResultLauncher is not available until Android R"
            const val NOT_USED_ANDROID_30 =
                "Adding a network should be done with an ActivityResultLauncher on Android R+"
        }

        object WPA3Network {
            const val PRE_ANDROID_29 = "WPA3 networks are not supported until Android Q"
        }
    }

    object Signal {
        const val CALCULATE_BARS_ANDROID_30 = "calculateBars(rssiLevel: Int): Int should be used for Android R"
        const val CALCULATE_BARS_LEGACY = "calculateBars(rssiLevel: Int): Int is not available until Android R"
    }
}
