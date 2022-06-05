//[networkconnection](../../../index.md)/[com.isupatches.android.wisefy.networkconnection](../index.md)/[NetworkConnectionApiAsync](index.md)

# NetworkConnectionApiAsync

[androidJvm]\
interface [NetworkConnectionApiAsync](index.md)

A set of asynchronous APIs for connecting to and disconnecting from a network.

#### Author

Patches Klinefelter

#### Since

03/2022

## Functions

| Name | Summary |
|---|---|
| [connectToNetwork](connect-to-network.md) | [androidJvm]<br>abstract fun [connectToNetwork](connect-to-network.md)(request: [NetworkConnectionRequest](../../com.isupatches.android.wisefy.networkconnection.entities/-network-connection-request/index.md), callbacks: [ConnectToNetworkCallbacks](../../com.isupatches.android.wisefy.networkconnection.callbacks/-connect-to-network-callbacks/index.md)?)<br>An asynchronous API to connect to a network. |
| [disconnectFromCurrentNetwork](disconnect-from-current-network.md) | [androidJvm]<br>abstract fun [disconnectFromCurrentNetwork](disconnect-from-current-network.md)(callbacks: [DisconnectFromCurrentNetworkCallbacks](../../com.isupatches.android.wisefy.networkconnection.callbacks/-disconnect-from-current-network-callbacks/index.md)?)<br>An asynchronous API to disconnect from the current network. |

## Inheritors

| Name |
|---|
| [NetworkConnectionDelegate](../-network-connection-delegate/index.md) |
