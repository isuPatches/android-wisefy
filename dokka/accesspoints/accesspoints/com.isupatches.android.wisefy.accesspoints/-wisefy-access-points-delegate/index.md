//[accesspoints](../../../index.md)/[com.isupatches.android.wisefy.accesspoints](../index.md)/[WisefyAccessPointsDelegate](index.md)

# WisefyAccessPointsDelegate

[androidJvm]\
class [WisefyAccessPointsDelegate](index.md)(coroutineDispatcherProvider: [CoroutineDispatcherProvider](../../../../core/core/com.isupatches.android.wisefy.core.coroutines/-coroutine-dispatcher-provider/index.md), scope: CoroutineScope, logger: [WisefyLogger](../../../../core/core/com.isupatches.android.wisefy.core.logging/-wisefy-logger/index.md), wifiManager: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html)) : [AccessPointsDelegate](../-access-points-delegate/index.md)

An internal Wisefy delegate for getting and searching for nearby access points.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.accesspoints.AccessPointsDelegate](../-access-points-delegate/index.md) |  |
| [com.isupatches.android.wisefy.core.coroutines.CoroutineDispatcherProvider](../../../../core/core/com.isupatches.android.wisefy.core.coroutines/-coroutine-dispatcher-provider/index.md) |  |
| [com.isupatches.android.wisefy.core.logging.WisefyLogger](../../../../core/core/com.isupatches.android.wisefy.core.logging/-wisefy-logger/index.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| coroutineDispatcherProvider | The instance of the coroutine dispatcher provider to use |
| scope | The coroutine scope to use |
| logger | The logger instance to use |
| wifiManager | The WifiManager instance to use |

## Constructors

| | |
|---|---|
| [WisefyAccessPointsDelegate](-wisefy-access-points-delegate.md) | [androidJvm]<br>fun [WisefyAccessPointsDelegate](-wisefy-access-points-delegate.md)(coroutineDispatcherProvider: [CoroutineDispatcherProvider](../../../../core/core/com.isupatches.android.wisefy.core.coroutines/-coroutine-dispatcher-provider/index.md), scope: CoroutineScope, logger: [WisefyLogger](../../../../core/core/com.isupatches.android.wisefy.core.logging/-wisefy-logger/index.md), wifiManager: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html)) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md) |

## Functions

| Name | Summary |
|---|---|
| [getNearbyAccessPoints](get-nearby-access-points.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_FINE_LOCATION&quot;)<br>open override fun [getNearbyAccessPoints](get-nearby-access-points.md)(request: [GetNearbyAccessPointsRequest](../../com.isupatches.android.wisefy.accesspoints.entities/-get-nearby-access-points-request/index.md)): [GetNearbyAccessPointsResult](../../com.isupatches.android.wisefy.accesspoints.entities/-get-nearby-access-points-result/index.md)<br>A synchronous API to get a list of all nearby access points.<br>[androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_FINE_LOCATION&quot;)<br>open override fun [getNearbyAccessPoints](get-nearby-access-points.md)(request: [GetNearbyAccessPointsRequest](../../com.isupatches.android.wisefy.accesspoints.entities/-get-nearby-access-points-request/index.md), callbacks: [GetNearbyAccessPointCallbacks](../../com.isupatches.android.wisefy.accesspoints.callbacks/-get-nearby-access-point-callbacks/index.md)?)<br>An asynchronous API to get a list of all nearby access points. |
| [getRSSI](get-r-s-s-i.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_FINE_LOCATION&quot;)<br>open override fun [getRSSI](get-r-s-s-i.md)(request: [GetRSSIRequest](../../com.isupatches.android.wisefy.accesspoints.entities/-get-r-s-s-i-request/index.md)): [GetRSSIResult](../../com.isupatches.android.wisefy.accesspoints.entities/-get-r-s-s-i-result/index.md)<br>A synchronous API to get a network's RSSI level.<br>[androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_FINE_LOCATION&quot;)<br>open override fun [getRSSI](get-r-s-s-i.md)(request: [GetRSSIRequest](../../com.isupatches.android.wisefy.accesspoints.entities/-get-r-s-s-i-request/index.md), callbacks: [GetRSSICallbacks](../../com.isupatches.android.wisefy.accesspoints.callbacks/-get-r-s-s-i-callbacks/index.md)?)<br>An asynchronous API to get a network's RSSI level. |
| [searchForAccessPoint](search-for-access-point.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_FINE_LOCATION&quot;)<br>open override fun [searchForAccessPoint](search-for-access-point.md)(request: [SearchForSingleAccessPointRequest](../../com.isupatches.android.wisefy.accesspoints.entities/-search-for-single-access-point-request/index.md)): [SearchForAccessPointResult](../../com.isupatches.android.wisefy.accesspoints.entities/-search-for-access-point-result/index.md)<br>A synchronous API to search for a nearby access point.<br>[androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_FINE_LOCATION&quot;)<br>open override fun [searchForAccessPoint](search-for-access-point.md)(request: [SearchForSingleAccessPointRequest](../../com.isupatches.android.wisefy.accesspoints.entities/-search-for-single-access-point-request/index.md), callbacks: [SearchForAccessPointCallbacks](../../com.isupatches.android.wisefy.accesspoints.callbacks/-search-for-access-point-callbacks/index.md)?)<br>An asynchronous API to search for a nearby access point. |
| [searchForAccessPoints](search-for-access-points.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_FINE_LOCATION&quot;)<br>open override fun [searchForAccessPoints](search-for-access-points.md)(request: [SearchForMultipleAccessPointsRequest](../../com.isupatches.android.wisefy.accesspoints.entities/-search-for-multiple-access-points-request/index.md)): [SearchForAccessPointsResult](../../com.isupatches.android.wisefy.accesspoints.entities/-search-for-access-points-result/index.md)<br>A synchronous API to search for a list of nearby access points.<br>[androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_FINE_LOCATION&quot;)<br>open override fun [searchForAccessPoints](search-for-access-points.md)(request: [SearchForMultipleAccessPointsRequest](../../com.isupatches.android.wisefy.accesspoints.entities/-search-for-multiple-access-points-request/index.md), callbacks: [SearchForAccessPointsCallbacks](../../com.isupatches.android.wisefy.accesspoints.callbacks/-search-for-access-points-callbacks/index.md)?)<br>An asynchronous API to search for a list of nearby access points. |
| [searchForSSID](search-for-s-s-i-d.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_FINE_LOCATION&quot;)<br>open override fun [searchForSSID](search-for-s-s-i-d.md)(request: [SearchForSingleSSIDRequest](../../com.isupatches.android.wisefy.accesspoints.entities/-search-for-single-s-s-i-d-request/index.md)): [SearchForSSIDResult](../../com.isupatches.android.wisefy.accesspoints.entities/-search-for-s-s-i-d-result/index.md)<br>A synchronous API to search for a nearby SSID.<br>[androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_FINE_LOCATION&quot;)<br>open override fun [searchForSSID](search-for-s-s-i-d.md)(request: [SearchForSingleSSIDRequest](../../com.isupatches.android.wisefy.accesspoints.entities/-search-for-single-s-s-i-d-request/index.md), callbacks: [SearchForSSIDCallbacks](../../com.isupatches.android.wisefy.accesspoints.callbacks/-search-for-s-s-i-d-callbacks/index.md)?)<br>An asynchronous API to search for a nearby SSID. |
| [searchForSSIDs](search-for-s-s-i-ds.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_FINE_LOCATION&quot;)<br>open override fun [searchForSSIDs](search-for-s-s-i-ds.md)(request: [SearchForMultipleSSIDsRequest](../../com.isupatches.android.wisefy.accesspoints.entities/-search-for-multiple-s-s-i-ds-request/index.md)): [SearchForSSIDsResult](../../com.isupatches.android.wisefy.accesspoints.entities/-search-for-s-s-i-ds-result/index.md)<br>A synchronous API to search for a list of nearby SSIDs.<br>[androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_FINE_LOCATION&quot;)<br>open override fun [searchForSSIDs](search-for-s-s-i-ds.md)(request: [SearchForMultipleSSIDsRequest](../../com.isupatches.android.wisefy.accesspoints.entities/-search-for-multiple-s-s-i-ds-request/index.md), callbacks: [SearchForSSIDsCallbacks](../../com.isupatches.android.wisefy.accesspoints.callbacks/-search-for-s-s-i-ds-callbacks/index.md)?)<br>An asynchronous API to search for a list of nearby SSIDs. |
