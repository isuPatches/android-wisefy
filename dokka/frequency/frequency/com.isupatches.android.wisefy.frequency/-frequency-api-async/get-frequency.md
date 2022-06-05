//[frequency](../../../index.md)/[com.isupatches.android.wisefy.frequency](../index.md)/[FrequencyApiAsync](index.md)/[getFrequency](get-frequency.md)

# getFrequency

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_FINE_LOCATION&quot;)

abstract fun [getFrequency](get-frequency.md)(request: [GetFrequencyRequest](../../com.isupatches.android.wisefy.frequency.entities/-get-frequency-request/index.md) = GetFrequencyRequest.CurrentNetwork, callbacks: [GetFrequencyCallbacks](../../com.isupatches.android.wisefy.frequency.callbacks/-get-frequency-callbacks/index.md)?)

An asynchronous API to get the frequency of the current network.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.frequency.entities.GetFrequencyRequest](../../com.isupatches.android.wisefy.frequency.entities/-get-frequency-request/index.md) |  |
| [com.isupatches.android.wisefy.frequency.callbacks.GetFrequencyCallbacks](../../com.isupatches.android.wisefy.frequency.callbacks/-get-frequency-callbacks/index.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| request | The details of the request to get the frequency of a network |
| callbacks | The callbacks for when the frequency is returned |
