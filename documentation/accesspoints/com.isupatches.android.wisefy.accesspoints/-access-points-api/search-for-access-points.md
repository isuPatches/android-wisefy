//[accesspoints](../../../index.md)/[com.isupatches.android.wisefy.accesspoints](../index.md)/[AccessPointsApi](index.md)/[searchForAccessPoints](search-for-access-points.md)

# searchForAccessPoints

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_FINE_LOCATION&quot;)

abstract fun [searchForAccessPoints](search-for-access-points.md)(request: [SearchForMultipleAccessPointsRequest](../../com.isupatches.android.wisefy.accesspoints.entities/-search-for-multiple-access-points-request/index.md)): [SearchForAccessPointsResult](../../com.isupatches.android.wisefy.accesspoints.entities/-search-for-access-points-result/index.md)

A synchronous API to search for a list of nearby access points.

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
| [com.isupatches.android.wisefy.accesspoints.entities.SearchForMultipleAccessPointsRequest](../../com.isupatches.android.wisefy.accesspoints.entities/-search-for-multiple-access-points-request/index.md) |  |
| [com.isupatches.android.wisefy.accesspoints.entities.AccessPointData](../../com.isupatches.android.wisefy.accesspoints.entities/-access-point-data/index.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| request | The details of the request to search for a list of nearby access points. |
