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
package com.isupatches.android.wisefy.sample.ui.remove

import android.Manifest.permission.ACCESS_FINE_LOCATION
import androidx.annotation.RequiresPermission
import com.isupatches.wisefy.callbacks.RemoveNetworkCallbacks
import com.isupatches.wisefy.constants.WiseFyCode
import com.isupatches.android.wisefy.sample.internal.scaffolding.BasePresenter
import com.isupatches.android.wisefy.sample.internal.scaffolding.Presenter
import com.isupatches.android.wisefy.sample.internal.util.RxSchedulersProvider
import javax.inject.Inject

internal interface RemoveNetworkPresenter : Presenter<RemoveNetworkFragment> {

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun removeNetwork(networkName: String)
}

@RemoveNetworkScope
internal class DefaultRemoveNetworkPresenter @Inject constructor(
    private val model: RemoveNetworkModel,
    rxSchedulersProvider: RxSchedulersProvider
) : BasePresenter<RemoveNetworkFragment>(rxSchedulersProvider), RemoveNetworkPresenter {

    /*
     * Model call-throughs
     */

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun removeNetwork(networkName: String) {
        model.removeNetwork(networkName, object : RemoveNetworkCallbacks {
            override fun networkRemoved() {
                doSafelyWithView { view -> view.displayNetworkRemoved() }
            }

            override fun networkNotFoundToRemove() {
                doSafelyWithView { view -> view.displayNetworkNotFoundToRemove() }
            }

            override fun failureRemovingNetwork() {
                doSafelyWithView { view -> view.displayFailureRemovingNetwork() }
            }

            override fun wisefyFailure(@WiseFyCode wisefyFailureCode: Int) {
                displayWiseFyFailure(wisefyFailureCode)
            }
        })
    }
}
