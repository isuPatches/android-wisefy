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
| [equals](../../com.isupatches.android.wisefy.frequency.entities/-is-network5g-hz-result/-false/index.md#585090901%2FFunctions%2F-831600846) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.frequency.entities/-is-network5g-hz-result/-false/index.md#585090901%2FFunctions%2F-831600846)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getFrequency](get-frequency.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_FINE_LOCATION&quot;)<br>abstract fun [getFrequency](get-frequency.md)(request: [GetFrequencyRequest](../../com.isupatches.android.wisefy.frequency.entities/-get-frequency-request/index.md) = GetFrequencyRequest.CurrentNetwork): [GetFrequencyResult](../../com.isupatches.android.wisefy.frequency.entities/-get-frequency-result/index.md)<br>A synchronous API to get the frequency of the current network. |
| [hashCode](../../com.isupatches.android.wisefy.frequency.entities/-is-network5g-hz-result/-false/index.md#1794629105%2FFunctions%2F-831600846) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.frequency.entities/-is-network5g-hz-result/-false/index.md#1794629105%2FFunctions%2F-831600846)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [isNetwork5gHz](is-network5g-hz.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_FINE_LOCATION&quot;)<br>abstract fun [isNetwork5gHz](is-network5g-hz.md)(request: [IsNetwork5gHzRequest](../../com.isupatches.android.wisefy.frequency.entities/-is-network5g-hz-request/index.md) = IsNetwork5gHzRequest.CurrentNetwork): [IsNetwork5gHzResult](../../com.isupatches.android.wisefy.frequency.entities/-is-network5g-hz-result/index.md)<br>A synchronous API to check if the frequency of the current network is 5G. |
| [toString](../../com.isupatches.android.wisefy.frequency.entities/-is-network5g-hz-result/-false/index.md#1616463040%2FFunctions%2F-831600846) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.frequency.entities/-is-network5g-hz-result/-false/index.md#1616463040%2FFunctions%2F-831600846)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Inheritors

| Name |
|---|
| [FrequencyDelegate](../-frequency-delegate/index.md) |
