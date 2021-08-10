//[wisefy](../../../index.md)/[com.isupatches.android.wisefy.networkconnection.entities](../index.md)/[NetworkConnectionResult](index.md)

# NetworkConnectionResult

[androidJvm]\
sealed class [NetworkConnectionResult](index.md)

## Types

| Name | Summary |
|---|---|
| [ConnectionRequestPlaced](-connection-request-placed/index.md) | [androidJvm]<br>object [ConnectionRequestPlaced](-connection-request-placed/index.md) : [NetworkConnectionResult](index.md) |
| [NetworkNotFound](-network-not-found/index.md) | [androidJvm]<br>object [NetworkNotFound](-network-not-found/index.md) : [NetworkConnectionResult](index.md) |
| [Succeeded](-succeeded/index.md) | [androidJvm]<br>data class [Succeeded](-succeeded/index.md)(**data**: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) : [NetworkConnectionResult](index.md) |
| [UnregisterRequestSent](-unregister-request-sent/index.md) | [androidJvm]<br>object [UnregisterRequestSent](-unregister-request-sent/index.md) : [NetworkConnectionResult](index.md) |

## Functions

| Name | Summary |
|---|---|
| [equals](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#585090901%2FFunctions%2F1622544596) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#585090901%2FFunctions%2F1622544596)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1794629105%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1794629105%2FFunctions%2F1622544596)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1616463040%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1616463040%2FFunctions%2F1622544596)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Inheritors

| Name |
|---|
| [NetworkConnectionResult](-succeeded/index.md) |
| [NetworkConnectionResult](-connection-request-placed/index.md) |
| [NetworkConnectionResult](-network-not-found/index.md) |
| [NetworkConnectionResult](-unregister-request-sent/index.md) |
