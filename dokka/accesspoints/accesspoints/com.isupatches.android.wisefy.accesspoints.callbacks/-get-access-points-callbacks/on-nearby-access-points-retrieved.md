//[accesspoints](../../../index.md)/[com.isupatches.android.wisefy.accesspoints.callbacks](../index.md)/[GetAccessPointsCallbacks](index.md)/[onNearbyAccessPointsRetrieved](on-nearby-access-points-retrieved.md)

# onNearbyAccessPointsRetrieved

[androidJvm]\
abstract fun [onNearbyAccessPointsRetrieved](on-nearby-access-points-retrieved.md)(accessPoints: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[AccessPointData](../../com.isupatches.android.wisefy.accesspoints.entities/-access-point-data/index.md)&gt;)

A callback triggered when there are access points matching the [GetAccessPointsQuery](../../com.isupatches.android.wisefy.accesspoints.entities/-get-access-points-query/index.md).

*NOTES*

- 
   Will not return an empty list due to [onNoNearbyAccessPoints](on-no-nearby-access-points.md)

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.accesspoints.entities.AccessPointData](../../com.isupatches.android.wisefy.accesspoints.entities/-access-point-data/index.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| accessPoints | The non-empty list of access points found by the query |
