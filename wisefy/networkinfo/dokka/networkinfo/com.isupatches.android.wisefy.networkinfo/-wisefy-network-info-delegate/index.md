//[networkinfo](../../../index.md)/[com.isupatches.android.wisefy.networkinfo](../index.md)/[WisefyNetworkInfoDelegate](index.md)

# WisefyNetworkInfoDelegate

class [WisefyNetworkInfoDelegate](index.md)(connectivityManager: [ConnectivityManager](https://developer.android.com/reference/kotlin/android/net/ConnectivityManager.html), logger: WisefyLogger, sdkUtil: SdkUtil, wifiManager: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html), networkConnectionStatusProvider: suspend () -&gt; NetworkConnectionStatus?, coroutineDispatcherProvider: CoroutineDispatcherProvider, scope: CoroutineScope, networkConnectionMutex: Mutex, adapter: [NetworkInfoApi](../-network-info-api/index.md) = DefaultNetworkInfoAdapter(
        connectivityManager = connectivityManager,
        wifiManager = wifiManager,
        sdkUtil = sdkUtil,
        logger = logger,
        networkConnectionStatusProvider = networkConnectionStatusProvider,
    )) : [NetworkInfoDelegate](../-network-info-delegate/index.md)

An internal Wisefy delegate for getting information about a network, the device's current network, and the device's IP through the Android OS.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### Parameters

androidJvm

| | |
|---|---|
| connectivityManager | The ConnectivityManager instance to use |
| logger | The WisefyLogger instance to use |
| sdkUtil | The SdkUtil instance to use |
| wifiManager | The WifiManager instance to use |
| networkConnectionStatusProvider | The on-demand way to retrieve the current network connection status |
| coroutineDispatcherProvider | The instance of the coroutine dispatcher provider to use |
| scope | The coroutine scope to use |
| networkConnectionMutex | The mutex for all read/write operations involving connecting, disconnecting, and getting the device's current network and connection status |
| adapter | The adapter instance to use for getting the device's current network and connection status (determined based on the Android OS level) |

#### See also

| |
|---|
| CoroutineDispatcherProvider |
| DefaultNetworkInfoAdapter |
| [NetworkInfoApi](../-network-info-api/index.md) |
| NetworkConnectionStatus |
| [NetworkInfoDelegate](../-network-info-delegate/index.md) |
| SdkUtil |
| WisefyLogger |

## Constructors

| | |
|---|---|
| [WisefyNetworkInfoDelegate](-wisefy-network-info-delegate.md) | [androidJvm]<br>constructor(connectivityManager: [ConnectivityManager](https://developer.android.com/reference/kotlin/android/net/ConnectivityManager.html), logger: WisefyLogger, sdkUtil: SdkUtil, wifiManager: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html), networkConnectionStatusProvider: suspend () -&gt; NetworkConnectionStatus?, coroutineDispatcherProvider: CoroutineDispatcherProvider, scope: CoroutineScope, networkConnectionMutex: Mutex, adapter: [NetworkInfoApi](../-network-info-api/index.md) = DefaultNetworkInfoAdapter(         connectivityManager = connectivityManager,         wifiManager = wifiManager,         sdkUtil = sdkUtil,         logger = logger,         networkConnectionStatusProvider = networkConnectionStatusProvider,     )) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md) |

## Functions

| Name | Summary |
|---|---|
| [equals](../../com.isupatches.android.wisefy.networkinfo.entities/-network-data/index.md#585090901%2FFunctions%2F373461554) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.networkinfo.entities/-network-data/index.md#585090901%2FFunctions%2F373461554)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getCurrentNetwork](get-current-network.md) | [androidJvm]<br>open override fun [getCurrentNetwork](get-current-network.md)(query: [GetCurrentNetworkQuery](../../com.isupatches.android.wisefy.networkinfo.entities/-get-current-network-query/index.md)): [GetCurrentNetworkResult](../../com.isupatches.android.wisefy.networkinfo.entities/-get-current-network-result/index.md)<br>A synchronous API to get the device's current network.<br>[androidJvm]<br>open override fun [getCurrentNetwork](get-current-network.md)(query: [GetCurrentNetworkQuery](../../com.isupatches.android.wisefy.networkinfo.entities/-get-current-network-query/index.md), callbacks: [GetCurrentNetworkCallbacks](../../com.isupatches.android.wisefy.networkinfo.callbacks/-get-current-network-callbacks/index.md)?)<br>An asynchronous API to get the device's current network. |
| [getNetworkConnectionStatus](get-network-connection-status.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_NETWORK_STATE&quot;)<br>open override fun [getNetworkConnectionStatus](get-network-connection-status.md)(query: [GetNetworkConnectionStatusQuery](../../com.isupatches.android.wisefy.networkinfo.entities/-get-network-connection-status-query/index.md)): [GetNetworkConnectionStatusResult](../../com.isupatches.android.wisefy.networkinfo.entities/-get-network-connection-status-result/index.md)<br>A synchronous API to get the device's current network connection status.<br>[androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_NETWORK_STATE&quot;)<br>open override fun [getNetworkConnectionStatus](get-network-connection-status.md)(query: [GetNetworkConnectionStatusQuery](../../com.isupatches.android.wisefy.networkinfo.entities/-get-network-connection-status-query/index.md), callbacks: [GetNetworkConnectionStatusCallbacks](../../com.isupatches.android.wisefy.networkinfo.callbacks/-get-network-connection-status-callbacks/index.md)?)<br>An asynchronous API to get the device's current network connection status. |
| [hashCode](../../com.isupatches.android.wisefy.networkinfo.entities/-network-data/index.md#1794629105%2FFunctions%2F373461554) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.networkinfo.entities/-network-data/index.md#1794629105%2FFunctions%2F373461554)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../com.isupatches.android.wisefy.networkinfo.entities/-network-data/index.md#1616463040%2FFunctions%2F373461554) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.networkinfo.entities/-network-data/index.md#1616463040%2FFunctions%2F373461554)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
