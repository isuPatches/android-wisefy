//[accesspoints](../../../index.md)/[com.isupatches.android.wisefy.accesspoints](../index.md)/[AccessPointsApiAsync](index.md)/[getAccessPoints](get-access-points.md)

# getAccessPoints

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_FINE_LOCATION&quot;)

abstract fun [getAccessPoints](get-access-points.md)(query: [GetAccessPointsQuery](../../com.isupatches.android.wisefy.accesspoints.entities/-get-access-points-query/index.md), callbacks: [GetAccessPointsCallbacks](../../com.isupatches.android.wisefy.accesspoints.callbacks/-get-access-points-callbacks/index.md)?)

An asynchronous API to query for a list of nearby access points.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.accesspoints.entities.GetAccessPointsQuery](../../com.isupatches.android.wisefy.accesspoints.entities/-get-access-points-query/index.md) |  |
| [com.isupatches.android.wisefy.accesspoints.callbacks.GetAccessPointsCallbacks](../../com.isupatches.android.wisefy.accesspoints.callbacks/-get-access-points-callbacks/index.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| query | The details of the query to get nearby access points |
| callbacks | The optional callbacks for when nearby access points are returned |
