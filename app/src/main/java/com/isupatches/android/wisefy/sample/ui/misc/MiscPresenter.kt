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
package com.isupatches.android.wisefy.sample.ui.misc

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.accesspoints.entities.AccessPointData
import com.isupatches.android.wisefy.callbacks.GetFrequencyCallbacks
import com.isupatches.android.wisefy.callbacks.GetNearbyAccessPointCallbacks
import com.isupatches.android.wisefy.sample.internal.scaffolding.BasePresenter
import com.isupatches.android.wisefy.sample.internal.scaffolding.Presenter
import javax.inject.Inject

internal interface MiscPresenter : Presenter<MiscFragment> {

    fun disableWifi()

    fun enableWifi()

    fun getCurrentNetwork()

    fun getCurrentNetworkInfo()

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun getFrequency()

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun getIP()

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun getNearbyAccessPoints()

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun getSavedNetworks()
}

@MiscScope
internal class DefaultMiscPresenter @Inject constructor(
    private val model: MiscModel,
) : BasePresenter<MiscFragment>(), MiscPresenter {

    /*
     * Model call-throughs
     */

    override fun disableWifi() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            model.disableWifi()
        } else {
            doSafelyWithView { view -> view.displayAndroidQWifiMessage() }
        }
    }

    override fun enableWifi() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            model.enableWifi()
        } else {
            doSafelyWithView { view -> view.displayAndroidQWifiMessage() }
        }
    }

    override fun getCurrentNetwork() {
        val currentNetwork = model.getCurrentNetwork()
        if (currentNetwork != null) {
            doSafelyWithView { view -> view.displayCurrentNetwork(currentNetwork) }
        } else {
            doSafelyWithView { view -> view.displayNoCurrentNetwork() }
        }
    }

    override fun getCurrentNetworkInfo() {
        val currentNetworkInfo = model.getCurrentNetworkInfo()
        if (currentNetworkInfo != null) {
            doSafelyWithView { view -> view.displayCurrentNetworkInfo(currentNetworkInfo) }
        } else {
            doSafelyWithView { view -> view.displayNoCurrentNetworkInfo() }
        }
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun getFrequency() {
        model.getFrequency(object : GetFrequencyCallbacks {
            override fun onFailureRetrievingFrequency() {
                doSafelyWithView { view -> view.displayFailureRetrievingFrequency() }
            }

            override fun onFrequencyRetrieved(frequency: Int) {
                doSafelyWithView { view -> view.displayFrequency(frequency) }
            }

            override fun onWisefyAsyncFailure(throwable: Throwable) {
                doSafelyWithView { view -> view.displayWisefyAsyncError(throwable) }
            }
        })
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getIP() {
        val ip = model.getIP()
        doSafelyWithView { view -> view.displayIP(ip) }
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getNearbyAccessPoints() {
        model.getNearbyAccessPoints(object : GetNearbyAccessPointCallbacks {
            override fun onNearbyAccessPointsRetrieved(accessPoints: List<AccessPointData>) {
                doSafelyWithView { view ->
                    view.displayNearbyAccessPoints(accessPoints)
                }
            }

            override fun onNoNearbyAccessPoints() {
                doSafelyWithView { view ->
                    view.displayNoAccessPointsFound()
                }
            }

            override fun onWisefyAsyncFailure(throwable: Throwable) {
                doSafelyWithView { view ->
                    view.displayWisefyAsyncError(throwable)
                }
            }
        })
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getSavedNetworks() {
        model.getSavedNetworks()
    }
}
