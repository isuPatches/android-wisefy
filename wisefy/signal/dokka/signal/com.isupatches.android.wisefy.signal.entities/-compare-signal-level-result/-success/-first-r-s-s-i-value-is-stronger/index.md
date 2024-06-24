//[signal](../../../../../index.md)/[com.isupatches.android.wisefy.signal.entities](../../../index.md)/[CompareSignalLevelResult](../../index.md)/[Success](../index.md)/[FirstRSSIValueIsStronger](index.md)

# FirstRSSIValueIsStronger

data class [FirstRSSIValueIsStronger](index.md)(val value: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [CompareSignalLevelResult.Success](../index.md)

A representation of when the first network has an RSSI value that is stronger than the second network's RSSI.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

| |
|---|
| [CompareSignalLevelResult.Success](../index.md) |

## Constructors

| | |
|---|---|
| [FirstRSSIValueIsStronger](-first-r-s-s-i-value-is-stronger.md) | [androidJvm]<br>constructor(value: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [value](value.md) | [androidJvm]<br>open override val [value](value.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>The difference between the first and second networks RSSI value (expected to be positive) |

## Functions

| Name | Summary |
|---|---|
| [component1](component1.md) | [androidJvm]<br>operator fun [component1](component1.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [copy](copy.md) | [androidJvm]<br>fun [copy](copy.md)(value: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [CompareSignalLevelResult.Success.FirstRSSIValueIsStronger](index.md) |
| [equals](index.md#585090901%2FFunctions%2F1816002514) | [androidJvm]<br>open operator override fun [equals](index.md#585090901%2FFunctions%2F1816002514)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](index.md#1794629105%2FFunctions%2F1816002514) | [androidJvm]<br>open override fun [hashCode](index.md#1794629105%2FFunctions%2F1816002514)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](index.md#1616463040%2FFunctions%2F1816002514) | [androidJvm]<br>open override fun [toString](index.md#1616463040%2FFunctions%2F1816002514)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
