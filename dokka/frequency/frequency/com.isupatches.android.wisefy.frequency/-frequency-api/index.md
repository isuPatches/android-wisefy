//[frequency](../../../index.md)/[com.isupatches.android.wisefy.frequency](../index.md)/[FrequencyApi](index.md)

# FrequencyApi

[androidJvm]\
interface [FrequencyApi](index.md)

A set of synchronous APIs for getting the frequency of a network.

#### Author

Patches Klinefelter

#### Since

03/2022

## Functions

| Name | Summary |
|---|---|
| [getFrequency](get-frequency.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_FINE_LOCATION&quot;)<br>abstract fun [getFrequency](get-frequency.md)(request: [GetFrequencyRequest](../../com.isupatches.android.wisefy.frequency.entities/-get-frequency-request/index.md) = GetFrequencyRequest.CurrentNetwork): [GetFrequencyResult](../../com.isupatches.android.wisefy.frequency.entities/-get-frequency-result/index.md)<br>A synchronous API to get the frequency of the current network. |
| [isNetwork5gHz](is-network5g-hz.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_FINE_LOCATION&quot;)<br>abstract fun [isNetwork5gHz](is-network5g-hz.md)(request: [IsNetwork5gHzRequest](../../com.isupatches.android.wisefy.frequency.entities/-is-network5g-hz-request/index.md) = IsNetwork5gHzRequest.CurrentNetwork): [IsNetwork5gHzResult](../../com.isupatches.android.wisefy.frequency.entities/-is-network5g-hz-result/index.md)<br>A synchronous API to check if the frequency of the current network is 5G. |

## Inheritors

| Name |
|---|
| [FrequencyDelegate](../-frequency-delegate/index.md) |
