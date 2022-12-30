//[signal](../../../../index.md)/[com.isupatches.android.wisefy.signal.entities](../../index.md)/[CalculateSignalLevelRequest](../index.md)/[BelowAndroid30](index.md)

# BelowAndroid30

[androidJvm]\
data class [BelowAndroid30](index.md)(val rssiLevel: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val numLevels: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [CalculateSignalLevelRequest](../index.md)

A data representation of a request on pre-Android 30 devices to calculate the number of signal bars based on the RSSI level of a network and the desired amount of bars.

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
| [BelowAndroid30](-below-android30.md) | [androidJvm]<br>fun [BelowAndroid30](-below-android30.md)(rssiLevel: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), numLevels: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [numLevels](num-levels.md) | [androidJvm]<br>val [numLevels](num-levels.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>The desired number of signal strength bars |
| [rssiLevel](rssi-level.md) | [androidJvm]<br>val [rssiLevel](rssi-level.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>The RSSI level of the network |
