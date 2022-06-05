//[accesspoints](../../../index.md)/[com.isupatches.android.wisefy.accesspoints](../index.md)/[AccessPointsApi](index.md)

# AccessPointsApi

[androidJvm]\
interface [AccessPointsApi](index.md)

A set of synchronous APIs for getting and searching for nearby access points.

#### Author

Patches Klinefelter

#### Since

03/2022

## Functions

| Name | Summary |
|---|---|
| [getNearbyAccessPoints](get-nearby-access-points.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_FINE_LOCATION&quot;)<br>abstract fun [getNearbyAccessPoints](get-nearby-access-points.md)(request: [GetNearbyAccessPointsRequest](../../com.isupatches.android.wisefy.accesspoints.entities/-get-nearby-access-points-request/index.md)): [GetNearbyAccessPointsResult](../../com.isupatches.android.wisefy.accesspoints.entities/-get-nearby-access-points-result/index.md)<br>A synchronous API to get a list of all nearby access points. |
| [getRSSI](get-r-s-s-i.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_FINE_LOCATION&quot;)<br>abstract fun [getRSSI](get-r-s-s-i.md)(request: [GetRSSIRequest](../../com.isupatches.android.wisefy.accesspoints.entities/-get-r-s-s-i-request/index.md)): [GetRSSIResult](../../com.isupatches.android.wisefy.accesspoints.entities/-get-r-s-s-i-result/index.md)<br>A synchronous API to get a network's RSSI level. |
| [searchForAccessPoint](search-for-access-point.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_FINE_LOCATION&quot;)<br>abstract fun [searchForAccessPoint](search-for-access-point.md)(request: [SearchForSingleAccessPointRequest](../../com.isupatches.android.wisefy.accesspoints.entities/-search-for-single-access-point-request/index.md)): [SearchForAccessPointResult](../../com.isupatches.android.wisefy.accesspoints.entities/-search-for-access-point-result/index.md)<br>A synchronous API to search for a nearby access point. |
| [searchForAccessPoints](search-for-access-points.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_FINE_LOCATION&quot;)<br>abstract fun [searchForAccessPoints](search-for-access-points.md)(request: [SearchForMultipleAccessPointsRequest](../../com.isupatches.android.wisefy.accesspoints.entities/-search-for-multiple-access-points-request/index.md)): [SearchForAccessPointsResult](../../com.isupatches.android.wisefy.accesspoints.entities/-search-for-access-points-result/index.md)<br>A synchronous API to search for a list of nearby access points. |
| [searchForSSID](search-for-s-s-i-d.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_FINE_LOCATION&quot;)<br>abstract fun [searchForSSID](search-for-s-s-i-d.md)(request: [SearchForSingleSSIDRequest](../../com.isupatches.android.wisefy.accesspoints.entities/-search-for-single-s-s-i-d-request/index.md)): [SearchForSSIDResult](../../com.isupatches.android.wisefy.accesspoints.entities/-search-for-s-s-i-d-result/index.md)<br>A synchronous API to search for a nearby SSID. |
| [searchForSSIDs](search-for-s-s-i-ds.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_FINE_LOCATION&quot;)<br>abstract fun [searchForSSIDs](search-for-s-s-i-ds.md)(request: [SearchForMultipleSSIDsRequest](../../com.isupatches.android.wisefy.accesspoints.entities/-search-for-multiple-s-s-i-ds-request/index.md)): [SearchForSSIDsResult](../../com.isupatches.android.wisefy.accesspoints.entities/-search-for-s-s-i-ds-result/index.md)<br>A synchronous API to search for a list of nearby SSIDs. |

## Inheritors

| Name |
|---|
| [AccessPointsDelegate](../-access-points-delegate/index.md) |
