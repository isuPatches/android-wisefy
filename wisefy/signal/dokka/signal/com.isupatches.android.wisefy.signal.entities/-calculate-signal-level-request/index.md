//[signal](../../../index.md)/[com.isupatches.android.wisefy.signal.entities](../index.md)/[CalculateSignalLevelRequest](index.md)

# CalculateSignalLevelRequest

sealed class [CalculateSignalLevelRequest](index.md)

A set of classes and objects that are used to represent requests to calculate the number of signal strength bars based on the RSSI level of a network.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### Inheritors

| |
|---|
| [BelowAndroid30](-below-android30/index.md) |
| [Android30AndAbove](-android30-and-above/index.md) |

## Types

| Name | Summary |
|---|---|
| [Android30AndAbove](-android30-and-above/index.md) | [androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 30)<br>data class [Android30AndAbove](-android30-and-above/index.md)(val rssiLevel: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [CalculateSignalLevelRequest](index.md)<br>A data representation of a request on Android 30 or higher devices to calculate the number of signal strength bars based on the RSSI level of a network. |
| [BelowAndroid30](-below-android30/index.md) | [androidJvm]<br>data class [BelowAndroid30](-below-android30/index.md)(val rssiLevel: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val numLevels: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [CalculateSignalLevelRequest](index.md)<br>A data representation of a request on pre-Android 30 devices to calculate the number of signal bars based on the RSSI level of a network and the desired amount of bars. |

## Functions

| Name | Summary |
|---|---|
| [equals](../-compare-signal-level-result/-success/-first-r-s-s-i-value-is-stronger/index.md#585090901%2FFunctions%2F1816002514) | [androidJvm]<br>open operator fun [equals](../-compare-signal-level-result/-success/-first-r-s-s-i-value-is-stronger/index.md#585090901%2FFunctions%2F1816002514)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../-compare-signal-level-result/-success/-first-r-s-s-i-value-is-stronger/index.md#1794629105%2FFunctions%2F1816002514) | [androidJvm]<br>open fun [hashCode](../-compare-signal-level-result/-success/-first-r-s-s-i-value-is-stronger/index.md#1794629105%2FFunctions%2F1816002514)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../-compare-signal-level-result/-success/-first-r-s-s-i-value-is-stronger/index.md#1616463040%2FFunctions%2F1816002514) | [androidJvm]<br>open fun [toString](../-compare-signal-level-result/-success/-first-r-s-s-i-value-is-stronger/index.md#1616463040%2FFunctions%2F1816002514)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
