//[networkinfo](../../../index.md)/[com.isupatches.android.wisefy.networkinfo](../index.md)/[NetworkInfoApiAsync](index.md)

# NetworkInfoApiAsync

[androidJvm]\
interface [NetworkInfoApiAsync](index.md)

A set of asynchronous APIs for getting information about a network, the device's current network, and the device's IP.

#### Author

Patches Klinefelter

#### Since

03/2022

## Functions

| Name | Summary |
|---|---|
| [getCurrentNetwork](get-current-network.md) | [androidJvm]<br>abstract fun [getCurrentNetwork](get-current-network.md)(request: [GetCurrentNetworkRequest](../../com.isupatches.android.wisefy.networkinfo.entities/-get-current-network-request/index.md) = GetCurrentNetworkRequest(), callbacks: [GetCurrentNetworkCallbacks](../../com.isupatches.android.wisefy.networkinfo.callbacks/-get-current-network-callbacks/index.md)?)<br>An asynchronous API to get the device's current network. |
| [getIP](get-i-p.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_NETWORK_STATE&quot;)<br>abstract fun [getIP](get-i-p.md)(request: [GetIPRequest](../../com.isupatches.android.wisefy.networkinfo.entities/-get-i-p-request/index.md) = GetIPRequest(), callbacks: [GetIPCallbacks](../../com.isupatches.android.wisefy.networkinfo.callbacks/-get-i-p-callbacks/index.md)?)<br>An asynchronous API to get the device's IP. |
| [getNetworkInfo](get-network-info.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_NETWORK_STATE&quot;)<br>abstract fun [getNetworkInfo](get-network-info.md)(request: [GetNetworkInfoRequest](../../com.isupatches.android.wisefy.networkinfo.entities/-get-network-info-request/index.md) = GetNetworkInfoRequest(), callbacks: [GetNetworkInfoCallbacks](../../com.isupatches.android.wisefy.networkinfo.callbacks/-get-network-info-callbacks/index.md)?)<br>An asynchronous API to get the information for a network. |

## Inheritors

| Name |
|---|
| [NetworkInfoDelegate](../-network-info-delegate/index.md) |
