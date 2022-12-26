//[signal](../../../../../index.md)/[com.isupatches.android.wisefy.signal.entities](../../../index.md)/[CompareSignalLevelResult](../../index.md)/[Success](../index.md)/[RSSIValuesAreEqual](index.md)

# RSSIValuesAreEqual

[androidJvm]\
data class [RSSIValuesAreEqual](index.md)(val value: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [CompareSignalLevelResult.Success](../index.md)

A representation of when the first network has an RSSI value that is equal to the second network's RSSI.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.signal.entities.CompareSignalLevelResult.Success](../index.md) |  |

## Constructors

| | |
|---|---|
| [RSSIValuesAreEqual](-r-s-s-i-values-are-equal.md) | [androidJvm]<br>fun [RSSIValuesAreEqual](-r-s-s-i-values-are-equal.md)(value: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [value](value.md) | [androidJvm]<br>open override val [value](value.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>The difference between the first and second networks RSSI value (always 0) |
