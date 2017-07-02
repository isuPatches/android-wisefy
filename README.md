# WiseFy

Wifi configuration and util library built for Android.

> <br/>*Developed by Patches 04/24/2016 - present* <br/>
> 
> <br/>Supports Android SDK levels 16-25<br/><br/>

[![Build Status](https://travis-ci.org/isuPatches/WiseFy.svg?branch=master)](https://travis-ci.org/isuPatches/WiseFy)

[ ![Download](https://api.bintray.com/packages/isupatches/Maven/wisefy/images/download.svg) ](https://bintray.com/isupatches/Maven/wisefy/_latestVersion)

<a href="http://www.methodscount.com/?lib=com.isupatches%3Awisefy%3A1.0.8"><img src="https://img.shields.io/badge/Methods and size-core: 105 | deps: 19372 | 56 KB-e91e63.svg"/></a>

## Adding to your project

Make sure you have one of the following repositories accessible:

```
repositories {
    jcenter()
}
```

```
repositories {
    mavenCentral()
}
```

```
repositories {
    maven {
        url  "http://dl.bintray.com/isupatches/Maven" 
    }
}
```

Then add it as a dependency:

Gradle:

```
compile 'com.isupatches:wisefy:1.0.8'
```

Maven:

```
<dependency>
  <groupId>com.isupatches</groupId>
  <artifactId>wisefy</artifactId>
  <version>1.0.8</version>
  <type>pom</type>
</dependency>
```

You may also download the @aar from the <a href="https://github.com/isuPatches/WiseFy/releases" title="WiseFy Releases">releases page</a> and import it into your project manually. 

## Getting An Instance

WiseFy now supports the builder pattern so cool functionality can be added later!

To grab a default instance:

```java
WiseFy mWiseFy = new WiseFy.withContext(getActivity()).getSmarts();
```

To grab an instance with logging enabled:

```java
WiseFy mWiseFy = new WiseFy.withContext(getActivity()).logging(true).getSmarts();
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

If a search for an SSID is failing (returning false or NULL depending on the method) on >= 6.x devices but the network is visible, it's most likely because you haven't asked in your application for the `Manifest.permission.ACCESS_COARSE_LOCATION` permission which is what allows us to see the access points nearby. It has been up for debate on if it would be beneficial to move permission requests to the WiseFy library, but at this time, it remains as-is so users can determine their own UI/UX and to not add additional package bloat.

## Commands

To add an open network:

```java
int addedSuccessfully = mWiseFy.addOpenNetwork("Open Network");
```

To add a WEP network:

```java
int addedSuccessfully = mWiseFy.addWEPNetwork("WEP Network", "123456");
```

To add a WPA2 network:

```java
int addedSuccessfully = mWiseFy.addWPA2Network("WPA2 Network", "12345678");
```

To calculate number of bars given a networks signal strength and total number of bars:

```java
int bars = mWiseFy.calculateBars(-35, -5);
```

To compare the signal strength of two networks:

```java
int result = mWiseFy.compareSignalLevel(-35, -70);
```

To connect to a network given an SSID:

```java
boolean connectedSuccessfully = mWiseFy.connectToNetwork("SSID To Reconnect To", 30000);
```

To disable wifi:

```java
boolean disabledWifiSuccessfully = mWiseFy.disableWifi();
```

To disconnect from current network:

```java
boolean disconnectedSuccessfully = mWiseFy.disconnectFromCurrentNetwork();
```

To enable wifi:

```java
boolean wifiEnabled = mWiseFy.enableWiFi();
```

To get current network:

```java
WifiInfo currentNetwork = mWiseFy.getCurrentNetwork();
```

To get the frequency of the devices current network:<br/><br/>
<strong>* NOTE *</strong>  Only supported on API >= 21

```java
int frequency = mWiseFy.getFrequency();
```

To get the frequency of a network:<br/><br/>
<strong>* NOTE *</strong>  Only supported on API >= 21

```java
int frequency = mWiseFy.getFrequency(wifiInfo);
```

To get nearby access points:<br/><br/>
<strong>Setting filterDuplicates to true will not return SSIDs with a weaker signal strength (will always take the highest)</strong>

```java
List<ScanResult> nearbyAccessPoints = mWiseFy.getNearbyAccessPoints(true);
```

To get the networks whose configuration is already stored:

```java
List<WifiConfiguration> savedNetworks = mWiseFy.getSavedNetworks();
```

To check and see if the device is connected to a mobile network:

```java
boolean isConnected = mWiseFy.isDeviceConnectedToMobileNetwork();
```

To check and see if the device is connected to a mobile or wifi network:

```java
boolean isConnected = mWiseFy.isDeviceConnectedToMobileOrWifiNetwork();
```

To check and see if the device is connected to a given SSID:

```java
boolean isConnected = mWiseFy.isDeviceConnectedToSSID("SSID");
```

To check and see if the device is connected to a wifi network:

```java
boolean isConnected = mWiseFy.isDeviceConnectedToWifiNetwork();
```

To check if the device's current network is 5gHz:<br/><br/>
<strong>* NOTE *</strong>  Only supported on API >= 21

```java
boolean is5gHz = mWiseFy.isNetwork5gHz();
```

To check if a network is 5gHz:<br/><br/>
<strong>* NOTE *</strong>  Only supported on API >= 21

```java
boolean is5gHz = mWiseFy.isNetwork5gHz(wifiInfo);
```

To check and see if a given SSID is in the devices set of configured networks:

```java
boolean isConfigured = mWiseFy.isNetworkInConfigurationList("SSID");
```

To check and see if a network is secure (WEP/PSK/EAP capabilities):

```java
boolean isSecure = mWiseFy.isNetworkSecure(scanResult);
```

To check and see if Wifi is enabled on a device:

```java
boolean wifiEnabled = mWiseFy.isWifiEnabled();
```

To remove a configured network:

```java
boolean removedSuccessfully = mWiseFy.removeNetwork("SSID To Remove");
```

To search for an SSID given a search string:

```java
String ssid = mWiseFy.searchForSSID("SSID To Search For", 30000);
```

## License ##
Copyright 2017 Patches Klinefelter

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in
compliance with the License. You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License
is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
or implied. See the License for the specific language governing permissions and limitations under
the License.
