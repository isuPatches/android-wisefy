//[ktx](../../index.md)/[com.isupatches.android.wisefy.ktx](index.md)/[addNetworkAsync](add-network-async.md)

# addNetworkAsync

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.CHANGE_WIFI_STATE&quot;])

suspend fun WisefyApi.[addNetworkAsync](add-network-async.md)(request: [AddNetworkRequest](../../../addnetwork/addnetwork/com.isupatches.android.wisefy.addnetwork.entities/-add-network-request/index.md)): [AddNetworkResult](../../../addnetwork/addnetwork/com.isupatches.android.wisefy.addnetwork.entities/-add-network-result/index.md)

A coroutine extension for adding a network.

*Notes*

- 
   Locked by the savedNetworkMutex along with functions for removing, querying, and checking if a network is saved

#### Receiver

WisefyApi

#### Return

AddNetworkResult - The result when adding a network

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.addnetwork.entities.AddNetworkRequest](../../../addnetwork/addnetwork/com.isupatches.android.wisefy.addnetwork.entities/-add-network-request/index.md) |  |
| [com.isupatches.android.wisefy.addnetwork.entities.AddNetworkResult](../../../addnetwork/addnetwork/com.isupatches.android.wisefy.addnetwork.entities/-add-network-result/index.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| request | The details of the request to add a network |

## Throws

| | |
|---|---|
| [com.isupatches.android.wisefy.core.exceptions.WisefyException](../../../core/core/com.isupatches.android.wisefy.core.exceptions/-wisefy-exception/index.md) |  |
