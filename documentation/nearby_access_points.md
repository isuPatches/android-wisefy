#### Via The Synchronous API

To get nearby access points:<br/><br/>
<strong>Setting filterDuplicates to true will not return SSIDs with a weaker signal strength (will always take the highest)</strong>

```java
List<ScanResult> nearbyAccessPoints = mWiseFy.getNearbyAccessPoints(true);
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

To get all nearby access points:<br/><br/>
<strong>Setting filterDuplicates to true will not return SSIDs with a weaker signal strength (will always take the highest)</strong>

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
mWiseFy.searchForSSIDs("regex for SSIDs", new SearchForSSIDsCallbacks() {
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