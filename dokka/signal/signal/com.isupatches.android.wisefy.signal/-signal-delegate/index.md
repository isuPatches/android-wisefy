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

## Inheritors

| Name |
|---|
| [WisefySignalDelegate](../-wisefy-signal-delegate/index.md) |
