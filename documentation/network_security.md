#### Via The Synchronous API

To check and see if a network is secure (defined by having EAP/PSK/WPA/WPA2 capabilities):

```java
boolean isSecure = mWiseFy.isNetworkSecure(scanResult);
```

To check and see if a network has EAP capabilities):

```java
boolean hasEAPCapabilities = mWiseFy.isNetworkEAP(scanResult);
```

To check and see if a network has PSK capabilities):

```java
boolean hasPSKCapabilities = mWiseFy.isNetworkPSK(scanResult);
```

To check and see if a network has WEP capabilities):

```java
boolean hasWEPCapabilities = mWiseFy.isNetworkWEP(scanResult);
```

To check and see if a network has WPA capabilities):

```java
boolean hasWPACapabilities = mWiseFy.isNetworkWPA(scanResult);
```

To check and see if a network has WPA2 capabilities):

```java
boolean hasWPA2Capabilities = mWiseFy.isNetworkWPA2(scanResult);
```