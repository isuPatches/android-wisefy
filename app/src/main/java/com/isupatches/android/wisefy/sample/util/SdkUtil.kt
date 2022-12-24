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

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast
import javax.inject.Inject

internal interface SdkUtil {

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.Q)
    fun isAtLeastQ(): Boolean

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.R)
    fun isAtLeastR(): Boolean
}

internal class DefaultSdkUtil @Inject constructor() : SdkUtil {

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.Q)
    override fun isAtLeastQ() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.R)
    override fun isAtLeastR() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.R
}
