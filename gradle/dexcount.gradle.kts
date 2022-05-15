import com.getkeepsafe.dexcount.DexMethodCountPlugin

buildscript {
    repositories {
        mavenCentral()
    }

    dependencies {
        val versions = com.isupatches.android.wisefy.build.Versions
        classpath("com.getkeepsafe.dexcount:dexcount-gradle-plugin:${versions.DEXCOUNT}")
    }
}

plugins.apply(DexMethodCountPlugin::class)
