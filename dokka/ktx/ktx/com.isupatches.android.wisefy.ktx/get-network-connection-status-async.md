//[ktx](../../index.md)/[com.isupatches.android.wisefy.ktx](index.md)/[getNetworkConnectionStatusAsync](get-network-connection-status-async.md)

# getNetworkConnectionStatusAsync

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_NETWORK_STATE&quot;)

suspend fun WisefyApi.[getNetworkConnectionStatusAsync](get-network-connection-status-async.md)(query: [GetNetworkConnectionStatusQuery](../../../networkinfo/networkinfo/com.isupatches.android.wisefy.networkinfo.entities/-get-network-connection-status-query/index.md) = GetNetworkConnectionStatusQuery()): [GetNetworkConnectionStatusResult](../../../networkinfo/networkinfo/com.isupatches.android.wisefy.networkinfo.entities/-get-network-connection-status-result/index.md)

A coroutine extension for getting the device's current network connection status.

*Notes*

- 
   Locked by the networkConnectionMutex along with functions for connecting, disconnecting, changing, and getting the     device's current network

#### Receiver

WisefyApi

#### Return

GetNetworkConnectionStatusResult - The result when getting device's current network connection status

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.networkinfo.entities.GetNetworkConnectionStatusQuery](../../../networkinfo/networkinfo/com.isupatches.android.wisefy.networkinfo.entities/-get-network-connection-status-query/index.md) |  |
| [com.isupatches.android.wisefy.networkinfo.entities.GetNetworkConnectionStatusResult](../../../networkinfo/networkinfo/com.isupatches.android.wisefy.networkinfo.entities/-get-network-connection-status-result/index.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| query | The details of the query to get the device's current network connection status |

## Throws

| | |
|---|---|
| [com.isupatches.android.wisefy.core.exceptions.WisefyException](../../../core/core/com.isupatches.android.wisefy.core.exceptions/-wisefy-exception/index.md) |  |
