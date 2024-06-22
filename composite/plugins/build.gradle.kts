plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.android.gradleplugin)
    implementation(libs.jetbrains.kotlin.gradleplugin)
    implementation(libs.jetbrains.dokka.plugin)
}
