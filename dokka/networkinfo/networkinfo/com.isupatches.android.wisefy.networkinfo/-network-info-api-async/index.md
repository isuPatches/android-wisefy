//[networkinfo](../../../index.md)/[com.isupatches.android.wisefy.networkinfo](../index.md)/[NetworkInfoApiAsync](index.md)

# NetworkInfoApiAsync

[androidJvm]\
interface [NetworkInfoApiAsync](index.md)

A set of asynchronous APIs for getting information about the device's current network and connection status.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

## Functions

| Name | Summary |
|---|---|
| [getCurrentNetwork](get-current-network.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_NETWORK_STATE&quot;)<br>abstract fun [getCurrentNetwork](get-current-network.md)(query: [GetCurrentNetworkQuery](../../com.isupatches.android.wisefy.networkinfo.entities/-get-current-network-query/index.md) = GetCurrentNetworkQuery(), callbacks: [GetCurrentNetworkCallbacks](../../com.isupatches.android.wisefy.networkinfo.callbacks/-get-current-network-callbacks/index.md)?)<br>An asynchronous API to get the device's current network. |
| [getNetworkConnectionStatus](get-network-connection-status.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_NETWORK_STATE&quot;)<br>abstract fun [getNetworkConnectionStatus](get-network-connection-status.md)(query: [GetNetworkConnectionStatusQuery](../../com.isupatches.android.wisefy.networkinfo.entities/-get-network-connection-status-query/index.md) = GetNetworkConnectionStatusQuery(), callbacks: [GetNetworkConnectionStatusCallbacks](../../com.isupatches.android.wisefy.networkinfo.callbacks/-get-network-connection-status-callbacks/index.md)?)<br>An asynchronous API to get the device's current network connection status. |

## Inheritors

| Name |
|---|
| [NetworkInfoDelegate](../-network-info-delegate/index.md) |
