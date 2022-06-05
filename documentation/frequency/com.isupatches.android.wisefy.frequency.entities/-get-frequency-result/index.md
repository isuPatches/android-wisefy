//[frequency](../../../index.md)/[com.isupatches.android.wisefy.frequency.entities](../index.md)/[GetFrequencyResult](index.md)

# GetFrequencyResult

[androidJvm]\
sealed class [GetFrequencyResult](index.md)

A set of classes that are data representations of a result when getting the frequency of a network.

#### Author

Patches Klinefelter

#### Since

03/2022

## Types

| Name | Summary |
|---|---|
| [Empty](-empty/index.md) | [androidJvm]<br>object [Empty](-empty/index.md) : [GetFrequencyResult](index.md)<br>A data representation for when there is no network to retrieve the frequency. |
| [WithFrequency](-with-frequency/index.md) | [androidJvm]<br>data class [WithFrequency](-with-frequency/index.md)(val data: [FrequencyData](../-frequency-data/index.md)) : [GetFrequencyResult](index.md)<br>A data representation for when there is a network to retrieve the frequency. |

## Functions

| Name | Summary |
|---|---|
| [equals](../-is-network5g-hz-result/-false/index.md#585090901%2FFunctions%2F-831600846) | [androidJvm]<br>open operator fun [equals](../-is-network5g-hz-result/-false/index.md#585090901%2FFunctions%2F-831600846)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../-is-network5g-hz-result/-false/index.md#1794629105%2FFunctions%2F-831600846) | [androidJvm]<br>open fun [hashCode](../-is-network5g-hz-result/-false/index.md#1794629105%2FFunctions%2F-831600846)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../-is-network5g-hz-result/-false/index.md#1616463040%2FFunctions%2F-831600846) | [androidJvm]<br>open fun [toString](../-is-network5g-hz-result/-false/index.md#1616463040%2FFunctions%2F-831600846)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Inheritors

| Name |
|---|
| [Empty](-empty/index.md) |
| [WithFrequency](-with-frequency/index.md) |
