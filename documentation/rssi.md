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

```java

```