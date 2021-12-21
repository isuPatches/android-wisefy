/*
 * Copyright 2021 Patches Klinefelter
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
package com.isupatches.android.wisefy.addnetwork.entities

import android.content.Intent
import android.os.Build
import androidx.activity.result.ActivityResultLauncher
import androidx.annotation.RequiresApi

sealed class AddOpenNetworkRequest {
    data class Ssid(
        val ssid: String
    ) : AddOpenNetworkRequest()

    @RequiresApi(Build.VERSION_CODES.R)
    data class SsidAndActivityResultLauncher(
        val ssid: String,
        val activityResultLauncher: ActivityResultLauncher<Intent>
    ) : AddOpenNetworkRequest()
}

sealed class AddWPA2NetworkRequest {
    data class SsidAndPassphrase(
        val ssid: String,
        val passphrase: String
    ) : AddWPA2NetworkRequest()

    @RequiresApi(Build.VERSION_CODES.R)
    data class SsidPassphraseAndActivityResultLauncher(
        val ssid: String,
        val passphrase: String,
        val activityResultLauncher: ActivityResultLauncher<Intent>
    ) : AddWPA2NetworkRequest()
}

@RequiresApi(Build.VERSION_CODES.Q)
sealed class AddWPA3NetworkRequest {

    @RequiresApi(Build.VERSION_CODES.Q)
    data class SsidAndPassphrase(
        val ssid: String,
        val passphrase: String
    ) : AddWPA3NetworkRequest()

    @RequiresApi(Build.VERSION_CODES.R)
    data class SsidPassphraseAndActivityResultLauncher(
        val ssid: String,
        val passphrase: String,
        val activityResultLauncher: ActivityResultLauncher<Intent>
    ) : AddWPA3NetworkRequest()
}
