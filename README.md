<img src="Banner.png" width="40%" height="40%" />

Wifi configuration and util library built for Android.  

> <br/>*Developed by Patches 04/24/2016 - present* <br/>
> <br/>Logo/icon created by mansya (2018)<br/>
> <br/>Supports Android SDK levels 16-28<br/><br/>

[![Build Status](https://travis-ci.org/isuPatches/WiseFy.svg?branch=master)](https://travis-ci.org/isuPatches/WiseFy) [ ![Download](https://api.bintray.com/packages/isupatches/Maven/wisefy/images/download.svg) ](https://bintray.com/isupatches/Maven/wisefy/_latestVersion) [![codecov](https://codecov.io/gh/isuPatches/WiseFy/branch/3.x/graph/badge.svg)](https://codecov.io/gh/isuPatches/WiseFy)

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-WiseFy-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/6011) [![Android Weekly](https://img.shields.io/badge/Android%20Weekly-%23230-blue.svg)](http://androidweekly.net/issues/issue-230) 

## What's New in 4.x

- Android P Support
    - New isDeviceRoaming logic
- New logic for SDK 23 and above
    - WiseFySearch rewritten to remove deprecated APIs from Android OS
    - WiseFyConnection rewritten to remove deprecated APIs from Android OS
    - Ability to use legacy search logic
    - Ability to use legacy connection logic
- Update to Android X
- Improved annotations for required permissions
- Update to gradle 5.x
- Static analysis tools updated
- WEP is now deprecated due to security and other issues with this network type and will be phased out
- Better naming for some saved network functions
    - GetSavedNetworkCallbacks renamed SearchForSavedNetworkCallbacks
    - Added SearchForSavedNetworksCallbacks
    - getSavedNetwork(regex: String?): List<WifiConfiguration>? refactored to searchForSavedNetwork(regexForSSID: String?): WifiConfiguration?
    - getSavedNetwork(regexForSSID: String?, callbacks: GetSavedNetworkCallbacks?) refactored to searchForSavedNetwork(regexForSSID: String?, callbacks: SearchForSavedNetworkCallbacks?)
    - getSavedNetworks(regexForSSID: String?, callbacks: GetSavedNetworksCallbacks?) refactored to searchForSavedNetworks(regexForSSID: String?, callbacks: SearchForSavedNetworksCallbacks?)
- Introduction of [RoboElectric](https://github.com/robolectric/robolectric) for better unit testing
    - This may be controversial, but please check this [issue](https://github.com/isuPatches/WiseFy/issues/133) for rationale
- Removal of Checkstyle and FindBugs since project is no longer Java
- Removal of GCM support due to GCM being sunset
- New [sample app](/wisefysample) included as part of the repo
    - This replaces the previous permissions example
- Crash fixes for:
    - Async api with null current network
    - Async api with null current network info
    - Async api with null nearby access points
- Fix for searching when empty list returned from OS
- More tests
- Removal of some generic variable names from documentation for clarity

Previous updates:
- [What's New in 3.x](/changes/whatsnew/3.x.md)
- [What's New in 2.x](/changes/whatsnew/2.x.md)

## Adding to your project

Make sure you have one of the following repositories accessible:

```groovy
    repositories {
        jcenter()
    }
```

```groovy
    repositories {
        mavenCentral()
    }
```

```groovy
    repositories {
        maven {
            url  "http://dl.bintray.com/isupatches/Maven"
        }
    }
```

Then add it as a dependency (please see https://github.com/isuPatches/WiseFy/releases for the latest version):

For Gradle:

```groovy
    implementation 'com.isupatches:wisefy:<LATEST_VERSION>'
```

For Maven:

```xml
    <dependency>
      <groupId>com.isupatches</groupId>
      <artifactId>wisefy</artifactId>
      <version>LATEST_VERSION</version>
      <type>pom</type>
    </dependency>
```

You may also download the @aar from the <a href="https://github.com/isuPatches/WiseFy/releases" title="WiseFy Releases">releases page</a> and import it into your project manually. 

## Getting An Instance

WiseFy is constructed with the builder pattern that allows you access to the synchronous and asynchronous APIs.

*NOTE* The context passed in must be non-null.

To grab a default instance:

_With Kotlin_

```kotlin
val wisefy = WiseFy.Brains(activity!!).getSmarts()
```

_With Java_

```java
WiseFy wisefy = new WiseFy.Brains(getActivity()).getSmarts();
```

To grab an instance with logging enabled:

_With Kotlin_

```kotlin
val wisefy = WiseFy.Brains(activity!!).logging(true).getSmarts()
```

_With Java_

```java
WiseFy wisefy = new WiseFy.Brains(getActivity()).logging(true).getSmarts();
```

By default, legacy logic is disabled on devices with SDK 23 or higher.  If you want to use or test against the legacy search or connection logic, please see: [Using Legacy Classes And Logic](/documentation/using_legacy_classes_and_logic.md).

## Cleanup

Since the Async API of WiseFy is run on a background thread, it is necessary to make sure it is exited and cleanup up properly.

To stop the WiseFy thread and nullify it along with it's handler please call:

_With Kotlin_

```kotlin
wisefy.dump()
```

_With Java_

```java
wisefy.dump();
```

## Permissions

For the sake of transparency and because you're probably curious as to what permissions this library adds to your app, here are the additional expected permissions:

```xml
 <uses-permission android:name="android.permission.CHANGE_WIFI_STATE"/>
 <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
 <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>

 <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
```

<strong> * NOTE * </strong>

If access points or SSIDs are not being returned on >= 6.x devices but there are visible networks, it's most likely because you haven't asked in your application for the `Manifest.permission.ACCESS_COARSE_LOCATION` permission which is what allows us to see the access points nearby. This permission request will not be added to the WiseFy library to reduce package bloat and so users can determine their own UI/UX.

Please check [the sample app](/wisefysample) for an example of how to request permissions for WiseFy.

## Usage

Please check [the documentation markdown directory](/documentation) for usage examples and details about both the synchronous and asynchronous API.

## License ##
Copyright 2019 Patches Klinefelter

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in
compliance with the License. You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License
is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
or implied. See the License for the specific language governing permissions and limitations under
the License.
