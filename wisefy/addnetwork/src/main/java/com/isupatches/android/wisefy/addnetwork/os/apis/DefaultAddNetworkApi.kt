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

/**
 * A default internal API for adding networks through the Android OS.
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
internal interface DefaultAddNetworkApi {

    /**
     * A default API to add an open network.
     *
     * @param ssid The SSID of the open network to add
     *
     * @return Int - The result code of adding the open network
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    fun addOpenNetwork(ssid: String): Int

    /**
     * A default API to add a WPA2 network.
     *
     * @param ssid The SSID of the WPA2 network to add
     * @param passphrase The passphrase to authenticate with the WPA2 network
     *
     * @return Int - The result code of adding the WPA2 network
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    fun addWPA2Network(ssid: String, passphrase: String): Int
}
