//[signal](../../../../index.md)/[com.isupatches.android.wisefy.signal.entities](../../index.md)/[CompareSignalLevelResult](../index.md)/[Success](index.md)

# Success

[androidJvm]\
sealed class [Success](index.md) : [CompareSignalLevelResult](../index.md)

A set of classes and objects that are used to represent a success while comparing the RSSI values of two networks.

*Notes* See https://developer.android.com/reference/android/net/wifi/WifiManager#compareSignalLevel(int,%20int)

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

## Types

| Name | Summary |
|---|---|
| [FirstRSSIValueIsStronger](-first-r-s-s-i-value-is-stronger/index.md) | [androidJvm]<br>data class [FirstRSSIValueIsStronger](-first-r-s-s-i-value-is-stronger/index.md)(val value: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [CompareSignalLevelResult.Success](index.md)<br>A representation of when the first network has an RSSI value that is stronger than the second network's RSSI. |
| [FirstRSSIValueIsWeaker](-first-r-s-s-i-value-is-weaker/index.md) | [androidJvm]<br>data class [FirstRSSIValueIsWeaker](-first-r-s-s-i-value-is-weaker/index.md)(val value: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [CompareSignalLevelResult.Success](index.md)<br>A representation of when the first network has an RSSI value that is weaker than the second network's RSSI. |
| [RSSIValuesAreEqual](-r-s-s-i-values-are-equal/index.md) | [androidJvm]<br>data class [RSSIValuesAreEqual](-r-s-s-i-values-are-equal/index.md)(val value: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [CompareSignalLevelResult.Success](index.md)<br>A representation of when the first network has an RSSI value that is equal to the second network's RSSI. |

## Properties

| Name | Summary |
|---|---|
| [value](value.md) | [androidJvm]<br>open val [value](value.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>The result of the comparison. This will be less than 0 if first RSSI value is weaker than the second RSSI, 0 if the two have the same strength, and greater than zero if the first RSSI is stronger than the second RSSI value. |

## Inheritors

| Name |
|---|
| [FirstRSSIValueIsWeaker](-first-r-s-s-i-value-is-weaker/index.md) |
| [RSSIValuesAreEqual](-r-s-s-i-values-are-equal/index.md) |
| [FirstRSSIValueIsStronger](-first-r-s-s-i-value-is-stronger/index.md) |
