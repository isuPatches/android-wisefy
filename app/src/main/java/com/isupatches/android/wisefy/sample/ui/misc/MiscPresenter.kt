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
package com.isupatches.android.wisefy.sample.ui.misc

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.accesspoints.callbacks.GetNearbyAccessPointCallbacks
import com.isupatches.android.wisefy.accesspoints.entities.AccessPointData
import com.isupatches.android.wisefy.savednetworks.callbacks.GetSavedNetworksCallbacks
import com.isupatches.android.wisefy.frequency.callbacks.GetFrequencyCallbacks
import com.isupatches.android.wisefy.frequency.entities.FrequencyData
import com.isupatches.android.wisefy.networkinfo.callbacks.GetCurrentNetworkCallbacks
import com.isupatches.android.wisefy.networkinfo.callbacks.GetCurrentNetworkInfoCallbacks
import com.isupatches.android.wisefy.networkinfo.callbacks.GetIPCallbacks
import com.isupatches.android.wisefy.networkinfo.entities.CurrentNetworkData
import com.isupatches.android.wisefy.networkinfo.entities.CurrentNetworkInfoData
import com.isupatches.android.wisefy.networkinfo.entities.IPData
import com.isupatches.android.wisefy.sample.internal.scaffolding.BasePresenter
import com.isupatches.android.wisefy.sample.internal.scaffolding.Presenter
import com.isupatches.android.wisefy.savednetworks.entities.SavedNetworkData
import com.isupatches.android.wisefy.wifi.callbacks.DisableWifiCallbacks
import com.isupatches.android.wisefy.wifi.callbacks.EnableWifiCallbacks
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
            model.disableWifi(
                callbacks = object : DisableWifiCallbacks {
                    override fun onFailureDisablingWifi() {
                        doSafelyWithView { view ->
                            view.displayFailureDisablingWifi()
                        }
                    }

                    override fun onWifiDisabled() {
                        doSafelyWithView { view ->
                            view.displayWifiDisabled()
                        }
                    }

                    override fun onWisefyAsyncFailure(throwable: Throwable) {
                        doSafelyWithView { view ->
                            view.displayWisefyAsyncError(throwable)
                        }
                    }
                }
            )
        } else {
            doSafelyWithView { view -> view.displayAndroidQWifiMessage() }
        }
    }

    override fun enableWifi() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            model.enableWifi(
                callbacks = object : EnableWifiCallbacks {
                    override fun onFailureEnablingWifi() {
                        doSafelyWithView { view ->
                            view.displayFailureEnablingWifi()
                        }
                    }

                    override fun onWifiEnabled() {
                        doSafelyWithView { view ->
                            view.displayWifiEnabled()
                        }
                    }

                    override fun onWisefyAsyncFailure(throwable: Throwable) {
                        doSafelyWithView { view ->
                            view.displayWisefyAsyncError(throwable)
                        }
                    }
                }
            )
        } else {
            doSafelyWithView { view -> view.displayAndroidQWifiMessage() }
        }
    }

    override fun getCurrentNetwork() {
        model.getCurrentNetwork(
            callbacks = object : GetCurrentNetworkCallbacks {
                override fun onNoCurrentNetwork() {
                    doSafelyWithView { view ->
                        view.displayNoCurrentNetwork()
                    }
                }

                override fun onCurrentNetworkRetrieved(currentNetwork: CurrentNetworkData) {
                    doSafelyWithView { view ->
                        view.displayCurrentNetwork(currentNetwork)
                    }
                }

                override fun onWisefyAsyncFailure(throwable: Throwable) {
                    doSafelyWithView { view ->
                        view.displayWisefyAsyncError(throwable)
                    }
                }
            }
        )
    }

    override fun getCurrentNetworkInfo() {
        model.getCurrentNetworkInfo(
            callbacks = object : GetCurrentNetworkInfoCallbacks {
                override fun onNoCurrentNetworkInfo() {
                    doSafelyWithView { view ->
                        view.displayNoCurrentNetworkInfo()
                    }
                }

                override fun onCurrentNetworkInfoRetrieved(currentNetworkInfo: CurrentNetworkInfoData) {
                    doSafelyWithView { view ->
                        view.displayCurrentNetworkInfo(currentNetworkInfo)
                    }
                }

                override fun onWisefyAsyncFailure(throwable: Throwable) {
                    doSafelyWithView { view ->
                        view.displayWisefyAsyncError(throwable)
                    }
                }
            }
        )
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun getFrequency() {
        model.getFrequency(
            callbacks = object : GetFrequencyCallbacks {
                override fun onFailureRetrievingFrequency() {
                    doSafelyWithView { view ->
                        view.displayFailureRetrievingFrequency()
                    }
                }

                override fun onFrequencyRetrieved(frequency: FrequencyData) {
                    doSafelyWithView { view ->
                        view.displayFrequency(frequency)
                    }
                }

                override fun onWisefyAsyncFailure(throwable: Throwable) {
                    doSafelyWithView { view ->
                        view.displayWisefyAsyncError(throwable)
                    }
                }
            }
        )
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getIP() {
        model.getIP(
            callbacks = object : GetIPCallbacks {
                override fun onFailureRetrievingIP() {
                    doSafelyWithView { view ->
                        view.displayFailureRetrievingIP()
                    }
                }

                override fun onIPRetrieved(ip: IPData) {
                    doSafelyWithView { view ->
                        view.displayIP(ip)
                    }
                }

                override fun onWisefyAsyncFailure(throwable: Throwable) {
                    doSafelyWithView { view ->
                        view.displayWisefyAsyncError(throwable)
                    }
                }
            }
        )
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getNearbyAccessPoints() {
        model.getNearbyAccessPoints(
            callbacks = object : GetNearbyAccessPointCallbacks {
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
            }
        )
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getSavedNetworks() {
        model.getSavedNetworks(
            callbacks = object : GetSavedNetworksCallbacks {
                override fun onNoSavedNetworksFound() {
                    doSafelyWithView { view ->
                        view.displayNoSavedNetworksFound()
                    }
                }

                override fun onSavedNetworksRetrieved(savedNetworks: List<SavedNetworkData>) {
                    doSafelyWithView { view ->
                        view.displaySavedNetworks(savedNetworks)
                    }
                }

                override fun onWisefyAsyncFailure(throwable: Throwable) {
                    doSafelyWithView { view ->
                        view.displayWisefyAsyncError(throwable)
                    }
                }
            }
        )
    }
}
