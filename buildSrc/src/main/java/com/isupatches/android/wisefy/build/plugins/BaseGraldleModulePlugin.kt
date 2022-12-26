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
package com.isupatches.android.wisefy.build.plugins

import com.android.build.gradle.LibraryExtension
import com.isupatches.android.wisefy.build.BuildVersions
import com.isupatches.android.wisefy.build.Dependencies
import com.isupatches.android.wisefy.build.DependencyConstants.IMPLEMENTATION
import com.isupatches.android.wisefy.build.Versions
import java.io.File
import java.io.FileInputStream
import java.util.Locale
import java.util.Properties
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.LockMode
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

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

            compileSdk = BuildVersions.COMPILE_SDK
            buildToolsVersion = BuildVersions.BUILD_TOOLS

            defaultConfig {
                minSdk = BuildVersions.MIN_SDK
                targetSdk = BuildVersions.TARGET_SDK

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
                    enableUnitTestCoverage = true
                    enableAndroidTestCoverage = true
                    isMinifyEnabled = false
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "${target.rootDir}/proguard/r8-lib-debug.pro"
                    )
                    testProguardFile("${target.rootDir}/proguard/r8-lib-test.pro")
                    consumerProguardFile("${target.rootDir}/proguard/r8-lib-consumer.pro")
                    signingConfig = signingConfigs.getByName("debug${target.name.capitalize(Locale.ROOT)}")
                }

                release {
                    enableUnitTestCoverage = false
                    enableAndroidTestCoverage = false
                    isMinifyEnabled = false
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "${target.rootDir}/proguard/r8-lib-release.pro"
                    )
                    consumerProguardFile("${target.rootDir}/proguard/r8-lib-consumer.pro")
                    signingConfig = signingConfigs.getByName("release${target.name.capitalize(Locale.ROOT)}")
                }
            }

            sourceSets {
                getByName("main") { jniLibs.srcDirs() }
            }

            lint {
                checkAllWarnings = true
                showAll = true
                explainIssues = true
                abortOnError = true
                warningsAsErrors = true
                disable += "UnusedIds"
            }

            testOptions {
                unitTests.isReturnDefaultValues = true
            }

            testCoverage {
                jacocoVersion = Versions.JACOCO
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_11
                targetCompatibility = JavaVersion.VERSION_11
            }
        }

        target.afterEvaluate {
            configurations.getByName("releaseRuntimeClasspath") {
                resolutionStrategy.activateDependencyLocking()
            }
            configurations.getByName("debugRuntimeClasspath") {
                resolutionStrategy.activateDependencyLocking()
            }
        }

        target.dependencyLocking {
            lockMode.set(LockMode.STRICT)
        }

        target.dependencies {
            add(IMPLEMENTATION, Dependencies.AndroidX.ANNOTATION)
            add(IMPLEMENTATION, Dependencies.AndroidX.APPCOMPAT)
            add(IMPLEMENTATION, Dependencies.Kotlin.STD_LIB)
        }
    }
}
