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

dependencies {
    implementation(project(":wisefy:accesspoints"))
    implementation(project(":wisefy:addnetwork"))
    implementation(project(":wisefy:core"))
    implementation(project(":wisefy:frequency"))
    implementation(project(":wisefy:networkconnection"))
    implementation(project(":wisefy:networkconnectionstatus"))
    implementation(project(":wisefy:networkinfo"))
    implementation(project(":wisefy:removenetwork"))
    implementation(project(":wisefy:savednetworks"))
    implementation(project(":wisefy:security"))
    implementation(project(":wisefy:signal"))
    implementation(project(":wisefy:wifi"))

    implementation(Dependencies.Kotlin.COROUTINES)
}
