//[accesspoints](../../../index.md)/[com.isupatches.android.wisefy.accesspoints.callbacks](../index.md)/[SearchForSSIDCallbacks](index.md)

# SearchForSSIDCallbacks

[androidJvm]\
interface [SearchForSSIDCallbacks](index.md) : [BaseWisefyCallbacks](../../../../core/core/com.isupatches.android.wisefy.core.base/-base-wisefy-callbacks/index.md)

A set of callbacks for retrieving a single SSID.

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
| [onSSIDFound](on-s-s-i-d-found.md) | [androidJvm]<br>abstract fun [onSSIDFound](on-s-s-i-d-found.md)(ssid: [SSIDData](../../com.isupatches.android.wisefy.accesspoints.entities/-s-s-i-d-data/index.md))<br>A callback triggered when there is a matching SSID. |
| [onSSIDNotFound](on-s-s-i-d-not-found.md) | [androidJvm]<br>abstract fun [onSSIDNotFound](on-s-s-i-d-not-found.md)()<br>A callback triggered when there is no matching SSID. |
| [onWisefyAsyncFailure](../-search-for-s-s-i-ds-callbacks/index.md#823639724%2FFunctions%2F2111858834) | [androidJvm]<br>abstract fun [onWisefyAsyncFailure](../-search-for-s-s-i-ds-callbacks/index.md#823639724%2FFunctions%2F2111858834)(throwable: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)) |
