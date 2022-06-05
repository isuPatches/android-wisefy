//[accesspoints](../../../index.md)/[com.isupatches.android.wisefy.accesspoints.callbacks](../index.md)/[SearchForSSIDCallbacks](index.md)

# SearchForSSIDCallbacks

[androidJvm]\
interface [SearchForSSIDCallbacks](index.md) : BaseWisefyCallbacks

A set of callbacks for retrieving a single SSID.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| com.isupatches.android.wisefy.core.base.BaseWisefyCallbacks |  |

## Functions

| Name | Summary |
|---|---|
| [equals](../../com.isupatches.android.wisefy.accesspoints.entities/-search-for-single-s-s-i-d-request/-b-s-s-i-d/index.md#585090901%2FFunctions%2F974708819) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.accesspoints.entities/-search-for-single-s-s-i-d-request/-b-s-s-i-d/index.md#585090901%2FFunctions%2F974708819)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../com.isupatches.android.wisefy.accesspoints.entities/-search-for-single-s-s-i-d-request/-b-s-s-i-d/index.md#1794629105%2FFunctions%2F974708819) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.accesspoints.entities/-search-for-single-s-s-i-d-request/-b-s-s-i-d/index.md#1794629105%2FFunctions%2F974708819)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [onSSIDFound](on-s-s-i-d-found.md) | [androidJvm]<br>abstract fun [onSSIDFound](on-s-s-i-d-found.md)(ssid: [SSIDData](../../com.isupatches.android.wisefy.accesspoints.entities/-s-s-i-d-data/index.md))<br>A callback triggered when there is a matching SSID. |
| [onSSIDNotFound](on-s-s-i-d-not-found.md) | [androidJvm]<br>abstract fun [onSSIDNotFound](on-s-s-i-d-not-found.md)()<br>A callback triggered when there is no matching SSID. |
| [onWisefyAsyncFailure](../-search-for-s-s-i-ds-callbacks/index.md#823639724%2FFunctions%2F974708819) | [androidJvm]<br>abstract fun [onWisefyAsyncFailure](../-search-for-s-s-i-ds-callbacks/index.md#823639724%2FFunctions%2F974708819)(throwable: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)) |
| [toString](../../com.isupatches.android.wisefy.accesspoints.entities/-search-for-single-s-s-i-d-request/-b-s-s-i-d/index.md#1616463040%2FFunctions%2F974708819) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.accesspoints.entities/-search-for-single-s-s-i-d-request/-b-s-s-i-d/index.md#1616463040%2FFunctions%2F974708819)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
