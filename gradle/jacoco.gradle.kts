import java.util.Locale

plugins.apply(JacocoPlugin::class)

private fun getCoverageVariants() = listOf("debug")
private val buildTaskGroup = "verification"

private val excludes = setOf(
    "**/R.class",
    "**/R\$*.class",
    "**/BuildConfig.*",
    "**/Manifest*.*",
    "**/*\$Lambda\$*.class",
    "**/*Factory*.class",
    "**/*\$Builder*"
)

private fun getSourceDirectoriesTree() = files(
    "src/main/java"
)

private fun getClassDirectoriesTree(buildDirectory: Directory, excludes: Set<String>): FileTree {
    return fileTree("$buildDirectory") {
        include(
            "**/classes/**/main/**",
            "**/intermediates/classes/debug/**",
            "**/intermediates/javac/debug/*/classes/**", // Android Gradle Plugin 3.2.x support.
            "**/tmp/kotlin-classes/debug/**"
        )

        exclude(excludes)
    }
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

        val buildDirectory = project.layout.buildDirectory.get()

        tasks.create<JacocoReport>("jacoco${capitalizedVariant}UnitTest") {
            group = buildTaskGroup

            dependsOn("test${capitalizedVariant}UnitTest")

            classDirectories.setFrom(getClassDirectoriesTree(buildDirectory, excludes))
            sourceDirectories.setFrom(getSourceDirectoriesTree())
            executionData.setFrom(
                fileTree("$buildDirectory/outputs/unit_test_code_coverage") {
                    include("**/*.exec")
                }
            )

            reports {
                xml.required.set(false)
                html.required.set(true)
            }
        }

        tasks.create<JacocoReport>("jacoco${capitalizedVariant}UnitTestReport") {
            group = buildTaskGroup

            classDirectories.setFrom(getClassDirectoriesTree(buildDirectory, excludes))
            sourceDirectories.setFrom(getSourceDirectoriesTree())
            executionData.setFrom(
                fileTree("$buildDirectory/outputs/unit_test_code_coverage") {
                    include("**/*.exec")
                }
            )

            reports {
                xml.required.set(false)
                html.required.set(true)
            }
        }

        tasks.create<JacocoReport>("jacoco${capitalizedVariant}AndroidTest") {
            group = buildTaskGroup

            dependsOn("connected${capitalizedVariant}AndroidTest")

            classDirectories.setFrom(getClassDirectoriesTree(buildDirectory, excludes))
            sourceDirectories.setFrom(getSourceDirectoriesTree())
            executionData.setFrom(
                fileTree("$buildDirectory/outputs/code_coverage/") {
                    include("**/*.ec")
                }
            )

            reports {
                xml.required.set(false)
                html.required.set(true)
            }
        }

        tasks.create<JacocoReport>("jacoco${capitalizedVariant}AndroidTestReport") {
            group = buildTaskGroup

            classDirectories.setFrom(getClassDirectoriesTree(buildDirectory, excludes))
            sourceDirectories.setFrom(getSourceDirectoriesTree())
            executionData.setFrom(
                fileTree("$buildDirectory/outputs/code_coverage/") {
                    include("**/*.ec")
                }
            )

            reports {
                xml.required.set(false)
                html.required.set(true)
            }
        }

        tasks.create<JacocoReport>("jacoco${capitalizedVariant}CombinedTest") {
            group = buildTaskGroup

            dependsOn("test${capitalizedVariant}UnitTest", "connected${capitalizedVariant}AndroidTest")

            classDirectories.setFrom(getClassDirectoriesTree(buildDirectory, excludes))
            sourceDirectories.setFrom(getSourceDirectoriesTree())
            executionData.setFrom(
                fileTree("$buildDirectory/outputs/unit_test_code_coverage") {
                    include("**/*.exec")
                },
                fileTree("$buildDirectory}/outputs/code_coverage/") {
                    include("**/*.ec")
                }
            )

            reports {
                xml.required.set(false)
                html.required.set(true)
            }
        }

        tasks.create<JacocoReport>("jacoco${capitalizedVariant}CombinedTestReport") {
            group = buildTaskGroup

            classDirectories.setFrom(getClassDirectoriesTree(buildDirectory, excludes))
            sourceDirectories.setFrom(getSourceDirectoriesTree())
            executionData.setFrom(
                fileTree("$buildDirectory/outputs/unit_test_code_coverage") {
                    include("**/*.exec")
                },
                fileTree("$buildDirectory/outputs/code_coverage/") {
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
