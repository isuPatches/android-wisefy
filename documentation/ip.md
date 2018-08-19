#### Via The Synchronous API

To get the device's current IP:

_With Kotlin_

```kotlin
val ip = wisefy.getIP()
```

_With Java_

```java
String ip = wisefy.getIP();
```

#### Via The Asynchronous API

To get the device's current IP:

_With Kotlin_

```kotlin
wisefy.getIP(object: GetIPCallbacks {
    override fun failureRetrievingIP() {

    }

    override fun retrievedIP(ip: String) {

    }

    override fun wisefyFailure(wisefyFailureCode: Int) {

    }
})
```

_With Java_

```java
wisefy.getIP(new GetIPCallbacks() {
    @Override
    public void failureRetrievingIP() {

    }

    @Override
    public void retrievedIP(String s) {

    }

    @Override
    public void wisefyFailure(int i) {

    }
});
```