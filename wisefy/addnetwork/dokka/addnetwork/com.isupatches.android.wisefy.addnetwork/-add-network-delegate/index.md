//[addnetwork](../../../index.md)/[com.isupatches.android.wisefy.addnetwork](../index.md)/[AddNetworkDelegate](index.md)

# AddNetworkDelegate

interface [AddNetworkDelegate](index.md) : [AddNetworkApi](../-add-network-api/index.md), [AddNetworkApiAsync](../-add-network-api-async/index.md)

A delegate for synchronous and asynchronous APIs to add networks.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

| |
|---|
| [AddNetworkApi](../-add-network-api/index.md) |
| [AddNetworkApiAsync](../-add-network-api-async/index.md) |

#### Inheritors

| |
|---|
| [WisefyAddNetworkDelegate](../-wisefy-add-network-delegate/index.md) |

## Functions

| Name | Summary |
|---|---|
| [addNetwork](../-add-network-api/add-network.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.CHANGE_WIFI_STATE&quot;])<br>abstract fun [addNetwork](../-add-network-api/add-network.md)(request: [AddNetworkRequest](../../com.isupatches.android.wisefy.addnetwork.entities/-add-network-request/index.md)): [AddNetworkResult](../../com.isupatches.android.wisefy.addnetwork.entities/-add-network-result/index.md)<br>A synchronous API for adding a network.<br>[androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.CHANGE_WIFI_STATE&quot;])<br>abstract fun [addNetwork](../-add-network-api-async/add-network.md)(request: [AddNetworkRequest](../../com.isupatches.android.wisefy.addnetwork.entities/-add-network-request/index.md), callbacks: [AddNetworkCallbacks](../../com.isupatches.android.wisefy.addnetwork.callbacks/-add-network-callbacks/index.md)?)<br>An asynchronous API for adding a network. |
| [equals](../../com.isupatches.android.wisefy.addnetwork.entities/-add-network-result/-failure/-assertion/index.md#585090901%2FFunctions%2F-271260435) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.addnetwork.entities/-add-network-result/-failure/-assertion/index.md#585090901%2FFunctions%2F-271260435)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../com.isupatches.android.wisefy.addnetwork.entities/-add-network-result/-failure/-assertion/index.md#1794629105%2FFunctions%2F-271260435) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.addnetwork.entities/-add-network-result/-failure/-assertion/index.md#1794629105%2FFunctions%2F-271260435)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../com.isupatches.android.wisefy.addnetwork.entities/-add-network-result/-failure/-assertion/index.md#1616463040%2FFunctions%2F-271260435) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.addnetwork.entities/-add-network-result/-failure/-assertion/index.md#1616463040%2FFunctions%2F-271260435)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
