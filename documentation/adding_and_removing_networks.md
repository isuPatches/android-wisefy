#### Via The Synchronous API

To add an open network:

```java
int newNetworkId = wisefy.addOpenNetwork("Open Network");
```

To add a WEP network:

```java
int newNetworkId = wisefy.addWEPNetwork("WEP Network", "123456");
```

To add a WPA2 network:

```java
int newNetworkId = wisefy.addWPA2Network("WPA2 Network", "12345678");
```

To remove a configured network:

```java
boolean removedSuccessfully = wisefy.removeNetwork("SSID to remove");
```

#### Via The Asynchronous API

To add an open network:

```java
wisefy.addOpenNetwork("Open Network", new AddOpenNetworkCallbacks() {
    @Override
    public void addOpenNetworkWiseFyFailure(int wisefyReturnCode) {
      
    }
    
    @Override
    public void failureAddingOpenNetwork(int wifiManagerReturn) {
    
    }
    
    @Override
    public void openNetworkAdded(int newNetworkId, WifiConfiguration openNetworkConfig) {
    
    }
});
```

To add a WEP network:

```java
wisefy.addWEPNetwork("WEP Network", "123456", new AddWEPNetworkCallbacks() {
    @Override
    public void addWEPNetworkWiseFyFailure(int wisefyReturnCode) {
      
    }
    
    @Override
    public void failureAddingWEPNetwork(int wifiManagerReturn) {
    
    }
    
    @Override
    public void wepNetworkAdded(int newNetworkId, WifiConfiguration wepNetworkConfig) {
    
    }
});
```

To add a WPA2 network:

```java
wisefy.addWPA2Network("WPA2 Network", "12345678", new AddWPA2NetworkCallbacks() {
    @Override
    public void addWPA2NetworkWiseFyFailure(int wisefyReturnCode) {
    
    }
    
    @Override
    public void failureAddingWPA2Network(int wifiManagerReturn) {
    
    }
    
    @Override
    public void wpa2NetworkAdded(int newNetworkId, WifiConfiguration wpa2Network) {
    
    }
});
```

To remove a configured network:

```java
wisefy.removeNetwork("SSID to remove", new RemoveNetworkCallbacks() {
    @Override
    public void failureRemovingNetwork() {
    
    }
    
    @Override
    public void networkNotFoundToRemove() {
    
    }
    
    @Override
    public void networkRemoved() {
    
    }
    
    @Override
    public void removeNetworkWiseFyFailure(int wisefyReturnCode) {
    
    }
});
```

***Notes***

- Will return a WiseFy error code if network is already a saved configuration
- Will return a WiseFy error code if parameter is missing
- Will return a WiseFy error code if the instance has a missing prerequisite