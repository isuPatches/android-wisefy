//[signal](../../../index.md)/[com.isupatches.android.wisefy.signal](../index.md)/[SignalDelegate](index.md)

# SignalDelegate

[androidJvm]\
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

androidJvm

| |
|---|
| [SignalApi](../-signal-api/index.md) |

## Functions

| Name | Summary |
|---|---|
| [calculateSignalLevel](../-signal-api/calculate-signal-level.md) | [androidJvm]<br>abstract fun [calculateSignalLevel](../-signal-api/calculate-signal-level.md)(request: [CalculateSignalLevelRequest](../../com.isupatches.android.wisefy.signal.entities/-calculate-signal-level-request/index.md)): [CalculateSignalLevelResult](../../com.isupatches.android.wisefy.signal.entities/-calculate-signal-level-result/index.md)<br>A synchronous API to calculate the number of signal strength bars for a network. |
| [compareSignalLevel](../-signal-api/compare-signal-level.md) | [androidJvm]<br>abstract fun [compareSignalLevel](../-signal-api/compare-signal-level.md)(request: [CompareSignalLevelRequest](../../com.isupatches.android.wisefy.signal.entities/-compare-signal-level-request/index.md)): [CompareSignalLevelResult](../../com.isupatches.android.wisefy.signal.entities/-compare-signal-level-result/index.md)<br>A synchronous API to compare the signal strength of two networks. |

## Inheritors

| Name |
|---|
| [WisefySignalDelegate](../-wisefy-signal-delegate/index.md) |
