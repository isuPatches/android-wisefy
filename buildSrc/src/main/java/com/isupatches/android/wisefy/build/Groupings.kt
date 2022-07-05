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

fun DependencyHandler.compose() {
    add(DependencyConstants.IMPLEMENTATION, Dependencies.AndroidX.Compose.ANIMATION)
    add(DependencyConstants.IMPLEMENTATION, Dependencies.AndroidX.Compose.MATERIAL)
    add(DependencyConstants.IMPLEMENTATION, Dependencies.AndroidX.Compose.MATERIAL_ICONS_EXTENDED)
    add(DependencyConstants.IMPLEMENTATION, Dependencies.AndroidX.Compose.UI)
    add(DependencyConstants.IMPLEMENTATION, Dependencies.AndroidX.Compose.UI_TOOLING_PREVIEW)

    add(DependencyConstants.DEBUG_IMPLEMENTATION, Dependencies.AndroidX.Compose.UI_TOOLING)

    add(DependencyConstants.IMPLEMENTATION, Dependencies.AndroidX.Activity.COMPOSE)
    add(DependencyConstants.IMPLEMENTATION, Dependencies.AndroidX.Lifecycle.VIEW_MODEL_COMPOSE)
    add(DependencyConstants.IMPLEMENTATION, Dependencies.AndroidX.Navigation.COMPOSE)
}

fun DependencyHandler.dagger() {
    add(DependencyConstants.IMPLEMENTATION, Dependencies.Dagger.CORE)
    add(DependencyConstants.KAPT, Dependencies.Dagger.COMPILER)
    add(DependencyConstants.IMPLEMENTATION, Dependencies.Dagger.HILT_ANDROID)
    add(DependencyConstants.KAPT, Dependencies.Dagger.HILT_COMPILER)
}

fun DependencyHandler.navigation() {
    add(DependencyConstants.IMPLEMENTATION, Dependencies.AndroidX.Navigation.FRAGMENT)
    add(DependencyConstants.IMPLEMENTATION, Dependencies.AndroidX.Navigation.UI)
}
