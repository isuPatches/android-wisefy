//[signal](../../../../index.md)/[com.isupatches.android.wisefy.signal.entities](../../index.md)/[CalculateSignalLevelRequest](../index.md)/[BelowAndroid30](index.md)

# BelowAndroid30

data class [BelowAndroid30](index.md)(val rssiLevel: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val numLevels: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [CalculateSignalLevelRequest](../index.md)

A data representation of a request on pre-Android 30 devices to calculate the number of signal bars based on the RSSI level of a network and the desired amount of bars.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

| |
|---|
| [CalculateSignalLevelRequest](../index.md) |

## Constructors

| | |
|---|---|
| [BelowAndroid30](-below-android30.md) | [androidJvm]<br>constructor(rssiLevel: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), numLevels: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [numLevels](num-levels.md) | [androidJvm]<br>val [numLevels](num-levels.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>The desired number of signal strength bars |
| [rssiLevel](rssi-level.md) | [androidJvm]<br>val [rssiLevel](rssi-level.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>The RSSI level of the network |

## Functions

| Name | Summary |
|---|---|
| [component1](component1.md) | [androidJvm]<br>operator fun [component1](component1.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [component2](component2.md) | [androidJvm]<br>operator fun [component2](component2.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [copy](copy.md) | [androidJvm]<br>fun [copy](copy.md)(rssiLevel: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), numLevels: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [CalculateSignalLevelRequest.BelowAndroid30](index.md) |
| [equals](../../-compare-signal-level-result/-success/-first-r-s-s-i-value-is-stronger/index.md#585090901%2FFunctions%2F1816002514) | [androidJvm]<br>open operator override fun [equals](../../-compare-signal-level-result/-success/-first-r-s-s-i-value-is-stronger/index.md#585090901%2FFunctions%2F1816002514)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../-compare-signal-level-result/-success/-first-r-s-s-i-value-is-stronger/index.md#1794629105%2FFunctions%2F1816002514) | [androidJvm]<br>open override fun [hashCode](../../-compare-signal-level-result/-success/-first-r-s-s-i-value-is-stronger/index.md#1794629105%2FFunctions%2F1816002514)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../-compare-signal-level-result/-success/-first-r-s-s-i-value-is-stronger/index.md#1616463040%2FFunctions%2F1816002514) | [androidJvm]<br>open override fun [toString](../../-compare-signal-level-result/-success/-first-r-s-s-i-value-is-stronger/index.md#1616463040%2FFunctions%2F1816002514)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
