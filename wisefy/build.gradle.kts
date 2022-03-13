import com.isupatches.android.wisefy.build.BuildVersions
import com.isupatches.android.wisefy.build.Dependencies
import com.isupatches.android.wisefy.build.PublishingConstants.GROUP_ID

plugins {
    id("com.android.library")
    id("com.isupatches.android.wisefy.build.plugins.BaseGradleModulePlugin")
    id("com.isupatches.android.wisefy.build.plugins.DocumentationPlugin")
    id("com.isupatches.android.wisefy.build.plugins.PublishingPlugin")
    id("kotlin-android")
    id("kotlin-kapt")
}

group = GROUP_ID
version = BuildVersions.MODULE_VERSION_NAME

dependencies {
    api(project(":wisefy:accesspoints"))
    api(project(":wisefy:addnetwork"))
    api(project(":wisefy:frequency"))
    api(project(":wisefy:networkconnection"))
    api(project(":wisefy:networkconnectionstatus"))
    api(project(":wisefy:networkinfo"))
    api(project(":wisefy:removenetwork"))
    api(project(":wisefy:savednetworks"))
    api(project(":wisefy:security"))
    api(project(":wisefy:signal"))
    api(project(":wisefy:wifi"))

    implementation(Dependencies.Kotlin.COROUTINES)
}
