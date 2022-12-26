//[ktx](../../index.md)/[com.isupatches.android.wisefy.ktx](index.md)/[changeNetworkAsync](change-network-async.md)

# changeNetworkAsync

[androidJvm]\

@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 29)

suspend fun WisefyApi.[changeNetworkAsync](change-network-async.md)(request: [ChangeNetworkRequest](../../../networkconnection/networkconnection/com.isupatches.android.wisefy.networkconnection.entities/-change-network-request/index.md)): [ChangeNetworkResult](../../../networkconnection/networkconnection/com.isupatches.android.wisefy.networkconnection.entities/-change-network-result/index.md)

A coroutine extension for changing the current network.

*Notes*

- 
   Locked by the networkConnectionMutex along with functions for connecting, disconnecting, getting the device's     current network connection status, and getting the device's current network

#### Receiver

WisefyApi

#### Return

ChangeNetworkResult - The result when changing the current network

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.networkconnection.entities.ChangeNetworkRequest](../../../networkconnection/networkconnection/com.isupatches.android.wisefy.networkconnection.entities/-change-network-request/index.md) |  |
| [com.isupatches.android.wisefy.networkconnection.entities.ChangeNetworkResult](../../../networkconnection/networkconnection/com.isupatches.android.wisefy.networkconnection.entities/-change-network-result/index.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| request | The details of the request to change the current network |

## Throws

| | |
|---|---|
| [com.isupatches.android.wisefy.core.exceptions.WisefyException](../../../core/core/com.isupatches.android.wisefy.core.exceptions/-wisefy-exception/index.md) |  |
