//[removenetwork](../../../../index.md)/[com.isupatches.android.wisefy.removenetwork.entities](../../index.md)/[RemoveNetworkResult](../index.md)/[Failure](index.md)

# Failure

[androidJvm]\
sealed class [Failure](index.md) : [RemoveNetworkResult](../index.md)

A set of classes that are used to denote a failure while attempting to remove a network.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkResult](../index.md) |  |

## Types

| Name | Summary |
|---|---|
| [False](-false/index.md) | [androidJvm]<br>object [False](-false/index.md) : [RemoveNetworkResult.Failure](index.md)<br>A data representation of a failure removing a network on older Android OS levels. |
| [NetworkNotFound](-network-not-found/index.md) | [androidJvm]<br>object [NetworkNotFound](-network-not-found/index.md) : [RemoveNetworkResult.Failure](index.md)<br>A data representation of a failure finding the network to remove. |
| [ResultCode](-result-code/index.md) | [androidJvm]<br>data class [ResultCode](-result-code/index.md)(val value: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [RemoveNetworkResult.Failure](index.md)<br>A data representation of a failure removing a network based on Android OS level returns. |

## Inheritors

| Name |
|---|
| [False](-false/index.md) |
| [NetworkNotFound](-network-not-found/index.md) |
| [ResultCode](-result-code/index.md) |
