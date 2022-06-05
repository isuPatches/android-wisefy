//[frequency](../../../index.md)/[com.isupatches.android.wisefy.frequency](../index.md)/[FrequencyApi](index.md)/[getFrequency](get-frequency.md)

# getFrequency

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_FINE_LOCATION&quot;)

abstract fun [getFrequency](get-frequency.md)(request: [GetFrequencyRequest](../../com.isupatches.android.wisefy.frequency.entities/-get-frequency-request/index.md) = GetFrequencyRequest.CurrentNetwork): [GetFrequencyResult](../../com.isupatches.android.wisefy.frequency.entities/-get-frequency-result/index.md)

A synchronous API to get the frequency of the current network.

#### Return

GetFrequencyResult - The result of getting the frequency of a network

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.frequency.entities.GetFrequencyRequest](../../com.isupatches.android.wisefy.frequency.entities/-get-frequency-request/index.md) |  |
| [com.isupatches.android.wisefy.frequency.entities.GetFrequencyResult](../../com.isupatches.android.wisefy.frequency.entities/-get-frequency-result/index.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| request | The details of the request to get the frequency of a network |
