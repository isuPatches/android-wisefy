/*
 * Copyright 2019 Patches Klinefelter
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
package com.isupatches.wisefysample.ui.add

import android.net.wifi.WifiConfiguration
import com.isupatches.wisefy.callbacks.AddNetworkCallbacks
import com.isupatches.wisefysample.internal.base.BaseMvp

@Suppress("UndocumentedPublicClass", "UndocumentedPublicFunction")
internal interface AddNetworkMvp {

    interface View : BaseMvp.View {
        fun displayFailureAddingNetwork(wifiManagerReturn: Int)
        fun displayNetworkAdded(newNetworkId: Int, networkConfig: WifiConfiguration)
    }

    interface Presenter : BaseMvp.Presenter<View> {
        fun addOpenNetwork(ssid: String)
        fun addWEPNetwork(ssid: String, password: String)
        fun addWPA2Network(ssid: String, password: String)
    }

    interface Model {
        fun addOpenNetwork(ssid: String, callbacks: AddNetworkCallbacks)
        fun addWEPNetwork(ssid: String, password: String, callbacks: AddNetworkCallbacks)
        fun addWPA2Network(ssid: String, password: String, callbacks: AddNetworkCallbacks)
    }
}
