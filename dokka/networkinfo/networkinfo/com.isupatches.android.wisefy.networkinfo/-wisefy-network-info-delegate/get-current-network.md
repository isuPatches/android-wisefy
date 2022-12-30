//[networkinfo](../../../index.md)/[com.isupatches.android.wisefy.networkinfo](../index.md)/[WisefyNetworkInfoDelegate](index.md)/[getCurrentNetwork](get-current-network.md)

# getCurrentNetwork

[androidJvm]\
open override fun [getCurrentNetwork](get-current-network.md)(query: [GetCurrentNetworkQuery](../../com.isupatches.android.wisefy.networkinfo.entities/-get-current-network-query/index.md)): [GetCurrentNetworkResult](../../com.isupatches.android.wisefy.networkinfo.entities/-get-current-network-result/index.md)

A synchronous API to get the device's current network.

#### Return

GetCurrentNetworkResult - The result of getting the device's current network

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

androidJvm

| |
|---|
| [GetCurrentNetworkQuery](../../com.isupatches.android.wisefy.networkinfo.entities/-get-current-network-query/index.md) |
| [GetCurrentNetworkResult](../../com.isupatches.android.wisefy.networkinfo.entities/-get-current-network-result/index.md) |

#### Parameters

androidJvm

| | |
|---|---|
| query | The details of the query to get the device's current network |

[androidJvm]\
open override fun [getCurrentNetwork](get-current-network.md)(query: [GetCurrentNetworkQuery](../../com.isupatches.android.wisefy.networkinfo.entities/-get-current-network-query/index.md), callbacks: [GetCurrentNetworkCallbacks](../../com.isupatches.android.wisefy.networkinfo.callbacks/-get-current-network-callbacks/index.md)?)

An asynchronous API to get the device's current network.

*Notes*

- 
   Locked by the networkConnectionMutex along with functions for connecting, disconnecting, changing and getting     the device's current network connection status

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

androidJvm

| |
|---|
| [GetCurrentNetworkQuery](../../com.isupatches.android.wisefy.networkinfo.entities/-get-current-network-query/index.md) |
| [GetCurrentNetworkCallbacks](../../com.isupatches.android.wisefy.networkinfo.callbacks/-get-current-network-callbacks/index.md) |

#### Parameters

androidJvm

| | |
|---|---|
| query | The details of the query to get the device's current network |
| callbacks | The callbacks for retrieving the device's current network |
