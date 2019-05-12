To grab an instance with legacy connection enabled:

_With Kotlin_

```kotlin
wiseFy = WiseFy.Brains(activity!!, useLegacyConnection = true, useLegacySearch = false)
                .logging(true)
                .getSmarts()

_With Java_

```java
WiseFy wisefy = new WiseFy.Brains(getActivity(), true, false).logging(true).getSmarts();
```

To grab an instance with legacy search enabled:

_With Kotlin_

```kotlin
wiseFy = WiseFy.Brains(activity!!, useLegacyConnection = false, useLegacySearch = true)
                .logging(true)
                .getSmarts()
```

_With Java_

```java
WiseFy wisefy = new WiseFy.Brains(getActivity(), false, true).logging(true).getSmarts();
```

To grab an instance with both legacy search and legacy connection enabled:

_With Kotlin_

```kotlin
wiseFy = WiseFy.Brains(activity!!, useLegacyConnection = true, useLegacySearch = true)
                .logging(true)
                .getSmarts()
```

_With Java_

```java
WiseFy wisefy = new WiseFy.Brains(getActivity(), true, true).logging(true).getSmarts();
```