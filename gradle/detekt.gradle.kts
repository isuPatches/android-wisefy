import io.gitlab.arturbosch.detekt.DetektPlugin
import io.gitlab.arturbosch.detekt.extensions.DetektExtension

buildscript {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }

    dependencies {
        val versions = com.isupatches.android.wisefy.build.Versions
        classpath("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${versions.DETEKT}")
    }
}

plugins.apply(DetektPlugin::class)

configure<DetektExtension> {
    version = com.isupatches.android.wisefy.build.Versions.DETEKT
    config = files("${project.rootDir}/config/detekt.yml")
}
