plugins {
    id(libs.plugins.wisefy.android.library.get().pluginId)
    id(libs.plugins.wisefy.android.publish.get().pluginId)
}

group = project.properties["groupId"] ?: error("Group ID is a required gradle property")
version = project.properties["version"] ?: error("Version is a required gradle property")

android {
    namespace = "com.isupatches.android.wisefy.removenetwork"
}

dependencies {
    implementation(project(":wisefy:core"))

    // AndroidX
    implementation(libs.androidx.annotation)

    // Kotlin
    implementation(libs.jetbrains.kotlin.stdlib)
    implementation(libs.jetbrains.kotlinx.coroutines)

    // Unit Tests
    testImplementation(project(":testsupport"))
    testImplementation(testLibs.junit)
    testImplementation(testLibs.mockito.core)
    testImplementation(testLibs.kotlin.coroutines.test)

    // Instrumentation Tests
    androidTestImplementation(project(":testsupport"))
    androidTestImplementation(testLibs.junit)
    androidTestImplementation(testLibs.androidx.test.rules)
    androidTestImplementation(testLibs.androidx.test.runner)
    androidTestImplementation(testLibs.mockito.core)
    androidTestImplementation(testLibs.mockito.android)
    androidTestImplementation(testLibs.kotlin.coroutines.test)
}
