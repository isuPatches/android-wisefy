#### Via The Synchronous API

To disable wifi:

```java
boolean disabledWifiSuccessfully = wisefy.disableWifi();
```

To enable wifi:

```java
boolean wifiEnabled = wisefy.enableWiFi();
```

#### Via The Asynchronous API

To disable wifi:

```java
wisefy.disableWifi(new DisableWifiCallbacks() {
    @Override
    public void disableWifiWiseFyFailure(int wisefyReturnCode) {
    
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
wisefy.enableWifi(new EnableWifiCallbacks() {
    @Override
    public void enableWifiWiseFyFailure(int wisefyReturnCode) {
      
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