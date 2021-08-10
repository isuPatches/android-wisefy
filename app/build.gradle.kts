import com.isupatches.android.wisefy.build.BuildVersions
import com.isupatches.android.wisefy.build.Dependencies
import com.isupatches.android.wisefy.build.Versions
import com.isupatches.android.wisefy.build.dagger
import com.isupatches.android.wisefy.build.debug
import com.isupatches.android.wisefy.build.navigation
import com.isupatches.android.wisefy.build.release
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
    compileSdkVersion(BuildVersions.COMPILE_SDK)
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
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules-debug.pro")
            testProguardFile(file("proguard-rules-test.pro"))
            signingConfig = signingConfigs.getByName("debug")
        }

        release {
            isTestCoverageEnabled = false
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules-release.pro")
            signingConfig = signingConfigs.getByName("release")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "${JavaVersion.VERSION_1_8}"
    }

    buildFeatures {
        viewBinding = true
    }

    jacoco {
        version = Versions.JACOCO
    }

    lintOptions {
        isCheckAllWarnings = true
        isShowAll = true
        isExplainIssues = true
        isAbortOnError = true
        isWarningsAsErrors = true
        disable("UnusedIds")
    }
}

dependencies {
    /*
     * Toggle these to test release binary vs. source code
     */
    implementation(project(":wisefy"))
//    implementation("com.isupatches.android:wisefy:5.0.0-RC1") {
//        isChanging = true
//    }

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