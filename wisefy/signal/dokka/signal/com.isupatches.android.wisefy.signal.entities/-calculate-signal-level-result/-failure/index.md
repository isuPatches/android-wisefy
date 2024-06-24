//[signal](../../../../index.md)/[com.isupatches.android.wisefy.signal.entities](../../index.md)/[CalculateSignalLevelResult](../index.md)/[Failure](index.md)

# Failure

sealed class [Failure](index.md) : [CalculateSignalLevelResult](../index.md)

A set of classes that denote a failure while attempting to calculate the sign level of a network.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

| |
|---|
| [CalculateSignalLevelResult](../index.md) |

#### Inheritors

| |
|---|
| [Assertion](-assertion/index.md) |

## Types

| Name | Summary |
|---|---|
| [Assertion](-assertion/index.md) | [androidJvm]<br>data class [Assertion](-assertion/index.md)(val message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [CalculateSignalLevelResult.Failure](index.md)<br>A representation of a failure to calculate the sign level of a network due to hitting an unexpected path causing an assertion. |

## Functions

| Name | Summary |
|---|---|
| [equals](../../-compare-signal-level-result/-success/-first-r-s-s-i-value-is-stronger/index.md#585090901%2FFunctions%2F1816002514) | [androidJvm]<br>open operator fun [equals](../../-compare-signal-level-result/-success/-first-r-s-s-i-value-is-stronger/index.md#585090901%2FFunctions%2F1816002514)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../-compare-signal-level-result/-success/-first-r-s-s-i-value-is-stronger/index.md#1794629105%2FFunctions%2F1816002514) | [androidJvm]<br>open fun [hashCode](../../-compare-signal-level-result/-success/-first-r-s-s-i-value-is-stronger/index.md#1794629105%2FFunctions%2F1816002514)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../-compare-signal-level-result/-success/-first-r-s-s-i-value-is-stronger/index.md#1616463040%2FFunctions%2F1816002514) | [androidJvm]<br>open fun [toString](../../-compare-signal-level-result/-success/-first-r-s-s-i-value-is-stronger/index.md#1616463040%2FFunctions%2F1816002514)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
