import java.net.URI

plugins {
    id("wisefy-android-documentation")
}
apply<MavenPublishPlugin>()
apply<SigningPlugin>()

// Set group on publishing modules so composite build can pick them up without dependency substitution
group = GROUP_ID

tasks{
    register<Jar>("javadocJar") {
        group = JavaBasePlugin.DOCUMENTATION_GROUP
        description = "Assembles Kotlin docs with Dokka"
        archiveClassifier.set("javadoc")
        from(tasks.getByName("dokkaGfm"))
        dependsOn(tasks.getByName("dokkaGfm"))
    }
}

afterEvaluate {
    configure<PublishingExtension> {
        // Configure repositories
        repositories {
            maven {
                name = "Release"
                url = URI("https://oss.sonatype.org/service/local/staging/deploy/maven2")
                credentials {
                    username =
                        System.getenv("SONATYPE_USERNAME") ?: providers.gradleProperty("SONATYPE_USERNAME").get()
                    password =
                        System.getenv("SONATYPE_PASSWORD") ?: providers.gradleProperty("SONATYPE_PASSWORD").get()
                }
            }

            maven {
                name = "Snapshot"
                url = URI("https://oss.sonatype.org/content/repositories/snapshots")
                credentials {
                    username =
                        System.getenv("SONATYPE_USERNAME") ?: providers.gradleProperty("SONATYPE_USERNAME").get()
                    password =
                        System.getenv("SONATYPE_PASSWORD") ?: providers.gradleProperty("SONATYPE_PASSWORD").get()
                }
            }
        }

        // Configure signing
        configure<SigningExtension> {
            useInMemoryPgpKeys(
                System.getenv("WISEFY_GPG_SIGNING_KEY_ID") ?: providers.gradleProperty("WISEFY_GPG_SIGNING_KEY_ID").get(),
                System.getenv("WISEFY_GPG_SIGNING_KEY") ?: providers.gradleProperty("WISEFY_GPG_SIGNING_KEY").get(),
                System.getenv("WISEFY_GPG_SIGNING_PASSWORD") ?: providers.gradleProperty("WISEFY_GPG_SIGNING_PASSWORD").get(),
            )
            sign(publications)
        }

        // Configure publications
        publications {
            create<MavenPublication>("debug") {
                groupId = GROUP_ID
                artifactId = project.name
                version = project.version.toString()

                from(project.components["debug"])
                artifact(tasks.named<Jar>("javadocJar"))

                pom {
                    name.set(LIBRARY_NAME)
                    description.set(LIBRARY_DESCRIPTION)
                    url.set(LIBRARY_URL)
                    licenses {
                        license {
                            name.set(LICENSE_NAME)
                            url.set(LICENSE_URL)
                        }
                    }
                    developers {
                        developer {
                            id.set(DEVELOPER_ID)
                            name.set(DEVELOPER_NAME)
                            email.set(DEVELOPER_EMAIL)
                        }
                    }
                    scm {
                        connection.set(LIBRARY_CONNECTION)
                        developerConnection.set(LIBRARY_CONNECTION)
                        url.set(LIBRARY_DESCRIPTION)
                    }
                }
            }

            create<MavenPublication>("release") {
                groupId = GROUP_ID
                artifactId = project.name
                version = project.version.toString()

                from(project.components["release"])
                artifact(tasks.named<Jar>("javadocJar"))

                pom {
                    name.set(LIBRARY_NAME)
                    description.set(LIBRARY_DESCRIPTION)
                    url.set(LIBRARY_URL)
                    licenses {
                        license {
                            name.set(LICENSE_NAME)
                            url.set(LICENSE_URL)
                        }
                    }
                    developers {
                        developer {
                            id.set(DEVELOPER_ID)
                            name.set(DEVELOPER_NAME)
                            email.set(DEVELOPER_EMAIL)
                        }
                    }
                    scm {
                        connection.set(LIBRARY_CONNECTION)
                        developerConnection.set(LIBRARY_CONNECTION)
                        url.set(LIBRARY_DESCRIPTION)
                    }
                }
            }
        }
    }
}
