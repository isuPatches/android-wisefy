//[networkinfo](../../../index.md)/[com.isupatches.android.wisefy.networkinfo](../index.md)/[NetworkInfoApiAsync](index.md)/[getCurrentNetwork](get-current-network.md)

# getCurrentNetwork

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_NETWORK_STATE&quot;)

abstract fun [getCurrentNetwork](get-current-network.md)(query: [GetCurrentNetworkQuery](../../com.isupatches.android.wisefy.networkinfo.entities/-get-current-network-query/index.md) = GetCurrentNetworkQuery(), callbacks: [GetCurrentNetworkCallbacks](../../com.isupatches.android.wisefy.networkinfo.callbacks/-get-current-network-callbacks/index.md)?)

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
