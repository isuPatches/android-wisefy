//[addnetwork](../../../index.md)/[com.isupatches.android.wisefy.addnetwork](../index.md)/[WisefyAddNetworkDelegate](index.md)/[addWPA2Network](add-w-p-a2-network.md)

# addWPA2Network

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.CHANGE_WIFI_STATE&quot;])

open override fun [addWPA2Network](add-w-p-a2-network.md)(request: [AddWPA2NetworkRequest](../../com.isupatches.android.wisefy.addnetwork.entities/-add-w-p-a2-network-request/index.md)): [AddNetworkResult](../../com.isupatches.android.wisefy.addnetwork.entities/-add-network-result/index.md)

A synchronous API to add a WPA2 network.

#### Return

AddNetworkResult - The result when adding the WPA2 network

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.addnetwork.entities.AddWPA2NetworkRequest](../../com.isupatches.android.wisefy.addnetwork.entities/-add-w-p-a2-network-request/index.md) |  |
| [com.isupatches.android.wisefy.addnetwork.entities.AddNetworkResult](../../com.isupatches.android.wisefy.addnetwork.entities/-add-network-result/index.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| request | The details of the request to add a WPA2 network |

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.CHANGE_WIFI_STATE&quot;])

open override fun [addWPA2Network](add-w-p-a2-network.md)(request: [AddWPA2NetworkRequest](../../com.isupatches.android.wisefy.addnetwork.entities/-add-w-p-a2-network-request/index.md), callbacks: [AddNetworkCallbacks](../../com.isupatches.android.wisefy.addnetwork.callbacks/-add-network-callbacks/index.md)?)

An asynchronous API to add a WPA2 network.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.addnetwork.entities.AddWPA2NetworkRequest](../../com.isupatches.android.wisefy.addnetwork.entities/-add-w-p-a2-network-request/index.md) |  |
| [com.isupatches.android.wisefy.addnetwork.callbacks.AddNetworkCallbacks](../../com.isupatches.android.wisefy.addnetwork.callbacks/-add-network-callbacks/index.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| request | The details of the request to add a WPA2 network |
| callbacks | The callbacks for when the result for adding a network is returned |
