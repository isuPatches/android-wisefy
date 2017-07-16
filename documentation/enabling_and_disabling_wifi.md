#### Via The Synchronous API

To disable wifi:

```java
boolean disabledWifiSuccessfully = mWiseFy.disableWifi();
```

To enable wifi:

```java
boolean wifiEnabled = mWiseFy.enableWiFi();
```

#### Via The Asynchronous API

To disable wifi:

```java
mWiseFy.disableWifi(new DisableWifiCallbacks() {
    @Override
    public void disableWifiWiseFyFailure(Integer wisefyReturnCode) {

    }

    @Override
    public void failureDisablingWifi() {

    }

    @Override
    public void wifiDisabled() {

    }
});
```

To enable wifi:

```java
mWiseFy.enableWifi(new EnableWifiCallbacks() {
    @Override
    public void enableWifiWiseFyFailure(Integer wisefyReturnCode) {

    }

    @Override
    public void failureEnablingWifi() {

    }

    @Override
    public void wifiEnabled() {

    }
});
```

***Notes***

- Will return a WiseFy error code if parameter is missing
- Will return a WiseFy error code if the instance has a missing prerequisite