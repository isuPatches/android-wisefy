//[signal](../../../index.md)/[com.isupatches.android.wisefy.signal.entities](../index.md)/[CompareSignalLevelRequest](index.md)

# CompareSignalLevelRequest

[androidJvm]\
data class [CompareSignalLevelRequest](index.md)(val rssi1: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val rssi2: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))

A data representation of a request to compare the RSSI level of two networks.

#### Author

Patches Klinefelter

#### Since

03/2022

## Constructors

| | |
|---|---|
| [CompareSignalLevelRequest](-compare-signal-level-request.md) | [androidJvm]<br>fun [CompareSignalLevelRequest](-compare-signal-level-request.md)(rssi1: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), rssi2: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |

## Functions

| Name | Summary |
|---|---|
| [component1](component1.md) | [androidJvm]<br>operator fun [component1](component1.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [component2](component2.md) | [androidJvm]<br>operator fun [component2](component2.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [copy](copy.md) | [androidJvm]<br>fun [copy](copy.md)(rssi1: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), rssi2: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [CompareSignalLevelRequest](index.md) |
| [equals](../-compare-signal-level-result/index.md#585090901%2FFunctions%2F1816002514) | [androidJvm]<br>open operator override fun [equals](../-compare-signal-level-result/index.md#585090901%2FFunctions%2F1816002514)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../-compare-signal-level-result/index.md#1794629105%2FFunctions%2F1816002514) | [androidJvm]<br>open override fun [hashCode](../-compare-signal-level-result/index.md#1794629105%2FFunctions%2F1816002514)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../-compare-signal-level-result/index.md#1616463040%2FFunctions%2F1816002514) | [androidJvm]<br>open override fun [toString](../-compare-signal-level-result/index.md#1616463040%2FFunctions%2F1816002514)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [rssi1](rssi1.md) | [androidJvm]<br>val [rssi1](rssi1.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>The RSSI level of the first network |
| [rssi2](rssi2.md) | [androidJvm]<br>val [rssi2](rssi2.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>The RSSI level of the second network |
