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
package com.isupatches.android.wisefy.shared.base

import com.isupatches.android.wisefy.networkinfo.entities.CurrentNetworkData
import com.isupatches.android.wisefy.networkinfo.entities.CurrentNetworkInfoData

interface GetCurrentNetworkCallbacks : com.isupatches.android.wisefy.shared.base.BaseWisefyCallbacks {
    fun onNoCurrentNetwork()
    fun onCurrentNetworkRetrieved(currentNetwork: CurrentNetworkData)
}

interface GetCurrentNetworkInfoCallbacks : com.isupatches.android.wisefy.shared.base.BaseWisefyCallbacks {
    fun onNoCurrentNetworkInfo()
    fun onCurrentNetworkInfoRetrieved(currentNetworkInfo: CurrentNetworkInfoData)
}
