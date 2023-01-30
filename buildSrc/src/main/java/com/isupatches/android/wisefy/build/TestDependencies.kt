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

object TestDependencies {
    const val JUNIT: String = "junit:junit:${Versions.JUNIT}"

    object Mockito {
        const val CORE: String = "org.mockito:mockito-core:${Versions.MOCKITO}"
        const val ANDROID: String = "org.mockito:mockito-android:${Versions.MOCKITO}"
    }

    object Kotlin {
        object Coroutines {
            const val TEST: String = "org.jetbrains.kotlinx:kotlinx-coroutines-test:" +
                Versions.KOTLIN_TEST
        }
    }

    object AndroidX {
        object Test {
            const val RUNNER: String = "androidx.test:runner:${Versions.ANDROIDX_TEST_RUNNER}"
            const val RULES: String = "androidx.test:rules:${Versions.ANDROIDX_TEST_RULES}"

            object Espresso {
                const val CORE: String = "androidx.test.espresso:espresso-core:${Versions.ANDROIDX_TEST_ESPRESSO}"
                const val INTENTS: String = "androidx.test.espresso:espresso-intents:${Versions.ANDROIDX_TEST_ESPRESSO}"

            }
        }
    }
}
