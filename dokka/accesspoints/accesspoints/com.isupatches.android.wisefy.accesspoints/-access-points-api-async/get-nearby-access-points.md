//[accesspoints](../../../index.md)/[com.isupatches.android.wisefy.accesspoints](../index.md)/[AccessPointsApiAsync](index.md)/[getNearbyAccessPoints](get-nearby-access-points.md)

# getNearbyAccessPoints

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_FINE_LOCATION&quot;)

abstract fun [getNearbyAccessPoints](get-nearby-access-points.md)(request: [GetNearbyAccessPointsRequest](../../com.isupatches.android.wisefy.accesspoints.entities/-get-nearby-access-points-request/index.md), callbacks: [GetNearbyAccessPointCallbacks](../../com.isupatches.android.wisefy.accesspoints.callbacks/-get-nearby-access-point-callbacks/index.md)?)

An asynchronous API to get a list of all nearby access points.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.accesspoints.entities.GetNearbyAccessPointsRequest](../../com.isupatches.android.wisefy.accesspoints.entities/-get-nearby-access-points-request/index.md) |  |
| [com.isupatches.android.wisefy.accesspoints.callbacks.GetNearbyAccessPointCallbacks](../../com.isupatches.android.wisefy.accesspoints.callbacks/-get-nearby-access-point-callbacks/index.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| request | The details of the request to get nearby access points |
| callbacks | The callbacks for when all nearby access points are returned |