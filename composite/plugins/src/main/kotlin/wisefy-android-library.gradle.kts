import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.io.File

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    // START HACK - https://github.com/gradle/gradle/issues/15383
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
    val buildToolsVersionFromLibs = libs.findVersion("build-tools-version").get().requiredVersion
    val compileSdkFromLibs = libs.findVersion("sdk-compile-version").get().requiredVersion
    val minSdkFromLibs = libs.findVersion("sdk-min-version").get().requiredVersion
    val jacocoVersionFromLibs = libs.findVersion("jacoco-version").get().requiredVersion
    // END HACK

    compileSdk = compileSdkFromLibs.toInt()
    buildToolsVersion = buildToolsVersionFromLibs

    defaultConfig {
        minSdk = minSdkFromLibs.toInt()

        vectorDrawables.useSupportLibrary = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("release") {
            storeFile = File("${rootDir}/keystores/wisefy-release.jks")
            keyAlias = properties["WISEFY_RELEASE_KEY_ALIAS"]?.toString() ?: System.getenv("WISEFY_RELEASE_KEY_ALIAS")
            storePassword = properties["WISEFY_RELEASE_PASSWORD"]?.toString() ?: System.getenv("WISEFY_RELEASE_PASSWORD")
            keyPassword = properties["WISEFY_RELEASE_PASSWORD"]?.toString() ?: System.getenv("WISEFY_RELEASE_PASSWORD")
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
                "${rootDir}/proguard/r8-lib-debug.pro"
            )
            testProguardFile("${rootDir}/proguard/r8-lib-test.pro")
            consumerProguardFile("${rootDir}/proguard/r8-lib-consumer.pro")
        }

        release {
            enableUnitTestCoverage = false
            enableAndroidTestCoverage = false
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "${rootDir}/proguard/r8-lib-release.pro"
            )
            consumerProguardFile("${rootDir}/proguard/r8-lib-consumer.pro")
            signingConfig = signingConfigs.getByName("release")
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
        jacocoVersion = jacocoVersionFromLibs
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    kotlin {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
            allWarningsAsErrors = true
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
}
