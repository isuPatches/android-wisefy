//[signal](../../../../index.md)/[com.isupatches.android.wisefy.signal.entities](../../index.md)/[CalculateSignalLevelResult](../index.md)/[Success](index.md)

# Success

data class [Success](index.md)(val value: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [CalculateSignalLevelResult](../index.md)

A representation of a success while attempting to calculate the sign level of a network.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

| |
|---|
| [CalculateSignalLevelResult](../index.md) |

## Constructors

| | |
|---|---|
| [Success](-success.md) | [androidJvm]<br>constructor(value: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [value](value.md) | [androidJvm]<br>val [value](value.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>The number of signal bars for the network based on its RSSI level |

## Functions

| Name | Summary |
|---|---|
| [component1](component1.md) | [androidJvm]<br>operator fun [component1](component1.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [copy](copy.md) | [androidJvm]<br>fun [copy](copy.md)(value: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [CalculateSignalLevelResult.Success](index.md) |
| [equals](../../-compare-signal-level-result/-success/-first-r-s-s-i-value-is-stronger/index.md#585090901%2FFunctions%2F1816002514) | [androidJvm]<br>open operator override fun [equals](../../-compare-signal-level-result/-success/-first-r-s-s-i-value-is-stronger/index.md#585090901%2FFunctions%2F1816002514)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../-compare-signal-level-result/-success/-first-r-s-s-i-value-is-stronger/index.md#1794629105%2FFunctions%2F1816002514) | [androidJvm]<br>open override fun [hashCode](../../-compare-signal-level-result/-success/-first-r-s-s-i-value-is-stronger/index.md#1794629105%2FFunctions%2F1816002514)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../-compare-signal-level-result/-success/-first-r-s-s-i-value-is-stronger/index.md#1616463040%2FFunctions%2F1816002514) | [androidJvm]<br>open override fun [toString](../../-compare-signal-level-result/-success/-first-r-s-s-i-value-is-stronger/index.md#1616463040%2FFunctions%2F1816002514)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
