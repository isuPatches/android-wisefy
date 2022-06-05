//[networkconnection](../../../index.md)/[com.isupatches.android.wisefy.networkconnection](../index.md)/[WisefyNetworkConnectionDelegate](index.md)

# WisefyNetworkConnectionDelegate

[androidJvm]\
class [WisefyNetworkConnectionDelegate](index.md)(coroutineDispatcherProvider: [CoroutineDispatcherProvider](../../../../core/core/com.isupatches.android.wisefy.core.coroutines/-coroutine-dispatcher-provider/index.md), scope: CoroutineScope, connectivityManager: [ConnectivityManager](https://developer.android.com/reference/kotlin/android/net/ConnectivityManager.html), logger: [WisefyLogger](../../../../core/core/com.isupatches.android.wisefy.core.logging/-wisefy-logger/index.md), networkConnectionStatusDelegate: [NetworkConnectionStatusDelegate](../../../../networkconnectionstatus/networkconnectionstatus/com.isupatches.android.wisefy.networkconnectionstatus/-network-connection-status-delegate/index.md), savedNetworkDelegate: [SavedNetworkDelegate](../../../../savednetworks/savednetworks/com.isupatches.android.wisefy.savednetworks/-saved-network-delegate/index.md), sdkUtil: [SdkUtil](../../../../core/core/com.isupatches.android.wisefy.core.util/-sdk-util/index.md), wifiManager: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html)) : [NetworkConnectionDelegate](../-network-connection-delegate/index.md)

An internal Wisefy delegate for getting and searching for nearby access points through the Android OS.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.core.coroutines.CoroutineDispatcherProvider](../../../../core/core/com.isupatches.android.wisefy.core.coroutines/-coroutine-dispatcher-provider/index.md) |  |
| [com.isupatches.android.wisefy.networkconnection.NetworkConnectionDelegate](../-network-connection-delegate/index.md) |  |
| [com.isupatches.android.wisefy.networkconnectionstatus.NetworkConnectionStatusDelegate](../../../../networkconnectionstatus/networkconnectionstatus/com.isupatches.android.wisefy.networkconnectionstatus/-network-connection-status-delegate/index.md) |  |
| [com.isupatches.android.wisefy.savednetworks.SavedNetworkDelegate](../../../../savednetworks/savednetworks/com.isupatches.android.wisefy.savednetworks/-saved-network-delegate/index.md) |  |
| [com.isupatches.android.wisefy.core.util.SdkUtil](../../../../core/core/com.isupatches.android.wisefy.core.util/-sdk-util/index.md) |  |
| [com.isupatches.android.wisefy.core.logging.WisefyLogger](../../../../core/core/com.isupatches.android.wisefy.core.logging/-wisefy-logger/index.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| coroutineDispatcherProvider | The instance of the coroutine dispatcher provider to use |
| scope | The coroutine scope to use |
| connectivityManager | The ConnectivityManager instance to use |
| logger | The logger instance to use |
| networkConnectionStatusDelegate | The NetworkConnectionStatusDelegate instance to use |
| savedNetworkDelegate | The SavedNetworkDelegate instance to use |
| sdkUtil | The SdkUtil instance to use |
| wifiManager | The WifiManager instance to use |

## Constructors

| | |
|---|---|
| [WisefyNetworkConnectionDelegate](-wisefy-network-connection-delegate.md) | [androidJvm]<br>fun [WisefyNetworkConnectionDelegate](-wisefy-network-connection-delegate.md)(coroutineDispatcherProvider: [CoroutineDispatcherProvider](../../../../core/core/com.isupatches.android.wisefy.core.coroutines/-coroutine-dispatcher-provider/index.md), scope: CoroutineScope, connectivityManager: [ConnectivityManager](https://developer.android.com/reference/kotlin/android/net/ConnectivityManager.html), logger: [WisefyLogger](../../../../core/core/com.isupatches.android.wisefy.core.logging/-wisefy-logger/index.md), networkConnectionStatusDelegate: [NetworkConnectionStatusDelegate](../../../../networkconnectionstatus/networkconnectionstatus/com.isupatches.android.wisefy.networkconnectionstatus/-network-connection-status-delegate/index.md), savedNetworkDelegate: [SavedNetworkDelegate](../../../../savednetworks/savednetworks/com.isupatches.android.wisefy.savednetworks/-saved-network-delegate/index.md), sdkUtil: [SdkUtil](../../../../core/core/com.isupatches.android.wisefy.core.util/-sdk-util/index.md), wifiManager: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html)) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md) |

## Functions

| Name | Summary |
|---|---|
| [connectToNetwork](connect-to-network.md) | [androidJvm]<br>open override fun [connectToNetwork](connect-to-network.md)(request: [NetworkConnectionRequest](../../com.isupatches.android.wisefy.networkconnection.entities/-network-connection-request/index.md)): [NetworkConnectionResult](../../com.isupatches.android.wisefy.networkconnection.entities/-network-connection-result/index.md)<br>A synchronous API to connect to a network.<br>[androidJvm]<br>open override fun [connectToNetwork](connect-to-network.md)(request: [NetworkConnectionRequest](../../com.isupatches.android.wisefy.networkconnection.entities/-network-connection-request/index.md), callbacks: [ConnectToNetworkCallbacks](../../com.isupatches.android.wisefy.networkconnection.callbacks/-connect-to-network-callbacks/index.md)?)<br>An asynchronous API to connect to a network. |
| [disconnectFromCurrentNetwork](disconnect-from-current-network.md) | [androidJvm]<br>open override fun [disconnectFromCurrentNetwork](disconnect-from-current-network.md)(): [NetworkConnectionResult](../../com.isupatches.android.wisefy.networkconnection.entities/-network-connection-result/index.md)<br>A synchronous API to disconnect from the current network.<br>[androidJvm]<br>open override fun [disconnectFromCurrentNetwork](disconnect-from-current-network.md)(callbacks: [DisconnectFromCurrentNetworkCallbacks](../../com.isupatches.android.wisefy.networkconnection.callbacks/-disconnect-from-current-network-callbacks/index.md)?)<br>An asynchronous API to disconnect from the current network. |
