import com.isupatches.android.wisefy.build.BuildVersions
import com.isupatches.android.wisefy.build.Dependencies
import com.isupatches.android.wisefy.build.PublishingConstants

plugins {
    id("com.android.library")
    id("com.isupatches.android.wisefy.build.plugins.BaseGradleModulePlugin")
    id("com.isupatches.android.wisefy.build.plugins.DocumentationPlugin")
    id("com.isupatches.android.wisefy.build.plugins.PublishingPlugin")
    id("kotlin-android")
}

group = PublishingConstants.GROUP_ID
version = BuildVersions.WISEFY_SIGNAL_VERSION

android {
    namespace = "com.isupatches.android.wisefy.signal"
    testNamespace = "com.isupatches.android.wisefy.signal.test"
}

dependencies {
    implementation(project(":wisefy:core"))

    // Kotlin
    implementation(Dependencies.Kotlin.COROUTINES)
}
