//[signal](../../../index.md)/[com.isupatches.android.wisefy.signal.entities](../index.md)/[CalculateBarsRequest](index.md)

# CalculateBarsRequest

[androidJvm]\
sealed class [CalculateBarsRequest](index.md)

A set of classes and objects that are used to represent requests to calculate the number of signal bars based on an RSSI level.

#### Author

Patches Klinefelter

#### Since

03/2022

## Types

| Name | Summary |
|---|---|
| [Android30AndAbove](-android30-and-above/index.md) | [androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 30)<br>data class [Android30AndAbove](-android30-and-above/index.md)(val rssiLevel: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [CalculateBarsRequest](index.md)<br>A data representation of a request on devices above Android 30 to calculate the number of signal bars based on an RSSI level. |
| [BelowAndroid30](-below-android30/index.md) | [androidJvm]<br>data class [BelowAndroid30](-below-android30/index.md)(val rssiLevel: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val targetNumberOfBars: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [CalculateBarsRequest](index.md)<br>A data representation of a request on devices below Android 30 to calculate the number of signal bars based on an RSSI level and a given number for the desired amount of bars. |

## Inheritors

| Name |
|---|
| [BelowAndroid30](-below-android30/index.md) |
| [Android30AndAbove](-android30-and-above/index.md) |
