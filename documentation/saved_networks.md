#### Via The Synchronous API

To search for the first saved network that matches a given regex:

_With Kotlin_

```kotlin
val savedNetwork = wisefy.searchForSavedNetwork("regex for SSID")
```

_With Java_

```java
WifiConfiguration savedNetwork = wisefy.searchForSavedNetwork("regex for SSID");
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
val savedNetworks = wisefy.searchForSavedNetworks("regex for SSID")
```

_With Java_

```java
List<WifiConfiguration> savedNetworks = wisefy.searchForSavedNetworks("regex for SSID");
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
wisefy.searchForSavedNetwork("regex for SSID", object: SearchForSavedNetworkCallbacks {
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
wisefy.searchForSavedNetwork("regex for SSID", new SearchForSavedNetworkCallbacks() {
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
wisefy.searchForSavedNetworks("regex for SSID", object: SearchForSavedNetworksCallbacks {
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
wisefy.searchForSavedNetworks("regex for SSID", new SearchForSavedNetworksCallbacks() {
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