#### Via The Synchronous API

To get current network:

```java
WifiInfo currentNetwork = mWiseFy.getCurrentNetwork();
```

#### Via The Asynchronous API

To get current network:

```java
mWiseFy.getCurrentNetwork(new GetCurrentNetworkCallbacks() {
    @Override
    public void getCurrentNetworkWiseFyFailure(Integer wisefyReturnCode) {

    }

    @Override
    public void retrievedCurrentNetwork(WifiInfo currentNetwork) {

    }
});
```

***Notes***

- Will return a WiseFy error code if parameter is missing
- Will return a WiseFy error code if the instance has a missing prerequisite