//[addnetwork](../../../../index.md)/[com.isupatches.android.wisefy.addnetwork.entities](../../index.md)/[AddNetworkResult](../index.md)/[Success](index.md)

# Success

[androidJvm]\
sealed class [Success](index.md) : [AddNetworkResult](../index.md)

A set of classes that are used to denote a success while attempting to add a network.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.addnetwork.entities.AddNetworkResult](../index.md) |  |

## Types

| Name | Summary |
|---|---|
| [IntentLaunched](-intent-launched/index.md) | [androidJvm]<br>object [IntentLaunched](-intent-launched/index.md) : [AddNetworkResult.Success](index.md)<br>A data representation of a success while launching a network suggestion intent in Android 30. |
| [ResultCode](-result-code/index.md) | [androidJvm]<br>data class [ResultCode](-result-code/index.md)(val value: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [AddNetworkResult.Success](index.md)<br>A data representation of a success while attempting to add a network based on Android OS level returns. |

## Inheritors

| Name |
|---|
| [ResultCode](-result-code/index.md) |
| [IntentLaunched](-intent-launched/index.md) |
