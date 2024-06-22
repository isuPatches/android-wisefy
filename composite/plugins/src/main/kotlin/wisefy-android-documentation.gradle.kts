import org.jetbrains.dokka.gradle.DokkaMultiModuleTask
import org.jetbrains.dokka.gradle.DokkaPlugin
import org.jetbrains.dokka.gradle.DokkaTaskPartial

apply<DokkaPlugin>()

// The top level project runs dokkaGfmMultiModule, then Dokka iterates through subprojects and either runs
// dokkaGfmMultiModule or dokkaGfmPartial, depending on if it finds children projects.
// We only want to set the outputDirectory for the top level task, and leave the default values alone for the rest.
if (project === rootProject) {
    tasks.getByName<DokkaMultiModuleTask>("dokkaGfmMultiModule") {
        outputDirectory.set(file("${rootProject.projectDir}/documentation"))
    }
} else {
    tasks.getByName<DokkaTaskPartial>("dokkaGfmPartial") {
        moduleName.set(name)
        dokkaSourceSets.configureEach {
            skipEmptyPackages.set(true)
            suppressGeneratedFiles.set(true)
        }
    }
}
