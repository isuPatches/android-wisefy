#### Via The Synchronous API

To get the frequency of the devices current network:<br/><br/>
<strong>* NOTE *</strong> Only supported on API >= 21

```java
int frequency = mWiseFy.getFrequency();
```

To get the frequency of a network:<br/><br/>
<strong>* NOTE *</strong> Only supported on API >= 21

```java
int frequency = mWiseFy.getFrequency(wifiInfo);
```

To check if the device's current network is 5gHz:<br/><br/>
<strong>* NOTE *</strong> Only supported on API >= 21

```java
boolean is5gHz = mWiseFy.isNetwork5gHz();
```

To check if a network is 5gHz:<br/><br/>
<strong>* NOTE *</strong> Only supported on API >= 21

```java
boolean is5gHz = mWiseFy.isNetwork5gHz(wifiInfo);
```

#### Via The Asynchronous API

To get the frequency of the devices current network:<br/><br/>
<strong>* NOTE *</strong> Only supported on API >= 21

```java
mWiseFy.getFrequency(new GetFrequencyCallbacks() {
    @Override
    public void failureGettingFrequency() {

    }

    @Override
    public void getFrequencyWiseFyFailure(Integer wisefyReturnCode) {

    }

    @Override
    public void retrievedFrequency(int frequency) {

    }
});
```

To get the frequency of a network:<br/><br/>
<strong>* NOTE *</strong> Only supported on API >= 21

```java
mWiseFy.getFrequency(wifiInfo, new GetFrequencyCallbacks() {
    @Override
    public void failureGettingFrequency() {

    }

    @Override
    public void getFrequencyWiseFyFailure(Integer wisefyReturnCode) {

    }

    @Override
    public void retrievedFrequency(int frequency) {

    }
});
```