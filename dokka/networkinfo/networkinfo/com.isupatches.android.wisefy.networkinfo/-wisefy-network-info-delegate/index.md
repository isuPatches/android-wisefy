//[networkinfo](../../../index.md)/[com.isupatches.android.wisefy.networkinfo](../index.md)/[WisefyNetworkInfoDelegate](index.md)

# WisefyNetworkInfoDelegate

[androidJvm]\
class [WisefyNetworkInfoDelegate](index.md)(coroutineDispatcherProvider: [CoroutineDispatcherProvider](../../../../core/core/com.isupatches.android.wisefy.core.coroutines/-coroutine-dispatcher-provider/index.md), scope: CoroutineScope, connectivityManager: [ConnectivityManager](https://developer.android.com/reference/kotlin/android/net/ConnectivityManager.html), logger: [WisefyLogger](../../../../core/core/com.isupatches.android.wisefy.core.logging/-wisefy-logger/index.md), wifiManager: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html)) : [NetworkInfoDelegate](../-network-info-delegate/index.md)

An internal Wisefy delegate for getting information about a network, the device's current network, and the device's IP through the Android OS.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.core.coroutines.CoroutineDispatcherProvider](../../../../core/core/com.isupatches.android.wisefy.core.coroutines/-coroutine-dispatcher-provider/index.md) |  |
| [com.isupatches.android.wisefy.networkinfo.NetworkInfoDelegate](../-network-info-delegate/index.md) |  |
| [com.isupatches.android.wisefy.core.logging.WisefyLogger](../../../../core/core/com.isupatches.android.wisefy.core.logging/-wisefy-logger/index.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| coroutineDispatcherProvider | The instance of the coroutine dispatcher provider to use |
| scope | The coroutine scope to use |
| connectivityManager | The ConnectivityManager instance to use |
| logger | The logger instance to use |
| wifiManager | The WifiManager instance to use |

## Constructors

| | |
|---|---|
| [WisefyNetworkInfoDelegate](-wisefy-network-info-delegate.md) | [androidJvm]<br>fun [WisefyNetworkInfoDelegate](-wisefy-network-info-delegate.md)(coroutineDispatcherProvider: [CoroutineDispatcherProvider](../../../../core/core/com.isupatches.android.wisefy.core.coroutines/-coroutine-dispatcher-provider/index.md), scope: CoroutineScope, connectivityManager: [ConnectivityManager](https://developer.android.com/reference/kotlin/android/net/ConnectivityManager.html), logger: [WisefyLogger](../../../../core/core/com.isupatches.android.wisefy.core.logging/-wisefy-logger/index.md), wifiManager: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html)) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md) |

## Functions

| Name | Summary |
|---|---|
| [getCurrentNetwork](get-current-network.md) | [androidJvm]<br>open override fun [getCurrentNetwork](get-current-network.md)(request: [GetCurrentNetworkRequest](../../com.isupatches.android.wisefy.networkinfo.entities/-get-current-network-request/index.md)): [GetCurrentNetworkResult](../../com.isupatches.android.wisefy.networkinfo.entities/-get-current-network-result/index.md)<br>A synchronous API to get the device's current network.<br>[androidJvm]<br>open override fun [getCurrentNetwork](get-current-network.md)(request: [GetCurrentNetworkRequest](../../com.isupatches.android.wisefy.networkinfo.entities/-get-current-network-request/index.md), callbacks: [GetCurrentNetworkCallbacks](../../com.isupatches.android.wisefy.networkinfo.callbacks/-get-current-network-callbacks/index.md)?)<br>An asynchronous API to get the device's current network. |
| [getIP](get-i-p.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_NETWORK_STATE&quot;)<br>open override fun [getIP](get-i-p.md)(request: [GetIPRequest](../../com.isupatches.android.wisefy.networkinfo.entities/-get-i-p-request/index.md)): [GetIPResult](../../com.isupatches.android.wisefy.networkinfo.entities/-get-i-p-result/index.md)<br>A synchronous API to get the device's IP.<br>[androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_NETWORK_STATE&quot;)<br>open override fun [getIP](get-i-p.md)(request: [GetIPRequest](../../com.isupatches.android.wisefy.networkinfo.entities/-get-i-p-request/index.md), callbacks: [GetIPCallbacks](../../com.isupatches.android.wisefy.networkinfo.callbacks/-get-i-p-callbacks/index.md)?)<br>An asynchronous API to get the device's IP. |
| [getNetworkInfo](get-network-info.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_NETWORK_STATE&quot;)<br>open override fun [getNetworkInfo](get-network-info.md)(request: [GetNetworkInfoRequest](../../com.isupatches.android.wisefy.networkinfo.entities/-get-network-info-request/index.md)): [GetNetworkInfoResult](../../com.isupatches.android.wisefy.networkinfo.entities/-get-network-info-result/index.md)<br>A synchronous API to get the information for a network.<br>[androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_NETWORK_STATE&quot;)<br>open override fun [getNetworkInfo](get-network-info.md)(request: [GetNetworkInfoRequest](../../com.isupatches.android.wisefy.networkinfo.entities/-get-network-info-request/index.md), callbacks: [GetNetworkInfoCallbacks](../../com.isupatches.android.wisefy.networkinfo.callbacks/-get-network-info-callbacks/index.md)?)<br>An asynchronous API to get the information for a network. |
