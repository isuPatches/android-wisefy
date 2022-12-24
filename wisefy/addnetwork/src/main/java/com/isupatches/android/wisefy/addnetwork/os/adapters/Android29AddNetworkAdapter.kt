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
package com.isupatches.android.wisefy.addnetwork.os.adapters

import android.os.Build
import androidx.annotation.RequiresApi
import com.isupatches.android.wisefy.addnetwork.AddNetworkApi
import com.isupatches.android.wisefy.addnetwork.entities.AddNetworkRequest
import com.isupatches.android.wisefy.addnetwork.entities.AddNetworkResult
import com.isupatches.android.wisefy.core.assertions.WisefyAssertions
import com.isupatches.android.wisefy.core.constants.AssertionMessages

/**
 * An Android 29 specific adapter for adding networks.
 *
 * @param assertions The [WisefyAssertions] instance to use
 *
 * @see AddNetworkApi
 * @see WisefyAssertions
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
@RequiresApi(Build.VERSION_CODES.Q)
internal class Android29AddNetworkAdapter(
    private val assertions: WisefyAssertions
) : AddNetworkApi {

    override fun addNetwork(request: AddNetworkRequest): AddNetworkResult {
        val message = AssertionMessages.AndroidQ.SAVED_NETWORK_FUNCTIONALITY_UNAVAILABLE_ANDROID_Q
        assertions.fail(message)
        return AddNetworkResult.Failure.Assertion(message)
    }
}
