## Wisefy

<img src="Banner.png" width="40%" height="40%" />

A Wifi configuration and util library built in Kotlin for Android.

> Developed by Patches 04/24/2016 - present  
> Logo/icon created by mansya (2018)  
> 
> Supports Android SDK levels 23-32

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-WiseFy-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/6011) [![Android Weekly](https://img.shields.io/badge/Android%20Weekly-%23230-blue.svg)](http://androidweekly.net/issues/issue-230)

- [Installation](#installation)
- [5.0 Rewrite](#5.0-rewrite)
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
implementation("com.isupatches.android:wisefy:5.0.0-RC3")
```

This will include all of the Wisefy sub-artifacts through `api` dependencies.

There are also new more modular artifacts published so that individual pieces of Wisefy can be imported directly:

> `com.isupatches.android.wisefy:core:<LATEST VERSION>` will be a requirement for the other Wisefy artifacts.

- `com.isupatches.android.wisefy:core:<LATEST VERSION>`
- `com.isupatches.android.wisefy:accesspoints:<LATEST VERSION>`
- `com.isupatches.android.wisefy:addnetwork:<LATEST VERSION>`
- `com.isupatches.android.wisefy:frequency:<LATEST VERSION>`
- `com.isupatches.android.wisefy:networkconnection:<LATEST VERSION>`
- `com.isupatches.android.wisefy:networkconnectionstatus:<LATEST VERSION>`
- `com.isupatches.android.wisefy:networkinfo:<LATEST VERSION>`
- `com.isupatches.android.wisefy:removenetwork:<LATEST VERSION>`
- `com.isupatches.android.wisefy:savednetworks:<LATEST VERSION>`
- `com.isupatches.android.wisefy:security:<LATEST VERSION>`
- `com.isupatches.android.wisefy:signal:<LATEST VERSION>`
- `com.isupatches.android.wisefy:wifi:<LATEST VERSION>`

Here are the descriptions of what functionality each artifact provides:

- `:core` For base Wisefy functionality
- `:accesspoints` For getting and searching for nearby networks
- `:addnetwork` For getting and searching for nearby networks
- `:frequency` For getting the frequency of a network
- `:networkconnection` For connecting and disconnecting from networks
- `:networkconnectionstatus` For current network status (wifi, mobile, connected, etc.)
- `:networkinfo` For information about the device's current network
- `:removenetwork` For removing a Wifi network
- `:savednetworks` For getting and searching for saved networks
- `:security` For determining the security capabilities of a network
- `:signal` For calculating signal strength bars and comparing signal strength
- `:wifi` For enabling and disabling Wifi

## 5.0 Rewrite

The 5.0 version of WiseFy works to rectify the problems that caused it to be overly challenging as a single developer 
to keep up with the ever-changing Wifi APIs for new Android operating systems, especially with a lot of functionality 
becoming privatized.

I hope you enjoy the rewrite and please create an issue if you see anything odd or have questions!

### Highlights

- Android P, Android Q, Android R, and Android S are now supported
- Compiled with Java 11
- Rewritten with extensibility and future Android OS's in-mind
    * Future versions of the Android OS will be easier to support with the new delegate/adapter system
    * Improved modularity where APIs for OS versions are contained in their own API / API implementation files
- Async operations updated to internally leverage Coroutines and a new exception handler
- Returns are now Wisefy classes opposed to a class from the OS, making it easier to add new variants in the future 
  through sealed classes
- Updated names for callbacks
- Kotlin first mentality (but willing to support Java as first class too!)
- WPA3 networks now supported
- `wisefysample` renamed to `app`
- BSSID support is now added
- More modular artifacts are available now 
  - Less bloat if there are things you're not going to use
  - Able to iterate and update portions of the library without the overhead of the entire project
- `gradle.lockfile`s and Gradle ranges are enabled for more control over versioning
  - [Locking dependency versions](https://docs.gradle.org/current/userguide/dependency_locking.html)
  - [Declaring Versions and Ranges](https://docs.gradle.org/current/userguide/single_versions.html)

### New Structure

- Wisefy public API -> Wisefy implementation -> Delegate to determine which adapter to use -> 
  Adapter converts request for usage by the Android OS -> Android OS APIs are used and the adapter returns information
  as a Wisefy result

### Packaging and Naming Conventions

Suffixes:

- Api (top-level of a feature package) -> Contains the definitions of synchronous APIs available for the feature
- ApiAsync (top-level of a feature package) -> Contains the definitions of asynchronous APIs available for the feature
- Delegate -> Determines which adapter to use based on the Android device's SDK level
- Adapter -> Middleware that converts requests and responses between the API and Delegate layers 
- Api (within os package context) -> House the definitions of Android OS level APIs that Adapter classes may use
- ApiImpl (within os package context) -> House the implementation for executing Android OS level APIs

Package structure for each section is as follows:

- Feature (accesspoints, addnetwork, etc.) -> Location of Wisefy Specific delegate -> supporting sub-directories
  
*Supporting sub-directories can include*

- callbacks (public) -> Location of callback interfaces for async responses/requests
- entities (public) -> Location of data classes for requests, responses, and data
- os (internal) - Purely on organizational directory
  - adapters (internal) -> Location of the classes that convert requests and responses between the Delegate and Android 
    OS level APIs
  - converters (internal) -> Location of helpers to convert one data class to another
  - apis (internal) -> Location of the API interfaces to talk to the Android OS
  - impls (internal) -> Location of the implementation for an API that talks to the Android OS

### Deprecations

- disableWifi() and disableWifi(callbacks: DisableWifiCallbacks?)
- enableWifi() and enableWifi(callbacks: EnableWifiCallbacks?)
- calculateBars(rssiLevel: Int, targetNumberOfBars: Int)

### Known Android Q Problems

- Saving a network doesn't seem possible.  A notification is presented if not connected to the suggestion, but even the 
appearance of the notification seems flakey

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
Copyright 2022 Patches Klinefelter

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in
compliance with the License. You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License
is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
or implied. See the License for the specific language governing permissions and limitations under
the License.
