//[addnetwork](../../../index.md)/[com.isupatches.android.wisefy.addnetwork](../index.md)/[AddNetworkApi](index.md)

# AddNetworkApi

[androidJvm]\
interface [AddNetworkApi](index.md)

A set of synchronous APIs related to adding networks.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

## Functions

| Name | Summary |
|---|---|
| [addNetwork](add-network.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.CHANGE_WIFI_STATE&quot;])<br>abstract fun [addNetwork](add-network.md)(request: [AddNetworkRequest](../../com.isupatches.android.wisefy.addnetwork.entities/-add-network-request/index.md)): [AddNetworkResult](../../com.isupatches.android.wisefy.addnetwork.entities/-add-network-result/index.md)<br>A synchronous API for adding a network. |

## Inheritors

| Name |
|---|
| [AddNetworkDelegate](../-add-network-delegate/index.md) |
