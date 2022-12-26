//[signal](../../../index.md)/[com.isupatches.android.wisefy.signal.entities](../index.md)/[CalculateSignalLevelResult](index.md)

# CalculateSignalLevelResult

[androidJvm]\
sealed class [CalculateSignalLevelResult](index.md)

A set of classes and objects that are used to represent a result while calculating the number of signal bars based on an RSSI level.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

## Types

| Name | Summary |
|---|---|
| [Failure](-failure/index.md) | [androidJvm]<br>sealed class [Failure](-failure/index.md) : [CalculateSignalLevelResult](index.md)<br>A set of classes that denote a failure while attempting to calculate the sign level of a network. |
| [Success](-success/index.md) | [androidJvm]<br>data class [Success](-success/index.md)(val value: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [CalculateSignalLevelResult](index.md)<br>A representation of a success while attempting to calculate the sign level of a network. |

## Inheritors

| Name |
|---|
| [Success](-success/index.md) |
| [Failure](-failure/index.md) |
