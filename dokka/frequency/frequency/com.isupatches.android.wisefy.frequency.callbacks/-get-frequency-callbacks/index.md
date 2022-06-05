//[frequency](../../../index.md)/[com.isupatches.android.wisefy.frequency.callbacks](../index.md)/[GetFrequencyCallbacks](index.md)

# GetFrequencyCallbacks

[androidJvm]\
interface [GetFrequencyCallbacks](index.md) : [BaseWisefyCallbacks](../../../../core/core/com.isupatches.android.wisefy.core.base/-base-wisefy-callbacks/index.md)

A set of callbacks for getting the frequency of a network.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.core.base.BaseWisefyCallbacks](../../../../core/core/com.isupatches.android.wisefy.core.base/-base-wisefy-callbacks/index.md) |  |

## Functions

| Name | Summary |
|---|---|
| [onFailureRetrievingFrequency](on-failure-retrieving-frequency.md) | [androidJvm]<br>abstract fun [onFailureRetrievingFrequency](on-failure-retrieving-frequency.md)()<br>A callback triggered when there is a failure getting the frequency of a network. |
| [onFrequencyRetrieved](on-frequency-retrieved.md) | [androidJvm]<br>abstract fun [onFrequencyRetrieved](on-frequency-retrieved.md)(frequency: [FrequencyData](../../com.isupatches.android.wisefy.frequency.entities/-frequency-data/index.md))<br>A callback triggered when there is a success getting the frequency of a network. |
| [onWisefyAsyncFailure](index.md#823639724%2FFunctions%2F459397331) | [androidJvm]<br>abstract fun [onWisefyAsyncFailure](index.md#823639724%2FFunctions%2F459397331)(throwable: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)) |
