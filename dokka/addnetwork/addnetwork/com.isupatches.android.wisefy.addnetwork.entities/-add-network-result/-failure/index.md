//[addnetwork](../../../../index.md)/[com.isupatches.android.wisefy.addnetwork.entities](../../index.md)/[AddNetworkResult](../index.md)/[Failure](index.md)

# Failure

[androidJvm]\
sealed class [Failure](index.md) : [AddNetworkResult](../index.md)

A set of classes that are used to denote a failure while attempting to add a network.

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
| [ResultCode](-result-code/index.md) | [androidJvm]<br>data class [ResultCode](-result-code/index.md)(val value: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [AddNetworkResult.Failure](index.md)<br>A data representation of a failure to add a network based on Android OS level returns. |
| [WrongSDKLevel](-wrong-s-d-k-level/index.md) | [androidJvm]<br>data class [WrongSDKLevel](-wrong-s-d-k-level/index.md)(val message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [AddNetworkResult.Failure](index.md)<br>A data representation of a failure to add a network due to a function being called for the wrong SDK level. |

## Inheritors

| Name |
|---|
| [ResultCode](-result-code/index.md) |
| [WrongSDKLevel](-wrong-s-d-k-level/index.md) |
