#### Via The Synchronous API

To get the first saved network that matches a given regex:

_With Kotlin_

```kotlin
val savedNetwork = wisefy.getSavedNetwork("regex for SSID")
```

_With Java_

```java
WifiConfiguration savedNetwork = wisefy.getSavedNetwork("regex for SSID");
```

To retrieve all of the saved networks:

_With Kotlin_

```kotlin
val savedNetworks = wisefy.getSavedNetworks()
```

_With Java_

```java
List<WifiConfiguration> savedNetworks = wisefy.getSavedNetworks();
```

To return all saved networks that match a given regex:

_With Kotlin_

```kotlin
val savedNetworks = wisefy.getSavedNetworks("regex for SSID")
```

_With Java_

```java
List<WifiConfiguration> savedNetworks = wisefy.getSavedNetworks("regex for SSID");
```

To check and see if a given SSID is in the devices set of configured networks:

_With Kotlin_

```kotlin
val saved = wisefy.isNetworkSaved("regex for SSID")
```

_With Java_

```java
boolean saved = wisefy.isNetworkSaved("regex for SSID");
```

#### Via The Asynchronous API

To get the first saved network that matches a given regex:

_With Kotlin_

```kotlin
wisefy.getSavedNetwork("regex for SSID", object: GetSavedNetworkCallbacks {
    override fun retrievedSavedNetwork(savedNetwork: WifiConfiguration) {

    }

    override fun savedNetworkNotFound() {

    }

    override fun wisefyFailure(wisefyFailureCode: Int) {

    }
})
```

_With Java_

```java
wisefy.getSavedNetwork("regex for SSID", new GetSavedNetworkCallbacks() {
    @Override
    public void retrievedSavedNetwork(WifiConfiguration savedNetwork) {
    
    }
        
    @Override
    public void savedNetworkNotFound() {

    }

    @Override
    public void wisefyFailure(int wisefyFailureCode) {

    }
});
```

To retrieve all of the saved networks:

_With Kotlin_

```kotlin
wisefy.getSavedNetworks(object: GetSavedNetworksCallbacks {
    override fun retrievedSavedNetworks(savedNetworks: List<WifiConfiguration>) {

    }

    override fun noSavedNetworksFound() {

    }
    
    override fun wisefyFailure(wisefyFailureCode: Int) {

    }
})
```

_With Java_

```java
wisefy.getSavedNetworks(new GetSavedNetworksCallbacks() {
    @Override
    public void retrievedSavedNetworks(List<WifiConfiguration> savedNetworks) {
    
    }
    
    @Override
    public void noSavedNetworksFound() {

    }

    @Override
    public void wisefyFailure(int wisefyFailureCode) {

    }
});
```

To return all saved networks that match a given regex:

_With Kotlin_

```kotlin
wisefy.getSavedNetworks("regex for SSID", object: GetSavedNetworksCallbacks {
    override fun retrievedSavedNetworks(savedNetworks: List<WifiConfiguration>) {

    }

    override fun noSavedNetworksFound() {

    }

    override fun wisefyFailure(wisefyFailureCode: Int) {

    }
})
```

_With Java_

```java
wisefy.getSavedNetworks("regex for SSID", new GetSavedNetworksCallbacks() {
    @Override
    public void retrievedSavedNetworks(List<WifiConfiguration> savedNetworks) {

    }

    @Override
    public void noSavedNetworksFound() {

    }
    
    @Override
    public void wisefyFailure(int wisefyFailureCode) {

    }
});
```

***Notes***

- Will return a WiseFy error code if parameter is missing