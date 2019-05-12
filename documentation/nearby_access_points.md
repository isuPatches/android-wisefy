#### Via The Synchronous API

To get nearby access points:<br/><br/>
<strong>Setting filterDuplicates to true will exclude access points for an SSID that have a weaker RSSI (will always take the highest signal strength)</strong>

_With Kotlin_

```kotlin
val nearbyAccessPoints = wisefy.getNearbyAccessPoints(true)
```

_With Java_

```java
List<ScanResult> nearbyAccessPoints = wisefy.getNearbyAccessPoints(true);
```

To search for an access point given a regex (will return first match):<br/><br/>
<strong>Setting filterDuplicates to true will return the access point with the highest RSSI for the given SSID</strong>

_With Kotlin_

```kotlin
val wisefy.searchForAccessPoint("regex for SSID", 3000, true);
```

_With Java_

```java
wisefy.searchForAccessPoint("regex for SSID", 3000, true);
```

To search for all access points matching a given regex:<br/><br/>
<strong>Setting filterDuplicates to true will exclude access points for an SSID that have a weaker RSSI (will always take the highest signal strength)</strong>

_With Kotlin_

```kotlin
val wisefy.searchForAccessPoints("regex for SSID", true)
```

_With Java_

```java
wisefy.searchForAccessPoints("regex for SSID", true);
```

To search for an SSID given a regex (will return first match):

_With Kotlin_

```kotlin
val ssid = wisefy.searchForSSID("regex for SSID", 3000)
```

_With Java_

```java
String ssid = wisefy.searchForSSID("regex for SSID", 3000);
```

To search for all SSIDs matching a given regex:

_With Kotlin_

```kotlin
val ssids = wisefy.searchForSSIDs("regex for SSIDs")
```

_With Java_

```java
List<String> ssids = wisefy.searchForSSIDs("regex for SSIDs");
```

#### Via The Asynchronous API

To get nearby access points:<br/><br/>
<strong>Setting filterDuplicates to true will exclude access points for an SSID that have a weaker RSSI (will always take the highest signal strength)</strong>

_With Kotlin_

```kotlin
wisefy.getNearbyAccessPoints(true, object: GetNearbyAccessPointsCallbacks {
    override fun retrievedNearbyAccessPoints(nearbyAccessPoints: List<ScanResult>) {

    }

    override fun noAccessPointsFound() {
    
    }
    
    override fun wisefyFailure(wisefyFailureCode: Int) {

    }
})
```

_With Java_

```java
wisefy.getNearbyAccessPoints(true, new GetNearbyAccessPointsCallbacks() {
    @Override
    public void retrievedNearbyAccessPoints(List<ScanResult> accessPoints) {

    }
    
    @Override
    public void noAccessPointsFound() {
        
    }

    @Override
    public void wisefyFailure(int wisefyFailureCode) {

    }
});
```

To search for an access point given a regex (will return first match):<br/><br/>
<strong>Setting filterDuplicates to true will return the access point with the highest RSSI (will always take the highest signal strength)</strong>

_With Kotlin_

```kotlin
wisefy.searchForAccessPoint("regex for SSID", 3000, true, object: SearchForAccessPointCallbacks {
    override fun accessPointFound(accessPoint: ScanResult) {

    }

    override fun accessPointNotFound() {

    }

    override fun wisefyFailure(wisefyFailureCode: Int) {

    }
})
```

_With Java_

```java
wisefy.searchForAccessPoint("regex for SSID", 3000, true, new SearchForAccessPointCallbacks() {
    @Override
    public void accessPointFound(ScanResult accessPoint) {

    }

    @Override
    public void accessPointNotFound() {

    }

    @Override
    public void wisefyFailure(int wisefyFailureCode) {

    }
});
```

To search for all access points matching a given regex:<br/><br/>
<strong>Setting filterDuplicates to true will exclude access points for an SSID that have a weaker RSSI (will always take the highest signal strength)</strong>

_With Kotlin_

```kotlin
wisefy.searchForAccessPoints("regex for SSID", true, object: SearchForAccessPointsCallbacks {
    override fun foundAccessPoints(accessPoints: List<ScanResult>) {

    }

    override fun noAccessPointsFound() {

    }

    override fun wisefyFailure(wisefyFailureCode: Int) {

    }
})
```

_With Java_

```java
wisefy.searchForAccessPoints("regex for SSID", true, new SearchForAccessPointsCallbacks() {
    @Override
    public void foundAccessPoints(List<ScanResult> accessPoints) {

    }

    @Override
    public void noAccessPointsFound() {

    }

    @Override
    public void wisefyFailure(int wisefyFailureCode) {

    }
});
```

To search for an SSID given a regex (will return first match):

_With Kotlin_

```kotlin
wisefy.searchForSSID("regex for SSID", 3000, object: SearchForSSIDCallbacks {
    override fun ssidFound(ssid: String) {

    }

    override fun ssidNotFound() {

    }

    override fun wisefyFailure(wisefyFailureCode: Int) {

    }
})
```

_With Java_

```java
wisefy.searchForSSID("regex for SSID", 3000, new SearchForSSIDCallbacks() {
    @Override
    public void ssidFound(String ssid) {

    }

    @Override
    public void ssidNotFound() {

    }

    @Override
    public void wisefyFailure(int wisefyFailureCode) {

    }
});
```

To search for all SSIDs matching a given regex:

_With Kotlin_

```kotlin
wisefy.searchForSSIDs("regex for SSID", object: SearchForSSIDsCallbacks {
    override fun retrievedSSIDs(ssids: List<String>) {

    }
    
    override fun noSSIDsFound() {

    }

    override fun wisefyFailure(wisefyFailureCode: Int) {

    }
})
```

_With Java_

```java
wisefy.searchForSSIDs("regex for SSID", new SearchForSSIDsCallbacks() {
    @Override
    public void retrievedSSIDs(List<String> ssids) {

    }

    @Override
    public void noSSIDsFound() {

    }

    @Override
    public void wisefyFailure(int wisefyFailureCode) {

    }
});
```

***Notes***

- Will return a WiseFy error code if parameter is missing