//[signal](../../../index.md)/[com.isupatches.android.wisefy.signal.entities](../index.md)/[CalculateSignalLevelResult](index.md)

# CalculateSignalLevelResult

sealed class [CalculateSignalLevelResult](index.md)

A set of classes and objects that are used to represent a result while calculating the number of signal bars based on an RSSI level.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### Inheritors

| |
|---|
| [Success](-success/index.md) |
| [Failure](-failure/index.md) |

## Types

| Name | Summary |
|---|---|
| [Failure](-failure/index.md) | [androidJvm]<br>sealed class [Failure](-failure/index.md) : [CalculateSignalLevelResult](index.md)<br>A set of classes that denote a failure while attempting to calculate the sign level of a network. |
| [Success](-success/index.md) | [androidJvm]<br>data class [Success](-success/index.md)(val value: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [CalculateSignalLevelResult](index.md)<br>A representation of a success while attempting to calculate the sign level of a network. |

## Functions

| Name | Summary |
|---|---|
| [equals](../-compare-signal-level-result/-success/-first-r-s-s-i-value-is-stronger/index.md#585090901%2FFunctions%2F1816002514) | [androidJvm]<br>open operator fun [equals](../-compare-signal-level-result/-success/-first-r-s-s-i-value-is-stronger/index.md#585090901%2FFunctions%2F1816002514)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../-compare-signal-level-result/-success/-first-r-s-s-i-value-is-stronger/index.md#1794629105%2FFunctions%2F1816002514) | [androidJvm]<br>open fun [hashCode](../-compare-signal-level-result/-success/-first-r-s-s-i-value-is-stronger/index.md#1794629105%2FFunctions%2F1816002514)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../-compare-signal-level-result/-success/-first-r-s-s-i-value-is-stronger/index.md#1616463040%2FFunctions%2F1816002514) | [androidJvm]<br>open fun [toString](../-compare-signal-level-result/-success/-first-r-s-s-i-value-is-stronger/index.md#1616463040%2FFunctions%2F1816002514)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
