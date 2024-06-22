import java.io.ByteArrayOutputStream
import java.io.OutputStream
import java.util.Locale

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.dagger.hilt.android)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
}

android {
    namespace = "com.isupatches.android.wisefy.sample"

    compileSdk = libs.versions.sdk.compile.get().toInt()
    buildToolsVersion = libs.versions.build.tools.version.get()

    defaultConfig {
        applicationId = "com.isupatches.android.wisefy.sample"

        minSdk = libs.versions.sdk.min.get().toInt()
        targetSdk = libs.versions.sdk.target.get().toInt()

        versionCode = 18
        versionName = "5.0.0-RC3"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "GIT_HASH", "\"${gitCommitHash()}\"")
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
        buildConfig = true
    }

    testCoverage {
        jacocoVersion = libs.versions.jacoco.version.get()
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
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
        kotlinCompilerExtensionVersion = libs.versions.kotlin.compiler.version.get()
    }
}

/*
 * https://stackoverflow.com/questions/28498688/gradle-script-to-autoversion-and-include-the-commit-hash-in-android
 */
fun Project.gitCommitHash(): String {
    val stdout: OutputStream = ByteArrayOutputStream()
    exec {
        commandLine("git", "rev-parse", "--short", "HEAD")
        standardOutput = stdout
    }
    return stdout.toString().trim()
}

dependencies {
    /*
     * This should be uncommented to run sample app directly against a published release
     */
    implementation(platform("com.isupatches.android.wisefy:wisefy-bom:5.0.0-RC3"))

    /*
     * This should be uncommented to run sample app directly against the source BOM
     */
//    implementation(platform(project(":wisefy:bom")))

    implementation("com.isupatches.android.wisefy:accesspoints")
    implementation("com.isupatches.android.wisefy:addnetwork")
    implementation("com.isupatches.android.wisefy:wisefy")
    implementation("com.isupatches.android.wisefy:core")
    implementation("com.isupatches.android.wisefy:ktx")
    implementation("com.isupatches.android.wisefy:networkconnection")
    implementation("com.isupatches.android.wisefy:networkinfo")
    implementation("com.isupatches.android.wisefy:removenetwork")
    implementation("com.isupatches.android.wisefy:savednetworks")
    implementation("com.isupatches.android.wisefy:signal")
    implementation("com.isupatches.android.wisefy:wifi")

    // AndroidX
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.viewmodelcompose)
    implementation(libs.androidx.compose.animation)
    implementation(libs.bundles.androidx.compose.material)
    implementation(libs.bundles.androidx.compose.ui)
    debugImplementation(libs.androidx.compose.ui.tooling)

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.annotation)
    implementation(libs.androidx.corektx)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.bundles.androidx.navigation)

    // Koltin
    implementation(libs.jetbrains.kotlin.stdlib)

    // Dependency Injection
    implementation(libs.bundles.google.dagger)
    kapt(libs.bundles.google.dagger.compiler)
}

private val bomLibDef = project.configurations.getByName("implementation").allDependencies.find {
    it.group == "com.isupatches.android.wisefy" && it.name == "wisefy-bom"
}

if (bomLibDef != null) {
    android.buildTypes.forEach { buildType ->
        buildType.buildConfigField("String", "WISEFY_BOM_VERSION", "\"${bomLibDef.version}\"")
    }
} else {
    android.buildTypes.forEach { buildType ->
        buildType.buildConfigField("String", "WISEFY_BOM_VERSION", "unknown")
    }
}
