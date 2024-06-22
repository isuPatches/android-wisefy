plugins {
    id(libs.plugins.wisefy.android.library.get().pluginId)
}

android {
    namespace = "com.isupatches.android.wisefy.testsupport"
}

dependencies {
    implementation(project(":wisefy:core"))

    implementation(testLibs.mockito.core)
    implementation(testLibs.kotlin.coroutines.test)
}
