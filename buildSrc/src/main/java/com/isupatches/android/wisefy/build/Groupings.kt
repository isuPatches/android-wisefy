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

import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.dagger() {
    add(DependencyConstants.IMPLEMENTATION, Dependencies.Dagger.CORE)
    add(DependencyConstants.KAPT, Dependencies.Dagger.COMPILER)
    add(DependencyConstants.IMPLEMENTATION, Dependencies.Dagger.ANDROID_SUPPORT)
    add(DependencyConstants.KAPT, Dependencies.Dagger.ANDROID_PROCESSOR)
}

fun DependencyHandler.lifecycle() {
    add(DependencyConstants.IMPLEMENTATION, Dependencies.AndroidX.Lifecycle.RUNTIME)
    add(DependencyConstants.KAPT, Dependencies.AndroidX.Lifecycle.COMPILER)
}

fun DependencyHandler.navigation() {
    add(DependencyConstants.IMPLEMENTATION, Dependencies.AndroidX.Navigation.FRAGMENT)
    add(DependencyConstants.IMPLEMENTATION, Dependencies.AndroidX.Navigation.UI)
}
