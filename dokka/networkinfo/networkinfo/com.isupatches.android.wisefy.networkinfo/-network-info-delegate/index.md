//[networkinfo](../../../index.md)/[com.isupatches.android.wisefy.networkinfo](../index.md)/[NetworkInfoDelegate](index.md)

# NetworkInfoDelegate

[androidJvm]\
interface [NetworkInfoDelegate](index.md) : [NetworkInfoApi](../-network-info-api/index.md), [NetworkInfoApiAsync](../-network-info-api-async/index.md)

A delegate for synchronous and asynchronous APIs for getting information about a network, the device's current network, and the device's IP.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.networkinfo.NetworkInfoApi](../-network-info-api/index.md) |  |
| [com.isupatches.android.wisefy.networkinfo.NetworkInfoApiAsync](../-network-info-api-async/index.md) |  |

## Functions

| Name | Summary |
|---|---|
| [getCurrentNetwork](../-network-info-api/get-current-network.md) | [androidJvm]<br>abstract fun [getCurrentNetwork](../-network-info-api/get-current-network.md)(request: [GetCurrentNetworkRequest](../../com.isupatches.android.wisefy.networkinfo.entities/-get-current-network-request/index.md) = GetCurrentNetworkRequest()): [GetCurrentNetworkResult](../../com.isupatches.android.wisefy.networkinfo.entities/-get-current-network-result/index.md)<br>A synchronous API to get the device's current network.<br>[androidJvm]<br>abstract fun [getCurrentNetwork](../-network-info-api-async/get-current-network.md)(request: [GetCurrentNetworkRequest](../../com.isupatches.android.wisefy.networkinfo.entities/-get-current-network-request/index.md) = GetCurrentNetworkRequest(), callbacks: [GetCurrentNetworkCallbacks](../../com.isupatches.android.wisefy.networkinfo.callbacks/-get-current-network-callbacks/index.md)?)<br>An asynchronous API to get the device's current network. |
| [getIP](../-network-info-api/get-i-p.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_NETWORK_STATE&quot;)<br>abstract fun [getIP](../-network-info-api/get-i-p.md)(request: [GetIPRequest](../../com.isupatches.android.wisefy.networkinfo.entities/-get-i-p-request/index.md) = GetIPRequest()): [GetIPResult](../../com.isupatches.android.wisefy.networkinfo.entities/-get-i-p-result/index.md)<br>A synchronous API to get the device's IP.<br>[androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_NETWORK_STATE&quot;)<br>abstract fun [getIP](../-network-info-api-async/get-i-p.md)(request: [GetIPRequest](../../com.isupatches.android.wisefy.networkinfo.entities/-get-i-p-request/index.md) = GetIPRequest(), callbacks: [GetIPCallbacks](../../com.isupatches.android.wisefy.networkinfo.callbacks/-get-i-p-callbacks/index.md)?)<br>An asynchronous API to get the device's IP. |
| [getNetworkInfo](../-network-info-api/get-network-info.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_NETWORK_STATE&quot;)<br>abstract fun [getNetworkInfo](../-network-info-api/get-network-info.md)(request: [GetNetworkInfoRequest](../../com.isupatches.android.wisefy.networkinfo.entities/-get-network-info-request/index.md) = GetNetworkInfoRequest()): [GetNetworkInfoResult](../../com.isupatches.android.wisefy.networkinfo.entities/-get-network-info-result/index.md)<br>A synchronous API to get the information for a network.<br>[androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_NETWORK_STATE&quot;)<br>abstract fun [getNetworkInfo](../-network-info-api-async/get-network-info.md)(request: [GetNetworkInfoRequest](../../com.isupatches.android.wisefy.networkinfo.entities/-get-network-info-request/index.md) = GetNetworkInfoRequest(), callbacks: [GetNetworkInfoCallbacks](../../com.isupatches.android.wisefy.networkinfo.callbacks/-get-network-info-callbacks/index.md)?)<br>An asynchronous API to get the information for a network. |

## Inheritors

| Name |
|---|
| [WisefyNetworkInfoDelegate](../-wisefy-network-info-delegate/index.md) |
