//[ktx](../../index.md)/[com.isupatches.android.wisefy.ktx](index.md)/[getCurrentNetworkAsync](get-current-network-async.md)

# getCurrentNetworkAsync

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_NETWORK_STATE&quot;)

suspend fun WisefyApi.[getCurrentNetworkAsync](get-current-network-async.md)(query: [GetCurrentNetworkQuery](../../../networkinfo/networkinfo/com.isupatches.android.wisefy.networkinfo.entities/-get-current-network-query/index.md) = GetCurrentNetworkQuery()): [GetCurrentNetworkResult](../../../networkinfo/networkinfo/com.isupatches.android.wisefy.networkinfo.entities/-get-current-network-result/index.md)

A coroutine extension for getting the device's current network.

*Notes*

- 
   Locked by the networkConnectionMutex along with functions for connecting, disconnecting, changing, and getting     the device's current network connection status

#### Receiver

WisefyApi

#### Return

GetCurrentNetworkResult - The result when getting device's current network

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.networkinfo.entities.GetCurrentNetworkQuery](../../../networkinfo/networkinfo/com.isupatches.android.wisefy.networkinfo.entities/-get-current-network-query/index.md) |  |
| [com.isupatches.android.wisefy.networkinfo.entities.GetCurrentNetworkResult](../../../networkinfo/networkinfo/com.isupatches.android.wisefy.networkinfo.entities/-get-current-network-result/index.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| query | The details of the query to get the device's current network |

## Throws

| | |
|---|---|
| [com.isupatches.android.wisefy.core.exceptions.WisefyException](../../../core/core/com.isupatches.android.wisefy.core.exceptions/-wisefy-exception/index.md) |  |
