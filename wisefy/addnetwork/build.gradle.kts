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
version = BuildVersions.WISEFY_ADD_NETWORK_VERSION

android {
    namespace = "com.isupatches.android.wisefy.addnetwork"
    testNamespace = "com.isupatches.android.wisefy.addnetwork.test"
}

dependencies {
    implementation(project(":wisefy:core"))

    // Kotlin
    implementation(Dependencies.Kotlin.COROUTINES)

    // Unit Tests
    testImplementation(project(":testsupport"))
    testImplementation(com.isupatches.android.wisefy.build.TestDependencies.JUNIT)
    testImplementation(com.isupatches.android.wisefy.build.TestDependencies.Mockito.CORE)
    testImplementation(com.isupatches.android.wisefy.build.TestDependencies.Kotlin.Coroutines.TEST)

    // Instrumentation Tests
    androidTestImplementation(project(":testsupport"))
    androidTestImplementation(com.isupatches.android.wisefy.build.TestDependencies.JUNIT)
    androidTestImplementation(com.isupatches.android.wisefy.build.TestDependencies.AndroidX.TEST_RUNNER)
    androidTestImplementation(com.isupatches.android.wisefy.build.TestDependencies.AndroidX.TEST_RULES)
    androidTestImplementation(com.isupatches.android.wisefy.build.TestDependencies.Mockito.CORE)
    androidTestImplementation(com.isupatches.android.wisefy.build.TestDependencies.Mockito.ANDROID)
    androidTestImplementation(com.isupatches.android.wisefy.build.TestDependencies.Kotlin.Coroutines.TEST)
}
