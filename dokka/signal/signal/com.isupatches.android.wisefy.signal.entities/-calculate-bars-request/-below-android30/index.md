//[signal](../../../../index.md)/[com.isupatches.android.wisefy.signal.entities](../../index.md)/[CalculateBarsRequest](../index.md)/[BelowAndroid30](index.md)

# BelowAndroid30

[androidJvm]\
data class [BelowAndroid30](index.md)(val rssiLevel: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val targetNumberOfBars: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [CalculateBarsRequest](../index.md)

A data representation of a request on devices below Android 30 to calculate the number of signal bars based on an RSSI level and a given number for the desired amount of bars.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.signal.entities.CalculateBarsRequest](../index.md) |  |

## Constructors

| | |
|---|---|
| [BelowAndroid30](-below-android30.md) | [androidJvm]<br>fun [BelowAndroid30](-below-android30.md)(rssiLevel: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), targetNumberOfBars: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [rssiLevel](rssi-level.md) | [androidJvm]<br>val [rssiLevel](rssi-level.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>The RSSI level of the network |
| [targetNumberOfBars](target-number-of-bars.md) | [androidJvm]<br>val [targetNumberOfBars](target-number-of-bars.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>The desired number of bars |
