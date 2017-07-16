#### Via The Synchronous API

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

To check and see if Wifi is enabled on a device:

```java
boolean wifiEnabled = mWiseFy.isWifiEnabled();
```