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
package com.isupatches.android.wisefy.build.plugins

import com.android.build.gradle.LibraryExtension
import com.isupatches.android.wisefy.build.BuildVersions
import com.isupatches.android.wisefy.build.debug
import com.isupatches.android.wisefy.build.Dependencies
import com.isupatches.android.wisefy.build.release
import com.isupatches.android.wisefy.build.DependencyConstants.IMPLEMENTATION
import com.isupatches.android.wisefy.build.Versions
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import java.io.File
import java.io.FileInputStream
import java.util.Locale
import java.util.Properties

class BaseGradleModulePlugin : Plugin<Project> {

    @Suppress("LongMethod")
    override fun apply(target: Project) {

        // WARNING: It is CRUCIALLY important to not just add every plugin every feature module MIGHT need. For example,
        // adding kotlin-kapt here would degrade build performance significantly. Only add those gradle-plugins that
        // EVERY feature module is CERTAIN to use.
        target.apply(plugin = "kotlin-android")

        target.configure<LibraryExtension> {

            val keystorePropertiesFile = target.file("../keystore.properties")
            val keystoreProperties = Properties()
            if (keystorePropertiesFile.exists()) {
                keystoreProperties.load(FileInputStream(keystorePropertiesFile))
            }

            compileSdkVersion(BuildVersions.COMPILE_SDK)
            buildToolsVersion = BuildVersions.BUILD_TOOLS

            defaultConfig {
                minSdkVersion(BuildVersions.MIN_SDK)
                targetSdkVersion(BuildVersions.TARGET_SDK)

                versionCode = BuildVersions.MODULE_VERSION_CODE
                versionName = BuildVersions.MODULE_VERSION_NAME

                vectorDrawables.useSupportLibrary = true

                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }

            signingConfigs {
                create("debug${target.name.capitalize(Locale.ROOT)}") {
                    storeFile = File(System.getenv("WISEFY_DEBUG_KEYSTORE_LOCATION") ?: keystoreProperties["wisefy.debug.keystore_location"].toString())
                    storePassword = System.getenv("WISEFY_DEBUG_PASSWORD") ?: keystoreProperties["wisefy.debug.password"].toString()
                    keyPassword = System.getenv("WISEFY_DEBUG_PASSWORD") ?: keystoreProperties["wisefy.debug.password"].toString()
                    keyAlias = System.getenv("WISEFY_DEBUG_KEY_ALIAS") ?: keystoreProperties["wisefy.debug.key_alias"].toString()
                }

                create("release${target.name.capitalize(Locale.ROOT)}") {
                    storeFile = File(System.getenv("WISEFY_RELEASE_KEYSTORE_LOCATION") ?: keystoreProperties["wisefy.release.keystore_location"].toString())
                    storePassword = System.getenv("WISEFY_RELEASE_PASSWORD") ?: keystoreProperties["wisefy.release.password"].toString()
                    keyPassword = System.getenv("WISEFY_RELEASE_PASSWORD") ?: keystoreProperties["wisefy.release.password"].toString()
                    keyAlias = System.getenv("WISEFY_RELEASE_KEY_ALIAS") ?: keystoreProperties["wisefy.release.key_alias"].toString()
                }
            }

            buildTypes {
                debug {
                    // Test coverage needs to be disabled to release -SNAPSHOT builds
                    isTestCoverageEnabled = true
                    isMinifyEnabled = false
                    proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
                    testProguardFile("proguard-rules-test.pro")
                    consumerProguardFile("consumer-rules.pro")
                    signingConfig = signingConfigs.getByName("debug${target.name.capitalize(Locale.ROOT)}")
                }

                release {
                    isTestCoverageEnabled = false
                    isMinifyEnabled = true
                    proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
                    consumerProguardFile("consumer-rules.pro")
                    signingConfig = signingConfigs.getByName("release${target.name.capitalize(Locale.ROOT)}")
                }
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_1_8
                targetCompatibility = JavaVersion.VERSION_1_8
            }

            sourceSets {
                getByName("main") { jni.srcDirs() }
            }

            lintOptions {
                isCheckAllWarnings = true
                isShowAll = true
                isExplainIssues = true
                isAbortOnError = true
                isWarningsAsErrors = true
                warning("UnusedIds") // Currently warnings for ViewBinding
            }

            testOptions {
                unitTests.isReturnDefaultValues = true
            }

            jacoco {
                version = Versions.JACOCO
            }
        }

        target.dependencies {
            add(IMPLEMENTATION, Dependencies.AndroidX.ANNOTATION)
            add(IMPLEMENTATION, Dependencies.Kotlin.STD_LIB)
        }
    }
}
