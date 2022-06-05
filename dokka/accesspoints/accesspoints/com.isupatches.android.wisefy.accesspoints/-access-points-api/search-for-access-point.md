//[accesspoints](../../../index.md)/[com.isupatches.android.wisefy.accesspoints](../index.md)/[AccessPointsApi](index.md)/[searchForAccessPoint](search-for-access-point.md)

# searchForAccessPoint

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_FINE_LOCATION&quot;)

abstract fun [searchForAccessPoint](search-for-access-point.md)(request: [SearchForSingleAccessPointRequest](../../com.isupatches.android.wisefy.accesspoints.entities/-search-for-single-access-point-request/index.md)): [SearchForAccessPointResult](../../com.isupatches.android.wisefy.accesspoints.entities/-search-for-access-point-result/index.md)

A synchronous API to search for a nearby access point.

#### Return

AccessPointData - Access point data if network found, otherwise null

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.accesspoints.entities.SearchForSingleAccessPointRequest](../../com.isupatches.android.wisefy.accesspoints.entities/-search-for-single-access-point-request/index.md) |  |
| [com.isupatches.android.wisefy.accesspoints.entities.AccessPointData](../../com.isupatches.android.wisefy.accesspoints.entities/-access-point-data/index.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| request | The details of the request to search for a nearby access point. |
