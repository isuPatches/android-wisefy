//[ktx](../../index.md)/[com.isupatches.android.wisefy.ktx](index.md)/[getAccessPointsAsync](get-access-points-async.md)

# getAccessPointsAsync

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_FINE_LOCATION&quot;)

suspend fun WisefyApi.[getAccessPointsAsync](get-access-points-async.md)(query: [GetAccessPointsQuery](../../../accesspoints/accesspoints/com.isupatches.android.wisefy.accesspoints.entities/-get-access-points-query/index.md) = GetAccessPointsQuery.All()): [GetAccessPointsResult](../../../accesspoints/accesspoints/com.isupatches.android.wisefy.accesspoints.entities/-get-access-points-result/index.md)

A coroutine extension for getting all nearby access points.

#### Receiver

WisefyApi

#### Return

GetNearbyAccessPointsResult - The result of getting all nearby access points

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.accesspoints.entities.GetAccessPointsQuery](../../../accesspoints/accesspoints/com.isupatches.android.wisefy.accesspoints.entities/-get-access-points-query/index.md) |  |
| [com.isupatches.android.wisefy.accesspoints.entities.GetAccessPointsResult](../../../accesspoints/accesspoints/com.isupatches.android.wisefy.accesspoints.entities/-get-access-points-result/index.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| query | The details of the query to get all nearby access points |

## Throws

| | |
|---|---|
| [com.isupatches.android.wisefy.core.exceptions.WisefyException](../../../core/core/com.isupatches.android.wisefy.core.exceptions/-wisefy-exception/index.md) |  |
