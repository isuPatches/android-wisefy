#### Via The Synchronous API

To get the networks whose configuration is already stored:

```java
List<WifiConfiguration> savedNetworks = mWiseFy.getSavedNetworks();
```

To get a network that is already stored:

```java
mWiseFy.getSavedNetwork("Saved network SSID")
```

To check and see if a given SSID is in the devices set of configured networks:

```java
boolean isConfigured = mWiseFy.isNetworkInConfigurationList("SSID");
```

#### Via The Asynchronous API

To get the networks whose configuration is already stored:

```java
mWiseFy.getSavedNetworks(new GetSavedNetworksCallbacks() {
    @Override
    public void getSavedNetworksWiseFyFailure(Integer wisefyReturnCode) {

    }

    @Override
    public void retrievedSavedNetworks(List<WifiConfiguration> savedNetworks) {

    }
});
```

To get a network that is already stored:

```java
mWiseFy.getSavedNetwork("Saved network SSID", new GetSavedNetworkCallbacks() {
    @Override
    public void getSavedNetworkWiseFyFailure(Integer wisefyReturnCode) {

    }

    @Override
    public void savedNtworkNotFound() {

    }

    @Override
    public void retrievedSavedNetwork(WifiConfiguration savedNetwork) {

    }
});
```

***Notes***

- Will return a WiseFy error code if parameter is missing
- Will return a WiseFy error code if the instance has a missing prerequisite