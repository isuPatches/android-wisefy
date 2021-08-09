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

android {
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // AndroidX
    implementation(Dependencies.AndroidX.APPCOMPAT)
    implementation(Dependencies.AndroidX.ANNOTATION)

    // Kotlin
    implementation(Dependencies.Kotlin.STD_LIB)
    implementation(Dependencies.Kotlin.COROUTINES)
}
