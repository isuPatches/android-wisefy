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

import android.Manifest.permission.ACCESS_WIFI_STATE
import android.net.wifi.WifiConfiguration
import androidx.annotation.RequiresPermission

import com.isupatches.wisefy.callbacks.AddNetworkCallbacks
import com.isupatches.wisefysample.internal.base.BasePresenter
import com.isupatches.wisefysample.internal.util.RxSchedulersProvider

import javax.inject.Inject

internal class AddNetworkPresenter @Inject constructor(
    private val model: AddNetworkMvp.Model,
    rxSchedulersProvider: RxSchedulersProvider
) : BasePresenter<AddNetworkMvp.View>(rxSchedulersProvider), AddNetworkMvp.Presenter {

    private val addNetworkCallbacks by lazy {
        object : AddNetworkCallbacks {
            override fun networkAdded(newNetworkId: Int, networkConfig: WifiConfiguration) {
                displayNetworkAdded(newNetworkId, networkConfig)
            }

            override fun failureAddingNetwork(wifiManagerReturn: Int) {
                displayFailureAddingNetwork(wifiManagerReturn)
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
                displayWiseFyFailure(wisefyFailureCode)
            }
        }
    }

    /*
     * Model call-throughs
     */

    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun addOpenNetwork(ssid: String) {
        model.addOpenNetwork(ssid, addNetworkCallbacks)
    }

    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun addWEPNetwork(ssid: String, password: String) {
        model.addWEPNetwork(ssid, password, addNetworkCallbacks)
    }

    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun addWPA2Network(ssid: String, password: String) {
        model.addWPA2Network(ssid, password, addNetworkCallbacks)
    }

    /*
     * View callbacks
     */

    private fun displayFailureAddingNetwork(wifiManagerReturn: Int) {
        doSafelyWithView { view -> view.displayFailureAddingNetwork(wifiManagerReturn) }
    }

    private fun displayNetworkAdded(newNetworkId: Int, networkConfig: WifiConfiguration) {
        doSafelyWithView { view -> view.displayNetworkAdded(newNetworkId, networkConfig) }
    }
}
