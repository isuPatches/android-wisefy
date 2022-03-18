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
package com.isupatches.android.wisefy.addnetwork.os.apis

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.CHANGE_WIFI_STATE
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.addnetwork.entities.AddNetworkResult

/**
 * An internal Android 30 specific API for adding networks through the Android OS.
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
internal interface Android30AddNetworkApi {

    /**
     * An API to add an open network for Android 30.
     *
     * @param ssid The SSID of the open network to add
     *
     * @see AddNetworkResult
     *
     * @return AddNetworkResult - The result of launching the intent to add an open network
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    fun addOpenNetwork(
        ssid: String,
        activityResultLauncher: ActivityResultLauncher<Intent>
    )

    /**
     * An API to add a WPA2 network for Android 30.
     *
     * @param ssid The SSID of the WPA2 network to add
     * @param passphrase The passphrase to authenticate with the WPA2 network
     *
     * @see AddNetworkResult
     *
     * @return AddNetworkResult - The result of launching the intent to add a WPA2 network
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    fun addWPA2Network(
        ssid: String,
        passphrase: String,
        activityResultLauncher: ActivityResultLauncher<Intent>
    )

    /**
     * An API to add a WPA3 network for Android 30.
     *
     * @param ssid The SSID of the WPA3 network to add
     * @param passphrase The passphrase to authenticate with the WPA3 network
     *
     * @see AddNetworkResult
     *
     * @return AddNetworkResult - The result of launching the intent to add a WPA3 network
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    fun addWPA3Network(
        ssid: String,
        passphrase: String,
        activityResultLauncher: ActivityResultLauncher<Intent>
    )
}
