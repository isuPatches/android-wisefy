## Wisefy

<img src="Banner.png" width="40%" height="40%" />

A Wifi configuration and util library built in Kotlin for Android.

> Developed by Patches 04/24/2016 - present  
> Logo/icon created by mansya (2018)  
> 
> Supports Android SDK levels 23-33

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-WiseFy-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/6011) [![Android Weekly](https://img.shields.io/badge/Android%20Weekly-%23230-blue.svg)](http://androidweekly.net/issues/issue-230)

[![CircleCI](https://dl.circleci.com/status-badge/img/gh/isuPatches/android-wisefy/tree/develop.svg?style=svg)](https://dl.circleci.com/status-badge/redirect/gh/isuPatches/android-wisefy/tree/develop)

- [Installation](#installation)
- [KTX Artifact](#ktx-artifact)
- [5.0 Rewrite](#50-rewrite)
  - [Highlights](#highlights)
  - [New Structure](#new-structure)
  - [Packaging & Naming Conventions](#packaging-and-naming-conventions)
  - [Deprecations](#deprecations)
  - [Known Android Q Problems](#known-android-q-problems)
- [Documentation](#documentation)
  - [Reset](#reset)
  - [Links](#links)
- [FAQ](#faq)
- [For Contributors](#for-contributors)
- [License](#license)

## Installation

There is a new package for 5.0.  Please use:

```kotlin
implementation("com.isupatches.android.wisefy:wisefy:5.0.0-RC3")
```

This will include all of the Wisefy sub-artifacts through `api` dependencies.

There are also new more modular artifacts published so that individual pieces of Wisefy can be imported directly:

> `com.isupatches.android.wisefy:core:<LATEST VERSION>` will be a requirement for the other Wisefy artifacts.

- `com.isupatches.android.wisefy:accesspoints:<LATEST VERSION>`
- `com.isupatches.android.wisefy:addnetwork:<LATEST VERSION>`
- `com.isupatches.android.wisefy:core:<LATEST VERSION>`
- `com.isupatches.android.wisefy:networkconnection:<LATEST VERSION>`
- `com.isupatches.android.wisefy:networkinfo:<LATEST VERSION>`
- `com.isupatches.android.wisefy:removenetwork:<LATEST VERSION>`
- `com.isupatches.android.wisefy:savednetworks:<LATEST VERSION>`
- `com.isupatches.android.wisefy:signal:<LATEST VERSION>`
- `com.isupatches.android.wisefy:wifi:<LATEST VERSION>`

Here are the descriptions of what functionality each artifact provides:

- `:accesspoints` For getting and searching for nearby networks
- `:addnetwork` For adding a Wifi network
- `:core` For base Wisefy functionality
- `:networkconnection` For connecting and disconnecting from networks
- `:networkinfo` For information about the device's current network and current connectivity status
- `:removenetwork` For removing a Wifi network
- `:savednetworks` For getting and searching for saved networks
- `:signal` For calculating a signal level and comparing signal levels
- `:wifi` For enabling and disabling Wifi

## KTX Artifact

There is a new artifact for 5.0 that provides Kotlin extension functions.  Please use:

```kotlin
implementation("com.isupatches.android.wisefy:ktx:<LATEST VERSION>")
```

if you want to try it out. All functions in this package have the suffix `Async` and are `suspend` functions.

## 5.0 Rewrite

The 5.0 version of WiseFy works to rectify the problems that caused it to be overly challenging as a single developer 
to keep up with the ever-changing Wifi APIs for new Android operating systems, especially with a lot of functionality 
becoming privatized.  It also strives to simplify the API and removes redundancy within the APIs that caused additional
overhead for maintenance.

I hope you enjoy the rewrite and please create an issue if you see anything odd or have questions!

### Highlights

- Android Q, Android R, Android S, and Android T are now supported
- Compiled with Java 11
- Rewritten with extensibility and future Android OS's in-mind
    * Future versions of the Android OS will be easier to support with the new delegate/adapter system
    * Improved modularity where APIs for OS versions are contained in their own API / API implementation files
- Async operations updated to internally leverage Coroutines and a new exception handler
- Returns are now Wisefy classes opposed to a class from the OS, making it easier to add new variants in the future 
  through sealed classes
- Updated names for callbacks
- Kotlin first mentality (but willing to support Java as first class too!)
- WPA3 networks are now supported
- `wisefysample` renamed to `app`
- BSSID support is now added
- More modular artifacts are available now 
  - Less bloat if there are things you're not going to use
  - Able to iterate and update portions of the library without the overhead of the entire project
- `gradle.lockfile`s and Gradle ranges are enabled for more control over versioning
  - [Locking dependency versions](https://docs.gradle.org/current/userguide/dependency_locking.html)
  - [Declaring Versions and Ranges](https://docs.gradle.org/current/userguide/single_versions.html)
- Sample app re-written
  - Down with SharedPreferences, onward to DataStore
  - Written in Compose complete with previews
  - Niceties like progress bars added
  - Better input validation

### New Structure

- Wisefy public API -> Wisefy implementation -> Delegate to determine which adapter to use -> 
  Adapter converts request for usage by the Android OS -> Android OS APIs are used and the adapter returns information
  as a Wisefy result

### Packaging and Naming Conventions

Types of classes:

- `Query` - Indicates a read only operation where nothing is modified or written
- `Request` - Indicates an action where some state is modified or a value is written
- `Result` - Indicates the return from an action or query
- `Data` - Indicates a complex return for a result that includes several different properties, fields, and functions

Types of property values:

- `value` - A field within a `Result` object for a `Data` class (a Wisefy based class)
- `rawValue` - A field for a OS level class or object (not a Wisefy based class) within a `Data` object

Suffixes:

- Api (top-level of a feature package) -> Contains the definitions of synchronous APIs available for the feature
- ApiAsync (top-level of a feature package) -> Contains the definitions of asynchronous APIs available for the feature
- Delegate -> Determines which adapter to use based on the Android device's SDK level
- Adapter -> Middleware that converts requests and responses between the API and Delegate layers 
- Api (within os package context) -> Houses the definitions of Android OS level APIs that Adapter classes may use
- ApiImpl (within os package context) -> Houses the implementation for executing Android OS level APIs

Package structure for each section is as follows:

- Feature (accesspoints, addnetwork, etc.) -> Location of Wisefy Specific delegate -> supporting sub-directories
  
*Supporting sub-directories can include*

- callbacks (public) -> Location of callback interfaces for returns from async calls
- entities (public) -> Location of data classes for queries, requests, responses, and data
- os (internal) - Purely on organizational directory
  - adapters (internal) -> Location of the classes that convert requests and responses between the Delegate and Android 
    OS level APIs
  - converters (internal) -> Location of helpers to convert one data class to another
  - apis (internal) -> Location of the API interfaces to talk to the Android OS
  - impls (internal) -> Location of the implementation for an API that talks to the Android OS

### Deprecations

- `fun connectToNetwork(request: ConnectToNetworkRequest): ConnectToNetworkResult` 
- `fun connectToNetwork(request: ConnectToNetworkRequest, callbacks: ConnectToNetworkCallbacks?)`
- `fun disconnectFromCurrentNetwork(request: DisconnectFromCurrentNetworkRequest): DisconnectFromCurrentNetworkResult`
- `fun disconnectFromCurrentNetwork(request: DisconnectFromCurrentNetworkRequest, callbacks: DisconnectFromCurrentNetworkCallbacks?)`

### Known Android Q Problems

- Adding, removing, and getting saved network functionality is not present. 
  Android Q / SDK 29 was in a weird half-baked state between the old WifiManager APIs and the new WifiSuggestion APIs...Android 30 seems to have full support, but Android 29 really drops the ball for saved network functionality.

## Documentation

### Reset

To keep the documentation clean and because of the amount of architectural changes for the 5.x release, the 
documentation was stripped and then completely re-written.

## Links

For auto-generated [Dokka](https://kotlin.github.io/dokka) markdown files based on the KDocs please see 
[here](/dokka/index.md).

For more high-level examples based on different functionality please see [here](/documentation/index.md).

## FAQ

You may find a list of frequently asked questions [here](/documentation/FAQ.md).

## For Contributors

Want to help? Have an idea for a cool feature or want to fix a bug for the community? See 
[CONTRIBUTING](CONTRIBUTING.md) for how you can make impactful changes for Wisefy.

## License ##
Copyright 2022 Patches Barrett

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in
compliance with the License. You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License
is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
or implied. See the License for the specific language governing permissions and limitations under
the License.
