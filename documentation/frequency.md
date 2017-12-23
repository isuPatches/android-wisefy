#### Via The Synchronous API

To get the frequency of the devices current network:<br/><br/>
<strong>* NOTE *</strong> Only supported on API >= 21

```java
int frequency = wisefy.getFrequency();
```

To get the frequency of a network:<br/><br/>
<strong>* NOTE *</strong> Only supported on API >= 21

```java
int frequency = wisefy.getFrequency(wifiInfo);
```

To check if the device's current network is 5gHz:<br/><br/>
<strong>* NOTE *</strong> Only supported on API >= 21

```java
boolean is5gHz = wisefy.isNetwork5gHz();
```

To check if a network is 5gHz:<br/><br/>
<strong>* NOTE *</strong> Only supported on API >= 21

```java
boolean is5gHz = wisefy.isNetwork5gHz(wifiInfo);
```

#### Via The Asynchronous API

To get the frequency of the devices current network:<br/><br/>
<strong>* NOTE *</strong> Only supported on API >= 21

```java
wisefy.getFrequency(new GetFrequencyCallbacks() {
    @Override
    public void failureGettingFrequency() {
    
    }
    
    @Override
    public void getFrequencyWiseFyFailure(int wisefyReturnCode) {
    
    }
    
    @Override
    public void retrievedFrequency(int frequency) {
    
    }
});
```

To get the frequency of a network:<br/><br/>
<strong>* NOTE *</strong> Only supported on API >= 21

```java
wisefy.getFrequency(wifiInfo, new GetFrequencyCallbacks() {
    @Override
    public void failureGettingFrequency() {
      
    }
    
    @Override
    public void getFrequencyWiseFyFailure(int wisefyReturnCode) {
    
    }
    
    @Override
    public void retrievedFrequency(int frequency) {
    
    }
});
```