#### Via The Synchronous API

To calculate number of bars given a networks signal strength and total number of bars:

```java
int bars = wisefy.calculateBars(-35, 5);
```

To compare the signal strength of two networks:

```java
int result = wisefy.compareSignalLevel(-35, -70);
```

To get the RSSI of the first SSID matching a given regex:<br/><br/>
<strong>Setting takeHighest to true will return the access point with the highest RSSI for the given SSID</strong>

```java
Integer rssi = wisefy.getRSSI("regex for SSID", true, 3000);
```

#### Via The Asynchronous API

To get the RSSI of the first SSID matching a given regex:

```java
wisefy.getRSSI("regex for SSID", true, 3000, new GetRSSICallbacks() {
    @Override
    public void retrievedRSSI(int rssi) {
    
    }
    
    @Override
    public void networkNotFoundToRetrieveRSSI() {
    
    }
    
    @Override
    public void getRSSIWiseFyFailure(int wisefyReturnCode) {
    
    }
});
```

***Notes***

- Will return a WiseFy error code if parameter is missing
- Will return a WiseFy error code if the instance has a missing prerequisite