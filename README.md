# WiseFy

Wifi configuration and util library built for Android.

> <br/>*Developed by Patches 04/24/2016 - present* <br/>
> 
> <br/>Supports Android SDK levels 16-26<br/><br/>

[![Android Weekly](https://img.shields.io/badge/Android%20Weekly-%23230-blue.svg)](http://androidweekly.net/issues/issue-230) [![Build Status](https://travis-ci.org/isuPatches/WiseFy.svg?branch=master)](https://travis-ci.org/isuPatches/WiseFy) [ ![Download](https://api.bintray.com/packages/isupatches/Maven/wisefy/images/download.svg) ](https://bintray.com/isupatches/Maven/wisefy/_latestVersion) <a href="http://www.methodscount.com/?lib=com.isupatches%3Awisefy%3A2.0.0"><img src="https://img.shields.io/badge/Methods and size-291 | 49 KB-e91e63.svg"/></a> [![codecov.io](https://codecov.io/github/codecov/wisefy/branch/2.x/graph/badge.svg)](https://codecov.io/github/codecov/wisefy)

## What's New in 2.x

- Asynchronous API
    - Certain methods have callbacks and are run on background thread
- Ability search by regex
    - Nearby access points
    - Saved Configurations
    - SSIDs
- Ability to query for RSSI
- Ability to query for if device is roaming
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
      <version><LATEST_VERSION></version>
      <type>pom</type>
    </dependency>
```

You may also download the @aar from the <a href="https://github.com/isuPatches/WiseFy/releases" title="WiseFy Releases">releases page</a> and import it into your project manually. 

## Getting An Instance

WiseFy is constructed with the builder pattern that allows you access to the synchronous and asynchronous APIs.

To grab a default instance:

```java
WiseFy mWiseFy = new WiseFy.brains(getActivity()).getSmarts();
```

To grab an instance with logging enabled:

```java
WiseFy mWiseFy = new WiseFy.brains(getActivity()).logging(true).getSmarts();
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
Copyright 2017 Patches Klinefelter

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in
compliance with the License. You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License
is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
or implied. See the License for the specific language governing permissions and limitations under
the License.
