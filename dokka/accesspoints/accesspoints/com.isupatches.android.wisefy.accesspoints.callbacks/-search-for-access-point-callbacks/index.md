//[accesspoints](../../../index.md)/[com.isupatches.android.wisefy.accesspoints.callbacks](../index.md)/[SearchForAccessPointCallbacks](index.md)

# SearchForAccessPointCallbacks

[androidJvm]\
interface [SearchForAccessPointCallbacks](index.md) : [BaseWisefyCallbacks](../../../../core/core/com.isupatches.android.wisefy.core.base/-base-wisefy-callbacks/index.md)

A set of callbacks for retrieving a single nearby access point.

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
| [onAccessPointFound](on-access-point-found.md) | [androidJvm]<br>abstract fun [onAccessPointFound](on-access-point-found.md)(accessPoint: [AccessPointData](../../com.isupatches.android.wisefy.accesspoints.entities/-access-point-data/index.md))<br>A callback triggered when there is a matching access point. |
| [onNoAccessPointFound](on-no-access-point-found.md) | [androidJvm]<br>abstract fun [onNoAccessPointFound](on-no-access-point-found.md)()<br>A callback triggered when there is no matching matching access point. |
| [onWisefyAsyncFailure](../-search-for-s-s-i-ds-callbacks/index.md#823639724%2FFunctions%2F2111858834) | [androidJvm]<br>abstract fun [onWisefyAsyncFailure](../-search-for-s-s-i-ds-callbacks/index.md#823639724%2FFunctions%2F2111858834)(throwable: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)) |
