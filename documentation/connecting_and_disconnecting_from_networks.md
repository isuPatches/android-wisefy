#### Via The Synchronous API

To connect to a network given an SSID:

```java
boolean connectedSuccessfully = wisefy.connectToNetwork("SSID to connect to", 30000);
```

To disconnect from current network:

```java
boolean disconnectedSuccessfully = wisefy.disconnectFromCurrentNetwork();
```

#### Via The Asynchronous API

To connect to a network given an SSID:

```java
wisefy.connectToNetwork("SSID to connect to", 30000, new ConnectToNetworkCallbacks() {
    @Override
    public void connectedToNetwork() {
     
    }
    
    @Override
    public void connectToNetworkWiseFyFailure(int wisefyReturnCode) {
    
    }
    
    @Override
    public void failureConnectingToNetwork() {
    
    }
    
    @Override
    public void networkNotFoundToConnectTo() {
    
    }
});
```

To disconnect from current network:

```java
wisefy.disconnectFromCurrentNetwork(new DisconnectFromCurrentNetworkCallbacks() {
  @Override
  public void disconnectedFromCurrentNetwork() {
    
  }
  
  @Override
  public void disconnectFromCurrentNetworkWiseFyFailure(int wisefyReturnCode) {
  
  }
  
  @Override
  public void failureDisconnectingFromCurrentNetwork() {
  
  }
});
```

***Notes***

- Will return a WiseFy error code if parameter is missing
- Will return a WiseFy error code if the instance has a missing prerequisite