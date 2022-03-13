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
package com.isupatches.android.wisefy.sample.ui.remove

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.CHANGE_WIFI_STATE
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.removenetwork.callbacks.RemoveNetworkCallbacks
import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkResult
import com.isupatches.android.wisefy.sample.internal.scaffolding.BasePresenter
import com.isupatches.android.wisefy.sample.internal.scaffolding.Presenter
import javax.inject.Inject

internal interface RemoveNetworkPresenter : Presenter<RemoveNetworkFragment> {

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    fun removeNetwork(networkName: String)
}

@RemoveNetworkScope
internal class DefaultRemoveNetworkPresenter @Inject constructor(
    private val model: RemoveNetworkModel,
) : BasePresenter<RemoveNetworkFragment>(), RemoveNetworkPresenter {

    /*
     * Model call-throughs
     */

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun removeNetwork(networkName: String) {
        model.removeNetwork(
            networkName = networkName,
            callbacks = object : RemoveNetworkCallbacks {
                override fun onFailureRemovingNetwork(result: RemoveNetworkResult) {
                    doSafelyWithView { view ->
                        view.displayFailureRemovingNetwork(result)
                    }
                }

                override fun onNetworkNotFoundToRemove() {
                    doSafelyWithView { view ->
                        view.displayNetworkNotFountToRemove()
                    }
                }

                override fun onNetworkRemoved(result: RemoveNetworkResult) {
                    doSafelyWithView { view ->
                        view.displayNetworkRemoved(result)
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
