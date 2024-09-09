import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.io.ByteArrayOutputStream
import java.io.OutputStream

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.dagger.hilt.android)
    alias(libs.plugins.google.ksp)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.jetbrains.kotlin.plugin.compose)
}

android {
    namespace = "com.isupatches.android.wisefy.sample"

    compileSdk = libs.versions.sdk.compile.version.get().toInt()
    buildToolsVersion = libs.versions.build.tools.version.get()

    defaultConfig {
        applicationId = "com.isupatches.android.wisefy.sample"

        minSdk = libs.versions.sdk.min.version.get().toInt()
        targetSdk = libs.versions.sdk.target.version.get().toInt()

        versionCode = 18
        versionName = "5.0.0-RC3"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "GIT_HASH", "\"${gitCommitHash()}\"")
    }

    signingConfigs {
        create("release") {
            storeFile = File("$rootDir/keystores/wisefy-sample-release.jks")
            keyAlias = properties["WISEFY_SAMPLE_RELEASE_KEY_ALIAS"]?.toString()
                ?: System.getenv("WISEFY_SAMPLE_RELEASE_KEY_ALIAS")
            storePassword = properties["WISEFY_SAMPLE_RELEASE_PASSWORD"]?.toString()
                ?: System.getenv("WISEFY_SAMPLE_RELEASE_PASSWORD")
            keyPassword = properties["WISEFY_SAMPLE_RELEASE_PASSWORD"]?.toString()
                ?: System.getenv("WISEFY_SAMPLE_RELEASE_PASSWORD")
        }
    }

    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
            enableUnitTestCoverage = true
            enableAndroidTestCoverage = true
            isMinifyEnabled = properties["MINIFY_DEBUG_BUILDS"]?.toString()?.toBoolean() ?: System.getenv("MINIFY_DEBUG_BUILDS").toBoolean()
            isShrinkResources = properties["MINIFY_DEBUG_BUILDS"]?.toString()?.toBoolean() ?: System.getenv("MINIFY_DEBUG_BUILDS").toBoolean()
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
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    kotlin {
        compilerOptions {
            allWarningsAsErrors = true
            jvmTarget.set(JvmTarget.JVM_21)
        }
        jvmToolchain(21)
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
private fun Project.gitCommitHash(): String {
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
    implementation(platform(libs.isupatches.wisefy.bom))

    /*
     * This should be uncommented to run sample app directly against the source BOM
     */
//    implementation(platform(project(":wisefy:bom")))

    implementation(libs.isupatches.wisefy.accesspoints)
    implementation(libs.isupatches.wisefy.addnetwork)
    implementation(libs.isupatches.wisefy.core)
    implementation(libs.isupatches.wisefy.ktx)
    implementation(libs.isupatches.wisefy.networkconnection)
    implementation(libs.isupatches.wisefy.networkinfo)
    implementation(libs.isupatches.wisefy.removenetwork)
    implementation(libs.isupatches.wisefy.savednetworks)
    implementation(libs.isupatches.wisefy.signal)
    implementation(libs.isupatches.wisefy.wifi)
    implementation(libs.isupatches.wisefy.wisefy)

    // AndroidX
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.annotation)
    implementation(libs.androidx.corektx)
    implementation(libs.androidx.datastore.preferences)

    implementation(libs.androidx.hilt.navigationcompose)
    implementation(libs.androidx.lifecycle.viewmodelcompose)

    implementation(libs.androidx.compose.animation)
    implementation(libs.bundles.androidx.compose.material)
    implementation(libs.bundles.androidx.compose.ui)
    debugImplementation(libs.androidx.compose.ui.tooling)

    // Koltin
    implementation(libs.jetbrains.kotlin.stdlib)

    // Google
    implementation(libs.google.android.material)

    // Dependency Injection
    implementation(libs.bundles.google.dagger)
    ksp(libs.bundles.google.dagger.compiler)
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
