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

## Functions

| Name | Summary |
|---|---|
| [equals](-result-code/index.md#585090901%2FFunctions%2F-2039424092) | [androidJvm]<br>open operator fun [equals](-result-code/index.md#585090901%2FFunctions%2F-2039424092)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](-result-code/index.md#1794629105%2FFunctions%2F-2039424092) | [androidJvm]<br>open fun [hashCode](-result-code/index.md#1794629105%2FFunctions%2F-2039424092)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](-result-code/index.md#1616463040%2FFunctions%2F-2039424092) | [androidJvm]<br>open fun [toString](-result-code/index.md#1616463040%2FFunctions%2F-2039424092)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Inheritors

| Name |
|---|
| [False](-false/index.md) |
| [NetworkNotFound](-network-not-found/index.md) |
| [ResultCode](-result-code/index.md) |
