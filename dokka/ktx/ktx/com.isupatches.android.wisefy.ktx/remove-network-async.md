//[ktx](../../index.md)/[com.isupatches.android.wisefy.ktx](index.md)/[removeNetworkAsync](remove-network-async.md)

# removeNetworkAsync

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;, &quot;android.permission.CHANGE_WIFI_STATE&quot;])

suspend fun WisefyApi.[removeNetworkAsync](remove-network-async.md)(request: [RemoveNetworkRequest](../../../removenetwork/removenetwork/com.isupatches.android.wisefy.removenetwork.entities/-remove-network-request/index.md)): [RemoveNetworkResult](../../../removenetwork/removenetwork/com.isupatches.android.wisefy.removenetwork.entities/-remove-network-result/index.md)

A coroutine extension for removing a network.

*Notes*

- 
   Locked by the savedNetworkMutex along with functions for adding, querying, and checking if a network is saved

#### Receiver

WisefyApi

#### Return

RemoveNetworkResult - The result of removing a network

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

androidJvm

| |
|---|
| [RemoveNetworkRequest](../../../removenetwork/removenetwork/com.isupatches.android.wisefy.removenetwork.entities/-remove-network-request/index.md) |
| [RemoveNetworkResult](../../../removenetwork/removenetwork/com.isupatches.android.wisefy.removenetwork.entities/-remove-network-result/index.md) |

#### Parameters

androidJvm

| | |
|---|---|
| request | The details of the request to remove a network |

#### Throws

| |
|---|
| [WisefyException](../../../core/core/com.isupatches.android.wisefy.core.exceptions/-wisefy-exception/index.md) |
