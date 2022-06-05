//[accesspoints](../../../index.md)/[com.isupatches.android.wisefy.accesspoints.callbacks](../index.md)/[SearchForSSIDsCallbacks](index.md)

# SearchForSSIDsCallbacks

[androidJvm]\
interface [SearchForSSIDsCallbacks](index.md) : BaseWisefyCallbacks

A set of callbacks for retrieving multiple SSIDs.

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
| [onNoSSIDsFound](on-no-s-s-i-ds-found.md) | [androidJvm]<br>abstract fun [onNoSSIDsFound](on-no-s-s-i-ds-found.md)()<br>A callback triggered when there are no matching matching SSIDs. |
| [onSSIDsFound](on-s-s-i-ds-found.md) | [androidJvm]<br>abstract fun [onSSIDsFound](on-s-s-i-ds-found.md)(ssids: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[SSIDData](../../com.isupatches.android.wisefy.accesspoints.entities/-s-s-i-d-data/index.md)&gt;)<br>A callback triggered when there are matching SSIDs. |
| [onWisefyAsyncFailure](index.md#823639724%2FFunctions%2F974708819) | [androidJvm]<br>abstract fun [onWisefyAsyncFailure](index.md#823639724%2FFunctions%2F974708819)(throwable: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)) |
| [toString](../../com.isupatches.android.wisefy.accesspoints.entities/-search-for-single-s-s-i-d-request/-b-s-s-i-d/index.md#1616463040%2FFunctions%2F974708819) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.accesspoints.entities/-search-for-single-s-s-i-d-request/-b-s-s-i-d/index.md#1616463040%2FFunctions%2F974708819)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
