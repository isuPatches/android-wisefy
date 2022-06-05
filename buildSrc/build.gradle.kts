plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation("com.android.tools.build:gradle:7.2.1")

    // https://github.com/google/dagger/issues/3068
    implementation("com.squareup:javapoet:1.13.0")

    // https://github.com/Kotlin/dokka/issues/2452
    implementation("org.jetbrains.dokka:dokka-core:1.6.21")
}
