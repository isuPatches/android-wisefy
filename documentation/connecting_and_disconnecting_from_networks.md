#### Via The Synchronous API

To connect to a network given an SSID:

_With Kotlin_

```kotlin
val connectedSuccessfully = wisefy.connectToNetwork("SSID to connect to", 3000);
```

_With Java_

```java
boolean connectedSuccessfully = wisefy.connectToNetwork("SSID to connect to", 3000);
```

To disconnect from current network:

_With Kotlin_

```kotlin
val disconnectedSuccessfully = wisefy.disconnectFromCurrentNetwork();
```

_With Java_

```java
boolean disconnectedSuccessfully = wisefy.disconnectFromCurrentNetwork();
```

#### Via The Asynchronous API

To connect to a network given an SSID:

_With Kotlin_

```kotlin
wisefy.connectToNetwork("SSID to connect to", 3000, object: ConnectToNetworkCallbacks {
    override fun connectedToNetwork() {

    }

    override fun failureConnectingToNetwork() {

    }

    override fun networkNotFoundToConnectTo() {

    }

    override fun wisefyFailure(wisefyFailureCode: Int) {

    }
})
```

_With Java_

```java
wisefy.connectToNetwork("SSID to connect to", 3000, new ConnectToNetworkCallbacks() {
    @Override
    public void connectedToNetwork() {

    }

    @Override
    public void failureConnectingToNetwork() {

    }

    @Override
    public void networkNotFoundToConnectTo() {

    }

    @Override
    public void wisefyFailure(int i) {

    }
});
```

To disconnect from current network:

_With Kotlin_

```Kotlin
wisefy.disconnectFromCurrentNetwork(object: DisconnectFromCurrentNetworkCallbacks {
    override fun disconnectedFromCurrentNetwork() {

    }

    override fun failureDisconnectingFromCurrentNetwork() {

    }

    override fun wisefyFailure(wisefyFailureCode: Int) {

    }
})
```

_With Java_

```java
wisefy.disconnectFromCurrentNetwork(new DisconnectFromCurrentNetworkCallbacks() {
    @Override
    public void disconnectedFromCurrentNetwork() {

    }

    @Override
    public void failureDisconnectingFromCurrentNetwork() {

    }

    @Override
    public void wisefyFailure(int i) {

    }
});
```

***Notes***

- Will return a WiseFy error code if parameter is missing