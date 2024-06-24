//[networkconnection](../../../index.md)/[com.isupatches.android.wisefy.networkconnection](../index.md)/[WisefyNetworkConnectionDelegate](index.md)

# WisefyNetworkConnectionDelegate

class [WisefyNetworkConnectionDelegate](index.md)(assertions: WisefyAssertions, connectivityManager: [ConnectivityManager](https://developer.android.com/reference/kotlin/android/net/ConnectivityManager.html), logger: WisefyLogger, sdkUtil: SdkUtil, wifiManager: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html), networkConnectionStatusProvider: suspend () -&gt; NetworkConnectionStatus?, coroutineDispatcherProvider: CoroutineDispatcherProvider, scope: CoroutineScope, networkConnectionMutex: Mutex, adapter: [NetworkConnectionApi](../-network-connection-api/index.md) = if (sdkUtil.isAtLeastQ()) {
        Android29NetworkConnectionAdapter(
            logger,
            assertions,
        )
    } else {
        DefaultNetworkConnectionAdapter(
            connectivityManager,
            wifiManager,
            logger,
            sdkUtil,
            networkConnectionStatusProvider,
            assertions,
        )
    }) : [NetworkConnectionDelegate](../-network-connection-delegate/index.md)

An internal Wisefy delegate for getting and searching for nearby access points through the Android OS.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### Parameters

androidJvm

| | |
|---|---|
| assertions | The WisefyAssertions instance to use |
| connectivityManager | The ConnectivityManager instance to use |
| logger | The WisefyLogger instance to use |
| sdkUtil | The SdkUtil instance to use |
| wifiManager | The WifiManager instance to use |
| networkConnectionStatusProvider | The on-demand way to retrieve the current network connection status |
| coroutineDispatcherProvider | The instance of the coroutine dispatcher provider to use |
| scope | The coroutine scope to use |
| networkConnectionMutex | The mutex for all read/write operations involving connecting, disconnecting, and getting the device's current network and connection status |
| adapter | The adapter instance to use for connecting, disconnecting, and changing networks (determined based on the Android OS level) |

#### See also

| |
|---|
| Android29NetworkConnectionAdapter |
| CoroutineDispatcherProvider |
| DefaultNetworkConnectionAdapter |
| [NetworkConnectionDelegate](../-network-connection-delegate/index.md) |
| NetworkConnectionStatus |
| SdkUtil |
| WisefyAssertions |
| WisefyLogger |

## Constructors

| | |
|---|---|
| [WisefyNetworkConnectionDelegate](-wisefy-network-connection-delegate.md) | [androidJvm]<br>constructor(assertions: WisefyAssertions, connectivityManager: [ConnectivityManager](https://developer.android.com/reference/kotlin/android/net/ConnectivityManager.html), logger: WisefyLogger, sdkUtil: SdkUtil, wifiManager: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html), networkConnectionStatusProvider: suspend () -&gt; NetworkConnectionStatus?, coroutineDispatcherProvider: CoroutineDispatcherProvider, scope: CoroutineScope, networkConnectionMutex: Mutex, adapter: [NetworkConnectionApi](../-network-connection-api/index.md) = if (sdkUtil.isAtLeastQ()) {         Android29NetworkConnectionAdapter(             logger,             assertions,         )     } else {         DefaultNetworkConnectionAdapter(             connectivityManager,             wifiManager,             logger,             sdkUtil,             networkConnectionStatusProvider,             assertions,         )     }) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md) |

## Functions

| Name | Summary |
|---|---|
| [changeNetwork](change-network.md) | [androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 29)<br>open override fun [changeNetwork](change-network.md)(request: [ChangeNetworkRequest](../../com.isupatches.android.wisefy.networkconnection.entities/-change-network-request/index.md)): [ChangeNetworkResult](../../com.isupatches.android.wisefy.networkconnection.entities/-change-network-result/index.md)<br>An synchronous API to change the network the device is connected to.<br>[androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 29)<br>open override fun [changeNetwork](change-network.md)(request: [ChangeNetworkRequest](../../com.isupatches.android.wisefy.networkconnection.entities/-change-network-request/index.md), callbacks: [ChangeNetworkCallbacks](../../com.isupatches.android.wisefy.networkconnection.callbacks/-change-network-callbacks/index.md)?)<br>An asynchronous API to change the network the device is connected to. |
| [connectToNetwork](connect-to-network.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;, &quot;android.permission.ACCESS_NETWORK_STATE&quot;])<br>open override fun [~~connectToNetwork~~](connect-to-network.md)(request: [ConnectToNetworkRequest](../../com.isupatches.android.wisefy.networkconnection.entities/-connect-to-network-request/index.md)): [ConnectToNetworkResult](../../com.isupatches.android.wisefy.networkconnection.entities/-connect-to-network-result/index.md)<br>A synchronous API to connect to a network.<br>[androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;, &quot;android.permission.ACCESS_NETWORK_STATE&quot;])<br>open override fun [~~connectToNetwork~~](connect-to-network.md)(request: [ConnectToNetworkRequest](../../com.isupatches.android.wisefy.networkconnection.entities/-connect-to-network-request/index.md), callbacks: [ConnectToNetworkCallbacks](../../com.isupatches.android.wisefy.networkconnection.callbacks/-connect-to-network-callbacks/index.md)?)<br>An asynchronous API to connect to a network. |
| [disconnectFromCurrentNetwork](disconnect-from-current-network.md) | [androidJvm]<br>open override fun [~~disconnectFromCurrentNetwork~~](disconnect-from-current-network.md)(request: [DisconnectFromCurrentNetworkRequest](../../com.isupatches.android.wisefy.networkconnection.entities/-disconnect-from-current-network-request/index.md)): [DisconnectFromCurrentNetworkResult](../../com.isupatches.android.wisefy.networkconnection.entities/-disconnect-from-current-network-result/index.md)<br>A synchronous API to disconnect from the current network.<br>[androidJvm]<br>open override fun [~~disconnectFromCurrentNetwork~~](disconnect-from-current-network.md)(request: [DisconnectFromCurrentNetworkRequest](../../com.isupatches.android.wisefy.networkconnection.entities/-disconnect-from-current-network-request/index.md), callbacks: [DisconnectFromCurrentNetworkCallbacks](../../com.isupatches.android.wisefy.networkconnection.callbacks/-disconnect-from-current-network-callbacks/index.md)?)<br>An asynchronous API to disconnect from the current network. |
| [equals](../../com.isupatches.android.wisefy.networkconnection.entities/-disconnect-from-current-network-result/-failure/-assertion/index.md#585090901%2FFunctions%2F-1202619134) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.networkconnection.entities/-disconnect-from-current-network-result/-failure/-assertion/index.md#585090901%2FFunctions%2F-1202619134)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../com.isupatches.android.wisefy.networkconnection.entities/-disconnect-from-current-network-result/-failure/-assertion/index.md#1794629105%2FFunctions%2F-1202619134) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.networkconnection.entities/-disconnect-from-current-network-result/-failure/-assertion/index.md#1794629105%2FFunctions%2F-1202619134)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../com.isupatches.android.wisefy.networkconnection.entities/-disconnect-from-current-network-result/-failure/-assertion/index.md#1616463040%2FFunctions%2F-1202619134) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.networkconnection.entities/-disconnect-from-current-network-result/-failure/-assertion/index.md#1616463040%2FFunctions%2F-1202619134)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
