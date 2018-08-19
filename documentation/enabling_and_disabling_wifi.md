#### Via The Synchronous API

To disable wifi:

_With Kotlin_

```kotlin
val disabledWifiSuccessfully = wisefy.disableWifi()
```

_With Java_

```java
boolean disabledWifiSuccessfully = wisefy.disableWifi();
```

To enable wifi:

_With Kotlin_

```kotlin
val enabledWifiSuccessfully = wisefy.enableWiFi()
```

_With Java_

```java
boolean enabledWifiSuccessfully = wisefy.enableWiFi();
```

#### Via The Asynchronous API

To disable wifi:

_With Kotlin_

```kotlin
wisefy.disableWifi(object : DisableWifiCallbacks {
    override fun failureDisablingWifi() {

    }

    override fun wifiDisabled() {

    }

    override fun wisefyFailure(wisefyFailureCode: Int) {

    }
})
```

_With Java_

```java
wisefy.disableWifi(new DisableWifiCallbacks() {
    @Override
    public void failureDisablingWifi() {

    }

    @Override
    public void wifiDisabled() {

    }

    @Override
    public void wisefyFailure(int i) {

    }
});
```

To enable wifi:

_With Kotlin_

```kotlin
wisefy.enableWifi(object: EnableWifiCallbacks {
    override fun failureEnablingWifi() {

    }

    override fun wifiEnabled() {

    }

    override fun wisefyFailure(wisefyFailureCode: Int) {
    }
})
```

_With Java_

```java
wisefy.enableWifi(new EnableWifiCallbacks() {
    @Override
    public void failureEnablingWifi() {

    }

    @Override
    public void wifiEnabled() {

    }

    @Override
    public void wisefyFailure(int i) {

    }
});
```

***Notes***

- Will return a WiseFy error code if parameter is missing