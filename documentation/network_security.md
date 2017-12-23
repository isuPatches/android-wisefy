#### Via The Synchronous API

To check and see if a network is secure (defined by having EAP/PSK/WPA/WPA2 capabilities):

```java
boolean isSecure = wisefy.isNetworkSecure(scanResult);
```

To check and see if a network has EAP capabilities):

```java
boolean hasEAPCapabilities = wisefy.isNetworkEAP(scanResult);
```

To check and see if a network has PSK capabilities):

```java
boolean hasPSKCapabilities = wisefy.isNetworkPSK(scanResult);
```

To check and see if a network has WEP capabilities):

```java
boolean hasWEPCapabilities = wisefy.isNetworkWEP(scanResult);
```

To check and see if a network has WPA capabilities):

```java
boolean hasWPACapabilities = wisefy.isNetworkWPA(scanResult);
```

To check and see if a network has WPA2 capabilities):

```java
boolean hasWPA2Capabilities = wisefy.isNetworkWPA2(scanResult);
```