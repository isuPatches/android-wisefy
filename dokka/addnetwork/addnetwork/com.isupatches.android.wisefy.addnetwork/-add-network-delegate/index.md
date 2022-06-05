//[addnetwork](../../../index.md)/[com.isupatches.android.wisefy.addnetwork](../index.md)/[AddNetworkDelegate](index.md)

# AddNetworkDelegate

[androidJvm]\
interface [AddNetworkDelegate](index.md) : [AddNetworkApi](../-add-network-api/index.md), [AddNetworkApiAsync](../-add-network-api-async/index.md)

A delegate for synchronous and asynchronous APIs to add networks.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.addnetwork.AddNetworkApi](../-add-network-api/index.md) |  |
| [com.isupatches.android.wisefy.addnetwork.AddNetworkApiAsync](../-add-network-api-async/index.md) |  |

## Functions

| Name | Summary |
|---|---|
| [addOpenNetwork](../-add-network-api/add-open-network.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.CHANGE_WIFI_STATE&quot;])<br>abstract fun [addOpenNetwork](../-add-network-api/add-open-network.md)(request: [AddOpenNetworkRequest](../../com.isupatches.android.wisefy.addnetwork.entities/-add-open-network-request/index.md)): [AddNetworkResult](../../com.isupatches.android.wisefy.addnetwork.entities/-add-network-result/index.md)<br>A synchronous API to add an open network.<br>[androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.CHANGE_WIFI_STATE&quot;])<br>abstract fun [addOpenNetwork](../-add-network-api-async/add-open-network.md)(request: [AddOpenNetworkRequest](../../com.isupatches.android.wisefy.addnetwork.entities/-add-open-network-request/index.md), callbacks: [AddNetworkCallbacks](../../com.isupatches.android.wisefy.addnetwork.callbacks/-add-network-callbacks/index.md)?)<br>An asynchronous API to add an open network. |
| [addWPA2Network](../-add-network-api/add-w-p-a2-network.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.CHANGE_WIFI_STATE&quot;])<br>abstract fun [addWPA2Network](../-add-network-api/add-w-p-a2-network.md)(request: [AddWPA2NetworkRequest](../../com.isupatches.android.wisefy.addnetwork.entities/-add-w-p-a2-network-request/index.md)): [AddNetworkResult](../../com.isupatches.android.wisefy.addnetwork.entities/-add-network-result/index.md)<br>A synchronous API to add a WPA2 network.<br>[androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.CHANGE_WIFI_STATE&quot;])<br>abstract fun [addWPA2Network](../-add-network-api-async/add-w-p-a2-network.md)(request: [AddWPA2NetworkRequest](../../com.isupatches.android.wisefy.addnetwork.entities/-add-w-p-a2-network-request/index.md), callbacks: [AddNetworkCallbacks](../../com.isupatches.android.wisefy.addnetwork.callbacks/-add-network-callbacks/index.md)?)<br>An asynchronous API to add a WPA2 network. |
| [addWPA3Network](../-add-network-api/add-w-p-a3-network.md) | [androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 29)<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.CHANGE_WIFI_STATE&quot;])<br>abstract fun [addWPA3Network](../-add-network-api/add-w-p-a3-network.md)(request: [AddWPA3NetworkRequest](../../com.isupatches.android.wisefy.addnetwork.entities/-add-w-p-a3-network-request/index.md)): [AddNetworkResult](../../com.isupatches.android.wisefy.addnetwork.entities/-add-network-result/index.md)<br>A synchronous API to add a WPA3 network.<br>[androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 29)<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.CHANGE_WIFI_STATE&quot;])<br>abstract fun [addWPA3Network](../-add-network-api-async/add-w-p-a3-network.md)(request: [AddWPA3NetworkRequest](../../com.isupatches.android.wisefy.addnetwork.entities/-add-w-p-a3-network-request/index.md), callbacks: [AddNetworkCallbacks](../../com.isupatches.android.wisefy.addnetwork.callbacks/-add-network-callbacks/index.md)?)<br>An asynchronous API to add a WPA3 network. |

## Inheritors

| Name |
|---|
| [WisefyAddNetworkDelegate](../-wisefy-add-network-delegate/index.md) |
