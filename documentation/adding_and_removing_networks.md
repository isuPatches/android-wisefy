#### Via The Synchronous API

To add an open network:

_With Kotlin_

```kotlin
val result = wisefy.addOpenNetwork("Open Network")
```

_With Java_

```java
int result = wisefy.addOpenNetwork("Open Network");
```

To add a WEP network:

_With Kotlin_

```kotlin
val result = wisefy.addWEPNetwork("WEP Network", "123456")
```

_With Java_

```java
int result = wisefy.addWEPNetwork("WEP Network", "123456");
```

To add a WPA2 network:

_With Kotlin_

```kotlin
val result = wisefy.addWEPNetwork("WPA2 Network", "123456")
```

_With Java_

```java
int result = wisefy.addWPA2Network("WPA2 Network", "12345678");
```

To remove a configured network:

_With Kotlin_

```kotlin
val removedSuccessfully = wisefy.removeNetwork("SSID to remove");
```

_With Java_

```java
boolean removedSuccessfully = wisefy.removeNetwork("SSID to remove");
```

#### Via The Asynchronous API

To add an open network:

_With Kotlin_

```kotlin
wisefy.addOpenNetwork("Open Network", object: AddNetworkCallbacks {
    override fun networkAdded(newNetworkId: Int, networkConfig: WifiConfiguration) {

    }
    
    override fun failureAddingNetwork(wifiManagerReturn: Int) {

    }

    override fun wisefyFailure(wisefyFailureCode: Int) {

    }
})
```

_With Java_

```java
wisefy.addOpenNetwork("Open Network", new AddNetworkCallbacks() {
    @Override
    public void networkAdded(int newNetworkId, WifiConfiguration wifiConfiguration) {
    
    }
    
    @Override
    public void failureAddingNetwork(int wifiManagerReturn) {

    }

    @Override
    public void wisefyFailure(int wisefyFailureCode) {

    }
});
```

To add a WEP network:

_With Kotlin_

```Kotlin
wisefy.addWEPNetwork("WEP Network", "123456", object: AddNetworkCallbacks {
    override fun networkAdded(newNetworkId: Int, networkConfig: WifiConfiguration) {
    
    }
    
    override fun failureAddingNetwork(wifiManagerReturn: Int) {
    
    }

    override fun wisefyFailure(wisefyFailureCode: Int) {

    }
})
```

_With Java_

```java
wisefy.addWEPNetwork("WEP Network", "123456", new AddNetworkCallbacks() {
    @Override
    public void networkAdded(int newNetworkId, WifiConfiguration wifiConfiguration) {

    }
        
    @Override
    public void failureAddingNetwork(int wifiManagerReturn) {

    }

    @Override
    public void wisefyFailure(int wisefyFailureCode) {

    }
});
```

To add a WPA2 network:

_With Kotlin_

```kotlin
wisefy.addWPA2Network("WPA2 Network", "12345678", object: AddNetworkCallbacks {
    override fun networkAdded(newNetworkId: Int, networkConfig: WifiConfiguration) {

    }
    
    override fun failureAddingNetwork(wifiManagerReturn: Int) {

    }

    override fun wisefyFailure(wisefyFailureCode: Int) {

    }
})
```

_With Java_

```java
wisefy.addWPA2Network("WPA2 Network", "12345678", new AddNetworkCallbacks() {
    @Override
    public void networkAdded(int newNetworkId, WifiConfiguration wifiConfiguration) {

    }
        
    @Override
    public void failureAddingNetwork(int wifiManagerReturn) {

    }

    @Override
    public void wisefyFailure(int wisefyFailureCode) {

    }
});
```

To remove a configured network:

_With Kotlin_

```kotlin
wisefy.removeNetwork("SSID to remove", object: RemoveNetworkCallbacks {
    override fun networkRemoved() {

    }
    
    override fun failureRemovingNetwork() {

    }

    override fun networkNotFoundToRemove() {

    }

    override fun wisefyFailure(wisefyFailureCode: Int) {

    }
})
```

_With Java_

```java
wisefy.removeNetwork("SSID to remove", new RemoveNetworkCallbacks() {
    @Override
    public void networkRemoved() {

    }
        
    @Override
    public void failureRemovingNetwork() {

    }

    @Override
    public void networkNotFoundToRemove() {

    }

    @Override
    public void wisefyFailure(int wisefyFailureCode) {

    }
});
```

***Notes***

- Will return a WiseFy error code if network is already a saved configuration
- Will return a WiseFy error code if parameter is missing