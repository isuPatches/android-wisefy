//[removenetwork](../../../index.md)/[com.isupatches.android.wisefy.removenetwork](../index.md)/[RemoveNetworkDelegate](index.md)

# RemoveNetworkDelegate

[androidJvm]\
interface [RemoveNetworkDelegate](index.md) : [RemoveNetworkApi](../-remove-network-api/index.md), [RemoveNetworkApiAsync](../-remove-network-api-async/index.md)

A delegate for synchronous and asynchronous APIs for removing a network.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.removenetwork.RemoveNetworkApi](../-remove-network-api/index.md) |  |
| [com.isupatches.android.wisefy.removenetwork.RemoveNetworkApiAsync](../-remove-network-api-async/index.md) |  |

## Functions

| Name | Summary |
|---|---|
| [removeNetwork](../-remove-network-api/remove-network.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;, &quot;android.permission.CHANGE_WIFI_STATE&quot;])<br>abstract fun [removeNetwork](../-remove-network-api/remove-network.md)(request: [RemoveNetworkRequest](../../com.isupatches.android.wisefy.removenetwork.entities/-remove-network-request/index.md)): [RemoveNetworkResult](../../com.isupatches.android.wisefy.removenetwork.entities/-remove-network-result/index.md)<br>An synchronous API to remove a network.<br>[androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;, &quot;android.permission.CHANGE_WIFI_STATE&quot;])<br>abstract fun [removeNetwork](../-remove-network-api-async/remove-network.md)(request: [RemoveNetworkRequest](../../com.isupatches.android.wisefy.removenetwork.entities/-remove-network-request/index.md), callbacks: [RemoveNetworkCallbacks](../../com.isupatches.android.wisefy.removenetwork.callbacks/-remove-network-callbacks/index.md)?)<br>An asynchronous API to remove a network. |

## Inheritors

| Name |
|---|
| [WisefyRemoveNetworkDelegate](../-wisefy-remove-network-delegate/index.md) |
