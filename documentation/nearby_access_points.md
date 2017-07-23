#### Via The Synchronous API

To get nearby access points:<br/><br/>
<strong>Setting filterDuplicates to true will exclude access points for an SSID that have a weaker RSSI (will always take the highest signal strength)</strong>

```java
List<ScanResult> nearbyAccessPoints = mWiseFy.getNearbyAccessPoints(true);
```

To search for an access point given a regex (will return first match):<br/><br/>
<strong>Setting filterDuplicates to true will return the access point with the highest RSSI for the given SSID</strong>

```java
mWiseFy.searchForAccessPoint("regex for SSID", 3000, true);
```

To search for all access points matching a given regex:<br/><br/>
<strong>Setting filterDuplicates to true will exclude access points for an SSID that have a weaker RSSI (will always take the highest signal strength)</strong>

```java
mWiseFy.searchForAccessPoints("regex for SSID", true);
```

To search for an SSID given a regex (will return first match):

```java
String ssid = mWiseFy.searchForSSID("regex for SSID", 3000);
```

To search for all SSIDs matching a given regex:

```java
List<String> ssids = mWiseFy.searchForSSIDs("regex for SSIDs");
```

#### Via The Asynchronous API

To get nearby access points:<br/><br/>
<strong>Setting filterDuplicates to true will exclude access points for an SSID that have a weaker RSSI (will always take the highest signal strength)</strong>

```java
WiseFy.getNearbyAccessPoints(true, new GetNearbyAccessPointsCallbacks() {
    @Override
    public void getNearbyAccessPointsWiseFyFailure(Integer wisefyReturnCode) {

    }

    @Override
    public void retrievedNearbyAccessPoints(List<ScanResult> nearbyAccessPoints) {

    }
});
```

To search for an access point given a regex (will return first match):<br/><br/>
<strong>Setting filterDuplicates to true will return the access point with the highest RSSI (will always take the highest signal strength)</strong>

```java
mWiseFy.searchForAccessPoint("regex for SSID", 3000, true, new SearchForAccessPointCallbacks() {
    @Override
    public void searchForAccessPointWiseFyFailure(Integer wisefyReturnCode) {

    }

    @Override
    public void accessPointFound(ScanResult accessPoint) {

    }

    @Override
    public void accessPointNotFound() {

    }
});
```

To search for all access points matching a given regex:<br/><br/>
<strong>Setting filterDuplicates to true will exclude access points for an SSID that have a weaker RSSI (will always take the highest signal strength)</strong>


```java
mWiseFy.searchForAccessPoints("regex for SSID", true, new SearchForAccessPointsCallbacks() {
    @Override
    public void searchForAccessPointsWiseFyFailure(Integer wisefyReturnCode) {

    }

    @Override
    public void foundAccessPoints(List<ScanResult> accessPoints) {

    }

    @Override
    public void noAccessPointsFound() {

    }
});
```

To search for an SSID given a regex (will return first match):

```java
mWiseFy.searchForSSID("regex for SSID", 3000, new SearchForSSIDCallbacks() {
    @Override
    public void searchForSSIDWiseFyFailure(Integer wisefyReturnCode) {

    }

    @Override
    public void ssidFound(String ssid) {

    }

    @Override
    public void ssidNotFound() {

    }
});
```

To search for all SSIDs matching a given regex:

```java
mWiseFy.searchForSSIDs("regex for SSID", new SearchForSSIDsCallbacks() {
    @Override
    public void searchForSSIDsWiseFyFailure(Integer wisefyReturnCode) {

    }

    @Override
    public void retrievedSSIDs(List<String> ssids) {

    }

    @Override
    public void noSSIDsFound() {

    }
});
```

***Notes***

- Will return a WiseFy error code if parameter is missing
- Will return a WiseFy error code if the instance has a missing prerequisite