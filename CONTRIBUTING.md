- [Contributing](#contributing)
- [Pull Requests](#pull-requests)
- [Conventional Commits](#conventional-commits)
- [Semantic Versioning](#semantic-versioning)
- [Architecture Guidelines](#architecture-guidelines)
- [Release Process](#release-process)

## Contributing

I'd love to have your help, but please don't skimp on greatness!

Please check the [Issues](https://github.com/isupatches/android-wisefy/issues) to make sure what you want is not already being worked on.

* Please create an issue
    - Please tag the issue with the appropriate labels as well (i.e. status, bug, TODO, etc.)
    - Each issue should have clear details (i.e. steps to reproduce if it's a bug or acceptance criteria if a new feature)

## Pull Requests

For merging and pull request:

* PRs will be squashed and merged after a rebase
    - The commit should have the issue-# in its message
    - [Signed commits](https://docs.github.com/en/github/authenticating-to-github/managing-commit-signature-verification/signing-commits) are required
    - The branch must be up-to-date before merging
    - Conventional commits should be used to denote and differentiate between breaking changes, fixes, chores/tasks, and new features

Some helpful tips to make PRs go smoother:

* Please branch off develop to do any new work
    - Branches should be prefixed with issue-#/ and a short description, example: issue-53/adding-some-feature
* Please make sure all static code analysis checks pass
* Please write tests
* Please make sure current tests pass
* Please help maintain the KDocs and write new ones
* Please help maintain the markdown docs (if necessary)
* Once satisfied with your work, please submit a Pull Request and I'll be happy to review it!  All PRs need to be reviewed by a code owner.

## Conventional Commits

This project strives to follow [conventional commits](https://www.conventionalcommits.org/en/v1.0.0/) where there is a specified format to help standardize commit messages.  This is enforced via the [commit-msg](/.githooks/commit-msg) Git Hook. More on Git Hooks [here](https://git-scm.com/docs/githooks).

The basic format of a commit message is: (type)(optional scope)(breaking change indicator if needed): issue# - description

Examples:
- `feat(addnetowrk): issue25 - Add network connection wizard`
- `fix(core, removenetwork): issue54 - Foobared`
- `feat(wisefy)!: issue999 - Breaking change of doom`

`feat` - A new feature  
`fix` - A bug fix  
`ci` - Changes to CI configuration files and scripts  
`chore` - Changes that don't modify src or test files  
`docs` - Documentation only changes  
`test` - Modifies code for tests without touching production code  
`style` - Changes that do not affect the meaning of the code (white-space, formatting, missing semi-colons, etc)  
`refactor` - A code change that neither fixes a bug nor adds a feature  
`revert` - Reverts a previous commit  
`perf`- A code change that improves performance  
`release` - A commit specific to the [release process](#release-process)  

*Note* Adding `!` before the `:` is meant to denote a breaking change

## Semantic Versioning

Each artifact follows [semantic versioning](https://semver.org/) with a major, minor, and patch version. 

The [BuildVersions](/buildSrc/src/main/java/com/isupatches/android/wisefy/build/BuildVersions.kt) file contains the version of each artifact within Wisefy.

## Architecture Guidelines

Some architecture/code guidelines:
 - Follow the patterns and naming conventions present to ensure consistency
   - The query class is prefixed with function name (f.e. the function `getCurrentNetwork` uses the query class `GetCurrentNetworkQuery`)
   - The request class is prefixed with function name (f.e. the function `addNetwork` uses the request class `AddNetworkRequest`)
   - The result class is prefixed with function name (f.e. the function `removeNetwork` uses the result class `RemoveNetworkResult`)
   - The `ktx` extension functions have the suffix `Async` (f.e. the extension for `getNetworkConnectionStatus` is `getNetworkConnectionStatusAsync`)
   - Keep in-mind the folder and organization structure defined in the [README.md](/README.md)
 - Synchronous, async, and ktx options are supported for each feature (unless there is an exceptional case)
 - Adapters are the only expected location for assertions
 - For `ktx` extension functions, the library deliberately catches any exception and throws a [WisefyException](/wisefy/core/src/main/java/com/isupatches/android/wisefy/core/exceptions/WisefyException.kt)
 - Async operations use coroutines internally and:
   - Execute on the background thread
   - Return results to the main thread
   - Are locked with a mutex if there are potential conflicts with out async functions (f.e. enableWifi, disableWifi, and isWifiEnabled)
 
## Release Process

This requires the keystore and GPG signing to be present locally on the machine so typically I am the only one following this process.  Documentation never hurt and future me may be thankful :P 

1. Create a working branch off of develop
   - The branch should be named `release/wisefy<artifact>/#`. For example, releasing `wisefy:addnetwork` 5.1.0 would look like: `release/wisefy-addnetwork/5.1.0` and releasing `wisefy` 5.1.0 would look like: `release/wisefy/5.1.0`.
2. Bump the version for the artifact within the [BuildVersions](/buildSrc/src/main/java/com/isupatches/android/wisefy/build/BuildVersions.kt)
3. Publish the artifact locally
   - Run `./gradlew wisefy:<artifact>:clean`
   - Run `./gradlew wisefy:<artifact>:publishReleasePublicationToMavenLocal`
4. Publish `wisefy` locally so the [gradlew.lockfile](/wisefy/gradle.lockfile) file can be updated for `wisefy:ktx` with the new version
   - Run `./gradlew wisefy:clean`
   - Run `./gradlew wisefy:publishReleasePublicationToMavenLocal`
5. Publish `wisefy:ktx` locally so the [gradlew.lockfile](/app/gradle.lockfile) file can be updated for `app` with the new version
   - Run `./gradlew wisefy:ktx:clean`
   - Run `./gradlew wisefy:ktx:publishReleasePublicationToMavenLocal`
6. Update the gradle.lockfile files for [wisefy](/wisefy/gradle.lockfile), [wisefy:ktx](/wisefy/ktx/gradle.lockfile), and the sample [app](/app/gradle.lockfile)
   - Run `./gradlew wisefy:depedencies --write-locks`
   - Run `./gradlew wisefy:ktx:depedencies --write-locks`
   - Run `./gradlew app:depedencies --write-locks`
7. Regenerate the markdown documentation 
   - Run `./gradlew dokkaGfmMultiModule`
8. Run the sample app and ensure everything is working as expected
9. Create a commit `release(wisefy:<artifact>): Release wisefy:<artifact> <version number>` with the changes from steps 2-7
10. Update the [CHANGELOG.md](/CHANGELOG.md)
    - Run `scripts/updateChangelog.sh`
    - Commit the update to the [CHANGELOG.md](/CHANGELOG.md)
11. Open up a PR of the release branch into `develop`
12. Wait for CI to pass and then squash and merge the the PR into develop. The result should be a single release commit (`release(wisefy:<artifact>): Release wisefy:<artifact> <version number>`)
13. Create a release in the GitHub UI pointing to the release commit in develop 
    - The title should be `wisefy:<artifact>-5.0.0`
    - The tag should also then be created as `wisefy:<artifact>:5.0.0`
14. Change over locally to the newly created tag
15. Publish the artifact to [MavenCentral](https://search.maven.org/artifact/com.isupatches.android/wisefy)
    - Run `/gradlew wisefy:<artifact>:publishReleasePublicationToReleaseRepository`
16. Reach out to me by opening a GitHub issue in the repository or via email (isuPatches@yahoo.com) and I'll log on and complete the release to MavenCentral
17. Profit

- Artifact hierarchy for releasing:
    - `wisefy:core`
    - `wisefy:<artifact>` (all other artifacts excluding `core` and `ktx`
    - `wisefy`
    - `wisefy:ktx`
