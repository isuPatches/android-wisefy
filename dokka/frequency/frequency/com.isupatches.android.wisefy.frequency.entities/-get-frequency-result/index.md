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

## Inheritors

| Name |
|---|
| [Empty](-empty/index.md) |
| [WithFrequency](-with-frequency/index.md) |
