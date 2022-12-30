//[addnetwork](../../../../index.md)/[com.isupatches.android.wisefy.addnetwork.entities](../../index.md)/[AddNetworkResult](../index.md)/[Failure](index.md)

# Failure

[androidJvm]\
sealed class [Failure](index.md) : [AddNetworkResult](../index.md)

A set of classes that denote a failure while attempting to add a network.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

androidJvm

| |
|---|
| [AddNetworkResult](../index.md) |

## Types

| Name | Summary |
|---|---|
| [Assertion](-assertion/index.md) | [androidJvm]<br>data class [Assertion](-assertion/index.md)(val message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [AddNetworkResult.Failure](index.md)<br>A representation of a failure to add a network due to hitting an unexpected path causing an assertion. |
| [ResultCode](-result-code/index.md) | [androidJvm]<br>data class [ResultCode](-result-code/index.md)(val value: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [AddNetworkResult.Failure](index.md)<br>A representation of a failure while attempting to add a network based on result codes returned from the Android OS. |

## Inheritors

| Name |
|---|
| [ResultCode](-result-code/index.md) |
| [Assertion](-assertion/index.md) |
