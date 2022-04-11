import com.isupatches.android.wisefy.build.BuildVersions
import com.isupatches.android.wisefy.build.Dependencies
import com.isupatches.android.wisefy.build.Versions
import com.isupatches.android.wisefy.build.dagger
import com.isupatches.android.wisefy.build.navigation
import java.util.Properties

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
}

val keystoreProperties: Properties = Properties()
val keystoreFile: File = rootProject.file("keystore.properties")
if (keystoreFile.exists()) {
    keystoreFile.inputStream().use { keystoreProperties.load(it) }
}

android {
    compileSdk = BuildVersions.COMPILE_SDK
    buildToolsVersion = BuildVersions.BUILD_TOOLS

    defaultConfig {
        applicationId = "com.isupatches.android.wisefy.sample"

        minSdk = BuildVersions.MIN_SDK
        targetSdk = BuildVersions.TARGET_SDK

        versionCode = BuildVersions.MODULE_VERSION_CODE
        versionName = BuildVersions.MODULE_VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        getByName("debug") {
            storeFile = file(keystoreProperties.getProperty("app.debug.keystore_location"))
            keyAlias = keystoreProperties.getProperty("app.debug.key_alias")
            storePassword = System.getenv("APP_DEBUG_PASSWORD") ?: keystoreProperties.getProperty("app.debug.password")
            keyPassword = System.getenv("APP_DEBUG_PASSWORD") ?: keystoreProperties.getProperty("app.debug.password")
        }

        create("release") {
            storeFile = file(keystoreProperties.getProperty("app.release.keystore_location"))
            keyAlias = keystoreProperties.getProperty("app.release.key_alias")
            storePassword = keystoreProperties.getProperty("app.release.password")
            keyPassword = keystoreProperties.getProperty("app.release.password")
        }
    }

    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
            isTestCoverageEnabled = true
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "$rootDir/proguard/r8-app-debug.pro"
            )
            testProguardFile(file("$rootDir/proguard/r8-app-test.pro"))
            signingConfig = signingConfigs.getByName("debug")
        }

        release {
            isTestCoverageEnabled = false
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
        viewBinding = true
    }

    testCoverage {
        jacocoVersion = Versions.JACOCO
    }

    lint {
        isCheckAllWarnings = true
        isShowAll = true
        isExplainIssues = true
        isAbortOnError = true
        isWarningsAsErrors = true
        disable("UnusedIds", "ConvertToWebp")
    }
}

dependencies {
    /*
     * Set of source code platform imports
     */
//    implementation(platform(project(":wisefy:platform")))
//    implementation(project(":wisefy:core"))
//    implementation(project(":wisefy"))
//    implementation(project(":wisefy:accesspoints"))
//    implementation(project(":wisefy:addnetwork"))
//    implementation(project(":wisefy:frequency"))
//    implementation(project(":wisefy:networkconnection"))
//    implementation(project(":wisefy:networkconnectionstatus"))
//    implementation(project(":wisefy:networkinfo"))
//    implementation(project(":wisefy:removenetwork"))
//    implementation(project(":wisefy:savednetworks"))
//    implementation(project(":wisefy:security"))
//    implementation(project(":wisefy:signal"))
//    implementation(project(":wisefy:wifi"))

    /*
     * Set of release artifact platform imports
     */
    implementation(platform("com.isupatches.android.wisefy:platform:5.0.0-RC3"))
    implementation("com.isupatches.android.wisefy:core")
    implementation("com.isupatches.android.wisefy:wisefy")
    implementation("com.isupatches.android.wisefy:accesspoints")
    implementation("com.isupatches.android.wisefy:addnetwork")
    implementation("com.isupatches.android.wisefy:frequency")
    implementation("com.isupatches.android.wisefy:networkconnection")
    implementation("com.isupatches.android.wisefy:networkconnectionstatus")
    implementation("com.isupatches.android.wisefy:networkinfo")
    implementation("com.isupatches.android.wisefy:removenetwork")
    implementation("com.isupatches.android.wisefy:savednetworks")
    implementation("com.isupatches.android.wisefy:security")
    implementation("com.isupatches.android.wisefy:signal")
    implementation("com.isupatches.android.wisefy:wifi")

    implementation(Dependencies.VIEWGLU)

    // AndroidX
    implementation(Dependencies.AndroidX.APPCOMPAT)
    implementation(Dependencies.AndroidX.CONSTRAINT_LAYOUT)
    implementation(Dependencies.AndroidX.CORE_KTX)
    navigation()

    // Koltin
    implementation(Dependencies.Kotlin.STD_LIB)

    // Google
    implementation(Dependencies.Google.MATERIAL)

    // Dependency Injection
    dagger()
}
