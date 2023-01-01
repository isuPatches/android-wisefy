import com.isupatches.android.wisefy.build.TestDependencies

plugins {
    id("com.android.library")
    id("com.isupatches.android.wisefy.build.plugins.BaseGradleModulePlugin")
    id("kotlin-android")
}

android {
    namespace = "com.isupatches.android.wisefy.testsupport"
}

dependencies {
    implementation(project(":wisefy:core"))

    implementation(TestDependencies.Mockito.CORE)
    implementation(TestDependencies.Kotlin.Coroutines.TEST)
}
