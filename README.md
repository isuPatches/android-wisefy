# WiFiManager
Util library used as a wrapper around WiFi and Connectivity manager for Android

> <br/>*Developed by Patches 04/24/2016 - present* <br/><br/>

## Getting An Instance

WiseFy now supports the builder pattern so cool functionality can be added later!

To grab a default instance:

```java
WiseFy mWiseFy = new WiseFy.getSmarts().initiateHamsters();
```

To grab an instance with logging enabled:

```java
WiseFy mWiseFy = new WiseFy.getSmarts().withLogging(true).initiateHamsters();
```


## Commands

To add an open network:

```java
int addedSuccessfully = mWiseFy.addOpenNetwork(getActivity(), "Open Network");
```

To add a WEP network:

```java
int addedSuccessfully = mWiseFy.addWEPNetwork(getActivity(), "WEP Network", "123456");
```

To add a WPA2 network:

```java
int addedSuccessfully = mWiseFy.addWPA2Network(getActivity(), "WPA2 Network", "12345678");
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
boolean connectedSuccessfully = mWiseFy.connectToNetwork(getActivity(), "SSID To Reconnect To", 3000);
```

To disable wifi:

```java
boolean disabledWifiSuccessfully = mWiseFy.disableWifi(getActivity());
```

To disconnect from current network:

```java
boolean disconnectedSuccessfully = mWiseFy.disconnectFromCurrentNetwork(getActivity());
```

To enable wifi:

```java
boolean wifiEnabled = mWiseFy.enableWiFi(getActivity());
```

To get current network:

```java
WifiInfo currentNetwork = mWiseFy.getCurrentNetwork(getActivity());
```

To get nearby access points:<br/><br/>
<strong>Setting filterDuplicates to true will not return SSIDs with a weaker signal strength (will always take the highest)</strong>

```java
List<ScanResult> nearbyAccessPoints = mWiseFy.getNearbyAccessPoints(getActivity(), true);
```

To get the networks whose configuration is already stored:

```java
List<WifiConfiguration> savedNetworks = mWiseFy.getSavedNetworks(getActivity());
```

To check and see if the device is connected to a mobile network:

```java
boolean isConnected = mWiseFy.isDeviceConnectedToMobileNetwork(getActivity());
```

To check and see if the device is connected to a mobile or wifi network:

```java
boolean isConnected = mWiseFy.isDeviceConnectedToMobileOrWifiNetwork(getActivity());
```

To check and see if the device is connected to a given SSID:

```java
boolean isConnected = mWiseFy.isDeviceConnectedToSSID(getActivity(), "SSID");
```

To check and see if a given SSID is in the devices set of configured networks:

```java
boolean isConfigured = mWiseFy.isNetworkInConfigurationList(getActivity(), "SSID");
```

To check and see if a network is secure (WEP/PSK/EAP capabilities):

```java
boolean isSecure = mWiseFy.isNetworkSecure(scanResult);
```

To check and see if Wifi is enabled on a device:

```java
boolean wifiEnabled = mWiseFy.isWifiEnabled(getActivity());
```

To remove a configured network:

```java
boolean removedSuccessfully = mWiseFy.removeNetwork(getActivity(), "SSID To Remove");
```

To search for an SSID given a search string:

```java
String ssid = WiseFy.getSmarts().searchForSSID(getActivity(), "SSID To Search For", 3000);
```
