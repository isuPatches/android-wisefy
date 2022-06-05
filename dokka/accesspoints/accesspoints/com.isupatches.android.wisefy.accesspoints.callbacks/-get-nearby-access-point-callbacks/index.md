//[accesspoints](../../../index.md)/[com.isupatches.android.wisefy.accesspoints.callbacks](../index.md)/[GetNearbyAccessPointCallbacks](index.md)

# GetNearbyAccessPointCallbacks

[androidJvm]\
interface [GetNearbyAccessPointCallbacks](index.md) : [BaseWisefyCallbacks](../../../../core/core/com.isupatches.android.wisefy.core.base/-base-wisefy-callbacks/index.md)

A set of callbacks for retrieving nearby access points.

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
| [onNearbyAccessPointsRetrieved](on-nearby-access-points-retrieved.md) | [androidJvm]<br>abstract fun [onNearbyAccessPointsRetrieved](on-nearby-access-points-retrieved.md)(accessPoints: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[AccessPointData](../../com.isupatches.android.wisefy.accesspoints.entities/-access-point-data/index.md)&gt;)<br>A callback triggered when there are nearby access points. |
| [onNoNearbyAccessPoints](on-no-nearby-access-points.md) | [androidJvm]<br>abstract fun [onNoNearbyAccessPoints](on-no-nearby-access-points.md)()<br>A callback triggered when there are no nearby access points. |
| [onWisefyAsyncFailure](../-search-for-s-s-i-ds-callbacks/index.md#823639724%2FFunctions%2F2111858834) | [androidJvm]<br>abstract fun [onWisefyAsyncFailure](../-search-for-s-s-i-ds-callbacks/index.md#823639724%2FFunctions%2F2111858834)(throwable: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)) |
