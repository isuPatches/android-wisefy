//[networkconnection](../../../index.md)/[com.isupatches.android.wisefy.networkconnection](../index.md)/[WisefyNetworkConnectionDelegate](index.md)

# WisefyNetworkConnectionDelegate

[androidJvm]\
class [WisefyNetworkConnectionDelegate](index.md)(assertions: [WisefyAssertions](../../../../core/core/com.isupatches.android.wisefy.core.assertions/-wisefy-assertions/index.md), connectivityManager: [ConnectivityManager](https://developer.android.com/reference/kotlin/android/net/ConnectivityManager.html), logger: [WisefyLogger](../../../../core/core/com.isupatches.android.wisefy.core.logging/-wisefy-logger/index.md), sdkUtil: [SdkUtil](../../../../core/core/com.isupatches.android.wisefy.core.util/-sdk-util/index.md), wifiManager: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html), networkConnectionStatusProvider: suspend () -&gt; [NetworkConnectionStatus](../../../../core/core/com.isupatches.android.wisefy.core.entities/-network-connection-status/index.md)?, coroutineDispatcherProvider: [CoroutineDispatcherProvider](../../../../core/core/com.isupatches.android.wisefy.core.coroutines/-coroutine-dispatcher-provider/index.md), scope: CoroutineScope, networkConnectionMutex: Mutex, adapter: [NetworkConnectionApi](../-network-connection-api/index.md) = if (sdkUtil.isAtLeastQ()) {
        Android29NetworkConnectionAdapter(
            logger,
            assertions
        )
    } else {
        DefaultNetworkConnectionAdapter(
            connectivityManager,
            wifiManager,
            logger,
            sdkUtil,
            networkConnectionStatusProvider,
            assertions
        )
    }) : [NetworkConnectionDelegate](../-network-connection-delegate/index.md)

An internal Wisefy delegate for getting and searching for nearby access points through the Android OS.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

## See also

androidJvm

| | |
|---|---|
| com.isupatches.android.wisefy.networkconnection.os.adapters.Android29NetworkConnectionAdapter |  |
| [com.isupatches.android.wisefy.core.coroutines.CoroutineDispatcherProvider](../../../../core/core/com.isupatches.android.wisefy.core.coroutines/-coroutine-dispatcher-provider/index.md) |  |
| com.isupatches.android.wisefy.networkconnection.os.adapters.DefaultNetworkConnectionAdapter |  |
| [com.isupatches.android.wisefy.networkconnection.NetworkConnectionDelegate](../-network-connection-delegate/index.md) |  |
| [com.isupatches.android.wisefy.core.entities.NetworkConnectionStatus](../../../../core/core/com.isupatches.android.wisefy.core.entities/-network-connection-status/index.md) |  |
| [com.isupatches.android.wisefy.core.util.SdkUtil](../../../../core/core/com.isupatches.android.wisefy.core.util/-sdk-util/index.md) |  |
| [com.isupatches.android.wisefy.core.assertions.WisefyAssertions](../../../../core/core/com.isupatches.android.wisefy.core.assertions/-wisefy-assertions/index.md) |  |
| [com.isupatches.android.wisefy.core.logging.WisefyLogger](../../../../core/core/com.isupatches.android.wisefy.core.logging/-wisefy-logger/index.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| assertions | The [WisefyAssertions](../../../../core/core/com.isupatches.android.wisefy.core.assertions/-wisefy-assertions/index.md) instance to use |
| connectivityManager | The ConnectivityManager instance to use |
| logger | The [WisefyLogger](../../../../core/core/com.isupatches.android.wisefy.core.logging/-wisefy-logger/index.md) instance to use |
| sdkUtil | The [SdkUtil](../../../../core/core/com.isupatches.android.wisefy.core.util/-sdk-util/index.md) instance to use |
| wifiManager | The WifiManager instance to use |
| networkConnectionStatusProvider | The on-demand way to retrieve the current network connection status |

## Constructors

| | |
|---|---|
| [WisefyNetworkConnectionDelegate](-wisefy-network-connection-delegate.md) | [androidJvm]<br>fun [WisefyNetworkConnectionDelegate](-wisefy-network-connection-delegate.md)(assertions: [WisefyAssertions](../../../../core/core/com.isupatches.android.wisefy.core.assertions/-wisefy-assertions/index.md), connectivityManager: [ConnectivityManager](https://developer.android.com/reference/kotlin/android/net/ConnectivityManager.html), logger: [WisefyLogger](../../../../core/core/com.isupatches.android.wisefy.core.logging/-wisefy-logger/index.md), sdkUtil: [SdkUtil](../../../../core/core/com.isupatches.android.wisefy.core.util/-sdk-util/index.md), wifiManager: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html), networkConnectionStatusProvider: suspend () -&gt; [NetworkConnectionStatus](../../../../core/core/com.isupatches.android.wisefy.core.entities/-network-connection-status/index.md)?, coroutineDispatcherProvider: [CoroutineDispatcherProvider](../../../../core/core/com.isupatches.android.wisefy.core.coroutines/-coroutine-dispatcher-provider/index.md), scope: CoroutineScope, networkConnectionMutex: Mutex, adapter: [NetworkConnectionApi](../-network-connection-api/index.md) = if (sdkUtil.isAtLeastQ()) {         Android29NetworkConnectionAdapter(             logger,             assertions         )     } else {         DefaultNetworkConnectionAdapter(             connectivityManager,             wifiManager,             logger,             sdkUtil,             networkConnectionStatusProvider,             assertions         )     }) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md) |

## Functions

| Name | Summary |
|---|---|
| [changeNetwork](change-network.md) | [androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 29)<br>open override fun [changeNetwork](change-network.md)(request: [ChangeNetworkRequest](../../com.isupatches.android.wisefy.networkconnection.entities/-change-network-request/index.md)): [ChangeNetworkResult](../../com.isupatches.android.wisefy.networkconnection.entities/-change-network-result/index.md)<br>An synchronous API to change the network the device is connected to.<br>[androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 29)<br>open override fun [changeNetwork](change-network.md)(request: [ChangeNetworkRequest](../../com.isupatches.android.wisefy.networkconnection.entities/-change-network-request/index.md), callbacks: [ChangeNetworkCallbacks](../../com.isupatches.android.wisefy.networkconnection.callbacks/-change-network-callbacks/index.md)?)<br>An asynchronous API to change the network the device is connected to. |
| [connectToNetwork](connect-to-network.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;, &quot;android.permission.ACCESS_NETWORK_STATE&quot;])<br>~~open~~ ~~override~~ ~~fun~~ [~~connectToNetwork~~](connect-to-network.md)~~(~~request: [ConnectToNetworkRequest](../../com.isupatches.android.wisefy.networkconnection.entities/-connect-to-network-request/index.md)~~)~~~~:~~ [ConnectToNetworkResult](../../com.isupatches.android.wisefy.networkconnection.entities/-connect-to-network-result/index.md)<br>A synchronous API to connect to a network.<br>[androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;, &quot;android.permission.ACCESS_NETWORK_STATE&quot;])<br>~~open~~ ~~override~~ ~~fun~~ [~~connectToNetwork~~](connect-to-network.md)~~(~~request: [ConnectToNetworkRequest](../../com.isupatches.android.wisefy.networkconnection.entities/-connect-to-network-request/index.md), callbacks: [ConnectToNetworkCallbacks](../../com.isupatches.android.wisefy.networkconnection.callbacks/-connect-to-network-callbacks/index.md)?~~)~~<br>An asynchronous API to connect to a network. |
| [disconnectFromCurrentNetwork](disconnect-from-current-network.md) | [androidJvm]<br>~~open~~ ~~override~~ ~~fun~~ [~~disconnectFromCurrentNetwork~~](disconnect-from-current-network.md)~~(~~request: [DisconnectFromCurrentNetworkRequest](../../com.isupatches.android.wisefy.networkconnection.entities/-disconnect-from-current-network-request/index.md)~~)~~~~:~~ [DisconnectFromCurrentNetworkResult](../../com.isupatches.android.wisefy.networkconnection.entities/-disconnect-from-current-network-result/index.md)<br>A synchronous API to disconnect from the current network.<br>[androidJvm]<br>~~open~~ ~~override~~ ~~fun~~ [~~disconnectFromCurrentNetwork~~](disconnect-from-current-network.md)~~(~~request: [DisconnectFromCurrentNetworkRequest](../../com.isupatches.android.wisefy.networkconnection.entities/-disconnect-from-current-network-request/index.md), callbacks: [DisconnectFromCurrentNetworkCallbacks](../../com.isupatches.android.wisefy.networkconnection.callbacks/-disconnect-from-current-network-callbacks/index.md)?~~)~~<br>An asynchronous API to disconnect from the current network. |
