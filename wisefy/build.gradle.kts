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

    /*
     * These should be uncommented to build wisefy / wisefy-ktx against released artifacts for wisefy features.
     * These will be necessary to allow the gradle.lockfiles to pick-up on the versions of each sub-dependency in-use.
     *
     * See:
     *  - https://docs.gradle.org/current/userguide/single_versions.html
     *  - https://docs.gradle.org/current/userguide/dependency_locking.html
     */
    api("com.isupatches.android.wisefy:accesspoints:[5.0,6.0)")
    api("com.isupatches.android.wisefy:addnetwork:[5.0,6.0)")
    api("com.isupatches.android.wisefy:core:[5.0,6.0)")
    api("com.isupatches.android.wisefy:networkconnection:[5.0,6.0)")
    api("com.isupatches.android.wisefy:networkinfo:[5.0,6.0)")
    api("com.isupatches.android.wisefy:removenetwork:[5.0,6.0)")
    api("com.isupatches.android.wisefy:savednetworks:[5.0,6.0)")
    api("com.isupatches.android.wisefy:signal:[5.0,6.0)")
    api("com.isupatches.android.wisefy:wifi:[5.0,6.0)")

    /*
     * These should be uncommented to build wisefy / wisefy-ktx directly from source code
     */
    implementation(project(":wisefy:accesspoints"))
    implementation(project(":wisefy:addnetwork"))
    implementation(project(":wisefy:core"))
    implementation(project(":wisefy:networkconnection"))
    implementation(project(":wisefy:networkinfo"))
    implementation(project(":wisefy:removenetwork"))
    implementation(project(":wisefy:savednetworks"))
    implementation(project(":wisefy:signal"))
    implementation(project(":wisefy:wifi"))

    // AndroidX
    implementation(libs.androidx.annotation)

    // Kotlin
    implementation(libs.jetbrains.kotlin.stdlib)
    implementation(libs.jetbrains.kotlinx.coroutines)
}
