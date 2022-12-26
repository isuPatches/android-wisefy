//[core](../../index.md)/[com.isupatches.android.wisefy.core.util](index.md)

# Package com.isupatches.android.wisefy.core.util

## Types

| Name | Summary |
|---|---|
| [SdkUtil](-sdk-util/index.md) | [androidJvm]<br>interface [SdkUtil](-sdk-util/index.md)<br>An interface that helps the library determine the SDK level of the device. |
| [SdkUtilImpl](-sdk-util-impl/index.md) | [androidJvm]<br>class [SdkUtilImpl](-sdk-util-impl/index.md) : [SdkUtil](-sdk-util/index.md)<br>A default implementation of [SdkUtil](-sdk-util/index.md) that helps the library determine the SDK level of the device. |

## Functions

| Name | Summary |
|---|---|
| [withTimeout](with-timeout.md) | [androidJvm]<br>fun [withTimeout](with-timeout.md)(timeoutInMillis: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), block: () -&gt; [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>A convenience function that will wait for a given time until the provided function returns true. |
| [withTimeoutAsync](with-timeout-async.md) | [androidJvm]<br>suspend fun [withTimeoutAsync](with-timeout-async.md)(timeoutInMillis: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), block: suspend () -&gt; [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>A convenience function that will wait for a given time until the provided function returns true. |
