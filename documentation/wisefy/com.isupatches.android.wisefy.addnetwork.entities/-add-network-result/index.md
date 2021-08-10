//[wisefy](../../../index.md)/[com.isupatches.android.wisefy.addnetwork.entities](../index.md)/[AddNetworkResult](index.md)

# AddNetworkResult

[androidJvm]\
sealed class [AddNetworkResult](index.md)

## Types

| Name | Summary |
|---|---|
| [IntentLaunched](-intent-launched/index.md) | [androidJvm]<br>object [IntentLaunched](-intent-launched/index.md) : [AddNetworkResult](index.md) |
| [ResultCode](-result-code/index.md) | [androidJvm]<br>data class [ResultCode](-result-code/index.md)(**data**: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [AddNetworkResult](index.md) |
| [WrongSDKLevelError](-wrong-s-d-k-level-error/index.md) | [androidJvm]<br>data class [WrongSDKLevelError](-wrong-s-d-k-level-error/index.md)(**message**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [AddNetworkResult](index.md) |

## Functions

| Name | Summary |
|---|---|
| [equals](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#585090901%2FFunctions%2F1622544596) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#585090901%2FFunctions%2F1622544596)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1794629105%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1794629105%2FFunctions%2F1622544596)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1616463040%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1616463040%2FFunctions%2F1622544596)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Inheritors

| Name |
|---|
| [AddNetworkResult](-result-code/index.md) |
| [AddNetworkResult](-intent-launched/index.md) |
| [AddNetworkResult](-wrong-s-d-k-level-error/index.md) |
