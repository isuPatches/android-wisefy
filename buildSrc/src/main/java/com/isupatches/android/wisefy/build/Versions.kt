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
package com.isupatches.android.wisefy.build

@Suppress("StringLiteralDuplication")
object Versions {
    // Core tooling
    const val AGP: String = "4.2.2"
    const val KOTLIN: String = "1.5.21"
    const val COROUTINES: String = "1.5.1"

    // AndroidX
    const val ANDROIDX_ANNOTATION: String = "1.2.0"
    const val ANDROIDX_APPCOMPAT: String = "1.3.0"
    const val ANDROIDX_CONSTRAINT_LAYOUT: String = "2.0.4"
    const val ANDROIDX_CORE_KTX = "1.6.0"
    const val ANDROIDX_LIFECYCLE: String = "2.3.1"
    const val ANDROIDX_NAVIGATION: String = "2.3.5"

    // Google
    const val GOOGLE_MATERIAL: String = "1.4.0"

    // Dependency Injection
    const val DAGGER = "2.38.1"

    // Static Analysis
    const val CPD: String = "3.2"
    const val DETEKT: String = "1.17.1"
    const val DEXCOUNT: String = "2.1.0-RC01"
    const val KTLINT_PLUGIN: String = "10.1.0"
    const val KTLINT: String = "0.41.0"

    // Documentation
    const val DOKKA: String = "1.5.0"

    // Code Coverage
    const val JACOCO: String = "0.8.3"
}
