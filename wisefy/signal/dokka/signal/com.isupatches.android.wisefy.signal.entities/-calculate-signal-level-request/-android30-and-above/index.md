//[signal](../../../../index.md)/[com.isupatches.android.wisefy.signal.entities](../../index.md)/[CalculateSignalLevelRequest](../index.md)/[Android30AndAbove](index.md)

# Android30AndAbove

@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 30)

data class [Android30AndAbove](index.md)(val rssiLevel: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [CalculateSignalLevelRequest](../index.md)

A data representation of a request on Android 30 or higher devices to calculate the number of signal strength bars based on the RSSI level of a network.

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
| [Android30AndAbove](-android30-and-above.md) | [androidJvm]<br>constructor(rssiLevel: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [rssiLevel](rssi-level.md) | [androidJvm]<br>val [rssiLevel](rssi-level.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>The RSSI level of the network |

## Functions

| Name | Summary |
|---|---|
| [component1](component1.md) | [androidJvm]<br>operator fun [component1](component1.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [copy](copy.md) | [androidJvm]<br>fun [copy](copy.md)(rssiLevel: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [CalculateSignalLevelRequest.Android30AndAbove](index.md) |
| [equals](../../-compare-signal-level-result/-success/-first-r-s-s-i-value-is-stronger/index.md#585090901%2FFunctions%2F1816002514) | [androidJvm]<br>open operator override fun [equals](../../-compare-signal-level-result/-success/-first-r-s-s-i-value-is-stronger/index.md#585090901%2FFunctions%2F1816002514)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../-compare-signal-level-result/-success/-first-r-s-s-i-value-is-stronger/index.md#1794629105%2FFunctions%2F1816002514) | [androidJvm]<br>open override fun [hashCode](../../-compare-signal-level-result/-success/-first-r-s-s-i-value-is-stronger/index.md#1794629105%2FFunctions%2F1816002514)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../-compare-signal-level-result/-success/-first-r-s-s-i-value-is-stronger/index.md#1616463040%2FFunctions%2F1816002514) | [androidJvm]<br>open override fun [toString](../../-compare-signal-level-result/-success/-first-r-s-s-i-value-is-stronger/index.md#1616463040%2FFunctions%2F1816002514)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
