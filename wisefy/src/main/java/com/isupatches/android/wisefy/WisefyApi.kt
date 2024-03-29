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
package com.isupatches.android.wisefy

import com.isupatches.android.wisefy.accesspoints.AccessPointsApi
import com.isupatches.android.wisefy.accesspoints.AccessPointsApiAsync
import com.isupatches.android.wisefy.addnetwork.AddNetworkApi
import com.isupatches.android.wisefy.addnetwork.AddNetworkApiAsync
import com.isupatches.android.wisefy.networkconnection.NetworkConnectionApi
import com.isupatches.android.wisefy.networkconnection.NetworkConnectionApiAsync
import com.isupatches.android.wisefy.networkinfo.NetworkInfoApi
import com.isupatches.android.wisefy.networkinfo.NetworkInfoApiAsync
import com.isupatches.android.wisefy.removenetwork.RemoveNetworkApi
import com.isupatches.android.wisefy.removenetwork.RemoveNetworkApiAsync
import com.isupatches.android.wisefy.savednetworks.SavedNetworkApi
import com.isupatches.android.wisefy.savednetworks.SavedNetworkApiAsync
import com.isupatches.android.wisefy.signal.SignalApi
import com.isupatches.android.wisefy.wifi.WifiApi
import com.isupatches.android.wisefy.wifi.WifiApiAsync

/**
 * The culmination of APIs that create Wisefy's public interface.
 *
 * @see AccessPointsApi
 * @see AccessPointsApiAsync
 * @see AddNetworkApi
 * @see AddNetworkApiAsync
 * @see NetworkConnectionApi
 * @see NetworkConnectionApiAsync
 * @see NetworkInfoApi
 * @see NetworkInfoApiAsync
 * @see RemoveNetworkApi
 * @see RemoveNetworkApiAsync
 * @see SavedNetworkApi
 * @see SavedNetworkApiAsync
 * @see SignalApi
 * @see WifiApi
 * @see WifiApiAsync
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
interface WisefyApi :
    AccessPointsApi,
    AccessPointsApiAsync,
    AddNetworkApi,
    AddNetworkApiAsync,
    NetworkConnectionApi,
    NetworkConnectionApiAsync,
    NetworkInfoApi,
    NetworkInfoApiAsync,
    RemoveNetworkApi,
    RemoveNetworkApiAsync,
    SavedNetworkApi,
    SavedNetworkApiAsync,
    SignalApi,
    WifiApi,
    WifiApiAsync {

    /**
     * The initialization function for Wisefy.  This is recommended to be called in the `onCreate` of the activity for
     * the application.
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    fun init()

    /**
     * The cleanup function for Wisefy.  This is recommended to be called in the `onDestroy` of the activity for the
     * application.
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    fun dump()
}
