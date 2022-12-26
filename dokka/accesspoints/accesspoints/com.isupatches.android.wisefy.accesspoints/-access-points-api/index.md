//[accesspoints](../../../index.md)/[com.isupatches.android.wisefy.accesspoints](../index.md)/[AccessPointsApi](index.md)

# AccessPointsApi

[androidJvm]\
interface [AccessPointsApi](index.md)

A set of synchronous APIs for getting and searching for nearby access points.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

## Functions

| Name | Summary |
|---|---|
| [getAccessPoints](get-access-points.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_FINE_LOCATION&quot;)<br>abstract fun [getAccessPoints](get-access-points.md)(query: [GetAccessPointsQuery](../../com.isupatches.android.wisefy.accesspoints.entities/-get-access-points-query/index.md)): [GetAccessPointsResult](../../com.isupatches.android.wisefy.accesspoints.entities/-get-access-points-result/index.md)<br>A synchronous API to query for a list of nearby access points. |

## Inheritors

| Name |
|---|
| [AccessPointsDelegate](../-access-points-delegate/index.md) |
