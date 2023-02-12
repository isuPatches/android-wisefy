import com.isupatches.android.wisefy.build.BuildVersions
import com.isupatches.android.wisefy.build.Dependencies
import com.isupatches.android.wisefy.build.PublishingConstants
import com.isupatches.android.wisefy.build.TestDependencies

plugins {
    id("com.android.library")
    id("com.isupatches.android.wisefy.build.plugins.BaseGradleModulePlugin")
    id("com.isupatches.android.wisefy.build.plugins.DocumentationPlugin")
    id("com.isupatches.android.wisefy.build.plugins.PublishingPlugin")
    id("kotlin-android")
}

group = PublishingConstants.GROUP_ID
version = BuildVersions.WISEFY_ACCESS_POINTS_VERSION

android {
    namespace = "com.isupatches.android.wisefy.accesspoints"
    testNamespace = "com.isupatches.android.wisefy.accesspoints.test"
}

dependencies {
    implementation(project(":wisefy:core"))

    // Kotlin
    implementation(Dependencies.Kotlin.COROUTINES)

    // Unit Tests
    testImplementation(project(":testsupport"))
    testImplementation(TestDependencies.JUNIT)
    testImplementation(TestDependencies.Mockito.CORE)
    testImplementation(TestDependencies.Kotlin.Coroutines.TEST)

    // Instrumentation Tests
    androidTestImplementation(project(":testsupport"))
    androidTestImplementation(TestDependencies.JUNIT)
    androidTestImplementation(TestDependencies.AndroidX.Test.RULES)
    androidTestImplementation(TestDependencies.AndroidX.Test.RUNNER)
    androidTestImplementation(TestDependencies.Mockito.CORE)
    androidTestImplementation(TestDependencies.Mockito.ANDROID)
    androidTestImplementation(TestDependencies.Kotlin.Coroutines.TEST)
}
