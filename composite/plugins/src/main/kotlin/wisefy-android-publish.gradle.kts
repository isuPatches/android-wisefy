import com.android.build.gradle.LibraryExtension
import java.net.URI

//import com.isupatches.android.wisefy.build.plugins.DEVELOPER_EMAIL
//import com.isupatches.android.wisefy.build.plugins.DEVELOPER_ID
//import com.isupatches.android.wisefy.build.plugins.DEVELOPER_NAME
//import com.isupatches.android.wisefy.build.plugins.GROUP_ID
//import com.isupatches.android.wisefy.build.plugins.LIBRARY_CONNECTION
//import com.isupatches.android.wisefy.build.plugins.LIBRARY_DESCRIPTION
//import com.isupatches.android.wisefy.build.plugins.LIBRARY_NAME
//import com.isupatches.android.wisefy.build.plugins.LIBRARY_URL
//import com.isupatches.android.wisefy.build.plugins.LICENSE_NAME
//import com.isupatches.android.wisefy.build.plugins.LICENSE_URL
//import java.net.URI

private val groupId = "com.isupatches.android.wisefy"

private val developerId = "isuPatches"
private val developerName = "Patches Barrett"
private val developerEmail = "isuPatches@yahoo.com"

private val libraryConnection = "https://github.com/isuPatches/android-wisefy.git"
private val libraryDescription = "Wrapper around WifiManager and ConnectivityManager for Android."
private val libraryName = "Wisefy"
private val libraryUrl = "https://github.com/isuPatches/android-wisefy"

private val licenseName = "The Apache License, Version 2.0"
private val licenseUrl = "http://www.apache.org/licenses/LICENSE-2.0.txt"

plugins {
    id("wisefy-android-documentation")
}
apply<MavenPublishPlugin>()
apply<SigningPlugin>()

// Set group on publishing modules so composite build can pick them up without dependency substitution
group = groupId

if (project is LibraryExtension) {
    configure<LibraryExtension> {
        tasks.create<Jar>("javadocJar") {
            group = JavaBasePlugin.DOCUMENTATION_GROUP
            description = "Assembles Kotlin docs with Dokka"
            archiveClassifier.set("javadoc")
            from(tasks.getByName("dokkaGfm"))
            dependsOn(tasks.getByName("dokkaGfm"))
        }

        tasks.create<Jar>("sourcesJar") {
            archiveClassifier.set("sources")
            from(sourceSets.getByName("main").java.srcDirs)
        }

        publishing {
            singleVariant("debug") {
                withSourcesJar()
                if (System.getenv("GENERATE_DOCS_FOR_DEBUG_PUBLICATIONS").toBoolean()) {
                    withJavadocJar()
                }
            }

            singleVariant("release") {
                withSourcesJar()
                withJavadocJar()
            }
        }
    }
}

afterEvaluate {
    configure<PublishingExtension> {
        publications {
            whenObjectAdded {
                if (this is MavenPublication) {
                    groupId = groupId
                    artifactId = name

                    plugins.withId("com.android.library") {
                        from(components[name])
                    }

                    plugins.withType<JavaPlatformPlugin> {
                        from(components["javaPlatform"])
                    }
                }
            }

            create<MavenPublication>("debug") {
                groupId = groupId
                artifactId = when (project.name) {
                    "bom" -> "wisefy-${project.name}"
                    else -> project.name
                }
                version = project.version.toString()

                pom {
                    name.set(libraryName)
                    description.set(libraryDescription)
                    url.set(libraryUrl)
                    licenses {
                        license {
                            name.set(licenseName)
                            url.set(licenseUrl)
                        }
                    }
                    developers {
                        developer {
                            id.set(developerId)
                            name.set(developerName)
                            email.set(developerEmail)
                        }
                    }
                    scm {
                        connection.set(libraryConnection)
                        developerConnection.set(libraryConnection)
                        url.set(libraryDescription)
                    }
                }
            }

            create<MavenPublication>("release") {
                groupId = groupId
                artifactId = when (project.name) {
                    "bom" -> "wisefy-${project.name}"
                    else -> project.name
                }
                version = project.version.toString()

                pom {
                    name.set(libraryName)
                    description.set(libraryDescription)
                    url.set(libraryUrl)
                    licenses {
                        license {
                            name.set(licenseName)
                            url.set(licenseUrl)
                        }
                    }
                    developers {
                        developer {
                            id.set(developerId)
                            name.set(developerName)
                            email.set(developerEmail)
                        }
                    }
                    scm {
                        connection.set(libraryConnection)
                        developerConnection.set(libraryConnection)
                        url.set(libraryUrl)
                    }
                }

                repositories {
                    maven {
                        name = "Release"
                        url = URI("https://oss.sonatype.org/service/local/staging/deploy/maven2")
                        credentials {
                            username = providers.gradleProperty("SONATYPE_USERNAME").get()
                            password = providers.gradleProperty("SONATYPE_PASSWORD").get()
                        }
                    }

                    maven {
                        name = "Snapshot"
                        url = URI("https://oss.sonatype.org/content/repositories/snapshots")
                        credentials {
                            username = providers.gradleProperty("SONATYPE_USERNAME").get()
                            password = providers.gradleProperty("SONATYPE_PASSWORD").get()
                        }
                    }
                }
            }

            configure<SigningExtension> {
                val signingKeyId: String = providers.gradleProperty("WISEFY_GPG_SIGNING_KEY_ID").get()
                val signingKey: String = providers.gradleProperty("WISEFY_GPG_SIGNING_KEY").get()
                val signingPassword: String = providers.gradleProperty("WISEFY_GPG_SIGNING_PASSWORD").get()
                useInMemoryPgpKeys(signingKeyId, signingKey, signingPassword)
                sign(publications)
            }
        }
    }
}
