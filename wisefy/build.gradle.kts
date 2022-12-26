import com.isupatches.android.wisefy.build.BuildVersions
import com.isupatches.android.wisefy.build.Dependencies
import com.isupatches.android.wisefy.build.PublishingConstants.GROUP_ID

plugins {
    id("com.android.library")
    id("com.isupatches.android.wisefy.build.plugins.BaseGradleModulePlugin")
    id("com.isupatches.android.wisefy.build.plugins.DocumentationPlugin")
    id("com.isupatches.android.wisefy.build.plugins.PublishingPlugin")
    id("kotlin-android")
}

group = GROUP_ID
version = BuildVersions.MODULE_VERSION_NAME

android {
    namespace = "com.isupatches.android.wisefy"
    testNamespace = "com.isupatches.android.wisefy.test"
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
//    api(project(":wisefy:accesspoints"))
//    api(project(":wisefy:addnetwork"))
//    api(project(":wisefy:core"))
//    api(project(":wisefy:networkconnection"))
//    api(project(":wisefy:networkinfo"))
//    api(project(":wisefy:removenetwork"))
//    api(project(":wisefy:savednetworks"))
//    api(project(":wisefy:signal"))
//    api(project(":wisefy:wifi"))

    implementation(Dependencies.Kotlin.COROUTINES)
}
