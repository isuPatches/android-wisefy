# WiFiManager
Util library used as a wrapper around WiFi and Connectivity manager for Android

> <br/>*Developed by Patches 04/24/2016 - present* <br/><br/>

## Commands

To add a WEP network:

```java
WiseFy.getSmarts().addWEPNetwork(getActivity(), "WEP Network", "123456")
```

To add a WPA2 network:

```java
WiseFy.getSmarts().addWPA2Network(getActivity(), "WPA2 Network", "12345678")
```

To add an open network:

```java
WiseFy.getSmarts().addOpenNetwork(getActivity(), "Open Network")
```

To calculate number of bars given a networks signal strength and total number of bars:

```java
int bars = WiseFy.getSmarts().calculateBars(-35, -5)
```

To compare the signal strength of two networks:

```java
int result = WiseFy.getSmarts().compareSignalLevel(-35, -70)
```

To disable wifi:

```java
WiseFy.getSmarts().disableWiFi(getActivity())
```

To disconnect from current network:

```java
WiseFy.getSmarts().disconnectFromCurrentNetwork(getActivity())
```

To enable wifi:

```java
WiseFy.getSmarts().enableWiFi(getActivity())
```

To get current network:

```java
WifiInfo wifiInfo = WiseFy.getSmarts().getCurrentNetwork(getActivity())
```

To get nearby access points:<br/><br/>
<strong>Setting filterDuplicates to true will not return SSIDs with a weaker signal strength (will always take the highest)</strong>

```java
List<ScanResult> scanResults = WiseFy.getSmarts().getNearbyAccessPoints(getActivity(), true)
```

To get the networks whose configuration is already stored:

```java
List<WifiConfiguration> savedNetworks = WiseFy.getSmarts().getSavedNetworks(getActivity());
```

To check and see if a network is secure (WEP/PSK/EAP capabilities):

```java
boolean secure = WiseFy.getSmarts().isSecure(scanResult;
```

To reconnect to a network given an SSID:

```java
boolean reasul = WiseFy.getSmarts().reconnectToNetwork(getActivity(), "SSID To Reconnect To", 30);
```

To remove a configured network:

```java
boolean reasul = WiseFy.getSmarts().removeNetwork(getActivity(), "SSID To Remove");
```

To search for an SSID given a search string:

```java
String ssid = WiseFy.getSmarts().searchForSSID(getActivity(), "SSID To Search For", 30);
```
