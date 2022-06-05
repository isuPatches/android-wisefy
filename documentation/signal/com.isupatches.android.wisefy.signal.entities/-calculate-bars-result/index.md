//[signal](../../../index.md)/[com.isupatches.android.wisefy.signal.entities](../index.md)/[CalculateBarsResult](index.md)

# CalculateBarsResult

[androidJvm]\
sealed class [CalculateBarsResult](index.md)

A set of classes and objects that are used to represent a result while calculating the number of signal bars based on an RSSI level.

#### Author

Patches Klinefelter

#### Since

03/2022

## Types

| Name | Summary |
|---|---|
| [Success](-success/index.md) | [androidJvm]<br>data class [Success](-success/index.md)(val value: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [CalculateBarsResult](index.md)<br>A data representation of the number of bars that represents the RSSI level of a network. |
| [WrongSDKLevel](-wrong-s-d-k-level/index.md) | [androidJvm]<br>data class [WrongSDKLevel](-wrong-s-d-k-level/index.md)(val message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [CalculateBarsResult](index.md)<br>A data representation of calling the wrong version of calculate bars on a given SDK level. |

## Functions

| Name | Summary |
|---|---|
| [equals](../-compare-signal-level-result/index.md#585090901%2FFunctions%2F1816002514) | [androidJvm]<br>open operator fun [equals](../-compare-signal-level-result/index.md#585090901%2FFunctions%2F1816002514)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../-compare-signal-level-result/index.md#1794629105%2FFunctions%2F1816002514) | [androidJvm]<br>open fun [hashCode](../-compare-signal-level-result/index.md#1794629105%2FFunctions%2F1816002514)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../-compare-signal-level-result/index.md#1616463040%2FFunctions%2F1816002514) | [androidJvm]<br>open fun [toString](../-compare-signal-level-result/index.md#1616463040%2FFunctions%2F1816002514)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Inheritors

| Name |
|---|
| [Success](-success/index.md) |
| [WrongSDKLevel](-wrong-s-d-k-level/index.md) |
