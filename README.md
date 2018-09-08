# WiseFy

<img src="Banner.png" width="40%" height="40%" />

Wifi configuration and util library built for Android.

> <br/>*Developed by Patches 04/24/2016 - present* <br/>
> 
> <br/>Supports Android SDK levels 16-27<br/><br/>

[![Build Status](https://travis-ci.org/isuPatches/WiseFy.svg?branch=master)](https://travis-ci.org/isuPatches/WiseFy) [ ![Download](https://api.bintray.com/packages/isupatches/Maven/wisefy/images/download.svg) ](https://bintray.com/isupatches/Maven/wisefy/_latestVersion) [![codecov](https://codecov.io/gh/isuPatches/WiseFy/branch/3.x/graph/badge.svg)](https://codecov.io/gh/isuPatches/WiseFy)

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-WiseFy-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/6011) [![Android Weekly](https://img.shields.io/badge/Android%20Weekly-%23230-blue.svg)](http://androidweekly.net/issues/issue-230) 

## What's New in 3.x

- Rewritten in Kotlin
    - Static analysis tools added
- Ability to get the IP of a device
- Additional details in callbacks for adding a network:
    - The new id of the network
    - The WifiConfiguration of the network that was added
- isNetworkInConfigurationList renamed isNetworkSaved
- brains renamed Brains
- Nullability issues will be more visible
- Definitions for NetworkTypes and WiseFyCodes
- Immutability throughout the library
- Improved architecture
- Updated dependencies
- Updated to Gradle 4.x and AGP 3.x
- Target now is set to API 27
- Less duplicate code
- Improved testing
- Updated documentation with new Kotlin examples
- Other improvements and adjustments!

## What's New in 2.0.x

- Asynchronous API
    - Certain methods have callbacks and are run on a WiseFy specific background thread
- Ability to search by regex for:
    - Nearby access points
    - Saved Configurations
    - SSIDs
- Ability to query for RSSI
- Ability to query for if the device is roaming
- Additional methods to query for network security
- Full fledged documentation directory
- Tested against Android O

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
    compile 'com.isupatches:wisefy:<LATEST_VERSION>'
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

## Cleanup

Since the Async API of WiseFy is run on a background thread, it is necessary to make sure it is exited and cleanup up properly.

To stop the WiseFy thread and nullify it along with it's handler please call:

_With Kotlin_

```kotlin
wisefy.dump();
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

 <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
 <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
```

<strong> * NOTE * </strong>

If access points or SSIDs are not being returned on >= 6.x devices but there are visible networks, it's most likely because you haven't asked in your application for the `Manifest.permission.ACCESS_COARSE_LOCATION` permission which is what allows us to see the access points nearby. This permission request will not be added to the WiseFy library to reduce package bloat and so users can determine their own UI/UX.

Please check [the permssions example](/documentation/permissions_example.md) for a sample of how to request permissions for WiseFy.

## Usage

Please check [the documentation markdown directory](/documentation) for usage examples and details about both the synchronous and asynchronous API.

## License ##
Copyright 2018 Patches Klinefelter

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in
compliance with the License. You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License
is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
or implied. See the License for the specific language governing permissions and limitations under
the License.
