## Wisefy

## Installation

There is a new package for 5.0.  Please use:

```kotlin
implementation("com.isupatches.android:wisefy:5.0.0-RC3")
```

There are also new more modular artifacts published so that individual pieces of Wisefy can be imported:

For base Wisefy functionality:
- com.isupatches.android.wisefy:core:<LATEST VERSION>

For getting and searching for nearby networks:
- com.isupatches.android.wisefy:accesspoints:<LATEST VERSION>:

For adding a Wifi network:
- com.isupatches.android.wisefy:addnetwork:<LATEST VERSION>

For getting the frequency of a network:
- com.isupatches.android.wisefy:frequency:<LATEST VERSION>

For connecting and disconnecting from networks:
- com.isupatches.android.wisefy:networkconnection:<LATEST VERSION>:

For current network status (wifi, mobile, connected, etc.):
- com.isupatches.android.wisefy:networkconnectionstatus:<LATEST VERSION> 

For information about the device's current network:
- com.isupatches.android.wisefy:networkinfo:<LATEST VERSION> 

For removing a Wifi network:
- com.isupatches.android.wisefy:removenetwork:<LATEST VERSION>
- 
For getting and searching for saved networks:
- com.isupatches.android.wisefy:savednetworks:<LATEST VERSION> 

For determining the security capabilities of a network:
- com.isupatches.android.wisefy:security:<LATEST VERSION>

For calculating signal strength bars and comparing signal strength:
- com.isupatches.android.wisefy:signal:<LATEST VERSION>

For enabling and disabling Wifi:
- com.isupatches.android.wisefy:wifi:<LATEST VERSION>

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
- More modular artifacts are available now (less bloat if there are things you're not going to use)

### New Structure

- WiseFy public API -> WiseFy implementation -> Delegate to determine which adapter to use -> 
  Adapter converts request for usage by the Android OS -> Android OS APIs are used and adapter returns their response 
  into a result Wisefy returns

### Packaging & Naming Conventions

Suffixes:

- Api () - 
- ApiSuffix () - 
- Delegate -> Determines what adapter to use based on the Android device's SDK level
- Adapter -> Middleware that converts requests and responses between the Api and Delegate layers 
- Api (within os package context) -> Defines the API to talk directly to the Android OS
- ApiImpl (within os package context) -> Talks directly to the Android OS

Package structure for each section is as follows:

- Category (accesspoints, addnetwork, etc.) -> Location of Wisefy Specific delegate -> supporting sub-directories
  
*Supporting sub-directories can include* 

- callbacks (public) -> Location of callback interfaces for async responses/requests
- entities (public) -> Location of data classes for requests and responses
- os (internal) - Organizational only directory
  - adapters (internal) -> Location of the classes that convert requests and responses between the delegate and Android 
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
