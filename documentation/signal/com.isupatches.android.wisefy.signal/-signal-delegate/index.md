//[signal](../../../index.md)/[com.isupatches.android.wisefy.signal](../index.md)/[SignalDelegate](index.md)

# SignalDelegate

[androidJvm]\
interface [SignalDelegate](index.md) : [SignalApi](../-signal-api/index.md)

A delegate for synchronous signal strength APIs.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.signal.SignalApi](../-signal-api/index.md) |  |

## Functions

| Name | Summary |
|---|---|
| [calculateBars](../-signal-api/calculate-bars.md) | [androidJvm]<br>abstract fun [calculateBars](../-signal-api/calculate-bars.md)(request: [CalculateBarsRequest](../../com.isupatches.android.wisefy.signal.entities/-calculate-bars-request/index.md)): [CalculateBarsResult](../../com.isupatches.android.wisefy.signal.entities/-calculate-bars-result/index.md)<br>A synchronous API to calculate the number of signal strength bars for a network. |
| [compareSignalLevel](../-signal-api/compare-signal-level.md) | [androidJvm]<br>abstract fun [compareSignalLevel](../-signal-api/compare-signal-level.md)(request: [CompareSignalLevelRequest](../../com.isupatches.android.wisefy.signal.entities/-compare-signal-level-request/index.md)): [CompareSignalLevelResult](../../com.isupatches.android.wisefy.signal.entities/-compare-signal-level-result/index.md)<br>A synchronous API to compare the signal strength of two networks. |
| [equals](../../com.isupatches.android.wisefy.signal.entities/-compare-signal-level-result/index.md#585090901%2FFunctions%2F1816002514) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.signal.entities/-compare-signal-level-result/index.md#585090901%2FFunctions%2F1816002514)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../com.isupatches.android.wisefy.signal.entities/-compare-signal-level-result/index.md#1794629105%2FFunctions%2F1816002514) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.signal.entities/-compare-signal-level-result/index.md#1794629105%2FFunctions%2F1816002514)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../com.isupatches.android.wisefy.signal.entities/-compare-signal-level-result/index.md#1616463040%2FFunctions%2F1816002514) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.signal.entities/-compare-signal-level-result/index.md#1616463040%2FFunctions%2F1816002514)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Inheritors

| Name |
|---|
| [WisefySignalDelegate](../-wisefy-signal-delegate/index.md) |
