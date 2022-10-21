I'd love to have your help, but please don't skimp on greatness!

Please check the [Issues](https://github.com/isupatches/android-wisefy/issues) and the [Main Board](https://github.com/isuPatches/android-wisefy/projects/1) to make sure what you want is not already being worked on.

* Please create an issue and use the [Main Board](https://github.com/isuPatches/android-wisefy/projects/1) to denote its status
    - Please tag the issue with the appropriate labels as well (i.e. status, bug, TODO, etc.)
    - Each issue should have clear details (i.e. steps to reproduce if it's a bug or acceptance criteria if a new feature)
* Please branch off develop to do any new work
    - Branches should be prefixed with issue-#/ and a short description, example: issue-53/adding-some-feature
* Please make sure all static code analysis checks pass
* Please write tests
* Please make sure current tests pass
* Please help maintain the KDocs and write new ones
* Please help maintain the markdown docs (if necessary)
* Once satisfied with your work, please submit a Pull Request and I'll be happy to review it!  All PRs need to be reviewed by a code owner.

For merging:

* PRs will be squashed and merged after a rebase
    - The commit should have the issue-# in its message
    - [Signed commits](https://docs.github.com/en/github/authenticating-to-github/managing-commit-signature-verification/signing-commits) are required
    - The branch must be up-to-date before merging
    - Conventional commits should be used to denote and differentiate between breaking changes, fixes, chores/tasks, and new features

Some architecture/code guidelines:
 - Follow the patterns and naming conventions present to ensure consistency
   - The query class is prefixed with function name (f.e. the function `getCurrentNetwork` uses the query class `GetCurrentNetworkQuery`)
   - The request class is prefixed with function name (f.e. the function `addNetwork` uses the request class `AddNetworkRequest`)
   - The result class is prefixed with function name (f.e. the function `reemoveNetwork` uses the result class `RemoveNetworkResult`)
   - The `ktx` extension functions have the suffix `Async` (f.e. the extension for `getNetworkConnectionStatus` is `getNetworkConnectionStatusAsync`)
   - Keep in-mind the folder and organization structure defined in the [README.md](/README.md)
 - Synchronous, async, and ktx options are supported for each feature (unless there is an exceptional case)
 - Adapters are the only expected location for assertions
 - For `ktx` extension functions, the library deliberately catches any exception and throws a [WisefyException](/wisefy/core/src/main/java/com/isupatches/android/wisefy/core/exceptions/WisefyException.kt)
 - Async operations use coroutines internally and:
   - Execute on the background thread
   - Return results to the main thread
   - Are locked with a mutex if there are potential conflicts with out async functions (f.e. enableWifi, disableWifi, and isWifiEnabled)
 
For releasing:

  - The CHANGELOG.md will be generated once per release
  - The release task will be run to regenerate all of the KDocs 
  - The version codes and version names will be bumped in a release PR
