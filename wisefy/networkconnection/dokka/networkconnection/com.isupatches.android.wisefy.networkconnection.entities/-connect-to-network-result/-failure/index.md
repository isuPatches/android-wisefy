//[networkconnection](../../../../index.md)/[com.isupatches.android.wisefy.networkconnection.entities](../../index.md)/[ConnectToNetworkResult](../index.md)/[Failure](index.md)

# Failure

sealed class [Failure](index.md) : [ConnectToNetworkResult](../index.md)

A set of classes that are data representations of a failure when connecting to a network.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

| |
|---|
| [ConnectToNetworkResult](../index.md) |

#### Inheritors

| |
|---|
| [False](-false/index.md) |
| [NetworkNotFound](-network-not-found/index.md) |
| [Assertion](-assertion/index.md) |

## Types

| Name | Summary |
|---|---|
| [Assertion](-assertion/index.md) | [androidJvm]<br>data class [Assertion](-assertion/index.md)(val message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [ConnectToNetworkResult.Failure](index.md)<br>A representation of a failure connecting to a network due to hitting an unexpected path causing an assertion. |
| [False](-false/index.md) | [androidJvm]<br>object [False](-false/index.md) : [ConnectToNetworkResult.Failure](index.md)<br>A data representation for when there is a failure connecting to a network. |
| [NetworkNotFound](-network-not-found/index.md) | [androidJvm]<br>object [NetworkNotFound](-network-not-found/index.md) : [ConnectToNetworkResult.Failure](index.md)<br>A data representation for when there is no network found to connect to. |

## Functions

| Name | Summary |
|---|---|
| [equals](../../-disconnect-from-current-network-result/-failure/-assertion/index.md#585090901%2FFunctions%2F-1202619134) | [androidJvm]<br>open operator fun [equals](../../-disconnect-from-current-network-result/-failure/-assertion/index.md#585090901%2FFunctions%2F-1202619134)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../-disconnect-from-current-network-result/-failure/-assertion/index.md#1794629105%2FFunctions%2F-1202619134) | [androidJvm]<br>open fun [hashCode](../../-disconnect-from-current-network-result/-failure/-assertion/index.md#1794629105%2FFunctions%2F-1202619134)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../-disconnect-from-current-network-result/-failure/-assertion/index.md#1616463040%2FFunctions%2F-1202619134) | [androidJvm]<br>open fun [toString](../../-disconnect-from-current-network-result/-failure/-assertion/index.md#1616463040%2FFunctions%2F-1202619134)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
