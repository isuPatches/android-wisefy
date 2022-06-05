//[accesspoints](../../../index.md)/[com.isupatches.android.wisefy.accesspoints](../index.md)/[AccessPointsApi](index.md)/[getNearbyAccessPoints](get-nearby-access-points.md)

# getNearbyAccessPoints

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_FINE_LOCATION&quot;)

abstract fun [getNearbyAccessPoints](get-nearby-access-points.md)(request: [GetNearbyAccessPointsRequest](../../com.isupatches.android.wisefy.accesspoints.entities/-get-nearby-access-points-request/index.md)): [GetNearbyAccessPointsResult](../../com.isupatches.android.wisefy.accesspoints.entities/-get-nearby-access-points-result/index.md)

A synchronous API to get a list of all nearby access points.

#### Return

List<AccessPointData> - List of access points or empty list

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.accesspoints.entities.GetNearbyAccessPointsRequest](../../com.isupatches.android.wisefy.accesspoints.entities/-get-nearby-access-points-request/index.md) |  |
| [com.isupatches.android.wisefy.accesspoints.entities.AccessPointData](../../com.isupatches.android.wisefy.accesspoints.entities/-access-point-data/index.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| request | The details of the request to get nearby access points |
