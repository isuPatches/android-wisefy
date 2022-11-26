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
package com.isupatches.android.wisefy.accesspoints

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.accesspoints.callbacks.GetAccessPointsCallbacks
import com.isupatches.android.wisefy.accesspoints.entities.GetAccessPointsQuery
import com.isupatches.android.wisefy.accesspoints.entities.GetAccessPointsResult
import com.isupatches.android.wisefy.accesspoints.os.adapters.DefaultAccessPointsAdapter
import com.isupatches.android.wisefy.core.coroutines.CoroutineDispatcherProvider
import com.isupatches.android.wisefy.core.coroutines.createBaseCoroutineExceptionHandler
import com.isupatches.android.wisefy.core.logging.WisefyLogger
import com.isupatches.android.wisefy.core.util.SdkUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * An internal Wisefy delegate for getting and searching for nearby access points.
 *
 * @param coroutineDispatcherProvider The [CoroutineDispatcherProvider] instance to use
 * @param scope The coroutine scope to use
 * @param logger The [WisefyLogger] instance to use
 * @param sdkUtil The [SdkUtil] instance to use
 * @param wifiManager The WifiManager instance to use
 *
 * @see AccessPointsDelegate
 * @see CoroutineDispatcherProvider
 * @see SdkUtil
 * @see WisefyLogger
 *
 * @author Patches Klinefelter
 * @since 11/2022, version 5.0.0
 */
class WisefyAccessPointsDelegate(
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider,
    private val scope: CoroutineScope,
    logger: WisefyLogger,
    sdkUtil: SdkUtil,
    wifiManager: WifiManager
) : AccessPointsDelegate {

    companion object {
        private const val LOG_TAG = "WisefyAccessPointsDelegate"
    }

    private val adapter = DefaultAccessPointsAdapter(wifiManager, logger, sdkUtil)

    init {
        logger.d(LOG_TAG, "WisefyAccessPointsDelegate adapter is: ${adapter::class.java.simpleName}")
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getAccessPoints(query: GetAccessPointsQuery): GetAccessPointsResult {
        return adapter.getAccessPoints(query)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getAccessPoints(
        query: GetAccessPointsQuery,
        callbacks: GetAccessPointsCallbacks?
    ) {
        scope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            val result = adapter.getAccessPoints(query)
            withContext(coroutineDispatcherProvider.main) {
                when (result) {
                    is GetAccessPointsResult.Empty -> callbacks?.onNoNearbyAccessPoints()
                    is GetAccessPointsResult.AccessPoints -> {
                        callbacks?.onNearbyAccessPointsRetrieved(result.value)
                    }
                }
            }
        }
    }
}
