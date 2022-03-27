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
package com.isupatches.android.wisefy.addnetwork.entities

import android.content.Intent
import android.os.Build
import androidx.activity.result.ActivityResultLauncher
import androidx.annotation.RequiresApi

/**
 * A set of classes that are used in requests to add a WPA2 network.
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
sealed class AddWPA2NetworkRequest {

    /**
     * A data representation of a request to add a WPA2 network.
     *
     * @property ssid The SSID of the WPA2 network to add
     * @property passphrase The password for the WPA2 network to add
     *
     * @see AddWPA2NetworkRequest
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    data class Default(
        val ssid: String,
        val passphrase: String
    ) : AddWPA2NetworkRequest()

    /**
     * A data representation of a request to add a WPA2 network on Android 30 and above devices.
     *
     * @property ssid The SSID of the WPA2 network to add
     * @property passphrase The password for the WPA2 network to add
     * @property launcher The activity result launcher for the request to add a WPA2 network
     *
     * @see AddWPA2NetworkRequest
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresApi(Build.VERSION_CODES.R)
    data class Android30OrAbove(
        val ssid: String,
        val passphrase: String,
        val launcher: ActivityResultLauncher<Intent>
    ) : AddWPA2NetworkRequest()
}
