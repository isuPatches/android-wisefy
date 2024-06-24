//[networkconnection](../../../../index.md)/[com.isupatches.android.wisefy.networkconnection.entities](../../index.md)/[DisconnectFromCurrentNetworkResult](../index.md)/[Failure](index.md)

# Failure

sealed class [Failure](index.md) : [DisconnectFromCurrentNetworkResult](../index.md)

A set of classes and objects that are representations of a failure when disconnecting from the current network.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

| |
|---|
| [DisconnectFromCurrentNetworkResult](../index.md) |

#### Inheritors

| |
|---|
| [False](-false/index.md) |
| [Assertion](-assertion/index.md) |

## Types

| Name | Summary |
|---|---|
| [Assertion](-assertion/index.md) | [androidJvm]<br>data class [Assertion](-assertion/index.md)(val message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [DisconnectFromCurrentNetworkResult.Failure](index.md)<br>A representation of a failure disconnecting from the current network due to hitting an unexpected path causing an assertion. |
| [False](-false/index.md) | [androidJvm]<br>object [False](-false/index.md) : [DisconnectFromCurrentNetworkResult.Failure](index.md)<br>A data representation for when there is a failure disconnecting from the current network. |

## Functions

| Name | Summary |
|---|---|
| [equals](-assertion/index.md#585090901%2FFunctions%2F-1202619134) | [androidJvm]<br>open operator fun [equals](-assertion/index.md#585090901%2FFunctions%2F-1202619134)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](-assertion/index.md#1794629105%2FFunctions%2F-1202619134) | [androidJvm]<br>open fun [hashCode](-assertion/index.md#1794629105%2FFunctions%2F-1202619134)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](-assertion/index.md#1616463040%2FFunctions%2F-1202619134) | [androidJvm]<br>open fun [toString](-assertion/index.md#1616463040%2FFunctions%2F-1202619134)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
