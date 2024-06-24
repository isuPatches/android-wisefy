//[accesspoints](../../../index.md)/[com.isupatches.android.wisefy.accesspoints](../index.md)/[WisefyAccessPointsDelegate](index.md)

# WisefyAccessPointsDelegate

class [WisefyAccessPointsDelegate](index.md)(logger: WisefyLogger, wifiManager: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html), coroutineDispatcherProvider: CoroutineDispatcherProvider, scope: CoroutineScope, adapter: [AccessPointsApi](../-access-points-api/index.md) = DefaultAccessPointsAdapter(wifiManager, logger)) : [AccessPointsDelegate](../-access-points-delegate/index.md)

An internal Wisefy delegate for getting and searching for nearby access points.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### Parameters

androidJvm

| | |
|---|---|
| logger | The WisefyLogger instance to use |
| wifiManager | The WifiManager instance to use |
| coroutineDispatcherProvider | The CoroutineDispatcherProvider instance to use |
| scope | The coroutine scope to use |
| adapter | The adapter instance to use for access point queries (determined based on the Android OS level) |

#### See also

| |
|---|
| [AccessPointsApi](../-access-points-api/index.md) |
| [AccessPointsDelegate](../-access-points-delegate/index.md) |
| CoroutineDispatcherProvider |
| DefaultAccessPointsAdapter |
| WisefyLogger |

## Constructors

| | |
|---|---|
| [WisefyAccessPointsDelegate](-wisefy-access-points-delegate.md) | [androidJvm]<br>constructor(logger: WisefyLogger, wifiManager: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html), coroutineDispatcherProvider: CoroutineDispatcherProvider, scope: CoroutineScope, adapter: [AccessPointsApi](../-access-points-api/index.md) = DefaultAccessPointsAdapter(wifiManager, logger)) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md) |

## Functions

| Name | Summary |
|---|---|
| [equals](../../com.isupatches.android.wisefy.accesspoints.entities/-get-access-points-result/-access-points/index.md#585090901%2FFunctions%2F974708819) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.accesspoints.entities/-get-access-points-result/-access-points/index.md#585090901%2FFunctions%2F974708819)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getAccessPoints](get-access-points.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_FINE_LOCATION&quot;)<br>open override fun [getAccessPoints](get-access-points.md)(query: [GetAccessPointsQuery](../../com.isupatches.android.wisefy.accesspoints.entities/-get-access-points-query/index.md)): [GetAccessPointsResult](../../com.isupatches.android.wisefy.accesspoints.entities/-get-access-points-result/index.md)<br>A synchronous API to query for a list of nearby access points.<br>[androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_FINE_LOCATION&quot;)<br>open override fun [getAccessPoints](get-access-points.md)(query: [GetAccessPointsQuery](../../com.isupatches.android.wisefy.accesspoints.entities/-get-access-points-query/index.md), callbacks: [GetAccessPointsCallbacks](../../com.isupatches.android.wisefy.accesspoints.callbacks/-get-access-points-callbacks/index.md)?)<br>An asynchronous API to query for a list of nearby access points. |
| [hashCode](../../com.isupatches.android.wisefy.accesspoints.entities/-get-access-points-result/-access-points/index.md#1794629105%2FFunctions%2F974708819) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.accesspoints.entities/-get-access-points-result/-access-points/index.md#1794629105%2FFunctions%2F974708819)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../com.isupatches.android.wisefy.accesspoints.entities/-get-access-points-result/-access-points/index.md#1616463040%2FFunctions%2F974708819) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.accesspoints.entities/-get-access-points-result/-access-points/index.md#1616463040%2FFunctions%2F974708819)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
