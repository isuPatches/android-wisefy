//[signal](../../../index.md)/[com.isupatches.android.wisefy.signal.entities](../index.md)/[CalculateSignalLevelRequest](index.md)

# CalculateSignalLevelRequest

[androidJvm]\
sealed class [CalculateSignalLevelRequest](index.md)

A set of classes and objects that are used to represent requests to calculate the number of signal strength bars based on the RSSI level of a network.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

## Types

| Name | Summary |
|---|---|
| [Android30AndAbove](-android30-and-above/index.md) | [androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 30)<br>data class [Android30AndAbove](-android30-and-above/index.md)(val rssiLevel: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [CalculateSignalLevelRequest](index.md)<br>A data representation of a request on Android 30 or higher devices to calculate the number of signal strength bars based on the RSSI level of a network. |
| [BelowAndroid30](-below-android30/index.md) | [androidJvm]<br>data class [BelowAndroid30](-below-android30/index.md)(val rssiLevel: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val numLevels: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [CalculateSignalLevelRequest](index.md)<br>A data representation of a request on pre-Android 30 devices to calculate the number of signal bars based on the RSSI level of a network and the desired amount of bars. |

## Inheritors

| Name |
|---|
| [BelowAndroid30](-below-android30/index.md) |
| [Android30AndAbove](-android30-and-above/index.md) |
