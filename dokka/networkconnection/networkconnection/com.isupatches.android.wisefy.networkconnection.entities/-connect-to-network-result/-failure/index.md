//[networkconnection](../../../../index.md)/[com.isupatches.android.wisefy.networkconnection.entities](../../index.md)/[ConnectToNetworkResult](../index.md)/[Failure](index.md)

# Failure

[androidJvm]\
sealed class [Failure](index.md) : [ConnectToNetworkResult](../index.md)

A set of classes that are data representations of a failure when connecting to a network.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.networkconnection.entities.ConnectToNetworkResult](../index.md) |  |

## Types

| Name | Summary |
|---|---|
| [Assertion](-assertion/index.md) | [androidJvm]<br>data class [Assertion](-assertion/index.md)(val message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [ConnectToNetworkResult.Failure](index.md)<br>A representation of a failure connecting to a network due to hitting an unexpected path causing an assertion. |
| [False](-false/index.md) | [androidJvm]<br>object [False](-false/index.md) : [ConnectToNetworkResult.Failure](index.md)<br>A data representation for when there is a failure connecting to a network. |
| [NetworkNotFound](-network-not-found/index.md) | [androidJvm]<br>object [NetworkNotFound](-network-not-found/index.md) : [ConnectToNetworkResult.Failure](index.md)<br>A data representation for when there is no network found to connect to. |

## Inheritors

| Name |
|---|
| [False](-false/index.md) |
| [NetworkNotFound](-network-not-found/index.md) |
| [Assertion](-assertion/index.md) |
