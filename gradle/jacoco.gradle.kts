import java.util.Locale
import org.gradle.testing.jacoco.plugins.JacocoTaskExtension

plugins.apply(JacocoPlugin::class)

fun getCoverageVariants() = listOf("debug")
val buildTaskGroup = "verification"

val excludes = setOf(
    // Default auto-generates classes with Android (R file and BuildConfig)
    "**/R.class",
    "**/R\$*.class",
    "**/BuildConfig.*",

    // Dagger and Hilt excludes
    "**/*Module*.*",
    "**/*Factory*.*",
    "**/*GeneratedInjector*.*",
    "**/*MembersInjector*.*",
    "**/*Hilt_*.*",

    // Other excludes
    "**/Manifest*.*",
    "**/*\$Lambda\$*.class",
    "**/*\$Builder*",
)

fun getSourceDirectoriesTree(project: Project): ConfigurableFileCollection {
    val sourceDir = "${project.projectDir}/src/main/kotlin"
    return files(sourceDir)
}

fun getClassDirectoriesTree(project: Project, excludes: Set<String>): ConfigurableFileCollection {
    val debugKotlinClasses = fileTree("${project.layout.buildDirectory.get()}/tmp/kotlin-classes/debug") {
        exclude(excludes)
    }
    val debugJavaClasses = fileTree("${project.layout.buildDirectory.get()}/intermediates/javac/debug/classes") {
        exclude(excludes)
    }
    val allFiles = files(debugKotlinClasses, debugJavaClasses)
    return allFiles
}

afterEvaluate {
    getCoverageVariants().forEach { variant ->
        val capitalizedVariant = variant.replaceFirstChar {
            if (it.isLowerCase()) {
                it.titlecase(Locale.getDefault())
            } else {
                it.toString()
            }
        }

        tasks.register<JacocoReport>("jacoco${capitalizedVariant}UnitTest") {
            group = buildTaskGroup

            dependsOn("test${capitalizedVariant}UnitTest")

            if (plugins.hasPlugin("com.android.library") ) {
                mustRunAfter("compile${capitalizedVariant}LibraryResources")
            }

            classDirectories.setFrom(getClassDirectoriesTree(project, excludes))
            sourceDirectories.setFrom(getSourceDirectoriesTree(project))
            executionData.setFrom(
                fileTree("${project.layout.buildDirectory.get()}/outputs/unit_test_code_coverage") {
                    include("**/*.exec")
                }
            )

            reports {
                xml.required.set(false)
                html.required.set(true)
            }
        }

        tasks.register<JacocoReport>("jacoco${capitalizedVariant}UnitTestReport") {
            group = buildTaskGroup

            classDirectories.setFrom(getClassDirectoriesTree(project, excludes))
            sourceDirectories.setFrom(getSourceDirectoriesTree(project))
            executionData.setFrom(
                fileTree("${project.layout.buildDirectory.get()}/outputs/unit_test_code_coverage") {
                    include("**/*.exec")
                }
            )

            reports {
                xml.required.set(false)
                html.required.set(true)
            }
        }

        tasks.register<JacocoReport>("jacoco${capitalizedVariant}AndroidTest") {
            group = buildTaskGroup

            dependsOn("connected${capitalizedVariant}AndroidTest")

            classDirectories.setFrom(getClassDirectoriesTree(project, excludes))
            sourceDirectories.setFrom(getSourceDirectoriesTree(project))
            executionData.setFrom(
                fileTree("${project.layout.buildDirectory.get()}/outputs/code_coverage/") {
                    include("**/*.ec")
                }
            )

            reports {
                xml.required.set(false)
                html.required.set(true)
            }
        }

        tasks.register<JacocoReport>("jacoco${capitalizedVariant}AndroidTestReport") {
            group = buildTaskGroup

            classDirectories.setFrom(getClassDirectoriesTree(project, excludes))
            sourceDirectories.setFrom(getSourceDirectoriesTree(project))
            executionData.setFrom(
                fileTree("${project.layout.buildDirectory.get()}/outputs/code_coverage/") {
                    include("**/*.ec")
                }
            )

            reports {
                xml.required.set(false)
                html.required.set(true)
            }
        }

        tasks.register<JacocoReport>("jacoco${capitalizedVariant}CombinedTest") {
            group = buildTaskGroup

            dependsOn("test${capitalizedVariant}UnitTest", "connected${capitalizedVariant}AndroidTest")

            if (plugins.hasPlugin("com.android.library") ) {
                mustRunAfter("compile${capitalizedVariant}LibraryResources")
            }

            classDirectories.setFrom(getClassDirectoriesTree(project, excludes))
            sourceDirectories.setFrom(getSourceDirectoriesTree(project))
            executionData.setFrom(
                fileTree("${project.layout.buildDirectory.get()}/outputs/unit_test_code_coverage") {
                    include("**/*.exec")
                },
                fileTree("${project.layout.buildDirectory.get()}/outputs/code_coverage/") {
                    include("**/*.ec")
                }
            )

            reports {
                xml.required.set(false)
                html.required.set(true)
            }
        }

        tasks.register<JacocoReport>("jacoco${capitalizedVariant}CombinedTestReport") {
            group = buildTaskGroup

            classDirectories.setFrom(getClassDirectoriesTree(project, excludes))
            sourceDirectories.setFrom(getSourceDirectoriesTree(project))
            executionData.setFrom(
                fileTree("${project.layout.buildDirectory.get()}/outputs/unit_test_code_coverage") {
                    include("**/*.exec")
                },
                fileTree("${project.layout.buildDirectory.get()}/outputs/code_coverage/") {
                    include("**/*.ec")
                }
            )

            reports {
                xml.required.set(false)
                html.required.set(true)
            }
        }
    }
}

tasks.withType<Test> {
    configure<JacocoTaskExtension> {
        isIncludeNoLocationClasses  = true
        excludes = listOf("jdk.internal.*")
    }
}
