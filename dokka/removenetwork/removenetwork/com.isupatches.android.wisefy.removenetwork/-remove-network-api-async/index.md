//[removenetwork](../../../index.md)/[com.isupatches.android.wisefy.removenetwork](../index.md)/[RemoveNetworkApiAsync](index.md)

# RemoveNetworkApiAsync

[androidJvm]\
interface [RemoveNetworkApiAsync](index.md)

A set of asynchronous APIs for removing a network.

#### Author

Patches Klinefelter

#### Since

03/2022

## Functions

| Name | Summary |
|---|---|
| [removeNetwork](remove-network.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.CHANGE_WIFI_STATE&quot;])<br>abstract fun [removeNetwork](remove-network.md)(request: [RemoveNetworkRequest](../../com.isupatches.android.wisefy.removenetwork.entities/-remove-network-request/index.md), callbacks: [RemoveNetworkCallbacks](../../com.isupatches.android.wisefy.removenetwork.callbacks/-remove-network-callbacks/index.md)?)<br>An asynchronous API to remove a network. |

## Inheritors

| Name |
|---|
| [RemoveNetworkDelegate](../-remove-network-delegate/index.md) |
