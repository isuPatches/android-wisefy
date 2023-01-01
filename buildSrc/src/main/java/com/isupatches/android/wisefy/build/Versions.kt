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
package com.isupatches.android.wisefy.build

object Versions {
    // Core tooling
    const val AGP: String = "7.3.1"
    const val KOTLIN: String = "1.7.20"
    const val COROUTINES: String = "1.6.4"

    // AndroidX
    const val ANDROIDX_ACTIVITY: String = "1.6.1"
    const val ANDROIDX_ANNOTATION: String = "1.5.0"
    const val ANDROIDX_APPCOMPAT: String = "1.5.1"
    const val ANDROIDX_COMPOSE_ANIMATION: String = "1.3.2"
    const val ANDROIDX_COMPOSE_COMPILER: String = "1.3.2"
    const val ANDROIDX_COMPOSE_MATERIAL: String = "1.3.1"
    const val ANDROIDX_COMPOSE_UI: String = "1.3.2"
    const val ANDROIDX_CORE_KTX: String = "1.9.0"
    const val ANDROIDX_DATA_STORE: String = "1.0.0"
    const val ANDROIDX_LIFECYCLE: String = "2.5.1"
    const val ANDROIDX_NAVIGATION: String = "2.5.3"

    // Dependency Injection
    const val DAGGER: String = "2.44.1"

    // Static Analysis
    const val CPD: String = "3.3"
    const val DETEKT: String = "1.21.0"
    const val DEXCOUNT: String = "3.1.0"
    const val KOTLINTER_PLUGIN: String = "3.12.0"

    // Documentation
    const val DOKKA: String = "1.7.20"

    // Code Coverage
    const val JACOCO: String = "0.8.8"

    // Unit Tests
    const val KOTLIN_TEST: String = "1.6.4"
    const val JUNIT: String = "4.13.2"
    const val MOCKITO: String = "4.11.0"

    // Instrumentation Tests
    const val ANDROIDX_TEST_RULES: String = "1.5.0"
    const val ANDROIDX_TEST_RUNNER: String = "1.5.1"
}
