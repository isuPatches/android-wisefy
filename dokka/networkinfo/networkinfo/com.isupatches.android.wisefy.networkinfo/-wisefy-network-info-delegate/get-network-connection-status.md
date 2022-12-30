//[networkinfo](../../../index.md)/[com.isupatches.android.wisefy.networkinfo](../index.md)/[WisefyNetworkInfoDelegate](index.md)/[getNetworkConnectionStatus](get-network-connection-status.md)

# getNetworkConnectionStatus

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_NETWORK_STATE&quot;)

open override fun [getNetworkConnectionStatus](get-network-connection-status.md)(query: [GetNetworkConnectionStatusQuery](../../com.isupatches.android.wisefy.networkinfo.entities/-get-network-connection-status-query/index.md)): [GetNetworkConnectionStatusResult](../../com.isupatches.android.wisefy.networkinfo.entities/-get-network-connection-status-result/index.md)

A synchronous API to get the device's current network connection status.

#### Return

GetNetworkConnectionStatusResult - The result of getting the device's current network connection status

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

androidJvm

| |
|---|
| [GetNetworkConnectionStatusQuery](../../com.isupatches.android.wisefy.networkinfo.entities/-get-network-connection-status-query/index.md) |
| [GetNetworkConnectionStatusResult](../../com.isupatches.android.wisefy.networkinfo.entities/-get-network-connection-status-result/index.md) |

#### Parameters

androidJvm

| | |
|---|---|
| query | The details of the query to get the device's current network connection status |

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_NETWORK_STATE&quot;)

open override fun [getNetworkConnectionStatus](get-network-connection-status.md)(query: [GetNetworkConnectionStatusQuery](../../com.isupatches.android.wisefy.networkinfo.entities/-get-network-connection-status-query/index.md), callbacks: [GetNetworkConnectionStatusCallbacks](../../com.isupatches.android.wisefy.networkinfo.callbacks/-get-network-connection-status-callbacks/index.md)?)

An asynchronous API to get the device's current network connection status.

*Notes*

- 
   Locked by the networkConnectionMutex along with functions for connecting, disconnecting, changing, and getting     the device's current network

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

androidJvm

| |
|---|
| [GetNetworkConnectionStatusQuery](../../com.isupatches.android.wisefy.networkinfo.entities/-get-network-connection-status-query/index.md) |
| [GetNetworkConnectionStatusCallbacks](../../com.isupatches.android.wisefy.networkinfo.callbacks/-get-network-connection-status-callbacks/index.md) |

#### Parameters

androidJvm

| | |
|---|---|
| query | The details of the query to get the device's current network connection status |
| callbacks | The callbacks for retrieving the device's current network connection status |
