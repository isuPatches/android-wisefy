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

        object Lifecycle {
            const val RUNTIME: String = "androidx.lifecycle:lifecycle-runtime:${Versions.ANDROIDX_LIFECYCLE}"
            const val COMPILER: String = "androidx.lifecycle:lifecycle-compiler:${Versions.ANDROIDX_LIFECYCLE}"
        }

        object Navigation {
            const val FRAGMENT = "androidx.navigation:navigation-fragment:${Versions.ANDROIDX_NAVIGATION}"
            const val UI ="androidx.navigation:navigation-ui:${Versions.ANDROIDX_NAVIGATION}"
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
        const val CORE = "com.google.dagger:dagger:${Versions.DAGGER}"
        const val COMPILER = "com.google.dagger:dagger-compiler:${Versions.DAGGER}"
        const val ANDROID_SUPPORT = "com.google.dagger:dagger-android-support:${Versions.DAGGER}"
        const val ANDROID_PROCESSOR = "com.google.dagger:dagger-android-processor:${Versions.DAGGER}"
    }

    const val VIEWGLU = "com.isupatches.android:viewglu:${Versions.VIEWGLU_VERSION}"
}
