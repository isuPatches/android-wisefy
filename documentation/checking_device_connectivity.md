#### Via The Synchronous API

To check and see if the device is connected to a mobile network:

```java
boolean isConnected = wisefy.isDeviceConnectedToMobileNetwork();
```

To check and see if the device is connected to a mobile or wifi network:

```java
boolean isConnected = wisefy.isDeviceConnectedToMobileOrWifiNetwork();
```

To check and see if the device is connected to a given SSID:

```java
boolean isConnected = wisefy.isDeviceConnectedToSSID("SSID");
```

To check and see if the device is connected to a wifi network:

```java
boolean isConnected = wisefy.isDeviceConnectedToWifiNetwork();
```

To check and see if the device is roaming:

```java
boolean isDeviceRoaming = wisefy.isDeviceRoaming();
```

To check and see if Wifi is enabled on a device:

```java
boolean wifiEnabled = wisefy.isWifiEnabled();
```
