//[accesspoints](../../../index.md)/[com.isupatches.android.wisefy.accesspoints.callbacks](../index.md)/[SearchForSSIDsCallbacks](index.md)

# SearchForSSIDsCallbacks

[androidJvm]\
interface [SearchForSSIDsCallbacks](index.md) : [BaseWisefyCallbacks](../../../../core/core/com.isupatches.android.wisefy.core.base/-base-wisefy-callbacks/index.md)

A set of callbacks for retrieving multiple SSIDs.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.core.base.BaseWisefyCallbacks](../../../../core/core/com.isupatches.android.wisefy.core.base/-base-wisefy-callbacks/index.md) |  |

## Functions

| Name | Summary |
|---|---|
| [onNoSSIDsFound](on-no-s-s-i-ds-found.md) | [androidJvm]<br>abstract fun [onNoSSIDsFound](on-no-s-s-i-ds-found.md)()<br>A callback triggered when there are no matching matching SSIDs. |
| [onSSIDsFound](on-s-s-i-ds-found.md) | [androidJvm]<br>abstract fun [onSSIDsFound](on-s-s-i-ds-found.md)(ssids: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[SSIDData](../../com.isupatches.android.wisefy.accesspoints.entities/-s-s-i-d-data/index.md)&gt;)<br>A callback triggered when there are matching SSIDs. |
| [onWisefyAsyncFailure](index.md#823639724%2FFunctions%2F2111858834) | [androidJvm]<br>abstract fun [onWisefyAsyncFailure](index.md#823639724%2FFunctions%2F2111858834)(throwable: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)) |
