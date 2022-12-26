//[signal](../../../index.md)/[com.isupatches.android.wisefy.signal](../index.md)/[SignalApi](index.md)

# SignalApi

[androidJvm]\
interface [SignalApi](index.md)

A set of synchronous APIs for signal strength functionality.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

## Functions

| Name | Summary |
|---|---|
| [calculateSignalLevel](calculate-signal-level.md) | [androidJvm]<br>abstract fun [calculateSignalLevel](calculate-signal-level.md)(request: [CalculateSignalLevelRequest](../../com.isupatches.android.wisefy.signal.entities/-calculate-signal-level-request/index.md)): [CalculateSignalLevelResult](../../com.isupatches.android.wisefy.signal.entities/-calculate-signal-level-result/index.md)<br>A synchronous API to calculate the number of signal strength bars for a network. |
| [compareSignalLevel](compare-signal-level.md) | [androidJvm]<br>abstract fun [compareSignalLevel](compare-signal-level.md)(request: [CompareSignalLevelRequest](../../com.isupatches.android.wisefy.signal.entities/-compare-signal-level-request/index.md)): [CompareSignalLevelResult](../../com.isupatches.android.wisefy.signal.entities/-compare-signal-level-result/index.md)<br>A synchronous API to compare the signal strength of two networks. |

## Inheritors

| Name |
|---|
| [SignalDelegate](../-signal-delegate/index.md) |
