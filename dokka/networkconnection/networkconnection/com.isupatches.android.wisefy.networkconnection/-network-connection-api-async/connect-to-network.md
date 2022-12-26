//[networkconnection](../../../index.md)/[com.isupatches.android.wisefy.networkconnection](../index.md)/[NetworkConnectionApiAsync](index.md)/[connectToNetwork](connect-to-network.md)

# connectToNetwork

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;, &quot;android.permission.ACCESS_NETWORK_STATE&quot;])

~~abstract~~ ~~fun~~ [~~connectToNetwork~~](connect-to-network.md)~~(~~request: [ConnectToNetworkRequest](../../com.isupatches.android.wisefy.networkconnection.entities/-connect-to-network-request/index.md), callbacks: [ConnectToNetworkCallbacks](../../com.isupatches.android.wisefy.networkconnection.callbacks/-connect-to-network-callbacks/index.md)?~~)~~

An asynchronous API to connect to a network.

*Notes*

- 
   Locked by the networkConnectionMutex along with functions for disconnecting, changing, getting the device's     current network connection status, and getting the device's current network

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.networkconnection.callbacks.ConnectToNetworkCallbacks](../../com.isupatches.android.wisefy.networkconnection.callbacks/-connect-to-network-callbacks/index.md) |  |
| [com.isupatches.android.wisefy.networkconnection.entities.ConnectToNetworkRequest](../../com.isupatches.android.wisefy.networkconnection.entities/-connect-to-network-request/index.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| request | The details of the request to connect to a network |
| callbacks | The callbacks for connecting to a network |
