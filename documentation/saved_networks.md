#### Via The Synchronous API

To get the first saved network that matches a given regex:

```java
WifiConfiguration savedNetwork = mWiseFy.getSavedNetwork("regex for SSID");
```

To retrieve all of the saved networks:

```java
List<WifiConfiguration> savedNetworks = mWiseFy.getSavedNetworks();
```

To return all saved networks that match a given regex:

```java
List<WifiConfiguration> savedNetworks = mWiseFy.getSavedNetworks("regex for SSID");
```

To check and see if a given SSID is in the devices set of configured networks:

```java
boolean isConfigured = mWiseFy.isNetworkInConfigurationList("regex for SSID");
```

#### Via The Asynchronous API

To get the first saved network that matches a given regex:

```java
  mWiseFy.getSavedNetwork("regex for SSID", new GetSavedNetworkCallbacks() {
    @Override
    public void getSavedNetworkWiseFyFailure(Integer wisefyReturnCode) {

    }

    @Override
    public void savedNetworkNotFound() {

    }

    @Override
    public void retrievedSavedNetwork(WifiConfiguration savedNetwork) {

    }
});
```

To retrieve all of the saved networks:

```java
mWiseFy.getSavedNetworks(new GetSavedNetworksCallbacks() {
    @Override
    public void getSavedNetworksWiseFyFailure(Integer wisefyReturnCode) {

    }

    @Override
    public void noSavedNetworksFound() {

    }

    @Override
    public void retrievedSavedNetworks(List<WifiConfiguration> savedNetworks) {

    }
});
```

To return all saved networks that match a given regex:

```java
mWiseFy.getSavedNetwork("regex for SSID", new GetSavedNetworkCallbacks() {
    @Override
    public void getSavedNetworkWiseFyFailure(Integer wisefyReturnCode) {

    }

    @Override
    public void savedNetworkNotFound() {

    }

    @Override
    public void retrievedSavedNetwork(WifiConfiguration savedNetwork) {

    }
});
```

***Notes***

- Will return a WiseFy error code if parameter is missing
- Will return a WiseFy error code if the instance has a missing prerequisite