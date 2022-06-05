//[removenetwork](../../../index.md)/[com.isupatches.android.wisefy.removenetwork](../index.md)/[RemoveNetworkApi](index.md)

# RemoveNetworkApi

[androidJvm]\
interface [RemoveNetworkApi](index.md)

A set of synchronous APIs for removing a network.

#### Author

Patches Klinefelter

#### Since

03/2022

## Functions

| Name | Summary |
|---|---|
| [removeNetwork](remove-network.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.CHANGE_WIFI_STATE&quot;])<br>abstract fun [removeNetwork](remove-network.md)(request: [RemoveNetworkRequest](../../com.isupatches.android.wisefy.removenetwork.entities/-remove-network-request/index.md)): [RemoveNetworkResult](../../com.isupatches.android.wisefy.removenetwork.entities/-remove-network-result/index.md)<br>An synchronous API to remove a network. |

## Inheritors

| Name |
|---|
| [RemoveNetworkDelegate](../-remove-network-delegate/index.md) |
