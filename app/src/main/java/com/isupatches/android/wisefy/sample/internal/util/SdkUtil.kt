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
package com.isupatches.android.wisefy.sample.internal.util

import android.os.Build
import javax.inject.Inject

internal interface SdkUtil {

    fun isAtLeastQ(): Boolean
}

internal class SdkUtilImpl @Inject constructor() : SdkUtil {

    override fun isAtLeastQ() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
}
