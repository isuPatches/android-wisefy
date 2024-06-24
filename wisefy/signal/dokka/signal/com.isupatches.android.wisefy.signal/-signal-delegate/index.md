//[signal](../../../index.md)/[com.isupatches.android.wisefy.signal](../index.md)/[SignalDelegate](index.md)

# SignalDelegate

interface [SignalDelegate](index.md) : [SignalApi](../-signal-api/index.md)

A delegate for synchronous signal strength APIs.

*Notes*

- 
   No async APIs because [SignalApi](../-signal-api/index.md) operates directly on input from the client (f.e. raw RSSI values)

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

| |
|---|
| [SignalApi](../-signal-api/index.md) |

#### Inheritors

| |
|---|
| [WisefySignalDelegate](../-wisefy-signal-delegate/index.md) |

## Functions

| Name | Summary |
|---|---|
| [calculateSignalLevel](../-signal-api/calculate-signal-level.md) | [androidJvm]<br>abstract fun [calculateSignalLevel](../-signal-api/calculate-signal-level.md)(request: [CalculateSignalLevelRequest](../../com.isupatches.android.wisefy.signal.entities/-calculate-signal-level-request/index.md)): [CalculateSignalLevelResult](../../com.isupatches.android.wisefy.signal.entities/-calculate-signal-level-result/index.md)<br>A synchronous API to calculate the number of signal strength bars for a network. |
| [compareSignalLevel](../-signal-api/compare-signal-level.md) | [androidJvm]<br>abstract fun [compareSignalLevel](../-signal-api/compare-signal-level.md)(request: [CompareSignalLevelRequest](../../com.isupatches.android.wisefy.signal.entities/-compare-signal-level-request/index.md)): [CompareSignalLevelResult](../../com.isupatches.android.wisefy.signal.entities/-compare-signal-level-result/index.md)<br>A synchronous API to compare the signal strength of two networks. |
| [equals](../../com.isupatches.android.wisefy.signal.entities/-compare-signal-level-result/-success/-first-r-s-s-i-value-is-stronger/index.md#585090901%2FFunctions%2F1816002514) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.signal.entities/-compare-signal-level-result/-success/-first-r-s-s-i-value-is-stronger/index.md#585090901%2FFunctions%2F1816002514)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../com.isupatches.android.wisefy.signal.entities/-compare-signal-level-result/-success/-first-r-s-s-i-value-is-stronger/index.md#1794629105%2FFunctions%2F1816002514) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.signal.entities/-compare-signal-level-result/-success/-first-r-s-s-i-value-is-stronger/index.md#1794629105%2FFunctions%2F1816002514)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../com.isupatches.android.wisefy.signal.entities/-compare-signal-level-result/-success/-first-r-s-s-i-value-is-stronger/index.md#1616463040%2FFunctions%2F1816002514) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.signal.entities/-compare-signal-level-result/-success/-first-r-s-s-i-value-is-stronger/index.md#1616463040%2FFunctions%2F1816002514)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
