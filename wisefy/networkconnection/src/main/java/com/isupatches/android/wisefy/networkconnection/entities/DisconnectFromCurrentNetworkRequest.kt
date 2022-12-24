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
package com.isupatches.android.wisefy.networkconnection.entities

import android.content.Context

/**
 * A set of classes that are used in requests to connect and disconnect from a network.
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
@Deprecated("")
sealed class DisconnectFromCurrentNetworkRequest {

    @Deprecated("")
    object Default : DisconnectFromCurrentNetworkRequest()

    @Deprecated("")
    data class Android29OrAbove(val context: Context) : DisconnectFromCurrentNetworkRequest()
}
