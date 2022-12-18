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

import android.os.Build
import androidx.annotation.RequiresApi

/**
 * A set of classes and objects that represent requests to add a network.
 *
 * @author Patches Klinefelter
 * @since 11/2022, version 5.0.0
 */
sealed class AddNetworkRequest {

    /**
     * A set of classes and objects that represent requests to add an open network.
     *
     * @property ssid The SSID of the open network to add
     * @property bssid The optional BSSID for the open network being added
     *
     * @see AddNetworkRequest
     *
     * @author Patches Klinefelter
     * @since 11/2022, version 5.0.0
     */
    data class Open(val ssid: String, val bssid: String? = null) : AddNetworkRequest()

    /**
     * A representation of a request to add a WPA2 network prior to Android 30.
     *
     * @property ssid The SSID of the WPA2 network to add
     * @property passphrase The password for the WPA2 network to add
     * @property bssid The optional BSSID for the WPA2 network being added
     *
     * @see AddNetworkRequest
     *
     * @author Patches Klinefelter
     * @since 11/2022, version 5.0.0
     */
    data class WPA2(val ssid: String, val passphrase: String, val bssid: String? = null) : AddNetworkRequest()

    /**
     * A representation of a request to add a WPA3 network prior to Android 30.
     *
     * @property ssid The SSID of the WPA3 network to add
     * @property passphrase The password for the WPA3 network to add
     * @property bssid The optional BSSID for the WPA3 network being added
     *
     * @see AddNetworkRequest
     *
     * @author Patches Klinefelter
     * @since 11/2022, version 5.0.0
     */
    @RequiresApi(Build.VERSION_CODES.Q)
    data class WPA3(val ssid: String, val passphrase: String, val bssid: String? = null) : AddNetworkRequest()
}
