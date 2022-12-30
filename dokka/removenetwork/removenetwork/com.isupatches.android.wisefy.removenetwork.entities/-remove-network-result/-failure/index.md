//[removenetwork](../../../../index.md)/[com.isupatches.android.wisefy.removenetwork.entities](../../index.md)/[RemoveNetworkResult](../index.md)/[Failure](index.md)

# Failure

[androidJvm]\
sealed class [Failure](index.md) : [RemoveNetworkResult](../index.md)

A set of classes that are used to denote a failure while attempting to remove a network.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

androidJvm

| |
|---|
| [RemoveNetworkResult](../index.md) |

## Types

| Name | Summary |
|---|---|
| [Assertion](-assertion/index.md) | [androidJvm]<br>data class [Assertion](-assertion/index.md)(val message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [RemoveNetworkResult.Failure](index.md)<br>A representation of a failure removing a network due to hitting an unexpected path causing an assertion. |
| [False](-false/index.md) | [androidJvm]<br>object [False](-false/index.md) : [RemoveNetworkResult.Failure](index.md)<br>A data representation of a failure removing a network on older Android OS levels. |
| [ResultCode](-result-code/index.md) | [androidJvm]<br>data class [ResultCode](-result-code/index.md)(val value: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [RemoveNetworkResult.Failure](index.md)<br>A data representation of a failure removing a network based on Android OS level returns. |

## Inheritors

| Name |
|---|
| [False](-false/index.md) |
| [ResultCode](-result-code/index.md) |
| [Assertion](-assertion/index.md) |
