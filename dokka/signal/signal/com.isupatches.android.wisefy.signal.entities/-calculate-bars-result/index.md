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

## Inheritors

| Name |
|---|
| [Success](-success/index.md) |
| [WrongSDKLevel](-wrong-s-d-k-level/index.md) |
