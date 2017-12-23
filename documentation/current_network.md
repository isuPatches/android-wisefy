#### Via The Synchronous API

To get current network:

```java
WifiInfo currentNetwork = wisefy.getCurrentNetwork();
```

#### Via The Asynchronous API

To get current network:

```java
wisefy.getCurrentNetwork(new GetCurrentNetworkCallbacks() {
    @Override
    public void getCurrentNetworkWiseFyFailure(int wisefyReturnCode) {
    
    }
    
    @Override
    public void retrievedCurrentNetwork(WifiInfo currentNetwork) {
    
    }
});
```

***Notes***

- Will return a WiseFy error code if parameter is missing
- Will return a WiseFy error code if the instance has a missing prerequisite