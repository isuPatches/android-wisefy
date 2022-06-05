//[signal](../../../index.md)/[com.isupatches.android.wisefy.signal](../index.md)/[SignalApi](index.md)

# SignalApi

[androidJvm]\
interface [SignalApi](index.md)

A set of synchronous APIs for signal strength functionality.

#### Author

Patches Klinefelter

#### Since

03/2022

## Functions

| Name | Summary |
|---|---|
| [calculateBars](calculate-bars.md) | [androidJvm]<br>abstract fun [calculateBars](calculate-bars.md)(request: [CalculateBarsRequest](../../com.isupatches.android.wisefy.signal.entities/-calculate-bars-request/index.md)): [CalculateBarsResult](../../com.isupatches.android.wisefy.signal.entities/-calculate-bars-result/index.md)<br>A synchronous API to calculate the number of signal strength bars for a network. |
| [compareSignalLevel](compare-signal-level.md) | [androidJvm]<br>abstract fun [compareSignalLevel](compare-signal-level.md)(request: [CompareSignalLevelRequest](../../com.isupatches.android.wisefy.signal.entities/-compare-signal-level-request/index.md)): [CompareSignalLevelResult](../../com.isupatches.android.wisefy.signal.entities/-compare-signal-level-result/index.md)<br>A synchronous API to compare the signal strength of two networks. |

## Inheritors

| Name |
|---|
| [SignalDelegate](../-signal-delegate/index.md) |
