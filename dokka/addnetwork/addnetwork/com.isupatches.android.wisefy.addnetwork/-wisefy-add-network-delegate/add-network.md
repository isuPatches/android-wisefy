//[addnetwork](../../../index.md)/[com.isupatches.android.wisefy.addnetwork](../index.md)/[WisefyAddNetworkDelegate](index.md)/[addNetwork](add-network.md)

# addNetwork

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.CHANGE_WIFI_STATE&quot;])

open override fun [addNetwork](add-network.md)(request: [AddNetworkRequest](../../com.isupatches.android.wisefy.addnetwork.entities/-add-network-request/index.md)): [AddNetworkResult](../../com.isupatches.android.wisefy.addnetwork.entities/-add-network-result/index.md)

A synchronous API for adding a network.

#### Return

AddNetworkResult - The result while adding a network

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.addnetwork.entities.AddNetworkRequest](../../com.isupatches.android.wisefy.addnetwork.entities/-add-network-request/index.md) |  |
| [com.isupatches.android.wisefy.addnetwork.entities.AddNetworkResult](../../com.isupatches.android.wisefy.addnetwork.entities/-add-network-result/index.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| request | The details of the request to add a network |

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.CHANGE_WIFI_STATE&quot;])

open override fun [addNetwork](add-network.md)(request: [AddNetworkRequest](../../com.isupatches.android.wisefy.addnetwork.entities/-add-network-request/index.md), callbacks: [AddNetworkCallbacks](../../com.isupatches.android.wisefy.addnetwork.callbacks/-add-network-callbacks/index.md)?)

An asynchronous API for adding a network.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.addnetwork.entities.AddNetworkRequest](../../com.isupatches.android.wisefy.addnetwork.entities/-add-network-request/index.md) |  |
| [com.isupatches.android.wisefy.addnetwork.callbacks.AddNetworkCallbacks](../../com.isupatches.android.wisefy.addnetwork.callbacks/-add-network-callbacks/index.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| request | The details of the request to add a network |
| callbacks | The optional callbacks for when the result for adding a network is returned |
