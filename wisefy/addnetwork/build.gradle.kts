import com.isupatches.android.wisefy.build.Dependencies

plugins {
    id("com.android.library")
    id("com.isupatches.android.wisefy.build.plugins.BaseGradleModulePlugin")
    id("com.isupatches.android.wisefy.build.plugins.DocumentationPlugin")
    id("com.isupatches.android.wisefy.build.plugins.PublishingPlugin")
    id("kotlin-android")
}

dependencies {
    api(project(":wisefy:shared:assertions"))
    api(project(":wisefy:shared:base"))
    api(project(":wisefy:shared:coroutines"))
    api(project(":wisefy:shared:entities"))
    api(project(":wisefy:shared:logging"))
    api(project(":wisefy:shared:util"))
    api(project(":wisefy:shared:wifimanager"))

    // Kotlin
    implementation(Dependencies.Kotlin.COROUTINES)
}
