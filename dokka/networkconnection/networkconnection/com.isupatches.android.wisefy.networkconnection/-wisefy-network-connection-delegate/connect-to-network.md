//[networkconnection](../../../index.md)/[com.isupatches.android.wisefy.networkconnection](../index.md)/[WisefyNetworkConnectionDelegate](index.md)/[connectToNetwork](connect-to-network.md)

# connectToNetwork

[androidJvm]\
open override fun [connectToNetwork](connect-to-network.md)(request: [NetworkConnectionRequest](../../com.isupatches.android.wisefy.networkconnection.entities/-network-connection-request/index.md)): [NetworkConnectionResult](../../com.isupatches.android.wisefy.networkconnection.entities/-network-connection-result/index.md)

A synchronous API to connect to a network.

#### Return

NetworkConnectionResult - The result of connecting to a network

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.networkconnection.entities.NetworkConnectionRequest](../../com.isupatches.android.wisefy.networkconnection.entities/-network-connection-request/index.md) |  |
| [com.isupatches.android.wisefy.networkconnection.entities.NetworkConnectionResult](../../com.isupatches.android.wisefy.networkconnection.entities/-network-connection-result/index.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| request | The details of the request to connect to a network |

[androidJvm]\
open override fun [connectToNetwork](connect-to-network.md)(request: [NetworkConnectionRequest](../../com.isupatches.android.wisefy.networkconnection.entities/-network-connection-request/index.md), callbacks: [ConnectToNetworkCallbacks](../../com.isupatches.android.wisefy.networkconnection.callbacks/-connect-to-network-callbacks/index.md)?)

An asynchronous API to connect to a network.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.networkconnection.callbacks.ConnectToNetworkCallbacks](../../com.isupatches.android.wisefy.networkconnection.callbacks/-connect-to-network-callbacks/index.md) |  |
| [com.isupatches.android.wisefy.networkconnection.entities.NetworkConnectionRequest](../../com.isupatches.android.wisefy.networkconnection.entities/-network-connection-request/index.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| request | The details of the request to connect to a network |
| callbacks | The callbacks for connecting to a network |
