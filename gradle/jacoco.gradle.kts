plugins.apply(JacocoPlugin::class)

fun getCoverageVariants() = listOf("debug")
val buildTaskGroup = "verification"

val excludes = setOf(
    "**/R.class",
    "**/R\$*.class",
    "**/BuildConfig.*",
    "**/Manifest*.*",
    "**/*\$Lambda\$*.class",
    "**/*Factory*.class",
    "**/*\$Builder*"
)

fun getSourceDirectoriesTree() = files(
    "src/main/java"
)

fun getClassDirectoriesTree(excludes: Set<String>): FileTree {
    return fileTree("${project.buildDir}") {
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
        tasks.create<JacocoReport>("jacoco${variant.capitalize()}UnitTest") {
            group = buildTaskGroup

            dependsOn("test${variant.capitalize()}UnitTest")

            classDirectories.setFrom(getClassDirectoriesTree(excludes))
            sourceDirectories.setFrom(getSourceDirectoriesTree())
            executionData.setFrom(
                fileTree("${project.buildDir}/outputs/unit_test_code_coverage") {
                    include("**/*.exec")
                }
            )

            reports {
                xml.required.set(false)
                html.required.set(true)
            }
        }

        tasks.create<JacocoReport>("jacoco${variant.capitalize()}UnitTestReport") {
            group = buildTaskGroup

            classDirectories.setFrom(getClassDirectoriesTree(excludes))
            sourceDirectories.setFrom(getSourceDirectoriesTree())
            executionData.setFrom(
                fileTree("${project.buildDir}/outputs/unit_test_code_coverage") {
                    include("**/*.exec")
                }
            )

            reports {
                xml.required.set(false)
                html.required.set(true)
            }
        }

        tasks.create<JacocoReport>("jacoco${variant.capitalize()}AndroidTest") {
            group = buildTaskGroup

            dependsOn("connected${variant.capitalize()}AndroidTest")

            classDirectories.setFrom(getClassDirectoriesTree(excludes))
            sourceDirectories.setFrom(getSourceDirectoriesTree())
            executionData.setFrom(
                fileTree("${project.buildDir}/outputs/code_coverage/") {
                    include("**/*.ec")
                }
            )

            reports {
                xml.required.set(false)
                html.required.set(true)
            }
        }

        tasks.create<JacocoReport>("jacoco${variant.capitalize()}AndroidTestReport") {
            group = buildTaskGroup

            classDirectories.setFrom(getClassDirectoriesTree(excludes))
            sourceDirectories.setFrom(getSourceDirectoriesTree())
            executionData.setFrom(
                fileTree("${project.buildDir}/outputs/code_coverage/") {
                    include("**/*.ec")
                }
            )

            reports {
                xml.required.set(false)
                html.required.set(true)
            }
        }

        tasks.create<JacocoReport>("jacoco${variant.capitalize()}CombinedTest") {
            group = buildTaskGroup

            dependsOn("test${variant.capitalize()}UnitTest", "connected${variant.capitalize()}AndroidTest")

            classDirectories.setFrom(getClassDirectoriesTree(excludes))
            sourceDirectories.setFrom(getSourceDirectoriesTree())
            executionData.setFrom(
                fileTree("${project.buildDir}/outputs/unit_test_code_coverage") {
                    include("**/*.exec")
                },
                fileTree("${project.buildDir}/outputs/code_coverage/") {
                    include("**/*.ec")
                }
            )

            reports {
                xml.required.set(false)
                html.required.set(true)
            }
        }

        tasks.create<JacocoReport>("jacoco${variant.capitalize()}CombinedTestReport") {
            group = buildTaskGroup

            classDirectories.setFrom(getClassDirectoriesTree(excludes))
            sourceDirectories.setFrom(getSourceDirectoriesTree())
            executionData.setFrom(
                fileTree("${project.buildDir}/outputs/unit_test_code_coverage") {
                    include("**/*.exec")
                },
                fileTree("${project.buildDir}/outputs/code_coverage/") {
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
