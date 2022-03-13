import com.isupatches.android.wisefy.build.Dependencies

plugins {
    id("com.android.library")
    id("com.isupatches.android.wisefy.build.plugins.BaseGradleModulePlugin")
    id("com.isupatches.android.wisefy.build.plugins.DocumentationPlugin")
    id("com.isupatches.android.wisefy.build.plugins.PublishingPlugin")
    id("kotlin-android")
}

dependencies {
    implementation(project(":wisefy:networkconnectionstatus"))
    implementation(project(":wisefy:savednetworks"))
    api(project(":wisefy:shared:base"))
    api(project(":wisefy:shared:coroutines"))
    api(project(":wisefy:shared:entities"))
    api(project(":wisefy:shared:logging"))
    api(project(":wisefy:shared:util"))

    // Kotlin
    implementation(Dependencies.Kotlin.COROUTINES)
}
