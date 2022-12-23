/*
 * Copyright 2022 Patches Klinefelter
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
package com.isupatches.android.wisefy.networkconnectionstatus

import com.isupatches.android.wisefy.core.entities.NetworkConnectionStatus
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

internal class WisefyNetworkConnectionStatusManager private constructor(
    private val networkConnectionStatusMutex: Mutex
) {

    companion object {
        private var instance: WisefyNetworkConnectionStatusManager? = null

        fun getInstance(networkConnectionStatusMutex: Mutex): WisefyNetworkConnectionStatusManager {
            return instance ?: WisefyNetworkConnectionStatusManager(networkConnectionStatusMutex).also { manager ->
                this.instance = manager
            }
        }
    }

    private var networkConnectionStatus: NetworkConnectionStatus? = null

    suspend fun getNetworkConnectionStatus(): NetworkConnectionStatus? {
        networkConnectionStatusMutex.withLock {
            return networkConnectionStatus
        }
    }

    suspend fun setNetworkConnectionStatus(networkConnectionStatus: NetworkConnectionStatus) {
        networkConnectionStatusMutex.withLock {
            this.networkConnectionStatus = networkConnectionStatus
        }
    }

    suspend fun clear() {
        networkConnectionStatusMutex.withLock {
            networkConnectionStatus = null
        }
    }
}
