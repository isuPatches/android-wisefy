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
package com.isupatches.android.wisefy.sample.ui.add

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Intent
import android.os.Build
import androidx.activity.result.ActivityResultLauncher
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.addnetwork.entities.AddNetworkResult
import com.isupatches.android.wisefy.callbacks.AddNetworkCallbacks
import com.isupatches.android.wisefy.sample.internal.scaffolding.BasePresenter
import com.isupatches.android.wisefy.sample.internal.scaffolding.Presenter
import javax.inject.Inject

internal interface AddNetworkPresenter : Presenter<AddNetworkFragment> {

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun addOpenNetwork(ssid: String)

    @RequiresApi(Build.VERSION_CODES.R)
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun addOpenNetwork(
        ssid: String,
        activityResultLauncher: ActivityResultLauncher<Intent>
    )

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun addWPA2Network(ssid: String, passphrase: String)

    @RequiresApi(Build.VERSION_CODES.R)
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun addWPA2Network(
        ssid: String,
        passphrase: String,
        activityResultLauncher: ActivityResultLauncher<Intent>
    )

    @RequiresApi(Build.VERSION_CODES.Q)
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun addWPA3Network(ssid: String, passphrase: String)

    @RequiresApi(Build.VERSION_CODES.R)
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun addWPA3Network(
        ssid: String,
        passphrase: String,
        activityResultLauncher: ActivityResultLauncher<Intent>
    )
}

@AddNetworkScope
internal class DefaultAddNetworkPresenter @Inject constructor(
    private val model: AddNetworkModel,
) : BasePresenter<AddNetworkFragment>(), AddNetworkPresenter {

    private val addNetworkCallbacks = object : AddNetworkCallbacks {
        override fun onFailureAddingNetwork(result: AddNetworkResult) {
            doSafelyWithView { view ->
                view.displayFailureAddingNetwork(result)
            }
        }

        override fun onNetworkAdded(result: AddNetworkResult) {
            doSafelyWithView { view ->
                view.displayNetworkAdded(result)
            }
        }

        override fun onWisefyAsyncFailure(throwable: Throwable) {
            doSafelyWithView { view ->
                view.displayWisefyAsyncError(throwable)
            }
        }
    }

    /*
     * Model call-throughs
     */

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun addOpenNetwork(ssid: String) {
        model.addOpenNetwork(
            ssid = ssid,
            callbacks = addNetworkCallbacks
        )
    }

    @RequiresApi(Build.VERSION_CODES.R)
    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun addOpenNetwork(
        ssid: String,
        activityResultLauncher: ActivityResultLauncher<Intent>
    ) {
        model.addOpenNetwork(
            ssid = ssid,
            activityResultLauncher = activityResultLauncher,
            callbacks = addNetworkCallbacks
        )
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun addWPA2Network(ssid: String, passphrase: String) {
        model.addWPA2Network(
            ssid = ssid,
            passphrase = passphrase,
            callbacks = addNetworkCallbacks
        )
    }

    @RequiresApi(Build.VERSION_CODES.R)
    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun addWPA2Network(
        ssid: String,
        passphrase: String,
        activityResultLauncher: ActivityResultLauncher<Intent>
    ) {
        model.addWPA2Network(
            ssid = ssid,
            passphrase = passphrase,
            activityResultLauncher = activityResultLauncher,
            callbacks = addNetworkCallbacks
        )
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun addWPA3Network(ssid: String, passphrase: String) {
        model.addWPA3Network(
            ssid = ssid,
            passphrase = passphrase,
            callbacks = addNetworkCallbacks
        )
    }

    @RequiresApi(Build.VERSION_CODES.R)
    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun addWPA3Network(
        ssid: String,
        passphrase: String,
        activityResultLauncher: ActivityResultLauncher<Intent>
    ) {
        model.addWPA3Network(
            ssid = ssid,
            passphrase = passphrase,
            activityResultLauncher = activityResultLauncher,
            callbacks = addNetworkCallbacks
        )
    }
}