//[removenetwork](../../../index.md)/[com.isupatches.android.wisefy.removenetwork](../index.md)/[RemoveNetworkDelegate](index.md)

# RemoveNetworkDelegate

[androidJvm]\
interface [RemoveNetworkDelegate](index.md) : [RemoveNetworkApi](../-remove-network-api/index.md), [RemoveNetworkApiAsync](../-remove-network-api-async/index.md)

A delegate for synchronous and asynchronous APIs for removing a network.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.removenetwork.RemoveNetworkApi](../-remove-network-api/index.md) |  |
| [com.isupatches.android.wisefy.removenetwork.RemoveNetworkApiAsync](../-remove-network-api-async/index.md) |  |

## Functions

| Name | Summary |
|---|---|
| [equals](../../com.isupatches.android.wisefy.removenetwork.entities/-remove-network-result/-failure/-result-code/index.md#585090901%2FFunctions%2F-2039424092) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.removenetwork.entities/-remove-network-result/-failure/-result-code/index.md#585090901%2FFunctions%2F-2039424092)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../com.isupatches.android.wisefy.removenetwork.entities/-remove-network-result/-failure/-result-code/index.md#1794629105%2FFunctions%2F-2039424092) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.removenetwork.entities/-remove-network-result/-failure/-result-code/index.md#1794629105%2FFunctions%2F-2039424092)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [removeNetwork](../-remove-network-api/remove-network.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.CHANGE_WIFI_STATE&quot;])<br>abstract fun [removeNetwork](../-remove-network-api/remove-network.md)(request: [RemoveNetworkRequest](../../com.isupatches.android.wisefy.removenetwork.entities/-remove-network-request/index.md)): [RemoveNetworkResult](../../com.isupatches.android.wisefy.removenetwork.entities/-remove-network-result/index.md)<br>An synchronous API to remove a network.<br>[androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.CHANGE_WIFI_STATE&quot;])<br>abstract fun [removeNetwork](../-remove-network-api-async/remove-network.md)(request: [RemoveNetworkRequest](../../com.isupatches.android.wisefy.removenetwork.entities/-remove-network-request/index.md), callbacks: [RemoveNetworkCallbacks](../../com.isupatches.android.wisefy.removenetwork.callbacks/-remove-network-callbacks/index.md)?)<br>An asynchronous API to remove a network. |
| [toString](../../com.isupatches.android.wisefy.removenetwork.entities/-remove-network-result/-failure/-result-code/index.md#1616463040%2FFunctions%2F-2039424092) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.removenetwork.entities/-remove-network-result/-failure/-result-code/index.md#1616463040%2FFunctions%2F-2039424092)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Inheritors

| Name |
|---|
| [WisefyRemoveNetworkDelegate](../-wisefy-remove-network-delegate/index.md) |
