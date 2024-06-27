/*
 * Copyright 2022 Patches Barrett
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
package com.isupatches.android.wisefy.removenetwork

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.ACCESS_WIFI_STATE
import android.Manifest.permission.CHANGE_WIFI_STATE
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.removenetwork.callbacks.RemoveNetworkCallbacks
import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkRequest
import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkResult

/**
 * A set of synchronous APIs for removing a network.
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
interface RemoveNetworkApi {

    /**
     * An synchronous API to remove a network.
     *
     * @param request The details of the request to remove a network
     *
     * @see RemoveNetworkRequest
     * @see RemoveNetworkCallbacks
     *
     * @return RemoveNetworkResult - The result of removing a network
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE, CHANGE_WIFI_STATE])
    fun removeNetwork(request: RemoveNetworkRequest): RemoveNetworkResult
}
