import com.isupatches.android.wisefy.build.BuildVersions
import com.isupatches.android.wisefy.build.Dependencies
import com.isupatches.android.wisefy.build.Versions
import com.isupatches.android.wisefy.build.compose
import com.isupatches.android.wisefy.build.dagger
import com.isupatches.android.wisefy.build.gitCommitHash
import com.isupatches.android.wisefy.build.navigation

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.isupatches.android.wisefy.sample"
    testNamespace = "com.isupatches.android.wisefy.sample.test"

    compileSdk = BuildVersions.COMPILE_SDK
    buildToolsVersion = BuildVersions.BUILD_TOOLS

    defaultConfig {
        applicationId = "com.isupatches.android.wisefy.sample"

        minSdk = BuildVersions.MIN_SDK
        targetSdk = BuildVersions.TARGET_SDK

        versionCode = BuildVersions.Sample.VERSION_CODE
        versionName = BuildVersions.Sample.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "GIT_HASH", "\"${gitCommitHash()}\"")

        buildConfigField("String", "WISEFY_CORE_VERSION","\"${BuildVersions.WISEFY_CORE_VERSION}\"")
        buildConfigField("String", "WISEFY_ACCESS_POINTS_VERSION","\"${BuildVersions.WISEFY_ACCESS_POINTS_VERSION}\"")
        buildConfigField("String", "WISEFY_ADD_NETWORK_VERSION","\"${BuildVersions.WISEFY_ADD_NETWORK_VERSION}\"")
        buildConfigField("String", "WISEFY_NETWORK_CONNECTION_VERSION","\"${BuildVersions.WISEFY_NETWORK_CONNECTION_VERSION}\"")
        buildConfigField("String", "WISEFY_NETWORK_INFO_VERSION","\"${BuildVersions.WISEFY_NETWORK_INFO_VERSION}\"")
        buildConfigField("String", "WISEFY_REMOVE_NETWORK_VERSION","\"${BuildVersions.WISEFY_REMOVE_NETWORK_VERSION}\"")
        buildConfigField("String", "WISEFY_SAVED_NETWORKS_VERSION","\"${BuildVersions.WISEFY_SAVED_NETWORKS_VERSION}\"")
        buildConfigField("String", "WISEFY_SIGNAL_VERSION","\"${BuildVersions.WISEFY_SIGNAL_VERSION}\"")
        buildConfigField("String", "WISEFY_WIFI_VERSION","\"${BuildVersions.WISEFY_WIFI_VERSION}\"")
        buildConfigField("String", "WISEFY_VERSION", "\"${BuildVersions.WISEFY_VERSION}\"")
        buildConfigField("String", "WISEFY_KTX_VERSION", "\"${BuildVersions.WISEFY_KTX_VERSION}\"")
    }

    signingConfigs {
        create("release") {
            storeFile = File("$rootDir/keystores/wisefy-sample-release.jks")
            keyAlias = System.getenv("WISEFY_SAMPLE_RELEASE_KEY_ALIAS")
            storePassword = System.getenv("WISEFY_SAMPLE_RELEASE_PASSWORD")
            keyPassword = System.getenv("WISEFY_SAMPLE_RELEASE_PASSWORD")
        }
    }

    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
            enableUnitTestCoverage = true
            enableAndroidTestCoverage = true
            isMinifyEnabled = System.getenv("MINIFY_DEBUG_BUILDS").toBoolean()
            isShrinkResources = System.getenv("MINIFY_DEBUG_BUILDS").toBoolean()
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "$rootDir/proguard/r8-app-debug.pro"
            )
            testProguardFile(file("$rootDir/proguard/r8-app-test.pro"))
        }

        release {
            enableUnitTestCoverage = false
            enableAndroidTestCoverage = false
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "$rootDir/proguard/r8-app-release.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }

    buildFeatures {
        compose = true
    }

    testCoverage {
        jacocoVersion = Versions.JACOCO
    }

    lint {
        checkAllWarnings = true
        showAll = true
        explainIssues = true
        abortOnError = true
        warningsAsErrors = true
        disable += "UnusedIds"
        disable += "ConvertToWebp"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    afterEvaluate {
        configurations.getByName("releaseRuntimeClasspath") {
            resolutionStrategy.activateDependencyLocking()
        }
        configurations.getByName("debugRuntimeClasspath") {
            resolutionStrategy.activateDependencyLocking()
        }
    }

    dependencyLocking {
        lockMode.set(LockMode.STRICT)
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.ANDROIDX_COMPOSE_COMPILER
    }
}

dependencies {
    /*
     * This should be uncommented to run sample app directly against source code
     */
//    implementation(project(":wisefy:ktx"))

    /*
     * This should be uncommented to run sample app directly against release versions of wisefy / wisefy-ktx
     */
    implementation("com.isupatches.android.wisefy:ktx:5.0.0")

    // AndroidX
    compose()
    implementation(Dependencies.AndroidX.APPCOMPAT)
    implementation(Dependencies.AndroidX.CORE_KTX)
    implementation(Dependencies.AndroidX.DATA_STORE)
    navigation()

    // Koltin
    implementation(Dependencies.Kotlin.STD_LIB)

    // Dependency Injection
    dagger()
}
