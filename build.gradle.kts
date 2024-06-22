import com.getkeepsafe.dexcount.DexMethodCountPlugin
import de.aaschmid.gradle.plugins.cpd.Cpd
import de.aaschmid.gradle.plugins.cpd.CpdExtension
import de.aaschmid.gradle.plugins.cpd.CpdPlugin
import io.gitlab.arturbosch.detekt.DetektPlugin
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.dokka.Platform.jvm
import org.jetbrains.dokka.gradle.DokkaMultiModuleTask
import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jmailen.gradle.kotlinter.KotlinterPlugin

plugins {
    // Android
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false

    // Kotlin
    alias(libs.plugins.jetbrains.kotlin.android) apply false

    // Build
    alias(libs.plugins.google.dagger.hilt.android) apply false

    // Static Analysis
    alias(libs.plugins.cpd) apply false
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.dexcount) apply false
    alias(libs.plugins.kotlinter) apply false

    // Documentation
    alias(libs.plugins.jetbrains.dokka) apply false
}

allprojects {
    tasks.withType(Test::class).configureEach {
        /*
        * Run tests in parallel (https://docs.gradle.org/nightly/userguide/performance.html).
        * Must be less than the number of CPU cores.
        */
        maxParallelForks = Runtime.getRuntime().availableProcessors().div(2)

        testLogging {
            // Log events
            events = setOf(
                TestLogEvent.STARTED,
                TestLogEvent.FAILED,
                TestLogEvent.SKIPPED,
                TestLogEvent.PASSED
            )

            exceptionFormat = TestExceptionFormat.FULL
            showExceptions = true
            showCauses = true
            showStackTraces = true
        }
    }

    tasks {
        withType<KotlinCompile> {
            kotlinOptions {
                jvmTarget = "17"
                allWarningsAsErrors = true
                // https://issuetracker.google.com/issues/217593040
                freeCompilerArgs = freeCompilerArgs + "-Xjvm-default=all"
            }
        }

        withType<JavaCompile> {
            sourceCompatibility = "${JavaVersion.VERSION_17}"
            targetCompatibility = "${JavaVersion.VERSION_17}"
        }
    }
}

subprojects {
    // Static Analysis
    @Suppress("UnstableApiUsage")
    plugins.apply(CpdPlugin::class)
    configure<CpdExtension> {
        language = "kotlin"
        group = "reporting"
        isIgnoreFailures = false
        minimumTokenCount = 75
    }
    tasks.withType<Cpd>().configureEach {
        source = fileTree("$projectDir/src/main/java")
        reports {
            text.required.set(false)
            xml.required.set(true)
        }
        exclude(
            "**/*Delegate.kt",
            "**/*Api.kt"
        )
    }

    plugins.apply(DetektPlugin::class)
    configure<io.gitlab.arturbosch.detekt.extensions.DetektExtension> {
        config.setFrom(files("${project.rootDir}/config/detekt.yml"))
    }

    plugins.apply(DexMethodCountPlugin::class)

    plugins.apply(KotlinterPlugin::class)

    // Code coverage
    apply(from = "${rootProject.projectDir}/gradle/jacoco.gradle.kts")

    /**
     * Ideally this would be migrated out of the project level build.gradle.kts to the [DocumentationPlugin],
     * but currently the buildSrc directory cannot see [DokkaTask] or [jvm] and unsure why.
     */
    tasks.withType<DokkaTask>().configureEach {
        outputDirectory.set(project.projectDir.resolve("dokka"))
        moduleName.set(project.name)
        suppressObviousFunctions.set(false)
        dokkaSourceSets {
            configureEach {
                offlineMode.set(false)
                includeNonPublic.set(true)
                skipDeprecated.set(false)
                reportUndocumented.set(true)
                skipEmptyPackages.set(false)
                platform.set(jvm)
                jdkVersion.set(11)
                noStdlibLink.set(false)
                noJdkLink.set(false)
                noAndroidSdkLink.set(false)
            }
        }
    }

    tasks.withType<DokkaMultiModuleTask>().configureEach {
        outputDirectory.set(rootProject.projectDir.resolve("dokka"))
        moduleName.set(project.name)
        suppressObviousFunctions.set(false)
    }

    configurations.all {
        // Check for updates every build
        resolutionStrategy.cacheChangingModulesFor(0, "seconds")
    }
}
