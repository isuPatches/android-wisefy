#### Via The Synchronous API

To check and see if a network is secure (defined by having EAP/PSK/WPA/WPA2 capabilities):

_With Kotlin_

```kotlin
val isSecure = wisefy.isNetworkSecure(scanResult)
```

_With Java_

```java
boolean isSecure = wisefy.isNetworkSecure(scanResult);
```

To check and see if a network has EAP capabilities):

_With Kotlin_

```kotlin
val hasEAPCapabilities = wisefy.isNetworkEAP(scanResult)
```

_With Java_

```java
boolean hasEAPCapabilities = wisefy.isNetworkEAP(scanResult);
```

To check and see if a network has PSK capabilities):

_With Kotlin_

```kotlin
val hasPSKCapabilities = wisefy.isNetworkPSK(scanResult)
```

_With Java_

```java
boolean hasPSKCapabilities = wisefy.isNetworkPSK(scanResult);
```

To check and see if a network has WEP capabilities):

_With Kotlin_

```kotlin
val hasWEPCapabilities = wisefy.isNetworkWEP(scanResult)
```

_With Java_

```java
boolean hasWEPCapabilities = wisefy.isNetworkWEP(scanResult);
```

To check and see if a network has WPA capabilities):

_With Kotlin_

```kotlin
val hasWPACapabilities = wisefy.isNetworkWPA(scanResult)
```

_With Java_

```java
boolean hasWPACapabilities = wisefy.isNetworkWPA(scanResult);
```

To check and see if a network has WPA2 capabilities):

_With Kotlin_

```kotlin
val hasWPA2Capabilities = wisefy.isNetworkWPA2(scanResult)
```

_With Java_

```java
boolean hasWPA2Capabilities = wisefy.isNetworkWPA2(scanResult);
```