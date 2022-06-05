//[networkconnection](../../../index.md)/[com.isupatches.android.wisefy.networkconnection](../index.md)/[NetworkConnectionApi](index.md)

# NetworkConnectionApi

[androidJvm]\
interface [NetworkConnectionApi](index.md)

A set of synchronous APIs for connecting to and disconnecting from a network.

#### Author

Patches Klinefelter

#### Since

03/2022

## Functions

| Name | Summary |
|---|---|
| [connectToNetwork](connect-to-network.md) | [androidJvm]<br>abstract fun [connectToNetwork](connect-to-network.md)(request: [NetworkConnectionRequest](../../com.isupatches.android.wisefy.networkconnection.entities/-network-connection-request/index.md)): [NetworkConnectionResult](../../com.isupatches.android.wisefy.networkconnection.entities/-network-connection-result/index.md)<br>A synchronous API to connect to a network. |
| [disconnectFromCurrentNetwork](disconnect-from-current-network.md) | [androidJvm]<br>abstract fun [disconnectFromCurrentNetwork](disconnect-from-current-network.md)(): [NetworkConnectionResult](../../com.isupatches.android.wisefy.networkconnection.entities/-network-connection-result/index.md)<br>A synchronous API to disconnect from the current network. |

## Inheritors

| Name |
|---|
| [NetworkConnectionDelegate](../-network-connection-delegate/index.md) |
