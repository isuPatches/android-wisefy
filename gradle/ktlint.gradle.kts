import org.jlleitschuh.gradle.ktlint.KtlintExtension
import org.jlleitschuh.gradle.ktlint.KtlintPlugin
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType.PLAIN

buildscript {
    repositories {
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
    }

    dependencies {
        val versions = com.isupatches.android.wisefy.build.Versions
        classpath("org.jlleitschuh.gradle:ktlint-gradle:${versions.KTLINT_PLUGIN}")
    }
}

plugins.apply(KtlintPlugin::class)

configure<KtlintExtension> {
    version.set(com.isupatches.android.wisefy.build.Versions.KTLINT)
    debug.set(false)
    verbose.set(true)
    android.set(true)
    reporters {
        reporter(PLAIN)
        reporter(CHECKSTYLE)
    }
    ignoreFailures.set(false)
}
