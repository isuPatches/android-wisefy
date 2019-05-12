#### Via The Synchronous API

To calculate number of bars given a networks signal strength and total number of bars:

_With Kotlin_

```kotlin
val bars = wisefy.calculateBars(-35, 5)
```

_With Java_

```java
int bars = wisefy.calculateBars(-35, 5);
```

To compare the signal strength of two networks:

_With Kotlin_

```kotlin
val result = wisefy.compareSignalLevel(-35, -70)
```

_With Java_

```java
int result = wisefy.compareSignalLevel(-35, -70);
```

To get the RSSI of the first SSID matching a given regex:<br/><br/>
<strong>Setting takeHighest to true will return the access point with the highest RSSI for the given SSID</strong>

_With Kotlin_

```kotlin
val rssi = wisefy.getRSSI("regex for SSID", true, 3000)
```

_With Java_

```java
Integer rssi = wisefy.getRSSI("regex for SSID", true, 3000);
```

#### Via The Asynchronous API

To get the RSSI of the first SSID matching a given regex:

_With Kotlin_

```kotlin
wisefy.getRSSI("regex for SSID", true, 3000, object: GetRSSICallbacks{
    override fun retrievedRSSI(rssi: Int) {

    }
    
    override fun networkNotFoundToRetrieveRSSI() {
    
    }

    override fun wisefyFailure(wisefyFailureCode: Int) {

    }
})
```

_With Java_

```java
wisefy.getRSSI("regex for SSID", true, 3000, new GetRSSICallbacks() {
    @Override
    public void retrievedRSSI(int rssi) {

    }

    @Override
    public void networkNotFoundToRetrieveRSSI() {

    }

    @Override
    public void wisefyFailure(int wisefyFailureCode) {

    }
});
```

***Notes***

- Will return a WiseFy error code if parameter is missing
