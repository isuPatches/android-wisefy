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
@file:JvmName("Capabilities")
package com.isupatches.wisefy.constants

import android.support.annotation.StringDef

/**
 * Constant for EAP security capabilities.
 *
 * @author Patches
 * @since 3.0
 */
const val EAP: String = "EAP"

/**
 * Constant for PSK security capabilities.
 *
 * @author Patches
 * @since 3.0
 */
const val PSK: String = "PSK"

/**
 * Constant for WEP security capabilities.
 *
 * @author Patches
 * @since 3.0
 */
const val WEP: String = "WEP"

/**
 * Constant for WPA security capabilities.
 *
 * @author Patches
 * @since 3.0
 */
const val WPA: String = "WPA"

/**
 * Constant for WPA2 security capabilities.
 *
 * @author Patches
 * @since 3.0
 */
const val WPA2: String = "WPA2"

/**
 * Annotation to help avoid crazy strings when working with network security capabilities.
 *
 * @author Patches
 * @since 3.0
 */
@Retention(AnnotationRetention.SOURCE)
@StringDef(EAP, PSK, WEP, WPA, WPA2)
annotation class Capability
