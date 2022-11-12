import org.jmailen.gradle.kotlinter.KotlinterExtension
import org.jmailen.gradle.kotlinter.KotlinterPlugin

buildscript {
    repositories {
        maven(url = "https://plugins.gradle.org/m2/")
    }

    dependencies {
        val versions = com.isupatches.android.wisefy.build.Versions
        classpath("org.jmailen.gradle:kotlinter-gradle:${versions.KOTLINTER_PLUGIN}")
    }
}

plugins.apply(KotlinterPlugin::class)

//configure<KotlinterExtension> {
//    ignoreFailures = false
//    reporters = arrayOf("checkstyle", "plain")
//    experimentalRules = false
//    disabledRules = emptyArray()
//}
