//[addnetwork](../../../../index.md)/[com.isupatches.android.wisefy.addnetwork.entities](../../index.md)/[AddNetworkResult](../index.md)/[Failure](index.md)

# Failure

sealed class [Failure](index.md) : [AddNetworkResult](../index.md)

A set of classes that denote a failure while attempting to add a network.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

| |
|---|
| [AddNetworkResult](../index.md) |

#### Inheritors

| |
|---|
| [ResultCode](-result-code/index.md) |
| [Assertion](-assertion/index.md) |

## Types

| Name | Summary |
|---|---|
| [Assertion](-assertion/index.md) | [androidJvm]<br>data class [Assertion](-assertion/index.md)(val message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [AddNetworkResult.Failure](index.md)<br>A representation of a failure to add a network due to hitting an unexpected path causing an assertion. |
| [ResultCode](-result-code/index.md) | [androidJvm]<br>data class [ResultCode](-result-code/index.md)(val value: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [AddNetworkResult.Failure](index.md)<br>A representation of a failure while attempting to add a network based on result codes returned from the Android OS. |

## Functions

| Name | Summary |
|---|---|
| [equals](-assertion/index.md#585090901%2FFunctions%2F-271260435) | [androidJvm]<br>open operator fun [equals](-assertion/index.md#585090901%2FFunctions%2F-271260435)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](-assertion/index.md#1794629105%2FFunctions%2F-271260435) | [androidJvm]<br>open fun [hashCode](-assertion/index.md#1794629105%2FFunctions%2F-271260435)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](-assertion/index.md#1616463040%2FFunctions%2F-271260435) | [androidJvm]<br>open fun [toString](-assertion/index.md#1616463040%2FFunctions%2F-271260435)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
