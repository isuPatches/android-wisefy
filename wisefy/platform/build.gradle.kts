import com.isupatches.android.wisefy.build.BuildVersions
import com.isupatches.android.wisefy.build.PublishingConstants

plugins {
    id("java-platform")
    id("maven-publish")
}

group = PublishingConstants.GROUP_ID
version = BuildVersions.MODULE_VERSION_NAME

publishing {
    publications {
        create<MavenPublication>("wisefyPlatform") {
            artifactId = project.name
            from(components["javaPlatform"])
        }
    }
}

dependencies {
    constraints {
        api(project(":wisefy"))
        api(project(":wisefy:accesspoints"))
        api(project(":wisefy:addnetwork"))
        api(project(":wisefy:core"))
        api(project(":wisefy:frequency"))
        api(project(":wisefy:networkconnection"))
        api(project(":wisefy:networkconnectionstatus"))
        api(project(":wisefy:networkinfo"))
        api(project(":wisefy:removenetwork"))
        api(project(":wisefy:savednetworks"))
        api(project(":wisefy:security"))
        api(project(":wisefy:signal"))
        api(project(":wisefy:wifi"))
    }
}
