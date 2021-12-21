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
package com.isupatches.android.wisefy.util

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast

internal interface SdkUtil {
    fun isAtLeastP(): Boolean
    fun isAtLeastQ(): Boolean
    fun isAtLeastR(): Boolean
    fun isAtLeastS(): Boolean
}

internal class SdkUtilImpl : SdkUtil {
    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.P)
    override fun isAtLeastP() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.P

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.Q)
    override fun isAtLeastQ() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.R)
    override fun isAtLeastR() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.R

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
    override fun isAtLeastS() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
}
