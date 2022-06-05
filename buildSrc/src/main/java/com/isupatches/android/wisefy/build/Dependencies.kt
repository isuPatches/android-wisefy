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

object Dependencies {

    object AndroidX {
        const val ANNOTATION: String = "androidx.annotation:annotation:${Versions.ANDROIDX_ANNOTATION}"
        const val APPCOMPAT: String = "androidx.appcompat:appcompat:${Versions.ANDROIDX_APPCOMPAT}"
        const val CONSTRAINT_LAYOUT: String = "androidx.constraintlayout:constraintlayout:" +
            Versions.ANDROIDX_CONSTRAINT_LAYOUT
        const val CORE_KTX: String = "androidx.core:core-ktx:${Versions.ANDROIDX_CORE_KTX}"

        object Navigation {
            const val FRAGMENT: String = "androidx.navigation:navigation-fragment:${Versions.ANDROIDX_NAVIGATION}"
            const val UI: String ="androidx.navigation:navigation-ui:${Versions.ANDROIDX_NAVIGATION}"
        }
    }

    object Kotlin {
        const val STD_LIB: String = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.KOTLIN}"
        const val COROUTINES: String = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES}"
    }

    object Google {
        const val MATERIAL: String = "com.google.android.material:material:${Versions.GOOGLE_MATERIAL}"
    }

    object Dagger {
        const val CORE: String = "com.google.dagger:dagger:${Versions.DAGGER}"
        const val COMPILER: String = "com.google.dagger:dagger-compiler:${Versions.DAGGER}"
        const val HILT_ANDROID: String = "com.google.dagger:hilt-android:${Versions.DAGGER}"
        const val HILT_COMPILER: String = "com.google.dagger:hilt-compiler:${Versions.DAGGER}"
    }

    const val VIEWGLU: String = "com.isupatches.android:viewglu:${Versions.VIEWGLU_VERSION}"
}
