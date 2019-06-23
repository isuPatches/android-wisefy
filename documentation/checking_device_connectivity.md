#### Via The Synchronous API

To check and see if the device is connected to a mobile network:

_With Kotlin_

```Kotlin
val isConnected = wisefy.isDeviceConnectedToMobileNetwork()
```

_With Java_

```java
boolean isConnected = wisefy.isDeviceConnectedToMobileNetwork();
```

To check and see if the device is connected to a mobile or wifi network:

_With Kotlin_

```kotlin
val isConnected = wisefy.isDeviceConnectedToMobileOrWifiNetwork()
```

_With Java_

```java
boolean isConnected = wisefy.isDeviceConnectedToMobileOrWifiNetwork();
```

To check and see if the device is connected to a given SSID:

_With Kotlin_

```kotlin
val isConnected = wisefy.isDeviceConnectedToSSID("SSID")
```

_With Java_

```java
boolean isConnected = wisefy.isDeviceConnectedToSSID("SSID");
```

To check and see if the device is connected to a wifi network:

_With Kotlin_

```kotlin
val isConnected = wisefy.isDeviceConnectedToWifiNetwork()
```

_With Java_

```java
boolean isConnected = wisefy.isDeviceConnectedToWifiNetwork();
```

To check and see if the device is roaming:

_With Kotlin_

```kotlin
val isDeviceRoaming = wisefy.isDeviceRoaming()
```

_With Java_

```java
boolean isDeviceRoaming = wisefy.isDeviceRoaming();
```

To check and see if Wifi is enabled on a device:

_With Kotlin_

```kotlin
boolean wifiEnabled = wisefy.isWifiEnabled()
```

_With Java_

```java
boolean wifiEnabled = wisefy.isWifiEnabled();
```
