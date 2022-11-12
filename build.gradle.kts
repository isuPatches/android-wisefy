import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.dokka.Platform.jvm
import org.jetbrains.dokka.gradle.DokkaMultiModuleTask
import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {

    repositories {
        google()
        mavenCentral()
        maven(url = "https://plugins.gradle.org/m2/")
    }

    dependencies {
        val versions = com.isupatches.android.wisefy.build.Versions
        classpath("com.android.tools.build:gradle:${versions.AGP}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${versions.KOTLIN}")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${versions.DAGGER}")

        /**
         * Ideally this would be migrated out of the project level build.gradle.kts to the [DocumentationPlugin],
         * but currently the buildSrc directory cannot see [DokkaTask] or [jvm] and unsure why.
         */
        classpath("org.jetbrains.dokka:dokka-gradle-plugin:${versions.DOKKA}")
    }
}

allprojects {
    repositories {
        mavenLocal()
        google()
        mavenCentral()
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots")
    }

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
                jvmTarget = "11"
                // https://issuetracker.google.com/issues/217593040
                freeCompilerArgs = freeCompilerArgs + "-Xjvm-default=all"
            }
        }

        withType<JavaCompile> {
            sourceCompatibility = "${JavaVersion.VERSION_11}"
            targetCompatibility = "${JavaVersion.VERSION_11}"
        }
    }
}

subprojects {
    // Static Analysis
    apply(from = "${rootProject.projectDir}/gradle/cpd.gradle.kts")
    apply(from = "${rootProject.projectDir}/gradle/detekt.gradle.kts")
    apply(from = "${rootProject.projectDir}/gradle/dexcount.gradle.kts")
    apply(from = "${rootProject.projectDir}/gradle/ktlint.gradle.kts")

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
