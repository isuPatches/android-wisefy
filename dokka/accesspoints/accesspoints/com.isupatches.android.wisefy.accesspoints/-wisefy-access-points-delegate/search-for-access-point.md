//[accesspoints](../../../index.md)/[com.isupatches.android.wisefy.accesspoints](../index.md)/[WisefyAccessPointsDelegate](index.md)/[searchForAccessPoint](search-for-access-point.md)

# searchForAccessPoint

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_FINE_LOCATION&quot;)

open override fun [searchForAccessPoint](search-for-access-point.md)(request: [SearchForSingleAccessPointRequest](../../com.isupatches.android.wisefy.accesspoints.entities/-search-for-single-access-point-request/index.md)): [SearchForAccessPointResult](../../com.isupatches.android.wisefy.accesspoints.entities/-search-for-access-point-result/index.md)

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
| AccessPointData |  |

## Parameters

androidJvm

| | |
|---|---|
| request | The details of the request to search for a nearby access point. |

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_FINE_LOCATION&quot;)

open override fun [searchForAccessPoint](search-for-access-point.md)(request: [SearchForSingleAccessPointRequest](../../com.isupatches.android.wisefy.accesspoints.entities/-search-for-single-access-point-request/index.md), callbacks: [SearchForAccessPointCallbacks](../../com.isupatches.android.wisefy.accesspoints.callbacks/-search-for-access-point-callbacks/index.md)?)

An asynchronous API to search for a nearby access point.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.accesspoints.entities.SearchForSingleAccessPointRequest](../../com.isupatches.android.wisefy.accesspoints.entities/-search-for-single-access-point-request/index.md) |  |
| [com.isupatches.android.wisefy.accesspoints.callbacks.SearchForAccessPointCallbacks](../../com.isupatches.android.wisefy.accesspoints.callbacks/-search-for-access-point-callbacks/index.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| request | The details of the request to search for a nearby access point |
| callbacks | The callbacks for when the list of access points is returned |
