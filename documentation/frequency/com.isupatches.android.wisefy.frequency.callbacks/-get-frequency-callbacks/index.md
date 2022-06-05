//[frequency](../../../index.md)/[com.isupatches.android.wisefy.frequency.callbacks](../index.md)/[GetFrequencyCallbacks](index.md)

# GetFrequencyCallbacks

[androidJvm]\
interface [GetFrequencyCallbacks](index.md) : BaseWisefyCallbacks

A set of callbacks for getting the frequency of a network.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| com.isupatches.android.wisefy.core.base.BaseWisefyCallbacks |  |

## Functions

| Name | Summary |
|---|---|
| [equals](../../com.isupatches.android.wisefy.frequency.entities/-is-network5g-hz-result/-false/index.md#585090901%2FFunctions%2F-831600846) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.frequency.entities/-is-network5g-hz-result/-false/index.md#585090901%2FFunctions%2F-831600846)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../com.isupatches.android.wisefy.frequency.entities/-is-network5g-hz-result/-false/index.md#1794629105%2FFunctions%2F-831600846) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.frequency.entities/-is-network5g-hz-result/-false/index.md#1794629105%2FFunctions%2F-831600846)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [onFailureRetrievingFrequency](on-failure-retrieving-frequency.md) | [androidJvm]<br>abstract fun [onFailureRetrievingFrequency](on-failure-retrieving-frequency.md)()<br>A callback triggered when there is a failure getting the frequency of a network. |
| [onFrequencyRetrieved](on-frequency-retrieved.md) | [androidJvm]<br>abstract fun [onFrequencyRetrieved](on-frequency-retrieved.md)(frequency: [FrequencyData](../../com.isupatches.android.wisefy.frequency.entities/-frequency-data/index.md))<br>A callback triggered when there is a success getting the frequency of a network. |
| [onWisefyAsyncFailure](index.md#823639724%2FFunctions%2F-831600846) | [androidJvm]<br>abstract fun [onWisefyAsyncFailure](index.md#823639724%2FFunctions%2F-831600846)(throwable: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)) |
| [toString](../../com.isupatches.android.wisefy.frequency.entities/-is-network5g-hz-result/-false/index.md#1616463040%2FFunctions%2F-831600846) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.frequency.entities/-is-network5g-hz-result/-false/index.md#1616463040%2FFunctions%2F-831600846)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
