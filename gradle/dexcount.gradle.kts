import com.getkeepsafe.dexcount.DexCountExtension
import com.getkeepsafe.dexcount.DexMethodCountPlugin
import com.getkeepsafe.dexcount.OutputFormat

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

configure<DexCountExtension> {
    format = OutputFormat.TREE
    includeClasses = true
    includeFieldCount = true
    includeTotalMethodCount = true
    orderByMethodCount = true
    verbose = false
    runOnEachPackage = true
}
