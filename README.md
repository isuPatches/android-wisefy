## Wisefy

## Installation

There is a new package for 5.0.  Please use:

```kotlin
implementation("com.isupatches.android:wisefy:5.0.0-RC2")
```

## 5.0 Rewrite

The 5.0 version of WiseFy works to rectify the problems that caused it to be overly challenging as a single developer 
to keep up with the ever-changing Wifi APIs for new Android operating systems, especially with a lot of functionality 
becoming privatized.

I hope you enjoy the rewrite and please create an issue if you see anything odd or have questions!

### Highlights

- Android P, Android Q, Android R, and Android S are now supported
- Compiled with Java 11
- Rewritten with extensibility and future Android OS's in-mind
    * Future versions of the Android OS will be easier to support with the new delegate system
    * Improved modularity where APIs for OS versions are contained in their own API / API implementation files
- Async operations updated to internally leverage Coroutines and new exception handler
- Returns are now Wisefy classes or primitives opposed to a class from the OS, making it easier to add new variants
  in the future through sealed classes
- Updated names for callbacks
- Kotlin first mentality (but willing to support Java as first class too!)
- WPA3 networks now supported
- wisefysample renamed to app
- BSSID support now added

### New Structure

- WiseFy Public API -> WiseFy Implementation -> Delegate to determine which OS level proxy to use -> 
  Proxy for specific SDK level -> Implementation with OS level APIs for specific SDK version

### Packaging & Naming Conventions

- Delegate -> Determines what proxy to use based on the Android device's SDK level
- Proxy -> Forwards the request to the appropriate operating system level API
- API / APIImpl -> Talk directly to the Android OS

Package structure for each section is as follows:

- Category (accesspoints, addnetwork, etc.) -> Location of Wisefy Specific delegate -> supporting sub-directories
  
*Supporting sub-directories can include* 

- callbacks -> Location of callback interfaces for async requests/responses
- converters -> Location of helpers to convert one data class to another
- entities -> Location of data classes for requests and responses
- impl -> Location of the implementation that talks to the Android operating system based on the device's SDK level
- proxies -> Location of the proxy classes that delegates use to forward requests to appropriate SDK level APIs

### Deprecations

- disableWifi() and disableWifi(callbacks: DisableWifiCallbacks?)
- enableWifi() and enableWifi(callbacks: EnableWifiCallbacks?)
- calculateBars(rssiLevel: Int, targetNumberOfBars: Int)

### Known Android Q Problems

- Saving a network doesn't seem possible.  A notification is presented if not connected to the suggestion, but even the 
appearance of the notification seems flakey

### Documentation Reset

To keep the documentation clean and because of the amount of architectural changes for the 5.x release, the 
documentation was stripped and then completely re-written.

## License ##
Copyright 2022 Patches Klinefelter

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in
compliance with the License. You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License
is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
or implied. See the License for the specific language governing permissions and limitations under
the License.
