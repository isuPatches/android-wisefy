import com.isupatches.android.wisefy.build.Dependencies

plugins {
    id("com.android.library")
    id("com.isupatches.android.wisefy.build.plugins.BaseGradleModulePlugin")
    id("com.isupatches.android.wisefy.build.plugins.DocumentationPlugin")
    id("com.isupatches.android.wisefy.build.plugins.PublishingPlugin")
    id("kotlin-android")
}

dependencies {
    implementation(project(":wisefy:shared:base"))

    implementation(Dependencies.Kotlin.COROUTINES)
}
