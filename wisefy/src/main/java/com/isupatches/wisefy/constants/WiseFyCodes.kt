/*
 * Copyright 2018 Patches Klinefelter
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
@file:JvmName("WiseFyCodes")
package com.isupatches.wisefy.constants

import androidx.annotation.IntDef

/**
 * A constant that denotes a successful operation within WiseFy.
 *
 * @author Patches
 * @since 3.0
 */
const val DEFAULT_PRECHECK_RETURN_CODE: Int = 0

/**
 * A constant that denotes a parameter was null or empty ("")/ length 0.
 *
 * @author Patches
 * @since 3.0
 */
const val MISSING_PARAMETER: Int = -1000

/**
 * A constant that denotes that a network is already a saved configuration.
 *
 * @author Patches
 * @since 3.0
 */
const val NETWORK_ALREADY_CONFIGURED: Int = -1002

/**
 * Interface to avoid magic numbers when handling internal WiseFy codes.
 *
 * @author Patches
 * @since 3.0
 */
@Retention(AnnotationRetention.SOURCE)
@IntDef(
    DEFAULT_PRECHECK_RETURN_CODE,
    MISSING_PARAMETER,
    NETWORK_ALREADY_CONFIGURED
)
annotation class WiseFyCode
