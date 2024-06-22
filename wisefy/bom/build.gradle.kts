plugins {
    id(libs.plugins.java.platform.get().pluginId)
    id(libs.plugins.wisefy.android.publish.get().pluginId)
}

javaPlatform {
    allowDependencies()
}

dependencies {
    constraints {
        api(project(":wisefy:accesspoints"))
        api(project(":wisefy:addnetwork"))
        api(project(":wisefy"))
        api(project(":wisefy:core"))
        api(project(":wisefy:ktx"))
        api(project(":wisefy:networkconnection"))
        api(project(":wisefy:networkinfo"))
        api(project(":wisefy:removenetwork"))
        api(project(":wisefy:savednetworks"))
        api(project(":wisefy:signal"))
        api(project(":wisefy:wifi"))
    }
}
