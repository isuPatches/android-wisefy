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
package com.isupatches.android.wisefy.build

@Suppress("StringLiteralDuplication")
object Versions {
    // Core tooling
    const val AGP: String = "7.2.1"
    const val KOTLIN: String = "1.6.10"
    const val COROUTINES: String = "1.6.1"

    // AndroidX
    const val ANDROIDX_ACTIVITY_COMPOSE = "1.4.0"
    const val ANDROIDX_ANNOTATION: String = "1.3.0"
    const val ANDROIDX_APPCOMPAT: String = "1.4.1"
    const val ANDROIDX_COMPOSE = "1.1.1"
    const val ANDROIDX_CONSTRAINT_LAYOUT: String = "2.1.3"
    const val ANDROIDX_CORE_KTX = "1.7.0"
    const val ANDROIDX_LIFECYCLE_VIEW_MODEL_COMPOSE = "2.4.1"
    const val ANDROIDX_NAVIGATION: String = "2.5.0-rc01"
    const val ANDROIDX_NAVIGATION_COMPOSE: String = "2.4.2"

    // Google
    const val GOOGLE_MATERIAL: String = "1.6.0"

    // Dependency Injection
    const val DAGGER: String = "2.42"

    // Static Analysis
    const val CPD: String = "3.2"
    const val DETEKT: String = "1.20.0"
    const val DEXCOUNT: String = "3.1.0"
    const val KTLINT_PLUGIN: String = "10.3.0"
    const val KTLINT: String = "0.45.2"

    // Documentation
    const val DOKKA: String = "1.6.21"

    // Code Coverage
    const val JACOCO: String = "0.8.8"
}
