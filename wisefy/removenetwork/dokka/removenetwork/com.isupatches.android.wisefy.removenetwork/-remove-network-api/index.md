//[removenetwork](../../../index.md)/[com.isupatches.android.wisefy.removenetwork](../index.md)/[RemoveNetworkApi](index.md)

# RemoveNetworkApi

interface [RemoveNetworkApi](index.md)

A set of synchronous APIs for removing a network.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### Inheritors

| |
|---|
| [RemoveNetworkDelegate](../-remove-network-delegate/index.md) |

## Functions

| Name | Summary |
|---|---|
| [equals](../../com.isupatches.android.wisefy.removenetwork.entities/-remove-network-result/-failure/-assertion/index.md#585090901%2FFunctions%2F-2039424092) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.removenetwork.entities/-remove-network-result/-failure/-assertion/index.md#585090901%2FFunctions%2F-2039424092)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../com.isupatches.android.wisefy.removenetwork.entities/-remove-network-result/-failure/-assertion/index.md#1794629105%2FFunctions%2F-2039424092) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.removenetwork.entities/-remove-network-result/-failure/-assertion/index.md#1794629105%2FFunctions%2F-2039424092)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [removeNetwork](remove-network.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;, &quot;android.permission.CHANGE_WIFI_STATE&quot;])<br>abstract fun [removeNetwork](remove-network.md)(request: [RemoveNetworkRequest](../../com.isupatches.android.wisefy.removenetwork.entities/-remove-network-request/index.md)): [RemoveNetworkResult](../../com.isupatches.android.wisefy.removenetwork.entities/-remove-network-result/index.md)<br>An synchronous API to remove a network. |
| [toString](../../com.isupatches.android.wisefy.removenetwork.entities/-remove-network-result/-failure/-assertion/index.md#1616463040%2FFunctions%2F-2039424092) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.removenetwork.entities/-remove-network-result/-failure/-assertion/index.md#1616463040%2FFunctions%2F-2039424092)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
