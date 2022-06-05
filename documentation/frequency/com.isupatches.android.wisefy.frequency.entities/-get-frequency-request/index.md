//[frequency](../../../index.md)/[com.isupatches.android.wisefy.frequency.entities](../index.md)/[GetFrequencyRequest](index.md)

# GetFrequencyRequest

[androidJvm]\
sealed class [GetFrequencyRequest](index.md)

A set of classes and objects that are used to represent requests to get a network's frequency.

#### Author

Patches Klinefelter

#### Since

03/2022

## Types

| Name | Summary |
|---|---|
| [CurrentNetwork](-current-network/index.md) | [androidJvm]<br>object [CurrentNetwork](-current-network/index.md) : [GetFrequencyRequest](index.md)<br>A data representation of a request to get the current network's frequency. |
| [ForNetwork](-for-network/index.md) | [androidJvm]<br>data class [ForNetwork](-for-network/index.md)(val network: [WifiInfo](https://developer.android.com/reference/kotlin/android/net/wifi/WifiInfo.html)) : [GetFrequencyRequest](index.md)<br>A data representation of a request to get a given network's frequency. |

## Functions

| Name | Summary |
|---|---|
| [equals](../-is-network5g-hz-result/-false/index.md#585090901%2FFunctions%2F-831600846) | [androidJvm]<br>open operator fun [equals](../-is-network5g-hz-result/-false/index.md#585090901%2FFunctions%2F-831600846)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../-is-network5g-hz-result/-false/index.md#1794629105%2FFunctions%2F-831600846) | [androidJvm]<br>open fun [hashCode](../-is-network5g-hz-result/-false/index.md#1794629105%2FFunctions%2F-831600846)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../-is-network5g-hz-result/-false/index.md#1616463040%2FFunctions%2F-831600846) | [androidJvm]<br>open fun [toString](../-is-network5g-hz-result/-false/index.md#1616463040%2FFunctions%2F-831600846)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Inheritors

| Name |
|---|
| [CurrentNetwork](-current-network/index.md) |
| [ForNetwork](-for-network/index.md) |
