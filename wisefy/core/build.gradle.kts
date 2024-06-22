plugins {
    id(libs.plugins.wisefy.android.library.get().pluginId)
    id(libs.plugins.wisefy.android.publish.get().pluginId)
}

group = project.properties["groupId"] ?: error("Group ID is a required gradle property")
version = project.properties["version"] ?: error("Version is a required gradle property")

android {
    namespace = "com.isupatches.android.wisefy.core"
}

dependencies {
    // AndroidX
    implementation(libs.androidx.annotation)

    // Kotlin
    implementation(libs.jetbrains.kotlin.stdlib)
    implementation(libs.jetbrains.kotlinx.coroutines)
}
