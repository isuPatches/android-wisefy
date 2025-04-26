/*
 * Copyright (c) 2024. Patches Barrett
 *
 * Last modified: September 22, 2024
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

plugins {
    id(libs.plugins.wisefy.android.library.get().pluginId)
    id(libs.plugins.wisefy.android.publish.get().pluginId)
}

group = project.properties["groupId"] ?: error("Group ID is a required gradle property")
version = project.properties["version"] ?: error("Version is a required gradle property")

android {
    namespace = "com.isupatches.android.wisefy.wifi"
}

dependencies {
    implementation(project(":wisefy:core"))

    // AndroidX
    implementation(libs.androidx.annotation)

    // Kotlin
    implementation(libs.jetbrains.kotlinx.coroutines)

    // Unit Tests
    testImplementation(project(":testsupport"))
    testImplementation(testLibs.junit)
    testImplementation(testLibs.mockito.core)
    testImplementation(testLibs.kotlin.coroutines.test)

    // Instrumentation Tests
    androidTestImplementation(project(":testsupport"))
    androidTestImplementation(testLibs.junit)
    androidTestImplementation(testLibs.androidx.test.rules)
    androidTestImplementation(testLibs.androidx.test.runner)
    androidTestImplementation(testLibs.mockito.android)
    androidTestImplementation(testLibs.kotlin.coroutines.test)
    androidTestImplementation(testLibs.androidx.espresso.intents)
}
