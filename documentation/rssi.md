#### Via The Synchronous API

To calculate number of bars given a networks signal strength and total number of bars:

```java
int bars = mWiseFy.calculateBars(-35, 5);
```

To compare the signal strength of two networks:

```java
int result = mWiseFy.compareSignalLevel(-35, -70);
```

To get the RSSI of the first SSID matching a given regex:

```java
Integer rssi = mWiseFy.getRSSI("regex for SSID", true, 3000);
```

#### Via The Asynchronous API

To get the RSSI of the first SSID matching a given regex:

```java
mWiseFy.getRSSI("regex for SSID", true, 3000, new GetRSSICallbacks() {
    @Override
    public void retrievedRSSI(Integer rssi) {

    }

    @Override
    public void networkNotFoundToRetrieveRSSI() {

    }

    @Override
    public void getRSSIWiseFyFailure(Integer wisefyReturnCode) {

    }
});
```

***Notes***

- Will return a WiseFy error code if parameter is missing
- Will return a WiseFy error code if the instance has a missing prerequisite