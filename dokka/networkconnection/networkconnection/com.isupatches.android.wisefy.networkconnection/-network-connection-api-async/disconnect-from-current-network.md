//[networkconnection](../../../index.md)/[com.isupatches.android.wisefy.networkconnection](../index.md)/[NetworkConnectionApiAsync](index.md)/[disconnectFromCurrentNetwork](disconnect-from-current-network.md)

# disconnectFromCurrentNetwork

[androidJvm]\
~~abstract~~ ~~fun~~ [~~disconnectFromCurrentNetwork~~](disconnect-from-current-network.md)~~(~~request: [DisconnectFromCurrentNetworkRequest](../../com.isupatches.android.wisefy.networkconnection.entities/-disconnect-from-current-network-request/index.md), callbacks: [DisconnectFromCurrentNetworkCallbacks](../../com.isupatches.android.wisefy.networkconnection.callbacks/-disconnect-from-current-network-callbacks/index.md)?~~)~~

An asynchronous API to disconnect from the current network.

*Notes*

- 
   Locked by the networkConnectionMutex along with functions for connecting, changing, getting the device's     current network connection status, and getting the device's current network

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.networkconnection.entities.DisconnectFromCurrentNetworkRequest](../../com.isupatches.android.wisefy.networkconnection.entities/-disconnect-from-current-network-request/index.md) |  |
| [com.isupatches.android.wisefy.networkconnection.callbacks.DisconnectFromCurrentNetworkCallbacks](../../com.isupatches.android.wisefy.networkconnection.callbacks/-disconnect-from-current-network-callbacks/index.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| request | The details of the request to disconnect from the current network |
| callbacks | The callbacks for disconnecting from a network |
