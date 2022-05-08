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
package com.isupatches.android.wisefy.core.util

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast

/**
 * An interface that helps the library determine the SDK level of the device.
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
interface SdkUtil {

    /**
     * A function that will check whether the device is on at least Android P.
     *
     * @return Boolean - True if the device's SDK is at least Android P, other false
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    fun isAtLeastP(): Boolean

    /**
     * A function that will check whether the device is on at least Android Q.
     *
     * @return Boolean - True if the device's SDK is at least Android Q, other false
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    fun isAtLeastQ(): Boolean

    /**
     * A function that will check whether the device is on at least Android R.
     *
     * @return Boolean - True if the device's SDK is at least Android R, other false
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    fun isAtLeastR(): Boolean

    /**
     * A function that will check whether the device is on at least Android S.
     *
     * @return Boolean - True if the device's SDK is at least Android S, other false
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    fun isAtLeastS(): Boolean
}

/**
 * A default implementation of [SdkUtil] that helps the library determine the SDK level of the device.
 *
 * @see SdkUtil
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
class SdkUtilImpl : SdkUtil {
    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.P)
    override fun isAtLeastP(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.P

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.Q)
    override fun isAtLeastQ(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.R)
    override fun isAtLeastR(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.R

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
    override fun isAtLeastS(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
}
