//[signal](../../../index.md)/[com.isupatches.android.wisefy.signal](../index.md)/[SignalApi](index.md)

# SignalApi

interface [SignalApi](index.md)

A set of synchronous APIs for signal strength functionality.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### Inheritors

| |
|---|
| [SignalDelegate](../-signal-delegate/index.md) |

## Functions

| Name | Summary |
|---|---|
| [calculateSignalLevel](calculate-signal-level.md) | [androidJvm]<br>abstract fun [calculateSignalLevel](calculate-signal-level.md)(request: [CalculateSignalLevelRequest](../../com.isupatches.android.wisefy.signal.entities/-calculate-signal-level-request/index.md)): [CalculateSignalLevelResult](../../com.isupatches.android.wisefy.signal.entities/-calculate-signal-level-result/index.md)<br>A synchronous API to calculate the number of signal strength bars for a network. |
| [compareSignalLevel](compare-signal-level.md) | [androidJvm]<br>abstract fun [compareSignalLevel](compare-signal-level.md)(request: [CompareSignalLevelRequest](../../com.isupatches.android.wisefy.signal.entities/-compare-signal-level-request/index.md)): [CompareSignalLevelResult](../../com.isupatches.android.wisefy.signal.entities/-compare-signal-level-result/index.md)<br>A synchronous API to compare the signal strength of two networks. |
| [equals](../../com.isupatches.android.wisefy.signal.entities/-compare-signal-level-result/-success/-first-r-s-s-i-value-is-stronger/index.md#585090901%2FFunctions%2F1816002514) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.signal.entities/-compare-signal-level-result/-success/-first-r-s-s-i-value-is-stronger/index.md#585090901%2FFunctions%2F1816002514)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../com.isupatches.android.wisefy.signal.entities/-compare-signal-level-result/-success/-first-r-s-s-i-value-is-stronger/index.md#1794629105%2FFunctions%2F1816002514) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.signal.entities/-compare-signal-level-result/-success/-first-r-s-s-i-value-is-stronger/index.md#1794629105%2FFunctions%2F1816002514)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../com.isupatches.android.wisefy.signal.entities/-compare-signal-level-result/-success/-first-r-s-s-i-value-is-stronger/index.md#1616463040%2FFunctions%2F1816002514) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.signal.entities/-compare-signal-level-result/-success/-first-r-s-s-i-value-is-stronger/index.md#1616463040%2FFunctions%2F1816002514)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
