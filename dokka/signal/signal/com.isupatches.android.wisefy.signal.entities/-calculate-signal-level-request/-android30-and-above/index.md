//[signal](../../../../index.md)/[com.isupatches.android.wisefy.signal.entities](../../index.md)/[CalculateSignalLevelRequest](../index.md)/[Android30AndAbove](index.md)

# Android30AndAbove

[androidJvm]\
@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 30)

data class [Android30AndAbove](index.md)(val rssiLevel: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [CalculateSignalLevelRequest](../index.md)

A data representation of a request on Android 30 or higher devices to calculate the number of signal strength bars based on the RSSI level of a network.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

androidJvm

| |
|---|
| [CalculateSignalLevelRequest](../index.md) |

## Constructors

| | |
|---|---|
| [Android30AndAbove](-android30-and-above.md) | [androidJvm]<br>fun [Android30AndAbove](-android30-and-above.md)(rssiLevel: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [rssiLevel](rssi-level.md) | [androidJvm]<br>val [rssiLevel](rssi-level.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>The RSSI level of the network |
