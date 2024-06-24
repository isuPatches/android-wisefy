//[networkinfo](../../../index.md)/[com.isupatches.android.wisefy.networkinfo](../index.md)/[NetworkInfoApiAsync](index.md)

# NetworkInfoApiAsync

interface [NetworkInfoApiAsync](index.md)

A set of asynchronous APIs for getting information about the device's current network and connection status.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### Inheritors

| |
|---|
| [NetworkInfoDelegate](../-network-info-delegate/index.md) |

## Functions

| Name | Summary |
|---|---|
| [equals](../../com.isupatches.android.wisefy.networkinfo.entities/-network-data/index.md#585090901%2FFunctions%2F373461554) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.networkinfo.entities/-network-data/index.md#585090901%2FFunctions%2F373461554)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getCurrentNetwork](get-current-network.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_NETWORK_STATE&quot;)<br>abstract fun [getCurrentNetwork](get-current-network.md)(query: [GetCurrentNetworkQuery](../../com.isupatches.android.wisefy.networkinfo.entities/-get-current-network-query/index.md) = GetCurrentNetworkQuery(), callbacks: [GetCurrentNetworkCallbacks](../../com.isupatches.android.wisefy.networkinfo.callbacks/-get-current-network-callbacks/index.md)?)<br>An asynchronous API to get the device's current network. |
| [getNetworkConnectionStatus](get-network-connection-status.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_NETWORK_STATE&quot;)<br>abstract fun [getNetworkConnectionStatus](get-network-connection-status.md)(query: [GetNetworkConnectionStatusQuery](../../com.isupatches.android.wisefy.networkinfo.entities/-get-network-connection-status-query/index.md) = GetNetworkConnectionStatusQuery(), callbacks: [GetNetworkConnectionStatusCallbacks](../../com.isupatches.android.wisefy.networkinfo.callbacks/-get-network-connection-status-callbacks/index.md)?)<br>An asynchronous API to get the device's current network connection status. |
| [hashCode](../../com.isupatches.android.wisefy.networkinfo.entities/-network-data/index.md#1794629105%2FFunctions%2F373461554) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.networkinfo.entities/-network-data/index.md#1794629105%2FFunctions%2F373461554)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../com.isupatches.android.wisefy.networkinfo.entities/-network-data/index.md#1616463040%2FFunctions%2F373461554) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.networkinfo.entities/-network-data/index.md#1616463040%2FFunctions%2F373461554)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
