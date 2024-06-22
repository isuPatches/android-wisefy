rootProject.name = "android-wisefy"

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
    // For composite + conventional Gradle plugins
    includeBuild("composite")
}

include(
    ":app",
    ":wisefy",
    ":wisefy:bom",
    ":wisefy:core",
    ":wisefy:accesspoints",
    ":wisefy:addnetwork",
    ":wisefy:ktx",
    ":wisefy:networkconnection",
    ":wisefy:networkinfo",
    ":wisefy:savednetworks",
    ":wisefy:removenetwork",
    ":wisefy:signal",
    ":wisefy:wifi",
    ":testsupport"
)

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenLocal()
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("testLibs") {
            from(files("./gradle/test-libs.versions.toml"))
        }
    }
}
