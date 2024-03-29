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
package com.isupatches.android.wisefy.sample.util

import android.content.Context
import com.isupatches.android.wisefy.BuildConfig
import com.isupatches.android.wisefy.Wisefy
import com.isupatches.android.wisefy.WisefyApi
import com.isupatches.android.wisefy.sample.logging.WisefySampleLogger

internal object WisefyFactory {

    private var wisefy: WisefyApi? = null

    private fun createWisefy(context: Context): WisefyApi {
        return Wisefy.Brains(
            context = context,
            throwOnAssertions = BuildConfig.DEBUG,
            logger = WisefySampleLogger
        ).getSmarts()
    }

    fun getInstance(context: Context) = wisefy ?: createWisefy(context)
}
