//[frequency](../../../index.md)/[com.isupatches.android.wisefy.frequency](../index.md)/[FrequencyApiAsync](index.md)

# FrequencyApiAsync

[androidJvm]\
interface [FrequencyApiAsync](index.md)

A set of asynchronous APIs for getting the frequency of a network.

#### Author

Patches Klinefelter

#### Since

03/2022

## Functions

| Name | Summary |
|---|---|
| [getFrequency](get-frequency.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_FINE_LOCATION&quot;)<br>abstract fun [getFrequency](get-frequency.md)(request: [GetFrequencyRequest](../../com.isupatches.android.wisefy.frequency.entities/-get-frequency-request/index.md) = GetFrequencyRequest.CurrentNetwork, callbacks: [GetFrequencyCallbacks](../../com.isupatches.android.wisefy.frequency.callbacks/-get-frequency-callbacks/index.md)?)<br>An asynchronous API to get the frequency of the current network. |

## Inheritors

| Name |
|---|
| [FrequencyDelegate](../-frequency-delegate/index.md) |
