//[networkconnection](../../../index.md)/[com.isupatches.android.wisefy.networkconnection](../index.md)/[NetworkConnectionDelegate](index.md)

# NetworkConnectionDelegate

[androidJvm]\
interface [NetworkConnectionDelegate](index.md) : [NetworkConnectionApi](../-network-connection-api/index.md), [NetworkConnectionApiAsync](../-network-connection-api-async/index.md)

A delegate for synchronous and asynchronous APIs to connect to and disconnect from networks.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.networkconnection.NetworkConnectionApi](../-network-connection-api/index.md) |  |
| [com.isupatches.android.wisefy.networkconnection.NetworkConnectionApiAsync](../-network-connection-api-async/index.md) |  |

## Functions

| Name | Summary |
|---|---|
| [connectToNetwork](../-network-connection-api/connect-to-network.md) | [androidJvm]<br>abstract fun [connectToNetwork](../-network-connection-api/connect-to-network.md)(request: [NetworkConnectionRequest](../../com.isupatches.android.wisefy.networkconnection.entities/-network-connection-request/index.md)): [NetworkConnectionResult](../../com.isupatches.android.wisefy.networkconnection.entities/-network-connection-result/index.md)<br>A synchronous API to connect to a network.<br>[androidJvm]<br>abstract fun [connectToNetwork](../-network-connection-api-async/connect-to-network.md)(request: [NetworkConnectionRequest](../../com.isupatches.android.wisefy.networkconnection.entities/-network-connection-request/index.md), callbacks: [ConnectToNetworkCallbacks](../../com.isupatches.android.wisefy.networkconnection.callbacks/-connect-to-network-callbacks/index.md)?)<br>An asynchronous API to connect to a network. |
| [disconnectFromCurrentNetwork](../-network-connection-api/disconnect-from-current-network.md) | [androidJvm]<br>abstract fun [disconnectFromCurrentNetwork](../-network-connection-api/disconnect-from-current-network.md)(): [NetworkConnectionResult](../../com.isupatches.android.wisefy.networkconnection.entities/-network-connection-result/index.md)<br>A synchronous API to disconnect from the current network.<br>[androidJvm]<br>abstract fun [disconnectFromCurrentNetwork](../-network-connection-api-async/disconnect-from-current-network.md)(callbacks: [DisconnectFromCurrentNetworkCallbacks](../../com.isupatches.android.wisefy.networkconnection.callbacks/-disconnect-from-current-network-callbacks/index.md)?)<br>An asynchronous API to disconnect from the current network. |

## Inheritors

| Name |
|---|
| [WisefyNetworkConnectionDelegate](../-wisefy-network-connection-delegate/index.md) |
