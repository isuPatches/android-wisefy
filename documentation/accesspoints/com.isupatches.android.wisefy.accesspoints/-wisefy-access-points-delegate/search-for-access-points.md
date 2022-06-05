//[accesspoints](../../../index.md)/[com.isupatches.android.wisefy.accesspoints](../index.md)/[WisefyAccessPointsDelegate](index.md)/[searchForAccessPoints](search-for-access-points.md)

# searchForAccessPoints

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_FINE_LOCATION&quot;)

open override fun [searchForAccessPoints](search-for-access-points.md)(request: [SearchForMultipleAccessPointsRequest](../../com.isupatches.android.wisefy.accesspoints.entities/-search-for-multiple-access-points-request/index.md)): [SearchForAccessPointsResult](../../com.isupatches.android.wisefy.accesspoints.entities/-search-for-access-points-result/index.md)

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
| AccessPointData |  |

## Parameters

androidJvm

| | |
|---|---|
| request | The details of the request to search for a list of nearby access points. |

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_FINE_LOCATION&quot;)

open override fun [searchForAccessPoints](search-for-access-points.md)(request: [SearchForMultipleAccessPointsRequest](../../com.isupatches.android.wisefy.accesspoints.entities/-search-for-multiple-access-points-request/index.md), callbacks: [SearchForAccessPointsCallbacks](../../com.isupatches.android.wisefy.accesspoints.callbacks/-search-for-access-points-callbacks/index.md)?)

An asynchronous API to search for a list of nearby access points.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.accesspoints.entities.SearchForMultipleAccessPointsRequest](../../com.isupatches.android.wisefy.accesspoints.entities/-search-for-multiple-access-points-request/index.md) |  |
| [com.isupatches.android.wisefy.accesspoints.callbacks.SearchForAccessPointsCallbacks](../../com.isupatches.android.wisefy.accesspoints.callbacks/-search-for-access-points-callbacks/index.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| request | The details of the request to search for a list of nearby access points |
| callbacks | The callbacks for when the list of access points is returned |
