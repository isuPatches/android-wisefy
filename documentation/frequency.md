#### Via The Synchronous API

To get the frequency of the devices current network:<br/><br/>
<strong>* NOTE *</strong> Only supported on API >= 21

_With Kotlin_

```kotlin
val frequency = wisefy.getFrequency()
```

_With Java_

```java
int frequency = wisefy.getFrequency();
```

To get the frequency of a network:<br/><br/>
<strong>* NOTE *</strong> Only supported on API >= 21

_With Kotlin_

```kotlin
val frequency = wisefy.getFrequency(wifiInfo)
```

_With Java_

```java
int frequency = wisefy.getFrequency(wifiInfo);
```

To check if the device's current network is 5gHz:<br/><br/>
<strong>* NOTE *</strong> Only supported on API >= 21

_With Kotlin_

```kotlin
val is5gHz = wisefy.isNetwork5gHz()
```

_With Java_

```java
boolean is5gHz = wisefy.isNetwork5gHz();
```

To check if a network is 5gHz:<br/><br/>
<strong>* NOTE *</strong> Only supported on API >= 21

_With Kotlin_

```kotlin
val is5gHz = wisefy.isNetwork5gHz(wifiInfo)
```

_With Java_

```java
boolean is5gHz = wisefy.isNetwork5gHz(wifiInfo);
```

#### Via The Asynchronous API

To get the frequency of the devices current network:<br/><br/>
<strong>* NOTE *</strong> Only supported on API >= 21

_With Kotlin_

```kotlin
wisefy.getFrequency(object: GetFrequencyCallbacks {
    override fun failureGettingFrequency() {

    }

    override fun retrievedFrequency(frequency: Int) {

    }

    override fun wisefyFailure(wisefyFailureCode: Int) {

    }
})
```

_With Java_

```java
wisefy.getFrequency(new GetFrequencyCallbacks() {
    @Override
    public void failureGettingFrequency() {

    }

    @Override
    public void retrievedFrequency(int i) {

    }

    @Override
    public void wisefyFailure(int i) {

    }
});
```

To get the frequency of a network:<br/><br/>
<strong>* NOTE *</strong> Only supported on API >= 21

_With Kotlin_

```kotlin
wisefy.getFrequency(wifiInfo, object: GetFrequencyCallbacks {
    override fun failureGettingFrequency() {

    }

    override fun retrievedFrequency(frequency: Int) {

    }

    override fun wisefyFailure(wisefyFailureCode: Int) {

    }
})
```

_With Java_

```java
wisefy.getFrequency(wifiInfo, new GetFrequencyCallbacks() {
    @Override
    public void failureGettingFrequency() {

    }

    @Override
    public void retrievedFrequency(int i) {

    }

    @Override
    public void wisefyFailure(int i) {
    
    }
});
```