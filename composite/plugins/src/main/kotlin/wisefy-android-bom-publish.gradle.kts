import java.net.URI

apply<MavenPublishPlugin>()
apply<SigningPlugin>()

afterEvaluate {
    configure<PublishingExtension> {
        // Configure repositories
        repositories {
            maven {
                name = "Release"
                url = URI("https://oss.sonatype.org/service/local/staging/deploy/maven2")
                credentials {
                    username =
                        properties["SONATYPE_USERNAME"]?.toString() ?: System.getenv("SONATYPE_USERNAME")
                    password =
                        properties["SONATYPE_PASSWORD"]?.toString() ?: System.getenv("SONATYPE_PASSWORD")
                }
            }

            maven {
                name = "Snapshot"
                url = URI("https://oss.sonatype.org/content/repositories/snapshots")
                credentials {
                    username =
                        properties["SONATYPE_USERNAME"]?.toString() ?: System.getenv("SONATYPE_USERNAME")
                    password =
                        properties["SONATYPE_PASSWORD"]?.toString() ?: System.getenv("SONATYPE_PASSWORD")
                }
            }
        }

        // Configure signing
        configure<SigningExtension> {
            val signingKeyId = properties["WISEFY_GPG_SIGNING_KEY_ID"]?.toString() ?: System.getenv("WISEFY_GPG_SIGNING_KEY_ID")
            val signingKey = properties["WISEFY_GPG_SIGNING_KEY"]?.toString() ?: System.getenv("WISEFY_GPG_SIGNING_KEY")
            val signingKeyPassword = properties["WISEFY_GPG_SIGNING_PASSWORD"]?.toString() ?: System.getenv("WISEFY_GPG_SIGNING_PASSWORD")
            useInMemoryPgpKeys(signingKeyId, signingKey, signingKeyPassword)
            sign(publications)
        }

        // Configure publications
        publications {
            create<MavenPublication>("release") {
                groupId = GROUP_ID
                artifactId = "wisefy-bom"
                version = project.version.toString()

                from(components["javaPlatform"])

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

            create<MavenPublication>("debug") {
                groupId = GROUP_ID
                artifactId = "wisefy-bom"
                version = project.version.toString()

                from(components["javaPlatform"])

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
