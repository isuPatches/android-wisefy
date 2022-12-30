//[accesspoints](../../../index.md)/[com.isupatches.android.wisefy.accesspoints](../index.md)/[AccessPointsDelegate](index.md)

# AccessPointsDelegate

[androidJvm]\
interface [AccessPointsDelegate](index.md) : [AccessPointsApi](../-access-points-api/index.md), [AccessPointsApiAsync](../-access-points-api-async/index.md)

A delegate for synchronous and asynchronous access point APIs.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

androidJvm

| |
|---|
| [AccessPointsApi](../-access-points-api/index.md) |
| [AccessPointsApiAsync](../-access-points-api-async/index.md) |

## Functions

| Name | Summary |
|---|---|
| [getAccessPoints](../-access-points-api/get-access-points.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_FINE_LOCATION&quot;)<br>abstract fun [getAccessPoints](../-access-points-api/get-access-points.md)(query: [GetAccessPointsQuery](../../com.isupatches.android.wisefy.accesspoints.entities/-get-access-points-query/index.md)): [GetAccessPointsResult](../../com.isupatches.android.wisefy.accesspoints.entities/-get-access-points-result/index.md)<br>A synchronous API to query for a list of nearby access points.<br>[androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_FINE_LOCATION&quot;)<br>abstract fun [getAccessPoints](../-access-points-api-async/get-access-points.md)(query: [GetAccessPointsQuery](../../com.isupatches.android.wisefy.accesspoints.entities/-get-access-points-query/index.md), callbacks: [GetAccessPointsCallbacks](../../com.isupatches.android.wisefy.accesspoints.callbacks/-get-access-points-callbacks/index.md)?)<br>An asynchronous API to query for a list of nearby access points. |

## Inheritors

| Name |
|---|
| [WisefyAccessPointsDelegate](../-wisefy-access-points-delegate/index.md) |
