//[frequency](../../../index.md)/[com.isupatches.android.wisefy.frequency](../index.md)/[FrequencyApi](index.md)/[isNetwork5gHz](is-network5g-hz.md)

# isNetwork5gHz

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_FINE_LOCATION&quot;)

abstract fun [isNetwork5gHz](is-network5g-hz.md)(request: [IsNetwork5gHzRequest](../../com.isupatches.android.wisefy.frequency.entities/-is-network5g-hz-request/index.md) = IsNetwork5gHzRequest.CurrentNetwork): [IsNetwork5gHzResult](../../com.isupatches.android.wisefy.frequency.entities/-is-network5g-hz-result/index.md)

A synchronous API to check if the frequency of the current network is 5G.

#### Return

IsNetwork5gHzResult - The result of whether the network is 5G or not

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.frequency.entities.IsNetwork5gHzRequest](../../com.isupatches.android.wisefy.frequency.entities/-is-network5g-hz-request/index.md) |  |
| [com.isupatches.android.wisefy.frequency.entities.IsNetwork5gHzResult](../../com.isupatches.android.wisefy.frequency.entities/-is-network5g-hz-result/index.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| request | The details of the request to check if a network is 5G |
