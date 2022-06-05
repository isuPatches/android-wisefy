//[frequency](../../../index.md)/[com.isupatches.android.wisefy.frequency](../index.md)/[FrequencyDelegate](index.md)

# FrequencyDelegate

[androidJvm]\
interface [FrequencyDelegate](index.md) : [FrequencyApi](../-frequency-api/index.md), [FrequencyApiAsync](../-frequency-api-async/index.md)

A delegate for synchronous and asynchronous APIs to get the frequency of a network.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.frequency.FrequencyApi](../-frequency-api/index.md) |  |
| [com.isupatches.android.wisefy.frequency.FrequencyApiAsync](../-frequency-api-async/index.md) |  |

## Functions

| Name | Summary |
|---|---|
| [getFrequency](../-frequency-api/get-frequency.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_FINE_LOCATION&quot;)<br>abstract fun [getFrequency](../-frequency-api/get-frequency.md)(request: [GetFrequencyRequest](../../com.isupatches.android.wisefy.frequency.entities/-get-frequency-request/index.md) = GetFrequencyRequest.CurrentNetwork): [GetFrequencyResult](../../com.isupatches.android.wisefy.frequency.entities/-get-frequency-result/index.md)<br>A synchronous API to get the frequency of the current network.<br>[androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_FINE_LOCATION&quot;)<br>abstract fun [getFrequency](../-frequency-api-async/get-frequency.md)(request: [GetFrequencyRequest](../../com.isupatches.android.wisefy.frequency.entities/-get-frequency-request/index.md) = GetFrequencyRequest.CurrentNetwork, callbacks: [GetFrequencyCallbacks](../../com.isupatches.android.wisefy.frequency.callbacks/-get-frequency-callbacks/index.md)?)<br>An asynchronous API to get the frequency of the current network. |
| [isNetwork5gHz](../-frequency-api/is-network5g-hz.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_FINE_LOCATION&quot;)<br>abstract fun [isNetwork5gHz](../-frequency-api/is-network5g-hz.md)(request: [IsNetwork5gHzRequest](../../com.isupatches.android.wisefy.frequency.entities/-is-network5g-hz-request/index.md) = IsNetwork5gHzRequest.CurrentNetwork): [IsNetwork5gHzResult](../../com.isupatches.android.wisefy.frequency.entities/-is-network5g-hz-result/index.md)<br>A synchronous API to check if the frequency of the current network is 5G. |

## Inheritors

| Name |
|---|
| [WisefyFrequencyDelegate](../-wisefy-frequency-delegate/index.md) |
