## Wisefy

## Installation

There is a new package for 5.0.  Please use:

```kotlin
implementation("com.isupatches.android:wisefy:5.0.0-RC1")
```

## 5.0 Rewrite

The 5.0 version of WiseFy works to rectify the problems that caused it to be overly challenging as a single developer 
to keep up with the ever-changing Wifi APIs for new Android OS's, especially with a lot of functionality becoming 
privatized.

I hope you enjoy the rewrite and please create an issue if you see anything odd or have questions!

### Highlights

- Android P, Android Q, and Android R are now supported
- Rewritten with extensibility and future Android OS's in-mind
    * Future versions of the Android OS will be easier to support with the new delegate system
    * Improved modularity where APIs for OS versions are contained in their own API / API implementation files
- Async operations updated to internally leverage Coroutines and new exception handler
- Returns are now WiseFy classes or primitives opposed to a class from the OS, making it easier to add new variants
  in the future through sealed classes
- Updated names for callbacks
- Kotlin first mentality (but willing to support Java as first class too!)
- WPA3 networks now supported
- wisefysample renamed to app

### New Structure

- WiseFy API -> WiseFy -> Internal util -> Delegate -> OS API and OS API implementation 

### Deprecations

- disableWifi() and disableWifi(callbacks: DisableWifiCallbacks?)
- enableWifi() and enableWifi(callbacks: EnableWifiCallbacks?)
- calculateBars(rssiLevel: Int, targetNumberOfBars: Int)

### Known Android Q Problems

- Saving a network doesn't seem possible.  A notification is presented if not connected to the suggestion, but even the 
appearance of the notification seems flakey

## License ##
Copyright 2021 Patches Klinefelter

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in
compliance with the License. You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License
is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
or implied. See the License for the specific language governing permissions and limitations under
the License.
