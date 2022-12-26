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
package com.isupatches.android.wisefy.networkconnection.os.adapters

import android.os.Build
import androidx.annotation.RequiresApi
import com.isupatches.android.wisefy.core.assertions.WisefyAssertions
import com.isupatches.android.wisefy.core.constants.AssertionMessages
import com.isupatches.android.wisefy.core.constants.DeprecationMessages
import com.isupatches.android.wisefy.core.logging.WisefyLogger
import com.isupatches.android.wisefy.networkconnection.NetworkConnectionApi
import com.isupatches.android.wisefy.networkconnection.entities.ChangeNetworkRequest
import com.isupatches.android.wisefy.networkconnection.entities.ChangeNetworkResult
import com.isupatches.android.wisefy.networkconnection.entities.ConnectToNetworkRequest
import com.isupatches.android.wisefy.networkconnection.entities.ConnectToNetworkResult
import com.isupatches.android.wisefy.networkconnection.entities.DisconnectFromCurrentNetworkRequest
import com.isupatches.android.wisefy.networkconnection.entities.DisconnectFromCurrentNetworkResult
import com.isupatches.android.wisefy.networkconnection.os.apis.Android29NetworkConnectionApi
import com.isupatches.android.wisefy.networkconnection.os.impls.Android29NetworkConnectionApiImpl

/**
 * An Android 29 or higher adapter for connecting to or disconnecting from a network.
 *
 * @param logger The [WisefyLogger] instance to use
 * @property assertions The [WisefyAssertions] instance to use
 * @property api The OS level API instance to use
 *
 * @see Android29NetworkConnectionApi
 * @see Android29NetworkConnectionApiImpl
 * @see NetworkConnectionApi
 * @see WisefyLogger
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
@RequiresApi(Build.VERSION_CODES.Q)
internal class Android29NetworkConnectionAdapter(
    logger: WisefyLogger,
    private val assertions: WisefyAssertions,
    private val api: Android29NetworkConnectionApi = Android29NetworkConnectionApiImpl(logger = logger)
) : NetworkConnectionApi {

    override fun changeNetwork(request: ChangeNetworkRequest): ChangeNetworkResult {
        api.openInternetConnectivityPanel(request.context)
        return ChangeNetworkResult.Success.InternetConnectivityPanelOpened
    }

    @Deprecated(DeprecationMessages.NetworkConnection.CONNECT_TO_NETWORK)
    override fun connectToNetwork(request: ConnectToNetworkRequest): ConnectToNetworkResult {
        val message = AssertionMessages.NetworkConnection.ConnectToNetwork.USED_ANDROID_Q_OR_HIGHER
        assertions.fail(message)
        return ConnectToNetworkResult.Failure.Assertion(message)
    }

    @Deprecated(DeprecationMessages.NetworkConnection.DISCONNECT_FROM_CURRENT_NETWORK)
    override fun disconnectFromCurrentNetwork(
        request: DisconnectFromCurrentNetworkRequest
    ): DisconnectFromCurrentNetworkResult {
        val message = AssertionMessages.NetworkConnection.DisconnectFromCurrentNetwork.USED_ANDROID_Q_OR_HIGHER
        assertions.fail(message)
        return DisconnectFromCurrentNetworkResult.Failure.Assertion(message)
    }
}
