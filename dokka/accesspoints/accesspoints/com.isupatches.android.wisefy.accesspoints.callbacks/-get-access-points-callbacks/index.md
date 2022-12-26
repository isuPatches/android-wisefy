//[accesspoints](../../../index.md)/[com.isupatches.android.wisefy.accesspoints.callbacks](../index.md)/[GetAccessPointsCallbacks](index.md)

# GetAccessPointsCallbacks

[androidJvm]\
interface [GetAccessPointsCallbacks](index.md) : [BaseWisefyCallbacks](../../../../core/core/com.isupatches.android.wisefy.core.base/-base-wisefy-callbacks/index.md)

A set of callbacks for querying access points.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.core.base.BaseWisefyCallbacks](../../../../core/core/com.isupatches.android.wisefy.core.base/-base-wisefy-callbacks/index.md) |  |

## Functions

| Name | Summary |
|---|---|
| [onNearbyAccessPointsRetrieved](on-nearby-access-points-retrieved.md) | [androidJvm]<br>abstract fun [onNearbyAccessPointsRetrieved](on-nearby-access-points-retrieved.md)(accessPoints: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[AccessPointData](../../com.isupatches.android.wisefy.accesspoints.entities/-access-point-data/index.md)&gt;)<br>A callback triggered when there are access points matching the [GetAccessPointsQuery](../../com.isupatches.android.wisefy.accesspoints.entities/-get-access-points-query/index.md). |
| [onNoNearbyAccessPoints](on-no-nearby-access-points.md) | [androidJvm]<br>abstract fun [onNoNearbyAccessPoints](on-no-nearby-access-points.md)()<br>A callback triggered when there are no access points found for the [GetAccessPointsQuery](../../com.isupatches.android.wisefy.accesspoints.entities/-get-access-points-query/index.md). |
| [onWisefyAsyncFailure](index.md#-2014443064%2FFunctions%2F2111858834) | [androidJvm]<br>abstract fun [onWisefyAsyncFailure](index.md#-2014443064%2FFunctions%2F2111858834)(exception: [WisefyException](../../../../core/core/com.isupatches.android.wisefy.core.exceptions/-wisefy-exception/index.md)) |
