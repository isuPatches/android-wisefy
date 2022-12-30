//[signal](../../../../../index.md)/[com.isupatches.android.wisefy.signal.entities](../../../index.md)/[CompareSignalLevelResult](../../index.md)/[Success](../index.md)/[FirstRSSIValueIsStronger](index.md)

# FirstRSSIValueIsStronger

[androidJvm]\
data class [FirstRSSIValueIsStronger](index.md)(val value: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [CompareSignalLevelResult.Success](../index.md)

A representation of when the first network has an RSSI value that is stronger than the second network's RSSI.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

androidJvm

| |
|---|
| [CompareSignalLevelResult.Success](../index.md) |

## Constructors

| | |
|---|---|
| [FirstRSSIValueIsStronger](-first-r-s-s-i-value-is-stronger.md) | [androidJvm]<br>fun [FirstRSSIValueIsStronger](-first-r-s-s-i-value-is-stronger.md)(value: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [value](value.md) | [androidJvm]<br>open override val [value](value.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>The difference between the first and second networks RSSI value (expected to be positive) |
