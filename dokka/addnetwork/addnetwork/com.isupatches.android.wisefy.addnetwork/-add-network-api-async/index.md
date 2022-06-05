//[addnetwork](../../../index.md)/[com.isupatches.android.wisefy.addnetwork](../index.md)/[AddNetworkApiAsync](index.md)

# AddNetworkApiAsync

[androidJvm]\
interface [AddNetworkApiAsync](index.md)

A set of asynchronous APIs for getting and searching for nearby access points.

#### Author

Patches Klinefelter

#### Since

03/2022

## Functions

| Name | Summary |
|---|---|
| [addOpenNetwork](add-open-network.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.CHANGE_WIFI_STATE&quot;])<br>abstract fun [addOpenNetwork](add-open-network.md)(request: [AddOpenNetworkRequest](../../com.isupatches.android.wisefy.addnetwork.entities/-add-open-network-request/index.md), callbacks: [AddNetworkCallbacks](../../com.isupatches.android.wisefy.addnetwork.callbacks/-add-network-callbacks/index.md)?)<br>An asynchronous API to add an open network. |
| [addWPA2Network](add-w-p-a2-network.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.CHANGE_WIFI_STATE&quot;])<br>abstract fun [addWPA2Network](add-w-p-a2-network.md)(request: [AddWPA2NetworkRequest](../../com.isupatches.android.wisefy.addnetwork.entities/-add-w-p-a2-network-request/index.md), callbacks: [AddNetworkCallbacks](../../com.isupatches.android.wisefy.addnetwork.callbacks/-add-network-callbacks/index.md)?)<br>An asynchronous API to add a WPA2 network. |
| [addWPA3Network](add-w-p-a3-network.md) | [androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 29)<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.CHANGE_WIFI_STATE&quot;])<br>abstract fun [addWPA3Network](add-w-p-a3-network.md)(request: [AddWPA3NetworkRequest](../../com.isupatches.android.wisefy.addnetwork.entities/-add-w-p-a3-network-request/index.md), callbacks: [AddNetworkCallbacks](../../com.isupatches.android.wisefy.addnetwork.callbacks/-add-network-callbacks/index.md)?)<br>An asynchronous API to add a WPA3 network. |

## Inheritors

| Name |
|---|
| [AddNetworkDelegate](../-add-network-delegate/index.md) |
