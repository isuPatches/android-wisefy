//[wisefy](../../../index.md)/[com.isupatches.android.wisefy.removenetwork.entities](../index.md)/[RemoveNetworkResult](index.md)

# RemoveNetworkResult

[androidJvm]\
sealed class [RemoveNetworkResult](index.md)

## Types

| Name | Summary |
|---|---|
| [NetworkNotFound](-network-not-found/index.md) | [androidJvm]<br>object [NetworkNotFound](-network-not-found/index.md) : [RemoveNetworkResult](index.md) |
| [ResultCode](-result-code/index.md) | [androidJvm]<br>data class [ResultCode](-result-code/index.md)(**data**: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [RemoveNetworkResult](index.md) |
| [Succeeded](-succeeded/index.md) | [androidJvm]<br>data class [Succeeded](-succeeded/index.md)(**data**: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) : [RemoveNetworkResult](index.md) |

## Functions

| Name | Summary |
|---|---|
| [equals](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#585090901%2FFunctions%2F1622544596) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#585090901%2FFunctions%2F1622544596)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1794629105%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1794629105%2FFunctions%2F1622544596)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1616463040%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1616463040%2FFunctions%2F1622544596)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Inheritors

| Name |
|---|
| [RemoveNetworkResult](-result-code/index.md) |
| [RemoveNetworkResult](-succeeded/index.md) |
| [RemoveNetworkResult](-network-not-found/index.md) |
