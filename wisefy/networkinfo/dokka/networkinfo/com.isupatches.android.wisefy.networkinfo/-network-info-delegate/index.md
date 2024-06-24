//[networkinfo](../../../index.md)/[com.isupatches.android.wisefy.networkinfo](../index.md)/[NetworkInfoDelegate](index.md)

# NetworkInfoDelegate

interface [NetworkInfoDelegate](index.md) : [NetworkInfoApi](../-network-info-api/index.md), [NetworkInfoApiAsync](../-network-info-api-async/index.md)

A delegate for synchronous and asynchronous APIs for getting information about the device's current network and connection status.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

| |
|---|
| [NetworkInfoApi](../-network-info-api/index.md) |
| [NetworkInfoApiAsync](../-network-info-api-async/index.md) |

#### Inheritors

| |
|---|
| [WisefyNetworkInfoDelegate](../-wisefy-network-info-delegate/index.md) |

## Functions

| Name | Summary |
|---|---|
| [equals](../../com.isupatches.android.wisefy.networkinfo.entities/-network-data/index.md#585090901%2FFunctions%2F373461554) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.networkinfo.entities/-network-data/index.md#585090901%2FFunctions%2F373461554)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getCurrentNetwork](../-network-info-api/get-current-network.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_NETWORK_STATE&quot;)<br>abstract fun [getCurrentNetwork](../-network-info-api/get-current-network.md)(query: [GetCurrentNetworkQuery](../../com.isupatches.android.wisefy.networkinfo.entities/-get-current-network-query/index.md) = GetCurrentNetworkQuery()): [GetCurrentNetworkResult](../../com.isupatches.android.wisefy.networkinfo.entities/-get-current-network-result/index.md)<br>A synchronous API to get the device's current network.<br>[androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_NETWORK_STATE&quot;)<br>abstract fun [getCurrentNetwork](../-network-info-api-async/get-current-network.md)(query: [GetCurrentNetworkQuery](../../com.isupatches.android.wisefy.networkinfo.entities/-get-current-network-query/index.md) = GetCurrentNetworkQuery(), callbacks: [GetCurrentNetworkCallbacks](../../com.isupatches.android.wisefy.networkinfo.callbacks/-get-current-network-callbacks/index.md)?)<br>An asynchronous API to get the device's current network. |
| [getNetworkConnectionStatus](../-network-info-api/get-network-connection-status.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_NETWORK_STATE&quot;)<br>abstract fun [getNetworkConnectionStatus](../-network-info-api/get-network-connection-status.md)(query: [GetNetworkConnectionStatusQuery](../../com.isupatches.android.wisefy.networkinfo.entities/-get-network-connection-status-query/index.md) = GetNetworkConnectionStatusQuery()): [GetNetworkConnectionStatusResult](../../com.isupatches.android.wisefy.networkinfo.entities/-get-network-connection-status-result/index.md)<br>A synchronous API to get the device's current network connection status.<br>[androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_NETWORK_STATE&quot;)<br>abstract fun [getNetworkConnectionStatus](../-network-info-api-async/get-network-connection-status.md)(query: [GetNetworkConnectionStatusQuery](../../com.isupatches.android.wisefy.networkinfo.entities/-get-network-connection-status-query/index.md) = GetNetworkConnectionStatusQuery(), callbacks: [GetNetworkConnectionStatusCallbacks](../../com.isupatches.android.wisefy.networkinfo.callbacks/-get-network-connection-status-callbacks/index.md)?)<br>An asynchronous API to get the device's current network connection status. |
| [hashCode](../../com.isupatches.android.wisefy.networkinfo.entities/-network-data/index.md#1794629105%2FFunctions%2F373461554) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.networkinfo.entities/-network-data/index.md#1794629105%2FFunctions%2F373461554)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../com.isupatches.android.wisefy.networkinfo.entities/-network-data/index.md#1616463040%2FFunctions%2F373461554) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.networkinfo.entities/-network-data/index.md#1616463040%2FFunctions%2F373461554)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
