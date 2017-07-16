#### Via The Synchronous API

To add an open network:

```java
int addedSuccessfully = mWiseFy.addOpenNetwork("Open Network");
```

To add a WEP network:

```java
int addedSuccessfully = mWiseFy.addWEPNetwork("WEP Network", "123456");
```

To add a WPA2 network:

```java
int addedSuccessfully = mWiseFy.addWPA2Network("WPA2 Network", "12345678");
```

To remove a configured network:

```java
boolean removedSuccessfully = mWiseFy.removeNetwork("SSID to remove");
```

#### Via The Asynchronous API

To add an open network:

```java
mWiseFy.addOpenNetwork("Open Network", new AddOpenNetworkCallbacks() {
    @Override
    public void addOpenNetworkWiseFyFailure(Integer wisefyReturnCode) {

    }

    @Override
    public void failureAddingOpenNetwork(Integer wifiManagerReturnCode) {

    }

    @Override
    public void openNetworkAdded(WifiConfiguration wifiConfiguration) {

    }
});
```

To add a WEP network:

```java
mWiseFy.addWEPNetwork("WEP Network", "123456", new AddWEPNetworkCallbacks() {
    @Override
    public void addWEPNetworkWiseFyFailure(Integer wisefyReturnCode) {

    }

    @Override
    public void failureAddingWEPNetwork(Integer wifiManagerReturnCode) {

    }

    @Override
    public void wepNetworkAdded(WifiConfiguration wifiConfiguration) {

    }
});
```

To add a WPA2 network:

```java
mWiseFy.addWPA2Network("WPA2 Network", "12345678", new AddWPA2NetworkCallbacks() {
    @Override
    public void addWPA2NetworkWiseFyFailure(Integer wisefyReturnCode) {

    }

    @Override
    public void failureAddingWPA2Network(Integer wifiManagerReturnCode) {

    }

    @Override
    public void wpa2NetworkAdded(WifiConfiguration wifiConfiguration) {

    }
});
```

To remove a configured network:

```java
mWiseFy.removeNetwork("SSID to remove", new RemoveNetworkCallbacks() {
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
    public void removeNetworkWiseFyFailure(Integer wisefyReturnCode) {

    }
});
```

***Notes***

- Will return a WiseFy error code if network is already a saved configuration
- Will return a WiseFy error code if parameter is missing
- Will return a WiseFy error code if the instance has a missing prerequisite