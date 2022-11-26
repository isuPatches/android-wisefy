//[networkinfo](../../../index.md)/[com.isupatches.android.wisefy.networkinfo](../index.md)/[WisefyNetworkInfoDelegate](index.md)/[getNetworkInfo](get-network-info.md)

# getNetworkInfo

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_NETWORK_STATE&quot;)

open override fun [getNetworkInfo](get-network-info.md)(request: [GetNetworkInfoRequest](../../com.isupatches.android.wisefy.networkinfo.entities/-get-network-info-request/index.md)): [GetNetworkInfoResult](../../com.isupatches.android.wisefy.networkinfo.entities/-get-network-info-result/index.md)

A synchronous API to get the information for a network.

#### Return

GetNetworkInfoResult - The result of getting the information for a network

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.networkinfo.entities.GetNetworkInfoRequest](../../com.isupatches.android.wisefy.networkinfo.entities/-get-network-info-request/index.md) |  |
| [com.isupatches.android.wisefy.networkinfo.entities.GetNetworkInfoResult](../../com.isupatches.android.wisefy.networkinfo.entities/-get-network-info-result/index.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| request | The details of the request to get the information for a network |

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_NETWORK_STATE&quot;)

open override fun [getNetworkInfo](get-network-info.md)(request: [GetNetworkInfoRequest](../../com.isupatches.android.wisefy.networkinfo.entities/-get-network-info-request/index.md), callbacks: [GetNetworkInfoCallbacks](../../com.isupatches.android.wisefy.networkinfo.callbacks/-get-network-info-callbacks/index.md)?)

An asynchronous API to get the information for a network.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.networkinfo.entities.GetNetworkInfoRequest](../../com.isupatches.android.wisefy.networkinfo.entities/-get-network-info-request/index.md) |  |
| [com.isupatches.android.wisefy.networkinfo.callbacks.GetNetworkInfoCallbacks](../../com.isupatches.android.wisefy.networkinfo.callbacks/-get-network-info-callbacks/index.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| request | The details of the request to get the information for a network |
| callbacks | The callbacks for retrieving the information for a network |