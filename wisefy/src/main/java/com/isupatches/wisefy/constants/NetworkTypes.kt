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
@file:JvmName("NetworkTypes")
package com.isupatches.wisefy.constants

import androidx.annotation.StringDef

/**
 * Constant for a mobile network.
 *
 * @author Patches
 * @since 3.0
 */
const val MOBILE: String = "MOBILE"

/**
 * Constant for a Wifi network.
 *
 * @author Patches
 * @since 3.0
 */
const val WIFI: String = "WIFI"

/**
 * Annotation to help avoid crazy strings when handling network types.
 *
 * @author Patches
 * @since 3.0
 */
@Retention(AnnotationRetention.SOURCE)
@StringDef(MOBILE, WIFI)
annotation class NetworkType
