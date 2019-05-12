#### Via The Synchronous API

To get current network:

_With Kotlin_

```Kotlin
val currentNetwork = wisefy.getCurrentNetwork()
```

_With Java_

```java
WifiInfo currentNetwork = wisefy.getCurrentNetwork();
```

To get current network info:

_With Kotlin_

```kotlin
val currentNetworkInfo = wisefy.getCurrentNetworkInfo()
```

_With Java_

```java
NetworkInfo currentNetworkInfo = wisefy.getCurrentNetworkInfo();
```

#### Via The Asynchronous API

To get current network:

_With Kotlin_

```kotlin
wisefy.getCurrentNetwork(object: GetCurrentNetworkCallbacks {
    override fun retrievedCurrentNetwork(currentNetwork: WifiInfo) {

    }

    override fun noCurrentNetwork() {
            
    }
    
    override fun wisefyFailure(wisefyFailureCode: Int) {

    }
})
```

_With Java_

```java
wisefy.getCurrentNetwork(new GetCurrentNetworkCallbacks() {
    @Override
    public void retrievedCurrentNetwork(WifiInfo wifiInfo) {

    }
    
    @Override 
    public void noCurrentNetwork() {
        
    }

    @Override
    public void wisefyFailure(int wisefyFailureCode) {

    }
});
```

To get current network info:

_With Kotlin_

```kotlin
wisefy.getCurrentNetworkInfo(object: GetCurrentNetworkInfoCallbacks {
   
    override fun retrievedCurrentNetworkInfo(currentNetworkDetails: NetworkInfo) {
    
    }
    
    override fun noCurrentNetworkInfo() {
        
    }

    override fun wisefyFailure(wisefyFailureCode: Int) {
   
    }
})
```

_With Java_

```java
wisefy.getCurrentNetworkInfo(new GetCurrentNetworkInfoCallbacks() {
    @Override
    public void retrievedCurrentNetworkInfo(NetworkInfo networkInfo) {

    }
    
    @Override 
    public void noCurrentNetworkInfo() {
            
    }

    @Override
    public void wisefyFailure(int wisefyFailureCode) {

    }
});
```

***Notes***

- Will return a WiseFy error code if parameter is missing