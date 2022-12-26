plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation("com.android.tools.build:gradle:7.3.0")

    // https://github.com/google/dagger/issues/3068
    implementation("com.squareup:javapoet:1.13.0")

    // https://issuetracker.google.com/issues/195342732?pli=1
    implementation(kotlin("gradle-plugin","1.7.10"))
}
