//[signal](../../../../index.md)/[com.isupatches.android.wisefy.signal.entities](../../index.md)/[CalculateSignalLevelResult](../index.md)/[Failure](index.md)

# Failure

[androidJvm]\
sealed class [Failure](index.md) : [CalculateSignalLevelResult](../index.md)

A set of classes that denote a failure while attempting to calculate the sign level of a network.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.signal.entities.CalculateSignalLevelResult](../index.md) |  |

## Types

| Name | Summary |
|---|---|
| [Assertion](-assertion/index.md) | [androidJvm]<br>data class [Assertion](-assertion/index.md)(val message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [CalculateSignalLevelResult.Failure](index.md)<br>A representation of a failure to calculate the sign level of a network due to hitting an unexpected path causing an assertion. |

## Inheritors

| Name |
|---|
| [Assertion](-assertion/index.md) |
