/*
 * Copyright 2022 Patches Barrett
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.isupatches.android.wisefy.build.plugins

import com.android.build.gradle.LibraryExtension
import com.isupatches.android.wisefy.build.PublishingConstants
import java.io.File
import java.net.URI
import java.util.Properties
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaBasePlugin
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.bundling.Jar
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.get
import org.gradle.plugins.signing.SigningExtension

private const val DEVELOPER_ID = "isuPatches"
private const val DEVELOPER_NAME = "Patches Barrett"
private const val DEVELOPER_EMAIL = "isuPatches@yahoo.com"

private const val LIBRARY_CONNECTION = "https://github.com/isuPatches/android-wisefy.git"
private const val LIBRARY_DESCRIPTION = "Wrapper around WifiManager and ConnectivityManager for Android."
private const val LIBRARY_NAME = "Wisefy"
private const val LIBRARY_URL = "https://github.com/isuPatches/android-wisefy"

private const val LICENSE_NAME = "The Apache License, Version 2.0"
private const val LICENSE_URL = "http://www.apache.org/licenses/LICENSE-2.0.txt"

class PublishingPlugin : Plugin<Project> {

    override fun apply(target: Project) {

        target.apply(plugin = "maven-publish")
        target.apply(plugin = "signing")

        val localProperties = Properties()
        val localFile: File = target.rootProject.file("local.properties")
        if (localFile.exists()) {
            localFile.inputStream().use { localProperties.load(it) }
        }

        target.configure<LibraryExtension> {

            target.tasks.create<Jar>("javadocJar") {
                group = JavaBasePlugin.DOCUMENTATION_GROUP
                description = "Assembles Kotlin docs with Dokka"
                archiveClassifier.set("javadoc")
                from(target.tasks.getByName("dokkaGfm"))
                dependsOn(target.tasks.getByName("dokkaGfm"))
            }

            target.tasks.create<Jar>("sourcesJar") {
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

        target.afterEvaluate {
            configure<PublishingExtension> {
                publications {
                    create<MavenPublication>("debug") {
                        groupId = PublishingConstants.GROUP_ID
                        artifactId = project.name
                        version = project.version.toString()

                        from(project.components["debug"])

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
                        groupId = PublishingConstants.GROUP_ID
                        artifactId = project.name
                        version = project.version.toString()

                        from(project.components["release"])

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

                    repositories {
                        maven {
                            name = "Release"
                            url = URI("https://oss.sonatype.org/service/local/staging/deploy/maven2")
                            credentials {
                                username = System.getenv("SONATYPE_USERNAME") ?: localProperties["sonatype.username"].toString()
                                password = System.getenv("SONATYPE_PASSWORD") ?: localProperties["sonatype.password"].toString()
                            }
                        }

                        maven {
                            name = "Snapshot"
                            url = URI("https://oss.sonatype.org/content/repositories/snapshots")
                            credentials {
                                username = System.getenv("SONATYPE_USERNAME") ?: localProperties["sonatype.username"].toString()
                                password = System.getenv("SONATYPE_PASSWORD") ?: localProperties["sonatype.password"].toString()
                            }
                        }
                    }
                }

                configure<SigningExtension> {
                    useInMemoryPgpKeys(
                        System.getenv("SIGNING_KEY_ID") ?: localProperties["signing.keyId"].toString(),
                        System.getenv("SIGNING_KEY") ?: localProperties["signing.key"].toString(),
                        System.getenv("SIGNING_PASSWORD") ?: localProperties["signing.password"].toString(),
                    )
                    sign(publications)
                }
            }
        }
    }
}
