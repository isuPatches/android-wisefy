plugins {
    id(libs.plugins.wisefy.android.library.get().pluginId)
    id(libs.plugins.wisefy.android.publish.get().pluginId)
}

group = project.properties["groupId"] ?: error("Group ID is a required gradle property")
version = project.properties["version"] ?: error("Version is a required gradle property")

android {
    namespace = "com.isupatches.android.wisefy"
}

dependencies {
    api(project(":wisefy:accesspoints"))
    api(project(":wisefy:addnetwork"))
    api(project(":wisefy:core"))
    api(project(":wisefy:networkconnection"))
    api(project(":wisefy:networkinfo"))
    api(project(":wisefy:removenetwork"))
    api(project(":wisefy:savednetworks"))
    api(project(":wisefy:signal"))
    api(project(":wisefy:wifi"))

    // AndroidX
    implementation(libs.androidx.annotation)

    // Kotlin
    implementation(libs.jetbrains.kotlin.stdlib)
    implementation(libs.jetbrains.kotlinx.coroutines)
}
